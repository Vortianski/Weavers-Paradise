package xox.labvorty.weaversparadise.init;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import xox.labvorty.vortylib.data.config.ConfigHolder;
import xox.labvorty.vortylib.data.config.ModEntry;
import xox.labvorty.vortylib.data.config.ModRegistry;
import xox.labvorty.vortylib.data.config.SocialType;
import xox.labvorty.weaversparadise.configs.ClientConfig;
import xox.labvorty.weaversparadise.configs.CommonConfig;
import xox.labvorty.weaversparadise.data.initializers.BuiltInDyeTypes;
import xox.labvorty.weaversparadise.data.initializers.BuiltInStencils;
import xox.labvorty.weaversparadise.data.initializers.BuiltInTextures;
import xox.labvorty.weaversparadise.data.texture.StencilRegistry;
import xox.labvorty.weaversparadise.data.texture.TextureRegistry;
import xox.labvorty.weaversparadise.data.tooltip_components.DyeTypeRegistry;
import xox.labvorty.weaversparadise.events.ReloadEvent;

import java.util.List;

@EventBusSubscriber
public class WeaversParadiseInitialization {
    @SubscribeEvent
    public static void onCommon(FMLCommonSetupEvent event) {
        event.enqueueWork(WeaversParadiseInitialization::reloadCommon);
    }

    @SubscribeEvent
    public static void onClient(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            WeaversParadiseInitialization.reloadClient();
            ModRegistry.register(
                    ModEntry.builder("weaversparadise", Component.literal("Weavers Paradise"))
                            .banner(ResourceLocation.fromNamespaceAndPath("weaversparadise", "textures/gui/weaversparadise.png"), 115, 64)
                            .featuredItemsAllFromModId()
                            .clientConfig(
                                    ConfigHolder.builder(ClientConfig.SPEC)
                                            .addBoolean(Component.literal("Patchouli warning"), Component.literal("Display a message if Patchouli is not installed when joining the game"), ClientConfig.PATCHOULI_WARNING, true)
                                            .addBoolean(Component.literal("Update warnings"), Component.literal("Display a message if your mod version is outdated"), ClientConfig.VERSION_WARNING, true)
                                            .addItemList(Component.literal("Thigh Highs Restrictor"), Component.literal("Items that will prevent Thigh Highs from rendering"), ClientConfig.THIGH_HIGHS_RESTRICTOR, List.of("weaversparadise:astolfo_cosmetics", "weaversparadise:bridget_cosmetics", "weaversparadise:expie_cosmetics", "weaversparadise:felix_cosmetics", "weaversparadise:gabriel_cosmetics", "weaversparadise:gaster_cosmetics", "weaversparadise:giselle_cosmetics", "weaversparadise:griffith_cosmetics", "weaversparadise:jaya_utomo_cosmetics", "weaversparadise:mikkela_cosmetics", "weaversparadise:minos_prime_cosmetics", "weaversparadise:ralsei_cosmetics", "weaversparadise:niko_cosmetics"))
                                            .addItemList(Component.literal("Hand Warmers Restrictor"), Component.literal("Items that will prevent Hand Warmers from rendering"), ClientConfig.HAND_WARMERS_RESTRICTOR, List.of("weaversparadise:astolfo_cosmetics", "weaversparadise:bridget_cosmetics", "weaversparadise:expie_cosmetics", "weaversparadise:felix_cosmetics", "weaversparadise:gabriel_cosmetics", "weaversparadise:gaster_cosmetics", "weaversparadise:giselle_cosmetics", "weaversparadise:griffith_cosmetics", "weaversparadise:jaya_utomo_cosmetics", "weaversparadise:mikkela_cosmetics", "weaversparadise:minos_prime_cosmetics", "weaversparadise:ralsei_cosmetics", "weaversparadise:niko_cosmetics"))
                                            .addItemList(Component.literal("Pants Restrictors"), Component.literal("Items that will prevent Pants from rendering"), ClientConfig.PANTS_RESTRICTOR, List.of("weaversparadise:astolfo_cosmetics", "weaversparadise:bridget_cosmetics", "weaversparadise:expie_cosmetics", "weaversparadise:felix_cosmetics", "weaversparadise:gabriel_cosmetics", "weaversparadise:gaster_cosmetics", "weaversparadise:giselle_cosmetics", "weaversparadise:griffith_cosmetics", "weaversparadise:jaya_utomo_cosmetics", "weaversparadise:mikkela_cosmetics", "weaversparadise:minos_prime_cosmetics", "weaversparadise:ralsei_cosmetics", "weaversparadise:niko_cosmetics"))
                                            .addItemList(Component.literal("Shirt Restrictors"), Component.literal("Items that will prevent Shirt from rendering"), ClientConfig.SHIRT_RESTRICTOR, List.of("weaversparadise:astolfo_cosmetics", "weaversparadise:bridget_cosmetics", "weaversparadise:expie_cosmetics", "weaversparadise:felix_cosmetics", "weaversparadise:gabriel_cosmetics", "weaversparadise:gaster_cosmetics", "weaversparadise:giselle_cosmetics", "weaversparadise:griffith_cosmetics", "weaversparadise:jaya_utomo_cosmetics", "weaversparadise:mikkela_cosmetics", "weaversparadise:minos_prime_cosmetics", "weaversparadise:ralsei_cosmetics", "weaversparadise:niko_cosmetics"))
                                            .addItemList(Component.literal("Choker Restrictors"), Component.literal("Items that will prevent Choker and it's trinkets from rendering"), ClientConfig.CHOKER_RESTRICTOR, List.of("weaversparadise:astolfo_cosmetics", "weaversparadise:bridget_cosmetics", "weaversparadise:expie_cosmetics", "weaversparadise:felix_cosmetics", "weaversparadise:gabriel_cosmetics", "weaversparadise:gaster_cosmetics", "weaversparadise:giselle_cosmetics", "weaversparadise:griffith_cosmetics", "weaversparadise:jaya_utomo_cosmetics", "weaversparadise:mikkela_cosmetics", "weaversparadise:minos_prime_cosmetics", "weaversparadise:ralsei_cosmetics", "weaversparadise:niko_cosmetics"))
                                            .addItemList(Component.literal("Hats Restrictors"), Component.literal("Items that will prevent Hats from rendering"), ClientConfig.HAT_RESTRICTOR, List.of("weaversparadise:astolfo_cosmetics", "weaversparadise:bridget_cosmetics", "weaversparadise:expie_cosmetics", "weaversparadise:felix_cosmetics", "weaversparadise:gabriel_cosmetics", "weaversparadise:gaster_cosmetics", "weaversparadise:giselle_cosmetics", "weaversparadise:griffith_cosmetics", "weaversparadise:jaya_utomo_cosmetics", "weaversparadise:mikkela_cosmetics", "weaversparadise:minos_prime_cosmetics", "weaversparadise:ralsei_cosmetics", "weaversparadise:niko_cosmetics"))
                                            .addItemList(Component.literal("Skirts Restrictor"), Component.literal("Items that will prevent Skirts from rendering"), ClientConfig.SKIRT_RESTRICTOR, List.of("weaversparadise:astolfo_cosmetics", "weaversparadise:bridget_cosmetics", "weaversparadise:expie_cosmetics", "weaversparadise:felix_cosmetics", "weaversparadise:gabriel_cosmetics", "weaversparadise:gaster_cosmetics", "weaversparadise:giselle_cosmetics", "weaversparadise:griffith_cosmetics", "weaversparadise:jaya_utomo_cosmetics", "weaversparadise:mikkela_cosmetics", "weaversparadise:minos_prime_cosmetics", "weaversparadise:ralsei_cosmetics", "weaversparadise:niko_cosmetics"))
                                            .addBoolean(Component.literal("Full Cosplay Armor"), Component.literal("Should cosplay armor render fully, or use equipped armor to determine visible pieces"), ClientConfig.FULL_ARMOR, false)
                                            .build()
                            )
                            .commonConfig(
                                    ConfigHolder.builder(CommonConfig.SPEC)
                                            .addBoolean(Component.literal("Abilities active"), Component.literal("Whether items have special abilities when enchanted"), CommonConfig.ITEM_SPECIAL_ABILITIES, true)
                                            .addDouble(Component.literal("Armor Lootbox Chance"), Component.literal("Chance for armor lootbox to spawn in chests"), CommonConfig.ARMOR_LOOTBOX_CHANCE, 0.05, 0, 1, 2)
                                            .addDouble(Component.literal("Plushie Chance"), Component.literal("Chance for plushies to spawn in chests"), CommonConfig.PLUSHIE_CHANCE, 0.05, 0, 1, 2)
                                            .build()
                            )
                            .addSocial(SocialType.modrinth("https://modrinth.com/mod/weavers-paradise"))
                            .addSocial(SocialType.curseforge("https://www.curseforge.com/minecraft/mc-mods/weavers-paradise"))
                            .addSocial(SocialType.github("https://github.com/Vortianski/Weavers-Paradise/tree/master"))
                            .addSocial(SocialType.discord("https://discord.gg/ZesGqhGnAN"))
                            .addSocial(SocialType.kofi("https://ko-fi.com/vortianski"))
                            .build()
            );
        });
    }

    public static void reloadCommon() {
        StencilRegistry.clear();
        BuiltInStencils.register();
        NeoForge.EVENT_BUS.post(new ReloadEvent.Common());
    }

    public static void reloadClient() {
        DyeTypeRegistry.clear();
        TextureRegistry.clear();
        BuiltInDyeTypes.register();
        BuiltInTextures.register();
        NeoForge.EVENT_BUS.post(new ReloadEvent.Client());
    }
}