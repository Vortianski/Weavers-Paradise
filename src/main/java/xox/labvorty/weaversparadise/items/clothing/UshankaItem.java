package xox.labvorty.weaversparadise.items.clothing;

import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.SlotAttribute;
import top.theillusivec4.curios.api.SlotContext;
import xox.labvorty.weaversparadise.configs.CommonConfig;
import xox.labvorty.weaversparadise.init.WeaversParadiseEnchantments;
import xox.labvorty.weaversparadise.init.WeaversParadiseItems;
import xox.labvorty.weaversparadise.items.clothing.defined.DoubleSidedClothingArmorItem;
import xox.labvorty.weaversparadise.items.clothing.defined.ShirtInterface;
import xox.labvorty.weaversparadise.items.clothing.defined.SingleSidedClothingArmorItem;

import java.util.List;

public class UshankaItem extends DoubleSidedClothingArmorItem {
    public UshankaItem() {
        super(
                new Properties()
                        .stacksTo(1)
                        .rarity(Rarity.COMMON)
                        .durability(1)
                        .component(DataComponents.CUSTOM_DATA, CustomData.of(createDefault())),
                EquipmentSlot.HEAD
        );
    }

    @Override
    public int getMaxDamage(@NotNull ItemStack itemStack) {
        int quality = itemStack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getInt("quality");

        return 48 + (12 * quality);
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
    protected List<ResourceKey<Enchantment>> getSupportedEnchantments() {
        return List.of(
                Enchantments.UNBREAKING,
                Enchantments.VANISHING_CURSE,
                Enchantments.MENDING,
                Enchantments.BINDING_CURSE
        );
    }
}
