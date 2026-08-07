package com.fayetalerror.createarsenal.item;

import com.fayetalerror.createarsenal.config.TierDefinition;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.SimpleTier;

/** Builds vanilla mining tiers from data-loaded balance definitions. */
public final class ArsenalToolTiers {
    private ArsenalToolTiers() { }

    public static Tier create(TierDefinition definition) {
        TagKey<Block> miningTag = TagKey.create(
                Registries.BLOCK, ResourceLocation.parse(definition.miningTag()));
        return new SimpleTier(
                miningTag,
                definition.durability(), definition.speed(), definition.attackBonus(),
                definition.enchantability(), () -> Ingredient.of(BuiltInRegistries.ITEM.get(
                        ResourceLocation.parse(definition.repairItem()))));
    }
}
