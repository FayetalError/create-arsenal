package com.fayetalerror.createarsenal.client.model.item.tools;

import com.fayetalerror.createarsenal.CreateArsenal;
import com.fayetalerror.createarsenal.item.tools.BrassPickaxeItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedItemGeoModel;

/** Resolves the brass pickaxe's model and texture using GeckoLib's categorized item paths. */
public final class BrassPickaxeModel extends DefaultedItemGeoModel<BrassPickaxeItem> {
    public BrassPickaxeModel() {
        // GeckoLib expands this into geo/item/tools and textures/item/tools resource paths.
        super(ResourceLocation.fromNamespaceAndPath(CreateArsenal.MODID, "tools/brass_pickaxe"));
    }
}
