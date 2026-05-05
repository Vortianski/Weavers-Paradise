package xox.labvorty.weaversparadise.items.materials;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class ChromaticBloomFruitItem extends Item {
    public ChromaticBloomFruitItem() {
        super(new Properties()
                .rarity(Rarity.COMMON)
                .stacksTo(64)
        );
    }
}
