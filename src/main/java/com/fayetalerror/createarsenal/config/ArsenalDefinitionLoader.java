package com.fayetalerror.createarsenal.config;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/** Loads item definition JSON resources into shared definition records. */
public final class ArsenalDefinitionLoader {
    private ArsenalDefinitionLoader() { }

    /** Loads a tool definition from a classpath JSON resource. */
    public static ToolDefinition loadTool(String path) {
        JsonObject json = loadJson(path);
        return new ToolDefinition(item(json, ItemKind.valueOf(
                        required(json, "kind").toUpperCase(java.util.Locale.ROOT))), required(json, "tier"),
                parseEnum(ToolType.class, json, "tool_type"), integer(json, "durability"),
                decimal(json, "attack_damage"), decimal(json, "attack_speed"));
    }

    /** Loads a regular item definition from a classpath JSON resource. */
    public static ArsenalItemDefinition loadCore(String path) {
        return item(loadJson(path), ItemKind.ITEM);
    }

    /** Loads a weapon definition from a classpath JSON resource. */
    public static WeaponDefinition loadWeapon(String path) {
        JsonObject json = loadJson(path);
        return new WeaponDefinition(item(json, ItemKind.WEAPON), required(json, "tier"),
                parseEnum(WeaponType.class, json, "weapon_type"), integer(json, "durability"),
                decimal(json, "attack_damage"), decimal(json, "attack_speed"),
                optional(json, "animations"));
    }

    /** Loads an armor definition from a classpath JSON resource. */
    public static ArmorDefinition loadArmor(String path) {
        JsonObject json = loadJson(path);
        return new ArmorDefinition(item(json, ItemKind.ARMOR), required(json, "material"),
                parseEnum(ArmorSlot.class, json, "slot"), integer(json, "durability_modifier"),
                required(json, "equipped_model"));
    }

    /** Loads one tool tier definition from a classpath JSON resource. */
    public static TierDefinition loadTier(String path) {
        JsonObject json = loadJson(path);
        return new TierDefinition(required(json, "id"), required(json, "mining_tag"),
                integer(json, "durability"), decimal(json, "speed"),
                decimal(json, "attack_bonus"), integer(json, "enchantability"),
                required(json, "repair_item"));
    }

    /** Loads all tool tier definitions from a JSON array resource. */
    public static Map<String, TierDefinition> loadTiers(String path) {
        Map<String, TierDefinition> result = new LinkedHashMap<>();
        JsonParser.parseReader(new InputStreamReader(resource(path), StandardCharsets.UTF_8))
                .getAsJsonArray().forEach(element -> {
                    JsonObject json = element.getAsJsonObject();
                    TierDefinition definition = new TierDefinition(required(json, "id"),
                            required(json, "mining_tag"), integer(json, "durability"),
                            decimal(json, "speed"), decimal(json, "attack_bonus"),
                            integer(json, "enchantability"), required(json, "repair_item"));
                    result.put(definition.id(), definition);
                });
        return Map.copyOf(result);
    }

    /** Loads all armor material definitions from a JSON array resource. */
    public static Map<String, ArmorMaterialDefinition> loadArmorMaterials(String path) {
        Map<String, ArmorMaterialDefinition> result = new LinkedHashMap<>();
        JsonParser.parseReader(new InputStreamReader(resource(path), StandardCharsets.UTF_8))
                .getAsJsonArray().forEach(element -> {
                    JsonObject json = element.getAsJsonObject();
                    result.put(required(json, "id"), new ArmorMaterialDefinition(
                            integer(json, "helmet_defense"), integer(json, "chestplate_defense"),
                            integer(json, "leggings_defense"), integer(json, "boots_defense"),
                            integer(json, "enchantability"), decimal(json, "toughness"),
                            decimal(json, "knockback_resistance")));
                });
        return Map.copyOf(result);
    }

    /** Loads one armor material definition from a JSON resource. */
    public static ArmorMaterialDefinition loadArmorMaterial(String path) {
        JsonObject json = loadJson(path);
        return new ArmorMaterialDefinition(integer(json, "helmet_defense"),
                integer(json, "chestplate_defense"), integer(json, "leggings_defense"),
                integer(json, "boots_defense"), integer(json, "enchantability"),
                decimal(json, "toughness"), decimal(json, "knockback_resistance"));
    }

    /** Loads the item registration list from a JSON array resource. */
    public static List<ItemRegistration> loadRegistrations(String path) {
        JsonObject json = loadJson(path);
        if (!json.has("items") || !json.get("items").isJsonArray()) {
            throw new IllegalArgumentException("Missing registrations array: " + path);
        }
        List<ItemRegistration> registrations = new ArrayList<>();
        json.getAsJsonArray("items").forEach(element -> {
            JsonObject item = element.getAsJsonObject();
            registrations.add(new ItemRegistration(required(item, "id"),
                    parseEnum(ItemKind.class, item, "kind")));
        });
        return List.copyOf(registrations);
    }

    private static ArsenalItemDefinition item(JsonObject json, ItemKind kind) {
        return new ArsenalItemDefinition(required(json, "id"), kind, required(json, "model"));
    }

    private static JsonObject loadJson(String path) {
        try (InputStream stream = resource(path)) {
            if (stream == null) throw new IllegalArgumentException("Missing definition resource: " + path);
            return JsonParser.parseReader(new InputStreamReader(stream, StandardCharsets.UTF_8))
                    .getAsJsonObject();
        } catch (Exception exception) {
            if (exception instanceof IllegalArgumentException illegal) throw illegal;
            throw new IllegalArgumentException("Unable to load definition: " + path, exception);
        }
    }

    private static InputStream resource(String path) {
        InputStream stream = ArsenalDefinitionLoader.class.getClassLoader().getResourceAsStream(path);
        if (stream == null) throw new IllegalArgumentException("Missing definition resource: " + path);
        return stream;
    }

    private static String required(JsonObject json, String key) {
        if (!json.has(key) || !json.get(key).isJsonPrimitive()) {
            throw new IllegalArgumentException("Missing definition field: " + key);
        }
        return json.get(key).getAsString();
    }

    /** Returns an optional string field, or null when the definition omits it. */
    private static String optional(JsonObject json, String key) {
        return json.has(key) && json.get(key).isJsonPrimitive() ? json.get(key).getAsString() : null;
    }

    private static int integer(JsonObject json, String key) { return json.get(key).getAsInt(); }
    private static float decimal(JsonObject json, String key) { return json.get(key).getAsFloat(); }

    private static <T extends Enum<T>> T parseEnum(Class<T> type, JsonObject json, String key) {
        String value = required(json, key);
        try { return Enum.valueOf(type, value.toUpperCase(java.util.Locale.ROOT)); }
        catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException("Invalid " + key + " value: " + value, exception);
        }
    }
}
