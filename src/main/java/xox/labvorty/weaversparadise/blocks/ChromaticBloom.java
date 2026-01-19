package xox.labvorty.weaversparadise.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FarmBlock;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import xox.labvorty.weaversparadise.init.WeaversParadiseMobEffects;

public class ChromaticBloom extends FlowerBlock {
    public ChromaticBloom() {
        super(WeaversParadiseMobEffects.CHROMATIC_SHIFT,
                260,
                BlockBehaviour.Properties.of()
                        .mapColor(MapColor.PLANT)
                        .sound(SoundType.GRASS)
                        .instabreak()
                        .noCollission()
                        .offsetType(BlockBehaviour.OffsetType.NONE)
                        .pushReaction(PushReaction.DESTROY));
    }

    protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
        return state.getBlock() == Blocks.DEEPSLATE || state.getBlock() == Blocks.COBBLED_DEEPSLATE;
    }
}
