package xox.labvorty.weaversparadise.renderers.curio;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.client.ICurioRenderer;
import xox.labvorty.weaversparadise.configs.ClientConfig;
import xox.labvorty.weaversparadise.items.clothing.*;
import xox.labvorty.weaversparadise.items.clothing.defined.OverallsInterface;
import xox.labvorty.weaversparadise.items.clothing.defined.PantsInterface;
import xox.labvorty.weaversparadise.model.PantsModel;
import xox.labvorty.weaversparadise.renderers.helpers.RenderingUtils;
import xox.labvorty.weaversparadise.renderers.helpers.SingleSidedClothingRenderingData;
import xox.labvorty.weaversparadise.renderers.models.PantsModelRenderer;
import xox.labvorty.weaversparadise.utilities.WeaversUtilities;

public class PantsCurioRenderer implements ICurioRenderer {
    private final PantsModel<?> model;

    public PantsCurioRenderer() {
        this.model = new PantsModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(PantsModel.LAYER_LOCATION));
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

        if (WeaversUtilities.isRestricted(livingEntity, ClientConfig.PANTS_RESTRICTOR.get(), EquipmentSlot.LEGS)) {
            return;
        }

        PantsModelRenderer.Type type;
        SingleSidedClothingRenderingData renderingData;
        if (itemStack.getItem() instanceof PantsInterface pantsInterface) {
            String material;
            if (itemStack.getItem() instanceof PantsCottonItem) {
                material = "cotton";
            } else if (itemStack.getItem() instanceof PantsSilkItem) {
                material = "silk";
            } else if (itemStack.getItem() instanceof PantsWoolItem) {
                material = "wool";
            } else if (itemStack.getItem() instanceof PantsJeansItem) {
                material = "jeans";
            } else {
                return;
            }
            type = PantsModelRenderer.Type.PANTS;

            renderingData = new SingleSidedClothingRenderingData(
                    pantsInterface.getFlag(itemStack),
                    pantsInterface.getItemMainColor(itemStack, 1),
                    pantsInterface.getItemSecondaryColor(itemStack, 1),
                    pantsInterface.getItemMainColor(itemStack, 2),
                    pantsInterface.getItemSecondaryColor(itemStack, 2),
                    pantsInterface.getItemDyeType(itemStack, 1),
                    pantsInterface.getItemDyeType(itemStack, 2),
                    pantsInterface.getStensilType(itemStack),
                    pantsInterface.getItemLightValue(itemStack, 1),
                    pantsInterface.getItemLightValue(itemStack, 2),
                    material,
                    itemStack.isEnchanted(),
                    pantsInterface.getGlintColor(itemStack),
                    pantsInterface.getAdditionalData(itemStack)
            );
        } else if (itemStack.getItem() instanceof OverallsInterface overallsInterface) {
            String material;
            if (itemStack.getItem() instanceof OverallsItem) {
                material = "cotton";
            } else {
                return;
            }
            type = PantsModelRenderer.Type.OVERALLS;

            renderingData = new SingleSidedClothingRenderingData(
                    overallsInterface.getFlag(itemStack),
                    overallsInterface.getItemMainColor(itemStack, 1),
                    overallsInterface.getItemSecondaryColor(itemStack, 1),
                    overallsInterface.getItemMainColor(itemStack, 2),
                    overallsInterface.getItemSecondaryColor(itemStack, 2),
                    overallsInterface.getItemDyeType(itemStack, 1),
                    overallsInterface.getItemDyeType(itemStack, 2),
                    overallsInterface.getStensilType(itemStack),
                    overallsInterface.getItemLightValue(itemStack, 1),
                    overallsInterface.getItemLightValue(itemStack, 2),
                    material,
                    itemStack.isEnchanted(),
                    overallsInterface.getGlintColor(itemStack),
                    overallsInterface.getAdditionalData(itemStack)
            );
        } else {
            return;
        }

        M parentModel = renderLayerParent.getModel();
        if (parentModel instanceof HumanoidModel<?> humanoidModel) {
            this.model.Body.copyFrom(humanoidModel.body);
            this.model.RightLeg.copyFrom(humanoidModel.rightLeg);
            this.model.LeftLeg.copyFrom(humanoidModel.leftLeg);
        } else {
            return;
        }

        PantsModelRenderer.renderModel(
                multiBufferSource,
                model,
                renderingData,
                livingEntity,
                RenderingUtils.getScale(),
                RenderingUtils.getScale(),
                RenderingUtils.getScale(),
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
                packedLight,
                type
        );
    }
}
