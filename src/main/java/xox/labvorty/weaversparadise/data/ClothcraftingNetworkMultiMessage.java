package xox.labvorty.weaversparadise.data;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.PacketFlow;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.common.extensions.ILevelExtension;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.IItemHandlerModifiable;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import xox.labvorty.weaversparadise.WeaversParadise;
import xox.labvorty.weaversparadise.blocks.entities.ClothcraftingStationBlockEntity;
import xox.labvorty.weaversparadise.blocks.entities.SpinningJennyBlockEntity;
import xox.labvorty.weaversparadise.gui.screen.ClothcraftingScreen;
import xox.labvorty.weaversparadise.gui.screen.StringScreen;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@EventBusSubscriber
public record ClothcraftingNetworkMultiMessage(int buttonID, int x, int y, int z, int gameTime, int gameScore, boolean isGameOn, List<ItemStack> items, String clothType) implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<ClothcraftingNetworkMultiMessage> TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(WeaversParadise.MODID, "clothcrafting_network_message"));
    public static StreamCodec<RegistryFriendlyByteBuf, List<ItemStack>> listCodec = ItemStack.STREAM_CODEC.apply(ByteBufCodecs.list());
    public static final StreamCodec<RegistryFriendlyByteBuf, ClothcraftingNetworkMultiMessage> STREAM_CODEC = StreamCodec.of((RegistryFriendlyByteBuf buffer, ClothcraftingNetworkMultiMessage message) -> {
        buffer.writeInt(message.buttonID);
        buffer.writeInt(message.x);
        buffer.writeInt(message.y);
        buffer.writeInt(message.z);
        buffer.writeInt(message.gameTime);
        buffer.writeInt(message.gameScore);
        buffer.writeBoolean(message.isGameOn);
        listCodec.encode(buffer, message.items);
        buffer.writeUtf(message.clothType);
    }, (RegistryFriendlyByteBuf buffer) -> new ClothcraftingNetworkMultiMessage(buffer.readInt(), buffer.readInt(), buffer.readInt(), buffer.readInt(), buffer.readInt(), buffer.readInt(), buffer.readBoolean(), listCodec.decode(buffer), buffer.readUtf()));
    @Override
    public CustomPacketPayload.Type<ClothcraftingNetworkMultiMessage> type() {
        return TYPE;
    }

    public static void handleData(final ClothcraftingNetworkMultiMessage message, final IPayloadContext context) {
        //message id:
        //0 - update data in the UI from BlockEntity
        //1 - send data from UI into the BlockEntity
        //2 - receive items from UI into the BlockEntity
        //3 - receive items into player inventory from BlockEntity

        //5 - remove 6 spools from main slot

        if (context.flow() == PacketFlow.SERVERBOUND) {
            context.enqueueWork(() -> {
                Player player = context.player();
                int buttonID = message.buttonID;
                int x = message.x;
                int y = message.y;
                int z = message.z;
                int gameTime = message.gameTime;
                int gameScore = message.gameScore;
                boolean isGameOn = message.isGameOn;
                String clothType = message.clothType;

                List<ItemStack> stackList = message.items;
                BlockPos blockPos = BlockPos.containing(x, y, z);
                BlockEntity blockEntity = player.level().getBlockEntity(blockPos);

                if (blockEntity instanceof ClothcraftingStationBlockEntity clothEntity) {
                    if (player instanceof ServerPlayer serverPlayer) {
                        List<ItemStack> filteredItems = clothEntity.getItemsList().stream()
                                .filter(item -> !item.isEmpty())
                                .collect(Collectors.toList());

                        if (buttonID == 0) {
                            PacketDistributor.sendToPlayer(
                                    serverPlayer,
                                    new ClothcraftingNetworkMultiMessage(
                                            0,
                                            x,
                                            y,
                                            z,
                                            clothEntity.getGameTime(),
                                            clothEntity.getGameScore(),
                                            clothEntity.getGameOn(),
                                            filteredItems,
                                            clothEntity.getClothType()
                                    )
                            );
                        }

                        if (buttonID == 1) {
                            clothEntity.setGameOn(isGameOn);
                            clothEntity.setGameScore(gameScore);
                            clothEntity.setGameTime(gameTime);
                            clothEntity.setItems(filteredItems);
                            clothEntity.setClothType(clothType);
                        }

                        if (buttonID == 2) {

                        }

                        if (buttonID == 3) {
                            for (ItemStack itemStack : filteredItems) {
                                player.addItem(itemStack);
                            }
                            filteredItems.clear();
                            clothEntity.setItems(filteredItems);
                        }

                        if (buttonID == 5) {
                            ItemStack stack = clothEntity.getItem(0);
                            stack.shrink(6);
                            clothEntity.setItem(0, stack);
                        }
                    }
                }
            }).exceptionally(e -> {
                context.connection().disconnect(Component.literal(e.getMessage()));
                return null;
            });
        }

        if (context.flow() == PacketFlow.CLIENTBOUND) {
            context.enqueueWork(() -> {
                Player player = context.player();
                int buttonID = message.buttonID;
                int x = message.x;
                int y = message.y;
                int z = message.z;
                int gameTime = message.gameTime;
                int gameScore = message.gameScore;
                List<ItemStack> stackList = message.items;
                boolean isGameOn = message.isGameOn;
                String clothType = message.clothType;

                if (buttonID == 0) {
                    ClothcraftingScreen.updateData(gameTime, gameScore, isGameOn, stackList, clothType);
                }

                if (buttonID == 1) {

                }

                if (buttonID == 2) {

                }

                if (buttonID == 3) {

                }
            });
        }
    }

    @SubscribeEvent
    public static void registerMessage(FMLCommonSetupEvent event) {
        WeaversParadise.addNetworkMessage(
                ClothcraftingNetworkMultiMessage.TYPE,
                ClothcraftingNetworkMultiMessage.STREAM_CODEC,
                ClothcraftingNetworkMultiMessage::handleData
        );
    }
}
