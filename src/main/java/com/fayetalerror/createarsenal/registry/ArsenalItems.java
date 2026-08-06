package com.fayetalerror.createarsenal.registry;

import com.fayetalerror.createarsenal.CreateArsenal;
import com.fayetalerror.createarsenal.item.ArsenalToolTiers;
import com.fayetalerror.createarsenal.item.ArsenalCoreItem;
import com.fayetalerror.createarsenal.config.ArsenalItemDefinition;
import com.fayetalerror.createarsenal.config.ArsenalDefinitionLoader;
import com.fayetalerror.createarsenal.config.ArmorDefinition;
import com.fayetalerror.createarsenal.config.ToolDefinition;
import java.util.function.BiFunction;
import com.fayetalerror.createarsenal.item.armor.AndesiteArmorItem;
import com.fayetalerror.createarsenal.item.tools.AndesiteAxeItem;
import com.fayetalerror.createarsenal.item.tools.AndesiteHoeItem;
import com.fayetalerror.createarsenal.item.tools.AndesitePickaxeItem;
import com.fayetalerror.createarsenal.item.tools.AndesiteShovelItem;
import com.fayetalerror.createarsenal.item.weapons.AndesiteSwordItem;
import com.fayetalerror.createarsenal.item.tools.BrassPickaxeItem;
import com.fayetalerror.createarsenal.item.tools.BrassAxeItem;
import com.fayetalerror.createarsenal.item.tools.BrassHoeItem;
import com.fayetalerror.createarsenal.item.tools.BrassPaxelItem;
import com.fayetalerror.createarsenal.item.tools.BrassShovelItem;
import com.fayetalerror.createarsenal.item.weapons.BrassSwordItem;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

/** Declares every item and block item registered by Create: Arsenal. */
public final class ArsenalItems {
    /** Deferred item registry scoped to the {@code createarsenal} namespace. */
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(CreateArsenal.MODID);

    private static ToolDefinition loadToolDefinition(String id) {
        return ArsenalDefinitionLoader.loadTool(
                "data/" + CreateArsenal.MODID + "/item_definitions/" + id + ".json");
    }

    private static ArsenalItemDefinition loadCoreDefinition(String id) {
        return ArsenalDefinitionLoader.loadCore(
                "data/" + CreateArsenal.MODID + "/item_definitions/" + id + ".json");
    }

    private static ArmorDefinition loadArmorDefinition(String id) {
        return ArsenalDefinitionLoader.loadArmor(
                "data/" + CreateArsenal.MODID + "/item_definitions/" + id + ".json");
    }

    private static final ToolDefinition ANDESITE_PICKAXE_DEFINITION = loadToolDefinition("andesite_pickaxe");
    private static final ToolDefinition ANDESITE_AXE_DEFINITION = loadToolDefinition("andesite_axe");
    private static final ToolDefinition ANDESITE_SHOVEL_DEFINITION = loadToolDefinition("andesite_shovel");
    private static final ToolDefinition ANDESITE_HOE_DEFINITION = loadToolDefinition("andesite_hoe");
    private static final ToolDefinition ANDESITE_SWORD_DEFINITION = loadToolDefinition("andesite_sword");
    private static final ToolDefinition BRASS_PICKAXE_DEFINITION = loadToolDefinition("brass_pickaxe");
    private static final ToolDefinition BRASS_AXE_DEFINITION = loadToolDefinition("brass_axe");
    private static final ToolDefinition BRASS_SHOVEL_DEFINITION = loadToolDefinition("brass_shovel");
    private static final ToolDefinition BRASS_HOE_DEFINITION = loadToolDefinition("brass_hoe");
    private static final ToolDefinition BRASS_PAXEL_DEFINITION = loadToolDefinition("brass_paxel");
    private static final ToolDefinition BRASS_SWORD_DEFINITION = loadToolDefinition("brass_sword");
    private static final ArsenalItemDefinition ANDESITE_PICKAXE_CORE_DEFINITION = loadCoreDefinition("andesite_pickaxe_core");
    private static final ArsenalItemDefinition ANDESITE_AXE_CORE_DEFINITION = loadCoreDefinition("andesite_axe_core");
    private static final ArsenalItemDefinition ANDESITE_SHOVEL_CORE_DEFINITION = loadCoreDefinition("andesite_shovel_core");
    private static final ArsenalItemDefinition ANDESITE_HOE_CORE_DEFINITION = loadCoreDefinition("andesite_hoe_core");
    private static final ArsenalItemDefinition ANDESITE_SWORD_CORE_DEFINITION = loadCoreDefinition("andesite_sword_core");
    private static final ArsenalItemDefinition BRASS_PICKAXE_CORE_DEFINITION = loadCoreDefinition("brass_pickaxe_core");
    private static final ArsenalItemDefinition BRASS_AXE_CORE_DEFINITION = loadCoreDefinition("brass_axe_core");
    private static final ArsenalItemDefinition BRASS_SHOVEL_CORE_DEFINITION = loadCoreDefinition("brass_shovel_core");
    private static final ArsenalItemDefinition BRASS_HOE_CORE_DEFINITION = loadCoreDefinition("brass_hoe_core");
    private static final ArsenalItemDefinition BRASS_SWORD_CORE_DEFINITION = loadCoreDefinition("brass_sword_core");
    private static final ArmorDefinition HELMET_DEFINITION = loadArmorDefinition("andesite_helmet");
    private static final ArmorDefinition CHESTPLATE_DEFINITION = loadArmorDefinition("andesite_chestplate");
    private static final ArmorDefinition LEGGINGS_DEFINITION = loadArmorDefinition("andesite_leggings");
    private static final ArmorDefinition BOOTS_DEFINITION = loadArmorDefinition("andesite_boots");
    private static final ArsenalItemDefinition HELMET_CORE_DEFINITION = loadCoreDefinition("andesite_helmet_core");
    private static final ArsenalItemDefinition CHESTPLATE_CORE_DEFINITION = loadCoreDefinition("andesite_chestplate_core");
    private static final ArsenalItemDefinition LEGGINGS_CORE_DEFINITION = loadCoreDefinition("andesite_leggings_core");
    private static final ArsenalItemDefinition BOOTS_CORE_DEFINITION = loadCoreDefinition("andesite_boots_core");

    /** Static GeckoLib component registered as {@code createarsenal:andesite_pickaxe_core}. */
    public static final DeferredItem<ArsenalCoreItem> ANDESITE_PICKAXE_CORE = registerCore(ANDESITE_PICKAXE_CORE_DEFINITION);

    /** GeckoLib-rendered pickaxe registered as {@code createarsenal:andesite_pickaxe}. */
    public static final DeferredItem<AndesitePickaxeItem> ANDESITE_PICKAXE = registerTool(
            ArsenalToolTiers.ANDESITE, ANDESITE_PICKAXE_DEFINITION, AndesitePickaxeItem::new);

    /** Static GeckoLib component registered as {@code createarsenal:andesite_axe_core}. */
    public static final DeferredItem<ArsenalCoreItem> ANDESITE_AXE_CORE = registerCore(ANDESITE_AXE_CORE_DEFINITION);

    /** GeckoLib-rendered axe registered as {@code createarsenal:andesite_axe}. */
    public static final DeferredItem<AndesiteAxeItem> ANDESITE_AXE = registerTool(
            ArsenalToolTiers.ANDESITE, ANDESITE_AXE_DEFINITION, AndesiteAxeItem::new);

    /** Static GeckoLib component registered as {@code createarsenal:andesite_shovel_core}. */
    public static final DeferredItem<ArsenalCoreItem> ANDESITE_SHOVEL_CORE = registerCore(ANDESITE_SHOVEL_CORE_DEFINITION);

    /** GeckoLib-rendered shovel registered as {@code createarsenal:andesite_shovel}. */
    public static final DeferredItem<AndesiteShovelItem> ANDESITE_SHOVEL = registerTool(
            ArsenalToolTiers.ANDESITE, ANDESITE_SHOVEL_DEFINITION, AndesiteShovelItem::new);

    /** Static GeckoLib component registered as {@code createarsenal:andesite_hoe_core}. */
    public static final DeferredItem<ArsenalCoreItem> ANDESITE_HOE_CORE = registerCore(ANDESITE_HOE_CORE_DEFINITION);

    /** GeckoLib-rendered hoe registered as {@code createarsenal:andesite_hoe}. */
    public static final DeferredItem<AndesiteHoeItem> ANDESITE_HOE = registerTool(
            ArsenalToolTiers.ANDESITE, ANDESITE_HOE_DEFINITION, AndesiteHoeItem::new);

    /** Static GeckoLib component registered as {@code createarsenal:andesite_sword_core}. */
    public static final DeferredItem<ArsenalCoreItem> ANDESITE_SWORD_CORE = registerCore(ANDESITE_SWORD_CORE_DEFINITION);

    /** GeckoLib-rendered sword registered as {@code createarsenal:andesite_sword}. */
    public static final DeferredItem<AndesiteSwordItem> ANDESITE_SWORD = registerTool(
            ArsenalToolTiers.ANDESITE, ANDESITE_SWORD_DEFINITION, AndesiteSwordItem::new);

    /** Static GeckoLib component used to craft the Andesite Helmet. */
    public static final DeferredItem<ArsenalCoreItem> ANDESITE_HELMET_CORE = registerCore(HELMET_CORE_DEFINITION);

    /** Static GeckoLib component used to craft the Andesite Chestplate. */
    public static final DeferredItem<ArsenalCoreItem> ANDESITE_CHESTPLATE_CORE = registerCore(CHESTPLATE_CORE_DEFINITION);

    /** Static GeckoLib component used to craft the Andesite Leggings. */
    public static final DeferredItem<ArsenalCoreItem> ANDESITE_LEGGINGS_CORE = registerCore(LEGGINGS_CORE_DEFINITION);

    /** Static GeckoLib component used to craft the Andesite Boots. */
    public static final DeferredItem<ArsenalCoreItem> ANDESITE_BOOTS_CORE = registerCore(BOOTS_CORE_DEFINITION);

    /** Andesite helmet registered with the shared armor material and GeckoLib renderer. */
    public static final DeferredItem<AndesiteArmorItem> ANDESITE_HELMET = registerArmor(HELMET_DEFINITION);

    /** Andesite chestplate registered with the shared armor material and GeckoLib renderer. */
    public static final DeferredItem<AndesiteArmorItem> ANDESITE_CHESTPLATE = registerArmor(CHESTPLATE_DEFINITION);

    /** Andesite leggings registered with the shared armor material and GeckoLib renderer. */
    public static final DeferredItem<AndesiteArmorItem> ANDESITE_LEGGINGS = registerArmor(LEGGINGS_DEFINITION);

    /** Andesite boots registered with the shared armor material and GeckoLib renderer. */
    public static final DeferredItem<AndesiteArmorItem> ANDESITE_BOOTS = registerArmor(BOOTS_DEFINITION);

    /** GeckoLib-rendered brass pickaxe registered as {@code createarsenal:brass_pickaxe}. */
    public static final DeferredItem<BrassPickaxeItem> BRASS_PICKAXE = registerBrassTool(
            BRASS_PICKAXE_DEFINITION, BrassPickaxeItem::new);

    public static final DeferredItem<ArsenalCoreItem> BRASS_PICKAXE_CORE = registerCore(BRASS_PICKAXE_CORE_DEFINITION);

    public static final DeferredItem<ArsenalCoreItem> BRASS_AXE_CORE = registerCore(BRASS_AXE_CORE_DEFINITION);
    public static final DeferredItem<BrassAxeItem> BRASS_AXE = registerBrassTool(
            BRASS_AXE_DEFINITION, BrassAxeItem::new);

    public static final DeferredItem<ArsenalCoreItem> BRASS_SHOVEL_CORE = registerCore(BRASS_SHOVEL_CORE_DEFINITION);
    public static final DeferredItem<BrassShovelItem> BRASS_SHOVEL = registerBrassTool(
            BRASS_SHOVEL_DEFINITION, BrassShovelItem::new);

    public static final DeferredItem<ArsenalCoreItem> BRASS_HOE_CORE = registerCore(BRASS_HOE_CORE_DEFINITION);
    public static final DeferredItem<BrassHoeItem> BRASS_HOE = registerBrassTool(
            BRASS_HOE_DEFINITION, BrassHoeItem::new);

    public static final DeferredItem<BrassPaxelItem> BRASS_PAXEL = registerBrassTool(
            BRASS_PAXEL_DEFINITION, BrassPaxelItem::new);

    public static final DeferredItem<ArsenalCoreItem> BRASS_SWORD_CORE = registerCore(BRASS_SWORD_CORE_DEFINITION);
    public static final DeferredItem<BrassSwordItem> BRASS_SWORD = registerBrassTool(
            BRASS_SWORD_DEFINITION, BrassSwordItem::new);

    /** Utility class; registered items are exposed as static deferred holders. */
    private static DeferredItem<ArsenalCoreItem> registerCore(ArsenalItemDefinition definition) {
        return ITEMS.register(definition.id(),
                () -> new ArsenalCoreItem(definition.modelPath(), new Item.Properties().stacksTo(1)));
    }

    private static DeferredItem<AndesiteArmorItem> registerArmor(ArmorDefinition definition) {
        ArmorItem.Type type = switch (definition.slot()) {
            case "helmet" -> ArmorItem.Type.HELMET;
            case "chestplate" -> ArmorItem.Type.CHESTPLATE;
            case "leggings" -> ArmorItem.Type.LEGGINGS;
            case "boots" -> ArmorItem.Type.BOOTS;
            default -> throw new IllegalArgumentException("Unknown armor slot: " + definition.slot());
        };
        return ITEMS.register(definition.item().id(), () -> new AndesiteArmorItem(
                ArsenalArmorMaterials.ANDESITE,
                type,
                new Item.Properties().durability(type.getDurability(definition.durabilityModifier()))));
    }

    private static <T extends Item> DeferredItem<T> registerBrassTool(
            ToolDefinition definition,
            BiFunction<Tier, Item.Properties, T> factory
    ) {
        return registerTool(ArsenalToolTiers.BRASS, definition, factory);
    }

    private static <T extends Item> DeferredItem<T> registerTool(
            Tier tier,
            ToolDefinition definition,
            BiFunction<Tier, Item.Properties, T> factory
    ) {
        return ITEMS.register(definition.item().id(),
                () -> factory.apply(tier, toolProperties(tier, definition)));
    }

    private static Item.Properties toolProperties(Tier tier, ToolDefinition definition) {
        return new Item.Properties().attributes(switch (definition.toolType()) {
            case "pickaxe" -> PickaxeItem.createAttributes(
                    tier, definition.attackDamage(), definition.attackSpeed());
            case "axe" -> AxeItem.createAttributes(
                    tier, definition.attackDamage(), definition.attackSpeed());
            case "shovel" -> ShovelItem.createAttributes(
                    tier, definition.attackDamage(), definition.attackSpeed());
            case "hoe" -> HoeItem.createAttributes(
                    tier, definition.attackDamage(), definition.attackSpeed());
            case "sword" -> SwordItem.createAttributes(
                    tier, definition.attackDamage(), definition.attackSpeed());
            case "multi_tool" -> DiggerItem.createAttributes(
                    tier, definition.attackDamage(), definition.attackSpeed());
            default -> throw new IllegalArgumentException("Unknown Brass tool type: " + definition.toolType());
        });
    }

    private ArsenalItems() {
    }
}
