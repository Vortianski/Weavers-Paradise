package xox.labvorty.weaversparadise.data;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
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
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import xox.labvorty.weaversparadise.curios.WeaversParadiseMobLayers;
import xox.labvorty.weaversparadise.items.*;
import xox.labvorty.weaversparadise.model.ThighHighsModel;
import xox.labvorty.weaversparadise.renderers.WeaversParadiseGlobalRendererDataHolder;
import xox.labvorty.weaversparadise.renderers.WeaversParadiseRenderTypes;

public class HandWarmersRenderer extends BlockEntityWithoutLevelRenderer {
    private final ThighHighsModel model;
    private final ThighHighsModel model1;

    public HandWarmersRenderer(BlockEntityRenderDispatcher dispatcher, EntityModelSet entityModels) {
        super(dispatcher, entityModels);
        this.model = new ThighHighsModel(entityModels.bakeLayer(WeaversParadiseMobLayers.THIGH_HIGHS));
        this.model1 = new ThighHighsModel(entityModels.bakeLayer(WeaversParadiseMobLayers.THIGH_HIGHS));
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
            if (!(stack.getItem() instanceof HandWarmersCotton item1)) {
                if (!(stack.getItem() instanceof HandWarmersSilk item2)) {
                    if (!(stack.getItem() instanceof HandWarmersWool item3)) {
                        return;
                    }
                }
            }

            float scale = 1.0f;

            double ytranslation = -1.75;

            float additionalXrot = 0;
            float additionalYrot = 0;
            float additionalZrot = 0;

            switch (transformType) {
                case GUI -> {
                    scale = 1.0f;
                    ytranslation = -1.65;
                    additionalYrot = 45f;
                    additionalXrot = 22.5f;
                }
                case THIRD_PERSON_LEFT_HAND, THIRD_PERSON_RIGHT_HAND, FIRST_PERSON_LEFT_HAND, FIRST_PERSON_RIGHT_HAND -> {
                    scale = 0.5f;
                    ytranslation = -1.25;
                }
                case FIXED -> {
                    additionalYrot = 180f;
                }
                case GROUND -> {
                    scale = 0.5f;
                    ytranslation = -1.15;
                }
            }

            Minecraft minecraft = Minecraft.getInstance();
            Player entity = minecraft.player;

            if (stack.getItem() instanceof HandWarmersCotton cottonThighHighs) {
                Minecraft mc = Minecraft.getInstance();
                int ticks = (int) mc.level.getGameTime();
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

                WeaversParadiseHandWarmersTextureHandler leftHandler = WeaversParadiseHandWarmersTextureHandler.getByTypeAndMaterial(stensilTypeLeft, "cotton");
                String renderTypeLeft = leftHandler.getRenderType();
                ResourceLocation tex1left = leftHandler.getTextureOne();
                ResourceLocation tex2left = leftHandler.getTextureTwo();

                WeaversParadiseHandWarmersTextureHandler rightHandler = WeaversParadiseHandWarmersTextureHandler.getByTypeAndMaterial(stensilTypeRight, "cotton");
                String renderTypeRight = rightHandler.getRenderType();
                ResourceLocation tex1right = rightHandler.getTextureOne();
                ResourceLocation tex2right = rightHandler.getTextureTwo();

                int redLeftPrimaryOne = (primaryColorLeftOne >> 16) & 0xFF;
                int greenLeftPrimaryOne = (primaryColorLeftOne >> 8) & 0xFF;
                int blueLeftPrimaryOne = primaryColorLeftOne & 0xFF;

                int redRightPrimaryOne = (primaryColorRightOne >> 16) & 0xFF;
                int greenRightPrimaryOne = (primaryColorRightOne >> 8) & 0xFF;
                int blueRightPrimaryOne = primaryColorRightOne & 0xFF;

                int redLeftSecondaryOne = (secondaryColorLeftOne >> 16) & 0xFF;
                int greenLeftSecondaryOne = (secondaryColorLeftOne >> 8) & 0xFF;
                int blueLeftSecondaryOne = secondaryColorLeftOne & 0xFF;

                int redRightSecondaryOne = (secondaryColorRightOne >> 16) & 0xFF;
                int greenRightSecondaryOne = (secondaryColorRightOne >> 8) & 0xFF;
                int blueRightSecondaryOne = secondaryColorRightOne & 0xFF;

                int redLeftPrimaryTwo = (primaryColorLeftTwo >> 16) & 0xFF;
                int greenLeftPrimaryTwo = (primaryColorLeftTwo >> 8) & 0xFF;
                int blueLeftPrimaryTwo = primaryColorLeftTwo & 0xFF;

                int redRightPrimaryTwo = (primaryColorRightTwo >> 16) & 0xFF;
                int greenRightPrimaryTwo = (primaryColorRightTwo >> 8) & 0xFF;
                int blueRightPrimaryTwo = primaryColorRightTwo & 0xFF;

                int redLeftSecondaryTwo = (secondaryColorLeftTwo >> 16) & 0xFF;
                int greenLeftSecondaryTwo = (secondaryColorLeftTwo >> 8) & 0xFF;
                int blueLeftSecondaryTwo = secondaryColorLeftTwo & 0xFF;

                int redRightSecondaryTwo = (secondaryColorRightTwo >> 16) & 0xFF;
                int greenRightSecondaryTwo = (secondaryColorRightTwo >> 8) & 0xFF;
                int blueRightSecondaryTwo = secondaryColorRightTwo & 0xFF;

                int finalRedLeftOne = redLeftPrimaryOne;
                int finalGreenLeftOne = greenLeftPrimaryOne;
                int finalBlueLeftOne = blueLeftPrimaryOne;

                int finalRedRightOne = redRightPrimaryOne;
                int finalGreenRightOne = greenRightPrimaryOne;
                int finalBlueRightOne = blueRightPrimaryOne;

                int finalRedLeftTwo = redLeftPrimaryTwo;
                int finalGreenLeftTwo = greenLeftPrimaryTwo;
                int finalBlueLeftTwo = blueLeftPrimaryTwo;

                int finalRedRightTwo = redRightPrimaryTwo;
                int finalGreenRightTwo = greenRightPrimaryTwo;
                int finalBlueRightTwo = blueRightPrimaryTwo;

                int colorLeftOne = 255 << 24 | finalRedLeftOne << 16 | finalGreenLeftOne << 8 | finalBlueLeftOne;
                int colorRightOne = 255 << 24 | finalRedRightOne << 16 | finalGreenRightOne << 8 | finalBlueRightOne;

                int colorLeftTwo = 255 << 24 | finalRedLeftTwo << 16 | finalGreenLeftTwo << 8 | finalBlueLeftTwo;
                int colorRightTwo = 255 << 24 | finalRedRightTwo << 16 | finalGreenRightTwo << 8 | finalBlueRightTwo;

                int lightOne = packedLight;
                int lightTwo = packedLight;
                int lightThree = packedLight;
                int lightFour = packedLight;

                if (dyeTypeLeftOne.equals("rainbow")) {
                    colorLeftOne = getRainbowColor(ticks);
                }

                if (dyeTypeLeftTwo.equals("rainbow")) {
                    colorLeftTwo = getRainbowColor(ticks);
                }

                if (dyeTypeRightOne.equals("rainbow")) {
                    colorRightOne = getRainbowColor(ticks);
                }

                if (dyeTypeRightTwo.equals("rainbow")) {
                    colorRightTwo = getRainbowColor(ticks);
                }

                if (dyeTypeLeftOne.equals("ender")) {
                    lightOne = 0;
                }

                if (dyeTypeLeftTwo.equals("ender")) {
                    lightTwo = 0;
                }

                if (dyeTypeRightOne.equals("ender")) {
                    lightThree = 0;
                }

                if (dyeTypeRightTwo.equals("ender")) {
                    lightFour = 0;
                }

                if (dyeTypeLeftOne.equals("glowstone")) {
                    lightOne = LightTexture.FULL_BRIGHT;
                }

                if (dyeTypeLeftTwo.equals("glowstone")) {
                    lightTwo = LightTexture.FULL_BRIGHT;
                }

                if (dyeTypeRightOne.equals("glowstone")) {
                    lightThree = LightTexture.FULL_BRIGHT;
                }

                if (dyeTypeRightTwo.equals("glowstone")) {
                    lightFour = LightTexture.FULL_BRIGHT;
                }

                if (dyeTypeLeftOne.equals("biome")) {
                    BlockPos bpos = new BlockPos((int)entity.getX(), (int)entity.getY(), (int)entity.getZ());
                    int color = mc.level.getBiome(bpos).value().getGrassColor(entity.getX(), entity.getZ());
                    color = 255 << 24 | ((color >> 16) & 0xFF) << 16 | ((color >> 8) & 0xFF) << 8 | (color & 0xFF);

                    colorLeftOne = color;
                }

                if (dyeTypeLeftTwo.equals("biome")) {
                    BlockPos bpos = new BlockPos((int)entity.getX(), (int)entity.getY(), (int)entity.getZ());
                    int color = mc.level.getBiome(bpos).value().getGrassColor(entity.getX(), entity.getZ());
                    color = 255 << 24 | ((color >> 16) & 0xFF) << 16 | ((color >> 8) & 0xFF) << 8 | (color & 0xFF);

                    colorLeftTwo = color;
                }

                if (dyeTypeRightOne.equals("biome")) {
                    BlockPos bpos = new BlockPos((int)entity.getX(), (int)entity.getY(), (int)entity.getZ());
                    int color = mc.level.getBiome(bpos).value().getGrassColor(entity.getX(), entity.getZ());
                    color = 255 << 24 | ((color >> 16) & 0xFF) << 16 | ((color >> 8) & 0xFF) << 8 | (color & 0xFF);

                    colorRightOne = color;
                }

                if (dyeTypeRightTwo.equals("biome")) {
                    BlockPos bpos = new BlockPos((int)entity.getX(), (int)entity.getY(), (int)entity.getZ());
                    int color = mc.level.getBiome(bpos).value().getGrassColor(entity.getX(), entity.getZ());
                    color = 255 << 24 | ((color >> 16) & 0xFF) << 16 | ((color >> 8) & 0xFF) << 8 | (color & 0xFF);

                    colorRightTwo = color;
                }

                if (dyeTypeLeftOne.equals("speed")) {
                    Vec3 vec3 = entity.getDeltaMovement();
                    double velocity = vec3.lengthSqr();

                    int colorOne = primaryColorLeftOne;
                    int colorTwo = secondaryColorLeftOne;

                    int redOne = (colorOne >> 16) & 0xFF;
                    int greenOne = (colorOne >> 8) & 0xFF;
                    int blueOne = colorOne & 0xFF;

                    int redTwo = (colorTwo >> 16) & 0xFF;
                    int greenTwo = (colorTwo >> 8) & 0xFF;
                    int blueTwo = colorTwo & 0xFF;

                    int finalRed = Mth.lerpInt((float)Mth.clamp(velocity / 0.029081503190380643, 0, 1), redOne, redTwo);
                    int finalGreen = Mth.lerpInt((float)Mth.clamp(velocity / 0.029081503190380643, 0, 1), greenOne, greenTwo);
                    int finalBlue = Mth.lerpInt((float)Mth.clamp(velocity / 0.029081503190380643, 0, 1), blueOne, blueTwo);

                    int finalColor = 255 << 24 | finalRed << 16 | finalGreen << 8 | finalBlue;
                    colorLeftOne = finalColor;
                }

                if (dyeTypeLeftTwo.equals("speed")) {
                    Vec3 vec3 = entity.getDeltaMovement();
                    double velocity = vec3.lengthSqr();

                    int colorOne = primaryColorLeftTwo;
                    int colorTwo = secondaryColorLeftTwo;

                    int redOne = (colorOne >> 16) & 0xFF;
                    int greenOne = (colorOne >> 8) & 0xFF;
                    int blueOne = colorOne & 0xFF;

                    int redTwo = (colorTwo >> 16) & 0xFF;
                    int greenTwo = (colorTwo >> 8) & 0xFF;
                    int blueTwo = colorTwo & 0xFF;

                    int finalRed = Mth.lerpInt((float)Mth.clamp(velocity / 0.029081503190380643, 0, 1), redOne, redTwo);
                    int finalGreen = Mth.lerpInt((float)Mth.clamp(velocity / 0.029081503190380643, 0, 1), greenOne, greenTwo);
                    int finalBlue = Mth.lerpInt((float)Mth.clamp(velocity / 0.029081503190380643, 0, 1), blueOne, blueTwo);

                    int finalColor = 255 << 24 | finalRed << 16 | finalGreen << 8 | finalBlue;
                    colorLeftTwo = finalColor;
                }

                if (dyeTypeRightOne.equals("speed")) {
                    Vec3 vec3 = entity.getDeltaMovement();
                    double velocity = vec3.lengthSqr();

                    int colorOne = primaryColorRightOne;
                    int colorTwo = secondaryColorRightOne;

                    int redOne = (colorOne >> 16) & 0xFF;
                    int greenOne = (colorOne >> 8) & 0xFF;
                    int blueOne = colorOne & 0xFF;

                    int redTwo = (colorTwo >> 16) & 0xFF;
                    int greenTwo = (colorTwo >> 8) & 0xFF;
                    int blueTwo = colorTwo & 0xFF;

                    int finalRed = Mth.lerpInt((float)Mth.clamp(velocity / 0.029081503190380643, 0, 1), redOne, redTwo);
                    int finalGreen = Mth.lerpInt((float)Mth.clamp(velocity / 0.029081503190380643, 0, 1), greenOne, greenTwo);
                    int finalBlue = Mth.lerpInt((float)Mth.clamp(velocity / 0.029081503190380643, 0, 1), blueOne, blueTwo);

                    int finalColor = 255 << 24 | finalRed << 16 | finalGreen << 8 | finalBlue;
                    colorRightOne = finalColor;
                }

                if (dyeTypeRightTwo.equals("speed")) {
                    Vec3 vec3 = entity.getDeltaMovement();
                    double velocity = vec3.lengthSqr();

                    int colorOne = primaryColorRightTwo;
                    int colorTwo = secondaryColorRightTwo;

                    int redOne = (colorOne >> 16) & 0xFF;
                    int greenOne = (colorOne >> 8) & 0xFF;
                    int blueOne = colorOne & 0xFF;

                    int redTwo = (colorTwo >> 16) & 0xFF;
                    int greenTwo = (colorTwo >> 8) & 0xFF;
                    int blueTwo = colorTwo & 0xFF;

                    int finalRed = Mth.lerpInt((float)Mth.clamp(velocity / 0.029081503190380643, 0, 1), redOne, redTwo);
                    int finalGreen = Mth.lerpInt((float)Mth.clamp(velocity / 0.029081503190380643, 0, 1), greenOne, greenTwo);
                    int finalBlue = Mth.lerpInt((float)Mth.clamp(velocity / 0.029081503190380643, 0, 1), blueOne, blueTwo);

                    int finalColor = 255 << 24 | finalRed << 16 | finalGreen << 8 | finalBlue;
                    colorRightTwo = finalColor;
                }

                if (dyeTypeLeftOne.equals("height_bedrock")) {
                    int absoluteMinimum = mc.level.getMinBuildHeight();
                    int absoluteMaximum = mc.level.getMaxBuildHeight();
                    int height = (int)entity.getY();

                    float value = Mth.clamp(((float)height - (float)absoluteMinimum) / ((float)absoluteMaximum - (float)absoluteMinimum), 0, 1);

                    int colorOne = primaryColorLeftOne;
                    int colorTwo = secondaryColorLeftOne;

                    int redOne = (colorOne >> 16) & 0xFF;
                    int greenOne = (colorOne >> 8) & 0xFF;
                    int blueOne = colorOne & 0xFF;

                    int redTwo = (colorTwo >> 16) & 0xFF;
                    int greenTwo = (colorTwo >> 8) & 0xFF;
                    int blueTwo = colorTwo & 0xFF;

                    int finalRed = Mth.lerpInt(value, redOne, redTwo);
                    int finalGreen = Mth.lerpInt(value, greenOne, greenTwo);
                    int finalBlue = Mth.lerpInt(value, blueOne, blueTwo);

                    int finalColor = 255 << 24 | finalRed << 16 | finalGreen << 8 | finalBlue;
                    colorLeftOne = finalColor;
                }

                if (dyeTypeLeftTwo.equals("height_bedrock")) {
                    int absoluteMinimum = mc.level.getMinBuildHeight();
                    int absoluteMaximum = mc.level.getMaxBuildHeight();
                    int height = (int)entity.getY();

                    float value = Mth.clamp(((float)height - (float)absoluteMinimum) / ((float)absoluteMaximum - (float)absoluteMinimum), 0, 1);

                    int colorOne = primaryColorLeftTwo;
                    int colorTwo = secondaryColorLeftTwo;

                    int redOne = (colorOne >> 16) & 0xFF;
                    int greenOne = (colorOne >> 8) & 0xFF;
                    int blueOne = colorOne & 0xFF;

                    int redTwo = (colorTwo >> 16) & 0xFF;
                    int greenTwo = (colorTwo >> 8) & 0xFF;
                    int blueTwo = colorTwo & 0xFF;

                    int finalRed = Mth.lerpInt(value, redOne, redTwo);
                    int finalGreen = Mth.lerpInt(value, greenOne, greenTwo);
                    int finalBlue = Mth.lerpInt(value, blueOne, blueTwo);

                    int finalColor = 255 << 24 | finalRed << 16 | finalGreen << 8 | finalBlue;
                    colorLeftTwo = finalColor;
                }

                if (dyeTypeRightOne.equals("height_bedrock")) {
                    int absoluteMinimum = mc.level.getMinBuildHeight();
                    int absoluteMaximum = mc.level.getMaxBuildHeight();
                    int height = (int)entity.getY();

                    float value = Mth.clamp(((float)height - (float)absoluteMinimum) / ((float)absoluteMaximum - (float)absoluteMinimum), 0, 1);

                    int colorOne = primaryColorRightOne;
                    int colorTwo = secondaryColorRightOne;

                    int redOne = (colorOne >> 16) & 0xFF;
                    int greenOne = (colorOne >> 8) & 0xFF;
                    int blueOne = colorOne & 0xFF;

                    int redTwo = (colorTwo >> 16) & 0xFF;
                    int greenTwo = (colorTwo >> 8) & 0xFF;
                    int blueTwo = colorTwo & 0xFF;

                    int finalRed = Mth.lerpInt(value, redOne, redTwo);
                    int finalGreen = Mth.lerpInt(value, greenOne, greenTwo);
                    int finalBlue = Mth.lerpInt(value, blueOne, blueTwo);

                    int finalColor = 255 << 24 | finalRed << 16 | finalGreen << 8 | finalBlue;
                    colorRightOne = finalColor;
                }

                if (dyeTypeRightTwo.equals("height_bedrock")) {
                    int absoluteMinimum = mc.level.getMinBuildHeight();
                    int absoluteMaximum = mc.level.getMaxBuildHeight();
                    int height = (int)entity.getY();

                    float value = Mth.clamp(((float)height - (float)absoluteMinimum) / ((float)absoluteMaximum - (float)absoluteMinimum), 0, 1);

                    int colorOne = primaryColorRightTwo;
                    int colorTwo = secondaryColorRightTwo;

                    int redOne = (colorOne >> 16) & 0xFF;
                    int greenOne = (colorOne >> 8) & 0xFF;
                    int blueOne = colorOne & 0xFF;

                    int redTwo = (colorTwo >> 16) & 0xFF;
                    int greenTwo = (colorTwo >> 8) & 0xFF;
                    int blueTwo = colorTwo & 0xFF;

                    int finalRed = Mth.lerpInt(value, redOne, redTwo);
                    int finalGreen = Mth.lerpInt(value, greenOne, greenTwo);
                    int finalBlue = Mth.lerpInt(value, blueOne, blueTwo);

                    int finalColor = 255 << 24 | finalRed << 16 | finalGreen << 8 | finalBlue;
                    colorRightTwo = finalColor;
                }

                if (dyeTypeLeftOne.equals("height_sea")) {
                    double minY = mc.level.getMinBuildHeight();
                    double maxY = mc.level.getMaxBuildHeight();
                    double seaY = mc.level.getSeaLevel();
                    double playerY = entity.getY();

                    double distanceAbove = maxY - seaY;
                    double distanceBelow = seaY - minY;
                    double maxDistance = Math.max(distanceAbove, distanceBelow);
                    float value = (float)Mth.clamp(Math.abs(playerY - seaY) / maxDistance, 0, 1);

                    int colorOne = primaryColorLeftOne;
                    int colorTwo = secondaryColorLeftOne;

                    int redOne = (colorOne >> 16) & 0xFF;
                    int greenOne = (colorOne >> 8) & 0xFF;
                    int blueOne = colorOne & 0xFF;

                    int redTwo = (colorTwo >> 16) & 0xFF;
                    int greenTwo = (colorTwo >> 8) & 0xFF;
                    int blueTwo = colorTwo & 0xFF;

                    int finalRed = Mth.lerpInt(value, redOne, redTwo);
                    int finalGreen = Mth.lerpInt(value, greenOne, greenTwo);
                    int finalBlue = Mth.lerpInt(value, blueOne, blueTwo);

                    int finalColor = 255 << 24 | finalRed << 16 | finalGreen << 8 | finalBlue;
                    colorLeftOne = finalColor;
                }

                if (dyeTypeLeftTwo.equals("height_sea")) {
                    double minY = mc.level.getMinBuildHeight();
                    double maxY = mc.level.getMaxBuildHeight();
                    double seaY = mc.level.getSeaLevel();
                    double playerY = entity.getY();

                    double distanceAbove = maxY - seaY;
                    double distanceBelow = seaY - minY;
                    double maxDistance = Math.max(distanceAbove, distanceBelow);
                    float value = (float)Mth.clamp(Math.abs(playerY - seaY) / maxDistance, 0, 1);

                    int colorOne = primaryColorLeftTwo;
                    int colorTwo = secondaryColorLeftTwo;

                    int redOne = (colorOne >> 16) & 0xFF;
                    int greenOne = (colorOne >> 8) & 0xFF;
                    int blueOne = colorOne & 0xFF;

                    int redTwo = (colorTwo >> 16) & 0xFF;
                    int greenTwo = (colorTwo >> 8) & 0xFF;
                    int blueTwo = colorTwo & 0xFF;

                    int finalRed = Mth.lerpInt(value, redOne, redTwo);
                    int finalGreen = Mth.lerpInt(value, greenOne, greenTwo);
                    int finalBlue = Mth.lerpInt(value, blueOne, blueTwo);

                    int finalColor = 255 << 24 | finalRed << 16 | finalGreen << 8 | finalBlue;
                    colorLeftTwo = finalColor;
                }

                if (dyeTypeRightOne.equals("height_sea")) {
                    double minY = mc.level.getMinBuildHeight();
                    double maxY = mc.level.getMaxBuildHeight();
                    double seaY = mc.level.getSeaLevel();
                    double playerY = entity.getY();

                    double distanceAbove = maxY - seaY;
                    double distanceBelow = seaY - minY;
                    double maxDistance = Math.max(distanceAbove, distanceBelow);
                    float value = (float)Mth.clamp(Math.abs(playerY - seaY) / maxDistance, 0, 1);

                    int colorOne = primaryColorRightOne;
                    int colorTwo = secondaryColorRightOne;

                    int redOne = (colorOne >> 16) & 0xFF;
                    int greenOne = (colorOne >> 8) & 0xFF;
                    int blueOne = colorOne & 0xFF;

                    int redTwo = (colorTwo >> 16) & 0xFF;
                    int greenTwo = (colorTwo >> 8) & 0xFF;
                    int blueTwo = colorTwo & 0xFF;

                    int finalRed = Mth.lerpInt(value, redOne, redTwo);
                    int finalGreen = Mth.lerpInt(value, greenOne, greenTwo);
                    int finalBlue = Mth.lerpInt(value, blueOne, blueTwo);

                    int finalColor = 255 << 24 | finalRed << 16 | finalGreen << 8 | finalBlue;
                    colorRightOne = finalColor;
                }

                if (dyeTypeRightTwo.equals("height_sea")) {
                    double minY = mc.level.getMinBuildHeight();
                    double maxY = mc.level.getMaxBuildHeight();
                    double seaY = mc.level.getSeaLevel();
                    double playerY = entity.getY();

                    double distanceAbove = maxY - seaY;
                    double distanceBelow = seaY - minY;
                    double maxDistance = Math.max(distanceAbove, distanceBelow);
                    float value = (float)Mth.clamp(Math.abs(playerY - seaY) / maxDistance, 0, 1);

                    int colorOne = primaryColorRightTwo;
                    int colorTwo = secondaryColorRightTwo;

                    int redOne = (colorOne >> 16) & 0xFF;
                    int greenOne = (colorOne >> 8) & 0xFF;
                    int blueOne = colorOne & 0xFF;

                    int redTwo = (colorTwo >> 16) & 0xFF;
                    int greenTwo = (colorTwo >> 8) & 0xFF;
                    int blueTwo = colorTwo & 0xFF;

                    int finalRed = Mth.lerpInt(value, redOne, redTwo);
                    int finalGreen = Mth.lerpInt(value, greenOne, greenTwo);
                    int finalBlue = Mth.lerpInt(value, blueOne, blueTwo);

                    int finalColor = 255 << 24 | finalRed << 16 | finalGreen << 8 | finalBlue;
                    colorRightTwo = finalColor;
                }

                if (dyeTypeLeftOne.equals("sculk")) {
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

                    colorLeftOne = 255 << 24 | finalRed << 16 | finalGreen << 8 | finalBlue;
                }

                if (dyeTypeLeftTwo.equals("sculk")) {
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

                    colorLeftTwo = 255 << 24 | finalRed << 16 | finalGreen << 8 | finalBlue;
                }

                if (dyeTypeRightOne.equals("sculk")) {
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

                    colorRightOne = 255 << 24 | finalRed << 16 | finalGreen << 8 | finalBlue;
                }

                if (dyeTypeRightTwo.equals("sculk")) {
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

                    colorRightTwo = 255 << 24 | finalRed << 16 | finalGreen << 8 | finalBlue;
                }

                if (dyeTypeLeftOne.equals("lamp")) {
                    lightOne = lightValueLeftOne << 20 | lightValueLeftOne << 4;
                }

                if (dyeTypeLeftTwo.equals("lamp")) {
                    lightTwo = lightValueLeftTwo << 20 | lightValueLeftTwo << 4;
                }

                if (dyeTypeRightOne.equals("lamp")) {
                    lightThree = lightValueRightOne << 20 | lightValueRightOne << 4;
                }

                if (dyeTypeRightTwo.equals("lamp")) {
                    lightFour = lightValueRightTwo << 20 | lightValueRightTwo << 4;
                }

                poseStack.pushPose();

                poseStack.scale(scale, -scale, scale);

                poseStack.mulPose(Axis.YP.rotationDegrees(180));

                poseStack.translate((-0.5) / scale, (ytranslation) / scale, (-0.5) / scale);

                poseStack.mulPose(Axis.XP.rotationDegrees(additionalXrot));

                poseStack.mulPose(Axis.YP.rotationDegrees(additionalYrot));

                poseStack.mulPose(Axis.ZP.rotationDegrees(additionalZrot));

                VertexConsumer consumerOneLeft = buffer.getBuffer(RenderType.entityTranslucent(tex1left)/**RenderType.armorCutoutNoCull(tex1left)**/);
                if (dyeTypeLeftOne.equals("ender")) {
                    consumerOneLeft = buffer.getBuffer(
                            WeaversParadiseRenderTypes.getVoidArmor(
                                    TheEndPortalRenderer.END_SKY_LOCATION,
                                    tex1left,
                                    TheEndPortalRenderer.END_PORTAL_LOCATION
                            )
                    );
                }
                this.model.LeftLeg.render(poseStack, consumerOneLeft, lightOne, OverlayTexture.NO_OVERLAY, colorLeftOne);

                VertexConsumer consumerOneRight = buffer.getBuffer(RenderType.entityTranslucent(tex1right)/**RenderType.armorCutoutNoCull(tex1right)**/);
                if (dyeTypeRightOne.equals("ender")) {
                    consumerOneRight = buffer.getBuffer(
                            WeaversParadiseRenderTypes.getVoidArmor(
                                    TheEndPortalRenderer.END_SKY_LOCATION,
                                    tex1right,
                                    TheEndPortalRenderer.END_PORTAL_LOCATION
                            )
                    );
                }
                this.model.RightLeg.render(poseStack, consumerOneRight, lightThree, OverlayTexture.NO_OVERLAY, colorRightOne);

                if (renderTypeLeft.equals("double")) {
                    VertexConsumer consumerTwoLeft = buffer.getBuffer(RenderType.entityTranslucent(tex2left)/**RenderType.armorCutoutNoCull(tex2left)**/);
                    if (dyeTypeLeftTwo.equals("ender")) {
                        consumerTwoLeft = buffer.getBuffer(
                                WeaversParadiseRenderTypes.getVoidArmor(
                                        TheEndPortalRenderer.END_SKY_LOCATION,
                                        tex2left,
                                        TheEndPortalRenderer.END_PORTAL_LOCATION
                                )
                        );
                    }
                    this.model1.LeftLeg.render(poseStack, consumerTwoLeft, lightTwo, OverlayTexture.NO_OVERLAY, colorLeftTwo);
                }

                if (renderTypeRight.equals("double")) {
                    VertexConsumer consumerTwoRight = buffer.getBuffer(RenderType.entityTranslucent(tex2right)/**RenderType.armorCutoutNoCull(tex2right)**/);
                    if (dyeTypeRightTwo.equals("ender")) {
                        consumerTwoRight = buffer.getBuffer(
                                WeaversParadiseRenderTypes.getVoidArmor(
                                        TheEndPortalRenderer.END_SKY_LOCATION,
                                        tex2right,
                                        TheEndPortalRenderer.END_PORTAL_LOCATION
                                )
                        );
                    }
                    this.model1.RightLeg.render(poseStack, consumerTwoRight, lightFour, OverlayTexture.NO_OVERLAY, colorRightTwo);
                }

                poseStack.popPose();
            } else if (stack.getItem() instanceof HandWarmersSilk silkThighHighs) {
                Minecraft mc = Minecraft.getInstance();
                int ticks = (int) mc.level.getGameTime();
                int primaryColorLeftOne = silkThighHighs.getItemMainColor(stack, "left", 1);
                int secondaryColorLeftOne = silkThighHighs.getItemSecondaryColor(stack, "left", 1);
                int primaryColorRightOne = silkThighHighs.getItemMainColor(stack, "right", 1);
                int secondaryColorRightOne = silkThighHighs.getItemSecondaryColor(stack, "right", 1);
                int primaryColorLeftTwo = silkThighHighs.getItemMainColor(stack, "left", 2);
                int secondaryColorLeftTwo = silkThighHighs.getItemSecondaryColor(stack, "left", 2);
                int primaryColorRightTwo = silkThighHighs.getItemMainColor(stack, "right", 2);
                int secondaryColorRightTwo = silkThighHighs.getItemSecondaryColor(stack, "right", 2);
                String dyeTypeLeftOne = silkThighHighs.getItemDyeType(stack, "left", 1);
                String dyeTypeRightOne = silkThighHighs.getItemDyeType(stack, "right", 1);
                String dyeTypeLeftTwo = silkThighHighs.getItemDyeType(stack, "left", 2);
                String dyeTypeRightTwo = silkThighHighs.getItemDyeType(stack, "right", 2);
                String stensilTypeLeft = silkThighHighs.getStensilType(stack, "left");
                String stensilTypeRight = silkThighHighs.getStensilType(stack, "right");
                int lightValueLeftOne = silkThighHighs.getItemLightValue(stack, "left", 1);
                int lightValueLeftTwo = silkThighHighs.getItemLightValue(stack, "left", 2);
                int lightValueRightOne = silkThighHighs.getItemLightValue(stack, "right", 1);
                int lightValueRightTwo = silkThighHighs.getItemLightValue(stack, "right", 2);

                WeaversParadiseHandWarmersTextureHandler leftHandler = WeaversParadiseHandWarmersTextureHandler.getByTypeAndMaterial(stensilTypeLeft, "silk");
                String renderTypeLeft = leftHandler.getRenderType();
                ResourceLocation tex1left = leftHandler.getTextureOne();
                ResourceLocation tex2left = leftHandler.getTextureTwo();

                WeaversParadiseHandWarmersTextureHandler rightHandler = WeaversParadiseHandWarmersTextureHandler.getByTypeAndMaterial(stensilTypeRight, "silk");
                String renderTypeRight = rightHandler.getRenderType();
                ResourceLocation tex1right = rightHandler.getTextureOne();
                ResourceLocation tex2right = rightHandler.getTextureTwo();

                int redLeftPrimaryOne = (primaryColorLeftOne >> 16) & 0xFF;
                int greenLeftPrimaryOne = (primaryColorLeftOne >> 8) & 0xFF;
                int blueLeftPrimaryOne = primaryColorLeftOne & 0xFF;

                int redRightPrimaryOne = (primaryColorRightOne >> 16) & 0xFF;
                int greenRightPrimaryOne = (primaryColorRightOne >> 8) & 0xFF;
                int blueRightPrimaryOne = primaryColorRightOne & 0xFF;

                int redLeftSecondaryOne = (secondaryColorLeftOne >> 16) & 0xFF;
                int greenLeftSecondaryOne = (secondaryColorLeftOne >> 8) & 0xFF;
                int blueLeftSecondaryOne = secondaryColorLeftOne & 0xFF;

                int redRightSecondaryOne = (secondaryColorRightOne >> 16) & 0xFF;
                int greenRightSecondaryOne = (secondaryColorRightOne >> 8) & 0xFF;
                int blueRightSecondaryOne = secondaryColorRightOne & 0xFF;

                int redLeftPrimaryTwo = (primaryColorLeftTwo >> 16) & 0xFF;
                int greenLeftPrimaryTwo = (primaryColorLeftTwo >> 8) & 0xFF;
                int blueLeftPrimaryTwo = primaryColorLeftTwo & 0xFF;

                int redRightPrimaryTwo = (primaryColorRightTwo >> 16) & 0xFF;
                int greenRightPrimaryTwo = (primaryColorRightTwo >> 8) & 0xFF;
                int blueRightPrimaryTwo = primaryColorRightTwo & 0xFF;

                int redLeftSecondaryTwo = (secondaryColorLeftTwo >> 16) & 0xFF;
                int greenLeftSecondaryTwo = (secondaryColorLeftTwo >> 8) & 0xFF;
                int blueLeftSecondaryTwo = secondaryColorLeftTwo & 0xFF;

                int redRightSecondaryTwo = (secondaryColorRightTwo >> 16) & 0xFF;
                int greenRightSecondaryTwo = (secondaryColorRightTwo >> 8) & 0xFF;
                int blueRightSecondaryTwo = secondaryColorRightTwo & 0xFF;

                int finalRedLeftOne = redLeftPrimaryOne;
                int finalGreenLeftOne = greenLeftPrimaryOne;
                int finalBlueLeftOne = blueLeftPrimaryOne;

                int finalRedRightOne = redRightPrimaryOne;
                int finalGreenRightOne = greenRightPrimaryOne;
                int finalBlueRightOne = blueRightPrimaryOne;

                int finalRedLeftTwo = redLeftPrimaryTwo;
                int finalGreenLeftTwo = greenLeftPrimaryTwo;
                int finalBlueLeftTwo = blueLeftPrimaryTwo;

                int finalRedRightTwo = redRightPrimaryTwo;
                int finalGreenRightTwo = greenRightPrimaryTwo;
                int finalBlueRightTwo = blueRightPrimaryTwo;

                int colorLeftOne = 255 << 24 | finalRedLeftOne << 16 | finalGreenLeftOne << 8 | finalBlueLeftOne;
                int colorRightOne = 255 << 24 | finalRedRightOne << 16 | finalGreenRightOne << 8 | finalBlueRightOne;

                int colorLeftTwo = 255 << 24 | finalRedLeftTwo << 16 | finalGreenLeftTwo << 8 | finalBlueLeftTwo;
                int colorRightTwo = 255 << 24 | finalRedRightTwo << 16 | finalGreenRightTwo << 8 | finalBlueRightTwo;

                int lightOne = packedLight;
                int lightTwo = packedLight;
                int lightThree = packedLight;
                int lightFour = packedLight;

                if (dyeTypeLeftOne.equals("rainbow")) {
                    colorLeftOne = getRainbowColor(ticks);
                }

                if (dyeTypeLeftTwo.equals("rainbow")) {
                    colorLeftTwo = getRainbowColor(ticks);
                }

                if (dyeTypeRightOne.equals("rainbow")) {
                    colorRightOne = getRainbowColor(ticks);
                }

                if (dyeTypeRightTwo.equals("rainbow")) {
                    colorRightTwo = getRainbowColor(ticks);
                }

                if (dyeTypeLeftOne.equals("ender")) {
                    lightOne = 0;
                }

                if (dyeTypeLeftTwo.equals("ender")) {
                    lightTwo = 0;
                }

                if (dyeTypeRightOne.equals("ender")) {
                    lightThree = 0;
                }

                if (dyeTypeRightTwo.equals("ender")) {
                    lightFour = 0;
                }

                if (dyeTypeLeftOne.equals("glowstone")) {
                    lightOne = LightTexture.FULL_BRIGHT;
                }

                if (dyeTypeLeftTwo.equals("glowstone")) {
                    lightTwo = LightTexture.FULL_BRIGHT;
                }

                if (dyeTypeRightOne.equals("glowstone")) {
                    lightThree = LightTexture.FULL_BRIGHT;
                }

                if (dyeTypeRightTwo.equals("glowstone")) {
                    lightFour = LightTexture.FULL_BRIGHT;
                }

                if (dyeTypeLeftOne.equals("biome")) {
                    BlockPos bpos = new BlockPos((int)entity.getX(), (int)entity.getY(), (int)entity.getZ());
                    int color = mc.level.getBiome(bpos).value().getGrassColor(entity.getX(), entity.getZ());
                    color = 255 << 24 | ((color >> 16) & 0xFF) << 16 | ((color >> 8) & 0xFF) << 8 | (color & 0xFF);

                    colorLeftOne = color;
                }

                if (dyeTypeLeftTwo.equals("biome")) {
                    BlockPos bpos = new BlockPos((int)entity.getX(), (int)entity.getY(), (int)entity.getZ());
                    int color = mc.level.getBiome(bpos).value().getGrassColor(entity.getX(), entity.getZ());
                    color = 255 << 24 | ((color >> 16) & 0xFF) << 16 | ((color >> 8) & 0xFF) << 8 | (color & 0xFF);

                    colorLeftTwo = color;
                }

                if (dyeTypeRightOne.equals("biome")) {
                    BlockPos bpos = new BlockPos((int)entity.getX(), (int)entity.getY(), (int)entity.getZ());
                    int color = mc.level.getBiome(bpos).value().getGrassColor(entity.getX(), entity.getZ());
                    color = 255 << 24 | ((color >> 16) & 0xFF) << 16 | ((color >> 8) & 0xFF) << 8 | (color & 0xFF);

                    colorRightOne = color;
                }

                if (dyeTypeRightTwo.equals("biome")) {
                    BlockPos bpos = new BlockPos((int)entity.getX(), (int)entity.getY(), (int)entity.getZ());
                    int color = mc.level.getBiome(bpos).value().getGrassColor(entity.getX(), entity.getZ());
                    color = 255 << 24 | ((color >> 16) & 0xFF) << 16 | ((color >> 8) & 0xFF) << 8 | (color & 0xFF);

                    colorRightTwo = color;
                }

                if (dyeTypeLeftOne.equals("speed")) {
                    Vec3 vec3 = entity.getDeltaMovement();
                    double velocity = vec3.lengthSqr();

                    int colorOne = primaryColorLeftOne;
                    int colorTwo = secondaryColorLeftOne;

                    int redOne = (colorOne >> 16) & 0xFF;
                    int greenOne = (colorOne >> 8) & 0xFF;
                    int blueOne = colorOne & 0xFF;

                    int redTwo = (colorTwo >> 16) & 0xFF;
                    int greenTwo = (colorTwo >> 8) & 0xFF;
                    int blueTwo = colorTwo & 0xFF;

                    int finalRed = Mth.lerpInt((float)Mth.clamp(velocity / 0.029081503190380643, 0, 1), redOne, redTwo);
                    int finalGreen = Mth.lerpInt((float)Mth.clamp(velocity / 0.029081503190380643, 0, 1), greenOne, greenTwo);
                    int finalBlue = Mth.lerpInt((float)Mth.clamp(velocity / 0.029081503190380643, 0, 1), blueOne, blueTwo);

                    int finalColor = 255 << 24 | finalRed << 16 | finalGreen << 8 | finalBlue;
                    colorLeftOne = finalColor;
                }

                if (dyeTypeLeftTwo.equals("speed")) {
                    Vec3 vec3 = entity.getDeltaMovement();
                    double velocity = vec3.lengthSqr();

                    int colorOne = primaryColorLeftTwo;
                    int colorTwo = secondaryColorLeftTwo;

                    int redOne = (colorOne >> 16) & 0xFF;
                    int greenOne = (colorOne >> 8) & 0xFF;
                    int blueOne = colorOne & 0xFF;

                    int redTwo = (colorTwo >> 16) & 0xFF;
                    int greenTwo = (colorTwo >> 8) & 0xFF;
                    int blueTwo = colorTwo & 0xFF;

                    int finalRed = Mth.lerpInt((float)Mth.clamp(velocity / 0.029081503190380643, 0, 1), redOne, redTwo);
                    int finalGreen = Mth.lerpInt((float)Mth.clamp(velocity / 0.029081503190380643, 0, 1), greenOne, greenTwo);
                    int finalBlue = Mth.lerpInt((float)Mth.clamp(velocity / 0.029081503190380643, 0, 1), blueOne, blueTwo);

                    int finalColor = 255 << 24 | finalRed << 16 | finalGreen << 8 | finalBlue;
                    colorLeftTwo = finalColor;
                }

                if (dyeTypeRightOne.equals("speed")) {
                    Vec3 vec3 = entity.getDeltaMovement();
                    double velocity = vec3.lengthSqr();

                    int colorOne = primaryColorRightOne;
                    int colorTwo = secondaryColorRightOne;

                    int redOne = (colorOne >> 16) & 0xFF;
                    int greenOne = (colorOne >> 8) & 0xFF;
                    int blueOne = colorOne & 0xFF;

                    int redTwo = (colorTwo >> 16) & 0xFF;
                    int greenTwo = (colorTwo >> 8) & 0xFF;
                    int blueTwo = colorTwo & 0xFF;

                    int finalRed = Mth.lerpInt((float)Mth.clamp(velocity / 0.029081503190380643, 0, 1), redOne, redTwo);
                    int finalGreen = Mth.lerpInt((float)Mth.clamp(velocity / 0.029081503190380643, 0, 1), greenOne, greenTwo);
                    int finalBlue = Mth.lerpInt((float)Mth.clamp(velocity / 0.029081503190380643, 0, 1), blueOne, blueTwo);

                    int finalColor = 255 << 24 | finalRed << 16 | finalGreen << 8 | finalBlue;
                    colorRightOne = finalColor;
                }

                if (dyeTypeRightTwo.equals("speed")) {
                    Vec3 vec3 = entity.getDeltaMovement();
                    double velocity = vec3.lengthSqr();

                    int colorOne = primaryColorRightTwo;
                    int colorTwo = secondaryColorRightTwo;

                    int redOne = (colorOne >> 16) & 0xFF;
                    int greenOne = (colorOne >> 8) & 0xFF;
                    int blueOne = colorOne & 0xFF;

                    int redTwo = (colorTwo >> 16) & 0xFF;
                    int greenTwo = (colorTwo >> 8) & 0xFF;
                    int blueTwo = colorTwo & 0xFF;

                    int finalRed = Mth.lerpInt((float)Mth.clamp(velocity / 0.029081503190380643, 0, 1), redOne, redTwo);
                    int finalGreen = Mth.lerpInt((float)Mth.clamp(velocity / 0.029081503190380643, 0, 1), greenOne, greenTwo);
                    int finalBlue = Mth.lerpInt((float)Mth.clamp(velocity / 0.029081503190380643, 0, 1), blueOne, blueTwo);

                    int finalColor = 255 << 24 | finalRed << 16 | finalGreen << 8 | finalBlue;
                    colorRightTwo = finalColor;
                }

                if (dyeTypeLeftOne.equals("height_bedrock")) {
                    int absoluteMinimum = mc.level.getMinBuildHeight();
                    int absoluteMaximum = mc.level.getMaxBuildHeight();
                    int height = (int)entity.getY();

                    float value = Mth.clamp(((float)height - (float)absoluteMinimum) / ((float)absoluteMaximum - (float)absoluteMinimum), 0, 1);

                    int colorOne = primaryColorLeftOne;
                    int colorTwo = secondaryColorLeftOne;

                    int redOne = (colorOne >> 16) & 0xFF;
                    int greenOne = (colorOne >> 8) & 0xFF;
                    int blueOne = colorOne & 0xFF;

                    int redTwo = (colorTwo >> 16) & 0xFF;
                    int greenTwo = (colorTwo >> 8) & 0xFF;
                    int blueTwo = colorTwo & 0xFF;

                    int finalRed = Mth.lerpInt(value, redOne, redTwo);
                    int finalGreen = Mth.lerpInt(value, greenOne, greenTwo);
                    int finalBlue = Mth.lerpInt(value, blueOne, blueTwo);

                    int finalColor = 255 << 24 | finalRed << 16 | finalGreen << 8 | finalBlue;
                    colorLeftOne = finalColor;
                }

                if (dyeTypeLeftTwo.equals("height_bedrock")) {
                    int absoluteMinimum = mc.level.getMinBuildHeight();
                    int absoluteMaximum = mc.level.getMaxBuildHeight();
                    int height = (int)entity.getY();

                    float value = Mth.clamp(((float)height - (float)absoluteMinimum) / ((float)absoluteMaximum - (float)absoluteMinimum), 0, 1);

                    int colorOne = primaryColorLeftTwo;
                    int colorTwo = secondaryColorLeftTwo;

                    int redOne = (colorOne >> 16) & 0xFF;
                    int greenOne = (colorOne >> 8) & 0xFF;
                    int blueOne = colorOne & 0xFF;

                    int redTwo = (colorTwo >> 16) & 0xFF;
                    int greenTwo = (colorTwo >> 8) & 0xFF;
                    int blueTwo = colorTwo & 0xFF;

                    int finalRed = Mth.lerpInt(value, redOne, redTwo);
                    int finalGreen = Mth.lerpInt(value, greenOne, greenTwo);
                    int finalBlue = Mth.lerpInt(value, blueOne, blueTwo);

                    int finalColor = 255 << 24 | finalRed << 16 | finalGreen << 8 | finalBlue;
                    colorLeftTwo = finalColor;
                }

                if (dyeTypeRightOne.equals("height_bedrock")) {
                    int absoluteMinimum = mc.level.getMinBuildHeight();
                    int absoluteMaximum = mc.level.getMaxBuildHeight();
                    int height = (int)entity.getY();

                    float value = Mth.clamp(((float)height - (float)absoluteMinimum) / ((float)absoluteMaximum - (float)absoluteMinimum), 0, 1);

                    int colorOne = primaryColorRightOne;
                    int colorTwo = secondaryColorRightOne;

                    int redOne = (colorOne >> 16) & 0xFF;
                    int greenOne = (colorOne >> 8) & 0xFF;
                    int blueOne = colorOne & 0xFF;

                    int redTwo = (colorTwo >> 16) & 0xFF;
                    int greenTwo = (colorTwo >> 8) & 0xFF;
                    int blueTwo = colorTwo & 0xFF;

                    int finalRed = Mth.lerpInt(value, redOne, redTwo);
                    int finalGreen = Mth.lerpInt(value, greenOne, greenTwo);
                    int finalBlue = Mth.lerpInt(value, blueOne, blueTwo);

                    int finalColor = 255 << 24 | finalRed << 16 | finalGreen << 8 | finalBlue;
                    colorRightOne = finalColor;
                }

                if (dyeTypeRightTwo.equals("height_bedrock")) {
                    int absoluteMinimum = mc.level.getMinBuildHeight();
                    int absoluteMaximum = mc.level.getMaxBuildHeight();
                    int height = (int)entity.getY();

                    float value = Mth.clamp(((float)height - (float)absoluteMinimum) / ((float)absoluteMaximum - (float)absoluteMinimum), 0, 1);

                    int colorOne = primaryColorRightTwo;
                    int colorTwo = secondaryColorRightTwo;

                    int redOne = (colorOne >> 16) & 0xFF;
                    int greenOne = (colorOne >> 8) & 0xFF;
                    int blueOne = colorOne & 0xFF;

                    int redTwo = (colorTwo >> 16) & 0xFF;
                    int greenTwo = (colorTwo >> 8) & 0xFF;
                    int blueTwo = colorTwo & 0xFF;

                    int finalRed = Mth.lerpInt(value, redOne, redTwo);
                    int finalGreen = Mth.lerpInt(value, greenOne, greenTwo);
                    int finalBlue = Mth.lerpInt(value, blueOne, blueTwo);

                    int finalColor = 255 << 24 | finalRed << 16 | finalGreen << 8 | finalBlue;
                    colorRightTwo = finalColor;
                }

                if (dyeTypeLeftOne.equals("height_sea")) {
                    double minY = mc.level.getMinBuildHeight();
                    double maxY = mc.level.getMaxBuildHeight();
                    double seaY = mc.level.getSeaLevel();
                    double playerY = entity.getY();

                    double distanceAbove = maxY - seaY;
                    double distanceBelow = seaY - minY;
                    double maxDistance = Math.max(distanceAbove, distanceBelow);
                    float value = (float)Mth.clamp(Math.abs(playerY - seaY) / maxDistance, 0, 1);

                    int colorOne = primaryColorLeftOne;
                    int colorTwo = secondaryColorLeftOne;

                    int redOne = (colorOne >> 16) & 0xFF;
                    int greenOne = (colorOne >> 8) & 0xFF;
                    int blueOne = colorOne & 0xFF;

                    int redTwo = (colorTwo >> 16) & 0xFF;
                    int greenTwo = (colorTwo >> 8) & 0xFF;
                    int blueTwo = colorTwo & 0xFF;

                    int finalRed = Mth.lerpInt(value, redOne, redTwo);
                    int finalGreen = Mth.lerpInt(value, greenOne, greenTwo);
                    int finalBlue = Mth.lerpInt(value, blueOne, blueTwo);

                    int finalColor = 255 << 24 | finalRed << 16 | finalGreen << 8 | finalBlue;
                    colorLeftOne = finalColor;
                }

                if (dyeTypeLeftTwo.equals("height_sea")) {
                    double minY = mc.level.getMinBuildHeight();
                    double maxY = mc.level.getMaxBuildHeight();
                    double seaY = mc.level.getSeaLevel();
                    double playerY = entity.getY();

                    double distanceAbove = maxY - seaY;
                    double distanceBelow = seaY - minY;
                    double maxDistance = Math.max(distanceAbove, distanceBelow);
                    float value = (float)Mth.clamp(Math.abs(playerY - seaY) / maxDistance, 0, 1);

                    int colorOne = primaryColorLeftTwo;
                    int colorTwo = secondaryColorLeftTwo;

                    int redOne = (colorOne >> 16) & 0xFF;
                    int greenOne = (colorOne >> 8) & 0xFF;
                    int blueOne = colorOne & 0xFF;

                    int redTwo = (colorTwo >> 16) & 0xFF;
                    int greenTwo = (colorTwo >> 8) & 0xFF;
                    int blueTwo = colorTwo & 0xFF;

                    int finalRed = Mth.lerpInt(value, redOne, redTwo);
                    int finalGreen = Mth.lerpInt(value, greenOne, greenTwo);
                    int finalBlue = Mth.lerpInt(value, blueOne, blueTwo);

                    int finalColor = 255 << 24 | finalRed << 16 | finalGreen << 8 | finalBlue;
                    colorLeftTwo = finalColor;
                }

                if (dyeTypeRightOne.equals("height_sea")) {
                    double minY = mc.level.getMinBuildHeight();
                    double maxY = mc.level.getMaxBuildHeight();
                    double seaY = mc.level.getSeaLevel();
                    double playerY = entity.getY();

                    double distanceAbove = maxY - seaY;
                    double distanceBelow = seaY - minY;
                    double maxDistance = Math.max(distanceAbove, distanceBelow);
                    float value = (float)Mth.clamp(Math.abs(playerY - seaY) / maxDistance, 0, 1);

                    int colorOne = primaryColorRightOne;
                    int colorTwo = secondaryColorRightOne;

                    int redOne = (colorOne >> 16) & 0xFF;
                    int greenOne = (colorOne >> 8) & 0xFF;
                    int blueOne = colorOne & 0xFF;

                    int redTwo = (colorTwo >> 16) & 0xFF;
                    int greenTwo = (colorTwo >> 8) & 0xFF;
                    int blueTwo = colorTwo & 0xFF;

                    int finalRed = Mth.lerpInt(value, redOne, redTwo);
                    int finalGreen = Mth.lerpInt(value, greenOne, greenTwo);
                    int finalBlue = Mth.lerpInt(value, blueOne, blueTwo);

                    int finalColor = 255 << 24 | finalRed << 16 | finalGreen << 8 | finalBlue;
                    colorRightOne = finalColor;
                }

                if (dyeTypeRightTwo.equals("height_sea")) {
                    double minY = mc.level.getMinBuildHeight();
                    double maxY = mc.level.getMaxBuildHeight();
                    double seaY = mc.level.getSeaLevel();
                    double playerY = entity.getY();

                    double distanceAbove = maxY - seaY;
                    double distanceBelow = seaY - minY;
                    double maxDistance = Math.max(distanceAbove, distanceBelow);
                    float value = (float)Mth.clamp(Math.abs(playerY - seaY) / maxDistance, 0, 1);

                    int colorOne = primaryColorRightTwo;
                    int colorTwo = secondaryColorRightTwo;

                    int redOne = (colorOne >> 16) & 0xFF;
                    int greenOne = (colorOne >> 8) & 0xFF;
                    int blueOne = colorOne & 0xFF;

                    int redTwo = (colorTwo >> 16) & 0xFF;
                    int greenTwo = (colorTwo >> 8) & 0xFF;
                    int blueTwo = colorTwo & 0xFF;

                    int finalRed = Mth.lerpInt(value, redOne, redTwo);
                    int finalGreen = Mth.lerpInt(value, greenOne, greenTwo);
                    int finalBlue = Mth.lerpInt(value, blueOne, blueTwo);

                    int finalColor = 255 << 24 | finalRed << 16 | finalGreen << 8 | finalBlue;
                    colorRightTwo = finalColor;
                }

                if (dyeTypeLeftOne.equals("sculk")) {
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

                    colorLeftOne = 255 << 24 | finalRed << 16 | finalGreen << 8 | finalBlue;
                }

                if (dyeTypeLeftTwo.equals("sculk")) {
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

                    colorLeftTwo = 255 << 24 | finalRed << 16 | finalGreen << 8 | finalBlue;
                }

                if (dyeTypeRightOne.equals("sculk")) {
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

                    colorRightOne = 255 << 24 | finalRed << 16 | finalGreen << 8 | finalBlue;
                }

                if (dyeTypeRightTwo.equals("sculk")) {
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

                    colorRightTwo = 255 << 24 | finalRed << 16 | finalGreen << 8 | finalBlue;
                }

                if (dyeTypeLeftOne.equals("lamp")) {
                    lightOne = lightValueLeftOne << 20 | lightValueLeftOne << 4;
                }

                if (dyeTypeLeftTwo.equals("lamp")) {
                    lightTwo = lightValueLeftTwo << 20 | lightValueLeftTwo << 4;
                }

                if (dyeTypeRightOne.equals("lamp")) {
                    lightThree = lightValueRightOne << 20 | lightValueRightOne << 4;
                }

                if (dyeTypeRightTwo.equals("lamp")) {
                    lightFour = lightValueRightTwo << 20 | lightValueRightTwo << 4;
                }

                poseStack.pushPose();

                poseStack.scale(scale, -scale, scale);

                poseStack.mulPose(Axis.YP.rotationDegrees(180));

                poseStack.translate((-0.5) / scale, (ytranslation) / scale, (-0.5) / scale);

                poseStack.mulPose(Axis.XP.rotationDegrees(additionalXrot));

                poseStack.mulPose(Axis.YP.rotationDegrees(additionalYrot));

                poseStack.mulPose(Axis.ZP.rotationDegrees(additionalZrot));

                VertexConsumer consumerOneLeft = buffer.getBuffer(RenderType.entityTranslucent(tex1left)/**RenderType.armorCutoutNoCull(tex1left)**/);
                if (dyeTypeLeftOne.equals("ender")) {
                    consumerOneLeft = buffer.getBuffer(
                            WeaversParadiseRenderTypes.getVoidArmor(
                                    TheEndPortalRenderer.END_SKY_LOCATION,
                                    tex1left,
                                    TheEndPortalRenderer.END_PORTAL_LOCATION
                            )
                    );
                }
                this.model.LeftLeg.render(poseStack, consumerOneLeft, lightOne, OverlayTexture.NO_OVERLAY, colorLeftOne);

                VertexConsumer consumerOneRight = buffer.getBuffer(RenderType.entityTranslucent(tex1right)/**RenderType.armorCutoutNoCull(tex1right)**/);
                if (dyeTypeRightOne.equals("ender")) {
                    consumerOneRight = buffer.getBuffer(
                            WeaversParadiseRenderTypes.getVoidArmor(
                                    TheEndPortalRenderer.END_SKY_LOCATION,
                                    tex1right,
                                    TheEndPortalRenderer.END_PORTAL_LOCATION
                            )
                    );
                }
                this.model.RightLeg.render(poseStack, consumerOneRight, lightThree, OverlayTexture.NO_OVERLAY, colorRightOne);

                if (renderTypeLeft.equals("double")) {
                    VertexConsumer consumerTwoLeft = buffer.getBuffer(RenderType.entityTranslucent(tex2left)/**RenderType.armorCutoutNoCull(tex2left)**/);
                    if (dyeTypeLeftTwo.equals("ender")) {
                        consumerTwoLeft = buffer.getBuffer(
                                WeaversParadiseRenderTypes.getVoidArmor(
                                        TheEndPortalRenderer.END_SKY_LOCATION,
                                        tex2left,
                                        TheEndPortalRenderer.END_PORTAL_LOCATION
                                )
                        );
                    }
                    this.model1.LeftLeg.render(poseStack, consumerTwoLeft, lightTwo, OverlayTexture.NO_OVERLAY, colorLeftTwo);
                }

                if (renderTypeRight.equals("double")) {
                    VertexConsumer consumerTwoRight = buffer.getBuffer(RenderType.entityTranslucent(tex2right)/**RenderType.armorCutoutNoCull(tex2right)**/);
                    if (dyeTypeRightTwo.equals("ender")) {
                        consumerTwoRight = buffer.getBuffer(
                                WeaversParadiseRenderTypes.getVoidArmor(
                                        TheEndPortalRenderer.END_SKY_LOCATION,
                                        tex2right,
                                        TheEndPortalRenderer.END_PORTAL_LOCATION
                                )
                        );
                    }
                    this.model1.RightLeg.render(poseStack, consumerTwoRight, lightFour, OverlayTexture.NO_OVERLAY, colorRightTwo);
                }

                poseStack.popPose();
            } else if (stack.getItem() instanceof HandWarmersWool woolThighHighs) {
                Minecraft mc = Minecraft.getInstance();
                int ticks = (int) mc.level.getGameTime();
                int primaryColorLeftOne = woolThighHighs.getItemMainColor(stack, "left", 1);
                int secondaryColorLeftOne = woolThighHighs.getItemSecondaryColor(stack, "left", 1);
                int primaryColorRightOne = woolThighHighs.getItemMainColor(stack, "right", 1);
                int secondaryColorRightOne = woolThighHighs.getItemSecondaryColor(stack, "right", 1);
                int primaryColorLeftTwo = woolThighHighs.getItemMainColor(stack, "left", 2);
                int secondaryColorLeftTwo = woolThighHighs.getItemSecondaryColor(stack, "left", 2);
                int primaryColorRightTwo = woolThighHighs.getItemMainColor(stack, "right", 2);
                int secondaryColorRightTwo = woolThighHighs.getItemSecondaryColor(stack, "right", 2);
                String dyeTypeLeftOne = woolThighHighs.getItemDyeType(stack, "left", 1);
                String dyeTypeRightOne = woolThighHighs.getItemDyeType(stack, "right", 1);
                String dyeTypeLeftTwo = woolThighHighs.getItemDyeType(stack, "left", 2);
                String dyeTypeRightTwo = woolThighHighs.getItemDyeType(stack, "right", 2);
                String stensilTypeLeft = woolThighHighs.getStensilType(stack, "left");
                String stensilTypeRight = woolThighHighs.getStensilType(stack, "right");
                int lightValueLeftOne = woolThighHighs.getItemLightValue(stack, "left", 1);
                int lightValueLeftTwo = woolThighHighs.getItemLightValue(stack, "left", 2);
                int lightValueRightOne = woolThighHighs.getItemLightValue(stack, "right", 1);
                int lightValueRightTwo = woolThighHighs.getItemLightValue(stack, "right", 2);

                WeaversParadiseHandWarmersTextureHandler leftHandler = WeaversParadiseHandWarmersTextureHandler.getByTypeAndMaterial(stensilTypeLeft, "wool");
                String renderTypeLeft = leftHandler.getRenderType();
                ResourceLocation tex1left = leftHandler.getTextureOne();
                ResourceLocation tex2left = leftHandler.getTextureTwo();

                WeaversParadiseHandWarmersTextureHandler rightHandler = WeaversParadiseHandWarmersTextureHandler.getByTypeAndMaterial(stensilTypeRight, "wool");
                String renderTypeRight = rightHandler.getRenderType();
                ResourceLocation tex1right = rightHandler.getTextureOne();
                ResourceLocation tex2right = rightHandler.getTextureTwo();

                int redLeftPrimaryOne = (primaryColorLeftOne >> 16) & 0xFF;
                int greenLeftPrimaryOne = (primaryColorLeftOne >> 8) & 0xFF;
                int blueLeftPrimaryOne = primaryColorLeftOne & 0xFF;

                int redRightPrimaryOne = (primaryColorRightOne >> 16) & 0xFF;
                int greenRightPrimaryOne = (primaryColorRightOne >> 8) & 0xFF;
                int blueRightPrimaryOne = primaryColorRightOne & 0xFF;

                int redLeftSecondaryOne = (secondaryColorLeftOne >> 16) & 0xFF;
                int greenLeftSecondaryOne = (secondaryColorLeftOne >> 8) & 0xFF;
                int blueLeftSecondaryOne = secondaryColorLeftOne & 0xFF;

                int redRightSecondaryOne = (secondaryColorRightOne >> 16) & 0xFF;
                int greenRightSecondaryOne = (secondaryColorRightOne >> 8) & 0xFF;
                int blueRightSecondaryOne = secondaryColorRightOne & 0xFF;

                int redLeftPrimaryTwo = (primaryColorLeftTwo >> 16) & 0xFF;
                int greenLeftPrimaryTwo = (primaryColorLeftTwo >> 8) & 0xFF;
                int blueLeftPrimaryTwo = primaryColorLeftTwo & 0xFF;

                int redRightPrimaryTwo = (primaryColorRightTwo >> 16) & 0xFF;
                int greenRightPrimaryTwo = (primaryColorRightTwo >> 8) & 0xFF;
                int blueRightPrimaryTwo = primaryColorRightTwo & 0xFF;

                int redLeftSecondaryTwo = (secondaryColorLeftTwo >> 16) & 0xFF;
                int greenLeftSecondaryTwo = (secondaryColorLeftTwo >> 8) & 0xFF;
                int blueLeftSecondaryTwo = secondaryColorLeftTwo & 0xFF;

                int redRightSecondaryTwo = (secondaryColorRightTwo >> 16) & 0xFF;
                int greenRightSecondaryTwo = (secondaryColorRightTwo >> 8) & 0xFF;
                int blueRightSecondaryTwo = secondaryColorRightTwo & 0xFF;

                int finalRedLeftOne = redLeftPrimaryOne;
                int finalGreenLeftOne = greenLeftPrimaryOne;
                int finalBlueLeftOne = blueLeftPrimaryOne;

                int finalRedRightOne = redRightPrimaryOne;
                int finalGreenRightOne = greenRightPrimaryOne;
                int finalBlueRightOne = blueRightPrimaryOne;

                int finalRedLeftTwo = redLeftPrimaryTwo;
                int finalGreenLeftTwo = greenLeftPrimaryTwo;
                int finalBlueLeftTwo = blueLeftPrimaryTwo;

                int finalRedRightTwo = redRightPrimaryTwo;
                int finalGreenRightTwo = greenRightPrimaryTwo;
                int finalBlueRightTwo = blueRightPrimaryTwo;

                int colorLeftOne = 255 << 24 | finalRedLeftOne << 16 | finalGreenLeftOne << 8 | finalBlueLeftOne;
                int colorRightOne = 255 << 24 | finalRedRightOne << 16 | finalGreenRightOne << 8 | finalBlueRightOne;

                int colorLeftTwo = 255 << 24 | finalRedLeftTwo << 16 | finalGreenLeftTwo << 8 | finalBlueLeftTwo;
                int colorRightTwo = 255 << 24 | finalRedRightTwo << 16 | finalGreenRightTwo << 8 | finalBlueRightTwo;

                int lightOne = packedLight;
                int lightTwo = packedLight;
                int lightThree = packedLight;
                int lightFour = packedLight;

                if (dyeTypeLeftOne.equals("rainbow")) {
                    colorLeftOne = getRainbowColor(ticks);
                }

                if (dyeTypeLeftTwo.equals("rainbow")) {
                    colorLeftTwo = getRainbowColor(ticks);
                }

                if (dyeTypeRightOne.equals("rainbow")) {
                    colorRightOne = getRainbowColor(ticks);
                }

                if (dyeTypeRightTwo.equals("rainbow")) {
                    colorRightTwo = getRainbowColor(ticks);
                }

                if (dyeTypeLeftOne.equals("ender")) {
                    lightOne = 0;
                }

                if (dyeTypeLeftTwo.equals("ender")) {
                    lightTwo = 0;
                }

                if (dyeTypeRightOne.equals("ender")) {
                    lightThree = 0;
                }

                if (dyeTypeRightTwo.equals("ender")) {
                    lightFour = 0;
                }

                if (dyeTypeLeftOne.equals("glowstone")) {
                    lightOne = LightTexture.FULL_BRIGHT;
                }

                if (dyeTypeLeftTwo.equals("glowstone")) {
                    lightTwo = LightTexture.FULL_BRIGHT;
                }

                if (dyeTypeRightOne.equals("glowstone")) {
                    lightThree = LightTexture.FULL_BRIGHT;
                }

                if (dyeTypeRightTwo.equals("glowstone")) {
                    lightFour = LightTexture.FULL_BRIGHT;
                }

                if (dyeTypeLeftOne.equals("biome")) {
                    BlockPos bpos = new BlockPos((int)entity.getX(), (int)entity.getY(), (int)entity.getZ());
                    int color = mc.level.getBiome(bpos).value().getGrassColor(entity.getX(), entity.getZ());
                    color = 255 << 24 | ((color >> 16) & 0xFF) << 16 | ((color >> 8) & 0xFF) << 8 | (color & 0xFF);

                    colorLeftOne = color;
                }

                if (dyeTypeLeftTwo.equals("biome")) {
                    BlockPos bpos = new BlockPos((int)entity.getX(), (int)entity.getY(), (int)entity.getZ());
                    int color = mc.level.getBiome(bpos).value().getGrassColor(entity.getX(), entity.getZ());
                    color = 255 << 24 | ((color >> 16) & 0xFF) << 16 | ((color >> 8) & 0xFF) << 8 | (color & 0xFF);

                    colorLeftTwo = color;
                }

                if (dyeTypeRightOne.equals("biome")) {
                    BlockPos bpos = new BlockPos((int)entity.getX(), (int)entity.getY(), (int)entity.getZ());
                    int color = mc.level.getBiome(bpos).value().getGrassColor(entity.getX(), entity.getZ());
                    color = 255 << 24 | ((color >> 16) & 0xFF) << 16 | ((color >> 8) & 0xFF) << 8 | (color & 0xFF);

                    colorRightOne = color;
                }

                if (dyeTypeRightTwo.equals("biome")) {
                    BlockPos bpos = new BlockPos((int)entity.getX(), (int)entity.getY(), (int)entity.getZ());
                    int color = mc.level.getBiome(bpos).value().getGrassColor(entity.getX(), entity.getZ());
                    color = 255 << 24 | ((color >> 16) & 0xFF) << 16 | ((color >> 8) & 0xFF) << 8 | (color & 0xFF);

                    colorRightTwo = color;
                }

                if (dyeTypeLeftOne.equals("speed")) {
                    Vec3 vec3 = entity.getDeltaMovement();
                    double velocity = vec3.lengthSqr();

                    int colorOne = primaryColorLeftOne;
                    int colorTwo = secondaryColorLeftOne;

                    int redOne = (colorOne >> 16) & 0xFF;
                    int greenOne = (colorOne >> 8) & 0xFF;
                    int blueOne = colorOne & 0xFF;

                    int redTwo = (colorTwo >> 16) & 0xFF;
                    int greenTwo = (colorTwo >> 8) & 0xFF;
                    int blueTwo = colorTwo & 0xFF;

                    int finalRed = Mth.lerpInt((float)Mth.clamp(velocity / 0.029081503190380643, 0, 1), redOne, redTwo);
                    int finalGreen = Mth.lerpInt((float)Mth.clamp(velocity / 0.029081503190380643, 0, 1), greenOne, greenTwo);
                    int finalBlue = Mth.lerpInt((float)Mth.clamp(velocity / 0.029081503190380643, 0, 1), blueOne, blueTwo);

                    int finalColor = 255 << 24 | finalRed << 16 | finalGreen << 8 | finalBlue;
                    colorLeftOne = finalColor;
                }

                if (dyeTypeLeftTwo.equals("speed")) {
                    Vec3 vec3 = entity.getDeltaMovement();
                    double velocity = vec3.lengthSqr();

                    int colorOne = primaryColorLeftTwo;
                    int colorTwo = secondaryColorLeftTwo;

                    int redOne = (colorOne >> 16) & 0xFF;
                    int greenOne = (colorOne >> 8) & 0xFF;
                    int blueOne = colorOne & 0xFF;

                    int redTwo = (colorTwo >> 16) & 0xFF;
                    int greenTwo = (colorTwo >> 8) & 0xFF;
                    int blueTwo = colorTwo & 0xFF;

                    int finalRed = Mth.lerpInt((float)Mth.clamp(velocity / 0.029081503190380643, 0, 1), redOne, redTwo);
                    int finalGreen = Mth.lerpInt((float)Mth.clamp(velocity / 0.029081503190380643, 0, 1), greenOne, greenTwo);
                    int finalBlue = Mth.lerpInt((float)Mth.clamp(velocity / 0.029081503190380643, 0, 1), blueOne, blueTwo);

                    int finalColor = 255 << 24 | finalRed << 16 | finalGreen << 8 | finalBlue;
                    colorLeftTwo = finalColor;
                }

                if (dyeTypeRightOne.equals("speed")) {
                    Vec3 vec3 = entity.getDeltaMovement();
                    double velocity = vec3.lengthSqr();

                    int colorOne = primaryColorRightOne;
                    int colorTwo = secondaryColorRightOne;

                    int redOne = (colorOne >> 16) & 0xFF;
                    int greenOne = (colorOne >> 8) & 0xFF;
                    int blueOne = colorOne & 0xFF;

                    int redTwo = (colorTwo >> 16) & 0xFF;
                    int greenTwo = (colorTwo >> 8) & 0xFF;
                    int blueTwo = colorTwo & 0xFF;

                    int finalRed = Mth.lerpInt((float)Mth.clamp(velocity / 0.029081503190380643, 0, 1), redOne, redTwo);
                    int finalGreen = Mth.lerpInt((float)Mth.clamp(velocity / 0.029081503190380643, 0, 1), greenOne, greenTwo);
                    int finalBlue = Mth.lerpInt((float)Mth.clamp(velocity / 0.029081503190380643, 0, 1), blueOne, blueTwo);

                    int finalColor = 255 << 24 | finalRed << 16 | finalGreen << 8 | finalBlue;
                    colorRightOne = finalColor;
                }

                if (dyeTypeRightTwo.equals("speed")) {
                    Vec3 vec3 = entity.getDeltaMovement();
                    double velocity = vec3.lengthSqr();

                    int colorOne = primaryColorRightTwo;
                    int colorTwo = secondaryColorRightTwo;

                    int redOne = (colorOne >> 16) & 0xFF;
                    int greenOne = (colorOne >> 8) & 0xFF;
                    int blueOne = colorOne & 0xFF;

                    int redTwo = (colorTwo >> 16) & 0xFF;
                    int greenTwo = (colorTwo >> 8) & 0xFF;
                    int blueTwo = colorTwo & 0xFF;

                    int finalRed = Mth.lerpInt((float)Mth.clamp(velocity / 0.029081503190380643, 0, 1), redOne, redTwo);
                    int finalGreen = Mth.lerpInt((float)Mth.clamp(velocity / 0.029081503190380643, 0, 1), greenOne, greenTwo);
                    int finalBlue = Mth.lerpInt((float)Mth.clamp(velocity / 0.029081503190380643, 0, 1), blueOne, blueTwo);

                    int finalColor = 255 << 24 | finalRed << 16 | finalGreen << 8 | finalBlue;
                    colorRightTwo = finalColor;
                }

                if (dyeTypeLeftOne.equals("height_bedrock")) {
                    int absoluteMinimum = mc.level.getMinBuildHeight();
                    int absoluteMaximum = mc.level.getMaxBuildHeight();
                    int height = (int)entity.getY();

                    float value = Mth.clamp(((float)height - (float)absoluteMinimum) / ((float)absoluteMaximum - (float)absoluteMinimum), 0, 1);

                    int colorOne = primaryColorLeftOne;
                    int colorTwo = secondaryColorLeftOne;

                    int redOne = (colorOne >> 16) & 0xFF;
                    int greenOne = (colorOne >> 8) & 0xFF;
                    int blueOne = colorOne & 0xFF;

                    int redTwo = (colorTwo >> 16) & 0xFF;
                    int greenTwo = (colorTwo >> 8) & 0xFF;
                    int blueTwo = colorTwo & 0xFF;

                    int finalRed = Mth.lerpInt(value, redOne, redTwo);
                    int finalGreen = Mth.lerpInt(value, greenOne, greenTwo);
                    int finalBlue = Mth.lerpInt(value, blueOne, blueTwo);

                    int finalColor = 255 << 24 | finalRed << 16 | finalGreen << 8 | finalBlue;
                    colorLeftOne = finalColor;
                }

                if (dyeTypeLeftTwo.equals("height_bedrock")) {
                    int absoluteMinimum = mc.level.getMinBuildHeight();
                    int absoluteMaximum = mc.level.getMaxBuildHeight();
                    int height = (int)entity.getY();

                    float value = Mth.clamp(((float)height - (float)absoluteMinimum) / ((float)absoluteMaximum - (float)absoluteMinimum), 0, 1);

                    int colorOne = primaryColorLeftTwo;
                    int colorTwo = secondaryColorLeftTwo;

                    int redOne = (colorOne >> 16) & 0xFF;
                    int greenOne = (colorOne >> 8) & 0xFF;
                    int blueOne = colorOne & 0xFF;

                    int redTwo = (colorTwo >> 16) & 0xFF;
                    int greenTwo = (colorTwo >> 8) & 0xFF;
                    int blueTwo = colorTwo & 0xFF;

                    int finalRed = Mth.lerpInt(value, redOne, redTwo);
                    int finalGreen = Mth.lerpInt(value, greenOne, greenTwo);
                    int finalBlue = Mth.lerpInt(value, blueOne, blueTwo);

                    int finalColor = 255 << 24 | finalRed << 16 | finalGreen << 8 | finalBlue;
                    colorLeftTwo = finalColor;
                }

                if (dyeTypeRightOne.equals("height_bedrock")) {
                    int absoluteMinimum = mc.level.getMinBuildHeight();
                    int absoluteMaximum = mc.level.getMaxBuildHeight();
                    int height = (int)entity.getY();

                    float value = Mth.clamp(((float)height - (float)absoluteMinimum) / ((float)absoluteMaximum - (float)absoluteMinimum), 0, 1);

                    int colorOne = primaryColorRightOne;
                    int colorTwo = secondaryColorRightOne;

                    int redOne = (colorOne >> 16) & 0xFF;
                    int greenOne = (colorOne >> 8) & 0xFF;
                    int blueOne = colorOne & 0xFF;

                    int redTwo = (colorTwo >> 16) & 0xFF;
                    int greenTwo = (colorTwo >> 8) & 0xFF;
                    int blueTwo = colorTwo & 0xFF;

                    int finalRed = Mth.lerpInt(value, redOne, redTwo);
                    int finalGreen = Mth.lerpInt(value, greenOne, greenTwo);
                    int finalBlue = Mth.lerpInt(value, blueOne, blueTwo);

                    int finalColor = 255 << 24 | finalRed << 16 | finalGreen << 8 | finalBlue;
                    colorRightOne = finalColor;
                }

                if (dyeTypeRightTwo.equals("height_bedrock")) {
                    int absoluteMinimum = mc.level.getMinBuildHeight();
                    int absoluteMaximum = mc.level.getMaxBuildHeight();
                    int height = (int)entity.getY();

                    float value = Mth.clamp(((float)height - (float)absoluteMinimum) / ((float)absoluteMaximum - (float)absoluteMinimum), 0, 1);

                    int colorOne = primaryColorRightTwo;
                    int colorTwo = secondaryColorRightTwo;

                    int redOne = (colorOne >> 16) & 0xFF;
                    int greenOne = (colorOne >> 8) & 0xFF;
                    int blueOne = colorOne & 0xFF;

                    int redTwo = (colorTwo >> 16) & 0xFF;
                    int greenTwo = (colorTwo >> 8) & 0xFF;
                    int blueTwo = colorTwo & 0xFF;

                    int finalRed = Mth.lerpInt(value, redOne, redTwo);
                    int finalGreen = Mth.lerpInt(value, greenOne, greenTwo);
                    int finalBlue = Mth.lerpInt(value, blueOne, blueTwo);

                    int finalColor = 255 << 24 | finalRed << 16 | finalGreen << 8 | finalBlue;
                    colorRightTwo = finalColor;
                }

                if (dyeTypeLeftOne.equals("height_sea")) {
                    double minY = mc.level.getMinBuildHeight();
                    double maxY = mc.level.getMaxBuildHeight();
                    double seaY = mc.level.getSeaLevel();
                    double playerY = entity.getY();

                    double distanceAbove = maxY - seaY;
                    double distanceBelow = seaY - minY;
                    double maxDistance = Math.max(distanceAbove, distanceBelow);
                    float value = (float)Mth.clamp(Math.abs(playerY - seaY) / maxDistance, 0, 1);

                    int colorOne = primaryColorLeftOne;
                    int colorTwo = secondaryColorLeftOne;

                    int redOne = (colorOne >> 16) & 0xFF;
                    int greenOne = (colorOne >> 8) & 0xFF;
                    int blueOne = colorOne & 0xFF;

                    int redTwo = (colorTwo >> 16) & 0xFF;
                    int greenTwo = (colorTwo >> 8) & 0xFF;
                    int blueTwo = colorTwo & 0xFF;

                    int finalRed = Mth.lerpInt(value, redOne, redTwo);
                    int finalGreen = Mth.lerpInt(value, greenOne, greenTwo);
                    int finalBlue = Mth.lerpInt(value, blueOne, blueTwo);

                    int finalColor = 255 << 24 | finalRed << 16 | finalGreen << 8 | finalBlue;
                    colorLeftOne = finalColor;
                }

                if (dyeTypeLeftTwo.equals("height_sea")) {
                    double minY = mc.level.getMinBuildHeight();
                    double maxY = mc.level.getMaxBuildHeight();
                    double seaY = mc.level.getSeaLevel();
                    double playerY = entity.getY();

                    double distanceAbove = maxY - seaY;
                    double distanceBelow = seaY - minY;
                    double maxDistance = Math.max(distanceAbove, distanceBelow);
                    float value = (float)Mth.clamp(Math.abs(playerY - seaY) / maxDistance, 0, 1);

                    int colorOne = primaryColorLeftTwo;
                    int colorTwo = secondaryColorLeftTwo;

                    int redOne = (colorOne >> 16) & 0xFF;
                    int greenOne = (colorOne >> 8) & 0xFF;
                    int blueOne = colorOne & 0xFF;

                    int redTwo = (colorTwo >> 16) & 0xFF;
                    int greenTwo = (colorTwo >> 8) & 0xFF;
                    int blueTwo = colorTwo & 0xFF;

                    int finalRed = Mth.lerpInt(value, redOne, redTwo);
                    int finalGreen = Mth.lerpInt(value, greenOne, greenTwo);
                    int finalBlue = Mth.lerpInt(value, blueOne, blueTwo);

                    int finalColor = 255 << 24 | finalRed << 16 | finalGreen << 8 | finalBlue;
                    colorLeftTwo = finalColor;
                }

                if (dyeTypeRightOne.equals("height_sea")) {
                    double minY = mc.level.getMinBuildHeight();
                    double maxY = mc.level.getMaxBuildHeight();
                    double seaY = mc.level.getSeaLevel();
                    double playerY = entity.getY();

                    double distanceAbove = maxY - seaY;
                    double distanceBelow = seaY - minY;
                    double maxDistance = Math.max(distanceAbove, distanceBelow);
                    float value = (float)Mth.clamp(Math.abs(playerY - seaY) / maxDistance, 0, 1);

                    int colorOne = primaryColorRightOne;
                    int colorTwo = secondaryColorRightOne;

                    int redOne = (colorOne >> 16) & 0xFF;
                    int greenOne = (colorOne >> 8) & 0xFF;
                    int blueOne = colorOne & 0xFF;

                    int redTwo = (colorTwo >> 16) & 0xFF;
                    int greenTwo = (colorTwo >> 8) & 0xFF;
                    int blueTwo = colorTwo & 0xFF;

                    int finalRed = Mth.lerpInt(value, redOne, redTwo);
                    int finalGreen = Mth.lerpInt(value, greenOne, greenTwo);
                    int finalBlue = Mth.lerpInt(value, blueOne, blueTwo);

                    int finalColor = 255 << 24 | finalRed << 16 | finalGreen << 8 | finalBlue;
                    colorRightOne = finalColor;
                }

                if (dyeTypeRightTwo.equals("height_sea")) {
                    double minY = mc.level.getMinBuildHeight();
                    double maxY = mc.level.getMaxBuildHeight();
                    double seaY = mc.level.getSeaLevel();
                    double playerY = entity.getY();

                    double distanceAbove = maxY - seaY;
                    double distanceBelow = seaY - minY;
                    double maxDistance = Math.max(distanceAbove, distanceBelow);
                    float value = (float)Mth.clamp(Math.abs(playerY - seaY) / maxDistance, 0, 1);

                    int colorOne = primaryColorRightTwo;
                    int colorTwo = secondaryColorRightTwo;

                    int redOne = (colorOne >> 16) & 0xFF;
                    int greenOne = (colorOne >> 8) & 0xFF;
                    int blueOne = colorOne & 0xFF;

                    int redTwo = (colorTwo >> 16) & 0xFF;
                    int greenTwo = (colorTwo >> 8) & 0xFF;
                    int blueTwo = colorTwo & 0xFF;

                    int finalRed = Mth.lerpInt(value, redOne, redTwo);
                    int finalGreen = Mth.lerpInt(value, greenOne, greenTwo);
                    int finalBlue = Mth.lerpInt(value, blueOne, blueTwo);

                    int finalColor = 255 << 24 | finalRed << 16 | finalGreen << 8 | finalBlue;
                    colorRightTwo = finalColor;
                }

                if (dyeTypeLeftOne.equals("sculk")) {
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

                    colorLeftOne = 255 << 24 | finalRed << 16 | finalGreen << 8 | finalBlue;
                }

                if (dyeTypeLeftTwo.equals("sculk")) {
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

                    colorLeftTwo = 255 << 24 | finalRed << 16 | finalGreen << 8 | finalBlue;
                }

                if (dyeTypeRightOne.equals("sculk")) {
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

                    colorRightOne = 255 << 24 | finalRed << 16 | finalGreen << 8 | finalBlue;
                }

                if (dyeTypeRightTwo.equals("sculk")) {
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

                    colorRightTwo = 255 << 24 | finalRed << 16 | finalGreen << 8 | finalBlue;
                }

                if (dyeTypeLeftOne.equals("lamp")) {
                    lightOne = lightValueLeftOne << 20 | lightValueLeftOne << 4;
                }

                if (dyeTypeLeftTwo.equals("lamp")) {
                    lightTwo = lightValueLeftTwo << 20 | lightValueLeftTwo << 4;
                }

                if (dyeTypeRightOne.equals("lamp")) {
                    lightThree = lightValueRightOne << 20 | lightValueRightOne << 4;
                }

                if (dyeTypeRightTwo.equals("lamp")) {
                    lightFour = lightValueRightTwo << 20 | lightValueRightTwo << 4;
                }

                poseStack.pushPose();

                poseStack.scale(scale, -scale, scale);

                poseStack.mulPose(Axis.YP.rotationDegrees(180));

                poseStack.translate((-0.5) / scale, (ytranslation) / scale, (-0.5) / scale);

                poseStack.mulPose(Axis.XP.rotationDegrees(additionalXrot));

                poseStack.mulPose(Axis.YP.rotationDegrees(additionalYrot));

                poseStack.mulPose(Axis.ZP.rotationDegrees(additionalZrot));

                VertexConsumer consumerOneLeft = buffer.getBuffer(RenderType.entityTranslucent(tex1left)/**RenderType.armorCutoutNoCull(tex1left)**/);
                if (dyeTypeLeftOne.equals("ender")) {
                    consumerOneLeft = buffer.getBuffer(
                            WeaversParadiseRenderTypes.getVoidArmor(
                                    TheEndPortalRenderer.END_SKY_LOCATION,
                                    tex1left,
                                    TheEndPortalRenderer.END_PORTAL_LOCATION
                            )
                    );
                }
                this.model.LeftLeg.render(poseStack, consumerOneLeft, lightOne, OverlayTexture.NO_OVERLAY, colorLeftOne);

                VertexConsumer consumerOneRight = buffer.getBuffer(RenderType.entityTranslucent(tex1right)/**RenderType.armorCutoutNoCull(tex1right)**/);
                if (dyeTypeRightOne.equals("ender")) {
                    consumerOneRight = buffer.getBuffer(
                            WeaversParadiseRenderTypes.getVoidArmor(
                                    TheEndPortalRenderer.END_SKY_LOCATION,
                                    tex1right,
                                    TheEndPortalRenderer.END_PORTAL_LOCATION
                            )
                    );
                }
                this.model.RightLeg.render(poseStack, consumerOneRight, lightThree, OverlayTexture.NO_OVERLAY, colorRightOne);

                if (renderTypeLeft.equals("double")) {
                    VertexConsumer consumerTwoLeft = buffer.getBuffer(RenderType.entityTranslucent(tex2left)/**RenderType.armorCutoutNoCull(tex2left)**/);
                    if (dyeTypeLeftTwo.equals("ender")) {
                        consumerTwoLeft = buffer.getBuffer(
                                WeaversParadiseRenderTypes.getVoidArmor(
                                        TheEndPortalRenderer.END_SKY_LOCATION,
                                        tex2left,
                                        TheEndPortalRenderer.END_PORTAL_LOCATION
                                )
                        );
                    }
                    this.model1.LeftLeg.render(poseStack, consumerTwoLeft, lightTwo, OverlayTexture.NO_OVERLAY, colorLeftTwo);
                }

                if (renderTypeRight.equals("double")) {
                    VertexConsumer consumerTwoRight = buffer.getBuffer(RenderType.entityTranslucent(tex2right)/**RenderType.armorCutoutNoCull(tex2right)**/);
                    if (dyeTypeRightTwo.equals("ender")) {
                        consumerTwoRight = buffer.getBuffer(
                                WeaversParadiseRenderTypes.getVoidArmor(
                                        TheEndPortalRenderer.END_SKY_LOCATION,
                                        tex2right,
                                        TheEndPortalRenderer.END_PORTAL_LOCATION
                                )
                        );
                    }
                    this.model1.RightLeg.render(poseStack, consumerTwoRight, lightFour, OverlayTexture.NO_OVERLAY, colorRightTwo);
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
