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
import xox.labvorty.weaversparadise.renderers.render_helper.ShirtModelRenderer;
import xox.labvorty.weaversparadise.renderers.render_helper.ShirtRenderingData;

public class ShirtRenderer extends BlockEntityWithoutLevelRenderer {
    private final ModelUpperWear model;

    public ShirtRenderer(BlockEntityRenderDispatcher dispatcher, EntityModelSet entityModels) {
        super(dispatcher, entityModels);
        this.model = new ModelUpperWear(entityModels.bakeLayer(WeaversParadiseMobLayers.UPPER_WEAR));
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

        if (!(stack.getItem() instanceof ShirtInterface shirtInterface)) return;

        if (stack.getItem() instanceof ShirtCotton shirtCotton) {
            material = "cotton";
        } else if (stack.getItem() instanceof ShirtSilk shirtSilk) {
            material = "silk";
        } else if (stack.getItem() instanceof SweaterWool sweaterWool) {
            material = "wool";
        } else {
            material = "cotton";
        }

        float scale = 1.0f;

        float ytranslation = 0.85f;

        float additionalXrot = 0;
        float additionalYrot = 0;
        float additionalZrot = 0;

        switch (transformType) {
            case GUI -> {
                scale = 0.9f;
                ytranslation = 0.8f;
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
                ytranslation = 0.75f;
                scale = 0.5f;
            }
        }

        int primaryColorOne = shirtInterface.getItemMainColor(stack, 1);
        int secondaryColorOne = shirtInterface.getItemSecondaryColor(stack, 1);
        int primaryColorTwo = shirtInterface.getItemMainColor(stack, 2);
        int secondaryColorTwo = shirtInterface.getItemSecondaryColor(stack, 2);
        String dyeTypeOne = shirtInterface.getItemDyeType(stack, 1);
        String dyeTypeTwo = shirtInterface.getItemDyeType(stack, 2);
        String stensilType = shirtInterface.getStensilType(stack);
        int lightValueOne = shirtInterface.getItemLightValue(stack, 1);
        int lightValueTwo = shirtInterface.getItemLightValue(stack, 2);
        Minecraft mc = Minecraft.getInstance();

        ShirtModelRenderer shirtModelRenderer = new ShirtModelRenderer();

        shirtModelRenderer.renderModel(
                buffer,
                model,
                new ShirtRenderingData(
                        stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getBoolean("is_open"),
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
