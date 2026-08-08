package com.fayetalerror.createarsenal.config;

import net.minecraft.world.item.ArmorItem;

/** Logical armor slots used by JSON definitions. */
public enum ArmorSlot {
    HELMET,
    CHESTPLATE,
    LEGGINGS,
    BOOTS;

    /** Returns the corresponding Minecraft armor item type. */
    public ArmorItem.Type minecraftType() {
        return switch (this) {
            case HELMET -> ArmorItem.Type.HELMET;
            case CHESTPLATE -> ArmorItem.Type.CHESTPLATE;
            case LEGGINGS -> ArmorItem.Type.LEGGINGS;
            case BOOTS -> ArmorItem.Type.BOOTS;
        };
    }
}
