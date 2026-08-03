package com.fayetalerror.createarsenal.registry;

import com.fayetalerror.createarsenal.CreateArsenal;
import com.fayetalerror.createarsenal.item.armor.AndesiteArmorItem;
import com.fayetalerror.createarsenal.item.tools.AndesiteAxeCoreItem;
import com.fayetalerror.createarsenal.item.tools.AndesiteAxeItem;
import com.fayetalerror.createarsenal.item.tools.AndesiteHoeCoreItem;
import com.fayetalerror.createarsenal.item.tools.AndesiteHoeItem;
import com.fayetalerror.createarsenal.item.tools.AndesitePickaxeCoreItem;
import com.fayetalerror.createarsenal.item.tools.AndesitePickaxeItem;
import com.fayetalerror.createarsenal.item.tools.AndesiteShovelCoreItem;
import com.fayetalerror.createarsenal.item.tools.AndesiteShovelItem;
import com.fayetalerror.createarsenal.item.tools.ArsenalToolTiers;
import com.fayetalerror.createarsenal.item.weapons.AndesiteSwordCoreItem;
import com.fayetalerror.createarsenal.item.weapons.AndesiteSwordItem;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.SwordItem;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

/** Declares every item and block item registered by Create: Arsenal. */
public final class ArsenalItems {
    /** Deferred item registry scoped to the {@code createarsenal} namespace. */
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(CreateArsenal.MODID);

    /** Static GeckoLib component registered as {@code createarsenal:andesite_pickaxe_core}. */
    public static final DeferredItem<AndesitePickaxeCoreItem> ANDESITE_PICKAXE_CORE = ITEMS.register(
            "andesite_pickaxe_core",
            () -> new AndesitePickaxeCoreItem(new Item.Properties().stacksTo(1))
    );

    /** GeckoLib-rendered pickaxe registered as {@code createarsenal:andesite_pickaxe}. */
    public static final DeferredItem<AndesitePickaxeItem> ANDESITE_PICKAXE = ITEMS.register(
            "andesite_pickaxe",
            () -> new AndesitePickaxeItem(
                    ArsenalToolTiers.ANDESITE,
                    new Item.Properties().attributes(PickaxeItem.createAttributes(
                            ArsenalToolTiers.ANDESITE,
                            1.0F,
                            -2.8F
                    ))
            )
    );

    /** Static GeckoLib component registered as {@code createarsenal:andesite_axe_core}. */
    public static final DeferredItem<AndesiteAxeCoreItem> ANDESITE_AXE_CORE = ITEMS.register(
            "andesite_axe_core",
            () -> new AndesiteAxeCoreItem(new Item.Properties().stacksTo(1))
    );

    /** GeckoLib-rendered axe registered as {@code createarsenal:andesite_axe}. */
    public static final DeferredItem<AndesiteAxeItem> ANDESITE_AXE = ITEMS.register(
            "andesite_axe",
            () -> new AndesiteAxeItem(
                    ArsenalToolTiers.ANDESITE,
                    new Item.Properties().attributes(AxeItem.createAttributes(
                            ArsenalToolTiers.ANDESITE,
                            6.0F,
                            -3.1F
                    ))
            )
    );

    /** Static GeckoLib component registered as {@code createarsenal:andesite_shovel_core}. */
    public static final DeferredItem<AndesiteShovelCoreItem> ANDESITE_SHOVEL_CORE = ITEMS.register(
            "andesite_shovel_core",
            () -> new AndesiteShovelCoreItem(new Item.Properties().stacksTo(1))
    );

    /** GeckoLib-rendered shovel registered as {@code createarsenal:andesite_shovel}. */
    public static final DeferredItem<AndesiteShovelItem> ANDESITE_SHOVEL = ITEMS.register(
            "andesite_shovel",
            () -> new AndesiteShovelItem(
                    ArsenalToolTiers.ANDESITE,
                    new Item.Properties().attributes(ShovelItem.createAttributes(
                            ArsenalToolTiers.ANDESITE,
                            1.5F,
                            -3.0F
                    ))
            )
    );

    /** Static GeckoLib component registered as {@code createarsenal:andesite_hoe_core}. */
    public static final DeferredItem<AndesiteHoeCoreItem> ANDESITE_HOE_CORE = ITEMS.register(
            "andesite_hoe_core",
            () -> new AndesiteHoeCoreItem(new Item.Properties().stacksTo(1))
    );

    /** GeckoLib-rendered hoe registered as {@code createarsenal:andesite_hoe}. */
    public static final DeferredItem<AndesiteHoeItem> ANDESITE_HOE = ITEMS.register(
            "andesite_hoe",
            () -> new AndesiteHoeItem(
                    ArsenalToolTiers.ANDESITE,
                    new Item.Properties().attributes(HoeItem.createAttributes(
                            ArsenalToolTiers.ANDESITE,
                            -2.0F,
                            -1.0F
                    ))
            )
    );

    /** Static GeckoLib component registered as {@code createarsenal:andesite_sword_core}. */
    public static final DeferredItem<AndesiteSwordCoreItem> ANDESITE_SWORD_CORE = ITEMS.register(
            "andesite_sword_core",
            () -> new AndesiteSwordCoreItem(new Item.Properties().stacksTo(1))
    );

    /** GeckoLib-rendered sword registered as {@code createarsenal:andesite_sword}. */
    public static final DeferredItem<AndesiteSwordItem> ANDESITE_SWORD = ITEMS.register(
            "andesite_sword",
            () -> new AndesiteSwordItem(
                    ArsenalToolTiers.ANDESITE,
                    new Item.Properties().attributes(SwordItem.createAttributes(
                            ArsenalToolTiers.ANDESITE,
                            3.5F,
                            -2.4F
                    ))
            )
    );

    /** Andesite helmet registered with the shared armor material and GeckoLib renderer. */
    public static final DeferredItem<AndesiteArmorItem> ANDESITE_HELMET = ITEMS.register(
            "andesite_helmet",
            () -> new AndesiteArmorItem(
                    ArsenalArmorMaterials.ANDESITE,
                    ArmorItem.Type.HELMET,
                    new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(25))
            )
    );

    /** Andesite chestplate registered with the shared armor material and GeckoLib renderer. */
    public static final DeferredItem<AndesiteArmorItem> ANDESITE_CHESTPLATE = ITEMS.register(
            "andesite_chestplate",
            () -> new AndesiteArmorItem(
                    ArsenalArmorMaterials.ANDESITE,
                    ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(25))
            )
    );

    /** Andesite leggings registered with the shared armor material and GeckoLib renderer. */
    public static final DeferredItem<AndesiteArmorItem> ANDESITE_LEGGINGS = ITEMS.register(
            "andesite_leggings",
            () -> new AndesiteArmorItem(
                    ArsenalArmorMaterials.ANDESITE,
                    ArmorItem.Type.LEGGINGS,
                    new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(25))
            )
    );

    /** Andesite boots registered with the shared armor material and GeckoLib renderer. */
    public static final DeferredItem<AndesiteArmorItem> ANDESITE_BOOTS = ITEMS.register(
            "andesite_boots",
            () -> new AndesiteArmorItem(
                    ArsenalArmorMaterials.ANDESITE,
                    ArmorItem.Type.BOOTS,
                    new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(25))
            )
    );

    /** Utility class; registered items are exposed as static deferred holders. */
    private ArsenalItems() {
    }
}
