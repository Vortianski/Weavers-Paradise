package xox.labvorty.weaversparadise.data.creative_tab;

import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import xox.labvorty.weaversparadise.init.WeaversParadiseDataComponents;

public class ExpansionHelpers {
    public static boolean isExpanded(ItemStack itemStack) {
        CompoundTag compoundTag = itemStack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag();

        return compoundTag.getBoolean("weavers_paradise_expanded");
    }

    public static void toggleExpanded(ItemStack itemStack) {
        CompoundTag compoundTag = itemStack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag();
        compoundTag.putBoolean("weavers_paradise_expanded", !compoundTag.getBoolean("weavers_paradise_expanded"));
        CustomData.set(DataComponents.CUSTOM_DATA, itemStack, compoundTag);
    }

    public static String getGroupID(ItemStack itemStack) {
        return itemStack.get(WeaversParadiseDataComponents.GROUP_COMPONENT);
    }

    public static String getItemGroupID(ItemStack itemStack) {
        return itemStack.get(WeaversParadiseDataComponents.GROUP_ITEM_COMPONENT);
    }
}
