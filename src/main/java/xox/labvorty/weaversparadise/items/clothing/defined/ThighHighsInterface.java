package xox.labvorty.weaversparadise.items.clothing.defined;

import net.minecraft.world.item.ItemStack;

public interface ThighHighsInterface {
    default int getItemMainColor(ItemStack stack, String side, int part) {
        return 1;
    }

    default int getItemSecondaryColor(ItemStack stack, String side, int part) {
        return 1;
    }

    default String getItemDyeType(ItemStack stack, String side, int part) {
        return "";
    }

    default int getItemLightValue(ItemStack stack, String side, int part) {
        return 1;
    }

    default String getStensilType(ItemStack stack, String side) {
        return "";
    }
}
