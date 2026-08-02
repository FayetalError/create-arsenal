package com.fayetalerror.createarsenal.client.renderer.item.tools;

import com.fayetalerror.createarsenal.client.model.item.tools.AndesiteShovelCoreModel;
import com.fayetalerror.createarsenal.item.tools.AndesiteShovelCoreItem;
import software.bernie.geckolib.renderer.GeoItemRenderer;

/** Renders the andesite shovel core with its GeckoLib model and texture. */
public final class AndesiteShovelCoreRenderer extends GeoItemRenderer<AndesiteShovelCoreItem> {
    public AndesiteShovelCoreRenderer() {
        super(new AndesiteShovelCoreModel());
    }
}
