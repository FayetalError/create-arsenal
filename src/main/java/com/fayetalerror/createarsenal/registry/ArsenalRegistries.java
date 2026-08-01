package com.fayetalerror.createarsenal.registry;

import net.neoforged.bus.api.IEventBus;

/** Central coordinator that attaches all deferred registries to NeoForge. */
public final class ArsenalRegistries {
    /** Utility class; registry coordination is performed through {@link #register(IEventBus)}. */
    private ArsenalRegistries() {
    }

    /**
     * Attaches each content registry to the mod event bus before registry events fire.
     *
     * @param modEventBus bus supplied to the main mod entry point by NeoForge
     */
    public static void register(IEventBus modEventBus) {
        ArsenalItems.ITEMS.register(modEventBus);

        // Creative tabs resolve their item suppliers after the item registry has been attached.
        ArsenalCreativeTabs.CREATIVE_MODE_TABS.register(modEventBus);
    }
}
