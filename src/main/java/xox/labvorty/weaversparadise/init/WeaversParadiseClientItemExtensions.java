package xox.labvorty.weaversparadise.init;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import org.jetbrains.annotations.NotNull;
import xox.labvorty.weaversparadise.model.AstolfoArmorModel;
import xox.labvorty.weaversparadise.model.BridgetClothingModel;
import xox.labvorty.weaversparadise.model.FelixClothingModel;
import xox.labvorty.weaversparadise.renderers.bewlr.*;

import java.util.Collections;
import java.util.Map;

@EventBusSubscriber
public class WeaversParadiseClientItemExtensions {
    @SubscribeEvent
    public static void registerItemExtensions(RegisterClientExtensionsEvent event) {
        event.registerItem(new IClientItemExtensions() {
            @Override
            public HumanoidModel getHumanoidArmorModel(LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel defaultModel) {
                HumanoidModel armorModel = new HumanoidModel(new ModelPart(Collections.emptyList(),
                        Map.of("head", new AstolfoArmorModel(Minecraft.getInstance().getEntityModels().bakeLayer(AstolfoArmorModel.LAYER_LOCATION)).Head, "hat", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "body",
                                new ModelPart(Collections.emptyList(), Collections.emptyMap()), "right_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "left_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                                "right_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "left_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap()))));
                armorModel.crouching = living.isShiftKeyDown();
                armorModel.riding = defaultModel.riding;
                armorModel.young = living.isBaby();
                return armorModel;
            }
        }, WeaversParadiseItems.ASTOLFO_ARMOR_WIG.get());

        event.registerItem(new IClientItemExtensions() {
            @Override
            @OnlyIn(Dist.CLIENT)
            public HumanoidModel getHumanoidArmorModel(LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel defaultModel) {
                HumanoidModel armorModel = new HumanoidModel(new ModelPart(Collections.emptyList(), Map.of("body", new AstolfoArmorModel(Minecraft.getInstance().getEntityModels().bakeLayer(AstolfoArmorModel.LAYER_LOCATION)).Body, "left_arm",
                        new AstolfoArmorModel(Minecraft.getInstance().getEntityModels().bakeLayer(AstolfoArmorModel.LAYER_LOCATION)).LeftArm, "right_arm",
                        new AstolfoArmorModel(Minecraft.getInstance().getEntityModels().bakeLayer(AstolfoArmorModel.LAYER_LOCATION)).RightArm, "head", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "hat",
                        new ModelPart(Collections.emptyList(), Collections.emptyMap()), "right_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "left_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap()))));
                armorModel.crouching = living.isShiftKeyDown();
                armorModel.riding = defaultModel.riding;
                armorModel.young = living.isBaby();
                return armorModel;
            }
        }, WeaversParadiseItems.ASTOLFO_ARMOR_CHESTPLATE.get());

        event.registerItem(new IClientItemExtensions() {
            @Override
            @OnlyIn(Dist.CLIENT)
            public HumanoidModel getHumanoidArmorModel(LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel defaultModel) {
                HumanoidModel armorModel = new HumanoidModel(new ModelPart(Collections.emptyList(),
                        Map.of("left_leg", new AstolfoArmorModel(Minecraft.getInstance().getEntityModels().bakeLayer(AstolfoArmorModel.LAYER_LOCATION)).LeftLeg, "right_leg",
                                new AstolfoArmorModel(Minecraft.getInstance().getEntityModels().bakeLayer(AstolfoArmorModel.LAYER_LOCATION)).RightLeg, "head", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "hat",
                                new ModelPart(Collections.emptyList(), Collections.emptyMap()), "body", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "right_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                                "left_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()))));
                armorModel.crouching = living.isShiftKeyDown();
                armorModel.riding = defaultModel.riding;
                armorModel.young = living.isBaby();
                return armorModel;
            }
        }, WeaversParadiseItems.ASTOLFO_ARMOR_LEGGINGS.get());

        event.registerItem(new IClientItemExtensions() {
            @Override
            @OnlyIn(Dist.CLIENT)
            public HumanoidModel getHumanoidArmorModel(LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel defaultModel) {
                HumanoidModel armorModel = new HumanoidModel(new ModelPart(Collections.emptyList(),
                        Map.of("left_leg", new AstolfoArmorModel(Minecraft.getInstance().getEntityModels().bakeLayer(AstolfoArmorModel.LAYER_LOCATION)).LeftBoot, "right_leg",
                                new AstolfoArmorModel(Minecraft.getInstance().getEntityModels().bakeLayer(AstolfoArmorModel.LAYER_LOCATION)).RightBoot, "head", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "hat",
                                new ModelPart(Collections.emptyList(), Collections.emptyMap()), "body", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "right_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                                "left_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()))));
                armorModel.crouching = living.isShiftKeyDown();
                armorModel.riding = defaultModel.riding;
                armorModel.young = living.isBaby();
                return armorModel;
            }
        }, WeaversParadiseItems.ASTOLFO_ARMOR_BOOTS.get());

        event.registerItem(new IClientItemExtensions() {
            @Override
            public HumanoidModel getHumanoidArmorModel(LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel defaultModel) {
                HumanoidModel armorModel = new HumanoidModel(new ModelPart(Collections.emptyList(),
                        Map.of("head", new BridgetClothingModel(Minecraft.getInstance().getEntityModels().bakeLayer(BridgetClothingModel.LAYER_LOCATION)).Head, "hat", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "body",
                                new ModelPart(Collections.emptyList(), Collections.emptyMap()), "right_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "left_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                                "right_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "left_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap()))));
                armorModel.crouching = living.isShiftKeyDown();
                armorModel.riding = defaultModel.riding;
                armorModel.young = living.isBaby();
                return armorModel;
            }
        }, WeaversParadiseItems.BRIDGET_ARMOR_HAT.get());

        event.registerItem(new IClientItemExtensions() {
            @Override
            @OnlyIn(Dist.CLIENT)
            public HumanoidModel getHumanoidArmorModel(LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel defaultModel) {
                HumanoidModel armorModel = new HumanoidModel(new ModelPart(Collections.emptyList(), Map.of("body", new BridgetClothingModel(Minecraft.getInstance().getEntityModels().bakeLayer(BridgetClothingModel.LAYER_LOCATION)).Body, "left_arm",
                        new BridgetClothingModel(Minecraft.getInstance().getEntityModels().bakeLayer(BridgetClothingModel.LAYER_LOCATION)).LeftArm, "right_arm",
                        new BridgetClothingModel(Minecraft.getInstance().getEntityModels().bakeLayer(BridgetClothingModel.LAYER_LOCATION)).RightArm, "head", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "hat",
                        new ModelPart(Collections.emptyList(), Collections.emptyMap()), "right_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "left_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap()))));
                armorModel.crouching = living.isShiftKeyDown();
                armorModel.riding = defaultModel.riding;
                armorModel.young = living.isBaby();
                return armorModel;
            }
        }, WeaversParadiseItems.BRIDGET_ARMOR_JACKET.get());

        event.registerItem(new IClientItemExtensions() {
            @Override
            @OnlyIn(Dist.CLIENT)
            public HumanoidModel getHumanoidArmorModel(LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel defaultModel) {
                HumanoidModel armorModel = new HumanoidModel(new ModelPart(Collections.emptyList(),
                        Map.of("left_leg", new BridgetClothingModel(Minecraft.getInstance().getEntityModels().bakeLayer(BridgetClothingModel.LAYER_LOCATION)).LeftLegPants, "right_leg",
                                new BridgetClothingModel(Minecraft.getInstance().getEntityModels().bakeLayer(BridgetClothingModel.LAYER_LOCATION)).RightLegPants, "head", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "hat",
                                new ModelPart(Collections.emptyList(), Collections.emptyMap()), "body", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "right_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                                "left_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()))));
                armorModel.crouching = living.isShiftKeyDown();
                armorModel.riding = defaultModel.riding;
                armorModel.young = living.isBaby();
                return armorModel;
            }
        }, WeaversParadiseItems.BRIDGET_ARMOR_SKIRT.get());

        event.registerItem(new IClientItemExtensions() {
            @Override
            @OnlyIn(Dist.CLIENT)
            public HumanoidModel<?> getHumanoidArmorModel(LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel defaultModel) {
                HumanoidModel armorModel = new HumanoidModel(new ModelPart(Collections.emptyList(),
                        Map.of("left_leg", new BridgetClothingModel(Minecraft.getInstance().getEntityModels().bakeLayer(BridgetClothingModel.LAYER_LOCATION)).LeftLeg, "right_leg",
                                new BridgetClothingModel(Minecraft.getInstance().getEntityModels().bakeLayer(BridgetClothingModel.LAYER_LOCATION)).RightLeg, "head", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "hat",
                                new ModelPart(Collections.emptyList(), Collections.emptyMap()), "body", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "right_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                                "left_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()))));
                armorModel.crouching = living.isShiftKeyDown();
                armorModel.riding = defaultModel.riding;
                armorModel.young = living.isBaby();
                return armorModel;
            }
        }, WeaversParadiseItems.BRIDGET_ARMOR_BOOTS.get());

        event.registerItem(new IClientItemExtensions() {
            @Override
            public HumanoidModel getHumanoidArmorModel(LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel defaultModel) {
                HumanoidModel armorModel = new HumanoidModel(new ModelPart(Collections.emptyList(),
                        Map.of("head", new FelixClothingModel(Minecraft.getInstance().getEntityModels().bakeLayer(FelixClothingModel.LAYER_LOCATION)).Head, "hat", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "body",
                                new ModelPart(Collections.emptyList(), Collections.emptyMap()), "right_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "left_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                                "right_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "left_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap()))));
                armorModel.crouching = living.isShiftKeyDown();
                armorModel.riding = defaultModel.riding;
                armorModel.young = living.isBaby();
                return armorModel;
            }
        }, WeaversParadiseItems.FELIX_ARMOR_HAT.get());

        event.registerItem(new IClientItemExtensions() {
            @Override
            @OnlyIn(Dist.CLIENT)
            public HumanoidModel getHumanoidArmorModel(LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel defaultModel) {
                HumanoidModel armorModel = new HumanoidModel(new ModelPart(Collections.emptyList(), Map.of("body", new FelixClothingModel(Minecraft.getInstance().getEntityModels().bakeLayer(FelixClothingModel.LAYER_LOCATION)).Body, "left_arm",
                        new FelixClothingModel(Minecraft.getInstance().getEntityModels().bakeLayer(FelixClothingModel.LAYER_LOCATION)).Left_Arm, "right_arm",
                        new FelixClothingModel(Minecraft.getInstance().getEntityModels().bakeLayer(FelixClothingModel.LAYER_LOCATION)).Right_Arm, "head", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "hat",
                        new ModelPart(Collections.emptyList(), Collections.emptyMap()), "right_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "left_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap()))));
                armorModel.crouching = living.isShiftKeyDown();
                armorModel.riding = defaultModel.riding;
                armorModel.young = living.isBaby();
                return armorModel;
            }
        }, WeaversParadiseItems.FELIX_ARMOR_JACKET.get());

        event.registerItem(new IClientItemExtensions() {
            @Override
            @OnlyIn(Dist.CLIENT)
            public HumanoidModel getHumanoidArmorModel(LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel defaultModel) {
                HumanoidModel armorModel = new HumanoidModel(new ModelPart(Collections.emptyList(),
                        Map.of("left_leg", new FelixClothingModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(FelixClothingModel.LAYER_LOCATION)).Left_Leg, "right_leg",
                                new FelixClothingModel(Minecraft.getInstance().getEntityModels().bakeLayer(FelixClothingModel.LAYER_LOCATION)).Right_Leg, "head", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "hat",
                                new ModelPart(Collections.emptyList(), Collections.emptyMap()), "body", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "right_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                                "left_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()))));
                armorModel.crouching = living.isShiftKeyDown();
                armorModel.riding = defaultModel.riding;
                armorModel.young = living.isBaby();
                return armorModel;
            }
        }, WeaversParadiseItems.FELIX_ARMOR_SKIRT.get());

        event.registerItem(new IClientItemExtensions() {
            @Override
            @OnlyIn(Dist.CLIENT)
            public HumanoidModel getHumanoidArmorModel(LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel defaultModel) {
                HumanoidModel armorModel = new HumanoidModel(new ModelPart(Collections.emptyList(),
                        Map.of("left_leg", new FelixClothingModel(Minecraft.getInstance().getEntityModels().bakeLayer(FelixClothingModel.LAYER_LOCATION)).Left_Leg2, "right_leg",
                                new FelixClothingModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(FelixClothingModel.LAYER_LOCATION)).Right_Leg2, "head", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "hat",
                                new ModelPart(Collections.emptyList(), Collections.emptyMap()), "body", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "right_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                                "left_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()))));
                armorModel.crouching = living.isShiftKeyDown();
                armorModel.riding = defaultModel.riding;
                armorModel.young = living.isBaby();
                return armorModel;
            }
        }, WeaversParadiseItems.FELIX_ARMOR_BOOTS.get());

        event.registerItem(
                new IClientItemExtensions() {
                    @Override
                    public @NotNull BlockEntityWithoutLevelRenderer getCustomRenderer() {
                        return new ThighHighsRenderer(Minecraft.getInstance().getBlockEntityRenderDispatcher(),
                                Minecraft.getInstance().getEntityModels());
                    }
                },
                WeaversParadiseItems.THIGH_HIGHS_COTTON,
                WeaversParadiseItems.THIGH_HIGHS_SILK,
                WeaversParadiseItems.THIGH_HIGHS_WOOL
        );

        event.registerItem(
                new IClientItemExtensions() {
                    @Override
                    public @NotNull BlockEntityWithoutLevelRenderer getCustomRenderer() {
                        return new HandWarmersRenderer(Minecraft.getInstance().getBlockEntityRenderDispatcher(),
                                Minecraft.getInstance().getEntityModels());
                    }
                },
                WeaversParadiseItems.HAND_WARMERS_COTTON,
                WeaversParadiseItems.HAND_WARMERS_SILK,
                WeaversParadiseItems.HAND_WARMERS_WOOL
        );

        event.registerItem(
                new IClientItemExtensions() {
                    @Override
                    public @NotNull BlockEntityWithoutLevelRenderer getCustomRenderer() {
                        return new ShirtRenderer(Minecraft.getInstance().getBlockEntityRenderDispatcher(),
                                Minecraft.getInstance().getEntityModels());
                    }
                },
                WeaversParadiseItems.SHIRT_COTTON,
                WeaversParadiseItems.SHIRT_SILK,
                WeaversParadiseItems.SWEATER_WOOL
        );

        event.registerItem(
                new IClientItemExtensions() {
                    @Override
                    public @NotNull BlockEntityWithoutLevelRenderer getCustomRenderer() {
                        return new PlushieItemRenderer(Minecraft.getInstance().getBlockEntityRenderDispatcher(),
                                Minecraft.getInstance().getEntityModels());
                    }
                },
                WeaversParadiseItems.PLAYER_PLUSHIE
        );

        event.registerItem(
                new IClientItemExtensions() {
                    @Override
                    public @NotNull BlockEntityWithoutLevelRenderer getCustomRenderer() {
                        return new ChokerRenderer(Minecraft.getInstance().getBlockEntityRenderDispatcher(),
                                Minecraft.getInstance().getEntityModels());
                    }
                },
                WeaversParadiseItems.CHOKER
        );

        event.registerItem(
                new IClientItemExtensions() {
                    @Override
                    public @NotNull BlockEntityWithoutLevelRenderer getCustomRenderer() {
                        return new BellRenderer(Minecraft.getInstance().getBlockEntityRenderDispatcher(),
                                Minecraft.getInstance().getEntityModels());
                    }
                },
                WeaversParadiseItems.BELL
        );

        event.registerItem(
                new IClientItemExtensions() {
                    @Override
                    public @NotNull BlockEntityWithoutLevelRenderer getCustomRenderer() {
                        return new RingRenderer(Minecraft.getInstance().getBlockEntityRenderDispatcher(),
                                Minecraft.getInstance().getEntityModels());
                    }
                },
                WeaversParadiseItems.RING
        );

        event.registerItem(
                new IClientItemExtensions() {
                    @Override
                    public @NotNull BlockEntityWithoutLevelRenderer getCustomRenderer() {
                        return new CatRingRenderer(Minecraft.getInstance().getBlockEntityRenderDispatcher(),
                                Minecraft.getInstance().getEntityModels());
                    }
                },
                WeaversParadiseItems.CAT_RING
        );

        event.registerItem(
                new IClientItemExtensions() {
                    @Override
                    public @NotNull BlockEntityWithoutLevelRenderer getCustomRenderer() {
                        return new HeartRenderer(Minecraft.getInstance().getBlockEntityRenderDispatcher(),
                                Minecraft.getInstance().getEntityModels());
                    }
                },
                WeaversParadiseItems.HEART
        );

        event.registerItem(
                new IClientItemExtensions() {
                    @Override
                    public @NotNull BlockEntityWithoutLevelRenderer getCustomRenderer() {
                        return new PlateRenderer(Minecraft.getInstance().getBlockEntityRenderDispatcher(),
                                Minecraft.getInstance().getEntityModels());
                    }
                },
                WeaversParadiseItems.PLATE
        );

        event.registerItem(
                new IClientItemExtensions() {
                    @Override
                    public @NotNull BlockEntityWithoutLevelRenderer getCustomRenderer() {
                        return new PantsRenderer(Minecraft.getInstance().getBlockEntityRenderDispatcher(),
                                Minecraft.getInstance().getEntityModels());
                    }
                },
                WeaversParadiseItems.PANTS_JEANS,
                WeaversParadiseItems.PANTS_COTTON,
                WeaversParadiseItems.PANTS_SILK
        );

        event.registerItem(
                new IClientItemExtensions() {
                    @Override
                    public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                        return new CapeRenderer(Minecraft.getInstance().getBlockEntityRenderDispatcher(),
                                Minecraft.getInstance().getEntityModels()
                        );
                    }
                },
                WeaversParadiseItems.COTTON_CAPE,
                WeaversParadiseItems.SILK_CAPE,
                WeaversParadiseItems.WOOL_CAPE
        );
    }
}
