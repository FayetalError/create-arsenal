package com.fayetalerror.createarsenal.config;

import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.Tier;

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
) implements ArsenalDefinition {
    @Override
    public String id() { return item.id(); }

    @Override
    public ItemKind kind() { return item.kind(); }

    @Override
    public String modelPath() { return item.modelPath(); }

    /** Creates the Minecraft properties using this definition's balance values. */
    public Item.Properties properties(Tier tier) {
        return new Item.Properties().attributes(switch (toolType) {
            case PICKAXE -> PickaxeItem.createAttributes(tier, attackDamage, attackSpeed);
            case AXE -> AxeItem.createAttributes(tier, attackDamage, attackSpeed);
            case SHOVEL -> ShovelItem.createAttributes(tier, attackDamage, attackSpeed);
            case HOE -> HoeItem.createAttributes(tier, attackDamage, attackSpeed);
            case MULTI_TOOL -> DiggerItem.createAttributes(tier, attackDamage, attackSpeed);
        });
    }
}
