package com.fayetalerror.createarsenal.config;

/** Data-driven balance values for an armor material. */
public record ArmorMaterialDefinition(
        int helmetDefense,
        int chestplateDefense,
        int leggingsDefense,
        int bootsDefense,
        int enchantability,
        float toughness,
        float knockbackResistance
) {
}
