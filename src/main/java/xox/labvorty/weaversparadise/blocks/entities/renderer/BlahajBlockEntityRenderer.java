package xox.labvorty.weaversparadise.blocks.entities.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import xox.labvorty.weaversparadise.blocks.BlahajBlock;
import xox.labvorty.weaversparadise.blocks.entities.BlahajBlockEntity;
import xox.labvorty.weaversparadise.items.clothing.defined.DoubleSidedBlockItem;
import xox.labvorty.weaversparadise.model.BlahajModel;
import xox.labvorty.weaversparadise.renderers.helpers.DoubleSidedClothingRenderingData;
import xox.labvorty.weaversparadise.renderers.models.BlahajModelRenderer;

public class BlahajBlockEntityRenderer implements BlockEntityRenderer<BlahajBlockEntity> {
    private final BlahajModel<?> blahajModel;

    public BlahajBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        this.blahajModel = new BlahajModel<>(context.bakeLayer(BlahajModel.LAYER_LOCATION));
    }

    @Override
    public void render(
            @NotNull BlahajBlockEntity blockEntity,
            float partialTick,
            @NotNull PoseStack poseStack,
            @NotNull MultiBufferSource multiBufferSource,
            int packedLight,
            int packedOverlay
    ) {
        ItemStack itemStack = blockEntity.getItemStack();
        if (itemStack.isEmpty() || !(itemStack.getItem() instanceof DoubleSidedBlockItem doubleSidedBlockItem)) {
            return;
        }

        Minecraft minecraft = Minecraft.getInstance();

        int rotationValue = blockEntity.getBlockState().getValue(BlahajBlock.ROTATION);
        float yRot = rotationValue * 360.0F / 16.0F;

        poseStack.pushPose();
        poseStack.translate(0.5, 0, 0.5);
        poseStack.mulPose(Axis.YP.rotationDegrees(-yRot));

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
                1.0f, 1.0f, 1.0f,
                180, 180, 0,
                0, -1.5f, 0f,
                0, 0, 0,
                poseStack,
                packedLight
        );

        poseStack.popPose();
    }
}