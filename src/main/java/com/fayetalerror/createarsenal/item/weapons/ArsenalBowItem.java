package com.fayetalerror.createarsenal.item.weapons;

import com.fayetalerror.createarsenal.item.ArsenalGeoItem;
import com.fayetalerror.createarsenal.item.ArsenalGeoItemSupport;
import net.minecraft.client.Minecraft;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.UseAnim;
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

    /** Plays and holds the draw pose while this bow is being used. */
    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "bow_draw", 0, state -> {
            ItemStack stack = state.getData(DataTickets.ITEMSTACK);
            var player = Minecraft.getInstance().player;

            boolean drawing = stack != null && player != null
                    && stack.is(this)
                    && player.isUsingItem()
                    && player.getUseItem().is(this);

            RawAnimation animation = drawing ? drawAnimation : idleAnimation;
            if (!state.isCurrentAnimation(animation)) {
                state.setAnimation(animation);
            }
            return software.bernie.geckolib.animation.PlayState.CONTINUE;
        }));
    }
}
