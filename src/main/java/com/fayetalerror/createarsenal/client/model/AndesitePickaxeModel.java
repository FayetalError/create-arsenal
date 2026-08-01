package com.fayetalerror.createarsenal.client.model;

import com.fayetalerror.createarsenal.CreateArsenal;
import com.fayetalerror.createarsenal.item.AndesitePickaxeItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedItemGeoModel;

/** Resolves the andesite pickaxe's model, texture, and animation using GeckoLib's standard item paths. */
public final class AndesitePickaxeModel extends DefaultedItemGeoModel<AndesitePickaxeItem> {
    public AndesitePickaxeModel() {
        // GeckoLib expands this base location into geo/item, textures/item, and animations/item paths.
        super(ResourceLocation.fromNamespaceAndPath(CreateArsenal.MODID, "andesite_pickaxe"));
    }
}
