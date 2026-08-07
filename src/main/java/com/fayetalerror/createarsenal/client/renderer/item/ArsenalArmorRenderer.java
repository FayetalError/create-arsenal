package com.fayetalerror.createarsenal.client.renderer.item;

import com.fayetalerror.createarsenal.item.armor.ArsenalArmorItem;
import com.fayetalerror.createarsenal.CreateArsenal;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedItemGeoModel;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

/** Generic worn-armor renderer selected by the armor definition. */
public final class ArsenalArmorRenderer extends GeoArmorRenderer<ArsenalArmorItem> {
    public ArsenalArmorRenderer(String modelPath) {
        super(new DefaultedItemGeoModel<>(
                ResourceLocation.fromNamespaceAndPath(CreateArsenal.MODID, modelPath)));
    }
}
