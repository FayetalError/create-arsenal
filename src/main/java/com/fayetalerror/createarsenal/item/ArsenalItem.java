package com.fayetalerror.createarsenal.item;

import net.minecraft.world.item.Item;

/** Generic data-driven item without tool, weapon, or armor functionality. */
public final class ArsenalItem extends Item implements ArsenalGeoItem {
    private final ArsenalGeoItemSupport geoSupport;

    public ArsenalItem(String modelPath, Properties properties) {
        super(properties);
        this.geoSupport = new ArsenalGeoItemSupport(this, modelPath);
        ArsenalGeoItem.register(this);
    }

    @Override
    public ArsenalGeoItemSupport geoSupport() {
        return geoSupport;
    }
}
