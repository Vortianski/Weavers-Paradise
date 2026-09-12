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
import xox.labvorty.weaversparadise.items.clothing.ChokerItem;
import xox.labvorty.weaversparadise.model.ChokerModel;
import xox.labvorty.weaversparadise.renderers.helpers.DoubleSidedClothingRenderingData;
import xox.labvorty.weaversparadise.renderers.models.ChokerModelRenderer;

public class ChokerRenderer extends BlockEntityWithoutLevelRenderer {
    private final ChokerModel<?> model;

    public ChokerRenderer(BlockEntityRenderDispatcher dispatcher, EntityModelSet entityModels) {
        super(dispatcher, entityModels);
        this.model = new ChokerModel<>(entityModels.bakeLayer(ChokerModel.LAYER_LOCATION));
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

        if (!(stack.getItem() instanceof ChokerItem chokerItem)) return;

        float scale = 1.0f;

        float ytranslation = 0.85f;

        float additionalXrot = 0;
        float additionalYrot = 0;
        float additionalZrot = 0;

        switch (transformType) {
            case GUI -> {
                scale = 1.2f;
                ytranslation = 0.5f;
                additionalYrot = -225f;
                additionalXrot = -22.5f;
            }
            case THIRD_PERSON_LEFT_HAND, THIRD_PERSON_RIGHT_HAND, FIRST_PERSON_LEFT_HAND, FIRST_PERSON_RIGHT_HAND -> {
                scale = 0.75f;
                ytranslation = 0.6f;
                additionalYrot = 180f;
            }
            case FIXED -> {
                additionalYrot = -0f;
            }
            case GROUND -> {
                ytranslation = 0.25f;
                scale = 0.7f;
            }
        }

        Minecraft minecraft = Minecraft.getInstance();

        ChokerModelRenderer.renderModel(
                buffer,
                model,
                new DoubleSidedClothingRenderingData(
                        chokerItem.getFlag(stack),
                        chokerItem.getItemMainColor(stack, "left", 1),
                        chokerItem.getItemSecondaryColor(stack, "left", 1),
                        chokerItem.getItemMainColor(stack, "right", 1),
                        chokerItem.getItemSecondaryColor(stack, "right", 1),
                        chokerItem.getItemMainColor(stack, "left", 2),
                        chokerItem.getItemSecondaryColor(stack, "left", 2),
                        chokerItem.getItemMainColor(stack, "right", 2),
                        chokerItem.getItemSecondaryColor(stack, "right", 2),
                        chokerItem.getItemDyeType(stack, "left", 1),
                        chokerItem.getItemDyeType(stack, "right", 1),
                        chokerItem.getItemDyeType(stack, "left", 2),
                        chokerItem.getItemDyeType(stack, "right", 2),
                        chokerItem.getStensilType(stack, "left"),
                        chokerItem.getStensilType(stack, "right"),
                        chokerItem.getItemLightValue(stack, "left", 1),
                        chokerItem.getItemLightValue(stack, "left", 2),
                        chokerItem.getItemLightValue(stack, "right", 1),
                        chokerItem.getItemLightValue(stack, "right", 2),
                        material,
                        stack.isEnchanted(),
                        chokerItem.getGlintColor(stack),
                        chokerItem.getAdditionalData(stack)
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
