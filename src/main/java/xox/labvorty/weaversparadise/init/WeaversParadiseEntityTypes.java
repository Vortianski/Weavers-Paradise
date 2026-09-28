package xox.labvorty.weaversparadise.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import xox.labvorty.weaversparadise.WeaversParadiseMod;
import xox.labvorty.weaversparadise.entities.HangingFlagEntity;

public class WeaversParadiseEntityTypes {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(Registries.ENTITY_TYPE, WeaversParadiseMod.MOD_ID);
    public static final RegistryObject<EntityType<HangingFlagEntity>> HANGING_FLAG_ENTITY = ENTITY_TYPES.register(
            "hanging_flag_entity",
            () -> EntityType.Builder.<HangingFlagEntity>of(
                            HangingFlagEntity::new, MobCategory.MISC
                    ).sized(0.5F, 0.5F)
                    .clientTrackingRange(10)
                    .updateInterval(Integer.MAX_VALUE)
                    .build("hanging_flag_entity")
    );
}
