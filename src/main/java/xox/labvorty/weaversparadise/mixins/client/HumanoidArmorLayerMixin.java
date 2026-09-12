package xox.labvorty.weaversparadise.mixins.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;
import xox.labvorty.weaversparadise.util.WPTrinkets;
import xox.labvorty.weaversparadise.configs.ClientConfig;
import xox.labvorty.weaversparadise.items.armor.ModelReplacer;
import xox.labvorty.weaversparadise.items.armor.RenderingData;

@Mixin(value = HumanoidArmorLayer.class, priority = 100)
public abstract class HumanoidArmorLayerMixin<T extends LivingEntity, M extends HumanoidModel<T>, A extends HumanoidModel<T>> {
    @Shadow
    protected abstract void renderModel(PoseStack poseStack, MultiBufferSource multiBufferSource, int packedLight, HumanoidModel model, int color, ResourceLocation resourceLocation);

    @Shadow
    protected abstract void renderGlint(PoseStack poseStack, MultiBufferSource multiBufferSource, int packedLight, HumanoidModel model);

    @Inject(
            method = "render(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;ILnet/minecraft/world/entity/LivingEntity;FFFFFF)V",
            at = @At("HEAD"),
            cancellable = true
    )
    private void weaversparadise$replaceRenderer(
            PoseStack poseStack,
            MultiBufferSource multiBufferSource,
            int packedLight,
            T livingEntity,
            float limbSwing,
            float limbSwingAmount,
            float partialTick,
            float ageInTicks,
            float netHeadYaw,
            float headPitch,
            CallbackInfo ci
    ) {
        WPTrinkets.CurioMatch<ModelReplacer> match = WPTrinkets.findFirstCurioMatch(livingEntity, ModelReplacer.class);

        if (match != null) {
            this.weaversparadise$renderReplacedArmorPiece(match.value(), poseStack, multiBufferSource, livingEntity, EquipmentSlot.CHEST, packedLight, limbSwing, limbSwingAmount, partialTick, ageInTicks, netHeadYaw, headPitch);
            this.weaversparadise$renderReplacedArmorPiece(match.value(), poseStack, multiBufferSource, livingEntity, EquipmentSlot.LEGS, packedLight, limbSwing, limbSwingAmount, partialTick, ageInTicks, netHeadYaw, headPitch);
            this.weaversparadise$renderReplacedArmorPiece(match.value(), poseStack, multiBufferSource, livingEntity, EquipmentSlot.FEET, packedLight, limbSwing, limbSwingAmount, partialTick, ageInTicks, netHeadYaw, headPitch);
            this.weaversparadise$renderReplacedArmorPiece(match.value(), poseStack, multiBufferSource, livingEntity, EquipmentSlot.HEAD, packedLight, limbSwing, limbSwingAmount, partialTick, ageInTicks, netHeadYaw, headPitch);
            ci.cancel();
        }
    }

    @SuppressWarnings("unchecked")
    @Unique
    private void weaversparadise$renderReplacedArmorPiece(
            ModelReplacer modelReplacer,
            PoseStack poseStack,
            MultiBufferSource multiBufferSource,
            T livingEntity,
            EquipmentSlot equipmentSlot,
            int packedLight,
            float limbSwing,
            float limbSwingAmount,
            float partialTick,
            float ageInTicks,
            float netHeadYaw,
            float headPitch
    ) {
        ItemStack itemStack = livingEntity.getItemBySlot(equipmentSlot);

        if ((itemStack.getItem() instanceof ArmorItem armorItem && armorItem.getEquipmentSlot() == equipmentSlot) || ClientConfig.FULL_ARMOR.get()) {
            HumanoidModel<?> parentModel = ((HumanoidArmorLayer<T, M, A>)(Object)this).getParentModel();
            HumanoidModel<?> humanoidModel = modelReplacer.getModelForSlot(livingEntity, equipmentSlot, parentModel);
            HumanoidModel<?> additionalModel = modelReplacer.getAdditionalModelForSlot(livingEntity, equipmentSlot, parentModel);
            weaversparadise$copyAllProperties(parentModel, humanoidModel);
            ResourceLocation resourceLocation = modelReplacer.getTextureForSlot(equipmentSlot);
            ResourceLocation additionalResourceLocation = modelReplacer.getAdditionalTextureForSlot(equipmentSlot);

            this.renderModel(poseStack, multiBufferSource, packedLight, humanoidModel, -1, resourceLocation);
            RenderType renderType = modelReplacer.getAdditionalRenderTypeForSlot(equipmentSlot, additionalResourceLocation);
            if (renderType != null && additionalModel != null) {
                weaversparadise$copyAllProperties(parentModel, additionalModel);
                VertexConsumer vertexConsumer = multiBufferSource.getBuffer(renderType);
                RenderingData renderingData = modelReplacer.getAdditionalRenderingDataForSlot(equipmentSlot, livingEntity.getItemBySlot(equipmentSlot), packedLight, OverlayTexture.NO_OVERLAY);
                modelReplacer.renderAdditionalLayerBySlot(equipmentSlot, additionalModel, renderingData, poseStack, vertexConsumer);
            }

            if (!itemStack.isEmpty() && itemStack.isEnchanted()) {
                this.renderGlint(poseStack, multiBufferSource, packedLight, humanoidModel);
            }
        }
    }

    @Unique
    private void weaversparadise$copyAllProperties(HumanoidModel<?> parentModel, HumanoidModel<?> childModel) {
        childModel.leftArmPose = parentModel.leftArmPose;
        childModel.rightArmPose = parentModel.rightArmPose;
        childModel.crouching = parentModel.crouching;
        childModel.head.copyFrom(parentModel.head);
        childModel.hat.copyFrom(parentModel.hat);
        childModel.body.copyFrom(parentModel.body);
        childModel.rightArm.copyFrom(parentModel.rightArm);
        childModel.leftArm.copyFrom(parentModel.leftArm);
        childModel.rightLeg.copyFrom(parentModel.rightLeg);
        childModel.leftLeg.copyFrom(parentModel.leftLeg);
    }

    @ModifyArgs(
            method = "renderArmorPiece(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/entity/EquipmentSlot;ILnet/minecraft/client/model/HumanoidModel;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/renderer/entity/layers/HumanoidArmorLayer;renderModel(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;ILnet/minecraft/client/model/HumanoidModel;ILnet/minecraft/resources/ResourceLocation;)V",
                    ordinal = 0
            )
    )
    private void weaversparadise$modifyNormalRenderArgs(Args args, PoseStack poseStack, MultiBufferSource multiBufferSource, T livingEntity, EquipmentSlot equipmentSlot, int packedLight, A defaultModel) {
        WPTrinkets.CurioMatch<ModelReplacer> match = WPTrinkets.findFirstCurioMatch(livingEntity, ModelReplacer.class);
        if (match != null) {
            RenderingData data = match.value().getRenderingDataForSlot(equipmentSlot, livingEntity.getItemBySlot(equipmentSlot), packedLight, -1);
            args.set(2, data.light());
            args.set(4, data.color());
        }
    }
}
