package xox.labvorty.weaversparadise.renderers.models;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix4f;
import oshi.util.tuples.Pair;
import xox.labvorty.vortylib.mixin_helpers.FontAccessor;
import xox.labvorty.vortylib.mixin_helpers.GuiGraphicsAccessor;
import xox.labvorty.vortylib.utilities.CustomFont;
import xox.labvorty.weaversparadise.data.texture.ItemTexture;
import xox.labvorty.weaversparadise.data.texture.TextureRegistry;
import xox.labvorty.weaversparadise.model.UpperWearModel;
import xox.labvorty.weaversparadise.renderers.helpers.ColorHandlers;
import xox.labvorty.weaversparadise.renderers.helpers.RenderingUtils;
import xox.labvorty.weaversparadise.renderers.helpers.SingleSidedClothingRenderingData;

public class UpperwearModelRenderer {
    public static void renderModel(
            MultiBufferSource multiBufferSource,
            UpperWearModel<?> model,
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
            int packedLight,
            Type type
    ) {
        RenderingUtils renderingUtils = new RenderingUtils();
        Minecraft minecraft = Minecraft.getInstance();
        int ticks = 0;
        if (minecraft.level != null) {
            ticks = (int)minecraft.level.getGameTime();
        }

        Pair<Integer, Integer> fCO = ColorHandlers.handle(renderingData.dTO(), renderingData.pCO(), renderingData.sCO(), renderingData.lVO(), livingEntity, packedLight, ticks);
        Pair<Integer, Integer> fCT = ColorHandlers.handle(renderingData.dTT(), renderingData.pCT(), renderingData.sCT(), renderingData.lVT(), livingEntity, packedLight, ticks);

        ItemTexture itemTexture = TextureRegistry.find(type.getSerializedName(), renderingData.sT(), renderingData.mat());
        if (renderingData.flag() && type.getSerializedName().equals("shirt")) {
            itemTexture = TextureRegistry.find("shirt_open", renderingData.sT(), renderingData.mat());
        } else if (renderingData.flag() && type.getSerializedName().equals("crop_top_long_sleeved")) {
            itemTexture = TextureRegistry.find("crop_top_long_sleeved_sag", renderingData.sT(), renderingData.mat());
        }
        ResourceLocation tex1 = itemTexture.getTextureOne();
        ResourceLocation tex2 = itemTexture.getTextureTwo();
        boolean renderType = itemTexture.getRenderType();

        poseStack.pushPose();

        poseStack.scale(scaleX, scaleY, scaleZ);

        poseStack.mulPose(Axis.XP.rotationDegrees(xRotPre));

        poseStack.mulPose(Axis.YP.rotationDegrees(yRotPre));

        poseStack.mulPose(Axis.ZP.rotationDegrees(zRotPre));

        poseStack.translate((xT) / scaleX, (yT) / scaleY, (zT) / scaleZ);

        poseStack.mulPose(Axis.XP.rotationDegrees(xRot));

        poseStack.mulPose(Axis.YP.rotationDegrees(yRot));

        poseStack.mulPose(Axis.ZP.rotationDegrees(zRot));

        VertexConsumer vc1 = renderingUtils.parseVC(multiBufferSource, renderingData.dTO(), tex1, type.getSerializedName(), renderingData.glint(), renderingData.glintColor());
        model.renderToBuffer(
                poseStack,
                vc1,
                fCO.getB(),
                OverlayTexture.NO_OVERLAY,
                fCO.getA()
        );

        if (renderType) {
            VertexConsumer vc2 = renderingUtils.parseVC(multiBufferSource, renderingData.dTT(), tex2, type.getSerializedName(), renderingData.glint(), renderingData.glintColor());
            model.renderToBuffer(
                    poseStack,
                    vc2,
                    fCT.getB(),
                    OverlayTexture.NO_OVERLAY,
                    fCT.getA()
            );
        }

        CompoundTag compoundTag = renderingData.additionalData();

        if (compoundTag.getBoolean("customText")) {
            renderText(
                    multiBufferSource,
                    poseStack,
                    compoundTag.getString("custom_text_l1"),
                    packedLight,
                    compoundTag.getInt("custom_text_c1"),
                    0.0F,
                    0.15F,
                    -0.16F,
                    0.02F
            );
            renderText(
                    multiBufferSource,
                    poseStack,
                    compoundTag.getString("custom_text_l2"),
                    packedLight,
                    compoundTag.getInt("custom_text_c2"),
                    0.0F,
                    0.275F,
                    -0.16F,
                    0.02F
            );
        }

        poseStack.popPose();
    }

    private static void renderText(
            MultiBufferSource multiBufferSource,
            PoseStack poseStack,
            String text,
            int packedLight,
            int color,
            float x,
            float y,
            float z,
            float scale
    ) {
        Font font = Minecraft.getInstance().font;
        FontAccessor fontAccessor = (FontAccessor) font;
        CustomFont customFont = new CustomFont(
                fontAccessor.getFonts(),
                fontAccessor.filterFishyGlyphs()
        );

        poseStack.pushPose();

        poseStack.translate(x, y, z);

        float textScale = scale;
        if (text.length() > 4) {
            textScale *= 4.0F / text.length();
        }

        poseStack.scale(textScale, -textScale, textScale);
        poseStack.mulPose(Axis.ZP.rotationDegrees(180.0F));
        poseStack.mulPose(Axis.YP.rotationDegrees(180.0F));

        Matrix4f matrix = poseStack.last().pose();
        int width = font.width(text);

        customFont.drawInBatch(
                text,
                -width / 2.0F,
                0.0F,
                color,
                false,
                matrix,
                multiBufferSource,
                0,
                packedLight,
                true
        );

        poseStack.popPose();
    }

    public enum Type implements StringRepresentable {
        TOPS,
        SHIRT,
        PULLOVER,
        PONCHO,
        CROP_TOP_LONG_SLEEVED;

        @Override
        public @NotNull String getSerializedName() {
            return this.name().toLowerCase();
        }
    }
}
