package xox.labvorty.weaversparadise;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.handling.IPayloadHandler;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.ModContainer;
import xox.labvorty.weaversparadise.init.*;

import java.util.HashMap;
import java.util.Map;

@Mod(WeaversParadise.MODID)
public class WeaversParadise {
    public static final String MODID = "weaversparadise";
    public static final Logger LOGGER = LogUtils.getLogger();

    public WeaversParadise(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::registerNetworking);
        WeaversParadiseMobEffects.MOB_EFFECTS.register(modEventBus);
        WeaversParadiseItems.ITEMS.register(modEventBus);
        WeaversParadiseBlocks.BLOCKS.register(modEventBus);
        WeaversParadiseBlockEntities.BLOCK_ENTITIES.register(modEventBus);
        WeaversParadiseModAttributes.ATTRIBUTES.register(modEventBus);
        WeaversParadiseCreativeTabs.CREATIVE_MODE_TABS.register(modEventBus);
        WeaversParadiseInterfaces.MENUS.register(modEventBus);
        WeaversParadiseRecipes.RECIPE_SERIALIZERS.register(modEventBus);
        WeaversParadiseLootModifiers.LOOT_MODIFIERS.register(modEventBus);
        WeaversParadiseEntityTypes.ENTITY_TYPES.register(modEventBus);
    }

    private static boolean networkingRegistered = false;
    private static final Map<CustomPacketPayload.Type<?>, NetworkMessage<?>> MESSAGES = new HashMap<>();

    private record NetworkMessage<T extends CustomPacketPayload>(StreamCodec<? extends FriendlyByteBuf, T> reader, IPayloadHandler<T> handler) {
    }

    public static <T extends CustomPacketPayload> void addNetworkMessage(CustomPacketPayload.Type<T> id, StreamCodec<? extends FriendlyByteBuf, T> reader, IPayloadHandler<T> handler) {
        if (networkingRegistered)
            throw new IllegalStateException("Cannot register new network messages after networking has been registered!");
        MESSAGES.put(id, new NetworkMessage<>(reader, handler));
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    private void registerNetworking(final RegisterPayloadHandlersEvent event) {
        final PayloadRegistrar registrar = event.registrar(MODID);
        MESSAGES.forEach((id, networkMessage) -> registrar.playBidirectional(id, ((NetworkMessage) networkMessage).reader(), ((NetworkMessage) networkMessage).handler()));
        networkingRegistered = true;
    }
}
