package xox.labvorty.weaversparadise.compat.patchouli;

import net.minecraft.ChatFormatting;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.ClickEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ClientPlayerNetworkEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import xox.labvorty.weaversparadise.configs.ClientConfig;

@Mod.EventBusSubscriber(value = Dist.CLIENT)
public class PatchouliCompat {
    @SubscribeEvent
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