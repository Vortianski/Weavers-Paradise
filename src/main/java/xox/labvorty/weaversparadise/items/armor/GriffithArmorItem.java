package xox.labvorty.weaversparadise.items.armor;

import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.registries.RegisterEvent;

import java.util.EnumMap;
import java.util.List;

@EventBusSubscriber
public abstract class GriffithArmorItem extends ArmorItem {
    public static Holder<ArmorMaterial> ARMOR_MATERIAL = null;
    @SubscribeEvent
    public static void registerArmorMaterial(RegisterEvent event) {
        event.register(Registries.ARMOR_MATERIAL, registerHelper -> {
            ArmorMaterial armorMaterial = new ArmorMaterial(Util.make(new EnumMap<>(Type.class), map -> {
                map.put(Type.BOOTS, 2);
                map.put(Type.LEGGINGS, 5);
                map.put(Type.CHESTPLATE, 6);
                map.put(Type.HELMET, 2);
                map.put(Type.BODY, 6);
            }), 9, BuiltInRegistries.SOUND_EVENT.wrapAsHolder(SoundEvents.EMPTY), () -> Ingredient.of(), List.of(new ArmorMaterial.Layer(ResourceLocation.parse("weaversparadise:armor"))), 0f, 0f);
            registerHelper.register(ResourceLocation.parse("weaversparadise:griffith_armor"), armorMaterial);
            ARMOR_MATERIAL = BuiltInRegistries.ARMOR_MATERIAL.wrapAsHolder(armorMaterial);
        });
    }

    public GriffithArmorItem(Type type, Properties properties) {
        super(ARMOR_MATERIAL, type, properties);
    }

    public static class Helmet extends GriffithArmorItem {
        public Helmet() {
            super(Type.HELMET, new Properties().durability(Type.HELMET.getDurability(26)));
        }

        @Override
        public ResourceLocation getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, ArmorMaterial.Layer layer, boolean innerModel) {
            return ResourceLocation.parse("weaversparadise:textures/clothing/griffith_armor_texture.png");
        }
    }

    public static class Chestplate extends GriffithArmorItem {
        public Chestplate() {
            super(Type.CHESTPLATE, new Properties().durability(Type.CHESTPLATE.getDurability(26)));
        }

        @Override
        public ResourceLocation getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, ArmorMaterial.Layer layer, boolean innerModel) {
            return ResourceLocation.parse("weaversparadise:textures/clothing/griffith_armor_texture.png");
        }
    }

    public static class Leggings extends GriffithArmorItem {
        public Leggings() {
            super(Type.LEGGINGS, new Properties().durability(Type.LEGGINGS.getDurability(26)));
        }

        @Override
        public ResourceLocation getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, ArmorMaterial.Layer layer, boolean innerModel) {
            return ResourceLocation.parse("weaversparadise:textures/clothing/griffith_armor_texture.png");
        }
    }

    public static class Boots extends GriffithArmorItem {
        public Boots() {
            super(Type.BOOTS, new Properties().durability(Type.BOOTS.getDurability(26)));
        }

        @Override
        public ResourceLocation getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, ArmorMaterial.Layer layer, boolean innerModel) {
            return ResourceLocation.parse("weaversparadise:textures/clothing/griffith_armor_texture.png");
        }
    }
}
