package com.fayetalerror.createarsenal.config;

/**
 * Shared metadata for a content definition.
 *
 * @param id the registry path, such as {@code brass_pickaxe}
 * @param kind the broad behavior category for the item
 * @param modelPath the GeckoLib model path relative to the mod namespace
 */
public record ArsenalItemDefinition(
        String id,
        ItemKind kind,
        String modelPath
) implements ArsenalDefinition {
}
