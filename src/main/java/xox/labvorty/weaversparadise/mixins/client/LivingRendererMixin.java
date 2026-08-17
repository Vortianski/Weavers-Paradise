package xox.labvorty.weaversparadise.mixins.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xox.labvorty.vortylib.init.VortyLibRenderTypes;
import xox.labvorty.weaversparadise.init.WeaversParadiseAttachmentTypes;

@Mixin(LivingEntityRenderer.class)
public class LivingRendererMixin<T extends LivingEntity> {
    @Unique
    private T weaversparadise$currentEntity;

    @Inject(
            method = "render(Lnet/minecraft/world/entity/LivingEntity;FFLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V",
            at = @At("HEAD"),
            remap = false
    )
    private void weaversparadise$captureEntity(T entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight, CallbackInfo ci) {
        this.weaversparadise$currentEntity = entity;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Redirect(
            method = "render(Lnet/minecraft/world/entity/LivingEntity;FFLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/renderer/MultiBufferSource;getBuffer(Lnet/minecraft/client/renderer/RenderType;)Lcom/mojang/blaze3d/vertex/VertexConsumer;"
            ),
            remap = false
    )
    private VertexConsumer weaversparadise$redirectGetBuffer(MultiBufferSource buffer, RenderType renderType) {
        T entity = this.weaversparadise$currentEntity;

        if (entity != null && entity.getData(WeaversParadiseAttachmentTypes.CHROMATIC_SHIFT_ACTIVE.get())) {
            renderType = VortyLibRenderTypes.getEntityPolychromaticCull(((LivingEntityRenderer) (Object) this).getTextureLocation(entity));
        }

        return buffer.getBuffer(renderType);
    }
}
