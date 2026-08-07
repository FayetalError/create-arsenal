package com.fayetalerror.createarsenal.registry;

import com.fayetalerror.createarsenal.CreateArsenal;
import com.fayetalerror.createarsenal.config.ArmorDefinition;
import com.fayetalerror.createarsenal.config.ArsenalDefinitionLoader;
import com.fayetalerror.createarsenal.config.ArsenalItemDefinition;
import com.fayetalerror.createarsenal.config.ItemKind;
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
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

/** Registers every JSON-described item through generic behavior factories. */
public final class ArsenalItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(CreateArsenal.MODID);

    private static final List<ItemRegistration> REGISTRATIONS =
            ArsenalDefinitionLoader.loadRegistrations(definitionPath("registrations"));
    private static final Map<String, ToolDefinition> TOOL_DEFINITIONS = loadDefinitions(
            ItemKind.TOOL, ArsenalDefinitionLoader::loadTool);
    private static final Map<String, WeaponDefinition> WEAPON_DEFINITIONS = loadDefinitions(
            ItemKind.WEAPON, ArsenalDefinitionLoader::loadWeapon);
    private static final Map<String, ArsenalItemDefinition> ITEM_DEFINITIONS = loadDefinitions(
            ItemKind.ITEM, ArsenalDefinitionLoader::loadCore);
    private static final Map<String, ArmorDefinition> ARMOR_DEFINITIONS = loadDefinitions(
            ItemKind.ARMOR, ArsenalDefinitionLoader::loadArmor);

    private static final Map<String, Tier> TIERS = loadTierDefinitions();

    private static final Map<ToolType, ToolFactory> TOOL_FACTORIES = Map.of(
            ToolType.PICKAXE, ArsenalPickaxeItem::new,
            ToolType.AXE, ArsenalAxeItem::new,
            ToolType.SHOVEL, ArsenalShovelItem::new,
            ToolType.HOE, ArsenalHoeItem::new,
            ToolType.MULTI_TOOL, ArsenalPaxelItem::new);

    private static final Map<WeaponType, WeaponFactory> WEAPON_FACTORIES = Map.of(
            WeaponType.SWORD, ArsenalSwordItem::new);

    public static final Map<String, DeferredItem<? extends Item>> ITEMS_BY_ID = registerAll();

    public static Item item(String id) {
        DeferredItem<? extends Item> holder = ITEMS_BY_ID.get(id);
        if (holder == null) throw new IllegalArgumentException("Unknown registered item: " + id);
        return holder.get();
    }

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

    private static Map<String, Tier> loadTierDefinitions() {
        Map<String, Tier> tiers = new LinkedHashMap<>();
        for (TierDefinition definition : ArsenalDefinitionLoader.loadTiers(
                "data/" + CreateArsenal.MODID + "/tiers.json").values()) {
            tiers.put(definition.id(), ArsenalToolTiers.create(definition));
        }
        return Collections.unmodifiableMap(tiers);
    }

    private static DeferredItem<? extends Item> register(ItemRegistration registration) {
        return switch (registration.kind()) {
            case ITEM -> registerItem(required(ITEM_DEFINITIONS, registration.id()));
            case TOOL, MULTI_TOOL -> registerTool(required(TOOL_DEFINITIONS, registration.id()));
            case WEAPON -> registerWeapon(required(WEAPON_DEFINITIONS, registration.id()));
            case ARMOR -> registerArmor(required(ARMOR_DEFINITIONS, registration.id()));
        };
    }

    private static DeferredItem<ArsenalItem> registerItem(ArsenalItemDefinition definition) {
        return ITEMS.register(definition.id(),
                () -> new ArsenalItem(definition.modelPath(), new Item.Properties().stacksTo(1)));
    }

    private static DeferredItem<? extends Item> registerTool(ToolDefinition definition) {
        Tier tier = required(TIERS, definition.tierName());
        ToolFactory factory = required(TOOL_FACTORIES, definition.toolType());
        return ITEMS.register(definition.item().id(), () -> factory.create(
                tier, toolProperties(tier, definition), definition.item().modelPath()));
    }

    private static DeferredItem<? extends Item> registerWeapon(WeaponDefinition definition) {
        Tier tier = required(TIERS, definition.tierName());
        WeaponFactory factory = required(WEAPON_FACTORIES, definition.weaponType());
        return ITEMS.register(definition.item().id(), () -> factory.create(
                tier, weaponProperties(tier, definition), definition.item().modelPath()));
    }

    private static DeferredItem<ArsenalArmorItem> registerArmor(ArmorDefinition definition) {
        ArmorItem.Type type = switch (definition.slot()) {
            case HELMET -> ArmorItem.Type.HELMET;
            case CHESTPLATE -> ArmorItem.Type.CHESTPLATE;
            case LEGGINGS -> ArmorItem.Type.LEGGINGS;
            case BOOTS -> ArmorItem.Type.BOOTS;
        };
        return ITEMS.register(definition.item().id(), () -> new ArsenalArmorItem(
                ArsenalArmorMaterials.byId(definition.material()), type,
                new Item.Properties().durability(type.getDurability(definition.durabilityModifier())),
                definition.item().modelPath(), definition.equippedModel()));
    }

    private static Item.Properties toolProperties(Tier tier, ToolDefinition definition) {
        return new Item.Properties().attributes(switch (definition.toolType()) {
            case PICKAXE -> PickaxeItem.createAttributes(tier, definition.attackDamage(), definition.attackSpeed());
            case AXE -> AxeItem.createAttributes(tier, definition.attackDamage(), definition.attackSpeed());
            case SHOVEL -> ShovelItem.createAttributes(tier, definition.attackDamage(), definition.attackSpeed());
            case HOE -> HoeItem.createAttributes(tier, definition.attackDamage(), definition.attackSpeed());
            case MULTI_TOOL -> DiggerItem.createAttributes(tier, definition.attackDamage(), definition.attackSpeed());
        });
    }

    private static Item.Properties weaponProperties(Tier tier, WeaponDefinition definition) {
        return new Item.Properties().attributes(SwordItem.createAttributes(
                tier, definition.attackDamage(), definition.attackSpeed()));
    }

    private static <T> Map<String, T> loadDefinitions(ItemKind kind, Function<String, T> loader) {
        return REGISTRATIONS.stream()
                .filter(registration -> registration.kind() == kind
                        || kind == ItemKind.TOOL && registration.kind() == ItemKind.MULTI_TOOL)
                .collect(Collectors.toUnmodifiableMap(ItemRegistration::id,
                        registration -> loader.apply(definitionPath(registration.id()))));
    }

    private static <K, V> V required(Map<K, V> values, K key) {
        V value = values.get(key);
        if (value == null) throw new IllegalArgumentException("Unknown data-driven value: " + key);
        return value;
    }

    private static String definitionPath(String id) {
        return "data/" + CreateArsenal.MODID + "/item_definitions/" + id + ".json";
    }

    @FunctionalInterface
    private interface ToolFactory {
        Item create(Tier tier, Item.Properties properties, String modelPath);
    }

    @FunctionalInterface
    private interface WeaponFactory {
        Item create(Tier tier, Item.Properties properties, String modelPath);
    }

    private ArsenalItems() { }
}
