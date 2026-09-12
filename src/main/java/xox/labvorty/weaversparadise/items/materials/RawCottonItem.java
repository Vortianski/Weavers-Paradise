package xox.labvorty.weaversparadise.items.materials;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class RawCottonItem extends Item {
    public RawCottonItem() {
        super(new Item.Properties().stacksTo(64).rarity(Rarity.COMMON));
    }
}
