package xox.labvorty.weaversparadise.items;

import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
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
import xox.labvorty.weaversparadise.data.WeaversParadiseDyeIconHandler;
import xox.labvorty.weaversparadise.data.WeaversParadiseStatHandler;
import xox.labvorty.weaversparadise.init.WeaversParadiseItems;
import xox.labvorty.weaversparadise.tooltips.ImageTooltipComponent;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ThighHighsCotton extends Item implements ICurioItem, ThighHighsInterface {
    private static final ResourceKey<Enchantment> enchantment = ResourceKey.create(Registries.ENCHANTMENT, ResourceLocation.fromNamespaceAndPath("weaversparadise", "featherlight"));
    public ThighHighsCotton() {
        super(new Item.Properties()
                .stacksTo(1)
                .rarity(Rarity.COMMON)
                .durability(1)
                .component(DataComponents.CUSTOM_DATA, CustomData.of(new CompoundTag() {{
                    putInt("quality", 0);
                    putString("dyeTypeLeftOne", "default");
                    putString("dyeTypeRightOne", "default");
                    putString("dyeTypeLeftTwo", "default");
                    putString("dyeTypeRightTwo", "default");
                    putString("stensilTypeLeft", "default");
                    putString("stensilTypeRight", "default");
                    putInt("colorPriRedLeftOne", 255);
                    putInt("colorPriGreenLeftOne", 255);
                    putInt("colorPriBlueLeftOne", 255);

                    putInt("colorPriRedLeftTwo", 255);
                    putInt("colorPriGreenLeftTwo", 255);
                    putInt("colorPriBlueLeftTwo", 255);

                    putInt("colorSecRedLeftOne", 255);
                    putInt("colorSecGreenLeftOne", 255);
                    putInt("colorSecBlueLeftOne", 255);

                    putInt("colorSecRedLeftTwo", 255);
                    putInt("colorSecGreenLeftTwo", 255);
                    putInt("colorSecBlueLeftTwo", 255);

                    putInt("colorPriRedRightOne", 255);
                    putInt("colorPriGreenRightOne", 255);
                    putInt("colorPriBlueRightOne", 255);

                    putInt("colorPriRedRightTwo", 255);
                    putInt("colorPriGreenRightTwo", 255);
                    putInt("colorPriBlueRightTwo", 255);

                    putInt("colorSecRedRightOne", 255);
                    putInt("colorSecGreenRightOne", 255);
                    putInt("colorSecBlueRightOne", 255);

                    putInt("colorSecRedRightTwo", 255);
                    putInt("colorSecGreenRightTwo", 255);
                    putInt("colorSecBlueRightTwo", 255);

                    putInt("lightValueLeftOne", 15);
                    putInt("lightValueLeftTwo", 15);
                    putInt("lightValueRightOne", 15);
                    putInt("lightValueRightTwo", 15);
                }}))
        );
    }

    @Override
    public Multimap<Holder<Attribute>, AttributeModifier> getAttributeModifiers(SlotContext slotContext, ResourceLocation id, ItemStack stack) {
        Multimap<Holder<Attribute>, AttributeModifier> modifiers = LinkedHashMultimap.create();

        LivingEntity entity = slotContext.entity();

        Pair<String, Double> stat = WeaversParadiseStatHandler.calculateStaticType(entity);

        if (stat.getFirst().equals("cotton")) {
            modifiers.put(
                    Attributes.FALL_DAMAGE_MULTIPLIER,
                    new AttributeModifier(
                            ResourceLocation.fromNamespaceAndPath("weaversparadise", "thigh_highs_cotton"),
                            stat.getSecond() * -0.1,
                            AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
                    )
            );

            modifiers.put(
                    Attributes.MOVEMENT_EFFICIENCY,
                    new AttributeModifier(
                            ResourceLocation.fromNamespaceAndPath("weaversparadise", "thigh_highs_cotton"),
                            stat.getSecond() * -0.1,
                            AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
                    )
            );
        }

        return modifiers;
    }

    @Override
    public int getMaxDamage(ItemStack stack) {
        int quality = stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getInt("quality");
        int maxdamage = 100 + (20 * quality);

        return maxdamage;
    }

    @Override
    public Optional<TooltipComponent> getTooltipImage(ItemStack stack) {
        List<ResourceLocation> list = new ArrayList();

        CompoundTag data = stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag();

        if (data.getInt("quality") >= 2) {
            list.add(ResourceLocation.parse("weaversparadise:textures/tooltips/star_full.png"));
        } else if (data.getInt("quality") == 1) {
            list.add(ResourceLocation.parse("weaversparadise:textures/tooltips/star_half.png"));
        } else {
            list.add(ResourceLocation.parse("weaversparadise:textures/tooltips/star_empty.png"));
        }

        if (data.getInt("quality") >= 4) {
            list.add(ResourceLocation.parse("weaversparadise:textures/tooltips/star_full.png"));
        } else if (data.getInt("quality") == 3) {
            list.add(ResourceLocation.parse("weaversparadise:textures/tooltips/star_half.png"));
        } else {
            list.add(ResourceLocation.parse("weaversparadise:textures/tooltips/star_empty.png"));
        }

        if (data.getInt("quality") >= 6) {
            list.add(ResourceLocation.parse("weaversparadise:textures/tooltips/star_full.png"));
        } else if (data.getInt("quality") == 5) {
            list.add(ResourceLocation.parse("weaversparadise:textures/tooltips/star_half.png"));
        } else {
            list.add(ResourceLocation.parse("weaversparadise:textures/tooltips/star_empty.png"));
        }

        if (data.getInt("quality") >= 8) {
            list.add(ResourceLocation.parse("weaversparadise:textures/tooltips/star_full.png"));
        } else if (data.getInt("quality") == 7) {
            list.add(ResourceLocation.parse("weaversparadise:textures/tooltips/star_half.png"));
        } else {
            list.add(ResourceLocation.parse("weaversparadise:textures/tooltips/star_empty.png"));
        }

        if (data.getInt("quality") >= 10) {
            list.add(ResourceLocation.parse("weaversparadise:textures/tooltips/star_full.png"));
        } else if (data.getInt("quality") == 9) {
            list.add(ResourceLocation.parse("weaversparadise:textures/tooltips/star_half.png"));
        } else {
            list.add(ResourceLocation.parse("weaversparadise:textures/tooltips/star_empty.png"));
        }

        return Optional.of(
                new ImageTooltipComponent(
                        list,
                        WeaversParadiseDyeIconHandler.getOrDefault(
                                getItemDyeType(stack, "left", 1)
                        ).getTexture(),
                        getItemDyeType(stack, "left", 1),
                        getItemDyeType(stack, "left", 1),
                        getItemLightValue(stack, "left", 1),
                        getItemMainColor(stack, "left", 1),
                        getItemSecondaryColor(stack, "left", 1),
                        WeaversParadiseDyeIconHandler.getOrDefault(
                                getItemDyeType(stack, "left", 2)
                        ).getTexture(),
                        getItemDyeType(stack, "left", 2),
                        getItemDyeType(stack, "left", 2),
                        getItemLightValue(stack, "left", 2),
                        getItemMainColor(stack, "left", 2),
                        getItemSecondaryColor(stack, "left", 2),
                        WeaversParadiseDyeIconHandler.getOrDefault(
                                getItemDyeType(stack, "right", 1)
                        ).getTexture(),
                        getItemDyeType(stack, "right", 1),
                        getItemDyeType(stack, "right", 1),
                        getItemLightValue(stack, "right", 1),
                        getItemMainColor(stack, "right", 1),
                        getItemSecondaryColor(stack, "right", 1),
                        WeaversParadiseDyeIconHandler.getOrDefault(
                                getItemDyeType(stack, "right", 2)
                        ).getTexture(),
                        getItemDyeType(stack, "right", 2),
                        getItemDyeType(stack, "right", 2),
                        getItemLightValue(stack, "right", 2),
                        getItemMainColor(stack, "right", 2),
                        getItemSecondaryColor(stack, "right", 2)
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

    @Override
    public boolean isValidRepairItem(ItemStack stack, ItemStack repairCandidate) {
        CompoundTag stackdata = stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag();
        CompoundTag candidatedata = repairCandidate.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag();

        int stackquality = stackdata.getInt("quality");
        int candidatequality = candidatedata.getInt("quality");

        return repairCandidate.is(WeaversParadiseItems.COTTON_CLOTH) && candidatequality >= stackquality;
    }

    @Override
    public boolean supportsEnchantment(ItemStack stack, Holder<Enchantment> enchantment) {
        return enchantment.is(Enchantments.UNBREAKING) || enchantment.is(Enchantments.VANISHING_CURSE) || enchantment.is(Enchantments.MENDING) || enchantment.is(Enchantments.BINDING_CURSE);
    }

    @Override
    public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
        return EnchantmentHelper.getEnchantmentsForCrafting(book).keySet().stream().anyMatch(holder -> {
            if (holder.is(Enchantments.MENDING) || holder.is(Enchantments.UNBREAKING) || holder.is(Enchantments.VANISHING_CURSE) || holder.is(Enchantments.BINDING_CURSE)) {
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

        return EnchantmentHelper.getEnchantmentsForCrafting(stack).keySet().stream().anyMatch(holder -> {
            if (holder.is(Enchantments.BINDING_CURSE)) {
                return true;
            }

            return false;
        });
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
