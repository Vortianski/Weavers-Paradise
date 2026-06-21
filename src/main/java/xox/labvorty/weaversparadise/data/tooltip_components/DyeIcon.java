package xox.labvorty.weaversparadise.data.tooltip_components;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;

public class DyeIcon {
    protected final ResourceLocation resourceLocation;
    protected final int width;
    protected final int height;

    public DyeIcon(ResourceLocation resourceLocation) {
        this(resourceLocation, 8, 8);
    }

    public DyeIcon(
            ResourceLocation resourceLocation,
            int width,
            int height
    ) {
        this.resourceLocation = resourceLocation;
        this.width = width;
        this.height = height;
    }

    public void render(GuiGraphics guiGraphics, int x, int y) {
        guiGraphics.blit(resourceLocation, x, y, 0, 0, width, height, width, height);
    }
}
