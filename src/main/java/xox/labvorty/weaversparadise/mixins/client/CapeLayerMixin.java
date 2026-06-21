package xox.labvorty.weaversparadise.mixins.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.CapeLayer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.PlayerModelPart;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.util.LazyOptional;
import org.joml.Vector4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import oshi.util.tuples.Pair;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotResult;
import top.theillusivec4.curios.api.type.capability.ICuriosItemHandler;
import xox.labvorty.weaversparadise.data.texture.ItemTexture;
import xox.labvorty.weaversparadise.data.texture.TextureRegistry;
import xox.labvorty.weaversparadise.items.clothing.CapeCottonItem;
import xox.labvorty.weaversparadise.items.clothing.CapeSilkItem;
import xox.labvorty.weaversparadise.items.clothing.CapeWoolItem;
import xox.labvorty.weaversparadise.items.clothing.defined.CapeInterface;
import xox.labvorty.weaversparadise.renderers.helpers.ColorHandlers;
import xox.labvorty.weaversparadise.renderers.helpers.RenderingUtils;
import xox.labvorty.weaversparadise.utils.PlayerModelInterface;

import java.util.Optional;

@Mixin(CapeLayer.class)
public abstract class CapeLayerMixin extends RenderLayer<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> {
    public CapeLayerMixin(RenderLayerParent<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> pRenderer) {
        super(pRenderer);
    }

    @Inject(
            method = "render(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;ILnet/minecraft/client/player/AbstractClientPlayer;FFFFFF)V",
            at = @At("HEAD"),
            cancellable = true
    )
    private void weaversparadise$replaceCape(PoseStack poseStack, MultiBufferSource bufferSource, int light, AbstractClientPlayer abstractClientPlayer, float limbSwing, float limbSwingAmount, float partialTick, float ageInTicks, float netHeadYaw, float headPitch, CallbackInfo ci) {
        if (!abstractClientPlayer.isInvisible() && abstractClientPlayer.isModelPartShown(PlayerModelPart.CAPE)) {
            ItemStack itemstack = abstractClientPlayer.getItemBySlot(EquipmentSlot.CHEST);
            if (!itemstack.is(Items.ELYTRA)) {
                LazyOptional<ICuriosItemHandler> handler = CuriosApi.getCuriosInventory(abstractClientPlayer);

                if (handler.isPresent()) {
                    handler.ifPresent((curiosHandler) -> {
                        Optional<SlotResult> optionalSlotResult = curiosHandler.findFirstCurio(stack -> stack.getItem() instanceof CapeInterface capeInterface);

                        String type = "cotton";
                        int primaryColorOne = -1;
                        int secondaryColorOne = -1;
                        int primaryColorTwo = -1;
                        int secondaryColorTwo = -1;
                        String dyeTypeOne = "default";
                        String dyeTypeTwo = "default";
                        String stencilType = "default";
                        int lightValueOne = light;
                        int lightValueTwo = light;

                        if (optionalSlotResult.isPresent()) {
                            Minecraft minecraft = Minecraft.getInstance();
                            SlotResult slotResult = optionalSlotResult.get();
                            ItemStack itemStack = slotResult.stack();

                            if (!itemStack.isEmpty()) {
                                if (itemStack.getItem() instanceof CapeInterface capeInterface) {
                                    primaryColorOne = capeInterface.getItemMainColor(itemStack, 1);
                                    secondaryColorOne = capeInterface.getItemSecondaryColor(itemStack, 1);
                                    primaryColorTwo = capeInterface.getItemMainColor(itemStack, 2);
                                    secondaryColorTwo = capeInterface.getItemSecondaryColor(itemStack, 2);
                                    dyeTypeOne = capeInterface.getItemDyeType(itemStack, 1);
                                    dyeTypeTwo = capeInterface.getItemDyeType(itemStack, 2);
                                    stencilType = capeInterface.getStensilType(itemStack);
                                    lightValueOne = capeInterface.getItemLightValue(itemStack, 1);
                                    lightValueTwo = capeInterface.getItemLightValue(itemStack, 2);
                                }

                                if (itemStack.getItem() instanceof CapeCottonItem capeCottonItem) {
                                    type = "cotton";
                                }

                                if (itemStack.getItem() instanceof CapeSilkItem capeSilkItem) {
                                    type = "silk";
                                }

                                if (itemStack.getItem() instanceof CapeWoolItem capeWoolItem) {
                                    type = "wool";
                                }

                                Pair<Integer, Integer> col1 = ColorHandlers.handle(dyeTypeOne, primaryColorOne, secondaryColorOne, lightValueOne, minecraft.player, light, (int)minecraft.level.getGameTime());
                                Pair<Integer, Integer> col2 = ColorHandlers.handle(dyeTypeTwo, primaryColorTwo, secondaryColorTwo, lightValueTwo, minecraft.player, light, (int)minecraft.level.getGameTime());
                                Vector4f finalColorOne = colorIntToVector4f(col1.getA());
                                Vector4f finalColorTwo = colorIntToVector4f(col2.getA());
                                int finalLightOne = col1.getB();
                                int finalLightTwo = col2.getB();

                                ItemTexture texture = TextureRegistry.find("cape", stencilType, type);
                                RenderingUtils renderingUtils = new RenderingUtils();

                                poseStack.pushPose();
                                poseStack.translate(0.0F, 0.0F, 0.125F);
                                double d0 = Mth.lerp((double)partialTick, abstractClientPlayer.xCloakO, abstractClientPlayer.xCloak) - Mth.lerp((double)partialTick, abstractClientPlayer.xo, abstractClientPlayer.getX());
                                double d1 = Mth.lerp((double)partialTick, abstractClientPlayer.yCloakO, abstractClientPlayer.yCloak) - Mth.lerp((double)partialTick, abstractClientPlayer.yo, abstractClientPlayer.getY());
                                double d2 = Mth.lerp((double)partialTick, abstractClientPlayer.zCloakO, abstractClientPlayer.zCloak) - Mth.lerp((double)partialTick, abstractClientPlayer.zo, abstractClientPlayer.getZ());
                                float f = Mth.rotLerp(partialTick, abstractClientPlayer.yBodyRotO, abstractClientPlayer.yBodyRot);
                                double d3 = (double)Mth.sin(f * ((float)Math.PI / 180F));
                                double d4 = (double)(-Mth.cos(f * ((float)Math.PI / 180F)));
                                float f1 = (float)d1 * 10.0F;
                                f1 = Mth.clamp(f1, -6.0F, 32.0F);
                                float f2 = (float)(d0 * d3 + d2 * d4) * 100.0F;
                                f2 = Mth.clamp(f2, 0.0F, 150.0F);
                                float f3 = (float)(d0 * d4 - d2 * d3) * 100.0F;
                                f3 = Mth.clamp(f3, -20.0F, 20.0F);
                                if (f2 < 0.0F) {
                                    f2 = 0.0F;
                                }

                                float f4 = Mth.lerp(partialTick, abstractClientPlayer.oBob, abstractClientPlayer.bob);
                                f1 += Mth.sin(Mth.lerp(partialTick, abstractClientPlayer.walkDistO, abstractClientPlayer.walkDist) * 6.0F) * 32.0F * f4;
                                if (abstractClientPlayer.isCrouching()) {
                                    f1 += 25.0F;
                                }

                                poseStack.mulPose(Axis.XP.rotationDegrees(6.0F + f2 / 2.0F + f1));
                                poseStack.mulPose(Axis.ZP.rotationDegrees(f3 / 2.0F));
                                poseStack.mulPose(Axis.YP.rotationDegrees(180.0F - f3 / 2.0F));
                                VertexConsumer vertexConsumer1 = renderingUtils.parseVC(bufferSource, dyeTypeOne, texture.getTextureOne(),"cape");
                                ((PlayerModelInterface)this.getParentModel()).getCloak().render(poseStack, vertexConsumer1, finalLightOne, OverlayTexture.NO_OVERLAY, finalColorOne.x, finalColorOne.y, finalColorOne.z, finalColorOne.w);
                                if (texture.getRenderType()) {
                                    VertexConsumer vertexConsumer2 = renderingUtils.parseVC(bufferSource, dyeTypeTwo, texture.getTextureTwo(),"cape");
                                    ((PlayerModelInterface)this.getParentModel()).getCloak().render(poseStack, vertexConsumer2, finalLightTwo, OverlayTexture.NO_OVERLAY, finalColorTwo.x, finalColorTwo.y, finalColorTwo.z, finalColorTwo.w);
                                }
                                poseStack.popPose();

                                ci.cancel();
                            }
                        }
                    });
                }
            }
        }
    }

    @Unique
    private static Vector4f colorIntToVector4f(int color) {
        float a = ((color >> 24) & 0xFF) / 255.0f;
        float r = ((color >> 16) & 0xFF) / 255.0f;
        float g = ((color >> 8) & 0xFF) / 255.0f;
        float b = (color & 0xFF) / 255.0f;
        return new Vector4f(r, g, b, a);
    }
}
