package com.fayetalerror.createarsenal.config;

import net.minecraft.world.level.block.SoundType;

/** Sound profiles available to JSON-defined blocks. */
public enum BlockSound {
    /** Metallic placement, breaking, and walking sounds. */
    METAL(SoundType.METAL);

    private final SoundType minecraftSound;

    BlockSound(SoundType minecraftSound) {
        this.minecraftSound = minecraftSound;
    }

    /** Returns the Minecraft sound profile represented by this data value. */
    public SoundType minecraftSound() {
        return minecraftSound;
    }
}
