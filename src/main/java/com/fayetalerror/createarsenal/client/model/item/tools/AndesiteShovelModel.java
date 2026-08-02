package com.fayetalerror.createarsenal.client.model.item.tools;

import com.fayetalerror.createarsenal.CreateArsenal;
import com.fayetalerror.createarsenal.item.tools.AndesiteShovelItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedItemGeoModel;

/** Resolves the andesite shovel's model and texture from the categorized GeckoLib tool paths. */
public final class AndesiteShovelModel extends DefaultedItemGeoModel<AndesiteShovelItem> {
    public AndesiteShovelModel() {
        super(ResourceLocation.fromNamespaceAndPath(CreateArsenal.MODID, "tools/andesite_shovel"));
    }
}
