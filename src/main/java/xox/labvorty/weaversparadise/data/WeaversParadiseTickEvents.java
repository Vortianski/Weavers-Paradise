package xox.labvorty.weaversparadise.data;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.neoforged.neoforge.network.PacketDistributor;

@EventBusSubscriber
public class WeaversParadiseTickEvents {
    @SubscribeEvent
    public static void playerTick(PlayerTickEvent.Post event) {
        Player player = event.getEntity();

        if (player.getPersistentData().getInt("weaversSculkPulse") > 0) {
            player.getPersistentData().putInt("weaversSculkPulse", player.getPersistentData().getInt("weaversSculkPulse") - 1);

            if (player instanceof ServerPlayer serverPlayer) {
                PacketDistributor.sendToPlayer(serverPlayer, new RendererDataSync(1, (int) player.getX(), (int) player.getY(), (int) player.getZ(), player.getPersistentData().getInt("weaversSculkPulse")));
            }
        }
    }
}
