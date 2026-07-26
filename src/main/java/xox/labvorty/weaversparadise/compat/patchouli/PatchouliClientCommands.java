package xox.labvorty.weaversparadise.compat.patchouli;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterClientCommandsEvent;
import xox.labvorty.weaversparadise.configs.ClientConfig;

@EventBusSubscriber
public class PatchouliClientCommands {
    @SubscribeEvent
    public static void register(RegisterClientCommandsEvent event) {
        CommandDispatcher<CommandSourceStack> dispatcher = event.getDispatcher();

        dispatcher.register(
                com.mojang.brigadier.builder.LiteralArgumentBuilder.<CommandSourceStack>literal("wp_disable_patchouli_warning")
                        .executes(ctx -> {
                            if (ClientConfig.PATCHOULI_WARNING.get()) {
                                ClientConfig.PATCHOULI_WARNING.set(false);
                                ClientConfig.PATCHOULI_WARNING.save();

                                ctx.getSource().sendSuccess(
                                        () -> Component.translatable("weaversparadise.patchouli_warning.disabled")
                                                .withStyle(ChatFormatting.GREEN),
                                        false
                                );
                            }

                            return 1;
                        })
        );
    }
}