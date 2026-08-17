package xox.labvorty.weaversparadise.renderers.bewlr;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import org.jetbrains.annotations.NotNull;
import xox.labvorty.weaversparadise.items.clothing.RingItem;
import xox.labvorty.weaversparadise.model.BasicRingModel;
import xox.labvorty.weaversparadise.renderers.helpers.ChokerTrinketRenderingData;
import xox.labvorty.weaversparadise.renderers.models.RingModelRenderer;

public class RingRenderer extends BlockEntityWithoutLevelRenderer {
    private final BasicRingModel<?> bellModel;

    public RingRenderer(BlockEntityRenderDispatcher dispatcher, EntityModelSet entityModels) {
        super(dispatcher, entityModels);
        this.bellModel = new BasicRingModel<>(entityModels.bakeLayer(BasicRingModel.LAYER_LOCATION));
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
        if (!(stack.getItem() instanceof RingItem ringItem)) return;

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
                additionalYrot = -225f;
                additionalXrot = -22.5f;
            }
            case THIRD_PERSON_LEFT_HAND, THIRD_PERSON_RIGHT_HAND -> {
                scale = 0.75f;
                ytranslation = 0.6f;
                additionalYrot = 180f;
            }
            case FIRST_PERSON_LEFT_HAND, FIRST_PERSON_RIGHT_HAND -> {
                scale = 0.75f;
                ytranslation = 0.8f;
                additionalYrot = 180f;
            }
            case FIXED -> {
                additionalYrot = 0f;
            }
            case GROUND -> {
                ytranslation = 0.5f;
                scale = 0.7f;
            }
        }



        Minecraft minecraft = Minecraft.getInstance();

        RingModelRenderer ringModelRenderer = new RingModelRenderer();
        CompoundTag compoundTag = stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag();

        ringModelRenderer.renderModel(
                buffer,
                bellModel,
                new ChokerTrinketRenderingData(
                        compoundTag.getInt("color"),
                        compoundTag.getString("metalType")
                ),
                minecraft.player,
                scale,
                scale,
                scale,
                180,
                180,
                0,
                xtranslation,
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
