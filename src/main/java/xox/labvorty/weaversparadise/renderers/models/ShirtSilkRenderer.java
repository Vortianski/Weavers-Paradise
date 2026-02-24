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
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.phys.Vec3;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.client.ICurioRenderer;
import xox.labvorty.weaversparadise.curios.WeaversParadiseMobLayers;
import xox.labvorty.weaversparadise.data.WeaversParadiseShirtTextureHandler;
import xox.labvorty.weaversparadise.init.WeaversParadiseItems;
import xox.labvorty.weaversparadise.items.ShirtCotton;
import xox.labvorty.weaversparadise.items.ShirtSilk;
import xox.labvorty.weaversparadise.model.ModelUpperWear;
import xox.labvorty.weaversparadise.renderers.WeaversParadiseGlobalRendererDataHolder;
import xox.labvorty.weaversparadise.renderers.WeaversParadiseRenderTypes;
import xox.labvorty.weaversparadise.renderers.render_helper.ShirtModelRenderer;
import xox.labvorty.weaversparadise.renderers.render_helper.ShirtRenderingData;

public class ShirtSilkRenderer implements ICurioRenderer {
    public static ModelUpperWear model;

    public ShirtSilkRenderer() {
        this.model = new ModelUpperWear(Minecraft.getInstance().getEntityModels().bakeLayer(WeaversParadiseMobLayers.UPPER_WEAR));
    }

    @Override
    public <T extends LivingEntity, M extends EntityModel<T>> void render(ItemStack stack, SlotContext slotContext, PoseStack matrixStack, RenderLayerParent<T, M> renderLayerParent, MultiBufferSource renderTypeBuffer, int light, float limbSwing,
                                                                          float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        LivingEntity entity = slotContext.entity();

        if (stack.getItem() instanceof ShirtSilk shirtSilk && !entity.getItemBySlot(EquipmentSlot.BODY).is(WeaversParadiseItems.ASTOLFO_ARMOR_CHESTPLATE)) {
            int primaryColorOne = shirtSilk.getItemMainColor(stack, 1);
            int secondaryColorOne = shirtSilk.getItemSecondaryColor(stack, 1);
            int primaryColorTwo = shirtSilk.getItemMainColor(stack, 2);
            int secondaryColorTwo = shirtSilk.getItemSecondaryColor(stack, 2);
            String dyeTypeOne = shirtSilk.getItemDyeType(stack, 1);
            String dyeTypeTwo = shirtSilk.getItemDyeType(stack, 2);
            String stensilType = shirtSilk.getStensilType(stack);
            int lightValueOne = shirtSilk.getItemLightValue(stack, 1);
            int lightValueTwo = shirtSilk.getItemLightValue(stack, 2);

            this.model.prepareMobModel(entity, limbSwing, limbSwingAmount, partialTicks);
            this.model.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

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
                            "silk"
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
