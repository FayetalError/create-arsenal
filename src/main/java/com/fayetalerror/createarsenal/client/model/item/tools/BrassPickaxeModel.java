package com.fayetalerror.createarsenal.client.model.item.tools;

import com.fayetalerror.createarsenal.item.tools.BrassPickaxeItem;
import com.fayetalerror.createarsenal.client.model.item.ArsenalGeoItemModel;

/** Resolves the brass pickaxe's model and texture using GeckoLib's categorized item paths. */
public final class BrassPickaxeModel extends ArsenalGeoItemModel<BrassPickaxeItem> {
    public BrassPickaxeModel() {
        // GeckoLib expands this into geo/item/tools and textures/item/tools resource paths.
        super("tools/brass_pickaxe");
    }
}
