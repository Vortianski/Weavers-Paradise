package xox.labvorty.weaversparadise.data.tooltip_components.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;
import xox.labvorty.weaversparadise.data.tooltip_components.DyeIcon;
import xox.labvorty.weaversparadise.data.tooltip_components.DyeTypeRegistry;
import xox.labvorty.weaversparadise.data.tooltip_components.helper.DyeTooltipData;

@OnlyIn(Dist.CLIENT)
public class DyeClientTooltipComponent implements ClientTooltipComponent {
    private final DyeIcon dyeIcon;
    private final String text;
    private final String type;
    private final int width;
    private final int height;
    private final int lightValue;
    private final int primaryColor;
    private final int secondaryColor;

    public DyeClientTooltipComponent(DyeIcon dyeIcon, String text, String type, int lightValue, int primaryColor, int secondaryColor) {
        this(dyeIcon, text, type, lightValue, 8, 8, primaryColor, secondaryColor);
    }

    public DyeClientTooltipComponent(DyeIcon dyeIcon, String text, String type, int lightValue, int width, int height, int primaryColor, int secondaryColor) {
        this.dyeIcon = dyeIcon;
        this.text = text;
        this.type = type;
        this.lightValue = lightValue;
        this.width = width;
        this.height = height;
        this.primaryColor = primaryColor;
        this.secondaryColor = secondaryColor;
    }

    @Override
    public int getHeight() {
        return height + 2;
    }

    @Override
    public int getWidth(Font font) {
        return (width + 2 + font.width(DyeTypeRegistry.getDyeType(this.text).getComponent()));
    }

    @Override
    public void renderImage(@NotNull Font font, int x, int y, @NotNull GuiGraphics guiGraphics) {
        RenderSystem.enableBlend();

        Minecraft mc = Minecraft.getInstance();
        int ticks = (int)mc.level.getGameTime();

        dyeIcon.render(guiGraphics, x, y);
        guiGraphics.drawString(font, DyeTooltipData.parse(type, primaryColor, secondaryColor, lightValue), x + (width + 2), y + 1, 0xFFFFFF);

        RenderSystem.disableBlend();
    }
}
