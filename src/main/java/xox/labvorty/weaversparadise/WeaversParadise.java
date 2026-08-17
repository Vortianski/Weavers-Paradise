package xox.labvorty.weaversparadise;

import com.mojang.logging.LogUtils;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import org.slf4j.Logger;
import xox.labvorty.weaversparadise.configs.ClientConfig;
import xox.labvorty.weaversparadise.configs.CommonConfig;
import xox.labvorty.weaversparadise.init.*;

@Mod(WeaversParadise.MODID)
public class WeaversParadise {
    public static final String MODID = "weaversparadise";
    public static final Logger LOGGER = LogUtils.getLogger();

    public WeaversParadise(IEventBus modEventBus, ModContainer modContainer) {
        WeaversParadiseMobEffects.MOB_EFFECTS.register(modEventBus);
        WeaversParadiseItems.ITEMS.register(modEventBus);
        WeaversParadiseBlocks.BLOCKS.register(modEventBus);
        WeaversParadiseBlockEntities.BLOCK_ENTITIES.register(modEventBus);
        WeaversParadiseCreativeTabs.CREATIVE_MODE_TABS.register(modEventBus);
        WeaversParadiseInterfaces.MENUS.register(modEventBus);
        WeaversParadiseRecipes.RECIPE_SERIALIZERS.register(modEventBus);
        WeaversParadiseRecipes.RECIPE_TYPES.register(modEventBus);
        WeaversParadiseLootModifiers.LOOT_MODIFIERS.register(modEventBus);
        WeaversParadiseEntityTypes.ENTITY_TYPES.register(modEventBus);
        WeaversParadiseAttachmentTypes.ATTACHMENT_TYPES.register(modEventBus);
        WeaversParadiseSoundEvents.SOUND_EVENTS.register(modEventBus);
        WeaversParadiseParticles.PARTICLE_PROVIDERS.register(modEventBus);

        modContainer.registerConfig(
                ModConfig.Type.CLIENT,
                ClientConfig.SPEC,
                "weaversparadise-client.toml"
        );
        modContainer.registerConfig(
                ModConfig.Type.COMMON,
                CommonConfig.SPEC,
                "weaversparadise-common.toml"
        );
    }
}
