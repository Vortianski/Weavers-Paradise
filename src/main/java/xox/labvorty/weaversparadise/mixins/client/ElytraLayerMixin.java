package xox.labvorty.weaversparadise.mixins.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.VertexMultiConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ElytraModel;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.ElytraLayer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.util.LazyOptional;
import org.joml.Vector4f;
import org.spongepowered.asm.mixin.*;
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

import java.util.Optional;

@Mixin(ElytraLayer.class)
public abstract class ElytraLayerMixin extends RenderLayer<LivingEntity, EntityModel<LivingEntity>> {
    @Mutable
    @Final
    @Shadow
    private final ElytraModel<LivingEntity> elytraModel;

    @Shadow
    public abstract boolean shouldRender(ItemStack stack, LivingEntity entity);

    public ElytraLayerMixin(RenderLayerParent<LivingEntity, EntityModel<LivingEntity>> pRenderer, ElytraModel<LivingEntity> elytraModel) {
        super(pRenderer);
        this.elytraModel = elytraModel;
    }

    @Inject(
            at = @At("HEAD"),
            method = "render(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;ILnet/minecraft/world/entity/LivingEntity;FFFFFF)V",
            cancellable = true
    )
    private void weaversparadise$replaceElytra(PoseStack poseStack, MultiBufferSource multiBufferSource, int packedLight, LivingEntity livingEntity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, CallbackInfo ci) {
        ItemStack itemstack = livingEntity.getItemBySlot(EquipmentSlot.CHEST);
        if (this.shouldRender(itemstack, livingEntity)) {
            if (livingEntity instanceof AbstractClientPlayer abstractClientPlayer) {
                Minecraft minecraft = Minecraft.getInstance();
                LazyOptional<ICuriosItemHandler> handler = CuriosApi.getCuriosInventory(abstractClientPlayer);
                if (minecraft.player == null) return;

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
                        int lightValueOne = packedLight;
                        int lightValueTwo = packedLight;

                        if (optionalSlotResult.isPresent()) {
                            ItemStack stack = optionalSlotResult.get().stack();

                            if (stack.getItem() instanceof CapeCottonItem || stack.getItem() instanceof CapeSilkItem capeSilkItem || stack.getItem() instanceof CapeWoolItem capeWoolItem) {
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
                                    }

                                    if (stack.getItem() instanceof CapeSilkItem capeSilkItem) {
                                        type = "silk";
                                    }

                                    if (stack.getItem() instanceof CapeWoolItem capeWoolItem) {
                                        type = "wool";
                                    }
                                }

                                int ticks = 0;
                                if (minecraft.level != null) {
                                    ticks = (int)minecraft.level.getGameTime();
                                }

                                Pair<Integer, Integer> col1 = ColorHandlers.handle(dyeTypeOne, primaryColorOne, secondaryColorOne, lightValueOne, minecraft.player, packedLight, ticks);
                                Pair<Integer, Integer> col2 = ColorHandlers.handle(dyeTypeTwo, primaryColorTwo, secondaryColorTwo, lightValueTwo, minecraft.player, packedLight, ticks);
                                Vector4f finalColorOne = weaversParadiseForge1_20_1$colorIntToVector4f(col1.getA());
                                Vector4f finalColorTwo = weaversParadiseForge1_20_1$colorIntToVector4f(col2.getA());
                                int finalLightOne = col1.getB();
                                int finalLightTwo = col2.getB();

                                ItemTexture texture = TextureRegistry.find("cape", stencilType, type);
                                RenderingUtils renderingUtils = new RenderingUtils();

                                poseStack.pushPose();
                                poseStack.translate(0.0F, 0.0F, 0.125F);
                                this.getParentModel().copyPropertiesTo(this.elytraModel);
                                this.elytraModel.setupAnim(livingEntity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

                                VertexConsumer vertexConsumer1 = itemstack.hasFoil() ? VertexMultiConsumer.create(multiBufferSource.getBuffer(RenderType.armorEntityGlint()), renderingUtils.parseVC(multiBufferSource, dyeTypeOne, texture.getTextureOne(),"cape")) : renderingUtils.parseVC(multiBufferSource, dyeTypeOne, texture.getTextureOne(),"cape");
                                this.elytraModel.renderToBuffer(poseStack, vertexConsumer1, finalLightOne, OverlayTexture.NO_OVERLAY, finalColorOne.x, finalColorOne.y, finalColorOne.z, finalColorOne.w);

                                if (texture.getRenderType()) {
                                    VertexConsumer vertexConsumer2 = itemstack.hasFoil() ? VertexMultiConsumer.create(multiBufferSource.getBuffer(RenderType.armorEntityGlint()), renderingUtils.parseVC(multiBufferSource, dyeTypeTwo, texture.getTextureTwo(),"cape")) : renderingUtils.parseVC(multiBufferSource, dyeTypeTwo, texture.getTextureTwo(),"cape");
                                    this.elytraModel.renderToBuffer(poseStack, vertexConsumer2, finalLightTwo, OverlayTexture.NO_OVERLAY, finalColorTwo.x, finalColorTwo.y, finalColorTwo.z, finalColorTwo.w);
                                }
                                poseStack.popPose();
                                ci.cancel();
                            }
                        }
                    });
                } else {
                    return;
                }
            }
        }
    }

    @Unique
    private static Vector4f weaversParadiseForge1_20_1$colorIntToVector4f(int color) {
        float a = ((color >> 24) & 0xFF) / 255.0f;
        float r = ((color >> 16) & 0xFF) / 255.0f;
        float g = ((color >> 8) & 0xFF) / 255.0f;
        float b = (color & 0xFF) / 255.0f;
        return new Vector4f(r, g, b, a);
    }
}
