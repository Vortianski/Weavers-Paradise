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
import xox.labvorty.weaversparadise.items.ThighHighsCotton;
import xox.labvorty.weaversparadise.items.ThighHighsInterface;
import xox.labvorty.weaversparadise.items.ThighHighsSilk;
import xox.labvorty.weaversparadise.items.ThighHighsWool;
import xox.labvorty.weaversparadise.model.ThighHighsModel;
import xox.labvorty.weaversparadise.renderers.render_helper.ThighHighModelRenderer;
import xox.labvorty.weaversparadise.renderers.render_helper.ThighHighsRenderingData;

public class ThighHighsRenderer extends BlockEntityWithoutLevelRenderer {
    private final ThighHighsModel model;

    public ThighHighsRenderer(BlockEntityRenderDispatcher dispatcher, EntityModelSet entityModels) {
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

        if (!(stack.getItem() instanceof ThighHighsInterface thighHighsInterface)) return;

        if (stack.getItem() instanceof ThighHighsCotton thighHighsCotton) {
            material = "cotton";
        } else if (stack.getItem() instanceof ThighHighsSilk thighHighsSilk) {
            material = "silk";
        } else if (stack.getItem() instanceof ThighHighsWool thighHighsWool) {
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

        int primaryColorLeftOne = thighHighsInterface.getItemMainColor(stack, "left", 1);
        int secondaryColorLeftOne = thighHighsInterface.getItemSecondaryColor(stack, "left", 1);
        int primaryColorRightOne = thighHighsInterface.getItemMainColor(stack, "right", 1);
        int secondaryColorRightOne = thighHighsInterface.getItemSecondaryColor(stack, "right", 1);
        int primaryColorLeftTwo = thighHighsInterface.getItemMainColor(stack, "left", 2);
        int secondaryColorLeftTwo = thighHighsInterface.getItemSecondaryColor(stack, "left", 2);
        int primaryColorRightTwo = thighHighsInterface.getItemMainColor(stack, "right", 2);
        int secondaryColorRightTwo = thighHighsInterface.getItemSecondaryColor(stack, "right", 2);
        String dyeTypeLeftOne = thighHighsInterface.getItemDyeType(stack, "left", 1);
        String dyeTypeRightOne = thighHighsInterface.getItemDyeType(stack, "right", 1);
        String dyeTypeLeftTwo = thighHighsInterface.getItemDyeType(stack, "left", 2);
        String dyeTypeRightTwo = thighHighsInterface.getItemDyeType(stack, "right", 2);
        String stensilTypeLeft = thighHighsInterface.getStensilType(stack, "left");
        String stensilTypeRight = thighHighsInterface.getStensilType(stack, "right");
        int lightValueLeftOne = thighHighsInterface.getItemLightValue(stack, "left", 1);
        int lightValueLeftTwo = thighHighsInterface.getItemLightValue(stack, "left", 2);
        int lightValueRightOne = thighHighsInterface.getItemLightValue(stack, "right", 1);
        int lightValueRightTwo = thighHighsInterface.getItemLightValue(stack, "right", 2);

        Minecraft minecraft = Minecraft.getInstance();

        ThighHighModelRenderer modelRenderer = new ThighHighModelRenderer();

        modelRenderer.renderModel(
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
