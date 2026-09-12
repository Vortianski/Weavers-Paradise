package xox.labvorty.weaversparadise.gui.menu;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.Container;
import xox.labvorty.weaversparadise.util.WPItemContainers;
import org.jetbrains.annotations.NotNull;
import xox.labvorty.weaversparadise.init.WeaversParadiseInterfaces;
import xox.labvorty.weaversparadise.init.WeaversParadiseItems;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class DyemakingMenu extends AbstractContainerMenu implements Supplier<Map<Integer, Slot>> {
    public final static HashMap<String, Object> guistate = new HashMap<>();
    public final Level world;
    public final Player entity;
    public int x, y, z;
    private ContainerLevelAccess access = ContainerLevelAccess.NULL;
    private Container internal;
    private final Map<Integer, Slot> customSlots = new HashMap<>();
    private boolean bound = false;
    private BlockEntity boundBlockEntity = null;

    public DyemakingMenu(int id, Inventory inv, BlockPos pos) {
        super(WeaversParadiseInterfaces.DYEMAKING_MENU, id);
        this.entity = inv.player;
        this.world = inv.player.level();
        this.internal = WPItemContainers.sized(13, null);
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
        this.customSlots.put(0, this.addSlot(new Slot(internal, 0, 146, 34) {
            @Override
            public void onTake(@NotNull Player player, @NotNull ItemStack itemStack) {
                super.onTake(player, itemStack);
                slotChanged(0, 1, 0);
            }

            @Override
            public boolean mayPlace(@NotNull ItemStack itemStack) {
                return false;
            }
        }));
        this.customSlots.put(1, this.addSlot(new Slot(internal, 1, 59, 13) {
            @Override
            public boolean mayPlace(@NotNull ItemStack itemStack) {
                return itemStack.is(TagKey.create(Registries.ITEM, ResourceLocation.parse("weaversparadise:vanilla_dyes"))) || itemStack.is(TagKey.create(Registries.ITEM, ResourceLocation.parse("weaversparadise:custom_dyes")));
            }
        }));
        this.customSlots.put(2, this.addSlot(new Slot(internal, 2, 77, 13) {
            @Override
            public boolean mayPlace(@NotNull ItemStack itemStack) {
                return itemStack.is(TagKey.create(Registries.ITEM, ResourceLocation.parse("weaversparadise:vanilla_dyes"))) || itemStack.is(TagKey.create(Registries.ITEM, ResourceLocation.parse("weaversparadise:custom_dyes")));
            }
        }));
        this.customSlots.put(3, this.addSlot(new Slot(internal, 3, 95, 13) {
            @Override
            public boolean mayPlace(@NotNull ItemStack itemStack) {
                return itemStack.is(TagKey.create(Registries.ITEM, ResourceLocation.parse("weaversparadise:vanilla_dyes"))) || itemStack.is(TagKey.create(Registries.ITEM, ResourceLocation.parse("weaversparadise:custom_dyes")));
            }
        }));
        this.customSlots.put(4, this.addSlot(new Slot(internal, 4, 59, 31) {
            @Override
            public boolean mayPlace(@NotNull ItemStack itemStack) {
                return itemStack.is(TagKey.create(Registries.ITEM, ResourceLocation.parse("weaversparadise:vanilla_dyes"))) || itemStack.is(TagKey.create(Registries.ITEM, ResourceLocation.parse("weaversparadise:custom_dyes")));
            }
        }));
        this.customSlots.put(5, this.addSlot(new Slot(internal, 5, 77, 31) {
            @Override
            public boolean mayPlace(@NotNull ItemStack itemStack) {
                return itemStack.is(TagKey.create(Registries.ITEM, ResourceLocation.parse("weaversparadise:vanilla_dyes"))) || itemStack.is(TagKey.create(Registries.ITEM, ResourceLocation.parse("weaversparadise:custom_dyes")));
            }
        }));
        this.customSlots.put(6, this.addSlot(new Slot(internal, 6, 95, 31) {
            @Override
            public boolean mayPlace(@NotNull ItemStack itemStack) {
                return itemStack.is(TagKey.create(Registries.ITEM, ResourceLocation.parse("weaversparadise:vanilla_dyes"))) || itemStack.is(TagKey.create(Registries.ITEM, ResourceLocation.parse("weaversparadise:custom_dyes")));
            }
        }));
        this.customSlots.put(7, this.addSlot(new Slot(internal, 7, 59, 49) {
            @Override
            public boolean mayPlace(@NotNull ItemStack itemStack) {
                return itemStack.is(TagKey.create(Registries.ITEM, ResourceLocation.parse("weaversparadise:vanilla_dyes"))) || itemStack.is(TagKey.create(Registries.ITEM, ResourceLocation.parse("weaversparadise:custom_dyes")));
            }
        }));
        this.customSlots.put(8, this.addSlot(new Slot(internal, 8, 77, 49) {
            @Override
            public boolean mayPlace(@NotNull ItemStack itemStack) {
                return itemStack.is(TagKey.create(Registries.ITEM, ResourceLocation.parse("weaversparadise:vanilla_dyes"))) || itemStack.is(TagKey.create(Registries.ITEM, ResourceLocation.parse("weaversparadise:custom_dyes")));
            }
        }));
        this.customSlots.put(9, this.addSlot(new Slot(internal, 9, 95, 49) {
            @Override
            public boolean mayPlace(@NotNull ItemStack itemStack) {
                return itemStack.is(TagKey.create(Registries.ITEM, ResourceLocation.parse("weaversparadise:vanilla_dyes"))) || itemStack.is(TagKey.create(Registries.ITEM, ResourceLocation.parse("weaversparadise:custom_dyes")));
            }
        }));
        this.customSlots.put(10, this.addSlot(new Slot(internal, 10, 21, 60) {
            @Override
            public boolean mayPlace(@NotNull ItemStack itemStack) {
                return itemStack.is(Items.GLASS_BOTTLE);
            }
        }));
        this.customSlots.put(11, this.addSlot(new Slot(internal, 11, 21, 78) {
            @Override
            public boolean mayPlace(@NotNull ItemStack itemStack) {
                return itemStack.is(Items.POTION) || itemStack.is(WeaversParadiseItems.BOTTLED_DYE) || itemStack.is(Items.GLASS_BOTTLE);
            }
        }));
        this.customSlots.put(12, this.addSlot(new Slot(internal, 12, 138, 72) {
            @Override
            public boolean mayPlace(@NotNull ItemStack itemStack) {
                return itemStack.is(WeaversParadiseItems.DYE_CORE);
            }
        }));
        for (int si = 0; si < 3; ++si)
            for (int sj = 0; sj < 9; ++sj)
                this.addSlot(new Slot(inv, sj + (si + 1) * 9, 8 + sj * 18, 35 + 84 + si * 18));
        for (int si = 0; si < 9; ++si)
            this.addSlot(new Slot(inv, si, 8 + si * 18, 35 + 142));
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
        Slot slot = (Slot) this.slots.get(index);
        if (slot != null && slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();
            if (index < 13) {
                if (!this.moveItemStackTo(itemstack1, 13, this.slots.size(), true))
                    return ItemStack.EMPTY;
                slot.onQuickCraft(itemstack1, itemstack);
            } else if (!this.moveItemStackTo(itemstack1, 0, 13, false)) {
                if (index < 13 + 27) {
                    if (!this.moveItemStackTo(itemstack1, 13 + 27, this.slots.size(), true))
                        return ItemStack.EMPTY;
                } else {
                    if (!this.moveItemStackTo(itemstack1, 13, 13 + 27, false))
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
    protected boolean moveItemStackTo(@NotNull ItemStack p_38904_, int p_38905_, int p_38906_, boolean p_38907_) {
        boolean flag = false;
        int i = p_38905_;
        if (p_38907_) {
            i = p_38906_ - 1;
        }
        if (p_38904_.isStackable()) {
            while (!p_38904_.isEmpty() && (p_38907_ ? i >= p_38905_ : i < p_38906_)) {
                Slot slot = this.slots.get(i);
                ItemStack itemstack = slot.getItem();
                if (slot.mayPlace(itemstack) && !itemstack.isEmpty() && ItemStack.isSameItemSameComponents(p_38904_, itemstack)) {
                    int j = itemstack.getCount() + p_38904_.getCount();
                    int k = slot.getMaxStackSize(itemstack);
                    if (j <= k) {
                        p_38904_.setCount(0);
                        itemstack.setCount(j);
                        slot.set(itemstack);
                        flag = true;
                    } else if (itemstack.getCount() < k) {
                        p_38904_.shrink(k - itemstack.getCount());
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
        if (!p_38904_.isEmpty()) {
            if (p_38907_) {
                i = p_38906_ - 1;
            } else {
                i = p_38905_;
            }
            while (p_38907_ ? i >= p_38905_ : i < p_38906_) {
                Slot slot1 = this.slots.get(i);
                ItemStack itemstack1 = slot1.getItem();
                if (itemstack1.isEmpty() && slot1.mayPlace(p_38904_)) {
                    int l = slot1.getMaxStackSize(p_38904_);
                    slot1.setByPlayer(p_38904_.split(Math.min(p_38904_.getCount(), l)));
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

    /**
     * Server-side ingredient consumption (runs on the server menu instance when
     * the result is taken; the client menu instance does nothing).
     */
    private void slotChanged(int slotid, int ctype, int meta) {
        if (this.world == null || this.world.isClientSide()) {
            return;
        }
        if (slotid == 0 && ctype == 1) {
            for (int i = 1; i < 13; i++) {
                ItemStack stack = internal.getItem(i);
                stack.shrink(1);
                if (i == 11) {
                    stack = new ItemStack(Items.GLASS_BOTTLE);
                }
                internal.setItem(i, stack);
            }
        }
    }
}
