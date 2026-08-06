package com.fayetalerror.createarsenal.item;

import java.util.function.Consumer;
import java.util.function.Supplier;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.client.GeoRenderProvider;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.util.GeckoLibUtil;

/** Shared GeckoLib state and lazy renderer wiring for vanilla-derived items. */
public final class ArsenalGeoItemSupport {
    private final AnimatableInstanceCache animationCache;

    public ArsenalGeoItemSupport(GeoItem owner) {
        this.animationCache = GeckoLibUtil.createInstanceCache(owner);
        GeoItem.registerSyncedAnimatable(owner);
    }

    public void createRenderer(
            Consumer<GeoRenderProvider> consumer,
            Supplier<BlockEntityWithoutLevelRenderer> rendererSupplier
    ) {
        consumer.accept(new GeoRenderProvider() {
            private BlockEntityWithoutLevelRenderer renderer;

            @Override
            public BlockEntityWithoutLevelRenderer getGeoItemRenderer() {
                if (renderer == null) renderer = rendererSupplier.get();
                return renderer;
            }
        });
    }

    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
    }

    public AnimatableInstanceCache animationCache() {
        return animationCache;
    }
}
