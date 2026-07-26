package xox.labvorty.weaversparadise.data.recipe;

import net.minecraft.world.level.block.ComposterBlock;
import xox.labvorty.weaversparadise.init.WeaversParadiseItems;

public class Compostables {
    public static void register() {
        ComposterBlock.COMPOSTABLES.put(
                WeaversParadiseItems.COTTON_SEEDS.get(),
                0.3f
        );
    }
}
