package com.fayetalerror.createarsenal.config;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;

public record WeaponDefinition(ArsenalItemDefinition item, String tierName, WeaponType weaponType,
        int durability, float attackDamage, float attackSpeed, String animationPath,
        float arrowDamageBonus) implements ArsenalDefinition {
    @Override public String id() { return item.id(); }
    @Override public ItemKind kind() { return item.kind(); }
    @Override public String modelPath() { return item.modelPath(); }

    /** Creates the Minecraft properties using this definition's balance values. */
    public Item.Properties properties(Tier tier) {
        return switch (weaponType) {
            case SWORD -> new Item.Properties().attributes(
                    SwordItem.createAttributes(tier, attackDamage, attackSpeed));
            case BOW -> new Item.Properties().durability(durability);
        };
    }
}
