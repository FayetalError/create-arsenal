package com.fayetalerror.createarsenal.item.tools;

import com.fayetalerror.createarsenal.item.ArsenalGeoItem;
import com.fayetalerror.createarsenal.item.ArsenalGeoItemSupport;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.Tier;

/** Data-driven shovel that retains vanilla shovel behavior and GeckoLib rendering. */
public final class ArsenalShovelItem extends ShovelItem implements ArsenalGeoItem {
    private final ArsenalGeoItemSupport geoSupport;

    public ArsenalShovelItem(Tier tier, Properties properties, String modelPath) {
        super(tier, properties);
        this.geoSupport = new ArsenalGeoItemSupport(this, modelPath);
    }

    @Override
    public ArsenalGeoItemSupport geoSupport() {
        return geoSupport;
    }
}
