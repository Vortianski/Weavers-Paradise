package xox.labvorty.weaversparadise.mixins.client;

import com.mojang.blaze3d.platform.InputConstants;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.MatrixUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelShaper;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HalfTransparentBlock;
import net.minecraft.world.level.block.StainedGlassPaneBlock;
import net.neoforged.neoforge.client.ClientHooks;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xox.labvorty.weaversparadise.init.WeaversParadiseItems;
import xox.labvorty.weaversparadise.items.dye.BottledDyeItem;
import xox.labvorty.weaversparadise.items.dye.DyeCoreItem;

@Mixin(ItemRenderer.class)
public abstract class ItemRendererMixin {
    @Shadow
    public static VertexConsumer getCompassFoilBuffer(MultiBufferSource bufferSource, RenderType renderType, PoseStack.Pose pose) {
        return null;
    }

    @Shadow
    public static VertexConsumer getFoilBufferDirect(MultiBufferSource bufferSource, RenderType renderType, boolean noEntity, boolean withGlint) {
        return null;
    }

    @Shadow
    public static VertexConsumer getFoilBuffer(MultiBufferSource bufferSource, RenderType renderType, boolean isItem, boolean glint) {
        return null;
    }

    @Shadow
    public abstract void renderModelLists(BakedModel model, ItemStack stack, int combinedLight, int combinedOverlay, PoseStack poseStack, VertexConsumer buffer);

    @Shadow
    private static boolean hasAnimatedTexture(ItemStack stack) {
        return false;
    }

    @Shadow
    @Final
    private ItemModelShaper itemModelShaper;

    @Shadow
    @Final
    private static ModelResourceLocation TRIDENT_MODEL;

    @Shadow
    @Final
    private static ModelResourceLocation SPYGLASS_MODEL;

    @Inject(
            method = "render",
            at = @At("HEAD"),
            cancellable = true
    )
    private void weaversparadise$replaceRender(ItemStack itemStack, ItemDisplayContext displayContext, boolean leftHand, PoseStack poseStack, MultiBufferSource bufferSource, int combinedLight, int combinedOverlay, BakedModel p_model, CallbackInfo ci) {
        if (!itemStack.isEmpty() && displayContext == ItemDisplayContext.GUI) {
            if (itemStack.getItem() instanceof BottledDyeItem bottledDyeItem || itemStack.getItem() instanceof DyeCoreItem dyeCore) {
                if (InputConstants.isKeyDown(Minecraft.getInstance().getWindow().getWindow(), 340)) {
                    String type = itemStack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getString("dyeType");

                    p_model = this.itemModelShaper.getItemModel(getItemForType(type));

                    poseStack.pushPose();
                    boolean flag = displayContext == ItemDisplayContext.GUI || displayContext == ItemDisplayContext.GROUND || displayContext == ItemDisplayContext.FIXED;
                    if (flag) {
                        if (itemStack.is(Items.TRIDENT)) {
                            p_model = this.itemModelShaper.getModelManager().getModel(TRIDENT_MODEL);
                        } else if (itemStack.is(Items.SPYGLASS)) {
                            p_model = this.itemModelShaper.getModelManager().getModel(SPYGLASS_MODEL);
                        }
                    }

                    p_model = ClientHooks.handleCameraTransforms(poseStack, p_model, displayContext, leftHand);
                    poseStack.translate(-0.5F, -0.5F, -0.5F);
                    if (!p_model.isCustomRenderer() && (!itemStack.is(Items.TRIDENT) || flag)) {
                        boolean flag1;
                        label79: {
                            if (displayContext != ItemDisplayContext.GUI && !displayContext.firstPerson()) {
                                Item var12 = itemStack.getItem();
                                if (var12 instanceof BlockItem) {
                                    BlockItem blockitem = (BlockItem)var12;
                                    Block block = blockitem.getBlock();
                                    flag1 = !(block instanceof HalfTransparentBlock) && !(block instanceof StainedGlassPaneBlock);
                                    break label79;
                                }
                            }

                            flag1 = true;
                        }

                        for(BakedModel model : p_model.getRenderPasses(itemStack, flag1)) {
                            for(RenderType rendertype : model.getRenderTypes(itemStack, flag1)) {
                                VertexConsumer vertexconsumer;
                                if (hasAnimatedTexture(itemStack) && itemStack.hasFoil()) {
                                    PoseStack.Pose posestack$pose = poseStack.last().copy();
                                    if (displayContext == ItemDisplayContext.GUI) {
                                        MatrixUtil.mulComponentWise(posestack$pose.pose(), 0.5F);
                                    } else if (displayContext.firstPerson()) {
                                        MatrixUtil.mulComponentWise(posestack$pose.pose(), 0.75F);
                                    }

                                    vertexconsumer = getCompassFoilBuffer(bufferSource, rendertype, posestack$pose);
                                } else if (flag1) {
                                    vertexconsumer = getFoilBufferDirect(bufferSource, rendertype, true, itemStack.hasFoil());
                                } else {
                                    vertexconsumer = getFoilBuffer(bufferSource, rendertype, true, itemStack.hasFoil());
                                }

                                this.renderModelLists(model, itemStack, combinedLight, combinedOverlay, poseStack, vertexconsumer);
                            }
                        }
                    } else {
                        IClientItemExtensions.of(itemStack).getCustomRenderer().renderByItem(itemStack, displayContext, poseStack, bufferSource, combinedLight, combinedOverlay);
                    }

                    poseStack.popPose();
                    ci.cancel();
                }
            }
        }
    }

    @Unique
    private Item getItemForType(String type) {
        if (type.equals("default")) {
            return WeaversParadiseItems.FLAG_BASIC.asItem();
        }

        if (type.equals("agender")) {
            return WeaversParadiseItems.FLAG_AGENDER.asItem();
        }

        if (type.equals("aroace")) {
            return WeaversParadiseItems.FLAG_AROACE.asItem();
        }

        if (type.equals("aromantic")) {
            return WeaversParadiseItems.FLAG_AROMANTIC.asItem();
        }

        if (type.equals("asexual")) {
            return WeaversParadiseItems.FLAG_ASEXUAL.asItem();
        }

        if (type.equals("bisexual")) {
            return WeaversParadiseItems.FLAG_BISEXUAL.asItem();
        }

        if (type.equals("demiboy")) {
            return WeaversParadiseItems.FLAG_DEMIBOY.asItem();
        }

        if (type.equals("demigender")) {
            return WeaversParadiseItems.FLAG_DEMIGENDER.asItem();
        }

        if (type.equals("demigirl")) {
            return WeaversParadiseItems.FLAG_DEMIGIRL.asItem();
        }

        if (type.equals("gay")) {
            return WeaversParadiseItems.FLAG_GAY.asItem();
        }

        if (type.equals("genderfluid")) {
            return WeaversParadiseItems.FLAG_GENDERFLUID.asItem();
        }

        if (type.equals("genderqueer")) {
            return WeaversParadiseItems.FLAG_GENDERQUEER.asItem();
        }

        if (type.equals("intersex")) {
            return WeaversParadiseItems.FLAG_INTERSEX.asItem();
        }

        if (type.equals("lesbian")) {
            return WeaversParadiseItems.FLAG_LESBIAN.asItem();
        }

        if (type.equals("nonbinary")) {
            return WeaversParadiseItems.FLAG_NONBINARY.asItem();
        }

        if (type.equals("pansexual")) {
            return WeaversParadiseItems.FLAG_PANSEXUAL.asItem();
        }

        if (type.equals("pride")) {
            return WeaversParadiseItems.FLAG_PRIDE.asItem();
        }

        if (type.equals("trans")) {
            return WeaversParadiseItems.FLAG_TRANS.asItem();
        }

        if (type.equals("sculk")) {
            return Items.SCULK.asItem();
        }

        if (type.equals("colored_sculk")) {
            return Items.ECHO_SHARD.asItem();
        }

        if (type.equals("hunger")) {
            return Items.ROTTEN_FLESH.asItem();
        }

        if (type.equals("health")) {
            return Items.GLISTERING_MELON_SLICE.asItem();
        }

        if (type.equals("day_time")) {
            return Items.SUNFLOWER.asItem();
        }

        if (type.equals("colored_day_time")) {
            return Items.CLOCK.asItem();
        }

        if (type.equals("glowstone")) {
            return Items.GLOWSTONE.asItem();
        }

        if (type.equals("rainbow")) {
            return WeaversParadiseItems.CHROMATIC_BLOOM_FRUIT.asItem();
        }

        if (type.equals("biome")) {
            return Items.MOSS_BLOCK.asItem();
        }

        if (type.equals("ender")) {
            return Items.ENDER_EYE.asItem();
        }

        if (type.equals("speed")) {
            return Items.SUGAR.asItem();
        }

        if (type.equals("height_bedrock")) {
            return Items.PHANTOM_MEMBRANE.asItem();
        }

        if (type.equals("height_sea")) {
            return Items.PRISMARINE_CRYSTALS.asItem();
        }

        if (type.equals("invisible")) {
            return Items.GLASS.asItem();
        }

        if (type.equals("static")) {
            return Items.POWDER_SNOW_BUCKET.asItem();
        }

        if (type.equals("crystal")) {
            return Items.AMETHYST_SHARD.asItem();
        }

        if (type.equals("nebula")) {
            return Items.DRAGON_BREATH.asItem();
        }

        if (type.equals("redstone")) {
            return Items.REDSTONE.asItem();
        }

        if (type.equals("lamp")) {
            return Items.REDSTONE_LAMP.asItem();
        }

        if (type.equals("polychromatic")) {
            return WeaversParadiseItems.CHROMATIC_DUST.asItem();
        }

        return Items.BARRIER;
    }
}
