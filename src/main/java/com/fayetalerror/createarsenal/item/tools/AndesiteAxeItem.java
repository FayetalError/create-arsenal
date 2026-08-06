package com.fayetalerror.createarsenal.item.tools;

import com.fayetalerror.createarsenal.client.renderer.item.tools.AndesiteAxeRenderer;
import com.fayetalerror.createarsenal.item.ArsenalGeoItemSupport;
import java.util.function.Consumer;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Tier;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.client.GeoRenderProvider;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;

/** Axe implementation that retains vanilla chopping behavior and uses a static GeckoLib model. */
public final class AndesiteAxeItem extends AxeItem implements GeoItem {
    private final ArsenalGeoItemSupport geoSupport;

    public AndesiteAxeItem(Tier tier, Properties properties) {
        super(tier, properties);
        geoSupport = new ArsenalGeoItemSupport(this);
    }

    @Override
    public void createGeoRenderer(Consumer<GeoRenderProvider> consumer) {
        geoSupport.createRenderer(consumer, AndesiteAxeRenderer::new);
    }

    /** The axe is a static model, so it does not register animation controllers. */
    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        geoSupport.registerControllers(controllers);
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return geoSupport.animationCache();
    }
}
