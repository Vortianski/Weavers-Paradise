//Credits Break this bone! by makar_flexer
package xox.labvorty.weaversparadise.mixins.compat.emotecraft;

import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalBooleanRef;
import com.mojang.blaze3d.vertex.PoseStack;
import dev.kosmx.playerAnim.core.util.Pair;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xox.labvorty.weaversparadise.compat.emotecraft.BendContext;
import xox.labvorty.weaversparadise.renderers.curios.CottonSkirtCurioRenderer;
import xox.labvorty.weaversparadise.renderers.curios.HandWarmersCurioRenderer;
import xox.labvorty.weaversparadise.renderers.curios.LeatherGlovesRenderer;
import xox.labvorty.weaversparadise.renderers.curios.PantsCurioRenderer;
import xox.labvorty.weaversparadise.renderers.curios.ThighHighsCurioRenderer;
import xox.labvorty.weaversparadise.renderers.curios.UpperwearCurioRenderer;

@Mixin({
        UpperwearCurioRenderer.class,
        PantsCurioRenderer.class,
        ThighHighsCurioRenderer.class,
        HandWarmersCurioRenderer.class,
        LeatherGlovesRenderer.class,
        CottonSkirtCurioRenderer.class
})
public class ClothingCurioRendererMixin {
    @Inject(method = "render", at = @At("HEAD"))
    private void btb$undoLayerBend(CallbackInfo ci, @Local(argsOnly = true) PoseStack poseStack, @Share("pushed") LocalBooleanRef pushed) {
        Pair<Float, Float> bend = BendContext.bodyBend();
        if (bend != null) {
            poseStack.pushPose();
            BendContext.undoBend(poseStack, bend);
            pushed.set(true);
        }
    }

    @Inject(method = "render", at = @At("RETURN"))
    private void btb$restore(CallbackInfo ci, @Local(argsOnly = true) PoseStack poseStack, @Share("pushed") LocalBooleanRef pushed) {
        if (pushed.get()) {
            poseStack.popPose();
        }
    }
}