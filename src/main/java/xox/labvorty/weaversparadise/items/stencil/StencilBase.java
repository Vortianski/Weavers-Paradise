package xox.labvorty.weaversparadise.items.stencil;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class StencilBase extends Item {
    public StencilBase() {
        super(new Item.Properties().rarity(Rarity.COMMON).stacksTo(64));
    }
}
