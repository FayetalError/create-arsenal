package com.fayetalerror.createarsenal.item.weapons;

import com.fayetalerror.createarsenal.client.renderer.item.weapons.AndesiteSwordRenderer;
import com.fayetalerror.createarsenal.item.ArsenalGeoItemSupport;
import java.util.function.Consumer;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.client.GeoRenderProvider;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;

/** Sword implementation that retains vanilla melee behavior and uses a static GeckoLib model. */
public final class AndesiteSwordItem extends SwordItem implements GeoItem {
    private final ArsenalGeoItemSupport geoSupport;

    public AndesiteSwordItem(Tier tier, Properties properties) {
        super(tier, properties);
        geoSupport = new ArsenalGeoItemSupport(this);
    }

    @Override
    public void createGeoRenderer(Consumer<GeoRenderProvider> consumer) {
        geoSupport.createRenderer(consumer, AndesiteSwordRenderer::new);
    }

    /** The sword is a static model, so it does not register animation controllers. */
    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        geoSupport.registerControllers(controllers);
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return geoSupport.animationCache();
    }
}
