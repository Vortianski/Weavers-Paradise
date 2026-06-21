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
import xox.labvorty.weaversparadise.data.texture.deprecated.ChokerTextures;
import xox.labvorty.weaversparadise.models.ChokerModel;
import xox.labvorty.weaversparadise.renderers.helpers.ChokerRenderingData;
import xox.labvorty.weaversparadise.renderers.helpers.ColorHandlers;
import xox.labvorty.weaversparadise.renderers.helpers.RenderingUtils;

public class ChokerModelRenderer {
    private Minecraft minecraft;
    private int ticks;

    private int pCLO;
    private int sCLO;
    private int pCRO;
    private int sCRO;
    private int pCLT;
    private int sCLT;
    private int pCRT;
    private int sCRT;
    private String dTLO;
    private String dTRO;
    private String dTLT;
    private String dTRT;
    private String sTL;
    private String sTR;
    private int lVLO;
    private int lVLT;
    private int lVRO;
    private int lVRT;
    private String mat;

    private Vector4f finalCLO;
    private Vector4f finalCLT;
    private Vector4f finalCRO;
    private Vector4f finalCRT;

    private int finalL1;
    private int finalL2;
    private int finalL3;
    private int finalL4;

    public void initData(ChokerRenderingData renderingData) {
        pCLO = renderingData.getPrimaryColorLeftOne();
        sCLO = renderingData.getSecondaryColorLeftOne();
        pCRO = renderingData.getPrimaryColorRightOne();
        sCRO = renderingData.getSecondaryColorRightOne();

        pCLT = renderingData.getPrimaryColorLeftTwo();
        sCLT = renderingData.getSecondaryColorLeftTwo();
        pCRT = renderingData.getPrimaryColorRightTwo();
        sCRT = renderingData.getSecondaryColorRightTwo();

        dTLO = renderingData.getDyeTypeLeftOne();
        dTRO = renderingData.getDyeTypeRightOne();
        dTLT = renderingData.getDyeTypeLeftTwo();
        dTRT = renderingData.getDyeTypeRightTwo();

        sTL = renderingData.getStencilTypeLeft();
        sTR = renderingData.getStencilTypeRight();

        lVLO = renderingData.getLightValueLeftOne();
        lVLT = renderingData.getLightValueLeftTwo();
        lVRO = renderingData.getLightValueRightOne();
        lVRT = renderingData.getLightValueRightTwo();

        mat = renderingData.getMaterial();
    }

    public void renderModel(
            MultiBufferSource multiBufferSource,
            ChokerModel model,
            ChokerRenderingData renderingData,
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
        initData(renderingData);

        RenderingUtils renderingUtils = new RenderingUtils();
        minecraft = Minecraft.getInstance();
        ticks = (int)minecraft.level.getGameTime();

        Pair<Integer, Integer> fCLO = ColorHandlers.handle(dTLO, pCLO, sCLO, lVLO, livingEntity, packedLight, ticks);
        Pair<Integer, Integer> fCLT = ColorHandlers.handle(dTLT, pCLT, sCLT, lVLT, livingEntity, packedLight, ticks);
        Pair<Integer, Integer> fCRO = ColorHandlers.handle(dTRO, pCRO, sCRO, lVRO, livingEntity, packedLight, ticks);
        Pair<Integer, Integer> fCRT = ColorHandlers.handle(dTRT, pCRT, sCRT, lVRT, livingEntity, packedLight, ticks);
        finalCLO = colorIntToVector4f(fCLO.getA());
        finalL1 = fCLO.getB();
        finalCLT = colorIntToVector4f(fCLT.getA());
        finalL2 = fCLT.getB();
        finalCRO = colorIntToVector4f(fCRO.getA());
        finalL3 = fCRO.getB();
        finalCRT = colorIntToVector4f(fCRT.getA());
        finalL4 = fCRT.getB();

        ChokerTextures leftHandler = ChokerTextures.getByTypeAndMaterial(sTL, mat);
        String renderTypeLeft = leftHandler.getRenderType();
        ResourceLocation tex1left = leftHandler.getTextureOne();
        ResourceLocation tex2left = leftHandler.getTextureTwo();

        ChokerTextures rightHandler = ChokerTextures.getByTypeAndMaterial(sTR, mat);
        String renderTypeRight = rightHandler.getRenderType();
        ResourceLocation tex1right = rightHandler.getTextureThree();
        ResourceLocation tex2right = rightHandler.getTextureFour();

        poseStack.pushPose();

        poseStack.scale(scaleX, scaleY, scaleZ);

        poseStack.mulPose(Axis.XP.rotationDegrees(xRotPre));

        poseStack.mulPose(Axis.YP.rotationDegrees(yRotPre));

        poseStack.mulPose(Axis.ZP.rotationDegrees(zRotPre));

        poseStack.translate((xT) / scaleX, (yT) / scaleY, (zT) / scaleZ);

        poseStack.mulPose(Axis.XP.rotationDegrees(xRot));

        poseStack.mulPose(Axis.YP.rotationDegrees(yRot));

        poseStack.mulPose(Axis.ZP.rotationDegrees(zRot));

        VertexConsumer vc1 = renderingUtils.parseVC(multiBufferSource, dTLO, tex1left, "choker");
        model.Body.render(
                poseStack,
                vc1,
                finalL1,
                OverlayTexture.NO_OVERLAY,
                finalCLO.x,
                finalCLO.y,
                finalCLO.z,
                finalCLO.w
        );

        if (renderTypeLeft.equals("double")) {
            VertexConsumer vc2 = renderingUtils.parseVC(multiBufferSource, dTLT, tex2left, "choker");
            model.Body.render(
                    poseStack,
                    vc2,
                    finalL2,
                    OverlayTexture.NO_OVERLAY,
                    finalCLT.x,
                    finalCLT.y,
                    finalCLT.z,
                    finalCLT.w
            );
        }

        VertexConsumer vc3 = renderingUtils.parseVC(multiBufferSource, dTRO, tex1right, "choker");
        model.Body.render(
                poseStack,
                vc3,
                finalL3,
                OverlayTexture.NO_OVERLAY,
                finalCRO.x,
                finalCRO.y,
                finalCRO.z,
                finalCRO.w
        );

        if (renderTypeRight.equals("double")) {
            VertexConsumer vc4 = renderingUtils.parseVC(multiBufferSource, dTRT, tex2right, "choker");
            model.Body.render(
                    poseStack,
                    vc4,
                    finalL4,
                    OverlayTexture.NO_OVERLAY,
                    finalCRT.x,
                    finalCRT.y,
                    finalCRT.z,
                    finalCRT.w
            );
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
