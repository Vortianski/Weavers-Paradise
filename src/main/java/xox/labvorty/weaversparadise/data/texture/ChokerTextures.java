package xox.labvorty.weaversparadise.data.texture;

import net.minecraft.resources.ResourceLocation;

public class ChokerTextures {
    private static final String CLOTHING_TYPE = "choker";

    private static final String STENCIL_DEFAULT = "default";
    private static final String STENCIL_HALF = "half";
    private static final String STENCIL_VERTICAL_LINES = "vertical_lines";
    private static final String STENCIL_SMALL_LINES = "small_lines";
    private static final String STENCIL_BIG_LINES = "big_lines";
    private static final String STENCIL_CHECKERS_SMALL = "checkers_small";

    public static void register() {
        registerDefaultVariants();
        registerPatternVariants();
    }

    private static void registerDefaultVariants() {
        registerTexture(
                "pri",
                STENCIL_DEFAULT,
                ResourceLocation.fromNamespaceAndPath(
                        "weaversparadise",
                        "textures/clothing/chocker_half_pri.png"
                ),
                ResourceLocation.fromNamespaceAndPath(
                        "weaversparadise",
                        "textures/clothing/chocker_half_pri.png"
                ),
                false
        );
        registerTexture(
                "sec",
                STENCIL_DEFAULT,
                ResourceLocation.fromNamespaceAndPath(
                        "weaversparadise",
                        "textures/clothing/chocker_half_sec.png"
                ),
                ResourceLocation.fromNamespaceAndPath(
                        "weaversparadise",
                        "textures/clothing/chocker_half_sec.png"
                ),
                false
        );
    }

    private static void registerPatternVariants() {
        preRegister(
                STENCIL_HALF,
                "half_1.png",
                "half_2.png",
                true
        );
        preRegister(
                STENCIL_VERTICAL_LINES,
                "lines_vertical_1.png",
                "lines_vertical_2.png",
                true
        );
        preRegister(
                STENCIL_SMALL_LINES,
                "lines_small_1.png",
                "lines_small_2.png",
                true
        );
        preRegister(
                STENCIL_BIG_LINES,
                "lines_big_1.png",
                "lines_big_2.png",
                true
        );
        preRegister(
                STENCIL_CHECKERS_SMALL,
                "checkers_small_1.png",
                "checkers_small_2.png",
                true
        );
    }

    private static void preRegister(String stencil, String textureOne, String textureTwo, boolean renderType) {
        registerTexture(
                "pri",
                stencil,
                ResourceLocation.fromNamespaceAndPath("weaversparadise", "textures/clothing/variants/choker/choker_half_pri_" + textureOne),
                ResourceLocation.fromNamespaceAndPath("weaversparadise", "textures/clothing/variants/choker/choker_half_pri_" + textureTwo),
                renderType
        );
        registerTexture(
                "sec",
                stencil,
                ResourceLocation.fromNamespaceAndPath("weaversparadise", "textures/clothing/variants/choker/choker_half_sec_" + textureOne),
                ResourceLocation.fromNamespaceAndPath("weaversparadise", "textures/clothing/variants/choker/choker_half_sec_" + textureTwo),
                renderType
        );
    }

    private static void registerTexture(String material, String stencil, ResourceLocation textureOne, ResourceLocation textureTwo, boolean renderType) {
        TextureRegistry.registerTexture(
                CLOTHING_TYPE,
                stencil,
                material,
                textureOne,
                textureTwo,
                renderType
        );
    }
}
