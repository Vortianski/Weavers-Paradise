package xox.labvorty.weaversparadise.renderers.models;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import oshi.util.tuples.Pair;
import xox.labvorty.weaversparadise.data.texture.ItemTexture;
import xox.labvorty.weaversparadise.data.texture.TextureRegistry;
import xox.labvorty.weaversparadise.model.ChokerModel;
import xox.labvorty.weaversparadise.renderers.helpers.ColorHandlers;
import xox.labvorty.weaversparadise.renderers.helpers.DoubleSidedClothingRenderingData;
import xox.labvorty.weaversparadise.renderers.helpers.RenderingUtils;

public class ChokerModelRenderer {
    public static void renderModel(
            MultiBufferSource multiBufferSource,
            ChokerModel<?> model,
            DoubleSidedClothingRenderingData renderingData,
            LivingEntity livingEntity,
            float scaleX,
            float scaleY,
            float scaleZ,
            float xRotPre,
            float yRotPre,
            float zRotPre,
            float xT,
            float yT,
            float zT,
            float xRot,
            float yRot,
            float zRot,
            PoseStack poseStack,
            int packedLight
    ) {
        RenderingUtils renderingUtils = new RenderingUtils();
        Minecraft minecraft = Minecraft.getInstance();
        int ticks = 0;
        if (minecraft.level != null) {
            ticks = (int)minecraft.level.getGameTime();
        }

        Pair<Integer, Integer> fCLO = ColorHandlers.handle(renderingData.dTLO(), renderingData.pCLO(), renderingData.sCLO(), renderingData.lVLO(), livingEntity, packedLight, ticks);
        Pair<Integer, Integer> fCLT = ColorHandlers.handle(renderingData.dTLT(), renderingData.pCLT(), renderingData.sCLT(), renderingData.lVLT(), livingEntity, packedLight, ticks);
        Pair<Integer, Integer> fCRO = ColorHandlers.handle(renderingData.dTRO(), renderingData.pCRO(), renderingData.sCRO(), renderingData.lVRO(), livingEntity, packedLight, ticks);
        Pair<Integer, Integer> fCRT = ColorHandlers.handle(renderingData.dTRT(), renderingData.pCRT(), renderingData.sCRT(), renderingData.lVRT(), livingEntity, packedLight, ticks);

        ItemTexture primaryTexture = TextureRegistry.find("choker", renderingData.sTL(), "pri");
        boolean renderTypeLeft = primaryTexture.getRenderType();
        ResourceLocation tex1left = primaryTexture.getTextureOne();
        ResourceLocation tex2left = primaryTexture.getTextureTwo();

        ItemTexture secondaryTexture = TextureRegistry.find("choker", renderingData.sTR(), "sec");
        boolean renderTypeRight = secondaryTexture.getRenderType();
        ResourceLocation tex1right = secondaryTexture.getTextureOne();
        ResourceLocation tex2right = secondaryTexture.getTextureTwo();

        poseStack.pushPose();

        poseStack.scale(scaleX, scaleY, scaleZ);

        poseStack.mulPose(Axis.XP.rotationDegrees(xRotPre));

        poseStack.mulPose(Axis.YP.rotationDegrees(yRotPre));

        poseStack.mulPose(Axis.ZP.rotationDegrees(zRotPre));

        poseStack.translate((xT) / scaleX, (yT) / scaleY, (zT) / scaleZ);

        poseStack.mulPose(Axis.XP.rotationDegrees(xRot));

        poseStack.mulPose(Axis.YP.rotationDegrees(yRot));

        poseStack.mulPose(Axis.ZP.rotationDegrees(zRot));

        VertexConsumer vc1 = renderingUtils.parseVC(multiBufferSource, renderingData.dTLO(), tex1left, "choker", renderingData.glint(), renderingData.glintColor());
        model.Body.render(
                poseStack,
                vc1,
                fCLO.getB(),
                OverlayTexture.NO_OVERLAY,
                fCLO.getA()
        );

        if (renderTypeLeft) {
            VertexConsumer vc2 = renderingUtils.parseVC(multiBufferSource, renderingData.dTLT(), tex2left, "choker", renderingData.glint(), renderingData.glintColor());
            model.Body.render(
                    poseStack,
                    vc2,
                    fCLT.getB(),
                    OverlayTexture.NO_OVERLAY,
                    fCLT.getA()
            );
        }

        VertexConsumer vc3 = renderingUtils.parseVC(multiBufferSource, renderingData.dTRO(), tex1right, "choker", renderingData.glint(), renderingData.glintColor());
        model.Body.render(
                poseStack,
                vc3,
                fCRO.getB(),
                OverlayTexture.NO_OVERLAY,
                fCRO.getA()
        );

        if (renderTypeRight) {
            VertexConsumer vc4 = renderingUtils.parseVC(multiBufferSource, renderingData.dTRT(), tex2right, "choker", renderingData.glint(), renderingData.glintColor());
            model.Body.render(
                    poseStack,
                    vc4,
                    fCRT.getB(),
                    OverlayTexture.NO_OVERLAY,
                    fCRT.getA()
            );
        }

        poseStack.popPose();
    }
}
