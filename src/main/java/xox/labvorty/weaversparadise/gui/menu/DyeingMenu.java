package xox.labvorty.weaversparadise.gui.menu;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
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
import xox.labvorty.weaversparadise.WeaversParadiseMod;
import xox.labvorty.weaversparadise.data.network.DyeingNetworkMessage;
import xox.labvorty.weaversparadise.init.WeaversParadiseItems;
import xox.labvorty.weaversparadise.init.WeaversParadiseMenus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class DyeingMenu extends AbstractContainerMenu implements Supplier<Map<Integer, Slot>> {
    public static final HashMap<String, Object> guistate = new HashMap<>();

    public final Level world;
    public final Player entity;
    public int x, y, z;

    private ContainerLevelAccess access = ContainerLevelAccess.NULL;
    private IItemHandler internal;
    private final Map<Integer, Slot> customSlots = new HashMap<>();
    private boolean bound = false;
    private Supplier<Boolean> boundItemMatcher = null;
    private Entity boundEntity = null;
    private BlockEntity boundBlockEntity = null;

    private final List<Item> singleItems = List.of(
            WeaversParadiseItems.SHIRT_COTTON.get(),
            WeaversParadiseItems.SHIRT_SILK.get(),
            WeaversParadiseItems.SWEATER_WOOL.get(),
            WeaversParadiseItems.PANTS_COTTON.get(),
            WeaversParadiseItems.PANTS_JEANS.get(),
            WeaversParadiseItems.PANTS_SILK.get(),
            WeaversParadiseItems.COTTON_CAPE.get(),
            WeaversParadiseItems.SILK_CAPE.get(),
            WeaversParadiseItems.WOOL_CAPE.get()
    );

    public DyeingMenu(int id, Inventory inv, FriendlyByteBuf extraData) {
        super(WeaversParadiseMenus.DYEING_MENU.get(), id);
        this.entity = inv.player;
        this.world = inv.player.level();
        this.internal = new ItemStackHandler(8);

        BlockPos pos = null;
        if (extraData != null) {
            pos = extraData.readBlockPos();
            this.x = pos.getX();
            this.y = pos.getY();
            this.z = pos.getZ();
            this.access = ContainerLevelAccess.create(world, pos);
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
                this.boundEntity = world.getEntity(extraData.readVarInt());

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

        this.customSlots.put(0, this.addSlot(new SlotItemHandler(internal, 0, 58, 103) {
        }));

        this.customSlots.put(1, this.addSlot(new SlotItemHandler(internal, 1, 102, 103) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return false;
            }

            @Override
            public void onTake(Player player, ItemStack stack) {
                super.onTake(player, stack);

                boolean leftdyes = false;
                boolean rightdyes = false;

                if (internal.getStackInSlot(4).is(WeaversParadiseItems.BOTTLED_DYE.get())
                        || internal.getStackInSlot(5).is(WeaversParadiseItems.BOTTLED_DYE.get())) {
                    leftdyes = true;
                }

                if (internal.getStackInSlot(6).is(WeaversParadiseItems.BOTTLED_DYE.get())
                        || internal.getStackInSlot(7).is(WeaversParadiseItems.BOTTLED_DYE.get())) {
                    rightdyes = true;
                }

                slotChanged(leftdyes, rightdyes);
            }
        }));

        this.customSlots.put(2, this.addSlot(new SlotItemHandler(internal, 2, 21, 67) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                if (internal.getStackInSlot(0).is(WeaversParadiseItems.THIGH_HIGHS_COTTON.get())
                        || internal.getStackInSlot(0).is(WeaversParadiseItems.THIGH_HIGHS_WOOL.get())
                        || internal.getStackInSlot(0).is(WeaversParadiseItems.THIGH_HIGHS_SILK.get())
                        || internal.getStackInSlot(0).is(WeaversParadiseItems.HAND_WARMERS_COTTON.get())
                        || internal.getStackInSlot(0).is(WeaversParadiseItems.HAND_WARMERS_SILK.get())
                        || internal.getStackInSlot(0).is(WeaversParadiseItems.HAND_WARMERS_WOOL.get())) {

                    return stack.is(ItemTags.create(ResourceLocation.fromNamespaceAndPath("weaversparadise", "thigh_highs_stensils")));

                } else if (internal.getStackInSlot(0).is(WeaversParadiseItems.SHIRT_COTTON.get())
                        || internal.getStackInSlot(0).is(WeaversParadiseItems.SHIRT_SILK.get())
                        || internal.getStackInSlot(0).is(WeaversParadiseItems.SWEATER_WOOL.get())) {

                    return stack.is(ItemTags.create(ResourceLocation.fromNamespaceAndPath("weaversparadise", "shirts_stensils")));

                } else if (internal.getStackInSlot(0).is(WeaversParadiseItems.COTTON_CAPE.get())
                        || internal.getStackInSlot(0).is(WeaversParadiseItems.SILK_CAPE.get())
                        || internal.getStackInSlot(0).is(WeaversParadiseItems.WOOL_CAPE.get())) {

                    return stack.is(ItemTags.create(ResourceLocation.fromNamespaceAndPath("weaversparadise", "cape_stensils")));

                } else if (internal.getStackInSlot(0).is(WeaversParadiseItems.CHOKER.get())) {

                    return stack.is(ItemTags.create(ResourceLocation.fromNamespaceAndPath("weaversparadise", "choker_stencils")));

                } else if (internal.getStackInSlot(0).is(WeaversParadiseItems.PANTS_JEANS.get())
                        || internal.getStackInSlot(0).is(WeaversParadiseItems.PANTS_COTTON.get())
                        || internal.getStackInSlot(0).is(WeaversParadiseItems.PANTS_SILK.get())) {

                    return stack.is(ItemTags.create(ResourceLocation.fromNamespaceAndPath("weaversparadise", "pants_stencils")));
                }

                return false;
            }
        }));

        this.customSlots.put(3, this.addSlot(new SlotItemHandler(internal, 3, 137, 66) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                if (internal.getStackInSlot(0).is(WeaversParadiseItems.THIGH_HIGHS_COTTON.get())
                        || internal.getStackInSlot(0).is(WeaversParadiseItems.THIGH_HIGHS_WOOL.get())
                        || internal.getStackInSlot(0).is(WeaversParadiseItems.THIGH_HIGHS_SILK.get())
                        || internal.getStackInSlot(0).is(WeaversParadiseItems.HAND_WARMERS_COTTON.get())
                        || internal.getStackInSlot(0).is(WeaversParadiseItems.HAND_WARMERS_SILK.get())
                        || internal.getStackInSlot(0).is(WeaversParadiseItems.HAND_WARMERS_WOOL.get())) {

                    return stack.is(ItemTags.create(ResourceLocation.fromNamespaceAndPath("weaversparadise", "thigh_highs_stensils")));

                } else if (internal.getStackInSlot(0).is(WeaversParadiseItems.CHOKER.get())) {

                    return stack.is(ItemTags.create(ResourceLocation.fromNamespaceAndPath("weaversparadise", "choker_stencils")));
                }

                return false;
            }
        }));

        this.customSlots.put(4, this.addSlot(new SlotItemHandler(internal, 4, 10, 88) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return stack.is(WeaversParadiseItems.BOTTLED_DYE.get());
            }
        }));

        this.customSlots.put(5, this.addSlot(new SlotItemHandler(internal, 5, 32, 88) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                CompoundTag tags = stack.getOrCreateTag();

                if (internal.getStackInSlot(2).is(ItemTags.create(ResourceLocation.fromNamespaceAndPath("weaversparadise", "stensils")))) {
                    if (stack.is(WeaversParadiseItems.BOTTLED_DYE.get())) {
                        return true;
                    }
                }

                return stack.is(WeaversParadiseItems.BOTTLED_DYE.get());
            }
        }));

        this.customSlots.put(6, this.addSlot(new SlotItemHandler(internal, 6, 126, 87) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                CompoundTag tags = stack.getOrCreateTag();

                if (singleItems.contains(internal.getStackInSlot(0).getItem())) {
                    return false;
                }

                if (internal.getStackInSlot(3).is(ItemTags.create(ResourceLocation.fromNamespaceAndPath("weaversparadise", "stensils")))) {
                    if (stack.is(WeaversParadiseItems.BOTTLED_DYE.get())) {
                        return true;
                    }
                }

                return stack.is(WeaversParadiseItems.BOTTLED_DYE.get());
            }
        }));

        this.customSlots.put(7, this.addSlot(new SlotItemHandler(internal, 7, 148, 87) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                if (internal.getStackInSlot(3).isEmpty()) {
                    return false;
                }

                CompoundTag tags = stack.getOrCreateTag();

                if (singleItems.contains(internal.getStackInSlot(0).getItem())) {
                    return false;
                }

                if (internal.getStackInSlot(3).is(ItemTags.create(ResourceLocation.fromNamespaceAndPath("weaversparadise", "stensils")))) {
                    if (stack.is(WeaversParadiseItems.BOTTLED_DYE.get())) {
                        return true;
                    }
                }

                return stack.is(WeaversParadiseItems.BOTTLED_DYE.get());
            }
        }));

        for (int row = 0; row < 3; ++row) {
            for (int col = 0; col < 9; ++col) {
                this.addSlot(new Slot(inv, col + (row + 1) * 9, 8 + col * 18, 138 + row * 18));
            }
        }

        for (int col = 0; col < 9; ++col) {
            this.addSlot(new Slot(inv, col, 8 + col * 18, 196));
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

            if (index < 8) {
                if (!this.moveItemStackTo(stackInSlot, 8, this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
                slot.onQuickCraft(stackInSlot, itemstack);
            } else if (!this.moveItemStackTo(stackInSlot, 0, 8, false)) {
                if (index < 35) {
                    if (!this.moveItemStackTo(stackInSlot, 35, this.slots.size(), true)) {
                        return ItemStack.EMPTY;
                    }
                } else {
                    if (!this.moveItemStackTo(stackInSlot, 8, 35, false)) {
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

        if (!bound && player instanceof ServerPlayer serverPlayer) {
            if (!serverPlayer.isAlive() || serverPlayer.hasDisconnected()) {
                for (int j = 0; j < internal.getSlots(); ++j) {
                    player.drop(internal.getStackInSlot(j), false);
                    if (internal instanceof IItemHandlerModifiable modifiable) {
                        modifiable.setStackInSlot(j, ItemStack.EMPTY);
                    }
                }
            } else {
                for (int i = 0; i < internal.getSlots(); ++i) {
                    player.getInventory().placeItemBackInInventory(internal.getStackInSlot(i));
                    if (internal instanceof IItemHandlerModifiable modifiable) {
                        modifiable.setStackInSlot(i, ItemStack.EMPTY);
                    }
                }
            }
        }
    }

    @Override
    public Map<Integer, Slot> get() {
        return customSlots;
    }

    private void slotChanged(boolean leftdyes, boolean rightdyes) {
        if (this.world != null && this.world.isClientSide()) {
            WeaversParadiseMod.PACKET_HANDLER.sendToServer(new DyeingNetworkMessage(x, y, z, leftdyes, rightdyes));
        }
    }
}