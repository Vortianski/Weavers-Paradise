package xox.labvorty.weaversparadise.items;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class Button extends Item {
    public Button() {
        super(new Item.Properties().stacksTo(64).rarity(Rarity.COMMON));
    }
}
