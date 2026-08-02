package com.fayetalerror.createarsenal.client.renderer.item.tools;

import com.fayetalerror.createarsenal.client.model.item.tools.AndesiteAxeCoreModel;
import com.fayetalerror.createarsenal.item.tools.AndesiteAxeCoreItem;
import software.bernie.geckolib.renderer.GeoItemRenderer;

/** Renders the andesite axe core with its GeckoLib model and texture. */
public final class AndesiteAxeCoreRenderer extends GeoItemRenderer<AndesiteAxeCoreItem> {
    public AndesiteAxeCoreRenderer() {
        super(new AndesiteAxeCoreModel());
    }
}
