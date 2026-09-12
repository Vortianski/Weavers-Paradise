package xox.labvorty.weaversparadise.items.clothing;

import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import org.jetbrains.annotations.NotNull;
import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.Trinket;
import dev.emi.trinkets.api.TrinketsApi;
import dev.emi.trinkets.api.SlotReference;
import xox.labvorty.weaversparadise.configs.CommonConfig;
import xox.labvorty.weaversparadise.init.WeaversParadiseEnchantments;
import xox.labvorty.weaversparadise.init.WeaversParadiseItems;
import xox.labvorty.weaversparadise.items.clothing.defined.DoubleSidedClothingItem;

import java.util.List;

public class ChokerItem extends DoubleSidedClothingItem {
    public ChokerItem() {
        super(
                new Properties()
                        .stacksTo(1)
                        .rarity(Rarity.COMMON)
                        .durability(1)
                        .component(DataComponents.CUSTOM_DATA, CustomData.of(createDefault(10)))
        );
        TrinketsApi.registerTrinket(this, this);
    }

    @Override
    public Multimap<Holder<Attribute>, AttributeModifier> getModifiers(ItemStack stack, SlotReference slotReference, LivingEntity entity, ResourceLocation slotId) {
        Multimap<Holder<Attribute>, AttributeModifier> modifiers = LinkedHashMultimap.create();

        // SlotAttribute line removed: choker slot is now a static json slot

        LivingEntity livingEntity = entity;
        int durabilityLeft = stack.getMaxDamage() - stack.getDamageValue();

        if (livingEntity != null && durabilityLeft > 1 && CommonConfig.ITEM_SPECIAL_ABILITIES.get()) {
            int level = EnchantmentHelper.getItemEnchantmentLevel(livingEntity.level().registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(WeaversParadiseEnchantments.BREATHING_EXERCISE), stack);

            modifiers.put(
                    Attributes.OXYGEN_BONUS,
                    new AttributeModifier(
                            ResourceLocation.fromNamespaceAndPath("weaversparadise", "choker"),
                            (level / 6.0f),
                            AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
                    )
            );
        }

        return modifiers;
    }

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
                Enchantments.BINDING_CURSE,
                WeaversParadiseEnchantments.BREATHING_EXERCISE
        );
    }
}
