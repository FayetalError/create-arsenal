package com.fayetalerror.createarsenal.item.tools;

import com.fayetalerror.createarsenal.item.ArsenalGeoItem;
import com.fayetalerror.createarsenal.item.ArsenalGeoItemSupport;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.Tier;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.PlayState;
import software.bernie.geckolib.animation.RawAnimation;

/** Data-driven shovel that retains vanilla shovel behavior and GeckoLib rendering. */
public final class ArsenalShovelItem extends ShovelItem implements ArsenalGeoItem {
    private final ArsenalGeoItemSupport geoSupport;
    private final RawAnimation idleAnimation;

    /** Creates a shovel with optional looping GeckoLib animation data. */
    public ArsenalShovelItem(Tier tier, Properties properties, String modelPath, String animationPath) {
        super(tier, properties);
        this.geoSupport = new ArsenalGeoItemSupport(this, modelPath, animationPath);
        this.idleAnimation = animationPath == null ? null : RawAnimation.begin().thenLoop(animationName(animationPath));
    }

    @Override
    public ArsenalGeoItemSupport geoSupport() {
        return geoSupport;
    }

    /** Adds an always-looping controller only when the definition supplies an animation path. */
    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        if (idleAnimation != null) {
            controllers.add(new AnimationController<>(this, "shovel_idle", 0, state -> {
                state.setAnimation(idleAnimation);
                return PlayState.CONTINUE;
            }));
        }
    }

    /** Extracts the animation name from its data-defined resource path. */
    private static String animationName(String animationPath) {
        return animationPath.substring(animationPath.lastIndexOf('/') + 1);
    }
}
