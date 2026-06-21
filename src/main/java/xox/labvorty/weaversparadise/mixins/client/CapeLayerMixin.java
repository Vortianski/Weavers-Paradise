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
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import oshi.util.tuples.Pair;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.capability.ICuriosItemHandler;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import xox.labvorty.weaversparadise.data.texture.ItemTexture;
import xox.labvorty.weaversparadise.data.texture.TextureRegistry;
import xox.labvorty.weaversparadise.items.clothing.CapeCottonItem;
import xox.labvorty.weaversparadise.items.clothing.CapeSilkItem;
import xox.labvorty.weaversparadise.items.clothing.CapeWoolItem;
import xox.labvorty.weaversparadise.items.clothing.defined.CapeInterface;
import xox.labvorty.weaversparadise.mixin_helpers.PlayerModelInterface;
import xox.labvorty.weaversparadise.renderers.helpers.ColorHandlers;
import xox.labvorty.weaversparadise.renderers.helpers.RenderingUtils;

import java.util.Optional;

@Mixin(CapeLayer.class)
public abstract class CapeLayerMixin extends RenderLayer<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> {
    public CapeLayerMixin(RenderLayerParent<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> renderer) {
        super(renderer);
    }

    @Inject(
            method = "render(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;ILnet/minecraft/client/player/AbstractClientPlayer;FFFFFF)V",
            at = @At("HEAD"),
            cancellable = true
    )
    private void weaversparadise$replaceCape(PoseStack poseStack, MultiBufferSource buffer, int packedLight, AbstractClientPlayer livingEntity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, CallbackInfo ci) {
        if (!livingEntity.isInvisible() && livingEntity.isModelPartShown(PlayerModelPart.CAPE)) {
            ItemStack itemstack = livingEntity.getItemBySlot(EquipmentSlot.CHEST);
            if (!itemstack.is(Items.ELYTRA)) {
                Minecraft minecraft = Minecraft.getInstance();
                Optional<ICuriosItemHandler> handler = CuriosApi.getCuriosInventory(livingEntity);
                if (minecraft.player == null) return;

                if (handler.isPresent()) {
                    if (handler.get().isEquipped(stack -> {
                        return switch (stack.getItem()) {
                            case CapeCottonItem capeCottonItem -> true;
                            case CapeSilkItem capeSilkItem -> true;
                            case CapeWoolItem capeWoolItem -> true;
                            default -> false;
                        };
                    })) {
                        ICurioStacksHandler stacksHandler = handler.get().getCurios().get("cape");
                        if (stacksHandler == null) {
                            return;
                        }

                        String type = "cotton";
                        int primaryColorOne = -1;
                        int secondaryColorOne = -1;
                        int primaryColorTwo = -1;
                        int secondaryColorTwo = -1;
                        String dyeTypeOne = "default";
                        String dyeTypeTwo = "default";
                        String stencilType = "default";
                        int lightValueOne = packedLight;
                        int lightValueTwo = packedLight;
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stacksHandler.getStacks().getStackInSlot(i);

                            if (stack.getItem() instanceof CapeInterface capeInterface) {
                                primaryColorOne = capeInterface.getItemMainColor(stack, 1);
                                secondaryColorOne = capeInterface.getItemSecondaryColor(stack, 1);
                                primaryColorTwo = capeInterface.getItemMainColor(stack, 2);
                                secondaryColorTwo = capeInterface.getItemSecondaryColor(stack, 2);
                                dyeTypeOne = capeInterface.getItemDyeType(stack, 1);
                                dyeTypeTwo = capeInterface.getItemDyeType(stack, 2);
                                stencilType = capeInterface.getStensilType(stack);
                                lightValueOne = capeInterface.getItemLightValue(stack, 1);
                                lightValueTwo = capeInterface.getItemLightValue(stack, 2);

                                if (stack.getItem() instanceof CapeCottonItem capeCottonItem) {
                                    type = "cotton";
                                    break;
                                }

                                if (stack.getItem() instanceof CapeSilkItem capeSilkItem) {
                                    type = "silk";
                                    break;
                                }

                                if (stack.getItem() instanceof CapeWoolItem capeWoolItem) {
                                    type = "wool";
                                    break;
                                }
                            }
                        }

                        Pair<Integer, Integer> col1 = ColorHandlers.handle(dyeTypeOne, primaryColorOne, secondaryColorOne, lightValueOne, minecraft.player, packedLight, (int)minecraft.level.getGameTime());
                        Pair<Integer, Integer> col2 = ColorHandlers.handle(dyeTypeTwo, primaryColorTwo, secondaryColorTwo, lightValueTwo, minecraft.player, packedLight, (int)minecraft.level.getGameTime());
                        int finalColorOne = col1.getA();
                        int finalColorTwo = col2.getA();
                        int finalLightOne = col1.getB();
                        int finalLightTwo = col2.getB();

                        ItemTexture texture = TextureRegistry.find("cape", stencilType, type);
                        RenderingUtils renderingUtils = new RenderingUtils();

                        poseStack.pushPose();
                        poseStack.translate(0.0F, 0.0F, 0.125F);
                        double d0 = Mth.lerp((double)partialTicks, livingEntity.xCloakO, livingEntity.xCloak) - Mth.lerp((double)partialTicks, livingEntity.xo, livingEntity.getX());
                        double d1 = Mth.lerp((double)partialTicks, livingEntity.yCloakO, livingEntity.yCloak) - Mth.lerp((double)partialTicks, livingEntity.yo, livingEntity.getY());
                        double d2 = Mth.lerp((double)partialTicks, livingEntity.zCloakO, livingEntity.zCloak) - Mth.lerp((double)partialTicks, livingEntity.zo, livingEntity.getZ());
                        float f = Mth.rotLerp(partialTicks, livingEntity.yBodyRotO, livingEntity.yBodyRot);
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

                        float f4 = Mth.lerp(partialTicks, livingEntity.oBob, livingEntity.bob);
                        f1 += Mth.sin(Mth.lerp(partialTicks, livingEntity.walkDistO, livingEntity.walkDist) * 6.0F) * 32.0F * f4;
                        if (livingEntity.isCrouching()) {
                            f1 += 25.0F;
                        }

                        poseStack.mulPose(Axis.XP.rotationDegrees(6.0F + f2 / 2.0F + f1));
                        poseStack.mulPose(Axis.ZP.rotationDegrees(f3 / 2.0F));
                        poseStack.mulPose(Axis.YP.rotationDegrees(180.0F - f3 / 2.0F));
                        VertexConsumer vertexConsumer1 = renderingUtils.parseVC(buffer, dyeTypeOne, texture.getTextureOne(),"cape");
                        ((PlayerModelInterface)this.getParentModel()).getCloak().render(poseStack, vertexConsumer1, finalLightOne, OverlayTexture.NO_OVERLAY, finalColorOne);

                        if (texture.getRenderType()) {
                            VertexConsumer vertexConsumer2 = renderingUtils.parseVC(buffer, dyeTypeTwo, texture.getTextureTwo(),"cape");
                            ((PlayerModelInterface)this.getParentModel()).getCloak().render(poseStack, vertexConsumer2, finalLightTwo, OverlayTexture.NO_OVERLAY, finalColorTwo);
                        }
                        poseStack.popPose();
                        ci.cancel();
                    } else {
                        return;
                    }
                }
            }
        }
    }
}
