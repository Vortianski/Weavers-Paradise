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
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.client.ICurioRenderer;
import xox.labvorty.weaversparadise.curios.WeaversParadiseMobLayers;
import xox.labvorty.weaversparadise.data.WeaversParadiseHandWarmersTextureHandler;
import xox.labvorty.weaversparadise.data.WeaversParadiseThighHighsTextureHandler;
import xox.labvorty.weaversparadise.items.HandWarmersCotton;
import xox.labvorty.weaversparadise.items.ThighHighsCotton;
import xox.labvorty.weaversparadise.model.ModelHandWarmers;
import xox.labvorty.weaversparadise.model.ThighHighsModel;
import xox.labvorty.weaversparadise.renderers.WeaversParadiseGlobalRendererDataHolder;
import xox.labvorty.weaversparadise.renderers.WeaversParadiseRenderTypes;

public class HandWarmersCottonRenderer implements ICurioRenderer {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath("weaversparadise", "textures/clothing/hand_warmers_base.png");
    private final ModelHandWarmers model;
    private final ModelHandWarmers model1;

    public HandWarmersCottonRenderer() {
        this.model = new ModelHandWarmers(Minecraft.getInstance().getEntityModels().bakeLayer(WeaversParadiseMobLayers.HAND_WARMERS));
        this.model1 = new ModelHandWarmers(Minecraft.getInstance().getEntityModels().bakeLayer(WeaversParadiseMobLayers.HAND_WARMERS));
    }

    @Override
    public <T extends LivingEntity, M extends EntityModel<T>> void render(ItemStack stack, SlotContext slotContext, PoseStack matrixStack, RenderLayerParent<T, M> renderLayerParent, MultiBufferSource renderTypeBuffer, int light, float limbSwing,
                                                                          float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (stack.getItem() instanceof HandWarmersCotton handWarmersCotton) {
            Minecraft mc = Minecraft.getInstance();
            int ticks = (int)mc.level.getGameTime();
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

            String renderTypeLeft = WeaversParadiseHandWarmersTextureHandler.getByTypeAndMaterial(stensilTypeLeft, "cotton").getRenderType();
            ResourceLocation tex1left = WeaversParadiseHandWarmersTextureHandler.getByTypeAndMaterial(stensilTypeLeft, "cotton").getTextureOne();
            ResourceLocation tex2left = WeaversParadiseHandWarmersTextureHandler.getByTypeAndMaterial(stensilTypeLeft, "cotton").getTextureTwo();

            String renderTypeRight = WeaversParadiseHandWarmersTextureHandler.getByTypeAndMaterial(stensilTypeRight, "cotton").getRenderType();
            ResourceLocation tex1right = WeaversParadiseHandWarmersTextureHandler.getByTypeAndMaterial(stensilTypeRight, "cotton").getTextureOne();
            ResourceLocation tex2right = WeaversParadiseHandWarmersTextureHandler.getByTypeAndMaterial(stensilTypeRight, "cotton").getTextureTwo();

            LivingEntity entity = slotContext.entity();
            this.model.prepareMobModel(entity, limbSwing, limbSwingAmount, partialTicks);
            this.model.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

            M playerModel = renderLayerParent.getModel();
            if (playerModel instanceof HumanoidModel<?>) {
                HumanoidModel<?> humModel = (HumanoidModel<?>)playerModel;

                this.model.RightArm.copyFrom(humModel.rightArm);
                this.model.LeftArm.copyFrom(humModel.leftArm);
                this.model1.RightArm.copyFrom(humModel.rightArm);
                this.model1.LeftArm.copyFrom(humModel.leftArm);
            }

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

            int lightOne = light;
            int lightTwo = light;
            int lightThree = light;
            int lightFour = light;

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

            VertexConsumer consumerOneLeft = renderTypeBuffer.getBuffer(RenderType.entityTranslucent(tex1left)/**RenderType.armorCutoutNoCull(tex1left)**/);
            if (dyeTypeLeftOne.equals("ender")) {
                consumerOneLeft = renderTypeBuffer.getBuffer(
                        WeaversParadiseRenderTypes.getVoidArmor(
                                TheEndPortalRenderer.END_SKY_LOCATION,
                                tex1left,
                                TheEndPortalRenderer.END_PORTAL_LOCATION
                        )
                );
            }
            this.model.LeftArm.render(matrixStack, consumerOneLeft, lightOne, OverlayTexture.NO_OVERLAY, colorLeftOne);

            VertexConsumer consumerOneRight = renderTypeBuffer.getBuffer(RenderType.entityTranslucent(tex1right)/**RenderType.armorCutoutNoCull(tex1right)**/);
            if (dyeTypeRightOne.equals("ender")) {
                consumerOneRight = renderTypeBuffer.getBuffer(
                        WeaversParadiseRenderTypes.getVoidArmor(
                                TheEndPortalRenderer.END_SKY_LOCATION,
                                tex1right,
                                TheEndPortalRenderer.END_PORTAL_LOCATION
                        )
                );
            }
            this.model.RightArm.render(matrixStack, consumerOneRight, lightThree, OverlayTexture.NO_OVERLAY, colorRightOne);

            if (renderTypeLeft.equals("double")) {
                VertexConsumer consumerTwoLeft = renderTypeBuffer.getBuffer(RenderType.entityTranslucent(tex2left)/**RenderType.armorCutoutNoCull(tex2left)**/);
                if (dyeTypeLeftTwo.equals("ender")) {
                    consumerTwoLeft = renderTypeBuffer.getBuffer(
                            WeaversParadiseRenderTypes.getVoidArmor(
                                    TheEndPortalRenderer.END_SKY_LOCATION,
                                    tex2left,
                                    TheEndPortalRenderer.END_PORTAL_LOCATION
                            )
                    );
                }
                this.model1.LeftArm.render(matrixStack, consumerTwoLeft, lightTwo, OverlayTexture.NO_OVERLAY, colorLeftTwo);
            }

            if (renderTypeRight.equals("double")) {
                VertexConsumer consumerTwoRight = renderTypeBuffer.getBuffer(RenderType.entityTranslucent(tex2right)/**RenderType.armorCutoutNoCull(tex2right)**/);
                if (dyeTypeRightTwo.equals("ender")) {
                    consumerTwoRight = renderTypeBuffer.getBuffer(
                            WeaversParadiseRenderTypes.getVoidArmor(
                                    TheEndPortalRenderer.END_SKY_LOCATION,
                                    tex2right,
                                    TheEndPortalRenderer.END_PORTAL_LOCATION
                            )
                    );
                }
                this.model1.RightArm.render(matrixStack, consumerTwoRight, lightFour, OverlayTexture.NO_OVERLAY, colorRightTwo);
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
