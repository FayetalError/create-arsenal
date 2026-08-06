package com.fayetalerror.createarsenal.client.renderer.item.tools;

import com.fayetalerror.createarsenal.client.model.item.tools.BrassPickaxeModel;
import com.fayetalerror.createarsenal.item.tools.BrassPickaxeItem;
import software.bernie.geckolib.renderer.GeoItemRenderer;

/** Renders the andesite pickaxe with its GeckoLib model and texture resources. */
public final class BrassPickaxeRenderer extends GeoItemRenderer<BrassPickaxeItem> {
    public BrassPickaxeRenderer() {
        super(new BrassPickaxeModel());
    }
}
