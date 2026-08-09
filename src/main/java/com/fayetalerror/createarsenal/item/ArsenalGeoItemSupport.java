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
    private final String animationPath;

    /** Creates shared GeckoLib state for an item with a model path. */
    public ArsenalGeoItemSupport(GeoItem owner, String modelPath) {
        this(owner, modelPath, null);
    }

    /** Creates shared GeckoLib state for an item with configured model and animation paths. */
    public ArsenalGeoItemSupport(GeoItem owner, String modelPath, String animationPath) {
        this.animationCache = GeckoLibUtil.createInstanceCache(owner);
        this.modelPath = modelPath;
        this.animationPath = animationPath;
        GeoItem.registerSyncedAnimatable(owner);
    }

    /** Creates shared GeckoLib state for an item with a custom renderer. */
    public ArsenalGeoItemSupport(GeoItem owner) {
        this(owner, null);
    }

    /** Installs a lazily created item renderer provider. */
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
    /** Installs the standard data-driven item renderer provider. */
    public <T extends Item & GeoItem> void createRenderer(
            Consumer<GeoRenderProvider> consumer, String modelPath, String animationPath) {
        createRenderer(consumer, () -> new ArsenalGeoItemRenderer<T>(modelPath, animationPath));
    }

    /** Creates the standard renderer using this item's configured model path. */
    /** Installs the standard renderer using the configured model path. */
    public void createRenderer(Consumer<GeoRenderProvider> consumer) {
        if (modelPath == null) {
            throw new IllegalStateException("No model path configured for this item");
        }
        createRenderer(consumer, modelPath, animationPath);
    }

    /** Registers the default controller set, which is currently empty. */
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
    }

    /** Returns the GeckoLib instance cache owned by the item. */
    public AnimatableInstanceCache animationCache() {
        return animationCache;
    }
}
