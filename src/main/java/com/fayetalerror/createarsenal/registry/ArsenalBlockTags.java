package com.fayetalerror.createarsenal.registry;

import com.fayetalerror.createarsenal.CreateArsenal;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

/** Block tags used by Create: Arsenal's multi-purpose tools. */
public final class ArsenalBlockTags {
    /** Combines the vanilla pickaxe, axe, and shovel mining categories. */
    public static final TagKey<Block> MINEABLE_WITH_PAXEL = TagKey.create(
            Registries.BLOCK,
            ResourceLocation.fromNamespaceAndPath(CreateArsenal.MODID, "mineable/paxel")
    );

    private ArsenalBlockTags() {
    }
}
