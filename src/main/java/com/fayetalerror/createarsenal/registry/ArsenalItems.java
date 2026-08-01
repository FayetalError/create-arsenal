package com.fayetalerror.createarsenal.registry;

import com.fayetalerror.createarsenal.CreateArsenal;
import com.fayetalerror.createarsenal.item.tools.AndesitePickaxeCoreItem;
import com.fayetalerror.createarsenal.item.tools.AndesitePickaxeItem;
import com.fayetalerror.createarsenal.item.tools.ArsenalToolTiers;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.PickaxeItem;
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

    /** Utility class; registered items are exposed as static deferred holders. */
    private ArsenalItems() {
    }
}
