package xox.labvorty.weaversparadise.items.dye;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import xox.labvorty.weaversparadise.data.tooltip_components.DyeTooltipComponent;
import xox.labvorty.weaversparadise.data.tooltip_components.DyeTypeRegistry;

import java.util.Optional;

public class DyeCoreItem extends Item {
    public DyeCoreItem() {
        super(new Item.Properties()
                .stacksTo(1)
                .rarity(Rarity.COMMON)
        );
    }

    @Override
    public ItemStack getDefaultInstance() {
        ItemStack itemStack = super.getDefaultInstance();

        CompoundTag compoundTag = new CompoundTag() {{
            putString("dyeType", "default");
            putInt("lightValue", 15);
        }};
        itemStack.setTag(compoundTag);

        return itemStack;
    }

    @Override
    public Optional<TooltipComponent> getTooltipImage(ItemStack stack) {
        CompoundTag data = stack.getOrCreateTag();

        return Optional.of(
                new DyeTooltipComponent(
                        DyeTypeRegistry.getDyeType(data.getString("dyeType")).getA(),
                        data.getString("dyeType"),
                        data.getString("dyeType"),
                        data.getInt("lightValue"),
                        (255 << 24 | 255 << 16 | 255 << 8 | 255),
                        (255 << 24 | 255 << 16 | 255 << 8 | 255)
                )
        );
    }
}
