package xox.labvorty.weaversparadise.renderers.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import xox.labvorty.weaversparadise.configs.ClientConfig;
import xox.labvorty.weaversparadise.items.clothing.ThighHighsCottonItem;
import xox.labvorty.weaversparadise.items.clothing.ThighHighsSilkItem;
import xox.labvorty.weaversparadise.items.clothing.ThighHighsWoolItem;
import xox.labvorty.weaversparadise.items.clothing.defined.ThighHighsInterface;
import xox.labvorty.weaversparadise.model.ThighHighsModel;
import xox.labvorty.weaversparadise.renderers.helpers.DoubleSidedClothingRenderingData;
import xox.labvorty.weaversparadise.renderers.models.ThighHighsModelRenderer;
import xox.labvorty.weaversparadise.utilities.WeaversUtilities;

public class ThighHighsRenderLayer<T extends LivingEntity, M extends EntityModel<T>> extends RenderLayer<T, M> {
    private final ThighHighsModel<?> model;

    public ThighHighsRenderLayer(RenderLayerParent<T, M> parent) {
        super(parent);
        this.model = new ThighHighsModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(ThighHighsModel.LAYER_LOCATION));
    }

    @Override
    public void render(
            @NotNull PoseStack poseStack,
            @NotNull MultiBufferSource multiBufferSource,
            int packedLight,
            @NotNull T entity,
            float limbSwing,
            float limbSwingAmount,
            float partialTicks,
            float ageInTicks,
            float netHeadYaw,
            float headPitch
    ) {
        ItemStack itemStack = entity.getItemBySlot(EquipmentSlot.FEET);

        if (WeaversUtilities.isRestricted(entity, ClientConfig.THIGH_HIGHS_RESTRICTOR.get())) {
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

            EntityModel<?> entityModel = getParentModel();
            if (entityModel instanceof HumanoidModel<?> humanoidModel) {
                model.RightLeg.copyFrom(humanoidModel.rightLeg);
                model.LeftLeg.copyFrom(humanoidModel.leftLeg);
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