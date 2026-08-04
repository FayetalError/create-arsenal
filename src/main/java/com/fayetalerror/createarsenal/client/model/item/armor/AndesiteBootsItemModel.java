package com.fayetalerror.createarsenal.client.model.item.armor;

import com.fayetalerror.createarsenal.CreateArsenal;
import com.fayetalerror.createarsenal.item.armor.AndesiteArmorItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedItemGeoModel;

/** Resolves the dedicated geometry and texture used only for the boots item view. */
public final class AndesiteBootsItemModel extends DefaultedItemGeoModel<AndesiteArmorItem> {
    /** Expands the boots asset path below GeckoLib's categorized item folders. */
    public AndesiteBootsItemModel() {
        super(ResourceLocation.fromNamespaceAndPath(CreateArsenal.MODID, "armor/andesite_boots"));
    }
}
