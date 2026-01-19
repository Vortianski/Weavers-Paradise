package xox.labvorty.weaversparadise.mixins;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import xox.labvorty.weaversparadise.init.WeaversParadiseMobEffects;

@Mixin(LivingEntityRenderer.class)
public class LivingRendererMixin<T extends LivingEntity> {
    @Redirect(
            method = "render(Lnet/minecraft/world/entity/LivingEntity;FFLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/model/EntityModel;renderToBuffer(Lcom/mojang/blaze3d/vertex/PoseStack;Lcom/mojang/blaze3d/vertex/VertexConsumer;III)V"
            )
    )
    private void myRenderToBuffer(
            EntityModel<T> model,
            PoseStack poseStack,
            VertexConsumer vertexConsumer,
            int packedLight,
            int packedOverlay,
            int packedColor,
            // Context parameters from the calling method
            T livingEntity,
            float entityYaw,
            float partialTicks,
            PoseStack unusedPoseStack,
            MultiBufferSource buffer,
            int light
    ) {
        if (model == null || livingEntity == null || poseStack == null || vertexConsumer == null) {
            model.renderToBuffer(poseStack, vertexConsumer, packedLight, packedOverlay, packedColor);
            return;
        }

        if (livingEntity.hasEffect(WeaversParadiseMobEffects.CHROMATIC_SHIFT)) {
            float speed = 0.05F;

            int ticks = livingEntity.tickCount;

            float rred = Mth.clamp((float)(Math.sin(ticks * speed) * 0.5 + 0.5), 0, 1);;
            float ggreen = Mth.clamp((float)(Math.sin(ticks * speed + 2 * Math.PI / 3) * 0.5 + 0.5), 0, 1);;
            float bblue = Mth.clamp((float)(Math.sin(ticks * speed + 4 * Math.PI / 3) * 0.5 + 0.5), 0, 1);

            int pcackedColor = ((int)(255) << 24) | ((int)(rred * 255) << 16) | ((int)(ggreen * 255) << 8) | (int)(bblue * 255);

            model.renderToBuffer(poseStack, vertexConsumer, packedLight, packedOverlay, pcackedColor);
        } else {
            model.renderToBuffer(poseStack, vertexConsumer, packedLight, packedOverlay, packedColor);
        }
    }
}
