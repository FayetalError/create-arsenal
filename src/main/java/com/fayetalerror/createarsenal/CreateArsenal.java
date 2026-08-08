package com.fayetalerror.createarsenal;

import com.fayetalerror.createarsenal.registry.ArsenalRegistries;
import com.fayetalerror.createarsenal.registry.ArsenalItems;
import com.mojang.logging.LogUtils;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import com.simibubi.create.content.equipment.goggles.GogglesItem;
import net.minecraft.world.entity.EquipmentSlot;
import org.slf4j.Logger;

/**
 * Common entry point for Create: Arsenal.
 * NeoForge constructs this class while it discovers and initializes the mod.
 */
@Mod(CreateArsenal.MODID)
public final class CreateArsenal {
    /** Namespace used by registrations and resources. */
    public static final String MODID = "createarsenal";

    /** Shared logger for Create: Arsenal. */
    public static final Logger LOGGER = LogUtils.getLogger();

    /**
     * Connects the mod's registries to NeoForge and reports the loaded version.
     *
     * @param modEventBus bus used for mod lifecycle and registry events
     * @param modContainer container representing this loaded mod
     */
    public CreateArsenal(IEventBus modEventBus, ModContainer modContainer) {
        // Attach every deferred registry before NeoForge begins registering content.
        ArsenalRegistries.register(modEventBus);
        GogglesItem.addIsWearingPredicate(player -> player.getItemBySlot(EquipmentSlot.HEAD)
                .is(ArsenalItems.item("andesite_helmet")));
        GogglesItem.addIsWearingPredicate(player -> player.getItemBySlot(EquipmentSlot.HEAD)
                .is(ArsenalItems.item("brass_helmet")));

        LOGGER.info("Create: Arsenal {} initializing!", modContainer.getModInfo().getVersion());
    }
}
