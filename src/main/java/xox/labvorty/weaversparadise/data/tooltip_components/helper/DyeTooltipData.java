package xox.labvorty.weaversparadise.data.tooltip_components.helper;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import xox.labvorty.weaversparadise.data.tooltip_components.DyeTypeRegistry;

public class DyeTooltipData {
    public static MutableComponent parse(String type, int primaryColor, int secondaryColor, int lightValue) {
        Component dyeText = DyeTypeRegistry.getDyeType(type).getComponent();

        DyeInstance dyeInstance = DyeTypeRegistry.getDyeType(type);
        return dyeInstance.getNameParser().apply(
                new DyeData(
                        dyeText,
                        primaryColor,
                        secondaryColor,
                        lightValue
                )
        );
    }
}
