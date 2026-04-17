package xox.labvorty.weaversparadise.init;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import xox.labvorty.weaversparadise.gui.screen.ClothcraftingScreen;
import xox.labvorty.weaversparadise.gui.screen.DyeingScreen;
import xox.labvorty.weaversparadise.gui.screen.DyemakingScreen;
import xox.labvorty.weaversparadise.gui.screen.StringScreen;

@EventBusSubscriber
public class WeaversParadiseMenuScreens {
    @SubscribeEvent
    public static void registerScreens(RegisterMenuScreensEvent event) {
        event.register(WeaversParadiseInterfaces.STRING_MENU.get(), StringScreen::new);
        event.register(WeaversParadiseInterfaces.CLOTHCRAFTING_MENU.get(), ClothcraftingScreen::new);
        event.register(WeaversParadiseInterfaces.DYEMAKING_MENU.get(), DyemakingScreen::new);
        event.register(WeaversParadiseInterfaces.DYEING_MENU.get(), DyeingScreen::new);
    }
}
