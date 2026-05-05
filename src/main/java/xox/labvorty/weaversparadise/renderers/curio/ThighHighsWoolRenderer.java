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
import xox.labvorty.weaversparadise.items.clothing.ThighHighsWoolItem;
import xox.labvorty.weaversparadise.models.ThighHighsModel;
import xox.labvorty.weaversparadise.renderers.helpers.ThighHighsRenderingData;
import xox.labvorty.weaversparadise.renderers.models.ThighHighsModelRenderer;

public class ThighHighsWoolRenderer implements ICurioRenderer {
    private final ThighHighsModel model;

    public ThighHighsWoolRenderer() {
        this.model = new ThighHighsModel(Minecraft.getInstance().getEntityModels().bakeLayer(ThighHighsModel.LAYER_LOCATION));
    }

    @Override
    public <T extends LivingEntity, M extends EntityModel<T>> void render(ItemStack stack, SlotContext slotContext, PoseStack poseStack, RenderLayerParent<T, M> renderLayerParent, MultiBufferSource multiBufferSource, int i, float v, float v1, float v2, float v3, float v4, float v5) {
        if (stack.getItem() instanceof ThighHighsWoolItem thighHighsWool) {
            LivingEntity entity = slotContext.entity();

            M playerModel = renderLayerParent.getModel();
            if (playerModel instanceof HumanoidModel<?>) {
                HumanoidModel<?> humModel = (HumanoidModel<?>)playerModel;

                this.model.RightLeg.copyFrom(humModel.rightLeg);
                this.model.LeftLeg.copyFrom(humModel.leftLeg);
            }

            int primaryColorLeftOne = thighHighsWool.getItemMainColor(stack, "left", 1);
            int secondaryColorLeftOne = thighHighsWool.getItemSecondaryColor(stack, "left", 1);
            int primaryColorRightOne = thighHighsWool.getItemMainColor(stack, "right", 1);
            int secondaryColorRightOne = thighHighsWool.getItemSecondaryColor(stack, "right", 1);
            int primaryColorLeftTwo = thighHighsWool.getItemMainColor(stack, "left", 2);
            int secondaryColorLeftTwo = thighHighsWool.getItemSecondaryColor(stack, "left", 2);
            int primaryColorRightTwo = thighHighsWool.getItemMainColor(stack, "right", 2);
            int secondaryColorRightTwo = thighHighsWool.getItemSecondaryColor(stack, "right", 2);
            String dyeTypeLeftOne = thighHighsWool.getItemDyeType(stack, "left", 1);
            String dyeTypeRightOne = thighHighsWool.getItemDyeType(stack, "right", 1);
            String dyeTypeLeftTwo = thighHighsWool.getItemDyeType(stack, "left", 2);
            String dyeTypeRightTwo = thighHighsWool.getItemDyeType(stack, "right", 2);
            String stensilTypeLeft = thighHighsWool.getStensilType(stack, "left");
            String stensilTypeRight = thighHighsWool.getStensilType(stack, "right");
            int lightValueLeftOne = thighHighsWool.getItemLightValue(stack, "left", 1);
            int lightValueLeftTwo = thighHighsWool.getItemLightValue(stack, "left", 2);
            int lightValueRightOne = thighHighsWool.getItemLightValue(stack, "right", 1);
            int lightValueRightTwo = thighHighsWool.getItemLightValue(stack, "right", 2);

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
                    poseStack,
                    i
            );
        }
    }
}
