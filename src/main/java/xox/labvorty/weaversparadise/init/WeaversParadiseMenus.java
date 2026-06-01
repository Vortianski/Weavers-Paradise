package xox.labvorty.weaversparadise.init;

import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import xox.labvorty.weaversparadise.WeaversParadiseMod;
import xox.labvorty.weaversparadise.gui.menu.ClothcraftingMenu;
import xox.labvorty.weaversparadise.gui.menu.DyeingMenu;
import xox.labvorty.weaversparadise.gui.menu.DyemakingMenu;
import xox.labvorty.weaversparadise.gui.menu.StringMenu;

public class WeaversParadiseMenus {
    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(ForgeRegistries.MENU_TYPES, WeaversParadiseMod.MOD_ID);
    public static final RegistryObject<MenuType<ClothcraftingMenu>> CLOTHCRAFTING_MENU = MENUS.register(
            "clothcrafting_menu",
            () -> IForgeMenuType.create(ClothcraftingMenu::new)
    );
    public static final RegistryObject<MenuType<DyeingMenu>> DYEING_MENU = MENUS.register(
            "dyeing_menu",
            () -> IForgeMenuType.create(DyeingMenu::new)
    );
    public static final RegistryObject<MenuType<DyemakingMenu>> DYEMAKING_MENU = MENUS.register(
            "dyemaking_menu",
            () -> IForgeMenuType.create(DyemakingMenu::new)
    );
    public static final RegistryObject<MenuType<StringMenu>> STRING_MENU = MENUS.register(
            "string_menu",
            () -> IForgeMenuType.create(StringMenu::new)
    );
}
