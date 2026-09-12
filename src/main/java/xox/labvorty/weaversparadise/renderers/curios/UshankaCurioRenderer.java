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
import xox.labvorty.weaversparadise.items.clothing.UshankaItem;
import xox.labvorty.weaversparadise.model.UshankaModel;
import xox.labvorty.weaversparadise.renderers.helpers.DoubleSidedClothingRenderingData;
import xox.labvorty.weaversparadise.renderers.models.UshankaModelRenderer;
import xox.labvorty.weaversparadise.utilities.WeaversUtilities;

public class UshankaCurioRenderer implements TrinketRenderer {
    private final UshankaModel<?> model;

    public UshankaCurioRenderer() {
        this.model = new UshankaModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(UshankaModel.LAYER_LOCATION));
    }

    @Override
    public void render(ItemStack itemStack, SlotReference slotReference, EntityModel<? extends LivingEntity> contextModel, PoseStack poseStack, MultiBufferSource multiBufferSource, int packedLight, LivingEntity entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {

        if (WeaversUtilities.isRestricted(entity, ClientConfig.HAT_RESTRICTOR.get(), EquipmentSlot.HEAD)) {
            return;
        }

        if (itemStack.getItem() instanceof UshankaItem ushankaItem) {
            net.minecraft.client.model.EntityModel<? extends LivingEntity> parentModel = contextModel;
            if (parentModel instanceof HumanoidModel<?> humanoidModel) {
                this.model.Head.copyFrom(humanoidModel.head);
            }

            UshankaModelRenderer.renderModel(
                    multiBufferSource,
                    model,
                    new DoubleSidedClothingRenderingData(
                            ushankaItem.getFlag(itemStack),
                            ushankaItem.getItemMainColor(itemStack, "left", 1),
                            ushankaItem.getItemSecondaryColor(itemStack, "left", 1),
                            ushankaItem.getItemMainColor(itemStack, "right", 1),
                            ushankaItem.getItemSecondaryColor(itemStack, "right", 1),
                            ushankaItem.getItemMainColor(itemStack, "left", 2),
                            ushankaItem.getItemSecondaryColor(itemStack, "left", 2),
                            ushankaItem.getItemMainColor(itemStack, "right", 2),
                            ushankaItem.getItemSecondaryColor(itemStack, "right", 2),
                            ushankaItem.getItemDyeType(itemStack, "left", 1),
                            ushankaItem.getItemDyeType(itemStack, "right", 1),
                            ushankaItem.getItemDyeType(itemStack, "left", 2),
                            ushankaItem.getItemDyeType(itemStack, "right", 2),
                            ushankaItem.getStensilType(itemStack, "left"),
                            ushankaItem.getStensilType(itemStack, "right"),
                            ushankaItem.getItemLightValue(itemStack, "left", 1),
                            ushankaItem.getItemLightValue(itemStack, "left", 2),
                            ushankaItem.getItemLightValue(itemStack, "right", 1),
                            ushankaItem.getItemLightValue(itemStack, "right", 2),
                            "base",
                            itemStack.isEnchanted(),
                            ushankaItem.getGlintColor(itemStack),
                            ushankaItem.getAdditionalData(itemStack)
                    ),
                    entity,
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
