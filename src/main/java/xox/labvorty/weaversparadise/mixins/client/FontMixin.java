package xox.labvorty.weaversparadise.mixins.client;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.font.FontSet;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import xox.labvorty.weaversparadise.mixin_helpers.FontAccessor;

import java.util.function.Function;

@Mixin(Font.class)
public class FontMixin implements FontAccessor {
    @Shadow
    @Final
    private Function<ResourceLocation, FontSet> fonts;

    @Shadow
    @Final
    private boolean filterFishyGlyphs;

    @Override
    public Function<ResourceLocation, FontSet> getFonts() {
        return fonts;
    }

    @Override
    public boolean filterFishyGlyphs() {
        return filterFishyGlyphs;
    }
}
