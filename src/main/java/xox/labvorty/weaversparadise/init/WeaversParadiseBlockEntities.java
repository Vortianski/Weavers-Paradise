package xox.labvorty.weaversparadise.init;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntityType;
import xox.labvorty.weaversparadise.WeaversParadise;
import xox.labvorty.weaversparadise.blocks.entities.*;

public class WeaversParadiseBlockEntities {
    public static final BlockEntityType<?> SPINNING_JENNY_BE = register("spinning_jenny", WeaversParadiseBlocks.SPINNING_JENNY, SpinningJennyBlockEntity::new);
    public static final BlockEntityType<?> CLOTHCRAFTING_STATION_BE = register("clothcrafting_station", WeaversParadiseBlocks.CLOTHCRAFTING_STATION, ClothcraftingStationBlockEntity::new);
    public static final BlockEntityType<?> DYEMAKING_BE = register("dyemaking", WeaversParadiseBlocks.DYEMAKING_BLOCK, DyemakingBlockEntity::new);
    public static final BlockEntityType<?> DYEING_BARREL_BE = register("dyeing", WeaversParadiseBlocks.DYEING_BARREL_BLOCK, DyeingBarrelBlockEntity::new);
    public static final BlockEntityType<PlushieBlockEntity> PLUSHIE_BE = Registry.register(
            BuiltInRegistries.BLOCK_ENTITY_TYPE,
            ResourceLocation.fromNamespaceAndPath(WeaversParadise.MODID, "plushie"),
            BlockEntityType.Builder.of(PlushieBlockEntity::new, WeaversParadiseBlocks.PLUSHIE).build(null));
    public static final BlockEntityType<BlahajBlockEntity> BLAHAJ_BE = Registry.register(
            BuiltInRegistries.BLOCK_ENTITY_TYPE,
            ResourceLocation.fromNamespaceAndPath(WeaversParadise.MODID, "blahaj"),
            BlockEntityType.Builder.of(BlahajBlockEntity::new, WeaversParadiseBlocks.BLAHAJ).build(null));

    private static BlockEntityType<?> register(String name, net.minecraft.world.level.block.Block block, BlockEntityType.BlockEntitySupplier<?> supplier) {
        return Registry.register(
                BuiltInRegistries.BLOCK_ENTITY_TYPE,
                ResourceLocation.fromNamespaceAndPath(WeaversParadise.MODID, name),
                BlockEntityType.Builder.of(supplier, block).build(null)
        );
    }

    public static void register() {
    }
}
