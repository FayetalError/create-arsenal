package com.fayetalerror.createarsenal.client.model.item.armor;

import com.fayetalerror.createarsenal.CreateArsenal;
import com.fayetalerror.createarsenal.item.armor.AndesiteArmorItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedItemGeoModel;

/** Resolves the shared Andesite Armor geometry and texture from categorized resource paths. */
public final class AndesiteArmorModel extends DefaultedItemGeoModel<AndesiteArmorItem> {
    /**
     * Expands {@code armor/andesite_armor} into GeckoLib's model, texture,
     * and optional animation resource paths below the item asset folders.
     */
    public AndesiteArmorModel() {
        super(ResourceLocation.fromNamespaceAndPath(CreateArsenal.MODID, "armor/andesite_armor"));
    }
}
