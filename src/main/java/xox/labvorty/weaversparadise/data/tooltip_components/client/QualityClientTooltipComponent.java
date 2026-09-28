package xox.labvorty.weaversparadise.data.tooltip_components.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@OnlyIn(Dist.CLIENT)
public class QualityClientTooltipComponent implements ClientTooltipComponent {
    private final List<ResourceLocation> textures;

    public QualityClientTooltipComponent(
            List<ResourceLocation> textures
    ) {
        this.textures = textures;
    }

    @Override
    public int getHeight() {
        return 10;
    }

    @Override
    public int getWidth(@NotNull Font font) {
        return 5 * 9;
    }

    @Override
    public void renderImage(Font font, int x, int y, GuiGraphics guiGraphics) {
        RenderSystem.enableBlend();

        for (int i = 0; i < textures.size(); i++) {
            guiGraphics.blit(textures.get(i), x + (i * 9), y, 0, 0, 8, 8, 8, 8);
        }

        RenderSystem.disableBlend();
    }
}
