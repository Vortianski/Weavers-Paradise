//Credits Break this bone! by makar_flexer
package xox.labvorty.weaversparadise.mixins.compat.emotecraft;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.mojang.blaze3d.vertex.PoseStack;
import dev.kosmx.playerAnim.core.util.Pair;
import net.minecraft.client.renderer.MultiBufferSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import xox.labvorty.weaversparadise.compat.emotecraft.BendContext;
import xox.labvorty.weaversparadise.renderers.models.UpperwearModelRenderer;

@Mixin(UpperwearModelRenderer.class)
public class UpperwearModelRendererMixin {
    @WrapOperation(
            method = "renderModel",
            at = @At(value = "INVOKE", target = "Lxox/labvorty/weaversparadise/renderers/models/UpperwearModelRenderer;renderText(Lnet/minecraft/client/renderer/MultiBufferSource;Lcom/mojang/blaze3d/vertex/PoseStack;Ljava/lang/String;IIFFFF)V")
    )
    private static void btb$bendChestText(MultiBufferSource buffer, PoseStack poseStack, String text, int light, int color, float x, float y, float z, float scale, Operation<Void> original) {
        Pair<Float, Float> bend = BendContext.chestBend();
        if (bend == null) {
            original.call(buffer, poseStack, text, light, color, x, y, z, scale);
            return;
        }
        poseStack.pushPose();
        BendContext.applyBend(poseStack, bend);
        original.call(buffer, poseStack, text, light, color, x, y, z, scale);
        poseStack.popPose();
    }
}