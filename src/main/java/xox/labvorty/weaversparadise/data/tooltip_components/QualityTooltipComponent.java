package xox.labvorty.weaversparadise.data.tooltip_components;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import java.util.List;

@OnlyIn(Dist.CLIENT)
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
