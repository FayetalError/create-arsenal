package com.fayetalerror.createarsenal.client.renderer.item.tools;

import com.fayetalerror.createarsenal.client.model.item.tools.AndesiteHoeModel;
import com.fayetalerror.createarsenal.item.tools.AndesiteHoeItem;
import software.bernie.geckolib.renderer.GeoItemRenderer;

/** Renders the andesite hoe with its GeckoLib model and texture. */
public final class AndesiteHoeRenderer extends GeoItemRenderer<AndesiteHoeItem> {
    public AndesiteHoeRenderer() {
        super(new AndesiteHoeModel());
    }
}
