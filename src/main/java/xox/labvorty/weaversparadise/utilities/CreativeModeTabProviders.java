package xox.labvorty.weaversparadise.utilities;

import net.minecraft.world.item.ItemStack;
import xox.labvorty.weaversparadise.items.misc.PlushieItem;

import java.util.List;
import java.util.Optional;

public class CreativeModeTabProviders {
    public static List<ItemStack> getPlayerPlushies() {
        return List.of(
                PlushieItem.createPlushie(Optional.of("Vortianski"), Optional.empty()),
                PlushieItem.createPlushie(Optional.of("Pelemeshek"), Optional.empty()),
                PlushieItem.createPlushie(Optional.of("Elifian"), Optional.empty()),
                PlushieItem.createPlushie(Optional.of("_Alazi_"), Optional.empty())
        );
    }
}
