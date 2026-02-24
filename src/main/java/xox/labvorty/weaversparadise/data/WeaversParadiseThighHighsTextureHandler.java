package xox.labvorty.weaversparadise.data;

import net.minecraft.resources.ResourceLocation;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum WeaversParadiseThighHighsTextureHandler {
    DEFAULT_COTTON("default",
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/clothing/thigh_highs_base.png"
            ),
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/clothing/thigh_highs_base.png"
            ),
            "single",
            "cotton"
    ),
    DEFAULT_WOOL("default",
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/clothing/thigh_highs_warm.png"
            ),
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/clothing/thigh_highs_warm.png"
            ),
            "single",
            "wool"
    ),
    DEFAULT_SILK("default",
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/clothing/thigh_highs_silk.png"
            ),
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/clothing/thigh_highs_silk.png"
            ),
            "single",
            "silk"
    ),
    HALF_COTTON("half",
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/clothing/variants/thigh_highs_base_half_one.png"
            ),
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/clothing/variants/thigh_highs_base_half_two.png"
            ),
            "double",
            "cotton"
    ),
    HALF_WOOL("half",
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/clothing/variants/thigh_highs_warm_half_one.png"
            ),
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/clothing/variants/thigh_highs_warm_half_two.png"
            ),
            "double",
            "wool"
    ),
    HALF_SILK("half",
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/clothing/variants/thigh_highs_silk_half1.png"
            ),
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/clothing/variants/thigh_highs_silk_half2.png"
            ),
            "double",
            "silk"
    ),
    CHECKERS_COTTON("checkers",
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/clothing/variants/thigh_highs_base_checkers1.png"
            ),
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/clothing/variants/thigh_highs_base_checkers2.png"
            ),
            "double",
            "cotton"
    ),
    CHECKERS_WOOL("checkers",
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/clothing/variants/thigh_highs_warm_checkers1.png"
            ),
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/clothing/variants/thigh_highs_warm_checkers2.png"
            ),
            "double",
            "wool"
    ),
    CHECKERS_SILK("checkers",
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/clothing/variants/thigh_highs_silk_checkers1.png"
            ),
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/clothing/variants/thigh_highs_silk_checkers2.png"
            ),
            "double",
            "silk"
    ),
    VERTICAL_LINE_COTTON("vertical_lines",
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/clothing/variants/thigh_highs_base_vertical_lines1.png"
            ),
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/clothing/variants/thigh_highs_base_vertical_lines2.png"
            ),
            "double",
            "cotton"
    ),
    VERTICAL_LINE_WOOL("vertical_lines",
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/clothing/variants/thigh_highs_warm_vertical_lines1.png"
            ),
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/clothing/variants/thigh_highs_warm_vertical_lines2.png"
            ),
            "double",
            "wool"
    ),
    VERTICAL_LINE_SILK("vertical_lines",
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/clothing/variants/thigh_highs_silk_vertical_lines1.png"
            ),
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/clothing/variants/thigh_highs_silk_vertical_lines2.png"
            ),
            "double",
            "silk"
    ),
    SMALL_LINE_COTTON("small_lines",
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/clothing/variants/thigh_highs_base_small_lines1.png"
            ),
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/clothing/variants/thigh_highs_base_small_lines2.png"
            ),
            "double",
            "cotton"
    ),
    SMALL_LINE_WOOL("small_lines",
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/clothing/variants/thigh_highs_warm_small_lines1.png"
            ),
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/clothing/variants/thigh_highs_warm_small_lines2.png"
            ),
            "double",
            "wool"
    ),
    SMALL_LINE_SILK("small_lines",
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/clothing/variants/thigh_highs_silk_small_lines1.png"
            ),
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/clothing/variants/thigh_highs_silk_small_lines2.png"
            ),
            "double",
            "silk"
    ),
    BIG_LINE_COTTON("big_lines",
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/clothing/variants/thigh_highs_base_big_lines1.png"
            ),
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/clothing/variants/thigh_highs_base_big_lines2.png"
            ),
            "double",
            "cotton"
    ),
    BIG_LINE_WOOL("big_lines",
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/clothing/variants/thigh_highs_warm_big_lines1.png"
            ),
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/clothing/variants/thigh_highs_warm_big_lines2.png"
            ),
            "double",
            "wool"
    ),
    BIG_LINE_SILK("big_lines",
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/clothing/variants/thigh_highs_silk_big_lines1.png"
            ),
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/clothing/variants/thigh_highs_silk_big_lines2.png"
            ),
            "double",
            "silk"
    ),
    CROSS_COTTON("cross",
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/clothing/variants/thigh_highs_base_cross_2.png"
            ),
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/clothing/variants/thigh_highs_base_cross_1.png"
            ),
            "double",
            "cotton"
    ),
    CROSS_WOOL("cross",
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/clothing/variants/thigh_highs_warm_cross_2.png"
            ),
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/clothing/variants/thigh_highs_warm_cross_1.png"
            ),
            "double",
            "wool"
    ),
    CROSS_SILK("cross",
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/clothing/variants/thigh_highs_silk_cross_2.png"
            ),
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/clothing/variants/thigh_highs_silk_cross_1.png"
            ),
            "double",
            "silk"
    ),
    PAWS_COTTON("paws",
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/clothing/variants/thigh_highs_base_paws_1.png"
            ),
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/clothing/variants/thigh_highs_base_paws_2.png"
            ),
            "double",
            "cotton"
    ),
    PAWS_WOOL("paws",
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/clothing/variants/thigh_highs_warm_paws_1.png"
            ),
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/clothing/variants/thigh_highs_warm_paws_2.png"
            ),
            "double",
            "wool"
    ),
    PAWS_SILK("paws",
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/clothing/variants/thigh_highs_silk_paws_1.png"
            ),
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/clothing/variants/thigh_highs_silk_paws_2.png"
            ),
            "double",
            "silk"
    ),
    CHECKERS_SMALL_COTTON("checkers_small",
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/clothing/variants/thigh_highs_base_checkers_small_1.png"
            ),
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/clothing/variants/thigh_highs_base_checkers_small_2.png"
            ),
            "double",
            "cotton"
    ),
    CHECKERS_SMALL_WOOL("checkers_small",
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/clothing/variants/thigh_highs_warm_checkers_small_1.png"
            ),
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/clothing/variants/thigh_highs_warm_checkers_small_2.png"
            ),
            "double",
            "wool"
    ),
    CHECKERS_SMALL_SILK("checkers_small",
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/clothing/variants/thigh_highs_silk_checkers_small_1.png"
            ),
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/clothing/variants/thigh_highs_silk_checkers_small_2.png"
            ),
            "double",
            "silk"
    );

    private final String type;
    private final ResourceLocation textureOne;
    private final ResourceLocation textureTwo;
    private final String renderType;
    private final String material;

    WeaversParadiseThighHighsTextureHandler(String type, ResourceLocation textureOne, ResourceLocation textureTwo, String renderType, String material) {
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

    private static final Map<Pair<String, String>, WeaversParadiseThighHighsTextureHandler> HANDLER_MAP =
            Stream.of(values())
                    .collect(Collectors.toMap(
                            e -> Pair.of(e.getType(), e.getMaterial()),
                            e -> e
                    ));

    public static WeaversParadiseThighHighsTextureHandler getByTypeAndMaterial(String type, String material) {
        Pair<String, String> typematPair = Pair.of(type, material);

        return HANDLER_MAP.getOrDefault(typematPair, DEFAULT_COTTON);
    }
}
