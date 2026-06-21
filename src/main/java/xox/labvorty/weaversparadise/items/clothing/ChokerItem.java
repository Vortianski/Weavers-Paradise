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
import net.minecraft.world.item.enchantment.Enchantments;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.SlotAttribute;
import top.theillusivec4.curios.api.SlotContext;
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
    }

    @Override
    public Multimap<Holder<Attribute>, AttributeModifier> getAttributeModifiers(SlotContext slotContext, ResourceLocation id, ItemStack stack) {
        Multimap<Holder<Attribute>, AttributeModifier> modifiers = LinkedHashMultimap.create();

        modifiers.put(
                SlotAttribute.getOrCreate("choker_trinket"),
                new AttributeModifier(
                        ResourceLocation.fromNamespaceAndPath("weaversparadise", "choker"),
                        1,
                        AttributeModifier.Operation.ADD_VALUE
                )
        );

        LivingEntity livingEntity = slotContext.entity();
        int durabilityLeft = stack.getMaxDamage() - stack.getDamageValue();

        if (livingEntity != null && durabilityLeft > 1 && CommonConfig.ITEM_SPECIAL_ABILITIES.get()) {
            int level = stack.getEnchantmentLevel(livingEntity.level().registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(WeaversParadiseEnchantments.BREATHING_EXERCISE));

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
                Enchantments.BINDING_CURSE,
                WeaversParadiseEnchantments.BREATHING_EXERCISE
        );
    }
}
