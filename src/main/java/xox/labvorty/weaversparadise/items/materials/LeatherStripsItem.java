package xox.labvorty.weaversparadise.items.materials;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class LeatherStripsItem extends Item {
    public LeatherStripsItem() {
        super(
                new Properties().stacksTo(64).rarity(Rarity.COMMON)
        );
    }
}
