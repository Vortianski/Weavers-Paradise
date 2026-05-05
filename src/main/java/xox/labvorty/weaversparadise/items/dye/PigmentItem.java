package xox.labvorty.weaversparadise.items.dye;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

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
}
