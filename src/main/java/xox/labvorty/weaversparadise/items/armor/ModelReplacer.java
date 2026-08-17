package xox.labvorty.weaversparadise.items.armor;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public interface ModelReplacer {
    HumanoidModel<?> getModelForHead(LivingEntity livingEntity, HumanoidModel<?> defaultModel);
    HumanoidModel<?> getModelForChestplate(LivingEntity livingEntity, HumanoidModel<?> defaultModel);
    HumanoidModel<?> getModelForLeggings(LivingEntity livingEntity, HumanoidModel<?> defaultModel);
    HumanoidModel<?> getModelForBoots(LivingEntity livingEntity, HumanoidModel<?> defaultModel);
    default HumanoidModel<?> getModelForSlot(LivingEntity livingEntity, EquipmentSlot equipmentSlot, HumanoidModel<?> defaultModel) {
        return switch (equipmentSlot) {
            case HEAD -> getModelForHead(livingEntity, defaultModel);
            case CHEST -> getModelForChestplate(livingEntity, defaultModel);
            case LEGS -> getModelForLeggings(livingEntity, defaultModel);
            case FEET -> getModelForBoots(livingEntity, defaultModel);

            default -> defaultModel;
        };
    }
    ResourceLocation getMainTexture();
    default ResourceLocation getTextureForHead() {
        return getMainTexture();
    };
    default ResourceLocation getTextureForChestplate() {
        return getMainTexture();
    };
    default ResourceLocation getTextureForLeggings() {
        return getMainTexture();
    };
    default ResourceLocation getTextureForBoots() {
        return getMainTexture();
    };
    default ResourceLocation getTextureForSlot(EquipmentSlot equipmentSlot) {
        return switch (equipmentSlot) {
            case HEAD -> getTextureForHead();
            case CHEST -> getTextureForChestplate();
            case LEGS -> getTextureForLeggings();
            case FEET -> getTextureForBoots();

            default -> getMainTexture();
        };
    }
    default RenderingData getRenderingDataForSlot(EquipmentSlot equipmentSlot, ItemStack itemStack, int packedLight, int packedOverlay) {
        return new RenderingData(-1, packedLight, OverlayTexture.NO_OVERLAY);
    }
    default HumanoidModel<?> getAdditionalModelForSlot(LivingEntity livingEntity, EquipmentSlot equipmentSlot, HumanoidModel<?> defaultModel) {
        return null;
    }
    default ResourceLocation getAdditionalTextureForSlot(EquipmentSlot equipmentSlot) {
        return null;
    }
    default RenderType getAdditionalRenderTypeForSlot(EquipmentSlot equipmentSlot, ResourceLocation resourceLocation) {
        return null;
    }
    default RenderingData getAdditionalRenderingDataForSlot(EquipmentSlot equipmentSlot, ItemStack itemStack, int packedLight, int packedOverlay) {
        return new RenderingData(-1, packedLight, OverlayTexture.NO_OVERLAY);
    }
    default void renderAdditionalLayerBySlot(EquipmentSlot equipmentSlot, HumanoidModel<?> model, RenderingData renderingData, PoseStack poseStack, VertexConsumer vertexConsumer) {
        model.renderToBuffer(poseStack, vertexConsumer, renderingData.light(), renderingData.overlay(), renderingData.color());
    }
}