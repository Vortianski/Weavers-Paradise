package xox.labvorty.weaversparadise.data.text;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.util.Mth;

import java.util.List;

public class ComponentFormatter {
    public static MutableComponent gradientText(String text, List<Integer> colors, int ticks) {
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
        if (colors.size() == 1) return colors.getFirst();

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

    public static int lerpColor(int c1, int c2, float t) {
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
