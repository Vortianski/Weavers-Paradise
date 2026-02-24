package xox.labvorty.weaversparadise.renderers.render_helper;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.entity.LivingEntity;
import xox.labvorty.weaversparadise.data.WeaversParadiseCatRingTextureHandler;
import xox.labvorty.weaversparadise.data.WeaversParadiseRingTextureHandler;
import xox.labvorty.weaversparadise.model.BasicRingModel;
import xox.labvorty.weaversparadise.model.CatRingModel;

public class CatRingModelRenderer {
    private int color;
    private String metalType;

    public void initData(ChokerTrinketRenderingData data) {
        this.color = data.getColor();
        this.metalType = data.getMetalType();
    }

    public void renderModel(
            MultiBufferSource multiBufferSource,
            CatRingModel model,
            ChokerTrinketRenderingData data,
            LivingEntity entity,
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
        initData(data);

        RenderingUtils renderingUtils = new RenderingUtils();
        WeaversParadiseCatRingTextureHandler handler = WeaversParadiseCatRingTextureHandler.getByMetalType(metalType);
        int finalColor;
        if (handler.isFreezeColor()) {
            finalColor = 255 << 24 | 255 << 16 | 255 << 8 | 255;
        } else {
            finalColor = color;
        }

        poseStack.pushPose();

        poseStack.scale(scaleX, scaleY, scaleZ);

        poseStack.mulPose(Axis.XP.rotationDegrees(xRotPre));

        poseStack.mulPose(Axis.YP.rotationDegrees(yRotPre));

        poseStack.mulPose(Axis.ZP.rotationDegrees(zRotPre));

        poseStack.translate((xT) / scaleX, (yT) / scaleY, (zT) / scaleZ);

        poseStack.mulPose(Axis.XP.rotationDegrees(xRot));

        poseStack.mulPose(Axis.YP.rotationDegrees(yRot));

        poseStack.mulPose(Axis.ZP.rotationDegrees(zRot));

        VertexConsumer vertexConsumer = multiBufferSource.getBuffer(
                RenderType.entityTranslucent(handler.getTexture())
        );
        model.renderToBuffer(
                poseStack,
                vertexConsumer,
                packedLight,
                OverlayTexture.NO_OVERLAY,
                finalColor
        );

        poseStack.popPose();
    }
}
