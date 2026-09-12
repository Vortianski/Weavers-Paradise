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
import xox.labvorty.weaversparadise.items.clothing.PomponHatItem;
import xox.labvorty.weaversparadise.model.PomponHatModel;
import xox.labvorty.weaversparadise.renderers.helpers.SingleSidedClothingRenderingData;
import xox.labvorty.weaversparadise.renderers.models.PomponHatModelRenderer;
import xox.labvorty.weaversparadise.utilities.WeaversUtilities;

public class PomponHatCurioRenderer implements TrinketRenderer {
    private final PomponHatModel<?> model;

    public PomponHatCurioRenderer() {
        this.model = new PomponHatModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(PomponHatModel.LAYER_LOCATION));
    }


    @Override
    public void render(ItemStack itemStack, SlotReference slotReference, EntityModel<? extends LivingEntity> contextModel, PoseStack poseStack, MultiBufferSource multiBufferSource, int packedLight, LivingEntity entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
        LivingEntity livingEntity = entity;

        if (WeaversUtilities.isRestricted(livingEntity, ClientConfig.HAT_RESTRICTOR.get(), EquipmentSlot.HEAD)) {
            return;
        }

        if (itemStack.getItem() instanceof PomponHatItem pomponHatItem) {
            net.minecraft.client.model.EntityModel<? extends LivingEntity> parentModel = contextModel;
            if (parentModel instanceof HumanoidModel<?> humanoidModel) {
                this.model.Head.copyFrom(humanoidModel.head);
            } else {
                return;
            }

            PomponHatModelRenderer.renderModel(
                    multiBufferSource,
                    model,
                    new SingleSidedClothingRenderingData(
                            pomponHatItem.getFlag(itemStack),
                            pomponHatItem.getItemMainColor(itemStack, 1),
                            pomponHatItem.getItemSecondaryColor(itemStack, 1),
                            pomponHatItem.getItemMainColor(itemStack, 2),
                            pomponHatItem.getItemSecondaryColor(itemStack, 2),
                            pomponHatItem.getItemDyeType(itemStack, 1),
                            pomponHatItem.getItemDyeType(itemStack, 2),
                            pomponHatItem.getStensilType(itemStack),
                            pomponHatItem.getItemLightValue(itemStack, 1),
                            pomponHatItem.getItemLightValue(itemStack, 2),
                            "cotton",
                            itemStack.isEnchanted(),
                            pomponHatItem.getGlintColor(itemStack),
                            pomponHatItem.getAdditionalData(itemStack)
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
