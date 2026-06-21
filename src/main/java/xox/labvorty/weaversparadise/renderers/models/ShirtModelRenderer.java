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
import xox.labvorty.weaversparadise.models.UpperWearModel;
import xox.labvorty.weaversparadise.renderers.helpers.ColorHandlers;
import xox.labvorty.weaversparadise.renderers.helpers.RenderingUtils;
import xox.labvorty.weaversparadise.renderers.helpers.ShirtRenderingData;

public class ShirtModelRenderer {
    private Minecraft minecraft;
    private int ticks;

    private boolean isOpen;
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

    private Vector4f finalCO;
    private Vector4f finalCT;

    private int finalL1;
    private int finalL2;

    public void initData(ShirtRenderingData renderingData) {
        this.isOpen = renderingData.isOpen();
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
            UpperWearModel model,
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

        RenderingUtils renderingUtils = new RenderingUtils();
        minecraft = Minecraft.getInstance();
        ticks = (int)minecraft.level.getGameTime();

        Pair<Integer, Integer> fCO = ColorHandlers.handle(dTO, pCO, sCO, lVO, livingEntity, packedLight, ticks);
        Pair<Integer, Integer> fCT = ColorHandlers.handle(dTT, pCT, sCT, lVT, livingEntity, packedLight, ticks);
        finalCO = colorIntToVector4f(fCO.getA());
        finalL1 = fCO.getB();
        finalCT = colorIntToVector4f(fCT.getA());
        finalL2 = fCT.getB();

        ItemTexture texture = TextureRegistry.find("shirt", sT, mat);

        if (isOpen) {
            texture = TextureRegistry.find("shirt_open", sT, mat);
        }
        boolean renderType = texture.getRenderType();
        ResourceLocation tex1 = texture.getTextureOne();
        ResourceLocation tex2 = texture.getTextureTwo();

        poseStack.pushPose();

        poseStack.scale(scaleX, scaleY, scaleZ);

        poseStack.mulPose(Axis.XP.rotationDegrees(xRotPre));

        poseStack.mulPose(Axis.YP.rotationDegrees(yRotPre));

        poseStack.mulPose(Axis.ZP.rotationDegrees(zRotPre));

        poseStack.translate((xT) / scaleX, (yT) / scaleY, (zT) / scaleZ);

        poseStack.mulPose(Axis.XP.rotationDegrees(xRot));

        poseStack.mulPose(Axis.YP.rotationDegrees(yRot));

        poseStack.mulPose(Axis.ZP.rotationDegrees(zRot));

        VertexConsumer vc1 = renderingUtils.parseVC(multiBufferSource, dTO, tex1, "shirt");
        model.renderToBuffer(
                poseStack,
                vc1,
                finalL1,
                OverlayTexture.NO_OVERLAY,
                finalCO.x,
                finalCO.y,
                finalCO.z,
                finalCO.w
        );

        if (renderType) {
            VertexConsumer vc2 = renderingUtils.parseVC(multiBufferSource, dTT, tex2, "shirt");
            model.renderToBuffer(
                    poseStack,
                    vc2,
                    finalL2,
                    OverlayTexture.NO_OVERLAY,
                    finalCT.x,
                    finalCT.y,
                    finalCT.z,
                    finalCT.w
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
