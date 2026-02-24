package xox.labvorty.weaversparadise.items;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class LeatherStrips extends Item {
    public LeatherStrips() {
        super(
                new Properties().stacksTo(64).rarity(Rarity.COMMON)
        );
    }
}
