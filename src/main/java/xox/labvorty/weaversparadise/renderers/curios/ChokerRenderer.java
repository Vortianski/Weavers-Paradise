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
import xox.labvorty.weaversparadise.renderers.helpers.ChokerRenderingData;
import xox.labvorty.weaversparadise.renderers.models.ChokerModelRenderer;

import java.util.List;

public class ChokerRenderer implements ICurioRenderer {
    private final ChokerModel model;

    public ChokerRenderer() {
        this.model = new ChokerModel(Minecraft.getInstance().getEntityModels().bakeLayer(ChokerModel.LAYER_LOCATION));
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

        if (stack.getItem() instanceof ChokerItem chokerItem) {
            M playerModel = renderLayerParent.getModel();
            if (playerModel instanceof HumanoidModel<?>) {
                HumanoidModel<?> humModel = (HumanoidModel<?>)playerModel;

                this.model.Body.copyFrom(humModel.body);
            }

            int primaryColorLeftOne = chokerItem.getItemMainColor(stack, "left", 1);
            int secondaryColorLeftOne = chokerItem.getItemSecondaryColor(stack, "left", 1);
            int primaryColorRightOne = chokerItem.getItemMainColor(stack, "right", 1);
            int secondaryColorRightOne = chokerItem.getItemSecondaryColor(stack, "right", 1);
            int primaryColorLeftTwo = chokerItem.getItemMainColor(stack, "left", 2);
            int secondaryColorLeftTwo = chokerItem.getItemSecondaryColor(stack, "left", 2);
            int primaryColorRightTwo = chokerItem.getItemMainColor(stack, "right", 2);
            int secondaryColorRightTwo = chokerItem.getItemSecondaryColor(stack, "right", 2);
            String dyeTypeLeftOne = chokerItem.getItemDyeType(stack, "left", 1);
            String dyeTypeRightOne = chokerItem.getItemDyeType(stack, "right", 1);
            String dyeTypeLeftTwo = chokerItem.getItemDyeType(stack, "left", 2);
            String dyeTypeRightTwo = chokerItem.getItemDyeType(stack, "right", 2);
            String stensilTypeLeft = chokerItem.getStensilType(stack, "left");
            String stensilTypeRight = chokerItem.getStensilType(stack, "right");
            int lightValueLeftOne = chokerItem.getItemLightValue(stack, "left", 1);
            int lightValueLeftTwo = chokerItem.getItemLightValue(stack, "left", 2);
            int lightValueRightOne = chokerItem.getItemLightValue(stack, "right", 1);
            int lightValueRightTwo = chokerItem.getItemLightValue(stack, "right", 2);

            ChokerModelRenderer chokerModelRenderer = new ChokerModelRenderer();

            chokerModelRenderer.renderModel(
                    renderTypeBuffer,
                    model,
                    new ChokerRenderingData(
                            primaryColorLeftOne,
                            secondaryColorLeftOne,
                            primaryColorRightOne,
                            secondaryColorRightOne,
                            primaryColorLeftTwo,
                            secondaryColorLeftTwo,
                            primaryColorRightTwo,
                            secondaryColorRightTwo,
                            dyeTypeLeftOne,
                            dyeTypeRightOne,
                            dyeTypeLeftTwo,
                            dyeTypeRightTwo,
                            stensilTypeLeft,
                            stensilTypeRight,
                            lightValueLeftOne,
                            lightValueLeftTwo,
                            lightValueRightOne,
                            lightValueRightTwo,
                            "base"
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
