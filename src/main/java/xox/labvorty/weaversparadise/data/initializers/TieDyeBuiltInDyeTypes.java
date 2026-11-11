package xox.labvorty.weaversparadise.data.initializers;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import org.joml.Vector3f;
import xox.labvorty.vortylib.data.text.ComponentFormatter;
import xox.labvorty.vortylib.init.VortyLibRenderTypes;
import xox.labvorty.weaversparadise.data.tooltip_components.DyeTypeRegistry;
import xox.labvorty.weaversparadise.data.tooltip_components.ItemDyeIcon;
import xox.labvorty.weaversparadise.init.WeaversParadiseItems;

import java.util.List;

public class TieDyeBuiltInDyeTypes {
    public static void init() {
        registerTieDye(
                "pride_tie_dye",
                new ItemDyeIcon(WeaversParadiseItems.FLAG_PRIDE.get()),
                "pride",
                List.of(
                        (0xE40303),
                        (0xFF8C00),
                        (0xFFED00),
                        (0x008026),
                        (0x004DFF),
                        (0x750787)
                )
        );

        registerTieDye(
                "asexual_tie_dye",
                new ItemDyeIcon(WeaversParadiseItems.FLAG_ASEXUAL.get()),
                "asexual",
                List.of(
                        (0x2E2E2E),
                        (0xA3A3A3),
                        (0xFFFFFF),
                        (0x800080)
                )
        );

        registerTieDye(
                "aromantic_tie_dye",
                new ItemDyeIcon(WeaversParadiseItems.FLAG_AROMANTIC.get()),
                "aromantic",
                List.of(
                        (0x3DA542),
                        (0xA7D379),
                        (0xFFFFFF),
                        (0xA9A9A9),
                        (0x2E2E2E)
                )
        );

        registerTieDye(
                "aroace_tie_dye",
                new ItemDyeIcon(WeaversParadiseItems.FLAG_AROACE.get()),
                "aroace",
                List.of(
                        (0xE28C00),
                        (0xFFD700),
                        (0xFFFFFF),
                        (0x00A4E0),
                        (0x800080)
                )
        );

        registerTieDye(
                "agender_tie_dye",
                new ItemDyeIcon(WeaversParadiseItems.FLAG_AGENDER.get()),
                "agender",
                List.of(
                        (0x2E2E2E),
                        (0xB9B9B9),
                        (0xFFFFFF),
                        (0xB8F483),
                        (0xFFFFFF),
                        (0xB9B9B9),
                        (0x2E2E2E)
                )
        );

        registerTieDye(
                "bisexual_tie_dye",
                new ItemDyeIcon(WeaversParadiseItems.FLAG_BISEXUAL.get()),
                "bisexual",
                List.of(
                        (0xD60270),
                        (0xD60270),
                        (0x9B4F96),
                        (0x0038A8),
                        (0x0038A8)
                )
        );

        registerTieDye(
                "demiboy_tie_dye",
                new ItemDyeIcon(WeaversParadiseItems.FLAG_DEMIBOY.get()),
                "demiboy",
                List.of(
                        (0x424242),
                        (0x7F7F7F),
                        (0x9AD9EB),
                        (0xFFFFFF),
                        (0x9AD9EB),
                        (0x7F7F7F),
                        (0x424242)
                )
        );

        registerTieDye(
                "demigender_tie_dye",
                new ItemDyeIcon(WeaversParadiseItems.FLAG_DEMIGENDER.get()),
                "demigender",
                List.of(
                        (0x424242),
                        (0x7F7F7F),
                        (0xF9F06B),
                        (0xFFFFFF),
                        (0xF9F06B),
                        (0x7F7F7F),
                        (0x424242)
                )
        );

        registerTieDye(
                "demigirl_tie_dye",
                new ItemDyeIcon(WeaversParadiseItems.FLAG_DEMIGIRL.get()),
                "demigirl",
                List.of(
                        (0x424242),
                        (0x7F7F7F),
                        (0xFCACC9),
                        (0xFFFFFF),
                        (0xFCACC9),
                        (0x7F7F7F),
                        (0x424242)
                )
        );

        registerTieDye(
                "gay_tie_dye",
                new ItemDyeIcon(WeaversParadiseItems.FLAG_GAY.get()),
                "gay",
                List.of(
                        (0x078D70),
                        (0x26CEAA),
                        (0x98E8C1),
                        (0xFFFFFF),
                        (0x7BADE3),
                        (0x5049CC),
                        (0x3D1A78)
                )
        );

        registerTieDye(
                "genderfluid_tie_dye",
                new ItemDyeIcon(WeaversParadiseItems.FLAG_GENDERFLUID.get()),
                "genderfluid",
                List.of(
                        (0xFF75A2),
                        (0xFFFFFF),
                        (0xBE18D6),
                        (0x2E2E2E),
                        (0x333EBD)
                )
        );

        registerTieDye(
                "genderqueer_tie_dye",
                new ItemDyeIcon(WeaversParadiseItems.FLAG_GENDERQUEER.get()),
                "genderqueer",
                List.of(
                        (0xB57EDC),
                        (0xFFFFFF),
                        (0x49822B)
                )
        );

        registerTieDye(
                "intersex_tie_dye",
                new ItemDyeIcon(WeaversParadiseItems.FLAG_INTERSEX.get()),
                "intersex",
                List.of(
                        (0xFFD800),
                        (0xFFD800),
                        (0x7902AA),
                        (0xFFD800),
                        (0x7902AA),
                        (0xFFD800),
                        (0xFFD800)
                )
        );

        registerTieDye(
                "lesbian_tie_dye",
                new ItemDyeIcon(WeaversParadiseItems.FLAG_LESBIAN.get()),
                "lesbian",
                List.of(
                        (0xD62900),
                        (0xFF9B55),
                        (0xFFFFFF),
                        (0xD461A6),
                        (0xA50062)
                )
        );

        registerTieDye(
                "nonbinary_tie_dye",
                new ItemDyeIcon(WeaversParadiseItems.FLAG_NONBINARY.get()),
                "nonbinary",
                List.of(
                        (0xFFF430),
                        (0xFFFFFF),
                        (0x9C59D1),
                        (0x2E2E2E)
                )
        );

        registerTieDye(
                "pansexual_tie_dye",
                new ItemDyeIcon(WeaversParadiseItems.FLAG_PANSEXUAL.get()),
                "pansexual",
                List.of(
                        (0xFF218C),
                        (0xFFD800),
                        (0x21B1FF)
                )
        );

        registerTieDye(
                "trans_tie_dye",
                new ItemDyeIcon(WeaversParadiseItems.FLAG_TRANS.get()),
                "trans",
                List.of(
                        (0x5BCEFA),
                        (0xF5A9B8),
                        (0xFFFFFF),
                        (0xF5A9B8),
                        (0x5BCEFA)
                )
        );

        registerTieDye(
                "transfeminine_tie_dye",
                new ItemDyeIcon(WeaversParadiseItems.FLAG_TRANSFEMININE.get()),
                "transfeminine",
                List.of(
                        (0x72DFFF),
                        (0xFFE1ED),
                        (0xFFB5D6),
                        (0xFE8CBF),
                        (0xFFB5D6),
                        (0xFFE1ED),
                        (0x72DFFF)
                )
        );

        registerTieDye(
                "transmasculine_tie_dye",
                new ItemDyeIcon(WeaversParadiseItems.FLAG_TRANSMASCULINE.get()),
                "transmasculine",
                List.of(
                        (0xFF8ABE),
                        (0xCEF4FD),
                        (0x9AEBFE),
                        (0x74DFFF),
                        (0x9AEBFE),
                        (0xCEF4FD),
                        (0xFF8ABE)
                )
        );
    }

    private static void registerTieDye(String id, ItemDyeIcon icon, String langKey, List<Integer> colors) {
        DyeTypeRegistry.registerDyeType(
                id,
                icon,
                Component.translatable("weaversparadise.dye_text." + langKey + "_tie_dye"),
                (dyeData) -> {
                    Minecraft minecraft = Minecraft.getInstance();
                    int ticks = 0;
                    if (minecraft.level != null) {
                        ticks = (int)minecraft.level.getGameTime();
                    }

                    return ComponentFormatter.gradientText(dyeData.getComponent().getString(), colors, ticks);
                },
                (dyeDataColor) -> {
                    if (dyeDataColor.layer() == 1) {
                        Minecraft minecraft = Minecraft.getInstance();
                        int ticks = 0;
                        if (minecraft.level != null) {
                            ticks = (int)minecraft.level.getGameTime();
                        }

                        return ComponentFormatter.getCycledColor(colors, ticks);
                    }

                    return -1;
                },
                (renderData) -> {
                    return VortyLibRenderTypes.getEntityTieDye(
                            renderData.resourceLocation(),
                            Math.min(colors.size(), 8),
                            paletteColor(colors, 0),
                            paletteColor(colors, 1),
                            paletteColor(colors, 2),
                            paletteColor(colors, 3),
                            paletteColor(colors, 4),
                            paletteColor(colors, 5),
                            paletteColor(colors, 6),
                            paletteColor(colors, 7)
                    );
                }
        );
    }

    private static Vector3f paletteColor(List<Integer> colors, int index) {
        if (index >= colors.size()) {
            return new Vector3f(1, 1, 1);
        }

        int color = colors.get(index);
        return new Vector3f(
                ((color >> 16) & 0xFF) / 255.0f,
                ((color >> 8) & 0xFF) / 255.0f,
                (color & 0xFF) / 255.0f
        );
    }
}