package xox.labvorty.weaversparadise.blocks.entities;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
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
        super(WeaversParadiseBlockEntities.BLAHAJ_BE, pos, state);
    }

    public void setItemStack(ItemStack stack) {
        this.itemStack = stack.copy();
        this.itemStack.setCount(1);
        setChanged();
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag tag, HolderLookup.@NotNull Provider registries) {
        super.saveAdditional(tag, registries);
        if (!itemStack.isEmpty()) {
            ItemStack.CODEC.encodeStart(registries.createSerializationContext(NbtOps.INSTANCE), itemStack)
                    .resultOrPartial(err -> {})
                    .ifPresent(encoded -> tag.put("item", encoded));
        }
    }

    @Override
    protected void loadAdditional(@NotNull CompoundTag tag, HolderLookup.@NotNull Provider registries) {
        super.loadAdditional(tag, registries);
        if (tag.contains("item")) {
            ItemStack.CODEC.parse(registries.createSerializationContext(NbtOps.INSTANCE), tag.get("item"))
                    .resultOrPartial(err -> {})
                    .ifPresent(loaded -> this.itemStack = loaded);
        }
    }

    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public @NotNull CompoundTag getUpdateTag(HolderLookup.@NotNull Provider registries) {
        return saveWithoutMetadata(registries);
    }
}