package com.fayetalerror.createarsenal.client.renderer.item;

import net.minecraft.world.item.Item;
import com.fayetalerror.createarsenal.CreateArsenal;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedItemGeoModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;
import software.bernie.geckolib.animatable.GeoAnimatable;

/** Generic GeckoLib item renderer paired with a definition-provided model path. */
public class ArsenalGeoItemRenderer<T extends Item & GeoAnimatable> extends GeoItemRenderer<T> {
    public ArsenalGeoItemRenderer(String modelPath) {
        super(new DefaultedItemGeoModel<>(
                ResourceLocation.fromNamespaceAndPath(CreateArsenal.MODID, modelPath)));
    }
}
