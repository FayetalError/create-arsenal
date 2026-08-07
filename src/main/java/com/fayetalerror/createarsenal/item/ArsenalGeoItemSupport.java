package com.fayetalerror.createarsenal.item;

import java.util.function.Consumer;
import java.util.function.Supplier;
import com.fayetalerror.createarsenal.client.renderer.item.ArsenalGeoItemRenderer;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.world.item.Item;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.client.GeoRenderProvider;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.util.GeckoLibUtil;

/** Shared GeckoLib state and lazy renderer wiring for vanilla-derived items. */
public final class ArsenalGeoItemSupport {
    private final AnimatableInstanceCache animationCache;
    private final String modelPath;

    public ArsenalGeoItemSupport(GeoItem owner, String modelPath) {
        this.animationCache = GeckoLibUtil.createInstanceCache(owner);
        this.modelPath = modelPath;
        GeoItem.registerSyncedAnimatable(owner);
    }

    public ArsenalGeoItemSupport(GeoItem owner) {
        this(owner, null);
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

    /** Creates the standard data-driven GeckoLib item renderer lazily. */
    public <T extends Item & GeoItem> void createRenderer(
            Consumer<GeoRenderProvider> consumer, String modelPath) {
        createRenderer(consumer, () -> new ArsenalGeoItemRenderer<T>(modelPath));
    }

    /** Creates the standard renderer using this item's configured model path. */
    public void createRenderer(Consumer<GeoRenderProvider> consumer) {
        if (modelPath == null) {
            throw new IllegalStateException("No model path configured for this item");
        }
        createRenderer(consumer, modelPath);
    }

    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
    }

    public AnimatableInstanceCache animationCache() {
        return animationCache;
    }
}
