package xox.labvorty.weaversparadise.renderers.models;

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
import xox.labvorty.weaversparadise.curios.WeaversParadiseMobLayers;
import xox.labvorty.weaversparadise.items.ChokerItem;
import xox.labvorty.weaversparadise.model.ModelChoker;
import xox.labvorty.weaversparadise.renderers.render_helper.ChokerModelRenderer;
import xox.labvorty.weaversparadise.renderers.render_helper.ChokerRenderingData;

public class ChokerRenderer implements ICurioRenderer {
    private final ModelChoker model;

    public ChokerRenderer() {
        this.model = new ModelChoker(Minecraft.getInstance().getEntityModels().bakeLayer(WeaversParadiseMobLayers.CHOKER));
    }

    @Override
    public <T extends LivingEntity, M extends EntityModel<T>> void render(ItemStack stack, SlotContext slotContext, PoseStack matrixStack, RenderLayerParent<T, M> renderLayerParent, MultiBufferSource renderTypeBuffer, int light, float limbSwing,
                                                                          float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (stack.getItem() instanceof ChokerItem chokerItem) {
            LivingEntity entity = slotContext.entity();
            this.model.prepareMobModel(entity, limbSwing, limbSwingAmount, partialTicks);
            this.model.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

            M playerModel = renderLayerParent.getModel();
            if (playerModel instanceof HumanoidModel<?>) {
                HumanoidModel<?> humModel = (HumanoidModel<?>)playerModel;

                this.model.Body.copyFrom(humModel.body);
            }

            int primaryColorLeftOne = chokerItem.getItemMainColor(stack, "left", 1);
            int secondaryColorLeftOne = chokerItem.getItemSecondaryColor(stack, "left", 1);
            int primaryColorRightOne = chokerItem.getItemMainColor(stack, "right", 1);
            int secondaryColorRightOne = chokerItem.getItemSecondaryColor(stack, "right", 1);
            int primaryColorLeftTwo = chokerItem.getItemMainColor(stack, "left", 2);
            int secondaryColorLeftTwo = chokerItem.getItemSecondaryColor(stack, "left", 2);
            int primaryColorRightTwo = chokerItem.getItemMainColor(stack, "right", 2);
            int secondaryColorRightTwo = chokerItem.getItemSecondaryColor(stack, "right", 2);
            String dyeTypeLeftOne = chokerItem.getItemDyeType(stack, "left", 1);
            String dyeTypeRightOne = chokerItem.getItemDyeType(stack, "right", 1);
            String dyeTypeLeftTwo = chokerItem.getItemDyeType(stack, "left", 2);
            String dyeTypeRightTwo = chokerItem.getItemDyeType(stack, "right", 2);
            String stensilTypeLeft = chokerItem.getStensilType(stack, "left");
            String stensilTypeRight = chokerItem.getStensilType(stack, "right");
            int lightValueLeftOne = chokerItem.getItemLightValue(stack, "left", 1);
            int lightValueLeftTwo = chokerItem.getItemLightValue(stack, "left", 2);
            int lightValueRightOne = chokerItem.getItemLightValue(stack, "right", 1);
            int lightValueRightTwo = chokerItem.getItemLightValue(stack, "right", 2);

            ChokerModelRenderer chokerModelRenderer = new ChokerModelRenderer();

            chokerModelRenderer.renderModel(
                    renderTypeBuffer,
                    model,
                    new ChokerRenderingData(
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
                            "base"
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
