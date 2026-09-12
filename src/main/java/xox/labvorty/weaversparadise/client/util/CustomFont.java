package xox.labvorty.weaversparadise.client.util;

import com.google.common.collect.Lists;
import com.ibm.icu.text.ArabicShaping;
import com.ibm.icu.text.ArabicShapingException;
import com.ibm.icu.text.Bidi;
import com.mojang.blaze3d.font.GlyphInfo;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.font.FontSet;
import net.minecraft.client.gui.font.glyphs.BakedGlyph;
import net.minecraft.client.gui.font.glyphs.EmptyGlyph;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.locale.Language;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.util.FormattedCharSink;
import net.minecraft.util.StringDecomposer;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import xox.labvorty.weaversparadise.mixin_helpers.FontAccessor;

import org.jetbrains.annotations.Nullable;
import java.util.List;
import java.util.function.Function;

public class CustomFont {
    private static final Vector3f SHADOW_OFFSET = new Vector3f(0.0F, 0.0F, 0.03F);
    private final Function<ResourceLocation, FontSet> fonts;
    final boolean filterFishyGlyphs;


    public CustomFont(Function<ResourceLocation, FontSet> fonts, boolean filterFishyGlyphs) {
        this.fonts = fonts;
        this.filterFishyGlyphs = filterFishyGlyphs;
    }

    public String bidirectionalShaping(String text) {
        try {
            Bidi bidi = new Bidi((new ArabicShaping(8)).shape(text), 127);
            bidi.setReorderingMode(0);
            return bidi.writeReordered(2);
        } catch (ArabicShapingException var3) {
            return text;
        }
    }

    public boolean isBidirectional() {
        return Language.getInstance().isDefaultRightToLeft();
    }

    FontSet getFontSet(ResourceLocation fontLocation) {
        return (FontSet)this.fonts.apply(fontLocation);
    }

    public int drawInBatch(String text, float x, float y, int color, boolean dropShadow, Matrix4f matrix, MultiBufferSource buffer, int backgroundColor, int packedLightCoords) {
        return this.drawInBatch(text, x, y, color, dropShadow, matrix, buffer, backgroundColor, packedLightCoords, this.isBidirectional());
    }

    public int drawInBatch(String text, float x, float y, int color, boolean dropShadow, Matrix4f matrix, MultiBufferSource buffer, int backgroundColor, int packedLightCoords, boolean bidirectional) {
        return this.drawInternal(text, x, y, color, dropShadow, matrix, buffer, backgroundColor, packedLightCoords, bidirectional);
    }

    public int drawInBatch(Component text, float x, float y, int color, boolean dropShadow, Matrix4f matrix, MultiBufferSource buffer, int backgroundColor, int packedLightCoords) {
        return this.drawInBatch(text.getVisualOrderText(), x, y, color, dropShadow, matrix, buffer, backgroundColor, packedLightCoords);
    }

    public int drawInBatch(FormattedCharSequence text, float x, float y, int color, boolean dropShadow, Matrix4f matrix, MultiBufferSource buffer, int backgroundColor, int packedLightCoords) {
        return this.drawInternal(text, x, y, color, dropShadow, matrix, buffer, backgroundColor, packedLightCoords);
    }

    private int drawInternal(String text, float x, float y, int color, boolean dropShadow, Matrix4f matrix, MultiBufferSource buffer, int backgroundColor, int packedLightCoords, boolean bidirectional) {
        if (bidirectional) {
            text = bidirectionalShaping(text);
        }

        color = adjustColor(color);
        Matrix4f matrix4f = new Matrix4f(matrix);
        if (dropShadow) {
            this.renderText(text, x, y, color, true, matrix, buffer, backgroundColor, packedLightCoords);
            matrix4f.translate(SHADOW_OFFSET);
        }

        x = this.renderText(text, x, y, color, false, matrix4f, buffer, backgroundColor, packedLightCoords);
        return (int)x + (dropShadow ? 1 : 0);
    }

    private int drawInternal(FormattedCharSequence text, float x, float y, int color, boolean dropShadow, Matrix4f matrix, MultiBufferSource buffer, int backgroundColor, int packedLightCoords) {
        color = adjustColor(color);
        Matrix4f matrix4f = new Matrix4f(matrix);
        if (dropShadow) {
            this.renderText(text, x, y, color, true, matrix, buffer, backgroundColor, packedLightCoords);
            matrix4f.translate(SHADOW_OFFSET);
        }

        x = this.renderText(text, x, y, color, false, matrix4f, buffer, backgroundColor, packedLightCoords);
        return (int)x + (dropShadow ? 1 : 0);
    }

    private float renderText(String text, float x, float y, int color, boolean dropShadow, Matrix4f matrix, MultiBufferSource buffer, int backgroundColor, int packedLightCoords) {
        StringRenderOutput font$stringrenderoutput = new StringRenderOutput(buffer, x, y, color, dropShadow, matrix, packedLightCoords);
        StringDecomposer.iterateFormatted(text, Style.EMPTY, font$stringrenderoutput);
        return font$stringrenderoutput.finish(backgroundColor, x);
    }

    private float renderText(FormattedCharSequence text, float x, float y, int color, boolean dropShadow, Matrix4f matrix, MultiBufferSource buffer, int backgroundColor, int packedLightCoords) {
        StringRenderOutput font$stringrenderoutput = new StringRenderOutput(buffer, x, y, color, dropShadow, matrix, packedLightCoords);
        text.accept(font$stringrenderoutput);
        return font$stringrenderoutput.finish(backgroundColor, x);
    }

    void renderChar(BakedGlyph glyph, boolean bold, boolean italic, float boldOffset, float x, float y, Matrix4f matrix, VertexConsumer buffer, float red, float green, float blue, float alpha, int packedLight) {
        glyph.render(italic, x, y, matrix, buffer, red, green, blue, alpha, packedLight);
        if (bold) {
            glyph.render(italic, x + boldOffset, y, matrix, buffer, red, green, blue, alpha, packedLight);
        }
    }

    private static int adjustColor(int color) {
        return (color & -67108864) == 0 ? color | -16777216 : color;
    }

    class StringRenderOutput implements FormattedCharSink {
        final MultiBufferSource bufferSource;
        private final boolean dropShadow;
        private final float dimFactor;
        private final float r;
        private final float g;
        private final float b;
        private final float a;
        private final Matrix4f pose;
        private final int packedLightCoords;
        float x;
        float y;
        @Nullable
        private List<BakedGlyph.Effect> effects;

        private void addEffect(BakedGlyph.Effect effect) {
            if (this.effects == null) {
                this.effects = Lists.newArrayList();
            }

            this.effects.add(effect);
        }

        public StringRenderOutput(MultiBufferSource bufferSource, float x, float y, int color, boolean dropShadow, Matrix4f pose, int packedLightCoords) {
            this.bufferSource = bufferSource;
            this.x = x;
            this.y = y;
            this.dropShadow = dropShadow;
            this.dimFactor = dropShadow ? 0.25F : 1.0F;
            this.r = (float)(color >> 16 & 255) / 255.0F * this.dimFactor;
            this.g = (float)(color >> 8 & 255) / 255.0F * this.dimFactor;
            this.b = (float)(color & 255) / 255.0F * this.dimFactor;
            this.a = (float)(color >> 24 & 255) / 255.0F;
            this.pose = pose;
            this.packedLightCoords = packedLightCoords;
        }

        public boolean accept(int positionInCurrentSequence, Style style, int codePoint) {
            FontSet fontset = getFontSet(style.getFont());
            GlyphInfo glyphinfo = fontset.getGlyphInfo(codePoint, filterFishyGlyphs);
            BakedGlyph bakedglyph = style.isObfuscated() && codePoint != 32 ? fontset.getRandomGlyph(glyphinfo) : fontset.getGlyph(codePoint);
            boolean flag = style.isBold();
            float f3 = this.a;
            TextColor textcolor = style.getColor();
            float f;
            float f1;
            float f2;
            if (textcolor != null) {
                int i = textcolor.getValue();
                f = (float)(i >> 16 & 255) / 255.0F * this.dimFactor;
                f1 = (float)(i >> 8 & 255) / 255.0F * this.dimFactor;
                f2 = (float)(i & 255) / 255.0F * this.dimFactor;
            } else {
                f = this.r;
                f1 = this.g;
                f2 = this.b;
            }

            if (!(bakedglyph instanceof EmptyGlyph)) {
                float f5 = flag ? glyphinfo.getBoldOffset() : 0.0F;
                float f4 = this.dropShadow ? glyphinfo.getShadowOffset() : 0.0F;
                RenderType render = bakedglyph.renderType(Font.DisplayMode.NORMAL);

                VertexConsumer vertexconsumer = this.bufferSource.getBuffer(render);
                renderChar(bakedglyph, flag, style.isItalic(), f5, this.x + f4, this.y + f4, this.pose, vertexconsumer, f, f1, f2, f3, this.packedLightCoords);
            }

            float f6 = glyphinfo.getAdvance(flag);
            float f7 = this.dropShadow ? 1.0F : 0.0F;
            if (style.isStrikethrough()) {
                this.addEffect(new BakedGlyph.Effect(this.x + f7 - 1.0F, this.y + f7 + 4.5F, this.x + f7 + f6, this.y + f7 + 4.5F - 1.0F, 0.01F, f, f1, f2, f3));
            }

            if (style.isUnderlined()) {
                this.addEffect(new BakedGlyph.Effect(this.x + f7 - 1.0F, this.y + f7 + 9.0F, this.x + f7 + f6, this.y + f7 + 9.0F - 1.0F, 0.01F, f, f1, f2, f3));
            }

            this.x += f6;
            return true;
        }

        public float finish(int backgroundColor, float x) {
            if (backgroundColor != 0) {
                float f = (float)(backgroundColor >> 24 & 255) / 255.0F;
                float f1 = (float)(backgroundColor >> 16 & 255) / 255.0F;
                float f2 = (float)(backgroundColor >> 8 & 255) / 255.0F;
                float f3 = (float)(backgroundColor & 255) / 255.0F;
                this.addEffect(new BakedGlyph.Effect(x - 1.0F, this.y + 9.0F, this.x + 1.0F, this.y - 1.0F, 0.01F, f1, f2, f3, f));
            }

            if (this.effects != null) {
                BakedGlyph bakedglyph = getFontSet(Style.DEFAULT_FONT).whiteGlyph();
                RenderType render = bakedglyph.renderType(Font.DisplayMode.NORMAL);

                VertexConsumer vertexconsumer = this.bufferSource.getBuffer(render);

                for(BakedGlyph.Effect bakedglyph$effect : this.effects) {
                    bakedglyph.renderEffect(bakedglyph$effect, this.pose, vertexconsumer, this.packedLightCoords);
                }
            }

            return this.x;
        }
    }
}
