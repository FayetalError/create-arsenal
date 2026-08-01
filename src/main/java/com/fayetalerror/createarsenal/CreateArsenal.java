package com.fayetalerror.createarsenal;

import com.fayetalerror.createarsenal.config.ArsenalConfig;
import com.fayetalerror.createarsenal.event.CommonEvents;
import com.fayetalerror.createarsenal.registry.ArsenalRegistries;
import com.mojang.logging.LogUtils;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import org.slf4j.Logger;

/**
 * Common entry point for Create: Arsenal.
 * NeoForge constructs this class while it discovers and initializes the mod.
 */
@Mod(CreateArsenal.MODID)
public final class CreateArsenal {
    /** Namespace used by registrations, resources, configuration, and event subscribers. */
    public static final String MODID = "createarsenal";

    /** Shared logger used by common and client initialization code. */
    public static final Logger LOGGER = LogUtils.getLogger();

    /**
     * Connects the mod's registries, event listeners, and common configuration to NeoForge.
     *
     * @param modEventBus bus used for mod lifecycle and registry events
     * @param modContainer container representing this loaded mod
     */
    public CreateArsenal(IEventBus modEventBus, ModContainer modContainer) {
        // Attach every deferred registry before NeoForge begins registering content.
        ArsenalRegistries.register(modEventBus);

        // Attach lifecycle and gameplay listeners to their corresponding event buses.
        CommonEvents.register(modEventBus);

        // Make the common configuration specification available to NeoForge's config system.
        modContainer.registerConfig(ModConfig.Type.COMMON, ArsenalConfig.SPEC);
    }
}
