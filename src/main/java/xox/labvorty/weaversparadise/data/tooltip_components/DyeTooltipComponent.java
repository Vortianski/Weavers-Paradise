package xox.labvorty.weaversparadise.data.tooltip_components;

import net.minecraft.world.inventory.tooltip.TooltipComponent;

public class DyeTooltipComponent implements TooltipComponent {
    private final DyeIcon dyeIcon;
    private final String text;
    private final String type;
    private final int lightValue;
    private final int primaryColor;
    private final int secondaryColor;
    private final boolean isCore;

    public DyeTooltipComponent(DyeIcon dyeIcon, String text, String type, int lightValue, int primaryColor, int secondaryColor, boolean isCore) {
        this.dyeIcon = dyeIcon;
        this.text = text;
        this.type = type;
        this.lightValue = lightValue;
        this.primaryColor = primaryColor;
        this.secondaryColor = secondaryColor;
        this.isCore = isCore;
    }

    public int getWidth() {
        return 8;
    }

    public int getHeight() {
        return 8;
    }

    public DyeIcon getDyeIcon() {
        return this.dyeIcon;
    }

    public String getText() {
        return this.text;
    }

    public String getType() {
        return this.type;
    }

    public int getLightValue() {
        return this.lightValue;
    }

    public int getPrimaryColor() {
        return this.primaryColor;
    }

    public int getSecondaryColor() {
        return this.secondaryColor;
    }

    public boolean isCore() {
        return isCore;
    }
}