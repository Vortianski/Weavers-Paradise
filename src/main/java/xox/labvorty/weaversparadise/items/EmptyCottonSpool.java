package xox.labvorty.weaversparadise.items;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class EmptyCottonSpool extends Item {
    public EmptyCottonSpool() {
        super(new Item.Properties().stacksTo(64).rarity(Rarity.COMMON));
    }
}
