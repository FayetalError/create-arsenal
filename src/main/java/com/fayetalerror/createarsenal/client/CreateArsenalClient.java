package com.fayetalerror.createarsenal.client;

import com.fayetalerror.createarsenal.CreateArsenal;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

/**
 * Client-only mod entry point.
 * Keeping this class in the client package prevents client APIs from leaking into common code.
 */
@Mod(value = CreateArsenal.MODID, dist = Dist.CLIENT)
public final class CreateArsenalClient {
    /**
     * Registers NeoForge's standard configuration screen for this mod.
     *
     * @param container container representing the client-side mod instance
     */
    public CreateArsenalClient(ModContainer container) {
        // ConfigurationScreen reads the specification registered by the common entry point.
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }
}
