package com.fayetalerror.createarsenal.client.model;

import com.fayetalerror.createarsenal.CreateArsenal;
import com.fayetalerror.createarsenal.item.AndesitePickaxeCoreItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedItemGeoModel;

/** Resolves the pickaxe core's static model and texture from GeckoLib's standard item paths. */
public final class AndesitePickaxeCoreModel extends DefaultedItemGeoModel<AndesitePickaxeCoreItem> {
    public AndesitePickaxeCoreModel() {
        super(ResourceLocation.fromNamespaceAndPath(CreateArsenal.MODID, "andesite_pickaxe_core"));
    }
}
