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

public final class CommonEvents {
    private CommonEvents() {
    }

    public static void register(IEventBus modEventBus) {
        modEventBus.addListener(CommonEvents::commonSetup);
        modEventBus.addListener(CommonEvents::addCreative);
        NeoForge.EVENT_BUS.addListener(CommonEvents::onServerStarting);
    }

    private static void commonSetup(FMLCommonSetupEvent event) {
        CreateArsenal.LOGGER.info("HELLO FROM COMMON SETUP");

        if (ArsenalConfig.LOG_DIRT_BLOCK.getAsBoolean()) {
            CreateArsenal.LOGGER.info("DIRT BLOCK >> {}", BuiltInRegistries.BLOCK.getKey(Blocks.DIRT));
        }

        CreateArsenal.LOGGER.info(
                "{}{}",
                ArsenalConfig.MAGIC_NUMBER_INTRODUCTION.get(),
                ArsenalConfig.MAGIC_NUMBER.getAsInt()
        );
        ArsenalConfig.ITEM_STRINGS.get()
                .forEach(item -> CreateArsenal.LOGGER.info("ITEM >> {}", item));
    }

    private static void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            event.accept(ArsenalItems.EXAMPLE_BLOCK_ITEM);
        }
    }

    private static void onServerStarting(ServerStartingEvent event) {
        CreateArsenal.LOGGER.info("HELLO from server starting");
    }
}
