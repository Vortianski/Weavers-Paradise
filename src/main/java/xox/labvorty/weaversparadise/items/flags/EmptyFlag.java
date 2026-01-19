package xox.labvorty.weaversparadise.items.flags;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class EmptyFlag extends Item {
    public EmptyFlag() {
        super(new Item.Properties().rarity(Rarity.COMMON).stacksTo(1));
    }
}
