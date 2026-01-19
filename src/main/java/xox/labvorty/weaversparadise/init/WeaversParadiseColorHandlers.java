package xox.labvorty.weaversparadise.init;

import net.minecraft.client.Minecraft;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.TextColor;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Mth;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.phys.Vec3;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import xox.labvorty.weaversparadise.WeaversParadise;
import xox.labvorty.weaversparadise.init.WeaversParadiseItems;
import xox.labvorty.weaversparadise.items.BottledDyeItem;
import xox.labvorty.weaversparadise.items.ChromaticBloomFruit;
import xox.labvorty.weaversparadise.items.PureDyeItem;

import java.util.List;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public class WeaversParadiseColorHandlers {
    private static final List<String> dyeTypes = List.of(
            "agender",
            "aroace",
            "aromantic",
            "asexual",
            "bisexual",
            "demiboy",
            "demigender",
            "demigirl",
            "gay",
            "genderfluid",
            "genderqueer",
            "intersex",
            "lesbian",
            "nonbinary",
            "pansexual",
            "pride",
            "trans"
    );
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

    @SubscribeEvent
    public static void itemHandlers(RegisterColorHandlersEvent.Item event) {
        event.register((stack, layer) -> {
            if (layer == 0) {
                return -1;
            }
            if (stack.getItem() instanceof BottledDyeItem dye) {
                CompoundTag compoundTag = stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag();

                if (compoundTag.getString("dyeType").equals("redstone")) {
                    int lightValue = compoundTag.getInt("lightValue");

                    int minRed = 100;
                    int maxRed = 255;

                    int color = Mth.lerpInt(lightValue / 15.0f, minRed, maxRed);
                    return 255 << 24 | color << 16 | 0 << 8 | 0;
                }

                if (compoundTag.getString("dyeType").equals("rainbow")) {
                    Minecraft mc = Minecraft.getInstance();
                    int ticks = (int)mc.level.getGameTime();

                    float speed = 0.05F;

                    float red = Mth.clamp((float)(Math.sin(ticks * speed) * 0.5 + 0.5), 0, 1);
                    float green = Mth.clamp((float)(Math.sin(ticks * speed + 2 * Math.PI / 3) * 0.5 + 0.5), 0, 1);
                    float blue = Mth.clamp((float)(Math.sin(ticks * speed + 4 * Math.PI / 3) * 0.5 + 0.5), 0, 1);

                    int truered = (int)(red * 255);
                    int truegreen = (int)(green * 255);
                    int trueblue = (int)(blue * 255);

                    return 255 << 24 | truered << 16 | truegreen << 8 | trueblue;
                }

                if (compoundTag.getString("dyeType").equals("ender")) {
                    return 255 << 24 | 0 << 16 | 0 << 8 | 0;
                }

                if (compoundTag.getString("dyeType").equals("speed")) {
                    Minecraft mc = Minecraft.getInstance();
                    Vec3 vec3 = mc.player.getDeltaMovement();
                    double velocity = vec3.lengthSqr();

                    int colorOne = dye.getItemMainColor(stack);
                    int colorTwo = dye.getItemSecondaryColor(stack);

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

                    return finalColor;
                }

                if (compoundTag.getString("dyeType").equals("height_bedrock")) {
                    Minecraft mc = Minecraft.getInstance();
                    int absoluteMinimum = mc.level.getMinBuildHeight();
                    int absoluteMaximum = mc.level.getMaxBuildHeight();
                    int height = (int)mc.player.getY();

                    float value = Mth.clamp(((float)height - (float)absoluteMinimum) / ((float)absoluteMaximum - (float)absoluteMinimum), 0, 1);

                    int colorOne = dye.getItemMainColor(stack);
                    int colorTwo = dye.getItemSecondaryColor(stack);

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

                    return finalColor;
                }

                if (compoundTag.getString("dyeType").equals("height_sea")) {
                    Minecraft mc = Minecraft.getInstance();

                    double minY = mc.level.getMinBuildHeight();
                    double maxY = mc.level.getMaxBuildHeight();
                    double seaY = mc.level.getSeaLevel();
                    double playerY = mc.player.getY();

                    double distanceAbove = maxY - seaY;
                    double distanceBelow = seaY - minY;
                    double maxDistance = Math.max(distanceAbove, distanceBelow);
                    float value = (float)Mth.clamp(Math.abs(playerY - seaY) / maxDistance, 0, 1);

                    int colorOne = dye.getItemMainColor(stack);
                    int colorTwo = dye.getItemSecondaryColor(stack);

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
                    return finalColor;
                }

                if (compoundTag.getString("dyeType").equals("sculk")) {
                    Minecraft mc = Minecraft.getInstance();
                    int ticks = (int)mc.level.getGameTime();
                    int pulseInterval = 70;
                    int pulseDuration = 20;
                    int timeInCycle = ticks % pulseInterval;

                    int pulseStartTick = 0;

                    int baseColor = 0xFF05262D;
                    int pulseColorValue = 0xFF00FFFF;

                    if (timeInCycle >= pulseStartTick && timeInCycle <= pulseStartTick + pulseDuration) {
                        float fade = 1.0f - (float)(timeInCycle - pulseStartTick) / pulseDuration;

                        return blendColors(pulseColorValue, baseColor, fade);
                    } else {
                        return baseColor;
                    }
                }

                if (compoundTag.getString("dyeType").equals("lamp")) {
                    int colorOne = dye.getItemMainColor(stack);
                    int lightValue = dye.getItemLightValue(stack);

                    int redOne = (colorOne >> 16) & 0xFF;
                    int greenOne = (colorOne >> 8) & 0xFF;
                    int blueOne = colorOne & 0xFF;

                    int lowestRed = (int)(redOne * 0.4);
                    int lowestGreen = (int)(greenOne * 0.4);
                    int lowestBlue = (int)(blueOne * 0.4);

                    int finalRed = Mth.lerpInt(lightValue / 15.0f, lowestRed, redOne);
                    int finalGreen = Mth.lerpInt(lightValue / 15.0f, lowestGreen, greenOne);
                    int finalBlue = Mth.lerpInt(lightValue / 15.0f, lowestBlue, blueOne);

                    int finalColor = 255 << 24 | finalRed << 16 | finalGreen << 8 | finalBlue;

                    return finalColor;
                }

                if (compoundTag.getString("dyeType").equals("pride")) {
                    Minecraft mc = Minecraft.getInstance();
                    int ticks = (int)mc.level.getGameTime();
                    return getCycledColor(prideColors, ticks);
                }

                if (compoundTag.getString("dyeType").equals("asexual")) {
                    Minecraft mc = Minecraft.getInstance();
                    int ticks = (int)mc.level.getGameTime();
                    return getCycledColor(asexualColors, ticks);
                }

                if (compoundTag.getString("dyeType").equals("aromantic")) {
                    Minecraft mc = Minecraft.getInstance();
                    int ticks = (int)mc.level.getGameTime();
                    return getCycledColor(aromanticColors, ticks);
                }

                if (compoundTag.getString("dyeType").equals("aroace")) {
                    Minecraft mc = Minecraft.getInstance();
                    int ticks = (int)mc.level.getGameTime();
                    return getCycledColor(aroaceColors, ticks);
                }

                if (compoundTag.getString("dyeType").equals("agender")) {
                    Minecraft mc = Minecraft.getInstance();
                    int ticks = (int)mc.level.getGameTime();
                    return getCycledColor(agenderColors, ticks);
                }

                if (compoundTag.getString("dyeType").equals("bisexual")) {
                    Minecraft mc = Minecraft.getInstance();
                    int ticks = (int)mc.level.getGameTime();
                    return getCycledColor(bisexualColors, ticks);
                }

                if (compoundTag.getString("dyeType").equals("demiboy")) {
                    Minecraft mc = Minecraft.getInstance();
                    int ticks = (int)mc.level.getGameTime();
                    return getCycledColor(demiboyColors, ticks);
                }

                if (compoundTag.getString("dyeType").equals("demigender")) {
                    Minecraft mc = Minecraft.getInstance();
                    int ticks = (int)mc.level.getGameTime();
                    return getCycledColor(demigenderColors, ticks);
                }

                if (compoundTag.getString("dyeType").equals("demigirl")) {
                    Minecraft mc = Minecraft.getInstance();
                    int ticks = (int)mc.level.getGameTime();
                    return getCycledColor(demigirlColors, ticks);
                }

                if (compoundTag.getString("dyeType").equals("gay")) {
                    Minecraft mc = Minecraft.getInstance();
                    int ticks = (int)mc.level.getGameTime();
                    return getCycledColor(gayColors, ticks);
                }

                if (compoundTag.getString("dyeType").equals("genderfluid")) {
                    Minecraft mc = Minecraft.getInstance();
                    int ticks = (int)mc.level.getGameTime();
                    return getCycledColor(genderfluidColors, ticks);
                }

                if (compoundTag.getString("dyeType").equals("genderqueer")) {
                    Minecraft mc = Minecraft.getInstance();
                    int ticks = (int)mc.level.getGameTime();
                    return getCycledColor(genderqueerColors, ticks);
                }

                if (compoundTag.getString("dyeType").equals("intersex")) {
                    Minecraft mc = Minecraft.getInstance();
                    int ticks = (int)mc.level.getGameTime();
                    return getCycledColor(intersexColors, ticks);
                }

                if (compoundTag.getString("dyeType").equals("lesbian")) {
                    Minecraft mc = Minecraft.getInstance();
                    int ticks = (int)mc.level.getGameTime();
                    return getCycledColor(lesbianColors, ticks);
                }

                if (compoundTag.getString("dyeType").equals("nonbinary")) {
                    Minecraft mc = Minecraft.getInstance();
                    int ticks = (int)mc.level.getGameTime();
                    return getCycledColor(nonbinaryColors, ticks);
                }

                if (compoundTag.getString("dyeType").equals("pansexual")) {
                    Minecraft mc = Minecraft.getInstance();
                    int ticks = (int)mc.level.getGameTime();
                    return getCycledColor(pansexualColors, ticks);
                }

                if (compoundTag.getString("dyeType").equals("trans")) {
                    Minecraft mc = Minecraft.getInstance();
                    int ticks = (int)mc.level.getGameTime();
                    return getCycledColor(transColors, ticks);
                }

                return dye.getItemMainColor(stack);
            }

            return -1;
        }, WeaversParadiseItems.BOTTLED_DYE.get());

        event.register((stack, layer) -> {
            if (layer == 0) {
                return -1;
            }

            if (stack.getItem() instanceof ChromaticBloomFruit bloomFruit) {
                Minecraft mc = Minecraft.getInstance();
                int ticks = (int)mc.level.getGameTime() + (layer * 2);

                float speed = 0.05F;

                float red = Mth.clamp((float)(Math.sin(ticks * speed) * 0.5 + 0.5), 0, 1);
                float green = Mth.clamp((float)(Math.sin(ticks * speed + 2 * Math.PI / 3) * 0.5 + 0.5), 0, 1);
                float blue = Mth.clamp((float)(Math.sin(ticks * speed + 4 * Math.PI / 3) * 0.5 + 0.5), 0, 1);

                int truered = (int)(red * 255);
                int truegreen = (int)(green * 255);
                int trueblue = (int)(blue * 255);

                return 255 << 24 | truered << 16 | truegreen << 8 | trueblue;
            }

            return -1;
        }, WeaversParadiseItems.CHROMATIC_BLOOM_FRUIT);

        event.register((stack, layer) -> {
            if (stack.getItem() instanceof PureDyeItem pureDyeItem) {
                return pureDyeItem.getDyeColor(stack);
            }

            return -1;
        }, WeaversParadiseItems.PURE_DYE);
    }


    public static int blendColors(int c1, int c2, float ratio) {
        // Clamp ratio between 0 and 1
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
}
