package com.fayetalerror.createarsenal.client.renderer.item.tools;

import com.fayetalerror.createarsenal.client.model.item.tools.AndesiteShovelModel;
import com.fayetalerror.createarsenal.item.tools.AndesiteShovelItem;
import software.bernie.geckolib.renderer.GeoItemRenderer;

/** Renders the andesite shovel with its GeckoLib model and texture. */
public final class AndesiteShovelRenderer extends GeoItemRenderer<AndesiteShovelItem> {
    public AndesiteShovelRenderer() {
        super(new AndesiteShovelModel());
    }
}
