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
import xox.labvorty.weaversparadise.init.WeaversParadiseItems;
import xox.labvorty.weaversparadise.items.clothing.PantsJeansItem;
import xox.labvorty.weaversparadise.model.PantsModel;
import xox.labvorty.weaversparadise.renderers.helpers.PantsRenderingData;
import xox.labvorty.weaversparadise.renderers.models.PantsModelRenderer;

import java.util.List;

public class PantsJeansRenderer implements ICurioRenderer {
    public static PantsModel model;

    public PantsJeansRenderer() {
        this.model = new PantsModel(Minecraft.getInstance().getEntityModels().bakeLayer(PantsModel.LAYER_LOCATION));
    }

    @Override
    public <T extends LivingEntity, M extends EntityModel<T>> void render(ItemStack stack, SlotContext slotContext, PoseStack matrixStack, RenderLayerParent<T, M> renderLayerParent, MultiBufferSource renderTypeBuffer, int light, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        LivingEntity entity = slotContext.entity();

        List<? extends String> configValues = ClientConfig.PANTS_RESTRICTOR.get();
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

        if (stack.getItem() instanceof PantsJeansItem pantsJeans) {
            int primaryColorOne = pantsJeans.getItemMainColor(stack, 1);
            int secondaryColorOne = pantsJeans.getItemSecondaryColor(stack, 1);
            int primaryColorTwo = pantsJeans.getItemMainColor(stack, 2);
            int secondaryColorTwo = pantsJeans.getItemSecondaryColor(stack, 2);
            String dyeTypeOne = pantsJeans.getItemDyeType(stack, 1);
            String dyeTypeTwo = pantsJeans.getItemDyeType(stack, 2);
            String stensilType = pantsJeans.getStensilType(stack);
            int lightValueOne = pantsJeans.getItemLightValue(stack, 1);
            int lightValueTwo = pantsJeans.getItemLightValue(stack, 2);

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
                            "jeans"
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
