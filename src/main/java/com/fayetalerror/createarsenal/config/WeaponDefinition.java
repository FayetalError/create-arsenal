package com.fayetalerror.createarsenal.config;

public record WeaponDefinition(ArsenalItemDefinition item, String tierName, WeaponType weaponType,
        int durability, float attackDamage, float attackSpeed) { }
