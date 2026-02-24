package xox.labvorty.weaversparadise.items;

import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

public class PlateItem extends Item implements ICurioItem {
    public PlateItem() {
        super(
                new Properties()
                        .stacksTo(1)
                        .rarity(Rarity.COMMON)
                        .durability(1)
                        .component(DataComponents.CUSTOM_DATA, CustomData.of(new CompoundTag() {{
                            putInt("color", 255 << 24 | 255 << 16 | 255 << 8 | 255);
                            putString("metalType", "minecraft:iron_ingot");
                            putInt("damage", 100);
                        }}))
        );
    }

    @Override
    public int getMaxDamage(ItemStack stack) {
        return stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getInt("damage");
    }

    @Override
    public boolean isValidRepairItem(ItemStack stack, ItemStack repairCandidate) {
        CompoundTag compoundTag = stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag();
        Item item = BuiltInRegistries.ITEM.get(ResourceLocation.parse(compoundTag.getString("metalType")));
        Item pushedCandidate = Items.BEDROCK;
        if (item != Items.AIR && item != Items.STONE) {
            pushedCandidate = item;
        }

        return repairCandidate.is(pushedCandidate);
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
