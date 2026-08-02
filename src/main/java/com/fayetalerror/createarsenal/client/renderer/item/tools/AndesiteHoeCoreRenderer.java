package com.fayetalerror.createarsenal.client.renderer.item.tools;

import com.fayetalerror.createarsenal.client.model.item.tools.AndesiteHoeCoreModel;
import com.fayetalerror.createarsenal.item.tools.AndesiteHoeCoreItem;
import software.bernie.geckolib.renderer.GeoItemRenderer;

/** Renders the andesite hoe core with its GeckoLib model and texture. */
public final class AndesiteHoeCoreRenderer extends GeoItemRenderer<AndesiteHoeCoreItem> {
    public AndesiteHoeCoreRenderer() {
        super(new AndesiteHoeCoreModel());
    }
}
