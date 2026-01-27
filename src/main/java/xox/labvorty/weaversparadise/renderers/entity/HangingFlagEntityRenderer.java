package xox.labvorty.weaversparadise.renderers.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import xox.labvorty.weaversparadise.entities.HangingFlagEntity;

import java.util.List;

public class HangingFlagEntityRenderer extends EntityRenderer<HangingFlagEntity> {
    List<String> variants = List.of(
            "agender",
            "aroace",
            "aromantic",
            "asexual",
            "bisexual",
            "demiboy",
            "demigender",
            "demigirl",
            "gay",
            "genderfluid",
            "genderqueer",
            "intersex",
            "lesbian",
            "nonbinary",
            "pansexual",
            "pride",
            "trans"
    );
    private static final int TEX_W = 32;
    private static final int TEX_H = 16;
    private static final float PIXEL = 1.0F / 16.0F;
    private static final float DEPTH = 0.03125F;

    public HangingFlagEntityRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    public void render(HangingFlagEntity entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        poseStack.pushPose();
        poseStack.mulPose(Axis.YP.rotationDegrees(180.0F - entityYaw));
        VertexConsumer vertexconsumer = buffer.getBuffer(RenderType.entitySolid(this.getTextureLocation(entity)));
        this.renderFlag(entity, poseStack, vertexconsumer, packedLight);
        poseStack.popPose();
        super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(HangingFlagEntity hangingFlagEntity) {
        String variant = hangingFlagEntity.getVariant();

        if (!variants.contains(variant)) {
            variant = "default";
        }

        return ResourceLocation.fromNamespaceAndPath("weaversparadise", "textures/entity/" + variant + ".png");
    }

    private void renderFlag(HangingFlagEntity flag, PoseStack poseStack, VertexConsumer consumer, int light) {
        PoseStack.Pose pose = poseStack.last();

        float halfW = (TEX_W * PIXEL) / 2.0F;
        float halfH = (TEX_H * PIXEL) / 2.0F;

        float frontZ = -DEPTH;
        float backZ = DEPTH;

        vertex(pose, consumer,  halfW, -halfH, 1.0F, 1.0F, frontZ, 0, 0, -1, light);
        vertex(pose, consumer, -halfW, -halfH, 0.0F, 1.0F, frontZ, 0, 0, -1, light);
        vertex(pose, consumer, -halfW,  halfH, 0.0F, 0.0F, frontZ, 0, 0, -1, light);
        vertex(pose, consumer,  halfW,  halfH, 1.0F, 0.0F, frontZ, 0, 0, -1, light);

        vertex(pose, consumer,  halfW,  halfH, 1.0F, 0.0F, backZ, 0, 0, 1, light);
        vertex(pose, consumer, -halfW,  halfH, 0.0F, 0.0F, backZ, 0, 0, 1, light);
        vertex(pose, consumer, -halfW, -halfH, 0.0F, 1.0F, backZ, 0, 0, 1, light);
        vertex(pose, consumer,  halfW, -halfH, 1.0F, 1.0F, backZ, 0, 0, 1, light);

        float uLeft0 = 0.0F;
        float uLeft1 = 1.0F / TEX_W;
        float uRight0 = (TEX_W - 1.0F) / TEX_W;
        float uRight1 = 1.0F;
        float vTop0 = 0.0F;
        float vTop1 = 1.0F / TEX_H;
        float vBottom0 = (TEX_H - 1.0F) / TEX_H;
        float vBottom1 = 1.0F;

        vertex(pose, consumer, halfW,  halfH, uRight0, 0.0F, backZ, -1, 0, 0, light);
        vertex(pose, consumer, halfW, -halfH, uRight0, 1.0F, backZ, -1, 0, 0, light);
        vertex(pose, consumer, halfW, -halfH, uRight1, 1.0F, frontZ, -1, 0, 0, light);
        vertex(pose, consumer, halfW,  halfH, uRight1, 0.0F, frontZ, -1, 0, 0, light);

        vertex(pose, consumer, -halfW,  halfH, uLeft1, 0.0F, frontZ, 1, 0, 0, light);
        vertex(pose, consumer, -halfW, -halfH, uLeft1, 1.0F, frontZ, 1, 0, 0, light);
        vertex(pose, consumer, -halfW, -halfH, uLeft0, 1.0F, backZ, 1, 0, 0, light);
        vertex(pose, consumer, -halfW,  halfH, uLeft0, 0.0F, backZ, 1, 0, 0, light);

        vertex(pose, consumer,  halfW,  halfH, 1.0F, vTop1, frontZ, 0, -1, 0, light);
        vertex(pose, consumer, -halfW,  halfH, 0.0F, vTop1, frontZ, 0, -1, 0, light);
        vertex(pose, consumer, -halfW,  halfH, 0.0F, vTop0, backZ, 0, -1, 0, light);
        vertex(pose, consumer,  halfW,  halfH, 1.0F, vTop0, backZ, 0, -1, 0, light);

        vertex(pose, consumer,  halfW, -halfH, 1.0F, vBottom0, backZ, 0, 1, 0, light);
        vertex(pose, consumer, -halfW, -halfH, 0.0F, vBottom0, backZ, 0, 1, 0, light);
        vertex(pose, consumer, -halfW, -halfH, 0.0F, vBottom1, frontZ, 0, 1, 0, light);
        vertex(pose, consumer,  halfW, -halfH, 1.0F, vBottom1, frontZ, 0, 1, 0, light);
    }

    private void vertex(PoseStack.Pose pose, VertexConsumer consumer, float x, float y, float u, float v, float z, int normalX, int normalY, int normalZ, int packedLight) {
        consumer.addVertex(pose, x, y, z).setColor(-1).setUv(u, v).setOverlay(OverlayTexture.NO_OVERLAY).setLight(packedLight).setNormal(pose, (float)normalX, (float)normalY, (float)normalZ);
    }
}
