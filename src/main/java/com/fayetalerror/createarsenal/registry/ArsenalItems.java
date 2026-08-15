package com.fayetalerror.createarsenal.registry;

import com.fayetalerror.createarsenal.CreateArsenal;
import com.fayetalerror.createarsenal.config.ArmorDefinition;
import com.fayetalerror.createarsenal.config.ArsenalDefinition;
import com.fayetalerror.createarsenal.config.ArsenalDefinitionLoader;
import com.fayetalerror.createarsenal.config.ArsenalItemDefinition;
import com.fayetalerror.createarsenal.config.ItemRegistration;
import com.fayetalerror.createarsenal.config.ToolDefinition;
import com.fayetalerror.createarsenal.config.ToolType;
import com.fayetalerror.createarsenal.config.WeaponDefinition;
import com.fayetalerror.createarsenal.config.WeaponType;
import com.fayetalerror.createarsenal.config.TierDefinition;
import com.fayetalerror.createarsenal.item.ArsenalItem;
import com.fayetalerror.createarsenal.item.ArsenalToolTiers;
import com.fayetalerror.createarsenal.item.armor.ArsenalArmorItem;
import com.fayetalerror.createarsenal.item.tools.ArsenalAxeItem;
import com.fayetalerror.createarsenal.item.tools.ArsenalHoeItem;
import com.fayetalerror.createarsenal.item.tools.ArsenalPaxelItem;
import com.fayetalerror.createarsenal.item.tools.ArsenalPickaxeItem;
import com.fayetalerror.createarsenal.item.tools.ArsenalShovelItem;
import com.fayetalerror.createarsenal.item.weapons.ArsenalSwordItem;
import com.fayetalerror.createarsenal.item.weapons.ArsenalBowItem;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tier;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

/** Registers every JSON-described item through generic behavior factories. */
public final class ArsenalItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(CreateArsenal.MODID);

    private static final List<ItemRegistration> REGISTRATIONS =
            ArsenalDefinitionLoader.loadRegistrations(definitionPath("registrations"));
    private static final Map<String, ArsenalDefinition> DEFINITIONS = loadDefinitions();

    private static final Map<String, Tier> TIERS = loadTierDefinitions();

    private static final Map<ToolType, ToolFactory> TOOL_FACTORIES = Map.of(
            ToolType.PICKAXE, new ToolFactory((tier, definition) -> new ArsenalPickaxeItem(
                    tier, definition.properties(tier), definition.modelPath(), definition.animationPath())),
            ToolType.AXE, new ToolFactory((tier, definition) -> new ArsenalAxeItem(
                    tier, definition.properties(tier), definition.modelPath(), definition.animationPath())),
            ToolType.SHOVEL, new ToolFactory((tier, definition) -> new ArsenalShovelItem(
                    tier, definition.properties(tier), definition.modelPath(), definition.animationPath())),
            ToolType.HOE, new ToolFactory((tier, definition) -> new ArsenalHoeItem(
                    tier, definition.properties(tier), definition.modelPath(), definition.animationPath())),
            ToolType.MULTI_TOOL, new ToolFactory((tier, definition) -> new ArsenalPaxelItem(
                    tier, definition.properties(tier), definition.modelPath())));

    private static final Map<WeaponType, WeaponFactory> WEAPON_FACTORIES = Map.of(
            WeaponType.SWORD, (tier, definition) -> new ArsenalSwordItem(
                    tier, definition.properties(tier), definition.modelPath(), definition.animationPath()),
            WeaponType.BOW, (tier, definition) -> new ArsenalBowItem(
                    definition.properties(tier), definition.modelPath(), definition.animationPath(),
                    definition.arrowDamageBonus()));

    public static final Map<String, DeferredItem<? extends Item>> ITEMS_BY_ID = registerAll();

    /** Resolves a registered item by its data-defined registry ID. */
    public static Item item(String id) {
        DeferredItem<? extends Item> holder = ITEMS_BY_ID.get(id);
        if (holder != null) return holder.get();

        Item blockItem = ArsenalBlocks.findItem(id);
        if (blockItem != null) return blockItem;
        throw new IllegalArgumentException("Unknown registered item: " + id);
    }

    /** Registers every item described by the registrations JSON file. */
    private static Map<String, DeferredItem<? extends Item>> registerAll() {
        Map<String, DeferredItem<? extends Item>> items = new LinkedHashMap<>();
        for (ItemRegistration registration : REGISTRATIONS) {
            DeferredItem<? extends Item> previous = items.put(
                    registration.id(), register(registration));
            if (previous != null) {
                throw new IllegalArgumentException("Duplicate item registration: " + registration.id());
            }
        }
        return Collections.unmodifiableMap(items);
    }

    /** Loads and constructs all configured tool tiers. */
    private static Map<String, Tier> loadTierDefinitions() {
        Map<String, Tier> tiers = new LinkedHashMap<>();
        for (TierDefinition definition : ArsenalDefinitionLoader.loadTiers(
                "data/" + CreateArsenal.MODID + "/tiers.json").values()) {
            tiers.put(definition.id(), ArsenalToolTiers.create(definition));
        }
        return Collections.unmodifiableMap(tiers);
    }

    /** Dispatches one registration to the appropriate typed item factory. */
    private static DeferredItem<? extends Item> register(ItemRegistration registration) {
        return switch (registration.kind()) {
            case ITEM -> registerItem(definition(registration.id(), ArsenalItemDefinition.class));
            case TOOL, MULTI_TOOL -> registerTool(definition(registration.id(), ToolDefinition.class));
            case WEAPON -> registerWeapon(definition(registration.id(), WeaponDefinition.class));
            case ARMOR -> registerArmor(definition(registration.id(), ArmorDefinition.class));
        };
    }

    /** Registers a regular item definition. */
    private static DeferredItem<ArsenalItem> registerItem(ArsenalItemDefinition definition) {
        return ITEMS.register(definition.id(),
                () -> new ArsenalItem(definition.modelPath(),
                        new Item.Properties().stacksTo(definition.maxStackSize())));
    }

    /** Registers a tool or multitool definition. */
    private static DeferredItem<? extends Item> registerTool(ToolDefinition definition) {
        Tier tier = required(TIERS, definition.tierName());
        ToolFactory factory = required(TOOL_FACTORIES, definition.toolType());
        return ITEMS.register(definition.item().id(), () -> factory.create(tier, definition));
    }

    /** Registers a weapon definition. */
    private static DeferredItem<? extends Item> registerWeapon(WeaponDefinition definition) {
        Tier tier = required(TIERS, definition.tierName());
        WeaponFactory factory = required(WEAPON_FACTORIES, definition.weaponType());
        return ITEMS.register(definition.item().id(), () -> factory.create(tier, definition));
    }

    /** Registers an armor definition. */
    private static DeferredItem<ArsenalArmorItem> registerArmor(ArmorDefinition definition) {
        ArmorItem.Type type = definition.slot().minecraftType();
        return ITEMS.register(definition.item().id(), () -> new ArsenalArmorItem(
                ArsenalArmorMaterials.byId(definition.material()), type,
                new Item.Properties().durability(type.getDurability(definition.durabilityModifier())),
                definition.item().modelPath(), definition.equippedModel(), definition.beltImmune()));
    }

    /** Loads all item definitions into one polymorphic lookup map. */
    private static Map<String, ArsenalDefinition> loadDefinitions() {
        return REGISTRATIONS.stream()
                .collect(Collectors.toUnmodifiableMap(ItemRegistration::id, ArsenalItems::loadDefinition));
    }

    /** Loads the specialized definition matching one registration kind. */
    private static ArsenalDefinition loadDefinition(ItemRegistration registration) {
        return switch (registration.kind()) {
            case ITEM -> ArsenalDefinitionLoader.loadCore(definitionPath(registration.id()));
            case TOOL, MULTI_TOOL -> ArsenalDefinitionLoader.loadTool(definitionPath(registration.id()));
            case WEAPON -> ArsenalDefinitionLoader.loadWeapon(definitionPath(registration.id()));
            case ARMOR -> ArsenalDefinitionLoader.loadArmor(definitionPath(registration.id()));
        };
    }

    /** Retrieves and validates a definition's specialized record type. */
    private static <T extends ArsenalDefinition> T definition(String id, Class<T> type) {
        ArsenalDefinition definition = required(DEFINITIONS, id);
        if (!type.isInstance(definition)) {
            throw new IllegalArgumentException("Definition " + id + " is not a " + type.getSimpleName());
        }
        return type.cast(definition);
    }

    /** Retrieves a required map value or reports a configuration error. */
    private static <K, V> V required(Map<K, V> values, K key) {
        V value = values.get(key);
        if (value == null) throw new IllegalArgumentException("Unknown data-driven value: " + key);
        return value;
    }

    /** Builds the classpath path for an item definition JSON file. */
    private static String definitionPath(String id) {
        return "data/" + CreateArsenal.MODID + "/item_definitions/" + id + ".json";
    }

    private record ToolFactory(ToolConstructor constructor) {
        Item create(Tier tier, ToolDefinition definition) {
            return constructor.create(tier, definition);
        }
    }

    @FunctionalInterface
    private interface ToolConstructor {
        Item create(Tier tier, ToolDefinition definition);
    }

    @FunctionalInterface
    private interface WeaponFactory {
        Item create(Tier tier, WeaponDefinition definition);
    }

    private ArsenalItems() { }
}
