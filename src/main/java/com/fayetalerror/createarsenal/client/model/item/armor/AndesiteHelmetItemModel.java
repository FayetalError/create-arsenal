package com.fayetalerror.createarsenal.client.model.item.armor;

import com.fayetalerror.createarsenal.CreateArsenal;
import com.fayetalerror.createarsenal.item.armor.AndesiteArmorItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedItemGeoModel;

/** Resolves the dedicated geometry and texture used only for the helmet item view. */
public final class AndesiteHelmetItemModel extends DefaultedItemGeoModel<AndesiteArmorItem> {
    /** Expands the helmet asset path below GeckoLib's categorized item folders. */
    public AndesiteHelmetItemModel() {
        super(ResourceLocation.fromNamespaceAndPath(CreateArsenal.MODID, "armor/andesite_helmet"));
    }
}
