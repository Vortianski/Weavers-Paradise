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
import xox.labvorty.weaversparadise.items.clothing.PantsCottonItem;
import xox.labvorty.weaversparadise.items.clothing.PantsJeansItem;
import xox.labvorty.weaversparadise.items.clothing.PantsSilkItem;
import xox.labvorty.weaversparadise.items.clothing.defined.PantsInterface;
import xox.labvorty.weaversparadise.model.PantsModel;
import xox.labvorty.weaversparadise.renderers.helpers.SingleSidedClothingRenderingData;
import xox.labvorty.weaversparadise.renderers.models.PantsModelRenderer;

public class PantsRenderer extends BlockEntityWithoutLevelRenderer {
    private final PantsModel<?> model;

    public PantsRenderer(BlockEntityRenderDispatcher dispatcher, EntityModelSet entityModels) {
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

        if (!(stack.getItem() instanceof PantsInterface pantsInterface)) return;

        if (stack.getItem() instanceof PantsJeansItem pantsJeans) {
            material = "jeans";
        } else if (stack.getItem() instanceof PantsCottonItem pantsCotton) {
            material = "cotton";
        } else if (stack.getItem() instanceof PantsSilkItem pantsSilk) {
            material = "silk";
        } else {
            material = "jeans";
        }

        float scale = 1.0f;

        float ytranslation = 0.85f;

        float additionalXrot = 0;
        float additionalYrot = 0;
        float additionalZrot = 0;

        switch (transformType) {
            case GUI -> {
                scale = 0.9f;
                ytranslation = 1.3f;
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
                ytranslation = 1.25f;
                scale = 0.5f;
            }
        }

        PantsModelRenderer.renderModel(
                buffer,
                model,
                new SingleSidedClothingRenderingData(
                        pantsInterface.getFlag(stack),
                        pantsInterface.getItemMainColor(stack, 1),
                        pantsInterface.getItemSecondaryColor(stack, 1),
                        pantsInterface.getItemMainColor(stack, 2),
                        pantsInterface.getItemSecondaryColor(stack, 2),
                        pantsInterface.getItemDyeType(stack, 1),
                        pantsInterface.getItemDyeType(stack, 2),
                        pantsInterface.getStensilType(stack),
                        pantsInterface.getItemLightValue(stack, 1),
                        pantsInterface.getItemLightValue(stack, 2),
                        material,
                        stack.isEnchanted(),
                        pantsInterface.getGlintColor(stack),
                        pantsInterface.getAdditionalData(stack)
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
                packedLight
        );
    }
}
