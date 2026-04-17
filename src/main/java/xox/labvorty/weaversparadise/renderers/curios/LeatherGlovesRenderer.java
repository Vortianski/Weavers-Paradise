package xox.labvorty.weaversparadise.renderers.curios;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.client.ICurioRenderer;
import xox.labvorty.weaversparadise.model.HandWarmersModel;

public class LeatherGlovesRenderer implements ICurioRenderer {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath("weaversparadise", "textures/clothing/leather_gloves_clothing.png");
    private final HandWarmersModel model;

    public LeatherGlovesRenderer() {
        this.model = new HandWarmersModel(Minecraft.getInstance().getEntityModels().bakeLayer(HandWarmersModel.LAYER_LOCATION));
    }

    @Override
    public
    <T extends LivingEntity, M extends EntityModel<T>>
    void render(
            ItemStack stack,
            SlotContext slotContext,
            PoseStack matrixStack,
            RenderLayerParent<T, M> renderLayerParent,
            MultiBufferSource renderTypeBuffer,
            int light,
            float limbSwing,
            float limbSwingAmount,
            float partialTicks,
            float ageInTicks,
            float netHeadYaw,
            float headPitch
    ) {
        LivingEntity entity = slotContext.entity();
        VertexConsumer vertexconsumer = ItemRenderer.getArmorFoilBuffer(renderTypeBuffer, RenderType.armorCutoutNoCull(TEXTURE), stack.isEnchanted());

        M playerModel = renderLayerParent.getModel();
        if (playerModel instanceof HumanoidModel<?>) {
            HumanoidModel humModel = (HumanoidModel<?>) playerModel;

            this.model.RightArm.copyFrom(humModel.rightArm);
            this.model.LeftArm.copyFrom(humModel.leftArm);
        }

        this.model.renderToBuffer(matrixStack, vertexconsumer, light, OverlayTexture.NO_OVERLAY);
    }
}
