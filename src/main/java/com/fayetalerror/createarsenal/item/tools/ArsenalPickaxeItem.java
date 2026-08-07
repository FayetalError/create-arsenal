package com.fayetalerror.createarsenal.item.tools;

import com.fayetalerror.createarsenal.item.ArsenalGeoItemSupport;
import java.util.function.Consumer;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Tier;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.client.GeoRenderProvider;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;

/** Data-driven pickaxe that retains vanilla pickaxe behavior and GeckoLib rendering. */
public final class ArsenalPickaxeItem extends PickaxeItem implements GeoItem {
    private final ArsenalGeoItemSupport geoSupport;

    public ArsenalPickaxeItem(Tier tier, Properties properties, String modelPath) {
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
