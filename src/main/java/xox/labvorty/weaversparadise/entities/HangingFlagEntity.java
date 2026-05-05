package xox.labvorty.weaversparadise.entities;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.decoration.HangingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xox.labvorty.weaversparadise.init.WeaversParadiseEntityTypes;
import xox.labvorty.weaversparadise.init.WeaversParadiseItems;

import java.util.Optional;

public class HangingFlagEntity extends HangingEntity {
    private static final EntityDataAccessor<String> FLAG_TYPE =
            SynchedEntityData.defineId(HangingFlagEntity.class, EntityDataSerializers.STRING);

    public HangingFlagEntity(EntityType<? extends HangingFlagEntity> type, Level level) {
        super(type, level);
    }

    public HangingFlagEntity(Level level, BlockPos pos, Direction direction, String variant) {
        this(WeaversParadiseEntityTypes.HANGING_FLAG_ENTITY.get(), level);
        this.pos = pos;
        this.setVariant(variant);
        this.setDirection(direction);
    }

    public static Optional<HangingFlagEntity> create(Level level, BlockPos pos, Direction direction, String variant) {
        if (direction == null || direction.getAxis().isVertical()) {
            return Optional.empty();
        }

        return Optional.of(new HangingFlagEntity(level, pos, direction, variant));
    }

    @Override
    protected void defineSynchedData() {
        this.entityData.define(FLAG_TYPE, "");
    }

    @Override
    public void onSyncedDataUpdated(EntityDataAccessor<?> key) {
        super.onSyncedDataUpdated(key);

        if (FLAG_TYPE.equals(key) && this.direction != null) {
            this.recalculateBoundingBox();
        }
    }

    public void setVariant(String variant) {
        this.entityData.set(FLAG_TYPE, variant);
    }

    public String getVariant() {
        return this.entityData.get(FLAG_TYPE);
    }

    @Override
    protected void recalculateBoundingBox() {
        if (this.direction == null || this.pos == null) {
            return;
        }

        double wallOffset = 0.46875D;
        int widthBlocks = 2;
        int heightBlocks = 1;
        double depth = 0.0625D;

        Vec3 center = Vec3.atCenterOf(this.pos).relative(this.direction, -wallOffset);

        double xOffset = offsetForSize(widthBlocks);
        double yOffset = offsetForSize(heightBlocks);

        Direction ccw = this.direction.getCounterClockWise();
        center = center.relative(ccw, xOffset).relative(Direction.UP, yOffset);

        Direction.Axis axis = this.direction.getAxis();
        double xSize = (axis == Direction.Axis.X) ? depth : (double) widthBlocks;
        double zSize = (axis == Direction.Axis.Z) ? depth : (double) widthBlocks;
        double ySize = (double) heightBlocks;

        this.setPosRaw(center.x, center.y, center.z);
        this.setBoundingBox(AABB.ofSize(center, xSize, ySize, zSize));
    }

    @Override
    public int getWidth() {
        return 32;
    }

    @Override
    public int getHeight() {
        return 16;
    }

    @Override
    public void playPlacementSound() {
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);

        if (this.direction != null) {
            compound.putByte("facing", (byte) this.direction.get2DDataValue());
        }

        compound.putString("variant", this.getVariant());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);

        if (compound.contains("facing")) {
            this.setDirection(Direction.from2DDataValue(compound.getByte("facing")));
        }

        if (compound.contains("variant", CompoundTag.TAG_STRING)) {
            this.setVariant(compound.getString("variant"));
        }
    }

    @Override
    public void dropItem(@Nullable Entity entity) {
        if (this.level().getGameRules().getBoolean(GameRules.RULE_DOENTITYDROPS)) {
            if (entity instanceof Player player && player.isCreative()) {
                return;
            }

            String variant = getVariant();
            if (variant.equals("agender")) {
                this.spawnAtLocation(WeaversParadiseItems.FLAG_AGENDER.get());
            } else if (variant.equals("aroace")) {
                this.spawnAtLocation(WeaversParadiseItems.FLAG_AROACE.get());
            } else if (variant.equals("aromantic")) {
                this.spawnAtLocation(WeaversParadiseItems.FLAG_AROMANTIC.get());
            } else if (variant.equals("asexual")) {
                this.spawnAtLocation(WeaversParadiseItems.FLAG_ASEXUAL.get());
            } else if (variant.equals("bisexual")) {
                this.spawnAtLocation(WeaversParadiseItems.FLAG_BISEXUAL.get());
            } else if (variant.equals("demiboy")) {
                this.spawnAtLocation(WeaversParadiseItems.FLAG_DEMIBOY.get());
            } else if (variant.equals("demigender")) {
                this.spawnAtLocation(WeaversParadiseItems.FLAG_DEMIGENDER.get());
            } else if (variant.equals("demigirl")) {
                this.spawnAtLocation(WeaversParadiseItems.FLAG_DEMIGIRL.get());
            } else if (variant.equals("gay")) {
                this.spawnAtLocation(WeaversParadiseItems.FLAG_GAY.get());
            } else if (variant.equals("genderfluid")) {
                this.spawnAtLocation(WeaversParadiseItems.FLAG_GENDERFLUID.get());
            } else if (variant.equals("genderqueer")) {
                this.spawnAtLocation(WeaversParadiseItems.FLAG_GENDERQUEER.get());
            } else if (variant.equals("intersex")) {
                this.spawnAtLocation(WeaversParadiseItems.FLAG_INTERSEX.get());
            } else if (variant.equals("lesbian")) {
                this.spawnAtLocation(WeaversParadiseItems.FLAG_LESBIAN.get());
            } else if (variant.equals("nonbinary")) {
                this.spawnAtLocation(WeaversParadiseItems.FLAG_NONBINARY.get());
            } else if (variant.equals("pansexual")) {
                this.spawnAtLocation(WeaversParadiseItems.FLAG_PANSEXUAL.get());
            } else if (variant.equals("pride")) {
                this.spawnAtLocation(WeaversParadiseItems.FLAG_PRIDE.get());
            } else if (variant.equals("trans")) {
                this.spawnAtLocation(WeaversParadiseItems.FLAG_TRANS.get());
            } else {
                this.spawnAtLocation(WeaversParadiseItems.FLAG_BASIC.get());
            }
        }
    }

    @Override
    public void moveTo(double x, double y, double z, float yaw, float pitch) {
        this.setPos(x, y, z);
    }

    @Override
    public void lerpTo(double x, double y, double z, float yRot, float xRot, int lerpSteps, boolean teleport) {
        this.setPos(x, y, z);
    }

    @Override
    public Vec3 trackingPosition() {
        return Vec3.atLowerCornerOf(this.pos);
    }

    @Override
    public @NotNull Packet<ClientGamePacketListener> getAddEntityPacket() {
        return new ClientboundAddEntityPacket(
                this,
                this.direction != null ? this.direction.get3DDataValue() : 0,
                this.pos
        );
    }

    @Override
    public void recreateFromPacket(ClientboundAddEntityPacket packet) {
        super.recreateFromPacket(packet);

        this.setDirection(Direction.from3DDataValue(packet.getData()));
    }

    @Override
    public ItemStack getPickResult() {
        String variant = getVariant();
        if (variant.equals("agender")) {
            return new ItemStack(WeaversParadiseItems.FLAG_AGENDER.get());
        } else if (variant.equals("aroace")) {
            return new ItemStack(WeaversParadiseItems.FLAG_AROACE.get());
        } else if (variant.equals("aromantic")) {
            return new ItemStack(WeaversParadiseItems.FLAG_AROMANTIC.get());
        } else if (variant.equals("asexual")) {
            return new ItemStack(WeaversParadiseItems.FLAG_ASEXUAL.get());
        } else if (variant.equals("bisexual")) {
            return new ItemStack(WeaversParadiseItems.FLAG_BISEXUAL.get());
        } else if (variant.equals("demiboy")) {
            return new ItemStack(WeaversParadiseItems.FLAG_DEMIBOY.get());
        } else if (variant.equals("demigender")) {
            return new ItemStack(WeaversParadiseItems.FLAG_DEMIGENDER.get());
        } else if (variant.equals("demigirl")) {
            return new ItemStack(WeaversParadiseItems.FLAG_DEMIGIRL.get());
        } else if (variant.equals("gay")) {
            return new ItemStack(WeaversParadiseItems.FLAG_GAY.get());
        } else if (variant.equals("genderfluid")) {
            return new ItemStack(WeaversParadiseItems.FLAG_GENDERFLUID.get());
        } else if (variant.equals("genderqueer")) {
            return new ItemStack(WeaversParadiseItems.FLAG_GENDERQUEER.get());
        } else if (variant.equals("intersex")) {
            return new ItemStack(WeaversParadiseItems.FLAG_INTERSEX.get());
        } else if (variant.equals("lesbian")) {
            return new ItemStack(WeaversParadiseItems.FLAG_LESBIAN.get());
        } else if (variant.equals("nonbinary")) {
            return new ItemStack(WeaversParadiseItems.FLAG_NONBINARY.get());
        } else if (variant.equals("pansexual")) {
            return new ItemStack(WeaversParadiseItems.FLAG_PANSEXUAL.get());
        } else if (variant.equals("pride")) {
            return new ItemStack(WeaversParadiseItems.FLAG_PRIDE.get());
        } else if (variant.equals("trans")) {
            return new ItemStack(WeaversParadiseItems.FLAG_TRANS.get());
        } else {
            return new ItemStack(WeaversParadiseItems.FLAG_BASIC.get());
        }
    }

    private double offsetForSize(int size) {
        return (size % 2 == 0) ? 0.5D : 0.0D;
    }
}