package xox.labvorty.weaversparadise.items.stencil;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class StencilBaseItem extends Item {
    public StencilBaseItem() {
        super(new Item.Properties().rarity(Rarity.COMMON).stacksTo(64));
    }
}
