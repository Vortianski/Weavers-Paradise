package xox.labvorty.weaversparadise.items.dye;

import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.Level;
import xox.labvorty.weaversparadise.data.tooltip_components.DyeTooltipComponent;
import xox.labvorty.weaversparadise.data.tooltip_components.DyeTypeRegistry;

import java.util.Optional;

public class BottledDyeItem extends Item {
    public BottledDyeItem() {
        super(new Item.Properties()
                .stacksTo(1)
                .rarity(Rarity.COMMON)
                .component(DataComponents.CUSTOM_DATA, CustomData.of(new CompoundTag() {{
                    putString("dyeType", "default");
                    putInt("colorRedOne", 255);
                    putInt("colorGreenOne", 255);
                    putInt("colorBlueOne", 255);

                    putInt("colorRedTwo", 255);
                    putInt("colorGreenTwo", 255);
                    putInt("colorBlueTwo", 255);

                    putInt("amount", 10);
                    putInt("lightValue", 15);
                }}))
        );
    }

    @Override
    public boolean isBarVisible(ItemStack stack) {
        return true;
    }

    @Override
    public int getBarColor(ItemStack stack) {
        return 0xFFFFFF;
    }

    @Override
    public int getBarWidth(ItemStack stack) {
        int amount = stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getInt("amount");

        return (int)((amount / 10.0f) * 13.0f);
    }

    @Override
    public Optional<TooltipComponent> getTooltipImage(ItemStack stack) {
        CompoundTag data = stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag();

        return Optional.of(
                new DyeTooltipComponent(
                        DyeTypeRegistry.getDyeType(data.getString("dyeType")).getDyeIcon(),
                        data.getString("dyeType"),
                        data.getString("dyeType"),
                        data.getInt("lightValue"),
                        getItemMainColor(stack),
                        getItemSecondaryColor(stack)
                )
        );
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
        CompoundTag compoundTag = stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag();

        final int color = Mth.lerpInt(compoundTag.getInt("lightValue") / 15.0f, 100, 255);

        if (compoundTag.getString("dyeType").equals("redstone")) {
            CustomData.update(DataComponents.CUSTOM_DATA, stack, (tag) -> {
                tag.putInt("colorRedOne", color);
                tag.putInt("colorGreenOne", 0);
                tag.putInt("colorBlueOne", 0);
            });
        }

        if (compoundTag.getInt("amount") < 1) {
            if (entity instanceof Player player) {
                player.getInventory().setItem(slotId, new ItemStack(Items.GLASS_BOTTLE));
            }
        }
    }

    public int getItemMainColor(ItemStack stack) {
        int color = 0;

        CompoundTag tag = stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag();

        int red = tag.getInt("colorRedOne");
        int green = tag.getInt("colorGreenOne");
        int blue = tag.getInt("colorBlueOne");

        color = 255 << 24 | red << 16 | green << 8 | blue;

        return color;
    }

    public int getItemSecondaryColor(ItemStack stack) {
        int color = 0;

        CompoundTag tag = stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag();

        int red = tag.getInt("colorRedTwo");
        int green = tag.getInt("colorGreenTwo");
        int blue = tag.getInt("colorBlueTwo");

        color = 255 << 24 | red << 16 | green << 8 | blue;

        return color;
    }

    public int getItemLightValue(ItemStack stack) {
        int lightValue = 0;

        CompoundTag tag = stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag();

        lightValue = tag.getInt("lightValue");

        return lightValue;
    }
}
