package com.fayetalerror.createarsenal.client.renderer.item.armor;

import com.fayetalerror.createarsenal.client.model.item.armor.AndesiteChestplateCoreModel;
import com.fayetalerror.createarsenal.item.armor.AndesiteArmorCoreItem;
import software.bernie.geckolib.renderer.GeoItemRenderer;

/** Renders the Andesite Chestplate Core in item-display contexts. */
public final class AndesiteChestplateCoreRenderer extends GeoItemRenderer<AndesiteArmorCoreItem> {
    public AndesiteChestplateCoreRenderer() {
        super(new AndesiteChestplateCoreModel());
    }
}
