package xox.labvorty.weaversparadise.items;

import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.component.CustomData;
import xox.labvorty.weaversparadise.data.WeaversParadiseDyeIconHandler;
import xox.labvorty.weaversparadise.tooltips.ImageTooltipComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CottonUpperwearBase extends Item {
    public CottonUpperwearBase() {
        super(new Item.Properties()
                .stacksTo(1)
                .rarity(Rarity.COMMON)
                .component(DataComponents.CUSTOM_DATA, CustomData.of(new CompoundTag() {{
                    putInt("quality", 0);
                }}))
        );
    }

    @Override
    public Optional<TooltipComponent> getTooltipImage(ItemStack stack) {
        List<ResourceLocation> list = new ArrayList();

        CompoundTag data = stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag();

        if (data.getInt("quality") >= 2) {
            list.add(ResourceLocation.parse("weaversparadise:textures/tooltips/star_full.png"));
        } else if (data.getInt("quality") == 1) {
            list.add(ResourceLocation.parse("weaversparadise:textures/tooltips/star_half.png"));
        } else {
            list.add(ResourceLocation.parse("weaversparadise:textures/tooltips/star_empty.png"));
        }

        if (data.getInt("quality") >= 4) {
            list.add(ResourceLocation.parse("weaversparadise:textures/tooltips/star_full.png"));
        } else if (data.getInt("quality") == 3) {
            list.add(ResourceLocation.parse("weaversparadise:textures/tooltips/star_half.png"));
        } else {
            list.add(ResourceLocation.parse("weaversparadise:textures/tooltips/star_empty.png"));
        }

        if (data.getInt("quality") >= 6) {
            list.add(ResourceLocation.parse("weaversparadise:textures/tooltips/star_full.png"));
        } else if (data.getInt("quality") == 5) {
            list.add(ResourceLocation.parse("weaversparadise:textures/tooltips/star_half.png"));
        } else {
            list.add(ResourceLocation.parse("weaversparadise:textures/tooltips/star_empty.png"));
        }

        if (data.getInt("quality") >= 8) {
            list.add(ResourceLocation.parse("weaversparadise:textures/tooltips/star_full.png"));
        } else if (data.getInt("quality") == 7) {
            list.add(ResourceLocation.parse("weaversparadise:textures/tooltips/star_half.png"));
        } else {
            list.add(ResourceLocation.parse("weaversparadise:textures/tooltips/star_empty.png"));
        }

        if (data.getInt("quality") >= 10) {
            list.add(ResourceLocation.parse("weaversparadise:textures/tooltips/star_full.png"));
        } else if (data.getInt("quality") == 9) {
            list.add(ResourceLocation.parse("weaversparadise:textures/tooltips/star_half.png"));
        } else {
            list.add(ResourceLocation.parse("weaversparadise:textures/tooltips/star_empty.png"));
        }

        return Optional.of(
                new ImageTooltipComponent(
                        list,
                        WeaversParadiseDyeIconHandler.getOrDefault(
                                "none"
                        ).getTexture(),
                        "No text here",
                        "none",
                        15,
                        -1,
                        -1,
                        WeaversParadiseDyeIconHandler.getOrDefault(
                                "none"
                        ).getTexture(),
                        "No text here",
                        "none",
                        15,
                        -1,
                        -1,
                        WeaversParadiseDyeIconHandler.getOrDefault(
                                "none"
                        ).getTexture(),
                        "No text here",
                        "none",
                        15,
                        -1,
                        -1,
                        WeaversParadiseDyeIconHandler.getOrDefault(
                                "none"
                        ).getTexture(),
                        "No text here",
                        "none",
                        15,
                        -1,
                        -1
                )
        );
    }
}
