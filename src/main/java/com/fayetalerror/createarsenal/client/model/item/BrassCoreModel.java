package com.fayetalerror.createarsenal.client.model.item;

import com.fayetalerror.createarsenal.CreateArsenal;
import com.fayetalerror.createarsenal.item.BrassCoreItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedItemGeoModel;

/** Resolves a brass core's categorized geo and texture paths from its model key. */
public final class BrassCoreModel extends DefaultedItemGeoModel<BrassCoreItem> {
    public BrassCoreModel(String modelPath) {
        super(ResourceLocation.fromNamespaceAndPath(CreateArsenal.MODID, modelPath));
    }
}
