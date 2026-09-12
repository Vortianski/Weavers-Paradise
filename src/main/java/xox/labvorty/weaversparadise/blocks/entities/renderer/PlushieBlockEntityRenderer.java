package xox.labvorty.weaversparadise.blocks.entities.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.resources.PlayerSkin;
import net.minecraft.client.resources.SkinManager;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.component.ResolvableProfile;
import org.jetbrains.annotations.NotNull;
import xox.labvorty.weaversparadise.blocks.PlushieBlock;
import xox.labvorty.weaversparadise.blocks.entities.PlushieBlockEntity;
import xox.labvorty.weaversparadise.model.SlimPlushieModel;
import xox.labvorty.weaversparadise.model.WidePlushieModel;

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
        ResolvableProfile profile = blockEntity.getProfile();
        if (profile == null || !profile.isResolved()) {
            return;
        }

        Minecraft minecraft = Minecraft.getInstance();
        SkinManager skinManager = minecraft.getSkinManager();
        PlayerSkin skin = skinManager.getInsecureSkin(profile.gameProfile());

        ResourceLocation skinTexture = skin.texture();
        PlayerSkin.Model model = skin.model();

        VertexConsumer vertexConsumer = buffer.getBuffer(RenderType.entityTranslucent(skinTexture));

        int rotationValue = blockEntity.getBlockState().getValue(PlushieBlock.ROTATION);
        float yRot = rotationValue * 360.0F / 16.0F;

        poseStack.pushPose();

        poseStack.translate(0.5, 0.75, 0.5);
        poseStack.mulPose(Axis.YP.rotationDegrees(-yRot));
        poseStack.scale(0.5F, -0.5F, 0.5F);

        if (model == PlayerSkin.Model.SLIM) {
            this.slimPlushieModel.renderToBuffer(poseStack, vertexConsumer, packedLight, packedOverlay);
        } else {
            this.widePlushieModel.renderToBuffer(poseStack, vertexConsumer, packedLight, packedOverlay);
        }

        poseStack.popPose();
    }
}