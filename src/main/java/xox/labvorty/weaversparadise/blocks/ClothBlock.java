
package xox.labvorty.weaversparadise.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xox.labvorty.weaversparadise.blocks.entities.ClothBlockEntity;

import java.util.List;

public class ClothBlock extends Block implements EntityBlock {

    public static final DirectionProperty FACING =
            HorizontalDirectionalBlock.FACING;

    public static final EnumProperty<AttachFace> FACE =
            FaceAttachedHorizontalDirectionalBlock.FACE;

    public ClothBlock() {
        super(Properties.of()
                .sound(SoundType.WOOL)
                .strength(1.0F, 10.0F)
                .noOcclusion()
                .isRedstoneConductor((state, getter, pos) -> false)
        );

        this.registerDefaultState(
                this.stateDefinition.any()
                        .setValue(FACING, Direction.NORTH)
                        .setValue(FACE, AttachFace.WALL)
        );
    }

    @Override
    public boolean propagatesSkylightDown(
            @NotNull BlockState state,
            @NotNull BlockGetter getter,
            @NotNull BlockPos pos
    ) {
        return true;
    }

    @Override
    public int getLightBlock(
            @NotNull BlockState state,
            @NotNull BlockGetter getter,
            @NotNull BlockPos pos
    ) {
        return 0;
    }

    @Override
    public @NotNull VoxelShape getVisualShape(
            @NotNull BlockState state,
            @NotNull BlockGetter getter,
            @NotNull BlockPos pos,
            @NotNull CollisionContext context
    ) {
        return Shapes.empty();
    }

    @Override
    protected void createBlockStateDefinition(
            StateDefinition.@NotNull Builder<Block, BlockState> builder
    ) {
        super.createBlockStateDefinition(builder);
        builder.add(FACING, FACE);
    }

    @Override
    public @Nullable BlockState getStateForPlacement(
            @NotNull BlockPlaceContext context
    ) {
        BlockState state = super.getStateForPlacement(context);

        if (state == null) {
            return null;
        }

        return state
                .setValue(
                        FACE,
                        faceForDirection(context.getNearestLookingDirection())
                )
                .setValue(
                        FACING,
                        context.getHorizontalDirection().getOpposite()
                );
    }

    @Override
    public void setPlacedBy(
            @NotNull Level level,
            @NotNull BlockPos pos,
            @NotNull BlockState state,
            @Nullable LivingEntity placer,
            @NotNull ItemStack stack
    ) {
        super.setPlacedBy(level, pos, state, placer, stack);

        if (!level.isClientSide
                && level.getBlockEntity(pos) instanceof ClothBlockEntity cloth) {

            int quality = stack.hasTag()
                    ? stack.getTag().getInt("quality")
                    : 0;

            cloth.setQuality(quality);
        }
    }

    @Override
    public @NotNull List<ItemStack> getDrops(
            @NotNull BlockState state,
            LootParams.@NotNull Builder params
    ) {
        List<ItemStack> drops = super.getDrops(state, params);

        BlockEntity blockEntity = params.getOptionalParameter(
                LootContextParams.BLOCK_ENTITY
        );

        if (blockEntity instanceof ClothBlockEntity cloth
                && cloth.getQuality() > 0) {

            int quality = cloth.getQuality();

            for (ItemStack drop : drops) {
                if (drop.is(this.asItem())) {
                    drop.getOrCreateTag().putInt("quality", quality);
                }
            }
        }

        return drops;
    }

    @Override
    public @NotNull BlockState rotate(
            @NotNull BlockState state,
            @NotNull Rotation rotation
    ) {
        return state.setValue(
                FACING,
                rotation.rotate(state.getValue(FACING))
        );
    }

    @Override
    public @NotNull BlockState mirror(
            @NotNull BlockState state,
            @NotNull Mirror mirror
    ) {
        return state.rotate(
                mirror.getRotation(state.getValue(FACING))
        );
    }

    private AttachFace faceForDirection(Direction direction) {
        if (direction.getAxis() == Direction.Axis.Y) {
            return direction == Direction.UP
                    ? AttachFace.CEILING
                    : AttachFace.FLOOR;
        }

        return AttachFace.WALL;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(
            @NotNull BlockPos pos,
            @NotNull BlockState state
    ) {
        return new ClothBlockEntity(pos, state);
    }
}
