package xox.labvorty.weaversparadise.items;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import xox.labvorty.weaversparadise.init.WeaversParadiseItems;

public class NeedleWithThread extends Item {
    public NeedleWithThread() {
        super(new Item.Properties().rarity(Rarity.COMMON).stacksTo(1).craftRemainder(WeaversParadiseItems.NEEDLE.get()));
    }
}
