package xox.labvorty.weaversparadise.data;

import net.minecraft.resources.ResourceLocation;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum WeaversParadisePantsTextureHandler {
    DEFAULT_JEANS("default",
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/clothing/pants_jeans.png"
            ),
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/clothing/pants_jeans.png"
            ),
            "single",
            "jeans"),
    DEFAULT_COTTON("default",
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/clothing/pants_cotton.png"
            ),
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/clothing/pants_cotton.png"
            ),
            "single",
            "cotton"),
    DEFAULT_SILK("default",
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/clothing/pants_silk.png"
            ),
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/clothing/pants_silk.png"
            ),
            "single",
            "silk"),
    HALF_JEANS("half",
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/clothing/variants/pants/pants_jeans_half_1.png"
            ),
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/clothing/variants/pants/pants_jeans_half_2.png"
            ),
            "double",
            "jeans"),
    HALF_COTTON("half",
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/clothing/variants/pants/pants_cotton_half_1.png"
            ),
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/clothing/variants/pants/pants_cotton_half_2.png"
            ),
            "double",
            "cotton"),
    HALF_SILK("half",
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/clothing/variants/pants/pants_silk_half_1.png"
            ),
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/clothing/variants/pants/pants_silk_half_2.png"
            ),
            "double",
            "silk"),
    CHECKERS_JEANS("checkers",
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/clothing/variants/pants/pants_jeans_checkers_1.png"
            ),
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/clothing/variants/pants/pants_jeans_checkers_2.png"
            ),
            "double",
            "jeans"),
    CHECKERS_COTTON("checkers",
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/clothing/variants/pants/pants_cotton_checkers_1.png"
            ),
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/clothing/variants/pants/pants_cotton_checkers_2.png"
            ),
            "double",
            "cotton"),
    CHECKERS_SILK("checkers",
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/clothing/variants/pants/pants_silk_checkers_1.png"
            ),
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/clothing/variants/pants/pants_silk_checkers_2.png"
            ),
            "double",
            "silk"),
    CHECKERS_SMALL_JEANS("checkers_small",
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/clothing/variants/pants/pants_jeans_checkers_small_1.png"
            ),
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/clothing/variants/pants/pants_jeans_checkers_small_2.png"
            ),
            "double",
            "jeans"),
    CHECKERS_SMALL_COTTON("checkers_small",
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/clothing/variants/pants/pants_cotton_checkers_small_1.png"
            ),
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/clothing/variants/pants/pants_cotton_checkers_small_2.png"
            ),
            "double",
            "cotton"),
    CHECKERS_SMALL_SILK("checkers_small",
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/clothing/variants/pants/pants_silk_checkers_small_1.png"
            ),
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/clothing/variants/pants/pants_silk_checkers_small_2.png"
            ),
            "double",
            "silk"),
    LINES_SMALL_JEANS("small_lines",
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/clothing/variants/pants/pants_jeans_small_lines_1.png"
            ),
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/clothing/variants/pants/pants_jeans_small_lines_2.png"
            ),
            "double",
            "jeans"),
    LINES_SMALL_COTTON("small_lines",
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/clothing/variants/pants/pants_cotton_small_lines_1.png"
            ),
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/clothing/variants/pants/pants_cotton_small_lines_2.png"
            ),
            "double",
            "cotton"),
    LINES_SMALL_SILK("small_lines",
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/clothing/variants/pants/pants_silk_small_lines_1.png"
            ),
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/clothing/variants/pants/pants_silk_small_lines_2.png"
            ),
            "double",
            "silk"),
    LINES_BIG_JEANS("big_lines",
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/clothing/variants/pants/pants_jeans_big_lines_1.png"
            ),
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/clothing/variants/pants/pants_jeans_big_lines_2.png"
            ),
            "double",
            "jeans"),
    LINES_BIG_COTTON("big_lines",
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/clothing/variants/pants/pants_cotton_big_lines_1.png"
            ),
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/clothing/variants/pants/pants_cotton_big_lines_2.png"
            ),
            "double",
            "cotton"),
    LINES_BIG_SILK("big_lines",
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/clothing/variants/pants/pants_silk_big_lines_1.png"
            ),
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/clothing/variants/pants/pants_silk_big_lines_2.png"
            ),
            "double",
            "silk"),
    LINES_VERTICAL_JEANS("vertical_lines",
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/clothing/variants/pants/pants_jeans_vertical_lines_1.png"
            ),
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/clothing/variants/pants/pants_jeans_vertical_lines_2.png"
            ),
            "double",
            "jeans"),
    LINES_VERTICAL_COTTON("vertical_lines",
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/clothing/variants/pants/pants_cotton_vertical_lines_1.png"
            ),
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/clothing/variants/pants/pants_cotton_vertical_lines_2.png"
            ),
            "double",
            "cotton"),
    LINES_VERTICAL_SILK("vertical_lines",
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/clothing/variants/pants/pants_silk_vertical_lines_1.png"
            ),
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/clothing/variants/pants/pants_silk_vertical_lines_2.png"
            ),
            "double",
            "silk"),
    STARS_JEANS("stars",
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/clothing/variants/pants/pants_jeans_stars.png"
            ),
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/clothing/variants/pants/pants_basic_stars.png"
            ),
            "double",
            "jeans"),
    STARS_COTTON("stars",
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/clothing/variants/pants/pants_cotton_stars.png"
            ),
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/clothing/variants/pants/pants_basic_stars.png"
            ),
            "double",
            "cotton"),
    STARS_SILK("stars",
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/clothing/variants/pants/pants_silk_stars.png"
            ),
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/clothing/variants/pants/pants_basic_stars.png"
            ),
            "double",
            "silk"),
    DIRT_JEANS("dirt",
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/clothing/variants/pants/pants_jeans_dirt.png"
            ),
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/clothing/variants/pants/pants_basic_dirt.png"
            ),
            "double",
            "jeans"),
    DIRT_COTTON("dirt",
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/clothing/variants/pants/pants_cotton_dirt.png"
            ),
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/clothing/variants/pants/pants_basic_dirt.png"
            ),
            "double",
            "cotton"),
    DIRT_SILK("dirt",
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/clothing/variants/pants/pants_silk_dirt.png"
            ),
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/clothing/variants/pants/pants_basic_dirt.png"
            ),
            "double",
            "silk"),
    FLOWERS_JEANS("flowers",
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/clothing/variants/pants/pants_jeans_flowers.png"
            ),
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/clothing/variants/pants/pants_basic_flowers.png"
            ),
            "double",
            "jeans"),
    FLOWERS_COTTON("flowers",
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/clothing/variants/pants/pants_cotton_flowers.png"
            ),
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/clothing/variants/pants/pants_basic_flowers.png"
            ),
            "double",
            "cotton"),
    FLOWERS_SILK("flowers",
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/clothing/variants/pants/pants_silk_flowers.png"
            ),
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/clothing/variants/pants/pants_basic_flowers.png"
            ),
            "double",
            "silk");

    private final String type;
    private final ResourceLocation textureOne;
    private final ResourceLocation textureTwo;
    private final String renderType;
    private final String material;

    WeaversParadisePantsTextureHandler(String type, ResourceLocation textureOne, ResourceLocation textureTwo, String renderType, String material) {
        this.type = type;
        this.textureOne = textureOne;
        this.textureTwo = textureTwo;
        this.renderType = renderType;
        this.material = material;
    }

    public String getType() {
        return type;
    }

    public ResourceLocation getTextureOne() {
        return textureOne;
    }

    public ResourceLocation getTextureTwo() {
        return textureTwo;
    }

    public String getRenderType() {
        return renderType;
    }

    public String getMaterial() {
        return material;
    }

    private static final Map<Pair<String, String>, WeaversParadisePantsTextureHandler> HANDLER_MAP =
            Stream.of(values())
                    .collect(Collectors.toMap(
                            e -> Pair.of(e.getType(), e.getMaterial()),
                            e -> e
                    ));

    public static WeaversParadisePantsTextureHandler getByTypeAndMaterial(String type, String material) {
        Pair<String, String> typematPair = Pair.of(type, material);

        return HANDLER_MAP.getOrDefault(typematPair, DEFAULT_COTTON);
    }
}
