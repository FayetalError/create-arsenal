package com.fayetalerror.createarsenal.config;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.Tier;

public record WeaponDefinition(ArsenalItemDefinition item, String tierName, WeaponType weaponType,
        int durability, float attackDamage, float attackSpeed, String animationPath,
        float arrowDamageBonus) implements ArsenalDefinition {
    @Override public String id() { return item.id(); }
    @Override public ItemKind kind() { return item.kind(); }
    @Override public String modelPath() { return item.modelPath(); }

    /**
     * Creates the Minecraft properties using this definition's balance values.
     * The sword modifiers account for Minecraft's one base attack damage and four base attack
     * speed so the definition values are the values shown in game.
     */
    public Item.Properties properties(Tier tier) {
        return switch (weaponType) {
            case SWORD -> new Item.Properties().attributes(ItemAttributeModifiers.builder()
                    .add(Attributes.ATTACK_DAMAGE, new AttributeModifier(
                            ResourceLocation.withDefaultNamespace("base_attack_damage"), attackDamage - 1.0F,
                            AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND)
                    .add(Attributes.ATTACK_SPEED, new AttributeModifier(
                            ResourceLocation.withDefaultNamespace("base_attack_speed"), attackSpeed - 4.0F,
                            AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND)
                    .build());
            case BOW -> new Item.Properties().durability(durability);
        };
    }
}
