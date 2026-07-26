package xox.labvorty.weaversparadise.utils;

import net.minecraft.world.item.ItemStack;
import xox.labvorty.weaversparadise.items.misc.PlushieItem;

import java.util.List;

public class CreativeModeTabProviders {
    public static List<ItemStack> getPlushies() {
        return List.of(
                PlushieItem.createPlushie("Vortianski", null),
                PlushieItem.createPlushie("Pelemeshek", null),
                PlushieItem.createPlushie("Elifian", null),
                PlushieItem.createPlushie("_Alazi_", null)
        );
    }
}
