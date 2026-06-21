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
import xox.labvorty.weaversparadise.data.recipe.ClothcraftingRecipeInput;
import xox.labvorty.weaversparadise.gui.screen.ClothcraftingScreen;
import xox.labvorty.weaversparadise.init.WeaversParadiseRecipes;

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
    private final ItemStack clothType;

    public ClothcraftingNetworkMessage(
            int buttonID,
            int x, int y, int z,
            int gameTime,
            int gameScore,
            boolean isGameOn,
            List<ItemStack> items,
            ItemStack clothType
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
        this.clothType = buf.readItem();
    }

    public static void buffer(ClothcraftingNetworkMessage message, FriendlyByteBuf buf) {
        buf.writeInt(message.buttonID);
        buf.writeInt(message.x);
        buf.writeInt(message.y);
        buf.writeInt(message.z);
        buf.writeInt(message.gameTime);
        buf.writeInt(message.gameScore);
        buf.writeBoolean(message.isGameOn);
        buf.writeInt(message.items.size());
        for (ItemStack stack : message.items) {
            buf.writeItem(stack);
        }
        buf.writeItem(message.clothType.isEmpty() ? ItemStack.EMPTY : message.clothType);
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
                        // Score update only — server owns game state
                        clothEntity.setGameScore(message.gameScore);
                    }

                    case 3 -> {
                        for (ItemStack stack : filteredItems) {
                            player.addItem(stack);
                        }
                        filteredItems.clear();
                        clothEntity.setItems(filteredItems);
                    }

                    case 4 -> {
                        // Start game — look up recipe from spool in slot 0
                        ItemStack stack = clothEntity.getItem(0);
                        ClothcraftingRecipeInput input = new ClothcraftingRecipeInput(stack.copy());

                        int cost = player.level().getRecipeManager()
                                .getAllRecipesFor(WeaversParadiseRecipes.CLOTHCRAFTING_TYPE.get())
                                .stream()
                                .filter(r -> r.matches(input, player.level()))
                                .mapToInt(r -> r.getSpoolCost())
                                .findFirst()
                                .orElse(6);

                        int duration = player.level().getRecipeManager()
                                .getAllRecipesFor(WeaversParadiseRecipes.CLOTHCRAFTING_TYPE.get())
                                .stream()
                                .filter(r -> r.matches(input, player.level()))
                                .mapToInt(r -> r.getGameDuration())
                                .findFirst()
                                .orElse(900);

                        // Store spool as clothType so tick() can re-derive the recipe
                        clothEntity.setClothType(stack.copy());

                        stack.shrink(cost);
                        clothEntity.setItem(0, stack);

                        clothEntity.setGameOn(true);
                        clothEntity.setGameScore(0);
                        clothEntity.setGameTime(duration);
                        clothEntity.setChanged();
                    }
                }

                clothEntity.setChanged();
                player.level().sendBlockUpdated(pos, clothEntity.getBlockState(), clothEntity.getBlockState(), 3);
            }

            if (context.getDirection().getReceptionSide().isClient()) {
                if (message.buttonID == 0) {
                    ClothcraftingScreen.updateData(
                            message.gameTime,
                            message.gameScore,
                            message.isGameOn,
                            message.items,
                            message.clothType
                    );
                }
            }
        });
        context.setPacketHandled(true);
    }
}