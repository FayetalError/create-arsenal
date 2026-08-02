package com.fayetalerror.createarsenal.client.model.item.weapons;

import com.fayetalerror.createarsenal.CreateArsenal;
import com.fayetalerror.createarsenal.item.weapons.AndesiteSwordItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedItemGeoModel;

/** Resolves the andesite sword's model and texture from the categorized GeckoLib weapon paths. */
public final class AndesiteSwordModel extends DefaultedItemGeoModel<AndesiteSwordItem> {
    public AndesiteSwordModel() {
        super(ResourceLocation.fromNamespaceAndPath(CreateArsenal.MODID, "weapons/andesite_sword"));
    }
}
