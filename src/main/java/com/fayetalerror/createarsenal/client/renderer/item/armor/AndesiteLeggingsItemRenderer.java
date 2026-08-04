package com.fayetalerror.createarsenal.client.renderer.item.armor;

import com.fayetalerror.createarsenal.client.model.item.armor.AndesiteLeggingsItemModel;
import com.fayetalerror.createarsenal.item.armor.AndesiteArmorItem;
import software.bernie.geckolib.renderer.GeoItemRenderer;

/** Renders the dedicated three-dimensional leggings model in item-display contexts. */
public final class AndesiteLeggingsItemRenderer extends GeoItemRenderer<AndesiteArmorItem> {
    /** Connects the item renderer to the leggings-only model and texture. */
    public AndesiteLeggingsItemRenderer() {
        super(new AndesiteLeggingsItemModel());
    }
}
