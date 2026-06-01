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
import xox.labvorty.weaversparadise.items.clothing.ThighHighsSilkItem;
import xox.labvorty.weaversparadise.models.ThighHighsModel;
import xox.labvorty.weaversparadise.renderers.helpers.ThighHighsRenderingData;
import xox.labvorty.weaversparadise.renderers.models.ThighHighsModelRenderer;

import java.util.List;

public class ThighHighsSilkRenderer implements ICurioRenderer {
    private final ThighHighsModel model;

    public ThighHighsSilkRenderer() {
        this.model = new ThighHighsModel(Minecraft.getInstance().getEntityModels().bakeLayer(ThighHighsModel.LAYER_LOCATION));
    }

    @Override
    public <T extends LivingEntity, M extends EntityModel<T>> void render(ItemStack stack, SlotContext slotContext, PoseStack poseStack, RenderLayerParent<T, M> renderLayerParent, MultiBufferSource multiBufferSource, int i, float v, float v1, float v2, float v3, float v4, float v5) {
        LivingEntity entity = slotContext.entity();

        List<? extends String> configValues = ClientConfig.THIGH_HIGHS_RESTRICTOR.get();
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

        if (stack.getItem() instanceof ThighHighsSilkItem thighHighsSilk) {
            M playerModel = renderLayerParent.getModel();
            if (playerModel instanceof HumanoidModel<?>) {
                HumanoidModel<?> humModel = (HumanoidModel<?>)playerModel;

                this.model.RightLeg.copyFrom(humModel.rightLeg);
                this.model.LeftLeg.copyFrom(humModel.leftLeg);
            }

            int primaryColorLeftOne = thighHighsSilk.getItemMainColor(stack, "left", 1);
            int secondaryColorLeftOne = thighHighsSilk.getItemSecondaryColor(stack, "left", 1);
            int primaryColorRightOne = thighHighsSilk.getItemMainColor(stack, "right", 1);
            int secondaryColorRightOne = thighHighsSilk.getItemSecondaryColor(stack, "right", 1);
            int primaryColorLeftTwo = thighHighsSilk.getItemMainColor(stack, "left", 2);
            int secondaryColorLeftTwo = thighHighsSilk.getItemSecondaryColor(stack, "left", 2);
            int primaryColorRightTwo = thighHighsSilk.getItemMainColor(stack, "right", 2);
            int secondaryColorRightTwo = thighHighsSilk.getItemSecondaryColor(stack, "right", 2);
            String dyeTypeLeftOne = thighHighsSilk.getItemDyeType(stack, "left", 1);
            String dyeTypeRightOne = thighHighsSilk.getItemDyeType(stack, "right", 1);
            String dyeTypeLeftTwo = thighHighsSilk.getItemDyeType(stack, "left", 2);
            String dyeTypeRightTwo = thighHighsSilk.getItemDyeType(stack, "right", 2);
            String stensilTypeLeft = thighHighsSilk.getStensilType(stack, "left");
            String stensilTypeRight = thighHighsSilk.getStensilType(stack, "right");
            int lightValueLeftOne = thighHighsSilk.getItemLightValue(stack, "left", 1);
            int lightValueLeftTwo = thighHighsSilk.getItemLightValue(stack, "left", 2);
            int lightValueRightOne = thighHighsSilk.getItemLightValue(stack, "right", 1);
            int lightValueRightTwo = thighHighsSilk.getItemLightValue(stack, "right", 2);

            ThighHighsModelRenderer thighHighModelRenderer = new ThighHighsModelRenderer();

            thighHighModelRenderer.renderModel(
                    multiBufferSource,
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
                    poseStack,
                    i
            );
        }
    }
}
