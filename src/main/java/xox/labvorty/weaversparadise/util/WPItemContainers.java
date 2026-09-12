package xox.labvorty.weaversparadise.util;

import net.minecraft.core.NonNullList;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

/** Замена NeoForge ItemStackHandler в меню (gui/menu/*). */
public final class WPItemContainers {
    private WPItemContainers() {}

    public static HandlerContainer sized(int size, Runnable onChanged) {
        return new HandlerContainer(NonNullList.withSize(size, ItemStack.EMPTY), onChanged);
    }

    public static class HandlerContainer implements Container {
        private final NonNullList<ItemStack> stacks;
        private final Runnable onChanged;
        private int maxStackSize = 99;

        public HandlerContainer(NonNullList<ItemStack> stacks, Runnable onChanged) {
            this.stacks = stacks;
            this.onChanged = onChanged;
        }

        public void setMaxStackSize(int maxStackSize) {
            this.maxStackSize = maxStackSize;
        }

        @Override
        public int getContainerSize() {
            return this.stacks.size();
        }

        @Override
        public boolean isEmpty() {
            for (ItemStack stack : this.stacks) {
                if (!stack.isEmpty()) return false;
            }
            return true;
        }

        @Override
        public ItemStack getItem(int index) {
            return this.stacks.get(index);
        }

        @Override
        public ItemStack removeItem(int index, int count) {
            ItemStack removed = ContainerHelper.removeItem(this.stacks, index, count);
            if (!removed.isEmpty()) this.setChanged();
            return removed;
        }

        @Override
        public ItemStack removeItemNoUpdate(int index) {
            ItemStack removed = this.stacks.set(index, ItemStack.EMPTY);
            this.setChanged();
            return removed;
        }

        @Override
        public void setItem(int index, ItemStack stack) {
            this.stacks.set(index, stack);
            if (stack.getCount() > this.maxStackSize) {
                stack.setCount(this.maxStackSize);
            }
            this.setChanged();
        }

        @Override
        public int getMaxStackSize() {
            return this.maxStackSize;
        }

        @Override
        public void setChanged() {
            if (this.onChanged != null) this.onChanged.run();
        }

        @Override
        public boolean stillValid(Player player) {
            return true;
        }

        @Override
        public void clearContent() {
            this.stacks.clear();
            this.setChanged();
        }
    }
}
