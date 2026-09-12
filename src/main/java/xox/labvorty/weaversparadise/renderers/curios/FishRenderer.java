package xox.labvorty.weaversparadise.renderers.curios;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.client.TrinketRenderer;
import xox.labvorty.weaversparadise.configs.ClientConfig;
import xox.labvorty.weaversparadise.items.clothing.FishItem;
import xox.labvorty.weaversparadise.model.FishModel;
import xox.labvorty.weaversparadise.renderers.helpers.ChokerTrinketRenderingData;
import xox.labvorty.weaversparadise.renderers.models.FishModelRenderer;
import xox.labvorty.weaversparadise.utilities.WeaversUtilities;

public class FishRenderer implements TrinketRenderer {
    private final FishModel<?> model;

    public FishRenderer() {
        this.model = new FishModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(FishModel.LAYER_LOCATION));
    }

    @Override
    public void render(ItemStack itemStack, SlotReference slotReference, EntityModel<? extends LivingEntity> contextModel, PoseStack matrixStack, MultiBufferSource renderTypeBuffer, int light, LivingEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {

        if (WeaversUtilities.isRestricted(entity, ClientConfig.CHOKER_RESTRICTOR.get(), EquipmentSlot.CHEST)) {
            return;
        }

        if (itemStack.getItem() instanceof FishItem fishItem) {
            net.minecraft.client.model.EntityModel<? extends LivingEntity> playerModel = contextModel;
            if (playerModel instanceof HumanoidModel<?> humanoidModel) {
                this.model.Body.copyFrom(humanoidModel.body);
            }


            FishModelRenderer fishModelRenderer = new FishModelRenderer();
            CompoundTag compoundTag = itemStack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag();

            fishModelRenderer.renderModel(
                    renderTypeBuffer,
                    model,
                    new ChokerTrinketRenderingData(
                            compoundTag.getInt("color"),
                            compoundTag.getString("metalType")
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
                    matrixStack,
                    light
            );
        }
    }
}
