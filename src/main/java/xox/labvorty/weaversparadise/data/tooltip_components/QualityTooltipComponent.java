package xox.labvorty.weaversparadise.data.tooltip_components;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.tooltip.TooltipComponent;

import java.util.List;

public class QualityTooltipComponent implements TooltipComponent {
    private final List<ResourceLocation> textures;

    public QualityTooltipComponent(
            List<ResourceLocation> textures
    ) {
        this.textures = textures;
    }

    public List<ResourceLocation> getTextures() {
        return textures;
    }
}
