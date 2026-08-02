package com.fayetalerror.createarsenal.client.model.item.tools;

import com.fayetalerror.createarsenal.CreateArsenal;
import com.fayetalerror.createarsenal.item.tools.AndesiteHoeCoreItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedItemGeoModel;

/** Resolves the andesite hoe core's model and texture from the categorized GeckoLib tool paths. */
public final class AndesiteHoeCoreModel extends DefaultedItemGeoModel<AndesiteHoeCoreItem> {
    public AndesiteHoeCoreModel() {
        super(ResourceLocation.fromNamespaceAndPath(CreateArsenal.MODID, "tools/andesite_hoe_core"));
    }
}
