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
import xox.labvorty.weaversparadise.data.WeaversParadiseHandWarmersTextureHandler;
import xox.labvorty.weaversparadise.items.HandWarmersWool;
import xox.labvorty.weaversparadise.items.ThighHighsCotton;
import xox.labvorty.weaversparadise.model.ModelHandWarmers;
import xox.labvorty.weaversparadise.renderers.WeaversParadiseGlobalRendererDataHolder;
import xox.labvorty.weaversparadise.renderers.WeaversParadiseRenderTypes;
import xox.labvorty.weaversparadise.renderers.render_helper.HandWarmersModelRenderer;
import xox.labvorty.weaversparadise.renderers.render_helper.HandWarmersRenderingData;

public class HandWarmersWoolRenderer implements ICurioRenderer {
    private final ModelHandWarmers model;

    public HandWarmersWoolRenderer() {
        this.model = new ModelHandWarmers(Minecraft.getInstance().getEntityModels().bakeLayer(WeaversParadiseMobLayers.HAND_WARMERS));
    }

    @Override
    public <T extends LivingEntity, M extends EntityModel<T>> void render(ItemStack stack, SlotContext slotContext, PoseStack matrixStack, RenderLayerParent<T, M> renderLayerParent, MultiBufferSource renderTypeBuffer, int light, float limbSwing,
                                                                          float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (stack.getItem() instanceof HandWarmersWool handWarmersWool) {
            int primaryColorLeftOne = handWarmersWool.getItemMainColor(stack, "left", 1);
            int secondaryColorLeftOne = handWarmersWool.getItemSecondaryColor(stack, "left", 1);
            int primaryColorRightOne = handWarmersWool.getItemMainColor(stack, "right", 1);
            int secondaryColorRightOne = handWarmersWool.getItemSecondaryColor(stack, "right", 1);
            int primaryColorLeftTwo = handWarmersWool.getItemMainColor(stack, "left", 2);
            int secondaryColorLeftTwo = handWarmersWool.getItemSecondaryColor(stack, "left", 2);
            int primaryColorRightTwo = handWarmersWool.getItemMainColor(stack, "right", 2);
            int secondaryColorRightTwo = handWarmersWool.getItemSecondaryColor(stack, "right", 2);
            String dyeTypeLeftOne = handWarmersWool.getItemDyeType(stack, "left", 1);
            String dyeTypeRightOne = handWarmersWool.getItemDyeType(stack, "right", 1);
            String dyeTypeLeftTwo = handWarmersWool.getItemDyeType(stack, "left", 2);
            String dyeTypeRightTwo = handWarmersWool.getItemDyeType(stack, "right", 2);
            String stensilTypeLeft = handWarmersWool.getStensilType(stack, "left");
            String stensilTypeRight = handWarmersWool.getStensilType(stack, "right");
            int lightValueLeftOne = handWarmersWool.getItemLightValue(stack, "left", 1);
            int lightValueLeftTwo = handWarmersWool.getItemLightValue(stack, "left", 2);
            int lightValueRightOne = handWarmersWool.getItemLightValue(stack, "right", 1);
            int lightValueRightTwo = handWarmersWool.getItemLightValue(stack, "right", 2);

            LivingEntity entity = slotContext.entity();
            this.model.prepareMobModel(entity, limbSwing, limbSwingAmount, partialTicks);
            this.model.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

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
