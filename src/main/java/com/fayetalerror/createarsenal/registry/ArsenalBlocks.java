package com.fayetalerror.createarsenal.registry;

import com.fayetalerror.createarsenal.CreateArsenal;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

/** Declares every block registered by Create: Arsenal. */
public final class ArsenalBlocks {
    /** Deferred block registry scoped to the {@code createarsenal} namespace. */
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(CreateArsenal.MODID);

    /**
     * Starter block registered as {@code createarsenal:example_block}.
     * Its properties create a basic stone-colored block with NeoForge's default behavior.
     */
    public static final DeferredBlock<Block> EXAMPLE_BLOCK = BLOCKS.registerSimpleBlock(
            "example_block",
            BlockBehaviour.Properties.of().mapColor(MapColor.STONE)
    );

    /** Utility class; registered blocks are exposed as static deferred holders. */
    private ArsenalBlocks() {
    }
}
