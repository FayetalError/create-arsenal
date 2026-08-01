package com.fayetalerror.createarsenal.client.renderer.item.tools;

import com.fayetalerror.createarsenal.client.model.item.tools.AndesitePickaxeModel;
import com.fayetalerror.createarsenal.item.tools.AndesitePickaxeItem;
import software.bernie.geckolib.renderer.GeoItemRenderer;

/** Renders the andesite pickaxe with its GeckoLib model and texture resources. */
public final class AndesitePickaxeRenderer extends GeoItemRenderer<AndesitePickaxeItem> {
    public AndesitePickaxeRenderer() {
        super(new AndesitePickaxeModel());
    }
}
