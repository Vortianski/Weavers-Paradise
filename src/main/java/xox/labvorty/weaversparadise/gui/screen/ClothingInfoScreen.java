package xox.labvorty.weaversparadise.gui.screen;

import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.layouts.HeaderAndFooterLayout;
import net.minecraft.client.gui.layouts.LinearLayout;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix4f;
import oshi.util.tuples.Pair;
import xox.labvorty.weaversparadise.data.tooltip_components.DyeTypeRegistry;
import xox.labvorty.weaversparadise.data.tooltip_components.helper.DyeData;
import xox.labvorty.weaversparadise.data.tooltip_components.helper.DyeInstance;
import xox.labvorty.weaversparadise.items.clothing.defined.DoubleSidedBlockItem;
import xox.labvorty.weaversparadise.items.clothing.defined.DoubleSidedClothingItem;
import xox.labvorty.weaversparadise.items.clothing.defined.SingleSidedClothingItem;
import xox.labvorty.weaversparadise.renderers.helpers.ColorHandlers;
import xox.labvorty.weaversparadise.renderers.helpers.RenderingUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@OnlyIn(Dist.CLIENT)
public class ClothingInfoScreen extends Screen {
    private static final int HEADER_HEIGHT = 33;
    private static final int FOOTER_HEIGHT = 40;

    private static final int PATCH_SIZE = 32;
    private static final int ROW_SPACING = 8;
    private static final int PATCH_GAP = 12;
    private static final int TEXT_PADDING = 8;

    private static final ResourceLocation PATCH_TEXTURE = ResourceLocation.withDefaultNamespace("textures/misc/white.png");

    private static final String PATCH_CLOTHING_TYPE = "example_patch";

    private final RenderingUtils renderingUtils = new RenderingUtils();

    private final Screen lastScreen;
    private final List<Row> rows;

    private HeaderAndFooterLayout layout =
            new HeaderAndFooterLayout(
                    this,
                    HEADER_HEIGHT,
                    FOOTER_HEIGHT
            );

    private ClothingInfoScreen(
            Screen lastScreen,
            Component title,
            List<Row> rows
    ) {
        super(title);
        this.lastScreen = lastScreen;
        this.rows = rows;
    }

    public static ClothingInfoScreen forItem(
            Screen lastScreen,
            ItemStack stack
    ) {
        if (stack.getItem() instanceof SingleSidedClothingItem item) {
            return forSingleSided(lastScreen, item, stack);
        }

        if (stack.getItem() instanceof DoubleSidedClothingItem item) {
            return forDoubleSided(lastScreen, item, stack);
        }

        if (stack.getItem() instanceof DoubleSidedBlockItem doubleSidedBlockItem) {
            return forDoubleSidedPlushie(lastScreen, doubleSidedBlockItem, stack);
        }

        return new ClothingInfoScreen(
                lastScreen,
                Component.empty(),
                List.of()
        );
    }

    private static ClothingInfoScreen forSingleSided(
            Screen lastScreen,
            SingleSidedClothingItem item,
            ItemStack stack
    ) {
        List<Row> rows = new ArrayList<>();

        addSingleSidedEntry(rows, item, stack);

        return new ClothingInfoScreen(
                lastScreen,
                Component.translatable("weaversparadise.clothing_info.title"),
                rows
        );
    }

    private static ClothingInfoScreen forDoubleSidedPlushie(
            Screen lastScreen,
            DoubleSidedBlockItem item,
            ItemStack stack
    ) {
        List<Row> rows = new ArrayList<>();

        addDoubleSidedPlushieEntry(
                rows,
                Component.translatable("weaversparadise.clothing_info.side_left"),
                item,
                stack,
                "left"
        );

        addDoubleSidedPlushieEntry(
                rows,
                Component.translatable("weaversparadise.clothing_info.side_right"),
                item,
                stack,
                "right"
        );

        return new ClothingInfoScreen(
                lastScreen,
                Component.translatable(
                        "weaversparadise.clothing_info.title"
                ),
                rows
        );
    }

    private static ClothingInfoScreen forDoubleSided(
            Screen lastScreen,
            DoubleSidedClothingItem item,
            ItemStack stack
    ) {
        List<Row> rows = new ArrayList<>();

        addDoubleSidedEntry(
                rows,
                Component.translatable("weaversparadise.clothing_info.side_left"),
                item,
                stack,
                "left"
        );

        addDoubleSidedEntry(
                rows,
                Component.translatable("weaversparadise.clothing_info.side_right"),
                item,
                stack,
                "right"
        );

        return new ClothingInfoScreen(
                lastScreen,
                Component.translatable(
                        "weaversparadise.clothing_info.title"
                ),
                rows
        );
    }

    private static void addSingleSidedEntry(
            List<Row> rows,
            SingleSidedClothingItem item,
            ItemStack stack
    ) {
        String stencilType = item.getStensilType(stack);

        rows.add(
                Row.stencil(
                        null,
                        stencilType
                )
        );

        DyeEntry partOne = new DyeEntry(
                item.getItemDyeType(stack, 1),
                item.getItemMainColor(stack, 1),
                item.getItemSecondaryColor(stack, 1),
                item.getItemLightValue(stack, 1)
        );

        @Nullable DyeEntry partTwo = null;

        if (!stencilType.equals("default")) {
            partTwo = new DyeEntry(
                    item.getItemDyeType(stack, 2),
                    item.getItemMainColor(stack, 2),
                    item.getItemSecondaryColor(stack, 2),
                    item.getItemLightValue(stack, 2)
            );
        }

        rows.add(
                Row.dyes(
                        null,
                        partOne,
                        partTwo
                )
        );
    }

    private static void addDoubleSidedPlushieEntry(
            List<Row> rows,
            Component sideLabel,
            DoubleSidedBlockItem item,
            ItemStack stack,
            String side
    ) {
        String stencilType = item.getStensilType(stack, side);

        rows.add(
                Row.stencil(
                        sideLabel,
                        stencilType
                )
        );

        DyeEntry partOne = new DyeEntry(
                item.getItemDyeType(stack, side, 1),
                item.getItemMainColor(stack, side, 1),
                item.getItemSecondaryColor(stack, side, 1),
                item.getItemLightValue(stack, side, 1)
        );

        @Nullable DyeEntry partTwo = null;

        if (!stencilType.equals("default")) {
            partTwo = new DyeEntry(
                    item.getItemDyeType(stack, side, 2),
                    item.getItemMainColor(stack, side, 2),
                    item.getItemSecondaryColor(stack, side, 2),
                    item.getItemLightValue(stack, side, 2)
            );
        }

        rows.add(
                Row.dyes(
                        null,
                        partOne,
                        partTwo
                )
        );
    }

    private static void addDoubleSidedEntry(
            List<Row> rows,
            Component sideLabel,
            DoubleSidedClothingItem item,
            ItemStack stack,
            String side
    ) {
        String stencilType = item.getStensilType(stack, side);

        rows.add(
                Row.stencil(
                        sideLabel,
                        stencilType
                )
        );

        DyeEntry partOne = new DyeEntry(
                item.getItemDyeType(stack, side, 1),
                item.getItemMainColor(stack, side, 1),
                item.getItemSecondaryColor(stack, side, 1),
                item.getItemLightValue(stack, side, 1)
        );

        @Nullable DyeEntry partTwo = null;

        if (!stencilType.equals("default")) {
            partTwo = new DyeEntry(
                    item.getItemDyeType(stack, side, 2),
                    item.getItemMainColor(stack, side, 2),
                    item.getItemSecondaryColor(stack, side, 2),
                    item.getItemLightValue(stack, side, 2)
            );
        }

        rows.add(
                Row.dyes(
                        null,
                        partOne,
                        partTwo
                )
        );
    }

    @Override
    protected void init() {
        HeaderAndFooterLayout headerAndFooterLayout =
                new HeaderAndFooterLayout(
                        this,
                        HEADER_HEIGHT,
                        FOOTER_HEIGHT
                );

        headerAndFooterLayout.addTitleHeader(
                this.title,
                this.font
        );

        LinearLayout footer =
                headerAndFooterLayout.addToFooter(
                        LinearLayout.vertical()
                );

        footer.defaultCellSetting()
                .alignHorizontallyCenter();

        footer.addChild(
                Button.builder(
                                CommonComponents.GUI_DONE,
                                button -> this.onClose()
                        )
                        .width(200)
                        .build()
        );

        this.layout = headerAndFooterLayout;

        this.layout.visitWidgets(
                this::addRenderableWidget
        );

        this.repositionElements();
    }

    @Override
    protected void repositionElements() {
        this.layout.arrangeElements();
    }

    @Override
    public void render(
            @NotNull GuiGraphics guiGraphics,
            int mouseX,
            int mouseY,
            float partialTick
    ) {
        super.render(
                guiGraphics,
                mouseX,
                mouseY,
                partialTick
        );

        this.renderRows(guiGraphics);
    }

    private void renderRows(GuiGraphics guiGraphics) {
        int centerX = this.width / 2;
        int y = HEADER_HEIGHT + 12;

        if (minecraft == null) {
            return;
        }

        MultiBufferSource.BufferSource bufferSource = this.minecraft.renderBuffers().bufferSource();
        List<int[]> patchPositions = new ArrayList<>();

        for (Row row : this.rows) {
            int rowLeft = centerX - 150;

            if (row.isStencil()) {
                this.renderStencilRow(
                        guiGraphics,
                        row,
                        rowLeft,
                        y
                );

                y += this.font.lineHeight + ROW_SPACING;
                continue;
            }

            if (row.isDyes()) {
                this.renderDyeRow(
                        guiGraphics,
                        bufferSource,
                        row,
                        rowLeft,
                        y,
                        patchPositions
                );

                y += PATCH_SIZE + ROW_SPACING;
            }
        }

        bufferSource.endBatch();

        for (int[] position : patchPositions) {
            this.renderPatchOutline(
                    guiGraphics,
                    position[0],
                    position[1],
                    PATCH_SIZE
            );
        }
    }

    private void renderStencilRow(
            GuiGraphics guiGraphics,
            Row row,
            int x,
            int y
    ) {
        Component text =
                Component.translatable(
                        "weaversparadise.stencil." + row.stencilType()
                );

        Component label = row.sideLabel() != null
                ? row.sideLabel()
                .copy()
                .append(" - ")
                .append(text)
                : text;

        guiGraphics.drawString(
                this.font,
                label,
                x,
                y,
                0xFFFFFF
        );
    }

    private void renderDyeRow(
            GuiGraphics guiGraphics,
            MultiBufferSource.BufferSource bufferSource,
            Row row,
            int x,
            int y,
            List<int[]> patchPositions
    ) {
        int currentX = x;

        currentX = this.renderDyeEntry(
                guiGraphics,
                bufferSource,
                row.partOne(),
                currentX,
                y,
                patchPositions
        );

        if (row.partTwo() != null) {
            this.renderDyeEntry(
                    guiGraphics,
                    bufferSource,
                    row.partTwo(),
                    currentX + PATCH_GAP,
                    y,
                    patchPositions
            );
        }
    }

    private int renderDyeEntry(
            GuiGraphics guiGraphics,
            MultiBufferSource.BufferSource bufferSource,
            DyeEntry entry,
            int x,
            int y,
            List<int[]> patchPositions
    ) {
        int packedLight = LightTexture.pack(
                entry.lightValue(),
                entry.lightValue()
        );

        int ticks = minecraft != null && minecraft.level != null
                ? (int)minecraft.level.getGameTime()
                : 0;

        Pair<Integer, Integer> resolved = ColorHandlers.handle(
                entry.dyeType(),
                entry.mainColor(),
                entry.secondaryColor(),
                entry.lightValue(),
                minecraft.player,
                packedLight,
                ticks
        );

        int finalColor = resolved.getA();
        int finalLight = resolved.getB();

        this.renderDyePatch(
                guiGraphics,
                bufferSource,
                entry.dyeType(),
                finalColor,
                finalLight,
                x,
                y,
                PATCH_SIZE
        );

        patchPositions.add(new int[]{x, y});

        int textX = x + PATCH_SIZE + TEXT_PADDING;

        DyeInstance dyeInstance = DyeTypeRegistry.getDyeType(entry.dyeType());
        Function<DyeData, MutableComponent> nameParser = dyeInstance.getNameParser();

        Component dyeName = nameParser.apply(new DyeData(dyeInstance.getComponent(), entry.mainColor(), entry.secondaryColor(), entry.lightValue()));

        guiGraphics.drawString(
                this.font,
                dyeName,
                textX,
                y,
                0xFFFFFF
        );

        String mainHex = hexOf(entry.mainColor());
        String secondaryHex = hexOf(entry.secondaryColor());

        guiGraphics.drawString(
                this.font,
                Component.literal("Main: #" + mainHex),
                textX,
                y + this.font.lineHeight + 2,
                entry.mainColor()
        );

        guiGraphics.drawString(
                this.font,
                Component.literal("Secondary: #" + secondaryHex),
                textX,
                y + (this.font.lineHeight + 2) * 2,
                entry.secondaryColor()
        );

        int textWidth = Math.max(
                this.font.width(dyeName),
                Math.max(
                        this.font.width("Main: #" + mainHex),
                        this.font.width("Secondary: #" + secondaryHex)
                )
        );

        return textX + textWidth;
    }

    private void renderDyePatch(
            GuiGraphics guiGraphics,
            MultiBufferSource.BufferSource bufferSource,
            String dyeType,
            int color,
            int packedLight,
            int x,
            int y,
            int width
    ) {
        VertexConsumer vertexConsumer =
                this.renderingUtils.parseVC(
                        bufferSource,
                        dyeType,
                        PATCH_TEXTURE,
                        PATCH_CLOTHING_TYPE
                );

        Matrix4f matrix =
                guiGraphics.pose()
                        .last()
                        .pose();

        int alpha = (color >>> 24) & 0xFF;
        int red = (color >>> 16) & 0xFF;
        int green = (color >>> 8) & 0xFF;
        int blue = color & 0xFF;

        vertexConsumer.addVertex(
                        matrix,
                        x,
                        y + PATCH_SIZE,
                        0
                )
                .setColor(red, green, blue, alpha)
                .setUv(0.0F, 1.0F)
                .setOverlay(OverlayTexture.NO_OVERLAY)
                .setLight(packedLight)
                .setNormal(0.0F, 0.0F, 1.0F);

        vertexConsumer.addVertex(
                        matrix,
                        x + width,
                        y + PATCH_SIZE,
                        0
                )
                .setColor(red, green, blue, alpha)
                .setUv(1.0F, 1.0F)
                .setOverlay(OverlayTexture.NO_OVERLAY)
                .setLight(packedLight)
                .setNormal(0.0F, 0.0F, 1.0F);

        vertexConsumer.addVertex(
                        matrix,
                        x + width,
                        y,
                        0
                )
                .setColor(red, green, blue, alpha)
                .setUv(1.0F, 0.0F)
                .setOverlay(OverlayTexture.NO_OVERLAY)
                .setLight(packedLight)
                .setNormal(0.0F, 0.0F, 1.0F);

        vertexConsumer.addVertex(
                        matrix,
                        x,
                        y,
                        0
                )
                .setColor(red, green, blue, alpha)
                .setUv(0.0F, 0.0F)
                .setOverlay(OverlayTexture.NO_OVERLAY)
                .setLight(packedLight)
                .setNormal(0.0F, 0.0F, 1.0F);
    }

    private void renderPatchOutline(
            GuiGraphics guiGraphics,
            int x,
            int y,
            int size
    ) {
        int outlineColor = 0xFF555555;

        guiGraphics.fill(
                x,
                y,
                x + size,
                y + 1,
                outlineColor
        );

        guiGraphics.fill(
                x,
                y + size - 1,
                x + size,
                y + size,
                outlineColor
        );

        guiGraphics.fill(
                x,
                y,
                x + 1,
                y + size,
                outlineColor
        );

        guiGraphics.fill(
                x + size - 1,
                y,
                x + size,
                y + size,
                outlineColor
        );
    }

    private static String hexOf(int color) {
        return String.format(
                "%06X",
                color & 0xFFFFFF
        );
    }

    @Override
    public void onClose() {
        if (this.minecraft != null) {
            this.minecraft.setScreen(
                    this.lastScreen
            );
        }
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    private record Row(
            @Nullable Component sideLabel,
            @Nullable String stencilType,
            @Nullable DyeEntry partOne,
            @Nullable DyeEntry partTwo
    ) {
        static Row stencil(
                @Nullable Component sideLabel,
                String stencilType
        ) {
            return new Row(
                    sideLabel,
                    stencilType,
                    null,
                    null
            );
        }

        static Row dyes(
                @Nullable Component sideLabel,
                DyeEntry partOne,
                @Nullable DyeEntry partTwo
        ) {
            return new Row(
                    sideLabel,
                    null,
                    partOne,
                    partTwo
            );
        }

        boolean isStencil() {
            return stencilType != null;
        }

        boolean isDyes() {
            return partOne != null;
        }
    }

    private record DyeEntry(
            String dyeType,
            int mainColor,
            int secondaryColor,
            int lightValue
    ) {
    }
}