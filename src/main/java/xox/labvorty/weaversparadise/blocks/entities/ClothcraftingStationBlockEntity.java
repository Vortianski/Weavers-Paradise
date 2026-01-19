package xox.labvorty.weaversparadise.blocks.entities;

import net.minecraft.nbt.Tag;
import net.neoforged.neoforge.items.wrapper.SidedInvWrapper;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.ContainerHelper;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.network.chat.Component;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.NonNullList;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

import javax.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

import io.netty.buffer.Unpooled;
import xox.labvorty.weaversparadise.gui.menu.ClothcraftingMenu;
import xox.labvorty.weaversparadise.init.WeaversParadiseBlockEntities;


public class ClothcraftingStationBlockEntity extends RandomizableContainerBlockEntity implements WorldlyContainer {
    private NonNullList<ItemStack> stacks = NonNullList.<ItemStack>withSize(1, ItemStack.EMPTY);
    private final SidedInvWrapper handler = new SidedInvWrapper(this, null);
    private int gameTime;
    private int gameScore;
    private boolean isGameOn;
    private List<ItemStack> items;
    private String clothType;

    public ClothcraftingStationBlockEntity(BlockPos position, BlockState state) {
        super(WeaversParadiseBlockEntities.CLOTHCRAFTING_STATION_BE.get(), position, state);
    }

    @Override
    public void loadAdditional(CompoundTag compound, HolderLookup.Provider lookupProvider) {
        super.loadAdditional(compound, lookupProvider);
        if (!this.tryLoadLootTable(compound))
            this.stacks = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        ContainerHelper.loadAllItems(compound, this.stacks, lookupProvider);
        if (compound != null) {
            List<ItemStack> itemStacks = new ArrayList<>();
            int amount = compound.getInt("amountOfItems");
            for (int i = 0; i < amount; i++) {
                Tag tag = compound.get("item" + i);
                if (tag instanceof CompoundTag compoundTag) {
                    ItemStack.parse(lookupProvider, compoundTag).ifPresentOrElse(
                            itemStacks::add,
                            () -> itemStacks.add(ItemStack.EMPTY)
                    );
                } else {
                    itemStacks.add(ItemStack.EMPTY);
                }
            }
            this.items = itemStacks;

            this.gameTime = compound.getInt("gameTime");
            this.gameScore = compound.getInt("gameScore");
            this.isGameOn = compound.getBoolean("isGameOn");
            this.clothType = compound.getString("clothType");
        }
    }

    @Override
    public void saveAdditional(CompoundTag compound, HolderLookup.Provider lookupProvider) {
        super.saveAdditional(compound, lookupProvider);
        if (!this.trySaveLootTable(compound)) {
            ContainerHelper.saveAllItems(compound, this.stacks, lookupProvider);
        }
        int amount = 0;
        if (items != null) {
            for (int i = 0; i < items.size(); i++) {
                ItemStack item = items.get(i);
                if (!item.isEmpty()) {
                    CompoundTag tag = new CompoundTag();
                    item.save(lookupProvider, tag);
                    compound.put("item" + amount, tag);
                    amount += 1;
                }
            }
        }
        compound.putInt("amountOfItems", amount);
        compound.putInt("gameTime", gameTime);
        compound.putInt("gameScore", gameScore);
        compound.putBoolean("isGameOn", isGameOn);
        if (clothType != null) {
            compound.putString("clothType", clothType);
        }
    }

    public void setGameTime(int time) {
        gameTime = time;
        setChanged();
    }

    public void setGameScore(int score) {
        gameScore = score;
        setChanged();
    }

    public void setGameOn(boolean ison) {
        isGameOn = ison;
        setChanged();
    }

    public void setItems(List<ItemStack> item) {
        items = item;
        setChanged();
    }

    public void setClothType(String type) {
        clothType = type;
        setChanged();
    }

    public int getGameTime() {
        return gameTime;
    }

    public int getGameScore() {
        return gameScore;
    }

    public boolean getGameOn() {
        return isGameOn;
    }

    public List<ItemStack> getItemsList() {
        return items;
    }

    public String getClothType() {
        return clothType;
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider lookupProvider) {
        return this.saveWithFullMetadata(lookupProvider);
    }

    @Override
    public int getContainerSize() {
        return stacks.size();
    }

    @Override
    public boolean isEmpty() {
        for (ItemStack itemstack : this.stacks)
            if (!itemstack.isEmpty())
                return false;
        return true;
    }

    @Override
    public Component getDefaultName() {
        return Component.literal("clothcrafting_station");
    }

    @Override
    public int getMaxStackSize() {
        return 64;
    }

    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inventory) {
        return new ClothcraftingMenu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(this.worldPosition));
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Clothcrafting Station");
    }

    @Override
    protected NonNullList<ItemStack> getItems() {
        return this.stacks;
    }

    @Override
    protected void setItems(NonNullList<ItemStack> stacks) {
        this.stacks = stacks;
    }

    @Override
    public boolean canPlaceItem(int index, ItemStack stack) {
        return true;
    }

    @Override
    public int[] getSlotsForFace(Direction side) {
        return IntStream.range(0, this.getContainerSize()).toArray();
    }

    @Override
    public boolean canPlaceItemThroughFace(int index, ItemStack stack, @Nullable Direction direction) {
        return this.canPlaceItem(index, stack);
    }

    @Override
    public boolean canTakeItemThroughFace(int index, ItemStack stack, Direction direction) {
        if (index == 0)
            return false;
        return true;
    }

    public SidedInvWrapper getItemHandler() {
        return handler;
    }

}
