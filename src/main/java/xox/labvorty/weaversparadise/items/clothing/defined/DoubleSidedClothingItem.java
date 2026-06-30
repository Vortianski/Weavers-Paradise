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

public abstract class DoubleSidedClothingItem extends Item implements ICurioItem {
    public DoubleSidedClothingItem(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull Optional<TooltipComponent> getTooltipImage(ItemStack stack) {
        CompoundTag data = stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag();

        List<DyeTooltipEntry> entries = new ArrayList<>();
        entries.add(
                new DyeTooltipEntry(
                        DyeTypeRegistry.getDyeType(getItemDyeType(stack, "left", 1)).getDyeIcon(),
                        getItemDyeType(stack, "left", 1),
                        getItemDyeType(stack, "left", 1),
                        getItemLightValue(stack, "left", 1),
                        getItemMainColor(stack, "left", 1),
                        getItemSecondaryColor(stack, "left", 1)
                )
        );

        if (!getStensilType(stack, "left").equals("default")) {
            entries.add(
                    new DyeTooltipEntry(
                            DyeTypeRegistry.getDyeType(getItemDyeType(stack, "left", 2)).getDyeIcon(),
                            getItemDyeType(stack, "left", 2),
                            getItemDyeType(stack, "left", 2),
                            getItemLightValue(stack, "left", 2),
                            getItemMainColor(stack, "left", 2),
                            getItemSecondaryColor(stack, "left", 2)
                    )
            );
        }

        entries.add(
                new DyeTooltipEntry(
                        DyeTypeRegistry.getDyeType(getItemDyeType(stack, "right", 1)).getDyeIcon(),
                        getItemDyeType(stack, "right", 1),
                        getItemDyeType(stack, "right", 1),
                        getItemLightValue(stack, "right", 1),
                        getItemMainColor(stack, "right", 1),
                        getItemSecondaryColor(stack, "right", 1)
                )
        );

        if (!getStensilType(stack, "right").equals("default")) {
            entries.add(
                    new DyeTooltipEntry(
                            DyeTypeRegistry.getDyeType(getItemDyeType(stack, "right", 2)).getDyeIcon(),
                            getItemDyeType(stack, "right", 2),
                            getItemDyeType(stack, "right" ,2),
                            getItemLightValue(stack, "right", 2),
                            getItemMainColor(stack, "right", 2),
                            getItemSecondaryColor(stack, "right", 2)
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

    public int getItemMainColor(ItemStack stack, String side, int part) {
        int color = 0;

        CompoundTag tag = stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag();

        if (side.equals("left")) {
            if (part == 1) {
                int red = tag.getInt("colorPriRedLeftOne");
                int green = tag.getInt("colorPriGreenLeftOne");
                int blue = tag.getInt("colorPriBlueLeftOne");

                color = 255 << 24 | red << 16 | green << 8 | blue;
            }

            if (part == 2) {
                int red = tag.getInt("colorPriRedLeftTwo");
                int green = tag.getInt("colorPriGreenLeftTwo");
                int blue = tag.getInt("colorPriBlueLeftTwo");

                color = 255 << 24 | red << 16 | green << 8 | blue;
            }

        } else if (side.equals("right")) {
            if (part == 1) {
                int red = tag.getInt("colorPriRedRightOne");
                int green = tag.getInt("colorPriGreenRightOne");
                int blue = tag.getInt("colorPriBlueRightOne");

                color = 255 << 24 | red << 16 | green << 8 | blue;
            }

            if (part == 2) {
                int red = tag.getInt("colorPriRedRightTwo");
                int green = tag.getInt("colorPriGreenRightTwo");
                int blue = tag.getInt("colorPriBlueRightTwo");

                color = 255 << 24 | red << 16 | green << 8 | blue;
            }
        }

        return color;
    }

    public int getItemSecondaryColor(ItemStack stack, String side, int part) {
        int color = 0;

        CompoundTag tag = stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag();

        if (side.equals("left")) {
            if (part == 1) {
                int red = tag.getInt("colorSecRedLeftOne");
                int green = tag.getInt("colorSecGreenLeftOne");
                int blue = tag.getInt("colorSecBlueLeftOne");

                color = 255 << 24 | red << 16 | green << 8 | blue;
            }

            if (part == 2) {
                int red = tag.getInt("colorSecRedLeftTwo");
                int green = tag.getInt("colorSecGreenLeftTwo");
                int blue = tag.getInt("colorSecBlueLeftTwo");

                color = 255 << 24 | red << 16 | green << 8 | blue;
            }
        } else if (side.equals("right")) {
            if (part == 1) {
                int red = tag.getInt("colorSecRedRightOne");
                int green = tag.getInt("colorSecGreenRightOne");
                int blue = tag.getInt("colorSecBlueRightOne");

                color = 255 << 24 | red << 16 | green << 8 | blue;
            }

            if (part == 2) {
                int red = tag.getInt("colorSecRedRightTwo");
                int green = tag.getInt("colorSecGreenRightTwo");
                int blue = tag.getInt("colorSecBlueRightTwo");

                color = 255 << 24 | red << 16 | green << 8 | blue;
            }
        }

        return color;
    }

    public String getItemDyeType(ItemStack stack, String side, int part) {
        String type = "default";

        CompoundTag tag = stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag();

        if (side.equals("left")) {
            if (part == 1) {
                type = tag.getString("dyeTypeLeftOne");
            }

            if (part == 2) {
                type = tag.getString("dyeTypeLeftTwo");
            }
        } else if (side.equals("right")) {
            if (part == 1) {
                type = tag.getString("dyeTypeRightOne");
            }

            if (part == 2) {
                type = tag.getString("dyeTypeRightTwo");
            }
        }

        return type;
    }

    public int getItemLightValue(ItemStack stack, String side, int part) {
        int light = 15;

        CompoundTag tag = stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag();

        if (side.equals("left")) {
            if (part == 1) {
                light = tag.getInt("lightValueLeftOne");
            }

            if (part == 2) {
                light = tag.getInt("lightValueLeftTwo");
            }
        } else if (side.equals("right")) {
            if (part == 1) {
                light = tag.getInt("lightValueRightOne");
            }

            if (part == 2) {
                light = tag.getInt("lightValueRightTwo");
            }
        }

        return light;
    }

    public String getStensilType(ItemStack stack, String side) {
        String type = "default";

        CompoundTag tag = stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag();

        if (side.equals("left")) {
            type = tag.getString("stensilTypeLeft");
        } else if (side.equals("right")) {
            type = tag.getString("stensilTypeRight");
        }

        return type;
    }

    protected static CompoundTag createDefault() {
        CompoundTag compoundTag = new CompoundTag();

        compoundTag.putInt("quality", 0);
        compoundTag.putString("dyeTypeLeftOne", "default");
        compoundTag.putString("dyeTypeRightOne", "default");
        compoundTag.putString("dyeTypeLeftTwo", "default");
        compoundTag.putString("dyeTypeRightTwo", "default");
        compoundTag.putString("stensilTypeLeft", "default");
        compoundTag.putString("stensilTypeRight", "default");
        compoundTag.putInt("colorPriRedLeftOne", 255);
        compoundTag.putInt("colorPriGreenLeftOne", 255);
        compoundTag.putInt("colorPriBlueLeftOne", 255);

        compoundTag.putInt("colorPriRedLeftTwo", 255);
        compoundTag.putInt("colorPriGreenLeftTwo", 255);
        compoundTag.putInt("colorPriBlueLeftTwo", 255);

        compoundTag.putInt("colorSecRedLeftOne", 255);
        compoundTag.putInt("colorSecGreenLeftOne", 255);
        compoundTag.putInt("colorSecBlueLeftOne", 255);

        compoundTag.putInt("colorSecRedLeftTwo", 255);
        compoundTag.putInt("colorSecGreenLeftTwo", 255);
        compoundTag.putInt("colorSecBlueLeftTwo", 255);

        compoundTag.putInt("colorPriRedRightOne", 255);
        compoundTag.putInt("colorPriGreenRightOne", 255);
        compoundTag.putInt("colorPriBlueRightOne", 255);

        compoundTag.putInt("colorPriRedRightTwo", 255);
        compoundTag.putInt("colorPriGreenRightTwo", 255);
        compoundTag.putInt("colorPriBlueRightTwo", 255);

        compoundTag.putInt("colorSecRedRightOne", 255);
        compoundTag.putInt("colorSecGreenRightOne", 255);
        compoundTag.putInt("colorSecBlueRightOne", 255);

        compoundTag.putInt("colorSecRedRightTwo", 255);
        compoundTag.putInt("colorSecGreenRightTwo", 255);
        compoundTag.putInt("colorSecBlueRightTwo", 255);

        compoundTag.putInt("lightValueLeftOne", 15);
        compoundTag.putInt("lightValueLeftTwo", 15);
        compoundTag.putInt("lightValueRightOne", 15);
        compoundTag.putInt("lightValueRightTwo", 15);

        return compoundTag;
    }

    protected static CompoundTag createDefault(int quality) {
        CompoundTag compoundTag = createDefault();

        compoundTag.putInt("quality", 10);

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
