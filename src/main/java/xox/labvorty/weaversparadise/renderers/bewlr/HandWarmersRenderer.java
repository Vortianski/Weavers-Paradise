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
import xox.labvorty.weaversparadise.items.clothing.HandWarmersCottonItem;
import xox.labvorty.weaversparadise.items.clothing.HandWarmersSilkItem;
import xox.labvorty.weaversparadise.items.clothing.HandWarmersWoolItem;
import xox.labvorty.weaversparadise.items.clothing.defined.HandWarmersInterface;
import xox.labvorty.weaversparadise.model.ThighHighsModel;
import xox.labvorty.weaversparadise.renderers.helpers.DoubleSidedClothingRenderingData;
import xox.labvorty.weaversparadise.renderers.models.HandWarmersGUIModelRenderer;

public class HandWarmersRenderer extends BlockEntityWithoutLevelRenderer {
    private final ThighHighsModel<?> model;

    public HandWarmersRenderer(BlockEntityRenderDispatcher dispatcher, EntityModelSet entityModels) {
        super(dispatcher, entityModels);
        this.model = new ThighHighsModel<>(entityModels.bakeLayer(ThighHighsModel.LAYER_LOCATION));
    }

        @Override
        public void renderByItem(
                ItemStack stack,
                @NotNull ItemDisplayContext transformType,
                @NotNull PoseStack poseStack,
                @NotNull MultiBufferSource buffer,
                int packedLight,
                int packedOverlay
        ) {
            String material;

            if (!(stack.getItem() instanceof HandWarmersInterface handWarmersInterface)) return;

            if (stack.getItem() instanceof HandWarmersCottonItem handWarmersCottonItem) {
                material = "cotton";
            } else if (stack.getItem() instanceof HandWarmersSilkItem handWarmersSilk) {
                material = "silk";
            } else if (stack.getItem() instanceof HandWarmersWoolItem handWarmersWool) {
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
                    additionalYrot = -225f;
                    additionalXrot = -22.5f;
                }
                case THIRD_PERSON_LEFT_HAND, THIRD_PERSON_RIGHT_HAND, FIRST_PERSON_LEFT_HAND, FIRST_PERSON_RIGHT_HAND -> {
                    scale = 0.5f;
                    ytranslation = 1.25f;
                    additionalYrot = 180f;
                }
                case FIXED -> {
                    additionalYrot = 0f;
                }
                case GROUND -> {
                    scale = 0.5f;
                    ytranslation = 1.15f;
                }
            }

            Minecraft minecraft = Minecraft.getInstance();

            HandWarmersGUIModelRenderer.renderModel(
                    buffer,
                    model,
                    new DoubleSidedClothingRenderingData(
                            handWarmersInterface.getFlag(stack),
                            handWarmersInterface.getItemMainColor(stack, "left", 1),
                            handWarmersInterface.getItemSecondaryColor(stack, "left", 1),
                            handWarmersInterface.getItemMainColor(stack, "right", 1),
                            handWarmersInterface.getItemSecondaryColor(stack, "right", 1),
                            handWarmersInterface.getItemMainColor(stack, "left", 2),
                            handWarmersInterface.getItemSecondaryColor(stack, "left", 2),
                            handWarmersInterface.getItemMainColor(stack, "right", 2),
                            handWarmersInterface.getItemSecondaryColor(stack, "right", 2),
                            handWarmersInterface.getItemDyeType(stack, "left", 1),
                            handWarmersInterface.getItemDyeType(stack, "right", 1),
                            handWarmersInterface.getItemDyeType(stack, "left", 2),
                            handWarmersInterface.getItemDyeType(stack, "right", 2),
                            handWarmersInterface.getStensilType(stack, "left"),
                            handWarmersInterface.getStensilType(stack, "right"),
                            handWarmersInterface.getItemLightValue(stack, "left", 1),
                            handWarmersInterface.getItemLightValue(stack, "left", 2),
                            handWarmersInterface.getItemLightValue(stack, "right", 1),
                            handWarmersInterface.getItemLightValue(stack, "right", 2),
                            material,
                            stack.isEnchanted(),
                            handWarmersInterface.getGlintColor(stack),
                            handWarmersInterface.getAdditionalData(stack)
                    ),
                    minecraft.player,
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
                    packedLight
            );
        }
}
