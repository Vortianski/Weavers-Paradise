
package xox.labvorty.weaversparadise.blocks.entities;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xox.labvorty.weaversparadise.init.WeaversParadiseBlockEntities;

public class ClothBlockEntity extends BlockEntity {
    private int quality = 0;

    public ClothBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(WeaversParadiseBlockEntities.CLOTH_BE.get(), blockPos, blockState);
    }

    public int getQuality() {
        return quality;
    }

    public void setQuality(int quality) {
        if (this.quality == quality) {
            return;
        }

        this.quality = quality;
        this.setChanged();

        if (this.level != null && !this.level.isClientSide) {
            BlockState state = this.getBlockState();

            this.level.sendBlockUpdated(
                    this.worldPosition,
                    state,
                    state,
                    3
            );
        }
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag tag) {
        super.saveAdditional(tag);
        tag.putInt("quality", this.quality);
    }

    @Override
    public void load(@NotNull CompoundTag tag) {
        super.load(tag);
        this.quality = tag.getInt("quality");
    }

    @Override
    @Nullable
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public @NotNull CompoundTag getUpdateTag() {
        return this.saveWithoutMetadata();
    }
}
