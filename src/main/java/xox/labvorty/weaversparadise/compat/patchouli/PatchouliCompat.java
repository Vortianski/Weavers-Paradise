package xox.labvorty.weaversparadise.compat.patchouli;

import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientPlayerNetworkEvent;
import xox.labvorty.weaversparadise.configs.ClientConfig;

@EventBusSubscriber
public class PatchouliCompat {
    @SubscribeEvent
    public static void noPatchouli(ClientPlayerNetworkEvent.LoggingIn event) {
        LocalPlayer localPlayer = event.getPlayer();

        if (!ModList.get().isLoaded("patchouli") && ClientConfig.PATCHOULI_WARNING.get()) {
                localPlayer.sendSystemMessage(Component.translatable("weaversparadise.patchouli_warning"));
        }
    }
}
