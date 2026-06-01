package xox.labvorty.weaversparadise.items.materials;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import xox.labvorty.weaversparadise.data.tooltip_components.QualityTooltipComponent;
import xox.labvorty.weaversparadise.data.tooltip_components.helper.QualityTooltipData;

import java.util.Optional;

public class SilkClothItem extends Item {
    public SilkClothItem() {
        super(
                new Properties()
                    .stacksTo(64)
                    .rarity(Rarity.COMMON)
        );
    }

    @Override
    public ItemStack getDefaultInstance() {
        ItemStack itemStack = super.getDefaultInstance();

        CompoundTag compoundTag = new CompoundTag() {{
            putInt("quality", 0);
        }};
        itemStack.setTag(compoundTag);

        return itemStack;
    }

    @Override
    public Optional<TooltipComponent> getTooltipImage(ItemStack itemStack) {
        CompoundTag data = itemStack.getOrCreateTag();

        return Optional.of(new QualityTooltipComponent(QualityTooltipData.getTooltipData(data.getInt("quality"))));
    }
}
