package com.fayetalerror.createarsenal.item.tools;

import com.fayetalerror.createarsenal.client.renderer.item.tools.AndesiteHoeRenderer;
import com.fayetalerror.createarsenal.item.ArsenalGeoItemSupport;
import java.util.function.Consumer;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Tier;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.client.GeoRenderProvider;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;

/** Hoe implementation that retains vanilla tilling behavior and uses a static GeckoLib model. */
public final class AndesiteHoeItem extends HoeItem implements GeoItem {
    private final ArsenalGeoItemSupport geoSupport;

    public AndesiteHoeItem(Tier tier, Properties properties) {
        super(tier, properties);
        geoSupport = new ArsenalGeoItemSupport(this);
    }

    @Override
    public void createGeoRenderer(Consumer<GeoRenderProvider> consumer) {
        geoSupport.createRenderer(consumer, AndesiteHoeRenderer::new);
    }

    /** The hoe is a static model, so it does not register animation controllers. */
    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        geoSupport.registerControllers(controllers);
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return geoSupport.animationCache();
    }
}
