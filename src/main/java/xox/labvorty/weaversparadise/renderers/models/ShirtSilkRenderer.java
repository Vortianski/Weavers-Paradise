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
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
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

public class ShirtSilkRenderer implements ICurioRenderer {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath("weaversparadise", "textures/clothing/shirt_silk.png");

    public static ModelUpperWear model;

    public ShirtSilkRenderer() {
        this.model = new ModelUpperWear(Minecraft.getInstance().getEntityModels().bakeLayer(WeaversParadiseMobLayers.UPPER_WEAR));
    }

    @Override
    public <T extends LivingEntity, M extends EntityModel<T>> void render(ItemStack stack, SlotContext slotContext, PoseStack matrixStack, RenderLayerParent<T, M> renderLayerParent, MultiBufferSource renderTypeBuffer, int light, float limbSwing,
                                                                          float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        LivingEntity entity = slotContext.entity();

        if (stack.getItem() instanceof ShirtSilk shirtSilk && !entity.getItemBySlot(EquipmentSlot.BODY).is(WeaversParadiseItems.ASTOLFO_ARMOR_CHESTPLATE)) {
            Minecraft mc = Minecraft.getInstance();
            int ticks = (int)mc.level.getGameTime();
            int primaryColorOne = shirtSilk.getItemMainColor(stack, 1);
            int secondaryColorOne = shirtSilk.getItemSecondaryColor(stack, 1);
            int primaryColorTwo = shirtSilk.getItemMainColor(stack, 2);
            int secondaryColorTwo = shirtSilk.getItemSecondaryColor(stack, 2);
            String dyeTypeOne = shirtSilk.getItemDyeType(stack, 1);
            String dyeTypeTwo = shirtSilk.getItemDyeType(stack, 2);
            String stensilType = shirtSilk.getStensilType(stack);
            int lightValueOne = shirtSilk.getItemLightValue(stack, 1);
            int lightValueTwo = shirtSilk.getItemLightValue(stack, 2);

            String renderType = WeaversParadiseShirtTextureHandler.getByTypeAndMaterial(stensilType, "silk").getRenderType();
            ResourceLocation tex1 = WeaversParadiseShirtTextureHandler.getByTypeAndMaterial(stensilType, "silk").getTextureOne();
            ResourceLocation tex2 = WeaversParadiseShirtTextureHandler.getByTypeAndMaterial(stensilType, "silk").getTextureTwo();

            this.model.prepareMobModel(entity, limbSwing, limbSwingAmount, partialTicks);
            this.model.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

            M playerModel = renderLayerParent.getModel();
            if (playerModel instanceof HumanoidModel<?>) {
                HumanoidModel<?> humModel = (HumanoidModel<?>)playerModel;

                this.model.Body.copyFrom(humModel.body);
                this.model.LeftArm.copyFrom(humModel.leftArm);
                this.model.RightArm.copyFrom(humModel.rightArm);
            }

            int redPrimaryOne = (primaryColorOne >> 16) & 0xFF;
            int greenPrimaryOne = (primaryColorOne >> 8) & 0xFF;
            int bluePrimaryOne = primaryColorOne & 0xFF;

            int redSecondaryOne = (secondaryColorOne >> 16) & 0xFF;
            int greenSecondaryOne = (secondaryColorOne >> 8) & 0xFF;
            int blueSecondaryOne = secondaryColorOne & 0xFF;

            int redPrimaryTwo = (primaryColorTwo >> 16) & 0xFF;
            int greenPrimaryTwo = (primaryColorTwo >> 8) & 0xFF;
            int bluePrimaryTwo = primaryColorTwo & 0xFF;

            int redSecondaryTwo = (secondaryColorTwo >> 16) & 0xFF;
            int greenSecondaryTwo = (secondaryColorTwo >> 8) & 0xFF;
            int blueSecondaryTwo = secondaryColorTwo & 0xFF;

            int finalRedOne = redPrimaryOne;
            int finalGreenOne = greenPrimaryOne;
            int finalBlueOne = bluePrimaryOne;

            int finalRedTwo = redPrimaryTwo;
            int finalGreenTwo = greenPrimaryTwo;
            int finalBlueTwo = bluePrimaryTwo;

            int colorOne = 255 << 24 | finalRedOne << 16 | finalGreenOne << 8 | finalBlueOne;
            int colorTwo = 255 << 24 | finalRedTwo << 16 | finalGreenTwo << 8 | finalBlueTwo;

            int lightOne = light;
            int lightTwo = light;

            if (dyeTypeOne.equals("rainbow")) {
                colorOne = getRainbowColor(ticks);
            }

            if (dyeTypeTwo.equals("rainbow")) {
                colorTwo = getRainbowColor(ticks);
            }

            if (dyeTypeOne.equals("ender")) {
                lightOne = 0;
            }

            if (dyeTypeTwo.equals("ender")) {
                lightTwo = 0;
            }

            if (dyeTypeOne.equals("glowstone")) {
                lightOne = LightTexture.FULL_BRIGHT;
            }

            if (dyeTypeTwo.equals("glowstone")) {
                lightTwo = LightTexture.FULL_BRIGHT;
            }

            if (dyeTypeOne.equals("biome")) {
                BlockPos bpos = new BlockPos((int)entity.getX(), (int)entity.getY(), (int)entity.getZ());
                int color = mc.level.getBiome(bpos).value().getGrassColor(entity.getX(), entity.getZ());
                color = 255 << 24 | ((color >> 16) & 0xFF) << 16 | ((color >> 8) & 0xFF) << 8 | (color & 0xFF);

                colorOne = color;
            }

            if (dyeTypeTwo.equals("biome")) {
                BlockPos bpos = new BlockPos((int)entity.getX(), (int)entity.getY(), (int)entity.getZ());
                int color = mc.level.getBiome(bpos).value().getGrassColor(entity.getX(), entity.getZ());
                color = 255 << 24 | ((color >> 16) & 0xFF) << 16 | ((color >> 8) & 0xFF) << 8 | (color & 0xFF);

                colorTwo = color;
            }

            if (dyeTypeOne.equals("speed")) {
                Vec3 vec3 = entity.getDeltaMovement();
                double velocity = vec3.lengthSqr();

                int colorOnes = primaryColorOne;
                int colorTwos = secondaryColorOne;

                int redOne = (colorOnes >> 16) & 0xFF;
                int greenOne = (colorOnes >> 8) & 0xFF;
                int blueOne = colorOnes & 0xFF;

                int redTwo = (colorTwos >> 16) & 0xFF;
                int greenTwo = (colorTwos >> 8) & 0xFF;
                int blueTwo = colorTwos & 0xFF;

                int finalRed = Mth.lerpInt((float)Mth.clamp(velocity / 0.029081503190380643, 0, 1), redOne, redTwo);
                int finalGreen = Mth.lerpInt((float)Mth.clamp(velocity / 0.029081503190380643, 0, 1), greenOne, greenTwo);
                int finalBlue = Mth.lerpInt((float)Mth.clamp(velocity / 0.029081503190380643, 0, 1), blueOne, blueTwo);

                int finalColor = 255 << 24 | finalRed << 16 | finalGreen << 8 | finalBlue;
                colorOne = finalColor;
            }

            if (dyeTypeTwo.equals("speed")) {
                Vec3 vec3 = entity.getDeltaMovement();
                double velocity = vec3.lengthSqr();

                int colorOnes = primaryColorTwo;
                int colorTwos = secondaryColorTwo;

                int redOne = (colorOnes >> 16) & 0xFF;
                int greenOne = (colorOnes >> 8) & 0xFF;
                int blueOne = colorOnes & 0xFF;

                int redTwo = (colorTwos >> 16) & 0xFF;
                int greenTwo = (colorTwos >> 8) & 0xFF;
                int blueTwo = colorTwos & 0xFF;

                int finalRed = Mth.lerpInt((float)Mth.clamp(velocity / 0.029081503190380643, 0, 1), redOne, redTwo);
                int finalGreen = Mth.lerpInt((float)Mth.clamp(velocity / 0.029081503190380643, 0, 1), greenOne, greenTwo);
                int finalBlue = Mth.lerpInt((float)Mth.clamp(velocity / 0.029081503190380643, 0, 1), blueOne, blueTwo);

                int finalColor = 255 << 24 | finalRed << 16 | finalGreen << 8 | finalBlue;
                colorTwo = finalColor;
            }

            if (dyeTypeOne.equals("height_bedrock")) {
                int absoluteMinimum = mc.level.getMinBuildHeight();
                int absoluteMaximum = mc.level.getMaxBuildHeight();
                int height = (int)entity.getY();

                float value = Mth.clamp(((float)height - (float)absoluteMinimum) / ((float)absoluteMaximum - (float)absoluteMinimum), 0, 1);

                int colorOnes = primaryColorOne;
                int colorTwos = secondaryColorOne;

                int redOne = (colorOnes >> 16) & 0xFF;
                int greenOne = (colorOnes >> 8) & 0xFF;
                int blueOne = colorOnes & 0xFF;

                int redTwo = (colorTwos >> 16) & 0xFF;
                int greenTwo = (colorTwos >> 8) & 0xFF;
                int blueTwo = colorTwos & 0xFF;

                int finalRed = Mth.lerpInt(value, redOne, redTwo);
                int finalGreen = Mth.lerpInt(value, greenOne, greenTwo);
                int finalBlue = Mth.lerpInt(value, blueOne, blueTwo);

                int finalColor = 255 << 24 | finalRed << 16 | finalGreen << 8 | finalBlue;
                colorOne = finalColor;
            }

            if (dyeTypeTwo.equals("height_bedrock")) {
                int absoluteMinimum = mc.level.getMinBuildHeight();
                int absoluteMaximum = mc.level.getMaxBuildHeight();
                int height = (int)entity.getY();

                float value = Mth.clamp(((float)height - (float)absoluteMinimum) / ((float)absoluteMaximum - (float)absoluteMinimum), 0, 1);

                int colorOnes = primaryColorTwo;
                int colorTwos = secondaryColorTwo;

                int redOne = (colorOnes >> 16) & 0xFF;
                int greenOne = (colorOnes >> 8) & 0xFF;
                int blueOne = colorOnes & 0xFF;

                int redTwo = (colorTwos >> 16) & 0xFF;
                int greenTwo = (colorTwos >> 8) & 0xFF;
                int blueTwo = colorTwos & 0xFF;

                int finalRed = Mth.lerpInt(value, redOne, redTwo);
                int finalGreen = Mth.lerpInt(value, greenOne, greenTwo);
                int finalBlue = Mth.lerpInt(value, blueOne, blueTwo);

                int finalColor = 255 << 24 | finalRed << 16 | finalGreen << 8 | finalBlue;
                colorTwo = finalColor;
            }

            if (dyeTypeOne.equals("height_sea")) {
                double minY = mc.level.getMinBuildHeight();
                double maxY = mc.level.getMaxBuildHeight();
                double seaY = mc.level.getSeaLevel();
                double playerY = entity.getY();

                double distanceAbove = maxY - seaY;
                double distanceBelow = seaY - minY;
                double maxDistance = Math.max(distanceAbove, distanceBelow);
                float value = (float)Mth.clamp(Math.abs(playerY - seaY) / maxDistance, 0, 1);

                int colorOnes = primaryColorOne;
                int colorTwos = secondaryColorOne;

                int redOne = (colorOnes >> 16) & 0xFF;
                int greenOne = (colorOnes >> 8) & 0xFF;
                int blueOne = colorOnes & 0xFF;

                int redTwo = (colorTwos >> 16) & 0xFF;
                int greenTwo = (colorTwos >> 8) & 0xFF;
                int blueTwo = colorTwos & 0xFF;

                int finalRed = Mth.lerpInt(value, redOne, redTwo);
                int finalGreen = Mth.lerpInt(value, greenOne, greenTwo);
                int finalBlue = Mth.lerpInt(value, blueOne, blueTwo);

                int finalColor = 255 << 24 | finalRed << 16 | finalGreen << 8 | finalBlue;
                colorOne = finalColor;
            }

            if (dyeTypeTwo.equals("height_sea")) {
                double minY = mc.level.getMinBuildHeight();
                double maxY = mc.level.getMaxBuildHeight();
                double seaY = mc.level.getSeaLevel();
                double playerY = entity.getY();

                double distanceAbove = maxY - seaY;
                double distanceBelow = seaY - minY;
                double maxDistance = Math.max(distanceAbove, distanceBelow);
                float value = (float)Mth.clamp(Math.abs(playerY - seaY) / maxDistance, 0, 1);

                int colorOnes = primaryColorTwo;
                int colorTwos = secondaryColorTwo;

                int redOne = (colorOnes >> 16) & 0xFF;
                int greenOne = (colorOnes >> 8) & 0xFF;
                int blueOne = colorOnes & 0xFF;

                int redTwo = (colorTwos >> 16) & 0xFF;
                int greenTwo = (colorTwos >> 8) & 0xFF;
                int blueTwo = colorTwos & 0xFF;

                int finalRed = Mth.lerpInt(value, redOne, redTwo);
                int finalGreen = Mth.lerpInt(value, greenOne, greenTwo);
                int finalBlue = Mth.lerpInt(value, blueOne, blueTwo);

                int finalColor = 255 << 24 | finalRed << 16 | finalGreen << 8 | finalBlue;
                colorTwo = finalColor;
            }

            if (dyeTypeOne.equals("sculk")) {
                int redMain = 5;
                int greenMain = 38;
                int blueMain = 43;

                int redPulse = 0;
                int greenPulse = 255;
                int bluePulse = 255;

                int sculkPulse = WeaversParadiseGlobalRendererDataHolder.getSculkPulse();
                int finalRed = Mth.lerpInt(sculkPulse / 60.0f, redMain, redPulse);
                int finalGreen = Mth.lerpInt(sculkPulse / 60.0f, greenMain, greenPulse);
                int finalBlue = Mth.lerpInt(sculkPulse / 60.0f, blueMain, bluePulse);

                colorOne = 255 << 24 | finalRed << 16 | finalGreen << 8 | finalBlue;
            }

            if (dyeTypeTwo.equals("sculk")) {
                int redMain = 5;
                int greenMain = 38;
                int blueMain = 43;

                int redPulse = 0;
                int greenPulse = 255;
                int bluePulse = 255;

                int sculkPulse = WeaversParadiseGlobalRendererDataHolder.getSculkPulse();
                int finalRed = Mth.lerpInt(sculkPulse / 60.0f, redMain, redPulse);
                int finalGreen = Mth.lerpInt(sculkPulse / 60.0f, greenMain, greenPulse);
                int finalBlue = Mth.lerpInt(sculkPulse / 60.0f, blueMain, bluePulse);

                colorTwo = 255 << 24 | finalRed << 16 | finalGreen << 8 | finalBlue;
            }

            if (dyeTypeOne.equals("lamp")) {
                lightOne = lightValueOne << 20 | lightValueOne << 4;
            }

            if (dyeTypeTwo.equals("lamp")) {
                lightTwo = lightValueTwo << 20 | lightValueTwo << 4;
            }

            VertexConsumer vertexConsumerOne = renderTypeBuffer.getBuffer(RenderType.entityTranslucent(tex1));
            if (dyeTypeOne.equals("ender")) {
                vertexConsumerOne = renderTypeBuffer.getBuffer(
                        WeaversParadiseRenderTypes.getVoidArmor(
                                TheEndPortalRenderer.END_SKY_LOCATION,
                                tex1,
                                TheEndPortalRenderer.END_PORTAL_LOCATION
                        )
                );
            }
            model.renderToBuffer(matrixStack, vertexConsumerOne, lightOne, OverlayTexture.NO_OVERLAY, colorOne);

            if (renderType.equals("double")) {
                VertexConsumer vertexConsumerTwo = renderTypeBuffer.getBuffer(RenderType.entityTranslucent(tex2));
                if (dyeTypeTwo.equals("ender")) {
                    vertexConsumerTwo = renderTypeBuffer.getBuffer(
                            WeaversParadiseRenderTypes.getVoidArmor(
                                    TheEndPortalRenderer.END_SKY_LOCATION,
                                    tex2,
                                    TheEndPortalRenderer.END_PORTAL_LOCATION
                            )
                    );
                }
                model.renderToBuffer(matrixStack, vertexConsumerTwo, lightTwo, OverlayTexture.NO_OVERLAY, colorTwo);
            }
        }
    }

    public int getRainbowColor(int ticks) {
        float speed = 0.05F;

        float red = Mth.clamp((float)(Math.sin(ticks * speed) * 0.5 + 0.5), 0, 1);
        float green = Mth.clamp((float)(Math.sin(ticks * speed + 2 * Math.PI / 3) * 0.5 + 0.5), 0, 1);
        float blue = Mth.clamp((float)(Math.sin(ticks * speed + 4 * Math.PI / 3) * 0.5 + 0.5), 0, 1);

        int truered = (int)(red * 255);
        int truegreen = (int)(green * 255);
        int trueblue = (int)(blue * 255);

        return 255 << 24 | truered << 16 | truegreen << 8 | trueblue;
    }
}
