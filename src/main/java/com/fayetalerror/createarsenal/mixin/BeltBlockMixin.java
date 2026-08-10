package com.fayetalerror.createarsenal.mixin;

import com.fayetalerror.createarsenal.item.armor.ArsenalArmorItem;
import com.simibubi.create.content.kinetics.belt.BeltBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/** Extends Create belts to ignore entities wearing Arsenal armor marked as belt-immune. */
@Mixin(BeltBlock.class)
abstract class BeltBlockMixin {
    /** Stops belt processing before Create applies any transport movement to an immune wearer. */
    @Inject(method = "entityInside", at = @At("HEAD"), cancellable = true)
    private void createarsenal$skipBeltImmuneWearers(BlockState state, Level level, BlockPos pos,
            Entity entity, CallbackInfo callback) {
        if (entity instanceof LivingEntity livingEntity
                && livingEntity.getItemBySlot(EquipmentSlot.FEET).getItem() instanceof ArsenalArmorItem armor
                && armor.isBeltImmune()) {
            callback.cancel();
        }
    }
}
