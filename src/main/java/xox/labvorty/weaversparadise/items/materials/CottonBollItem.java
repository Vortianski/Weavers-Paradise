package xox.labvorty.weaversparadise.items.materials;

import net.minecraft.util.RandomSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import xox.labvorty.weaversparadise.init.WeaversParadiseItems;

public class CottonBollItem extends Item {
    public CottonBollItem() {
        super(new Properties().stacksTo(16).rarity(Rarity.COMMON));
    }

    @Override
    public boolean hasCraftingRemainingItem(ItemStack stack) {
        return true;
    }

    @Override
    public ItemStack getCraftingRemainingItem(ItemStack itemstack) {
        RandomSource randomSource = RandomSource.create();
        ItemStack stack = new ItemStack(WeaversParadiseItems.COTTON_SEEDS.get());
        stack.setCount(randomSource.nextInt(3));
        return stack;
    }

}
