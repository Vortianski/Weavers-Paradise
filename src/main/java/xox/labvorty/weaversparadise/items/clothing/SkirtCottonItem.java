package xox.labvorty.weaversparadise.items.clothing;

import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import org.jetbrains.annotations.NotNull;
import xox.labvorty.weaversparadise.init.WeaversParadiseItems;
import xox.labvorty.weaversparadise.items.clothing.defined.DoubleSidedClothingArmorItem;

import java.util.List;

public class SkirtCottonItem extends DoubleSidedClothingArmorItem {
    public SkirtCottonItem() {
        super(
                new Item.Properties()
                        .stacksTo(1)
                        .rarity(Rarity.COMMON)
                        .durability(1)
                        .component(DataComponents.CUSTOM_DATA, CustomData.of(createDefault(10))),
                EquipmentSlot.LEGS
        );
    }

    @Override
    public int getMaxDamage(@NotNull ItemStack itemStack) {
        return 100;
    }

    @Override
    public boolean isValidRepairItem(@NotNull ItemStack itemStack, ItemStack repairCandidate) {
        return repairCandidate.is(WeaversParadiseItems.COTTON_CLOTH) || repairCandidate.is(Items.LEATHER);
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
