//Credits Break this bone! by makar_flexer
package xox.labvorty.weaversparadise.mixins.compat.emotecraft;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import dev.kosmx.playerAnim.core.util.Pair;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.core.Direction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xox.labvorty.weaversparadise.compat.emotecraft.BendContext;
import xox.labvorty.weaversparadise.model.UpperWearModel;

@Mixin(UpperWearModel.class)
public class UpperWearModelMixin {
    @Inject(method = "<init>", at = @At("RETURN"))
    private void btb$initBends(ModelPart root, CallbackInfo ci) {
        UpperWearModel<?> self = (UpperWearModel<?>) (Object) this;
        BendContext.initBend(self.Body, Direction.DOWN);
        BendContext.initBend(self.RightArm, Direction.UP);
        BendContext.initBend(self.LeftArm, Direction.UP);
    }

    @WrapOperation(
            method = "renderToBuffer",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/model/geom/ModelPart;render(Lcom/mojang/blaze3d/vertex/PoseStack;Lcom/mojang/blaze3d/vertex/VertexConsumer;III)V")
    )
    private void btb$followBodyBend(ModelPart part, PoseStack poseStack, VertexConsumer consumer, int light, int overlay, int color, Operation<Void> original) {
        UpperWearModel<?> self = (UpperWearModel<?>) (Object) this;
        Pair<Float, Float> bend = part == self.Body ? null : BendContext.bodyBend();
        if (bend == null) {
            original.call(part, poseStack, consumer, light, overlay, color);
            return;
        }
        poseStack.pushPose();
        BendContext.applyBend(poseStack, bend);
        original.call(part, poseStack, consumer, light, overlay, color);
        poseStack.popPose();
    }
}