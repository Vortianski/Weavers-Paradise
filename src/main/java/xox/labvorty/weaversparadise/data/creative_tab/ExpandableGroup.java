package xox.labvorty.weaversparadise.data.creative_tab;

import net.minecraft.world.item.ItemStack;

import java.util.List;

public class ExpandableGroup {
    public final ItemStack icon;
    public final List<ItemStack> items;

    public ExpandableGroup(ItemStack icon, List<ItemStack> items) {
        this.icon = icon;
        this.items = items;
    }
}
