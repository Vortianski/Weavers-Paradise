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
import xox.labvorty.weaversparadise.items.clothing.CapItem;
import xox.labvorty.weaversparadise.model.CapModel;
import xox.labvorty.weaversparadise.renderers.helpers.SingleSidedClothingRenderingData;
import xox.labvorty.weaversparadise.renderers.models.CapModelRenderer;
import xox.labvorty.weaversparadise.utilities.WeaversUtilities;

public class CapCurioRenderer implements TrinketRenderer {
    private final CapModel<?> model;

    public CapCurioRenderer() {
        this.model = new CapModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(CapModel.LAYER_LOCATION));
    }


    @Override
    public void render(ItemStack itemStack, SlotReference slotReference, EntityModel<? extends LivingEntity> contextModel, PoseStack poseStack, MultiBufferSource multiBufferSource, int packedLight, LivingEntity entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
        LivingEntity livingEntity = entity;

        if (WeaversUtilities.isRestricted(livingEntity, ClientConfig.HAT_RESTRICTOR.get(), EquipmentSlot.HEAD)) {
            return;
        }

        if (itemStack.getItem() instanceof CapItem capItem) {
            net.minecraft.client.model.EntityModel<? extends LivingEntity> parentModel = contextModel;
            if (parentModel instanceof HumanoidModel<?> humanoidModel) {
                this.model.Head.copyFrom(humanoidModel.head);
            } else {
                return;
            }

            CapModelRenderer.renderModel(
                    multiBufferSource,
                    model,
                    new SingleSidedClothingRenderingData(
                            capItem.getFlag(itemStack),
                            capItem.getItemMainColor(itemStack, 1),
                            capItem.getItemSecondaryColor(itemStack, 1),
                            capItem.getItemMainColor(itemStack, 2),
                            capItem.getItemSecondaryColor(itemStack, 2),
                            capItem.getItemDyeType(itemStack, 1),
                            capItem.getItemDyeType(itemStack, 2),
                            capItem.getStensilType(itemStack),
                            capItem.getItemLightValue(itemStack, 1),
                            capItem.getItemLightValue(itemStack, 2),
                            "jeans",
                            itemStack.isEnchanted(),
                            capItem.getGlintColor(itemStack),
                            capItem.getAdditionalData(itemStack)
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
