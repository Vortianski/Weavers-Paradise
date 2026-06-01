package xox.labvorty.weaversparadise.items.clothing;

import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.SlotAttribute;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;
import xox.labvorty.weaversparadise.data.tooltip_components.ClothingTooltipComponent;
import xox.labvorty.weaversparadise.data.tooltip_components.data.DyeIconHandler;
import xox.labvorty.weaversparadise.data.tooltip_components.helper.DyeTooltipEntry;
import xox.labvorty.weaversparadise.data.tooltip_components.helper.QualityTooltipData;
import xox.labvorty.weaversparadise.init.WeaversParadiseEnchantments;
import xox.labvorty.weaversparadise.init.WeaversParadiseItems;
import xox.labvorty.weaversparadise.renderers.bewlr.ChokerRenderer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Consumer;

public class ChokerItem extends Item implements ICurioItem {
    public ChokerItem() {
        super(
                new Properties()
                        .stacksTo(1)
                        .rarity(Rarity.COMMON)
                        .durability(1)
        );
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {
        Multimap<Attribute, AttributeModifier> modifiers = LinkedHashMultimap.create();

        modifiers.put(
                SlotAttribute.getOrCreate("choker_trinket"),
                new AttributeModifier(
                        "weaversparadise:choker",
                        1,
                        AttributeModifier.Operation.ADDITION
                )
        );

        return modifiers;
    }

    @Override
    public @NotNull ItemStack getDefaultInstance() {
        ItemStack itemStack = super.getDefaultInstance();

        CompoundTag compoundTag = new CompoundTag() {{
            putInt("quality", 10);
            putString("dyeTypeLeftOne", "default");
            putString("dyeTypeRightOne", "default");
            putString("dyeTypeLeftTwo", "default");
            putString("dyeTypeRightTwo", "default");
            putString("stensilTypeLeft", "default");
            putString("stensilTypeRight", "default");
            putInt("colorPriRedLeftOne", 160);
            putInt("colorPriGreenLeftOne", 102);
            putInt("colorPriBlueLeftOne", 0);

            putInt("colorPriRedLeftTwo", 160);
            putInt("colorPriGreenLeftTwo", 102);
            putInt("colorPriBlueLeftTwo", 0);

            putInt("colorSecRedLeftOne", 160);
            putInt("colorSecGreenLeftOne", 102);
            putInt("colorSecBlueLeftOne", 0);

            putInt("colorSecRedLeftTwo", 160);
            putInt("colorSecGreenLeftTwo", 102);
            putInt("colorSecBlueLeftTwo", 0);

            putInt("colorPriRedRightOne", 160);
            putInt("colorPriGreenRightOne", 102);
            putInt("colorPriBlueRightOne", 0);

            putInt("colorPriRedRightTwo", 160);
            putInt("colorPriGreenRightTwo", 102);
            putInt("colorPriBlueRightTwo", 0);

            putInt("colorSecRedRightOne", 160);
            putInt("colorSecGreenRightOne", 102);
            putInt("colorSecBlueRightOne", 0);

            putInt("colorSecRedRightTwo", 160);
            putInt("colorSecGreenRightTwo", 102);
            putInt("colorSecBlueRightTwo", 0);

            putInt("lightValueLeftOne", 15);
            putInt("lightValueLeftTwo", 15);
            putInt("lightValueRightOne", 15);
            putInt("lightValueRightTwo", 15);
        }};

        itemStack.setTag(compoundTag);

        return itemStack;
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                return new ChokerRenderer(
                        Minecraft.getInstance().getBlockEntityRenderDispatcher(),
                        Minecraft.getInstance().getEntityModels()
                );
            }
        });

        super.initializeClient(consumer);
    }

    @Override
    public int getMaxDamage(ItemStack stack) {
        return 100;
    }

    @Override
    public Optional<TooltipComponent> getTooltipImage(ItemStack stack) {
        CompoundTag data = stack.getOrCreateTag();

        List<DyeTooltipEntry> entries = new ArrayList<>();
        entries.add(
                new DyeTooltipEntry(
                        DyeIconHandler.getOrDefault(getItemDyeType(stack, "left", 1)).getTexture(),
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
                            DyeIconHandler.getOrDefault(getItemDyeType(stack, "left", 2)).getTexture(),
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
                        DyeIconHandler.getOrDefault(getItemDyeType(stack, "right", 1)).getTexture(),
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
                            DyeIconHandler.getOrDefault(getItemDyeType(stack, "right", 2)).getTexture(),
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

        CompoundTag tag = stack.getOrCreateTag();

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

        CompoundTag tag = stack.getOrCreateTag();

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

        CompoundTag tag = stack.getOrCreateTag();

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

        CompoundTag tag = stack.getOrCreateTag();

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

        CompoundTag tag = stack.getOrCreateTag();

        if (side.equals("left")) {
            type = tag.getString("stensilTypeLeft");
        } else if (side.equals("right")) {
            type = tag.getString("stensilTypeRight");
        }

        return type;
    }

    @Override
    public boolean isValidRepairItem(ItemStack stack, ItemStack repairCandidate) {
        return repairCandidate.is(WeaversParadiseItems.COTTON_CLOTH.get()) || repairCandidate.is(Items.LEATHER);
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment holder) {
        return holder == Enchantments.MENDING || holder == Enchantments.UNBREAKING || holder == Enchantments.VANISHING_CURSE || holder == Enchantments.BINDING_CURSE || holder == WeaversParadiseEnchantments.BREATHING_EXERCISE.get();
    }

    @Override
    public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
        return book.getAllEnchantments().keySet().stream().allMatch(
                holder -> {
                    return holder == Enchantments.MENDING || holder == Enchantments.UNBREAKING || holder == Enchantments.VANISHING_CURSE || holder == Enchantments.BINDING_CURSE || holder == WeaversParadiseEnchantments.BREATHING_EXERCISE.get();
                }
        );
    }

    @Override
    public boolean canUnequip(SlotContext slotContext, ItemStack stack) {
        LivingEntity livingEntity = slotContext.entity();
        if (livingEntity instanceof Player player && player.isCreative()) {
            return true;
        }

        return stack.getAllEnchantments()
                .keySet()
                .stream()
                .noneMatch(holder -> holder == Enchantments.BINDING_CURSE);
    }

    @Override
    public int getEnchantmentValue(ItemStack stack) {
        return 15;
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return true;
    }
}
