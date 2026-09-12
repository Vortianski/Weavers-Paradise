package xox.labvorty.weaversparadise.data.tooltip_components.helper;

import net.minecraft.nbt.CompoundTag;

public record DyeDataColor(
        int layer,
        CompoundTag compoundTag,
        int colorOne,
        int colorTwo,
        int light
) {}