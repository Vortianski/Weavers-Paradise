package xox.labvorty.weaversparadise.items.armor;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import xox.labvorty.weaversparadise.models.GriffithArmorModel;

import java.util.Collections;
import java.util.Map;
import java.util.function.Consumer;

public abstract class GriffithArmorItem extends ArmorItem {
    public GriffithArmorItem(Type type, Properties properties) {
        super(
                new ArmorMaterial() {
                    @Override
                    public int getDurabilityForType(Type type) {
                        return new int[]{429, 495, 528, 363}[type.getSlot().getIndex()];
                    }

                    @Override
                    public int getDefenseForType(Type type) {
                        return new int[]{2, 5, 6, 2}[type.getSlot().getIndex()];
                    }

                    @Override
                    public int getEnchantmentValue() {
                        return 9;
                    }

                    @Override
                    public SoundEvent getEquipSound() {
                        return SoundEvents.ARMOR_EQUIP_IRON;
                    }

                    @Override
                    public Ingredient getRepairIngredient() {
                        return null;
                    }

                    @Override
                    public String getName() {
                        return "weaversparadise:griffith_armor";
                    }

                    @Override
                    public float getToughness() {
                        return 0;
                    }

                    @Override
                    public float getKnockbackResistance() {
                        return 0;
                    }
                },
                type,
                properties
        );
    }

    public static class Helmet extends GriffithArmorItem {
        public Helmet() {
            super(Type.HELMET, new Properties());
        }

        @Override
        public void initializeClient(Consumer<IClientItemExtensions> consumer) {
            super.initializeClient(consumer);

            consumer.accept(
                    new IClientItemExtensions() {
                        @Override
                        public HumanoidModel getHumanoidArmorModel(LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel defaultModel) {
                            HumanoidModel armorModel = new HumanoidModel(new ModelPart(Collections.emptyList(),
                                    Map.of("head", new GriffithArmorModel(Minecraft.getInstance().getEntityModels().bakeLayer(GriffithArmorModel.LAYER_LOCATION)).Head, "hat", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "body",
                                            new ModelPart(Collections.emptyList(), Collections.emptyMap()), "right_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "left_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                                            "right_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "left_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap()))));
                            armorModel.crouching = living.isShiftKeyDown();
                            armorModel.riding = defaultModel.riding;
                            armorModel.young = living.isBaby();
                            return armorModel;
                        }
                    }
            );
        }
    }

    public static class Chestplate extends GriffithArmorItem {
        public Chestplate() {
            super(Type.CHESTPLATE, new Properties());
        }

        @Override
        public void initializeClient(Consumer<IClientItemExtensions> consumer) {
            super.initializeClient(consumer);

            consumer.accept(
                    new IClientItemExtensions() {
                        @Override
                        public HumanoidModel getHumanoidArmorModel(LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel defaultModel) {
                            HumanoidModel armorModel = new HumanoidModel(new ModelPart(Collections.emptyList(), Map.of("body", new GriffithArmorModel(Minecraft.getInstance().getEntityModels().bakeLayer(GriffithArmorModel.LAYER_LOCATION)).Body, "left_arm",
                                    new GriffithArmorModel(Minecraft.getInstance().getEntityModels().bakeLayer(GriffithArmorModel.LAYER_LOCATION)).LeftArm, "right_arm",
                                    new GriffithArmorModel(Minecraft.getInstance().getEntityModels().bakeLayer(GriffithArmorModel.LAYER_LOCATION)).RightArm, "head", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "hat",
                                    new ModelPart(Collections.emptyList(), Collections.emptyMap()), "right_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "left_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap()))));
                            armorModel.crouching = living.isShiftKeyDown();
                            armorModel.riding = defaultModel.riding;
                            armorModel.young = living.isBaby();
                            return armorModel;
                        }
                    }
            );
        }
    }

    public static class Leggings extends GriffithArmorItem {
        public Leggings() {
            super(Type.LEGGINGS, new Properties());
        }

        @Override
        public void initializeClient(Consumer<IClientItemExtensions> consumer) {
            super.initializeClient(consumer);

            consumer.accept(
                    new IClientItemExtensions() {
                        @Override
                        public HumanoidModel getHumanoidArmorModel(LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel defaultModel) {
                            HumanoidModel armorModel = new HumanoidModel(new ModelPart(Collections.emptyList(),
                                    Map.of("left_leg", new GriffithArmorModel(Minecraft.getInstance().getEntityModels().bakeLayer(GriffithArmorModel.LAYER_LOCATION)).LeftLeg, "right_leg",
                                            new GriffithArmorModel(Minecraft.getInstance().getEntityModels().bakeLayer(GriffithArmorModel.LAYER_LOCATION)).RightLeg, "head", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "hat",
                                            new ModelPart(Collections.emptyList(), Collections.emptyMap()), "body", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "right_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                                            "left_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()))));
                            armorModel.crouching = living.isShiftKeyDown();
                            armorModel.riding = defaultModel.riding;
                            armorModel.young = living.isBaby();
                            return armorModel;
                        }
                    }
            );
        }
    }

    public static class Boots extends GriffithArmorItem {
        public Boots() {
            super(Type.BOOTS, new Properties());
        }

        @Override
        public void initializeClient(Consumer<IClientItemExtensions> consumer) {
            super.initializeClient(consumer);

            consumer.accept(
                    new IClientItemExtensions() {
                        @Override
                        public HumanoidModel getHumanoidArmorModel(LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel defaultModel) {
                            HumanoidModel armorModel = new HumanoidModel(new ModelPart(Collections.emptyList(),
                                    Map.of("left_leg", new GriffithArmorModel(Minecraft.getInstance().getEntityModels().bakeLayer(GriffithArmorModel.LAYER_LOCATION)).LeftBoot, "right_leg",
                                            new GriffithArmorModel(Minecraft.getInstance().getEntityModels().bakeLayer(GriffithArmorModel.LAYER_LOCATION)).RightBoot, "head", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "hat",
                                            new ModelPart(Collections.emptyList(), Collections.emptyMap()), "body", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "right_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                                            "left_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()))));
                            armorModel.crouching = living.isShiftKeyDown();
                            armorModel.riding = defaultModel.riding;
                            armorModel.young = living.isBaby();
                            return armorModel;
                        }
                    }
            );
        }
    }
}
