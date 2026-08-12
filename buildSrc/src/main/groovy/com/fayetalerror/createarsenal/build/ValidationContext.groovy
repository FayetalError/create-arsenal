package com.fayetalerror.createarsenal.build

import groovy.io.FileType
import groovy.json.JsonSlurper

/** Discovers the resource files and IDs shared by all Arsenal definition validators. */
final class ValidationContext {
    final File dataDirectory
    final File assetsDirectory
    final Set<File> itemDefinitions
    final Set<File> blockDefinitions
    final Set<File> recipes
    final Set<File> itemTags
    final Set<File> blockStates
    final Set<String> contentIds
    final Set<String> tagIds

    private ValidationContext(File dataDirectory, File assetsDirectory, Set<File> itemDefinitions, Set<File> blockDefinitions,
            Set<File> recipes, Set<File> itemTags, Set<File> blockStates, Set<String> contentIds,
            Set<String> tagIds) {
        this.dataDirectory = dataDirectory
        this.assetsDirectory = assetsDirectory
        this.itemDefinitions = itemDefinitions
        this.blockDefinitions = blockDefinitions
        this.recipes = recipes
        this.itemTags = itemTags
        this.blockStates = blockStates
        this.contentIds = contentIds
        this.tagIds = tagIds
    }

    /** Builds a context by discovering resources beneath the configured resource root. */
    static ValidationContext load(File resources) {
        File dataDirectory = new File(resources, 'data')
        File assetsDirectory = new File(resources, 'assets/createarsenal')
        Set<File> itemDefinitions = ValidationContext.jsonFiles(new File(dataDirectory, 'createarsenal/item_definitions'), 'registrations.json')
        Set<File> blockDefinitions = ValidationContext.jsonFiles(new File(dataDirectory, 'createarsenal/block_definitions'), 'registrations.json')
        Set<File> itemTags = ValidationContext.jsonFiles(dataDirectory).findAll {
            it.path.replace('\\', '/').contains('/tags/item/')
        } as Set<File>
        Set<String> contentIds = (itemDefinitions + blockDefinitions).collect {
            ValidationContext.parse(it).id?.toString()
        }.findAll { it } as Set<String>
        Set<String> tagIds = itemTags.collect { it.name.replaceFirst(/\.json$/, '') } as Set<String>
        return new ValidationContext(dataDirectory, assetsDirectory, itemDefinitions, blockDefinitions,
                ValidationContext.jsonFiles(new File(dataDirectory, 'createarsenal/recipe')), itemTags,
                ValidationContext.jsonFiles(new File(assetsDirectory, 'blockstates')), contentIds, tagIds)
    }

    /** Parses a JSON file into Groovy maps and collections. */
    static Object parse(File file) {
        new JsonSlurper().parse(file)
    }

    /** Returns JSON files below a directory, optionally excluding a filename. */
    static Set<File> jsonFiles(File directory, String excludedName = null) {
        if (!directory.isDirectory()) return [] as Set<File>
        Set<File> files = [] as Set<File>
        directory.eachFileRecurse(FileType.FILES) { file ->
            if (file.name.endsWith('.json') && file.name != excludedName) files << file
        }
        return files
    }

    /** Visits every primitive string representation contained in a decoded JSON tree. */
    static void walk(Object value, Closure<Void> visitor) {
        if (value instanceof Map) value.values().each { ValidationContext.walk(it, visitor) }
        else if (value instanceof Collection) value.each { ValidationContext.walk(it, visitor) }
        else if (value != null) visitor(value.toString())
    }

    /** Requires a referenced asset file to exist. */
    static void requireFile(File file, File definition, String assetKind) {
        if (!file.isFile()) {
            throw new org.gradle.api.GradleException("${definition.name}: missing ${assetKind} ${file}")
        }
    }
}
