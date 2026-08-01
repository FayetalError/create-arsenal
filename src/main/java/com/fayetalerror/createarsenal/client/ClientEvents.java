package com.fayetalerror.createarsenal.client;

import com.fayetalerror.createarsenal.CreateArsenal;
import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;

/** Handles lifecycle events that may access Minecraft client classes. */
@EventBusSubscriber(modid = CreateArsenal.MODID, value = Dist.CLIENT)
public final class ClientEvents {
    /** Utility class; event handlers are static and the class should never be instantiated. */
    private ClientEvents() {
    }

    /**
     * Runs during client initialization after the mod has been constructed.
     *
     * @param event NeoForge's client setup lifecycle event
     */
    @SubscribeEvent
    static void onClientSetup(FMLClientSetupEvent event) {
        // These template messages confirm that client initialization ran and a user is available.
        CreateArsenal.LOGGER.info("HELLO FROM CLIENT SETUP");
        CreateArsenal.LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
    }
}
