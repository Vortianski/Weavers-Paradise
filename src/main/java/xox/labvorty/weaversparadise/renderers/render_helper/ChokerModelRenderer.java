package xox.labvorty.weaversparadise.renderers.render_helper;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;
import xox.labvorty.weaversparadise.data.WeaversParadiseChokerTextureHandler;
import xox.labvorty.weaversparadise.data.WeaversParadiseThighHighsTextureHandler;
import xox.labvorty.weaversparadise.model.ModelChoker;
import xox.labvorty.weaversparadise.model.ThighHighsModel;
import xox.labvorty.weaversparadise.renderers.WeaversParadiseGlobalRendererDataHolder;

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

    private int finalCLO;
    private int finalCLT;
    private int finalCRO;
    private int finalCRT;

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
            ModelChoker model,
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

        handleColor(dTLO, "leftOne", livingEntity, packedLight);
        handleColor(dTLT, "leftTwo", livingEntity, packedLight);
        handleColor(dTRO, "rightOne", livingEntity, packedLight);
        handleColor(dTRT, "rightTwo", livingEntity, packedLight);

        WeaversParadiseChokerTextureHandler leftHandler = WeaversParadiseChokerTextureHandler.getByTypeAndMaterial(sTL, mat);
        String renderTypeLeft = leftHandler.getRenderType();
        ResourceLocation tex1left = leftHandler.getTextureOne();
        ResourceLocation tex2left = leftHandler.getTextureTwo();

        WeaversParadiseChokerTextureHandler rightHandler = WeaversParadiseChokerTextureHandler.getByTypeAndMaterial(sTR, mat);
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
                finalCLO
        );

        if (renderTypeLeft.equals("double")) {
            VertexConsumer vc2 = renderingUtils.parseVC(multiBufferSource, dTLT, tex2left, "choker");
            model.Body.render(
                    poseStack,
                    vc2,
                    finalL2,
                    OverlayTexture.NO_OVERLAY,
                    finalCLT
            );
        }

        VertexConsumer vc3 = renderingUtils.parseVC(multiBufferSource, dTRO, tex1right, "choker");
        model.Body.render(
                poseStack,
                vc3,
                finalL3,
                OverlayTexture.NO_OVERLAY,
                finalCRO
        );

        if (renderTypeRight.equals("double")) {
            VertexConsumer vc4 = renderingUtils.parseVC(multiBufferSource, dTRT, tex2right, "choker");
            model.Body.render(
                    poseStack,
                    vc4,
                    finalL4,
                    OverlayTexture.NO_OVERLAY,
                    finalCRT
            );
        }

        poseStack.popPose();
    }

    public void handleColor(String dyeType, String order, LivingEntity livingEntity, int packedLight) {
        int finalColor = 0;
        int finalLight = packedLight;
        int primary = 0;
        int secondary = 0;
        int lightValue = 0;

        switch (order) {
            case "leftOne" -> {
                primary = pCLO;
                secondary = sCLO;
                lightValue = lVLO;
            }

            case "leftTwo" -> {
                primary = pCLT;
                secondary = sCLT;
                lightValue = lVLT;
            }

            case "rightOne" -> {
                primary = pCRO;
                secondary = sCRO;
                lightValue = lVRO;
            }

            case "rightTwo" -> {
                primary = pCRT;
                secondary = sCRT;
                lightValue = lVRT;
            }
        }

        finalColor = primary;

        if (dyeType.equals("rainbow")) {
            finalColor = getRainbowColor(ticks);
        }

        if (dyeType.equals("ender")) {
            finalLight = 0;
        }

        if (dyeType.equals("glowstone")) {
            finalLight = LightTexture.FULL_BRIGHT;
        }

        if (dyeType.equals("biome")) {
            BlockPos bpos = new BlockPos((int)livingEntity.getX(), (int)livingEntity.getY(), (int)livingEntity.getZ());
            int c = minecraft.level.getBiome(bpos).value().getGrassColor(livingEntity.getX(), livingEntity.getZ());
            c = 255 << 24 | ((c >> 16) & 0xFF) << 16 | ((c >> 8) & 0xFF) << 8 | (c & 0xFF);

            finalColor = c;
        }

        if (dyeType.equals("speed")) {
            Vec3 vec3 = livingEntity.getDeltaMovement();
            double velocity = vec3.lengthSqr();

            int colorOne = primary;
            int colorTwo = secondary;

            int redOne = (colorOne >> 16) & 0xFF;
            int greenOne = (colorOne >> 8) & 0xFF;
            int blueOne = colorOne & 0xFF;

            int redTwo = (colorTwo >> 16) & 0xFF;
            int greenTwo = (colorTwo >> 8) & 0xFF;
            int blueTwo = colorTwo & 0xFF;

            int finalRed = Mth.lerpInt((float)Mth.clamp(velocity / 0.029081503190380643, 0, 1), redOne, redTwo);
            int finalGreen = Mth.lerpInt((float)Mth.clamp(velocity / 0.029081503190380643, 0, 1), greenOne, greenTwo);
            int finalBlue = Mth.lerpInt((float)Mth.clamp(velocity / 0.029081503190380643, 0, 1), blueOne, blueTwo);

            finalColor = 255 << 24 | finalRed << 16 | finalGreen << 8 | finalBlue;
        }

        if (dyeType.equals("height_bedrock")) {
            int absoluteMinimum = minecraft.level.getMinBuildHeight();
            int absoluteMaximum = minecraft.level.getMaxBuildHeight();
            int height = (int)livingEntity.getY();

            float value = Mth.clamp(((float)height - (float)absoluteMinimum) / ((float)absoluteMaximum - (float)absoluteMinimum), 0, 1);

            int colorOne = primary;
            int colorTwo = secondary;

            int redOne = (colorOne >> 16) & 0xFF;
            int greenOne = (colorOne >> 8) & 0xFF;
            int blueOne = colorOne & 0xFF;

            int redTwo = (colorTwo >> 16) & 0xFF;
            int greenTwo = (colorTwo >> 8) & 0xFF;
            int blueTwo = colorTwo & 0xFF;

            int finalRed = Mth.lerpInt(value, redOne, redTwo);
            int finalGreen = Mth.lerpInt(value, greenOne, greenTwo);
            int finalBlue = Mth.lerpInt(value, blueOne, blueTwo);

            finalColor = 255 << 24 | finalRed << 16 | finalGreen << 8 | finalBlue;
        }

        if (dyeType.equals("height_sea")) {
            double minY = minecraft.level.getMinBuildHeight();
            double maxY = minecraft.level.getMaxBuildHeight();
            double seaY = minecraft.level.getSeaLevel();
            double playerY = livingEntity.getY();

            double distanceAbove = maxY - seaY;
            double distanceBelow = seaY - minY;
            double maxDistance = Math.max(distanceAbove, distanceBelow);
            float value = (float)Mth.clamp(Math.abs(playerY - seaY) / maxDistance, 0, 1);

            int colorOne = primary;
            int colorTwo = secondary;

            int redOne = (colorOne >> 16) & 0xFF;
            int greenOne = (colorOne >> 8) & 0xFF;
            int blueOne = colorOne & 0xFF;

            int redTwo = (colorTwo >> 16) & 0xFF;
            int greenTwo = (colorTwo >> 8) & 0xFF;
            int blueTwo = colorTwo & 0xFF;

            int finalRed = Mth.lerpInt(value, redOne, redTwo);
            int finalGreen = Mth.lerpInt(value, greenOne, greenTwo);
            int finalBlue = Mth.lerpInt(value, blueOne, blueTwo);

            finalColor = 255 << 24 | finalRed << 16 | finalGreen << 8 | finalBlue;
        }

        if (dyeType.equals("sculk")) {
            int redMain = 5;
            int greenMain = 38;
            int blueMain = 43;

            int redPulse = 0;
            int greenPulse = 255;
            int bluePulse = 255;

            int sculkPulse = WeaversParadiseGlobalRendererDataHolder.getSculkPulse();
            int finalRed = Mth.lerpInt(sculkPulse / 60.0f, redMain, redPulse);
            int finalGreen = Mth.lerpInt(sculkPulse / 60.0f, greenMain, greenPulse);
            int finalBlue = Mth.lerpInt(sculkPulse / 60.0f, blueMain, bluePulse);

            finalColor = 255 << 24 | finalRed << 16 | finalGreen << 8 | finalBlue;
        }

        if (dyeType.equals("lamp")) {
            finalLight = lightValue << 20 | lightValue << 4;
        }

        if (dyeType.equals("invisible")) {
            finalColor = 0;
        }

        switch (order) {
            case "leftOne" -> {
                finalCLO = finalColor;
                finalL1 = finalLight;
            }

            case "leftTwo" -> {
                finalCLT = finalColor;
                finalL2 = finalLight;
            }

            case "rightOne" -> {
                finalCRO = finalColor;
                finalL3 = finalLight;
            }

            case "rightTwo" -> {
                finalCRT = finalColor;
                finalL4 = finalLight;
            }
        }
    }

    public int getRainbowColor(int ticks) {
        float speed = 0.05F;

        float red = Mth.clamp((float)(Math.sin(ticks * speed) * 0.5 + 0.5), 0, 1);
        float green = Mth.clamp((float)(Math.sin(ticks * speed + 2 * Math.PI / 3) * 0.5 + 0.5), 0, 1);
        float blue = Mth.clamp((float)(Math.sin(ticks * speed + 4 * Math.PI / 3) * 0.5 + 0.5), 0, 1);

        return 255 << 24 | (int)(red * 255) << 16 | (int)(green * 255) << 8 | (int)(blue * 255);
    }
}
