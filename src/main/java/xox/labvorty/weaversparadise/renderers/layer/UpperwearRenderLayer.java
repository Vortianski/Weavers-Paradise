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
import xox.labvorty.weaversparadise.items.clothing.*;
import xox.labvorty.weaversparadise.items.clothing.defined.PulloverInterface;
import xox.labvorty.weaversparadise.items.clothing.defined.ShirtInterface;
import xox.labvorty.weaversparadise.items.clothing.defined.SingleSidedClothingItem;
import xox.labvorty.weaversparadise.items.clothing.defined.TopsInterface;
import xox.labvorty.weaversparadise.model.UpperWearModel;
import xox.labvorty.weaversparadise.renderers.helpers.SingleSidedClothingRenderingData;
import xox.labvorty.weaversparadise.renderers.models.UpperwearModelRenderer;
import xox.labvorty.weaversparadise.utilities.WeaversUtilities;

public class UpperwearRenderLayer<T extends LivingEntity, M extends EntityModel<T>> extends RenderLayer<T, M> {
    private final UpperWearModel<?> model;

    public UpperwearRenderLayer(RenderLayerParent<T, M> parent) {
        super(parent);
        this.model = new UpperWearModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(UpperWearModel.LAYER_LOCATION));
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

        if (!(itemStack.getItem() instanceof SingleSidedClothingItem singleSidedClothingItem)) return;

        if (WeaversUtilities.isRestricted(entity, ClientConfig.SHIRT_RESTRICTOR.get())) {
            return;
        }

        String material;
        UpperwearModelRenderer.Type type;
        int primaryColorOne;
        int secondaryColorOne;
        int primaryColorTwo;
        int secondaryColorTwo;
        String dyeTypeOne;
        String dyeTypeTwo;
        String stensilType;
        int lightValueOne;
        int lightValueTwo;

        if (itemStack.getItem() instanceof ShirtInterface shirtInterface) {
            switch (itemStack.getItem()) {
                case ShirtCottonItem shirtCottonItem -> {
                    material = "cotton";
                    type = UpperwearModelRenderer.Type.SHIRT;
                }
                case ShirtSilkItem shirtSilkItem -> {
                    material = "silk";
                    type = UpperwearModelRenderer.Type.SHIRT;
                }
                default -> {
                    return;
                }
            }

            primaryColorOne = shirtInterface.getItemMainColor(itemStack, 1);
            secondaryColorOne = shirtInterface.getItemSecondaryColor(itemStack, 1);
            primaryColorTwo = shirtInterface.getItemMainColor(itemStack, 2);
            secondaryColorTwo = shirtInterface.getItemSecondaryColor(itemStack, 2);
            dyeTypeOne = shirtInterface.getItemDyeType(itemStack, 1);
            dyeTypeTwo = shirtInterface.getItemDyeType(itemStack, 2);
            stensilType = shirtInterface.getStensilType(itemStack);
            lightValueOne = shirtInterface.getItemLightValue(itemStack, 1);
            lightValueTwo = shirtInterface.getItemLightValue(itemStack, 2);
        } else if (itemStack.getItem() instanceof PulloverInterface pulloverInterface) {
            switch (itemStack.getItem()) {
                case LongSleeveCottonItem longSleeveCottonItem -> {
                    material = "cotton";
                    type = UpperwearModelRenderer.Type.PULLOVER;
                }
                case SweaterWoolItem sweaterWoolItem -> {
                    material = "wool";
                    type = UpperwearModelRenderer.Type.PULLOVER;
                }
                default -> {
                    return;
                }
            }

            primaryColorOne = pulloverInterface.getItemMainColor(itemStack, 1);
            secondaryColorOne = pulloverInterface.getItemSecondaryColor(itemStack, 1);
            primaryColorTwo = pulloverInterface.getItemMainColor(itemStack, 2);
            secondaryColorTwo = pulloverInterface.getItemSecondaryColor(itemStack, 2);
            dyeTypeOne = pulloverInterface.getItemDyeType(itemStack, 1);
            dyeTypeTwo = pulloverInterface.getItemDyeType(itemStack, 2);
            stensilType = pulloverInterface.getStensilType(itemStack);
            lightValueOne = pulloverInterface.getItemLightValue(itemStack, 1);
            lightValueTwo = pulloverInterface.getItemLightValue(itemStack, 2);
        } else if (itemStack.getItem() instanceof TopsInterface topsInterface) {
            switch (itemStack.getItem()) {
                case TShirtItem tShirtItem -> {
                    material = "cotton";
                    type = UpperwearModelRenderer.Type.TOPS;
                }
                case TankTopItem tankTopItem -> {
                    material = "silk";
                    type = UpperwearModelRenderer.Type.TOPS;
                }
                case WoolVestItem woolVestItem -> {
                    material = "wool";
                    type = UpperwearModelRenderer.Type.TOPS;
                }
                default -> {
                    return;
                }
            }

            primaryColorOne = topsInterface.getItemMainColor(itemStack, 1);
            secondaryColorOne = topsInterface.getItemSecondaryColor(itemStack, 1);
            primaryColorTwo = topsInterface.getItemMainColor(itemStack, 2);
            secondaryColorTwo = topsInterface.getItemSecondaryColor(itemStack, 2);
            dyeTypeOne = topsInterface.getItemDyeType(itemStack, 1);
            dyeTypeTwo = topsInterface.getItemDyeType(itemStack, 2);
            stensilType = topsInterface.getStensilType(itemStack);
            lightValueOne = topsInterface.getItemLightValue(itemStack, 1);
            lightValueTwo = topsInterface.getItemLightValue(itemStack, 2);
        } else if (itemStack.getItem() instanceof PonchoItem ponchoItem) {
            material = "cotton";
            type = UpperwearModelRenderer.Type.PONCHO;

            primaryColorOne = ponchoItem.getItemMainColor(itemStack, 1);
            secondaryColorOne = ponchoItem.getItemSecondaryColor(itemStack, 1);
            primaryColorTwo = ponchoItem.getItemMainColor(itemStack, 2);
            secondaryColorTwo = ponchoItem.getItemSecondaryColor(itemStack, 2);
            dyeTypeOne = ponchoItem.getItemDyeType(itemStack, 1);
            dyeTypeTwo = ponchoItem.getItemDyeType(itemStack, 2);
            stensilType = ponchoItem.getStensilType(itemStack);
            lightValueOne = ponchoItem.getItemLightValue(itemStack, 1);
            lightValueTwo = ponchoItem.getItemLightValue(itemStack, 2);
        } else if (itemStack.getItem() instanceof CottonCropTopLongSleevedItem cottonCropTopLongSleevedItem) {
            material = "cotton";
            type = UpperwearModelRenderer.Type.CROP_TOP_LONG_SLEEVED;

            primaryColorOne = cottonCropTopLongSleevedItem.getItemMainColor(itemStack, 1);
            secondaryColorOne = cottonCropTopLongSleevedItem.getItemSecondaryColor(itemStack, 1);
            primaryColorTwo = cottonCropTopLongSleevedItem.getItemMainColor(itemStack, 2);
            secondaryColorTwo = cottonCropTopLongSleevedItem.getItemSecondaryColor(itemStack, 2);
            dyeTypeOne = cottonCropTopLongSleevedItem.getItemDyeType(itemStack, 1);
            dyeTypeTwo = cottonCropTopLongSleevedItem.getItemDyeType(itemStack, 2);
            stensilType = cottonCropTopLongSleevedItem.getStensilType(itemStack);
            lightValueOne = cottonCropTopLongSleevedItem.getItemLightValue(itemStack, 1);
            lightValueTwo = cottonCropTopLongSleevedItem.getItemLightValue(itemStack, 2);
        } else {
            return;
        }

        EntityModel<?> entityModel = getParentModel();
        if (entityModel instanceof HumanoidModel<?> humanoidModel) {
            this.model.Body.copyFrom(humanoidModel.body);
            this.model.LeftArm.copyFrom(humanoidModel.leftArm);
            this.model.RightArm.copyFrom(humanoidModel.rightArm);
        }

        UpperwearModelRenderer.renderModel(
                multiBufferSource,
                model,
                new SingleSidedClothingRenderingData(
                        singleSidedClothingItem.getFlag(itemStack),
                        primaryColorOne,
                        secondaryColorOne,
                        primaryColorTwo,
                        secondaryColorTwo,
                        dyeTypeOne,
                        dyeTypeTwo,
                        stensilType,
                        lightValueOne,
                        lightValueTwo,
                        material,
                        itemStack.isEnchanted(),
                        singleSidedClothingItem.getGlintColor(itemStack),
                        singleSidedClothingItem.getAdditionalData(itemStack)
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
                packedLight,
                type
        );
    }
}
