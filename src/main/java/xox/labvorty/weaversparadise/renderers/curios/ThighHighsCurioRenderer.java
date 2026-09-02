package xox.labvorty.weaversparadise.renderers.curios;

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
import xox.labvorty.weaversparadise.items.clothing.ThighHighsCottonItem;
import xox.labvorty.weaversparadise.items.clothing.ThighHighsSilkItem;
import xox.labvorty.weaversparadise.items.clothing.ThighHighsWoolItem;
import xox.labvorty.weaversparadise.items.clothing.defined.ThighHighsInterface;
import xox.labvorty.weaversparadise.model.ThighHighsModel;
import xox.labvorty.weaversparadise.renderers.helpers.DoubleSidedClothingRenderingData;
import xox.labvorty.weaversparadise.renderers.models.ThighHighsModelRenderer;
import xox.labvorty.weaversparadise.utilities.WeaversUtilities;

public class ThighHighsCurioRenderer implements ICurioRenderer {
    private final ThighHighsModel<?> model;

    public ThighHighsCurioRenderer() {
        this.model = new ThighHighsModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(ThighHighsModel.LAYER_LOCATION));
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

        if (WeaversUtilities.isRestricted(livingEntity, ClientConfig.THIGH_HIGHS_RESTRICTOR.get(), EquipmentSlot.FEET, EquipmentSlot.LEGS)) {
            return;
        }

        if (itemStack.getItem() instanceof ThighHighsInterface thighHighsInterface) {
            String material;

            switch (itemStack.getItem()) {
                case ThighHighsCottonItem thighHighsCottonItem -> material = "cotton";
                case ThighHighsSilkItem thighHighsSilkItem -> material = "silk";
                case ThighHighsWoolItem thighHighsWoolItem -> material = "wool";
                default -> {
                    return;
                }
            }

            M parentModel = renderLayerParent.getModel();
            if (parentModel instanceof HumanoidModel<?> humanoidModel) {
                this.model.RightLeg.copyFrom(humanoidModel.rightLeg);
                this.model.LeftLeg.copyFrom(humanoidModel.leftLeg);
            } else {
                return;
            }

            ThighHighsModelRenderer.renderModel(
                    multiBufferSource,
                    model,
                    new DoubleSidedClothingRenderingData(
                            thighHighsInterface.getFlag(itemStack),
                            thighHighsInterface.getItemMainColor(itemStack, "left", 1),
                            thighHighsInterface.getItemSecondaryColor(itemStack, "left", 1),
                            thighHighsInterface.getItemMainColor(itemStack, "right", 1),
                            thighHighsInterface.getItemSecondaryColor(itemStack, "right", 1),
                            thighHighsInterface.getItemMainColor(itemStack, "left", 2),
                            thighHighsInterface.getItemSecondaryColor(itemStack, "left", 2),
                            thighHighsInterface.getItemMainColor(itemStack, "right", 2),
                            thighHighsInterface.getItemSecondaryColor(itemStack, "right", 2),
                            thighHighsInterface.getItemDyeType(itemStack, "left", 1),
                            thighHighsInterface.getItemDyeType(itemStack, "right", 1),
                            thighHighsInterface.getItemDyeType(itemStack, "left", 2),
                            thighHighsInterface.getItemDyeType(itemStack, "right", 2),
                            thighHighsInterface.getStensilType(itemStack, "left"),
                            thighHighsInterface.getStensilType(itemStack, "right"),
                            thighHighsInterface.getItemLightValue(itemStack, "left", 1),
                            thighHighsInterface.getItemLightValue(itemStack, "left", 2),
                            thighHighsInterface.getItemLightValue(itemStack, "right", 1),
                            thighHighsInterface.getItemLightValue(itemStack, "right", 2),
                            material,
                            itemStack.isEnchanted(),
                            thighHighsInterface.getGlintColor(itemStack),
                            thighHighsInterface.getAdditionalData(itemStack)
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
