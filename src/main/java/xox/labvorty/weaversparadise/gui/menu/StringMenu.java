package xox.labvorty.weaversparadise.gui.menu;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.Container;
import xox.labvorty.weaversparadise.util.WPItemContainers;
import org.jetbrains.annotations.NotNull;
import xox.labvorty.weaversparadise.init.WeaversParadiseInterfaces;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class StringMenu extends AbstractContainerMenu implements Supplier<Map<Integer, Slot>> {
    public final static HashMap<String, Object> guistate = new HashMap<>();
    public final Level world;
    public final Player entity;
    public int x, y, z;
    private ContainerLevelAccess access = ContainerLevelAccess.NULL;
    private Container internal;
    private final Map<Integer, Slot> customSlots = new HashMap<>();
    private boolean bound = false;
    private BlockEntity boundBlockEntity = null;

    public StringMenu(int id, Inventory inv, BlockPos pos) {
        super(WeaversParadiseInterfaces.STRING_MENU, id);
        this.entity = inv.player;
        this.world = inv.player.level();
        this.internal = WPItemContainers.sized(7, null);
        if (pos != null) {
            this.x = pos.getX();
            this.y = pos.getY();
            this.z = pos.getZ();
            access = ContainerLevelAccess.create(world, pos);

            boundBlockEntity = this.world.getBlockEntity(pos);
            if (boundBlockEntity instanceof Container containerBlockEntity) {
                this.internal = containerBlockEntity;
                this.bound = true;
            }
        }
        this.customSlots.put(0, this.addSlot(new Slot(internal, 0, 183, 74) {
            @Override
            public boolean mayPlace(@NotNull ItemStack itemStack) {
               return true;
            }
        }));
        this.customSlots.put(1, this.addSlot(new Slot(internal, 1, 123, 73) {
            @Override
            public int getMaxStackSize() {
                return 1;
            }

            @Override
            public int getMaxStackSize(@NotNull ItemStack itemStack) {
                return 1;
            }

            @Override
            public boolean mayPlace(@NotNull ItemStack itemStack) {
                return true;
            }
        }));
        this.customSlots.put(2, this.addSlot(new Slot(internal, 2, 106, 73) {
            @Override
            public int getMaxStackSize() {
                return 1;
            }

            @Override
            public int getMaxStackSize(@NotNull ItemStack itemStack) {
                return 1;
            }

            @Override
            public boolean mayPlace(@NotNull ItemStack itemStack) {
                return true;
            }
        }));
        this.customSlots.put(3, this.addSlot(new Slot(internal, 3, 89, 73) {
            @Override
            public int getMaxStackSize() {
                return 1;
            }

            @Override
            public int getMaxStackSize(@NotNull ItemStack itemStack) {
                return 1;
            }

            @Override
            public boolean mayPlace(@NotNull ItemStack itemStack) {
                return true;
            }
        }));
        this.customSlots.put(4, this.addSlot(new Slot(internal, 4, 71, 73) {
            @Override
            public int getMaxStackSize() {
                return 1;
            }

            @Override
            public int getMaxStackSize(@NotNull ItemStack itemStack) {
                return 1;
            }

            @Override
            public boolean mayPlace(@NotNull ItemStack itemStack) {
                return true;
            }
        }));
        this.customSlots.put(5, this.addSlot(new Slot(internal, 5, 53, 73) {
            @Override
            public int getMaxStackSize() {
                return 1;
            }

            @Override
            public int getMaxStackSize(@NotNull ItemStack itemStack) {
                return 1;
            }

            @Override
            public boolean mayPlace(@NotNull ItemStack itemStack) {
                return true;
            }
        }));
        this.customSlots.put(6, this.addSlot(new Slot(internal, 6, 36, 73) {
            @Override
            public int getMaxStackSize() {
                return 1;
            }

            @Override
            public int getMaxStackSize(@NotNull ItemStack itemStack) {
                return 1;
            }

            @Override
            public boolean mayPlace(@NotNull ItemStack itemStack) {
                return true;
            }
        }));
        for (int si = 0; si < 3; ++si)
            for (int sj = 0; sj < 9; ++sj)
                this.addSlot(new Slot(inv, sj + (si + 1) * 9, 8 + sj * 18, 34 + 84 + si * 18));
        for (int si = 0; si < 9; ++si)
            this.addSlot(new Slot(inv, si, 8 + si * 18, 34 + 142));
    }

    @Override
    public boolean stillValid(@NotNull Player player) {
        if (this.bound) {
            if (this.boundBlockEntity != null)
                return AbstractContainerMenu.stillValid(this.access, player, this.boundBlockEntity.getBlockState().getBlock());
                    }
        return true;
    }

    @Override
    public @NotNull ItemStack quickMoveStack(@NotNull Player playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();
            if (index < 7) {
                if (!this.moveItemStackTo(itemstack1, 7, this.slots.size(), true))
                    return ItemStack.EMPTY;
                slot.onQuickCraft(itemstack1, itemstack);
            } else if (!this.moveItemStackTo(itemstack1, 0, 7, false)) {
                if (index < 7 + 27) {
                    if (!this.moveItemStackTo(itemstack1, 7 + 27, this.slots.size(), true))
                        return ItemStack.EMPTY;
                } else {
                    if (!this.moveItemStackTo(itemstack1, 7, 7 + 27, false))
                        return ItemStack.EMPTY;
                }
                return ItemStack.EMPTY;
            }
            if (itemstack1.getCount() == 0)
                slot.set(ItemStack.EMPTY);
            else
                slot.setChanged();
            if (itemstack1.getCount() == itemstack.getCount())
                return ItemStack.EMPTY;
            slot.onTake(playerIn, itemstack1);
        }
        return itemstack;
    }

    @Override
    protected boolean moveItemStackTo(@NotNull ItemStack itemStack, int p_38905_, int p_38906_, boolean p_38907_) {
        boolean flag = false;
        int i = p_38905_;
        if (p_38907_) {
            i = p_38906_ - 1;
        }
        if (itemStack.isStackable()) {
            while (!itemStack.isEmpty() && (p_38907_ ? i >= p_38905_ : i < p_38906_)) {
                Slot slot = this.slots.get(i);
                ItemStack itemstack = slot.getItem();
                if (slot.mayPlace(itemstack) && !itemstack.isEmpty() && ItemStack.isSameItemSameComponents(itemStack, itemstack)) {
                    int j = itemStack.getCount() + itemstack.getCount();
                    int k = slot.getMaxStackSize(itemstack);
                    if (j <= k) {
                        itemStack.setCount(0);
                        itemstack.setCount(j);
                        slot.set(itemstack);
                        flag = true;
                    } else if (itemstack.getCount() < k) {
                        itemStack.shrink(k - itemstack.getCount());
                        itemstack.setCount(k);
                        slot.set(itemstack);
                        flag = true;
                    }
                }

                if (p_38907_) {
                    i--;
                } else {
                    i++;
                }
            }
        }
        if (!itemStack.isEmpty()) {
            if (p_38907_) {
                i = p_38906_ - 1;
            } else {
                i = p_38905_;
            }
            while (p_38907_ ? i >= p_38905_ : i < p_38906_) {
                Slot slot1 = this.slots.get(i);
                ItemStack itemstack1 = slot1.getItem();
                if (itemstack1.isEmpty() && slot1.mayPlace(itemStack)) {
                    int l = slot1.getMaxStackSize(itemStack);
                    slot1.setByPlayer(itemStack.split(Math.min(itemStack.getCount(), l)));
                    slot1.setChanged();
                    flag = true;
                    break;
                }
                if (p_38907_) {
                    i--;
                } else {
                    i++;
                }
            }
        }
        return flag;
    }

    @Override
    public void removed(@NotNull Player playerIn) {
        super.removed(playerIn);
        if (!bound && playerIn instanceof ServerPlayer serverPlayer) {
            if (!serverPlayer.isAlive() || serverPlayer.hasDisconnected()) {
                for (int j = 0; j < internal.getContainerSize(); ++j) {
                    playerIn.drop(internal.getItem(j), false);
                    internal.setItem(j, ItemStack.EMPTY);
                }
            } else {
                for (int i = 0; i < internal.getContainerSize(); ++i) {
                    playerIn.getInventory().placeItemBackInInventory(internal.getItem(i));
                    internal.setItem(i, ItemStack.EMPTY);
                }
            }
        }
    }

    public Map<Integer, Slot> get() {
        return customSlots;
    }
}
