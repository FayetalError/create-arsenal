package com.fayetalerror.createarsenal.item.tools;

import com.fayetalerror.createarsenal.client.renderer.item.tools.BrassAxeRenderer;
import com.fayetalerror.createarsenal.item.ArsenalGeoItemSupport;
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
    private final ArsenalGeoItemSupport geoSupport;
    public BrassAxeItem(Tier tier, Properties properties) { super(tier, properties); geoSupport = new ArsenalGeoItemSupport(this); }
    @Override public void createGeoRenderer(Consumer<GeoRenderProvider> consumer) { geoSupport.createRenderer(consumer, BrassAxeRenderer::new); }
    @Override public void registerControllers(AnimatableManager.ControllerRegistrar controllers) { geoSupport.registerControllers(controllers); }
    @Override public AnimatableInstanceCache getAnimatableInstanceCache() { return geoSupport.animationCache(); }
}
