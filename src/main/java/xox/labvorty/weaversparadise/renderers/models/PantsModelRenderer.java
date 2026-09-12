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
import xox.labvorty.weaversparadise.renderers.helpers.RenderingUtils;
import xox.labvorty.weaversparadise.renderers.helpers.SingleSidedClothingRenderingData;

public class PantsModelRenderer {
    public static void renderModel(
            MultiBufferSource multiBufferSource,
            PantsModel<?> model,
            SingleSidedClothingRenderingData renderingData,
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
        RenderingUtils renderingUtils = new RenderingUtils();
        Minecraft minecraft = Minecraft.getInstance();
        int ticks = 0;
        if (minecraft.level != null) {
            ticks = (int)minecraft.level.getGameTime();
        }

        Pair<Integer, Integer> fCO = ColorHandlers.handle(renderingData.dTO(), renderingData.pCO(), renderingData.sCO(), renderingData.lVO(), livingEntity, packedLight, ticks);
        Pair<Integer, Integer> fCT = ColorHandlers.handle(renderingData.dTT(), renderingData.pCT(), renderingData.sCT(), renderingData.lVT(), livingEntity, packedLight, ticks);

        ItemTexture texture = TextureRegistry.find("pants", renderingData.sT(), renderingData.mat());
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

        VertexConsumer vc1 = renderingUtils.parseVC(multiBufferSource, renderingData.dTO(), tex1, "pants", renderingData.glint(), renderingData.glintColor());
        model.renderToBuffer(
                poseStack,
                vc1,
                fCO.getB(),
                OverlayTexture.NO_OVERLAY,
                fCO.getA()
        );

        if (renderType) {
            VertexConsumer vc2 = renderingUtils.parseVC(multiBufferSource, renderingData.dTT(), tex2, "pants", renderingData.glint(), renderingData.glintColor());
            model.renderToBuffer(
                    poseStack,
                    vc2,
                    fCT.getB(),
                    OverlayTexture.NO_OVERLAY,
                    fCT.getA()
            );
        }

        poseStack.popPose();
    }
}
