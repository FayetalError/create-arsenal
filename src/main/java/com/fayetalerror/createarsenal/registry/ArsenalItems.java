package com.fayetalerror.createarsenal.registry;

import com.fayetalerror.createarsenal.CreateArsenal;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

/** Declares every item and block item registered by Create: Arsenal. */
public final class ArsenalItems {
    /** Deferred item registry scoped to the {@code createarsenal} namespace. */
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(CreateArsenal.MODID);

    /** Inventory representation of {@link ArsenalBlocks#EXAMPLE_BLOCK}. */
    public static final DeferredItem<BlockItem> EXAMPLE_BLOCK_ITEM = ITEMS.registerSimpleBlockItem(
            "example_block",
            ArsenalBlocks.EXAMPLE_BLOCK
    );

    /**
     * Starter food item registered as {@code createarsenal:example_item}.
     * It restores one nutrition point, uses a 2.0 saturation modifier, and may be eaten at full hunger.
     */
    public static final DeferredItem<Item> EXAMPLE_ITEM = ITEMS.registerSimpleItem(
            "example_item",
            new Item.Properties().food(new FoodProperties.Builder()
                    // Allow eating the item even when the player is not hungry.
                    .alwaysEdible()
                    .nutrition(1)
                    .saturationModifier(2.0F)
                    .build())
    );

    /** Utility class; registered items are exposed as static deferred holders. */
    private ArsenalItems() {
    }
}
