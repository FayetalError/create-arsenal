package com.fayetalerror.createarsenal.client.renderer.item.tools;

import com.fayetalerror.createarsenal.client.model.item.tools.AndesitePickaxeCoreModel;
import com.fayetalerror.createarsenal.item.tools.AndesitePickaxeCoreItem;
import software.bernie.geckolib.renderer.GeoItemRenderer;

/** Renders the andesite pickaxe core with its GeckoLib model and texture. */
public final class AndesitePickaxeCoreRenderer extends GeoItemRenderer<AndesitePickaxeCoreItem> {
    public AndesitePickaxeCoreRenderer() {
        super(new AndesitePickaxeCoreModel());
    }
}
