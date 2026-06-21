package xox.labvorty.weaversparadise.data.texture;

import net.minecraft.resources.ResourceLocation;

public class PantsTextures {
    private static final String CLOTHING_TYPE = "pants";

    private static final String STENCIL_DEFAULT = "default";
    private static final String STENCIL_HALF = "half";
    private static final String STENCIL_CHECKERS = "checkers";
    private static final String STENCIL_CHECKERS_SMALL = "checkers_small";
    private static final String STENCIL_SMALL_LINES = "small_lines";
    private static final String STENCIL_BIG_LINES = "big_lines";
    private static final String STENCIL_VERTICAL_LINES = "vertical_lines";
    private static final String STENCIL_STARS = "stars";
    private static final String STENCIL_DIRT = "dirt";
    private static final String STENCIL_FLOWERS = "flowers";

    private static final String MATERIAL_JEANS = "jeans";
    private static final String MATERIAL_COTTON = "cotton";
    private static final String MATERIAL_SILK = "silk";

    private static final String TEXTURE_BASE_PATH = "textures/clothing/";
    private static final String VARIANT_PATH = TEXTURE_BASE_PATH + "variants/pants/";

    public static void register() {
        registerDefaultVariants();
        registerPatternVariants();
    }

    private static void registerDefaultVariants() {
        registerTexture(STENCIL_DEFAULT, MATERIAL_JEANS, "pants_jeans.png", "pants_jeans.png", false);
        registerTexture(STENCIL_DEFAULT, MATERIAL_COTTON, "pants_cotton.png", "pants_cotton.png", false);
        registerTexture(STENCIL_DEFAULT, MATERIAL_SILK, "pants_silk.png", "pants_silk.png", false);
    }

    private static void registerPatternVariants() {
        registerPattern(STENCIL_HALF, "pants_jeans_half_1.png", "pants_jeans_half_2.png", "pants_cotton_half_1.png", "pants_cotton_half_2.png", "pants_silk_half_1.png", "pants_silk_half_2.png");
        registerPattern(STENCIL_CHECKERS, "pants_jeans_checkers_1.png", "pants_jeans_checkers_2.png", "pants_cotton_checkers_1.png", "pants_cotton_checkers_2.png", "pants_silk_checkers_1.png", "pants_silk_checkers_2.png");
        registerPattern(STENCIL_CHECKERS_SMALL, "pants_jeans_checkers_small_1.png", "pants_jeans_checkers_small_2.png", "pants_cotton_checkers_small_1.png", "pants_cotton_checkers_small_2.png", "pants_silk_checkers_small_1.png", "pants_silk_checkers_small_2.png");
        registerPattern(STENCIL_SMALL_LINES, "pants_jeans_small_lines_1.png", "pants_jeans_small_lines_2.png", "pants_cotton_small_lines_1.png", "pants_cotton_small_lines_2.png", "pants_silk_small_lines_1.png", "pants_silk_small_lines_2.png");
        registerPattern(STENCIL_BIG_LINES, "pants_jeans_big_lines_1.png", "pants_jeans_big_lines_2.png", "pants_cotton_big_lines_1.png", "pants_cotton_big_lines_2.png", "pants_silk_big_lines_1.png", "pants_silk_big_lines_2.png");
        registerPattern(STENCIL_VERTICAL_LINES, "pants_jeans_vertical_lines_1.png", "pants_jeans_vertical_lines_2.png", "pants_cotton_vertical_lines_1.png", "pants_cotton_vertical_lines_2.png", "pants_silk_vertical_lines_1.png", "pants_silk_vertical_lines_2.png");
        registerPattern(STENCIL_STARS, "pants_jeans_stars.png", "pants_basic_stars.png", "pants_cotton_stars.png", "pants_basic_stars.png", "pants_silk_stars.png", "pants_basic_stars.png");
        registerPattern(STENCIL_DIRT, "pants_jeans_dirt.png", "pants_basic_dirt.png", "pants_cotton_dirt.png", "pants_basic_dirt.png", "pants_silk_dirt.png", "pants_basic_dirt.png");
        registerPattern(STENCIL_FLOWERS, "pants_jeans_flowers.png", "pants_basic_flowers.png", "pants_cotton_flowers.png", "pants_basic_flowers.png", "pants_silk_flowers.png", "pants_basic_flowers.png");
    }

    private static void registerPattern(String stencil, String jeans1, String jeans2, String cotton1, String cotton2, String silk1, String silk2) {
        registerTexture(stencil, MATERIAL_JEANS, jeans1, jeans2, true);
        registerTexture(stencil, MATERIAL_COTTON, cotton1, cotton2, true);
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