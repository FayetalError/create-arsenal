package com.fayetalerror.createarsenal.item.tools;

import com.fayetalerror.createarsenal.client.renderer.item.tools.BrassPaxelRenderer;
import com.fayetalerror.createarsenal.item.ArsenalGeoItemSupport;
import com.fayetalerror.createarsenal.registry.ArsenalBlockTags;
import java.util.function.Consumer;
import javax.annotation.Nullable;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.common.ItemAbility;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.client.GeoRenderProvider;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.util.GeckoLibUtil;

/** Combined pickaxe, axe, and shovel with GeckoLib rendering. */
public final class BrassPaxelItem extends DiggerItem implements GeoItem {
    private final ArsenalGeoItemSupport geoSupport;

    public BrassPaxelItem(Tier tier, Properties properties) {
        // The combined block tag supplies mining speed and correct-drop behavior for all three tools.
        super(tier, ArsenalBlockTags.MINEABLE_WITH_PAXEL, properties);
        geoSupport = new ArsenalGeoItemSupport(this);
    }

    /** Advertises every standard pickaxe, axe, and shovel ability to NeoForge integrations. */
    @Override
    public boolean canPerformAction(ItemStack stack, ItemAbility ability) {
        return ItemAbilities.DEFAULT_PICKAXE_ACTIONS.contains(ability)
                || ItemAbilities.DEFAULT_AXE_ACTIONS.contains(ability)
                || ItemAbilities.DEFAULT_SHOVEL_ACTIONS.contains(ability);
    }

    /** Applies axe transformations first, then shovel transformations when no axe action applies. */
    @Override
    public InteractionResult useOn(UseOnContext context) {
        if (playerHasShieldUseIntent(context)) {
            return InteractionResult.PASS;
        }

        Level level = context.getLevel();
        BlockPos position = context.getClickedPos();
        BlockState originalState = level.getBlockState(position);

        BlockState modifiedState = originalState.getToolModifiedState(context, ItemAbilities.AXE_STRIP, false);
        if (modifiedState != null) {
            return applyModification(context, modifiedState, SoundEvents.AXE_STRIP, -1);
        }

        modifiedState = originalState.getToolModifiedState(context, ItemAbilities.AXE_SCRAPE, false);
        if (modifiedState != null) {
            return applyModification(context, modifiedState, SoundEvents.AXE_SCRAPE, 3005);
        }

        modifiedState = originalState.getToolModifiedState(context, ItemAbilities.AXE_WAX_OFF, false);
        if (modifiedState != null) {
            return applyModification(context, modifiedState, SoundEvents.AXE_WAX_OFF, 3004);
        }

        // Shovels cannot flatten or douse through the underside of a block.
        if (context.getClickedFace() == Direction.DOWN) {
            return InteractionResult.PASS;
        }

        modifiedState = originalState.getToolModifiedState(context, ItemAbilities.SHOVEL_FLATTEN, false);
        if (modifiedState != null && level.getBlockState(position.above()).isAir()) {
            return applyModification(context, modifiedState, SoundEvents.SHOVEL_FLATTEN, -1);
        }

        modifiedState = originalState.getToolModifiedState(context, ItemAbilities.SHOVEL_DOUSE, false);
        if (modifiedState != null) {
            return applyModification(context, modifiedState, null, 1009);
        }

        return InteractionResult.PASS;
    }

    /** Applies a block transformation, sound/event feedback, advancement trigger, and durability cost. */
    private InteractionResult applyModification(
            UseOnContext context,
            BlockState modifiedState,
            @Nullable SoundEvent sound,
            int levelEvent
    ) {
        Level level = context.getLevel();
        BlockPos position = context.getClickedPos();
        Player player = context.getPlayer();
        ItemStack stack = context.getItemInHand();

        if (sound != null) {
            level.playSound(player, position, sound, SoundSource.BLOCKS, 1.0F, 1.0F);
        }
        if (levelEvent >= 0) {
            level.levelEvent(player, levelEvent, position, 0);
        }
        if (player instanceof ServerPlayer serverPlayer) {
            CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger(serverPlayer, position, stack);
        }

        if (!level.isClientSide) {
            level.setBlock(position, modifiedState, 11);
            level.gameEvent(GameEvent.BLOCK_CHANGE, position, GameEvent.Context.of(player, modifiedState));
            if (player != null) {
                stack.hurtAndBreak(1, player, LivingEntity.getSlotForHand(context.getHand()));
            }
        }

        return InteractionResult.sidedSuccess(level.isClientSide);
    }

    /** Preserves vanilla axe behavior when a player is intentionally using an offhand shield. */
    private static boolean playerHasShieldUseIntent(UseOnContext context) {
        Player player = context.getPlayer();
        return player != null
                && context.getHand() == InteractionHand.MAIN_HAND
                && player.getOffhandItem().is(Items.SHIELD)
                && !player.isSecondaryUseActive();
    }

    @Override
    public void createGeoRenderer(Consumer<GeoRenderProvider> consumer) {
        geoSupport.createRenderer(consumer, BrassPaxelRenderer::new);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        geoSupport.registerControllers(controllers);
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return geoSupport.animationCache();
    }
}
