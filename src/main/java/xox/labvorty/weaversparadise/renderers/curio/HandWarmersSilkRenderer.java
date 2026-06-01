package xox.labvorty.weaversparadise.renderers.curio;

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
import xox.labvorty.weaversparadise.items.clothing.HandWarmersSilkItem;
import xox.labvorty.weaversparadise.models.HandWarmersModel;
import xox.labvorty.weaversparadise.renderers.helpers.HandWarmersRenderingData;
import xox.labvorty.weaversparadise.renderers.models.HandWarmersModelRenderer;

import java.util.List;

public class HandWarmersSilkRenderer implements ICurioRenderer {
    private final HandWarmersModel model;

    public HandWarmersSilkRenderer() {
        this.model = new HandWarmersModel(Minecraft.getInstance().getEntityModels().bakeLayer(HandWarmersModel.LAYER_LOCATION));
    }

    @Override
    public <T extends LivingEntity, M extends EntityModel<T>> void render(ItemStack stack, SlotContext slotContext, PoseStack matrixStack, RenderLayerParent<T, M> renderLayerParent, MultiBufferSource renderTypeBuffer, int light, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        LivingEntity entity = slotContext.entity();

        List<? extends String> configValues = ClientConfig.HAND_WARMERS_RESTRICTOR.get();
        ItemStack FEET = entity.getItemBySlot(EquipmentSlot.FEET);
        ItemStack LEGS = entity.getItemBySlot(EquipmentSlot.LEGS);
        ItemStack BODY = entity.getItemBySlot(EquipmentSlot.CHEST);
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

        if (stack.getItem() instanceof HandWarmersSilkItem handWarmersSilk) {
            int primaryColorLeftOne = handWarmersSilk.getItemMainColor(stack, "left", 1);
            int secondaryColorLeftOne = handWarmersSilk.getItemSecondaryColor(stack, "left", 1);
            int primaryColorRightOne = handWarmersSilk.getItemMainColor(stack, "right", 1);
            int secondaryColorRightOne = handWarmersSilk.getItemSecondaryColor(stack, "right", 1);
            int primaryColorLeftTwo = handWarmersSilk.getItemMainColor(stack, "left", 2);
            int secondaryColorLeftTwo = handWarmersSilk.getItemSecondaryColor(stack, "left", 2);
            int primaryColorRightTwo = handWarmersSilk.getItemMainColor(stack, "right", 2);
            int secondaryColorRightTwo = handWarmersSilk.getItemSecondaryColor(stack, "right", 2);
            String dyeTypeLeftOne = handWarmersSilk.getItemDyeType(stack, "left", 1);
            String dyeTypeRightOne = handWarmersSilk.getItemDyeType(stack, "right", 1);
            String dyeTypeLeftTwo = handWarmersSilk.getItemDyeType(stack, "left", 2);
            String dyeTypeRightTwo = handWarmersSilk.getItemDyeType(stack, "right", 2);
            String stensilTypeLeft = handWarmersSilk.getStensilType(stack, "left");
            String stensilTypeRight = handWarmersSilk.getStensilType(stack, "right");
            int lightValueLeftOne = handWarmersSilk.getItemLightValue(stack, "left", 1);
            int lightValueLeftTwo = handWarmersSilk.getItemLightValue(stack, "left", 2);
            int lightValueRightOne = handWarmersSilk.getItemLightValue(stack, "right", 1);
            int lightValueRightTwo = handWarmersSilk.getItemLightValue(stack, "right", 2);

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
                            "silk"
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
