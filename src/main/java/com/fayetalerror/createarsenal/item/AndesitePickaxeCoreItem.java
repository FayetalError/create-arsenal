package com.fayetalerror.createarsenal.item;

import com.fayetalerror.createarsenal.client.renderer.item.AndesitePickaxeCoreRenderer;
import java.util.function.Consumer;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.world.item.Item;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.client.GeoRenderProvider;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.util.GeckoLibUtil;

/** Static GeckoLib item representing the core component used to assemble an andesite pickaxe. */
public final class AndesitePickaxeCoreItem extends Item implements GeoItem {
    private final AnimatableInstanceCache animationCache = GeckoLibUtil.createInstanceCache(this);

    public AndesitePickaxeCoreItem(Properties properties) {
        super(properties);
    }

    /** Lazily supplies the client-only renderer used for the core's geo model. */
    @Override
    public void createGeoRenderer(Consumer<GeoRenderProvider> consumer) {
        consumer.accept(new GeoRenderProvider() {
            private AndesitePickaxeCoreRenderer renderer;

            @Override
            public BlockEntityWithoutLevelRenderer getGeoItemRenderer() {
                if (this.renderer == null) {
                    this.renderer = new AndesitePickaxeCoreRenderer();
                }

                return this.renderer;
            }
        });
    }

    /** The core is a static model, so it does not register animation controllers. */
    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.animationCache;
    }
}
