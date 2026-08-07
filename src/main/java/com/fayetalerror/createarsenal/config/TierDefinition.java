package com.fayetalerror.createarsenal.config;

public record TierDefinition(String id, String miningTag, int durability, float speed,
        float attackBonus, int enchantability, String repairItem) { }
