package xox.labvorty.weaversparadise.init;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import xox.labvorty.weaversparadise.gui.screen.ClothcraftingScreen;
import xox.labvorty.weaversparadise.gui.screen.DyeingScreen;
import xox.labvorty.weaversparadise.gui.screen.DyemakingScreen;
import xox.labvorty.weaversparadise.gui.screen.StringScreen;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class WeaversParadiseScreens {
    @SubscribeEvent
    public static void registerScreens(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            MenuScreens.register(WeaversParadiseMenus.STRING_MENU.get(), StringScreen::new);
            MenuScreens.register(WeaversParadiseMenus.DYEING_MENU.get(), DyeingScreen::new);
            MenuScreens.register(WeaversParadiseMenus.CLOTHCRAFTING_MENU.get(), ClothcraftingScreen::new);
            MenuScreens.register(WeaversParadiseMenus.DYEMAKING_MENU.get(), DyemakingScreen::new);
        });
    }
}
