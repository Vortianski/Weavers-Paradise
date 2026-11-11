package xox.labvorty.weaversparadise.items.misc;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;
import xox.labvorty.weaversparadise.data.tooltip_components.QualityTooltipComponent;
import xox.labvorty.weaversparadise.data.tooltip_components.helper.QualityTooltipData;

import java.util.Optional;

public class QualityBlockItem extends BlockItem {
    public QualityBlockItem(Block block, Properties properties) {
        super(block, properties);
    }

    @Override
    public @NotNull Optional<TooltipComponent> getTooltipImage(ItemStack stack) {
        CompoundTag data = stack.getOrCreateTag();

        return Optional.of(new QualityTooltipComponent(QualityTooltipData.getTooltipData(data.getInt("quality"))));
    }
}
