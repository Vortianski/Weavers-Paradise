package xox.labvorty.weaversparadise.renderers.models;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.TheEndPortalRenderer;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.client.ICurioRenderer;
import xox.labvorty.weaversparadise.curios.WeaversParadiseMobLayers;
import xox.labvorty.weaversparadise.data.WeaversParadiseThighHighsTextureHandler;
import xox.labvorty.weaversparadise.items.ThighHighsCotton;
import xox.labvorty.weaversparadise.items.ThighHighsWool;
import xox.labvorty.weaversparadise.model.ThighHighsModel;
import xox.labvorty.weaversparadise.renderers.WeaversParadiseGlobalRendererDataHolder;
import xox.labvorty.weaversparadise.renderers.WeaversParadiseRenderTypes;
import xox.labvorty.weaversparadise.renderers.render_helper.ThighHighModelRenderer;
import xox.labvorty.weaversparadise.renderers.render_helper.ThighHighsRenderingData;

public class ThighsHighsWoolRenderer implements ICurioRenderer {
    private final ThighHighsModel model;

    public ThighsHighsWoolRenderer() {
        this.model = new ThighHighsModel(Minecraft.getInstance().getEntityModels().bakeLayer(WeaversParadiseMobLayers.THIGH_HIGHS));
    }

    @Override
    public <T extends LivingEntity, M extends EntityModel<T>> void render(ItemStack stack, SlotContext slotContext, PoseStack matrixStack, RenderLayerParent<T, M> renderLayerParent, MultiBufferSource renderTypeBuffer, int light, float limbSwing,
                                                                          float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (stack.getItem() instanceof ThighHighsWool thighHighsWool) {
            LivingEntity entity = slotContext.entity();
            this.model.prepareMobModel(entity, limbSwing, limbSwingAmount, partialTicks);
            this.model.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

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

            ThighHighModelRenderer thighHighModelRenderer = new ThighHighModelRenderer();

            thighHighModelRenderer.renderModel(
                    renderTypeBuffer,
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
                    matrixStack,
                    light
            );
        }
    }
}
