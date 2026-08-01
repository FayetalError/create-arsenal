package com.fayetalerror.createarsenal.config;

import java.util.List;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.common.ModConfigSpec;

/** Declares and validates every common configuration option for Create: Arsenal. */
public final class ArsenalConfig {
    /** Builder used to collect options before producing the immutable specification. */
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    /** Controls whether common setup logs the dirt block's registry name. */
    public static final ModConfigSpec.BooleanValue LOG_DIRT_BLOCK = BUILDER
            .comment("Whether to log the dirt block on common setup")
            .define("logDirtBlock", true);

    /** Example non-negative numeric option retained from the NeoForge starter project. */
    public static final ModConfigSpec.IntValue MAGIC_NUMBER = BUILDER
            .comment("A magic number")
            .defineInRange("magicNumber", 42, 0, Integer.MAX_VALUE);

    /** Text placed immediately before the configured magic number in the setup log. */
    public static final ModConfigSpec.ConfigValue<String> MAGIC_NUMBER_INTRODUCTION = BUILDER
            .comment("What you want the introduction message to be for the magic number")
            .define("magicNumberIntroduction", "The magic number is... ");

    /** Registry names of items that common setup writes to the log. */
    public static final ModConfigSpec.ConfigValue<List<? extends String>> ITEM_STRINGS = BUILDER
            .comment("A list of items to log on common setup.")
            .defineListAllowEmpty("items", List.of("minecraft:iron_ingot"), () -> "", ArsenalConfig::validateItemName);

    /** Completed specification registered with the mod container during initialization. */
    public static final ModConfigSpec SPEC = BUILDER.build();

    /** Utility class; configuration values are exposed as static fields. */
    private ArsenalConfig() {
    }

    /**
     * Accepts list entries only when they name an item currently present in the item registry.
     *
     * @param value candidate value supplied by the configuration loader
     * @return {@code true} when the value is a valid, registered item identifier
     */
    private static boolean validateItemName(Object value) {
        return value instanceof String itemName
                && BuiltInRegistries.ITEM.containsKey(ResourceLocation.parse(itemName));
    }
}
