package xox.labvorty.weaversparadise.renderers.models;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.entity.LivingEntity;
import xox.labvorty.weaversparadise.data.texture.ItemTexture;
import xox.labvorty.weaversparadise.data.texture.TextureRegistry;
import xox.labvorty.weaversparadise.model.FishModel;
import xox.labvorty.weaversparadise.renderers.helpers.ChokerTrinketRenderingData;

public class FishModelRenderer {
    public void renderModel(
            MultiBufferSource multiBufferSource,
            FishModel<?> model,
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
        ItemTexture itemTexture = TextureRegistry.find("fish", "default", renderingData.metalType());
        if (itemTexture == null) {
            itemTexture = TextureRegistry.find("fish", "default", "default");
        }
        int finalColor;
        if (itemTexture.getRenderType()) {
            finalColor = 255 << 24 | 255 << 16 | 255 << 8 | 255;
        } else {
            finalColor = renderingData.color();
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
