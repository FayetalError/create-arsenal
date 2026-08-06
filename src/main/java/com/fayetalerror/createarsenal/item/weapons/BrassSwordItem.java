package com.fayetalerror.createarsenal.item.weapons;

import com.fayetalerror.createarsenal.client.renderer.item.weapons.BrassSwordRenderer;
import java.util.function.Consumer;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.client.GeoRenderProvider;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.util.GeckoLibUtil;

/** Brass sword with vanilla melee behavior and a GeckoLib item model. */
public final class BrassSwordItem extends SwordItem implements GeoItem {
    private final AnimatableInstanceCache animationCache = GeckoLibUtil.createInstanceCache(this);
    public BrassSwordItem(Tier tier, Properties properties) { super(tier, properties); GeoItem.registerSyncedAnimatable(this); }
    @Override public void createGeoRenderer(Consumer<GeoRenderProvider> consumer) { consumer.accept(new GeoRenderProvider() {
        private BrassSwordRenderer renderer;
        @Override public BlockEntityWithoutLevelRenderer getGeoItemRenderer() { if (renderer == null) renderer = new BrassSwordRenderer(); return renderer; }
    }); }
    @Override public void registerControllers(AnimatableManager.ControllerRegistrar controllers) { }
    @Override public AnimatableInstanceCache getAnimatableInstanceCache() { return animationCache; }
}
