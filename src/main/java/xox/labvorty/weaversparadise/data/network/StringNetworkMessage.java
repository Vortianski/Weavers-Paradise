package xox.labvorty.weaversparadise.data.network;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.PacketDistributor;
import xox.labvorty.weaversparadise.WeaversParadiseMod;
import xox.labvorty.weaversparadise.blocks.entities.SpinningJennyBlockEntity;
import xox.labvorty.weaversparadise.data.recipe.SpinningJennyRecipe;
import xox.labvorty.weaversparadise.data.recipe.SpinningJennyRecipeInput;
import xox.labvorty.weaversparadise.gui.screen.StringScreen;
import xox.labvorty.weaversparadise.init.WeaversParadiseRecipes;

import java.util.function.Supplier;

public class StringNetworkMessage {
    private final int buttonID;
    private final int x;
    private final int y;
    private final int z;
    private final int data;
    private final int maxProgress;

    public StringNetworkMessage(int buttonID, int x, int y, int z, int data, int maxProgress) {
        this.buttonID = buttonID;
        this.x = x;
        this.y = y;
        this.z = z;
        this.data = data;
        this.maxProgress = maxProgress;
    }

    public StringNetworkMessage(FriendlyByteBuf buf) {
        this.buttonID = buf.readInt();
        this.x = buf.readInt();
        this.y = buf.readInt();
        this.z = buf.readInt();
        this.data = buf.readInt();
        this.maxProgress = buf.readInt();
    }

    public static void buffer(StringNetworkMessage message, FriendlyByteBuf buf) {
        buf.writeInt(message.buttonID);
        buf.writeInt(message.x);
        buf.writeInt(message.y);
        buf.writeInt(message.z);
        buf.writeInt(message.data);
        buf.writeInt(message.maxProgress);
    }

    public static void handler(StringNetworkMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> {
            if (context.getDirection().getReceptionSide().isServer()) {
                Player player = context.getSender();
                if (player == null) {
                    return;
                }

                int x = message.x;
                int y = message.y;
                int z = message.z;

                BlockEntity blockEntity = player.level().getBlockEntity(new BlockPos(x, y, z));
                if (blockEntity instanceof SpinningJennyBlockEntity spinningJennyBE) {
                    if (player instanceof ServerPlayer serverPlayer) {
                        ItemStack mainStack = spinningJennyBE.getItem(0);
                        int maxProgress = player.level().getRecipeManager()
                                .getAllRecipesFor(WeaversParadiseRecipes.SPINNING_JENNY_TYPE.get())
                                .stream()
                                .filter(r -> r.matches(
                                        new SpinningJennyRecipeInput(mainStack),
                                        player.level()))
                                .mapToInt(SpinningJennyRecipe::getCraftTime)
                                .findFirst()
                                .orElse(100);

                        WeaversParadiseMod.PACKET_HANDLER.send(
                                PacketDistributor.PLAYER.with(context::getSender),
                                new StringNetworkMessage(
                                        0, x, y, z,
                                        spinningJennyBE.getWorkingState(),
                                        maxProgress
                                )
                        );
                    }
                }
            }

            if (context.getDirection().getReceptionSide().isClient()) {
                StringScreen.updateProgress(message.data, message.maxProgress);
            }
        });
        context.setPacketHandled(true);
    }
}