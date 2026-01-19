package xox.labvorty.weaversparadise.items;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class EmptyWoolSpool extends Item {
    public EmptyWoolSpool() {
        super(new Item.Properties().stacksTo(64).rarity(Rarity.COMMON));
    }
}
