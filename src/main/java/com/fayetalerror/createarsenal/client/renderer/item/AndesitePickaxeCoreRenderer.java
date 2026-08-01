package com.fayetalerror.createarsenal.client.renderer.item;

import com.fayetalerror.createarsenal.client.model.AndesitePickaxeCoreModel;
import com.fayetalerror.createarsenal.item.AndesitePickaxeCoreItem;
import software.bernie.geckolib.renderer.GeoItemRenderer;

/** Renders the andesite pickaxe core with its GeckoLib model and texture. */
public final class AndesitePickaxeCoreRenderer extends GeoItemRenderer<AndesitePickaxeCoreItem> {
    public AndesitePickaxeCoreRenderer() {
        super(new AndesitePickaxeCoreModel());
    }
}
