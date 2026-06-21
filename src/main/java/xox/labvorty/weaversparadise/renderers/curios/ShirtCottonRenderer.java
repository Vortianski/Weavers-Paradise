package xox.labvorty.weaversparadise.renderers.curios;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.client.ICurioRenderer;
import xox.labvorty.weaversparadise.configs.ClientConfig;
import xox.labvorty.weaversparadise.items.clothing.ShirtCottonItem;
import xox.labvorty.weaversparadise.model.UpperWearModel;
import xox.labvorty.weaversparadise.renderers.helpers.ShirtRenderingData;
import xox.labvorty.weaversparadise.renderers.models.ShirtModelRenderer;

import java.util.List;

public class ShirtCottonRenderer implements ICurioRenderer {
    public static UpperWearModel model;

    public ShirtCottonRenderer() {
        this.model = new UpperWearModel(Minecraft.getInstance().getEntityModels().bakeLayer(UpperWearModel.LAYER_LOCATION));
    }

    @Override
    public <T extends LivingEntity, M extends EntityModel<T>> void render(ItemStack stack, SlotContext slotContext, PoseStack matrixStack, RenderLayerParent<T, M> renderLayerParent, MultiBufferSource renderTypeBuffer, int light, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        LivingEntity entity = slotContext.entity();

        List<? extends String> configValues = ClientConfig.SHIRT_RESTRICTOR.get();
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

        if (stack.getItem() instanceof ShirtCottonItem shirtCotton) {
            int primaryColorOne = shirtCotton.getItemMainColor(stack, 1);
            int secondaryColorOne = shirtCotton.getItemSecondaryColor(stack, 1);
            int primaryColorTwo = shirtCotton.getItemMainColor(stack, 2);
            int secondaryColorTwo = shirtCotton.getItemSecondaryColor(stack, 2);
            String dyeTypeOne = shirtCotton.getItemDyeType(stack, 1);
            String dyeTypeTwo = shirtCotton.getItemDyeType(stack, 2);
            String stensilType = shirtCotton.getStensilType(stack);
            int lightValueOne = shirtCotton.getItemLightValue(stack, 1);
            int lightValueTwo = shirtCotton.getItemLightValue(stack, 2);

            M playerModel = renderLayerParent.getModel();
            if (playerModel instanceof HumanoidModel<?>) {
                HumanoidModel<?> humModel = (HumanoidModel<?>)playerModel;

                this.model.Body.copyFrom(humModel.body);
                this.model.LeftArm.copyFrom(humModel.leftArm);
                this.model.RightArm.copyFrom(humModel.rightArm);
            }

            ShirtModelRenderer shirtModelRenderer = new ShirtModelRenderer();

            shirtModelRenderer.renderModel(
                    renderTypeBuffer,
                    model,
                    new ShirtRenderingData(
                            stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getBoolean("is_open"),
                            primaryColorOne,
                            secondaryColorOne,
                            primaryColorTwo,
                            secondaryColorTwo,
                            dyeTypeOne,
                            dyeTypeTwo,
                            stensilType,
                            lightValueOne,
                            lightValueTwo,
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
