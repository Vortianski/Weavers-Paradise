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
import xox.labvorty.weaversparadise.items.clothing.ChokerItem;
import xox.labvorty.weaversparadise.model.ChokerModel;
import xox.labvorty.weaversparadise.renderers.helpers.DoubleSidedClothingRenderingData;
import xox.labvorty.weaversparadise.renderers.models.ChokerModelRenderer;
import xox.labvorty.weaversparadise.utilities.WeaversUtilities;

public class ChokerCurioRenderer implements TrinketRenderer {
    private final ChokerModel<?> model;

    public ChokerCurioRenderer() {
        this.model = new ChokerModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(ChokerModel.LAYER_LOCATION));
    }

    @Override
    public void render(ItemStack itemStack, SlotReference slotReference, EntityModel<? extends LivingEntity> contextModel, PoseStack poseStack, MultiBufferSource multiBufferSource, int packedLight, LivingEntity entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {

        if (WeaversUtilities.isRestricted(entity, ClientConfig.CHOKER_RESTRICTOR.get(), EquipmentSlot.CHEST)) {
            return;
        }

        if (itemStack.getItem() instanceof ChokerItem chokerItem) {
            net.minecraft.client.model.EntityModel<? extends LivingEntity> parentModel = contextModel;
            if (parentModel instanceof HumanoidModel<?> humanoidModel) {
                this.model.Body.copyFrom(humanoidModel.body);
            }

            ChokerModelRenderer.renderModel(
                    multiBufferSource,
                    model,
                    new DoubleSidedClothingRenderingData(
                            chokerItem.getFlag(itemStack),
                            chokerItem.getItemMainColor(itemStack, "left", 1),
                            chokerItem.getItemSecondaryColor(itemStack, "left", 1),
                            chokerItem.getItemMainColor(itemStack, "right", 1),
                            chokerItem.getItemSecondaryColor(itemStack, "right", 1),
                            chokerItem.getItemMainColor(itemStack, "left", 2),
                            chokerItem.getItemSecondaryColor(itemStack, "left", 2),
                            chokerItem.getItemMainColor(itemStack, "right", 2),
                            chokerItem.getItemSecondaryColor(itemStack, "right", 2),
                            chokerItem.getItemDyeType(itemStack, "left", 1),
                            chokerItem.getItemDyeType(itemStack, "right", 1),
                            chokerItem.getItemDyeType(itemStack, "left", 2),
                            chokerItem.getItemDyeType(itemStack, "right", 2),
                            chokerItem.getStensilType(itemStack, "left"),
                            chokerItem.getStensilType(itemStack, "right"),
                            chokerItem.getItemLightValue(itemStack, "left", 1),
                            chokerItem.getItemLightValue(itemStack, "left", 2),
                            chokerItem.getItemLightValue(itemStack, "right", 1),
                            chokerItem.getItemLightValue(itemStack, "right", 2),
                            "base",
                            itemStack.isEnchanted(),
                            chokerItem.getGlintColor(itemStack),
                            chokerItem.getAdditionalData(itemStack)
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
