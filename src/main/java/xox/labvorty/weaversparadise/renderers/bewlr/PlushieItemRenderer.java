package xox.labvorty.weaversparadise.renderers.bewlr;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.client.resources.SkinManager;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import xox.labvorty.weaversparadise.items.misc.PlushieItem;
import xox.labvorty.weaversparadise.models.SlimPlushieModel;
import xox.labvorty.weaversparadise.models.WidePlushieModel;

import java.util.Map;
import java.util.UUID;

public class PlushieItemRenderer extends BlockEntityWithoutLevelRenderer {

    private final SlimPlushieModel slimPlushieModel;
    private final WidePlushieModel widePlushieModel;

    public PlushieItemRenderer(BlockEntityRenderDispatcher dispatcher, EntityModelSet entityModels) {
        super(dispatcher, entityModels);
        this.slimPlushieModel = new SlimPlushieModel(entityModels.bakeLayer(SlimPlushieModel.LAYER_LOCATION));
        this.widePlushieModel = new WidePlushieModel(entityModels.bakeLayer(WidePlushieModel.LAYER_LOCATION));
    }

    @Override
    public void renderByItem(ItemStack stack, ItemDisplayContext context, PoseStack poseStack,
                             MultiBufferSource buffer, int light, int overlay) {

        if (!(stack.getItem() instanceof PlushieItem plushieItem)) return;

        // === TRANSFORMS (unchanged) ===
        float scale = 0.5f;
        float xT = 0.5f, yT = 1f, zT = 0.5f;
        float xRot = 0, yRot = 0, zRot = 0;

        switch (context) {
            case GUI -> { xT = 0.55f; yT = 1; xRot = 202.5f; yRot = 45f; }
            case THIRD_PERSON_LEFT_HAND -> { xT = 0.6f; yT = 0.5f; zT = 0.25f; xRot = 105; }
            case THIRD_PERSON_RIGHT_HAND -> { xT = 0.4f; yT = 0.5f; zT = 0.25f; xRot = 105; }
            case FIRST_PERSON_RIGHT_HAND -> { xT = 0.6f; yT = 0.9f; zT = -0.25f; xRot = 135f; }
            case FIRST_PERSON_LEFT_HAND -> { xT = 0.4f; yT = 0.9f; zT = -0.25f; xRot = 135f; }
            case FIXED -> { xT = 0.5f; yT = 0.35f; zT = -0.2f; xRot = 90f; }
            case GROUND -> { xT = 0.5f; yT = 1f; zT = 0.5f; xRot = 180f; }
        }

        // === PROFILE ===
        GameProfile profile = plushieItem.getProfile(stack);
        if (profile == null) return;

        Minecraft mc = Minecraft.getInstance();
        SkinManager skinManager = mc.getSkinManager();

        Map<MinecraftProfileTexture.Type, MinecraftProfileTexture> textures =
                skinManager.getInsecureSkinInformation(profile);

        ResourceLocation skinTexture;
        boolean isSlim = false;

        if (textures.containsKey(MinecraftProfileTexture.Type.SKIN)) {
            MinecraftProfileTexture texture = textures.get(MinecraftProfileTexture.Type.SKIN);

            skinTexture = skinManager.registerTexture(texture, MinecraftProfileTexture.Type.SKIN);

            String model = texture.getMetadata("model");
            isSlim = "slim".equals(model);
        } else {
            UUID uuid = profile.getId() != null ? profile.getId() : UUID.randomUUID();
            skinTexture = DefaultPlayerSkin.getDefaultSkin(uuid);
        }

        VertexConsumer vc = buffer.getBuffer(RenderType.entityTranslucent(skinTexture));

        poseStack.pushPose();

        poseStack.scale(scale, scale, scale);
        poseStack.translate(xT / scale, yT / scale, zT / scale);

        poseStack.mulPose(Axis.XP.rotationDegrees(xRot));
        poseStack.mulPose(Axis.YP.rotationDegrees(yRot));
        poseStack.mulPose(Axis.ZP.rotationDegrees(zRot));

        if (isSlim) {
            slimPlushieModel.renderToBuffer(poseStack, vc, light, OverlayTexture.NO_OVERLAY, 1, 1, 1, 1);
        } else {
            widePlushieModel.renderToBuffer(poseStack, vc, light, OverlayTexture.NO_OVERLAY, 1, 1, 1, 1);
        }

        poseStack.popPose();
    }
}
