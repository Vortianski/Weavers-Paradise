package xox.labvorty.weaversparadise.client;

import dev.emi.trinkets.api.client.TrinketRenderer;
import dev.emi.trinkets.api.client.TrinketRendererRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.LivingEntityFeatureRendererRegistrationCallback;
import net.fabricmc.fabric.api.client.rendering.v1.TooltipComponentCallback;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import xox.labvorty.weaversparadise.WeaversParadise;
import xox.labvorty.weaversparadise.blocks.entities.renderer.BlahajBlockEntityRenderer;
import xox.labvorty.weaversparadise.blocks.entities.renderer.PlushieBlockEntityRenderer;
import xox.labvorty.weaversparadise.client.render.WPShaders;
import xox.labvorty.weaversparadise.data.network.ClothcraftingNetworkMultiMessage;
import xox.labvorty.weaversparadise.data.network.StringNetworkMessage;
import xox.labvorty.weaversparadise.data.tooltip_components.ClothingTooltipComponent;
import xox.labvorty.weaversparadise.data.tooltip_components.DyeTooltipComponent;
import xox.labvorty.weaversparadise.data.tooltip_components.DyeTypeRegistry;
import xox.labvorty.weaversparadise.data.tooltip_components.QualityTooltipComponent;
import xox.labvorty.weaversparadise.data.tooltip_components.client.ClothingClientTooltipComponent;
import xox.labvorty.weaversparadise.data.tooltip_components.client.DyeClientTooltipComponent;
import xox.labvorty.weaversparadise.data.tooltip_components.client.QualityClientTooltipComponent;
import xox.labvorty.weaversparadise.data.tooltip_components.helper.DyeDataColor;
import xox.labvorty.weaversparadise.data.tooltip_components.helper.DyeInstance;
import xox.labvorty.weaversparadise.init.WeaversParadiseBlockEntities;
import xox.labvorty.weaversparadise.init.WeaversParadiseEntityTypes;
import xox.labvorty.weaversparadise.init.WeaversParadiseInitialization;
import xox.labvorty.weaversparadise.init.WeaversParadiseInterfaces;
import xox.labvorty.weaversparadise.init.WeaversParadiseItems;
import xox.labvorty.weaversparadise.init.WeaversParadiseKeyMappings;
import xox.labvorty.weaversparadise.init.WeaversParadiseParticles;
import xox.labvorty.weaversparadise.items.dye.BottledDyeItem;
import xox.labvorty.weaversparadise.items.dye.PigmentItem;
import xox.labvorty.weaversparadise.items.materials.ChromaticBloomFruitItem;
import xox.labvorty.weaversparadise.items.materials.ChromaticDustItem;
import xox.labvorty.weaversparadise.model.*;
import xox.labvorty.weaversparadise.particles.providers.StarbloomParticleProvider;
import xox.labvorty.weaversparadise.renderers.bewlr.BlahajRenderer;
import xox.labvorty.weaversparadise.renderers.bewlr.BellRenderer;
import xox.labvorty.weaversparadise.renderers.bewlr.CapeRenderer;
import xox.labvorty.weaversparadise.renderers.bewlr.CapRenderer;
import xox.labvorty.weaversparadise.renderers.bewlr.CatRingRenderer;
import xox.labvorty.weaversparadise.renderers.bewlr.ChokerRenderer;
import xox.labvorty.weaversparadise.renderers.bewlr.CottonSkirtRenderer;
import xox.labvorty.weaversparadise.renderers.bewlr.FishRenderer;
import xox.labvorty.weaversparadise.renderers.bewlr.HandWarmersRenderer;
import xox.labvorty.weaversparadise.renderers.bewlr.HeartRenderer;
import xox.labvorty.weaversparadise.renderers.bewlr.PantsRenderer;
import xox.labvorty.weaversparadise.renderers.bewlr.PlateRenderer;
import xox.labvorty.weaversparadise.renderers.bewlr.PlushieItemRenderer;
import xox.labvorty.weaversparadise.renderers.bewlr.PomponHatRenderer;
import xox.labvorty.weaversparadise.renderers.bewlr.RingRenderer;
import xox.labvorty.weaversparadise.renderers.bewlr.ThighHighsRenderer;
import xox.labvorty.weaversparadise.renderers.bewlr.UpperwearRenderer;
import xox.labvorty.weaversparadise.renderers.bewlr.UshankaRenderer;
import xox.labvorty.weaversparadise.renderers.curios.CapCurioRenderer;
import xox.labvorty.weaversparadise.renderers.curios.ChokerCurioRenderer;
import xox.labvorty.weaversparadise.renderers.curios.CottonSkirtCurioRenderer;
import xox.labvorty.weaversparadise.renderers.curios.HandWarmersCurioRenderer;
import xox.labvorty.weaversparadise.renderers.curios.LeatherGlovesRenderer;
import xox.labvorty.weaversparadise.renderers.curios.PantsCurioRenderer;
import xox.labvorty.weaversparadise.renderers.curios.PomponHatCurioRenderer;
import xox.labvorty.weaversparadise.renderers.curios.ThighHighsCurioRenderer;
import xox.labvorty.weaversparadise.renderers.curios.UpperwearCurioRenderer;
import xox.labvorty.weaversparadise.renderers.curios.UshankaCurioRenderer;
import xox.labvorty.weaversparadise.renderers.entity.HangingFlagEntityRenderer;
import xox.labvorty.weaversparadise.renderers.layer.*;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class WeaversParadiseFabricClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        WPShaders.registerShaders();
        WeaversParadiseBlockRenderLayers.init();

        registerClientReceivers();
        registerItemRenderers();
        registerLayerDefinitions();
        registerFeatureLayers();
        registerMenuScreens();
        registerEntityAndBlockEntityRenderers();
        registerParticles();
        registerKeyMappings();
        registerTooltipComponents();
        registerColorProviders();
        registerTrinketRenderers();

        WeaversParadiseInitialization.reloadClient();

        WeaversParadise.LOGGER.info("Weavers Paradise client initialized");
    }

    // ===================== Client packet receivers (S2C) =====================

    private void registerClientReceivers() {
        WPClientNetwork.registerClientReceiver(
                ClothcraftingNetworkMultiMessage.TYPE,
                (message, context) -> {
                    context.client().execute(() -> {
                        int buttonID = message.buttonID();
                        if (buttonID == 0) {
                            xox.labvorty.weaversparadise.gui.screen.ClothcraftingScreen.updateData(
                                    message.gameTime(), message.gameScore(), message.isGameOn(),
                                    message.items(), message.clothType());
                        }
                    });
                });
        WPClientNetwork.registerClientReceiver(
                StringNetworkMessage.TYPE,
                (message, context) -> {
                    context.client().execute(() ->
                            xox.labvorty.weaversparadise.gui.screen.StringScreen.updateProgress(message.data()));
                });
    }

    // ===================== BEWLR (IClientItemExtensions.getCustomRenderer) =====================
    // Fabric-нативный путь: BuiltinItemRendererRegistry для моделей builtin/entity.
    // Рендереры создаются лениво при первом рендере (Minecraft уже существует).

    private final Map<String, net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer> bewlrCache = new HashMap<>();

    private net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer bewlr(String key, Supplier<net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer> factory) {
        return this.bewlrCache.computeIfAbsent(key, k -> factory.get());
    }

    private void registerBewlr(net.minecraft.world.item.Item item, String key, Supplier<net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer> factory) {
        BuiltinItemRendererRegistry.INSTANCE.register(item, (stack, mode, matrices, buffers, light, overlay) ->
                this.bewlr(key, factory).renderByItem(stack, mode, matrices, buffers, light, overlay));
    }

    private Supplier<net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer> bewlrFactory(Supplier<net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer> factory) {
        return factory;
    }

    private void registerItemRenderers() {
        this.registerBewlr(WeaversParadiseItems.THIGH_HIGHS_COTTON, "thigh_highs", this.bewlrFactory(() -> {
            var mc = Minecraft.getInstance();
            return new ThighHighsRenderer(mc.getBlockEntityRenderDispatcher(), mc.getEntityModels());
        }));
        this.registerBewlr(WeaversParadiseItems.THIGH_HIGHS_SILK, "thigh_highs", this.bewlrFactory(() -> {
            var mc = Minecraft.getInstance();
            return new ThighHighsRenderer(mc.getBlockEntityRenderDispatcher(), mc.getEntityModels());
        }));
        this.registerBewlr(WeaversParadiseItems.THIGH_HIGHS_WOOL, "thigh_highs", this.bewlrFactory(() -> {
            var mc = Minecraft.getInstance();
            return new ThighHighsRenderer(mc.getBlockEntityRenderDispatcher(), mc.getEntityModels());
        }));

        this.registerBewlr(WeaversParadiseItems.HAND_WARMERS_COTTON, "hand_warmers", this.bewlrFactory(() -> {
            var mc = Minecraft.getInstance();
            return new HandWarmersRenderer(mc.getBlockEntityRenderDispatcher(), mc.getEntityModels());
        }));
        this.registerBewlr(WeaversParadiseItems.HAND_WARMERS_SILK, "hand_warmers", this.bewlrFactory(() -> {
            var mc = Minecraft.getInstance();
            return new HandWarmersRenderer(mc.getBlockEntityRenderDispatcher(), mc.getEntityModels());
        }));
        this.registerBewlr(WeaversParadiseItems.HAND_WARMERS_WOOL, "hand_warmers", this.bewlrFactory(() -> {
            var mc = Minecraft.getInstance();
            return new HandWarmersRenderer(mc.getBlockEntityRenderDispatcher(), mc.getEntityModels());
        }));

        Supplier<net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer> upperwearFactory = this.bewlrFactory(() -> {
            var mc = Minecraft.getInstance();
            return new UpperwearRenderer(mc.getBlockEntityRenderDispatcher(), mc.getEntityModels());
        });
        this.registerBewlr(WeaversParadiseItems.T_SHIRT, "upperwear", upperwearFactory);
        this.registerBewlr(WeaversParadiseItems.TANK_TOP, "upperwear", upperwearFactory);
        this.registerBewlr(WeaversParadiseItems.WOOL_VEST, "upperwear", upperwearFactory);
        this.registerBewlr(WeaversParadiseItems.SHIRT_COTTON, "upperwear", upperwearFactory);
        this.registerBewlr(WeaversParadiseItems.SHIRT_SILK, "upperwear", upperwearFactory);
        this.registerBewlr(WeaversParadiseItems.LONG_SLEEVE_COTTON, "upperwear", upperwearFactory);
        this.registerBewlr(WeaversParadiseItems.SWEATER_WOOL, "upperwear", upperwearFactory);
        this.registerBewlr(WeaversParadiseItems.PONCHO, "upperwear", upperwearFactory);
        this.registerBewlr(WeaversParadiseItems.COTTON_CROP_TOP_LONG_SLEEVED, "upperwear", upperwearFactory);

        this.registerBewlr(WeaversParadiseItems.PLAYER_PLUSHIE, "plushie", this.bewlrFactory(() -> {
            var mc = Minecraft.getInstance();
            return new PlushieItemRenderer(mc.getBlockEntityRenderDispatcher(), mc.getEntityModels());
        }));

        this.registerBewlr(WeaversParadiseItems.CHOKER, "choker", this.bewlrFactory(() -> {
            var mc = Minecraft.getInstance();
            return new ChokerRenderer(mc.getBlockEntityRenderDispatcher(), mc.getEntityModels());
        }));

        this.registerBewlr(WeaversParadiseItems.BELL, "bell", this.bewlrFactory(() -> {
            var mc = Minecraft.getInstance();
            return new BellRenderer(mc.getBlockEntityRenderDispatcher(), mc.getEntityModels());
        }));

        this.registerBewlr(WeaversParadiseItems.RING, "ring", this.bewlrFactory(() -> {
            var mc = Minecraft.getInstance();
            return new RingRenderer(mc.getBlockEntityRenderDispatcher(), mc.getEntityModels());
        }));

        this.registerBewlr(WeaversParadiseItems.CAT_RING, "cat_ring", this.bewlrFactory(() -> {
            var mc = Minecraft.getInstance();
            return new CatRingRenderer(mc.getBlockEntityRenderDispatcher(), mc.getEntityModels());
        }));

        this.registerBewlr(WeaversParadiseItems.HEART, "heart", this.bewlrFactory(() -> {
            var mc = Minecraft.getInstance();
            return new HeartRenderer(mc.getBlockEntityRenderDispatcher(), mc.getEntityModels());
        }));

        this.registerBewlr(WeaversParadiseItems.PLATE, "plate", this.bewlrFactory(() -> {
            var mc = Minecraft.getInstance();
            return new PlateRenderer(mc.getBlockEntityRenderDispatcher(), mc.getEntityModels());
        }));

        Supplier<net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer> pantsFactory = this.bewlrFactory(() -> {
            var mc = Minecraft.getInstance();
            return new PantsRenderer(mc.getBlockEntityRenderDispatcher(), mc.getEntityModels());
        });
        this.registerBewlr(WeaversParadiseItems.PANTS_JEANS, "pants", pantsFactory);
        this.registerBewlr(WeaversParadiseItems.PANTS_COTTON, "pants", pantsFactory);
        this.registerBewlr(WeaversParadiseItems.PANTS_SILK, "pants", pantsFactory);
        this.registerBewlr(WeaversParadiseItems.PANTS_WOOL, "pants", pantsFactory);

        Supplier<net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer> capeFactory = this.bewlrFactory(() -> {
            var mc = Minecraft.getInstance();
            return new CapeRenderer(mc.getBlockEntityRenderDispatcher(), mc.getEntityModels());
        });
        this.registerBewlr(WeaversParadiseItems.COTTON_CAPE, "cape", capeFactory);
        this.registerBewlr(WeaversParadiseItems.SILK_CAPE, "cape", capeFactory);
        this.registerBewlr(WeaversParadiseItems.WOOL_CAPE, "cape", capeFactory);

        this.registerBewlr(WeaversParadiseItems.COTTON_SKIRT, "skirt", this.bewlrFactory(() -> {
            var mc = Minecraft.getInstance();
            return new CottonSkirtRenderer(mc.getBlockEntityRenderDispatcher(), mc.getEntityModels());
        }));

        this.registerBewlr(WeaversParadiseItems.USHANKA, "ushanka", this.bewlrFactory(() -> {
            var mc = Minecraft.getInstance();
            return new UshankaRenderer(mc.getBlockEntityRenderDispatcher(), mc.getEntityModels());
        }));

        this.registerBewlr(WeaversParadiseItems.CAP, "cap", this.bewlrFactory(() -> {
            var mc = Minecraft.getInstance();
            return new CapRenderer(mc.getBlockEntityRenderDispatcher(), mc.getEntityModels());
        }));

        this.registerBewlr(WeaversParadiseItems.POMPON_HAT, "pompon_hat", this.bewlrFactory(() -> {
            var mc = Minecraft.getInstance();
            return new PomponHatRenderer(mc.getBlockEntityRenderDispatcher(), mc.getEntityModels());
        }));

        this.registerBewlr(WeaversParadiseItems.FISH, "fish", this.bewlrFactory(() -> {
            var mc = Minecraft.getInstance();
            return new FishRenderer(mc.getBlockEntityRenderDispatcher(), mc.getEntityModels());
        }));

        this.registerBewlr(WeaversParadiseItems.BLAHAJ, "blahaj", this.bewlrFactory(() -> {
            var mc = Minecraft.getInstance();
            return new BlahajRenderer(mc.getBlockEntityRenderDispatcher(), mc.getEntityModels());
        }));
    }

    // ===================== Layer Definitions (EntityRenderersEvent.RegisterLayerDefinitions) =====================

    private void registerLayerDefinitions() {
        EntityModelLayerRegistry.registerModelLayer(ThighHighsModel.LAYER_LOCATION, ThighHighsModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(AstolfoModel.LAYER_LOCATION, AstolfoModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(BridgetModel.LAYER_LOCATION, BridgetModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(FelixModel.LAYER_LOCATION, FelixModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(GriffithModel.LAYER_LOCATION, GriffithModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(NikoModel.LAYER_LOCATION, NikoModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(GiselleModel.LAYER_LOCATION, GiselleModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(GabrielModel.LAYER_LOCATION, GabrielModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(MikkelaModel.LAYER_LOCATION, MikkelaModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ExpieModel.LAYER_LOCATION, ExpieModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(GasterModel.LAYER_LOCATION, GasterModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(RalseiModel.LAYER_LOCATION, RalseiModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(MinosPrimeModel.LAYER_LOCATION, MinosPrimeModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(JayaUtomoModel.LAYER_LOCATION, JayaUtomoModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(HandWarmersModel.LAYER_LOCATION, HandWarmersModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(UpperWearModel.LAYER_LOCATION, UpperWearModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(SlimPlushieModel.LAYER_LOCATION, SlimPlushieModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(WidePlushieModel.LAYER_LOCATION, WidePlushieModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ChokerModel.LAYER_LOCATION, ChokerModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(BellModel.LAYER_LOCATION, BellModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(PantsModel.LAYER_LOCATION, PantsModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(BasicRingModel.LAYER_LOCATION, BasicRingModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(CatRingModel.LAYER_LOCATION, CatRingModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(HeartModel.LAYER_LOCATION, HeartModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(BasicPlateModel.LAYER_LOCATION, BasicPlateModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(FishModel.LAYER_LOCATION, FishModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(CottonSkirtModel.LAYER_LOCATION, CottonSkirtModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(UshankaModel.LAYER_LOCATION, UshankaModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(CapModel.LAYER_LOCATION, CapModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(PomponHatModel.LAYER_LOCATION, PomponHatModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(BlahajModel.LAYER_LOCATION, BlahajModel::createBodyLayer);
    }

    // ===================== Feature Layers (EntityRenderersEvent.AddLayers) =====================

    private void registerFeatureLayers() {
        LivingEntityFeatureRendererRegistrationCallback.EVENT.register((entityType, entityRenderer, registrationHelper, context) -> {
            if (entityRenderer instanceof net.minecraft.client.renderer.entity.LivingEntityRenderer livingRenderer) {
                registrationHelper.register(new ThighHighsRenderLayer(livingRenderer));
                registrationHelper.register(new UpperwearRenderLayer(livingRenderer));
                registrationHelper.register(new PantsRenderLayer(livingRenderer));
                registrationHelper.register(new HandWarmersRenderLayer(livingRenderer));
                registrationHelper.register(new CottonSkirtRenderLayer(livingRenderer));
                registrationHelper.register(new HatRenderLayer(livingRenderer));
            }
        });
        // Fabric вызывает этот колбэк и для рендереров игроков (все скины) — отдельный цикл по skins не нужен.
    }

    // ===================== Menu Screens =====================

    private void registerMenuScreens() {
        MenuScreens.register(WeaversParadiseInterfaces.STRING_MENU, xox.labvorty.weaversparadise.gui.screen.StringScreen::new);
        MenuScreens.register(WeaversParadiseInterfaces.CLOTHCRAFTING_MENU, xox.labvorty.weaversparadise.gui.screen.ClothcraftingScreen::new);
        MenuScreens.register(WeaversParadiseInterfaces.DYEMAKING_MENU, xox.labvorty.weaversparadise.gui.screen.DyemakingScreen::new);
        MenuScreens.register(WeaversParadiseInterfaces.DYEING_MENU, xox.labvorty.weaversparadise.gui.screen.DyeingScreen::new);
    }

    // ===================== Entity / BlockEntity Renderers =====================

    private void registerEntityAndBlockEntityRenderers() {
        EntityRendererRegistry.register(WeaversParadiseEntityTypes.HANGING_FLAG_ENTITY, HangingFlagEntityRenderer::new);
        BlockEntityRendererRegistry.register(WeaversParadiseBlockEntities.PLUSHIE_BE, PlushieBlockEntityRenderer::new);
        BlockEntityRendererRegistry.register(WeaversParadiseBlockEntities.BLAHAJ_BE, BlahajBlockEntityRenderer::new);
    }

    // ===================== Particles =====================

    private void registerParticles() {
        ParticleFactoryRegistry.getInstance().register(
                WeaversParadiseParticles.STARBLOOM_PARTICLE,
                StarbloomParticleProvider::new);
    }

    // ===================== KeyMappings =====================

    private void registerKeyMappings() {
        KeyBindingHelper.registerKeyBinding(WeaversParadiseKeyMappings.MINIGAME_UP_BUTTON);
        KeyBindingHelper.registerKeyBinding(WeaversParadiseKeyMappings.MINIGAME_DOWN_BUTTON);
        KeyBindingHelper.registerKeyBinding(WeaversParadiseKeyMappings.MINIGAME_LEFT_BUTTON);
        KeyBindingHelper.registerKeyBinding(WeaversParadiseKeyMappings.MINIGAME_RIGHT_BUTTON);
        KeyBindingHelper.registerKeyBinding(WeaversParadiseKeyMappings.OPEN_UPPER_WEAR_BUTTON);
        KeyBindingHelper.registerKeyBinding(WeaversParadiseKeyMappings.TRINKET_SOUND);

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            WeaversParadiseKeyMappings.OPEN_UPPER_WEAR_BUTTON.consumeClick();
            WeaversParadiseKeyMappings.TRINKET_SOUND.consumeClick();
        });
    }

    // ===================== Tooltip Components =====================

    private void registerTooltipComponents() {
        TooltipComponentCallback.EVENT.register(data -> {
            if (data instanceof ClothingTooltipComponent c) {
                return new ClothingClientTooltipComponent(c.getQualityTextures(), c.getEntries());
            }
            if (data instanceof DyeTooltipComponent d) {
                return new DyeClientTooltipComponent(d.getDyeIcon(), d.getText(), d.getType(), d.getLightValue(), d.getPrimaryColor(), d.getSecondaryColor(), d.isCore());
            }
            if (data instanceof QualityTooltipComponent q) {
                return new QualityClientTooltipComponent(q.getTextures());
            }
            return null;
        });
    }

    // ===================== Item Colors =====================

    private void registerColorProviders() {
        ColorProviderRegistry.ITEM.register((stack, layer) -> {
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
        }, WeaversParadiseItems.BOTTLED_DYE);

        ColorProviderRegistry.ITEM.register((stack, layer) -> {
            if (layer == 0) {
                return -1;
            }

            if (stack.getItem() instanceof ChromaticBloomFruitItem) {
                Minecraft minecraft = Minecraft.getInstance();
                int ticks = 0;
                if (minecraft.level != null) {
                    ticks = (int) minecraft.level.getGameTime();
                }
                ticks += (layer * 2);

                float speed = 0.05F;

                float red = Mth.clamp((float) (Math.sin(ticks * speed) * 0.5 + 0.5), 0, 1);
                float green = Mth.clamp((float) (Math.sin(ticks * speed + 2 * Math.PI / 3) * 0.5 + 0.5), 0, 1);
                float blue = Mth.clamp((float) (Math.sin(ticks * speed + 4 * Math.PI / 3) * 0.5 + 0.5), 0, 1);

                int trueRed = (int) (red * 255);
                int trueGreen = (int) (green * 255);
                int trueBlue = (int) (blue * 255);

                return 255 << 24 | trueRed << 16 | trueGreen << 8 | trueBlue;
            }

            return -1;
        }, WeaversParadiseItems.CHROMATIC_BLOOM_FRUIT);

        ColorProviderRegistry.ITEM.register((stack, layer) -> {
            if (stack.getItem() instanceof PigmentItem pureDyeItem) {
                return pureDyeItem.getDyeColor(stack);
            }

            return -1;
        }, WeaversParadiseItems.PURE_DYE);

        ColorProviderRegistry.ITEM.register((stack, layer) -> {
            if (stack.getItem() instanceof ChromaticDustItem) {
                Minecraft minecraft = Minecraft.getInstance();
                int ticks = 0;
                if (minecraft.level != null) {
                    ticks = (int) minecraft.level.getGameTime();
                }

                float speed = 0.05F;

                float red = Mth.clamp((float) (Math.sin(ticks * speed) * 0.5 + 0.5), 0, 1);
                float green = Mth.clamp((float) (Math.sin(ticks * speed + 2 * Math.PI / 3) * 0.5 + 0.5), 0, 1);
                float blue = Mth.clamp((float) (Math.sin(ticks * speed + 4 * Math.PI / 3) * 0.5 + 0.5), 0, 1);

                int trueRed = (int) (red * 255);
                int trueGreen = (int) (green * 255);
                int trueBlue = (int) (blue * 255);

                return 255 << 24 | trueRed << 16 | trueGreen << 8 | trueBlue;
            }

            return -1;
        }, WeaversParadiseItems.CHROMATIC_DUST);
    }

    // ===================== Trinket renderers (CuriosRendererRegistry) =====================

    /**
     * Trinkets принимает ГОТОВЫЙ инстанс рендерера, а Curios — ленивый supplier.
     * Рендереры пекут модели в конструкторе, поэтому оборачиваем в ленивую обёртку:
     * реальный рендерер создаётся при первом использовании (после загрузки моделей).
     */
    private static void registerLazyTrinketRenderer(net.minecraft.world.item.Item item, Supplier<TrinketRenderer> supplier) {
        TrinketRendererRegistry.registerRenderer(item, new TrinketRenderer() {
            private TrinketRenderer delegate;

            @Override
            public void render(ItemStack stack, dev.emi.trinkets.api.SlotReference slotReference,
                               net.minecraft.client.model.EntityModel<? extends net.minecraft.world.entity.LivingEntity> contextModel,
                               com.mojang.blaze3d.vertex.PoseStack poseStack,
                               net.minecraft.client.renderer.MultiBufferSource multiBufferSource,
                               int light, net.minecraft.world.entity.LivingEntity entity,
                               float limbAngle, float limbDistance, float tickDelta, float animationProgress,
                               float headYaw, float headPitch) {
                if (this.delegate == null) {
                    this.delegate = supplier.get();
                }
                this.delegate.render(stack, slotReference, contextModel, poseStack, multiBufferSource, light, entity,
                        limbAngle, limbDistance, tickDelta, animationProgress, headYaw, headPitch);
            }
        });
    }

    private void registerTrinketRenderers() {
        registerLazyTrinketRenderer(WeaversParadiseItems.THIGH_HIGHS_COTTON, ThighHighsCurioRenderer::new);
        registerLazyTrinketRenderer(WeaversParadiseItems.THIGH_HIGHS_WOOL, ThighHighsCurioRenderer::new);
        registerLazyTrinketRenderer(WeaversParadiseItems.THIGH_HIGHS_SILK, ThighHighsCurioRenderer::new);

        registerLazyTrinketRenderer(WeaversParadiseItems.LEATHER_GLOVES, LeatherGlovesRenderer::new);
        registerLazyTrinketRenderer(WeaversParadiseItems.CHOKER, ChokerCurioRenderer::new);
        registerLazyTrinketRenderer(WeaversParadiseItems.BELL, xox.labvorty.weaversparadise.renderers.curios.BellRenderer::new);
        registerLazyTrinketRenderer(WeaversParadiseItems.RING, xox.labvorty.weaversparadise.renderers.curios.RingRenderer::new);
        registerLazyTrinketRenderer(WeaversParadiseItems.CAT_RING, xox.labvorty.weaversparadise.renderers.curios.CatRingRenderer::new);
        registerLazyTrinketRenderer(WeaversParadiseItems.HEART, xox.labvorty.weaversparadise.renderers.curios.HeartRenderer::new);
        registerLazyTrinketRenderer(WeaversParadiseItems.PLATE, xox.labvorty.weaversparadise.renderers.curios.PlateRenderer::new);
        registerLazyTrinketRenderer(WeaversParadiseItems.FISH, xox.labvorty.weaversparadise.renderers.curios.FishRenderer::new);

        registerLazyTrinketRenderer(WeaversParadiseItems.HAND_WARMERS_COTTON, HandWarmersCurioRenderer::new);
        registerLazyTrinketRenderer(WeaversParadiseItems.HAND_WARMERS_SILK, HandWarmersCurioRenderer::new);
        registerLazyTrinketRenderer(WeaversParadiseItems.HAND_WARMERS_WOOL, HandWarmersCurioRenderer::new);

        registerLazyTrinketRenderer(WeaversParadiseItems.T_SHIRT, UpperwearCurioRenderer::new);
        registerLazyTrinketRenderer(WeaversParadiseItems.TANK_TOP, UpperwearCurioRenderer::new);
        registerLazyTrinketRenderer(WeaversParadiseItems.WOOL_VEST, UpperwearCurioRenderer::new);
        registerLazyTrinketRenderer(WeaversParadiseItems.SHIRT_COTTON, UpperwearCurioRenderer::new);
        registerLazyTrinketRenderer(WeaversParadiseItems.SHIRT_SILK, UpperwearCurioRenderer::new);
        registerLazyTrinketRenderer(WeaversParadiseItems.LONG_SLEEVE_COTTON, UpperwearCurioRenderer::new);
        registerLazyTrinketRenderer(WeaversParadiseItems.SWEATER_WOOL, UpperwearCurioRenderer::new);
        registerLazyTrinketRenderer(WeaversParadiseItems.PONCHO, UpperwearCurioRenderer::new);
        registerLazyTrinketRenderer(WeaversParadiseItems.COTTON_CROP_TOP_LONG_SLEEVED, UpperwearCurioRenderer::new);

        registerLazyTrinketRenderer(WeaversParadiseItems.POMPON_HAT, PomponHatCurioRenderer::new);
        registerLazyTrinketRenderer(WeaversParadiseItems.CAP, CapCurioRenderer::new);
        registerLazyTrinketRenderer(WeaversParadiseItems.USHANKA, UshankaCurioRenderer::new);

        registerLazyTrinketRenderer(WeaversParadiseItems.PANTS_JEANS, PantsCurioRenderer::new);
        registerLazyTrinketRenderer(WeaversParadiseItems.PANTS_COTTON, PantsCurioRenderer::new);
        registerLazyTrinketRenderer(WeaversParadiseItems.PANTS_SILK, PantsCurioRenderer::new);
        registerLazyTrinketRenderer(WeaversParadiseItems.PANTS_WOOL, PantsCurioRenderer::new);

        registerLazyTrinketRenderer(WeaversParadiseItems.COTTON_SKIRT, CottonSkirtCurioRenderer::new);
    }
}
