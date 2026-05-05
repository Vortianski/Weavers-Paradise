package xox.labvorty.weaversparadise.items.clothing;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;
import xox.labvorty.weaversparadise.data.tooltip_components.ClothingTooltipComponent;
import xox.labvorty.weaversparadise.data.tooltip_components.data.DyeIconHandler;
import xox.labvorty.weaversparadise.data.tooltip_components.helper.DyeTooltipEntry;
import xox.labvorty.weaversparadise.data.tooltip_components.helper.QualityTooltipData;
import xox.labvorty.weaversparadise.init.WeaversParadiseEnchantments;
import xox.labvorty.weaversparadise.init.WeaversParadiseItems;
import xox.labvorty.weaversparadise.renderers.bewlr.HandWarmersRenderer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class HandWarmersWoolItem extends Item implements ICurioItem, HandWarmersInterface {
    public HandWarmersWoolItem() {
        super(
                new Properties()
                        .stacksTo(1)
                        .defaultDurability(1)
        );
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                return new HandWarmersRenderer(
                        Minecraft.getInstance().getBlockEntityRenderDispatcher(),
                        Minecraft.getInstance().getEntityModels()
                );
            }
        });

        super.initializeClient(consumer);
    }

    @Override
    public @NotNull ItemStack getDefaultInstance() {
        ItemStack itemStack = super.getDefaultInstance();

        CompoundTag compoundTag = new CompoundTag() {{
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
        }};

        itemStack.setTag(compoundTag);

        return itemStack;
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

    @Override
    public int getMaxDamage(ItemStack stack) {
        int quality = stack.getOrCreateTag().getInt("quality");

        return 100 + (25 * quality);
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
        CompoundTag stackData = stack.getOrCreateTag();
        CompoundTag candidateData = repairCandidate.getOrCreateTag();

        int stackQuality = stackData.getInt("quality");
        int candidateQuality = candidateData.getInt("quality");

        return repairCandidate.is(WeaversParadiseItems.WOOL_CLOTH.get()) && candidateQuality >= stackQuality;
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment holder) {
        return holder == Enchantments.MENDING || holder == Enchantments.UNBREAKING || holder == Enchantments.VANISHING_CURSE || holder == Enchantments.BINDING_CURSE || holder == WeaversParadiseEnchantments.SOUND_MUFFLER.get();
    }

    @Override
    public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
        return book.getAllEnchantments().keySet().stream().allMatch(
                holder -> {
                    return holder == Enchantments.MENDING || holder == Enchantments.UNBREAKING || holder == Enchantments.VANISHING_CURSE || holder == Enchantments.BINDING_CURSE || holder == WeaversParadiseEnchantments.SOUND_MUFFLER.get();
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
