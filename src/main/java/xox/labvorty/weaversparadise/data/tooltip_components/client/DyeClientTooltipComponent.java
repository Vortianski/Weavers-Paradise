package xox.labvorty.weaversparadise.data.tooltip_components.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import xox.labvorty.weaversparadise.data.tooltip_components.data.DyeTextHandler;
import xox.labvorty.weaversparadise.data.tooltip_components.helper.DyeTooltipData;

@OnlyIn(Dist.CLIENT)
public class DyeClientTooltipComponent implements ClientTooltipComponent {
    private final ResourceLocation texture;
    private final String text;
    private final String type;
    private final int width;
    private final int height;
    private final int lightValue;
    private final int primaryColor;
    private final int secondaryColor;

    public DyeClientTooltipComponent(ResourceLocation texture, String text, String type, int lightValue, int primaryColor, int secondaryColor) {
        this(texture, text, type, lightValue, 8, 8, primaryColor, secondaryColor);
    }

    public DyeClientTooltipComponent(ResourceLocation texture, String text, String type, int lightValue, int width, int height, int primaryColor, int secondaryColor) {
        this.texture = texture;
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
        return (width + 2 + font.width(DyeTextHandler.getOrDefault(this.text).getText()));
    }

    @Override
    public void renderImage(Font font, int x, int y, GuiGraphics guiGraphics) {
        RenderSystem.enableBlend();

        Minecraft mc = Minecraft.getInstance();
        int ticks = (int)mc.level.getGameTime();

        guiGraphics.blit(this.texture, x, y, 0, 0, width, height, width, height);
        guiGraphics.drawString(font, DyeTooltipData.parse(text, type, ticks, primaryColor, secondaryColor, lightValue), x + (width + 2), y + 1, 0xFFFFFF);

        RenderSystem.disableBlend();
    }
}
