package xox.labvorty.weaversparadise.gui.menu;

import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.component.CustomData;
import net.neoforged.neoforge.items.wrapper.InvWrapper;
import net.neoforged.neoforge.items.SlotItemHandler;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.neoforged.neoforge.items.IItemHandlerModifiable;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.capabilities.Capabilities;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.core.BlockPos;
import net.neoforged.neoforge.network.PacketDistributor;
import xox.labvorty.weaversparadise.data.DyeingNetworkMessage;
import xox.labvorty.weaversparadise.data.DyemakingNetworkMessage;
import xox.labvorty.weaversparadise.init.WeaversParadiseInterfaces;
import xox.labvorty.weaversparadise.init.WeaversParadiseItems;

import java.util.List;
import java.util.function.Supplier;
import java.util.Map;
import java.util.HashMap;


public class DyeingMenu extends AbstractContainerMenu implements Supplier<Map<Integer, Slot>> {
    public final static HashMap<String, Object> guistate = new HashMap<>();
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
    private static final List<String> dyeTypes = List.of(
            "agender",
            "aroace",
            "aromantic",
            "asexual",
            "bisexual",
            "demiboy",
            "demigender",
            "demigirl",
            "gay",
            "genderfluid",
            "genderqueer",
            "intersex",
            "lesbian",
            "nonbinary",
            "pansexual",
            "pride",
            "trans"
    );

    public DyeingMenu(int id, Inventory inv, FriendlyByteBuf extraData) {
        super(WeaversParadiseInterfaces.DYEING_MENU.get(), id);
        this.entity = inv.player;
        this.world = inv.player.level();
        this.internal = new ItemStackHandler(8);
        BlockPos pos = null;
        if (extraData != null) {
            pos = extraData.readBlockPos();
            this.x = pos.getX();
            this.y = pos.getY();
            this.z = pos.getZ();
            access = ContainerLevelAccess.create(world, pos);
        }
        if (pos != null) {
            if (extraData.readableBytes() == 1) { // bound to item
                byte hand = extraData.readByte();
                ItemStack itemstack = hand == 0 ? this.entity.getMainHandItem() : this.entity.getOffhandItem();
                this.boundItemMatcher = () -> itemstack == (hand == 0 ? this.entity.getMainHandItem() : this.entity.getOffhandItem());
                IItemHandler cap = itemstack.getCapability(Capabilities.ItemHandler.ITEM);
                if (cap != null) {
                    this.internal = cap;
                    this.bound = true;
                }
            } else if (extraData.readableBytes() > 1) { // bound to entity
                extraData.readByte(); // drop padding
                boundEntity = world.getEntity(extraData.readVarInt());
                if (boundEntity != null) {
                    IItemHandler cap = boundEntity.getCapability(Capabilities.ItemHandler.ENTITY);
                    if (cap != null) {
                        this.internal = cap;
                        this.bound = true;
                    }
                }
            } else { // might be bound to block
                boundBlockEntity = this.world.getBlockEntity(pos);
                if (boundBlockEntity instanceof BaseContainerBlockEntity baseContainerBlockEntity) {
                    this.internal = new InvWrapper(baseContainerBlockEntity);
                    this.bound = true;
                }
            }
        }
        this.customSlots.put(0, this.addSlot(new SlotItemHandler(internal, 0, 58, 103) {
            private final int slot = 0;
            private int x = DyeingMenu.this.x;
            private int y = DyeingMenu.this.y;
        }));
        this.customSlots.put(1, this.addSlot(new SlotItemHandler(internal, 1, 102, 103) {
            private final int slot = 1;
            private int x = DyeingMenu.this.x;
            private int y = DyeingMenu.this.y;

            @Override
            public boolean mayPlace(ItemStack stack) {
                return false;
            }

            @Override
            public void onTake(Player player, ItemStack stack) {
                super.onTake(player, stack);

                boolean leftdyes = false;
                boolean rightdyes = false;

                if (internal.getStackInSlot(4).is(WeaversParadiseItems.BOTTLED_DYE) || internal.getStackInSlot(5).is(WeaversParadiseItems.BOTTLED_DYE)) {
                    leftdyes = true;
                }

                if (internal.getStackInSlot(6).is(WeaversParadiseItems.BOTTLED_DYE) || internal.getStackInSlot(7).is(WeaversParadiseItems.BOTTLED_DYE)) {
                    rightdyes = true;
                }

                slotChanged(leftdyes, rightdyes);
            }
        }));
        this.customSlots.put(2, this.addSlot(new SlotItemHandler(internal, 2, 21, 67) {
            private final int slot = 2;
            private int x = DyeingMenu.this.x;
            private int y = DyeingMenu.this.y;

            @Override
            public boolean mayPlace(ItemStack stack) {
                if (
                        internal.getStackInSlot(0).is(WeaversParadiseItems.THIGH_HIGHS_COTTON)
                                || internal.getStackInSlot(0).is(WeaversParadiseItems.THIGH_HIGHS_WOOL)
                                || internal.getStackInSlot(0).is(WeaversParadiseItems.THIGH_HIGHS_SILK)
                                || internal.getStackInSlot(0).is(WeaversParadiseItems.HAND_WARMERS_COTTON)
                                || internal.getStackInSlot(0).is(WeaversParadiseItems.HAND_WARMERS_SILK)
                                || internal.getStackInSlot(0).is(WeaversParadiseItems.HAND_WARMERS_WOOL)
                ) {
                    if (stack.is(ItemTags.create(ResourceLocation.parse("weaversparadise:thigh_highs_stensils")))) {
                        return true;
                    }
                } else if (
                        internal.getStackInSlot(0).is(WeaversParadiseItems.SHIRT_COTTON)
                                || internal.getStackInSlot(0).is(WeaversParadiseItems.SHIRT_SILK)
                                || internal.getStackInSlot(0).is(WeaversParadiseItems.SWEATER_WOOL)
                ) {
                    if (stack.is(ItemTags.create(ResourceLocation.parse("weaversparadise:shirts_stensils")))) {
                        return true;
                    }
                }

                return false;
            }
        }));
        this.customSlots.put(3, this.addSlot(new SlotItemHandler(internal, 3, 137, 66) {
            private final int slot = 3;
            private int x = DyeingMenu.this.x;
            private int y = DyeingMenu.this.y;

            @Override
            public boolean mayPlace(ItemStack stack) {
                if (
                        internal.getStackInSlot(0).is(WeaversParadiseItems.THIGH_HIGHS_COTTON)
                                || internal.getStackInSlot(0).is(WeaversParadiseItems.THIGH_HIGHS_WOOL)
                                || internal.getStackInSlot(0).is(WeaversParadiseItems.THIGH_HIGHS_SILK)
                                || internal.getStackInSlot(0).is(WeaversParadiseItems.HAND_WARMERS_COTTON)
                                || internal.getStackInSlot(0).is(WeaversParadiseItems.HAND_WARMERS_SILK)
                                || internal.getStackInSlot(0).is(WeaversParadiseItems.HAND_WARMERS_WOOL)
                ) {
                    if (stack.is(ItemTags.create(ResourceLocation.parse("weaversparadise:thigh_highs_stensils")))) {
                        return true;
                    }
                }

                return false;
            }
        }));
        this.customSlots.put(4, this.addSlot(new SlotItemHandler(internal, 4, 10, 88) {
            private final int slot = 4;
            private int x = DyeingMenu.this.x;
            private int y = DyeingMenu.this.y;

            @Override
            public boolean mayPlace(ItemStack stack) {
                return stack.is(WeaversParadiseItems.BOTTLED_DYE);
            }
        }));
        this.customSlots.put(5, this.addSlot(new SlotItemHandler(internal, 5, 32, 88) {
            private final int slot = 5;
            private int x = DyeingMenu.this.x;
            private int y = DyeingMenu.this.y;

            @Override
            public boolean mayPlace(ItemStack stack) {
                CompoundTag tags = stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag();

                if (internal.getStackInSlot(2).is(ItemTags.create(ResourceLocation.parse("weaversparadise:stensils")))) {
                    if (stack.is(WeaversParadiseItems.BOTTLED_DYE)) {
                        boolean isPride = false;
                        for (String entry : dyeTypes) {
                            if (tags.getString("dyeType").equals(entry)) {
                                isPride = true;
                                break;
                            }
                        }
                        return !isPride;
                    }
                }

                return stack.is(WeaversParadiseItems.BOTTLED_DYE);
            }
        }));
        this.customSlots.put(6, this.addSlot(new SlotItemHandler(internal, 6, 126, 87) {
            private final int slot = 6;
            private int x = DyeingMenu.this.x;
            private int y = DyeingMenu.this.y;

            @Override
            public boolean mayPlace(ItemStack stack) {
                CompoundTag tags = stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag();

                if (internal.getStackInSlot(3).is(ItemTags.create(ResourceLocation.parse("weaversparadise:stensils")))) {
                    if (stack.is(WeaversParadiseItems.BOTTLED_DYE)) {
                        boolean isPride = false;
                        for (String entry : dyeTypes) {
                            if (tags.getString("dyeType").equals(entry)) {
                                isPride = true;
                                break;
                            }
                        }
                        return !isPride;
                    }
                }

                if (internal.getStackInSlot(0).is(WeaversParadiseItems.SHIRT_COTTON) || internal.getStackInSlot(0).is(WeaversParadiseItems.SHIRT_SILK) || internal.getStackInSlot(0).is(WeaversParadiseItems.SWEATER_WOOL)) {
                    return false;
                }

                return stack.is(WeaversParadiseItems.BOTTLED_DYE);
            }
        }));
        this.customSlots.put(7, this.addSlot(new SlotItemHandler(internal, 7, 148, 87) {
            private final int slot = 7;
            private int x = DyeingMenu.this.x;
            private int y = DyeingMenu.this.y;

            @Override
            public boolean mayPlace(ItemStack stack) {
                if (internal.getStackInSlot(3).isEmpty()) {
                    return false;
                }

                CompoundTag tags = stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag();

                if (internal.getStackInSlot(3).is(ItemTags.create(ResourceLocation.parse("weaversparadise:stensils")))) {
                    if (stack.is(WeaversParadiseItems.BOTTLED_DYE)) {
                        boolean isPride = false;
                        for (String entry : dyeTypes) {
                            if (tags.getString("dyeType").equals(entry)) {
                                isPride = true;
                                break;
                            }
                        }
                        return !isPride;
                    }
                }

                if (internal.getStackInSlot(0).is(WeaversParadiseItems.SHIRT_COTTON) || internal.getStackInSlot(0).is(WeaversParadiseItems.SHIRT_SILK) || internal.getStackInSlot(0).is(WeaversParadiseItems.SWEATER_WOOL)) {
                    return false;
                }

                return stack.is(WeaversParadiseItems.BOTTLED_DYE);
            }
        }));
        for (int si = 0; si < 3; ++si)
            for (int sj = 0; sj < 9; ++sj)
                this.addSlot(new Slot(inv, sj + (si + 1) * 9, 0 + 8 + sj * 18, 54 + 84 + si * 18));
        for (int si = 0; si < 9; ++si)
            this.addSlot(new Slot(inv, si, 0 + 8 + si * 18, 54 + 142));
    }

    @Override
    public boolean stillValid(Player player) {
        if (this.bound) {
            if (this.boundItemMatcher != null)
                return this.boundItemMatcher.get();
            else if (this.boundBlockEntity != null)
                return AbstractContainerMenu.stillValid(this.access, player, this.boundBlockEntity.getBlockState().getBlock());
            else if (this.boundEntity != null)
                return this.boundEntity.isAlive();
        }
        return true;
    }

    @Override
    public ItemStack quickMoveStack(Player playerIn, int index) {
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
            slot.onTake(playerIn, itemstack1);
        }
        return itemstack;
    }

    @Override
    protected boolean moveItemStackTo(ItemStack p_38904_, int p_38905_, int p_38906_, boolean p_38907_) {
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
    public void removed(Player playerIn) {
        super.removed(playerIn);
        if (!bound && playerIn instanceof ServerPlayer serverPlayer) {
            if (!serverPlayer.isAlive() || serverPlayer.hasDisconnected()) {
                for (int j = 0; j < internal.getSlots(); ++j) {
                    playerIn.drop(internal.getStackInSlot(j), false);
                    if (internal instanceof IItemHandlerModifiable ihm)
                        ihm.setStackInSlot(j, ItemStack.EMPTY);
                }
            } else {
                for (int i = 0; i < internal.getSlots(); ++i) {
                    playerIn.getInventory().placeItemBackInInventory(internal.getStackInSlot(i));
                    if (internal instanceof IItemHandlerModifiable ihm)
                        ihm.setStackInSlot(i, ItemStack.EMPTY);
                }
            }
        }
    }

    public Map<Integer, Slot> get() {
        return customSlots;
    }

    private void slotChanged(boolean leftdyes, boolean rightdyes) {
        if (this.world != null && this.world.isClientSide()) {
            PacketDistributor.sendToServer(new DyeingNetworkMessage(x, y, z, leftdyes, rightdyes));
        }
    }
}
