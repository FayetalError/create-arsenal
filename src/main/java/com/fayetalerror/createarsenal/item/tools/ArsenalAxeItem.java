package com.fayetalerror.createarsenal.item.tools;

import com.fayetalerror.createarsenal.item.ArsenalGeoItem;
import com.fayetalerror.createarsenal.item.ArsenalGeoItemSupport;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Tier;

/** Data-driven axe that retains vanilla axe behavior and GeckoLib rendering. */
public final class ArsenalAxeItem extends AxeItem implements ArsenalGeoItem {
    private final ArsenalGeoItemSupport geoSupport;

    public ArsenalAxeItem(Tier tier, Properties properties, String modelPath) {
        super(tier, properties);
        this.geoSupport = new ArsenalGeoItemSupport(this, modelPath);
    }

    @Override
    public ArsenalGeoItemSupport geoSupport() {
        return geoSupport;
    }
}
