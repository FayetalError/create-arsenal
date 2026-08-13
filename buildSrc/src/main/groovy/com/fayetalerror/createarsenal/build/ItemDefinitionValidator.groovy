package com.fayetalerror.createarsenal.build

import org.gradle.api.GradleException

/** Validates JSON fields and visual assets required by registered items. */
final class ItemDefinitionValidator {
    private static final Map<String, List<String>> REQUIRED_FIELDS = [
            'TOOL'          : ['id', 'kind', 'tier', 'tool_type', 'durability', 'attack_damage', 'attack_speed', 'model'],
            'WEAPON'        : ['id', 'kind', 'tier', 'weapon_type', 'durability', 'attack_damage', 'attack_speed', 'model'],
            'MULTI_TOOL'    : ['id', 'kind', 'tier', 'tool_type', 'durability', 'attack_damage', 'attack_speed', 'model'],
            'ITEM'          : ['id', 'kind', 'model'],
            'ARMOR'         : ['id', 'kind', 'material', 'slot', 'durability_modifier', 'model', 'equipped_model'],
            'TIER'          : ['id', 'kind', 'mining_tag', 'durability', 'speed', 'enchantability', 'repair_item'],
            'ARMOR_MATERIAL': ['kind', 'helmet_defense', 'chestplate_defense', 'leggings_defense', 'boots_defense', 'enchantability', 'toughness', 'knockback_resistance']
    ]

    /** Validates every registered item definition in the context. */
    static void validate(ValidationContext context) {
        context.itemDefinitions.each { file -> ItemDefinitionValidator.validateDefinition(
                ValidationContext.parse(file) as Map, file, context.assetsDirectory) }
    }

    /** Validates fields, models, textures, and specialized weapon or armor resources. */
    private static void validateDefinition(Map json, File file, File assetsDirectory) {
        String kind = json.kind?.toString()?.toUpperCase()
        if (!REQUIRED_FIELDS.containsKey(kind)) {
            throw new GradleException("${file.name}: unknown definition kind '${json.kind}'")
        }
        List<String> missing = REQUIRED_FIELDS[kind].findAll { !json.containsKey(it) }
        if (!missing.isEmpty()) throw new GradleException("${file.name}: missing fields ${missing}")

        if (!['ARMOR_MATERIAL', 'TIER'].contains(kind)) {
            String modelPath = json.model.toString()
            ValidationContext.requireFile(new File(assetsDirectory, "geo/item/${modelPath}.geo.json"), file, 'geo model')
            ValidationContext.requireFile(new File(assetsDirectory, "textures/item/${modelPath}.png"), file, 'texture')
        }
        if (json.animations) {
            ValidationContext.requireFile(new File(assetsDirectory,
                    "animations/item/${json.animations}.animation.json"), file, 'animation')
        }
        if (kind == 'WEAPON' && json.weapon_type?.toString()?.equalsIgnoreCase('BOW')) {
            ItemDefinitionValidator.validateBow(json, file, assetsDirectory)
        }
        if (kind == 'ARMOR' && json.equipped_model) {
            ValidationContext.requireFile(new File(assetsDirectory, "geo/item/${json.equipped_model}.geo.json"), file,
                    'equipped geo model')
        }
    }

    /** Validates bow-only animation and projectile-damage settings. */
    private static void validateBow(Map json, File file, File assetsDirectory) {
        if (!json.animations) throw new GradleException("${file.name}: bow definitions require an animations path")
        if (!json.containsKey('arrow_damage_bonus') || json.arrow_damage_bonus < 0) {
            throw new GradleException("${file.name}: bow definitions require a non-negative arrow_damage_bonus")
        }
    }
}
