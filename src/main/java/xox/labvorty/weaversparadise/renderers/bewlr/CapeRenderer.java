package xox.labvorty.weaversparadise.renderers.bewlr;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.joml.Vector4f;
import oshi.util.tuples.Pair;
import xox.labvorty.weaversparadise.data.texture.ItemTexture;
import xox.labvorty.weaversparadise.data.texture.TextureRegistry;
import xox.labvorty.weaversparadise.items.clothing.CapeCottonItem;
import xox.labvorty.weaversparadise.items.clothing.CapeSilkItem;
import xox.labvorty.weaversparadise.items.clothing.CapeWoolItem;
import xox.labvorty.weaversparadise.items.clothing.defined.CapeInterface;
import xox.labvorty.weaversparadise.renderers.helpers.ColorHandlers;
import xox.labvorty.weaversparadise.renderers.helpers.RenderingUtils;
import xox.labvorty.weaversparadise.utils.PlayerModelInterface;

public class CapeRenderer extends BlockEntityWithoutLevelRenderer {
    private final PlayerModel playerModel;

    public CapeRenderer(BlockEntityRenderDispatcher blockEntityRenderDispatcher, EntityModelSet entityModelSet) {
        super(blockEntityRenderDispatcher, entityModelSet);

        this.playerModel = new PlayerModel(entityModelSet.bakeLayer(ModelLayers.PLAYER), false);
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
        Minecraft minecraft = Minecraft.getInstance();
        String material = "cotton";
        if (minecraft.player == null) return;

        if (!(stack.getItem() instanceof CapeInterface capeInterface)) return;

        if (stack.getItem() instanceof CapeCottonItem capeCottonItem) {
            material = "cotton";
        } else if (stack.getItem() instanceof CapeSilkItem capeSilkItem) {
            material = "silk";
        } else if (stack.getItem() instanceof CapeWoolItem capeWoolItem) {
            material = "wool";
        }

        float scale = 0.5f;

        float xRot = 0.0f;
        float yRot = 0.0f;
        float zRot = 0.0f;

        float xTranslation = 0.5f;
        float yTranslation = 1f;
        float zTranslation = 0.0f;

        float additionalXRot = 0;
        float additionalYRot = 0;
        float additionalZRot = 0;

        switch (transformType) {
            case GUI -> {
                scale = 0.75f;
                yTranslation = 0.85f;
                additionalYRot = 225f;
                additionalXRot = -22.5f;
            }
            case THIRD_PERSON_LEFT_HAND, THIRD_PERSON_RIGHT_HAND, FIRST_PERSON_LEFT_HAND, FIRST_PERSON_RIGHT_HAND -> {
                zTranslation = 0.5f;
                additionalYRot = 180f;
            }
            case FIXED -> {
                additionalYRot = 0f;
                yTranslation = 0.5f;
                zTranslation = 0.5f;
            }
            case GROUND -> {
                scale = 0.25f;
                yTranslation = 0.5f;
                zTranslation = 0.5f;
            }
        }

        int primaryColorOne = capeInterface.getItemMainColor(stack, 1);
        int secondaryColorOne = capeInterface.getItemSecondaryColor(stack, 1);
        int primaryColorTwo = capeInterface.getItemMainColor(stack, 2);
        int secondaryColorTwo = capeInterface.getItemSecondaryColor(stack, 2);
        String dyeTypeOne = capeInterface.getItemDyeType(stack, 1);
        String dyeTypeTwo = capeInterface.getItemDyeType(stack, 2);
        String stencilType = capeInterface.getStensilType(stack);
        int lightValueOne = capeInterface.getItemLightValue(stack, 1);
        int lightValueTwo = capeInterface.getItemLightValue(stack, 2);

        Pair<Integer, Integer> col1 = ColorHandlers.handle(dyeTypeOne, primaryColorOne, secondaryColorOne, lightValueOne, minecraft.player, packedLight, (int)minecraft.level.getGameTime());
        Pair<Integer, Integer> col2 = ColorHandlers.handle(dyeTypeTwo, primaryColorTwo, secondaryColorTwo, lightValueTwo, minecraft.player, packedLight, (int)minecraft.level.getGameTime());
        Vector4f finalColorOne = colorIntToVector4f(col1.getA());
        Vector4f finalColorTwo = colorIntToVector4f(col2.getA());
        int finalLightOne = col1.getB();
        int finalLightTwo = col2.getB();

        ItemTexture texture = TextureRegistry.find("cape", stencilType, material);

        RenderingUtils renderingUtils = new RenderingUtils();

        poseStack.pushPose();

        poseStack.mulPose(Axis.XP.rotationDegrees(xRot));
        poseStack.mulPose(Axis.YP.rotationDegrees(yRot));
        poseStack.mulPose(Axis.ZP.rotationDegrees(zRot));
        poseStack.translate(xTranslation, yTranslation, zTranslation);
        poseStack.scale(scale, -scale, scale);
        poseStack.mulPose(Axis.XP.rotationDegrees(additionalXRot));
        poseStack.mulPose(Axis.YP.rotationDegrees(additionalYRot));
        poseStack.mulPose(Axis.ZP.rotationDegrees(additionalZRot));

        VertexConsumer vertexConsumer1 = renderingUtils.parseVC(buffer, dyeTypeOne, texture.getTextureOne(),"cape");
        ((PlayerModelInterface)playerModel).getCloak().render(poseStack, vertexConsumer1, finalLightOne, packedOverlay, finalColorOne.x, finalColorOne.y, finalColorOne.z, finalColorOne.w);

        if (texture.getRenderType()) {
            VertexConsumer vertexConsumer2 = renderingUtils.parseVC(buffer, dyeTypeTwo, texture.getTextureTwo(), "cape");
            ((PlayerModelInterface)playerModel).getCloak().render(poseStack, vertexConsumer2, finalLightTwo, packedOverlay, finalColorTwo.x, finalColorTwo.y, finalColorTwo.z, finalColorTwo.w);
        }

        poseStack.popPose();
    }

    private static Vector4f colorIntToVector4f(int color) {
        float a = ((color >> 24) & 0xFF) / 255.0f;
        float r = ((color >> 16) & 0xFF) / 255.0f;
        float g = ((color >> 8) & 0xFF) / 255.0f;
        float b = (color & 0xFF) / 255.0f;
        return new Vector4f(r, g, b, a);
    }
}
