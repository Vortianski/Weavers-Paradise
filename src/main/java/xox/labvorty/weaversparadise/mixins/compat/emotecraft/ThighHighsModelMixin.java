//Credits Break this bone! by makar_flexer
package xox.labvorty.weaversparadise.mixins.compat.emotecraft;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.core.Direction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xox.labvorty.weaversparadise.compat.emotecraft.BendContext;
import xox.labvorty.weaversparadise.model.ThighHighsModel;

@Mixin(ThighHighsModel.class)
public class ThighHighsModelMixin {
    @Inject(method = "<init>", at = @At("RETURN"))
    private void btb$initBends(ModelPart root, CallbackInfo ci) {
        ThighHighsModel<?> self = (ThighHighsModel<?>) (Object) this;
        BendContext.initBend(self.RightLeg, Direction.UP);
        BendContext.initBend(self.LeftLeg, Direction.UP);
    }
}