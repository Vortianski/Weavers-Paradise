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
import xox.labvorty.weaversparadise.items.clothing.HandWarmersCottonItem;
import xox.labvorty.weaversparadise.items.clothing.HandWarmersSilkItem;
import xox.labvorty.weaversparadise.items.clothing.HandWarmersWoolItem;
import xox.labvorty.weaversparadise.items.clothing.defined.HandWarmersInterface;
import xox.labvorty.weaversparadise.model.HandWarmersModel;
import xox.labvorty.weaversparadise.renderers.helpers.DoubleSidedClothingRenderingData;
import xox.labvorty.weaversparadise.renderers.models.HandWarmersModelRenderer;
import xox.labvorty.weaversparadise.utilities.WeaversUtilities;

public class HandWarmersRenderLayer<T extends LivingEntity, M extends EntityModel<T>> extends RenderLayer<T, M> {
    private final HandWarmersModel<?> model;

    public HandWarmersRenderLayer(RenderLayerParent<T, M> parent) {
        super(parent);
        this.model = new HandWarmersModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(HandWarmersModel.LAYER_LOCATION));
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
        ItemStack itemStack = entity.getItemBySlot(EquipmentSlot.CHEST);

        if (WeaversUtilities.isRestricted(entity, ClientConfig.HAND_WARMERS_RESTRICTOR.get(), EquipmentSlot.CHEST)) {
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

            EntityModel<?> entityModel = getParentModel();
            if (entityModel instanceof HumanoidModel<?> humanoidModel) {
                this.model.RightArm.copyFrom(humanoidModel.rightArm);
                this.model.LeftArm.copyFrom(humanoidModel.leftArm);
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
