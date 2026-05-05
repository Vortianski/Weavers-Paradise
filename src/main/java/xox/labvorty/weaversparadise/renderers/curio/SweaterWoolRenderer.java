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
import net.minecraft.world.item.Items;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.client.ICurioRenderer;
import xox.labvorty.weaversparadise.items.clothing.SweaterWoolItem;
import xox.labvorty.weaversparadise.models.UpperWearModel;
import xox.labvorty.weaversparadise.renderers.helpers.ShirtRenderingData;
import xox.labvorty.weaversparadise.renderers.models.ShirtModelRenderer;

public class SweaterWoolRenderer implements ICurioRenderer {
    public static UpperWearModel model;

    public SweaterWoolRenderer() {
        this.model = new UpperWearModel(Minecraft.getInstance().getEntityModels().bakeLayer(UpperWearModel.LAYER_LOCATION));
    }

    @Override
    public <T extends LivingEntity, M extends EntityModel<T>> void render(ItemStack stack, SlotContext slotContext, PoseStack matrixStack, RenderLayerParent<T, M> renderLayerParent, MultiBufferSource renderTypeBuffer, int light, float limbSwing,
                                                                          float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        LivingEntity entity = slotContext.entity();

        if (stack.getItem() instanceof SweaterWoolItem sweaterWool && !entity.getItemBySlot(EquipmentSlot.CHEST).is(Items.DIAMOND /**WeaversParadiseItems.ASTOLFO_ARMOR_CHESTPLATE**/)) {
            int primaryColorOne = sweaterWool.getItemMainColor(stack, 1);
            int secondaryColorOne = sweaterWool.getItemSecondaryColor(stack, 1);
            int primaryColorTwo = sweaterWool.getItemMainColor(stack, 2);
            int secondaryColorTwo = sweaterWool.getItemSecondaryColor(stack, 2);
            String dyeTypeOne = sweaterWool.getItemDyeType(stack, 1);
            String dyeTypeTwo = sweaterWool.getItemDyeType(stack, 2);
            String stensilType = sweaterWool.getStensilType(stack);
            int lightValueOne = sweaterWool.getItemLightValue(stack, 1);
            int lightValueTwo = sweaterWool.getItemLightValue(stack, 2);

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
                            stack.getOrCreateTag().getBoolean("is_open"),
                            primaryColorOne,
                            secondaryColorOne,
                            primaryColorTwo,
                            secondaryColorTwo,
                            dyeTypeOne,
                            dyeTypeTwo,
                            stensilType,
                            lightValueOne,
                            lightValueTwo,
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