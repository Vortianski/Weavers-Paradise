package xox.labvorty.weaversparadise.init;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import xox.labvorty.weaversparadise.WeaversParadise;
import xox.labvorty.weaversparadise.blocks.*;

import java.util.function.Supplier;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

public class WeaversParadiseBlocks {
    private static <T extends Block> T registerBlock(String name, Supplier<T> sup) {
        T block = sup.get();
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(WeaversParadise.MODID, name), block);
        return block;
    }

    public static final Block COTTON_BUSH = registerBlock("cotton_bush", () -> new CottonBush(BlockBehaviour.Properties
            .of().mapColor(MapColor.PLANT)
            .sound(SoundType.CROP)
            .instabreak()
            .noCollission()
            .pushReaction(PushReaction.DESTROY)
    ));
    public static final Block WILD_COTTON_PLANT = registerBlock("wild_cotton", WildCottonPlant::new);
    public static final Block CHROMATIC_BLOOM = registerBlock("chromatic_bloom", ChromaticBloom::new);
    public static final Block SPINNING_JENNY = registerBlock("spinning_jenny", SpinningJennyBlock::new);
    public static final Block CLOTHCRAFTING_STATION = registerBlock("clothcrafting_station", ClothcraftingStation::new);
    public static final Block DYEMAKING_BLOCK = registerBlock("dyemaking_block", DyemakingBlock::new);
    public static final Block DYEING_BARREL_BLOCK = registerBlock("dyeing_barrel", DyeingBarrelBlock::new);
    public static final Block COTTON_SPOOL_BLOCK = registerBlock("cotton_spool_block", CottonSpoolBlock::new);
    public static final Block COTTON_CLOTH_BLOCK = registerBlock("cotton_cloth_block", CottonClothBlock::new);
    public static final Block SILK_SPOOL_BLOCK = registerBlock("silk_spool_block", SilkSpoolBlock::new);
    public static final Block SILK_CLOTH_BLOCK = registerBlock("silk_cloth_block", SilkClothBlock::new);
    public static final Block WOOL_SPOOL_BLOCK = registerBlock("wool_spool_block", WoolSpoolBlock::new);
    public static final Block WOOL_CLOTH_BLOCK = registerBlock("wool_cloth_block", WoolClothBlock::new);
    public static final Block WITCHROOT = registerBlock("witchroot", () -> new CeilingPlantBlock(
            BlockBehaviour.Properties.of()
                    .noCollission()
                    .instabreak()
                    .sound(SoundType.GRASS)
                    .offsetType(BlockBehaviour.OffsetType.XZ)
    ));
    public static final Block PLUSHIE = registerBlock("player_plushie", () -> new PlushieBlock(BlockBehaviour.Properties.of().sound(SoundType.WOOL).noOcclusion().strength(0.5f).noTerrainParticles()));
    public static final Block STARBLOOM = registerBlock("starbloom", () -> new StarbloomBlock(
            BlockBehaviour.Properties.of()
                    .noCollission()
                    .instabreak()
                    .sound(SoundType.GRASS)
                    .offsetType(BlockBehaviour.OffsetType.XZ)
    ));
    public static final Block BLAHAJ = registerBlock("blahaj", () -> new BlahajBlock(
            BlockBehaviour.Properties.of().sound(SoundType.WOOL).noOcclusion().strength(0.5f).noTerrainParticles()
    ));

    public static void register() {
    }
}
