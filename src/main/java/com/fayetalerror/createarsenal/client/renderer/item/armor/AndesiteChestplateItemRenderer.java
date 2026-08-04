package com.fayetalerror.createarsenal.client.renderer.item.armor;

import com.fayetalerror.createarsenal.client.model.item.armor.AndesiteChestplateItemModel;
import com.fayetalerror.createarsenal.item.armor.AndesiteArmorItem;
import software.bernie.geckolib.renderer.GeoItemRenderer;

/** Renders the dedicated three-dimensional chestplate model in item-display contexts. */
public final class AndesiteChestplateItemRenderer extends GeoItemRenderer<AndesiteArmorItem> {
    /** Connects the item renderer to the chestplate-only model and texture. */
    public AndesiteChestplateItemRenderer() {
        super(new AndesiteChestplateItemModel());
    }
}
