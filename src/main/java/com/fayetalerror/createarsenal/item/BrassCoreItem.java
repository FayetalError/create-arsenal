package com.fayetalerror.createarsenal.item;

import com.fayetalerror.createarsenal.client.renderer.item.BrassCoreRenderer;
import java.util.function.Consumer;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.world.item.Item;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.client.GeoRenderProvider;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.util.GeckoLibUtil;

/** Shared GeckoLib-backed item for brass tool and weapon core components. */
public final class BrassCoreItem extends Item implements GeoItem {
    private final String modelPath;
    private final AnimatableInstanceCache animationCache = GeckoLibUtil.createInstanceCache(this);

    public BrassCoreItem(String modelPath, Properties properties) {
        super(properties);
        this.modelPath = modelPath;
        GeoItem.registerSyncedAnimatable(this);
    }

    public String modelPath() { return modelPath; }

    @Override public void createGeoRenderer(Consumer<GeoRenderProvider> consumer) { consumer.accept(new GeoRenderProvider() {
        private BrassCoreRenderer renderer;
        @Override public BlockEntityWithoutLevelRenderer getGeoItemRenderer() {
            if (renderer == null) renderer = new BrassCoreRenderer(modelPath);
            return renderer;
        }
    }); }

    @Override public void registerControllers(AnimatableManager.ControllerRegistrar controllers) { }
    @Override public AnimatableInstanceCache getAnimatableInstanceCache() { return animationCache; }
}
