package com.fayetalerror.createarsenal.client.renderer.item.armor;

import com.fayetalerror.createarsenal.client.model.item.armor.AndesiteHelmetItemModel;
import com.fayetalerror.createarsenal.item.armor.AndesiteArmorItem;
import software.bernie.geckolib.renderer.GeoItemRenderer;

/** Renders the dedicated three-dimensional helmet model in item-display contexts. */
public final class AndesiteHelmetItemRenderer extends GeoItemRenderer<AndesiteArmorItem> {
    /** Connects the item renderer to the helmet-only model and texture. */
    public AndesiteHelmetItemRenderer() {
        super(new AndesiteHelmetItemModel());
    }
}
