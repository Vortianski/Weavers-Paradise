package xox.labvorty.weaversparadise.renderers.models;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.entity.LivingEntity;
import org.joml.Vector4f;
import xox.labvorty.weaversparadise.data.texture.deprecated.CatRingTextures;
import xox.labvorty.weaversparadise.models.CatRingModel;
import xox.labvorty.weaversparadise.renderers.helpers.ChokerTrinketRenderingData;
import xox.labvorty.weaversparadise.renderers.helpers.RenderingUtils;

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
        CatRingTextures handler = CatRingTextures.getByMetalType(metalType);
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
        Vector4f finalVec = colorIntToVector4f(finalColor);
        model.renderToBuffer(
                poseStack,
                vertexConsumer,
                packedLight,
                OverlayTexture.NO_OVERLAY,
                finalVec.x,
                finalVec.y,
                finalVec.z,
                finalVec.w
        );

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
