package xox.labvorty.weaversparadise.items.stencil;

import net.minecraft.world.item.Rarity;

public class StencilCheckersSmallItem extends Stencil {
    public StencilCheckersSmallItem() {
        super(
                new Properties().rarity(Rarity.COMMON).stacksTo(1)
        );
    }

    @Override
    public String getType() {
        return "checkers_small";
    }
}
