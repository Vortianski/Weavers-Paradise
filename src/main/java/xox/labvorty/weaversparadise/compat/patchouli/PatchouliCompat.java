package xox.labvorty.weaversparadise.compat.patchouli;

import net.minecraft.ChatFormatting;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.ClickEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientPlayerNetworkEvent;
import xox.labvorty.weaversparadise.configs.ClientConfig;

@EventBusSubscriber
public class PatchouliCompat {
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void noPatchouli(ClientPlayerNetworkEvent.LoggingIn event) {
        LocalPlayer localPlayer = event.getPlayer();

        if (!ModList.get().isLoaded("patchouli") && ClientConfig.PATCHOULI_WARNING.get()) {
            MutableComponent prefix = Component.literal("[Weaver's Paradise] ")
                    .withStyle(ChatFormatting.GOLD);

            MutableComponent message = Component.translatable("weaversparadise.patchouli_warning")
                    .withStyle(ChatFormatting.YELLOW);

            MutableComponent disableHint = Component.translatable("weaversparadise.patchouli_warning.hint")
                    .withStyle(style -> style
                            .withColor(ChatFormatting.GRAY)
                            .withItalic(true)
                            .withUnderlined(true)
                            .withClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/wp_disable_patchouli_warning"))
                    );

            localPlayer.sendSystemMessage(prefix.copy().append(message));
            localPlayer.sendSystemMessage(disableHint);
        }
    }
}