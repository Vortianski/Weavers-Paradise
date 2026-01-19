package xox.labvorty.weaversparadise.init;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.Block;
import xox.labvorty.weaversparadise.blocks.entities.ClothcraftingStationBlockEntity;
import xox.labvorty.weaversparadise.blocks.entities.DyeingBarrelBlockEntity;
import xox.labvorty.weaversparadise.blocks.entities.DyemakingBlockEntity;
import xox.labvorty.weaversparadise.blocks.entities.SpinningJennyBlockEntity;

@EventBusSubscriber
public class WeaversParadiseBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, "weaversparadise");
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<?>> SPINNING_JENNY_BE = register("spinning_jenny", WeaversParadiseBlocks.SPINNING_JENNY, SpinningJennyBlockEntity::new);
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<?>> CLOTHCRAFTING_STATION_BE = register("clothcrafting_station", WeaversParadiseBlocks.CLOTHCRAFTING_STATION, ClothcraftingStationBlockEntity::new);
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<?>> DYEMAKING_BE = register("dyemaking", WeaversParadiseBlocks.DYEMAKING_BLOCK, DyemakingBlockEntity::new);
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<?>> DYEING_BARREL_BE = register("dyeing", WeaversParadiseBlocks.DYEING_BARREL_BLOCK, DyeingBarrelBlockEntity::new);

    private static DeferredHolder<BlockEntityType<?>, BlockEntityType<?>> register(String registryname, DeferredHolder<Block, Block> block, BlockEntityType.BlockEntitySupplier<?> supplier) {
        return BLOCK_ENTITIES.register(registryname, () -> BlockEntityType.Builder.of(supplier, block.get()).build(null));
    }

    @SubscribeEvent
    public static void registerCapabilities(RegisterCapabilitiesEvent event) {
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, SPINNING_JENNY_BE.get(), (blockEntity, side) -> ((SpinningJennyBlockEntity) blockEntity).getItemHandler());
    }

}
