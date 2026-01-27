package xox.labvorty.weaversparadise.renderers.render_helper;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.TheEndPortalRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;
import xox.labvorty.weaversparadise.data.WeaversParadiseShirtTextureHandler;
import xox.labvorty.weaversparadise.model.ModelUpperWear;
import xox.labvorty.weaversparadise.renderers.WeaversParadiseGlobalRendererDataHolder;
import xox.labvorty.weaversparadise.renderers.WeaversParadiseRenderTypes;

public class ShirtModelRenderer {
    private Minecraft minecraft;
    private int ticks;

    private int pCO;
    private int sCO;
    private int pCT;
    private int sCT;
    private String dTO;
    private String dTT;
    private String sT;
    private int lVO;
    private int lVT;
    private String mat;

    private int finalCO;
    private int finalCT;

    private int finalL1;
    private int finalL2;

    public void initData(ShirtRenderingData renderingData) {
        this.pCO = renderingData.getPrimaryColorOne();
        this.sCO = renderingData.getSecondaryColorOne();
        this.pCT = renderingData.getPrimaryColorTwo();
        this.sCT = renderingData.getSecondaryColorTwo();
        this.dTO = renderingData.getDyeTypeOne();
        this.dTT = renderingData.getDyeTypeTwo();
        this.sT = renderingData.getStencilType();
        this.lVO = renderingData.getLightValueOne();
        this.lVT = renderingData.getLightValueTwo();
        this.mat = renderingData.getMaterial();
    }

    public void renderModel(
            MultiBufferSource multiBufferSource,
            ModelUpperWear model,
            ShirtRenderingData renderingData,
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

        handleColor(dTO, "one", livingEntity, packedLight);
        handleColor(dTT, "two", livingEntity, packedLight);

        RenderingUtils renderingUtils = new RenderingUtils();
        minecraft = Minecraft.getInstance();
        ticks = (int)minecraft.level.getGameTime();

        String renderType = WeaversParadiseShirtTextureHandler.getByTypeAndMaterial(sT, mat).getRenderType();
        ResourceLocation tex1 = WeaversParadiseShirtTextureHandler.getByTypeAndMaterial(sT, mat).getTextureOne();
        ResourceLocation tex2 = WeaversParadiseShirtTextureHandler.getByTypeAndMaterial(sT, mat).getTextureTwo();

        poseStack.pushPose();

        poseStack.scale(scaleX, scaleY, scaleZ);

        poseStack.mulPose(Axis.XP.rotationDegrees(xRotPre));

        poseStack.mulPose(Axis.YP.rotationDegrees(yRotPre));

        poseStack.mulPose(Axis.ZP.rotationDegrees(zRotPre));

        poseStack.translate((xT) / scaleX, (yT) / scaleY, (zT) / scaleZ);

        poseStack.mulPose(Axis.XP.rotationDegrees(xRot));

        poseStack.mulPose(Axis.YP.rotationDegrees(yRot));

        poseStack.mulPose(Axis.ZP.rotationDegrees(zRot));

        VertexConsumer vc1 = renderingUtils.parseVC(multiBufferSource, dTO, tex1);
        model.renderToBuffer(
                poseStack,
                vc1,
                finalL1,
                OverlayTexture.NO_OVERLAY,
                finalCO
        );

        VertexConsumer vc2 = renderingUtils.parseVC(multiBufferSource, dTT, tex2);
        model.renderToBuffer(
                poseStack,
                vc2,
                finalL2,
                OverlayTexture.NO_OVERLAY,
                finalCT
        );

        poseStack.popPose();
    }

    public void handleColor(String dyeType, String order, LivingEntity livingEntity, int packedLight) {
        int finalColor = 0;
        int finalLight = packedLight;
        int primary = 0;
        int secondary = 0;
        int lightValue = 0;

        switch (order) {
            case "one" -> {
                primary = pCO;
                secondary = sCO;
                lightValue = lVO;
            }

            case "two" -> {
                primary = pCT;
                secondary = sCT;
                lightValue = lVT;
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

        switch (order) {
            case "one" -> {
                finalCO = finalColor;
                finalL1 = finalLight;
            }

            case "two" -> {
                finalCT = finalColor;
                finalL2 = finalLight;
            }
        }
    }

    public int getRainbowColor(int ticks) {
        float speed = 0.05F;

        float red = Mth.clamp((float)(Math.sin(ticks * speed) * 0.5 + 0.5), 0, 1);
        float green = Mth.clamp((float)(Math.sin(ticks * speed + 2 * Math.PI / 3) * 0.5 + 0.5), 0, 1);
        float blue = Mth.clamp((float)(Math.sin(ticks * speed + 4 * Math.PI / 3) * 0.5 + 0.5), 0, 1);

        int truered = (int)(red * 255);
        int truegreen = (int)(green * 255);
        int trueblue = (int)(blue * 255);

        return 255 << 24 | truered << 16 | truegreen << 8 | trueblue;
    }
}
