package xox.labvorty.weaversparadise.data.tooltip_components;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import xox.labvorty.weaversparadise.data.tooltip_components.helper.DyeTooltipEntry;

import java.util.List;

@OnlyIn(Dist.CLIENT)
public class ClothingTooltipComponent implements TooltipComponent {
    private final List<ResourceLocation> qualityTextures;
    private final List<DyeTooltipEntry> entries;

    public ClothingTooltipComponent(List<ResourceLocation> qualityTextures, List<DyeTooltipEntry> entries) {
        this.qualityTextures = qualityTextures;
        this.entries = entries;
    }

    public List<ResourceLocation> getQualityTextures() {
        return qualityTextures;
    }

    public List<DyeTooltipEntry> getEntries() {
        return entries;
    }

    public int getWidth() {
        return 8;
    }

    public int getHeight() {
        return 9 + (entries.size() * 9);
    }
}