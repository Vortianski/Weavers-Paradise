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
import xox.labvorty.weaversparadise.items.clothing.PantsCottonItem;
import xox.labvorty.weaversparadise.models.PantsModel;
import xox.labvorty.weaversparadise.renderers.helpers.PantsRenderingData;
import xox.labvorty.weaversparadise.renderers.models.PantsModelRenderer;

public class PantsCottonRenderer implements ICurioRenderer {
    public static PantsModel model;

    public PantsCottonRenderer() {
        this.model = new PantsModel(Minecraft.getInstance().getEntityModels().bakeLayer(PantsModel.LAYER_LOCATION));
    }

    @Override
    public <T extends LivingEntity, M extends EntityModel<T>> void render(ItemStack stack, SlotContext slotContext, PoseStack matrixStack, RenderLayerParent<T, M> renderLayerParent, MultiBufferSource renderTypeBuffer, int light, float limbSwing,
                                                                          float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        LivingEntity entity = slotContext.entity();

        if (stack.getItem() instanceof PantsCottonItem pantsCotton && !entity.getItemBySlot(EquipmentSlot.LEGS).is(Items.DIAMOND /**WeaversParadiseItems.ASTOLFO_ARMOR_LEGGINGS**/)) {
            int primaryColorOne = pantsCotton.getItemMainColor(stack, 1);
            int secondaryColorOne = pantsCotton.getItemSecondaryColor(stack, 1);
            int primaryColorTwo = pantsCotton.getItemMainColor(stack, 2);
            int secondaryColorTwo = pantsCotton.getItemSecondaryColor(stack, 2);
            String dyeTypeOne = pantsCotton.getItemDyeType(stack, 1);
            String dyeTypeTwo = pantsCotton.getItemDyeType(stack, 2);
            String stensilType = pantsCotton.getStensilType(stack);
            int lightValueOne = pantsCotton.getItemLightValue(stack, 1);
            int lightValueTwo = pantsCotton.getItemLightValue(stack, 2);


            M playerModel = renderLayerParent.getModel();
            if (playerModel instanceof HumanoidModel<?>) {
                HumanoidModel<?> humModel = (HumanoidModel<?>)playerModel;

                this.model.Body.copyFrom(humModel.body);
                this.model.LeftLeg.copyFrom(humModel.leftLeg);
                this.model.RightLeg.copyFrom(humModel.rightLeg);
            }

            PantsModelRenderer pantsRenderer = new PantsModelRenderer();

            pantsRenderer.renderModel(
                    renderTypeBuffer,
                    model,
                    new PantsRenderingData(
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