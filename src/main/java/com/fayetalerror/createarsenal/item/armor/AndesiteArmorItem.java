package com.fayetalerror.createarsenal.item.armor;

import com.fayetalerror.createarsenal.client.renderer.item.armor.AndesiteArmorRenderer;
import java.util.function.Consumer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.client.GeoRenderProvider;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.util.GeckoLibUtil;

/** Wearable armor item that uses the shared static Andesite Armor GeckoLib model. */
public final class AndesiteArmorItem extends ArmorItem implements GeoItem {
    /** Stores GeckoLib's per-item rendering and animation state. */
    private final AnimatableInstanceCache animationCache = GeckoLibUtil.createInstanceCache(this);

    /**
     * Creates one wearable piece from the shared material and a specific armor slot type.
     *
     * @param material registered armor material supplying protection and repair behavior
     * @param type armor piece type, such as helmet or chestplate
     * @param properties durability and other standard item settings
     */
    public AndesiteArmorItem(Holder<ArmorMaterial> material, Type type, Properties properties) {
        super(material, type, properties);
    }

    /** Supplies GeckoLib's custom worn-armor renderer on the client. */
    @Override
    public void createGeoRenderer(Consumer<GeoRenderProvider> consumer) {
        consumer.accept(new GeoRenderProvider() {
            /** Reuse one renderer instead of creating a new renderer every frame. */
            private AndesiteArmorRenderer renderer;

            /** Returns the shared armor renderer whenever this item is worn. */
            @Override
            public <T extends LivingEntity> HumanoidModel<?> getGeoArmorRenderer(
                    @Nullable T livingEntity,
                    ItemStack itemStack,
                    @Nullable EquipmentSlot equipmentSlot,
                    @Nullable HumanoidModel<T> original
            ) {
                if (this.renderer == null) {
                    this.renderer = new AndesiteArmorRenderer();
                }

                return this.renderer;
            }
        });
    }

    /** The current armor model is static, so it does not register animation controllers. */
    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
    }

    /** Gives GeckoLib access to the cached state associated with this armor item. */
    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.animationCache;
    }
}
