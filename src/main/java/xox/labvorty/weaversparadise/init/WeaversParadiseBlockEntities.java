package xox.labvorty.weaversparadise.init;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import xox.labvorty.weaversparadise.WeaversParadiseMod;
import xox.labvorty.weaversparadise.blocks.entities.*;

public class WeaversParadiseBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, WeaversParadiseMod.MOD_ID);
    public static final RegistryObject<BlockEntityType<SpinningJennyBlockEntity>> SPINNING_JENNY_BLOCK_ENTITY = BLOCK_ENTITIES.register(
            "spinning_jenny_block_entity",
            () -> BlockEntityType.Builder.of(
                    SpinningJennyBlockEntity::new,
                    WeaversParadiseBlocks.SPINNING_JENNY_BLOCK.get()
            ).build(null)
    );
    public static final RegistryObject<BlockEntityType<DyeingBarrelBlockEntity>> DYEING_BARREL_BLOCK_ENTITY = BLOCK_ENTITIES.register(
            "dyeing_barrel_block_entity",
            () -> BlockEntityType.Builder.of(
                    DyeingBarrelBlockEntity::new,
                    WeaversParadiseBlocks.DYEING_BARREL_BLOCK.get()
            ).build(null)
    );
    public static final RegistryObject<BlockEntityType<ClothcraftingStationBlockEntity>> CLOTHCRAFTING_STATION_BLOCK_ENTITY = BLOCK_ENTITIES.register(
            "clothcrafting_station_block_entity",
            () -> BlockEntityType.Builder.of(
                    ClothcraftingStationBlockEntity::new,
                    WeaversParadiseBlocks.CLOTHCRAFTING_STATION_BLOCK.get()
            ).build(null)
    );
    public static final RegistryObject<BlockEntityType<DyemakingBlockEntity>> DYEMAKING_BLOCK_ENTITY = BLOCK_ENTITIES.register(
            "dyemaking_block_entity",
            () -> BlockEntityType.Builder.of(
                    DyemakingBlockEntity::new,
                    WeaversParadiseBlocks.DYEMAKING_BLOCK.get()
            ).build(null)
    );
    public static final RegistryObject<BlockEntityType<PlushieBlockEntity>> PLUSHIE_BE = BLOCK_ENTITIES.register(
            "plushie_be",
            () -> BlockEntityType.Builder.of(
                    PlushieBlockEntity::new,
                    WeaversParadiseBlocks.PLUSHIE.get()
            ).build(null)
    );
    public static final RegistryObject<BlockEntityType<BlahajBlockEntity>> BLAHAJ_BE = BLOCK_ENTITIES.register(
            "blahaj_be",
            () -> BlockEntityType.Builder.of(
                    BlahajBlockEntity::new,
                    WeaversParadiseBlocks.BLAHAJ.get()
            ).build(null)
    );
}
