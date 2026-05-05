package xox.labvorty.weaversparadise.events;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.PacketDistributor;
import xox.labvorty.weaversparadise.WeaversParadiseMod;
import xox.labvorty.weaversparadise.data.network.RendererSyncMessage;

@Mod.EventBusSubscriber
public class WeaversParadiseTickEvents {
    @SubscribeEvent
    public static void playerTick(TickEvent.PlayerTickEvent event) {
        Player player = event.player;

        if (player.getPersistentData().getInt("weaversSculkPulse") > 0) {
            player.getPersistentData().putInt("weaversSculkPulse", player.getPersistentData().getInt("weaversSculkPulse") - 1);

            if (player instanceof ServerPlayer serverPlayer) {
                WeaversParadiseMod.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> serverPlayer), new RendererSyncMessage(player.getPersistentData().getInt("weaversSculkPulse")));
            }
        }
    }
}
