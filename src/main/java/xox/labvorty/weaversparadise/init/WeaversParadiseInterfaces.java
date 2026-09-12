package xox.labvorty.weaversparadise.init;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import xox.labvorty.weaversparadise.WeaversParadise;
import xox.labvorty.weaversparadise.gui.menu.ClothcraftingMenu;
import xox.labvorty.weaversparadise.gui.menu.DyeingMenu;
import xox.labvorty.weaversparadise.gui.menu.DyemakingMenu;
import xox.labvorty.weaversparadise.gui.menu.StringMenu;

public class WeaversParadiseInterfaces {
    public static final StreamCodec<RegistryFriendlyByteBuf, BlockPos> BLOCK_POS_CODEC =
            StreamCodec.of(
                    (buf, pos) -> buf.writeBlockPos(pos),
                    buf -> buf.readBlockPos()
            );

    public static final MenuType<StringMenu> STRING_MENU = Registry.register(
            BuiltInRegistries.MENU,
            ResourceLocation.fromNamespaceAndPath(WeaversParadise.MODID, "string_menu"),
            new ExtendedScreenHandlerType<>(StringMenu::new, BLOCK_POS_CODEC));
    public static final MenuType<ClothcraftingMenu> CLOTHCRAFTING_MENU = Registry.register(
            BuiltInRegistries.MENU,
            ResourceLocation.fromNamespaceAndPath(WeaversParadise.MODID, "clothcrafting_menu"),
            new ExtendedScreenHandlerType<>(ClothcraftingMenu::new, BLOCK_POS_CODEC));
    public static final MenuType<DyemakingMenu> DYEMAKING_MENU = Registry.register(
            BuiltInRegistries.MENU,
            ResourceLocation.fromNamespaceAndPath(WeaversParadise.MODID, "dyemaking_menu"),
            new ExtendedScreenHandlerType<>(DyemakingMenu::new, BLOCK_POS_CODEC));
    public static final MenuType<DyeingMenu> DYEING_MENU = Registry.register(
            BuiltInRegistries.MENU,
            ResourceLocation.fromNamespaceAndPath(WeaversParadise.MODID, "dyeing_menu"),
            new ExtendedScreenHandlerType<>(DyeingMenu::new, BLOCK_POS_CODEC));

    /** Открывает меню, привязанное к BlockPos (замена player.openMenu(SimpleMenuProvider) из NeoForge-версии). */
    public static <T extends AbstractContainerMenu> void open(
            ServerPlayer player, MenuType<T> type, BlockPos pos, Component title,
            TriMenuFactory<T> factory
    ) {
        player.openMenu(
                new net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory<BlockPos>() {
                    @Override
                    public BlockPos getScreenOpeningData(ServerPlayer serverPlayer) {
                        return pos;
                    }

                    @Override
                    public Component getDisplayName() {
                        return title;
                    }

                    @Override
                    public AbstractContainerMenu createMenu(int id, Inventory inventory, Player p) {
                        return factory.create(id, inventory, pos);
                    }
                }
        );
    }

    @FunctionalInterface
    public interface TriMenuFactory<T extends AbstractContainerMenu> {
        T create(int syncId, Inventory inventory, BlockPos pos);
    }

    public static void register() {
    }
}
