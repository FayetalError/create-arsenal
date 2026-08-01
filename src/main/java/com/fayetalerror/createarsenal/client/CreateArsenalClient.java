package com.fayetalerror.createarsenal.client;

import com.fayetalerror.createarsenal.CreateArsenal;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

@Mod(value = CreateArsenal.MODID, dist = Dist.CLIENT)
public final class CreateArsenalClient {
    public CreateArsenalClient(ModContainer container) {
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }
}
