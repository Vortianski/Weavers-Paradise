package xox.labvorty.weaversparadise.items.clothing;

import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import xox.labvorty.weaversparadise.init.WeaversParadiseEnchantments;
import xox.labvorty.weaversparadise.init.WeaversParadiseItems;
import xox.labvorty.weaversparadise.items.clothing.defined.DoubleSidedClothingArmorItem;
import xox.labvorty.weaversparadise.items.clothing.defined.HandWarmersInterface;

import java.util.List;

public class HandWarmersCottonItem extends DoubleSidedClothingArmorItem implements HandWarmersInterface {
    public HandWarmersCottonItem() {
        super(
                new Item.Properties()
                        .stacksTo(1)
                        .rarity(Rarity.COMMON)
                        .durability(1)
                        .component(DataComponents.CUSTOM_DATA, CustomData.of(createDefault())),
                EquipmentSlot.CHEST
        );
    }

    public int getMaxDamage(ItemStack stack) {
        int quality = stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getInt("quality");

        return 40 + (10 * quality);
    }

    @Override
    public boolean isValidRepairItem(ItemStack stack, ItemStack repairCandidate) {
        CompoundTag itemStackData = stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag();
        CompoundTag candidate = repairCandidate.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag();

        int itemStackQuality = itemStackData.getInt("quality");
        int candidateQuality = candidate.getInt("quality");

        return repairCandidate.is(WeaversParadiseItems.COTTON_CLOTH) && candidateQuality >= itemStackQuality;
    }

    @Override
    protected List<ResourceKey<Enchantment>> getSupportedEnchantments() {
        return List.of(
                Enchantments.UNBREAKING,
                Enchantments.VANISHING_CURSE,
                Enchantments.MENDING,
                Enchantments.BINDING_CURSE,
                WeaversParadiseEnchantments.VAMPIRISM
        );
    }
}
