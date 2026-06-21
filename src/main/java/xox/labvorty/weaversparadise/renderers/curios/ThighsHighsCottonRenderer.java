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
import xox.labvorty.weaversparadise.items.clothing.ThighHighsCottonItem;
import xox.labvorty.weaversparadise.model.ThighHighsModel;
import xox.labvorty.weaversparadise.renderers.helpers.ThighHighsRenderingData;
import xox.labvorty.weaversparadise.renderers.models.ThighHighModelRenderer;

import java.util.List;

public class ThighsHighsCottonRenderer implements ICurioRenderer {
    private final ThighHighsModel model;

    public ThighsHighsCottonRenderer() {
        this.model = new ThighHighsModel(Minecraft.getInstance().getEntityModels().bakeLayer(ThighHighsModel.LAYER_LOCATION));
    }

    @Override
    public <T extends LivingEntity, M extends EntityModel<T>> void render(ItemStack stack, SlotContext slotContext, PoseStack matrixStack, RenderLayerParent<T, M> renderLayerParent, MultiBufferSource renderTypeBuffer, int light, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        LivingEntity entity = slotContext.entity();

        List<? extends String> configValues = ClientConfig.THIGH_HIGHS_RESTRICTOR.get();
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

        if (stack.getItem() instanceof ThighHighsCottonItem cottonThighHighs) {
            M playerModel = renderLayerParent.getModel();
            if (playerModel instanceof HumanoidModel<?>) {
                HumanoidModel<?> humModel = (HumanoidModel<?>)playerModel;

                this.model.RightLeg.copyFrom(humModel.rightLeg);
                this.model.LeftLeg.copyFrom(humModel.leftLeg);
            }

            int primaryColorLeftOne = cottonThighHighs.getItemMainColor(stack, "left", 1);
            int secondaryColorLeftOne = cottonThighHighs.getItemSecondaryColor(stack, "left", 1);
            int primaryColorRightOne = cottonThighHighs.getItemMainColor(stack, "right", 1);
            int secondaryColorRightOne = cottonThighHighs.getItemSecondaryColor(stack, "right", 1);
            int primaryColorLeftTwo = cottonThighHighs.getItemMainColor(stack, "left", 2);
            int secondaryColorLeftTwo = cottonThighHighs.getItemSecondaryColor(stack, "left", 2);
            int primaryColorRightTwo = cottonThighHighs.getItemMainColor(stack, "right", 2);
            int secondaryColorRightTwo = cottonThighHighs.getItemSecondaryColor(stack, "right", 2);
            String dyeTypeLeftOne = cottonThighHighs.getItemDyeType(stack, "left", 1);
            String dyeTypeRightOne = cottonThighHighs.getItemDyeType(stack, "right", 1);
            String dyeTypeLeftTwo = cottonThighHighs.getItemDyeType(stack, "left", 2);
            String dyeTypeRightTwo = cottonThighHighs.getItemDyeType(stack, "right", 2);
            String stensilTypeLeft = cottonThighHighs.getStensilType(stack, "left");
            String stensilTypeRight = cottonThighHighs.getStensilType(stack, "right");
            int lightValueLeftOne = cottonThighHighs.getItemLightValue(stack, "left", 1);
            int lightValueLeftTwo = cottonThighHighs.getItemLightValue(stack, "left", 2);
            int lightValueRightOne = cottonThighHighs.getItemLightValue(stack, "right", 1);
            int lightValueRightTwo = cottonThighHighs.getItemLightValue(stack, "right", 2);

            ThighHighModelRenderer thighHighModelRenderer = new ThighHighModelRenderer();

            thighHighModelRenderer.renderModel(
                    renderTypeBuffer,
                    model,
                    new ThighHighsRenderingData(
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
                            "cotton"
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
