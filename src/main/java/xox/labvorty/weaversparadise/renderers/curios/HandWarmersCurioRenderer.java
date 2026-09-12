package xox.labvorty.weaversparadise.renderers.curios;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.client.TrinketRenderer;
import xox.labvorty.weaversparadise.configs.ClientConfig;
import xox.labvorty.weaversparadise.items.clothing.HandWarmersCottonItem;
import xox.labvorty.weaversparadise.items.clothing.HandWarmersSilkItem;
import xox.labvorty.weaversparadise.items.clothing.HandWarmersWoolItem;
import xox.labvorty.weaversparadise.items.clothing.defined.HandWarmersInterface;
import xox.labvorty.weaversparadise.model.HandWarmersModel;
import xox.labvorty.weaversparadise.renderers.helpers.DoubleSidedClothingRenderingData;
import xox.labvorty.weaversparadise.renderers.models.HandWarmersModelRenderer;
import xox.labvorty.weaversparadise.utilities.WeaversUtilities;

public class HandWarmersCurioRenderer implements TrinketRenderer {
    private final HandWarmersModel<?> model;

    public HandWarmersCurioRenderer() {
        this.model = new HandWarmersModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(HandWarmersModel.LAYER_LOCATION));
    }

    @Override
    public void render(ItemStack itemStack, SlotReference slotReference, EntityModel<? extends LivingEntity> contextModel, PoseStack poseStack, MultiBufferSource multiBufferSource, int packedLight, LivingEntity entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
        LivingEntity livingEntity = entity;

        if (WeaversUtilities.isRestricted(livingEntity, ClientConfig.HAND_WARMERS_RESTRICTOR.get(), EquipmentSlot.CHEST)) {
            return;
        }

        if (itemStack.getItem() instanceof HandWarmersInterface handWarmersInterface) {
            String material;

            switch (itemStack.getItem()) {
                case HandWarmersCottonItem handWarmersCottonItem -> material = "cotton";
                case HandWarmersSilkItem handWarmersSilkItem -> material = "silk";
                case HandWarmersWoolItem handWarmersWoolItem -> material = "wool";
                default -> {
                    return;
                }
            }

            net.minecraft.client.model.EntityModel<? extends LivingEntity> parentModel = contextModel;
            if (parentModel instanceof HumanoidModel<?> humanoidModel) {
                this.model.RightArm.copyFrom(humanoidModel.rightArm);
                this.model.LeftArm.copyFrom(humanoidModel.leftArm);
            } else {
                return;
            }

            HandWarmersModelRenderer.renderModel(
                    multiBufferSource,
                    model,
                    new DoubleSidedClothingRenderingData(
                            handWarmersInterface.getFlag(itemStack),
                            handWarmersInterface.getItemMainColor(itemStack, "left", 1),
                            handWarmersInterface.getItemSecondaryColor(itemStack, "left", 1),
                            handWarmersInterface.getItemMainColor(itemStack, "right", 1),
                            handWarmersInterface.getItemSecondaryColor(itemStack, "right", 1),
                            handWarmersInterface.getItemMainColor(itemStack, "left", 2),
                            handWarmersInterface.getItemSecondaryColor(itemStack, "left", 2),
                            handWarmersInterface.getItemMainColor(itemStack, "right", 2),
                            handWarmersInterface.getItemSecondaryColor(itemStack, "right", 2),
                            handWarmersInterface.getItemDyeType(itemStack, "left", 1),
                            handWarmersInterface.getItemDyeType(itemStack, "right", 1),
                            handWarmersInterface.getItemDyeType(itemStack, "left", 2),
                            handWarmersInterface.getItemDyeType(itemStack, "right", 2),
                            handWarmersInterface.getStensilType(itemStack, "left"),
                            handWarmersInterface.getStensilType(itemStack, "right"),
                            handWarmersInterface.getItemLightValue(itemStack, "left", 1),
                            handWarmersInterface.getItemLightValue(itemStack, "left", 2),
                            handWarmersInterface.getItemLightValue(itemStack, "right", 1),
                            handWarmersInterface.getItemLightValue(itemStack, "right", 2),
                            material,
                            itemStack.isEnchanted(),
                            handWarmersInterface.getGlintColor(itemStack),
                            handWarmersInterface.getAdditionalData(itemStack)
                    ),
                    livingEntity,
                    1,
                    1,
                    1,
                    0,
                    0,
                    0,
                    0,
                    0,
                    0,
                    0,
                    0,
                    0,
                    poseStack,
                    packedLight
            );
        }
    }
}
