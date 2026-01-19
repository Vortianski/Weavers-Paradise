package xox.labvorty.weaversparadise.items;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class Kozinaki extends Item {
    public Kozinaki() {
        super(
                new Item.Properties()
                        .food(new FoodProperties.Builder().nutrition(8).saturationModifier(3f).build())
                        .stacksTo(64)
                        .rarity(Rarity.COMMON)
        );
    }
}
