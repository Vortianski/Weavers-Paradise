package xox.labvorty.weaversparadise.items.clothing.defined;

import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;
import xox.labvorty.weaversparadise.data.tooltip_components.ClothingTooltipComponent;
import xox.labvorty.weaversparadise.data.tooltip_components.DyeTypeRegistry;
import xox.labvorty.weaversparadise.data.tooltip_components.helper.DyeTooltipEntry;
import xox.labvorty.weaversparadise.data.tooltip_components.helper.QualityTooltipData;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class SingleSidedClothingItem extends Item implements ICurioItem {
    public SingleSidedClothingItem(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull Optional<TooltipComponent> getTooltipImage(ItemStack stack) {
        CompoundTag data = stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag();

        List<DyeTooltipEntry> entries = new ArrayList<>();
        entries.add(
                new DyeTooltipEntry(
                        DyeTypeRegistry.getDyeType(getItemDyeType(stack, 1)).getDyeIcon(),
                        getItemDyeType(stack, 1),
                        getItemDyeType(stack, 1),
                        getItemLightValue(stack, 1),
                        getItemMainColor(stack, 1),
                        getItemSecondaryColor(stack, 1)
                )
        );
        if (!getStensilType(stack).equals("default")) {
            entries.add(
                    new DyeTooltipEntry(
                            DyeTypeRegistry.getDyeType(getItemDyeType(stack, 2)).getDyeIcon(),
                            getItemDyeType(stack, 2),
                            getItemDyeType(stack, 2),
                            getItemLightValue(stack, 2),
                            getItemMainColor(stack, 2),
                            getItemSecondaryColor(stack, 2)
                    )
            );
        }

        return Optional.of(
                new ClothingTooltipComponent(
                        QualityTooltipData.getTooltipData(data.getInt("quality")),
                        entries
                )
        );
    }

    public int getItemMainColor(ItemStack stack, int part) {
        int color = 0;

        CompoundTag tag = stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag();

        if (part == 1) {
            int red = tag.getInt("colorPriRedOne");
            int green = tag.getInt("colorPriGreenOne");
            int blue = tag.getInt("colorPriBlueOne");

            color = 255 << 24 | red << 16 | green << 8 | blue;
        }

        if (part == 2) {
            int red = tag.getInt("colorPriRedTwo");
            int green = tag.getInt("colorPriGreenTwo");
            int blue = tag.getInt("colorPriBlueTwo");

            color = 255 << 24 | red << 16 | green << 8 | blue;
        }

        return color;
    }

    public int getItemSecondaryColor(ItemStack stack, int part) {
        int color = 0;

        CompoundTag tag = stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag();

        if (part == 1) {
            int red = tag.getInt("colorSecRedOne");
            int green = tag.getInt("colorSecGreenOne");
            int blue = tag.getInt("colorSecBlueOne");

            color = 255 << 24 | red << 16 | green << 8 | blue;
        }

        if (part == 2) {
            int red = tag.getInt("colorSecRedTwo");
            int green = tag.getInt("colorSecGreenTwo");
            int blue = tag.getInt("colorSecBlueTwo");

            color = 255 << 24 | red << 16 | green << 8 | blue;
        }

        return color;
    }

    public String getItemDyeType(ItemStack stack, int part) {
        String type = "default";

        CompoundTag tag = stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag();

        if (part == 1) {
            type = tag.getString("dyeTypeOne");
        }

        if (part == 2) {
            type = tag.getString("dyeTypeTwo");
        }

        return type;
    }

    public int getItemLightValue(ItemStack stack, int part) {
        int light = 15;

        CompoundTag tag = stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag();

        if (part == 1) {
            light = tag.getInt("lightValueOne");
        }

        if (part == 2) {
            light = tag.getInt("lightValueTwo");
        }

        return light;
    }

    public String getStensilType(ItemStack stack) {
        String type = "default";

        CompoundTag tag = stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag();

        type = tag.getString("stensilType");

        return type;
    }

    protected static CompoundTag createDefault() {
        CompoundTag compoundTag = new CompoundTag();

        compoundTag.putInt("quality", 0);
        compoundTag.putString("dyeTypeOne", "default");
        compoundTag.putString("dyeTypeTwo", "default");
        compoundTag.putString("stensilType", "default");
        compoundTag.putInt("colorPriRedOne", 255);
        compoundTag.putInt("colorPriGreenOne", 255);
        compoundTag.putInt("colorPriBlueOne", 255);

        compoundTag.putInt("colorPriRedTwo", 255);
        compoundTag.putInt("colorPriGreenTwo", 255);
        compoundTag.putInt("colorPriBlueTwo", 255);

        compoundTag.putInt("colorSecRedOne", 255);
        compoundTag.putInt("colorSecGreenOne", 255);
        compoundTag.putInt("colorSecBlueOne", 255);

        compoundTag.putInt("colorSecRedTwo", 255);
        compoundTag.putInt("colorSecGreenTwo", 255);
        compoundTag.putInt("colorSecBlueTwo", 255);

        compoundTag.putInt("lightValueOne", 15);
        compoundTag.putInt("lightValueTwo", 15);

        return compoundTag;
    }

    protected static CompoundTag createDefault(boolean shirtParam) {
        CompoundTag compoundTag = createDefault();

        if (shirtParam) {
            compoundTag.putBoolean("is_open", false);
        }

        return compoundTag;
    }

    @Override
    public boolean canUnequip(SlotContext slotContext, ItemStack stack) {
        LivingEntity livingEntity = slotContext.entity();
        if (livingEntity instanceof Player player && player.isCreative()) {
            return true;
        }

        return EnchantmentHelper.getEnchantmentsForCrafting(stack)
                .keySet()
                .stream()
                .noneMatch(holder -> holder.is(Enchantments.BINDING_CURSE));
    }

    @Override
    public int getEnchantmentValue(@NotNull ItemStack itemStack) {
        return 15;
    }

    @Override
    public boolean isEnchantable(@NotNull ItemStack itemStack) {
        return true;
    }

    @Override
    public boolean supportsEnchantment(@NotNull ItemStack itemStack, Holder<Enchantment> enchantment) {
        return getSupportedEnchantments().contains(enchantment.getKey());
    }

    @Override
    public boolean isBookEnchantable(@NotNull ItemStack itemStack, @NotNull ItemStack bookStack) {
        return EnchantmentHelper.getEnchantmentsForCrafting(bookStack).keySet().stream().anyMatch(holder -> getSupportedEnchantments().contains(holder.getKey()));
    }

    protected List<ResourceKey<Enchantment>> getSupportedEnchantments() {
        return List.of();
    }
}
