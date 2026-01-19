package xox.labvorty.weaversparadise.renderers;

public class WeaversParadiseGlobalRendererDataHolder {
    private static int sculkPulse = 0;

    public static void updateSculkPulse(int pulse) {
        sculkPulse = pulse;
    }

    public static int getSculkPulse() {
        return sculkPulse;
    }
}
