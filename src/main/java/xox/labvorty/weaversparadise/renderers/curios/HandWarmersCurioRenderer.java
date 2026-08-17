package xox.labvorty.weaversparadise.renderers.curios;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.client.ICurioRenderer;
import xox.labvorty.weaversparadise.configs.ClientConfig;
import xox.labvorty.weaversparadise.items.clothing.HandWarmersCottonItem;
import xox.labvorty.weaversparadise.items.clothing.HandWarmersSilkItem;
import xox.labvorty.weaversparadise.items.clothing.HandWarmersWoolItem;
import xox.labvorty.weaversparadise.items.clothing.defined.HandWarmersInterface;
import xox.labvorty.weaversparadise.model.HandWarmersModel;
import xox.labvorty.weaversparadise.renderers.helpers.DoubleSidedClothingRenderingData;
import xox.labvorty.weaversparadise.renderers.models.HandWarmersModelRenderer;
import xox.labvorty.weaversparadise.utilities.WeaversUtilities;

public class HandWarmersCurioRenderer implements ICurioRenderer {
    private final HandWarmersModel<?> model;

    public HandWarmersCurioRenderer() {
        this.model = new HandWarmersModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(HandWarmersModel.LAYER_LOCATION));
    }

    @Override
    public <T extends LivingEntity, M extends EntityModel<T>> void render(
            ItemStack itemStack,
            SlotContext slotContext,
            PoseStack poseStack,
            RenderLayerParent<T, M> renderLayerParent,
            MultiBufferSource multiBufferSource,
            int packedLight,
            float limbSwing,
            float limbSwingAmount,
            float partialTicks,
            float ageInTicks,
            float netHeadYaw,
            float headPitch
    ) {
        LivingEntity livingEntity = slotContext.entity();

        if (WeaversUtilities.isRestricted(livingEntity, ClientConfig.HAND_WARMERS_RESTRICTOR.get())) {
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

            M parentModel = renderLayerParent.getModel();
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
