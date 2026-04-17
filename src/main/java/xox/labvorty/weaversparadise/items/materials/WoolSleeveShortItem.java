package xox.labvorty.weaversparadise.items.materials;

import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.component.CustomData;
import xox.labvorty.weaversparadise.data.tooltip_components.QualityTooltipComponent;
import xox.labvorty.weaversparadise.data.tooltip_components.helper.QualityTooltipData;

import java.util.Optional;

public class WoolSleeveShortItem extends Item {
    public WoolSleeveShortItem() {
        super(new Properties()
                .stacksTo(1)
                .rarity(Rarity.COMMON)
                .component(DataComponents.CUSTOM_DATA, CustomData.of(new CompoundTag() {{
                    putInt("quality", 0);
                }}))
        );
    }

    @Override
    public Optional<TooltipComponent> getTooltipImage(ItemStack stack) {
        CompoundTag data = stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag();

        return Optional.of(new QualityTooltipComponent(QualityTooltipData.getTooltipData(data.getInt("quality"))));
    }
}
