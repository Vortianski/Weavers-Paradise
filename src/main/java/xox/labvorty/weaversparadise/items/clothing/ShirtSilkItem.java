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
import net.minecraft.world.item.enchantment.Enchantments;
import top.theillusivec4.curios.api.SlotContext;
import xox.labvorty.weaversparadise.configs.CommonConfig;
import xox.labvorty.weaversparadise.init.WeaversParadiseEnchantments;
import xox.labvorty.weaversparadise.init.WeaversParadiseItems;
import xox.labvorty.weaversparadise.items.clothing.defined.ShirtInterface;
import xox.labvorty.weaversparadise.items.clothing.defined.SingleSidedClothingArmorItem;

import java.util.List;

public class ShirtSilkItem extends SingleSidedClothingArmorItem implements ShirtInterface {
    public ShirtSilkItem() {
        super(
                new Item.Properties()
                        .stacksTo(1)
                        .rarity(Rarity.COMMON)
                        .durability(1)
                        .component(DataComponents.CUSTOM_DATA, CustomData.of(createDefault(true))),
                EquipmentSlot.CHEST
        );
    }

    @Override
    public int getMaxDamage(ItemStack stack) {
        int quality = stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getInt("quality");

        return 112 + (8 * quality);
    }

    @Override
    public boolean getFlag(ItemStack itemStack) {
        return itemStack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getBoolean("is_open");
    }

    @Override
    public Multimap<Holder<Attribute>, AttributeModifier> getAttributeModifiers(SlotContext slotContext, ResourceLocation id, ItemStack stack) {
        Multimap<Holder<Attribute>, AttributeModifier> modifiers = LinkedHashMultimap.create();
        LivingEntity livingEntity = slotContext.entity();
        int durabilityLeft = stack.getMaxDamage() - stack.getDamageValue();

        if (livingEntity != null && durabilityLeft > 1 && CommonConfig.ITEM_SPECIAL_ABILITIES.get()) {
            int level = stack.getEnchantmentLevel(livingEntity.level().registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(WeaversParadiseEnchantments.GRACEFUL));

            modifiers.put(
                    Attributes.WATER_MOVEMENT_EFFICIENCY,
                    new AttributeModifier(
                            ResourceLocation.fromNamespaceAndPath("weaversparadise", "shirt_silk"),
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
        CompoundTag compoundTag = stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag();
        CompoundTag candidateCompound = repairCandidate.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag();

        int quality = compoundTag.getInt("quality");
        int candidateQuality = candidateCompound.getInt("quality");

        return repairCandidate.is(WeaversParadiseItems.SILK_CLOTH) && candidateQuality >= quality;
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
