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
import xox.labvorty.weaversparadise.items.clothing.defined.DoubleSidedBlockItem;
import xox.labvorty.weaversparadise.model.BlahajModel;
import xox.labvorty.weaversparadise.renderers.helpers.DoubleSidedClothingRenderingData;
import xox.labvorty.weaversparadise.renderers.models.BlahajModelRenderer;

public class BlahajRenderer extends BlockEntityWithoutLevelRenderer {
    public final BlahajModel<?> blahajModel;

    public BlahajRenderer(BlockEntityRenderDispatcher blockEntityRenderDispatcher, EntityModelSet entityModelSet) {
        super(blockEntityRenderDispatcher, entityModelSet);
        this.blahajModel = new BlahajModel<>(entityModelSet.bakeLayer(BlahajModel.LAYER_LOCATION));
    }

    @Override
    public void renderByItem(
            @NotNull ItemStack itemStack,
            @NotNull ItemDisplayContext itemDisplayContext,
            @NotNull PoseStack poseStack,
            @NotNull MultiBufferSource multiBufferSource,
            int packedLight,
            int packedOverlay
    ) {
        if (!(itemStack.getItem() instanceof DoubleSidedBlockItem doubleSidedBlockItem)) return;

        float scaleX = 1.0f;
        float scaleY = 1.0f;
        float scaleZ = 1.0f;

        float xT = 0;
        float yT = 1.75f;
        float zT = 0;

        float preXRot = 0;
        float preYRot = 0;
        float preZRot = 0;

        float postXrot = 0;
        float postYrot = 0;
        float postZrot = 0;

        switch (itemDisplayContext) {
            case GUI -> {
                scaleX = 0.9f;
                scaleY = 0.9f;
                scaleZ = 0.9f;
                xT = 0.45f;
                yT = 1 + 0.7f;
                postXrot = 202.5f;
                postYrot = 45f;
            }
            case THIRD_PERSON_LEFT_HAND -> {
                xT = 0.6f;
                yT = 0.5f + 0.6f;
                zT = -0.5f;
                postXrot = 105;
                postYrot = 0;
                postZrot = 0;
            }
            case THIRD_PERSON_RIGHT_HAND -> {
                xT = 0.4f;
                yT = 0.5f + 0.6f;
                zT = -0.5f;
                postXrot = 105;
                postYrot = 0;
                postZrot = 0;
            }
            case FIRST_PERSON_RIGHT_HAND -> {
                xT = 0.6f;
                yT = 0.9f + 0.6f;
                zT = -0.5f;
                postXrot = 135f;
                postYrot = 0;
                postZrot = 0;
            }
            case FIRST_PERSON_LEFT_HAND -> {
                xT = 0.4f;
                yT = 0.9f + 0.6f;
                zT = -0.5f;
                postXrot = 135f;
                postYrot = 0;
                postZrot = 0;
            }
            case FIXED -> {
                xT = 0.5f;
                yT = 0.35f + 0.5f;
                zT = -1f;
                postXrot = 90f;
                postYrot = 0;
            }
            case GROUND -> {
                xT = 0.5f;
                yT = 1 + 1f;
                zT = 0.5f;
                postXrot = 180f;
            }
        }

        Minecraft minecraft = Minecraft.getInstance();

        BlahajModelRenderer.renderModel(
                multiBufferSource,
                blahajModel,
                new DoubleSidedClothingRenderingData(
                        doubleSidedBlockItem.getFlag(itemStack),
                        doubleSidedBlockItem.getItemMainColor(itemStack, "left", 1),
                        doubleSidedBlockItem.getItemSecondaryColor(itemStack, "left", 1),
                        doubleSidedBlockItem.getItemMainColor(itemStack, "right", 1),
                        doubleSidedBlockItem.getItemSecondaryColor(itemStack, "right", 1),
                        doubleSidedBlockItem.getItemMainColor(itemStack, "left", 2),
                        doubleSidedBlockItem.getItemSecondaryColor(itemStack, "left", 2),
                        doubleSidedBlockItem.getItemMainColor(itemStack, "right", 2),
                        doubleSidedBlockItem.getItemSecondaryColor(itemStack, "right", 2),
                        doubleSidedBlockItem.getItemDyeType(itemStack, "left", 1),
                        doubleSidedBlockItem.getItemDyeType(itemStack, "right", 1),
                        doubleSidedBlockItem.getItemDyeType(itemStack, "left", 2),
                        doubleSidedBlockItem.getItemDyeType(itemStack, "right", 2),
                        doubleSidedBlockItem.getStensilType(itemStack, "left"),
                        doubleSidedBlockItem.getStensilType(itemStack, "right"),
                        doubleSidedBlockItem.getItemLightValue(itemStack, "left", 1),
                        doubleSidedBlockItem.getItemLightValue(itemStack, "left", 2),
                        doubleSidedBlockItem.getItemLightValue(itemStack, "right", 1),
                        doubleSidedBlockItem.getItemLightValue(itemStack, "right", 2),
                        "default",
                        itemStack.isEnchanted(),
                        doubleSidedBlockItem.getGlintColor(itemStack),
                        doubleSidedBlockItem.getAdditionalData(itemStack)
                ),
                minecraft.player,
                scaleX,
                scaleY,
                scaleZ,
                preXRot,
                preYRot,
                preZRot,
                xT,
                yT,
                zT,
                postXrot,
                postYrot,
                postZrot,
                poseStack,
                packedLight
        );
    }
}
