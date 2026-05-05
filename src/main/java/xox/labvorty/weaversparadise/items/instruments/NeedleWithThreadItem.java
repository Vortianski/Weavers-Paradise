package xox.labvorty.weaversparadise.items.instruments;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import xox.labvorty.weaversparadise.init.WeaversParadiseItems;

public class NeedleWithThreadItem extends Item {
    public NeedleWithThreadItem() {
        super(new Properties().rarity(Rarity.COMMON).stacksTo(1).craftRemainder(WeaversParadiseItems.NEEDLE.get()));
    }
}
