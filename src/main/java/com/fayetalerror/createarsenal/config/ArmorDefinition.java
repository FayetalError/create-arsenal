package com.fayetalerror.createarsenal.config;

/** Data-driven gameplay and rendering metadata for an armor piece. */
public record ArmorDefinition(
        ArsenalItemDefinition item,
        String material,
        ArmorSlot slot,
        int durabilityModifier,
        String equippedModel,
        boolean beltImmune
) implements ArsenalDefinition {
    @Override public String id() { return item.id(); }
    @Override public ItemKind kind() { return item.kind(); }
    @Override public String modelPath() { return item.modelPath(); }
}
