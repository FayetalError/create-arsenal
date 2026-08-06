package com.fayetalerror.createarsenal.item;

import com.simibubi.create.AllItems;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.SimpleTier;

/** Defines the mining statistics and repair materials shared by Create: Arsenal tools. */
public final class ArsenalToolTiers {
    /**
     * Andesite equipment mines at iron level with 650 durability and a 7.0 mining speed.
     * Damaged tools using this tier can be repaired with Create's Iron Sheets.
     */
    public static final Tier ANDESITE = new SimpleTier(
            BlockTags.INCORRECT_FOR_IRON_TOOL,
            650,
            7.0F,
            2.0F,
            16,
            () -> Ingredient.of(AllItems.IRON_SHEET.get())
    );

    /**
     * Brass equipment mines at diamond level with 2000 durability and a 9.0 mining speed.
     * Damaged tools using this tier can be repaired with Create's Brass Sheets.
     */
    public static final Tier BRASS = new SimpleTier(
            BlockTags.INCORRECT_FOR_DIAMOND_TOOL,
            2000,
            9.0F,
            4.0F,
            16,
            () -> Ingredient.of(AllItems.BRASS_SHEET.get())
    );

    private ArsenalToolTiers() {
    }
}
