package xox.labvorty.weaversparadise;

import com.mojang.logging.LogUtils;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import org.slf4j.Logger;
import xox.labvorty.weaversparadise.data.loot_modifiers.WPLoot;
import xox.labvorty.weaversparadise.events.WPServerEvents;
import xox.labvorty.weaversparadise.init.*;
import xox.labvorty.weaversparadise.init.WeaversParadiseDataComponents;
import xox.labvorty.weaversparadise.net.WPNetwork;

public class WeaversParadise implements ModInitializer {
    public static final String MODID = "weaversparadise";
    public static final Logger LOGGER = LogUtils.getLogger();

    @Override
    public void onInitialize() {
        WeaversParadiseMobEffects.register();
        WeaversParadiseBlocks.register();
        WeaversParadiseItems.register();
        WeaversParadiseBlockEntities.register();
        WeaversParadiseEntityTypes.register();
        WeaversParadiseSoundEvents.register();
        WeaversParadiseParticles.register();
        WeaversParadiseRecipes.register();
        WeaversParadiseInterfaces.register();
        WeaversParadiseDataComponents.touch();
        WeaversParadiseCreativeTabs.register();
        WeaversParadiseAttachmentTypes.touch();

        WPNetwork.registerPayloads();
        WPLoot.register();
        WPServerEvents.register();

        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) ->
                WeaversParadiseCommands.register(dispatcher));

        WeaversParadiseInitialization.reloadCommon();

        LOGGER.info("Weavers Paradise (Fabric) initialized");
    }
}
