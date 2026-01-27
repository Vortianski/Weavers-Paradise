package xox.labvorty.weaversparadise.mixins.client;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.world.InteractionHand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xox.labvorty.weaversparadise.items.Plushie;

@Mixin(PlayerRenderer.class)
public class PlayerRendererMixin {
    @Inject(
            method = "getArmPose",
            at = @At("TAIL"),
            cancellable = true
    )
    private static void cuddleBlahaj(AbstractClientPlayer player, InteractionHand hand, CallbackInfoReturnable<HumanoidModel.ArmPose> ci) {
        if(player.getItemInHand(hand).getItem() instanceof Plushie) {
            ci.setReturnValue(HumanoidModel.ArmPose.CROSSBOW_HOLD);
            ci.cancel();
        }
    }
}
