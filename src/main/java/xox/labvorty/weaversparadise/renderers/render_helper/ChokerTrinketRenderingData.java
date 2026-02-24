package xox.labvorty.weaversparadise.renderers.render_helper;

public class ChokerTrinketRenderingData {
    private final int color;
    private final String metalType;

    public ChokerTrinketRenderingData(
            int color,
            String metalType
    ) {
        this.color = color;
        this.metalType = metalType;
    }

    public int getColor() {
        return color;
    }

    public String getMetalType() {
        return metalType;
    }
}
