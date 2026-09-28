package xox.labvorty.weaversparadise.renderers.bewlr;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import xox.labvorty.weaversparadise.items.clothing.*;
import xox.labvorty.weaversparadise.items.clothing.defined.PulloverInterface;
import xox.labvorty.weaversparadise.items.clothing.defined.ShirtInterface;
import xox.labvorty.weaversparadise.items.clothing.defined.SingleSidedClothingItem;
import xox.labvorty.weaversparadise.items.clothing.defined.TopsInterface;
import xox.labvorty.weaversparadise.model.UpperWearModel;
import xox.labvorty.weaversparadise.renderers.helpers.SingleSidedClothingRenderingData;
import xox.labvorty.weaversparadise.renderers.models.UpperwearModelRenderer;

public class UpperwearRenderer extends BlockEntityWithoutLevelRenderer {
    private final UpperWearModel<?> model;

    public UpperwearRenderer(BlockEntityRenderDispatcher dispatcher, EntityModelSet entityModels) {
        super(dispatcher, entityModels);
        this.model = new UpperWearModel<>(entityModels.bakeLayer(UpperWearModel.LAYER_LOCATION));
    }

    @Override
    public void renderByItem(
            @NotNull ItemStack itemStack,
            @NotNull ItemDisplayContext transformType,
            @NotNull PoseStack poseStack,
            @NotNull MultiBufferSource buffer,
            int packedLight,
            int packedOverlay
    ) {
        if (!(itemStack.getItem() instanceof SingleSidedClothingItem singleSidedClothingItem)) return;

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
            if (itemStack.getItem() instanceof ShirtCottonItem) {
                material = "cotton";
                type = UpperwearModelRenderer.Type.SHIRT;
            } else if (itemStack.getItem() instanceof ShirtSilkItem) {
                material = "silk";
                type = UpperwearModelRenderer.Type.SHIRT;
            } else {
                return;
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
            if (itemStack.getItem() instanceof LongSleeveCottonItem) {
                material = "cotton";
                type = UpperwearModelRenderer.Type.PULLOVER;
            } else if (itemStack.getItem() instanceof SweaterWoolItem) {
                material = "wool";
                type = UpperwearModelRenderer.Type.PULLOVER;
            } else {
                return;
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
            if (itemStack.getItem() instanceof TShirtItem) {
                material = "cotton";
                type = UpperwearModelRenderer.Type.TOPS;
            } else if (itemStack.getItem() instanceof TankTopItem) {
                material = "silk";
                type = UpperwearModelRenderer.Type.TOPS;
            } else if (itemStack.getItem() instanceof WoolVestItem) {
                material = "wool";
                type = UpperwearModelRenderer.Type.TOPS;
            } else {
                return;
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

        float scale = 1.0f;

        float ytranslation = 0.85f;

        float additionalXrot = 0;
        float additionalYrot = 0;
        float additionalZrot = 0;

        switch (transformType) {
            case GUI -> {
                scale = 0.9f;
                ytranslation = 0.8f;
                additionalYrot = -225f;
                additionalXrot = -22.5f;
            }
            case THIRD_PERSON_LEFT_HAND, THIRD_PERSON_RIGHT_HAND, FIRST_PERSON_LEFT_HAND, FIRST_PERSON_RIGHT_HAND -> {
                scale = 0.5f;
                ytranslation = 0.9f;
                additionalYrot = 180f;
            }
            case FIXED -> {
                additionalYrot = 0f;
            }
            case GROUND -> {
                ytranslation = 0.75f;
                scale = 0.5f;
            }
        }

        Minecraft mc = Minecraft.getInstance();

        UpperwearModelRenderer.renderModel(
                buffer,
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
                mc.player,
                scale,
                scale,
                scale,
                180,
                180,
                0,
                -0.5f,
                -ytranslation,
                0.5f,
                additionalXrot,
                additionalYrot,
                additionalZrot,
                poseStack,
                packedLight,
                type
        );
    }
}
