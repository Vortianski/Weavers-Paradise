package xox.labvorty.weaversparadise.items.stencil;

import net.minecraft.world.item.Rarity;

public class StencilCrossItem extends Stencil {
    public StencilCrossItem() {
        super(new Properties().rarity(Rarity.COMMON).stacksTo(1));
    }

    @Override
    public String getType() {
        return "cross";
    }
}
