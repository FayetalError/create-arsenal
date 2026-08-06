package com.fayetalerror.createarsenal.item.tools;

import com.fayetalerror.createarsenal.client.renderer.item.tools.BrassPickaxeRenderer;
import java.util.function.Consumer;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Tier;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.client.GeoRenderProvider;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.util.GeckoLibUtil;

/**
 * Pickaxe implementation that can be rendered and animated by GeckoLib.
 * The vanilla {@link PickaxeItem} parent retains normal mining behavior.
 */
public final class BrassPickaxeItem extends PickaxeItem implements GeoItem {
    /** Stores animation state for individual item stacks. */
    private final AnimatableInstanceCache animationCache = GeckoLibUtil.createInstanceCache(this);

    /**
     * Creates a brass pickaxe with the supplied mining tier and item properties.
     *
     * @param tier controls durability, mining speed, and which blocks the pickaxe can harvest
     * @param properties controls item-stack behavior and attribute modifiers
     */
    public BrassPickaxeItem(Tier tier, Properties properties) {
        super(tier, properties);

        // Enable server-synchronized and server-triggered item animations for future controllers.
        GeoItem.registerSyncedAnimatable(this);
    }

    /**
     * Supplies GeckoLib's custom item renderer when this item is rendered on a client.
     * The renderer is created lazily so common initialization does not instantiate client rendering code.
     */
    @Override
    public void createGeoRenderer(Consumer<GeoRenderProvider> consumer) {
        consumer.accept(new GeoRenderProvider() {
            private BrassPickaxeRenderer renderer;

            @Override
            public BlockEntityWithoutLevelRenderer getGeoItemRenderer() {
                if (this.renderer == null) {
                    this.renderer = new BrassPickaxeRenderer();
                }

                return this.renderer;
            }
        });
    }

    /**
     * Registers animation controllers for this item.
     * Controllers will be added when the pickaxe's animation behavior is implemented.
     */
    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
    }

    /** Gives GeckoLib access to this item's per-stack animation state. */
    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.animationCache;
    }
}
