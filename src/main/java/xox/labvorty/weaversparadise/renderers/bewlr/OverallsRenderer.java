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
import xox.labvorty.weaversparadise.items.clothing.OverallsItem;
import xox.labvorty.weaversparadise.items.clothing.defined.OverallsInterface;
import xox.labvorty.weaversparadise.model.PantsModel;
import xox.labvorty.weaversparadise.renderers.helpers.SingleSidedClothingRenderingData;
import xox.labvorty.weaversparadise.renderers.models.PantsModelRenderer;

public class OverallsRenderer extends BlockEntityWithoutLevelRenderer {
    private final PantsModel<?> model;

    public OverallsRenderer(BlockEntityRenderDispatcher dispatcher, EntityModelSet entityModels) {
        super(dispatcher, entityModels);
        this.model = new PantsModel<>(entityModels.bakeLayer(PantsModel.LAYER_LOCATION));
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

        if (!(stack.getItem() instanceof OverallsInterface overallsInterface)) return;

        if (stack.getItem() instanceof OverallsItem overallsItem) {
            material = "cotton";
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
                scale = 0.75f;
                ytranslation = 1f;
                additionalYrot = -225f;
                additionalXrot = -22.5f;
            }
            case THIRD_PERSON_LEFT_HAND, THIRD_PERSON_RIGHT_HAND, FIRST_PERSON_LEFT_HAND, FIRST_PERSON_RIGHT_HAND -> {
                scale = 0.5f;
                ytranslation = 0.9f;
                additionalYrot = 180f;
            }
            case FIXED -> {
                ytranslation = 1;
                additionalYrot = 0f;
            }
            case GROUND -> {
                ytranslation = 1.25f;
                scale = 0.5f;
            }
        }

        PantsModelRenderer.renderModel(
                buffer,
                model,
                new SingleSidedClothingRenderingData(
                        overallsInterface.getFlag(stack),
                        overallsInterface.getItemMainColor(stack, 1),
                        overallsInterface.getItemSecondaryColor(stack, 1),
                        overallsInterface.getItemMainColor(stack, 2),
                        overallsInterface.getItemSecondaryColor(stack, 2),
                        overallsInterface.getItemDyeType(stack, 1),
                        overallsInterface.getItemDyeType(stack, 2),
                        overallsInterface.getStensilType(stack),
                        overallsInterface.getItemLightValue(stack, 1),
                        overallsInterface.getItemLightValue(stack, 2),
                        material,
                        stack.isEnchanted(),
                        overallsInterface.getGlintColor(stack),
                        overallsInterface.getAdditionalData(stack)
                ),
                Minecraft.getInstance().player,
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
                PantsModelRenderer.Type.OVERALLS
        );
    }
}
