package xox.labvorty.weaversparadise.mixinhelpers;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.shapes.VoxelShape;

public class FluidShapes {
    public static final VoxelShape[] VOXEL_SHAPES = new VoxelShape[16];

    static {
        for (int i = 0; i < 16; ++i) {
            VOXEL_SHAPES[i] = Block.box(0.0D, 0.0D, 0.0D, 16.0D, (double)i, 16.0D);
        }
    }
}
