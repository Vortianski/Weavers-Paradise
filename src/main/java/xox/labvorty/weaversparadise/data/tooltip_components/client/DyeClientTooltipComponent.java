package xox.labvorty.weaversparadise.data.tooltip_components.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;
import xox.labvorty.vortylib.utilities.VortyLibUtilities;
import xox.labvorty.weaversparadise.data.tooltip_components.DyeIcon;
import xox.labvorty.weaversparadise.data.tooltip_components.DyeTypeRegistry;
import xox.labvorty.weaversparadise.data.tooltip_components.ItemstackDyeIcon;
import xox.labvorty.weaversparadise.data.tooltip_components.helper.DyeTooltipData;
import xox.labvorty.weaversparadise.init.WeaversParadiseItems;

import java.util.List;

@OnlyIn(Dist.CLIENT)
public class DyeClientTooltipComponent implements ClientTooltipComponent {
    private static final List<String> poweredCores = List.of(
            "redstone",
            "lamp"
    );

    private final DyeIcon dyeIcon;
    private final String text;
    private final String type;
    private final int width;
    private final int height;
    private final int lightValue;
    private final int primaryColor;
    private final int secondaryColor;
    private final boolean isCore;

    public DyeClientTooltipComponent(DyeIcon dyeIcon, String text, String type, int lightValue, int primaryColor, int secondaryColor, boolean isCore) {
        this(dyeIcon, text, type, lightValue, 8, !isCore ? 24 : poweredCores.contains(type) ? 16 : 8, primaryColor, secondaryColor, isCore);
    }

    public DyeClientTooltipComponent(DyeIcon dyeIcon, String text, String type, int lightValue, int width, int height, int primaryColor, int secondaryColor, boolean isCore) {
        this.dyeIcon = dyeIcon;
        this.text = text;
        this.type = type;
        this.lightValue = lightValue;
        this.width = width;
        this.height = height;
        this.primaryColor = primaryColor;
        this.secondaryColor = secondaryColor;
        this.isCore = isCore;
    }

    @Override
    public int getHeight() {
        return height + 2;
    }

    @Override
    public int getWidth(Font font) {
        int v1 = !isCore ?
                width + 2 + font.width(Component.literal("Secondary:#" + hexOf(secondaryColor))) :
                poweredCores.contains(type) ?
                        font.width(Component.literal("Power:").append(VortyLibUtilities.createHoldBar(lightValue, 15)))
                        : 0;
        int v2 = (width + 2 + font.width(DyeTypeRegistry.getDyeType(this.text).getComponent()));

        return Math.max(v1, v2);
    }

    @Override
    public void renderImage(@NotNull Font font, int x, int y, @NotNull GuiGraphics guiGraphics) {
        RenderSystem.enableBlend();

        dyeIcon.render(guiGraphics, x, y);
        guiGraphics.drawString(font, DyeTooltipData.parse(type, primaryColor, secondaryColor, lightValue), x + (width + 2), y + 1, 0xFFFFFF);
        if (!isCore) {
            ItemStack primaryPigment = WeaversParadiseItems.PURE_DYE.toStack();
            CustomData.update(DataComponents.CUSTOM_DATA, primaryPigment, (compoundTag) -> {
                compoundTag.putInt("red", (primaryColor >> 16) & 0xFF);
                compoundTag.putInt("green", (primaryColor >> 8) & 0xFF);
                compoundTag.putInt("blue", (primaryColor) & 0xFF);
            });
            DyeIcon primary = new ItemstackDyeIcon(primaryPigment);

            ItemStack secondaryPigment = WeaversParadiseItems.PURE_DYE.toStack();
            CustomData.update(DataComponents.CUSTOM_DATA, secondaryPigment, (compoundTag) -> {
                compoundTag.putInt("red", (secondaryColor >> 16) & 0xFF);
                compoundTag.putInt("green", (secondaryColor >> 8) & 0xFF);
                compoundTag.putInt("blue", (secondaryColor) & 0xFF);
            });
            DyeIcon secondary = new ItemstackDyeIcon(secondaryPigment);
            primary.render(guiGraphics, x, y + 8);
            secondary.render(guiGraphics, x, y + 16);
            guiGraphics.drawString(font, Component.literal("Primary:#" + hexOf(primaryColor)), x + (width + 2), y + 9, primaryColor);
            guiGraphics.drawString(font, Component.literal("Secondary:#" + hexOf(secondaryColor)), x + (width + 2), y + 17, secondaryColor);
        } else {
            if (poweredCores.contains(type)) {
                guiGraphics.drawString(font, Component.literal("Power:").append(VortyLibUtilities.createHoldBar(lightValue, 15)), x, y + 10, 0xFFFFFF);
            }
        }

        RenderSystem.disableBlend();
    }

    private static String hexOf(int color) {
        return String.format(
                "%06X",
                color & 0xFFFFFF
        );
    }
}
