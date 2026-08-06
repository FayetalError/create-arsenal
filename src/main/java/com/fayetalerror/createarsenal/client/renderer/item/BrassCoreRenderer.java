package com.fayetalerror.createarsenal.client.renderer.item;

import com.fayetalerror.createarsenal.client.model.item.BrassCoreModel;
import com.fayetalerror.createarsenal.item.BrassCoreItem;
import software.bernie.geckolib.renderer.GeoItemRenderer;

/** Renders brass core components using the model path supplied by each registry entry. */
public final class BrassCoreRenderer extends GeoItemRenderer<BrassCoreItem> {
    public BrassCoreRenderer(String modelPath) { super(new BrassCoreModel(modelPath)); }
}
