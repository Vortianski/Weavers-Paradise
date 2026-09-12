package xox.labvorty.weaversparadise.items.materials;

import net.minecraft.util.RandomSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import xox.labvorty.weaversparadise.init.WeaversParadiseItems;

public class CottonBollItem extends Item {
    public CottonBollItem() {
        super(new Item.Properties().stacksTo(16).rarity(Rarity.COMMON));
    }

    public boolean hasCraftingRemainingItem(ItemStack stack) {
        return true;
    }

    public ItemStack getCraftingRemainingItem(ItemStack itemstack) {
        RandomSource randomSource = RandomSource.create();
        ItemStack stack = new ItemStack(WeaversParadiseItems.COTTON_SEEDS);
        stack.setCount(randomSource.nextInt(3));
        return stack;
    }

}
