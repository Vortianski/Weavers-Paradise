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
import xox.labvorty.weaversparadise.renderers.bewlr.ShirtRenderer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class ShirtCottonItem extends Item implements ICurioItem, ShirtInterface {
    public ShirtCottonItem() {
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
                return new ShirtRenderer(
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

    @Override
    public int getMaxDamage(ItemStack stack) {
        int quality = stack.getOrCreateTag().getInt("quality");

        return 100 + (20 * quality);
    }

    public int getItemMainColor(ItemStack stack, int part) {
        int color = 0;

        CompoundTag tag = stack.getOrCreateTag();

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

        CompoundTag tag = stack.getOrCreateTag();

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

        CompoundTag tag = stack.getOrCreateTag();

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

        CompoundTag tag = stack.getOrCreateTag();

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

        CompoundTag tag = stack.getOrCreateTag();

        type = tag.getString("stensilType");

        return type;
    }

    @Override
    public boolean isValidRepairItem(ItemStack stack, ItemStack repairCandidate) {
        CompoundTag stackData = stack.getOrCreateTag();
        CompoundTag candidateData = repairCandidate.getOrCreateTag();

        int stackQuality = stackData.getInt("quality");
        int candidateQuality = candidateData.getInt("quality");

        return repairCandidate.is(WeaversParadiseItems.COTTON_CLOTH.get()) && candidateQuality >= stackQuality;
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment holder) {
        return holder == Enchantments.MENDING || holder == Enchantments.UNBREAKING || holder == Enchantments.VANISHING_CURSE || holder == Enchantments.BINDING_CURSE || holder == WeaversParadiseEnchantments.SOFT_AND_COZY.get();
    }

    @Override
    public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
        return book.getAllEnchantments().keySet().stream().allMatch(
                holder -> {
                    return holder == Enchantments.MENDING || holder == Enchantments.UNBREAKING || holder == Enchantments.VANISHING_CURSE || holder == Enchantments.BINDING_CURSE || holder == WeaversParadiseEnchantments.SOFT_AND_COZY.get();
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
