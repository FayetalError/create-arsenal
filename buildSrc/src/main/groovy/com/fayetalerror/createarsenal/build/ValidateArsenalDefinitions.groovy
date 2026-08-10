package com.fayetalerror.createarsenal.build

import org.gradle.api.DefaultTask
import org.gradle.api.GradleException
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.tasks.InputDirectory
import org.gradle.api.tasks.TaskAction

/** Runs the focused validators for the mod's JSON-defined content. */
abstract class ValidateArsenalDefinitions extends DefaultTask {
    /** Root resource directory containing the content to validate. */
    @InputDirectory
    abstract DirectoryProperty getResourcesDirectory()

    /** Validates item definitions, block definitions, and their cross-resource references. */
    @TaskAction
    void validateDefinitions() {
        ValidationContext context = ValidationContext.load(resourcesDirectory.get().asFile)
        if (context.itemDefinitions.isEmpty()) throw new GradleException('No Arsenal definition JSON files found')
        if (context.blockDefinitions.isEmpty()) throw new GradleException('No Arsenal block definition JSON files found')

        ReferenceValidator.validate(context, logger)
        ItemDefinitionValidator.validate(context)
        BlockDefinitionValidator.validate(context)
        logger.lifecycle("Validated ${context.itemDefinitions.size()} Create: Arsenal definition JSON files")
    }
}
