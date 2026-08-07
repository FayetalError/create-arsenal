package com.fayetalerror.createarsenal.item.tools;

import com.fayetalerror.createarsenal.item.ArsenalGeoItemSupport;
import java.util.function.Consumer;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Tier;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.client.GeoRenderProvider;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;

/** Data-driven hoe that retains vanilla hoe behavior and GeckoLib rendering. */
public final class ArsenalHoeItem extends HoeItem implements GeoItem {
    private final ArsenalGeoItemSupport geoSupport;

    public ArsenalHoeItem(Tier tier, Properties properties, String modelPath) {
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
