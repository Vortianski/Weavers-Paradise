package xox.labvorty.weaversparadise.items.food;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class KozinakiItem extends Item {
    public KozinakiItem() {
        super(
                new Item.Properties()
                        .food(new FoodProperties.Builder().nutrition(8).saturationModifier(3f).build())
                        .stacksTo(64)
                        .rarity(Rarity.COMMON)
        );
    }
}
