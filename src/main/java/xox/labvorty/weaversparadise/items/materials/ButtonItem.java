package xox.labvorty.weaversparadise.items.materials;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class ButtonItem extends Item {
    public ButtonItem() {
        super(new Item.Properties().stacksTo(64).rarity(Rarity.COMMON));
    }
}
