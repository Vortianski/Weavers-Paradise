package xox.labvorty.weaversparadise.data;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import xox.labvorty.weaversparadise.curios.WeaversParadiseMobLayers;
import xox.labvorty.weaversparadise.items.*;
import xox.labvorty.weaversparadise.model.ThighHighsModel;
import xox.labvorty.weaversparadise.renderers.render_helper.HandWarmersSpecialModelRenderer;
import xox.labvorty.weaversparadise.renderers.render_helper.ThighHighsRenderingData;

public class HandWarmersRenderer extends BlockEntityWithoutLevelRenderer {
    private final ThighHighsModel model;

    public HandWarmersRenderer(BlockEntityRenderDispatcher dispatcher, EntityModelSet entityModels) {
        super(dispatcher, entityModels);
        this.model = new ThighHighsModel(entityModels.bakeLayer(WeaversParadiseMobLayers.THIGH_HIGHS));
    }

        @Override
        public void renderByItem(
                ItemStack stack,
                ItemDisplayContext transformType,
                PoseStack poseStack,
                MultiBufferSource buffer,
                int packedLight,
                int packedOverlay
        ) {
            String material;

            if (!(stack.getItem() instanceof HandWarmersInterface handWarmersInterface)) return;

            if (stack.getItem() instanceof HandWarmersCotton handWarmersCotton) {
                material = "cotton";
            } else if (stack.getItem() instanceof HandWarmersSilk handWarmersSilk) {
                material = "silk";
            } else if (stack.getItem() instanceof HandWarmersWool handWarmersWool) {
                material = "wool";
            } else {
                material = "cotton";
            }

            float scale = 1.0f;

            float ytranslation = 1.75f;

            float additionalXrot = 0;
            float additionalYrot = 0;
            float additionalZrot = 0;

            switch (transformType) {
                case GUI -> {
                    scale = 1.0f;
                    ytranslation = 1.65f;
                    additionalYrot = 45f;
                    additionalXrot = 22.5f;
                }
                case THIRD_PERSON_LEFT_HAND, THIRD_PERSON_RIGHT_HAND, FIRST_PERSON_LEFT_HAND, FIRST_PERSON_RIGHT_HAND -> {
                    scale = 0.5f;
                    ytranslation = 1.25f;
                }
                case FIXED -> {
                    additionalYrot = 180f;
                }
                case GROUND -> {
                    scale = 0.5f;
                    ytranslation = 1.15f;
                }
            }

            int primaryColorLeftOne = handWarmersInterface.getItemMainColor(stack, "left", 1);
            int secondaryColorLeftOne = handWarmersInterface.getItemSecondaryColor(stack, "left", 1);
            int primaryColorRightOne = handWarmersInterface.getItemMainColor(stack, "right", 1);
            int secondaryColorRightOne = handWarmersInterface.getItemSecondaryColor(stack, "right", 1);
            int primaryColorLeftTwo = handWarmersInterface.getItemMainColor(stack, "left", 2);
            int secondaryColorLeftTwo = handWarmersInterface.getItemSecondaryColor(stack, "left", 2);
            int primaryColorRightTwo = handWarmersInterface.getItemMainColor(stack, "right", 2);
            int secondaryColorRightTwo = handWarmersInterface.getItemSecondaryColor(stack, "right", 2);
            String dyeTypeLeftOne = handWarmersInterface.getItemDyeType(stack, "left", 1);
            String dyeTypeRightOne = handWarmersInterface.getItemDyeType(stack, "right", 1);
            String dyeTypeLeftTwo = handWarmersInterface.getItemDyeType(stack, "left", 2);
            String dyeTypeRightTwo = handWarmersInterface.getItemDyeType(stack, "right", 2);
            String stensilTypeLeft = handWarmersInterface.getStensilType(stack, "left");
            String stensilTypeRight = handWarmersInterface.getStensilType(stack, "right");
            int lightValueLeftOne = handWarmersInterface.getItemLightValue(stack, "left", 1);
            int lightValueLeftTwo = handWarmersInterface.getItemLightValue(stack, "left", 2);
            int lightValueRightOne = handWarmersInterface.getItemLightValue(stack, "right", 1);
            int lightValueRightTwo = handWarmersInterface.getItemLightValue(stack, "right", 2);

            Minecraft minecraft = Minecraft.getInstance();

            HandWarmersSpecialModelRenderer handWarmersSpecialModelRenderer = new HandWarmersSpecialModelRenderer();

            handWarmersSpecialModelRenderer.renderModel(
                    buffer,
                    model,
                    new ThighHighsRenderingData(
                            primaryColorLeftOne,
                            secondaryColorLeftOne,
                            primaryColorRightOne,
                            secondaryColorRightOne,
                            primaryColorLeftTwo,
                            secondaryColorLeftTwo,
                            primaryColorRightTwo,
                            secondaryColorRightTwo,
                            dyeTypeLeftOne,
                            dyeTypeRightOne,
                            dyeTypeLeftTwo,
                            dyeTypeRightTwo,
                            stensilTypeLeft,
                            stensilTypeRight,
                            lightValueLeftOne,
                            lightValueLeftTwo,
                            lightValueRightOne,
                            lightValueRightTwo,
                            material
                    ),
                    minecraft.player,
                    scale,
                    -scale,
                    scale,
                    0,
                    180,
                    0,
                    -0.5f,
                    ytranslation,
                    -0.5f,
                    additionalXrot,
                    additionalYrot,
                    additionalZrot,
                    poseStack,
                    packedLight
            );
        }
}
