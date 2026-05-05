package xox.labvorty.weaversparadise.data.tooltip_components.helper;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import xox.labvorty.weaversparadise.data.tooltip_components.data.DyeTextHandler;

import java.util.List;

public class DyeTooltipData {
    private static final int[] FIXED_SEQUENCE = {
            2, 3, 1, 4, 2, 4, 1, 3, 2, 4,
            3, 1, 2, 3, 4, 1, 2, 4, 3, 1,
            4, 2, 3, 1, 4, 3, 2, 1, 3, 4,
            2, 1, 3, 2, 4, 1, 3, 4, 2, 1,
            4, 3, 2, 4, 1, 3, 2, 1, 4, 3,
            1, 2, 4, 3, 1, 2, 3, 4, 1, 2,
            4, 3, 1, 2, 3, 4, 2, 1, 3, 4,
            2, 1, 4, 3, 2, 1, 3, 4, 2, 1,
            3, 2, 4, 1, 3, 2, 4, 3, 1, 2,
            4, 3, 1, 2, 3, 4, 1, 2, 3, 1
    };

    private static final List<Integer> prideColors = List.of(
            (0xE40303),
            (0xFF8C00),
            (0xFFED00),
            (0x008026),
            (0x004DFF),
            (0x750787)
    );

    private static final List<Integer> asexualColors = List.of(
            (0x2E2E2E),
            (0xA3A3A3),
            (0xFFFFFF),
            (0x800080)
    );

    private static final List<Integer> aromanticColors = List.of(
            (0x3DA542),
            (0xA7D379),
            (0xFFFFFF),
            (0xA9A9A9),
            (0x2E2E2E)
    );

    private static final List<Integer> aroaceColors = List.of(
            (0xE28C00),
            (0xFFD700),
            (0xFFFFFF),
            (0x00A4E0),
            (0x800080)
    );

    private static final List<Integer> agenderColors = List.of(
            (0x2E2E2E),
            (0xB9B9B9),
            (0xFFFFFF),
            (0xB8F483),
            (0xFFFFFF),
            (0xB9B9B9),
            (0x2E2E2E)
    );

    private static final List<Integer> bisexualColors = List.of(
            (0xD60270),
            (0xD60270),
            (0x9B4F96),
            (0x0038A8),
            (0x0038A8)
    );

    private static final List<Integer> demiboyColors = List.of(
            (0x2E2E2E),
            (0x7F7F7F),
            (0x9AD9EB),
            (0xFFFFFF),
            (0x9AD9EB),
            (0x7F7F7F),
            (0x2E2E2E)
    );

    private static final List<Integer> demigenderColors = List.of(
            (0x2E2E2E),
            (0x7F7F7F),
            (0xF9F06B),
            (0xFFFFFF),
            (0xF9F06B),
            (0x7F7F7F),
            (0x2E2E2E)
    );

    private static final List<Integer> demigirlColors = List.of(
            (0x2E2E2E),
            (0x7F7F7F),
            (0xFCACC9),
            (0xFFFFFF),
            (0xFCACC9),
            (0x7F7F7F),
            (0x2E2E2E)
    );

    private static final List<Integer> gayColors = List.of(
            (0x078D70),
            (0x26CEAA),
            (0x98E8C1),
            (0xFFFFFF),
            (0x7BADE3),
            (0x5049CC),
            (0x3D1A78)
    );

    private static final List<Integer> genderfluidColors = List.of(
            (0xFF75A2),
            (0xFFFFFF),
            (0xBE18D6),
            (0x2E2E2E),
            (0x333EBD)
    );

    private static final List<Integer> genderqueerColors = List.of(
            (0xB57EDC),
            (0xFFFFFF),
            (0x49822B)
    );

    private static final List<Integer> intersexColors = List.of(
            (0xFFD800),
            (0xFFD800),
            (0x7902AA),
            (0xFFD800),
            (0x7902AA),
            (0xFFD800),
            (0xFFD800)
    );

    private static final List<Integer> lesbianColors = List.of(
            (0xD62900),
            (0xFF9B55),
            (0xFFFFFF),
            (0xD461A6),
            (0xA50062)
    );

    private static final List<Integer> nonbinaryColors = List.of(
            (0xFFF430),
            (0xFFFFFF),
            (0x9C59D1),
            (0x2E2E2E)
    );

    private static final List<Integer> pansexualColors = List.of(
            (0xFF218C),
            (0xFFD800),
            (0x21B1FF)
    );

    private static final List<Integer> transColors = List.of(
            (0x5BCEFA),
            (0xF5A9B8),
            (0xFFFFFF),
            (0xF5A9B8),
            (0x5BCEFA)
    );

    public static MutableComponent parse(String s, String type, int ticks, int primaryColor, int secondaryColor, int lightValue) {
        MutableComponent component = Component.empty();
        Minecraft minecraft = Minecraft.getInstance();

        Component dyeText = DyeTextHandler.getOrDefault(s).getText();

        if (type.equals("default") || type.equals("crystal") || type.equals("invisible")) {
            component = dyeText.copy().withStyle(style -> style.withColor(primaryColor));
        } else if (type.equals("ender")) {
            component = dyeText.copy().withStyle(style -> style.withColor(0x2e2e2e));
        } else if (type.equals("rainbow")) {
            String val = dyeText.getString();

            for (int i = 0; i < val.length(); i++) {
                char c = val.charAt(i);
                int color = getRainbowColorFromTick(ticks + (3 * i));
                Component letter = Component.literal(String.valueOf(c)).withStyle(style -> style.withColor(color));
                component.append(letter);
            }
        } else if (type.equals("sculk")) {
            String val = dyeText.getString();
            int length = val.length();
            int middle = length / 2;

            int pulseInterval = 70;
            int pulseDuration = 20;
            int timeInCycle = ticks % pulseInterval;

            for (int i = 0; i < length; i++) {
                char c = val.charAt(i);

                int distFromMiddle = Math.abs(i - middle);
                int pulseStartTick = distFromMiddle * (pulseDuration / middle);
                int baseColor = 0x05262D;
                int pulseColor = 0x00FFFF;
                int finalColor;

                if (timeInCycle >= pulseStartTick && timeInCycle <= pulseStartTick + pulseDuration) {
                    float fade = 1.0f - (float)(timeInCycle - pulseStartTick) / pulseDuration;
                    finalColor = blendColors(pulseColor, baseColor, fade);
                } else {
                    finalColor = baseColor;
                }

                MutableComponent letter = Component.literal(String.valueOf(c)).withStyle(style -> style.withColor(finalColor));
                component.append(letter);
            }
        } else if (type.equals("colored_sculk")) {
            String val = dyeText.getString();
            int length = val.length();
            int middle = length / 2;

            int pulseInterval = 70;
            int pulseDuration = 20;
            int timeInCycle = ticks % pulseInterval;

            for (int i = 0; i < length; i++) {
                char c = val.charAt(i);

                int distFromMiddle = Math.abs(i - middle);
                int pulseStartTick = distFromMiddle * (pulseDuration / middle);
                int finalColor;

                if (timeInCycle >= pulseStartTick && timeInCycle <= pulseStartTick + pulseDuration) {
                    float fade = 1.0f - (float)(timeInCycle - pulseStartTick) / pulseDuration;
                    finalColor = blendColors(secondaryColor, primaryColor, fade);
                } else {
                    finalColor = primaryColor;
                }

                MutableComponent letter = Component.literal(String.valueOf(c)).withStyle(style -> style.withColor(finalColor));
                component.append(letter);
            }
        } else if (type.equals("biome")) {
            BlockPos bpos = new BlockPos((int)minecraft.player.getX(), (int)minecraft.player.getY(), (int)minecraft.player.getZ());
            int color = minecraft.level.getBiome(bpos).value().getGrassColor(minecraft.player.getX(), minecraft.player.getZ());

            component = dyeText.copy().withStyle(style -> style.withColor(color));
        } else if (type.equals("hunger")) {
            Player player = minecraft.player;
            int hunger = player.getFoodData().getFoodLevel();
            int maxHunger = 20;
            float l = hunger / (float)maxHunger;

            int redOne = (primaryColor >> 16) & 0xFF;
            int greenOne = (primaryColor >> 8) & 0xFF;
            int blueOne = primaryColor & 0xFF;

            int redTwo = (secondaryColor >> 16) & 0xFF;
            int greenTwo = (secondaryColor >> 8) & 0xFF;
            int blueTwo = secondaryColor & 0xFF;

            int colorRed = Mth.lerpInt(l, redTwo, redOne);
            int colorGreen = Mth.lerpInt(l, greenTwo, greenOne);
            int colorBlue = Mth.lerpInt(l, blueTwo, blueOne);

            component = dyeText.copy().withStyle(style -> style.withColor(colorRed << 16 | colorGreen << 8 | colorBlue));
        } else if (type.equals("health")) {
            Player player = minecraft.player;
            float health = 20;
            float maxHealth = 20;
            if (player != null) {
                health = player.getHealth();
                maxHealth = player.getMaxHealth();
            }
            float l = health / maxHealth;

            int redOne = (primaryColor >> 16) & 0xFF;
            int greenOne = (primaryColor >> 8) & 0xFF;
            int blueOne = primaryColor & 0xFF;

            int redTwo = (secondaryColor >> 16) & 0xFF;
            int greenTwo = (secondaryColor >> 8) & 0xFF;
            int blueTwo = secondaryColor & 0xFF;

            int colorRed = Mth.lerpInt(l, redTwo, redOne);
            int colorGreen = Mth.lerpInt(l, greenTwo, greenOne);
            int colorBlue = Mth.lerpInt(l, blueTwo, blueOne);

            component = dyeText.copy().withStyle(style -> style.withColor(colorRed << 16 | colorGreen << 8 | colorBlue));
        } else if (type.equals("day_time")) {
            int time = (int)minecraft.level.getDayTime() % 24000;

            float t;
            if (time >= 6000 && time <= 18000) {
                t = (time - 6000) / 12000.0f;
            } else {
                int wrappedTime = time < 6000 ? time + 24000 : time;
                t = 1.0f - ((wrappedTime - 18000) / 12000.0f);
            }

            int color = lerpColor(0xffdd40, 0x191970, t);

            component = dyeText.copy().withStyle(style -> style.withColor(color));
        } else if (type.equals("colored_day_time")) {
            int time = (int)minecraft.level.getDayTime() % 24000;

            float t;
            if (time >= 6000 && time <= 18000) {
                t = (time - 6000) / 12000.0f;
            } else {
                int wrappedTime = time < 6000 ? time + 24000 : time;
                t = 1.0f - ((wrappedTime - 18000) / 12000.0f);
            }

            int color = lerpColor(primaryColor, secondaryColor, t);

            component = dyeText.copy().withStyle(style -> style.withColor(color));
        } else if (type.equals("glowstone")) {
            String val = dyeText.getString();
            int baseRed = 181;
            int baseGreen = 113;
            int baseBlue = 66;
            int glowRed = 248;
            int glowGreen = 212;
            int glowBlue = 156;

            for (int i = 0; i < val.length(); i++) {
                char c = val.charAt(i);

                float t = (float)(Math.sin((ticks + FIXED_SEQUENCE[i]) * 0.5) + 1.0f) / 2.0f;
                int colorRed = Mth.lerpInt(t, baseRed, glowRed);
                int colorGreen = Mth.lerpInt(t, baseGreen, glowGreen);
                int colorBlue = Mth.lerpInt(t, baseBlue, glowBlue);

                int color = 255 << 24 | colorRed << 16 | colorGreen << 8 | colorBlue;

                component.append(Component.literal(String.valueOf(c)).withStyle(style -> style.withColor(color)));
            }
        } else if (type.equals("redstone")) {
            int minRed = 100;
            int maxRed = 255;

            int color = Mth.lerpInt(lightValue / 15.0f, minRed, maxRed);

            component = dyeText.copy().withStyle(style -> style.withColor(255 << 24 | color << 16 | 0 << 8 | 0));
        } else if (type.equals("lamp")) {
            int primaryRed = (primaryColor >> 16) & 0xFF;
            int primaryGreen = (primaryColor >> 8) & 0xFF;
            int primaryBlue = primaryColor & 0xFF;

            int lowestRed = (int)(primaryRed * 0.4);
            int lowestGreen = (int)(primaryGreen * 0.4);
            int lowestBlue = (int)(primaryBlue * 0.4);

            int finalRed = Mth.lerpInt(lightValue / 15.0f, lowestRed, primaryRed);
            int finalGreen = Mth.lerpInt(lightValue / 15.0f, lowestGreen, primaryGreen);
            int finalBlue = Mth.lerpInt(lightValue / 15.0f, lowestBlue, primaryBlue);

            int finalColor = 255 << 24 | finalRed << 16 | finalGreen << 8 | finalBlue;

            component = dyeText.copy().withStyle(style -> style.withColor(finalColor));
        } else if (type.equals("speed")) {
            Vec3 vec3 = minecraft.player.getDeltaMovement();
            double velocity = vec3.lengthSqr();

            int redOne = (primaryColor >> 16) & 0xFF;
            int greenOne = (primaryColor >> 8) & 0xFF;
            int blueOne = primaryColor & 0xFF;

            int redTwo = (secondaryColor >> 16) & 0xFF;
            int greenTwo = (secondaryColor >> 8) & 0xFF;
            int blueTwo = secondaryColor & 0xFF;

            int finalRed = Mth.lerpInt((float)Mth.clamp(velocity / 0.029081503190380643, 0, 1), redOne, redTwo);
            int finalGreen = Mth.lerpInt((float)Mth.clamp(velocity / 0.029081503190380643, 0, 1), greenOne, greenTwo);
            int finalBlue = Mth.lerpInt((float)Mth.clamp(velocity / 0.029081503190380643, 0, 1), blueOne, blueTwo);

            int finalColor = 255 << 24 | finalRed << 16 | finalGreen << 8 | finalBlue;

            component = dyeText.copy().withStyle(style -> style.withColor(finalColor));
        } else if (type.equals("height_bedrock")) {
            int absoluteMinimum = minecraft.level.getMinBuildHeight();
            int absoluteMaximum = minecraft.level.getMaxBuildHeight();
            int height = (int)minecraft.player.getY();

            float value = Mth.clamp(((float)height - (float)absoluteMinimum) / ((float)absoluteMaximum - (float)absoluteMinimum), 0, 1);

            int redOne = (primaryColor >> 16) & 0xFF;
            int greenOne = (primaryColor >> 8) & 0xFF;
            int blueOne = primaryColor & 0xFF;

            int redTwo = (secondaryColor >> 16) & 0xFF;
            int greenTwo = (secondaryColor >> 8) & 0xFF;
            int blueTwo = secondaryColor & 0xFF;

            int finalRed = Mth.lerpInt(value, redOne, redTwo);
            int finalGreen = Mth.lerpInt(value, greenOne, greenTwo);
            int finalBlue = Mth.lerpInt(value, blueOne, blueTwo);

            int finalColor = 255 << 24 | finalRed << 16 | finalGreen << 8 | finalBlue;

            component = dyeText.copy().withStyle(style -> style.withColor(finalColor));
        } else if (type.equals("height_sea")) {
            double minY = minecraft.level.getMinBuildHeight();
            double maxY = minecraft.level.getMaxBuildHeight();
            double seaY = minecraft.level.getSeaLevel();
            double playerY = minecraft.player.getY();

            double distanceAbove = maxY - seaY;
            double distanceBelow = seaY - minY;
            double maxDistance = Math.max(distanceAbove, distanceBelow);
            float value = (float)Mth.clamp(Math.abs(playerY - seaY) / maxDistance, 0, 1);

            int redOne = (primaryColor >> 16) & 0xFF;
            int greenOne = (primaryColor >> 8) & 0xFF;
            int blueOne = primaryColor & 0xFF;

            int redTwo = (secondaryColor >> 16) & 0xFF;
            int greenTwo = (secondaryColor >> 8) & 0xFF;
            int blueTwo = secondaryColor & 0xFF;

            int finalRed = Mth.lerpInt(value, redOne, redTwo);
            int finalGreen = Mth.lerpInt(value, greenOne, greenTwo);
            int finalBlue = Mth.lerpInt(value, blueOne, blueTwo);

            int finalColor = 255 << 24 | finalRed << 16 | finalGreen << 8 | finalBlue;

            component = dyeText.copy().withStyle(style -> style.withColor(finalColor));
        } else if (type.equals("pride")) {
            component = flagText(dyeText.getString(), prideColors, ticks);
        } else if (type.equals("asexual")) {
            component = flagText(dyeText.getString(), asexualColors, ticks);
        } else if (type.equals("aromantic")) {
            component = flagText(dyeText.getString(), aromanticColors, ticks);
        } else if (type.equals("aroace")) {
            component = flagText(dyeText.getString(), aroaceColors, ticks);
        } else if (type.equals("agender")) {
            component = flagText(dyeText.getString(), agenderColors, ticks);
        } else if (type.equals("bisexual")) {
            component = flagText(dyeText.getString(), bisexualColors, ticks);
        } else if (type.equals("demiboy")) {
            component = flagText(dyeText.getString(), demiboyColors, ticks);
        } else if (type.equals("demigender")) {
            component = flagText(dyeText.getString(), demigenderColors, ticks);
        } else if (type.equals("demigirl")) {
            component = flagText(dyeText.getString(), demigirlColors, ticks);
        } else if (type.equals("gay")) {
            component = flagText(dyeText.getString(), gayColors, ticks);
        } else if (type.equals("genderfluid")) {
            component = flagText(dyeText.getString(), genderfluidColors, ticks);
        } else if (type.equals("genderqueer")) {
            component = flagText(dyeText.getString(), genderqueerColors, ticks);
        } else if (type.equals("intersex")) {
            component = flagText(dyeText.getString(), intersexColors, ticks);
        } else if (type.equals("lesbian")) {
            component = flagText(dyeText.getString(), lesbianColors, ticks);
        } else if (type.equals("nonbinary")) {
            component = flagText(dyeText.getString(), nonbinaryColors, ticks);
        } else if (type.equals("pansexual")) {
            component = flagText(dyeText.getString(), pansexualColors, ticks);
        } else if (type.equals("trans")) {
            component = flagText(dyeText.getString(), transColors, ticks);
        } else if (type.equals("polychromatic")) {
            List<Integer> polychromisedColor = List.of(
                    primaryColor,
                    hueShift(primaryColor, 0.33f),
                    hueShift(primaryColor, 0.66f),
                    hueShift(primaryColor, 1)
            );

            component = flagText(dyeText.getString(), polychromisedColor, ticks);
        } else if (type.equals("static")) {
            String val = dyeText.getString();
            int baseRed = 255;
            int baseGreen = 255;
            int baseBlue = 255;
            int glowRed = 170;
            int glowGreen = 170;
            int glowBlue = 170;

            for (int i = 0; i < val.length(); i++) {
                char c = val.charAt(i);

                float t = (float)(Math.sin((ticks + FIXED_SEQUENCE[i]) * 0.5) + 1.0f) / 2.0f;
                int colorRed = Mth.lerpInt(t, baseRed, glowRed);
                int colorGreen = Mth.lerpInt(t, baseGreen, glowGreen);
                int colorBlue = Mth.lerpInt(t, baseBlue, glowBlue);

                int color = 255 << 24 | colorRed << 16 | colorGreen << 8 | colorBlue;

                component.append(Component.literal(String.valueOf(c)).withStyle(style -> style.withColor(color)));
            }
        } else if (type.equals("negative") || type.equals("true_negative")) {
            int primaryRed = (primaryColor >> 16) & 0xFF;
            int primaryGreen = (primaryColor >> 8) & 0xFF;
            int primaryBlue = primaryColor & 0xFF;

            List<Integer> colors = List.of(
                    primaryColor,
                    (255 << 24 | (255 - primaryRed) << 16 | (255 - primaryGreen) << 8 | (255 - primaryBlue))
            );

            component = flagText(dyeText.getString(), colors, ticks);
        } else if (type.equals("nebula")) {
            List<Integer> colors = List.of(
                    0x26103F,
                    0x26103F,
                    0xE673B2,
                    0x4CCCCD,
                    0x8C40A6
            );

            component = flagText(dyeText.getString(), colors, ticks);
        }

        return component;
    }

    public static int getRainbowColorFromTick(int ticks) {
        float speed = 0.05F;

        float red = Mth.clamp((float)(Math.sin(ticks * speed) * 0.5 + 0.5), 0, 1);
        float green = Mth.clamp((float)(Math.sin(ticks * speed + 2 * Math.PI / 3) * 0.5 + 0.5), 0, 1);
        float blue = Mth.clamp((float)(Math.sin(ticks * speed + 4 * Math.PI / 3) * 0.5 + 0.5), 0, 1);

        int truered = (int)(red * 255);
        int truegreen = (int)(green * 255);
        int trueblue = (int)(blue * 255);

        return (truered << 16) | (truegreen << 8) | trueblue;
    }

    public static MutableComponent flagText(String text, List<Integer> colors, int ticks) {
        MutableComponent component = Component.literal("");

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            int color = getCycledColor(colors, ticks + (3 * i));
            MutableComponent letter = Component.literal(String.valueOf(c)).withStyle(style -> style.withColor(color));
            component.append(letter);
        }

        return component;
    }

    public static int getCycledColor(List<Integer> colors, int ticks) {
        if (colors == null || colors.isEmpty()) return 0xFFFFFF;
        if (colors.size() == 1) return colors.get(0);

        int holdTime = 20;
        int fadeTime = 20;
        int segmentTime = holdTime + fadeTime;
        int totalCycle = colors.size() * segmentTime;

        int time = ticks % totalCycle;
        int index = time / segmentTime;
        int local = time % segmentTime;

        int current = colors.get(index);
        int next = colors.get((index + 1) % colors.size());

        float t = 0f;
        if (local >= holdTime) {
            t = (local - holdTime) / (float) fadeTime;
        }

        return lerpColor(current, next, t);
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

    public static int blendColors(int c1, int c2, float ratio) {
        ratio = Math.min(1.0f, Math.max(0.0f, ratio));

        int r1 = (c1 >> 16) & 0xFF;
        int g1 = (c1 >> 8) & 0xFF;
        int b1 = c1 & 0xFF;

        int r2 = (c2 >> 16) & 0xFF;
        int g2 = (c2 >> 8) & 0xFF;
        int b2 = c2 & 0xFF;

        int r = (int)(r1 * ratio + r2 * (1 - ratio));
        int g = (int)(g1 * ratio + g2 * (1 - ratio));
        int b = (int)(b1 * ratio + b2 * (1 - ratio));

        return 255 << 24 | (r << 16) | (g << 8) | b;
    }

    public static int hueShift(int color, float hueShift) {
        int a = (color >> 24) & 0xFF;
        int r = (color >> 16) & 0xFF;
        int g = (color >> 8) & 0xFF;
        int b = color & 0xFF;

        float[] hsb = java.awt.Color.RGBtoHSB(r, g, b, null);
        hsb[0] = (hsb[0] + hueShift) % 1.0f;
        if (hsb[0] < 0) hsb[0] += 1.0f;
        int rgb = java.awt.Color.HSBtoRGB(hsb[0], hsb[1], hsb[2]);


        return (a << 24) | (rgb & 0x00FFFFFF);
    }
}
