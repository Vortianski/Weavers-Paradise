package xox.labvorty.weaversparadise.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import xox.labvorty.weaversparadise.WeaversParadiseMod;

public class WeaversParadiseEnchantments {
    public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(Registries.ENCHANTMENT, WeaversParadiseMod.MOD_ID);
    public static final RegistryObject<Enchantment> WATER_STRIDER = ENCHANTMENTS.register(
            "water_strider",
            () -> new Enchantment(Enchantment.Rarity.UNCOMMON, EnchantmentCategory.ARMOR, new EquipmentSlot[]{}) {
                @Override
                public int getMaxLevel() {
                    return 1;
                }

                @Override
                public int getMinCost(int pLevel) {
                    return 18;
                }

                @Override
                public int getMaxCost(int pLevel) {
                    return 32;
                }

                @Override
                public String getDescriptionId() {
                    return "enchantment.weaversparadise.water_strider";
                }

                @Override
                public boolean canEnchant(ItemStack stack) {
                    return stack.is(WeaversParadiseItems.THIGH_HIGHS_SILK.get());
                }

                @Override
                public boolean canApplyAtEnchantingTable(ItemStack stack) {
                    return stack.is(WeaversParadiseItems.THIGH_HIGHS_SILK.get());
                }
            }
    );
    public static final RegistryObject<Enchantment> BREATHING_EXERCISE = ENCHANTMENTS.register(
            "breathing_exercise",
            () -> new Enchantment(Enchantment.Rarity.UNCOMMON, EnchantmentCategory.ARMOR, new EquipmentSlot[]{}) {
                @Override
                public int getMaxLevel() {
                    return 3;
                }

                @Override
                public int getMaxCost(int level) {
                    return 24 + (10 * level);
                }

                @Override
                public int getMinCost(int level) {
                    return 10 + (10 * level);
                }

                @Override
                public String getDescriptionId() {
                    return "enchantment.weaversparadise.breathing_exercise";
                }

                @Override
                public boolean canEnchant(ItemStack stack) {
                    return stack.is(WeaversParadiseItems.CHOKER.get());
                }

                @Override
                public boolean canApplyAtEnchantingTable(ItemStack stack) {
                    return stack.is(WeaversParadiseItems.CHOKER.get());
                }
            }
    );
    public static final RegistryObject<Enchantment> VAMPIRISM = ENCHANTMENTS.register(
            "vampirism",
            () -> new Enchantment(Enchantment.Rarity.UNCOMMON, EnchantmentCategory.ARMOR, new EquipmentSlot[]{}) {
                @Override
                public int getMaxLevel() {
                    return 5;
                }

                @Override
                public int getMinCost(int level) {
                    return 15 + (12 * level);
                }

                @Override
                public int getMaxCost(int level) {
                    return 25 + (12 * level);
                }

                @Override
                public String getDescriptionId() {
                    return "enchantment.weaversparadise.vampirism";
                }

                @Override
                public boolean canEnchant(ItemStack stack) {
                    return stack.is(WeaversParadiseItems.HAND_WARMERS_COTTON.get());
                }

                @Override
                public boolean canApplyAtEnchantingTable(ItemStack stack) {
                    return stack.is(WeaversParadiseItems.HAND_WARMERS_COTTON.get());
                }
            }
    );
    public static final RegistryObject<Enchantment> GRACEFUL = ENCHANTMENTS.register(
            "graceful",
            () -> new Enchantment(Enchantment.Rarity.UNCOMMON, EnchantmentCategory.ARMOR, new EquipmentSlot[]{}) {
                @Override
                public int getMaxLevel() {
                    return 5;
                }

                @Override
                public int getMinCost(int level) {
                    return 8 + (10 * level);
                }

                @Override
                public int getMaxCost(int level) {
                    return 18 + (10 * level);
                }

                @Override
                public String getDescriptionId() {
                    return "enchantment.weaversparadise.graceful";
                }

                @Override
                public boolean canEnchant(ItemStack stack) {
                    return stack.is(WeaversParadiseItems.HAND_WARMERS_SILK.get()) || stack.is(WeaversParadiseItems.SHIRT_SILK.get()) || stack.is(WeaversParadiseItems.PANTS_SILK.get());
                }

                @Override
                public boolean canApplyAtEnchantingTable(ItemStack stack) {
                    return stack.is(WeaversParadiseItems.HAND_WARMERS_SILK.get()) || stack.is(WeaversParadiseItems.SHIRT_SILK.get()) || stack.is(WeaversParadiseItems.PANTS_SILK.get());
                }
            }
    );
    public static final RegistryObject<Enchantment> TOUGH_AS_NAILS = ENCHANTMENTS.register(
            "tough_as_nails",
            () -> new Enchantment(Enchantment.Rarity.UNCOMMON, EnchantmentCategory.ARMOR, new EquipmentSlot[]{}) {
                @Override
                public int getMaxLevel() {
                    return 5;
                }

                @Override
                public int getMinCost(int level) {
                    return 12 + (11 * level);
                }

                @Override
                public int getMaxCost(int level) {
                    return 22 + (11 * level);
                }

                @Override
                public String getDescriptionId() {
                    return "enchantment.weaversparadise.tough_as_nails";
                }

                @Override
                public boolean canEnchant(ItemStack stack) {
                    return stack.is(WeaversParadiseItems.PANTS_JEANS.get());
                }

                @Override
                public boolean canApplyAtEnchantingTable(ItemStack stack) {
                    return stack.is(WeaversParadiseItems.PANTS_JEANS.get());
                }
            }
    );
    public static final RegistryObject<Enchantment> SOFT_AND_COZY = ENCHANTMENTS.register(
            "soft_and_cozy",
            () -> new Enchantment(Enchantment.Rarity.UNCOMMON, EnchantmentCategory.ARMOR, new EquipmentSlot[]{}) {
                @Override
                public int getMaxLevel() {
                    return 5;
                }

                @Override
                public int getMinCost(int level) {
                    return 4 + (8 * level);
                }

                @Override
                public int getMaxCost(int level) {
                    return 14 + (8 * level);
                }

                @Override
                public String getDescriptionId() {
                    return "enchantment.weaversparadise.soft_and_cozy";
                }

                @Override
                public boolean canEnchant(ItemStack stack) {
                    return stack.is(WeaversParadiseItems.PANTS_COTTON.get()) || stack.is(WeaversParadiseItems.SHIRT_COTTON.get()) || stack.is(WeaversParadiseItems.THIGH_HIGHS_COTTON.get());
                }

                @Override
                public boolean canApplyAtEnchantingTable(ItemStack stack) {
                    return stack.is(WeaversParadiseItems.PANTS_COTTON.get()) || stack.is(WeaversParadiseItems.SHIRT_COTTON.get()) || stack.is(WeaversParadiseItems.THIGH_HIGHS_COTTON.get());
                }
            }
    );
    public static final RegistryObject<Enchantment> SOUND_MUFFLER = ENCHANTMENTS.register(
            "sound_muffler",
            () -> new Enchantment(Enchantment.Rarity.UNCOMMON, EnchantmentCategory.ARMOR, new EquipmentSlot[]{}) {
                @Override
                public int getMaxLevel() {
                    return 1;
                }

                @Override
                public int getMinCost(int level) {
                    return 20;
                }

                @Override
                public int getMaxCost(int level) {
                    return 35;
                }

                @Override
                public String getDescriptionId() {
                    return "enchantment.weaversparadise.sound_muffler";
                }

                @Override
                public boolean canEnchant(ItemStack stack) {
                    return stack.is(WeaversParadiseItems.THIGH_HIGHS_WOOL.get()) || stack.is(WeaversParadiseItems.HAND_WARMERS_WOOL.get());
                }

                @Override
                public boolean canApplyAtEnchantingTable(ItemStack stack) {
                    return stack.is(WeaversParadiseItems.THIGH_HIGHS_WOOL.get()) || stack.is(WeaversParadiseItems.HAND_WARMERS_WOOL.get());
                }
            }
    );
}
