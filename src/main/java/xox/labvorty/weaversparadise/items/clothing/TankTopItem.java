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
import xox.labvorty.weaversparadise.init.WeaversParadiseItems;
import xox.labvorty.weaversparadise.items.clothing.defined.SingleSidedClothingArmorItem;
import xox.labvorty.weaversparadise.items.clothing.defined.TopsInterface;

import java.util.List;

public class TankTopItem extends SingleSidedClothingArmorItem implements TopsInterface {
    public TankTopItem() {
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

        return 64 + (8 * quality);
    }

    @Override
    public boolean isValidRepairItem(ItemStack stack, ItemStack repairCandidate) {
        CompoundTag compoundTag = stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag();
        CompoundTag candidateCompound = repairCandidate.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag();

        int quality = compoundTag.getInt("quality");
        int candidateQuality = candidateCompound.getInt("quality");

        return repairCandidate.is(WeaversParadiseItems.COTTON_CLOTH) && candidateQuality >= quality;
    }

    @Override
    protected List<ResourceKey<Enchantment>> getSupportedEnchantments() {
        return List.of(
                Enchantments.UNBREAKING,
                Enchantments.VANISHING_CURSE,
                Enchantments.MENDING,
                Enchantments.BINDING_CURSE
        );
    }
}
