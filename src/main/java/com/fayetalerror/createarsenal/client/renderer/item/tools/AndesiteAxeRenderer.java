package com.fayetalerror.createarsenal.client.renderer.item.tools;

import com.fayetalerror.createarsenal.client.model.item.tools.AndesiteAxeModel;
import com.fayetalerror.createarsenal.item.tools.AndesiteAxeItem;
import software.bernie.geckolib.renderer.GeoItemRenderer;

/** Renders the andesite axe with its GeckoLib model and texture. */
public final class AndesiteAxeRenderer extends GeoItemRenderer<AndesiteAxeItem> {
    public AndesiteAxeRenderer() {
        super(new AndesiteAxeModel());
    }
}
