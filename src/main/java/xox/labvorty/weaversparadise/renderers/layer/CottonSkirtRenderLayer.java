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
import xox.labvorty.weaversparadise.items.clothing.*;
import xox.labvorty.weaversparadise.model.CottonSkirtModel;
import xox.labvorty.weaversparadise.model.PantsModel;
import xox.labvorty.weaversparadise.renderers.helpers.DoubleSidedClothingRenderingData;
import xox.labvorty.weaversparadise.renderers.helpers.SingleSidedClothingRenderingData;
import xox.labvorty.weaversparadise.renderers.models.CottonSkirtModelRenderer;
import xox.labvorty.weaversparadise.renderers.models.PantsModelRenderer;
import xox.labvorty.weaversparadise.utilities.WeaversUtilities;

public class CottonSkirtRenderLayer<T extends LivingEntity, M extends EntityModel<T>> extends RenderLayer<T, M> {
    private final CottonSkirtModel<?> model;

    public CottonSkirtRenderLayer(RenderLayerParent<T, M> parent) {
        super(parent);
        this.model = new CottonSkirtModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(CottonSkirtModel.LAYER_LOCATION));
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

        if (WeaversUtilities.isRestricted(entity, ClientConfig.PANTS_RESTRICTOR.get())) {
            return;
        }

        if (itemStack.getItem() instanceof SkirtCottonItem skirtCottonItem) {
            String material;


            EntityModel<?> entityModel = getParentModel();
            if (entityModel instanceof HumanoidModel<?> humanoidModel) {
                this.model.Body.copyFrom(humanoidModel.body);
            }

            CottonSkirtModelRenderer.renderModel(
                    multiBufferSource,
                    model,
                    new DoubleSidedClothingRenderingData(
                            skirtCottonItem.getFlag(itemStack),
                            skirtCottonItem.getItemMainColor(itemStack, "left", 1),
                            skirtCottonItem.getItemSecondaryColor(itemStack, "left", 1),
                            skirtCottonItem.getItemMainColor(itemStack, "right", 1),
                            skirtCottonItem.getItemSecondaryColor(itemStack, "right", 1),
                            skirtCottonItem.getItemMainColor(itemStack, "left", 2),
                            skirtCottonItem.getItemSecondaryColor(itemStack, "left", 2),
                            skirtCottonItem.getItemMainColor(itemStack, "right", 2),
                            skirtCottonItem.getItemSecondaryColor(itemStack, "right", 2),
                            skirtCottonItem.getItemDyeType(itemStack, "left", 1),
                            skirtCottonItem.getItemDyeType(itemStack, "right", 1),
                            skirtCottonItem.getItemDyeType(itemStack, "left", 2),
                            skirtCottonItem.getItemDyeType(itemStack, "right", 2),
                            skirtCottonItem.getStensilType(itemStack, "left"),
                            skirtCottonItem.getStensilType(itemStack, "right"),
                            skirtCottonItem.getItemLightValue(itemStack, "left", 1),
                            skirtCottonItem.getItemLightValue(itemStack, "left", 2),
                            skirtCottonItem.getItemLightValue(itemStack, "right", 1),
                            skirtCottonItem.getItemLightValue(itemStack, "right", 2),
                            "base",
                            itemStack.isEnchanted(),
                            skirtCottonItem.getGlintColor(itemStack),
                            skirtCottonItem.getAdditionalData(itemStack)
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
