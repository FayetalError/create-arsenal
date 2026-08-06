package com.fayetalerror.createarsenal.client.renderer.item;

import net.minecraft.world.item.Item;
import com.fayetalerror.createarsenal.client.model.item.ArsenalGeoItemModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;
import software.bernie.geckolib.animatable.GeoAnimatable;

/** Generic GeckoLib item renderer paired with a definition-provided model path. */
public class ArsenalGeoItemRenderer<T extends Item & GeoAnimatable> extends GeoItemRenderer<T> {
    public ArsenalGeoItemRenderer(String modelPath) {
        super(new ArsenalGeoItemModel<>(modelPath));
    }
}
