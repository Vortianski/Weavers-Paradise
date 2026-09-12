package xox.labvorty.weaversparadise.gui.menu;

import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.Container;
import org.jetbrains.annotations.NotNull;
import xox.labvorty.weaversparadise.data.texture.StencilRegistry;
import xox.labvorty.weaversparadise.init.WeaversParadiseInterfaces;
import xox.labvorty.weaversparadise.init.WeaversParadiseItems;
import xox.labvorty.weaversparadise.items.clothing.defined.DoubleSidedBlockItem;
import xox.labvorty.weaversparadise.items.clothing.defined.DoubleSidedClothingItem;
import xox.labvorty.weaversparadise.items.clothing.defined.SingleSidedClothingItem;
import xox.labvorty.weaversparadise.items.stencil.Stencil;
import xox.labvorty.weaversparadise.util.WPItemContainers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;


public class DyeingMenu extends AbstractContainerMenu implements Supplier<Map<Integer, Slot>> {
    public final static HashMap<String, Object> guistate = new HashMap<>();
    public final Level world;
    public final Player entity;
    public int x, y, z;
    private ContainerLevelAccess access = ContainerLevelAccess.NULL;
    private Container internal;
    private final Map<Integer, Slot> customSlots = new HashMap<>();
    private boolean bound = false;
    private BlockEntity boundBlockEntity = null;

    public DyeingMenu(int id, Inventory inv, BlockPos pos) {
        super(WeaversParadiseInterfaces.DYEING_MENU, id);
        this.entity = inv.player;
        this.world = inv.player.level();
        this.internal = WPItemContainers.sized(8, null);
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

        this.customSlots.put(0, this.addSlot(new Slot(internal, 0, 58, 103) {
            @Override
            public boolean mayPlace(@NotNull ItemStack itemStack) {
                return itemStack.getItem() instanceof SingleSidedClothingItem || itemStack.getItem() instanceof DoubleSidedClothingItem || itemStack.getItem() instanceof DoubleSidedBlockItem;
            }
        }));

        this.customSlots.put(1, this.addSlot(new Slot(internal, 1, 102, 103) {
            @Override
            public boolean mayPlace(@NotNull ItemStack itemStack) {
                return false;
            }

            @Override
            public void onTake(@NotNull Player player, @NotNull ItemStack itemStack) {
                super.onTake(player, itemStack);

                boolean leftdyes = false;
                boolean rightdyes = false;

                if (internal.getItem(4).is(WeaversParadiseItems.BOTTLED_DYE) || internal.getItem(5).is(WeaversParadiseItems.BOTTLED_DYE)) {
                    leftdyes = true;
                }

                if (internal.getItem(6).is(WeaversParadiseItems.BOTTLED_DYE) || internal.getItem(7).is(WeaversParadiseItems.BOTTLED_DYE)) {
                    rightdyes = true;
                }

                slotChanged(leftdyes, rightdyes);
            }
        }));

        this.customSlots.put(2, this.addSlot(new Slot(internal, 2, 21, 67) {
            @Override
            public boolean mayPlace(@NotNull ItemStack itemStack) {
                if (itemStack.getItem() instanceof Stencil stencil) {
                    ItemStack clothingStack = internal.getItem(0);
                    String type = stencil.getType();
                    List<StencilRegistry.Stencil> stencils = StencilRegistry.getStencilsForType(type);

                    for (StencilRegistry.Stencil s : stencils) {
                        if (s.item().equals(clothingStack.getItem())) {
                            return true;
                        }
                    }
                }

                return false;
            }
        }));

        this.customSlots.put(3, this.addSlot(new Slot(internal, 3, 137, 66) {
            @Override
            public boolean mayPlace(@NotNull ItemStack itemStack) {
                if (itemStack.getItem() instanceof Stencil stencil) {
                    ItemStack clothingStack = internal.getItem(0);
                    String type = stencil.getType();
                    List<StencilRegistry.Stencil> stencils = StencilRegistry.getStencilsForType(type);

                    for (StencilRegistry.Stencil s : stencils) {
                        if (s.item().equals(clothingStack.getItem()) && (clothingStack.getItem() instanceof DoubleSidedClothingItem || clothingStack.getItem() instanceof DoubleSidedBlockItem)) {
                            return true;
                        }
                    }
                }

                return false;
            }
        }));

        this.customSlots.put(4, this.addSlot(new Slot(internal, 4, 10, 88) {
            @Override
            public boolean mayPlace(@NotNull ItemStack itemStack) {
                return itemStack.is(WeaversParadiseItems.BOTTLED_DYE);
            }
        }));

        this.customSlots.put(5, this.addSlot(new Slot(internal, 5, 32, 88) {
            @Override
            public boolean mayPlace(@NotNull ItemStack itemStack) {
                if (internal.getItem(2).getItem() instanceof Stencil stencil) {
                    return itemStack.is(WeaversParadiseItems.BOTTLED_DYE);
                }

                return false;
            }
        }));

        this.customSlots.put(6, this.addSlot(new Slot(internal, 6, 126, 87) {
            @Override
            public boolean mayPlace(@NotNull ItemStack itemStack) {
                ItemStack clothingStack = internal.getItem(0);

                if (!(clothingStack.getItem() instanceof DoubleSidedClothingItem || clothingStack.getItem() instanceof DoubleSidedBlockItem)) {
                    return false;
                }

                return itemStack.is(WeaversParadiseItems.BOTTLED_DYE);
            }
        }));
        this.customSlots.put(7, this.addSlot(new Slot(internal, 7, 148, 87) {
            @Override
            public boolean mayPlace(@NotNull ItemStack itemStack) {
                ItemStack clothingStack = internal.getItem(0);

                if (internal.getItem(3).isEmpty() || !(internal.getItem(3).getItem() instanceof Stencil) || !(clothingStack.getItem() instanceof DoubleSidedClothingItem || clothingStack.getItem() instanceof DoubleSidedBlockItem)) {
                    return false;
                }

                return itemStack.is(WeaversParadiseItems.BOTTLED_DYE);
            }
        }));
        for (int si = 0; si < 3; ++si)
            for (int sj = 0; sj < 9; ++sj)
                this.addSlot(new Slot(inv, sj + (si + 1) * 9, 8 + sj * 18, 54 + 84 + si * 18));
        for (int si = 0; si < 9; ++si)
            this.addSlot(new Slot(inv, si, 8 + si * 18, 54 + 142));
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
    public @NotNull ItemStack quickMoveStack(@NotNull Player player, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = (Slot) this.slots.get(index);
        if (slot != null && slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();
            if (index < 8) {
                if (!this.moveItemStackTo(itemstack1, 8, this.slots.size(), true))
                    return ItemStack.EMPTY;
                slot.onQuickCraft(itemstack1, itemstack);
            } else if (!this.moveItemStackTo(itemstack1, 0, 8, false)) {
                if (index < 8 + 27) {
                    if (!this.moveItemStackTo(itemstack1, 8 + 27, this.slots.size(), true))
                        return ItemStack.EMPTY;
                } else {
                    if (!this.moveItemStackTo(itemstack1, 8, 8 + 27, false))
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
            slot.onTake(player, itemstack1);
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
                    int j = itemstack.getCount() + itemStack.getCount();
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
    public void removed(@NotNull Player player) {
        super.removed(player);
        if (!bound && player instanceof ServerPlayer serverPlayer) {
            if (!serverPlayer.isAlive() || serverPlayer.hasDisconnected()) {
                for (int j = 0; j < internal.getContainerSize(); ++j) {
                    player.drop(internal.getItem(j), false);
                    internal.setItem(j, ItemStack.EMPTY);
                }
            } else {
                for (int i = 0; i < internal.getContainerSize(); ++i) {
                    player.getInventory().placeItemBackInInventory(internal.getItem(i));
                    internal.setItem(i, ItemStack.EMPTY);
                }
            }
        }
    }

    public Map<Integer, Slot> get() {
        return customSlots;
    }

    /**
     * Server-side dye consumption (runs on the server menu instance when the
     * result is taken; the client menu instance does nothing).
     */
    private void slotChanged(boolean leftdyes, boolean rightdyes) {
        if (this.world == null || this.world.isClientSide()) {
            return;
        }
        internal.setItem(0, ItemStack.EMPTY);
        if (leftdyes) {
            ItemStack slot4 = internal.getItem(4);
            ItemStack slot5 = internal.getItem(5);
            if (slot4.is(WeaversParadiseItems.BOTTLED_DYE)) {
                CompoundTag tag4 = slot4.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag();
                final int amount4 = tag4.getInt("amount") - 1;
                CustomData.update(DataComponents.CUSTOM_DATA, slot4, (tag) -> {
                    tag.putInt("amount", amount4);
                });
                internal.setItem(4, slot4);
            }
            if (slot5.is(WeaversParadiseItems.BOTTLED_DYE)) {
                CompoundTag tag5 = slot5.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag();
                final int amount5 = tag5.getInt("amount") - 1;
                CustomData.update(DataComponents.CUSTOM_DATA, slot5, (tag) -> {
                    tag.putInt("amount", amount5);
                });
                internal.setItem(5, slot5);
            }
        }
        if (rightdyes) {
            ItemStack slot6 = internal.getItem(6);
            ItemStack slot7 = internal.getItem(7);
            if (slot6.is(WeaversParadiseItems.BOTTLED_DYE)) {
                CompoundTag tag6 = slot6.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag();
                final int amount6 = tag6.getInt("amount") - 1;
                CustomData.update(DataComponents.CUSTOM_DATA, slot6, (tag) -> {
                    tag.putInt("amount", amount6);
                });
                internal.setItem(6, slot6);
            }
            if (slot7.is(WeaversParadiseItems.BOTTLED_DYE)) {
                CompoundTag tag7 = slot7.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag();
                final int amount7 = tag7.getInt("amount") - 1;
                CustomData.update(DataComponents.CUSTOM_DATA, slot7, (tag) -> {
                    tag.putInt("amount", amount7);
                });
                internal.setItem(7, slot7);
            }
        }
    }
}
