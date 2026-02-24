package xox.labvorty.weaversparadise.renderers.render_helper;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.entity.LivingEntity;
import xox.labvorty.weaversparadise.data.WeaversParadiseBellTextureHandler;
import xox.labvorty.weaversparadise.data.WeaversParadiseHeartTextureHandler;
import xox.labvorty.weaversparadise.model.BellModel;
import xox.labvorty.weaversparadise.model.HeartModel;

public class HeartModelRenderer {
    private int color;
    private String metalType;

    public void initData(ChokerTrinketRenderingData data) {
        this.color = data.getColor();
        this.metalType = data.getMetalType();
    }

    public void renderModel(
            MultiBufferSource multiBufferSource,
            HeartModel model,
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
        WeaversParadiseHeartTextureHandler handler = WeaversParadiseHeartTextureHandler.getByMetalType(metalType);
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
