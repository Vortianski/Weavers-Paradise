package xox.labvorty.weaversparadise.renderers.curios;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.client.TrinketRenderer;
import xox.labvorty.weaversparadise.configs.ClientConfig;
import xox.labvorty.weaversparadise.items.clothing.SkirtCottonItem;
import xox.labvorty.weaversparadise.model.CottonSkirtModel;
import xox.labvorty.weaversparadise.renderers.helpers.DoubleSidedClothingRenderingData;
import xox.labvorty.weaversparadise.renderers.models.CottonSkirtModelRenderer;
import xox.labvorty.weaversparadise.utilities.WeaversUtilities;

public class CottonSkirtCurioRenderer implements TrinketRenderer {
    private final CottonSkirtModel<?> model;

    public CottonSkirtCurioRenderer() {
        this.model = new CottonSkirtModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(CottonSkirtModel.LAYER_LOCATION));
    }

    @Override
    public void render(ItemStack itemStack, SlotReference slotReference, EntityModel<? extends LivingEntity> contextModel, PoseStack poseStack, MultiBufferSource multiBufferSource, int packedLight, LivingEntity entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {

        if (WeaversUtilities.isRestricted(entity, ClientConfig.SKIRT_RESTRICTOR.get(), EquipmentSlot.LEGS)) {
            return;
        }

        if (itemStack.getItem() instanceof SkirtCottonItem skirtCottonItem) {
            net.minecraft.client.model.EntityModel<? extends LivingEntity> parentModel = contextModel;
            if (parentModel instanceof HumanoidModel<?> humanoidModel) {
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
