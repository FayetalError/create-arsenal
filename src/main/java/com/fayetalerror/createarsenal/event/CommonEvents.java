package com.fayetalerror.createarsenal.event;

import com.fayetalerror.createarsenal.CreateArsenal;
import com.fayetalerror.createarsenal.config.ArsenalConfig;
import com.fayetalerror.createarsenal.registry.ArsenalItems;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

/** Owns common lifecycle and gameplay event listeners used on both physical sides. */
public final class CommonEvents {
    /** Utility class; listeners are registered as static method references. */
    private CommonEvents() {
    }

    /**
     * Connects each handler to the event bus on which NeoForge publishes its event type.
     *
     * @param modEventBus bus for lifecycle and other mod-scoped events
     */
    public static void register(IEventBus modEventBus) {
        // Lifecycle and creative-tab construction events are delivered on the mod event bus.
        modEventBus.addListener(CommonEvents::commonSetup);
        modEventBus.addListener(CommonEvents::addCreative);

        // Runtime server events are delivered on NeoForge's global gameplay event bus.
        NeoForge.EVENT_BUS.addListener(CommonEvents::onServerStarting);
    }

    /** Logs the starter configuration values once common initialization begins. */
    private static void commonSetup(FMLCommonSetupEvent event) {
        CreateArsenal.LOGGER.info("HELLO FROM COMMON SETUP");

        // The dirt entry is optional and demonstrates reading a boolean configuration value.
        if (ArsenalConfig.LOG_DIRT_BLOCK.getAsBoolean()) {
            CreateArsenal.LOGGER.info("DIRT BLOCK >> {}", BuiltInRegistries.BLOCK.getKey(Blocks.DIRT));
        }

        // Combine the configurable introduction and number into one log message.
        CreateArsenal.LOGGER.info(
                "{}{}",
                ArsenalConfig.MAGIC_NUMBER_INTRODUCTION.get(),
                ArsenalConfig.MAGIC_NUMBER.getAsInt()
        );

        // Write every validated item identifier from the configured list.
        ArsenalConfig.ITEM_STRINGS.get()
                .forEach(item -> CreateArsenal.LOGGER.info("ITEM >> {}", item));
    }

    /** Adds the example block item to Minecraft's building-blocks creative tab. */
    private static void addCreative(BuildCreativeModeTabContentsEvent event) {
        // The event fires once per tab, so only modify the intended vanilla tab.
        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            event.accept(ArsenalItems.EXAMPLE_BLOCK_ITEM);
        }
    }

    /** Confirms through the log that a dedicated or integrated server is starting. */
    private static void onServerStarting(ServerStartingEvent event) {
        CreateArsenal.LOGGER.info("HELLO from server starting");
    }
}
