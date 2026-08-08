package com.fayetalerror.createarsenal.item.tools;

import com.fayetalerror.createarsenal.item.ArsenalGeoItem;
import com.fayetalerror.createarsenal.item.ArsenalGeoItemSupport;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Tier;

/** Data-driven pickaxe that retains vanilla pickaxe behavior and GeckoLib rendering. */
public final class ArsenalPickaxeItem extends PickaxeItem implements ArsenalGeoItem {
    private final ArsenalGeoItemSupport geoSupport;

    public ArsenalPickaxeItem(Tier tier, Properties properties, String modelPath) {
        super(tier, properties);
        this.geoSupport = new ArsenalGeoItemSupport(this, modelPath);
    }

    @Override
    public ArsenalGeoItemSupport geoSupport() {
        return geoSupport;
    }
}
