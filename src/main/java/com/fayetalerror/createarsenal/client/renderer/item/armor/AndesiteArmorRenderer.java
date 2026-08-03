package com.fayetalerror.createarsenal.client.renderer.item.armor;

import com.fayetalerror.createarsenal.client.model.item.armor.AndesiteArmorModel;
import com.fayetalerror.createarsenal.item.armor.AndesiteArmorItem;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

/** Renders every Andesite Armor piece with the shared GeckoLib armor model. */
public final class AndesiteArmorRenderer extends GeoArmorRenderer<AndesiteArmorItem> {
    /** Connects the armor renderer to the model that resolves the shared assets. */
    public AndesiteArmorRenderer() {
        super(new AndesiteArmorModel());
    }
}
