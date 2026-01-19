package xox.labvorty.weaversparadise.items;

import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.CustomData;

import java.util.List;

public class PureDyeItem extends Item {
    public PureDyeItem() {
        super(
                new Item.Properties()
                        .stacksTo(64)
                        .rarity(Rarity.COMMON)
                        .component(DataComponents.CUSTOM_DATA, CustomData.of(new CompoundTag() {{
                            putInt("red", 0);
                            putInt("green", 0);
                            putInt("blue", 0);
                        }}))
        );
    }

    public int getDyeColor(ItemStack stack) {
        CompoundTag tag = stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag();

        return 255 << 24 | tag.getInt("red") << 16 | tag.getInt("green") << 8 | tag.getInt("blue");
    }

    public int getRed(ItemStack stack) {
        CompoundTag tag = stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag();

        return tag.getInt("red");
    }

    public int getGreen(ItemStack stack) {
        CompoundTag tag = stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag();

        return tag.getInt("green");
    }

    public int getBlue(ItemStack stack) {
        CompoundTag tag = stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag();

        return tag.getInt("blue");
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        if (tooltipFlag.isAdvanced()) {
            tooltipComponents.add(Component.literal("Red: " + getRed(stack)).withColor(ChatFormatting.GRAY.getColor()));
            tooltipComponents.add(Component.literal("Green: " + getGreen(stack)).withColor(ChatFormatting.GRAY.getColor()));
            tooltipComponents.add(Component.literal("Blue: " + getBlue(stack)).withColor(ChatFormatting.GRAY.getColor()));
        }
    }
}
