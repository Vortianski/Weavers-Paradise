package xox.labvorty.weaversparadise.items;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class StensilBase extends Item {
    public StensilBase() {
        super(new Item.Properties().rarity(Rarity.COMMON).stacksTo(64));
    }
}
