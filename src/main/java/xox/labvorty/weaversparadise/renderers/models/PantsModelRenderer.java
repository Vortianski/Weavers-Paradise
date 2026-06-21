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
import xox.labvorty.weaversparadise.model.PantsModel;
import xox.labvorty.weaversparadise.renderers.helpers.ColorHandlers;
import xox.labvorty.weaversparadise.renderers.helpers.PantsRenderingData;
import xox.labvorty.weaversparadise.renderers.helpers.RenderingUtils;

public class PantsModelRenderer {
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

    public void initData(PantsRenderingData renderingData) {
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
            PantsModel model,
            PantsRenderingData renderingData,
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
        finalCO = fCO.getA();
        finalL1 = fCO.getB();
        finalCT = fCT.getA();
        finalL2 = fCT.getB();

        ItemTexture texture = TextureRegistry.find("pants", sT, mat);
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

        VertexConsumer vc1 = renderingUtils.parseVC(multiBufferSource, dTO, tex1, "pants");
        model.renderToBuffer(
                poseStack,
                vc1,
                finalL1,
                OverlayTexture.NO_OVERLAY,
                finalCO
        );

        if (renderType) {
            VertexConsumer vc2 = renderingUtils.parseVC(multiBufferSource, dTT, tex2, "pants");
            model.renderToBuffer(
                    poseStack,
                    vc2,
                    finalL2,
                    OverlayTexture.NO_OVERLAY,
                    finalCT
            );
        }

        poseStack.popPose();
    }
}
