package com.fayetalerror.createarsenal.config;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/** Loads item definition JSON resources into the shared definition records. */
public final class ArsenalDefinitionLoader {
    private ArsenalDefinitionLoader() {
    }

    /**
     * Loads a tool definition from a resource path beneath the mod's data directory.
     *
     * @param resourcePath path such as {@code data/createarsenal/item_definitions/brass_pickaxe.json}
     * @return the parsed tool definition
     * @throws IllegalArgumentException when the resource is missing or malformed
     */
    public static ToolDefinition loadTool(String resourcePath) {
        try (InputStream stream = ArsenalDefinitionLoader.class.getClassLoader()
                .getResourceAsStream(resourcePath)) {
            if (stream == null) {
                throw new IllegalArgumentException("Missing item definition resource: " + resourcePath);
            }
            JsonObject json = JsonParser.parseReader(
                    new InputStreamReader(stream, StandardCharsets.UTF_8)).getAsJsonObject();
            return new ToolDefinition(
                    new ArsenalItemDefinition(
                            requiredString(json, "id"),
                            ItemKind.valueOf(requiredString(json, "kind").toUpperCase()),
                            requiredString(json, "model")),
                    requiredString(json, "tier"),
                    requiredString(json, "tool_type"),
                    json.get("durability").getAsInt(),
                    json.get("attack_damage").getAsFloat(),
                    json.get("attack_speed").getAsFloat());
        } catch (IOException | RuntimeException exception) {
            throw new IllegalArgumentException(
                    "Unable to load item definition: " + resourcePath, exception);
        }
    }

    /** Loads a core definition containing only registry and model metadata. */
    public static ArsenalItemDefinition loadCore(String resourcePath) {
        try (InputStream stream = ArsenalDefinitionLoader.class.getClassLoader()
                .getResourceAsStream(resourcePath)) {
            if (stream == null) {
                throw new IllegalArgumentException("Missing core definition resource: " + resourcePath);
            }
            JsonObject json = JsonParser.parseReader(
                    new InputStreamReader(stream, StandardCharsets.UTF_8)).getAsJsonObject();
            return new ArsenalItemDefinition(
                    requiredString(json, "id"),
                    ItemKind.CORE,
                    requiredString(json, "model"));
        } catch (IOException | RuntimeException exception) {
            throw new IllegalArgumentException(
                    "Unable to load core definition: " + resourcePath, exception);
        }
    }

    /** Loads an armor definition, including slot and durability metadata. */
    public static ArmorDefinition loadArmor(String resourcePath) {
        try (InputStream stream = ArsenalDefinitionLoader.class.getClassLoader()
                .getResourceAsStream(resourcePath)) {
            if (stream == null) {
                throw new IllegalArgumentException("Missing armor definition resource: " + resourcePath);
            }
            JsonObject json = JsonParser.parseReader(
                    new InputStreamReader(stream, StandardCharsets.UTF_8)).getAsJsonObject();
            return new ArmorDefinition(
                    new ArsenalItemDefinition(
                            requiredString(json, "id"), ItemKind.ARMOR, requiredString(json, "model")),
                    requiredString(json, "slot"),
                    json.get("defense").getAsInt(),
                    json.get("toughness").getAsFloat(),
                    json.get("durability_modifier").getAsInt(),
                    requiredString(json, "equipped_model"));
        } catch (IOException | RuntimeException exception) {
            throw new IllegalArgumentException(
                    "Unable to load armor definition: " + resourcePath, exception);
        }
    }

    /** Loads defense and balance values for an armor material. */
    public static ArmorMaterialDefinition loadArmorMaterial(String resourcePath) {
        try (InputStream stream = ArsenalDefinitionLoader.class.getClassLoader()
                .getResourceAsStream(resourcePath)) {
            if (stream == null) {
                throw new IllegalArgumentException("Missing armor material resource: " + resourcePath);
            }
            JsonObject json = JsonParser.parseReader(
                    new InputStreamReader(stream, StandardCharsets.UTF_8)).getAsJsonObject();
            return new ArmorMaterialDefinition(
                    json.get("helmet_defense").getAsInt(),
                    json.get("chestplate_defense").getAsInt(),
                    json.get("leggings_defense").getAsInt(),
                    json.get("boots_defense").getAsInt(),
                    json.get("enchantability").getAsInt(),
                    json.get("toughness").getAsFloat(),
                    json.get("knockback_resistance").getAsFloat());
        } catch (IOException | RuntimeException exception) {
            throw new IllegalArgumentException(
                    "Unable to load armor material definition: " + resourcePath, exception);
        }
    }

    private static String requiredString(JsonObject json, String key) {
        if (!json.has(key) || !json.get(key).isJsonPrimitive()) {
            throw new IllegalArgumentException("Missing definition field: " + key);
        }
        return json.get(key).getAsString();
    }
}
