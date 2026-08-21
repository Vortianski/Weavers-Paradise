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
import xox.labvorty.weaversparadise.items.clothing.SkirtCottonItem;
import xox.labvorty.weaversparadise.model.CottonSkirtModel;
import xox.labvorty.weaversparadise.renderers.helpers.DoubleSidedClothingRenderingData;
import xox.labvorty.weaversparadise.renderers.models.CottonSkirtModelRenderer;

public class CottonSkirtRenderer extends BlockEntityWithoutLevelRenderer {
    private final CottonSkirtModel<?> model;

    public CottonSkirtRenderer(BlockEntityRenderDispatcher dispatcher, EntityModelSet entityModels) {
        super(dispatcher, entityModels);
        this.model = new CottonSkirtModel<>(entityModels.bakeLayer(CottonSkirtModel.LAYER_LOCATION));
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
        String material = "base";

        if (!(stack.getItem() instanceof SkirtCottonItem skirtCottonItem)) return;

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

        Minecraft minecraft = Minecraft.getInstance();

        CottonSkirtModelRenderer.renderModel(
                buffer,
                model,
                new DoubleSidedClothingRenderingData(
                        skirtCottonItem.getFlag(stack),
                        skirtCottonItem.getItemMainColor(stack, "left", 1),
                        skirtCottonItem.getItemSecondaryColor(stack, "left", 1),
                        skirtCottonItem.getItemMainColor(stack, "right", 1),
                        skirtCottonItem.getItemSecondaryColor(stack, "right", 1),
                        skirtCottonItem.getItemMainColor(stack, "left", 2),
                        skirtCottonItem.getItemSecondaryColor(stack, "left", 2),
                        skirtCottonItem.getItemMainColor(stack, "right", 2),
                        skirtCottonItem.getItemSecondaryColor(stack, "right", 2),
                        skirtCottonItem.getItemDyeType(stack, "left", 1),
                        skirtCottonItem.getItemDyeType(stack, "right", 1),
                        skirtCottonItem.getItemDyeType(stack, "left", 2),
                        skirtCottonItem.getItemDyeType(stack, "right", 2),
                        skirtCottonItem.getStensilType(stack, "left"),
                        skirtCottonItem.getStensilType(stack, "right"),
                        skirtCottonItem.getItemLightValue(stack, "left", 1),
                        skirtCottonItem.getItemLightValue(stack, "left", 2),
                        skirtCottonItem.getItemLightValue(stack, "right", 1),
                        skirtCottonItem.getItemLightValue(stack, "right", 2),
                        material,
                        stack.isEnchanted(),
                        skirtCottonItem.getGlintColor(stack),
                        skirtCottonItem.getAdditionalData(stack)
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
