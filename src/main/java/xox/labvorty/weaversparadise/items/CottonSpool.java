package xox.labvorty.weaversparadise.items;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class CottonSpool extends Item {
    public CottonSpool() {
        super(new Item.Properties().stacksTo(64).rarity(Rarity.COMMON));
    }
}
