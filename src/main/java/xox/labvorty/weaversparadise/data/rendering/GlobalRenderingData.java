package xox.labvorty.weaversparadise.data.rendering;

public class GlobalRenderingData {
    private static int sculkPulse = 0;

    public static void updateSculkPulse(int pulse) {
        sculkPulse = pulse;
    }

    public static int getSculkPulse() {
        return sculkPulse;
    }
}
