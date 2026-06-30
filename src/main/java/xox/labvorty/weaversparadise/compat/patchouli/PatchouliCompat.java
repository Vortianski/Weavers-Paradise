package xox.labvorty.weaversparadise.compat.patchouli;

import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.minecraftforge.client.event.ClientPlayerNetworkEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import xox.labvorty.weaversparadise.configs.ClientConfig;

@Mod.EventBusSubscriber
public class PatchouliCompat {
    @SubscribeEvent
    public static void noPatchouli(ClientPlayerNetworkEvent.LoggingIn event) {
        LocalPlayer localPlayer = event.getPlayer();

        if (!ModList.get().isLoaded("patchouli") && ClientConfig.PATCHOULI_WARNING.get()) {
                localPlayer.sendSystemMessage(Component.translatable("weaversparadise.patchouli_warning"));
        }
    }
}
