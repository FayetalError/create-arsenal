package com.fayetalerror.createarsenal.client.model.item.tools;

import com.fayetalerror.createarsenal.CreateArsenal;
import com.fayetalerror.createarsenal.item.tools.AndesitePickaxeItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedItemGeoModel;

/** Resolves the andesite pickaxe's model and texture using GeckoLib's categorized item paths. */
public final class AndesitePickaxeModel extends DefaultedItemGeoModel<AndesitePickaxeItem> {
    public AndesitePickaxeModel() {
        // GeckoLib expands this into geo/item/tools and textures/item/tools resource paths.
        super(ResourceLocation.fromNamespaceAndPath(CreateArsenal.MODID, "tools/andesite_pickaxe"));
    }
}
