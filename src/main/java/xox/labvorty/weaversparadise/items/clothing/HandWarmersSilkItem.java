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
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import dev.emi.trinkets.api.SlotReference;
import xox.labvorty.weaversparadise.configs.CommonConfig;
import xox.labvorty.weaversparadise.init.WeaversParadiseEnchantments;
import xox.labvorty.weaversparadise.init.WeaversParadiseItems;
import xox.labvorty.weaversparadise.items.clothing.defined.DoubleSidedClothingArmorItem;
import xox.labvorty.weaversparadise.items.clothing.defined.HandWarmersInterface;

import java.util.List;

public class HandWarmersSilkItem extends DoubleSidedClothingArmorItem implements HandWarmersInterface {
    public HandWarmersSilkItem() {
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

        return 32 + (8 * quality);
    }

    @Override
    public Multimap<Holder<Attribute>, AttributeModifier> getModifiers(ItemStack stack, SlotReference slotReference, LivingEntity entity, ResourceLocation slotId) {
        Multimap<Holder<Attribute>, AttributeModifier> modifiers = LinkedHashMultimap.create();
        LivingEntity livingEntity = entity;
        int durabilityLeft = stack.getMaxDamage() - stack.getDamageValue();

        if (livingEntity != null && durabilityLeft > 1 && CommonConfig.ITEM_SPECIAL_ABILITIES.get()) {
            int level = EnchantmentHelper.getItemEnchantmentLevel(livingEntity.level().registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(WeaversParadiseEnchantments.GRACEFUL), stack);

            modifiers.put(
                    Attributes.SUBMERGED_MINING_SPEED,
                    new AttributeModifier(
                            ResourceLocation.fromNamespaceAndPath("weaversparadise", "hand_warmers_silk"),
                            (level / 5.0f) * 0.1,
                            AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
                    )
            );
        }

        return modifiers;
    }

    @Override
    protected boolean hideAttributes() {
        return true;
    }

    @Override
    protected List<ResourceKey<Enchantment>> getTooltipEnchantments() {
        return List.of(WeaversParadiseEnchantments.GRACEFUL);
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
                Enchantments.BINDING_CURSE,
                WeaversParadiseEnchantments.GRACEFUL
        );
    }
}
