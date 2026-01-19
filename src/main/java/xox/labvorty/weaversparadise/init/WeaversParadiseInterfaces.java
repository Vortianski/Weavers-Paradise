package xox.labvorty.weaversparadise.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import xox.labvorty.weaversparadise.gui.menu.*;

import java.awt.*;

public class WeaversParadiseInterfaces {
    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(Registries.MENU, "weaversparadise");
    public static final DeferredHolder<MenuType<?>, MenuType<TestMenu>> TEST_MENU = MENUS.register("test_menu", () -> IMenuTypeExtension.create(TestMenu::new));
    public static final DeferredHolder<MenuType<?>, MenuType<StringMenu>> STRING_MENU = MENUS.register("string_menu", () -> IMenuTypeExtension.create(StringMenu::new));
    public static final DeferredHolder<MenuType<?>, MenuType<ClothcraftingMenu>> CLOTHCRAFTING_MENU = MENUS.register("clothcrafting_menu", () -> IMenuTypeExtension.create(ClothcraftingMenu::new));
    public static final DeferredHolder<MenuType<?>, MenuType<DyemakingMenu>> DYEMAKING_MENU = MENUS.register("dyemaking_menu", () -> IMenuTypeExtension.create(DyemakingMenu::new));
    public static final DeferredHolder<MenuType<?>, MenuType<DyeingMenu>> DYEING_MENU = MENUS.register("dyeing_menu", () -> IMenuTypeExtension.create(DyeingMenu::new));
}
