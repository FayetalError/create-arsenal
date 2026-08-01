package com.fayetalerror.createarsenal.registry;

import net.neoforged.bus.api.IEventBus;

public final class ArsenalRegistries {
    private ArsenalRegistries() {
    }

    public static void register(IEventBus modEventBus) {
        ArsenalBlocks.BLOCKS.register(modEventBus);
        ArsenalItems.ITEMS.register(modEventBus);
        ArsenalCreativeTabs.CREATIVE_MODE_TABS.register(modEventBus);
    }
}
