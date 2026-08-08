package com.fayetalerror.createarsenal.item.weapons;

import com.fayetalerror.createarsenal.item.ArsenalGeoItem;
import com.fayetalerror.createarsenal.item.ArsenalGeoItemSupport;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;

/** Data-driven sword that retains vanilla sword behavior and GeckoLib rendering. */
public final class ArsenalSwordItem extends SwordItem implements ArsenalGeoItem {
    private final ArsenalGeoItemSupport geoSupport;

    public ArsenalSwordItem(Tier tier, Properties properties, String modelPath) {
        super(tier, properties);
        this.geoSupport = new ArsenalGeoItemSupport(this, modelPath);
    }

    @Override
    public ArsenalGeoItemSupport geoSupport() {
        return geoSupport;
    }
}
