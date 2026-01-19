package xox.labvorty.weaversparadise.items;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class SilkSpool extends Item {
    public SilkSpool() {
        super(new Item.Properties().stacksTo(64).rarity(Rarity.COMMON));
    }
}
