package xox.labvorty.weaversparadise.renderers.models;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.blockentity.TheEndPortalRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.server.packs.resources.ResourceProvider;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.phys.Vec3;
import xox.labvorty.weaversparadise.curios.WeaversParadiseMobLayers;
import xox.labvorty.weaversparadise.data.WeaversParadiseThighHighsTextureHandler;
import xox.labvorty.weaversparadise.items.ThighHighsCotton;
import xox.labvorty.weaversparadise.model.ThighHighsModel;
import top.theillusivec4.curios.api.client.ICurioRenderer;
import top.theillusivec4.curios.api.SlotContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import xox.labvorty.weaversparadise.renderers.WeaversParadiseCustomShaders;
import xox.labvorty.weaversparadise.renderers.WeaversParadiseGlobalRendererDataHolder;
import xox.labvorty.weaversparadise.renderers.WeaversParadiseRegisterCustomShaders;
import xox.labvorty.weaversparadise.renderers.WeaversParadiseRenderTypes;
import xox.labvorty.weaversparadise.renderers.render_helper.ThighHighModelRenderer;
import xox.labvorty.weaversparadise.renderers.render_helper.ThighHighsRenderingData;

import java.io.IOException;

public class ThighsHighsCottonRenderer implements ICurioRenderer {
    private final ThighHighsModel model;

    public ThighsHighsCottonRenderer() {
        this.model = new ThighHighsModel(Minecraft.getInstance().getEntityModels().bakeLayer(WeaversParadiseMobLayers.THIGH_HIGHS));
    }

    @Override
    public <T extends LivingEntity, M extends EntityModel<T>> void render(ItemStack stack, SlotContext slotContext, PoseStack matrixStack, RenderLayerParent<T, M> renderLayerParent, MultiBufferSource renderTypeBuffer, int light, float limbSwing,
                                                                          float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (stack.getItem() instanceof ThighHighsCotton cottonThighHighs) {
            LivingEntity entity = slotContext.entity();
            this.model.prepareMobModel(entity, limbSwing, limbSwingAmount, partialTicks);
            this.model.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

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
