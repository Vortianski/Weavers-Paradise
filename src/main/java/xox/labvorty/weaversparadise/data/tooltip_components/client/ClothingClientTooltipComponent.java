package xox.labvorty.weaversparadise.data.tooltip_components.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import xox.labvorty.weaversparadise.data.tooltip_components.DyeTypeRegistry;
import xox.labvorty.weaversparadise.data.tooltip_components.helper.DyeTooltipData;
import xox.labvorty.weaversparadise.data.tooltip_components.helper.DyeTooltipEntry;

import java.util.List;

@OnlyIn(Dist.CLIENT)
public class ClothingClientTooltipComponent implements ClientTooltipComponent {
    private final List<ResourceLocation> textures;
    private final List<DyeTooltipEntry> entries;

    public ClothingClientTooltipComponent(List<ResourceLocation> textures, List<DyeTooltipEntry> entries) {
        this.textures = textures;
        this.entries = entries;
    }

    @Override
    public int getHeight() {
        return 9 + (entries.size() * 9) + 2;
    }

    @Override
    public int getWidth(Font font) {
        int maxTextWidth = 0;

        for (DyeTooltipEntry entry : entries) {
            maxTextWidth = Math.max(maxTextWidth, font.width(DyeTypeRegistry.getDyeType(entry.textKey()).getB()));
        }

        return 8 + 2 + maxTextWidth;
    }

    @Override
    public void renderImage(Font font, int x, int y, GuiGraphics guiGraphics) {
        RenderSystem.enableBlend();

        Minecraft mc = Minecraft.getInstance();
        int ticks = mc.level != null ? (int) mc.level.getGameTime() : 0;

        for (int i = 0; i < textures.size(); i++) {
            guiGraphics.blit(textures.get(i), x + (i * 9), y, 0, 0, 8, 8, 8, 8);
        }

        for (int i = 0; i < entries.size(); i++) {
            DyeTooltipEntry entry = entries.get(i);
            int rowY = y + 9 + (i * 9);

            entry.dyeIcon().render(guiGraphics, x, rowY);
            guiGraphics.drawString(
                    font,
                    DyeTooltipData.parse(
                            entry.textKey(),
                            entry.type(),
                            ticks,
                            entry.primaryColor(),
                            entry.secondaryColor(),
                            entry.lightValue()
                    ),
                    x + 10,
                    rowY + 1,
                    0xFFFFFF
            );
        }

        RenderSystem.disableBlend();
    }
}