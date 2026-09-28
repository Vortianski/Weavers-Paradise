package xox.labvorty.weaversparadise.init;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import xox.labvorty.weaversparadise.WeaversParadiseMod;
import xox.labvorty.weaversparadise.data.network.*;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class WeaversParadiseNetworking {
    @SubscribeEvent
    public static void register(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            WeaversParadiseMod.addNetworkMessage(
                    StringNetworkMessage.class,
                    StringNetworkMessage::buffer,
                    StringNetworkMessage::new,
                    StringNetworkMessage::handler
            );

            WeaversParadiseMod.addNetworkMessage(
                    DyeingNetworkMessage.class,
                    DyeingNetworkMessage::buffer,
                    DyeingNetworkMessage::new,
                    DyeingNetworkMessage::handler
            );

            WeaversParadiseMod.addNetworkMessage(
                    ClothcraftingNetworkMessage.class,
                    ClothcraftingNetworkMessage::buffer,
                    ClothcraftingNetworkMessage::new,
                    ClothcraftingNetworkMessage::handler
            );

            WeaversParadiseMod.addNetworkMessage(
                    DyemakingNetworkMessage.class,
                    DyemakingNetworkMessage::buffer,
                    DyemakingNetworkMessage::new,
                    DyemakingNetworkMessage::handler
            );

            WeaversParadiseMod.addNetworkMessage(
                    RendererSyncMessage.class,
                    RendererSyncMessage::buffer,
                    RendererSyncMessage::new,
                    RendererSyncMessage::handler
            );

            WeaversParadiseMod.addNetworkMessage(
                    OpenUpperWearMessage.class,
                    OpenUpperWearMessage::buffer,
                    OpenUpperWearMessage::new,
                    OpenUpperWearMessage::handler
            );

            WeaversParadiseMod.addNetworkMessage(
                    ChromaticShiftSyncPacket.class,
                    ChromaticShiftSyncPacket::encode,
                    ChromaticShiftSyncPacket::new,
                    ChromaticShiftSyncPacket::handle
            );

            WeaversParadiseMod.addNetworkMessage(
                    SculkPulseSyncPacket.class,
                    SculkPulseSyncPacket::encode,
                    SculkPulseSyncPacket::new,
                    SculkPulseSyncPacket::handle
            );
        });
    }
}
