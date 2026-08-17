package xox.labvorty.weaversparadise.init;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.resources.PlayerSkin;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterClientTooltipComponentFactoriesEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.client.CuriosRendererRegistry;
import xox.labvorty.weaversparadise.data.tooltip_components.ClothingTooltipComponent;
import xox.labvorty.weaversparadise.data.tooltip_components.DyeTooltipComponent;
import xox.labvorty.weaversparadise.data.tooltip_components.DyeTypeRegistry;
import xox.labvorty.weaversparadise.data.tooltip_components.QualityTooltipComponent;
import xox.labvorty.weaversparadise.data.tooltip_components.client.ClothingClientTooltipComponent;
import xox.labvorty.weaversparadise.data.tooltip_components.client.DyeClientTooltipComponent;
import xox.labvorty.weaversparadise.data.tooltip_components.client.QualityClientTooltipComponent;
import xox.labvorty.weaversparadise.data.tooltip_components.helper.DyeDataColor;
import xox.labvorty.weaversparadise.data.tooltip_components.helper.DyeInstance;
import xox.labvorty.weaversparadise.items.dye.BottledDyeItem;
import xox.labvorty.weaversparadise.items.dye.PigmentItem;
import xox.labvorty.weaversparadise.items.materials.ChromaticBloomFruitItem;
import xox.labvorty.weaversparadise.items.materials.ChromaticDustItem;
import xox.labvorty.weaversparadise.model.*;
import xox.labvorty.weaversparadise.renderers.bewlr.*;
import xox.labvorty.weaversparadise.renderers.bewlr.BellRenderer;
import xox.labvorty.weaversparadise.renderers.bewlr.CatRingRenderer;
import xox.labvorty.weaversparadise.renderers.bewlr.HeartRenderer;
import xox.labvorty.weaversparadise.renderers.bewlr.PlateRenderer;
import xox.labvorty.weaversparadise.renderers.bewlr.RingRenderer;
import xox.labvorty.weaversparadise.renderers.curios.*;
import xox.labvorty.weaversparadise.renderers.layer.*;

@EventBusSubscriber(value = Dist.CLIENT)
public class WeaversParadiseClient {
    @SubscribeEvent
    public static void registerItemExtensions(RegisterClientExtensionsEvent event) {
        event.registerItem(
                new IClientItemExtensions() {
                    @Override
                    public @NotNull BlockEntityWithoutLevelRenderer getCustomRenderer() {
                        return new ThighHighsRenderer(Minecraft.getInstance().getBlockEntityRenderDispatcher(),
                                Minecraft.getInstance().getEntityModels());
                    }

                    @Override
                    public boolean shouldBobAsEntity(@NotNull ItemStack itemStack) {
                        if (itemStack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getBoolean("noBobbing")) {
                            return false;
                        }

                        return IClientItemExtensions.super.shouldBobAsEntity(itemStack);
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

                    @Override
                    public boolean shouldBobAsEntity(@NotNull ItemStack itemStack) {
                        if (itemStack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getBoolean("noBobbing")) {
                            return false;
                        }

                        return IClientItemExtensions.super.shouldBobAsEntity(itemStack);
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
                        return new UpperwearRenderer(Minecraft.getInstance().getBlockEntityRenderDispatcher(),
                                Minecraft.getInstance().getEntityModels());
                    }

                    @Override
                    public boolean shouldBobAsEntity(@NotNull ItemStack itemStack) {
                        if (itemStack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getBoolean("noBobbing")) {
                            return false;
                        }

                        return IClientItemExtensions.super.shouldBobAsEntity(itemStack);
                    }
                },
                WeaversParadiseItems.T_SHIRT,
                WeaversParadiseItems.TANK_TOP,
                WeaversParadiseItems.WOOL_VEST,
                WeaversParadiseItems.SHIRT_COTTON,
                WeaversParadiseItems.SHIRT_SILK,
                WeaversParadiseItems.LONG_SLEEVE_COTTON,
                WeaversParadiseItems.SWEATER_WOOL,
                WeaversParadiseItems.PONCHO,
                WeaversParadiseItems.COTTON_CROP_TOP_LONG_SLEEVED
        );

        event.registerItem(
                new IClientItemExtensions() {
                    @Override
                    public @NotNull BlockEntityWithoutLevelRenderer getCustomRenderer() {
                        return new PlushieItemRenderer(Minecraft.getInstance().getBlockEntityRenderDispatcher(),
                                Minecraft.getInstance().getEntityModels());
                    }

                    @Override
                    public boolean shouldBobAsEntity(@NotNull ItemStack itemStack) {
                        if (itemStack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getBoolean("noBobbing")) {
                            return false;
                        }

                        return IClientItemExtensions.super.shouldBobAsEntity(itemStack);
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

                    @Override
                    public boolean shouldBobAsEntity(@NotNull ItemStack itemStack) {
                        if (itemStack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getBoolean("noBobbing")) {
                            return false;
                        }

                        return IClientItemExtensions.super.shouldBobAsEntity(itemStack);
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

                    @Override
                    public boolean shouldBobAsEntity(@NotNull ItemStack itemStack) {
                        if (itemStack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getBoolean("noBobbing")) {
                            return false;
                        }

                        return IClientItemExtensions.super.shouldBobAsEntity(itemStack);
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

                    @Override
                    public boolean shouldBobAsEntity(@NotNull ItemStack itemStack) {
                        if (itemStack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getBoolean("noBobbing")) {
                            return false;
                        }

                        return IClientItemExtensions.super.shouldBobAsEntity(itemStack);
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

                    @Override
                    public boolean shouldBobAsEntity(@NotNull ItemStack itemStack) {
                        if (itemStack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getBoolean("noBobbing")) {
                            return false;
                        }

                        return IClientItemExtensions.super.shouldBobAsEntity(itemStack);
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

                    @Override
                    public boolean shouldBobAsEntity(@NotNull ItemStack itemStack) {
                        if (itemStack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getBoolean("noBobbing")) {
                            return false;
                        }

                        return IClientItemExtensions.super.shouldBobAsEntity(itemStack);
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

                    @Override
                    public boolean shouldBobAsEntity(@NotNull ItemStack itemStack) {
                        if (itemStack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getBoolean("noBobbing")) {
                            return false;
                        }

                        return IClientItemExtensions.super.shouldBobAsEntity(itemStack);
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

                    @Override
                    public boolean shouldBobAsEntity(@NotNull ItemStack itemStack) {
                        if (itemStack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getBoolean("noBobbing")) {
                            return false;
                        }

                        return IClientItemExtensions.super.shouldBobAsEntity(itemStack);
                    }
                },
                WeaversParadiseItems.PANTS_JEANS,
                WeaversParadiseItems.PANTS_COTTON,
                WeaversParadiseItems.PANTS_SILK,
                WeaversParadiseItems.PANTS_WOOL
        );

        event.registerItem(
                new IClientItemExtensions() {
                    @Override
                    public @NotNull BlockEntityWithoutLevelRenderer getCustomRenderer() {
                        return new CapeRenderer(Minecraft.getInstance().getBlockEntityRenderDispatcher(),
                                Minecraft.getInstance().getEntityModels()
                        );
                    }

                    @Override
                    public boolean shouldBobAsEntity(@NotNull ItemStack itemStack) {
                        if (itemStack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getBoolean("noBobbing")) {
                            return false;
                        }

                        return IClientItemExtensions.super.shouldBobAsEntity(itemStack);
                    }
                },
                WeaversParadiseItems.COTTON_CAPE,
                WeaversParadiseItems.SILK_CAPE,
                WeaversParadiseItems.WOOL_CAPE
        );

        event.registerItem(
                new IClientItemExtensions() {
                    @Override
                    public @NotNull BlockEntityWithoutLevelRenderer getCustomRenderer() {
                        return new CottonSkirtRenderer(Minecraft.getInstance().getBlockEntityRenderDispatcher(), Minecraft.getInstance().getEntityModels());
                    }

                    @Override
                    public boolean shouldBobAsEntity(@NotNull ItemStack itemStack) {
                        if (itemStack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getBoolean("noBobbing")) {
                            return false;
                        }

                        return IClientItemExtensions.super.shouldBobAsEntity(itemStack);
                    }
                },
                WeaversParadiseItems.COTTON_SKIRT
        );

        event.registerItem(
                new IClientItemExtensions() {
                    @Override
                    public @NotNull BlockEntityWithoutLevelRenderer getCustomRenderer() {
                        return new UshankaRenderer(Minecraft.getInstance().getBlockEntityRenderDispatcher(), Minecraft.getInstance().getEntityModels());
                    }

                    @Override
                    public boolean shouldBobAsEntity(@NotNull ItemStack itemStack) {
                        if (itemStack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getBoolean("noBobbing")) {
                            return false;
                        }

                        return IClientItemExtensions.super.shouldBobAsEntity(itemStack);
                    }
                },
                WeaversParadiseItems.USHANKA
        );

        event.registerItem(
                new IClientItemExtensions() {
                    @Override
                    public @NotNull BlockEntityWithoutLevelRenderer getCustomRenderer() {
                        return new CapRenderer(Minecraft.getInstance().getBlockEntityRenderDispatcher(), Minecraft.getInstance().getEntityModels());
                    }

                    @Override
                    public boolean shouldBobAsEntity(@NotNull ItemStack itemStack) {
                        if (itemStack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getBoolean("noBobbing")) {
                            return false;
                        }

                        return IClientItemExtensions.super.shouldBobAsEntity(itemStack);
                    }
                },
                WeaversParadiseItems.CAP
        );

        event.registerItem(
                new IClientItemExtensions() {
                    @Override
                    public @NotNull BlockEntityWithoutLevelRenderer getCustomRenderer() {
                        return new PomponHatRenderer(Minecraft.getInstance().getBlockEntityRenderDispatcher(), Minecraft.getInstance().getEntityModels());
                    }

                    @Override
                    public boolean shouldBobAsEntity(@NotNull ItemStack itemStack) {
                        if (itemStack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getBoolean("noBobbing")) {
                            return false;
                        }

                        return IClientItemExtensions.super.shouldBobAsEntity(itemStack);
                    }
                },
                WeaversParadiseItems.POMPON_HAT
        );
    }

    @SubscribeEvent
    public static void registerTooltipComponents(RegisterClientTooltipComponentFactoriesEvent event) {
        event.register(ClothingTooltipComponent.class, data -> new ClothingClientTooltipComponent(
                data.getQualityTextures(),
                data.getEntries()
        ));

        event.register(DyeTooltipComponent.class, data -> new DyeClientTooltipComponent(data.getDyeIcon(), data.getText(), data.getType(), data.getLightValue(), data.getPrimaryColor(), data.getSecondaryColor(), data.isCore()));
        event.register(QualityTooltipComponent.class, data -> new QualityClientTooltipComponent(data.getTextures()));
    }

    @SubscribeEvent
    public static void itemHandlers(RegisterColorHandlersEvent.Item event) {
        event.register((stack, layer) -> {
            if (stack.getItem() instanceof BottledDyeItem dye) {
                CompoundTag compoundTag = stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag();

                DyeInstance dyeInstance = DyeTypeRegistry.getDyeType(compoundTag.getString("dyeType"));
                return dyeInstance.getColorParser().apply(
                        new DyeDataColor(
                                layer,
                                compoundTag,
                                dye.getItemMainColor(stack),
                                dye.getItemSecondaryColor(stack),
                                dye.getItemLightValue(stack)
                        )
                );
            }

            return -1;
        }, WeaversParadiseItems.BOTTLED_DYE.get());

        event.register((stack, layer) -> {
            if (layer == 0) {
                return -1;
            }

            if (stack.getItem() instanceof ChromaticBloomFruitItem) {
                Minecraft minecraft = Minecraft.getInstance();
                int ticks = 0;
                if (minecraft.level != null) {
                    ticks = (int)minecraft.level.getGameTime();
                }
                ticks += (layer * 2);

                float speed = 0.05F;

                float red = Mth.clamp((float)(Math.sin(ticks * speed) * 0.5 + 0.5), 0, 1);
                float green = Mth.clamp((float)(Math.sin(ticks * speed + 2 * Math.PI / 3) * 0.5 + 0.5), 0, 1);
                float blue = Mth.clamp((float)(Math.sin(ticks * speed + 4 * Math.PI / 3) * 0.5 + 0.5), 0, 1);

                int trueRed = (int)(red * 255);
                int trueGreen = (int)(green * 255);
                int trueBlue = (int)(blue * 255);

                return 255 << 24 | trueRed << 16 | trueGreen << 8 | trueBlue;
            }

            return -1;
        }, WeaversParadiseItems.CHROMATIC_BLOOM_FRUIT);

        event.register((stack, layer) -> {
            if (stack.getItem() instanceof PigmentItem pureDyeItem) {
                return pureDyeItem.getDyeColor(stack);
            }

            return -1;
        }, WeaversParadiseItems.PURE_DYE);

        event.register((stack, layer) -> {
            if (stack.getItem() instanceof ChromaticDustItem) {
                Minecraft minecraft = Minecraft.getInstance();
                int ticks = 0;
                if (minecraft.level != null) {
                    ticks = (int)minecraft.level.getGameTime();
                }

                float speed = 0.05F;

                float red = Mth.clamp((float)(Math.sin(ticks * speed) * 0.5 + 0.5), 0, 1);
                float green = Mth.clamp((float)(Math.sin(ticks * speed + 2 * Math.PI / 3) * 0.5 + 0.5), 0, 1);
                float blue = Mth.clamp((float)(Math.sin(ticks * speed + 4 * Math.PI / 3) * 0.5 + 0.5), 0, 1);

                int trueRed = (int)(red * 255);
                int trueGreen = (int)(green * 255);
                int trueBlue = (int)(blue * 255);

                return 255 << 24 | trueRed << 16 | trueGreen << 8 | trueBlue;
            }

            return -1;
        }, WeaversParadiseItems.CHROMATIC_DUST);
    }

    @SubscribeEvent
    public static void registerLayers(final EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ThighHighsModel.LAYER_LOCATION, ThighHighsModel::createBodyLayer);
        event.registerLayerDefinition(AstolfoModel.LAYER_LOCATION, AstolfoModel::createBodyLayer);
        event.registerLayerDefinition(BridgetModel.LAYER_LOCATION, BridgetModel::createBodyLayer);
        event.registerLayerDefinition(FelixModel.LAYER_LOCATION, FelixModel::createBodyLayer);
        event.registerLayerDefinition(GriffithModel.LAYER_LOCATION, GriffithModel::createBodyLayer);
        event.registerLayerDefinition(NikoModel.LAYER_LOCATION, NikoModel::createBodyLayer);
        event.registerLayerDefinition(GiselleModel.LAYER_LOCATION, GiselleModel::createBodyLayer);
        event.registerLayerDefinition(GabrielModel.LAYER_LOCATION, GabrielModel::createBodyLayer);
        event.registerLayerDefinition(MikkelaModel.LAYER_LOCATION, MikkelaModel::createBodyLayer);
        event.registerLayerDefinition(ExpieModel.LAYER_LOCATION, ExpieModel::createBodyLayer);
        event.registerLayerDefinition(GasterModel.LAYER_LOCATION, GasterModel::createBodyLayer);
        event.registerLayerDefinition(RalseiModel.LAYER_LOCATION, RalseiModel::createBodyLayer);
        event.registerLayerDefinition(MinosPrimeModel.LAYER_LOCATION, MinosPrimeModel::createBodyLayer);
        event.registerLayerDefinition(JayaUtomoModel.LAYER_LOCATION, JayaUtomoModel::createBodyLayer);
        event.registerLayerDefinition(HandWarmersModel.LAYER_LOCATION, HandWarmersModel::createBodyLayer);
        event.registerLayerDefinition(UpperWearModel.LAYER_LOCATION, UpperWearModel::createBodyLayer);
        event.registerLayerDefinition(SlimPlushieModel.LAYER_LOCATION, SlimPlushieModel::createBodyLayer);
        event.registerLayerDefinition(WidePlushieModel.LAYER_LOCATION, WidePlushieModel::createBodyLayer);
        event.registerLayerDefinition(ChokerModel.LAYER_LOCATION, ChokerModel::createBodyLayer);
        event.registerLayerDefinition(BellModel.LAYER_LOCATION, BellModel::createBodyLayer);
        event.registerLayerDefinition(PantsModel.LAYER_LOCATION, PantsModel::createBodyLayer);
        event.registerLayerDefinition(BasicRingModel.LAYER_LOCATION, BasicRingModel::createBodyLayer);
        event.registerLayerDefinition(CatRingModel.LAYER_LOCATION, CatRingModel::createBodyLayer);
        event.registerLayerDefinition(HeartModel.LAYER_LOCATION, HeartModel::createBodyLayer);
        event.registerLayerDefinition(BasicPlateModel.LAYER_LOCATION, BasicPlateModel::createBodyLayer);
        event.registerLayerDefinition(CottonSkirtModel.LAYER_LOCATION, CottonSkirtModel::createBodyLayer);
        event.registerLayerDefinition(UshankaModel.LAYER_LOCATION, UshankaModel::createBodyLayer);
        event.registerLayerDefinition(CapModel.LAYER_LOCATION, CapModel::createBodyLayer);
        event.registerLayerDefinition(PomponHatModel.LAYER_LOCATION, PomponHatModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void clientSetup(final FMLClientSetupEvent event) {
        CuriosRendererRegistry.register(WeaversParadiseItems.THIGH_HIGHS_COTTON.get(), ThighHighsCurioRenderer::new);
        CuriosRendererRegistry.register(WeaversParadiseItems.THIGH_HIGHS_WOOL.get(), ThighHighsCurioRenderer::new);
        CuriosRendererRegistry.register(WeaversParadiseItems.THIGH_HIGHS_SILK.get(), ThighHighsCurioRenderer::new);

        CuriosRendererRegistry.register(WeaversParadiseItems.LEATHER_GLOVES.get(), LeatherGlovesRenderer::new);
        CuriosRendererRegistry.register(WeaversParadiseItems.CHOKER.get(), ChokerCurioRenderer::new);
        CuriosRendererRegistry.register(WeaversParadiseItems.BELL.get(), xox.labvorty.weaversparadise.renderers.curios.BellRenderer::new);
        CuriosRendererRegistry.register(WeaversParadiseItems.RING.get(), xox.labvorty.weaversparadise.renderers.curios.RingRenderer::new);
        CuriosRendererRegistry.register(WeaversParadiseItems.CAT_RING.get(), xox.labvorty.weaversparadise.renderers.curios.CatRingRenderer::new);
        CuriosRendererRegistry.register(WeaversParadiseItems.HEART.get(), xox.labvorty.weaversparadise.renderers.curios.HeartRenderer::new);
        CuriosRendererRegistry.register(WeaversParadiseItems.PLATE.get(), xox.labvorty.weaversparadise.renderers.curios.PlateRenderer::new);

        CuriosRendererRegistry.register(WeaversParadiseItems.HAND_WARMERS_COTTON.get(), HandWarmersCurioRenderer::new);
        CuriosRendererRegistry.register(WeaversParadiseItems.HAND_WARMERS_SILK.get(), HandWarmersCurioRenderer::new);
        CuriosRendererRegistry.register(WeaversParadiseItems.HAND_WARMERS_WOOL.get(), HandWarmersCurioRenderer::new);

        CuriosRendererRegistry.register(WeaversParadiseItems.T_SHIRT.get(), UpperwearCurioRenderer::new);
        CuriosRendererRegistry.register(WeaversParadiseItems.TANK_TOP.get(), UpperwearCurioRenderer::new);
        CuriosRendererRegistry.register(WeaversParadiseItems.WOOL_VEST.get(), UpperwearCurioRenderer::new);

        CuriosRendererRegistry.register(WeaversParadiseItems.SHIRT_COTTON.get(), UpperwearCurioRenderer::new);
        CuriosRendererRegistry.register(WeaversParadiseItems.SHIRT_SILK.get(), UpperwearCurioRenderer::new);

        CuriosRendererRegistry.register(WeaversParadiseItems.LONG_SLEEVE_COTTON.get(), UpperwearCurioRenderer::new);
        CuriosRendererRegistry.register(WeaversParadiseItems.SWEATER_WOOL.get(), UpperwearCurioRenderer::new);

        CuriosRendererRegistry.register(WeaversParadiseItems.PONCHO.get(), UpperwearCurioRenderer::new);

        CuriosRendererRegistry.register(WeaversParadiseItems.COTTON_CROP_TOP_LONG_SLEEVED.get(), UpperwearCurioRenderer::new);

        CuriosRendererRegistry.register(WeaversParadiseItems.POMPON_HAT.get(), PomponHatCurioRenderer::new);
        CuriosRendererRegistry.register(WeaversParadiseItems.CAP.get(), CapCurioRenderer::new);
        CuriosRendererRegistry.register(WeaversParadiseItems.USHANKA.get(), UshankaCurioRenderer::new);

        CuriosRendererRegistry.register(WeaversParadiseItems.PANTS_JEANS.get(), PantsCurioRenderer::new);
        CuriosRendererRegistry.register(WeaversParadiseItems.PANTS_COTTON.get(), PantsCurioRenderer::new);
        CuriosRendererRegistry.register(WeaversParadiseItems.PANTS_SILK.get(), PantsCurioRenderer::new);
        CuriosRendererRegistry.register(WeaversParadiseItems.PANTS_WOOL.get(), PantsCurioRenderer::new);

        CuriosRendererRegistry.register(WeaversParadiseItems.COTTON_SKIRT.get(), CottonSkirtCurioRenderer::new);
    }

    @SubscribeEvent
    public static void addLayers(EntityRenderersEvent.AddLayers event) {
        for (EntityType<?> type : event.getEntityTypes()) {
            var renderer = event.getRenderer(type);
            if (renderer instanceof LivingEntityRenderer<?, ?> livingRenderer) {
                addThighHighsLayer(livingRenderer);
                addShirtLayer(livingRenderer);
                addPantsLayer(livingRenderer);
                addHandWarmersLayer(livingRenderer);
                addCottonSkirtLayer(livingRenderer);
                addHatLayer(livingRenderer);
            }
        }

        for (PlayerSkin.Model skin : event.getSkins()) {
            var playerRenderer = event.getSkin(skin);
            if (playerRenderer != null) {
                addThighHighsLayer((LivingEntityRenderer<?, ?>) playerRenderer);
                addShirtLayer((LivingEntityRenderer<?, ?>) playerRenderer);
                addPantsLayer((LivingEntityRenderer<?, ?>) playerRenderer);
                addHandWarmersLayer((LivingEntityRenderer<?, ?>) playerRenderer);
                addCottonSkirtLayer((LivingEntityRenderer<?, ?>) playerRenderer);
                addHatLayer((LivingEntityRenderer<?, ?>) playerRenderer);
            }
        }
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private static void addThighHighsLayer(LivingEntityRenderer<?, ?> renderer) {
        renderer.addLayer(new ThighHighsRenderLayer(renderer));
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private static void addShirtLayer(LivingEntityRenderer<?, ?> renderer) {
        renderer.addLayer(new UpperwearRenderLayer(renderer));
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private static void addPantsLayer(LivingEntityRenderer<?, ?> renderer) {
        renderer.addLayer(new PantsRenderLayer(renderer));
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private static void addHandWarmersLayer(LivingEntityRenderer<?, ?> renderer) {
        renderer.addLayer(new HandWarmersRenderLayer(renderer));
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private static void addCottonSkirtLayer(LivingEntityRenderer<?, ?> renderer) {
        renderer.addLayer(new CottonSkirtRenderLayer(renderer));
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private static void addHatLayer(LivingEntityRenderer<?, ?> renderer) {
        renderer.addLayer(new HatRenderLayer(renderer));
    }
}
