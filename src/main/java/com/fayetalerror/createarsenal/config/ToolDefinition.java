package com.fayetalerror.createarsenal.config;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.Tier;

/**
 * Balance and rendering data for a tool definition.
 *
 * @param item common item metadata
 * @param tierName the logical tier name, such as {@code andesite} or {@code brass}
 * @param toolType the vanilla tool behavior, such as {@code pickaxe} or {@code axe}
 * @param durability maximum durability of the tool
 * @param attackDamage total attack damage shown for the tool in game
 * @param attackSpeed attack speed modifier supplied by the tool
 */
public record ToolDefinition(
        ArsenalItemDefinition item,
        String tierName,
        ToolType toolType,
        int durability,
        float attackDamage,
        float attackSpeed,
        String animationPath
) implements ArsenalDefinition {
    @Override
    public String id() { return item.id(); }

    @Override
    public ItemKind kind() { return item.kind(); }

    @Override
    public String modelPath() { return item.modelPath(); }

    /**
     * Creates tool properties whose combat attributes come solely from this definition.
     * Minecraft gives every attacker one base attack damage and four base attack speed, so the
     * modifiers are reduced by those base values to make the definition values match the values
     * shown in game.
     */
    public Item.Properties properties(Tier tier) {
        return new Item.Properties().attributes(ItemAttributeModifiers.builder()
                .add(Attributes.ATTACK_DAMAGE, new AttributeModifier(
                        ResourceLocation.withDefaultNamespace("base_attack_damage"), attackDamage - 1.0F,
                        AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND)
                .add(Attributes.ATTACK_SPEED, new AttributeModifier(
                        ResourceLocation.withDefaultNamespace("base_attack_speed"), attackSpeed - 4.0F,
                        AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND)
                .build());
    }
}
