package com.fayetalerror.createarsenal.config;

/** Common identity exposed by every JSON-backed item definition. */
public interface ArsenalDefinition {
    String id();

    ItemKind kind();

    String modelPath();
}
