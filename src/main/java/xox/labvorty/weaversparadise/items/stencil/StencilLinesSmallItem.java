package xox.labvorty.weaversparadise.items.stencil;

import net.minecraft.world.item.Rarity;

public class StencilLinesSmallItem extends Stencil {
    public StencilLinesSmallItem() {
        super(new Properties().rarity(Rarity.COMMON).stacksTo(1));
    }

    @Override
    public String getType() {
        return "small_lines";
    }
}
