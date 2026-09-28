package xox.labvorty.weaversparadise.renderers.models;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import org.joml.Vector4f;
import oshi.util.tuples.Pair;
import xox.labvorty.weaversparadise.data.texture.ItemTexture;
import xox.labvorty.weaversparadise.data.texture.TextureRegistry;
import xox.labvorty.weaversparadise.model.ThighHighsModel;
import xox.labvorty.weaversparadise.renderers.helpers.ColorHandlers;
import xox.labvorty.weaversparadise.renderers.helpers.DoubleSidedClothingRenderingData;
import xox.labvorty.weaversparadise.renderers.helpers.RenderingUtils;

public class HandWarmersGUIModelRenderer {
    public static void renderModel(
            MultiBufferSource multiBufferSource,
            ThighHighsModel<?> model,
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

        ItemTexture leftTexture = TextureRegistry.find("hand_warmers", renderingData.sTL(), renderingData.mat());
        boolean renderTypeLeft = leftTexture.getRenderType();
        ResourceLocation tex1left = leftTexture.getTextureOne();
        ResourceLocation tex2left = leftTexture.getTextureTwo();

        ItemTexture rightTexture = TextureRegistry.find("hand_warmers", renderingData.sTR(), renderingData.mat());
        boolean renderTypeRight = rightTexture.getRenderType();
        ResourceLocation tex1right = rightTexture.getTextureOne();
        ResourceLocation tex2right = rightTexture.getTextureTwo();

        poseStack.pushPose();

        poseStack.scale(scaleX, scaleY, scaleZ);

        poseStack.mulPose(Axis.XP.rotationDegrees(xRotPre));

        poseStack.mulPose(Axis.YP.rotationDegrees(yRotPre));

        poseStack.mulPose(Axis.ZP.rotationDegrees(zRotPre));

        poseStack.translate((xT) / scaleX, (yT) / scaleY, (zT) / scaleZ);

        poseStack.mulPose(Axis.XP.rotationDegrees(xRot));

        poseStack.mulPose(Axis.YP.rotationDegrees(yRot));

        poseStack.mulPose(Axis.ZP.rotationDegrees(zRot));

        VertexConsumer vc1 = renderingUtils.parseVC(multiBufferSource, renderingData.dTLO(), tex1left, "hand_warmers", renderingData.glint(), renderingData.glintColor());
        Vector4f v1 = renderingUtils.colorIntToVector4f(fCLO.getA());
        model.LeftLeg.render(
                poseStack,
                vc1,
                fCLO.getB(),
                OverlayTexture.NO_OVERLAY,
                v1.x,
                v1.y,
                v1.z,
                v1.w
        );

        if (renderTypeLeft) {
            VertexConsumer vc2 = renderingUtils.parseVC(multiBufferSource, renderingData.dTLT(), tex2left, "hand_warmers", renderingData.glint(), renderingData.glintColor());
            Vector4f v2 = renderingUtils.colorIntToVector4f(fCLT.getA());
            model.LeftLeg.render(
                    poseStack,
                    vc2,
                    fCLT.getB(),
                    OverlayTexture.NO_OVERLAY,
                    v2.x,
                    v2.y,
                    v2.z,
                    v2.w
            );
        }

        VertexConsumer vc3 = renderingUtils.parseVC(multiBufferSource, renderingData.dTRO(), tex1right, "hand_warmers", renderingData.glint(), renderingData.glintColor());
        Vector4f v3 = renderingUtils.colorIntToVector4f(fCRO.getA());
        model.RightLeg.render(
                poseStack,
                vc3,
                fCRO.getB(),
                OverlayTexture.NO_OVERLAY,
                v3.x,
                v3.y,
                v3.z,
                v3.w
        );

        if (renderTypeRight) {
            VertexConsumer vc4 = renderingUtils.parseVC(multiBufferSource, renderingData.dTRT(), tex2right, "hand_warmers", renderingData.glint(), renderingData.glintColor());
            Vector4f v4 = renderingUtils.colorIntToVector4f(fCRT.getA());
            model.RightLeg.render(
                    poseStack,
                    vc4,
                    fCRT.getB(),
                    OverlayTexture.NO_OVERLAY,
                    v4.x,
                    v4.y,
                    v4.z,
                    v4.w
            );
        }

        poseStack.popPose();
    }
}
