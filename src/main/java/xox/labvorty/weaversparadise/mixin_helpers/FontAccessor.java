package xox.labvorty.weaversparadise.mixin_helpers;

import net.minecraft.client.gui.font.FontSet;
import net.minecraft.resources.ResourceLocation;

import java.util.function.Function;

public interface FontAccessor {
    Function<ResourceLocation, FontSet> getFonts();
    boolean filterFishyGlyphs();
}
