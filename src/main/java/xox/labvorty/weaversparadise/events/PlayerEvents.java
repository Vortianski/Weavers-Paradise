package xox.labvorty.weaversparadise.events;

import net.minecraft.ChatFormatting;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.ClickEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.HoverEvent;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModList;
import net.neoforged.fml.VersionChecker;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientPlayerNetworkEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import xox.labvorty.weaversparadise.configs.ClientConfig;
import xox.labvorty.weaversparadise.data.listeners.SculkPlayerListenerManager;

@EventBusSubscriber
public class PlayerEvents {
    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void onClientLogin(ClientPlayerNetworkEvent.LoggingIn event) {
        LocalPlayer localPlayer = event.getPlayer();
        ModContainer container = ModList.get().getModContainerById("weaversparadise").orElse(null);
        if (container == null) return;

        VersionChecker.CheckResult checkResult = VersionChecker.getResult(container.getModInfo());
        if (checkResult.status().equals(VersionChecker.Status.OUTDATED) || checkResult.status().equals(VersionChecker.Status.BETA_OUTDATED)) {
            if (ClientConfig.VERSION_WARNING.get()) {
                Component component = Component.literal("[X]").withStyle(style -> style.withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, Component.translatable("weaversparadise.version_warning.hint"))).withClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/wp_disable_version_warning")).withColor(ChatFormatting.DARK_PURPLE));

                localPlayer.sendSystemMessage(Component.translatable("weaversparadise.version_warning", checkResult.target().toString()).withStyle(ChatFormatting.YELLOW).append(Component.literal(" ")).append(component));
            }
        }
    }

    @SubscribeEvent
    public static void onLogin(PlayerEvent.PlayerLoggedInEvent event) {
        if (event.getEntity() instanceof ServerPlayer serverPlayer) {
            SculkPlayerListenerManager.attach(serverPlayer);
        }
    }

    @SubscribeEvent
    public static void onLogout(PlayerEvent.PlayerLoggedOutEvent event) {
        if (event.getEntity() instanceof ServerPlayer serverPlayer) {
            SculkPlayerListenerManager.detach(serverPlayer);
        }
    }

    @SubscribeEvent
    public static void onChangedDimension(PlayerEvent.PlayerChangedDimensionEvent event) {
        if (event.getEntity() instanceof ServerPlayer serverPlayer) {
            SculkPlayerListenerManager.detach(serverPlayer);
            SculkPlayerListenerManager.attach(serverPlayer);
        }
    }
}
