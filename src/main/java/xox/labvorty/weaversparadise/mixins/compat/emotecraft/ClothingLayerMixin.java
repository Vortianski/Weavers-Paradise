//Credits Break this bone! by makar_flexer
package xox.labvorty.weaversparadise.mixins.compat.emotecraft;

import dev.kosmx.playerAnim.impl.IUpperPartHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xox.labvorty.weaversparadise.renderers.layer.CottonSkirtRenderLayer;
import xox.labvorty.weaversparadise.renderers.layer.HandWarmersRenderLayer;
import xox.labvorty.weaversparadise.renderers.layer.PantsRenderLayer;
import xox.labvorty.weaversparadise.renderers.layer.ThighHighsRenderLayer;
import xox.labvorty.weaversparadise.renderers.layer.UpperwearRenderLayer;

@Mixin({
        UpperwearRenderLayer.class,
        PantsRenderLayer.class,
        ThighHighsRenderLayer.class,
        HandWarmersRenderLayer.class,
        CottonSkirtRenderLayer.class
})
public class ClothingLayerMixin {
    @Inject(method = "<init>", at = @At("RETURN"))
    private void btb$markLowerLayer(CallbackInfo ci) {
        ((IUpperPartHelper) this).setUpperPart(false);
    }
}