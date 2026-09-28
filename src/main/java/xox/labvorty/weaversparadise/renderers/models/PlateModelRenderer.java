package xox.labvorty.weaversparadise.renderers.models;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.entity.LivingEntity;
import org.joml.Vector4f;
import xox.labvorty.weaversparadise.data.texture.ItemTexture;
import xox.labvorty.weaversparadise.data.texture.TextureRegistry;
import xox.labvorty.weaversparadise.model.BasicPlateModel;
import xox.labvorty.weaversparadise.renderers.helpers.ChokerTrinketRenderingData;
import xox.labvorty.weaversparadise.renderers.helpers.RenderingUtils;

public class PlateModelRenderer {
    public void renderModel(
            MultiBufferSource multiBufferSource,
            BasicPlateModel<?> model,
            ChokerTrinketRenderingData renderingData,
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
        ItemTexture itemTexture = TextureRegistry.find("plate", "default", renderingData.getMetalType());
        if (itemTexture == null) {
            itemTexture = TextureRegistry.find("plate", "default", "default");
        }

        int finalColor;
        if (itemTexture.getRenderType()) {
            finalColor = 255 << 24 | 255 << 16 | 255 << 8 | 255;
        } else {
            finalColor = renderingData.getColor();
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
                RenderType.entityTranslucent(itemTexture.getTextureOne())
        );
        Vector4f vector4f = new RenderingUtils().colorIntToVector4f(finalColor);
        model.renderToBuffer(
                poseStack,
                vertexConsumer,
                packedLight,
                OverlayTexture.NO_OVERLAY,
                vector4f.x,
                vector4f.y,
                vector4f.z,
                vector4f.w
        );

        poseStack.popPose();
    }
}
