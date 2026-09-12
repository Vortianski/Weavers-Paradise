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
import xox.labvorty.weaversparadise.items.clothing.UshankaItem;
import xox.labvorty.weaversparadise.model.UshankaModel;
import xox.labvorty.weaversparadise.renderers.helpers.DoubleSidedClothingRenderingData;
import xox.labvorty.weaversparadise.renderers.models.UshankaModelRenderer;

public class UshankaRenderer extends BlockEntityWithoutLevelRenderer {
    private final UshankaModel<?> model;

    public UshankaRenderer(BlockEntityRenderDispatcher dispatcher, EntityModelSet entityModels) {
        super(dispatcher, entityModels);
        this.model = new UshankaModel<>(entityModels.bakeLayer(UshankaModel.LAYER_LOCATION));
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

        if (!(stack.getItem() instanceof UshankaItem ushankaItem)) return;

        float scale = 1.0f;

        float ytranslation = 0.85f;

        float additionalXrot = 0;
        float additionalYrot = 0;
        float additionalZrot = 0;

        switch (transformType) {
            case GUI -> {
                ytranslation = 0.25f;
                additionalYrot = -225f;
                additionalXrot = -22.5f;
            }
            case THIRD_PERSON_LEFT_HAND, THIRD_PERSON_RIGHT_HAND, FIRST_PERSON_LEFT_HAND, FIRST_PERSON_RIGHT_HAND -> {
                scale = 0.75f;
                ytranslation = 0.1f;
                additionalYrot = 180f;
            }
            case FIXED -> {
                ytranslation = 0.5f;
                additionalYrot = 0f;
            }
            case GROUND -> {
                ytranslation = 0.25f;
                scale = 0.7f;
            }
            case HEAD -> {
                scale = 0;
            }
        }

        Minecraft minecraft = Minecraft.getInstance();

        UshankaModelRenderer.renderModel(
                buffer,
                model,
                new DoubleSidedClothingRenderingData(
                        ushankaItem.getFlag(stack),
                        ushankaItem.getItemMainColor(stack, "left", 1),
                        ushankaItem.getItemSecondaryColor(stack, "left", 1),
                        ushankaItem.getItemMainColor(stack, "right", 1),
                        ushankaItem.getItemSecondaryColor(stack, "right", 1),
                        ushankaItem.getItemMainColor(stack, "left", 2),
                        ushankaItem.getItemSecondaryColor(stack, "left", 2),
                        ushankaItem.getItemMainColor(stack, "right", 2),
                        ushankaItem.getItemSecondaryColor(stack, "right", 2),
                        ushankaItem.getItemDyeType(stack, "left", 1),
                        ushankaItem.getItemDyeType(stack, "right", 1),
                        ushankaItem.getItemDyeType(stack, "left", 2),
                        ushankaItem.getItemDyeType(stack, "right", 2),
                        ushankaItem.getStensilType(stack, "left"),
                        ushankaItem.getStensilType(stack, "right"),
                        ushankaItem.getItemLightValue(stack, "left", 1),
                        ushankaItem.getItemLightValue(stack, "left", 2),
                        ushankaItem.getItemLightValue(stack, "right", 1),
                        ushankaItem.getItemLightValue(stack, "right", 2),
                        material,
                        stack.isEnchanted(),
                        ushankaItem.getGlintColor(stack),
                        ushankaItem.getAdditionalData(stack)
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
