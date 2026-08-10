package com.fayetalerror.createarsenal.item.weapons;

import com.fayetalerror.createarsenal.item.ArsenalGeoItem;
import com.fayetalerror.createarsenal.item.ArsenalGeoItemSupport;
import net.minecraft.client.Minecraft;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.RawAnimation;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.animation.AnimatableManager;

/** Data-driven bow that retains vanilla bow behavior and GeckoLib rendering. */
public final class ArsenalBowItem extends BowItem implements ArsenalGeoItem {
    private final ArsenalGeoItemSupport geoSupport;
    private final RawAnimation drawAnimation;
    private final RawAnimation idleAnimation;

    public ArsenalBowItem(Properties properties, String modelPath, String animationPath) {
        super(properties);
        if (animationPath == null || animationPath.isBlank()) {
            throw new IllegalArgumentException("Bow definitions must provide an animations path");
        }
        this.geoSupport = new ArsenalGeoItemSupport(this, modelPath, animationPath);
        String animationPrefix = animationPath.substring(animationPath.lastIndexOf('/') + 1);
        this.drawAnimation = RawAnimation.begin().thenPlayAndHold(animationPrefix + "_draw");
        this.idleAnimation = RawAnimation.begin().thenPlayAndHold(animationPrefix + "_idle");
    }

    @Override
    public ArsenalGeoItemSupport geoSupport() {
        return geoSupport;
    }

    /** Disables the vanilla bow visual pose so GeckoLib controls the draw animation. */
    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.NONE;
    }

    /** Assigns a per-stack GeckoLib ID before vanilla begins drawing the bow. */
    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if (level instanceof ServerLevel serverLevel) {
            GeoItem.getOrAssignId(player.getItemInHand(hand), serverLevel);
        }
        return super.use(level, player, hand);
    }

    /** Plays and holds the draw pose while this bow is being used. */
    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "bow_draw", 0, state -> {
            ItemStack stack = state.getData(DataTickets.ITEMSTACK);
            var player = Minecraft.getInstance().player;

            boolean drawing = stack != null && player != null
                    && stack.is(this)
                    && player.isUsingItem()
                    && isActiveBowStack(stack, player.getUseItem());

            RawAnimation animation = drawing ? drawAnimation : idleAnimation;
            if (!state.isCurrentAnimation(animation)) {
                state.setAnimation(animation);
            }
            return software.bernie.geckolib.animation.PlayState.CONTINUE;
        }));
    }

    /** Returns whether a rendered stack is the precise stack currently being drawn by the player. */
    private static boolean isActiveBowStack(ItemStack renderedStack, ItemStack activeStack) {
        if (renderedStack == activeStack) return true;

        long renderedId = GeoItem.getId(renderedStack);
        return renderedId != Long.MAX_VALUE && renderedId == GeoItem.getId(activeStack);
    }
}
