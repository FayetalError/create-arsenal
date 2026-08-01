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

@Mod(CreateArsenal.MODID)
public final class CreateArsenal {
    public static final String MODID = "createarsenal";
    public static final Logger LOGGER = LogUtils.getLogger();

    public CreateArsenal(IEventBus modEventBus, ModContainer modContainer) {
        ArsenalRegistries.register(modEventBus);

        CommonEvents.register(modEventBus);
        modContainer.registerConfig(ModConfig.Type.COMMON, ArsenalConfig.SPEC);
    }
}
