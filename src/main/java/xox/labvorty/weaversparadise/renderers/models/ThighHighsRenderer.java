package xox.labvorty.weaversparadise.renderers.models;

import xox.labvorty.weaversparadise.curios.WeaversParadiseMobLayers;
import xox.labvorty.weaversparadise.model.ThighHighsModel;
import top.theillusivec4.curios.api.client.ICurioRenderer;
import top.theillusivec4.curios.api.SlotContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.Minecraft;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.HumanoidModel;

public class ThighHighsRenderer implements ICurioRenderer {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath("weaversparadise", "textures/clothing/thigh_highs_base.png");
    private final ThighHighsModel model;

    public ThighHighsRenderer() {
        this.model = new ThighHighsModel(Minecraft.getInstance().getEntityModels().bakeLayer(WeaversParadiseMobLayers.THIGH_HIGHS));
    }

    @Override
    public <T extends LivingEntity, M extends EntityModel<T>> void render(ItemStack stack, SlotContext slotContext, PoseStack matrixStack, RenderLayerParent<T, M> renderLayerParent, MultiBufferSource renderTypeBuffer, int light, float limbSwing,
                                                                          float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {

        LivingEntity entity = slotContext.entity();
        this.model.prepareMobModel(entity, limbSwing, limbSwingAmount, partialTicks);
        this.model.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        VertexConsumer vertexconsumer = ItemRenderer.getArmorFoilBuffer(renderTypeBuffer, RenderType.armorCutoutNoCull(TEXTURE), false);

        VertexConsumer consumerOne = renderTypeBuffer.getBuffer(RenderType.armorCutoutNoCull(ResourceLocation.fromNamespaceAndPath("weaversparadise", "textures/clothing/thigh_highs_base.png")));
        VertexConsumer consumerTwo = renderTypeBuffer.getBuffer(RenderType.armorCutoutNoCull(ResourceLocation.fromNamespaceAndPath("weaversparadise", "textures/clothing/thigh_highs_base.png")));

        M playerModel = renderLayerParent.getModel();
        if (playerModel instanceof HumanoidModel<?>) {
            HumanoidModel humModel = (HumanoidModel<?>)playerModel;

            this.model.RightLeg.copyFrom(humModel.rightLeg);
            this.model.LeftLeg.copyFrom(humModel.leftLeg);
        }

        int colorOne = 255 << 24 | 255 << 16 | 0 << 8 | 0;
        int colorTwo = 255 << 24 | 255 << 16 | 0 << 8 | 255;

        if (true) {
            this.model.RightLeg.render(matrixStack, consumerOne, light, OverlayTexture.NO_OVERLAY, colorOne);
            this.model.LeftLeg.render(matrixStack, consumerTwo, light, OverlayTexture.NO_OVERLAY, colorTwo);
        } else {
            this.model.renderToBuffer(matrixStack, vertexconsumer, light, OverlayTexture.NO_OVERLAY);
        }
    }
}
