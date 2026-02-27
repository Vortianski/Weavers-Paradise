package xox.labvorty.weaversparadise.tooltips;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.api.distmarker.Dist;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.client.gui.GuiGraphics;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.Font;
import net.minecraft.resources.ResourceLocation;
import xox.labvorty.weaversparadise.data.WeaversParadiseDyeTextHandler;

import java.util.List;

@OnlyIn(Dist.CLIENT)
public class ImageClientTooltipComponent implements ClientTooltipComponent {
    private final List<ResourceLocation> textures;
    private int width;
    private int height;
    private final ResourceLocation textureOne;
    private final String textOne;
    private final String typeOne;
    private final int lightValueOne;
    private final int primaryLeftColorOne;
    private final int secondaryLeftColorOne;
    private final ResourceLocation textureTwo;
    private final String textTwo;
    private final String typeTwo;
    private final int lightValueTwo;
    private final int primaryLeftColorTwo;
    private final int secondaryLeftColorTwo;
    private final ResourceLocation textureThree;
    private final String textThree;
    private final String typeThree;
    private final int lightValueThree;
    private final int primaryRightColorOne;
    private final int secondaryRightColorOne;
    private final ResourceLocation textureFour;
    private final String textFour;
    private final String typeFour;
    private final int lightValueFour;
    private final int primaryRightColorTwo;
    private final int secondaryRightColorTwo;

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

    public ImageClientTooltipComponent(
            List<ResourceLocation> textures,
            ResourceLocation textureOne,
            String textOne,
            String typeOne,
            int lightValueOne,
            int primaryLeftColorOne,
            int secondaryLeftColorOne,
            ResourceLocation textureTwo,
            String textTwo,
            String typeTwo,
            int lightValueTwo,
            int primaryLeftColorTwo,
            int secondaryLeftColorTwo,
            ResourceLocation textureThree,
            String textThree,
            String typeThree,
            int lightValueThree,
            int primaryRightColorOne,
            int secondaryRightColorOne,
            ResourceLocation textureFour,
            String textFour,
            String typeFour,
            int lightValueFour,
            int primaryRightColorTwo,
            int secondaryRightColorTwo
    ) {
        this(
                textures,
                8,
                typeOne.equals("none") ? 8 : typeTwo.equals("none") ? 18 : typeThree.equals("none") ? 28 : typeFour.equals("none") ? 38 : 48,
                textureOne,
                textOne,
                typeOne,
                lightValueOne,
                primaryLeftColorOne,
                secondaryLeftColorOne,
                textureTwo,
                textTwo,
                typeTwo,
                lightValueTwo,
                primaryLeftColorTwo,
                secondaryLeftColorTwo,
                textureThree,
                textThree,
                typeThree,
                lightValueThree,
                primaryRightColorOne,
                secondaryRightColorOne,
                textureFour,
                textFour,
                typeFour,
                lightValueFour,
                primaryRightColorTwo,
                secondaryRightColorTwo
        );
    }

    public ImageClientTooltipComponent(
            List<ResourceLocation> textures,
            int width,
            int height,
            ResourceLocation textureOne,
            String textOne,
            String typeOne,
            int lightValueOne,
            int primaryLeftColorOne,
            int secondaryLeftColorOne,
            ResourceLocation textureTwo,
            String textTwo,
            String typeTwo,
            int lightValueTwo,
            int primaryLeftColorTwo,
            int secondaryLeftColorTwo,
            ResourceLocation textureThree,
            String textThree,
            String typeThree,
            int lightValueThree,
            int primaryRightColorOne,
            int secondaryRightColorOne,
            ResourceLocation textureFour,
            String textFour,
            String typeFour,
            int lightValueFour,
            int primaryRightColorTwo,
            int secondaryRightColorTwo
    ) {
        this.textures = textures;
        this.width = width;
        this.height = height;
        this.textureOne = textureOne;
        this.textOne = textOne;
        this.typeOne = typeOne;
        this.lightValueOne = lightValueOne;
        this.primaryLeftColorOne = primaryLeftColorOne;
        this.secondaryLeftColorOne = secondaryLeftColorOne;
        this.textureTwo = textureTwo;
        this.textTwo = textTwo;
        this.typeTwo = typeTwo;
        this.lightValueTwo = lightValueTwo;
        this.primaryLeftColorTwo = primaryLeftColorTwo;
        this.secondaryLeftColorTwo = secondaryLeftColorTwo;
        this.textureThree = textureThree;
        this.textThree = textThree;
        this.typeThree = typeThree;
        this.lightValueThree = lightValueThree;
        this.primaryRightColorOne = primaryRightColorOne;
        this.secondaryRightColorOne = secondaryRightColorOne;
        this.textureFour = textureFour;
        this.textFour = textFour;
        this.typeFour = typeFour;
        this.lightValueFour = lightValueFour;
        this.primaryRightColorTwo = primaryRightColorTwo;
        this.secondaryRightColorTwo = secondaryRightColorTwo;
    }

    @Override
    public int getHeight() {
        return height + 2;
    }

    @Override
    public int getWidth(Font font) {
        return Math.max(
                Math.max(
                        width + 2 + font.width(WeaversParadiseDyeTextHandler.getOrDefault(this.textOne).getText()),
                        width + 2 + font.width(WeaversParadiseDyeTextHandler.getOrDefault(this.textTwo).getText())
                ),
                Math.max(
                        width + 2 + font.width(WeaversParadiseDyeTextHandler.getOrDefault(this.textThree).getText()),
                        width + 2 + font.width(WeaversParadiseDyeTextHandler.getOrDefault(this.textFour).getText())
                )
        );
    }

    @Override
    public void renderImage(Font font, int x, int y, GuiGraphics guiGraphics) {
        RenderSystem.enableBlend();

        Minecraft mc = Minecraft.getInstance();
        int ticks = (int)mc.level.getGameTime();

        for (int i = 0; i < textures.size(); i++) {
            guiGraphics.blit(textures.get(i), x + (i * 9), y, 0, 0, 8, 8, 8, 8);
        }

        guiGraphics.blit(this.textureOne, x, y + 9, 0, 0, 8, 8, 8, 8);
        guiGraphics.drawString(font, getComponentFromType(textOne, typeOne, ticks, primaryLeftColorOne, secondaryLeftColorOne, lightValueOne), x + 10, y + 10, 0xFFFFFF);

        guiGraphics.blit(this.textureTwo, x, y + 18, 0, 0, 8, 8, 8, 8);
        guiGraphics.drawString(font, getComponentFromType(textTwo, typeTwo, ticks, primaryLeftColorTwo, secondaryLeftColorTwo, lightValueTwo), x + 10, y + 19, 0xFFFFFF);

        guiGraphics.blit(this.textureThree, x, y + 27, 0, 0, 8, 8, 8, 8);
        guiGraphics.drawString(font, getComponentFromType(textThree, typeThree, ticks, primaryRightColorOne, secondaryRightColorOne, lightValueThree), x + 10, y + 28, 0xFFFFFF);

        guiGraphics.blit(this.textureFour, x, y + 36, 0, 0, 8, 8, 8, 8);
        guiGraphics.drawString(font, getComponentFromType(textFour, typeFour, ticks, primaryRightColorTwo, secondaryRightColorTwo, lightValueFour), x + 10, y + 37, 0xFFFFFF);


        RenderSystem.disableBlend();
    }

    public MutableComponent getComponentFromType(String stext, String type, int ticks, int primaryColor, int secondaryColor, int lightValue) {
        MutableComponent component = Component.empty();

        int primRed = (primaryColor >> 16) & 0xFF;
        int primGreen = (primaryColor >> 8) & 0xFF;
        int primBlue = primaryColor & 0xFF;

        int secRed = (secondaryColor >> 16) & 0xFF;
        int secGreen = (secondaryColor >> 8) & 0xFF;
        int secBlue = secondaryColor & 0xFF;

        String text = WeaversParadiseDyeTextHandler.getOrDefault(stext).getText();

        final List<Integer> prideColors = List.of(
                (0xE40303),
                (0xFF8C00),
                (0xFFED00),
                (0x008026),
                (0x004DFF),
                (0x750787)
        );

        final List<Integer> asexualColors = List.of(
                (0x2E2E2E),
                (0xA3A3A3),
                (0xFFFFFF),
                (0x800080)
        );

        final List<Integer> aromanticColors = List.of(
                (0x3DA542),
                (0xA7D379),
                (0xFFFFFF),
                (0xA9A9A9),
                (0x2E2E2E)
        );

        final List<Integer> aroaceColors = List.of(
                (0xE28C00),
                (0xFFD700),
                (0xFFFFFF),
                (0x00A4E0),
                (0x800080)
        );

        final List<Integer> agenderColors = List.of(
                (0x2E2E2E),
                (0xB9B9B9),
                (0xFFFFFF),
                (0xB8F483),
                (0xFFFFFF),
                (0xB9B9B9),
                (0x2E2E2E)
        );

        final List<Integer> bisexualColors = List.of(
                (0xD60270),
                (0xD60270),
                (0x9B4F96),
                (0x0038A8),
                (0x0038A8)
        );

        final List<Integer> demiboyColors = List.of(
                (0x2E2E2E),
                (0x7F7F7F),
                (0x9AD9EB),
                (0xFFFFFF),
                (0x9AD9EB),
                (0x7F7F7F),
                (0x2E2E2E)
        );

        final List<Integer> demigenderColors = List.of(
                (0x2E2E2E),
                (0x7F7F7F),
                (0xF9F06B),
                (0xFFFFFF),
                (0xF9F06B),
                (0x7F7F7F),
                (0x2E2E2E)
        );

        final List<Integer> demigirlColors = List.of(
                (0x2E2E2E),
                (0x7F7F7F),
                (0xFCACC9),
                (0xFFFFFF),
                (0xFCACC9),
                (0x7F7F7F),
                (0x2E2E2E)
        );

        final List<Integer> gayColors = List.of(
                (0x078D70),
                (0x26CEAA),
                (0x98E8C1),
                (0xFFFFFF),
                (0x7BADE3),
                (0x5049CC),
                (0x3D1A78)
        );

        final List<Integer> genderfluidColors = List.of(
                (0xFF75A2),
                (0xFFFFFF),
                (0xBE18D6),
                (0x2E2E2E),
                (0x333EBD)
        );

        final List<Integer> genderqueerColors = List.of(
                (0xB57EDC),
                (0xFFFFFF),
                (0x49822B)
        );

        final List<Integer> intersexColors = List.of(
                (0xFFD800),
                (0xFFD800),
                (0x7902AA),
                (0xFFD800),
                (0x7902AA),
                (0xFFD800),
                (0xFFD800)
        );

        final List<Integer> lesbianColors = List.of(
                (0xD62900),
                (0xFF9B55),
                (0xFFFFFF),
                (0xD461A6),
                (0xA50062)
        );

        final List<Integer> nonbinaryColors = List.of(
                (0xFFF430),
                (0xFFFFFF),
                (0x9C59D1),
                (0x2E2E2E)
        );

        final List<Integer> pansexualColors = List.of(
                (0xFF218C),
                (0xFFD800),
                (0x21B1FF)
        );

        final List<Integer> transColors = List.of(
                (0x5BCEFA),
                (0xF5A9B8),
                (0xFFFFFF),
                (0xF5A9B8),
                (0x5BCEFA)
        );

        if (type.equals("default")) {
            component = Component.literal(text).withStyle(style -> style.withColor(primaryColor));
        } else if (type.equals("ender")) {
            component = Component.literal(text).withStyle(style -> style.withColor(0x2e2e2e));
        } else if (type.equals("rainbow")) {
            for (int i = 0; i < text.length(); i++) {
                char c = text.charAt(i);
                int color = getRainbowColorFromTick(ticks + (3 * i));
                MutableComponent letter = Component.literal(String.valueOf(c)).withStyle(style -> style.withColor(color));
                component.append(letter);
            }
        } else if (type.equals("sculk")) {
            int length = text.length();
            int middle = length / 2;

            int pulseInterval = 70; //pulse interval
            int pulseDuration = 20; //pulse travel time
            int timeInCycle = ticks % pulseInterval;

            for (int i = 0; i < length; i++) {
                char c = text.charAt(i);

                // Distance from middle character
                int distFromMiddle = Math.abs(i - middle);

                // When does the pulse reach this character?
                int pulseStartTick = distFromMiddle * (pulseDuration / middle);

                // Base color dark cyan (hex: 0x008B8B)
                int baseColor = 0x05262D;

                // Pure cyan for pulse (hex: 0x00FFFF)
                int pulseColor = 0x00FFFF;

                int finalColor;

                // Check if pulse is active for this char
                if (timeInCycle >= pulseStartTick && timeInCycle <= pulseStartTick + pulseDuration) {
                    // Calculate fade factor (1.0 to 0.0)
                    float fade = 1.0f - (float)(timeInCycle - pulseStartTick) / pulseDuration;

                    // Blend pulseColor and baseColor based on fade
                    finalColor = blendColors(pulseColor, baseColor, fade);
                } else {
                    finalColor = baseColor;
                }

                MutableComponent letter = Component.literal(String.valueOf(c)).withStyle(style -> style.withColor(finalColor));
                component.append(letter);
            }
        } else if (type.equals("biome")) {
            Minecraft mine = Minecraft.getInstance();
            BlockPos bpos = new BlockPos((int)mine.player.getX(), (int)mine.player.getY(), (int)mine.player.getZ());
            int color = mine.level.getBiome(bpos).value().getGrassColor(mine.player.getX(), mine.player.getZ());

            component = Component.literal(text).withStyle(style -> style.withColor(color));
        } else if (type.equals("glowstone")) {
            // Configurable parameters
            int baseRed = 181;
            int baseGreen = 113;
            int baseBlue = 66;
            int glowRed = 248;
            int glowGreen = 212;
            int glowBlue = 156;

            for (int i = 0; i < text.length(); i++) {
                char c = text.charAt(i);

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

            component = Component.literal(text).withStyle(style -> style.withColor(255 << 24 |color << 16 | 0 << 8 | 0));
        } else if (type.equals("lamp")) {
            int lowestRed = (int)(primRed * 0.4);
            int lowestGreen = (int)(primGreen * 0.4);
            int lowestBlue = (int)(primBlue * 0.4);

            int finalRed = Mth.lerpInt(lightValue / 15.0f, lowestRed, primRed);
            int finalGreen = Mth.lerpInt(lightValue / 15.0f, lowestGreen, primGreen);
            int finalBlue = Mth.lerpInt(lightValue / 15.0f, lowestBlue, primBlue);

            int finalColor = 255 << 24 | finalRed << 16 | finalGreen << 8 | finalBlue;

            component = Component.literal(text).withStyle(style -> style.withColor(finalColor));
        } else if (type.equals("speed")) {
            Minecraft mc = Minecraft.getInstance();
            Vec3 vec3 = mc.player.getDeltaMovement();
            double velocity = vec3.lengthSqr();

            int colorOne = primaryColor;
            int colorTwo = secondaryColor;

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

            component = Component.literal(text).withStyle((style) -> {
                return style.withColor(finalColor);
            });
        } else if (type.equals("height_bedrock")) {
            Minecraft mc = Minecraft.getInstance();
            int absoluteMinimum = mc.level.getMinBuildHeight();
            int absoluteMaximum = mc.level.getMaxBuildHeight();
            int height = (int)mc.player.getY();

            float value = Mth.clamp(((float)height - (float)absoluteMinimum) / ((float)absoluteMaximum - (float)absoluteMinimum), 0, 1);

            int colorOne = primaryColor;
            int colorTwo = secondaryColor;

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

            component = Component.literal(text).withStyle(style -> style.withColor(finalColor));
        } else if (type.equals("height_sea")) {
            Minecraft mc = Minecraft.getInstance();
            double minY = mc.level.getMinBuildHeight();
            double maxY = mc.level.getMaxBuildHeight();
            double seaY = mc.level.getSeaLevel();
            double playerY = mc.player.getY();

            double distanceAbove = maxY - seaY;
            double distanceBelow = seaY - minY;
            double maxDistance = Math.max(distanceAbove, distanceBelow);
            float value = (float)Mth.clamp(Math.abs(playerY - seaY) / maxDistance, 0, 1);

            int colorOne = primaryColor;
            int colorTwo = secondaryColor;

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

            component = Component.literal(text).withStyle(style -> style.withColor(finalColor));
        } else if (type.equals("none")) {
            //do nothing
        } else if (type.equals("pride")) {
            component = flagText(text, prideColors, ticks);
        } else if (type.equals("asexual")) {
            component = flagText(text, asexualColors, ticks);
        } else if (type.equals("aromantic")) {
            component = flagText(text, aromanticColors, ticks);
        } else if (type.equals("aroace")) {
            component = flagText(text, aroaceColors, ticks);
        } else if (type.equals("agender")) {
            component = flagText(text, agenderColors, ticks);
        } else if (type.equals("bisexual")) {
            component = flagText(text, bisexualColors, ticks);
        } else if (type.equals("demiboy")) {
            component = flagText(text, demiboyColors, ticks);
        } else if (type.equals("demigender")) {
            component = flagText(text, demigenderColors, ticks);
        } else if (type.equals("demigirl")) {
            component = flagText(text, demigirlColors, ticks);
        } else if (type.equals("gay")) {
            component = flagText(text, gayColors, ticks);
        } else if (type.equals("genderfluid")) {
            component = flagText(text, genderfluidColors, ticks);
        } else if (type.equals("genderqueer")) {
            component = flagText(text, genderqueerColors, ticks);
        } else if (type.equals("intersex")) {
            component = flagText(text, intersexColors, ticks);
        } else if (type.equals("lesbian")) {
            component = flagText(text, lesbianColors, ticks);
        } else if (type.equals("nonbinary")) {
            component = flagText(text, nonbinaryColors, ticks);
        } else if (type.equals("pansexual")) {
            component = flagText(text, pansexualColors, ticks);
        } else if (type.equals("trans")) {
            component = flagText(text, transColors, ticks);
        } else if (type.equals("polychromatic")) {
            List<Integer> polychromisedColor = List.of(
                    primaryColor,
                    hueShift(primaryColor, 0.33f),
                    hueShift(primaryColor, 0.66f),
                    hueShift(primaryColor, 1)
            );

            component = flagText(text, polychromisedColor, ticks);
        } else if (type.equals("invisible")) {
            component = Component.literal(text);
        } else if (type.equals("static")) {
            int baseRed = 255;
            int baseGreen = 255;
            int baseBlue = 255;
            int glowRed = 170;
            int glowGreen = 170;
            int glowBlue = 170;

            for (int i = 0; i < text.length(); i++) {
                char c = text.charAt(i);

                float t = (float)(Math.sin((ticks + FIXED_SEQUENCE[i]) * 0.5) + 1.0f) / 2.0f;
                int colorRed = Mth.lerpInt(t, baseRed, glowRed);
                int colorGreen = Mth.lerpInt(t, baseGreen, glowGreen);
                int colorBlue = Mth.lerpInt(t, baseBlue, glowBlue);

                int color = 255 << 24 | colorRed << 16 | colorGreen << 8 | colorBlue;

                component.append(Component.literal(String.valueOf(c)).withStyle(style -> style.withColor(color)));
            }
        } else if (type.equals("crystal")) {
            component = Component.literal(text).withStyle(style -> style.withColor(primaryColor));
        } else if (type.equals("negative")) {
            List<Integer> colors = List.of(
                    primaryColor,
                    (255 << 24 | (255 - primRed) << 16 | (255 - primGreen) << 8 | (255 - primBlue))
            );

            component = flagText(text, colors, ticks);
        } else if (type.equals("true_negative")) {
            List<Integer> colors = List.of(
                    primaryColor,
                    (255 << 24 | (255 - primRed) << 16 | (255 - primGreen) << 8 | (255 - primBlue))
            );

            component = flagText(text, colors, ticks);
        } else if (type.equals("nebula")) {
            List<Integer> colors = List.of(
                    0x26103F,
                    0x26103F,
                    0xE673B2,
                    0x4CCCCD,
                    0x8C40A6
            );

            component = flagText(text, colors, ticks);
        }

        return component;
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

    public int getRainbowColorFromTick(int ticks) {
        float speed = 0.05F;

        float red = Mth.clamp((float)(Math.sin(ticks * speed) * 0.5 + 0.5), 0, 1);
        float green = Mth.clamp((float)(Math.sin(ticks * speed + 2 * Math.PI / 3) * 0.5 + 0.5), 0, 1);
        float blue = Mth.clamp((float)(Math.sin(ticks * speed + 4 * Math.PI / 3) * 0.5 + 0.5), 0, 1);

        int truered = (int)(red * 255);
        int truegreen = (int)(green * 255);
        int trueblue = (int)(blue * 255);

        return (truered << 16) | (truegreen << 8) | trueblue;
    }

    public int blendColors(int c1, int c2, float ratio) {
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

    public static int hueShift(int color, float hueShift) {
        // Extract ARGB
        int a = (color >> 24) & 0xFF;
        int r = (color >> 16) & 0xFF;
        int g = (color >> 8) & 0xFF;
        int b = color & 0xFF;

        // Convert to HSB
        float[] hsb = java.awt.Color.RGBtoHSB(r, g, b, null);

        // Shift hue (wraps around automatically)
        hsb[0] = (hsb[0] + hueShift) % 1.0f;
        if (hsb[0] < 0) hsb[0] += 1.0f;

        // Convert back to RGB
        int rgb = java.awt.Color.HSBtoRGB(hsb[0], hsb[1], hsb[2]);

        // Restore alpha
        return (a << 24) | (rgb & 0x00FFFFFF);
    }
}