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
import xox.labvorty.weaversparadise.items.clothing.HandWarmersWoolItem;
import xox.labvorty.weaversparadise.model.HandWarmersModel;
import xox.labvorty.weaversparadise.renderers.helpers.HandWarmersRenderingData;
import xox.labvorty.weaversparadise.renderers.models.HandWarmersModelRenderer;

import java.util.List;

public class HandWarmersWoolRenderer implements ICurioRenderer {
    private final HandWarmersModel model;

    public HandWarmersWoolRenderer() {
        this.model = new HandWarmersModel(Minecraft.getInstance().getEntityModels().bakeLayer(HandWarmersModel.LAYER_LOCATION));
    }

    @Override
    public <T extends LivingEntity, M extends EntityModel<T>> void render(ItemStack stack, SlotContext slotContext, PoseStack matrixStack, RenderLayerParent<T, M> renderLayerParent, MultiBufferSource renderTypeBuffer, int light, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        LivingEntity entity = slotContext.entity();

        List<? extends String> configValues = ClientConfig.HAND_WARMERS_RESTRICTOR.get();
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

        if (stack.getItem() instanceof HandWarmersWoolItem handWarmersWool) {
            int primaryColorLeftOne = handWarmersWool.getItemMainColor(stack, "left", 1);
            int secondaryColorLeftOne = handWarmersWool.getItemSecondaryColor(stack, "left", 1);
            int primaryColorRightOne = handWarmersWool.getItemMainColor(stack, "right", 1);
            int secondaryColorRightOne = handWarmersWool.getItemSecondaryColor(stack, "right", 1);
            int primaryColorLeftTwo = handWarmersWool.getItemMainColor(stack, "left", 2);
            int secondaryColorLeftTwo = handWarmersWool.getItemSecondaryColor(stack, "left", 2);
            int primaryColorRightTwo = handWarmersWool.getItemMainColor(stack, "right", 2);
            int secondaryColorRightTwo = handWarmersWool.getItemSecondaryColor(stack, "right", 2);
            String dyeTypeLeftOne = handWarmersWool.getItemDyeType(stack, "left", 1);
            String dyeTypeRightOne = handWarmersWool.getItemDyeType(stack, "right", 1);
            String dyeTypeLeftTwo = handWarmersWool.getItemDyeType(stack, "left", 2);
            String dyeTypeRightTwo = handWarmersWool.getItemDyeType(stack, "right", 2);
            String stensilTypeLeft = handWarmersWool.getStensilType(stack, "left");
            String stensilTypeRight = handWarmersWool.getStensilType(stack, "right");
            int lightValueLeftOne = handWarmersWool.getItemLightValue(stack, "left", 1);
            int lightValueLeftTwo = handWarmersWool.getItemLightValue(stack, "left", 2);
            int lightValueRightOne = handWarmersWool.getItemLightValue(stack, "right", 1);
            int lightValueRightTwo = handWarmersWool.getItemLightValue(stack, "right", 2);

            M playerModel = renderLayerParent.getModel();
            if (playerModel instanceof HumanoidModel<?>) {
                HumanoidModel<?> humModel = (HumanoidModel<?>)playerModel;

                this.model.RightArm.copyFrom(humModel.rightArm);
                this.model.LeftArm.copyFrom(humModel.leftArm);
            }

            HandWarmersModelRenderer thighHighModelRenderer = new HandWarmersModelRenderer();

            thighHighModelRenderer.renderModel(
                    renderTypeBuffer,
                    model,
                    new HandWarmersRenderingData(
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
                            "wool"
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
