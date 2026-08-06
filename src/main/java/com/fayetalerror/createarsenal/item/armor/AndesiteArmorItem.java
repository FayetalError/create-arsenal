package com.fayetalerror.createarsenal.item.armor;

import com.fayetalerror.createarsenal.client.renderer.item.armor.AndesiteArmorRenderer;
import com.fayetalerror.createarsenal.client.renderer.item.armor.AndesiteBootsItemRenderer;
import com.fayetalerror.createarsenal.client.renderer.item.armor.AndesiteChestplateItemRenderer;
import com.fayetalerror.createarsenal.client.renderer.item.armor.AndesiteHelmetItemRenderer;
import com.fayetalerror.createarsenal.client.renderer.item.armor.AndesiteLeggingsItemRenderer;
import com.fayetalerror.createarsenal.item.ArsenalGeoItemSupport;
import java.util.function.Consumer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
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

/** Wearable armor item that uses the shared static Andesite Armor GeckoLib model. */
public final class AndesiteArmorItem extends ArmorItem implements GeoItem {
    /** Stores GeckoLib's per-item rendering and animation state. */
    private final ArsenalGeoItemSupport geoSupport;

    /**
     * Creates one wearable piece from the shared material and a specific armor slot type.
     *
     * @param material registered armor material supplying protection and repair behavior
     * @param type armor piece type, such as helmet or chestplate
     * @param properties durability and other standard item settings
     */
    public AndesiteArmorItem(Holder<ArmorMaterial> material, Type type, Properties properties) {
        super(material, type, properties);
        geoSupport = new ArsenalGeoItemSupport(this);
    }

    /** Supplies GeckoLib's custom worn-armor renderer on the client. */
    @Override
    public void createGeoRenderer(Consumer<GeoRenderProvider> consumer) {
        consumer.accept(new GeoRenderProvider() {
            /** Reuse one renderer instead of creating a new renderer every frame. */
            private AndesiteArmorRenderer renderer;

            /** Reuse the helmet-only renderer for three-dimensional item-display views. */
            private AndesiteHelmetItemRenderer helmetItemRenderer;

            /** Reuse the chestplate-only renderer for three-dimensional item-display views. */
            private AndesiteChestplateItemRenderer chestplateItemRenderer;

            /** Reuse the leggings-only renderer for three-dimensional item-display views. */
            private AndesiteLeggingsItemRenderer leggingsItemRenderer;

            /** Reuse the boots-only renderer for three-dimensional item-display views. */
            private AndesiteBootsItemRenderer bootsItemRenderer;

            /** Returns the item renderer matching the armor piece's {@code builtin/entity} model. */
            @Override
            public BlockEntityWithoutLevelRenderer getGeoItemRenderer() {
                if (AndesiteArmorItem.this.getType() == ArmorItem.Type.HELMET) {
                    if (this.helmetItemRenderer == null) {
                        this.helmetItemRenderer = new AndesiteHelmetItemRenderer();
                    }

                    return this.helmetItemRenderer;
                }

                if (AndesiteArmorItem.this.getType() == ArmorItem.Type.CHESTPLATE) {
                    if (this.chestplateItemRenderer == null) {
                        this.chestplateItemRenderer = new AndesiteChestplateItemRenderer();
                    }

                    return this.chestplateItemRenderer;
                }

                if (AndesiteArmorItem.this.getType() == ArmorItem.Type.LEGGINGS) {
                    if (this.leggingsItemRenderer == null) {
                        this.leggingsItemRenderer = new AndesiteLeggingsItemRenderer();
                    }

                    return this.leggingsItemRenderer;
                }

                if (AndesiteArmorItem.this.getType() == ArmorItem.Type.BOOTS) {
                    if (this.bootsItemRenderer == null) {
                        this.bootsItemRenderer = new AndesiteBootsItemRenderer();
                    }

                    return this.bootsItemRenderer;
                }

                return null;
            }

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
        geoSupport.registerControllers(controllers);
    }

    /** Gives GeckoLib access to the cached state associated with this armor item. */
    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return geoSupport.animationCache();
    }
}
