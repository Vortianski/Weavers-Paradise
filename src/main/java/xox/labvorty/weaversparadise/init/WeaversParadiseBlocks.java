package xox.labvorty.weaversparadise.init;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import xox.labvorty.weaversparadise.WeaversParadiseMod;
import xox.labvorty.weaversparadise.blocks.*;

public class WeaversParadiseBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, WeaversParadiseMod.MOD_ID);
    public static final RegistryObject<SpinningJennyBlock> SPINNING_JENNY_BLOCK = BLOCKS.register(
            "spinning_jenny_block",
            SpinningJennyBlock::new
    );
    public static final RegistryObject<DyeingBarrelBlock> DYEING_BARREL_BLOCK = BLOCKS.register(
            "dyeing_barrel_block",
            DyeingBarrelBlock::new
    );
    public static final RegistryObject<ClothcraftingStationBlock> CLOTHCRAFTING_STATION_BLOCK = BLOCKS.register(
            "clothcrafting_station_block",
            ClothcraftingStationBlock::new
    );
    public static final RegistryObject<DyemakingBlock> DYEMAKING_BLOCK = BLOCKS.register(
            "dyemaking_block",
            DyemakingBlock::new
    );
    public static final RegistryObject<Block> COTTON_BUSH = BLOCKS.register("cotton_bush", () -> new CottonBush(BlockBehaviour.Properties
            .of().mapColor(MapColor.PLANT)
            .sound(SoundType.CROP)
            .instabreak()
            .noCollission()
            .pushReaction(PushReaction.DESTROY)
    ));
    public static final RegistryObject<Block> WILD_COTTON_PLANT = BLOCKS.register("wild_cotton", WildCottonPlant::new);
    public static final RegistryObject<Block> CHROMATIC_BLOOM = BLOCKS.register("chromatic_bloom", ChromaticBloom::new);
    public static final RegistryObject<Block> COTTON_SPOOL_BLOCK = BLOCKS.register("cotton_spool_block", CottonSpoolBlock::new);
    public static final RegistryObject<Block> COTTON_CLOTH_BLOCK = BLOCKS.register("cotton_cloth_block", CottonClothBlock::new);
    public static final RegistryObject<Block> SILK_SPOOL_BLOCK = BLOCKS.register("silk_spool_block", SilkSpoolBlock::new);
    public static final RegistryObject<Block> SILK_CLOTH_BLOCK = BLOCKS.register("silk_cloth_block", SilkClothBlock::new);
    public static final RegistryObject<Block> WOOL_SPOOL_BLOCK = BLOCKS.register("wool_spool_block", WoolSpoolBlock::new);
    public static final RegistryObject<Block> WOOL_CLOTH_BLOCK = BLOCKS.register("wool_cloth_block", WoolClothBlock::new);
}
