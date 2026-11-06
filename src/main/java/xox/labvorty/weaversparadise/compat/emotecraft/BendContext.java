//Credits Break this bone! by makar_flexer
package xox.labvorty.weaversparadise.compat.emotecraft;

import com.mojang.blaze3d.vertex.PoseStack;
import dev.kosmx.playerAnim.core.util.Pair;
import dev.kosmx.playerAnim.impl.Helper;
import dev.kosmx.playerAnim.impl.IAnimatedPlayer;
import dev.kosmx.playerAnim.impl.animation.AnimationApplier;
import dev.kosmx.playerAnim.impl.animation.IBendHelper;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.Nullable;
import org.joml.Quaternionf;
import org.joml.Vector3f;

public final class BendContext {
    private static final float BODY_PIVOT = 0.375F;
    @Nullable
    private static LivingEntity current;

    @Nullable
    public static LivingEntity enter(LivingEntity entity) {
        LivingEntity previous = current;
        current = entity;
        return previous;
    }

    public static void exit(@Nullable LivingEntity previous) {
        current = previous;
    }

    public static void initBend(ModelPart part, Direction direction) {
        IBendHelper.INSTANCE.initBend(part, direction);
    }

    @Nullable
    private static AnimationApplier activeAnimation() {
        if (!Helper.isBendEnabled() || !(current instanceof IAnimatedPlayer player)) {
            return null;
        }

        AnimationApplier animation = player.playerAnimator_getAnimation();
        return animation != null && animation.isActive() ? animation : null;
    }

    @Nullable
    public static Pair<Float, Float> bodyBend() {
        AnimationApplier animation = activeAnimation();
        return animation == null ? null : animation.getBend("body");
    }

    @Nullable
    public static Pair<Float, Float> chestBend() {
        AnimationApplier animation = activeAnimation();
        if (animation == null) {
            return null;
        }
        Pair<Float, Float> torso = animation.getBend("torso");
        Pair<Float, Float> body = animation.getBend("body");
        return new Pair<>(torso.getLeft() + body.getLeft(), torso.getRight() + body.getRight());
    }

    public static void applyBend(PoseStack poseStack, Pair<Float, Float> bend) {
        IBendHelper.rotateMatrixStack(poseStack, bend);
    }

    public static void undoBend(PoseStack poseStack, Pair<Float, Float> bend) {
        float axisAngle = -bend.getLeft();
        Vector3f axis = new Vector3f((float) Math.cos(axisAngle), 0.0F, (float) Math.sin(axisAngle));
        poseStack.translate(0.0F, BODY_PIVOT, 0.0F);
        poseStack.mulPose(new Quaternionf().rotateAxis(-bend.getRight(), axis));
        poseStack.translate(0.0F, -BODY_PIVOT, 0.0F);
    }
}