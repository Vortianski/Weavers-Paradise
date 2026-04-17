package xox.labvorty.weaversparadise.renderers.models;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import oshi.util.tuples.Pair;
import xox.labvorty.weaversparadise.data.texture.HandWarmersTextures;
import xox.labvorty.weaversparadise.model.HandWarmersModel;
import xox.labvorty.weaversparadise.renderers.helpers.ColorHandlers;
import xox.labvorty.weaversparadise.renderers.helpers.HandWarmersRenderingData;
import xox.labvorty.weaversparadise.renderers.helpers.RenderingUtils;

public class HandWarmersModelRenderer {
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

    private int finalCLO;
    private int finalCLT;
    private int finalCRO;
    private int finalCRT;

    private int finalL1;
    private int finalL2;
    private int finalL3;
    private int finalL4;

    public void initData(HandWarmersRenderingData renderingData) {
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
            HandWarmersModel model,
            HandWarmersRenderingData renderingData,
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
        finalCLO = fCLO.getA();
        finalL1 = fCLO.getB();
        finalCLT = fCLT.getA();
        finalL2 = fCLT.getB();
        finalCRO = fCRO.getA();
        finalL3 = fCRO.getB();
        finalCRT = fCRT.getA();
        finalL4 = fCRT.getB();

        HandWarmersTextures leftHandler = HandWarmersTextures.getByTypeAndMaterial(sTL, mat);
        String renderTypeLeft = leftHandler.getRenderType();
        ResourceLocation tex1left = leftHandler.getTextureOne();
        ResourceLocation tex2left = leftHandler.getTextureTwo();

        HandWarmersTextures rightHandler = HandWarmersTextures.getByTypeAndMaterial(sTR, mat);
        String renderTypeRight = rightHandler.getRenderType();
        ResourceLocation tex1right = rightHandler.getTextureOne();
        ResourceLocation tex2right = rightHandler.getTextureTwo();

        poseStack.pushPose();

        poseStack.scale(scaleX, scaleY, scaleZ);

        poseStack.mulPose(Axis.XP.rotationDegrees(xRotPre));

        poseStack.mulPose(Axis.YP.rotationDegrees(yRotPre));

        poseStack.mulPose(Axis.ZP.rotationDegrees(zRotPre));

        poseStack.translate((xT) / scaleX, (yT) / scaleY, (zT) / scaleZ);

        poseStack.mulPose(Axis.XP.rotationDegrees(xRot));

        poseStack.mulPose(Axis.YP.rotationDegrees(yRot));

        poseStack.mulPose(Axis.ZP.rotationDegrees(zRot));

        VertexConsumer vc1 = renderingUtils.parseVC(multiBufferSource, dTLO, tex1left, "hand_warmers");
        model.LeftArm.render(
                poseStack,
                vc1,
                finalL1,
                OverlayTexture.NO_OVERLAY,
                finalCLO
        );

        if (renderTypeLeft.equals("double")) {
            VertexConsumer vc2 = renderingUtils.parseVC(multiBufferSource, dTLT, tex2left, "hand_warmers");
            model.LeftArm.render(
                    poseStack,
                    vc2,
                    finalL2,
                    OverlayTexture.NO_OVERLAY,
                    finalCLT
            );
        }

        VertexConsumer vc3 = renderingUtils.parseVC(multiBufferSource, dTRO, tex1right, "hand_warmers");
        model.RightArm.render(
                poseStack,
                vc3,
                finalL3,
                OverlayTexture.NO_OVERLAY,
                finalCRO
        );

        if (renderTypeRight.equals("double")) {
            VertexConsumer vc4 = renderingUtils.parseVC(multiBufferSource, dTRT, tex2right, "hand_warmers");
            model.RightArm.render(
                    poseStack,
                    vc4,
                    finalL4,
                    OverlayTexture.NO_OVERLAY,
                    finalCRT
            );
        }

        poseStack.popPose();
    }

    public int getRainbowColor(int ticks) {
        float speed = 0.05F;

        float red = Mth.clamp((float)(Math.sin(ticks * speed) * 0.5 + 0.5), 0, 1);
        float green = Mth.clamp((float)(Math.sin(ticks * speed + 2 * Math.PI / 3) * 0.5 + 0.5), 0, 1);
        float blue = Mth.clamp((float)(Math.sin(ticks * speed + 4 * Math.PI / 3) * 0.5 + 0.5), 0, 1);

        return 255 << 24 | (int)(red * 255) << 16 | (int)(green * 255) << 8 | (int)(blue * 255);
    }
}
