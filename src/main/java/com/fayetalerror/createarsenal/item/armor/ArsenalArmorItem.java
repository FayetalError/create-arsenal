package com.fayetalerror.createarsenal.item.armor;

import com.fayetalerror.createarsenal.client.renderer.item.ArsenalArmorRenderer;
import com.fayetalerror.createarsenal.client.renderer.item.ArsenalGeoItemRenderer;
import com.fayetalerror.createarsenal.item.ArsenalGeoItem;
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
import software.bernie.geckolib.animatable.client.GeoRenderProvider;

/** Data-driven armor item with shared worn and inventory GeckoLib renderers. */
public final class ArsenalArmorItem extends ArmorItem implements ArsenalGeoItem {
    private final ArsenalGeoItemSupport geoSupport;
    private final String itemModelPath;
    private final String equippedModelPath;

    public ArsenalArmorItem(Holder<ArmorMaterial> material, Type type, Properties properties,
            String itemModelPath, String equippedModelPath) {
        super(material, type, properties);
        this.itemModelPath = itemModelPath;
        this.equippedModelPath = equippedModelPath;
        this.geoSupport = new ArsenalGeoItemSupport(this);
    }

    @Override
    public void createGeoRenderer(Consumer<GeoRenderProvider> consumer) {
        consumer.accept(new GeoRenderProvider() {
            private ArsenalArmorRenderer armorRenderer;
            private BlockEntityWithoutLevelRenderer itemRenderer;

            @Override
            public BlockEntityWithoutLevelRenderer getGeoItemRenderer() {
                if (itemRenderer == null) {
                    itemRenderer = new ArsenalGeoItemRenderer<>(itemModelPath);
                }
                return itemRenderer;
            }

            @Override
            public <T extends LivingEntity> HumanoidModel<?> getGeoArmorRenderer(
                    @Nullable T livingEntity, ItemStack itemStack,
                    @Nullable EquipmentSlot equipmentSlot, @Nullable HumanoidModel<T> original) {
                if (armorRenderer == null) {
                    armorRenderer = new ArsenalArmorRenderer(equippedModelPath);
                }
                return armorRenderer;
            }
        });
    }

    @Override
    public ArsenalGeoItemSupport geoSupport() {
        return geoSupport;
    }
}
