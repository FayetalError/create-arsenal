package com.fayetalerror.createarsenal.item;

import java.util.function.Consumer;
import net.minecraft.world.item.Item;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.client.GeoRenderProvider;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;

/** Generic data-driven item without tool, weapon, or armor functionality. */
public final class ArsenalItem extends Item implements GeoItem {
    private final ArsenalGeoItemSupport geoSupport;

    public ArsenalItem(String modelPath, Properties properties) {
        super(properties);
        this.geoSupport = new ArsenalGeoItemSupport(this, modelPath);
        GeoItem.registerSyncedAnimatable(this);
    }

    @Override
    public void createGeoRenderer(Consumer<GeoRenderProvider> consumer) {
        geoSupport.createRenderer(consumer);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return geoSupport.animationCache();
    }
}
