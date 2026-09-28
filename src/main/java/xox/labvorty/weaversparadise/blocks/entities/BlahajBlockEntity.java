package xox.labvorty.weaversparadise.blocks.entities;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import xox.labvorty.weaversparadise.init.WeaversParadiseBlockEntities;

public class BlahajBlockEntity extends BlockEntity {
    private ItemStack itemStack = ItemStack.EMPTY;

    public BlahajBlockEntity(BlockPos pos, BlockState state) {
        super(WeaversParadiseBlockEntities.BLAHAJ_BE.get(), pos, state);
    }

    public void setItemStack(ItemStack stack) {
        this.itemStack = stack.copy();
        this.itemStack.setCount(1);
        setChanged();
        if (level != null && !level.isClientSide) {
            level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3);
        }
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag tag) {
        super.saveAdditional(tag);
        if (!itemStack.isEmpty()) {
            tag.put("item", itemStack.save(new CompoundTag()));
        }
    }

    @Override
    public void load(@NotNull CompoundTag tag) {
        super.load(tag);
        if (tag.contains("item", Tag.TAG_COMPOUND)) {
            this.itemStack = ItemStack.of(tag.getCompound("item"));
        } else {
            this.itemStack = ItemStack.EMPTY;
        }
    }

    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public @NotNull CompoundTag getUpdateTag() {
        return saveWithoutMetadata();
    }
}