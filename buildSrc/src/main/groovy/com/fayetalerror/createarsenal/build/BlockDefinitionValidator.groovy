package com.fayetalerror.createarsenal.build

import org.gradle.api.GradleException

/** Validates JSON fields and world-rendering assets required by registered blocks. */
final class BlockDefinitionValidator {
    private static final List<String> REQUIRED_FIELDS = [
            'id', 'model', 'strength', 'explosion_resistance', 'sound', 'requires_correct_tool']

    /** Validates every registered block definition in the context. */
    static void validate(ValidationContext context) {
        context.blockDefinitions.each { file -> BlockDefinitionValidator.validateDefinition(
                ValidationContext.parse(file) as Map, file, context) }
    }

    /** Validates fields, blockstate presence, and the configured block model. */
    private static void validateDefinition(Map json, File file, ValidationContext context) {
        List<String> missing = REQUIRED_FIELDS.findAll { !json.containsKey(it) }
        if (!missing.isEmpty()) throw new GradleException("${file.name}: missing fields ${missing}")
        String id = json.id.toString()
        if (!context.blockStates.any { it.name == "${id}.json" }) {
            throw new GradleException("${file.name}: missing blockstate for ${id}")
        }
        ValidationContext.requireFile(new File(context.assetsDirectory, "models/block/${json.model}.json"), file,
                'block model')
    }
}
