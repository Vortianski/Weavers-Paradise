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
import xox.labvorty.weaversparadise.blocks.entities.ClothcraftingStationBlockEntity;
import xox.labvorty.weaversparadise.gui.screen.ClothcraftingScreen;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class ClothcraftingNetworkMessage {
    private final int buttonID;
    private final int x, y, z;
    private final int gameTime;
    private final int gameScore;
    private final boolean isGameOn;
    private final List<ItemStack> items;
    private final String clothType;

    public ClothcraftingNetworkMessage(
            int buttonID,
            int x, int y, int z,
            int gameTime,
            int gameScore,
            boolean isGameOn,
            List<ItemStack> items,
            String clothType
    ) {
        this.buttonID = buttonID;
        this.x = x;
        this.y = y;
        this.z = z;
        this.gameTime = gameTime;
        this.gameScore = gameScore;
        this.isGameOn = isGameOn;
        this.items = items;
        this.clothType = clothType;
    }

    public ClothcraftingNetworkMessage(FriendlyByteBuf buf) {
        this.buttonID = buf.readInt();
        this.x = buf.readInt();
        this.y = buf.readInt();
        this.z = buf.readInt();
        this.gameTime = buf.readInt();
        this.gameScore = buf.readInt();
        this.isGameOn = buf.readBoolean();

        int size = buf.readInt();
        this.items = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            this.items.add(buf.readItem());
        }

        this.clothType = buf.readUtf();
    }

    public void buffer(FriendlyByteBuf buf) {
        buf.writeInt(buttonID);
        buf.writeInt(x);
        buf.writeInt(y);
        buf.writeInt(z);
        buf.writeInt(gameTime);
        buf.writeInt(gameScore);
        buf.writeBoolean(isGameOn);

        buf.writeInt(items.size());
        for (ItemStack stack : items) {
            buf.writeItem(stack);
        }

        buf.writeUtf(clothType == null ? "" : clothType);
    }

    public static void handler(ClothcraftingNetworkMessage message, Supplier<NetworkEvent.Context> ctxSupplier) {
        NetworkEvent.Context context = ctxSupplier.get();
        context.enqueueWork(() -> {
            if (context.getDirection().getReceptionSide().isServer()) {
                Player player = context.getSender();
                if (player == null) return;

                BlockPos pos = new BlockPos(message.x, message.y, message.z);
                BlockEntity be = player.level().getBlockEntity(pos);

                if (!(be instanceof ClothcraftingStationBlockEntity clothEntity)) return;

                List<ItemStack> filteredItems = clothEntity.getItemsList().stream()
                        .filter(stack -> !stack.isEmpty())
                        .collect(Collectors.toList());

                switch (message.buttonID) {
                    case 0 -> {
                        if (player instanceof ServerPlayer serverPlayer) {
                            WeaversParadiseMod.PACKET_HANDLER.send(
                                    PacketDistributor.PLAYER.with(() -> serverPlayer),
                                    new ClothcraftingNetworkMessage(
                                            0,
                                            message.x, message.y, message.z,
                                            clothEntity.getGameTime(),
                                            clothEntity.getGameScore(),
                                            clothEntity.getGameOn(),
                                            filteredItems,
                                            clothEntity.getClothType()
                                    )
                            );
                        }
                    }

                    case 1 -> {
                        clothEntity.setGameOn(message.isGameOn);
                        clothEntity.setGameScore(message.gameScore);
                        clothEntity.setGameTime(message.gameTime);
                        clothEntity.setItems(filteredItems);
                        clothEntity.setClothType(message.clothType);

                        if (player instanceof ServerPlayer serverPlayer) {
                            WeaversParadiseMod.PACKET_HANDLER.send(
                                    PacketDistributor.PLAYER.with(() -> serverPlayer),
                                    new ClothcraftingNetworkMessage(
                                            0,
                                            message.x, message.y, message.z,
                                            clothEntity.getGameTime(),
                                            clothEntity.getGameScore(),
                                            clothEntity.getGameOn(),
                                            filteredItems,
                                            clothEntity.getClothType()
                                    )
                            );
                        }
                    }

                    case 3 -> {
                        for (ItemStack stack : filteredItems) {
                            player.addItem(stack);
                        }
                        filteredItems.clear();
                        clothEntity.setItems(filteredItems);
                    }

                    case 5 -> {
                        ItemStack stack = clothEntity.getItem(0);
                        stack.shrink(6);
                        clothEntity.setItem(0, stack);

                        if (player instanceof ServerPlayer serverPlayer) {
                            WeaversParadiseMod.PACKET_HANDLER.send(
                                    PacketDistributor.PLAYER.with(() -> serverPlayer),
                                    new ClothcraftingNetworkMessage(
                                            0,
                                            message.x, message.y, message.z,
                                            clothEntity.getGameTime(),
                                            clothEntity.getGameScore(),
                                            clothEntity.getGameOn(),
                                            filteredItems,
                                            clothEntity.getClothType()
                                    )
                            );
                        }
                    }
                }

                clothEntity.setChanged();
                player.level().sendBlockUpdated(pos, clothEntity.getBlockState(), clothEntity.getBlockState(), 3);
            }

            if (context.getDirection().getReceptionSide().isClient()) {
                int buttonID = message.buttonID;
                int x = message.x;
                int y = message.y;
                int z = message.z;
                int gameTime = message.gameTime;
                int gameScore = message.gameScore;
                List<ItemStack> stackList = message.items;
                boolean isGameOn = message.isGameOn;
                String clothType = message.clothType;

                if (buttonID == 0 || buttonID == 4) {
                    ClothcraftingScreen.updateData(gameTime, gameScore, isGameOn, stackList, clothType);
                }
            }
        });

        context.setPacketHandled(true);
    }
}