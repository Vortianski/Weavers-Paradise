package xox.labvorty.weaversparadise.renderers.curios;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.client.ICurioRenderer;
import xox.labvorty.weaversparadise.configs.ClientConfig;
import xox.labvorty.weaversparadise.items.clothing.UshankaItem;
import xox.labvorty.weaversparadise.model.UshankaModel;
import xox.labvorty.weaversparadise.renderers.helpers.DoubleSidedClothingRenderingData;
import xox.labvorty.weaversparadise.renderers.models.UshankaModelRenderer;
import xox.labvorty.weaversparadise.utilities.WeaversUtilities;

public class UshankaCurioRenderer implements ICurioRenderer {
    private final UshankaModel<?> model;

    public UshankaCurioRenderer() {
        this.model = new UshankaModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(UshankaModel.LAYER_LOCATION));
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
        LivingEntity entity = slotContext.entity();

        if (WeaversUtilities.isRestricted(entity, ClientConfig.HAT_RESTRICTOR.get(), EquipmentSlot.HEAD)) {
            return;
        }

        if (itemStack.getItem() instanceof UshankaItem ushankaItem) {
            M parentModel = renderLayerParent.getModel();
            if (parentModel instanceof HumanoidModel<?> humanoidModel) {
                this.model.Head.copyFrom(humanoidModel.head);
            }

            UshankaModelRenderer.renderModel(
                    multiBufferSource,
                    model,
                    new DoubleSidedClothingRenderingData(
                            ushankaItem.getFlag(itemStack),
                            ushankaItem.getItemMainColor(itemStack, "left", 1),
                            ushankaItem.getItemSecondaryColor(itemStack, "left", 1),
                            ushankaItem.getItemMainColor(itemStack, "right", 1),
                            ushankaItem.getItemSecondaryColor(itemStack, "right", 1),
                            ushankaItem.getItemMainColor(itemStack, "left", 2),
                            ushankaItem.getItemSecondaryColor(itemStack, "left", 2),
                            ushankaItem.getItemMainColor(itemStack, "right", 2),
                            ushankaItem.getItemSecondaryColor(itemStack, "right", 2),
                            ushankaItem.getItemDyeType(itemStack, "left", 1),
                            ushankaItem.getItemDyeType(itemStack, "right", 1),
                            ushankaItem.getItemDyeType(itemStack, "left", 2),
                            ushankaItem.getItemDyeType(itemStack, "right", 2),
                            ushankaItem.getStensilType(itemStack, "left"),
                            ushankaItem.getStensilType(itemStack, "right"),
                            ushankaItem.getItemLightValue(itemStack, "left", 1),
                            ushankaItem.getItemLightValue(itemStack, "left", 2),
                            ushankaItem.getItemLightValue(itemStack, "right", 1),
                            ushankaItem.getItemLightValue(itemStack, "right", 2),
                            "base",
                            itemStack.isEnchanted(),
                            ushankaItem.getGlintColor(itemStack),
                            ushankaItem.getAdditionalData(itemStack)
                    ),
                    entity,
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
