package com.fayetalerror.createarsenal.item.tools;

import com.fayetalerror.createarsenal.item.ArsenalGeoItem;
import com.fayetalerror.createarsenal.item.ArsenalGeoItemSupport;
import com.fayetalerror.createarsenal.registry.ArsenalBlockTags;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.advancements.CriteriaTriggers;
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
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.PlayState;
import software.bernie.geckolib.animation.RawAnimation;

/** Combined pickaxe, axe, and shovel with GeckoLib rendering. */
public final class ArsenalPaxelItem extends DiggerItem implements ArsenalGeoItem {
    private record ToolModification(ItemAbility ability, @Nullable SoundEvent sound, int levelEvent) {}

    private static final List<ToolModification> AXE_MODIFICATIONS = List.of(
            new ToolModification(ItemAbilities.AXE_STRIP, SoundEvents.AXE_STRIP, -1),
            new ToolModification(ItemAbilities.AXE_SCRAPE, SoundEvents.AXE_SCRAPE, 3005),
            new ToolModification(ItemAbilities.AXE_WAX_OFF, SoundEvents.AXE_WAX_OFF, 3004));

    private static final List<ToolModification> SHOVEL_MODIFICATIONS = List.of(
            new ToolModification(ItemAbilities.SHOVEL_FLATTEN, SoundEvents.SHOVEL_FLATTEN, -1),
            new ToolModification(ItemAbilities.SHOVEL_DOUSE, null, 1009));

    private final ArsenalGeoItemSupport geoSupport;
    private final RawAnimation idleAnimation;

    /** Creates a multi-tool with optional looping GeckoLib animation data. */
    public ArsenalPaxelItem(Tier tier, Properties properties, String modelPath, String animationPath) {
        // The combined block tag supplies mining speed and correct-drop behavior for all three tools.
        super(tier, ArsenalBlockTags.MINEABLE_WITH_PAXEL, properties);
        this.geoSupport = new ArsenalGeoItemSupport(this, modelPath, animationPath);
        this.idleAnimation = animationPath == null ? null : RawAnimation.begin().thenLoop(animationName(animationPath));
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

        for (ToolModification modification : AXE_MODIFICATIONS) {
            BlockState modifiedState = originalState.getToolModifiedState(context, modification.ability(), false);
            if (modifiedState != null) {
                return applyModification(context, modifiedState, modification.sound(), modification.levelEvent());
            }
        }

        // Shovels cannot flatten or douse through the underside of a block.
        if (context.getClickedFace() == Direction.DOWN) {
            return InteractionResult.PASS;
        }

        for (ToolModification modification : SHOVEL_MODIFICATIONS) {
            if (modification.ability() == ItemAbilities.SHOVEL_FLATTEN
                    && !level.getBlockState(position.above()).isAir()) {
                continue;
            }
            BlockState modifiedState = originalState.getToolModifiedState(context, modification.ability(), false);
            if (modifiedState != null) {
                return applyModification(context, modifiedState, modification.sound(), modification.levelEvent());
            }
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
    public ArsenalGeoItemSupport geoSupport() {
        return geoSupport;
    }

    /** Adds an always-looping controller only when the definition supplies an animation path. */
    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        if (idleAnimation != null) {
            controllers.add(new AnimationController<>(this, "paxel_idle", 0, state -> {
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
