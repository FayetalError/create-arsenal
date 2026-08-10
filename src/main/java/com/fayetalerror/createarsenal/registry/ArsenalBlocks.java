package com.fayetalerror.createarsenal.registry;

import com.fayetalerror.createarsenal.CreateArsenal;
import com.fayetalerror.createarsenal.config.ArsenalDefinitionLoader;
import com.fayetalerror.createarsenal.config.BlockDefinition;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

/** Registers the mod's placed blocks and their corresponding inventory items. */
public final class ArsenalBlocks {
    /** Deferred registry for placed blocks. */
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(CreateArsenal.MODID);

    /** Deferred registry for the inventory items that place Arsenal blocks. */
    public static final DeferredRegister.Items BLOCK_ITEMS = DeferredRegister.createItems(CreateArsenal.MODID);

    private static final List<String> REGISTRATIONS = ArsenalDefinitionLoader.loadBlockRegistrations(
            "data/" + CreateArsenal.MODID + "/block_definitions/registrations.json");
    private static final Map<String, BlockDefinition> DEFINITIONS = loadDefinitions();
    private static final Map<String, DeferredBlock<Block>> BLOCKS_BY_ID = registerBlocks();
    private static final Map<String, DeferredItem<BlockItem>> ITEMS_BY_ID = registerBlockItems();

    /** Loads every registered block definition keyed by its registry ID. */
    private static Map<String, BlockDefinition> loadDefinitions() {
        Map<String, BlockDefinition> definitions = new LinkedHashMap<>();
        for (String id : REGISTRATIONS) {
            if (definitions.put(id, ArsenalDefinitionLoader.loadBlock(definitionPath(id))) != null) {
                throw new IllegalArgumentException("Duplicate block registration: " + id);
            }
        }
        return Collections.unmodifiableMap(definitions);
    }

    /** Registers each configured block with the properties declared in its definition. */
    private static Map<String, DeferredBlock<Block>> registerBlocks() {
        Map<String, DeferredBlock<Block>> blocks = new LinkedHashMap<>();
        for (BlockDefinition definition : DEFINITIONS.values()) {
            blocks.put(definition.id(), BLOCKS.register(definition.id(), () -> new Block(properties(definition))));
        }
        return Collections.unmodifiableMap(blocks);
    }

    /** Registers the BlockItem for each configured placed block. */
    private static Map<String, DeferredItem<BlockItem>> registerBlockItems() {
        Map<String, DeferredItem<BlockItem>> items = new LinkedHashMap<>();
        for (String id : REGISTRATIONS) {
            items.put(id, BLOCK_ITEMS.register(id, () -> new BlockItem(block(id), new Item.Properties())));
        }
        return Collections.unmodifiableMap(items);
    }

    /** Converts a data definition into Minecraft's ordinary block properties. */
    private static BlockBehaviour.Properties properties(BlockDefinition definition) {
        BlockBehaviour.Properties properties = BlockBehaviour.Properties.of()
                .strength(definition.strength(), definition.explosionResistance())
                .sound(definition.sound().minecraftSound());
        return definition.requiresCorrectTool() ? properties.requiresCorrectToolForDrops() : properties;
    }

    /** Resolves one placed block by registry ID. */
    private static Block block(String id) {
        DeferredBlock<Block> block = BLOCKS_BY_ID.get(id);
        if (block == null) throw new IllegalArgumentException("Unknown registered block: " + id);
        return block.get();
    }

    /** Builds the classpath location for a block-definition JSON file. */
    private static String definitionPath(String id) {
        return "data/" + CreateArsenal.MODID + "/block_definitions/" + id + ".json";
    }

    /** Resolves a block inventory item by its registry ID, or returns {@code null} when it is unknown. */
    static Item findItem(String id) {
        DeferredItem<BlockItem> item = ITEMS_BY_ID.get(id);
        return item == null ? null : item.get();
    }

    private ArsenalBlocks() {
    }
}
