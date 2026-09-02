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
import xox.labvorty.weaversparadise.items.clothing.ChokerItem;
import xox.labvorty.weaversparadise.model.ChokerModel;
import xox.labvorty.weaversparadise.renderers.helpers.DoubleSidedClothingRenderingData;
import xox.labvorty.weaversparadise.renderers.models.ChokerModelRenderer;
import xox.labvorty.weaversparadise.utilities.WeaversUtilities;

public class ChokerCurioRenderer implements ICurioRenderer {
    private final ChokerModel<?> model;

    public ChokerCurioRenderer() {
        this.model = new ChokerModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(ChokerModel.LAYER_LOCATION));
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

        if (WeaversUtilities.isRestricted(entity, ClientConfig.CHOKER_RESTRICTOR.get(), EquipmentSlot.CHEST)) {
            return;
        }

        if (itemStack.getItem() instanceof ChokerItem chokerItem) {
            M parentModel = renderLayerParent.getModel();
            if (parentModel instanceof HumanoidModel<?> humanoidModel) {
                this.model.Body.copyFrom(humanoidModel.body);
            }

            ChokerModelRenderer.renderModel(
                    multiBufferSource,
                    model,
                    new DoubleSidedClothingRenderingData(
                            chokerItem.getFlag(itemStack),
                            chokerItem.getItemMainColor(itemStack, "left", 1),
                            chokerItem.getItemSecondaryColor(itemStack, "left", 1),
                            chokerItem.getItemMainColor(itemStack, "right", 1),
                            chokerItem.getItemSecondaryColor(itemStack, "right", 1),
                            chokerItem.getItemMainColor(itemStack, "left", 2),
                            chokerItem.getItemSecondaryColor(itemStack, "left", 2),
                            chokerItem.getItemMainColor(itemStack, "right", 2),
                            chokerItem.getItemSecondaryColor(itemStack, "right", 2),
                            chokerItem.getItemDyeType(itemStack, "left", 1),
                            chokerItem.getItemDyeType(itemStack, "right", 1),
                            chokerItem.getItemDyeType(itemStack, "left", 2),
                            chokerItem.getItemDyeType(itemStack, "right", 2),
                            chokerItem.getStensilType(itemStack, "left"),
                            chokerItem.getStensilType(itemStack, "right"),
                            chokerItem.getItemLightValue(itemStack, "left", 1),
                            chokerItem.getItemLightValue(itemStack, "left", 2),
                            chokerItem.getItemLightValue(itemStack, "right", 1),
                            chokerItem.getItemLightValue(itemStack, "right", 2),
                            "base",
                            itemStack.isEnchanted(),
                            chokerItem.getGlintColor(itemStack),
                            chokerItem.getAdditionalData(itemStack)
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
