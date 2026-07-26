package xox.labvorty.weaversparadise.init;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import xox.labvorty.vortylib.VortyLib;
import xox.labvorty.weaversparadise.data.network.*;

@EventBusSubscriber
public class WeaversParadiseNetworking {
    @SubscribeEvent
    public static void register(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            VortyLib.addNetworkMessage(
                    ClothcraftingNetworkMultiMessage.TYPE,
                    ClothcraftingNetworkMultiMessage.STREAM_CODEC,
                    ClothcraftingNetworkMultiMessage::handleData
            );
        });

        VortyLib.addNetworkMessage(
                DyeingNetworkMessage.TYPE,
                DyeingNetworkMessage.STREAM_CODEC,
                DyeingNetworkMessage::handleData
        );

        VortyLib.addNetworkMessage(
                DyemakingNetworkMessage.TYPE,
                DyemakingNetworkMessage.STREAM_CODEC,
                DyemakingNetworkMessage::handleData
        );

        VortyLib.addNetworkMessage(
                OpenUpperWearMessage.TYPE,
                OpenUpperWearMessage.STREAM_CODEC,
                OpenUpperWearMessage::handleData
        );

        VortyLib.addNetworkMessage(
                StringNetworkMessage.TYPE,
                StringNetworkMessage.STREAM_CODEC,
                StringNetworkMessage::handleData
        );
    }
}
