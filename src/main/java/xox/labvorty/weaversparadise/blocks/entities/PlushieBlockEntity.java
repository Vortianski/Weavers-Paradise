package xox.labvorty.weaversparadise.blocks.entities;

import com.mojang.authlib.GameProfile;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.nbt.Tag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.SkullBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xox.labvorty.weaversparadise.init.WeaversParadiseBlockEntities;
import xox.labvorty.weaversparadise.items.misc.PlushieItem;

public class PlushieBlockEntity extends BlockEntity {
    @Nullable
    private GameProfile profile;

    public PlushieBlockEntity(BlockPos pos, BlockState state) {
        super(WeaversParadiseBlockEntities.PLUSHIE_BE.get(), pos, state);
    }

    public void setProfile(@Nullable GameProfile profile) {
        this.profile = profile;
        setChanged();

        if (profile != null) {
            SkullBlockEntity.updateGameprofile(profile, resolved -> {
                this.profile = resolved;
                setChanged();
                if (level != null && !level.isClientSide) {
                    level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3);
                }
            });
        }
    }

    @Nullable
    public GameProfile getProfile() {
        return profile;
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag tag) {
        super.saveAdditional(tag);
        if (profile != null) {
            tag.put(PlushieItem.TAG_SKULL_OWNER, NbtUtils.writeGameProfile(new CompoundTag(), profile));
        }
    }

    @Override
    public void load(@NotNull CompoundTag tag) {
        super.load(tag);
        if (tag.contains(PlushieItem.TAG_SKULL_OWNER, Tag.TAG_COMPOUND)) {
            setProfile(NbtUtils.readGameProfile(tag.getCompound(PlushieItem.TAG_SKULL_OWNER)));
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