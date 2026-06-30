package xox.labvorty.weaversparadise.data.tooltip_components.helper;

import net.minecraft.network.chat.Component;

public final class DyeData {
    private final Component component;
    private final int colorOne;
    private final int colorTwo;
    private final int light;

    public DyeData(
            Component component,
            int colorOne,
            int colorTwo,
            int light
    ) {
        this.component = component;
        this.colorOne = colorOne;
        this.colorTwo = colorTwo;
        this.light = light;
    }

    public Component getComponent() {
        return component;
    }

    public int getColorOne() {
        return colorOne;
    }

    public int getColorTwo() {
        return colorTwo;
    }

    public int getLight() {
        return light;
    }
}
