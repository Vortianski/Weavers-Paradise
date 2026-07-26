package xox.labvorty.weaversparadise.items.stencil;

import net.minecraft.world.item.Rarity;

public class StarStencilItem extends Stencil {
    public StarStencilItem() {
        super(
                new Properties().rarity(Rarity.COMMON).stacksTo(1)
        );
    }

    @Override
    public String getType() {
        return "stars";
    }
}
