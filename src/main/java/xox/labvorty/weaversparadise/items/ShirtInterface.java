package xox.labvorty.weaversparadise.items;

import net.minecraft.world.item.ItemStack;

public interface ShirtInterface {
    default int getItemMainColor(ItemStack stack, int part) {
        return 1;
    }

    default int getItemSecondaryColor(ItemStack stack, int part) {
        return 1;
    }

    default String getItemDyeType(ItemStack stack, int part) {
        return "";
    }

    default int getItemLightValue(ItemStack stack, int part) {
        return 1;
    }

    default String getStensilType(ItemStack stack) {
        return "";
    }
}
