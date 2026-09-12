package xox.labvorty.weaversparadise.init;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import xox.labvorty.weaversparadise.WeaversParadise;
import xox.labvorty.weaversparadise.entities.HangingFlagEntity;

public class WeaversParadiseEntityTypes {
    public static final EntityType<HangingFlagEntity> HANGING_FLAG_ENTITY = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            ResourceLocation.fromNamespaceAndPath(WeaversParadise.MODID, "hanging_flag_entity"),
            EntityType.Builder.<HangingFlagEntity>of(HangingFlagEntity::new, MobCategory.MISC)
                    .sized(0.5F, 0.5F)
                    .clientTrackingRange(10)
                    .updateInterval(Integer.MAX_VALUE)
                    .build("flag")
    );

    public static void register() {
    }
}
