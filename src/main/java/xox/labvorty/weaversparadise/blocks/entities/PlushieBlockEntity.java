package xox.labvorty.weaversparadise.blocks.entities;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.nbt.Tag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.item.component.ResolvableProfile;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.SkullBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xox.labvorty.weaversparadise.init.WeaversParadiseBlockEntities;

public class PlushieBlockEntity extends BlockEntity {
    private ResolvableProfile profile;

    public PlushieBlockEntity(BlockPos pos, BlockState state) {
        super(WeaversParadiseBlockEntities.PLUSHIE_BE, pos, state);
    }

    public void setProfile(ResolvableProfile profile) {
        this.profile = profile;
        setChanged();

        if (profile != null && !profile.isResolved()) {
            profile.resolve().thenAcceptAsync((resolved) -> {
                this.profile = resolved;
                setChanged();
                if (level != null) {
                    level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3);
                }
            }, SkullBlockEntity.CHECKED_MAIN_THREAD_EXECUTOR);
        }
    }

    @Nullable
    public ResolvableProfile getProfile() {
        return profile;
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag tag, HolderLookup.@NotNull Provider registries) {
        super.saveAdditional(tag, registries);
        if (profile != null) {
            ResolvableProfile.CODEC.encodeStart(NbtOps.INSTANCE, profile)
                    .resultOrPartial(err -> {})
                    .ifPresent(encoded -> tag.put("profile", encoded));
        }
    }

    @Override
    protected void loadAdditional(@NotNull CompoundTag tag, HolderLookup.@NotNull Provider registries) {
        super.loadAdditional(tag, registries);
        if (tag.contains("profile")) {
            Tag profileTag = tag.get("profile");
            ResolvableProfile.CODEC.parse(NbtOps.INSTANCE, profileTag)
                    .resultOrPartial(err -> {})
                    .ifPresent(loaded -> this.profile = loaded);
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

    @Override
    protected void collectImplicitComponents(DataComponentMap.@NotNull Builder builder) {
        super.collectImplicitComponents(builder);
        if (profile != null) {
            builder.set(DataComponents.PROFILE, profile);
        }
    }

    @Override
    public void applyImplicitComponents(@NotNull DataComponentInput componentInput) {
        super.applyImplicitComponents(componentInput);
        this.profile = componentInput.get(DataComponents.PROFILE);
    }
}