package xox.labvorty.weaversparadise.items.stencil;

import net.minecraft.world.item.Rarity;

public class StencilPawsItem extends Stencil {
    public StencilPawsItem() {
        super(new Properties().rarity(Rarity.COMMON).stacksTo(1));
    }

    @Override
    public String getType() {
        return "paws";
    }
}
