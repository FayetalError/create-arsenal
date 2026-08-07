package com.fayetalerror.createarsenal.config;

/** Identifies one JSON-backed item and the Java behavior category that owns it. */
public record ItemRegistration(String id, ItemKind kind) {
}
