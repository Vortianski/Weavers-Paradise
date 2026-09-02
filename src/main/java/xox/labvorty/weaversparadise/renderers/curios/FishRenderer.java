package xox.labvorty.weaversparadise.renderers.curios;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.client.ICurioRenderer;
import xox.labvorty.weaversparadise.configs.ClientConfig;
import xox.labvorty.weaversparadise.items.clothing.FishItem;
import xox.labvorty.weaversparadise.model.FishModel;
import xox.labvorty.weaversparadise.renderers.helpers.ChokerTrinketRenderingData;
import xox.labvorty.weaversparadise.renderers.models.FishModelRenderer;
import xox.labvorty.weaversparadise.utilities.WeaversUtilities;

public class FishRenderer implements ICurioRenderer {
    private final FishModel<?> model;

    public FishRenderer() {
        this.model = new FishModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(FishModel.LAYER_LOCATION));
    }

    @Override
    public <T extends LivingEntity, M extends EntityModel<T>> void render(ItemStack stack, SlotContext slotContext, PoseStack matrixStack, RenderLayerParent<T, M> renderLayerParent, MultiBufferSource renderTypeBuffer, int light, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        LivingEntity entity = slotContext.entity();

        if (WeaversUtilities.isRestricted(entity, ClientConfig.CHOKER_RESTRICTOR.get(), EquipmentSlot.CHEST)) {
            return;
        }

        if (stack.getItem() instanceof FishItem fishItem) {
            M playerModel = renderLayerParent.getModel();
            if (playerModel instanceof HumanoidModel<?> humanoidModel) {
                this.model.Body.copyFrom(humanoidModel.body);
            }


            FishModelRenderer fishModelRenderer = new FishModelRenderer();
            CompoundTag compoundTag = stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag();

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
