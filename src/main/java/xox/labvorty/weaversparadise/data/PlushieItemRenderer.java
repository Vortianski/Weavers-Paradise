package xox.labvorty.weaversparadise.data;

import com.mojang.authlib.GameProfile;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.client.resources.PlayerSkin;
import net.minecraft.client.resources.SkinManager;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ResolvableProfile;
import xox.labvorty.weaversparadise.curios.WeaversParadiseMobLayers;
import xox.labvorty.weaversparadise.items.PlushieItem;
import xox.labvorty.weaversparadise.model.SlimPlushieModel;
import xox.labvorty.weaversparadise.model.WidePlushieModel;

public class PlushieItemRenderer extends BlockEntityWithoutLevelRenderer {
    private final SlimPlushieModel slimPlushieModel;
    private final WidePlushieModel widePlushieModel;

    public PlushieItemRenderer(BlockEntityRenderDispatcher dispatcher, EntityModelSet entityModels) {
        super(dispatcher, entityModels);
        this.slimPlushieModel = new SlimPlushieModel(entityModels.bakeLayer(WeaversParadiseMobLayers.SLIM_PLUSHIE));
        this.widePlushieModel = new WidePlushieModel(entityModels.bakeLayer(WeaversParadiseMobLayers.WIDE_PLUSHIE));
    }

    @Override
    public void renderByItem(
            ItemStack stack,
            ItemDisplayContext displayContext,
            PoseStack poseStack,
            MultiBufferSource buffer,
            int packedLight,
            int packedOverlay
    ) {
        if (!(stack.getItem() instanceof PlushieItem plushieItem)) return;

        float scaleX = 1.0f;
        float scaleY = 1.0f;
        float scaleZ = 1.0f;

        float xT = 0;
        float yT = 1.75f;
        float zT = 0;

        float preXRot = 0;
        float preYRot = 0;
        float preZRot = 0;

        float postXrot = 0;
        float postYrot = 0;
        float postZrot = 0;

        switch (displayContext) {
            case GUI -> {
                scaleX = 0.5f;
                scaleY = 0.5f;
                scaleZ = 0.5f;
                xT = 0.55f;
                yT = 1;
                postXrot = 202.5f;
                postYrot = 45f;
            }
            case THIRD_PERSON_LEFT_HAND -> {
                scaleX = 0.5f;
                scaleY = 0.5f;
                scaleZ = 0.5f;
                xT = 0.6f;
                yT = 0.5f;
                zT = 0.25f;
                postXrot = 105;
                postYrot = 0;
                postZrot = 0;
            }
            case THIRD_PERSON_RIGHT_HAND -> {
                scaleX = 0.5f;
                scaleY = 0.5f;
                scaleZ = 0.5f;
                xT = 0.4f;
                yT = 0.5f;
                zT = 0.25f;
                postXrot = 105;
                postYrot = 0;
                postZrot = 0;
            }
            case FIRST_PERSON_RIGHT_HAND -> {
                scaleX = 0.5f;
                scaleY = 0.5f;
                scaleZ = 0.5f;
                xT = 0.6f;
                yT = 0.9f;
                zT = -0.25f;
                postXrot = 135f;
                postYrot = 0;
                postZrot = 0;
            }
            case FIRST_PERSON_LEFT_HAND -> {
                scaleX = 0.5f;
                scaleY = 0.5f;
                scaleZ = 0.5f;
                xT = 0.4f;
                yT = 0.9f;
                zT = -0.25f;
                postXrot = 135f;
                postYrot = 0;
                postZrot = 0;
            }
            case FIXED -> {
                scaleX = 0.5f;
                scaleY = 0.5f;
                scaleZ = 0.5f;
                xT = 0.5f;
                yT = 0.35f;
                zT = -0.2f;
                postXrot = 90f;
                postYrot = 0;
            }
            case GROUND -> {
                scaleX = 0.5f;
                scaleY = 0.5f;
                scaleZ = 0.5f;
                xT = 0.5f;
                yT = 1;
                zT = 0.5f;
                postXrot = 180f;
            }
        }

        Minecraft minecraft = Minecraft.getInstance();

        ResolvableProfile resolvableProfile = plushieItem.getProfile(stack);
        if (resolvableProfile == null || !resolvableProfile.isResolved()) {
            return;
        }

        GameProfile gameProfile = resolvableProfile.gameProfile();
        if (gameProfile == null) return;
        SkinManager skinManager = minecraft.getSkinManager();
        PlayerSkin skin = skinManager.getInsecureSkin(gameProfile);

        ResourceLocation skinTexture = skin.texture();
        PlayerSkin.Model model = skin.model();

        VertexConsumer vertexConsumer = buffer.getBuffer(RenderType.entityTranslucent(skinTexture));

        poseStack.pushPose();

        poseStack.scale(scaleX, scaleY, scaleZ);

        poseStack.mulPose(Axis.XP.rotationDegrees(preXRot));

        poseStack.mulPose(Axis.YP.rotationDegrees(preYRot));

        poseStack.mulPose(Axis.ZP.rotationDegrees(preZRot));

        poseStack.translate((xT) / scaleX, (yT) / scaleY, (zT) / scaleZ);

        poseStack.mulPose(Axis.XP.rotationDegrees(postXrot));

        poseStack.mulPose(Axis.YP.rotationDegrees(postYrot));

        poseStack.mulPose(Axis.ZP.rotationDegrees(postZrot));

        if (model == PlayerSkin.Model.SLIM) {
            this.slimPlushieModel.renderToBuffer(
                    poseStack,
                    vertexConsumer,
                    packedLight,
                    packedOverlay
            );
        } else {
            this.widePlushieModel.renderToBuffer(
                    poseStack,
                    vertexConsumer,
                    packedLight,
                    packedOverlay
            );
        }

        poseStack.popPose();
    }
}
