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
import xox.labvorty.weaversparadise.client.render.WPRenderTypes;
import xox.labvorty.weaversparadise.init.WeaversParadiseAttachmentTypes;


@Mixin(LivingEntityRenderer.class)
public class LivingRendererMixin<T extends LivingEntity> {
    @Unique
    private T weaversparadise$currentEntity;


    @Inject(
            method = "render(Lnet/minecraft/world/entity/LivingEntity;FFLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V",
            at = @At("HEAD")
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
            )
    )
    private VertexConsumer weaversparadise$redirectGetBuffer(MultiBufferSource buffer, RenderType renderType) {
        T entity = this.weaversparadise$currentEntity;
        // NPE-фикс: NeoForge-аттачмент имел дефолт false; на Fabric getAttached возвращает null,
        // если аттачмент ни разу не ставился (любой моб, кроме помеченных). Читаем null-безопасно.
        Boolean chromaticShiftActive = entity == null ? null : entity.getAttached(WeaversParadiseAttachmentTypes.CHROMATIC_SHIFT_ACTIVE);
        if (Boolean.TRUE.equals(chromaticShiftActive)) {
            renderType = WPRenderTypes.getEntityPolychromaticCull(((LivingEntityRenderer) (Object) this).getTextureLocation(entity));
        }
        return buffer.getBuffer(renderType);
    }
}
