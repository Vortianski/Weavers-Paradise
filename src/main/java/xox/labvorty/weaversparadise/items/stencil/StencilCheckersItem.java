package xox.labvorty.weaversparadise.items.stencil;

import net.minecraft.world.item.Rarity;

public class StencilCheckersItem extends Stencil {
    public StencilCheckersItem() {
        super(new Properties().rarity(Rarity.COMMON).stacksTo(1));
    }

    @Override
    public String getType() {
        return "checkers";
    }
}
