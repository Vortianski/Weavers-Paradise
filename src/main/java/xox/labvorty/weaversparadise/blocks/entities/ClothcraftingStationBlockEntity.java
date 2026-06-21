package xox.labvorty.weaversparadise.blocks.entities;

import io.netty.buffer.Unpooled;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.items.wrapper.SidedInvWrapper;
import xox.labvorty.weaversparadise.gui.menu.ClothcraftingMenu;
import xox.labvorty.weaversparadise.init.WeaversParadiseBlockEntities;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class ClothcraftingStationBlockEntity extends RandomizableContainerBlockEntity implements WorldlyContainer {
    private NonNullList<ItemStack> stacks = NonNullList.withSize(1, ItemStack.EMPTY);
    private final SidedInvWrapper handler = new SidedInvWrapper(this, null);

    private int gameTime = 0;
    private int gameScore = 0;
    private boolean isGameOn = false;
    private ItemStack clothType = new ItemStack(Items.STONE);

    private List<ItemStack> items = new ArrayList<>();

    public ClothcraftingStationBlockEntity(BlockPos pos, BlockState state) {
        super(WeaversParadiseBlockEntities.CLOTHCRAFTING_STATION_BLOCK_ENTITY.get(), pos, state);
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);

        ContainerHelper.saveAllItems(tag, stacks);

        tag.putInt("gameTime", gameTime);
        tag.putInt("gameScore", gameScore);
        tag.putBoolean("isGameOn", isGameOn);

        for (int i = 0; i < items.size(); i++) {
            CompoundTag itemTag = new CompoundTag();
            items.get(i).save(itemTag);
            tag.put("item" + i, itemTag);
        }

        if (clothType != null && !clothType.isEmpty()) {
            CompoundTag compoundTag = new CompoundTag();
            clothType.save(compoundTag);
            tag.put("clothType", compoundTag);
        } else {
            CompoundTag compoundTag = new CompoundTag();
            new ItemStack(Items.STONE).save(compoundTag);
            tag.put("clothType", compoundTag);
        }

        tag.putInt("itemsSize", items.size());
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);

        this.stacks = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        ContainerHelper.loadAllItems(tag, stacks);

        this.gameTime = tag.getInt("gameTime");
        this.gameScore = tag.getInt("gameScore");
        this.isGameOn = tag.getBoolean("isGameOn");
        if (tag.contains("clothType")) {
            this.clothType = ItemStack.of(tag.getCompound("clothType"));
        } else {
            this.clothType = new ItemStack(Items.STONE);
        }

        this.items = new ArrayList<>();
        int size = tag.getInt("itemsSize");

        for (int i = 0; i < size; i++) {
            if (tag.contains("item" + i)) {
                ItemStack stack = ItemStack.of(tag.getCompound("item" + i));

                items.add(stack);
            }
        }
    }

    @Override
    public CompoundTag getUpdateTag() {
        return saveWithoutMetadata();
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    public void setGameTime(int time) {
        this.gameTime = time;
        setChanged();
    }

    public void setGameScore(int score) {
        this.gameScore = score;
        setChanged();
    }

    public void setGameOn(boolean on) {
        this.isGameOn = on;
        setChanged();
    }

    public void setItems(List<ItemStack> items) {
        this.items = items;
        setChanged();
    }

    public void setClothType(ItemStack type) {
        this.clothType = type;
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

    public ItemStack getClothType() {
        return clothType;
    }

    @Override
    public int getContainerSize() {
        return stacks.size();
    }

    @Override
    public boolean isEmpty() {
        for (ItemStack stack : stacks) {
            if (!stack.isEmpty()) return false;
        }
        return true;
    }

    @Override
    protected NonNullList<ItemStack> getItems() {
        return stacks;
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
        return IntStream.range(0, getContainerSize()).toArray();
    }

    @Override
    public boolean canPlaceItemThroughFace(int index, ItemStack stack, @Nullable Direction dir) {
        return true;
    }

    @Override
    public boolean canTakeItemThroughFace(int index, ItemStack stack, Direction dir) {
        return index != 0;
    }

    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inv) {
        return new ClothcraftingMenu(id, inv,
                new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(worldPosition));
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Clothcrafting Station");
    }

    @Override
    public Component getDefaultName() {
        return Component.literal("clothcrafting_station");
    }

    public SidedInvWrapper getItemHandler() {
        return handler;
    }
}