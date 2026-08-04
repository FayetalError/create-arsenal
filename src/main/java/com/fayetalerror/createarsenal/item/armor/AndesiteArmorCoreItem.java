package com.fayetalerror.createarsenal.item.armor;

import com.fayetalerror.createarsenal.client.renderer.item.armor.AndesiteBootsCoreRenderer;
import com.fayetalerror.createarsenal.client.renderer.item.armor.AndesiteChestplateCoreRenderer;
import com.fayetalerror.createarsenal.client.renderer.item.armor.AndesiteHelmetCoreRenderer;
import com.fayetalerror.createarsenal.client.renderer.item.armor.AndesiteLeggingsCoreRenderer;
import java.util.function.Consumer;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.world.item.Item;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.client.GeoRenderProvider;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.util.GeckoLibUtil;

/** Static GeckoLib item representing one component used to assemble Andesite Armor. */
public final class AndesiteArmorCoreItem extends Item implements GeoItem {
    /** Identifies which dedicated core model an item instance should render. */
    public enum Type {
        HELMET,
        CHESTPLATE,
        LEGGINGS,
        BOOTS
    }

    /** Stores GeckoLib's per-item rendering state. */
    private final AnimatableInstanceCache animationCache = GeckoLibUtil.createInstanceCache(this);

    /** Selects the dedicated geometry and texture for this core. */
    private final Type type;

    /** Creates an armor core item for the supplied armor-piece type. */
    public AndesiteArmorCoreItem(Type type, Properties properties) {
        super(properties);
        this.type = type;
    }

    /** Lazily supplies the item renderer matching this core's type. */
    @Override
    public void createGeoRenderer(Consumer<GeoRenderProvider> consumer) {
        consumer.accept(new GeoRenderProvider() {
            /** Reuses the selected renderer instead of recreating it every frame. */
            private BlockEntityWithoutLevelRenderer renderer;

            @Override
            public BlockEntityWithoutLevelRenderer getGeoItemRenderer() {
                if (this.renderer == null) {
                    this.renderer = switch (AndesiteArmorCoreItem.this.type) {
                        case HELMET -> new AndesiteHelmetCoreRenderer();
                        case CHESTPLATE -> new AndesiteChestplateCoreRenderer();
                        case LEGGINGS -> new AndesiteLeggingsCoreRenderer();
                        case BOOTS -> new AndesiteBootsCoreRenderer();
                    };
                }

                return this.renderer;
            }
        });
    }

    /** Armor cores use static models and therefore need no animation controllers. */
    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
    }

    /** Gives GeckoLib access to the cached state associated with this item. */
    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.animationCache;
    }
}
