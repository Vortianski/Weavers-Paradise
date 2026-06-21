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
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
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
import xox.labvorty.weaversparadise.renderers.helpers.ColorHandlers;
import xox.labvorty.weaversparadise.renderers.helpers.RenderingUtils;

import java.util.Optional;

@Mixin(ElytraLayer.class)
public abstract class ElytraLayerMixin extends RenderLayer<LivingEntity, EntityModel<LivingEntity>> {
    @Shadow
    private final ElytraModel<LivingEntity> elytraModel;

    @Shadow
    public abstract ResourceLocation getElytraTexture(ItemStack stack, LivingEntity entity);

    @Shadow
    public abstract boolean shouldRender(ItemStack stack, LivingEntity entity);

    public ElytraLayerMixin(RenderLayerParent<LivingEntity, EntityModel<LivingEntity>> renderer, ElytraModel<LivingEntity> elytraModel) {
        super(renderer);
        this.elytraModel = elytraModel;
    }

    @Inject(
            method = "render(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;ILnet/minecraft/world/entity/LivingEntity;FFFFFF)V",
            at = @At("HEAD"),
            cancellable = true
    )
    private void weaversparadise$replaceElytra(PoseStack poseStack, MultiBufferSource buffer, int packedLight, LivingEntity livingEntity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, CallbackInfo ci) {
        ItemStack itemstack = livingEntity.getItemBySlot(EquipmentSlot.CHEST);
        if (this.shouldRender(itemstack, livingEntity)) {
            if (livingEntity instanceof AbstractClientPlayer abstractClientPlayer) {
                Minecraft minecraft = Minecraft.getInstance();
                Optional<ICuriosItemHandler> handler = CuriosApi.getCuriosInventory(abstractClientPlayer);
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
                        this.getParentModel().copyPropertiesTo(this.elytraModel);
                        this.elytraModel.setupAnim(livingEntity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

                        VertexConsumer vertexConsumer1 = itemstack.hasFoil() ? VertexMultiConsumer.create(buffer.getBuffer(RenderType.armorEntityGlint()), renderingUtils.parseVC(buffer, dyeTypeOne, texture.getTextureOne(),"cape")) : renderingUtils.parseVC(buffer, dyeTypeOne, texture.getTextureOne(),"cape");
                        this.elytraModel.renderToBuffer(poseStack, vertexConsumer1, finalLightOne, OverlayTexture.NO_OVERLAY, finalColorOne);

                        if (texture.getRenderType()) {
                            VertexConsumer vertexConsumer2 = itemstack.hasFoil() ? VertexMultiConsumer.create(buffer.getBuffer(RenderType.armorEntityGlint()), renderingUtils.parseVC(buffer, dyeTypeTwo, texture.getTextureTwo(),"cape")) : renderingUtils.parseVC(buffer, dyeTypeTwo, texture.getTextureTwo(),"cape");
                            this.elytraModel.renderToBuffer(poseStack, vertexConsumer2, finalLightTwo, OverlayTexture.NO_OVERLAY, finalColorTwo);
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
