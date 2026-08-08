package com.fayetalerror.createarsenal.item.tools;

import com.fayetalerror.createarsenal.item.ArsenalGeoItem;
import com.fayetalerror.createarsenal.item.ArsenalGeoItemSupport;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Tier;

/** Data-driven hoe that retains vanilla hoe behavior and GeckoLib rendering. */
public final class ArsenalHoeItem extends HoeItem implements ArsenalGeoItem {
    private final ArsenalGeoItemSupport geoSupport;

    public ArsenalHoeItem(Tier tier, Properties properties, String modelPath) {
        super(tier, properties);
        this.geoSupport = new ArsenalGeoItemSupport(this, modelPath);
    }

    @Override
    public ArsenalGeoItemSupport geoSupport() {
        return geoSupport;
    }
}
