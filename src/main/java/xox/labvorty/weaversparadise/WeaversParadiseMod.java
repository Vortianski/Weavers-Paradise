package xox.labvorty.weaversparadise;

import com.mojang.logging.LogUtils;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.util.thread.SidedThreadGroups;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;
import org.slf4j.Logger;
import xox.labvorty.weaversparadise.configs.ClientConfig;
import xox.labvorty.weaversparadise.configs.CommonConfig;
import xox.labvorty.weaversparadise.init.*;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

@Mod(WeaversParadiseMod.MOD_ID)
public class WeaversParadiseMod {
    public static final String MOD_ID = "weaversparadise";
    public static final Logger LOGGER = LogUtils.getLogger();

    public WeaversParadiseMod(FMLJavaModLoadingContext context) {
        WeaversParadiseMenus.MENUS.register(context.getModEventBus());
        WeaversParadiseEntityTypes.ENTITY_TYPES.register(context.getModEventBus());
        WeaversParadiseItems.ITEMS.register(context.getModEventBus());
        WeaversParadiseBlocks.BLOCKS.register(context.getModEventBus());
        WeaversParadiseBlockEntities.BLOCK_ENTITIES.register(context.getModEventBus());
        WeaversParadiseCreativeTabs.CREATIVE_MODE_TABS.register(context.getModEventBus());
        WeaversParadiseEnchantments.ENCHANTMENTS.register(context.getModEventBus());
        WeaversParadiseRecipes.RECIPES.register(context.getModEventBus());
        WeaversParadiseRecipes.RECIPE_TYPE.register(context.getModEventBus());
        WeaversParadiseMobEffects.MOB_EFFECTS.register(context.getModEventBus());
        WeaversParadiseLootModifiers.LOOT_MODIFIERS.register(context.getModEventBus());
        WeaversParadiseParticles.PARTICLE_PROVIDERS.register(context.getModEventBus());

        context.registerConfig(ModConfig.Type.CLIENT, ClientConfig.SPEC);
        context.registerConfig(ModConfig.Type.COMMON, CommonConfig.SPEC);
    }

    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel PACKET_HANDLER = NetworkRegistry.newSimpleChannel(ResourceLocation.fromNamespaceAndPath(MOD_ID, MOD_ID), () -> PROTOCOL_VERSION, PROTOCOL_VERSION::equals, PROTOCOL_VERSION::equals);
    private static int messageID = 0;

    public static <T> void addNetworkMessage(Class<T> messageType, BiConsumer<T, FriendlyByteBuf> encoder, Function<FriendlyByteBuf, T> decoder, BiConsumer<T, Supplier<NetworkEvent.Context>> messageConsumer) {
        PACKET_HANDLER.registerMessage(messageID, messageType, encoder, decoder, messageConsumer);
        messageID++;
    }

    private static final Collection<AbstractMap.SimpleEntry<Runnable, Integer>> workQueue = new ConcurrentLinkedQueue<>();

    public static void queueServerWork(int tick, Runnable action) {
        if (Thread.currentThread().getThreadGroup() == SidedThreadGroups.SERVER)
            workQueue.add(new AbstractMap.SimpleEntry<>(action, tick));
    }

    @SubscribeEvent
    public void tick(TickEvent.ServerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            List<AbstractMap.SimpleEntry<Runnable, Integer>> actions = new ArrayList<>();
            workQueue.forEach(work -> {
                work.setValue(work.getValue() - 1);
                if (work.getValue() == 0)
                    actions.add(work);
            });
            actions.forEach(e -> e.getKey().run());
            workQueue.removeAll(actions);
        }
    }
}