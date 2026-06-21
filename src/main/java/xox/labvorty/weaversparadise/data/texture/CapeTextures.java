package xox.labvorty.weaversparadise.data.texture;

import net.minecraft.resources.ResourceLocation;

public class CapeTextures {
    private static final String CLOTHING_TYPE = "cape";

    private static final String STENCIL_DEFAULT = "default";
    private static final String STENCIL_HALF = "half";
    private static final String STENCIL_CHECKERS = "checkers";
    private static final String STENCIL_VERTICAL_LINES = "vertical_lines";
    private static final String STENCIL_SMALL_LINES = "small_lines";
    private static final String STENCIL_BIG_LINES = "big_lines";
    private static final String STENCIL_CHECKERS_SMALL = "checkers_small";

    private static final String MATERIAL_COTTON = "cotton";
    private static final String MATERIAL_WOOL = "wool";
    private static final String MATERIAL_SILK = "silk";

    private static final String TEXTURE_BASE_PATH = "textures/clothing/";
    private static final String VARIANT_PATH = TEXTURE_BASE_PATH + "variants/cape/";

    public static void register() {
        registerDefaultVariants();
        registerPatternVariants();
    }

    private static void registerDefaultVariants() {
        registerTexture(STENCIL_DEFAULT, MATERIAL_COTTON, "cape_cotton.png", "cape_cotton.png", false);
        registerTexture(STENCIL_DEFAULT, MATERIAL_WOOL, "cape_wool.png", "cape_wool.png", false);
        registerTexture(STENCIL_DEFAULT, MATERIAL_SILK, "cape_silk.png", "cape_silk.png", false);
    }

    private static void registerPatternVariants() {
        registerPattern(STENCIL_HALF, "cotton_cape_half_1.png", "cotton_cape_half_2.png", "wool_cape_half_1.png", "wool_cape_half_2.png", "silk_cape_half_1.png", "silk_cape_half_2.png");
        registerPattern(STENCIL_CHECKERS, "cotton_cape_checkers_1.png", "cotton_cape_checkers_2.png", "wool_cape_checkers_1.png", "wool_cape_checkers_2.png", "silk_cape_checkers_1.png", "silk_cape_checkers_2.png");
        registerPattern(STENCIL_VERTICAL_LINES, "cotton_cape_vertical_lines_1.png", "cotton_cape_vertical_lines_2.png", "wool_cape_vertical_lines_1.png", "wool_cape_vertical_lines_2.png", "silk_cape_vertical_lines_1.png", "silk_cape_vertical_lines_2.png");
        registerPattern(STENCIL_SMALL_LINES, "cotton_cape_small_lines_1.png", "cotton_cape_small_lines_2.png", "wool_cape_small_lines_1.png", "wool_cape_small_lines_2.png", "silk_cape_small_lines_1.png", "silk_cape_small_lines_2.png");
        registerPattern(STENCIL_BIG_LINES, "cotton_cape_big_lines_1.png", "cotton_cape_big_lines_2.png", "wool_cape_big_lines_1.png", "wool_cape_big_lines_2.png", "silk_cape_big_lines_1.png", "silk_cape_big_lines_2.png");
        registerPattern(STENCIL_CHECKERS_SMALL, "cotton_cape_checkers_small_1.png", "cotton_cape_checkers_small_2.png", "wool_cape_checkers_small_1.png", "wool_cape_checkers_small_2.png", "silk_cape_checkers_small_1.png", "silk_cape_checkers_small_2.png");
    }

    private static void registerPattern(String stencil, String cotton1, String cotton2, String wool1, String wool2, String silk1, String silk2) {
        registerTexture(stencil, MATERIAL_COTTON, cotton1, cotton2, true);
        registerTexture(stencil, MATERIAL_WOOL, wool1, wool2, true);
        registerTexture(stencil, MATERIAL_SILK, silk1, silk2, true);
    }

    private static void registerTexture(String stencil, String material, String textureOneFile, String textureTwoFile, boolean renderType) {
        TextureRegistry.registerTexture(
                CLOTHING_TYPE,
                stencil,
                material,
                ResourceLocation.fromNamespaceAndPath("weaversparadise", stencil.equals(STENCIL_DEFAULT) ? TEXTURE_BASE_PATH + textureOneFile : VARIANT_PATH + textureOneFile),
                ResourceLocation.fromNamespaceAndPath("weaversparadise", stencil.equals(STENCIL_DEFAULT) ? TEXTURE_BASE_PATH + textureTwoFile : VARIANT_PATH + textureTwoFile),
                renderType
        );
    }
}