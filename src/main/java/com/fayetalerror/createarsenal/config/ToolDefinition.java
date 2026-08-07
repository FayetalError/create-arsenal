package com.fayetalerror.createarsenal.config;

/**
 * Balance and rendering data for a tool definition.
 *
 * @param item common item metadata
 * @param tierName the logical tier name, such as {@code andesite} or {@code brass}
 * @param toolType the vanilla tool behavior, such as {@code pickaxe} or {@code axe}
 * @param durability maximum durability of the tool
 * @param attackDamage additional attack damage supplied by the tool
 * @param attackSpeed attack speed modifier supplied by the tool
 */
public record ToolDefinition(
        ArsenalItemDefinition item,
        String tierName,
        ToolType toolType,
        int durability,
        float attackDamage,
        float attackSpeed
) {
}
