package xox.labvorty.weaversparadise.blocks.entities.renderer;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.client.resources.SkinManager;
import net.minecraft.core.UUIDUtil;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import xox.labvorty.weaversparadise.blocks.PlushieBlock;
import xox.labvorty.weaversparadise.blocks.entities.PlushieBlockEntity;
import xox.labvorty.weaversparadise.model.SlimPlushieModel;
import xox.labvorty.weaversparadise.model.WidePlushieModel;

import java.util.Map;
import java.util.UUID;

public class PlushieBlockEntityRenderer implements BlockEntityRenderer<PlushieBlockEntity> {
    private final SlimPlushieModel<?> slimPlushieModel;
    private final WidePlushieModel<?> widePlushieModel;

    public PlushieBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        this.slimPlushieModel = new SlimPlushieModel<>(context.bakeLayer(SlimPlushieModel.LAYER_LOCATION));
        this.widePlushieModel = new WidePlushieModel<>(context.bakeLayer(WidePlushieModel.LAYER_LOCATION));
    }

    @Override
    public void render(
            @NotNull PlushieBlockEntity blockEntity,
            float partialTick,
            @NotNull PoseStack poseStack,
            @NotNull MultiBufferSource buffer,
            int packedLight,
            int packedOverlay
    ) {
        GameProfile profile = blockEntity.getProfile();
        if (profile == null) {
            return;
        }

        SkinManager skinManager = Minecraft.getInstance().getSkinManager();

        ResourceLocation skinTexture;
        boolean slim;

        Map<MinecraftProfileTexture.Type, MinecraftProfileTexture> textures = skinManager.getInsecureSkinInformation(profile);
        MinecraftProfileTexture skinInfo = textures.get(MinecraftProfileTexture.Type.SKIN);

        if (skinInfo != null) {
            skinTexture = skinManager.registerTexture(skinInfo, MinecraftProfileTexture.Type.SKIN);
            slim = "slim".equals(skinInfo.getMetadata("model"));
        } else {
            UUID uuid = UUIDUtil.getOrCreatePlayerUUID(profile);
            skinTexture = DefaultPlayerSkin.getDefaultSkin(uuid);
            slim = "slim".equals(DefaultPlayerSkin.getSkinModelName(uuid));
        }

        VertexConsumer vertexConsumer = buffer.getBuffer(RenderType.entityTranslucent(skinTexture));

        int rotationValue = blockEntity.getBlockState().getValue(PlushieBlock.ROTATION);
        float yRot = rotationValue * 360.0F / 16.0F;

        poseStack.pushPose();

        poseStack.translate(0.5, 0.75, 0.5);
        poseStack.mulPose(Axis.YP.rotationDegrees(-yRot));
        poseStack.scale(0.5F, -0.5F, 0.5F);

        if (slim) {
            this.slimPlushieModel.renderToBuffer(poseStack, vertexConsumer, packedLight, packedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
        } else {
            this.widePlushieModel.renderToBuffer(poseStack, vertexConsumer, packedLight, packedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
        }

        poseStack.popPose();
    }
}