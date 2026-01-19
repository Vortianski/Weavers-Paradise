package xox.labvorty.weaversparadise.items;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class ChromaticBloomFruit extends Item {
    public ChromaticBloomFruit() {
        super(new Item.Properties()
                .rarity(Rarity.COMMON)
                .stacksTo(64)
        );
    }
}
