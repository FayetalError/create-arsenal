package com.fayetalerror.createarsenal.config;

/** Data-driven gameplay and rendering metadata for an armor piece. */
public record ArmorDefinition(
        ArsenalItemDefinition item,
        String material,
        ArmorSlot slot,
        int defense,
        float toughness,
        int durabilityModifier,
        String equippedModel
) {
}
