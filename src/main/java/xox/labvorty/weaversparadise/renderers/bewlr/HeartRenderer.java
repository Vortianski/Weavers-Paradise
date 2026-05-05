package xox.labvorty.weaversparadise.renderers.bewlr;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import xox.labvorty.weaversparadise.items.clothing.HeartItem;
import xox.labvorty.weaversparadise.models.HeartModel;
import xox.labvorty.weaversparadise.renderers.helpers.ChokerTrinketRenderingData;
import xox.labvorty.weaversparadise.renderers.models.HeartModelRenderer;

public class HeartRenderer extends BlockEntityWithoutLevelRenderer {
    private final HeartModel bellModel;

    public HeartRenderer(BlockEntityRenderDispatcher dispatcher, EntityModelSet entityModels) {
        super(dispatcher, entityModels);
        this.bellModel = new HeartModel(entityModels.bakeLayer(HeartModel.LAYER_LOCATION));
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

        if (!(stack.getItem() instanceof HeartItem heartItem)) return;

        float scale = 1.0f;

        float xtranslation = -0.5f;
        float ytranslation = 0.85f;

        float additionalXrot = 0;
        float additionalYrot = 0;
        float additionalZrot = 0;

        switch (transformType) {
            case GUI -> {
                scale = 2.5f;
                xtranslation = -0.25f;
                ytranslation = 0.85f;
                additionalYrot = 45f;
                additionalXrot = 22.5f;
            }
            case THIRD_PERSON_LEFT_HAND, THIRD_PERSON_RIGHT_HAND -> {
                scale = 0.75f;
                ytranslation = 0.6f;
            }
            case FIRST_PERSON_LEFT_HAND, FIRST_PERSON_RIGHT_HAND -> {
                scale = 0.75f;
                ytranslation = 0.8f;
            }
            case FIXED -> {
                additionalYrot = 180f;
            }
            case GROUND -> {
                ytranslation = 0.5f;
                scale = 0.7f;
            }
        }



        Minecraft minecraft = Minecraft.getInstance();

        HeartModelRenderer heartModelRenderer = new HeartModelRenderer();
        CompoundTag compoundTag = stack.getOrCreateTag();

        heartModelRenderer.renderModel(
                buffer,
                bellModel,
                new ChokerTrinketRenderingData(
                        compoundTag.getInt("color"),
                        compoundTag.getString("metalType")
                ),
                minecraft.player,
                scale,
                -scale,
                scale,
                0,
                180,
                0,
                xtranslation,
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
