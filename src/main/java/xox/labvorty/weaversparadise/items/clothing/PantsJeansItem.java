package xox.labvorty.weaversparadise.items.clothing;

import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;
import xox.labvorty.weaversparadise.data.tooltip_components.ClothingTooltipComponent;
import xox.labvorty.weaversparadise.data.tooltip_components.data.DyeIconHandler;
import xox.labvorty.weaversparadise.data.tooltip_components.helper.DyeTooltipEntry;
import xox.labvorty.weaversparadise.data.tooltip_components.helper.QualityTooltipData;
import xox.labvorty.weaversparadise.init.WeaversParadiseEnchantments;
import xox.labvorty.weaversparadise.init.WeaversParadiseItems;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PantsJeansItem extends Item implements ICurioItem, PantsInterface {
    public PantsJeansItem() {
        super(new Item.Properties()
                .stacksTo(1)
                .rarity(Rarity.COMMON)
                .durability(1)
                .component(DataComponents.CUSTOM_DATA, CustomData.of(new CompoundTag() {{
                    putInt("quality", 0);
                    putBoolean("is_open", false);
                    putString("dyeTypeOne", "default");
                    putString("dyeTypeTwo", "default");
                    putString("stensilType", "default");
                    putInt("colorPriRedOne", 255);
                    putInt("colorPriGreenOne", 255);
                    putInt("colorPriBlueOne", 255);

                    putInt("colorPriRedTwo", 255);
                    putInt("colorPriGreenTwo", 255);
                    putInt("colorPriBlueTwo", 255);

                    putInt("colorSecRedOne", 255);
                    putInt("colorSecGreenOne", 255);
                    putInt("colorSecBlueOne", 255);

                    putInt("colorSecRedTwo", 255);
                    putInt("colorSecGreenTwo", 255);
                    putInt("colorSecBlueTwo", 255);

                    putInt("lightValueOne", 15);
                    putInt("lightValueTwo", 15);
                }}))
        );
    }

    @Override
    public int getMaxDamage(ItemStack stack) {
        int quality = stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getInt("quality");
        int maxdamage = 100 + (20 * quality);

        return maxdamage;
    }

    @Override
    public Optional<TooltipComponent> getTooltipImage(ItemStack stack) {
        CompoundTag data = stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag();

        List<DyeTooltipEntry> entries = new ArrayList<>();
        entries.add(
                new DyeTooltipEntry(
                        DyeIconHandler.getOrDefault(getItemDyeType(stack, 1)).getTexture(),
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
                            DyeIconHandler.getOrDefault(getItemDyeType(stack, 2)).getTexture(),
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

    @Override
    public boolean isValidRepairItem(ItemStack stack, ItemStack repairCandidate) {
        CompoundTag stackdata = stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag();
        CompoundTag candidatedata = repairCandidate.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag();

        int stackquality = stackdata.getInt("quality");
        int candidatequality = candidatedata.getInt("quality");

        return repairCandidate.is(WeaversParadiseItems.JEANS_CLOTH) && candidatequality >= stackquality;
    }

    @Override
    public boolean supportsEnchantment(ItemStack stack, Holder<Enchantment> enchantment) {
        return enchantment.is(Enchantments.UNBREAKING) || enchantment.is(Enchantments.VANISHING_CURSE) || enchantment.is(Enchantments.MENDING) || enchantment.is(Enchantments.BINDING_CURSE) || enchantment.is(WeaversParadiseEnchantments.TOUGH_AS_NAILS);
    }

    @Override
    public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
        return EnchantmentHelper.getEnchantmentsForCrafting(book).keySet().stream().anyMatch(holder -> {
            if (holder.is(Enchantments.MENDING) || holder.is(Enchantments.UNBREAKING) || holder.is(Enchantments.VANISHING_CURSE) || holder.is(Enchantments.BINDING_CURSE) || holder.is(WeaversParadiseEnchantments.TOUGH_AS_NAILS)) {
                return true;
            }
            return false;
        });
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
    public int getEnchantmentValue() {
        return 15;
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return true;
    }
}
