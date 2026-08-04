package com.fayetalerror.createarsenal.client.renderer.item.armor;

import com.fayetalerror.createarsenal.client.model.item.armor.AndesiteBootsItemModel;
import com.fayetalerror.createarsenal.item.armor.AndesiteArmorItem;
import software.bernie.geckolib.renderer.GeoItemRenderer;

/** Renders the dedicated three-dimensional boots model in item-display contexts. */
public final class AndesiteBootsItemRenderer extends GeoItemRenderer<AndesiteArmorItem> {
    /** Connects the item renderer to the boots-only model and texture. */
    public AndesiteBootsItemRenderer() {
        super(new AndesiteBootsItemModel());
    }
}
