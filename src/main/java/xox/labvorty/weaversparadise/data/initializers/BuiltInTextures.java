package xox.labvorty.weaversparadise.data.initializers;

import xox.labvorty.weaversparadise.data.texture.TexturesRegistrar;

public class BuiltInTextures {
    private static final String MOD_ID = "weaversparadise";

    private static final String BASE_PATH = "textures/clothing/";

    private static final String COTTON = "cotton";
    private static final String SILK = "silk";
    private static final String WOOL = "wool";
    private static final String JEANS = "jeans";

    private static final String DEFAULT = "default";
    private static final String HALF = "half";
    private static final String CHECKERS = "checkers";
    private static final String CHECKERS_SMALL = "checkers_small";
    private static final String VERTICAL_LINES = "vertical_lines";
    private static final String SMALL_LINES = "small_lines";
    private static final String BIG_LINES = "big_lines";
    private static final String CROSS = "cross";
    private static final String PAWS = "paws";
    private static final String STARS = "stars";
    private static final String FLOWERS = "flowers";
    private static final String DIRT = "dirt";

    public static void register() {
        ThighHighs.register();
        HandWarmers.register();
        Pants.register();
        Tops.register();
        Shirt.register();
        Pullover.register();
        Poncho.register();
        CropTopLongSleeved.register();
        Cape.register();
        Choker.register();
        Skirt.register();
        PomponHat.register();
        Cap.register();
        Ushanka.register();
        ChokerTrinkets.register();
        Blahaj.register();
    }

    private static class ThighHighs {
        private static final String TYPE = "thigh_highs";
        private static final String VARIANT_PATH = BASE_PATH + "variants/thigh_highs/";

        private static void register() {
            base();
            variants();
        }

        private static void base() {
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, COTTON, DEFAULT, BASE_PATH, VARIANT_PATH, "thigh_highs_cotton.png", "thigh_highs_cotton.png", false));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, SILK, DEFAULT, BASE_PATH, VARIANT_PATH, "thigh_highs_silk.png", "thigh_highs_silk.png", false));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, WOOL, DEFAULT, BASE_PATH, VARIANT_PATH, "thigh_highs_wool.png", "thigh_highs_wool.png", false));
        }

        private static void variants() {
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, COTTON, HALF, BASE_PATH, VARIANT_PATH, "thigh_highs_cotton_half_1.png", "thigh_highs_cotton_half_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, COTTON, CHECKERS, BASE_PATH, VARIANT_PATH, "thigh_highs_cotton_checkers_1.png", "thigh_highs_cotton_checkers_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, COTTON, CHECKERS_SMALL, BASE_PATH, VARIANT_PATH, "thigh_highs_cotton_checkers_small_1.png", "thigh_highs_cotton_checkers_small_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, COTTON, VERTICAL_LINES, BASE_PATH, VARIANT_PATH, "thigh_highs_cotton_vertical_lines_1.png", "thigh_highs_cotton_vertical_lines_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, COTTON, SMALL_LINES, BASE_PATH, VARIANT_PATH, "thigh_highs_cotton_small_lines_1.png", "thigh_highs_cotton_small_lines_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, COTTON, BIG_LINES, BASE_PATH, VARIANT_PATH, "thigh_highs_cotton_big_lines_1.png", "thigh_highs_cotton_big_lines_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, COTTON, CROSS, BASE_PATH, VARIANT_PATH, "thigh_highs_cotton_cross_1.png", "thigh_highs_cotton_cross_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, COTTON, PAWS, BASE_PATH, VARIANT_PATH, "thigh_highs_cotton_paws_1.png", "thigh_highs_cotton_paws_2.png", true));

            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, SILK, HALF, BASE_PATH, VARIANT_PATH, "thigh_highs_silk_half_1.png", "thigh_highs_silk_half_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, SILK, CHECKERS, BASE_PATH, VARIANT_PATH, "thigh_highs_silk_checkers_1.png", "thigh_highs_silk_checkers_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, SILK, CHECKERS_SMALL, BASE_PATH, VARIANT_PATH, "thigh_highs_silk_checkers_small_1.png", "thigh_highs_silk_checkers_small_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, SILK, VERTICAL_LINES, BASE_PATH, VARIANT_PATH, "thigh_highs_silk_vertical_lines_1.png", "thigh_highs_silk_vertical_lines_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, SILK, SMALL_LINES, BASE_PATH, VARIANT_PATH, "thigh_highs_silk_small_lines_1.png", "thigh_highs_silk_small_lines_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, SILK, BIG_LINES, BASE_PATH, VARIANT_PATH, "thigh_highs_silk_big_lines_1.png", "thigh_highs_silk_big_lines_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, SILK, CROSS, BASE_PATH, VARIANT_PATH, "thigh_highs_silk_cross_1.png", "thigh_highs_silk_cross_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, SILK, PAWS, BASE_PATH, VARIANT_PATH, "thigh_highs_silk_paws_1.png", "thigh_highs_silk_paws_2.png", true));

            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, WOOL, HALF, BASE_PATH, VARIANT_PATH, "thigh_highs_wool_half_1.png", "thigh_highs_wool_half_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, WOOL, CHECKERS, BASE_PATH, VARIANT_PATH, "thigh_highs_wool_checkers_1.png", "thigh_highs_wool_checkers_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, WOOL, CHECKERS_SMALL, BASE_PATH, VARIANT_PATH, "thigh_highs_wool_checkers_small_1.png", "thigh_highs_wool_checkers_small_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, WOOL, VERTICAL_LINES, BASE_PATH, VARIANT_PATH, "thigh_highs_wool_vertical_lines_1.png", "thigh_highs_wool_vertical_lines_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, WOOL, SMALL_LINES, BASE_PATH, VARIANT_PATH, "thigh_highs_wool_small_lines_1.png", "thigh_highs_wool_small_lines_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, WOOL, BIG_LINES, BASE_PATH, VARIANT_PATH, "thigh_highs_wool_big_lines_1.png", "thigh_highs_wool_big_lines_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, WOOL, CROSS, BASE_PATH, VARIANT_PATH, "thigh_highs_wool_cross_1.png", "thigh_highs_wool_cross_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, WOOL, PAWS, BASE_PATH, VARIANT_PATH, "thigh_highs_wool_paws_1.png", "thigh_highs_wool_paws_2.png", true));
        }
    }

    private static class HandWarmers {
        private static final String TYPE = "hand_warmers";
        private static final String VARIANT_PATH = BASE_PATH + "variants/hand_warmers/";

        private static void register() {
            base();
            variants();
        }

        private static void base() {
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, COTTON, DEFAULT, BASE_PATH, VARIANT_PATH, "hand_warmers_cotton.png", "hand_warmers_cotton.png", false));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, SILK, DEFAULT, BASE_PATH, VARIANT_PATH, "hand_warmers_silk.png", "hand_warmers_silk.png", false));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, WOOL, DEFAULT, BASE_PATH, VARIANT_PATH, "hand_warmers_wool.png", "hand_warmers_wool.png", false));
        }

        private static void variants() {
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, COTTON, HALF, BASE_PATH, VARIANT_PATH, "hand_warmers_cotton_half_1.png", "hand_warmers_cotton_half_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, COTTON, CHECKERS, BASE_PATH, VARIANT_PATH, "hand_warmers_cotton_checkers_1.png", "hand_warmers_cotton_checkers_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, COTTON, CHECKERS_SMALL, BASE_PATH, VARIANT_PATH, "hand_warmers_cotton_checkers_small_1.png", "hand_warmers_cotton_checkers_small_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, COTTON, VERTICAL_LINES, BASE_PATH, VARIANT_PATH, "hand_warmers_cotton_vertical_lines_1.png", "hand_warmers_cotton_vertical_lines_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, COTTON, SMALL_LINES, BASE_PATH, VARIANT_PATH, "hand_warmers_cotton_small_lines_1.png", "hand_warmers_cotton_small_lines_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, COTTON, BIG_LINES, BASE_PATH, VARIANT_PATH, "hand_warmers_cotton_big_lines_1.png", "hand_warmers_cotton_big_lines_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, COTTON, CROSS, BASE_PATH, VARIANT_PATH, "hand_warmers_cotton_cross_2.png", "hand_warmers_cotton_cross_1.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, COTTON, PAWS, BASE_PATH, VARIANT_PATH, "hand_warmers_cotton_paws_1.png", "hand_warmers_cotton_paws_2.png", true));

            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, SILK, HALF, BASE_PATH, VARIANT_PATH, "hand_warmers_silk_half_1.png", "hand_warmers_silk_half_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, SILK, CHECKERS, BASE_PATH, VARIANT_PATH, "hand_warmers_silk_checkers_1.png", "hand_warmers_silk_checkers_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, SILK, CHECKERS_SMALL, BASE_PATH, VARIANT_PATH, "hand_warmers_silk_checkers_small_1.png", "hand_warmers_silk_checkers_small_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, SILK, VERTICAL_LINES, BASE_PATH, VARIANT_PATH, "hand_warmers_silk_vertical_lines_1.png", "hand_warmers_silk_vertical_lines_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, SILK, SMALL_LINES, BASE_PATH, VARIANT_PATH, "hand_warmers_silk_small_lines_1.png", "hand_warmers_silk_small_lines_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, SILK, BIG_LINES, BASE_PATH, VARIANT_PATH, "hand_warmers_silk_big_lines_1.png", "hand_warmers_silk_big_lines_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, SILK, CROSS, BASE_PATH, VARIANT_PATH, "hand_warmers_silk_cross_2.png", "hand_warmers_silk_cross_1.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, SILK, PAWS, BASE_PATH, VARIANT_PATH, "hand_warmers_silk_paws_1.png", "hand_warmers_silk_paws_2.png", true));

            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, WOOL, HALF, BASE_PATH, VARIANT_PATH, "hand_warmers_wool_half_1.png", "hand_warmers_wool_half_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, WOOL, CHECKERS, BASE_PATH, VARIANT_PATH, "hand_warmers_wool_checkers_1.png", "hand_warmers_wool_checkers_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, WOOL, CHECKERS_SMALL, BASE_PATH, VARIANT_PATH, "hand_warmers_wool_checkers_small_1.png", "hand_warmers_wool_checkers_small_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, WOOL, VERTICAL_LINES, BASE_PATH, VARIANT_PATH, "hand_warmers_wool_vertical_lines_1.png", "hand_warmers_wool_vertical_lines_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, WOOL, SMALL_LINES, BASE_PATH, VARIANT_PATH, "hand_warmers_wool_small_lines_1.png", "hand_warmers_wool_small_lines_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, WOOL, BIG_LINES, BASE_PATH, VARIANT_PATH, "hand_warmers_wool_big_lines_1.png", "hand_warmers_wool_big_lines_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, WOOL, CROSS, BASE_PATH, VARIANT_PATH, "hand_warmers_wool_cross_2.png", "hand_warmers_wool_cross_1.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, WOOL, PAWS, BASE_PATH, VARIANT_PATH, "hand_warmers_wool_paws_1.png", "hand_warmers_wool_paws_2.png", true));
        }
    }

    private static class Pants {
        private static final String TYPE = "pants";
        private static final String VARIANT_PATH = BASE_PATH + "variants/pants/";

        private static void register() {
            base();
            variants();
        }

        private static void base() {
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, COTTON, DEFAULT, BASE_PATH, VARIANT_PATH, "pants_cotton.png", "pants_cotton.png", false));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, SILK, DEFAULT, BASE_PATH, VARIANT_PATH, "pants_silk.png", "pants_silk.png", false));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, WOOL, DEFAULT, BASE_PATH, VARIANT_PATH, "wool_pants.png", "wool_pants.png", false));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, JEANS, DEFAULT, BASE_PATH, VARIANT_PATH, "pants_jeans.png", "pants_jeans.png", false));
        }

        private static void variants() {
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, COTTON, HALF, BASE_PATH, VARIANT_PATH, "pants_cotton_half_1.png", "pants_cotton_half_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, COTTON, CHECKERS, BASE_PATH, VARIANT_PATH, "pants_cotton_checkers_1.png", "pants_cotton_checkers_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, COTTON, CHECKERS_SMALL, BASE_PATH, VARIANT_PATH, "pants_cotton_checkers_small_1.png", "pants_cotton_checkers_small_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, COTTON, VERTICAL_LINES, BASE_PATH, VARIANT_PATH, "pants_cotton_vertical_lines_1.png", "pants_cotton_vertical_lines_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, COTTON, SMALL_LINES, BASE_PATH, VARIANT_PATH, "pants_cotton_small_lines_1.png", "pants_cotton_small_lines_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, COTTON, BIG_LINES, BASE_PATH, VARIANT_PATH, "pants_cotton_big_lines_1.png", "pants_cotton_big_lines_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, COTTON, STARS, BASE_PATH, VARIANT_PATH, "pants_cotton_stars.png", "pants_basic_stars.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, COTTON, DIRT, BASE_PATH, VARIANT_PATH, "pants_cotton_dirt.png", "pants_basic_dirt.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, COTTON, FLOWERS, BASE_PATH, VARIANT_PATH, "pants_cotton_flowers.png", "pants_basic_flowers.png", true));

            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, SILK, HALF, BASE_PATH, VARIANT_PATH, "pants_silk_half_1.png", "pants_silk_half_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, SILK, CHECKERS, BASE_PATH, VARIANT_PATH, "pants_silk_checkers_1.png", "pants_silk_checkers_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, SILK, CHECKERS_SMALL, BASE_PATH, VARIANT_PATH, "pants_silk_checkers_small_1.png", "pants_silk_checkers_small_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, SILK, VERTICAL_LINES, BASE_PATH, VARIANT_PATH, "pants_silk_vertical_lines_1.png", "pants_silk_vertical_lines_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, SILK, SMALL_LINES, BASE_PATH, VARIANT_PATH, "pants_silk_small_lines_1.png", "pants_silk_small_lines_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, SILK, BIG_LINES, BASE_PATH, VARIANT_PATH, "pants_silk_big_lines_1.png", "pants_silk_big_lines_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, SILK, STARS, BASE_PATH, VARIANT_PATH, "pants_silk_stars.png", "pants_basic_stars.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, SILK, DIRT, BASE_PATH, VARIANT_PATH, "pants_silk_dirt.png", "pants_basic_dirt.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, SILK, FLOWERS, BASE_PATH, VARIANT_PATH, "pants_silk_flowers.png", "pants_basic_flowers.png", true));

            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, WOOL, HALF, BASE_PATH, VARIANT_PATH, "wool_pants_half_1.png", "wool_pants_half_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, WOOL, CHECKERS, BASE_PATH, VARIANT_PATH, "wool_pants_checkers_1.png", "wool_pants_checkers_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, WOOL, CHECKERS_SMALL, BASE_PATH, VARIANT_PATH, "wool_pants_checkers_small_1.png", "wool_pants_checkers_small_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, WOOL, VERTICAL_LINES, BASE_PATH, VARIANT_PATH, "wool_pants_vertical_lines_1.png", "wool_pants_vertical_lines_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, WOOL, SMALL_LINES, BASE_PATH, VARIANT_PATH, "wool_pants_small_lines_1.png", "wool_pants_small_lines_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, WOOL, BIG_LINES, BASE_PATH, VARIANT_PATH, "wool_pants_big_lines_1.png", "wool_pants_big_lines_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, WOOL, STARS, BASE_PATH, VARIANT_PATH, "wool_pants_stars.png", "pants_basic_stars.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, WOOL, DIRT, BASE_PATH, VARIANT_PATH, "wool_pants_dirt.png", "pants_basic_dirt.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, WOOL, FLOWERS, BASE_PATH, VARIANT_PATH, "wool_pants_flowers.png", "pants_basic_flowers.png", true));

            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, JEANS, HALF, BASE_PATH, VARIANT_PATH, "pants_jeans_half_1.png", "pants_jeans_half_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, JEANS, CHECKERS, BASE_PATH, VARIANT_PATH, "pants_jeans_checkers_1.png", "pants_jeans_checkers_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, JEANS, CHECKERS_SMALL, BASE_PATH, VARIANT_PATH, "pants_jeans_checkers_small_1.png", "pants_jeans_checkers_small_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, JEANS, VERTICAL_LINES, BASE_PATH, VARIANT_PATH, "pants_jeans_vertical_lines_1.png", "pants_jeans_vertical_lines_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, JEANS, SMALL_LINES, BASE_PATH, VARIANT_PATH, "pants_jeans_small_lines_1.png", "pants_jeans_small_lines_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, JEANS, BIG_LINES, BASE_PATH, VARIANT_PATH, "pants_jeans_big_lines_1.png", "pants_jeans_big_lines_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, JEANS, STARS, BASE_PATH, VARIANT_PATH, "pants_jeans_stars.png", "pants_basic_stars.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, JEANS, DIRT, BASE_PATH, VARIANT_PATH, "pants_jeans_dirt.png", "pants_basic_dirt.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, JEANS, FLOWERS, BASE_PATH, VARIANT_PATH, "pants_jeans_flowers.png", "pants_basic_flowers.png", true));
        }
    }

    private static class Tops {
        private static final String TYPE = "tops";
        private static final String VARIANT_PATH = BASE_PATH + "variants/tops/";

        private static void register() {
            base();
            variants();
        }

        private static void base() {
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, COTTON, DEFAULT, BASE_PATH, VARIANT_PATH, "t_shirt.png", "t_shirt.png", false));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, SILK, DEFAULT, BASE_PATH, VARIANT_PATH, "tank_top.png", "tank_top.png", false));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, WOOL, DEFAULT, BASE_PATH, VARIANT_PATH, "vest.png", "vest.png", false));
        }

        private static void variants() {
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, COTTON, HALF, BASE_PATH, VARIANT_PATH, "t_shirt_half_1.png", "t_shirt_half_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, COTTON, CHECKERS, BASE_PATH, VARIANT_PATH, "t_shirt_checkers_1.png", "t_shirt_checkers_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, COTTON, CHECKERS_SMALL, BASE_PATH, VARIANT_PATH, "t_shirt_checkers_small_1.png", "t_shirt_checkers_small_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, COTTON, VERTICAL_LINES, BASE_PATH, VARIANT_PATH, "t_shirt_vertical_lines_1.png", "t_shirt_vertical_lines_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, COTTON, SMALL_LINES, BASE_PATH, VARIANT_PATH, "t_shirt_small_lines_1.png", "t_shirt_small_lines_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, COTTON, BIG_LINES, BASE_PATH, VARIANT_PATH, "t_shirt_big_lines_1.png", "t_shirt_big_lines_2.png", true));

            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, SILK, HALF, BASE_PATH, VARIANT_PATH, "tank_top_half_1.png", "tank_top_half_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, SILK, CHECKERS, BASE_PATH, VARIANT_PATH, "tank_top_checkers_1.png", "tank_top_checkers_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, SILK, CHECKERS_SMALL, BASE_PATH, VARIANT_PATH, "tank_top_checkers_small_1.png", "tank_top_checkers_small_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, SILK, VERTICAL_LINES, BASE_PATH, VARIANT_PATH, "tank_top_vertical_lines_1.png", "tank_top_vertical_lines_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, SILK, SMALL_LINES, BASE_PATH, VARIANT_PATH, "tank_top_small_lines_1.png", "tank_top_small_lines_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, SILK, BIG_LINES, BASE_PATH, VARIANT_PATH, "tank_top_big_lines_1.png", "tank_top_big_lines_2.png", true));

            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, WOOL, HALF, BASE_PATH, VARIANT_PATH, "vest_half_1.png", "vest_half_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, WOOL, CHECKERS, BASE_PATH, VARIANT_PATH, "vest_checkers_1.png", "vest_checkers_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, WOOL, CHECKERS_SMALL, BASE_PATH, VARIANT_PATH, "vest_checkers_small_1.png", "vest_checkers_small_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, WOOL, VERTICAL_LINES, BASE_PATH, VARIANT_PATH, "vest_vertical_lines_1.png", "vest_vertical_lines_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, WOOL, SMALL_LINES, BASE_PATH, VARIANT_PATH, "vest_small_lines_1.png", "vest_small_lines_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, WOOL, BIG_LINES, BASE_PATH, VARIANT_PATH, "vest_big_lines_1.png", "vest_big_lines_2.png", true));
        }
    }

    private static class Shirt {
        private static final String TYPE = "shirt";
        private static final String TYPE_OPEN = "shirt_open";
        private static final String VARIANT_PATH = BASE_PATH + "variants/shirts/";

        private static void register() {
            open();
            closed();
        }

        private static void open() {
            openDefault();
            openVariants();
        }

        private static void openDefault() {
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE_OPEN, COTTON, DEFAULT, BASE_PATH, VARIANT_PATH, "shirt_base_open.png", "shirt_base_open.png", false));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE_OPEN, SILK, DEFAULT, BASE_PATH, VARIANT_PATH, "shirt_silk_open.png", "shirt_silk_open.png", false));
        }

        private static void openVariants() {
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE_OPEN, COTTON, HALF, BASE_PATH, VARIANT_PATH, "open/shirt_base_half_1.png", "open/shirt_base_half_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE_OPEN, COTTON, CHECKERS, BASE_PATH, VARIANT_PATH, "open/shirt_base_checkers_1.png", "open/shirt_base_checkers_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE_OPEN, COTTON, CHECKERS_SMALL, BASE_PATH, VARIANT_PATH, "open/shirt_base_checkers_small_1.png", "open/shirt_base_checkers_small_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE_OPEN, COTTON, VERTICAL_LINES, BASE_PATH, VARIANT_PATH, "open/shirt_base_vertical_lines_1.png", "open/shirt_base_vertical_lines_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE_OPEN, COTTON, SMALL_LINES, BASE_PATH, VARIANT_PATH, "open/shirt_base_small_lines_1.png", "open/shirt_base_small_lines_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE_OPEN, COTTON, BIG_LINES, BASE_PATH, VARIANT_PATH, "open/shirt_base_big_lines_1.png", "open/shirt_base_big_lines_2.png", true));

            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE_OPEN, SILK, HALF, BASE_PATH, VARIANT_PATH, "open/shirt_silk_half_1.png", "open/shirt_silk_half_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE_OPEN, SILK, CHECKERS, BASE_PATH, VARIANT_PATH, "open/shirt_silk_checkers_1.png", "open/shirt_silk_checkers_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE_OPEN, SILK, CHECKERS_SMALL, BASE_PATH, VARIANT_PATH, "open/shirt_silk_checkers_small_1.png", "open/shirt_silk_checkers_small_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE_OPEN, SILK, VERTICAL_LINES, BASE_PATH, VARIANT_PATH, "open/shirt_silk_vertical_lines_1.png", "open/shirt_silk_vertical_lines_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE_OPEN, SILK, SMALL_LINES, BASE_PATH, VARIANT_PATH, "open/shirt_silk_small_lines_1.png", "open/shirt_silk_small_lines_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE_OPEN, SILK, BIG_LINES, BASE_PATH, VARIANT_PATH, "open/shirt_silk_big_lines_1.png", "open/shirt_silk_big_lines_2.png", true));
        }

        private static void closed() {
            closedDefault();
            closedVariants();
        }

        private static void closedDefault() {
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, COTTON, DEFAULT, BASE_PATH, VARIANT_PATH, "shirt_base.png", "shirt_base.png", false));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, SILK, DEFAULT, BASE_PATH, VARIANT_PATH, "shirt_silk.png", "shirt_silk.png", false));
        }

        private static void closedVariants() {
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, COTTON, HALF, BASE_PATH, VARIANT_PATH, "shirt_base_half_1.png", "shirt_base_half_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, COTTON, CHECKERS, BASE_PATH, VARIANT_PATH, "shirt_base_checkers_1.png", "shirt_base_checkers_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, COTTON, CHECKERS_SMALL, BASE_PATH, VARIANT_PATH, "shirt_base_checkers_small_1.png", "shirt_base_checkers_small_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, COTTON, VERTICAL_LINES, BASE_PATH, VARIANT_PATH, "shirt_base_vertical_lines_1.png", "shirt_base_vertical_lines_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, COTTON, SMALL_LINES, BASE_PATH, VARIANT_PATH, "shirt_base_small_lines_1.png", "shirt_base_small_lines_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, COTTON, BIG_LINES, BASE_PATH, VARIANT_PATH, "shirt_base_big_lines_1.png", "shirt_base_big_lines_2.png", true));

            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, SILK, HALF, BASE_PATH, VARIANT_PATH, "shirt_silk_half_1.png", "shirt_silk_half_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, SILK, CHECKERS, BASE_PATH, VARIANT_PATH, "shirt_silk_checkers_1.png", "shirt_silk_checkers_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, SILK, CHECKERS_SMALL, BASE_PATH, VARIANT_PATH, "shirt_silk_checkers_small_1.png", "shirt_silk_checkers_small_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, SILK, VERTICAL_LINES, BASE_PATH, VARIANT_PATH, "shirt_silk_vertical_lines_1.png", "shirt_silk_vertical_lines_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, SILK, SMALL_LINES, BASE_PATH, VARIANT_PATH, "shirt_silk_small_lines_1.png", "shirt_silk_small_lines_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, SILK, BIG_LINES, BASE_PATH, VARIANT_PATH, "shirt_silk_big_lines_1.png", "shirt_silk_big_lines_2.png", true));
        }
    }

    private static class CropTopLongSleeved {
        private static final String TYPE = "crop_top_long_sleeved";
        private static final String TYPE_OPEN = "crop_top_long_sleeved_sag";
        private static final String VARIANT_PATH = BASE_PATH + "variants/crop_top_long_sleeved/";

        private static void register() {
            base();
            variants();
        }

        private static void base() {
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, COTTON, DEFAULT, BASE_PATH, VARIANT_PATH, "cotton_crop_top_long_sleeved.png", "cotton_crop_top_long_sleeved.png", false));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE_OPEN, COTTON, DEFAULT, BASE_PATH, VARIANT_PATH, "cotton_crop_top_long_sleeved_sag.png", "cotton_crop_top_long_sleeved_sag.png", false));
        }

        private static void variants() {
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, COTTON, HALF, BASE_PATH, VARIANT_PATH, "cotton_crop_top_long_sleeved_half_1.png", "cotton_crop_top_long_sleeved_half_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, COTTON, CHECKERS, BASE_PATH, VARIANT_PATH, "cotton_crop_top_long_sleeved_checkers_1.png", "cotton_crop_top_long_sleeved_checkers_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, COTTON, CHECKERS_SMALL, BASE_PATH, VARIANT_PATH, "cotton_crop_top_long_sleeved_checkers_small_1.png", "cotton_crop_top_long_sleeved_checkers_small_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, COTTON, VERTICAL_LINES, BASE_PATH, VARIANT_PATH, "cotton_crop_top_long_sleeved_vertical_lines_1.png", "cotton_crop_top_long_sleeved_vertical_lines_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, COTTON, SMALL_LINES, BASE_PATH, VARIANT_PATH, "cotton_crop_top_long_sleeved_small_lines_1.png", "cotton_crop_top_long_sleeved_small_lines_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, COTTON, BIG_LINES, BASE_PATH, VARIANT_PATH, "cotton_crop_top_long_sleeved_big_lines_1.png", "cotton_crop_top_long_sleeved_big_lines_2.png", true));

            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE_OPEN, COTTON, HALF, BASE_PATH, VARIANT_PATH, "sag/cotton_crop_top_long_sleeved_half_1.png", "sag/cotton_crop_top_long_sleeved_half_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE_OPEN, COTTON, CHECKERS, BASE_PATH, VARIANT_PATH, "sag/cotton_crop_top_long_sleeved_checkers_1.png", "sag/cotton_crop_top_long_sleeved_checkers_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE_OPEN, COTTON, CHECKERS_SMALL, BASE_PATH, VARIANT_PATH, "sag/cotton_crop_top_long_sleeved_checkers_small_1.png", "sag/cotton_crop_top_long_sleeved_checkers_small_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE_OPEN, COTTON, VERTICAL_LINES, BASE_PATH, VARIANT_PATH, "sag/cotton_crop_top_long_sleeved_vertical_lines_1.png", "sag/cotton_crop_top_long_sleeved_vertical_lines_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE_OPEN, COTTON, SMALL_LINES, BASE_PATH, VARIANT_PATH, "sag/cotton_crop_top_long_sleeved_small_lines_1.png", "sag/cotton_crop_top_long_sleeved_small_lines_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE_OPEN, COTTON, BIG_LINES, BASE_PATH, VARIANT_PATH, "sag/cotton_crop_top_long_sleeved_big_lines_1.png", "sag/cotton_crop_top_long_sleeved_big_lines_2.png", true));
        }
    }

    private static class Pullover {
        private static final String TYPE = "pullover";
        private static final String VARIANT_PATH = BASE_PATH + "variants/pullover/";

        private static void register() {
            base();
            variants();
        }

        private static void base() {
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, COTTON, DEFAULT, BASE_PATH, VARIANT_PATH, "cotton_long_sleeve.png", "cotton_long_sleeve.png", false));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, WOOL, DEFAULT, BASE_PATH, VARIANT_PATH, "sweater.png", "sweater.png", false));
        }

        private static void variants() {
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, COTTON, HALF, BASE_PATH, VARIANT_PATH, "cotton_long_sleeve_half_1.png", "cotton_long_sleeve_half_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, COTTON, CHECKERS, BASE_PATH, VARIANT_PATH, "cotton_long_sleeve_checkers_1.png", "cotton_long_sleeve_checkers_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, COTTON, CHECKERS_SMALL, BASE_PATH, VARIANT_PATH, "cotton_long_sleeve_checkers_small_1.png", "cotton_long_sleeve_checkers_small_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, COTTON, VERTICAL_LINES, BASE_PATH, VARIANT_PATH, "cotton_long_sleeve_vertical_lines_1.png", "cotton_long_sleeve_vertical_lines_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, COTTON, SMALL_LINES, BASE_PATH, VARIANT_PATH, "cotton_long_sleeve_small_lines_1.png", "cotton_long_sleeve_small_lines_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, COTTON, BIG_LINES, BASE_PATH, VARIANT_PATH, "cotton_long_sleeve_big_lines_1.png", "cotton_long_sleeve_big_lines_2.png", true));

            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, WOOL, HALF, BASE_PATH, VARIANT_PATH, "sweater_half_1.png", "sweater_half_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, WOOL, CHECKERS, BASE_PATH, VARIANT_PATH, "sweater_checkers_1.png", "sweater_checkers_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, WOOL, CHECKERS_SMALL, BASE_PATH, VARIANT_PATH, "sweater_checkers_small_1.png", "sweater_checkers_small_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, WOOL, VERTICAL_LINES, BASE_PATH, VARIANT_PATH, "sweater_vertical_lines_1.png", "sweater_vertical_lines_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, WOOL, SMALL_LINES, BASE_PATH, VARIANT_PATH, "sweater_small_lines_1.png", "sweater_small_lines_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, WOOL, BIG_LINES, BASE_PATH, VARIANT_PATH, "sweater_big_lines_1.png", "sweater_big_lines_2.png", true));
        }
    }

    private static class Poncho {
        private static final String TYPE = "poncho";
        private static final String VARIANT_PATH = BASE_PATH + "variants/poncho/";

        private static void register() {
            base();
            variants();
        }

        private static void base() {
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, COTTON, DEFAULT, BASE_PATH, VARIANT_PATH, "poncho.png", "poncho.png", false));
        }

        private static void variants() {
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, COTTON, HALF, BASE_PATH, VARIANT_PATH, "poncho_half_1.png", "poncho_half_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, COTTON, CHECKERS, BASE_PATH, VARIANT_PATH, "poncho_checkers_1.png", "poncho_checkers_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, COTTON, CHECKERS_SMALL, BASE_PATH, VARIANT_PATH, "poncho_checkers_small_1.png", "poncho_checkers_small_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, COTTON, VERTICAL_LINES, BASE_PATH, VARIANT_PATH, "poncho_vertical_lines_1.png", "poncho_vertical_lines_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, COTTON, SMALL_LINES, BASE_PATH, VARIANT_PATH, "poncho_small_lines_1.png", "poncho_small_lines_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, COTTON, BIG_LINES, BASE_PATH, VARIANT_PATH, "poncho_big_lines_1.png", "poncho_big_lines_2.png", true));
        }
    }

    private static class Cape {
        private static final String TYPE = "cape";
        private static final String VARIANT_PATH = BASE_PATH + "variants/cape/";

        private static void register() {
            base();
            variants();
        }

        private static void base() {
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, COTTON, DEFAULT, BASE_PATH, VARIANT_PATH, "cape_cotton.png", "cape_cotton.png", false));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, SILK, DEFAULT, BASE_PATH, VARIANT_PATH, "cape_silk.png", "cape_silk.png", false));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, WOOL, DEFAULT, BASE_PATH, VARIANT_PATH, "cape_wool.png", "cape_wool.png", false));
        }

        private static void variants() {
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, COTTON, HALF, BASE_PATH, VARIANT_PATH, "cotton_cape_half_1.png", "cotton_cape_half_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, COTTON, CHECKERS, BASE_PATH, VARIANT_PATH, "cotton_cape_checkers_1.png", "cotton_cape_checkers_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, COTTON, CHECKERS_SMALL, BASE_PATH, VARIANT_PATH, "cotton_cape_checkers_small_1.png", "cotton_cape_checkers_small_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, COTTON, VERTICAL_LINES, BASE_PATH, VARIANT_PATH, "cotton_cape_vertical_lines_1.png", "cotton_cape_vertical_lines_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, COTTON, SMALL_LINES, BASE_PATH, VARIANT_PATH, "cotton_cape_small_lines_1.png", "cotton_cape_small_lines_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, COTTON, BIG_LINES, BASE_PATH, VARIANT_PATH, "cotton_cape_big_lines_1.png", "cotton_cape_big_lines_2.png", true));

            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, SILK, HALF, BASE_PATH, VARIANT_PATH, "silk_cape_half_1.png", "silk_cape_half_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, SILK, CHECKERS, BASE_PATH, VARIANT_PATH, "silk_cape_checkers_1.png", "silk_cape_checkers_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, SILK, CHECKERS_SMALL, BASE_PATH, VARIANT_PATH, "silk_cape_checkers_small_1.png", "silk_cape_checkers_small_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, SILK, VERTICAL_LINES, BASE_PATH, VARIANT_PATH, "silk_cape_vertical_lines_1.png", "silk_cape_vertical_lines_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, SILK, SMALL_LINES, BASE_PATH, VARIANT_PATH, "silk_cape_small_lines_1.png", "silk_cape_small_lines_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, SILK, BIG_LINES, BASE_PATH, VARIANT_PATH, "silk_cape_big_lines_1.png", "silk_cape_big_lines_2.png", true));

            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, WOOL, HALF, BASE_PATH, VARIANT_PATH, "wool_cape_half_1.png", "wool_cape_half_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, WOOL, CHECKERS, BASE_PATH, VARIANT_PATH, "wool_cape_checkers_1.png", "wool_cape_checkers_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, WOOL, CHECKERS_SMALL, BASE_PATH, VARIANT_PATH, "wool_cape_checkers_small_1.png", "wool_cape_checkers_small_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, WOOL, VERTICAL_LINES, BASE_PATH, VARIANT_PATH, "wool_cape_vertical_lines_1.png", "wool_cape_vertical_lines_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, WOOL, SMALL_LINES, BASE_PATH, VARIANT_PATH, "wool_cape_small_lines_1.png", "wool_cape_small_lines_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, WOOL, BIG_LINES, BASE_PATH, VARIANT_PATH, "wool_cape_big_lines_1.png", "wool_cape_big_lines_2.png", true));
        }
    }

    private static class Choker {
        private static final String TYPE = "choker";
        private static final String VARIANT_PATH = BASE_PATH + "variants/choker/";
        private static final String PRI = "pri";
        private static final String SEC = "sec";

        private static void register() {
            base();
            variants();
        }

        private static void base() {
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, PRI, DEFAULT, BASE_PATH, VARIANT_PATH, "choker_half_pri.png", "choker_half_pri.png", false));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, SEC, DEFAULT, BASE_PATH, VARIANT_PATH, "choker_half_sec.png", "choker_half_sec.png", false));
        }

        private static void variants() {
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, PRI, HALF, BASE_PATH, VARIANT_PATH, "choker_half_pri_half_1.png", "choker_half_pri_half_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, PRI, CHECKERS_SMALL, BASE_PATH, VARIANT_PATH, "choker_half_pri_checkers_small_1.png", "choker_half_pri_checkers_small_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, PRI, VERTICAL_LINES, BASE_PATH, VARIANT_PATH, "choker_half_pri_lines_vertical_1.png", "choker_half_pri_lines_vertical_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, PRI, SMALL_LINES, BASE_PATH, VARIANT_PATH, "choker_half_pri_lines_small_1.png", "choker_half_pri_lines_small_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, PRI, BIG_LINES, BASE_PATH, VARIANT_PATH, "choker_half_pri_lines_big_1.png", "choker_half_pri_lines_big_2.png", true));

            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, SEC, HALF, BASE_PATH, VARIANT_PATH, "choker_half_sec_half_1.png", "choker_half_sec_half_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, SEC, CHECKERS_SMALL, BASE_PATH, VARIANT_PATH, "choker_half_sec_checkers_small_1.png", "choker_half_sec_checkers_small_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, SEC, VERTICAL_LINES, BASE_PATH, VARIANT_PATH, "choker_half_sec_lines_vertical_1.png", "choker_half_sec_lines_vertical_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, SEC, SMALL_LINES, BASE_PATH, VARIANT_PATH, "choker_half_sec_lines_small_1.png", "choker_half_sec_lines_small_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, SEC, BIG_LINES, BASE_PATH, VARIANT_PATH, "choker_half_sec_lines_big_1.png", "choker_half_sec_lines_big_2.png", true));
        }
    }

    private static class Skirt {
        private static final String TYPE = "skirt";
        private static final String VARIANT_PATH = BASE_PATH + "variants/skirt/";
        private static final String PRI = "cotton_pri";
        private static final String SEC = "cotton_sec";

        private static void register() {
            base();
            variants();
        }

        private static void base() {
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, PRI, DEFAULT, BASE_PATH, VARIANT_PATH, "cotton_skirt_pri.png", "cotton_skirt_pri.png", false));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, SEC, DEFAULT, BASE_PATH, VARIANT_PATH, "cotton_skirt_sec.png", "cotton_skirt_sec.png", false));
        }

        private static void variants() {

        }
    }

    private static class Ushanka {
        private static final String TYPE = "ushanka";
        private static final String VARIANT_PATH = BASE_PATH + "variants/ushanka/";
        private static final String PRI = "pri";
        private static final String SEC = "sec";

        private static void register() {
            base();
            variants();
        }

        private static void base() {
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, PRI, DEFAULT, BASE_PATH, VARIANT_PATH, "ushanka_pri.png", "ushanka_pri.png", false));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, SEC, DEFAULT, BASE_PATH, VARIANT_PATH, "ushanka_sec.png", "ushanka_sec.png", false));
        }

        private static void variants() {
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, PRI, HALF, BASE_PATH, VARIANT_PATH, "ushanka_pri_half_1.png", "ushanka_pri_half_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, PRI, CHECKERS, BASE_PATH, VARIANT_PATH, "ushanka_pri_checkers_1.png", "ushanka_pri_checkers_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, PRI, CHECKERS_SMALL, BASE_PATH, VARIANT_PATH, "ushanka_pri_checkers_small_1.png", "ushanka_pri_checkers_small_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, PRI, VERTICAL_LINES, BASE_PATH, VARIANT_PATH, "ushanka_pri_vertical_lines_1.png", "ushanka_pri_vertical_lines_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, PRI, SMALL_LINES, BASE_PATH, VARIANT_PATH, "ushanka_pri_small_lines_1.png", "ushanka_pri_small_lines_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, PRI, BIG_LINES, BASE_PATH, VARIANT_PATH, "ushanka_pri_big_lines_1.png", "ushanka_pri_big_lines_2.png", true));

            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, SEC, HALF, BASE_PATH, VARIANT_PATH, "ushanka_sec_half_1.png", "ushanka_sec_half_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, SEC, CHECKERS, BASE_PATH, VARIANT_PATH, "ushanka_sec_checkers_1.png", "ushanka_sec_checkers_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, SEC, CHECKERS_SMALL, BASE_PATH, VARIANT_PATH, "ushanka_sec_checkers_small_1.png", "ushanka_sec_checkers_small_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, SEC, VERTICAL_LINES, BASE_PATH, VARIANT_PATH, "ushanka_sec_vertical_lines_1.png", "ushanka_sec_vertical_lines_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, SEC, SMALL_LINES, BASE_PATH, VARIANT_PATH, "ushanka_sec_small_lines_1.png", "ushanka_sec_small_lines_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, SEC, BIG_LINES, BASE_PATH, VARIANT_PATH, "ushanka_sec_big_lines_1.png", "ushanka_sec_big_lines_2.png", true));
        }
    }

    private static class Cap {
        private static final String TYPE = "cap";
        private static final String VARIANT_PATH = BASE_PATH + "variants/cap/";

        private static void register() {
            base();
            variants();
        }

        private static void base() {
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, JEANS, DEFAULT, BASE_PATH, VARIANT_PATH, "cap.png", "cap.png", false));
        }

        private static void variants() {
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, JEANS, CHECKERS, BASE_PATH, VARIANT_PATH, "cap_checkers_1.png", "cap_checkers_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, JEANS, CHECKERS_SMALL, BASE_PATH, VARIANT_PATH, "cap_checkers_small_1.png", "cap_checkers_small_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, JEANS, SMALL_LINES, BASE_PATH, VARIANT_PATH, "cap_small_lines_1.png", "cap_small_lines_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, JEANS, BIG_LINES, BASE_PATH, VARIANT_PATH, "cap_big_lines_1.png", "cap_big_lines_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, JEANS, VERTICAL_LINES, BASE_PATH, VARIANT_PATH, "cap_vertical_lines_1.png", "cap_vertical_lines_2.png", true));
        }
    }

    private static class PomponHat {
        private static final String TYPE = "pompon_hat";
        private static final String VARIANT_PATH = BASE_PATH + "variants/pompon_hat/";

        private static void register() {
            base();
            variants();
        }

        private static void base() {
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, COTTON, DEFAULT, BASE_PATH, VARIANT_PATH, "pompon_hat.png", "pompon_hat.png", false));
        }

        private static void variants() {
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, COTTON, SMALL_LINES, BASE_PATH, VARIANT_PATH, "pompon_hat_small_lines_1.png", "pompon_hat_small_lines_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, COTTON, BIG_LINES, BASE_PATH, VARIANT_PATH, "pompon_hat_big_lines_1.png", "pompon_hat_big_lines_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, COTTON, VERTICAL_LINES, BASE_PATH, VARIANT_PATH, "pompon_hat_vertical_lines_1.png", "pompon_hat_vertical_lines_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, COTTON, CHECKERS, BASE_PATH, VARIANT_PATH, "pompon_hat_checkers_1.png", "pompon_hat_checkers_2.png", true));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, COTTON, CHECKERS_SMALL, BASE_PATH, VARIANT_PATH, "pompon_hat_checkers_small_1.png", "pompon_hat_checkers_small_2.png", true));
        }
    }

    private static class ChokerTrinkets {
        private static void register() {
            Bell.register();
            CatRing.register();
            Heart.register();
            Ring.register();
            Plate.register();
            Fish.register();
        }

        private static class Bell {
            private static final String TYPE = "bell";

            private static void register() {
                TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, DEFAULT, DEFAULT, BASE_PATH, "", "bell.png", "bell.png", false));
                TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, "minecraft:gold_ingot", DEFAULT, BASE_PATH, "", "bell_golden.png", "bell_golden.png", true));
            }
        }

        private static class CatRing {
            private static final String TYPE = "cat_ring";

            private static void register() {
                TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, DEFAULT, DEFAULT, BASE_PATH, "", "cat_ring.png", "cat_ring.png", false));
                TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, "minecraft:gold_ingot", DEFAULT, BASE_PATH, "", "cat_ring_golden.png", "cat_ring_golden.png", true));
            }
        }

        private static class Heart {
            private static final String TYPE = "heart";

            private static void register() {
                TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, DEFAULT, DEFAULT, BASE_PATH, "", "heart.png", "heart.png", false));
                TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, "minecraft:gold_ingot", DEFAULT, BASE_PATH, "", "heart_golden.png", "heart_golden.png", true));
            }
        }

        private static class Ring {
            private static final String TYPE = "ring";

            private static void register() {
                TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, DEFAULT, DEFAULT, BASE_PATH, "", "ring.png", "ring.png", false));
                TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, "minecraft:gold_ingot", DEFAULT, BASE_PATH, "", "ring_golden.png", "ring_golden.png", true));
            }
        }

        private static class Plate {
            private static final String TYPE = "plate";

            private static void register() {
                TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, DEFAULT, DEFAULT, BASE_PATH, "", "plate.png", "plate.png", false));
                TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, "minecraft:gold_ingot", DEFAULT, BASE_PATH, "", "plate_golden.png", "plate_golden.png", true));
            }
        }

        private static class Fish {
            private static final String TYPE = "fish";

            private static void register() {
                TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, DEFAULT, DEFAULT, BASE_PATH, "", "fish.png", "fish.png", false));
                TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, "minecraft:gold_ingot", DEFAULT, BASE_PATH, "", "fish_golden.png", "fish_golden.png", true));
            }
        }
    }

    private static class Blahaj {
        private static final String TYPE = "blahaj";

        private static void register() {
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE, DEFAULT, DEFAULT, BASE_PATH, "", "blahaj_main.png", "blahaj_main.png", false));
            TexturesRegistrar.registerTexture(new TexturesRegistrar.Texture(MOD_ID, TYPE + "_sec", DEFAULT, DEFAULT, BASE_PATH, "", "blahaj_secondary.png", "blahaj_secondary.png", false));
        }
    }
}
