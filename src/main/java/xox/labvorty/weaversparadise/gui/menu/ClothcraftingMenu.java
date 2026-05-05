package xox.labvorty.weaversparadise.gui.menu;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
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
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;
import xox.labvorty.weaversparadise.init.WeaversParadiseItems;
import xox.labvorty.weaversparadise.init.WeaversParadiseMenus;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class ClothcraftingMenu extends AbstractContainerMenu implements Supplier<Map<Integer, Slot>> {
    public static final HashMap<String, Object> guistate = new HashMap<>();

    public final Level world;
    public final Player entity;

    public int x, y, z;

    private ContainerLevelAccess access = ContainerLevelAccess.NULL;
    private IItemHandler internal = new ItemStackHandler(1);
    private final Map<Integer, Slot> customSlots = new HashMap<>();

    private boolean bound = false;
    private Supplier<Boolean> boundItemMatcher = null;
    private Entity boundEntity = null;
    private BlockEntity boundBlockEntity = null;

    public ClothcraftingMenu(int id, Inventory inv, FriendlyByteBuf extraData) {
        super(WeaversParadiseMenus.CLOTHCRAFTING_MENU.get(), id);
        this.entity = inv.player;
        this.world = inv.player.level();

        BlockPos pos = null;
        if (extraData != null) {
            pos = extraData.readBlockPos();
            this.x = pos.getX();
            this.y = pos.getY();
            this.z = pos.getZ();
            this.access = ContainerLevelAccess.create(this.world, pos);
        }

        if (pos != null) {
            if (extraData.readableBytes() == 1) {
                // bound to item
                byte hand = extraData.readByte();
                ItemStack itemstack = hand == 0 ? this.entity.getMainHandItem() : this.entity.getOffhandItem();
                this.boundItemMatcher = () -> itemstack == (hand == 0 ? this.entity.getMainHandItem() : this.entity.getOffhandItem());

                LazyOptional<IItemHandler> cap = itemstack.getCapability(ForgeCapabilities.ITEM_HANDLER, null);
                cap.ifPresent(handler -> {
                    this.internal = handler;
                    this.bound = true;
                });

            } else if (extraData.readableBytes() > 1) {
                // bound to entity
                extraData.readByte(); // drop padding
                this.boundEntity = this.world.getEntity(extraData.readVarInt());

                if (this.boundEntity != null) {
                    LazyOptional<IItemHandler> cap = this.boundEntity.getCapability(ForgeCapabilities.ITEM_HANDLER, null);
                    cap.ifPresent(handler -> {
                        this.internal = handler;
                        this.bound = true;
                    });
                }

            } else {
                // might be bound to block
                this.boundBlockEntity = this.world.getBlockEntity(pos);
                if (this.boundBlockEntity instanceof BaseContainerBlockEntity baseContainerBlockEntity) {
                    this.internal = new InvWrapper(baseContainerBlockEntity);
                    this.bound = true;
                } else if (this.boundBlockEntity != null) {
                    LazyOptional<IItemHandler> cap = this.boundBlockEntity.getCapability(ForgeCapabilities.ITEM_HANDLER, null);
                    cap.ifPresent(handler -> {
                        this.internal = handler;
                        this.bound = true;
                    });
                }
            }
        }

        this.customSlots.put(0, this.addSlot(new SlotItemHandler(this.internal, 0, 47, 1) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return stack.is(WeaversParadiseItems.JEANS_SPOOL.get())
                        || stack.is(WeaversParadiseItems.COTTON_SPOOL.get())
                        || stack.is(WeaversParadiseItems.SILK_SPOOL.get())
                        || stack.is(WeaversParadiseItems.WOOL_SPOOL.get());
            }
        }));

        // Player inventory
        for (int row = 0; row < 3; ++row) {
            for (int col = 0; col < 9; ++col) {
                this.addSlot(new Slot(inv, col + (row + 1) * 9, 8 + col * 18, 127 + row * 18));
            }
        }

        // Hotbar
        for (int col = 0; col < 9; ++col) {
            this.addSlot(new Slot(inv, col, 8 + col * 18, 185));
        }
    }

    @Override
    public boolean stillValid(Player player) {
        if (this.bound) {
            if (this.boundItemMatcher != null) {
                return this.boundItemMatcher.get();
            } else if (this.boundBlockEntity != null) {
                return AbstractContainerMenu.stillValid(this.access, player, this.boundBlockEntity.getBlockState().getBlock());
            } else if (this.boundEntity != null) {
                return this.boundEntity.isAlive();
            }
        }
        return true;
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);

        if (slot != null && slot.hasItem()) {
            ItemStack stackInSlot = slot.getItem();
            itemstack = stackInSlot.copy();

            if (index < 1) {
                if (!this.moveItemStackTo(stackInSlot, 1, this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
                slot.onQuickCraft(stackInSlot, itemstack);
            } else if (!this.moveItemStackTo(stackInSlot, 0, 1, false)) {
                if (index < 28) {
                    if (!this.moveItemStackTo(stackInSlot, 28, this.slots.size(), true)) {
                        return ItemStack.EMPTY;
                    }
                } else {
                    if (!this.moveItemStackTo(stackInSlot, 1, 28, false)) {
                        return ItemStack.EMPTY;
                    }
                }
                return ItemStack.EMPTY;
            }

            if (stackInSlot.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if (stackInSlot.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(player, stackInSlot);
        }

        return itemstack;
    }

    @Override
    protected boolean moveItemStackTo(ItemStack stack, int startIndex, int endIndex, boolean reverseDirection) {
        boolean flag = false;
        int i = reverseDirection ? endIndex - 1 : startIndex;

        if (stack.isStackable()) {
            while (!stack.isEmpty() && (reverseDirection ? i >= startIndex : i < endIndex)) {
                Slot slot = this.slots.get(i);
                ItemStack existing = slot.getItem();

                if (!existing.isEmpty()
                        && slot.mayPlace(stack)
                        && ItemStack.isSameItemSameTags(stack, existing)) {

                    int combined = existing.getCount() + stack.getCount();
                    int maxSize = slot.getMaxStackSize(existing);

                    if (combined <= maxSize) {
                        stack.setCount(0);
                        existing.setCount(combined);
                        slot.set(existing);
                        flag = true;
                    } else if (existing.getCount() < maxSize) {
                        stack.shrink(maxSize - existing.getCount());
                        existing.setCount(maxSize);
                        slot.set(existing);
                        flag = true;
                    }
                }

                i += reverseDirection ? -1 : 1;
            }
        }

        if (!stack.isEmpty()) {
            i = reverseDirection ? endIndex - 1 : startIndex;

            while (reverseDirection ? i >= startIndex : i < endIndex) {
                Slot slot = this.slots.get(i);
                ItemStack existing = slot.getItem();

                if (existing.isEmpty() && slot.mayPlace(stack)) {
                    int maxSize = slot.getMaxStackSize(stack);
                    slot.setByPlayer(stack.split(Math.min(stack.getCount(), maxSize)));
                    slot.setChanged();
                    flag = true;
                    break;
                }

                i += reverseDirection ? -1 : 1;
            }
        }

        return flag;
    }

    @Override
    public void removed(Player player) {
        super.removed(player);

        if (!this.bound && player instanceof ServerPlayer serverPlayer) {
            if (!serverPlayer.isAlive() || serverPlayer.hasDisconnected()) {
                for (int j = 0; j < this.internal.getSlots(); ++j) {
                    player.drop(this.internal.getStackInSlot(j), false);
                    if (this.internal instanceof IItemHandlerModifiable modifiable) {
                        modifiable.setStackInSlot(j, ItemStack.EMPTY);
                    }
                }
            } else {
                for (int i = 0; i < this.internal.getSlots(); ++i) {
                    player.getInventory().placeItemBackInInventory(this.internal.getStackInSlot(i));
                    if (this.internal instanceof IItemHandlerModifiable modifiable) {
                        modifiable.setStackInSlot(i, ItemStack.EMPTY);
                    }
                }
            }
        }
    }

    @Override
    public Map<Integer, Slot> get() {
        return this.customSlots;
    }
}