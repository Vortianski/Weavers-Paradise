package xox.labvorty.weaversparadise.data.tooltip_components.helper;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.blockentity.TheEndPortalRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.item.Items;
import net.minecraft.world.phys.Vec3;
import xox.labvorty.weaversparadise.data.tooltip_components.DyeIcon;
import xox.labvorty.weaversparadise.data.tooltip_components.DyeTypeRegistry;
import xox.labvorty.weaversparadise.data.tooltip_components.ItemDyeIcon;
import xox.labvorty.weaversparadise.init.WeaversParadiseItems;
import xox.labvorty.weaversparadise.init.WeaversParadiseRenderTypes;

import java.util.List;

public class BuiltInDyeTypes {
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

    public static void register() {
        DyeTypeRegistry.registerDyeType(
                "rainbow",
                new ItemDyeIcon(WeaversParadiseItems.CHROMATIC_BLOOM_FRUIT.get()),
                Component.translatable("weaversparadise.dye_text.rainbow"),
                (dyeData) -> {
                    Minecraft minecraft = Minecraft.getInstance();
                    int ticks = 0;

                    if (minecraft.level != null) {
                        ticks = (int)minecraft.level.getGameTime();
                    }

                    MutableComponent mutableComponent = Component.empty();

                    String val = dyeData.getComponent().getString();

                    for (int i = 0; i < val.length(); i++) {
                        char c = val.charAt(i);
                        int color = getRainbowColorFromTick(ticks + (3 * i));
                        Component letter = Component.literal(String.valueOf(c)).withStyle(style -> style.withColor(color));
                        mutableComponent.append(letter);
                    }

                    return mutableComponent;
                },
                (dyeDataColor) -> {
                    if (dyeDataColor.layer() == 1) {
                        Minecraft minecraft = Minecraft.getInstance();

                        int ticks = 0;
                        if (minecraft.level != null) {
                            ticks = (int)minecraft.level.getGameTime();
                        }

                        float speed = 0.05F;

                        float red = Mth.clamp((float)(Math.sin(ticks * speed) * 0.5 + 0.5), 0, 1);
                        float green = Mth.clamp((float)(Math.sin(ticks * speed + 2 * Math.PI / 3) * 0.5 + 0.5), 0, 1);
                        float blue = Mth.clamp((float)(Math.sin(ticks * speed + 4 * Math.PI / 3) * 0.5 + 0.5), 0, 1);

                        int tRed = (int)(red * 255);
                        int tGreen = (int)(green * 255);
                        int tBlue = (int)(blue * 255);

                        return 255 << 24 | tRed << 16 | tGreen << 8 | tBlue;
                    }

                    return -1;
                }
        );

        DyeTypeRegistry.registerDyeType(
                "ender",
                new ItemDyeIcon(Items.ENDER_EYE),
                Component.translatable("weaversparadise.dye_text.ender"),
                (dyeData) -> {
                    return dyeData.getComponent().copy().withStyle(style -> style.withColor(0x2e2e2e));
                },
                (dyeDataColor) -> {
                    if (dyeDataColor.layer() == 1) {
                        return 255 << 24;
                    }

                    return -1;
                },
                (renderData) -> {
                    return WeaversParadiseRenderTypes.getEndEntity(
                            TheEndPortalRenderer.END_SKY_LOCATION,
                            renderData.resourceLocation(),
                            TheEndPortalRenderer.END_PORTAL_LOCATION
                    );
                }
        );

        DyeTypeRegistry.registerDyeType(
                "sculk",
                new ItemDyeIcon(Items.SCULK),
                Component.translatable("weaversparadise.dye_text.sculk"),
                (dyeData) -> {
                    Minecraft minecraft = Minecraft.getInstance();
                    int ticks = 0;

                    if (minecraft.level != null) {
                        ticks = (int)minecraft.level.getGameTime();
                    }

                    String val = dyeData.getComponent().getString();
                    int length = val.length();
                    int middle = length / 2;

                    int pulseInterval = 70;
                    int pulseDuration = 20;
                    int timeInCycle = ticks % pulseInterval;

                    MutableComponent mutableComponent = Component.empty();

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
                        mutableComponent.append(letter);
                    }

                    return mutableComponent;
                },
                (dyeDataColor) -> {
                    if (dyeDataColor.layer() == 1) {
                        Minecraft minecraft = Minecraft.getInstance();

                        int ticks = 0;
                        if (minecraft.level != null) {
                            ticks = (int)minecraft.level.getGameTime();
                        }

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

                    return -1;
                }
        );

        DyeTypeRegistry.registerDyeType(
                "biome",
                new ItemDyeIcon(Items.MOSS_BLOCK),
                Component.translatable("weaversparadise.dye_text.biome"),
                (dyeData) -> {
                    Minecraft minecraft = Minecraft.getInstance();
                    final int color;
                    if (minecraft.player != null && minecraft.level != null) {
                        BlockPos blockPos = new BlockPos((int)minecraft.player.getX(), (int)minecraft.player.getY(), (int)minecraft.player.getZ());
                        color = minecraft.level.getBiome(blockPos).value().getGrassColor(minecraft.player.getX(), minecraft.player.getZ());
                    } else {
                        color = 0xFFFFFF;
                    }

                    return dyeData.getComponent().copy().withStyle(style -> style.withColor(color));
                },
                (dyeDataColor) -> {
                    if (dyeDataColor.layer() == 1) {
                        Minecraft minecraft = Minecraft.getInstance();
                        int color = 0xFFFFFF;

                        if (minecraft.player != null && minecraft.level != null) {
                            BlockPos blockPos = new BlockPos((int)minecraft.player.getX(), (int)minecraft.player.getY(), (int)minecraft.player.getZ());
                            color = minecraft.level.getBiome(blockPos).value().getGrassColor(minecraft.player.getX(), minecraft.player.getZ());
                        }

                        return 255 << 24 | color;
                    }

                    return -1;
                }
        );

        DyeTypeRegistry.registerDyeType(
                "glowstone",
                new ItemDyeIcon(Items.GLOWSTONE),
                Component.translatable("weaversparadise.dye_text.glowstone"),
                (dyeData) -> {
                    Minecraft minecraft = Minecraft.getInstance();
                    int ticks = 0;

                    if (minecraft.level != null) {
                        ticks = (int)minecraft.level.getGameTime();
                    }

                    String val = dyeData.getComponent().getString();
                    int baseRed = 181;
                    int baseGreen = 113;
                    int baseBlue = 66;
                    int glowRed = 248;
                    int glowGreen = 212;
                    int glowBlue = 156;

                    MutableComponent mutableComponent = Component.empty();

                    for (int i = 0; i < val.length(); i++) {
                        char c = val.charAt(i);

                        float t = (float)(Math.sin((ticks + FIXED_SEQUENCE[i]) * 0.5) + 1.0f) / 2.0f;
                        int colorRed = Mth.lerpInt(t, baseRed, glowRed);
                        int colorGreen = Mth.lerpInt(t, baseGreen, glowGreen);
                        int colorBlue = Mth.lerpInt(t, baseBlue, glowBlue);

                        int color = 255 << 24 | colorRed << 16 | colorGreen << 8 | colorBlue;

                        mutableComponent.append(Component.literal(String.valueOf(c)).withStyle(style -> style.withColor(color)));
                    }

                    return mutableComponent;
                },
                (dyeDataColor) -> {
                    if (dyeDataColor.layer() == 1) {
                        return dyeDataColor.colorOne();
                    }

                    return -1;
                }
        );

        DyeTypeRegistry.registerDyeType(
                "redstone",
                new ItemDyeIcon(Items.REDSTONE),
                Component.translatable("weaversparadise.dye_text.redstone"),
                (dyeData) -> {
                    int minRed = 100;
                    int maxRed = 255;

                    int color = Mth.lerpInt(dyeData.getLight() / 15.0f, minRed, maxRed);

                    return dyeData.getComponent().copy().withStyle(style -> style.withColor(255 << 24 | color << 16));
                },
                (dyeDataColor) -> {
                    if (dyeDataColor.layer() == 1) {
                        int lightValue = dyeDataColor.compoundTag().getInt("lightValue");

                        int minRed = 100;
                        int maxRed = 255;

                        int color = Mth.lerpInt(lightValue / 15.0f, minRed, maxRed);
                        return 255 << 24 | color << 16;
                    }

                    return -1;
                }
        );

        DyeTypeRegistry.registerDyeType(
                "lamp",
                new ItemDyeIcon(Items.REDSTONE_LAMP),
                Component.translatable("weaversparadise.dye_text.lamp"),
                (dyeData) -> {
                    int primaryRed = (dyeData.getColorOne() >> 16) & 0xFF;
                    int primaryGreen = (dyeData.getColorOne() >> 8) & 0xFF;
                    int primaryBlue = dyeData.getColorOne() & 0xFF;

                    int lowestRed = (int)(primaryRed * 0.4);
                    int lowestGreen = (int)(primaryGreen * 0.4);
                    int lowestBlue = (int)(primaryBlue * 0.4);

                    int finalRed = Mth.lerpInt(dyeData.getLight() / 15.0f, lowestRed, primaryRed);
                    int finalGreen = Mth.lerpInt(dyeData.getLight() / 15.0f, lowestGreen, primaryGreen);
                    int finalBlue = Mth.lerpInt(dyeData.getLight() / 15.0f, lowestBlue, primaryBlue);

                    int finalColor = 255 << 24 | finalRed << 16 | finalGreen << 8 | finalBlue;

                    return dyeData.getComponent().copy().withStyle(style -> style.withColor(finalColor));
                },
                (dyeDataColor) -> {
                    if (dyeDataColor.layer() == 1) {
                        int colorOne = dyeDataColor.colorOne();
                        int lightValue = dyeDataColor.light();

                        int redOne = (colorOne >> 16) & 0xFF;
                        int greenOne = (colorOne >> 8) & 0xFF;
                        int blueOne = colorOne & 0xFF;

                        int lowestRed = (int)(redOne * 0.4);
                        int lowestGreen = (int)(greenOne * 0.4);
                        int lowestBlue = (int)(blueOne * 0.4);

                        int finalRed = Mth.lerpInt(lightValue / 15.0f, lowestRed, redOne);
                        int finalGreen = Mth.lerpInt(lightValue / 15.0f, lowestGreen, greenOne);
                        int finalBlue = Mth.lerpInt(lightValue / 15.0f, lowestBlue, blueOne);

                        return 255 << 24 | finalRed << 16 | finalGreen << 8 | finalBlue;
                    }

                    return -1;
                }
        );

        DyeTypeRegistry.registerDyeType(
                "speed",
                new ItemDyeIcon(Items.SUGAR),
                Component.translatable("weaversparadise.dye_text.speed"),
                (dyeData) -> {
                    Minecraft minecraft = Minecraft.getInstance();
                    Vec3 vec3 = new Vec3(0, 0, 0);
                    if (minecraft.player != null) {
                        vec3 = minecraft.player.getDeltaMovement();
                    }
                    double velocity = vec3.lengthSqr();

                    int redOne = (dyeData.getColorOne() >> 16) & 0xFF;
                    int greenOne = (dyeData.getColorOne() >> 8) & 0xFF;
                    int blueOne = dyeData.getColorOne() & 0xFF;

                    int redTwo = (dyeData.getColorTwo() >> 16) & 0xFF;
                    int greenTwo = (dyeData.getColorTwo() >> 8) & 0xFF;
                    int blueTwo = dyeData.getColorTwo() & 0xFF;

                    int finalRed = Mth.lerpInt((float)Mth.clamp(velocity / 0.029081503190380643, 0, 1), redOne, redTwo);
                    int finalGreen = Mth.lerpInt((float)Mth.clamp(velocity / 0.029081503190380643, 0, 1), greenOne, greenTwo);
                    int finalBlue = Mth.lerpInt((float)Mth.clamp(velocity / 0.029081503190380643, 0, 1), blueOne, blueTwo);

                    int finalColor = 255 << 24 | finalRed << 16 | finalGreen << 8 | finalBlue;

                    return dyeData.getComponent().copy().withStyle(style -> style.withColor(finalColor));
                },
                (dyeDataColor) -> {
                    if (dyeDataColor.layer() ==  1) {
                        Minecraft minecraft = Minecraft.getInstance();
                        Vec3 vec3 = new Vec3(0, 0, 0);
                        if (minecraft.player != null) {
                            vec3 = minecraft.player.getDeltaMovement();
                        }
                        double velocity = vec3.lengthSqr();

                        int colorOne = dyeDataColor.colorOne();
                        int colorTwo = dyeDataColor.colorTwo();

                        int redOne = (colorOne >> 16) & 0xFF;
                        int greenOne = (colorOne >> 8) & 0xFF;
                        int blueOne = colorOne & 0xFF;

                        int redTwo = (colorTwo >> 16) & 0xFF;
                        int greenTwo = (colorTwo >> 8) & 0xFF;
                        int blueTwo = colorTwo & 0xFF;

                        int finalRed = Mth.lerpInt((float)Mth.clamp(velocity / 0.029081503190380643, 0, 1), redOne, redTwo);
                        int finalGreen = Mth.lerpInt((float)Mth.clamp(velocity / 0.029081503190380643, 0, 1), greenOne, greenTwo);
                        int finalBlue = Mth.lerpInt((float)Mth.clamp(velocity / 0.029081503190380643, 0, 1), blueOne, blueTwo);

                        return 255 << 24 | finalRed << 16 | finalGreen << 8 | finalBlue;
                    }

                    return -1;
                }
        );

        DyeTypeRegistry.registerDyeType(
                "height_bedrock",
                new ItemDyeIcon(Items.PHANTOM_MEMBRANE),
                Component.translatable("weaversparadise.dye_text.height_bedrock"),
                (dyeData) -> {
                    Minecraft minecraft = Minecraft.getInstance();
                    int absoluteMinimum = -1;
                    int absoluteMaximum = 1;
                    int height = 0;
                    if (minecraft.player != null & minecraft.level != null) {
                        absoluteMinimum = minecraft.level.getMinBuildHeight();
                        absoluteMaximum = minecraft.level.getMaxBuildHeight();
                        height = (int)minecraft.player.getY();
                    }

                    float value = Mth.clamp(((float)height - (float)absoluteMinimum) / ((float)absoluteMaximum - (float)absoluteMinimum), 0, 1);

                    int redOne = (dyeData.getColorOne() >> 16) & 0xFF;
                    int greenOne = (dyeData.getColorOne() >> 8) & 0xFF;
                    int blueOne = dyeData.getColorOne() & 0xFF;

                    int redTwo = (dyeData.getColorTwo() >> 16) & 0xFF;
                    int greenTwo = (dyeData.getColorTwo() >> 8) & 0xFF;
                    int blueTwo = dyeData.getColorTwo() & 0xFF;

                    int finalRed = Mth.lerpInt(value, redOne, redTwo);
                    int finalGreen = Mth.lerpInt(value, greenOne, greenTwo);
                    int finalBlue = Mth.lerpInt(value, blueOne, blueTwo);

                    int finalColor = 255 << 24 | finalRed << 16 | finalGreen << 8 | finalBlue;

                    return dyeData.getComponent().copy().withStyle(style -> style.withColor(finalColor));
                },
                (dyeDataColor) -> {
                    if (dyeDataColor.layer() == 1) {

                    }

                    return -1;
                }
        );

        DyeTypeRegistry.registerDyeType(
                "height_sea",
                new ItemDyeIcon(Items.PRISMARINE_CRYSTALS),
                Component.translatable("weaversparadise.dye_text.height_sea"),
                (dyeData) -> {
                    Minecraft minecraft = Minecraft.getInstance();
                    double minY = -1;
                    double maxY = 1;
                    double seaY = 0;
                    double playerY = 0;
                    if (minecraft.player != null && minecraft.level != null) {
                        minY = minecraft.level.getMinBuildHeight();
                        maxY = minecraft.level.getMaxBuildHeight();
                        seaY = minecraft.level.getSeaLevel();
                        playerY = minecraft.player.getY();
                    }

                    double distanceAbove = maxY - seaY;
                    double distanceBelow = seaY - minY;
                    double maxDistance = Math.max(distanceAbove, distanceBelow);
                    float value = (float)Mth.clamp(Math.abs(playerY - seaY) / maxDistance, 0, 1);

                    int redOne = (dyeData.getColorOne() >> 16) & 0xFF;
                    int greenOne = (dyeData.getColorOne() >> 8) & 0xFF;
                    int blueOne = dyeData.getColorOne() & 0xFF;

                    int redTwo = (dyeData.getColorTwo() >> 16) & 0xFF;
                    int greenTwo = (dyeData.getColorTwo() >> 8) & 0xFF;
                    int blueTwo = dyeData.getColorTwo() & 0xFF;

                    int finalRed = Mth.lerpInt(value, redOne, redTwo);
                    int finalGreen = Mth.lerpInt(value, greenOne, greenTwo);
                    int finalBlue = Mth.lerpInt(value, blueOne, blueTwo);

                    int finalColor = 255 << 24 | finalRed << 16 | finalGreen << 8 | finalBlue;

                    return dyeData.getComponent().copy().withStyle(style -> style.withColor(finalColor));
                },
                (dyeDataColor) -> {
                    if (dyeDataColor.layer() == 1) {
                        Minecraft minecraft = Minecraft.getInstance();
                        double minY = -1;
                        double maxY = 1;
                        double seaY = 0;
                        double playerY = 0;
                        if (minecraft.player != null && minecraft.level != null) {
                            minY = minecraft.level.getMinBuildHeight();
                            maxY = minecraft.level.getMaxBuildHeight();
                            seaY = minecraft.level.getSeaLevel();
                            playerY = minecraft.player.getY();
                        }

                        double distanceAbove = maxY - seaY;
                        double distanceBelow = seaY - minY;
                        double maxDistance = Math.max(distanceAbove, distanceBelow);
                        float value = (float)Mth.clamp(Math.abs(playerY - seaY) / maxDistance, 0, 1);

                        int colorOne = dyeDataColor.colorOne();
                        int colorTwo = dyeDataColor.colorTwo();

                        int redOne = (colorOne >> 16) & 0xFF;
                        int greenOne = (colorOne >> 8) & 0xFF;
                        int blueOne = colorOne & 0xFF;

                        int redTwo = (colorTwo >> 16) & 0xFF;
                        int greenTwo = (colorTwo >> 8) & 0xFF;
                        int blueTwo = colorTwo & 0xFF;

                        int finalRed = Mth.lerpInt(value, redOne, redTwo);
                        int finalGreen = Mth.lerpInt(value, greenOne, greenTwo);
                        int finalBlue = Mth.lerpInt(value, blueOne, blueTwo);

                        return  255 << 24 | finalRed << 16 | finalGreen << 8 | finalBlue;
                    }

                    return -1;
                }
        );

        DyeTypeRegistry.registerDyeType(
                "pride",
                new ItemDyeIcon(WeaversParadiseItems.FLAG_PRIDE.get()),
                Component.translatable("weaversparadise.dye_text.pride"),
                (dyeData) -> {
                    Minecraft minecraft = Minecraft.getInstance();
                    int ticks = 0;
                    if (minecraft.level != null) {
                        ticks = (int)minecraft.level.getGameTime();
                    }

                    List<Integer> colors = List.of(
                            (0xE40303),
                            (0xFF8C00),
                            (0xFFED00),
                            (0x008026),
                            (0x004DFF),
                            (0x750787)
                    );

                    return flagText(dyeData.getComponent().getString(), colors, ticks);
                },
                (dyeDataColor) -> {
                    if (dyeDataColor.layer() == 1) {
                        Minecraft minecraft = Minecraft.getInstance();
                        int ticks = 0;
                        if (minecraft.level != null) {
                            ticks = (int)minecraft.level.getGameTime();
                        }

                        List<Integer> colors = List.of(
                                (0xE40303),
                                (0xFF8C00),
                                (0xFFED00),
                                (0x008026),
                                (0x004DFF),
                                (0x750787)
                        );

                        return getCycledColor(colors, ticks);
                    }

                    return -1;
                },
                (renderData) -> {
                    ResourceLocation mask = ResourceLocation.fromNamespaceAndPath(
                            "weaversparadise",
                            "textures/clothing/mask/" + renderData.clothingType() + "/" + renderData.dyeType() + ".png"
                    );

                    return WeaversParadiseRenderTypes.getEntityTranslucentMask(
                            renderData.resourceLocation(),
                            mask
                    );
                }
        );

        DyeTypeRegistry.registerDyeType(
                "asexual",
                new ItemDyeIcon(WeaversParadiseItems.FLAG_ASEXUAL.get()),
                Component.translatable("weaversparadise.dye_text.asexual"),
                (dyeData) -> {
                    Minecraft minecraft = Minecraft.getInstance();
                    int ticks = 0;
                    if (minecraft.level != null) {
                        ticks = (int)minecraft.level.getGameTime();
                    }

                    List<Integer> colors = List.of(
                            (0x2E2E2E),
                            (0xA3A3A3),
                            (0xFFFFFF),
                            (0x800080)
                    );

                    return flagText(dyeData.getComponent().getString(), colors, ticks);
                },
                (dyeDataColor) -> {
                    if (dyeDataColor.layer() == 1) {
                        Minecraft minecraft = Minecraft.getInstance();
                        int ticks = 0;
                        if (minecraft.level != null) {
                            ticks = (int)minecraft.level.getGameTime();
                        }

                        List<Integer> colors = List.of(
                                (0x2E2E2E),
                                (0xA3A3A3),
                                (0xFFFFFF),
                                (0x800080)
                        );

                        return getCycledColor(colors, ticks);
                    }

                    return -1;
                },
                (renderData) -> {
                    ResourceLocation mask = ResourceLocation.fromNamespaceAndPath(
                            "weaversparadise",
                            "textures/clothing/mask/" + renderData.clothingType() + "/" + renderData.dyeType() + ".png"
                    );

                    return WeaversParadiseRenderTypes.getEntityTranslucentMask(
                            renderData.resourceLocation(),
                            mask
                    );
                }
        );

        DyeTypeRegistry.registerDyeType(
                "aromantic",
                new ItemDyeIcon(WeaversParadiseItems.FLAG_AROMANTIC.get()),
                Component.translatable("weaversparadise.dye_text.aromantic"),
                (dyeData) -> {
                    Minecraft minecraft = Minecraft.getInstance();
                    int ticks = 0;
                    if (minecraft.level != null) {
                        ticks = (int)minecraft.level.getGameTime();
                    }

                    List<Integer> colors = List.of(
                            (0x3DA542),
                            (0xA7D379),
                            (0xFFFFFF),
                            (0xA9A9A9),
                            (0x2E2E2E)
                    );

                    return flagText(dyeData.getComponent().getString(), colors, ticks);
                },
                (dyeDataColor) -> {
                    if (dyeDataColor.layer() == 1) {
                        Minecraft minecraft = Minecraft.getInstance();
                        int ticks = 0;
                        if (minecraft.level != null) {
                            ticks = (int)minecraft.level.getGameTime();
                        }

                        List<Integer> colors = List.of(
                                (0x3DA542),
                                (0xA7D379),
                                (0xFFFFFF),
                                (0xA9A9A9),
                                (0x2E2E2E)
                        );

                        return getCycledColor(colors, ticks);
                    }

                    return -1;
                },
                (renderData) -> {
                    ResourceLocation mask = ResourceLocation.fromNamespaceAndPath(
                            "weaversparadise",
                            "textures/clothing/mask/" + renderData.clothingType() + "/" + renderData.dyeType() + ".png"
                    );

                    return WeaversParadiseRenderTypes.getEntityTranslucentMask(
                            renderData.resourceLocation(),
                            mask
                    );
                }
        );

        DyeTypeRegistry.registerDyeType(
                "aroace",
                new ItemDyeIcon(WeaversParadiseItems.FLAG_AROACE.get()),
                Component.translatable("weaversparadise.dye_text.aroace"),
                (dyeData) -> {
                    Minecraft minecraft = Minecraft.getInstance();
                    int ticks = 0;
                    if (minecraft.level != null) {
                        ticks = (int)minecraft.level.getGameTime();
                    }

                    List<Integer> colors = List.of(
                            (0xE28C00),
                            (0xFFD700),
                            (0xFFFFFF),
                            (0x00A4E0),
                            (0x800080)
                    );

                    return flagText(dyeData.getComponent().getString(), colors, ticks);
                },
                (dyeDataColor) -> {
                    if (dyeDataColor.layer() == 1) {
                        Minecraft minecraft = Minecraft.getInstance();
                        int ticks = 0;
                        if (minecraft.level != null) {
                            ticks = (int)minecraft.level.getGameTime();
                        }

                        List<Integer> colors = List.of(
                                (0xE28C00),
                                (0xFFD700),
                                (0xFFFFFF),
                                (0x00A4E0),
                                (0x800080)
                        );

                        return getCycledColor(colors, ticks);
                    }

                    return -1;
                },
                (renderData) -> {
                    ResourceLocation mask = ResourceLocation.fromNamespaceAndPath(
                            "weaversparadise",
                            "textures/clothing/mask/" + renderData.clothingType() + "/" + renderData.dyeType() + ".png"
                    );

                    return WeaversParadiseRenderTypes.getEntityTranslucentMask(
                            renderData.resourceLocation(),
                            mask
                    );
                }
        );

        DyeTypeRegistry.registerDyeType(
                "agender",
                new ItemDyeIcon(WeaversParadiseItems.FLAG_AGENDER.get()),
                Component.translatable("weaversparadise.dye_text.agender"),
                (dyeData) -> {
                    Minecraft minecraft = Minecraft.getInstance();
                    int ticks = 0;
                    if (minecraft.level != null) {
                        ticks = (int)minecraft.level.getGameTime();
                    }

                    List<Integer> colors = List.of(
                            (0x2E2E2E),
                            (0xB9B9B9),
                            (0xFFFFFF),
                            (0xB8F483),
                            (0xFFFFFF),
                            (0xB9B9B9),
                            (0x2E2E2E)
                    );

                    return flagText(dyeData.getComponent().getString(), colors, ticks);
                },
                (dyeDataColor) -> {
                    if (dyeDataColor.layer() == 1) {
                        Minecraft minecraft = Minecraft.getInstance();
                        int ticks = 0;
                        if (minecraft.level != null) {
                            ticks = (int)minecraft.level.getGameTime();
                        }

                        List<Integer> colors = List.of(
                                (0x2E2E2E),
                                (0xB9B9B9),
                                (0xFFFFFF),
                                (0xB8F483),
                                (0xFFFFFF),
                                (0xB9B9B9),
                                (0x2E2E2E)
                        );

                        return getCycledColor(colors, ticks);
                    }

                    return -1;
                },
                (renderData) -> {
                    ResourceLocation mask = ResourceLocation.fromNamespaceAndPath(
                            "weaversparadise",
                            "textures/clothing/mask/" + renderData.clothingType() + "/" + renderData.dyeType() + ".png"
                    );

                    return WeaversParadiseRenderTypes.getEntityTranslucentMask(
                            renderData.resourceLocation(),
                            mask
                    );
                }
        );

        DyeTypeRegistry.registerDyeType(
                "bisexual",
                new ItemDyeIcon(WeaversParadiseItems.FLAG_BISEXUAL.get()),
                Component.translatable("weaversparadise.dye_text.bisexual"),
                (dyeData) -> {
                    Minecraft minecraft = Minecraft.getInstance();
                    int ticks = 0;
                    if (minecraft.level != null) {
                        ticks = (int)minecraft.level.getGameTime();
                    }

                    List<Integer> colors = List.of(
                            (0xD60270),
                            (0xD60270),
                            (0x9B4F96),
                            (0x0038A8),
                            (0x0038A8)
                    );

                    return flagText(dyeData.getComponent().getString(), colors, ticks);
                },
                (dyeDataColor) -> {
                    if (dyeDataColor.layer() == 1) {
                        Minecraft minecraft = Minecraft.getInstance();
                        int ticks = 0;
                        if (minecraft.level != null) {
                            ticks = (int)minecraft.level.getGameTime();
                        }

                        List<Integer> colors = List.of(
                                (0xD60270),
                                (0xD60270),
                                (0x9B4F96),
                                (0x0038A8),
                                (0x0038A8)
                        );

                        return getCycledColor(colors, ticks);
                    }

                    return -1;
                },
                (renderData) -> {
                    ResourceLocation mask = ResourceLocation.fromNamespaceAndPath(
                            "weaversparadise",
                            "textures/clothing/mask/" + renderData.clothingType() + "/" + renderData.dyeType() + ".png"
                    );

                    return WeaversParadiseRenderTypes.getEntityTranslucentMask(
                            renderData.resourceLocation(),
                            mask
                    );
                }
        );

        DyeTypeRegistry.registerDyeType(
                "demiboy",
                new ItemDyeIcon(WeaversParadiseItems.FLAG_DEMIBOY.get()),
                Component.translatable("weaversparadise.dye_text.demiboy"),
                (dyeData) -> {
                    Minecraft minecraft = Minecraft.getInstance();
                    int ticks = 0;
                    if (minecraft.level != null) {
                        ticks = (int)minecraft.level.getGameTime();
                    }

                    List<Integer> colors = List.of(
                            (0x2E2E2E),
                            (0x7F7F7F),
                            (0x9AD9EB),
                            (0xFFFFFF),
                            (0x9AD9EB),
                            (0x7F7F7F),
                            (0x2E2E2E)
                    );

                    return flagText(dyeData.getComponent().getString(), colors, ticks);
                },
                (dyeDataColor) -> {
                    if (dyeDataColor.layer() == 1) {
                        Minecraft minecraft = Minecraft.getInstance();
                        int ticks = 0;
                        if (minecraft.level != null) {
                            ticks = (int)minecraft.level.getGameTime();
                        }

                        List<Integer> colors = List.of(
                                (0x2E2E2E),
                                (0x7F7F7F),
                                (0x9AD9EB),
                                (0xFFFFFF),
                                (0x9AD9EB),
                                (0x7F7F7F),
                                (0x2E2E2E)
                        );

                        return getCycledColor(colors, ticks);
                    }

                    return -1;
                },
                (renderData) -> {
                    ResourceLocation mask = ResourceLocation.fromNamespaceAndPath(
                            "weaversparadise",
                            "textures/clothing/mask/" + renderData.clothingType() + "/" + renderData.dyeType() + ".png"
                    );

                    return WeaversParadiseRenderTypes.getEntityTranslucentMask(
                            renderData.resourceLocation(),
                            mask
                    );
                }
        );

        DyeTypeRegistry.registerDyeType(
                "demigender",
                new ItemDyeIcon(WeaversParadiseItems.FLAG_DEMIGENDER.get()),
                Component.translatable("weaversparadise.dye_text.demigender"),
                (dyeData) -> {
                    Minecraft minecraft = Minecraft.getInstance();
                    int ticks = 0;
                    if (minecraft.level != null) {
                        ticks = (int)minecraft.level.getGameTime();
                    }

                    List<Integer> colors = List.of(
                            (0x2E2E2E),
                            (0x7F7F7F),
                            (0xF9F06B),
                            (0xFFFFFF),
                            (0xF9F06B),
                            (0x7F7F7F),
                            (0x2E2E2E)
                    );

                    return flagText(dyeData.getComponent().getString(), colors, ticks);
                },
                (dyeDataColor) -> {
                    if (dyeDataColor.layer() == 1) {
                        Minecraft minecraft = Minecraft.getInstance();
                        int ticks = 0;
                        if (minecraft.level != null) {
                            ticks = (int)minecraft.level.getGameTime();
                        }

                        List<Integer> colors = List.of(
                                (0x2E2E2E),
                                (0x7F7F7F),
                                (0xF9F06B),
                                (0xFFFFFF),
                                (0xF9F06B),
                                (0x7F7F7F),
                                (0x2E2E2E)
                        );

                        return getCycledColor(colors, ticks);
                    }

                    return -1;
                },
                (renderData) -> {
                    ResourceLocation mask = ResourceLocation.fromNamespaceAndPath(
                            "weaversparadise",
                            "textures/clothing/mask/" + renderData.clothingType() + "/" + renderData.dyeType() + ".png"
                    );

                    return WeaversParadiseRenderTypes.getEntityTranslucentMask(
                            renderData.resourceLocation(),
                            mask
                    );
                }
        );

        DyeTypeRegistry.registerDyeType(
                "demigirl",
                new ItemDyeIcon(WeaversParadiseItems.FLAG_DEMIGIRL.get()),
                Component.translatable("weaversparadise.dye_text.demigirl"),
                (dyeData) -> {
                    Minecraft minecraft = Minecraft.getInstance();
                    int ticks = 0;
                    if (minecraft.level != null) {
                        ticks = (int)minecraft.level.getGameTime();
                    }

                    List<Integer> colors = List.of(
                            (0x2E2E2E),
                            (0x7F7F7F),
                            (0xFCACC9),
                            (0xFFFFFF),
                            (0xFCACC9),
                            (0x7F7F7F),
                            (0x2E2E2E)
                    );

                    return flagText(dyeData.getComponent().getString(), colors, ticks);
                },
                (dyeDataColor) -> {
                    if (dyeDataColor.layer() == 1) {
                        Minecraft minecraft = Minecraft.getInstance();
                        int ticks = 0;
                        if (minecraft.level != null) {
                            ticks = (int)minecraft.level.getGameTime();
                        }

                        List<Integer> colors = List.of(
                                (0x2E2E2E),
                                (0x7F7F7F),
                                (0xFCACC9),
                                (0xFFFFFF),
                                (0xFCACC9),
                                (0x7F7F7F),
                                (0x2E2E2E)
                        );

                        return getCycledColor(colors, ticks);
                    }

                    return -1;
                },
                (renderData) -> {
                    ResourceLocation mask = ResourceLocation.fromNamespaceAndPath(
                            "weaversparadise",
                            "textures/clothing/mask/" + renderData.clothingType() + "/" + renderData.dyeType() + ".png"
                    );

                    return WeaversParadiseRenderTypes.getEntityTranslucentMask(
                            renderData.resourceLocation(),
                            mask
                    );
                }
        );

        DyeTypeRegistry.registerDyeType(
                "gay",
                new ItemDyeIcon(WeaversParadiseItems.FLAG_GAY.get()),
                Component.translatable("weaversparadise.dye_text.gay"),
                (dyeData) -> {
                    Minecraft minecraft = Minecraft.getInstance();
                    int ticks = 0;
                    if (minecraft.level != null) {
                        ticks = (int)minecraft.level.getGameTime();
                    }

                    List<Integer> colors = List.of(
                            (0x078D70),
                            (0x26CEAA),
                            (0x98E8C1),
                            (0xFFFFFF),
                            (0x7BADE3),
                            (0x5049CC),
                            (0x3D1A78)
                    );

                    return flagText(dyeData.getComponent().getString(), colors, ticks);
                },
                (dyeDataColor) -> {
                    if (dyeDataColor.layer() == 1) {
                        Minecraft minecraft = Minecraft.getInstance();
                        int ticks = 0;
                        if (minecraft.level != null) {
                            ticks = (int)minecraft.level.getGameTime();
                        }

                        List<Integer> colors = List.of(
                                (0x078D70),
                                (0x26CEAA),
                                (0x98E8C1),
                                (0xFFFFFF),
                                (0x7BADE3),
                                (0x5049CC),
                                (0x3D1A78)
                        );

                        return getCycledColor(colors, ticks);
                    }

                    return -1;
                },
                (renderData) -> {
                    ResourceLocation mask = ResourceLocation.fromNamespaceAndPath(
                            "weaversparadise",
                            "textures/clothing/mask/" + renderData.clothingType() + "/" + renderData.dyeType() + ".png"
                    );

                    return WeaversParadiseRenderTypes.getEntityTranslucentMask(
                            renderData.resourceLocation(),
                            mask
                    );
                }
        );

        DyeTypeRegistry.registerDyeType(
                "genderfluid",
                new ItemDyeIcon(WeaversParadiseItems.FLAG_GENDERFLUID.get()),
                Component.translatable("weaversparadise.dye_text.genderfluid"),
                (dyeData) -> {
                    Minecraft minecraft = Minecraft.getInstance();
                    int ticks = 0;
                    if (minecraft.level != null) {
                        ticks = (int)minecraft.level.getGameTime();
                    }

                    List<Integer> colors = List.of(
                            (0xFF75A2),
                            (0xFFFFFF),
                            (0xBE18D6),
                            (0x2E2E2E),
                            (0x333EBD)
                    );

                    return flagText(dyeData.getComponent().getString(), colors, ticks);
                },
                (dyeDataColor) -> {
                    if (dyeDataColor.layer() == 1) {
                        Minecraft minecraft = Minecraft.getInstance();
                        int ticks = 0;
                        if (minecraft.level != null) {
                            ticks = (int)minecraft.level.getGameTime();
                        }

                        List<Integer> colors = List.of(
                                (0xFF75A2),
                                (0xFFFFFF),
                                (0xBE18D6),
                                (0x2E2E2E),
                                (0x333EBD)
                        );

                        return getCycledColor(colors, ticks);
                    }

                    return -1;
                },
                (renderData) -> {
                    ResourceLocation mask = ResourceLocation.fromNamespaceAndPath(
                            "weaversparadise",
                            "textures/clothing/mask/" + renderData.clothingType() + "/" + renderData.dyeType() + ".png"
                    );

                    return WeaversParadiseRenderTypes.getEntityTranslucentMask(
                            renderData.resourceLocation(),
                            mask
                    );
                }
        );

        DyeTypeRegistry.registerDyeType(
                "genderqueer",
                new ItemDyeIcon(WeaversParadiseItems.FLAG_GENDERQUEER.get()),
                Component.translatable("weaversparadise.dye_text.genderqueer"),
                (dyeData) -> {
                    Minecraft minecraft = Minecraft.getInstance();
                    int ticks = 0;
                    if (minecraft.level != null) {
                        ticks = (int)minecraft.level.getGameTime();
                    }

                    List<Integer> colors = List.of(
                            (0xB57EDC),
                            (0xFFFFFF),
                            (0x49822B)
                    );

                    return flagText(dyeData.getComponent().getString(), colors, ticks);
                },
                (dyeDataColor) -> {
                    if (dyeDataColor.layer() == 1) {
                        Minecraft minecraft = Minecraft.getInstance();
                        int ticks = 0;
                        if (minecraft.level != null) {
                            ticks = (int)minecraft.level.getGameTime();
                        }

                        List<Integer> colors = List.of(
                                (0xB57EDC),
                                (0xFFFFFF),
                                (0x49822B)
                        );

                        return getCycledColor(colors, ticks);
                    }

                    return -1;
                },
                (renderData) -> {
                    ResourceLocation mask = ResourceLocation.fromNamespaceAndPath(
                            "weaversparadise",
                            "textures/clothing/mask/" + renderData.clothingType() + "/" + renderData.dyeType() + ".png"
                    );

                    return WeaversParadiseRenderTypes.getEntityTranslucentMask(
                            renderData.resourceLocation(),
                            mask
                    );
                }
        );

        DyeTypeRegistry.registerDyeType(
                "intersex",
                new ItemDyeIcon(WeaversParadiseItems.FLAG_INTERSEX.get()),
                Component.translatable("weaversparadise.dye_text.intersex"),
                (dyeData) -> {
                    Minecraft minecraft = Minecraft.getInstance();
                    int ticks = 0;
                    if (minecraft.level != null) {
                        ticks = (int)minecraft.level.getGameTime();
                    }

                    List<Integer> colors = List.of(
                            (0xFFD800),
                            (0xFFD800),
                            (0x7902AA),
                            (0xFFD800),
                            (0x7902AA),
                            (0xFFD800),
                            (0xFFD800)
                    );

                    return flagText(dyeData.getComponent().getString(), colors, ticks);
                },
                (dyeDataColor) -> {
                    if (dyeDataColor.layer() == 1) {
                        Minecraft minecraft = Minecraft.getInstance();
                        int ticks = 0;
                        if (minecraft.level != null) {
                            ticks = (int)minecraft.level.getGameTime();
                        }

                        List<Integer> colors = List.of(
                                (0xFFD800),
                                (0xFFD800),
                                (0x7902AA),
                                (0xFFD800),
                                (0x7902AA),
                                (0xFFD800),
                                (0xFFD800)
                        );

                        return getCycledColor(colors, ticks);
                    }

                    return -1;
                },
                (renderData) -> {
                    ResourceLocation mask = ResourceLocation.fromNamespaceAndPath(
                            "weaversparadise",
                            "textures/clothing/mask/" + renderData.clothingType() + "/" + renderData.dyeType() + ".png"
                    );

                    return WeaversParadiseRenderTypes.getEntityTranslucentMask(
                            renderData.resourceLocation(),
                            mask
                    );
                }
        );

        DyeTypeRegistry.registerDyeType(
                "lesbian",
                new ItemDyeIcon(WeaversParadiseItems.FLAG_LESBIAN.get()),
                Component.translatable("weaversparadise.dye_text.lesbian"),
                (dyeData) -> {
                    Minecraft minecraft = Minecraft.getInstance();
                    int ticks = 0;
                    if (minecraft.level != null) {
                        ticks = (int)minecraft.level.getGameTime();
                    }

                    List<Integer> colors = List.of(
                            (0xD62900),
                            (0xFF9B55),
                            (0xFFFFFF),
                            (0xD461A6),
                            (0xA50062)
                    );

                    return flagText(dyeData.getComponent().getString(), colors, ticks);
                },
                (dyeDataColor) -> {
                    if (dyeDataColor.layer() == 1) {
                        Minecraft minecraft = Minecraft.getInstance();
                        int ticks = 0;
                        if (minecraft.level != null) {
                            ticks = (int)minecraft.level.getGameTime();
                        }

                        List<Integer> colors = List.of(
                                (0xD62900),
                                (0xFF9B55),
                                (0xFFFFFF),
                                (0xD461A6),
                                (0xA50062)
                        );

                        return getCycledColor(colors, ticks);
                    }

                    return -1;
                },
                (renderData) -> {
                    ResourceLocation mask = ResourceLocation.fromNamespaceAndPath(
                            "weaversparadise",
                            "textures/clothing/mask/" + renderData.clothingType() + "/" + renderData.dyeType() + ".png"
                    );

                    return WeaversParadiseRenderTypes.getEntityTranslucentMask(
                            renderData.resourceLocation(),
                            mask
                    );
                }
        );

        DyeTypeRegistry.registerDyeType(
                "nonbinary",
                new ItemDyeIcon(WeaversParadiseItems.FLAG_NONBINARY.get()),
                Component.translatable("weaversparadise.dye_text.nonbinary"),
                (dyeData) -> {
                    Minecraft minecraft = Minecraft.getInstance();
                    int ticks = 0;
                    if (minecraft.level != null) {
                        ticks = (int)minecraft.level.getGameTime();
                    }

                    List<Integer> colors = List.of(
                            (0xFFF430),
                            (0xFFFFFF),
                            (0x9C59D1),
                            (0x2E2E2E)
                    );

                    return flagText(dyeData.getComponent().getString(), colors, ticks);
                },
                (dyeDataColor) -> {
                    if (dyeDataColor.layer() == 1) {
                        Minecraft minecraft = Minecraft.getInstance();
                        int ticks = 0;
                        if (minecraft.level != null) {
                            ticks = (int)minecraft.level.getGameTime();
                        }

                        List<Integer> colors = List.of(
                                (0xFFF430),
                                (0xFFFFFF),
                                (0x9C59D1),
                                (0x2E2E2E)
                        );

                        return getCycledColor(colors, ticks);
                    }

                    return -1;
                },
                (renderData) -> {
                    ResourceLocation mask = ResourceLocation.fromNamespaceAndPath(
                            "weaversparadise",
                            "textures/clothing/mask/" + renderData.clothingType() + "/" + renderData.dyeType() + ".png"
                    );

                    return WeaversParadiseRenderTypes.getEntityTranslucentMask(
                            renderData.resourceLocation(),
                            mask
                    );
                }
        );

        DyeTypeRegistry.registerDyeType(
                "pansexual",
                new ItemDyeIcon(WeaversParadiseItems.FLAG_PANSEXUAL.get()),
                Component.translatable("weaversparadise.dye_text.pansexual"),
                (dyeData) -> {
                    Minecraft minecraft = Minecraft.getInstance();
                    int ticks = 0;
                    if (minecraft.level != null) {
                        ticks = (int)minecraft.level.getGameTime();
                    }

                    List<Integer> colors = List.of(
                            (0xFF218C),
                            (0xFFD800),
                            (0x21B1FF)
                    );

                    return flagText(dyeData.getComponent().getString(), colors, ticks);
                },
                (dyeDataColor) -> {
                    if (dyeDataColor.layer() == 1) {
                        Minecraft minecraft = Minecraft.getInstance();
                        int ticks = 0;
                        if (minecraft.level != null) {
                            ticks = (int)minecraft.level.getGameTime();
                        }

                        List<Integer> colors = List.of(
                                (0xFF218C),
                                (0xFFD800),
                                (0x21B1FF)
                        );

                        return getCycledColor(colors, ticks);
                    }

                    return -1;
                },
                (renderData) -> {
                    ResourceLocation mask = ResourceLocation.fromNamespaceAndPath(
                            "weaversparadise",
                            "textures/clothing/mask/" + renderData.clothingType() + "/" + renderData.dyeType() + ".png"
                    );

                    return WeaversParadiseRenderTypes.getEntityTranslucentMask(
                            renderData.resourceLocation(),
                            mask
                    );
                }
        );

        DyeTypeRegistry.registerDyeType(
                "trans",
                new ItemDyeIcon(WeaversParadiseItems.FLAG_TRANS.get()),
                Component.translatable("weaversparadise.dye_text.trans"),
                (dyeData) -> {
                    Minecraft minecraft = Minecraft.getInstance();
                    int ticks = 0;
                    if (minecraft.level != null) {
                        ticks = (int)minecraft.level.getGameTime();
                    }

                    List<Integer> colors = List.of(
                            (0x5BCEFA),
                            (0xF5A9B8),
                            (0xFFFFFF),
                            (0xF5A9B8),
                            (0x5BCEFA)
                    );

                    return flagText(dyeData.getComponent().getString(), colors, ticks);
                },
                (dyeDataColor) -> {
                    if (dyeDataColor.layer() == 1) {
                        Minecraft minecraft = Minecraft.getInstance();
                        int ticks = 0;
                        if (minecraft.level != null) {
                            ticks = (int)minecraft.level.getGameTime();
                        }

                        List<Integer> colors = List.of(
                                (0x5BCEFA),
                                (0xF5A9B8),
                                (0xFFFFFF),
                                (0xF5A9B8),
                                (0x5BCEFA)
                        );

                        return getCycledColor(colors, ticks);
                    }

                    return -1;
                },
                (renderData) -> {
                    ResourceLocation mask = ResourceLocation.fromNamespaceAndPath(
                            "weaversparadise",
                            "textures/clothing/mask/" + renderData.clothingType() + "/" + renderData.dyeType() + ".png"
                    );

                    return WeaversParadiseRenderTypes.getEntityTranslucentMask(
                            renderData.resourceLocation(),
                            mask
                    );
                }
        );

        DyeTypeRegistry.registerDyeType(
                "polychromatic",
                new ItemDyeIcon(WeaversParadiseItems.CHROMATIC_DUST.get()),
                Component.translatable("weaversparadise.dye_text.polychromatic"),
                (dyeData) -> {
                    Minecraft minecraft = Minecraft.getInstance();
                    int ticks = 0;
                    if (minecraft.level != null) {
                        ticks = (int)minecraft.level.getGameTime();
                    }

                    List<Integer> colors = List.of(
                            dyeData.getColorOne(),
                            hueShift(dyeData.getColorOne(), 0.33f),
                            hueShift(dyeData.getColorOne(), 0.66f),
                            hueShift(dyeData.getColorOne(), 1)
                    );

                    return flagText(dyeData.getComponent().getString(), colors, ticks);
                },
                (dyeDataColor) -> {
                    if (dyeDataColor.layer() == 1) {
                        Minecraft minecraft = Minecraft.getInstance();
                        int ticks = 0;
                        if (minecraft.level != null) {
                            ticks = (int)minecraft.level.getGameTime();
                        }

                        List<Integer> colors = List.of(
                                dyeDataColor.colorOne(),
                                hueShift(dyeDataColor.colorOne(), 0.33f),
                                hueShift(dyeDataColor.colorOne(), 0.66f),
                                hueShift(dyeDataColor.colorOne(), 1)
                        );

                        return getCycledColor(colors, ticks);
                    }

                    return -1;
                },
                (renderData) -> {
                    return WeaversParadiseRenderTypes.getPolychromatic(
                            renderData.resourceLocation()
                    );
                }
        );

        DyeTypeRegistry.registerDyeType(
                "invisible",
                new ItemDyeIcon(Items.GLASS),
                Component.translatable("weaversparadise.dye_text.invisible"),
                dyeData -> dyeData.getComponent().copy(),
                dyeDataColor -> -1
        );

        DyeTypeRegistry.registerDyeType(
                "static",
                new ItemDyeIcon(Items.POWDER_SNOW_BUCKET),
                Component.translatable("weaversparadise.dye_text.static"),
                (dyeData) -> {
                    Minecraft minecraft = Minecraft.getInstance();
                    int ticks = 0;
                    if (minecraft.level != null) {
                        ticks = (int)minecraft.level.getGameTime();
                    }

                    String val = dyeData.getComponent().getString();
                    int baseRed = 255;
                    int baseGreen = 255;
                    int baseBlue = 255;
                    int glowRed = 170;
                    int glowGreen = 170;
                    int glowBlue = 170;

                    MutableComponent mutableComponent = Component.empty();

                    for (int i = 0; i < val.length(); i++) {
                        char c = val.charAt(i);

                        float t = (float)(Math.sin((ticks + FIXED_SEQUENCE[i]) * 0.5) + 1.0f) / 2.0f;
                        int colorRed = Mth.lerpInt(t, baseRed, glowRed);
                        int colorGreen = Mth.lerpInt(t, baseGreen, glowGreen);
                        int colorBlue = Mth.lerpInt(t, baseBlue, glowBlue);

                        int color = 255 << 24 | colorRed << 16 | colorGreen << 8 | colorBlue;

                        mutableComponent.append(Component.literal(String.valueOf(c)).withStyle(style -> style.withColor(color)));
                    }

                    return mutableComponent;
                },
                (dyeDataColor) -> -1,
                (renderData) -> {
                    return WeaversParadiseRenderTypes.getEntityStatic(
                            renderData.resourceLocation()
                    );
                }
        );

        DyeTypeRegistry.registerDyeType(
                "crystal",
                new ItemDyeIcon(Items.AMETHYST_SHARD),
                Component.translatable("weaversparadise.dye_text.crystal"),
                (dyeData) -> {
                    return dyeData.getComponent().copy().withStyle(style -> style.withColor(dyeData.getColorOne()));
                },
                (dyeDataColor) -> {
                    if (dyeDataColor.layer() == 1) {
                        return dyeDataColor.colorOne();
                    }

                    return -1;
                },
                (renderData) -> {
                    return WeaversParadiseRenderTypes.getCrystal(
                            renderData.resourceLocation()
                    );
                }
        );

        DyeTypeRegistry.registerDyeType(
                "negative",
                new ItemDyeIcon(Items.BARRIER),
                Component.translatable("weaversparadise.dye_text.negative"),
                (dyeData) -> {
                    Minecraft minecraft = Minecraft.getInstance();
                    int ticks = 0;
                    if (minecraft.level != null) {
                        ticks = (int)minecraft.level.getGameTime();
                    }

                    int primaryRed = (dyeData.getColorOne() >> 16) & 0xFF;
                    int primaryGreen = (dyeData.getColorOne() >> 8) & 0xFF;
                    int primaryBlue = dyeData.getColorOne() & 0xFF;

                    List<Integer> colors = List.of(
                            dyeData.getColorOne(),
                            (255 << 24 | (255 - primaryRed) << 16 | (255 - primaryGreen) << 8 | (255 - primaryBlue))
                    );

                    return flagText(dyeData.getComponent().getString(), colors, ticks);
                },
                (dyeDataColor) -> {
                    if (dyeDataColor.layer() == 1) {
                        Minecraft minecraft = Minecraft.getInstance();
                        int ticks = 0;
                        if (minecraft.level != null) {
                            ticks = (int)minecraft.level.getGameTime();
                        }

                        int primaryRed = (dyeDataColor.colorOne() >> 16) & 0xFF;
                        int primaryGreen = (dyeDataColor.colorOne() >> 8) & 0xFF;
                        int primaryBlue = dyeDataColor.colorOne() & 0xFF;

                        List<Integer> colors = List.of(
                                dyeDataColor.colorOne(),
                                (255 << 24 | (255 - primaryRed) << 16 | (255 - primaryGreen) << 8 | (255 - primaryBlue))
                        );

                        return getCycledColor(colors, ticks);
                    }

                    return -1;
                },
                (renderData) -> {
                    return WeaversParadiseRenderTypes.getNegative(
                            renderData.resourceLocation()
                    );
                }
        );

        DyeTypeRegistry.registerDyeType(
                "true_negative",
                new ItemDyeIcon(Items.BARRIER),
                Component.translatable("weaversparadise.dye_text.true_negative"),
                (dyeData) -> {
                    Minecraft minecraft = Minecraft.getInstance();
                    int ticks = 0;
                    if (minecraft.level != null) {
                        ticks = (int)minecraft.level.getGameTime();
                    }

                    int primaryRed = (dyeData.getColorOne() >> 16) & 0xFF;
                    int primaryGreen = (dyeData.getColorOne() >> 8) & 0xFF;
                    int primaryBlue = dyeData.getColorOne() & 0xFF;

                    List<Integer> colors = List.of(
                            dyeData.getColorOne(),
                            (255 << 24 | (255 - primaryRed) << 16 | (255 - primaryGreen) << 8 | (255 - primaryBlue))
                    );

                    return flagText(dyeData.getComponent().getString(), colors, ticks);
                },
                (dyeDataColor) -> {
                    if (dyeDataColor.layer() == 1) {
                        Minecraft minecraft = Minecraft.getInstance();
                        int ticks = 0;
                        if (minecraft.level != null) {
                            ticks = (int)minecraft.level.getGameTime();
                        }

                        int primaryRed = (dyeDataColor.colorOne() >> 16) & 0xFF;
                        int primaryGreen = (dyeDataColor.colorOne() >> 8) & 0xFF;
                        int primaryBlue = dyeDataColor.colorOne() & 0xFF;

                        List<Integer> colors = List.of(
                                dyeDataColor.colorOne(),
                                (255 << 24 | (255 - primaryRed) << 16 | (255 - primaryGreen) << 8 | (255 - primaryBlue))
                        );

                        return getCycledColor(colors, ticks);
                    }

                    return -1;
                },
                (renderData) -> {
                    return WeaversParadiseRenderTypes.getTrueNegative(
                            renderData.resourceLocation()
                    );
                }
        );

        DyeTypeRegistry.registerDyeType(
                "nebula",
                new ItemDyeIcon(Items.DRAGON_BREATH),
                Component.translatable("weaversparadise.dye_text.nebula"),
                (dyeData) -> {
                    Minecraft minecraft = Minecraft.getInstance();
                    int ticks = 0;
                    if (minecraft.level != null) {
                        ticks = (int)minecraft.level.getGameTime();
                    }

                    List<Integer> colors = List.of(
                            0x26103F,
                            0x26103F,
                            0xE673B2,
                            0x4CCCCD,
                            0x8C40A6
                    );

                    return flagText(dyeData.getComponent().getString(), colors, ticks);
                },
                (dyeDataColor) -> {
                    if (dyeDataColor.layer() == 1) {
                        Minecraft minecraft = Minecraft.getInstance();
                        int ticks = 0;
                        if (minecraft.level != null) {
                            ticks = (int)minecraft.level.getGameTime();
                        }

                        List<Integer> colors = List.of(
                                0x26103F,
                                0x26103F,
                                0xE673B2,
                                0x4CCCCD,
                                0x8C40A6
                        );

                        return getCycledColor(colors, ticks);
                    }

                    return -1;
                },
                (renderData) -> {
                    return WeaversParadiseRenderTypes.getNebula(
                            renderData.resourceLocation()
                    );
                }
        );

        DyeTypeRegistry.registerDyeType(
                "colored_sculk",
                new ItemDyeIcon(Items.ECHO_SHARD),
                Component.translatable("weaversparadise.dye_text.sculk"),
                (dyeData) -> {
                    Minecraft minecraft = Minecraft.getInstance();
                    int ticks = 0;

                    if (minecraft.level != null) {
                        ticks = (int)minecraft.level.getGameTime();
                    }

                    String val = dyeData.getComponent().getString();
                    int length = val.length();
                    int middle = length / 2;

                    int pulseInterval = 70;
                    int pulseDuration = 20;
                    int timeInCycle = ticks % pulseInterval;

                    MutableComponent mutableComponent = Component.empty();

                    for (int i = 0; i < length; i++) {
                        char c = val.charAt(i);

                        int distFromMiddle = Math.abs(i - middle);
                        int pulseStartTick = distFromMiddle * (pulseDuration / middle);
                        int finalColor;

                        if (timeInCycle >= pulseStartTick && timeInCycle <= pulseStartTick + pulseDuration) {
                            float fade = 1.0f - (float)(timeInCycle - pulseStartTick) / pulseDuration;
                            finalColor = blendColors(dyeData.getColorTwo(), dyeData.getColorOne(), fade);
                        } else {
                            finalColor = dyeData.getColorOne();
                        }

                        MutableComponent letter = Component.literal(String.valueOf(c)).withStyle(style -> style.withColor(finalColor));
                        mutableComponent.append(letter);
                    }

                    return mutableComponent;
                },
                (dyeDataColor) -> {
                    if (dyeDataColor.layer() == 1) {
                        Minecraft minecraft = Minecraft.getInstance();

                        int ticks = 0;
                        if (minecraft.level != null) {
                            ticks = (int)minecraft.level.getGameTime();
                        }

                        int pulseInterval = 70;
                        int pulseDuration = 20;
                        int timeInCycle = ticks % pulseInterval;

                        int pulseStartTick = 0;

                        if (timeInCycle >= pulseStartTick && timeInCycle <= pulseStartTick + pulseDuration) {
                            float fade = 1.0f - (float)(timeInCycle - pulseStartTick) / pulseDuration;

                            return blendColors(dyeDataColor.colorTwo(), dyeDataColor.colorOne(), fade);
                        } else {
                            return dyeDataColor.colorOne();
                        }
                    }

                    return -1;
                }
        );

        DyeTypeRegistry.registerDyeType(
                "hunger",
                new DyeIcon(ResourceLocation.fromNamespaceAndPath("weaversparadise", "textures/tooltips/food_full.png")),
                Component.translatable("weaversparadise.dye_text.hunger"),
                (dyeData) -> {
                    Minecraft minecraft = Minecraft.getInstance();
                    int hunger = 20;
                    int maxHunger = 20;
                    if (minecraft.player != null) {
                        hunger = minecraft.player.getFoodData().getFoodLevel();
                    }
                    float l = hunger / (float)maxHunger;

                    int redOne = (dyeData.getColorOne() >> 16) & 0xFF;
                    int greenOne = (dyeData.getColorOne() >> 8) & 0xFF;
                    int blueOne = dyeData.getColorOne() & 0xFF;

                    int redTwo = (dyeData.getColorTwo() >> 16) & 0xFF;
                    int greenTwo = (dyeData.getColorTwo() >> 8) & 0xFF;
                    int blueTwo = dyeData.getColorTwo() & 0xFF;

                    int colorRed = Mth.lerpInt(l, redTwo, redOne);
                    int colorGreen = Mth.lerpInt(l, greenTwo, greenOne);
                    int colorBlue = Mth.lerpInt(l, blueTwo, blueOne);

                    return dyeData.getComponent().copy().withStyle(style -> style.withColor(colorRed << 16 | colorGreen << 8 | colorBlue));
                },
                (dyeDataColor) -> {
                    if (dyeDataColor.layer() == 1) {
                        Minecraft minecraft = Minecraft.getInstance();
                        int hunger = 20;
                        int maxHunger = 20;
                        if (minecraft.player != null) {
                            hunger = minecraft.player.getFoodData().getFoodLevel();
                        }
                        float l = hunger / (float)maxHunger;

                        int redOne = (dyeDataColor.colorOne() >> 16) & 0xFF;
                        int greenOne = (dyeDataColor.colorOne() >> 8) & 0xFF;
                        int blueOne = dyeDataColor.colorOne() & 0xFF;

                        int redTwo = (dyeDataColor.colorTwo() >> 16) & 0xFF;
                        int greenTwo = (dyeDataColor.colorTwo() >> 8) & 0xFF;
                        int blueTwo = dyeDataColor.colorTwo() & 0xFF;

                        int colorRed = Mth.lerpInt(l, redTwo, redOne);
                        int colorGreen = Mth.lerpInt(l, greenTwo, greenOne);
                        int colorBlue = Mth.lerpInt(l, blueTwo, blueOne);

                        return 255 << 24 | colorRed << 16 | colorGreen << 8 | colorBlue;
                    }

                    return -1;
                }
        );

        DyeTypeRegistry.registerDyeType(
                "health",
                new DyeIcon(ResourceLocation.fromNamespaceAndPath("weaversparadise", "textures/tooltips/heart_full.png")),
                Component.translatable("weaversparadise.dye_text.health"),
                (dyeData) -> {
                    Minecraft minecraft = Minecraft.getInstance();
                    float health = 20;
                    float maxHealth = 20;
                    if (minecraft.player != null) {
                        health = minecraft.player.getHealth();
                        maxHealth = minecraft.player.getMaxHealth();
                    }
                    float l = health / maxHealth;

                    int redOne = (dyeData.getColorOne() >> 16) & 0xFF;
                    int greenOne = (dyeData.getColorOne() >> 8) & 0xFF;
                    int blueOne = dyeData.getColorOne() & 0xFF;

                    int redTwo = (dyeData.getColorTwo() >> 16) & 0xFF;
                    int greenTwo = (dyeData.getColorTwo() >> 8) & 0xFF;
                    int blueTwo = dyeData.getColorTwo() & 0xFF;

                    int colorRed = Mth.lerpInt(l, redTwo, redOne);
                    int colorGreen = Mth.lerpInt(l, greenTwo, greenOne);
                    int colorBlue = Mth.lerpInt(l, blueTwo, blueOne);

                    return dyeData.getComponent().copy().withStyle(style -> style.withColor(colorRed << 16 | colorGreen << 8 | colorBlue));
                },
                (dyeDataColor) -> {
                    if (dyeDataColor.layer() == 1) {
                        Minecraft minecraft = Minecraft.getInstance();
                        float health = 20;
                        float maxHealth = 20;
                        if (minecraft.player != null) {
                            health = minecraft.player.getHealth();
                            maxHealth = minecraft.player.getMaxHealth();
                        }
                        float l = health / maxHealth;

                        int redOne = (dyeDataColor.colorOne() >> 16) & 0xFF;
                        int greenOne = (dyeDataColor.colorOne() >> 8) & 0xFF;
                        int blueOne = dyeDataColor.colorOne() & 0xFF;

                        int redTwo = (dyeDataColor.colorTwo() >> 16) & 0xFF;
                        int greenTwo = (dyeDataColor.colorTwo() >> 8) & 0xFF;
                        int blueTwo = dyeDataColor.colorTwo() & 0xFF;

                        int colorRed = Mth.lerpInt(l, redTwo, redOne);
                        int colorGreen = Mth.lerpInt(l, greenTwo, greenOne);
                        int colorBlue = Mth.lerpInt(l, blueTwo, blueOne);

                        return 255 << 24 | colorRed << 16 | colorGreen << 8 | colorBlue;
                    }

                    return -1;
                }
        );

        DyeTypeRegistry.registerDyeType(
                "day_time",
                new ItemDyeIcon(Items.SUNFLOWER),
                Component.translatable("weaversparadise.dye_text.day_time"),
                (dyeData) -> {
                    Minecraft minecraft = Minecraft.getInstance();
                    int time = 0;
                    if (minecraft.level != null) {
                        time = (int)minecraft.level.getDayTime() % 24000;
                    }

                    float t;
                    if (time >= 6000 && time <= 18000) {
                        t = (time - 6000) / 12000.0f;
                    } else {
                        int wrappedTime = time < 6000 ? time + 24000 : time;
                        t = 1.0f - ((wrappedTime - 18000) / 12000.0f);
                    }

                    int color = lerpColor(0xffdd40, 0x191970, t);

                    return dyeData.getComponent().copy().withStyle(style -> style.withColor(color));
                },
                (dyeDataColor) -> {
                    if (dyeDataColor.layer() == 1) {
                        Minecraft minecraft = Minecraft.getInstance();
                        int time = 0;
                        if (minecraft.level != null) {
                            time = (int)minecraft.level.getDayTime() % 24000;
                        }

                        float t;
                        if (time >= 6000 && time <= 18000) {
                            t = (time - 6000) / 12000.0f;
                        } else {
                            int wrappedTime = time < 6000 ? time + 24000 : time;
                            t = 1.0f - ((wrappedTime - 18000) / 12000.0f);
                        }

                        return lerpColor(0xffdd40, 0x191970, t);
                    }

                    return -1;
                }
        );

        DyeTypeRegistry.registerDyeType(
                "colored_day_time",
                new ItemDyeIcon(Items.CLOCK),
                Component.translatable("weaversparadise.dye_text.day_time"),
                (dyeData) -> {
                    Minecraft minecraft = Minecraft.getInstance();
                    int time = 0;
                    if (minecraft.level != null) {
                        time = (int)minecraft.level.getDayTime() % 24000;
                    }

                    float t;
                    if (time >= 6000 && time <= 18000) {
                        t = (time - 6000) / 12000.0f;
                    } else {
                        int wrappedTime = time < 6000 ? time + 24000 : time;
                        t = 1.0f - ((wrappedTime - 18000) / 12000.0f);
                    }

                    int color = lerpColor(dyeData.getColorOne(), dyeData.getColorTwo(), t);

                    return dyeData.getComponent().copy().withStyle(style -> style.withColor(color));
                },
                (dyeDataColor) -> {
                    if (dyeDataColor.layer() == 1) {
                        Minecraft minecraft = Minecraft.getInstance();
                        int time = 0;
                        if (minecraft.level != null) {
                            time = (int)minecraft.level.getDayTime() % 24000;
                        }

                        float t;
                        if (time >= 6000 && time <= 18000) {
                            t = (time - 6000) / 12000.0f;
                        } else {
                            int wrappedTime = time < 6000 ? time + 24000 : time;
                            t = 1.0f - ((wrappedTime - 18000) / 12000.0f);
                        }

                        return lerpColor(dyeDataColor.colorOne(), dyeDataColor.colorTwo(), t);
                    }

                    return -1;
                }
        );

        DyeTypeRegistry.registerDyeType(
                "starfall",
                new ItemDyeIcon(Items.BARRIER),
                Component.translatable("weaversparadise.dye_text.starfall"),
                (dyeData) -> {
                    Minecraft minecraft = Minecraft.getInstance();
                    int ticks = 0;
                    if (minecraft.level != null) {
                        ticks = (int)minecraft.level.getGameTime();
                    }
                    String val = dyeData.getComponent().getString();

                    int baseRed = (dyeData.getColorOne() >> 16) & 0xFF;
                    int baseGreen = (dyeData.getColorOne() >> 8) & 0xFF;
                    int baseBlue = dyeData.getColorOne() & 0xFF;
                    int glowRed = 255;
                    int glowGreen = 255;
                    int glowBlue = 255;

                    MutableComponent mutableComponent = Component.empty();

                    for (int i = 0; i < val.length(); i++) {
                        char c = val.charAt(i);

                        float t = (float)(Math.sin((ticks + FIXED_SEQUENCE[i]) * 0.5) + 1.0f) / 2.0f;
                        int colorRed = Mth.lerpInt(t, baseRed, glowRed);
                        int colorGreen = Mth.lerpInt(t, baseGreen, glowGreen);
                        int colorBlue = Mth.lerpInt(t, baseBlue, glowBlue);

                        int color = 255 << 24 | colorRed << 16 | colorGreen << 8 | colorBlue;

                        mutableComponent.append(Component.literal(String.valueOf(c)).withStyle(style -> style.withColor(color)));
                    }

                    return mutableComponent;
                },
                (dyeDataColor) -> {
                    if (dyeDataColor.layer() == 1) {
                        Minecraft minecraft = Minecraft.getInstance();
                        int ticks = 0;
                        if (minecraft.level != null) {
                            ticks = (int)minecraft.level.getGameTime();
                        }

                        int baseRed = (dyeDataColor.colorOne() >> 16) & 0xFF;
                        int baseGreen = (dyeDataColor.colorOne() >> 8) & 0xFF;
                        int baseBlue = dyeDataColor.colorOne() & 0xFF;
                        int glowRed = 255;
                        int glowGreen = 255;
                        int glowBlue = 255;

                        float t = (float)(Math.sin((ticks + FIXED_SEQUENCE[0]) * 0.5) + 1.0f) / 2.0f;
                        int colorRed = Mth.lerpInt(t, baseRed, glowRed);
                        int colorGreen = Mth.lerpInt(t, baseGreen, glowGreen);
                        int colorBlue = Mth.lerpInt(t, baseBlue, glowBlue);

                        return 255 << 24 | colorRed << 16 | colorGreen << 8 | colorBlue;
                    }

                    return -1;
                },
                (renderData) -> {
                    return WeaversParadiseRenderTypes.getEntityStarfall(
                            renderData.resourceLocation(),
                            TheEndPortalRenderer.END_PORTAL_LOCATION
                    );
                }
        );
    }

    public static int getRainbowColorFromTick(int ticks) {
        float speed = 0.05F;

        float red = Mth.clamp((float)(Math.sin(ticks * speed) * 0.5 + 0.5), 0, 1);
        float green = Mth.clamp((float)(Math.sin(ticks * speed + 2 * Math.PI / 3) * 0.5 + 0.5), 0, 1);
        float blue = Mth.clamp((float)(Math.sin(ticks * speed + 4 * Math.PI / 3) * 0.5 + 0.5), 0, 1);

        int tRed = (int)(red * 255);
        int tGreen = (int)(green * 255);
        int tBlue = (int)(blue * 255);

        return (tRed << 16) | (tGreen << 8) | tBlue;
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
