package xox.labvorty.weaversparadise.data.tooltip_components.helper;

import net.minecraft.resources.ResourceLocation;

public record DyeTooltipEntry(
        ResourceLocation texture,
        String textKey,
        String type,
        int lightValue,
        int primaryColor,
        int secondaryColor
) {}