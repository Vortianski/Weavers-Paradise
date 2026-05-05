package xox.labvorty.weaversparadise.renderers.helpers;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import oshi.util.tuples.Pair;
import xox.labvorty.weaversparadise.data.rendering.GlobalRenderingData;

public class ColorHandlers {
    public static Pair<Integer, Integer> handle(String dyeType, int colorOne, int colorTwo, int lightValue, LivingEntity livingEntity, int packedLight, int ticks) {
        Minecraft minecraft = Minecraft.getInstance();

        int finalColor = colorOne;
        int finalLight = packedLight;

        if (dyeType.equals("rainbow")) {
            finalColor = getRainbowColor(ticks);
        }

        if (dyeType.equals("ender")) {
            finalLight = 0;
        }

        if (dyeType.equals("glowstone")) {
            finalLight = LightTexture.FULL_BRIGHT;
        }

        if (dyeType.equals("biome")) {
            BlockPos bpos = new BlockPos((int)livingEntity.getX(), (int)livingEntity.getY(), (int)livingEntity.getZ());
            int c = minecraft.level.getBiome(bpos).value().getGrassColor(livingEntity.getX(), livingEntity.getZ());
            c = 255 << 24 | ((c >> 16) & 0xFF) << 16 | ((c >> 8) & 0xFF) << 8 | (c & 0xFF);

            finalColor = c;
        }

        if (dyeType.equals("hunger")) {
            if (livingEntity instanceof Player player) {
                int hunger = player.getFoodData().getFoodLevel();
                int maxHunger = 20;
                float l = hunger / (float)maxHunger;

                int redOne = (colorOne >> 16) & 0xFF;
                int greenOne = (colorOne >> 8) & 0xFF;
                int blueOne = colorOne & 0xFF;

                int redTwo = (colorTwo >> 16) & 0xFF;
                int greenTwo = (colorTwo >> 8) & 0xFF;
                int blueTwo = colorTwo & 0xFF;

                int colorRed = Mth.lerpInt(l, redTwo, redOne);
                int colorGreen = Mth.lerpInt(l, greenTwo, greenOne);
                int colorBlue = Mth.lerpInt(l, blueTwo, blueOne);

                finalColor = 255 << 24 | colorRed << 16 | colorGreen << 8 | colorBlue;
            }
        }

        if (dyeType.equals("health")) {
            Player player = minecraft.player;
            if (player != null) {
                float health = player.getHealth();
                float maxHealth = player.getMaxHealth();
                float l = health / maxHealth;

                int redOne = (colorOne >> 16) & 0xFF;
                int greenOne = (colorOne >> 8) & 0xFF;
                int blueOne = colorOne & 0xFF;

                int redTwo = (colorTwo >> 16) & 0xFF;
                int greenTwo = (colorTwo >> 8) & 0xFF;
                int blueTwo = colorTwo & 0xFF;

                int colorRed = Mth.lerpInt(l, redTwo, redOne);
                int colorGreen = Mth.lerpInt(l, greenTwo, greenOne);
                int colorBlue = Mth.lerpInt(l, blueTwo, blueOne);

                finalColor = 255 << 24 | colorRed << 16 | colorGreen << 8 | colorBlue;
            }
        }

        if (dyeType.equals("day_time")) {
            int time = (int)minecraft.level.getDayTime() % 24000;

            float t;
            if (time >= 6000 && time <= 18000) {
                t = (time - 6000) / 12000.0f;
            } else {
                int wrappedTime = time < 6000 ? time + 24000 : time;
                t = 1.0f - ((wrappedTime - 18000) / 12000.0f);
            }

            finalColor = lerpColor(0xffdd40, 0x191970, t);
        }

        if (dyeType.equals("colored_day_time")) {
            int time = (int)minecraft.level.getDayTime() % 24000;

            float t;
            if (time >= 6000 && time <= 18000) {
                t = (time - 6000) / 12000.0f;
            } else {
                int wrappedTime = time < 6000 ? time + 24000 : time;
                t = 1.0f - ((wrappedTime - 18000) / 12000.0f);
            }

            finalColor = lerpColor(colorOne, colorTwo, t);
        }

        if (dyeType.equals("speed")) {
            Vec3 vec3 = livingEntity.getDeltaMovement();
            double velocity = vec3.lengthSqr();

            int redOne = (colorOne >> 16) & 0xFF;
            int greenOne = (colorOne >> 8) & 0xFF;
            int blueOne = colorOne & 0xFF;

            int redTwo = (colorTwo >> 16) & 0xFF;
            int greenTwo = (colorTwo >> 8) & 0xFF;
            int blueTwo = colorTwo & 0xFF;

            int finalRed = Mth.lerpInt((float)Mth.clamp(velocity / 0.029081503190380643, 0, 1), redOne, redTwo);
            int finalGreen = Mth.lerpInt((float)Mth.clamp(velocity / 0.029081503190380643, 0, 1), greenOne, greenTwo);
            int finalBlue = Mth.lerpInt((float)Mth.clamp(velocity / 0.029081503190380643, 0, 1), blueOne, blueTwo);

            finalColor = 255 << 24 | finalRed << 16 | finalGreen << 8 | finalBlue;
        }

        if (dyeType.equals("height_bedrock")) {
            int absoluteMinimum = minecraft.level.getMinBuildHeight();
            int absoluteMaximum = minecraft.level.getMaxBuildHeight();
            int height = (int)livingEntity.getY();

            float value = Mth.clamp(((float)height - (float)absoluteMinimum) / ((float)absoluteMaximum - (float)absoluteMinimum), 0, 1);

            int redOne = (colorOne >> 16) & 0xFF;
            int greenOne = (colorOne >> 8) & 0xFF;
            int blueOne = colorOne & 0xFF;

            int redTwo = (colorTwo >> 16) & 0xFF;
            int greenTwo = (colorTwo >> 8) & 0xFF;
            int blueTwo = colorTwo & 0xFF;

            int finalRed = Mth.lerpInt(value, redOne, redTwo);
            int finalGreen = Mth.lerpInt(value, greenOne, greenTwo);
            int finalBlue = Mth.lerpInt(value, blueOne, blueTwo);

            finalColor = 255 << 24 | finalRed << 16 | finalGreen << 8 | finalBlue;
        }

        if (dyeType.equals("height_sea")) {
            double minY = minecraft.level.getMinBuildHeight();
            double maxY = minecraft.level.getMaxBuildHeight();
            double seaY = minecraft.level.getSeaLevel();
            double playerY = livingEntity.getY();

            double distanceAbove = maxY - seaY;
            double distanceBelow = seaY - minY;
            double maxDistance = Math.max(distanceAbove, distanceBelow);
            float value = (float)Mth.clamp(Math.abs(playerY - seaY) / maxDistance, 0, 1);

            int redOne = (colorOne >> 16) & 0xFF;
            int greenOne = (colorOne >> 8) & 0xFF;
            int blueOne = colorOne & 0xFF;

            int redTwo = (colorTwo >> 16) & 0xFF;
            int greenTwo = (colorTwo >> 8) & 0xFF;
            int blueTwo = colorTwo & 0xFF;

            int finalRed = Mth.lerpInt(value, redOne, redTwo);
            int finalGreen = Mth.lerpInt(value, greenOne, greenTwo);
            int finalBlue = Mth.lerpInt(value, blueOne, blueTwo);

            finalColor = 255 << 24 | finalRed << 16 | finalGreen << 8 | finalBlue;
        }

        if (dyeType.equals("sculk")) {
            int redMain = 5;
            int greenMain = 38;
            int blueMain = 43;

            int redPulse = 0;
            int greenPulse = 255;
            int bluePulse = 255;

            int sculkPulse = GlobalRenderingData.getSculkPulse();
            int finalRed = Mth.lerpInt(sculkPulse / 60.0f, redMain, redPulse);
            int finalGreen = Mth.lerpInt(sculkPulse / 60.0f, greenMain, greenPulse);
            int finalBlue = Mth.lerpInt(sculkPulse / 60.0f, blueMain, bluePulse);

            finalColor = 255 << 24 | finalRed << 16 | finalGreen << 8 | finalBlue;
        }

        if (dyeType.equals("colored_sculk")) {
            int redMain = (colorOne >> 16) & 0xFF;
            int greenMain = (colorOne >> 8) & 0xFF;
            int blueMain = (colorOne) & 0xFF;

            int redPulse = (colorTwo >> 16) & 0xFF;
            int greenPulse = (colorTwo >> 8) & 0xFF;
            int bluePulse = (colorTwo) & 0xFF;

            int sculkPulse = GlobalRenderingData.getSculkPulse();
            int finalRed = Mth.lerpInt(sculkPulse / 60.0f, redMain, redPulse);
            int finalGreen = Mth.lerpInt(sculkPulse / 60.0f, greenMain, greenPulse);
            int finalBlue = Mth.lerpInt(sculkPulse / 60.0f, blueMain, bluePulse);

            finalColor = 255 << 24 | finalRed << 16 | finalGreen << 8 | finalBlue;
        }

        if (dyeType.equals("lamp")) {
            finalLight = lightValue << 20 | lightValue << 4;
        }

        if (dyeType.equals("invisible")) {
            finalColor = 0;
        }

        return new Pair<>(finalColor, finalLight);
    }

    public static int getRainbowColor(int ticks) {
        float speed = 0.05F;

        float red = Mth.clamp((float)(Math.sin(ticks * speed) * 0.5 + 0.5), 0, 1);
        float green = Mth.clamp((float)(Math.sin(ticks * speed + 2 * Math.PI / 3) * 0.5 + 0.5), 0, 1);
        float blue = Mth.clamp((float)(Math.sin(ticks * speed + 4 * Math.PI / 3) * 0.5 + 0.5), 0, 1);

        return 255 << 24 | (int)(red * 255) << 16 | (int)(green * 255) << 8 | (int)(blue * 255);
    }

    private static int lerpColor(int c1, int c2, float t) {
        int r1 = (c1 >> 16) & 0xFF;
        int g1 = (c1 >> 8) & 0xFF;
        int b1 = c1 & 0xFF;

        int r2 = (c2 >> 16) & 0xFF;
        int g2 = (c2 >> 8) & 0xFF;
        int b2 = c2 & 0xFF;

        int r = (int)(r1 + (r2 - r1) * t);
        int g = (int)(g1 + (g2 - g1) * t);
        int b = (int)(b1 + (b2 - b1) * t);

        return 255 << 24 | (r << 16) | (g << 8) | b;
    }
}
