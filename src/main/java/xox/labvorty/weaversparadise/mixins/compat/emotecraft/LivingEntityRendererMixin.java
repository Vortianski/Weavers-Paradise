//Credits Break this bone! by makar_flexer
package xox.labvorty.weaversparadise.mixins.compat.emotecraft;

import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xox.labvorty.weaversparadise.compat.emotecraft.BendContext;

@Mixin(LivingEntityRenderer.class)
public class LivingEntityRendererMixin {
    private static final String RENDER = "render(Lnet/minecraft/world/entity/LivingEntity;FFLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V";

    @Inject(method = RENDER, at = @At("HEAD"))
    private void btb$enter(LivingEntity entity, float yaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int light, CallbackInfo ci, @Share("previous") LocalRef<LivingEntity> previous) {
        previous.set(BendContext.enter(entity));
    }

    @Inject(method = RENDER, at = @At("RETURN"))
    private void btb$exit(LivingEntity entity, float yaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int light, CallbackInfo ci, @Share("previous") LocalRef<LivingEntity> previous) {
        BendContext.exit(previous.get());
    }
}