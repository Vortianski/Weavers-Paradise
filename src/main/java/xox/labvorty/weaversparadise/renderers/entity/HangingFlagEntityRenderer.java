package xox.labvorty.weaversparadise.renderers.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import xox.labvorty.weaversparadise.entities.HangingFlagEntity;

import java.util.List;
import java.util.Set;

public class HangingFlagEntityRenderer extends EntityRenderer<HangingFlagEntity> {
    private static final Set<String> VARIANTS = Set.of(
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
            "trans",
            "default"
    );

    private static final int TEX_W = 32;
    private static final int TEX_H = 16;
    private static final float PIXEL = 1.0F / 16.0F;
    private static final float DEPTH = 0.03125F;

    public HangingFlagEntityRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.shadowRadius = 0.0F;
    }

    @Override
    public void render(HangingFlagEntity entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        poseStack.pushPose();

        Direction direction = entity.getDirection();
        if (direction != null) {
            poseStack.mulPose(Axis.YP.rotationDegrees(180.0F - direction.toYRot()));
        }

        VertexConsumer consumer = buffer.getBuffer(RenderType.entityCutoutNoCull(this.getTextureLocation(entity)));
        this.renderFlag(poseStack, consumer, packedLight);

        poseStack.popPose();
        super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(HangingFlagEntity entity) {
        String variant = entity.getVariant();
        if (!VARIANTS.contains(variant)) {
            variant = "default";
        }

        return new ResourceLocation("weaversparadise", "textures/entity/" + variant + ".png");
    }

    private void renderFlag(PoseStack poseStack, VertexConsumer consumer, int light) {
        PoseStack.Pose pose = poseStack.last();

        float halfW = (TEX_W * PIXEL) / 2.0F;
        float halfH = (TEX_H * PIXEL) / 2.0F;

        float frontZ = -DEPTH;
        float backZ = DEPTH;

        // Front
        vertex(pose, consumer,  halfW, -halfH, frontZ, 1.0F, 1.0F,  0,  0, -1, light);
        vertex(pose, consumer, -halfW, -halfH, frontZ, 0.0F, 1.0F,  0,  0, -1, light);
        vertex(pose, consumer, -halfW,  halfH, frontZ, 0.0F, 0.0F,  0,  0, -1, light);
        vertex(pose, consumer,  halfW,  halfH, frontZ, 1.0F, 0.0F,  0,  0, -1, light);

        // Back
        vertex(pose, consumer,  halfW,  halfH, backZ, 1.0F, 0.0F,  0,  0, 1, light);
        vertex(pose, consumer, -halfW,  halfH, backZ, 0.0F, 0.0F,  0,  0, 1, light);
        vertex(pose, consumer, -halfW, -halfH, backZ, 0.0F, 1.0F,  0,  0, 1, light);
        vertex(pose, consumer,  halfW, -halfH, backZ, 1.0F, 1.0F,  0,  0, 1, light);

        float uLeft0 = 0.0F;
        float uLeft1 = 1.0F / TEX_W;
        float uRight0 = (TEX_W - 1.0F) / TEX_W;
        float uRight1 = 1.0F;
        float vTop0 = 0.0F;
        float vTop1 = 1.0F / TEX_H;
        float vBottom0 = (TEX_H - 1.0F) / TEX_H;
        float vBottom1 = 1.0F;

        // Right edge
        vertex(pose, consumer, halfW,  halfH, backZ,  uRight0, 0.0F, -1, 0, 0, light);
        vertex(pose, consumer, halfW, -halfH, backZ,  uRight0, 1.0F, -1, 0, 0, light);
        vertex(pose, consumer, halfW, -halfH, frontZ, uRight1, 1.0F, -1, 0, 0, light);
        vertex(pose, consumer, halfW,  halfH, frontZ, uRight1, 0.0F, -1, 0, 0, light);

        // Left edge
        vertex(pose, consumer, -halfW,  halfH, frontZ, uLeft1, 0.0F, 1, 0, 0, light);
        vertex(pose, consumer, -halfW, -halfH, frontZ, uLeft1, 1.0F, 1, 0, 0, light);
        vertex(pose, consumer, -halfW, -halfH, backZ,  uLeft0, 1.0F, 1, 0, 0, light);
        vertex(pose, consumer, -halfW,  halfH, backZ,  uLeft0, 0.0F, 1, 0, 0, light);

        // Top edge
        vertex(pose, consumer,  halfW,  halfH, frontZ, 1.0F, vTop1, 0, -1, 0, light);
        vertex(pose, consumer, -halfW,  halfH, frontZ, 0.0F, vTop1, 0, -1, 0, light);
        vertex(pose, consumer, -halfW,  halfH, backZ,  0.0F, vTop0, 0, -1, 0, light);
        vertex(pose, consumer,  halfW,  halfH, backZ,  1.0F, vTop0, 0, -1, 0, light);

        // Bottom edge
        vertex(pose, consumer,  halfW, -halfH, backZ,  1.0F, vBottom0, 0, 1, 0, light);
        vertex(pose, consumer, -halfW, -halfH, backZ,  0.0F, vBottom0, 0, 1, 0, light);
        vertex(pose, consumer, -halfW, -halfH, frontZ, 0.0F, vBottom1, 0, 1, 0, light);
        vertex(pose, consumer,  halfW, -halfH, frontZ, 1.0F, vBottom1, 0, 1, 0, light);
    }

    private void vertex(PoseStack.Pose pose, VertexConsumer consumer,
                        float x, float y, float z,
                        float u, float v,
                        int normalX, int normalY, int normalZ,
                        int packedLight) {
        consumer.vertex(pose.pose(), x, y, z)
                .color(255, 255, 255, 255)
                .uv(u, v)
                .overlayCoords(OverlayTexture.NO_OVERLAY)
                .uv2(packedLight)
                .normal(pose.normal(), normalX, normalY, normalZ)
                .endVertex();
    }
}
