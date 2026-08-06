package com.fayetalerror.createarsenal.item.tools;

import com.fayetalerror.createarsenal.client.renderer.item.tools.BrassAxeRenderer;
import java.util.function.Consumer;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Tier;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.client.GeoRenderProvider;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.util.GeckoLibUtil;

/** Brass axe with vanilla axe behavior and a GeckoLib item model. */
public final class BrassAxeItem extends AxeItem implements GeoItem {
    private final AnimatableInstanceCache animationCache = GeckoLibUtil.createInstanceCache(this);
    public BrassAxeItem(Tier tier, Properties properties) { super(tier, properties); GeoItem.registerSyncedAnimatable(this); }
    @Override public void createGeoRenderer(Consumer<GeoRenderProvider> consumer) { consumer.accept(new GeoRenderProvider() {
        private BrassAxeRenderer renderer;
        @Override public BlockEntityWithoutLevelRenderer getGeoItemRenderer() { if (renderer == null) renderer = new BrassAxeRenderer(); return renderer; }
    }); }
    @Override public void registerControllers(AnimatableManager.ControllerRegistrar controllers) { }
    @Override public AnimatableInstanceCache getAnimatableInstanceCache() { return animationCache; }
}
