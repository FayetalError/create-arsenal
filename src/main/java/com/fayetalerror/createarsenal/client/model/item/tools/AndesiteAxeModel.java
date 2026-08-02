package com.fayetalerror.createarsenal.client.model.item.tools;

import com.fayetalerror.createarsenal.CreateArsenal;
import com.fayetalerror.createarsenal.item.tools.AndesiteAxeItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedItemGeoModel;

/** Resolves the andesite axe's model and texture from the categorized GeckoLib tool paths. */
public final class AndesiteAxeModel extends DefaultedItemGeoModel<AndesiteAxeItem> {
    public AndesiteAxeModel() {
        super(ResourceLocation.fromNamespaceAndPath(CreateArsenal.MODID, "tools/andesite_axe"));
    }
}
