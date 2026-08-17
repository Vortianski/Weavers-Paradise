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
import xox.labvorty.weaversparadise.items.clothing.PomponHatItem;
import xox.labvorty.weaversparadise.model.PomponHatModel;
import xox.labvorty.weaversparadise.renderers.helpers.SingleSidedClothingRenderingData;
import xox.labvorty.weaversparadise.renderers.models.PomponHatModelRenderer;

public class PomponHatRenderer extends BlockEntityWithoutLevelRenderer {
    private final PomponHatModel<?> model;

    public PomponHatRenderer(BlockEntityRenderDispatcher dispatcher, EntityModelSet entityModels) {
        super(dispatcher, entityModels);
        this.model = new PomponHatModel<>(entityModels.bakeLayer(PomponHatModel.LAYER_LOCATION));
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
        String material = "cotton";

        if (!(stack.getItem() instanceof PomponHatItem pomponHatItem)) return;

        float scale = 1.0f;

        float ytranslation = 0.85f;

        float additionalXrot = 0;
        float additionalYrot = 0;
        float additionalZrot = 0;

        switch (transformType) {
            case GUI -> {
                scale = 0.9f;
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

        PomponHatModelRenderer.renderModel(
                buffer,
                model,
                new SingleSidedClothingRenderingData(
                        pomponHatItem.getFlag(stack),
                        pomponHatItem.getItemMainColor(stack, 1),
                        pomponHatItem.getItemSecondaryColor(stack, 1),
                        pomponHatItem.getItemMainColor(stack, 2),
                        pomponHatItem.getItemSecondaryColor(stack, 2),
                        pomponHatItem.getItemDyeType(stack, 1),
                        pomponHatItem.getItemDyeType(stack, 2),
                        pomponHatItem.getStensilType(stack),
                        pomponHatItem.getItemLightValue(stack, 1),
                        pomponHatItem.getItemLightValue(stack, 2),
                        material,
                        stack.isEnchanted(),
                        pomponHatItem.getGlintColor(stack),
                        pomponHatItem.getAdditionalData(stack)
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
