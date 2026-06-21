package xox.labvorty.weaversparadise.data.texture;

import net.minecraft.resources.ResourceLocation;

public class HandWarmersTextures {
    private static final String CLOTHING_TYPE = "hand_warmers";

    private static final String STENCIL_DEFAULT = "default";
    private static final String STENCIL_HALF = "half";
    private static final String STENCIL_CHECKERS = "checkers";
    private static final String STENCIL_VERTICAL_LINES = "vertical_lines";
    private static final String STENCIL_SMALL_LINES = "small_lines";
    private static final String STENCIL_BIG_LINES = "big_lines";
    private static final String STENCIL_CROSS = "cross";
    private static final String STENCIL_PAWS = "paws";
    private static final String STENCIL_CHECKERS_SMALL = "checkers_small";

    private static final String MATERIAL_COTTON = "cotton";
    private static final String MATERIAL_WOOL = "wool";
    private static final String MATERIAL_SILK = "silk";

    private static final String TEXTURE_BASE_PATH = "textures/clothing/";
    private static final String VARIANT_PATH = TEXTURE_BASE_PATH + "variants/mittens/";

    public static void register() {
        registerDefaultVariants();
        registerPatternVariants();
    }

    private static void registerDefaultVariants() {
        registerTexture(STENCIL_DEFAULT, MATERIAL_COTTON, "mitten_base.png", "mitten_base.png", false);
        registerTexture(STENCIL_DEFAULT, MATERIAL_WOOL, "mitten_warm.png", "mitten_warm.png", false);
        registerTexture(STENCIL_DEFAULT, MATERIAL_SILK, "mitten_silk.png", "mitten_silk.png", false);
    }

    private static void registerPatternVariants() {
        registerPattern(STENCIL_HALF, "mitten_cotton_half_1.png", "mitten_cotton_half_2.png", "mitten_wool_half_1.png", "mitten_wool_half_2.png", "mitten_silk_half_1.png", "mitten_silk_half_2.png");
        registerPattern(STENCIL_CHECKERS, "mitten_cotton_checkers_1.png", "mitten_cotton_checkers_2.png", "mitten_wool_checkers_1.png", "mitten_wool_checkers_2.png", "mitten_silk_checkers_1.png", "mitten_silk_checkers_2.png");
        registerPattern(STENCIL_VERTICAL_LINES, "mitten_cotton_vertical_lines_1.png", "mitten_cotton_vertical_lines_2.png", "mitten_wool_vertical_lines_1.png", "mitten_wool_vertical_lines_2.png", "mitten_silk_vertical_lines_1.png", "mitten_silk_vertical_lines_2.png");
        registerPattern(STENCIL_SMALL_LINES, "mitten_cotton_small_lines_1.png", "mitten_cotton_small_lines_2.png", "mitten_wool_small_lines_1.png", "mitten_wool_small_lines_2.png", "mitten_silk_small_lines_1.png", "mitten_silk_small_lines_2.png");
        registerPattern(STENCIL_BIG_LINES, "mitten_cotton_big_lines_1.png", "mitten_cotton_big_lines_2.png", "mitten_wool_big_lines_1.png", "mitten_wool_big_lines_2.png", "mitten_silk_big_lines_1.png", "mitten_silk_big_lines_2.png");
        registerPattern(STENCIL_CROSS, "mitten_cotton_cross_2.png", "mitten_cotton_cross_1.png", "mitten_wool_cross_2.png", "mitten_wool_cross_1.png", "mitten_silk_cross_2.png", "mitten_silk_cross_1.png");
        registerPattern(STENCIL_PAWS, "mitten_cotton_paws_1.png", "mitten_cotton_paws_2.png", "mitten_wool_paws_1.png", "mitten_wool_paws_2.png", "mitten_silk_paws_1.png", "mitten_silk_paws_2.png");
        registerPattern(STENCIL_CHECKERS_SMALL, "mitten_cotton_checkers_small_1.png", "mitten_cotton_checkers_small_2.png", "mitten_wool_checkers_small_1.png", "mitten_wool_checkers_small_2.png", "mitten_silk_checkers_small_1.png", "mitten_silk_checkers_small_2.png");
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