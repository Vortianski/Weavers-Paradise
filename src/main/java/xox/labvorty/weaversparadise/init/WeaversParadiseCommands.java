package xox.labvorty.weaversparadise.init;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.ResourceLocationArgument;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import xox.labvorty.weaversparadise.configs.ClientConfig;
import xox.labvorty.weaversparadise.items.clothing.defined.DoubleSidedClothingItem;
import xox.labvorty.weaversparadise.items.clothing.defined.SingleSidedClothingItem;
import xox.labvorty.weaversparadise.utilities.WeaversUtilities;

public class WeaversParadiseCommands {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        registerRandomClothingCommand(dispatcher);
    }

    public static void registerRandomClothingCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(
                Commands.literal("randomclothing")
                        .requires(source -> source.hasPermission(4))
                        .then(Commands.argument("item", ResourceLocationArgument.id())
                                .suggests((context, builder) -> {
                                    BuiltInRegistries.ITEM.forEach(item -> {
                                        if (item instanceof SingleSidedClothingItem ||
                                                item instanceof DoubleSidedClothingItem) {

                                            ResourceLocation id = BuiltInRegistries.ITEM.getKey(item);
                                            builder.suggest(id.toString());
                                        }
                                    });

                                    return builder.buildFuture();
                                })
                                .executes(context -> {
                                    ResourceLocation id =
                                            ResourceLocationArgument.getId(context, "item");

                                    Item item = BuiltInRegistries.ITEM.get(id);

                                    if (item == null) {
                                        context.getSource().sendFailure(
                                                Component.literal("Unknown item: " + id)
                                        );
                                        return 0;
                                    }

                                    ItemStack stack;

                                    if (item instanceof SingleSidedClothingItem singleSidedClothingItem) {
                                        stack = WeaversUtilities.createRandomSingleSidedClothing(singleSidedClothingItem);
                                    } else if (item instanceof DoubleSidedClothingItem doubleSidedClothingItem) {
                                        stack = WeaversUtilities.createRandomDoubleSidedClothing(doubleSidedClothingItem);
                                    } else {
                                        context.getSource().sendFailure(
                                                Component.literal("That item is not a clothing item!")
                                        );
                                        return 0;
                                    }

                                    ServerPlayer player =
                                            context.getSource().getPlayerOrException();

                                    if (!player.getInventory().add(stack)) {
                                        player.drop(stack, false);
                                    }

                                    return 1;
                                })
                        )
        );

        dispatcher.register(
                com.mojang.brigadier.builder.LiteralArgumentBuilder.<CommandSourceStack>literal("wp_disable_version_warning")
                        .executes(ctx -> {
                            if (ClientConfig.VERSION_WARNING.get()) {
                                ClientConfig.VERSION_WARNING.set(false);
                                ClientConfig.VERSION_WARNING.save();

                                ctx.getSource().sendSuccess(
                                        () -> Component.translatable("weaversparadise.version_warning.disabled")
                                                .withStyle(ChatFormatting.GREEN),
                                        false
                                );
                            }

                            return 1;
                        })
        );
    }
}
