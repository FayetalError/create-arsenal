package com.fayetalerror.createarsenal.client.model.item.armor;

import com.fayetalerror.createarsenal.CreateArsenal;
import com.fayetalerror.createarsenal.item.armor.AndesiteArmorCoreItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedItemGeoModel;

/** Resolves the boots core's static geometry and texture. */
public final class AndesiteBootsCoreModel extends DefaultedItemGeoModel<AndesiteArmorCoreItem> {
    public AndesiteBootsCoreModel() {
        super(ResourceLocation.fromNamespaceAndPath(CreateArsenal.MODID, "armor/andesite_boots_core"));
    }
}
