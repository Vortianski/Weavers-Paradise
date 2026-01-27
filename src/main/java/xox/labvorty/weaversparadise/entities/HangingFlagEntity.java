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
import net.minecraft.server.level.ServerEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.decoration.HangingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import xox.labvorty.weaversparadise.init.WeaversParadiseEntityTypes;
import xox.labvorty.weaversparadise.init.WeaversParadiseItems;
import java.util.Optional;

public class HangingFlagEntity extends HangingEntity {
    private static final EntityDataAccessor<String> FLAG_TYPE = SynchedEntityData.defineId(HangingFlagEntity.class, EntityDataSerializers.STRING);

    private HangingFlagEntity(Level level, BlockPos pos) {
        super(WeaversParadiseEntityTypes.HANGING_FLAG_ENTITY.get(), level, pos);
    }

    public HangingFlagEntity(Level level, BlockPos pos, Direction direction, String variant) {
        this(level, pos);
        this.setVariant(variant);
        this.setDirection(direction);
    }

    public HangingFlagEntity(EntityType<? extends HangingFlagEntity> entityEntityType, Level level) {
        super(entityEntityType, level);
    }

    public static Optional<HangingFlagEntity> create(Level level, BlockPos pos, Direction direction, String variant) {
        HangingFlagEntity hangingFlagEntity = new HangingFlagEntity(level, pos);

        hangingFlagEntity.setDirection(direction);

        hangingFlagEntity.setVariant(variant);
        hangingFlagEntity.setDirection(direction);

        return Optional.of(hangingFlagEntity);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        builder.define(FLAG_TYPE, "");
    }

    @Override
    public void onSyncedDataUpdated(EntityDataAccessor<?> key) {
        if (FLAG_TYPE.equals(key)) {
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
    protected AABB calculateBoundingBox(BlockPos blockPos, Direction direction) {
        double wallOffset = 0.46875D;

        int widthBlocks = 2;
        int heightBlocks = 1;
        double depth = 0.0625D;

        Vec3 center = Vec3.atCenterOf(blockPos).relative(direction, -wallOffset);

        double xOffset = offsetForSize(widthBlocks);
        double yOffset = offsetForSize(heightBlocks);

        Direction ccw = direction.getCounterClockWise();
        center = center.relative(ccw, xOffset).relative(Direction.UP, yOffset);

        Direction.Axis axis = direction.getAxis();
        double xSize = (axis == Direction.Axis.X) ? depth : (double) widthBlocks;
        double zSize = (axis == Direction.Axis.Z) ? depth : (double) widthBlocks;
        double ySize = (double) heightBlocks;

        return AABB.ofSize(center, xSize, ySize, zSize);
    }

    @Override
    public void playPlacementSound() {

    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putByte("facing", (byte)this.direction.get2DDataValue());
        compound.putString("variant", this.getVariant());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        this.direction = Direction.from2DDataValue(compound.getByte("facing"));
        this.setDirection(this.direction);
        if (compound.contains("variant", CompoundTag.TAG_STRING)) {
            this.setVariant(compound.getString("variant"));
        }
    }

    @Override
    public void dropItem(@Nullable Entity entity) {
        if (this.level().getGameRules().getBoolean(GameRules.RULE_DOENTITYDROPS)) {
            //this.playSound(SoundEvents.PAINTING_BREAK, 1.0F, 1.0F);

            if (entity instanceof Player player && player.hasInfiniteMaterials()) {
                return;
            }

            String variant = getVariant();
            if (variant.equals("agender")) {
                this.spawnAtLocation(WeaversParadiseItems.FLAG_AGENDER);
            } else if (variant.equals("aroace")) {
                this.spawnAtLocation(WeaversParadiseItems.FLAG_AROACE);
            } else if (variant.equals("aromantic")) {
                this.spawnAtLocation(WeaversParadiseItems.FLAG_AROMANTIC);
            } else if (variant.equals("asexual")) {
                this.spawnAtLocation(WeaversParadiseItems.FLAG_ASEXUAL);
            } else if (variant.equals("bisexual")) {
                this.spawnAtLocation(WeaversParadiseItems.FLAG_BISEXUAL);
            } else if (variant.equals("demiboy")) {
                this.spawnAtLocation(WeaversParadiseItems.FLAG_DEMIBOY);
            } else if (variant.equals("demigender")) {
                this.spawnAtLocation(WeaversParadiseItems.FLAG_DEMIGENDER);
            } else if (variant.equals("demigirl")) {
                this.spawnAtLocation(WeaversParadiseItems.FLAG_DEMIGIRL);
            } else if (variant.equals("gay")) {
                this.spawnAtLocation(WeaversParadiseItems.FLAG_GAY);
            } else if (variant.equals("genderfluid")) {
                this.spawnAtLocation(WeaversParadiseItems.FLAG_GENDERFLUID);
            } else if (variant.equals("genderqueer")) {
                this.spawnAtLocation(WeaversParadiseItems.FLAG_GENDERQUEER);
            } else if (variant.equals("intersex")) {
                this.spawnAtLocation(WeaversParadiseItems.FLAG_INTERSEX);
            } else if (variant.equals("lesbian")) {
                this.spawnAtLocation(WeaversParadiseItems.FLAG_LESBIAN);
            } else if (variant.equals("nonbinary")) {
                this.spawnAtLocation(WeaversParadiseItems.FLAG_NONBINARY);
            } else if (variant.equals("pansexual")) {
                this.spawnAtLocation(WeaversParadiseItems.FLAG_PANSEXUAL);
            } else if (variant.equals("pride")) {
                this.spawnAtLocation(WeaversParadiseItems.FLAG_PRIDE);
            } else if (variant.equals("trans")) {
                this.spawnAtLocation(WeaversParadiseItems.FLAG_TRANS);
            } else {
                this.spawnAtLocation(WeaversParadiseItems.FLAG_BASIC);
            }
        }
    }

    @Override
    public void moveTo(double x, double y, double z, float yaw, float pitch) {
        this.setPos(x, y, z);
    }

    @Override
    public void lerpTo(double x, double y, double z, float yRot, float xRot, int steps) {
        this.setPos(x, y, z);
    }

    @Override
    public Vec3 trackingPosition() {
        return Vec3.atLowerCornerOf(this.pos);
    }

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket(ServerEntity entity) {
        return new ClientboundAddEntityPacket(this, this.direction.get3DDataValue(), this.getPos());
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
            return WeaversParadiseItems.FLAG_AGENDER.toStack();
        } else if (variant.equals("aroace")) {
            return WeaversParadiseItems.FLAG_AROACE.toStack();
        } else if (variant.equals("aromantic")) {
            return WeaversParadiseItems.FLAG_AROMANTIC.toStack();
        } else if (variant.equals("asexual")) {
            return WeaversParadiseItems.FLAG_ASEXUAL.toStack();
        } else if (variant.equals("bisexual")) {
            return WeaversParadiseItems.FLAG_BISEXUAL.toStack();
        } else if (variant.equals("demiboy")) {
            return WeaversParadiseItems.FLAG_DEMIBOY.toStack();
        } else if (variant.equals("demigender")) {
            return WeaversParadiseItems.FLAG_DEMIGENDER.toStack();
        } else if (variant.equals("demigirl")) {
            return WeaversParadiseItems.FLAG_DEMIGIRL.toStack();
        } else if (variant.equals("gay")) {
            return WeaversParadiseItems.FLAG_GAY.toStack();
        } else if (variant.equals("genderfluid")) {
            return WeaversParadiseItems.FLAG_GENDERFLUID.toStack();
        } else if (variant.equals("genderqueer")) {
            return WeaversParadiseItems.FLAG_GENDERQUEER.toStack();
        } else if (variant.equals("intersex")) {
            return WeaversParadiseItems.FLAG_INTERSEX.toStack();
        } else if (variant.equals("lesbian")) {
            return WeaversParadiseItems.FLAG_LESBIAN.toStack();
        } else if (variant.equals("nonbinary")) {
            return WeaversParadiseItems.FLAG_NONBINARY.toStack();
        } else if (variant.equals("pansexual")) {
            return WeaversParadiseItems.FLAG_PANSEXUAL.toStack();
        } else if (variant.equals("pride")) {
            return WeaversParadiseItems.FLAG_PRIDE.toStack();
        } else if (variant.equals("trans")) {
            return WeaversParadiseItems.FLAG_TRANS.toStack();
        } else {
            return WeaversParadiseItems.FLAG_BASIC.toStack();
        }
    }

    private double offsetForSize(int size) {
        return (size % 2 == 0) ? 0.5D : 0.0D;
    }
}
