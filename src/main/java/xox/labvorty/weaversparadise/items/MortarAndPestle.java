package xox.labvorty.weaversparadise.items;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import xox.labvorty.weaversparadise.init.WeaversParadiseItems;

import java.util.function.Supplier;

public class MortarAndPestle extends Item {
    public MortarAndPestle() {
        super(
                new Item.Properties()
                        .rarity(Rarity.COMMON)
                        .stacksTo(1)
        );
    }

    @Override
    public ItemStack getCraftingRemainingItem(ItemStack itemStack) {
        return new ItemStack(WeaversParadiseItems.MORTAR_AND_PESTLE.get());
    }

    @Override
    public boolean hasCraftingRemainingItem(ItemStack stack) {
        return true;
    }
}
