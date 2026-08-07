package com.fayetalerror.createarsenal.item.tools;

import com.fayetalerror.createarsenal.item.ArsenalGeoItemSupport;
import java.util.function.Consumer;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Tier;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.client.GeoRenderProvider;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;

/** Data-driven axe that retains vanilla axe behavior and GeckoLib rendering. */
public final class ArsenalAxeItem extends AxeItem implements GeoItem {
    private final ArsenalGeoItemSupport geoSupport;

    public ArsenalAxeItem(Tier tier, Properties properties, String modelPath) {
        super(tier, properties);
        this.geoSupport = new ArsenalGeoItemSupport(this, modelPath);
    }

    @Override
    public void createGeoRenderer(Consumer<GeoRenderProvider> consumer) {
        geoSupport.createRenderer(consumer);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        geoSupport.registerControllers(controllers);
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return geoSupport.animationCache();
    }
}
