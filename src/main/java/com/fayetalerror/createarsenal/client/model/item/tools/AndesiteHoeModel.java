package com.fayetalerror.createarsenal.client.model.item.tools;

import com.fayetalerror.createarsenal.CreateArsenal;
import com.fayetalerror.createarsenal.item.tools.AndesiteHoeItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedItemGeoModel;

/** Resolves the andesite hoe's model and texture from the categorized GeckoLib tool paths. */
public final class AndesiteHoeModel extends DefaultedItemGeoModel<AndesiteHoeItem> {
    public AndesiteHoeModel() {
        super(ResourceLocation.fromNamespaceAndPath(CreateArsenal.MODID, "tools/andesite_hoe"));
    }
}
