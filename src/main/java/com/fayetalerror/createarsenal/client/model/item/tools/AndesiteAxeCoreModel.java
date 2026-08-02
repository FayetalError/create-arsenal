package com.fayetalerror.createarsenal.client.model.item.tools;

import com.fayetalerror.createarsenal.CreateArsenal;
import com.fayetalerror.createarsenal.item.tools.AndesiteAxeCoreItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedItemGeoModel;

/** Resolves the andesite axe core's model and texture from the categorized GeckoLib tool paths. */
public final class AndesiteAxeCoreModel extends DefaultedItemGeoModel<AndesiteAxeCoreItem> {
    public AndesiteAxeCoreModel() {
        super(ResourceLocation.fromNamespaceAndPath(CreateArsenal.MODID, "tools/andesite_axe_core"));
    }
}
