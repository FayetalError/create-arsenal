package com.fayetalerror.createarsenal.client.model.item;

import com.fayetalerror.createarsenal.CreateArsenal;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import software.bernie.geckolib.model.DefaultedItemGeoModel;
import software.bernie.geckolib.animatable.GeoAnimatable;

/** Generic GeckoLib item model that receives its asset path from the definition. */
public class ArsenalGeoItemModel<T extends Item & GeoAnimatable> extends DefaultedItemGeoModel<T> {
    public ArsenalGeoItemModel(String modelPath) {
        super(ResourceLocation.fromNamespaceAndPath(CreateArsenal.MODID, modelPath));
    }
}
