package com.fayetalerror.createarsenal.config;

/** Describes one ordinary placed block and its inventory item. */
public record BlockDefinition(
        String id,
        String modelPath,
        float strength,
        float explosionResistance,
        BlockSound sound,
        boolean requiresCorrectTool) {
}
