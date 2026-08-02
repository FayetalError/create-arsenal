package com.fayetalerror.createarsenal.client.renderer.item.weapons;

import com.fayetalerror.createarsenal.client.model.item.weapons.AndesiteSwordCoreModel;
import com.fayetalerror.createarsenal.item.weapons.AndesiteSwordCoreItem;
import software.bernie.geckolib.renderer.GeoItemRenderer;

/** Renders the andesite sword core with its GeckoLib model and texture. */
public final class AndesiteSwordCoreRenderer extends GeoItemRenderer<AndesiteSwordCoreItem> {
    public AndesiteSwordCoreRenderer() {
        super(new AndesiteSwordCoreModel());
    }
}
