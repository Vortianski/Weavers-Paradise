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
import xox.labvorty.weaversparadise.items.clothing.PomponHatItem;
import xox.labvorty.weaversparadise.model.PomponHatModel;
import xox.labvorty.weaversparadise.renderers.helpers.SingleSidedClothingRenderingData;
import xox.labvorty.weaversparadise.renderers.models.PomponHatModelRenderer;
import xox.labvorty.weaversparadise.utilities.WeaversUtilities;

public class PomponHatCurioRenderer implements ICurioRenderer {
    private final PomponHatModel<?> model;

    public PomponHatCurioRenderer() {
        this.model = new PomponHatModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(PomponHatModel.LAYER_LOCATION));
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

        if (itemStack.getItem() instanceof PomponHatItem pomponHatItem) {
            M parentModel = renderLayerParent.getModel();
            if (parentModel instanceof HumanoidModel<?> humanoidModel) {
                this.model.Head.copyFrom(humanoidModel.head);
            } else {
                return;
            }

            PomponHatModelRenderer.renderModel(
                    multiBufferSource,
                    model,
                    new SingleSidedClothingRenderingData(
                            pomponHatItem.getFlag(itemStack),
                            pomponHatItem.getItemMainColor(itemStack, 1),
                            pomponHatItem.getItemSecondaryColor(itemStack, 1),
                            pomponHatItem.getItemMainColor(itemStack, 2),
                            pomponHatItem.getItemSecondaryColor(itemStack, 2),
                            pomponHatItem.getItemDyeType(itemStack, 1),
                            pomponHatItem.getItemDyeType(itemStack, 2),
                            pomponHatItem.getStensilType(itemStack),
                            pomponHatItem.getItemLightValue(itemStack, 1),
                            pomponHatItem.getItemLightValue(itemStack, 2),
                            "cotton",
                            itemStack.isEnchanted(),
                            pomponHatItem.getGlintColor(itemStack),
                            pomponHatItem.getAdditionalData(itemStack)
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
