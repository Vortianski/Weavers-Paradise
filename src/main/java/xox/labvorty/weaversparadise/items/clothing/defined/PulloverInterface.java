package xox.labvorty.weaversparadise.items.clothing.defined;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import org.joml.Vector3f;

public interface PulloverInterface {
    default int getItemMainColor(ItemStack itemStack, int part) {
        return 1;
    }

    default int getItemSecondaryColor(ItemStack itemStack, int part) {
        return 1;
    }

    default String getItemDyeType(ItemStack itemStack, int part) {
        return "";
    }

    default int getItemLightValue(ItemStack itemStack, int part) {
        return 1;
    }

    default String getStensilType(ItemStack itemStack) {
        return "";
    }

    default boolean getFlag(ItemStack itemStack) {
        return false;
    }

    default Vector3f getGlintColor(ItemStack itemStack) {
        return new Vector3f(1, 1, 1);
    }

    default CompoundTag getAdditionalData(ItemStack itemStack) {
        return new CompoundTag();
    }
}
