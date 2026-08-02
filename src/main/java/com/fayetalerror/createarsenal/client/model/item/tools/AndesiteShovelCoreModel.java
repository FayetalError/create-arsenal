package com.fayetalerror.createarsenal.client.model.item.tools;

import com.fayetalerror.createarsenal.CreateArsenal;
import com.fayetalerror.createarsenal.item.tools.AndesiteShovelCoreItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedItemGeoModel;

/** Resolves the andesite shovel core's model and texture from the categorized GeckoLib tool paths. */
public final class AndesiteShovelCoreModel extends DefaultedItemGeoModel<AndesiteShovelCoreItem> {
    public AndesiteShovelCoreModel() {
        super(ResourceLocation.fromNamespaceAndPath(CreateArsenal.MODID, "tools/andesite_shovel_core"));
    }
}
