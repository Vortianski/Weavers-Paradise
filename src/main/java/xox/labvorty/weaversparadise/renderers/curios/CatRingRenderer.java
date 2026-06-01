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
import xox.labvorty.weaversparadise.items.clothing.CatRingItem;
import xox.labvorty.weaversparadise.model.CatRingModel;
import xox.labvorty.weaversparadise.renderers.helpers.ChokerTrinketRenderingData;
import xox.labvorty.weaversparadise.renderers.models.CatRingModelRenderer;

import java.util.List;

public class CatRingRenderer implements ICurioRenderer {
    private final CatRingModel model;

    public CatRingRenderer() {
        this.model = new CatRingModel(Minecraft.getInstance().getEntityModels().bakeLayer(CatRingModel.LAYER_LOCATION));
    }

    @Override
    public <T extends LivingEntity, M extends EntityModel<T>> void render(ItemStack stack, SlotContext slotContext, PoseStack matrixStack, RenderLayerParent<T, M> renderLayerParent, MultiBufferSource renderTypeBuffer, int light, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        LivingEntity entity = slotContext.entity();

        List<? extends String> configValues = ClientConfig.CHOKER_RESTRICTOR.get();
        ItemStack FEET = entity.getItemBySlot(EquipmentSlot.FEET);
        ItemStack LEGS = entity.getItemBySlot(EquipmentSlot.LEGS);
        ItemStack BODY = entity.getItemBySlot(EquipmentSlot.BODY);
        ItemStack HEAD = entity.getItemBySlot(EquipmentSlot.HEAD);

        if (!FEET.isEmpty() && ClientConfig.containsItem(configValues, FEET.getItem())) {
            return;
        } else if (!LEGS.isEmpty() && ClientConfig.containsItem(configValues, LEGS.getItem())) {
            return;
        } else if (!BODY.isEmpty() && ClientConfig.containsItem(configValues, BODY.getItem())) {
            return;
        } else if (!HEAD.isEmpty() && ClientConfig.containsItem(configValues, HEAD.getItem())) {
            return;
        }

        if (stack.getItem() instanceof CatRingItem catRingItem) {
            M playerModel = renderLayerParent.getModel();
            if (playerModel instanceof HumanoidModel<?>) {
                HumanoidModel<?> humModel = (HumanoidModel<?>)playerModel;

                this.model.Body.copyFrom(humModel.body);
            }


            CatRingModelRenderer catRingModelRenderer = new CatRingModelRenderer();
            CompoundTag compoundTag = stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag();

            catRingModelRenderer.renderModel(
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
