package xox.labvorty.weaversparadise.data.tooltip_components.helper;

import xox.labvorty.weaversparadise.data.tooltip_components.DyeIcon;

public record DyeTooltipEntry(
        DyeIcon dyeIcon,
        String textKey,
        String type,
        int lightValue,
        int primaryColor,
        int secondaryColor
) {}