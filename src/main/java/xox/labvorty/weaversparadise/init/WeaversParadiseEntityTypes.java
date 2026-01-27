package xox.labvorty.weaversparadise.init;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import xox.labvorty.weaversparadise.WeaversParadise;
import xox.labvorty.weaversparadise.entities.HangingFlagEntity;

public class WeaversParadiseEntityTypes {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, WeaversParadise.MODID);
    public static final DeferredHolder<EntityType<?>, EntityType<HangingFlagEntity>> HANGING_FLAG_ENTITY =
            ENTITY_TYPES.register(
                    "hanging_flag_entity",
                    () -> EntityType.Builder.<HangingFlagEntity>of(
                            HangingFlagEntity::new, MobCategory.MISC
                    ).sized(0.5F, 0.5F)
                            .clientTrackingRange(10)
                            .updateInterval(Integer.MAX_VALUE)
                            .build("flag")
            );
}
