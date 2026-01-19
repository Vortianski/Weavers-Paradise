package xox.labvorty.weaversparadise.init;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import xox.labvorty.weaversparadise.blocks.*;

public class WeaversParadiseBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks("weaversparadise");
    public static final DeferredBlock<Block> COTTON_BUSH = BLOCKS.register("cotton_bush", () -> new CottonBush(BlockBehaviour.Properties
            .of().mapColor(MapColor.PLANT)
            .sound(SoundType.CROP)
            .instabreak()
            .noCollission()
            .pushReaction(PushReaction.DESTROY)
    ));
    public static final DeferredBlock<Block> WILD_COTTON_PLANT = BLOCKS.register("wild_cotton", WildCottonPlant::new);
    public static final DeferredBlock<Block> CHROMATIC_BLOOM = BLOCKS.register("chromatic_bloom", ChromaticBloom::new);
    public static final DeferredBlock<Block> SPINNING_JENNY = BLOCKS.register("spinning_jenny", SpinningJennyBlock::new);
    public static final DeferredBlock<Block> CLOTHCRAFTING_STATION = BLOCKS.register("clothcrafting_station", ClothcraftingStation::new);
    public static final DeferredBlock<Block> DYEMAKING_BLOCK = BLOCKS.register("dyemaking_block", DyemakingBlock::new);
    public static final DeferredBlock<Block> DYEING_BARREL_BLOCK = BLOCKS.register("dyeing_barrel", DyeingBarrelBlock::new);
    public static final DeferredBlock<Block> COTTON_SPOOL_BLOCK = BLOCKS.register("cotton_spool_block", CottonSpoolBlock::new);
    public static final DeferredBlock<Block> COTTON_CLOTH_BLOCK = BLOCKS.register("cotton_cloth_block", CottonClothBlock::new);
    public static final DeferredBlock<Block> SILK_SPOOL_BLOCK = BLOCKS.register("silk_spool_block", SilkSpoolBlock::new);
    public static final DeferredBlock<Block> SILK_CLOTH_BLOCK = BLOCKS.register("silk_cloth_block", SilkClothBlock::new);
    public static final DeferredBlock<Block> WOOL_SPOOL_BLOCK = BLOCKS.register("wool_spool_block", WoolSpoolBlock::new);
    public static final DeferredBlock<Block> WOOL_CLOTH_BLOCK = BLOCKS.register("wool_cloth_block", WoolClothBlock::new);
}
