package xox.labvorty.weaversparadise.renderers.curio;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.client.ICurioRenderer;
import xox.labvorty.weaversparadise.items.clothing.ThighHighsCottonItem;
import xox.labvorty.weaversparadise.models.ThighHighsModel;
import xox.labvorty.weaversparadise.renderers.helpers.ThighHighsRenderingData;
import xox.labvorty.weaversparadise.renderers.models.ThighHighsModelRenderer;

public class ThighHighsCottonRenderer implements ICurioRenderer {
    private final ThighHighsModel model;

    public ThighHighsCottonRenderer() {
        this.model = new ThighHighsModel(Minecraft.getInstance().getEntityModels().bakeLayer(ThighHighsModel.LAYER_LOCATION));
    }

    @Override
    public <T extends LivingEntity, M extends EntityModel<T>> void render(ItemStack stack, SlotContext slotContext, PoseStack poseStack, RenderLayerParent<T, M> renderLayerParent, MultiBufferSource multiBufferSource, int i, float v, float v1, float v2, float v3, float v4, float v5) {
        if (stack.getItem() instanceof ThighHighsCottonItem cottonThighHighs) {
            LivingEntity entity = slotContext.entity();

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
                    poseStack,
                    i
            );
        }
    }
}
