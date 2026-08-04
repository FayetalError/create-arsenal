package com.fayetalerror.createarsenal.client.renderer.item.armor;

import com.fayetalerror.createarsenal.client.model.item.armor.AndesiteHelmetCoreModel;
import com.fayetalerror.createarsenal.item.armor.AndesiteArmorCoreItem;
import software.bernie.geckolib.renderer.GeoItemRenderer;

/** Renders the Andesite Helmet Core in item-display contexts. */
public final class AndesiteHelmetCoreRenderer extends GeoItemRenderer<AndesiteArmorCoreItem> {
    public AndesiteHelmetCoreRenderer() {
        super(new AndesiteHelmetCoreModel());
    }
}
