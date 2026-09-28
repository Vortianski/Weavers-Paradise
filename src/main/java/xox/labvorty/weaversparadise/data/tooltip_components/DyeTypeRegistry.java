package xox.labvorty.weaversparadise.data.tooltip_components;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import xox.labvorty.weaversparadise.data.tooltip_components.helper.DyeData;
import xox.labvorty.weaversparadise.data.tooltip_components.helper.DyeDataColor;
import xox.labvorty.weaversparadise.data.tooltip_components.helper.DyeInstance;
import xox.labvorty.weaversparadise.data.tooltip_components.helper.RenderData;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class DyeTypeRegistry {
    private static final Map<String, DyeInstance> dyeType = new HashMap<>();
    private static final DyeInstance DEFAULT = new DyeInstance(
            new DyeIcon(ResourceLocation.fromNamespaceAndPath("weaversparadise", "textures/tooltips/flag_empty.png")),
            Component.translatable("weaversparadise.dye_text.default"),
            (dyeData) -> {
                int colorOne = dyeData.getColorOne();

                return dyeData.getComponent().copy().withStyle(style -> style.withColor(colorOne));
            },
            (dyeDataColor) -> {
                if (dyeDataColor.layer() == 1) {
                    return dyeDataColor.colorOne();
                }

                return -1;
            },
            (renderData) -> RenderType.entityTranslucent(renderData.resourceLocation())
    );

    public static void registerDyeType(
            String name,
            DyeIcon dyeIcon,
            Component component,
            Function<DyeData, MutableComponent> nameParser,
            Function<DyeDataColor, Integer> colorParser
    ) {
        registerDyeType(name, dyeIcon, component, nameParser, colorParser, (renderData) -> RenderType.entityTranslucent(renderData.resourceLocation()));
    }

    public static void registerDyeType(
            String name,
            DyeIcon dyeIcon,
            Component component,
            Function<DyeData, MutableComponent> nameParser,
            Function<DyeDataColor, Integer> colorParser,
            Function<RenderData, RenderType> renderTypeParser
    ) {
        dyeType.put(
                name,
                new DyeInstance(
                        dyeIcon,
                        component,
                        nameParser,
                        colorParser,
                        renderTypeParser
                )
        );
    }

    public static DyeInstance getDyeType(String s) {
        return dyeType.getOrDefault(s, DEFAULT);
    }
}
