package com.fayetalerror.createarsenal.client.renderer.item.armor;

import com.fayetalerror.createarsenal.client.model.item.armor.AndesiteLeggingsCoreModel;
import com.fayetalerror.createarsenal.item.armor.AndesiteArmorCoreItem;
import software.bernie.geckolib.renderer.GeoItemRenderer;

/** Renders the Andesite Leggings Core in item-display contexts. */
public final class AndesiteLeggingsCoreRenderer extends GeoItemRenderer<AndesiteArmorCoreItem> {
    public AndesiteLeggingsCoreRenderer() {
        super(new AndesiteLeggingsCoreModel());
    }
}
