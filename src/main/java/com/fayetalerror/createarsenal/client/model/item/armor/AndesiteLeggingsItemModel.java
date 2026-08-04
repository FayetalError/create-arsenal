package com.fayetalerror.createarsenal.client.model.item.armor;

import com.fayetalerror.createarsenal.CreateArsenal;
import com.fayetalerror.createarsenal.item.armor.AndesiteArmorItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedItemGeoModel;

/** Resolves the dedicated geometry and texture used only for the leggings item view. */
public final class AndesiteLeggingsItemModel extends DefaultedItemGeoModel<AndesiteArmorItem> {
    /** Expands the leggings asset path below GeckoLib's categorized item folders. */
    public AndesiteLeggingsItemModel() {
        super(ResourceLocation.fromNamespaceAndPath(CreateArsenal.MODID, "armor/andesite_leggings"));
    }
}
