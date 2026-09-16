package xox.labvorty.weaversparadise.mixins.compat.wildfires_female_gender_mod;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.wildfire.render.GenderLayer;
import com.wildfire.render.WildfireModelRenderer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import oshi.util.tuples.Pair;
import xox.labvorty.vortylib.utilities.VortyLibCurioUtilities;
import xox.labvorty.weaversparadise.data.texture.ItemTexture;
import xox.labvorty.weaversparadise.data.texture.TextureRegistry;
import xox.labvorty.weaversparadise.items.clothing.*;
import xox.labvorty.weaversparadise.items.clothing.defined.*;
import xox.labvorty.weaversparadise.renderers.helpers.ColorHandlers;
import xox.labvorty.weaversparadise.renderers.helpers.RenderingUtils;
import xox.labvorty.weaversparadise.renderers.models.UpperwearModelRenderer;

@Mixin(GenderLayer.class)
public abstract class GenderLayerMixin<T extends LivingEntity, M extends HumanoidModel<T>> {
    @Shadow
    private static void renderBox(WildfireModelRenderer.ModelBox model, PoseStack matrixStack, VertexConsumer bufferIn, int light, int overlay, int color) {
    }

    @Unique
    private WildfireModelRenderer.BreastModelBox weaversparadise$leftClothingBox, weaversparadise$rightClothingBox;

    @Inject(
            method = "render(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;ILnet/minecraft/world/entity/LivingEntity;FFFFFF)V",
            at = @At("HEAD")
    )
    private void weaversparadise$render(
            @NotNull PoseStack matrixStack,
            @NotNull MultiBufferSource bufferSource,
            int light,
            @NotNull T entity,
            float limbAngle,
            float limbDistance,
            float partialTicks,
            float animationProgress,
            float headYaw,
            float headPitch,
            CallbackInfo ci
    ) {
        weaversparadise$leftClothingBox = new WildfireModelRenderer.BreastModelBox(32, 32, 0, 1, -4.0F, 0.0F, 0.0F, 4, 5, 4, 0.0F, false);
        weaversparadise$rightClothingBox = new WildfireModelRenderer.BreastModelBox(32, 32, 0, 1, 0.0F, 0.0F, 0.0F, 4, 5, 4, 0.0F, true);
    }

    @Inject(
            method = "renderBreast",
            at = @At("HEAD"),
            cancellable = true
    )
    private void weaversparadise$renderBreast(
            T entity,
            ItemStack armorStack,
            PoseStack matrixStack,
            MultiBufferSource bufferSource,
            @Nullable RenderType breastRenderType,
            int light,
            int overlay,
            float alpha,
            boolean left,
            boolean hasJacketLayer,
            CallbackInfo ci
    ) {
        VortyLibCurioUtilities.CurioMatch<Upperwear> match = VortyLibCurioUtilities.findFirstCurioOfType(entity, Upperwear.class);
        String material = "";
        UpperwearModelRenderer.Type type = UpperwearModelRenderer.Type.SHIRT;
        int primaryColorOne = 0;
        int secondaryColorOne = 0;
        int primaryColorTwo = 0;
        int secondaryColorTwo = 0;
        String dyeTypeOne = "";
        String dyeTypeTwo = "";
        String stensilType = "";
        int lightValueOne = 0;
        int lightValueTwo = 0;
        boolean shouldRender = false;
        ItemTexture itemTexture = null;

        if (match != null) {
            ItemStack itemStack = match.stack();

            if (itemStack.getItem() instanceof ShirtInterface shirtInterface) {
                switch (itemStack.getItem()) {
                    case ShirtCottonItem shirtCottonItem -> {
                        material = "cotton";
                    }
                    case ShirtSilkItem shirtSilkItem -> {
                        material = "silk";
                    }
                    default -> {
                        return;
                    }
                }

                primaryColorOne = shirtInterface.getItemMainColor(itemStack, 1);
                secondaryColorOne = shirtInterface.getItemSecondaryColor(itemStack, 1);
                primaryColorTwo = shirtInterface.getItemMainColor(itemStack, 2);
                secondaryColorTwo = shirtInterface.getItemSecondaryColor(itemStack, 2);
                dyeTypeOne = shirtInterface.getItemDyeType(itemStack, 1);
                dyeTypeTwo = shirtInterface.getItemDyeType(itemStack, 2);
                stensilType = shirtInterface.getStensilType(itemStack);
                lightValueOne = shirtInterface.getItemLightValue(itemStack, 1);
                lightValueTwo = shirtInterface.getItemLightValue(itemStack, 2);
                shouldRender = true;
                itemTexture = TextureRegistry.find( shirtInterface.getFlag(itemStack) ? "shirt_open" : "shirt", stensilType, material);
            } else if (itemStack.getItem() instanceof PulloverInterface pulloverInterface) {
                switch (itemStack.getItem()) {
                    case LongSleeveCottonItem longSleeveCottonItem -> {
                        material = "cotton";
                        type = UpperwearModelRenderer.Type.PULLOVER;
                    }
                    case SweaterWoolItem sweaterWoolItem -> {
                        material = "wool";
                        type = UpperwearModelRenderer.Type.PULLOVER;
                    }
                    default -> {
                        return;
                    }
                }

                primaryColorOne = pulloverInterface.getItemMainColor(itemStack, 1);
                secondaryColorOne = pulloverInterface.getItemSecondaryColor(itemStack, 1);
                primaryColorTwo = pulloverInterface.getItemMainColor(itemStack, 2);
                secondaryColorTwo = pulloverInterface.getItemSecondaryColor(itemStack, 2);
                dyeTypeOne = pulloverInterface.getItemDyeType(itemStack, 1);
                dyeTypeTwo = pulloverInterface.getItemDyeType(itemStack, 2);
                stensilType = pulloverInterface.getStensilType(itemStack);
                lightValueOne = pulloverInterface.getItemLightValue(itemStack, 1);
                lightValueTwo = pulloverInterface.getItemLightValue(itemStack, 2);
                shouldRender = true;
                itemTexture = TextureRegistry.find(type.getSerializedName(), stensilType, material);
            } else if (itemStack.getItem() instanceof TopsInterface topsInterface) {
                switch (itemStack.getItem()) {
                    case TShirtItem tShirtItem -> {
                        material = "cotton";
                        type = UpperwearModelRenderer.Type.TOPS;
                    }
                    case TankTopItem tankTopItem -> {
                        material = "silk";
                        type = UpperwearModelRenderer.Type.TOPS;
                    }
                    case WoolVestItem woolVestItem -> {
                        material = "wool";
                        type = UpperwearModelRenderer.Type.TOPS;
                    }
                    default -> {
                        return;
                    }
                }

                primaryColorOne = topsInterface.getItemMainColor(itemStack, 1);
                secondaryColorOne = topsInterface.getItemSecondaryColor(itemStack, 1);
                primaryColorTwo = topsInterface.getItemMainColor(itemStack, 2);
                secondaryColorTwo = topsInterface.getItemSecondaryColor(itemStack, 2);
                dyeTypeOne = topsInterface.getItemDyeType(itemStack, 1);
                dyeTypeTwo = topsInterface.getItemDyeType(itemStack, 2);
                stensilType = topsInterface.getStensilType(itemStack);
                lightValueOne = topsInterface.getItemLightValue(itemStack, 1);
                lightValueTwo = topsInterface.getItemLightValue(itemStack, 2);
                shouldRender = true;
                itemTexture = TextureRegistry.find(type.getSerializedName(), stensilType, material);
            } else if (itemStack.getItem() instanceof PonchoItem ponchoItem) {
                material = "cotton";
                type = UpperwearModelRenderer.Type.PONCHO;

                primaryColorOne = ponchoItem.getItemMainColor(itemStack, 1);
                secondaryColorOne = ponchoItem.getItemSecondaryColor(itemStack, 1);
                primaryColorTwo = ponchoItem.getItemMainColor(itemStack, 2);
                secondaryColorTwo = ponchoItem.getItemSecondaryColor(itemStack, 2);
                dyeTypeOne = ponchoItem.getItemDyeType(itemStack, 1);
                dyeTypeTwo = ponchoItem.getItemDyeType(itemStack, 2);
                stensilType = ponchoItem.getStensilType(itemStack);
                lightValueOne = ponchoItem.getItemLightValue(itemStack, 1);
                lightValueTwo = ponchoItem.getItemLightValue(itemStack, 2);
                shouldRender = true;
                itemTexture = TextureRegistry.find(type.getSerializedName(), stensilType, material);
            } else if (itemStack.getItem() instanceof CottonCropTopLongSleevedItem cottonCropTopLongSleevedItem) {
                material = "cotton";
                type = UpperwearModelRenderer.Type.CROP_TOP_LONG_SLEEVED;

                primaryColorOne = cottonCropTopLongSleevedItem.getItemMainColor(itemStack, 1);
                secondaryColorOne = cottonCropTopLongSleevedItem.getItemSecondaryColor(itemStack, 1);
                primaryColorTwo = cottonCropTopLongSleevedItem.getItemMainColor(itemStack, 2);
                secondaryColorTwo = cottonCropTopLongSleevedItem.getItemSecondaryColor(itemStack, 2);
                dyeTypeOne = cottonCropTopLongSleevedItem.getItemDyeType(itemStack, 1);
                dyeTypeTwo = cottonCropTopLongSleevedItem.getItemDyeType(itemStack, 2);
                stensilType = cottonCropTopLongSleevedItem.getStensilType(itemStack);
                lightValueOne = cottonCropTopLongSleevedItem.getItemLightValue(itemStack, 1);
                lightValueTwo = cottonCropTopLongSleevedItem.getItemLightValue(itemStack, 2);
                shouldRender = true;
                itemTexture = TextureRegistry.find(cottonCropTopLongSleevedItem.getFlag(itemStack) ? "crop_top_long_sleeved_sag" : "crop_top_long_sleeved", stensilType, material);
            } else {
                return;
            }
        }

        if (armorStack.getItem() instanceof Upperwear) {
            if (armorStack.getItem() instanceof ShirtInterface shirtInterface) {
                switch (armorStack.getItem()) {
                    case ShirtCottonItem shirtCottonItem -> {
                        material = "cotton";
                    }
                    case ShirtSilkItem shirtSilkItem -> {
                        material = "silk";
                    }
                    default -> {
                        return;
                    }
                }

                primaryColorOne = shirtInterface.getItemMainColor(armorStack, 1);
                secondaryColorOne = shirtInterface.getItemSecondaryColor(armorStack, 1);
                primaryColorTwo = shirtInterface.getItemMainColor(armorStack, 2);
                secondaryColorTwo = shirtInterface.getItemSecondaryColor(armorStack, 2);
                dyeTypeOne = shirtInterface.getItemDyeType(armorStack, 1);
                dyeTypeTwo = shirtInterface.getItemDyeType(armorStack, 2);
                stensilType = shirtInterface.getStensilType(armorStack);
                lightValueOne = shirtInterface.getItemLightValue(armorStack, 1);
                lightValueTwo = shirtInterface.getItemLightValue(armorStack, 2);
                shouldRender = true;
                itemTexture = TextureRegistry.find( shirtInterface.getFlag(armorStack) ? "shirt_open" : "shirt", stensilType, material);
            } else if (armorStack.getItem() instanceof PulloverInterface pulloverInterface) {
                switch (armorStack.getItem()) {
                    case LongSleeveCottonItem longSleeveCottonItem -> {
                        material = "cotton";
                        type = UpperwearModelRenderer.Type.PULLOVER;
                    }
                    case SweaterWoolItem sweaterWoolItem -> {
                        material = "wool";
                        type = UpperwearModelRenderer.Type.PULLOVER;
                    }
                    default -> {
                        return;
                    }
                }

                primaryColorOne = pulloverInterface.getItemMainColor(armorStack, 1);
                secondaryColorOne = pulloverInterface.getItemSecondaryColor(armorStack, 1);
                primaryColorTwo = pulloverInterface.getItemMainColor(armorStack, 2);
                secondaryColorTwo = pulloverInterface.getItemSecondaryColor(armorStack, 2);
                dyeTypeOne = pulloverInterface.getItemDyeType(armorStack, 1);
                dyeTypeTwo = pulloverInterface.getItemDyeType(armorStack, 2);
                stensilType = pulloverInterface.getStensilType(armorStack);
                lightValueOne = pulloverInterface.getItemLightValue(armorStack, 1);
                lightValueTwo = pulloverInterface.getItemLightValue(armorStack, 2);
                shouldRender = true;
                itemTexture = TextureRegistry.find(type.getSerializedName(), stensilType, material);
            } else if (armorStack.getItem() instanceof TopsInterface topsInterface) {
                switch (armorStack.getItem()) {
                    case TShirtItem tShirtItem -> {
                        material = "cotton";
                        type = UpperwearModelRenderer.Type.TOPS;
                    }
                    case TankTopItem tankTopItem -> {
                        material = "silk";
                        type = UpperwearModelRenderer.Type.TOPS;
                    }
                    case WoolVestItem woolVestItem -> {
                        material = "wool";
                        type = UpperwearModelRenderer.Type.TOPS;
                    }
                    default -> {
                        return;
                    }
                }

                primaryColorOne = topsInterface.getItemMainColor(armorStack, 1);
                secondaryColorOne = topsInterface.getItemSecondaryColor(armorStack, 1);
                primaryColorTwo = topsInterface.getItemMainColor(armorStack, 2);
                secondaryColorTwo = topsInterface.getItemSecondaryColor(armorStack, 2);
                dyeTypeOne = topsInterface.getItemDyeType(armorStack, 1);
                dyeTypeTwo = topsInterface.getItemDyeType(armorStack, 2);
                stensilType = topsInterface.getStensilType(armorStack);
                lightValueOne = topsInterface.getItemLightValue(armorStack, 1);
                lightValueTwo = topsInterface.getItemLightValue(armorStack, 2);
                shouldRender = true;
                itemTexture = TextureRegistry.find(type.getSerializedName(), stensilType, material);
            } else if (armorStack.getItem() instanceof PonchoItem ponchoItem) {
                material = "cotton";
                type = UpperwearModelRenderer.Type.PONCHO;

                primaryColorOne = ponchoItem.getItemMainColor(armorStack, 1);
                secondaryColorOne = ponchoItem.getItemSecondaryColor(armorStack, 1);
                primaryColorTwo = ponchoItem.getItemMainColor(armorStack, 2);
                secondaryColorTwo = ponchoItem.getItemSecondaryColor(armorStack, 2);
                dyeTypeOne = ponchoItem.getItemDyeType(armorStack, 1);
                dyeTypeTwo = ponchoItem.getItemDyeType(armorStack, 2);
                stensilType = ponchoItem.getStensilType(armorStack);
                lightValueOne = ponchoItem.getItemLightValue(armorStack, 1);
                lightValueTwo = ponchoItem.getItemLightValue(armorStack, 2);
                shouldRender = true;
                itemTexture = TextureRegistry.find(type.getSerializedName(), stensilType, material);
            } else if (armorStack.getItem() instanceof CottonCropTopLongSleevedItem cottonCropTopLongSleevedItem) {
                material = "cotton";
                type = UpperwearModelRenderer.Type.CROP_TOP_LONG_SLEEVED;

                primaryColorOne = cottonCropTopLongSleevedItem.getItemMainColor(armorStack, 1);
                secondaryColorOne = cottonCropTopLongSleevedItem.getItemSecondaryColor(armorStack, 1);
                primaryColorTwo = cottonCropTopLongSleevedItem.getItemMainColor(armorStack, 2);
                secondaryColorTwo = cottonCropTopLongSleevedItem.getItemSecondaryColor(armorStack, 2);
                dyeTypeOne = cottonCropTopLongSleevedItem.getItemDyeType(armorStack, 1);
                dyeTypeTwo = cottonCropTopLongSleevedItem.getItemDyeType(armorStack, 2);
                stensilType = cottonCropTopLongSleevedItem.getStensilType(armorStack);
                lightValueOne = cottonCropTopLongSleevedItem.getItemLightValue(armorStack, 1);
                lightValueTwo = cottonCropTopLongSleevedItem.getItemLightValue(armorStack, 2);
                shouldRender = true;
                itemTexture = TextureRegistry.find(cottonCropTopLongSleevedItem.getFlag(armorStack) ? "crop_top_long_sleeved_sag" : "crop_top_long_sleeved", stensilType, material);
            } else {
                return;
            }
        }

        if (shouldRender) {
            matrixStack.pushPose();
            matrixStack.translate(left ? 0.001F : -0.001F, 0.015F, -0.015F);
            matrixStack.scale(1.05F, 1.0F, 1.0F);

            WildfireModelRenderer.BreastModelBox armor = left ? weaversparadise$leftClothingBox : weaversparadise$rightClothingBox;
            Pair<Integer, Integer> fCO = ColorHandlers.handle(dyeTypeOne, primaryColorOne, secondaryColorOne, lightValueOne, entity, light, 0);
            Pair<Integer, Integer> fCT = ColorHandlers.handle(dyeTypeTwo, primaryColorTwo, secondaryColorTwo, lightValueTwo, entity, light, 0);
            RenderingUtils renderingUtils = new RenderingUtils();

            VertexConsumer vertexConsumer1 = renderingUtils.parseVC(bufferSource, dyeTypeOne, itemTexture.getTextureOne(), type.getSerializedName());
            renderBox(armor, matrixStack, vertexConsumer1, fCO.getB(), OverlayTexture.NO_OVERLAY, fCO.getA());
            if (itemTexture.getRenderType()) {
                VertexConsumer vertexConsumer2 = renderingUtils.parseVC(bufferSource, dyeTypeTwo, itemTexture.getTextureTwo(), type.getSerializedName());
                renderBox(armor, matrixStack, vertexConsumer2, fCT.getB(), OverlayTexture.NO_OVERLAY, fCT.getA());
            }

            matrixStack.popPose();
            ci.cancel();
        }
    }
}
