package com.fayetalerror.createarsenal.build

import org.gradle.api.GradleException
import org.gradle.api.logging.Logger

/** Checks Create: Arsenal references embedded in recipes and item tags. */
final class ReferenceValidator {
    /** Validates recipe references and reports unresolved tag entries. */
    static void validate(ValidationContext context, Logger logger) {
        context.recipes.each { file -> ReferenceValidator.validateRecipe(ValidationContext.parse(file), file, context) }
        context.itemTags.each { file -> ReferenceValidator.warnUnresolvedTagEntries(
                ValidationContext.parse(file), file, context, logger) }
    }

    /** Rejects recipe IDs that identify neither known content nor a known item tag. */
    private static void validateRecipe(Object json, File file, ValidationContext context) {
        ValidationContext.walk(json) { reference ->
            if (reference.startsWith('createarsenal:')) {
                String id = reference.substring('createarsenal:'.length())
                if (!context.contentIds.contains(id) && !context.tagIds.contains(id)) {
                    throw new GradleException("${file.name}: unknown Create: Arsenal reference createarsenal:${id}")
                }
            }
        }
    }

    /** Warns when a Create: Arsenal item-tag value does not name configured content. */
    private static void warnUnresolvedTagEntries(Object json, File file, ValidationContext context, Logger logger) {
        Set<String> unresolved = [] as Set<String>
        ValidationContext.walk(json) { reference ->
            if (reference.startsWith('createarsenal:')) {
                String id = reference.substring('createarsenal:'.length())
                if (!context.contentIds.contains(id)) unresolved << id
            }
        }
        unresolved.each { logger.warn("${file.name}: unresolved Create: Arsenal tag reference createarsenal:${it}") }
    }
}
