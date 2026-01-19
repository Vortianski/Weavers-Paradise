package xox.labvorty.weaversparadise.gui.screen;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.TheEndPortalRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.util.Mth;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.GuiGraphics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import xox.labvorty.weaversparadise.curios.WeaversParadiseMobLayers;
import xox.labvorty.weaversparadise.data.WeaversParadiseHandWarmersTextureHandler;
import xox.labvorty.weaversparadise.data.WeaversParadiseShirtTextureHandler;
import xox.labvorty.weaversparadise.data.WeaversParadiseThighHighsTextureHandler;
import xox.labvorty.weaversparadise.gui.menu.DyeingMenu;
import xox.labvorty.weaversparadise.init.WeaversParadiseItems;
import xox.labvorty.weaversparadise.items.*;
import xox.labvorty.weaversparadise.model.ModelUpperWear;
import xox.labvorty.weaversparadise.model.ThighHighsModel;
import xox.labvorty.weaversparadise.renderers.WeaversParadiseGlobalRendererDataHolder;
import xox.labvorty.weaversparadise.renderers.WeaversParadiseRenderTypes;

@OnlyIn(Dist.CLIENT)
public class DyeingScreen extends AbstractContainerScreen<DyeingMenu> {
    private final static HashMap<String, Object> guistate = DyeingMenu.guistate;
    private final Level world;
    private final int x, y, z;
    private final Player entity;
    private ItemStack stack = ItemStack.EMPTY;
    private float modelYaw = 180f;
    private int lastMouseX = -1;
    private static final ThighHighsModel model = new ThighHighsModel(Minecraft.getInstance().getEntityModels().bakeLayer(WeaversParadiseMobLayers.THIGH_HIGHS));
    private static final ThighHighsModel model1 = new ThighHighsModel(Minecraft.getInstance().getEntityModels().bakeLayer(WeaversParadiseMobLayers.THIGH_HIGHS));
    private static final ModelUpperWear model2 = new ModelUpperWear(Minecraft.getInstance().getEntityModels().bakeLayer(WeaversParadiseMobLayers.UPPER_WEAR));

    public DyeingScreen(DyeingMenu container, Inventory inventory, Component text) {
        super(container, inventory, text);
        this.world = container.world;
        this.x = container.x;
        this.y = container.y;
        this.z = container.z;
        this.entity = container.entity;
        this.imageWidth = 176;
        this.imageHeight = 220;
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(guiGraphics, mouseX, mouseY, partialTicks);
        super.render(guiGraphics, mouseX, mouseY, partialTicks);
        this.renderTooltip(guiGraphics, mouseX, mouseY);

        PoseStack poseStack = guiGraphics.pose();

        if (stack.getItem() instanceof ThighHighsCotton cottonThighHighs) {
            Minecraft mc = Minecraft.getInstance();

            MultiBufferSource.BufferSource buffer = mc.renderBuffers().bufferSource();
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

            WeaversParadiseThighHighsTextureHandler leftHandler = WeaversParadiseThighHighsTextureHandler.getByTypeAndMaterial(stensilTypeLeft, "cotton");
            String renderTypeLeft = leftHandler.getRenderType();
            ResourceLocation tex1left = leftHandler.getTextureOne();
            ResourceLocation tex2left = leftHandler.getTextureTwo();

            WeaversParadiseThighHighsTextureHandler rightHandler = WeaversParadiseThighHighsTextureHandler.getByTypeAndMaterial(stensilTypeRight, "cotton");
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

            int lightOne = LightTexture.FULL_BRIGHT;
            int lightTwo = LightTexture.FULL_BRIGHT;
            int lightThree = LightTexture.FULL_BRIGHT;
            int lightFour = LightTexture.FULL_BRIGHT;

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

            poseStack.translate(this.leftPos + 87, this.topPos, 50);

            poseStack.scale(48f, 48f, 48f);

            poseStack.mulPose(Axis.YP.rotationDegrees(modelYaw));

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
        } else if (stack.getItem() instanceof ThighHighsSilk silkThighHighs) {
            Minecraft mc = Minecraft.getInstance();

            MultiBufferSource.BufferSource buffer = mc.renderBuffers().bufferSource();
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

            WeaversParadiseThighHighsTextureHandler leftHandler = WeaversParadiseThighHighsTextureHandler.getByTypeAndMaterial(stensilTypeLeft, "silk");
            String renderTypeLeft = leftHandler.getRenderType();
            ResourceLocation tex1left = leftHandler.getTextureOne();
            ResourceLocation tex2left = leftHandler.getTextureTwo();

            WeaversParadiseThighHighsTextureHandler rightHandler = WeaversParadiseThighHighsTextureHandler.getByTypeAndMaterial(stensilTypeRight, "silk");
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

            int lightOne = LightTexture.FULL_BRIGHT;
            int lightTwo = LightTexture.FULL_BRIGHT;
            int lightThree = LightTexture.FULL_BRIGHT;
            int lightFour = LightTexture.FULL_BRIGHT;

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

            poseStack.translate(this.leftPos + 87, this.topPos, 50);

            poseStack.scale(48f, 48f, 48f);

            poseStack.mulPose(Axis.YP.rotationDegrees(modelYaw));

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
        } else if (stack.getItem() instanceof ThighHighsWool woolThighHighs) {
            Minecraft mc = Minecraft.getInstance();

            MultiBufferSource.BufferSource buffer = mc.renderBuffers().bufferSource();
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

            WeaversParadiseThighHighsTextureHandler leftHandler = WeaversParadiseThighHighsTextureHandler.getByTypeAndMaterial(stensilTypeLeft, "wool");
            String renderTypeLeft = leftHandler.getRenderType();
            ResourceLocation tex1left = leftHandler.getTextureOne();
            ResourceLocation tex2left = leftHandler.getTextureTwo();

            WeaversParadiseThighHighsTextureHandler rightHandler = WeaversParadiseThighHighsTextureHandler.getByTypeAndMaterial(stensilTypeRight, "wool");
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

            int lightOne = LightTexture.FULL_BRIGHT;
            int lightTwo = LightTexture.FULL_BRIGHT;
            int lightThree = LightTexture.FULL_BRIGHT;
            int lightFour = LightTexture.FULL_BRIGHT;

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

            poseStack.translate(this.leftPos + 87, this.topPos, 50);

            poseStack.scale(48f, 48f, 48f);

            poseStack.mulPose(Axis.YP.rotationDegrees(modelYaw));

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
        } else if (stack.getItem() instanceof ShirtCotton shirtCotton) {
            Minecraft mc = Minecraft.getInstance();

            MultiBufferSource.BufferSource buffer = mc.renderBuffers().bufferSource();
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

            int lightOne = LightTexture.FULL_BRIGHT;
            int lightTwo = LightTexture.FULL_BRIGHT;

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

            poseStack.translate(this.leftPos + 88, this.topPos + 30, 50);

            poseStack.scale(36f, 36f, 36f);

            poseStack.mulPose(Axis.YP.rotationDegrees(modelYaw));

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
            model2.renderToBuffer(poseStack, vertexConsumerOne, lightOne, OverlayTexture.NO_OVERLAY, colorOne);

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
                model2.renderToBuffer(poseStack, vertexConsumerTwo, lightTwo, OverlayTexture.NO_OVERLAY, colorTwo);
            }

            poseStack.popPose();
        } else if (stack.getItem() instanceof ShirtSilk shirtSilk) {
            Minecraft mc = Minecraft.getInstance();

            MultiBufferSource.BufferSource buffer = mc.renderBuffers().bufferSource();
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

            int lightOne = LightTexture.FULL_BRIGHT;
            int lightTwo = LightTexture.FULL_BRIGHT;

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

            poseStack.translate(this.leftPos + 88, this.topPos + 30, 50);

            poseStack.scale(36f, 36f, 36f);

            poseStack.mulPose(Axis.YP.rotationDegrees(modelYaw));

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
            model2.renderToBuffer(poseStack, vertexConsumerOne, lightOne, OverlayTexture.NO_OVERLAY, colorOne);

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
                model2.renderToBuffer(poseStack, vertexConsumerTwo, lightTwo, OverlayTexture.NO_OVERLAY, colorTwo);
            }

            poseStack.popPose();
        } else if (stack.getItem() instanceof SweaterWool sweaterWool) {
            Minecraft mc = Minecraft.getInstance();

            MultiBufferSource.BufferSource buffer = mc.renderBuffers().bufferSource();
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

            int lightOne = LightTexture.FULL_BRIGHT;
            int lightTwo = LightTexture.FULL_BRIGHT;

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

            poseStack.translate(this.leftPos + 88, this.topPos + 30, 50);

            poseStack.scale(36f, 36f, 36f);

            poseStack.mulPose(Axis.YP.rotationDegrees(modelYaw));

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
            model2.renderToBuffer(poseStack, vertexConsumerOne, lightOne, OverlayTexture.NO_OVERLAY, colorOne);

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
                model2.renderToBuffer(poseStack, vertexConsumerTwo, lightTwo, OverlayTexture.NO_OVERLAY, colorTwo);
            }

            poseStack.popPose();
        } else if (stack.getItem() instanceof HandWarmersCotton handWarmersCotton) {
            Minecraft mc = Minecraft.getInstance();

            MultiBufferSource.BufferSource buffer = mc.renderBuffers().bufferSource();
            int ticks = (int) mc.level.getGameTime();
            int primaryColorLeftOne = handWarmersCotton.getItemMainColor(stack, "left", 1);
            int secondaryColorLeftOne = handWarmersCotton.getItemSecondaryColor(stack, "left", 1);
            int primaryColorRightOne = handWarmersCotton.getItemMainColor(stack, "right", 1);
            int secondaryColorRightOne = handWarmersCotton.getItemSecondaryColor(stack, "right", 1);
            int primaryColorLeftTwo = handWarmersCotton.getItemMainColor(stack, "left", 2);
            int secondaryColorLeftTwo = handWarmersCotton.getItemSecondaryColor(stack, "left", 2);
            int primaryColorRightTwo = handWarmersCotton.getItemMainColor(stack, "right", 2);
            int secondaryColorRightTwo = handWarmersCotton.getItemSecondaryColor(stack, "right", 2);
            String dyeTypeLeftOne = handWarmersCotton.getItemDyeType(stack, "left", 1);
            String dyeTypeRightOne = handWarmersCotton.getItemDyeType(stack, "right", 1);
            String dyeTypeLeftTwo = handWarmersCotton.getItemDyeType(stack, "left", 2);
            String dyeTypeRightTwo = handWarmersCotton.getItemDyeType(stack, "right", 2);
            String stensilTypeLeft = handWarmersCotton.getStensilType(stack, "left");
            String stensilTypeRight = handWarmersCotton.getStensilType(stack, "right");
            int lightValueLeftOne = handWarmersCotton.getItemLightValue(stack, "left", 1);
            int lightValueLeftTwo = handWarmersCotton.getItemLightValue(stack, "left", 2);
            int lightValueRightOne = handWarmersCotton.getItemLightValue(stack, "right", 1);
            int lightValueRightTwo = handWarmersCotton.getItemLightValue(stack, "right", 2);

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

            int lightOne = LightTexture.FULL_BRIGHT;
            int lightTwo = LightTexture.FULL_BRIGHT;
            int lightThree = LightTexture.FULL_BRIGHT;
            int lightFour = LightTexture.FULL_BRIGHT;

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

            poseStack.translate(this.leftPos + 87, this.topPos, 50);

            poseStack.scale(48f, 48f, 48f);

            poseStack.mulPose(Axis.YP.rotationDegrees(modelYaw));

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
        } else if (stack.getItem() instanceof HandWarmersSilk handWarmersSilk) {
            Minecraft mc = Minecraft.getInstance();

            MultiBufferSource.BufferSource buffer = mc.renderBuffers().bufferSource();
            int ticks = (int) mc.level.getGameTime();
            int primaryColorLeftOne = handWarmersSilk.getItemMainColor(stack, "left", 1);
            int secondaryColorLeftOne = handWarmersSilk.getItemSecondaryColor(stack, "left", 1);
            int primaryColorRightOne = handWarmersSilk.getItemMainColor(stack, "right", 1);
            int secondaryColorRightOne = handWarmersSilk.getItemSecondaryColor(stack, "right", 1);
            int primaryColorLeftTwo = handWarmersSilk.getItemMainColor(stack, "left", 2);
            int secondaryColorLeftTwo = handWarmersSilk.getItemSecondaryColor(stack, "left", 2);
            int primaryColorRightTwo = handWarmersSilk.getItemMainColor(stack, "right", 2);
            int secondaryColorRightTwo = handWarmersSilk.getItemSecondaryColor(stack, "right", 2);
            String dyeTypeLeftOne = handWarmersSilk.getItemDyeType(stack, "left", 1);
            String dyeTypeRightOne = handWarmersSilk.getItemDyeType(stack, "right", 1);
            String dyeTypeLeftTwo = handWarmersSilk.getItemDyeType(stack, "left", 2);
            String dyeTypeRightTwo = handWarmersSilk.getItemDyeType(stack, "right", 2);
            String stensilTypeLeft = handWarmersSilk.getStensilType(stack, "left");
            String stensilTypeRight = handWarmersSilk.getStensilType(stack, "right");
            int lightValueLeftOne = handWarmersSilk.getItemLightValue(stack, "left", 1);
            int lightValueLeftTwo = handWarmersSilk.getItemLightValue(stack, "left", 2);
            int lightValueRightOne = handWarmersSilk.getItemLightValue(stack, "right", 1);
            int lightValueRightTwo = handWarmersSilk.getItemLightValue(stack, "right", 2);

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

            int lightOne = LightTexture.FULL_BRIGHT;
            int lightTwo = LightTexture.FULL_BRIGHT;
            int lightThree = LightTexture.FULL_BRIGHT;
            int lightFour = LightTexture.FULL_BRIGHT;

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

            poseStack.translate(this.leftPos + 87, this.topPos, 50);

            poseStack.scale(48f, 48f, 48f);

            poseStack.mulPose(Axis.YP.rotationDegrees(modelYaw));

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
        } else if (stack.getItem() instanceof HandWarmersWool handWarmersWool) {
            Minecraft mc = Minecraft.getInstance();

            MultiBufferSource.BufferSource buffer = mc.renderBuffers().bufferSource();
            int ticks = (int) mc.level.getGameTime();
            int primaryColorLeftOne = handWarmersWool.getItemMainColor(stack, "left", 1);
            int secondaryColorLeftOne = handWarmersWool.getItemSecondaryColor(stack, "left", 1);
            int primaryColorRightOne = handWarmersWool.getItemMainColor(stack, "right", 1);
            int secondaryColorRightOne = handWarmersWool.getItemSecondaryColor(stack, "right", 1);
            int primaryColorLeftTwo = handWarmersWool.getItemMainColor(stack, "left", 2);
            int secondaryColorLeftTwo = handWarmersWool.getItemSecondaryColor(stack, "left", 2);
            int primaryColorRightTwo = handWarmersWool.getItemMainColor(stack, "right", 2);
            int secondaryColorRightTwo = handWarmersWool.getItemSecondaryColor(stack, "right", 2);
            String dyeTypeLeftOne = handWarmersWool.getItemDyeType(stack, "left", 1);
            String dyeTypeRightOne = handWarmersWool.getItemDyeType(stack, "right", 1);
            String dyeTypeLeftTwo = handWarmersWool.getItemDyeType(stack, "left", 2);
            String dyeTypeRightTwo = handWarmersWool.getItemDyeType(stack, "right", 2);
            String stensilTypeLeft = handWarmersWool.getStensilType(stack, "left");
            String stensilTypeRight = handWarmersWool.getStensilType(stack, "right");
            int lightValueLeftOne = handWarmersWool.getItemLightValue(stack, "left", 1);
            int lightValueLeftTwo = handWarmersWool.getItemLightValue(stack, "left", 2);
            int lightValueRightOne = handWarmersWool.getItemLightValue(stack, "right", 1);
            int lightValueRightTwo = handWarmersWool.getItemLightValue(stack, "right", 2);

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

            int lightOne = LightTexture.FULL_BRIGHT;
            int lightTwo = LightTexture.FULL_BRIGHT;
            int lightThree = LightTexture.FULL_BRIGHT;
            int lightFour = LightTexture.FULL_BRIGHT;

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

            poseStack.translate(this.leftPos + 87, this.topPos, 50);

            poseStack.scale(48f, 48f, 48f);

            poseStack.mulPose(Axis.YP.rotationDegrees(modelYaw));

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

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int gx, int gy) {
        RenderSystem.setShaderColor(1, 1, 1, 1);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();

        guiGraphics.blit(ResourceLocation.parse("weaversparadise:textures/screens/dyeing_screen.png"), this.leftPos + -1, this.topPos + -7, 0, 0, 178, 227, 178, 227);

        NonNullList<Slot> slots = this.menu.slots;

        if (slots.get(0).getItem().is(WeaversParadiseItems.SHIRT_COTTON)) {
            guiGraphics.blit(
                    ResourceLocation.parse("weaversparadise:textures/screens/nope.png"),
                    this.leftPos + 126,
                    this.topPos + 87,
                    0,
                    0,
                    16,
                    16,
                    16,
                    16
            );

            guiGraphics.blit(
                    ResourceLocation.parse("weaversparadise:textures/screens/nope.png"),
                    this.leftPos + 148,
                    this.topPos + 87,
                    0,
                    0,
                    16,
                    16,
                    16,
                    16
            );

            guiGraphics.blit(
                    ResourceLocation.parse("weaversparadise:textures/screens/nope.png"),
                    this.leftPos + 137,
                    this.topPos + 66,
                    0,
                    0,
                    16,
                    16,
                    16,
                    16
            );
        }

        RenderSystem.disableBlend();
    }

    @Override
    public boolean keyPressed(int key, int b, int c) {
        if (key == 256) {
            this.minecraft.player.closeContainer();
            return true;
        }
        return super.keyPressed(key, b, c);
    }

    @Override
    protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
    }

    @Override
    public void containerTick() {
        stack = menu.slots.get(1).getItem();
    }

    @Override
    public void init() {
        super.init();
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double dragX, double dragY) {
        if (button == 0) {
            if (lastMouseX != -1) {
                double dx = mouseX - lastMouseX;
                modelYaw += dx * 0.8f;
            }
            lastMouseX = (int) mouseX;
            return true;
        }

        return super.mouseDragged(mouseX, mouseY, button, dragX, dragY);
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        if (button == 0) lastMouseX = -1;
        return super.mouseReleased(mouseX, mouseY, button);
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
