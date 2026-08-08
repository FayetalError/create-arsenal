package com.fayetalerror.createarsenal.item;

import java.util.function.Consumer;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.client.GeoRenderProvider;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;

/** Shared GeckoLib behavior for Arsenal items that retain different vanilla item parents. */
public interface ArsenalGeoItem extends GeoItem {
    /** Returns the shared GeckoLib support object for this item. */
    ArsenalGeoItemSupport geoSupport();

    @Override
    default void createGeoRenderer(Consumer<GeoRenderProvider> consumer) {
        geoSupport().createRenderer(consumer);
    }

    @Override
    default void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        geoSupport().registerControllers(controllers);
    }

    @Override
    default AnimatableInstanceCache getAnimatableInstanceCache() {
        return geoSupport().animationCache();
    }

    /** Registers an item with GeckoLib's synchronized animatable registry. */
    static void register(ArsenalGeoItem item) {
        GeoItem.registerSyncedAnimatable(item);
    }
}
