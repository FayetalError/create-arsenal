package com.fayetalerror.createarsenal.client.model.item.weapons;

import com.fayetalerror.createarsenal.CreateArsenal;
import com.fayetalerror.createarsenal.item.weapons.AndesiteSwordCoreItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedItemGeoModel;

/** Resolves the andesite sword core's model and texture from the categorized GeckoLib weapon paths. */
public final class AndesiteSwordCoreModel extends DefaultedItemGeoModel<AndesiteSwordCoreItem> {
    public AndesiteSwordCoreModel() {
        super(ResourceLocation.fromNamespaceAndPath(CreateArsenal.MODID, "weapons/andesite_sword_core"));
    }
}
