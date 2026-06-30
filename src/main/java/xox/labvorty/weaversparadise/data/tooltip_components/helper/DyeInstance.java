package xox.labvorty.weaversparadise.data.tooltip_components.helper;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import xox.labvorty.weaversparadise.data.tooltip_components.DyeIcon;

import java.util.function.Function;

public final class DyeInstance {
    private final DyeIcon dyeIcon;
    private final Component component;
    private final Function<DyeData, MutableComponent> nameParser;
    private final Function<DyeDataColor, Integer> colorParser;
    private final Function<RenderData, RenderType> renderTypeParser;

    public DyeInstance(
            DyeIcon dyeIcon,
            Component component,
            Function<DyeData, MutableComponent> nameParser,
            Function<DyeDataColor, Integer> colorParser,
            Function<RenderData, RenderType> renderTypeParser
    ) {
        this.dyeIcon = dyeIcon;
        this.component = component;
        this.nameParser = nameParser;
        this.colorParser = colorParser;
        this.renderTypeParser = renderTypeParser;
    }

    public DyeIcon getDyeIcon() {
        return dyeIcon;
    }

    public Component getComponent() {
        return component;
    }

    public Function<DyeData, MutableComponent> getNameParser() {
        return nameParser;
    }

    public Function<DyeDataColor, Integer> getColorParser() {
        return colorParser;
    }

    public Function<RenderData, RenderType> getRenderTypeParser() {
        return renderTypeParser;
    }
}
