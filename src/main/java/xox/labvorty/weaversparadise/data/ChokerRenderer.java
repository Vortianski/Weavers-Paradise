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
import xox.labvorty.weaversparadise.model.ModelChoker;
import xox.labvorty.weaversparadise.model.ModelUpperWear;
import xox.labvorty.weaversparadise.model.ThighHighsModel;
import xox.labvorty.weaversparadise.renderers.render_helper.ChokerModelRenderer;
import xox.labvorty.weaversparadise.renderers.render_helper.ChokerRenderingData;
import xox.labvorty.weaversparadise.renderers.render_helper.HandWarmersSpecialModelRenderer;
import xox.labvorty.weaversparadise.renderers.render_helper.ThighHighsRenderingData;

public class ChokerRenderer extends BlockEntityWithoutLevelRenderer {
    private final ModelChoker model;

    public ChokerRenderer(BlockEntityRenderDispatcher dispatcher, EntityModelSet entityModels) {
        super(dispatcher, entityModels);
        this.model = new ModelChoker(entityModels.bakeLayer(WeaversParadiseMobLayers.CHOKER));
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
                additionalYrot = 45f;
                additionalXrot = 22.5f;
            }
            case THIRD_PERSON_LEFT_HAND, THIRD_PERSON_RIGHT_HAND, FIRST_PERSON_LEFT_HAND, FIRST_PERSON_RIGHT_HAND -> {
                scale = 0.75f;
                ytranslation = 0.6f;
            }
            case FIXED -> {
                additionalYrot = 180f;
            }
            case GROUND -> {
                ytranslation = 0.25f;
                scale = 0.7f;
            }
        }

        int primaryColorLeftOne = chokerItem.getItemMainColor(stack, "left", 1);
        int secondaryColorLeftOne = chokerItem.getItemSecondaryColor(stack, "left", 1);
        int primaryColorRightOne = chokerItem.getItemMainColor(stack, "right", 1);
        int secondaryColorRightOne = chokerItem.getItemSecondaryColor(stack, "right", 1);
        int primaryColorLeftTwo = chokerItem.getItemMainColor(stack, "left", 2);
        int secondaryColorLeftTwo = chokerItem.getItemSecondaryColor(stack, "left", 2);
        int primaryColorRightTwo = chokerItem.getItemMainColor(stack, "right", 2);
        int secondaryColorRightTwo = chokerItem.getItemSecondaryColor(stack, "right", 2);
        String dyeTypeLeftOne = chokerItem.getItemDyeType(stack, "left", 1);
        String dyeTypeRightOne = chokerItem.getItemDyeType(stack, "right", 1);
        String dyeTypeLeftTwo = chokerItem.getItemDyeType(stack, "left", 2);
        String dyeTypeRightTwo = chokerItem.getItemDyeType(stack, "right", 2);
        String stensilTypeLeft = chokerItem.getStensilType(stack, "left");
        String stensilTypeRight = chokerItem.getStensilType(stack, "right");
        int lightValueLeftOne = chokerItem.getItemLightValue(stack, "left", 1);
        int lightValueLeftTwo = chokerItem.getItemLightValue(stack, "left", 2);
        int lightValueRightOne = chokerItem.getItemLightValue(stack, "right", 1);
        int lightValueRightTwo = chokerItem.getItemLightValue(stack, "right", 2);

        Minecraft minecraft = Minecraft.getInstance();

        ChokerModelRenderer chokerRenderer = new ChokerModelRenderer();

        chokerRenderer.renderModel(
                buffer,
                model,
                new ChokerRenderingData(
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
