package xox.labvorty.weaversparadise.data;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import xox.labvorty.weaversparadise.curios.WeaversParadiseMobLayers;
import xox.labvorty.weaversparadise.items.*;
import xox.labvorty.weaversparadise.model.ModelUpperWear;
import xox.labvorty.weaversparadise.model.PantsModel;
import xox.labvorty.weaversparadise.renderers.render_helper.PantsModelRenderer;
import xox.labvorty.weaversparadise.renderers.render_helper.PantsRenderingData;
import xox.labvorty.weaversparadise.renderers.render_helper.ShirtModelRenderer;
import xox.labvorty.weaversparadise.renderers.render_helper.ShirtRenderingData;

public class PantsRenderer extends BlockEntityWithoutLevelRenderer {
    private final PantsModel model;

    public PantsRenderer(BlockEntityRenderDispatcher dispatcher, EntityModelSet entityModels) {
        super(dispatcher, entityModels);
        this.model = new PantsModel(entityModels.bakeLayer(WeaversParadiseMobLayers.PANTS));
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

        if (!(stack.getItem() instanceof PantsInterface pantsInterface)) return;

        if (stack.getItem() instanceof PantsJeans pantsJeans) {
            material = "jeans";
        } else if (stack.getItem() instanceof PantsCotton pantsCotton) {
            material = "cotton";
        } else if (stack.getItem() instanceof PantsSilk pantsSilk) {
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
                additionalYrot = 45f;
                additionalXrot = 22.5f;
            }
            case THIRD_PERSON_LEFT_HAND, THIRD_PERSON_RIGHT_HAND, FIRST_PERSON_LEFT_HAND, FIRST_PERSON_RIGHT_HAND -> {
                scale = 0.5f;
                ytranslation = 0.9f;
            }
            case FIXED -> {
                additionalYrot = 180f;
            }
            case GROUND -> {
                ytranslation = 1.25f;
                scale = 0.5f;
            }
        }

        int primaryColorOne = pantsInterface.getItemMainColor(stack, 1);
        int secondaryColorOne = pantsInterface.getItemSecondaryColor(stack, 1);
        int primaryColorTwo = pantsInterface.getItemMainColor(stack, 2);
        int secondaryColorTwo = pantsInterface.getItemSecondaryColor(stack, 2);
        String dyeTypeOne = pantsInterface.getItemDyeType(stack, 1);
        String dyeTypeTwo = pantsInterface.getItemDyeType(stack, 2);
        String stensilType = pantsInterface.getStensilType(stack);
        int lightValueOne = pantsInterface.getItemLightValue(stack, 1);
        int lightValueTwo = pantsInterface.getItemLightValue(stack, 2);
        Minecraft mc = Minecraft.getInstance();

        PantsModelRenderer pantsModelRenderer = new PantsModelRenderer();

        pantsModelRenderer.renderModel(
                buffer,
                model,
                new PantsRenderingData(
                        primaryColorOne,
                        secondaryColorOne,
                        primaryColorTwo,
                        secondaryColorTwo,
                        dyeTypeOne,
                        dyeTypeTwo,
                        stensilType,
                        lightValueOne,
                        lightValueTwo,
                        material
                ),
                mc.player,
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
