package xox.labvorty.weaversparadise.items.dye;

import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class PigmentItem extends Item {
    public PigmentItem() {
        super(new Properties());
    }

    public int getDyeColor(ItemStack stack) {
        CompoundTag tag = stack.getOrCreateTag();

        return 255 << 24 | tag.getInt("red") << 16 | tag.getInt("green") << 8 | tag.getInt("blue");
    }

    public int getRed(ItemStack stack) {
        CompoundTag tag = stack.getOrCreateTag();

        return tag.getInt("red");
    }

    public int getGreen(ItemStack stack) {
        CompoundTag tag = stack.getOrCreateTag();

        return tag.getInt("green");
    }

    public int getBlue(ItemStack stack) {
        CompoundTag tag = stack.getOrCreateTag();

        return tag.getInt("blue");
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level level, @NotNull List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        if (tooltipFlag.isAdvanced()) {
            tooltipComponents.add(Component.literal("Red: " + getRed(stack)).withStyle(style -> style.withColor(ChatFormatting.GRAY)));
            tooltipComponents.add(Component.literal("Green: " + getGreen(stack)).withStyle(style -> style.withColor(ChatFormatting.GRAY)));
            tooltipComponents.add(Component.literal("Blue: " + getBlue(stack)).withStyle(style -> style.withColor(ChatFormatting.GRAY)));
        }

        super.appendHoverText(stack, level, tooltipComponents, tooltipFlag);
    }
}
