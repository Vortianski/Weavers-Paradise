package xox.labvorty.weaversparadise.renderers.curios;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.client.TrinketRenderer;
import xox.labvorty.weaversparadise.configs.ClientConfig;
import xox.labvorty.weaversparadise.model.HandWarmersModel;
import xox.labvorty.weaversparadise.utilities.WeaversUtilities;

public class LeatherGlovesRenderer implements TrinketRenderer {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath("weaversparadise", "textures/clothing/leather_gloves_clothing.png");
    private final HandWarmersModel<?> model;

    public LeatherGlovesRenderer() {
        this.model = new HandWarmersModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(HandWarmersModel.LAYER_LOCATION));
    }

    @Override
    public void render(
            ItemStack itemStack,
            SlotReference slotReference,
            EntityModel<? extends LivingEntity> contextModel,
            PoseStack matrixStack,
            MultiBufferSource renderTypeBuffer,
            int light,
            LivingEntity entity,
            float limbSwing,
            float limbSwingAmount,
            float partialTicks,
            float ageInTicks,
            float netHeadYaw,
            float headPitch
    ) {
        VertexConsumer vertexconsumer = ItemRenderer.getArmorFoilBuffer(renderTypeBuffer, RenderType.armorCutoutNoCull(TEXTURE), itemStack.isEnchanted());

        if (WeaversUtilities.isRestricted(entity, ClientConfig.HAND_WARMERS_RESTRICTOR.get(), EquipmentSlot.CHEST)) {
            return;
        }

        if (contextModel instanceof HumanoidModel<?> humanoidModel) {
            this.model.RightArm.copyFrom(humanoidModel.rightArm);
            this.model.LeftArm.copyFrom(humanoidModel.leftArm);
        }

        this.model.renderToBuffer(matrixStack, vertexconsumer, light, OverlayTexture.NO_OVERLAY);
    }
}
