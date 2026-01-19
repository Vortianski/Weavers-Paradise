package xox.labvorty.weaversparadise.data;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.client.renderer.blockentity.TheEndPortalRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import xox.labvorty.weaversparadise.curios.WeaversParadiseMobLayers;
import xox.labvorty.weaversparadise.init.WeaversParadiseItems;
import xox.labvorty.weaversparadise.items.ShirtCotton;
import xox.labvorty.weaversparadise.items.ShirtSilk;
import xox.labvorty.weaversparadise.items.SweaterWool;
import xox.labvorty.weaversparadise.model.ModelUpperWear;
import xox.labvorty.weaversparadise.renderers.WeaversParadiseGlobalRendererDataHolder;
import xox.labvorty.weaversparadise.renderers.WeaversParadiseRenderTypes;

public class ShirtRenderer extends BlockEntityWithoutLevelRenderer {
    private final ModelUpperWear model;

    public ShirtRenderer(BlockEntityRenderDispatcher dispatcher, EntityModelSet entityModels) {
        super(dispatcher, entityModels);
        this.model = new ModelUpperWear(entityModels.bakeLayer(WeaversParadiseMobLayers.UPPER_WEAR));
    }

    @Override
    public void renderByItem(
            ItemStack stack,
            ItemDisplayContext transformType,
            PoseStack poseStack,
            MultiBufferSource buffer,
            int packedLight,
            int packedOverlay
    ) {
        if (!(stack.getItem() instanceof ShirtCotton i1)) {
            if (!(stack.getItem() instanceof ShirtSilk i2)) {
                if (!(stack.getItem() instanceof SweaterWool i3)) {
                    return;
                }
            }
        }

        float scale = 1.0f;

        double ytranslation = -0.85;

        float additionalXrot = 0;
        float additionalYrot = 0;
        float additionalZrot = 0;

        switch (transformType) {
            case GUI -> {
                scale = 0.9f;
                ytranslation = -0.8;
                additionalYrot = 45f;
                additionalXrot = 22.5f;
            }
            case THIRD_PERSON_LEFT_HAND, THIRD_PERSON_RIGHT_HAND, FIRST_PERSON_LEFT_HAND, FIRST_PERSON_RIGHT_HAND -> {
                scale = 0.5f;
                ytranslation = -0.9;
            }
            case FIXED -> {
                additionalYrot = 180f;
            }
            case GROUND -> {
                ytranslation = -0.75;
                scale = 0.5f;
            }
        }

        Minecraft mc = Minecraft.getInstance();
        Player entity = mc.player;

        if (stack.getItem() instanceof ShirtCotton shirtCotton) {
            int ticks = (int)mc.level.getGameTime();
            int primaryColorOne = shirtCotton.getItemMainColor(stack, 1);
            int secondaryColorOne = shirtCotton.getItemSecondaryColor(stack, 1);
            int primaryColorTwo = shirtCotton.getItemMainColor(stack, 2);
            int secondaryColorTwo = shirtCotton.getItemSecondaryColor(stack, 2);
            String dyeTypeOne = shirtCotton.getItemDyeType(stack, 1);
            String dyeTypeTwo = shirtCotton.getItemDyeType(stack, 2);
            String stensilType = shirtCotton.getStensilType(stack);
            int lightValueOne = shirtCotton.getItemLightValue(stack, 1);
            int lightValueTwo = shirtCotton.getItemLightValue(stack, 2);

            String renderType = WeaversParadiseShirtTextureHandler.getByTypeAndMaterial(stensilType, "cotton").getRenderType();
            ResourceLocation tex1 = WeaversParadiseShirtTextureHandler.getByTypeAndMaterial(stensilType, "cotton").getTextureOne();
            ResourceLocation tex2 = WeaversParadiseShirtTextureHandler.getByTypeAndMaterial(stensilType, "cotton").getTextureTwo();

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

            int lightOne = packedLight;
            int lightTwo = packedLight;

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

            poseStack.pushPose();

            poseStack.scale(scale, -scale, scale);

            poseStack.mulPose(Axis.YP.rotationDegrees(180));

            poseStack.translate((-0.5) / scale, (ytranslation) / scale, (-0.5) / scale);

            poseStack.mulPose(Axis.XP.rotationDegrees(additionalXrot));

            poseStack.mulPose(Axis.YP.rotationDegrees(additionalYrot));

            poseStack.mulPose(Axis.ZP.rotationDegrees(additionalZrot));

            VertexConsumer vertexConsumerOne = buffer.getBuffer(RenderType.entityTranslucent(tex1));
            if (dyeTypeOne.equals("ender")) {
                vertexConsumerOne = buffer.getBuffer(
                        WeaversParadiseRenderTypes.getVoidArmor(
                                TheEndPortalRenderer.END_SKY_LOCATION,
                                tex1,
                                TheEndPortalRenderer.END_PORTAL_LOCATION
                        )
                );
            }
            model.renderToBuffer(poseStack, vertexConsumerOne, lightOne, OverlayTexture.NO_OVERLAY, colorOne);

            if (renderType.equals("double")) {
                VertexConsumer vertexConsumerTwo = buffer.getBuffer(RenderType.entityTranslucent(tex2));
                if (dyeTypeTwo.equals("ender")) {
                    vertexConsumerTwo = buffer.getBuffer(
                            WeaversParadiseRenderTypes.getVoidArmor(
                                    TheEndPortalRenderer.END_SKY_LOCATION,
                                    tex2,
                                    TheEndPortalRenderer.END_PORTAL_LOCATION
                            )
                    );
                }
                model.renderToBuffer(poseStack, vertexConsumerTwo, lightTwo, OverlayTexture.NO_OVERLAY, colorTwo);
            }

            poseStack.popPose();
        } else if (stack.getItem() instanceof ShirtSilk shirtSilk) {
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

            int lightOne = packedLight;
            int lightTwo = packedLight;

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

            poseStack.pushPose();

            poseStack.scale(scale, -scale, scale);

            poseStack.mulPose(Axis.YP.rotationDegrees(180));

            poseStack.translate((-0.5) / scale, (ytranslation) / scale, (-0.5) / scale);

            poseStack.mulPose(Axis.XP.rotationDegrees(additionalXrot));

            poseStack.mulPose(Axis.YP.rotationDegrees(additionalYrot));

            poseStack.mulPose(Axis.ZP.rotationDegrees(additionalZrot));

            VertexConsumer vertexConsumerOne = buffer.getBuffer(RenderType.entityTranslucent(tex1));
            if (dyeTypeOne.equals("ender")) {
                vertexConsumerOne = buffer.getBuffer(
                        WeaversParadiseRenderTypes.getVoidArmor(
                                TheEndPortalRenderer.END_SKY_LOCATION,
                                tex1,
                                TheEndPortalRenderer.END_PORTAL_LOCATION
                        )
                );
            }
            model.renderToBuffer(poseStack, vertexConsumerOne, lightOne, OverlayTexture.NO_OVERLAY, colorOne);

            if (renderType.equals("double")) {
                VertexConsumer vertexConsumerTwo = buffer.getBuffer(RenderType.entityTranslucent(tex2));
                if (dyeTypeTwo.equals("ender")) {
                    vertexConsumerTwo = buffer.getBuffer(
                            WeaversParadiseRenderTypes.getVoidArmor(
                                    TheEndPortalRenderer.END_SKY_LOCATION,
                                    tex2,
                                    TheEndPortalRenderer.END_PORTAL_LOCATION
                            )
                    );
                }
                model.renderToBuffer(poseStack, vertexConsumerTwo, lightTwo, OverlayTexture.NO_OVERLAY, colorTwo);
            }

            poseStack.popPose();
        } else if (stack.getItem() instanceof SweaterWool sweaterWool) {
            int ticks = (int)mc.level.getGameTime();
            int primaryColorOne = sweaterWool.getItemMainColor(stack, 1);
            int secondaryColorOne = sweaterWool.getItemSecondaryColor(stack, 1);
            int primaryColorTwo = sweaterWool.getItemMainColor(stack, 2);
            int secondaryColorTwo = sweaterWool.getItemSecondaryColor(stack, 2);
            String dyeTypeOne = sweaterWool.getItemDyeType(stack, 1);
            String dyeTypeTwo = sweaterWool.getItemDyeType(stack, 2);
            String stensilType = sweaterWool.getStensilType(stack);
            int lightValueOne = sweaterWool.getItemLightValue(stack, 1);
            int lightValueTwo = sweaterWool.getItemLightValue(stack, 2);

            String renderType = WeaversParadiseShirtTextureHandler.getByTypeAndMaterial(stensilType, "wool").getRenderType();
            ResourceLocation tex1 = WeaversParadiseShirtTextureHandler.getByTypeAndMaterial(stensilType, "wool").getTextureOne();
            ResourceLocation tex2 = WeaversParadiseShirtTextureHandler.getByTypeAndMaterial(stensilType, "wool").getTextureTwo();

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

            int lightOne = packedLight;
            int lightTwo = packedLight;

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

            poseStack.pushPose();

            poseStack.scale(scale, -scale, scale);

            poseStack.mulPose(Axis.YP.rotationDegrees(180));

            poseStack.translate((-0.5) / scale, (ytranslation) / scale, (-0.5) / scale);

            poseStack.mulPose(Axis.XP.rotationDegrees(additionalXrot));

            poseStack.mulPose(Axis.YP.rotationDegrees(additionalYrot));

            poseStack.mulPose(Axis.ZP.rotationDegrees(additionalZrot));

            VertexConsumer vertexConsumerOne = buffer.getBuffer(RenderType.entityTranslucent(tex1));
            if (dyeTypeOne.equals("ender")) {
                vertexConsumerOne = buffer.getBuffer(
                        WeaversParadiseRenderTypes.getVoidArmor(
                                TheEndPortalRenderer.END_SKY_LOCATION,
                                tex1,
                                TheEndPortalRenderer.END_PORTAL_LOCATION
                        )
                );
            }
            model.renderToBuffer(poseStack, vertexConsumerOne, lightOne, OverlayTexture.NO_OVERLAY, colorOne);

            if (renderType.equals("double")) {
                VertexConsumer vertexConsumerTwo = buffer.getBuffer(RenderType.entityTranslucent(tex2));
                if (dyeTypeTwo.equals("ender")) {
                    vertexConsumerTwo = buffer.getBuffer(
                            WeaversParadiseRenderTypes.getVoidArmor(
                                    TheEndPortalRenderer.END_SKY_LOCATION,
                                    tex2,
                                    TheEndPortalRenderer.END_PORTAL_LOCATION
                            )
                    );
                }
                model.renderToBuffer(poseStack, vertexConsumerTwo, lightTwo, OverlayTexture.NO_OVERLAY, colorTwo);
            }

            poseStack.popPose();
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
