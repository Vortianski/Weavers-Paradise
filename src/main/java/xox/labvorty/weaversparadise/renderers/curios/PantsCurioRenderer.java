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
import xox.labvorty.weaversparadise.items.clothing.PantsCottonItem;
import xox.labvorty.weaversparadise.items.clothing.PantsJeansItem;
import xox.labvorty.weaversparadise.items.clothing.PantsSilkItem;
import xox.labvorty.weaversparadise.items.clothing.PantsWoolItem;
import xox.labvorty.weaversparadise.items.clothing.defined.PantsInterface;
import xox.labvorty.weaversparadise.model.PantsModel;
import xox.labvorty.weaversparadise.renderers.helpers.SingleSidedClothingRenderingData;
import xox.labvorty.weaversparadise.renderers.models.PantsModelRenderer;
import xox.labvorty.weaversparadise.utilities.WeaversUtilities;

public class PantsCurioRenderer implements ICurioRenderer {
    private final PantsModel<?> model;

    public PantsCurioRenderer() {
        this.model = new PantsModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(PantsModel.LAYER_LOCATION));
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

        if (WeaversUtilities.isRestricted(livingEntity, ClientConfig.PANTS_RESTRICTOR.get(), EquipmentSlot.LEGS)) {
            return;
        }

        if (itemStack.getItem() instanceof PantsInterface pantsInterface) {
            String material;

            switch (itemStack.getItem()) {
                case PantsCottonItem pantsCottonItem -> material = "cotton";
                case PantsSilkItem pantsSilkItem -> material = "silk";
                case PantsJeansItem pantsJeansItem -> material = "jeans";
                case PantsWoolItem pantsWoolItem -> material = "wool";
                default -> {
                    return;
                }
            }

            M parentModel = renderLayerParent.getModel();
            if (parentModel instanceof HumanoidModel<?> humanoidModel) {
                this.model.Body.copyFrom(humanoidModel.body);
                this.model.LeftLeg.copyFrom(humanoidModel.leftLeg);
                this.model.RightLeg.copyFrom(humanoidModel.rightLeg);
            } else {
                return;
            }

            PantsModelRenderer.renderModel(
                    multiBufferSource,
                    model,
                    new SingleSidedClothingRenderingData(
                            pantsInterface.getFlag(itemStack),
                            pantsInterface.getItemMainColor(itemStack, 1),
                            pantsInterface.getItemSecondaryColor(itemStack, 1),
                            pantsInterface.getItemMainColor(itemStack, 2),
                            pantsInterface.getItemSecondaryColor(itemStack, 2),
                            pantsInterface.getItemDyeType(itemStack, 1),
                            pantsInterface.getItemDyeType(itemStack, 2),
                            pantsInterface.getStensilType(itemStack),
                            pantsInterface.getItemLightValue(itemStack, 1),
                            pantsInterface.getItemLightValue(itemStack, 2),
                            material,
                            itemStack.isEnchanted(),
                            pantsInterface.getGlintColor(itemStack),
                            pantsInterface.getAdditionalData(itemStack)
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
