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
import xox.labvorty.weaversparadise.items.clothing.ThighHighsCottonItem;
import xox.labvorty.weaversparadise.items.clothing.ThighHighsSilkItem;
import xox.labvorty.weaversparadise.items.clothing.ThighHighsWoolItem;
import xox.labvorty.weaversparadise.items.clothing.defined.ThighHighsInterface;
import xox.labvorty.weaversparadise.model.ThighHighsModel;
import xox.labvorty.weaversparadise.renderers.helpers.DoubleSidedClothingRenderingData;
import xox.labvorty.weaversparadise.renderers.models.ThighHighsModelRenderer;

public class ThighHighsRenderer extends BlockEntityWithoutLevelRenderer {
    private final ThighHighsModel<?> model;

    public ThighHighsRenderer(BlockEntityRenderDispatcher dispatcher, EntityModelSet entityModels) {
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

        if (!(stack.getItem() instanceof ThighHighsInterface thighHighsInterface)) return;

        if (stack.getItem() instanceof ThighHighsCottonItem thighHighsCotton) {
            material = "cotton";
        } else if (stack.getItem() instanceof ThighHighsSilkItem thighHighsSilk) {
            material = "silk";
        } else if (stack.getItem() instanceof ThighHighsWoolItem thighHighsWool) {
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

        ThighHighsModelRenderer.renderModel(
                buffer,
                model,
                new DoubleSidedClothingRenderingData(
                        thighHighsInterface.getFlag(stack),
                        thighHighsInterface.getItemMainColor(stack, "left", 1),
                        thighHighsInterface.getItemSecondaryColor(stack, "left", 1),
                        thighHighsInterface.getItemMainColor(stack, "right", 1),
                        thighHighsInterface.getItemSecondaryColor(stack, "right", 1),
                        thighHighsInterface.getItemMainColor(stack, "left", 2),
                        thighHighsInterface.getItemSecondaryColor(stack, "left", 2),
                        thighHighsInterface.getItemMainColor(stack, "right", 2),
                        thighHighsInterface.getItemSecondaryColor(stack, "right", 2),
                        thighHighsInterface.getItemDyeType(stack, "left", 1),
                        thighHighsInterface.getItemDyeType(stack, "right", 1),
                        thighHighsInterface.getItemDyeType(stack, "left", 2),
                        thighHighsInterface.getItemDyeType(stack, "right", 2),
                        thighHighsInterface.getStensilType(stack, "left"),
                        thighHighsInterface.getStensilType(stack, "right"),
                        thighHighsInterface.getItemLightValue(stack, "left", 1),
                        thighHighsInterface.getItemLightValue(stack, "left", 2),
                        thighHighsInterface.getItemLightValue(stack, "right", 1),
                        thighHighsInterface.getItemLightValue(stack, "right", 2),
                        material,
                        stack.isEnchanted(),
                        thighHighsInterface.getGlintColor(stack),
                        thighHighsInterface.getAdditionalData(stack)
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
