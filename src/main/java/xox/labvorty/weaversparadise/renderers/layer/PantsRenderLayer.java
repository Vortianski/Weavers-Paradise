package xox.labvorty.weaversparadise.renderers.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
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

public class PantsRenderLayer<T extends LivingEntity, M extends EntityModel<T>> extends RenderLayer<T, M> {
    private final PantsModel<?> model;

    public PantsRenderLayer(RenderLayerParent<T, M> parent) {
        super(parent);
        this.model = new PantsModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(PantsModel.LAYER_LOCATION));
    }

    @Override
    public void render(
            @NotNull PoseStack poseStack,
            @NotNull MultiBufferSource multiBufferSource,
            int packedLight,
            @NotNull T entity,
            float limbSwing,
            float limbSwingAmount,
            float partialTicks,
            float ageInTicks,
            float netHeadYaw,
            float headPitch
    ) {
        ItemStack itemStack = entity.getItemBySlot(EquipmentSlot.LEGS);

        if (WeaversUtilities.isRestricted(entity, ClientConfig.PANTS_RESTRICTOR.get(), EquipmentSlot.LEGS)) {
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

            EntityModel<?> entityModel = getParentModel();
            if (entityModel instanceof HumanoidModel<?> humanoidModel) {
                this.model.Body.copyFrom(humanoidModel.body);
                this.model.LeftLeg.copyFrom(humanoidModel.leftLeg);
                this.model.RightLeg.copyFrom(humanoidModel.rightLeg);
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
