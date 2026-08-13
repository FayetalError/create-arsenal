package com.fayetalerror.createarsenal.config;

public record TierDefinition(String id, String miningTag, int durability, float speed,
        int enchantability, String repairItem) { }
