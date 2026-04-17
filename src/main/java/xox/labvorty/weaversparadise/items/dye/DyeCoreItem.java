package xox.labvorty.weaversparadise.items.dye;

import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.component.CustomData;
import xox.labvorty.weaversparadise.data.tooltip_components.DyeTooltipComponent;
import xox.labvorty.weaversparadise.data.tooltip_components.data.DyeIconHandler;

import java.util.Optional;

public class DyeCoreItem extends Item {
    public DyeCoreItem() {
        super(new Item.Properties()
                .rarity(Rarity.COMMON)
                .stacksTo(1)
                .component(DataComponents.CUSTOM_DATA, CustomData.of(new CompoundTag() {{
                    putString("dyeType", "default");
                    putInt("lightValue", 15);
                }}))
        );
    }

    @Override
    public Optional<TooltipComponent> getTooltipImage(ItemStack stack) {
        CompoundTag data = stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag();

        return Optional.of(
                new DyeTooltipComponent(
                        DyeIconHandler.getOrDefault(
                                data.getString("dyeType")
                        ).getTexture(),
                        data.getString("dyeType"),
                        data.getString("dyeType"),
                        data.getInt("lightValue"),
                        (255 << 24 | 255 << 16 | 255 << 8 | 255),
                        (255 << 24 | 255 << 16 | 255 << 8 | 255)
                )
        );
    }
}
