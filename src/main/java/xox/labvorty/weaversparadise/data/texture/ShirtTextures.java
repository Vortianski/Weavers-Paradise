package xox.labvorty.weaversparadise.data.texture;

import net.minecraft.resources.ResourceLocation;

public class ShirtTextures {
    private static final String CLOTHING_TYPE = "shirt";

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
    private static final String VARIANT_PATH = TEXTURE_BASE_PATH + "variants/shirts/";

    public static void register() {
        registerDefaultVariants();
        registerPatternVariants();
    }

    private static void registerDefaultVariants() {
        registerTexture(STENCIL_DEFAULT, MATERIAL_COTTON, "shirt_base.png", "shirt_base.png", false);
        registerTexture(STENCIL_DEFAULT, MATERIAL_WOOL, "sweater.png", "sweater.png", false);
        registerTexture(STENCIL_DEFAULT, MATERIAL_SILK, "shirt_silk.png", "shirt_silk.png", false);
    }

    private static void registerPatternVariants() {
        registerPattern(STENCIL_HALF, "shirt_base_half_1.png", "shirt_base_half_2.png", "sweater_half_1.png", "sweater_half_2.png", "shirt_silk_half_1.png", "shirt_silk_half_2.png");
        registerPattern(STENCIL_CHECKERS, "shirt_base_checkers_1.png", "shirt_base_checkers_2.png", "sweater_checkers_1.png", "sweater_checkers_2.png", "shirt_silk_checkers_1.png", "shirt_silk_checkers_2.png");
        registerPattern(STENCIL_VERTICAL_LINES, "shirt_base_vertical_lines_1.png", "shirt_base_vertical_lines_2.png", "sweater_vertical_lines_1.png", "sweater_vertical_lines_2.png", "shirt_silk_vertical_lines_1.png", "shirt_silk_vertical_lines_2.png");
        registerPattern(STENCIL_SMALL_LINES, "shirt_base_small_lines_1.png", "shirt_base_small_lines_2.png", "sweater_small_lines_1.png", "sweater_small_lines_2.png", "shirt_silk_small_lines_1.png", "shirt_silk_small_lines_2.png");
        registerPattern(STENCIL_BIG_LINES, "shirt_base_big_lines_1.png", "shirt_base_big_lines_2.png", "sweater_big_lines_1.png", "sweater_big_lines_2.png", "shirt_silk_big_lines_1.png", "shirt_silk_big_lines_2.png");
        registerPattern(STENCIL_CHECKERS_SMALL, "shirt_base_checkers_small_1.png", "shirt_base_checkers_small_2.png", "sweater_checkers_small_1.png", "sweater_checkers_small_2.png", "shirt_silk_checkers_small_1.png", "shirt_silk_checkers_small_2.png");
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