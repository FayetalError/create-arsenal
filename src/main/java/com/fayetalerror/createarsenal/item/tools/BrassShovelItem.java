package com.fayetalerror.createarsenal.item.tools;

import com.fayetalerror.createarsenal.client.renderer.item.tools.BrassShovelRenderer;
import com.fayetalerror.createarsenal.item.ArsenalGeoItemSupport;
import java.util.function.Consumer;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.Tier;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.client.GeoRenderProvider;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.util.GeckoLibUtil;

/** Brass shovel with vanilla shovel behavior and a GeckoLib item model. */
public final class BrassShovelItem extends ShovelItem implements GeoItem {
    private final ArsenalGeoItemSupport geoSupport;
    public BrassShovelItem(Tier tier, Properties properties) { super(tier, properties); geoSupport = new ArsenalGeoItemSupport(this); }
    @Override public void createGeoRenderer(Consumer<GeoRenderProvider> consumer) { geoSupport.createRenderer(consumer, BrassShovelRenderer::new); }
    @Override public void registerControllers(AnimatableManager.ControllerRegistrar controllers) { geoSupport.registerControllers(controllers); }
    @Override public AnimatableInstanceCache getAnimatableInstanceCache() { return geoSupport.animationCache(); }
}
