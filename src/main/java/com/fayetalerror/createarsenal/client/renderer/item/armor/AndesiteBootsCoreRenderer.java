package com.fayetalerror.createarsenal.client.renderer.item.armor;

import com.fayetalerror.createarsenal.client.model.item.armor.AndesiteBootsCoreModel;
import com.fayetalerror.createarsenal.item.armor.AndesiteArmorCoreItem;
import software.bernie.geckolib.renderer.GeoItemRenderer;

/** Renders the Andesite Boots Core in item-display contexts. */
public final class AndesiteBootsCoreRenderer extends GeoItemRenderer<AndesiteArmorCoreItem> {
    public AndesiteBootsCoreRenderer() {
        super(new AndesiteBootsCoreModel());
    }
}
