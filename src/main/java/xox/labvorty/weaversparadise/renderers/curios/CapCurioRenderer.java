package xox.labvorty.weaversparadise.renderers.curios;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.client.ICurioRenderer;
import xox.labvorty.weaversparadise.configs.ClientConfig;
import xox.labvorty.weaversparadise.items.clothing.*;
import xox.labvorty.weaversparadise.model.CapModel;
import xox.labvorty.weaversparadise.renderers.helpers.SingleSidedClothingRenderingData;
import xox.labvorty.weaversparadise.renderers.models.CapModelRenderer;
import xox.labvorty.weaversparadise.utilities.WeaversUtilities;

public class CapCurioRenderer implements ICurioRenderer {
    private final CapModel<?> model;

    public CapCurioRenderer() {
        this.model = new CapModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(CapModel.LAYER_LOCATION));
    }


    @Override
    public <T extends LivingEntity, M extends EntityModel<T>> void render(
            ItemStack itemStack,
            SlotContext slotContext,
            PoseStack poseStack,
            RenderLayerParent<T, M> renderLayerParent,
            MultiBufferSource multiBufferSource,
            int packedLight,
            float limbSwing,
            float limbSwingAmount,
            float partialTicks,
            float ageInTicks,
            float netHeadYaw,
            float headPitch
    ) {
        LivingEntity livingEntity = slotContext.entity();

        if (WeaversUtilities.isRestricted(livingEntity, ClientConfig.HAT_RESTRICTOR.get())) {
            return;
        }

        if (itemStack.getItem() instanceof CapItem capItem) {
            M parentModel = renderLayerParent.getModel();
            if (parentModel instanceof HumanoidModel<?> humanoidModel) {
                this.model.Head.copyFrom(humanoidModel.head);
            } else {
                return;
            }

            CapModelRenderer.renderModel(
                    multiBufferSource,
                    model,
                    new SingleSidedClothingRenderingData(
                            capItem.getFlag(itemStack),
                            capItem.getItemMainColor(itemStack, 1),
                            capItem.getItemSecondaryColor(itemStack, 1),
                            capItem.getItemMainColor(itemStack, 2),
                            capItem.getItemSecondaryColor(itemStack, 2),
                            capItem.getItemDyeType(itemStack, 1),
                            capItem.getItemDyeType(itemStack, 2),
                            capItem.getStensilType(itemStack),
                            capItem.getItemLightValue(itemStack, 1),
                            capItem.getItemLightValue(itemStack, 2),
                            "jeans",
                            itemStack.isEnchanted(),
                            capItem.getGlintColor(itemStack),
                            capItem.getAdditionalData(itemStack)
                    ),
                    livingEntity,
                    1,
                    1,
                    1,
                    0,
                    0,
                    0,
                    0,
                    0,
                    0,
                    0,
                    0,
                    0,
                    poseStack,
                    packedLight
            );
        }
    }
}
