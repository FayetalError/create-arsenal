package com.fayetalerror.createarsenal.client.renderer.item.weapons;

import com.fayetalerror.createarsenal.client.model.item.weapons.AndesiteSwordModel;
import com.fayetalerror.createarsenal.item.weapons.AndesiteSwordItem;
import software.bernie.geckolib.renderer.GeoItemRenderer;

/** Renders the andesite sword with its GeckoLib model and texture. */
public final class AndesiteSwordRenderer extends GeoItemRenderer<AndesiteSwordItem> {
    public AndesiteSwordRenderer() {
        super(new AndesiteSwordModel());
    }
}
