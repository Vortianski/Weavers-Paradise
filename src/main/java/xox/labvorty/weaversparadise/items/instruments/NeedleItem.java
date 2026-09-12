package xox.labvorty.weaversparadise.items.instruments;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class NeedleItem extends Item {
    public NeedleItem() {
        super(new Item.Properties().stacksTo(1).rarity(Rarity.COMMON));
    }
}
