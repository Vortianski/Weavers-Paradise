package xox.labvorty.weaversparadise.mixins.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.neoforge.client.ClientHooks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;
import xox.labvorty.vortylib.utilities.VortyLibCurioUtilities;
import xox.labvorty.weaversparadise.items.armor.ModelReplacer;
import xox.labvorty.weaversparadise.items.armor.RenderingData;

@Mixin(HumanoidArmorLayer.class)
public class HumanoidArmorLayerMixin<T extends LivingEntity, A extends HumanoidModel<T>> {
    @Inject(
            method = "renderArmorPiece(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/entity/EquipmentSlot;ILnet/minecraft/client/model/HumanoidModel;FFFFFF)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/renderer/entity/layers/HumanoidArmorLayer;renderModel(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;ILnet/minecraft/client/model/Model;ILnet/minecraft/resources/ResourceLocation;)V",
                    shift = At.Shift.AFTER,
                    ordinal = 0
            )
    )
    private void weaversparadise$additionalModels(PoseStack poseStack, MultiBufferSource multiBufferSource, T livingEntity, EquipmentSlot equipmentSlot, int packedLight, A defaultModel, float limbSwing, float limbSwingAmount, float partialTick, float ageInTicks, float netHeadYaw, float headPitch, CallbackInfo ci) {
        VortyLibCurioUtilities.CurioMatch<ModelReplacer> match = VortyLibCurioUtilities.findFirstCurioOfType(livingEntity, ModelReplacer.class);

        if (match != null) {
            HumanoidModel<?> humanoidModel = match.value().getAdditionalModelForSlot(livingEntity, equipmentSlot, defaultModel);
            if (humanoidModel != null) {
                ClientHooks.copyModelProperties(defaultModel, humanoidModel);
                VertexConsumer vertexConsumer = multiBufferSource.getBuffer(match.value().getAdditionalRenderTypeForSlot(equipmentSlot, match.value().getAdditionalTextureForSlot(equipmentSlot)));
                RenderingData renderingData = match.value().getAdditionalRenderingDataForSlot(equipmentSlot, livingEntity.getItemBySlot(equipmentSlot), packedLight, OverlayTexture.NO_OVERLAY);
                match.value().renderAdditionalLayerBySlot(equipmentSlot, humanoidModel, renderingData, poseStack, vertexConsumer);
            }
        }
    }

    @ModifyArgs(
            method = "renderArmorPiece(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/entity/EquipmentSlot;ILnet/minecraft/client/model/HumanoidModel;FFFFFF)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/renderer/entity/layers/HumanoidArmorLayer;renderModel(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;ILnet/minecraft/client/model/Model;ILnet/minecraft/resources/ResourceLocation;)V",
                    ordinal = 0
            )
    )
    private void weaversparadise$modifyNormalRenderArgs(Args args, PoseStack poseStack, MultiBufferSource multiBufferSource, T livingEntity, EquipmentSlot equipmentSlot, int packedLight, A defaultModel, float limbSwing, float limbSwingAmount, float partialTick, float ageInTicks, float netHeadYaw, float headPitch) {
        VortyLibCurioUtilities.CurioMatch<ModelReplacer> match = VortyLibCurioUtilities.findFirstCurioOfType(livingEntity, ModelReplacer.class);
        if (match != null) {
            RenderingData data = match.value().getRenderingDataForSlot(equipmentSlot, livingEntity.getItemBySlot(equipmentSlot), packedLight, -1);
            args.set(2, data.light());
            args.set(4, data.color());
        }
    }
}
