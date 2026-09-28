package xox.labvorty.weaversparadise.items.materials;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import org.jetbrains.annotations.NotNull;
import xox.labvorty.weaversparadise.data.tooltip_components.QualityTooltipComponent;
import xox.labvorty.weaversparadise.data.tooltip_components.helper.QualityTooltipData;

import java.util.Optional;

public class WoolPantLegItem extends Item {
    public WoolPantLegItem() {
        super(new Properties()
                .stacksTo(1)
                .rarity(Rarity.COMMON)
        );
    }

    @Override
    public @NotNull ItemStack getDefaultInstance() {
        ItemStack itemStack = super.getDefaultInstance();

        CompoundTag compoundTag = new CompoundTag() {{
            putInt("quality", 0);
        }};
        itemStack.setTag(compoundTag);

        return itemStack;
    }

    @Override
    public @NotNull Optional<TooltipComponent> getTooltipImage(ItemStack stack) {
        CompoundTag data = stack.getOrCreateTag();

        return Optional.of(new QualityTooltipComponent(QualityTooltipData.getTooltipData(data.getInt("quality"))));
    }
}
