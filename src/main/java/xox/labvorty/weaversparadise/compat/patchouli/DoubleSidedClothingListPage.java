package xox.labvorty.weaversparadise.compat.patchouli;

import com.google.gson.annotations.SerializedName;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.TagParser;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import org.apache.commons.lang3.tuple.Pair;
import vazkii.patchouli.client.base.ClientTicker;
import vazkii.patchouli.client.book.BookContentsBuilder;
import vazkii.patchouli.client.book.BookEntry;
import vazkii.patchouli.client.book.gui.GuiBook;
import vazkii.patchouli.client.book.gui.GuiBookEntry;
import vazkii.patchouli.client.book.page.abstr.PageWithText;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class DoubleSidedClothingListPage extends PageWithText {
    @SerializedName("entity")
    public String entityId;
    float scale = 1F;
    @SerializedName("offset")
    float extraOffset = 0F;
    String name;
    boolean rotate = true;
    @SerializedName("default_rotation")
    float defaultRotation = -45f;
    @SerializedName("nbt_interval")
    public int nbtInterval = 40;

    transient String baseEntityId;
    transient EntityType<?> entityType;
    transient CompoundTag baseEntityNbt;
    transient List<CompoundTag> variants;
    transient Entity entity;
    transient int currentVariantIndex = -1;
    transient boolean errored;
    transient float renderScale;
    transient float offset;

    List<String> dyeTypes = List.of(
            "default",
            "agender",
            "aroace",
            "aromantic",
            "asexual",
            "bisexual",
            "demiboy",
            "demigender",
            "demigirl",
            "gay",
            "genderfluid",
            "genderqueer",
            "intersex",
            "lesbian",
            "nonbinary",
            "pansexual",
            "pride",
            "trans",
            "redstone",
            "lamp",
            "sculk",
            "colored_sculk",
            "hunger",
            "health",
            "day_time",
            "colored_day_time",
            "glowstone",
            "rainbow",
            "biome",
            "ender",
            "speed",
            "height_bedrock",
            "height_sea",
            "invisible",
            "static",
            "crystal",
            "negative",
            "true_negative",
            "nebula",
            "polychromatic"
    );

    @Override
    public void build(Level level, BookEntry entry, BookContentsBuilder builder, int pageNum) {
        super.build(level, entry, builder, pageNum);

        Pair<String, CompoundTag> parsed = splitEntityIdAndNbt(entityId);
        baseEntityId  = parsed.getLeft();
        baseEntityNbt = parsed.getRight();

        ResourceLocation key = ResourceLocation.tryParse(baseEntityId);
        Optional<EntityType<?>> maybeType = BuiltInRegistries.ENTITY_TYPE.getOptional(key);
        if (maybeType.isEmpty()) {
            errored = true;

            return;
        }
        entityType = maybeType.get();

        List<CompoundTag> tags = new ArrayList<>();
        for (String type : dyeTypes) {
            int r = 255, g = 255, b = 255;

            if (type.equals("polychromatic") || type.equals("redstone")) {
                g = 0;
                b = 0;
            }

            tags.add(makeCustomData(type, r, g, b));
        }

        Collections.shuffle(tags);

        variants = tags;
    }

    @Override
    public void onDisplayed(GuiBookEntry parent, int left, int top) {
        super.onDisplayed(parent, left, top);
        entity = null;
        currentVariantIndex = -1;
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float pticks) {
        int x = GuiBook.PAGE_WIDTH / 2 - 53;
        int y = 7;
        RenderSystem.enableBlend();
        RenderSystem.setShaderColor(1F, 1F, 1F, 1F);
        GuiBook.drawFromTexture(graphics, book, x, y, 405, 149, 106, 106);

        if (name == null || name.isEmpty()) {
            if (entity != null) {
                parent.drawCenteredStringNoShadow(graphics, entity.getName().getVisualOrderText(), GuiBook.PAGE_WIDTH / 2, 0, book.headerColor);
            }
        } else {
            parent.drawCenteredStringNoShadow(graphics, name, GuiBook.PAGE_WIDTH / 2, 0, book.headerColor);
        }

        if (errored) {
            graphics.drawString(fontRenderer, I18n.get("patchouli.gui.lexicon.loading_error"), 58, 60, 0xFF0000, true);

            super.render(graphics, mouseX, mouseY, pticks);
            return;
        }

        int ticks = parent.getTicksInBook();
        int desiredIndex = (ticks / Math.max(1, nbtInterval)) % variants.size();

        if (entity == null || desiredIndex != currentVariantIndex) {
            spawnEntityWithVariant(parent.getMinecraft().level, desiredIndex);
        }

        if (entity != null) {
            float rotation = rotate ? ClientTicker.total : defaultRotation;
            renderEntity(graphics, entity, 58, 60, rotation, renderScale, offset);
        }

        super.render(graphics, mouseX, mouseY, pticks);
    }

    private void spawnEntityWithVariant(Level world, int index) {
        if (errored || entityType == null) return;
        try {
            Entity newEntity = entityType.create(world);
            if (newEntity == null) throw new IllegalStateException("EntityType.create() returned null for " + baseEntityId);

            CompoundTag entityNbt = baseEntityNbt != null ? baseEntityNbt.copy() : new CompoundTag();
            CompoundTag customData = variants.get(index).copy();

            if (entityNbt.contains("Item")) {
                CompoundTag itemTag = entityNbt.getCompound("Item");

                itemTag.put("tag", customData);
                entityNbt.put("Item", itemTag);
            }

            newEntity.load(entityNbt);

            if (entity == null) {
                entity = newEntity;
            }

            entity.load(entityNbt);

            float width = newEntity.getBbWidth();
            float height = newEntity.getBbHeight();
            float entitySize = Math.max(1F, Math.max(width, height));
            renderScale = 100F / entitySize * 0.8F * scale;
            offset = Math.max(height, entitySize) * 0.5F + extraOffset;

            currentVariantIndex = index;
        } catch (Exception e) {
            errored = true;
        }
    }

    public static void renderEntity(GuiGraphics graphics, Entity entity, float x, float y, float rotation, float renderScale, float offset) {
        PoseStack ms = graphics.pose();
        ms.pushPose();
        ms.translate(x, y, 50);
        ms.scale(renderScale, renderScale, renderScale);
        ms.translate(0, offset, 0);
        ms.mulPose(Axis.ZP.rotationDegrees(180));
        ms.mulPose(Axis.YP.rotationDegrees(rotation));
        EntityRenderDispatcher erd = Minecraft.getInstance().getEntityRenderDispatcher();
        MultiBufferSource.BufferSource immediate = Minecraft.getInstance().renderBuffers().bufferSource();
        erd.setRenderShadow(false);
        erd.render(entity, 0, 0, 0, 0, 1, ms, immediate, 0xF000F0);
        erd.setRenderShadow(true);
        immediate.endBatch();
        ms.popPose();
    }

    @Override
    public int getTextHeight() {
        return 115;
    }

    private static Pair<String, CompoundTag> splitEntityIdAndNbt(String raw) {
        int braceStart = raw.indexOf('{');
        if (braceStart < 0) return Pair.of(raw, null);

        String id = raw.substring(0, braceStart);
        String nbtStr = raw.substring(braceStart).replaceAll("([^\\\\])'", "$1\"").replaceAll("\\\\'", "'");

        try {
            return Pair.of(id, TagParser.parseTag(nbtStr));
        } catch (CommandSyntaxException e) {
            return Pair.of(id, null);
        }
    }

    private static CompoundTag makeCustomData(String dyeType, int r, int g, int b) {
        CompoundTag tag = new CompoundTag();

        tag.putInt("quality", 0);
        tag.putBoolean("noBobbing", true);
        tag.putString("dyeTypeLeftOne", dyeType);
        tag.putString("dyeTypeRightOne", dyeType);
        tag.putString("dyeTypeLeftTwo", dyeType);
        tag.putString("dyeTypeRightTwo", dyeType);
        tag.putString("stensilTypeLeft", "default");
        tag.putString("stensilTypeRight", "default");
        tag.putInt("colorPriRedLeftOne", r);
        tag.putInt("colorPriGreenLeftOne", g);
        tag.putInt("colorPriBlueLeftOne", b);
        tag.putInt("colorPriRedLeftTwo", r);
        tag.putInt("colorPriGreenLeftTwo", g);
        tag.putInt("colorPriBlueLeftTwo", b);
        tag.putInt("colorSecRedLeftOne", r);
        tag.putInt("colorSecGreenLeftOne", g);
        tag.putInt("colorSecBlueLeftOne", b);
        tag.putInt("colorSecRedLeftTwo", r);
        tag.putInt("colorSecGreenLeftTwo", g);
        tag.putInt("colorSecBlueLeftTwo", b);
        tag.putInt("colorPriRedRightOne", r);
        tag.putInt("colorPriGreenRightOne", g);
        tag.putInt("colorPriBlueRightOne", b);
        tag.putInt("colorPriRedRightTwo", r);
        tag.putInt("colorPriGreenRightTwo", g);
        tag.putInt("colorPriBlueRightTwo", b);
        tag.putInt("colorSecRedRightOne", r);
        tag.putInt("colorSecGreenRightOne", g);
        tag.putInt("colorSecBlueRightOne", b);
        tag.putInt("colorSecRedRightTwo", r);
        tag.putInt("colorSecGreenRightTwo", g);
        tag.putInt("colorSecBlueRightTwo", b);
        tag.putInt("lightValueLeftOne", 15);
        tag.putInt("lightValueLeftTwo", 15);
        tag.putInt("lightValueRightOne", 15);
        tag.putInt("lightValueRightTwo", 15);

        return tag;
    }
}