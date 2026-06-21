package xox.labvorty.weaversparadise.data.texture.deprecated;

import net.minecraft.resources.ResourceLocation;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum HandWarmersTextures {
    DEFAULT_COTTON("default",
            new ResourceLocation(
                    "weaversparadise",
                    "textures/clothing/mitten_base.png"
            ),
            new ResourceLocation(
                    "weaversparadise",
                    "textures/clothing/mitten_base.png"
            ),
            "single",
            "cotton"
    ),
    DEFAULT_WOOL("default",
            new ResourceLocation(
                    "weaversparadise",
                    "textures/clothing/mitten_warm.png"
            ),
            new ResourceLocation(
                    "weaversparadise",
                    "textures/clothing/mitten_warm.png"
            ),
            "single",
            "wool"
    ),
    DEFAULT_SILK("default",
            new ResourceLocation(
                    "weaversparadise",
                    "textures/clothing/mitten_silk.png"
            ),
            new ResourceLocation(
                    "weaversparadise",
                    "textures/clothing/mitten_silk.png"
            ),
            "single",
            "silk"
    ),
    HALF_COTTON("half",
            new ResourceLocation(
                    "weaversparadise",
                    "textures/clothing/variants/mittens/mitten_cotton_half_1.png"
            ),
            new ResourceLocation(
                    "weaversparadise",
                    "textures/clothing/variants/mittens/mitten_cotton_half_2.png"
            ),
            "double",
            "cotton"
    ),
    HALF_WOOL("half",
            new ResourceLocation(
                    "weaversparadise",
                    "textures/clothing/variants/mittens/mitten_wool_half_1.png"
            ),
            new ResourceLocation(
                    "weaversparadise",
                    "textures/clothing/variants/mittens/mitten_wool_half_2.png"
            ),
            "double",
            "wool"
    ),
    HALF_SILK("half",
            new ResourceLocation(
                    "weaversparadise",
                    "textures/clothing/variants/mittens/mitten_silk_half_1.png"
            ),
            new ResourceLocation(
                    "weaversparadise",
                    "textures/clothing/variants/mittens/mitten_silk_half_2.png"
            ),
            "double",
            "silk"
    ),
    CHECKERS_COTTON("checkers",
            new ResourceLocation(
                    "weaversparadise",
                    "textures/clothing/variants/mittens/mitten_cotton_checkers_1.png"
            ),
            new ResourceLocation(
                    "weaversparadise",
                    "textures/clothing/variants/mittens/mitten_cotton_checkers_2.png"
            ),
            "double",
            "cotton"
    ),
    CHECKERS_WOOL("checkers",
            new ResourceLocation(
                    "weaversparadise",
                    "textures/clothing/variants/mittens/mitten_wool_checkers_1.png"
            ),
            new ResourceLocation(
                    "weaversparadise",
                    "textures/clothing/variants/mittens/mitten_wool_checkers_2.png"
            ),
            "double",
            "wool"
    ),
    CHECKERS_SILK("checkers",
            new ResourceLocation(
                    "weaversparadise",
                    "textures/clothing/variants/mittens/mitten_silk_checkers_1.png"
            ),
            new ResourceLocation(
                    "weaversparadise",
                    "textures/clothing/variants/mittens/mitten_silk_checkers_2.png"
            ),
            "double",
            "silk"
    ),
    VERTICAL_LINE_COTTON("vertical_lines",
            new ResourceLocation(
                    "weaversparadise",
                    "textures/clothing/variants/mittens/mitten_cotton_vertical_lines_1.png"
            ),
            new ResourceLocation(
                    "weaversparadise",
                    "textures/clothing/variants/mittens/mitten_cotton_vertical_lines_2.png"
            ),
            "double",
            "cotton"
    ),
    VERTICAL_LINE_WOOL("vertical_lines",
            new ResourceLocation(
                    "weaversparadise",
                    "textures/clothing/variants/mittens/mitten_wool_vertical_lines_1.png"
            ),
            new ResourceLocation(
                    "weaversparadise",
                    "textures/clothing/variants/mittens/mitten_wool_vertical_lines_2.png"
            ),
            "double",
            "wool"
    ),
    VERTICAL_LINE_SILK("vertical_lines",
            new ResourceLocation(
                    "weaversparadise",
                    "textures/clothing/variants/mittens/mitten_silk_vertical_lines_1.png"
            ),
            new ResourceLocation(
                    "weaversparadise",
                    "textures/clothing/variants/mittens/mitten_silk_vertical_lines_2.png"
            ),
            "double",
            "silk"
    ),
    SMALL_LINE_COTTON("small_lines",
            new ResourceLocation(
                    "weaversparadise",
                    "textures/clothing/variants/mittens/mitten_cotton_small_lines_1.png"
            ),
            new ResourceLocation(
                    "weaversparadise",
                    "textures/clothing/variants/mittens/mitten_cotton_small_lines_2.png"
            ),
            "double",
            "cotton"
    ),
    SMALL_LINE_WOOL("small_lines",
            new ResourceLocation(
                    "weaversparadise",
                    "textures/clothing/variants/mittens/mitten_wool_small_lines_1.png"
            ),
            new ResourceLocation(
                    "weaversparadise",
                    "textures/clothing/variants/mittens/mitten_wool_small_lines_2.png"
            ),
            "double",
            "wool"
    ),
    SMALL_LINE_SILK("small_lines",
            new ResourceLocation(
                    "weaversparadise",
                    "textures/clothing/variants/mittens/mitten_silk_small_lines_1.png"
            ),
            new ResourceLocation(
                    "weaversparadise",
                    "textures/clothing/variants/mittens/mitten_silk_small_lines_2.png"
            ),
            "double",
            "silk"
    ),
    BIG_LINE_COTTON("big_lines",
            new ResourceLocation(
                    "weaversparadise",
                    "textures/clothing/variants/mittens/mitten_cotton_big_lines_1.png"
            ),
            new ResourceLocation(
                    "weaversparadise",
                    "textures/clothing/variants/mittens/mitten_cotton_big_lines_2.png"
            ),
            "double",
            "cotton"
    ),
    BIG_LINE_WOOL("big_lines",
            new ResourceLocation(
                    "weaversparadise",
                    "textures/clothing/variants/mittens/mitten_wool_big_lines_1.png"
            ),
            new ResourceLocation(
                    "weaversparadise",
                    "textures/clothing/variants/mittens/mitten_wool_big_lines_2.png"
            ),
            "double",
            "wool"
    ),
    BIG_LINE_SILK("big_lines",
            new ResourceLocation(
                    "weaversparadise",
                    "textures/clothing/variants/mittens/mitten_silk_big_lines_1.png"
            ),
            new ResourceLocation(
                    "weaversparadise",
                    "textures/clothing/variants/mittens/mitten_silk_big_lines_2.png"
            ),
            "double",
            "silk"
    ),
    CROSS_COTTON("cross",
            new ResourceLocation(
                    "weaversparadise",
                    "textures/clothing/variants/mittens/mitten_cotton_cross_2.png"
            ),
            new ResourceLocation(
                    "weaversparadise",
                    "textures/clothing/variants/mittens/mitten_cotton_cross_1.png"
            ),
            "double",
            "cotton"
    ),
    CROSS_WOOL("cross",
            new ResourceLocation(
                    "weaversparadise",
                    "textures/clothing/variants/mittens/mitten_wool_cross_2.png"
            ),
            new ResourceLocation(
                    "weaversparadise",
                    "textures/clothing/variants/mittens/mitten_wool_cross_1.png"
            ),
            "double",
            "wool"
    ),
    CROSS_SILK("cross",
            new ResourceLocation(
                    "weaversparadise",
                    "textures/clothing/variants/mittens/mitten_silk_cross_2.png"
            ),
            new ResourceLocation(
                    "weaversparadise",
                    "textures/clothing/variants/mittens/mitten_silk_cross_1.png"
            ),
            "double",
            "silk"
    ),
    PAWS_COTTON("paws",
            new ResourceLocation(
                    "weaversparadise",
                    "textures/clothing/variants/mittens/mitten_cotton_paws_1.png"
            ),
            new ResourceLocation(
                    "weaversparadise",
                    "textures/clothing/variants/mittens/mitten_cotton_paws_2.png"
            ),
            "double",
            "cotton"
    ),
    PAWS_WOOL("paws",
            new ResourceLocation(
                    "weaversparadise",
                    "textures/clothing/variants/mittens/mitten_wool_paws_1.png"
            ),
            new ResourceLocation(
                    "weaversparadise",
                    "textures/clothing/variants/mittens/mitten_wool_paws_2.png"
            ),
            "double",
            "wool"
    ),
    PAWS_SILK("paws",
            new ResourceLocation(
                    "weaversparadise",
                    "textures/clothing/variants/mittens/mitten_silk_paws_1.png"
            ),
            new ResourceLocation(
                    "weaversparadise",
                    "textures/clothing/variants/mittens/mitten_silk_paws_2.png"
            ),
            "double",
            "silk"
    ),
    CHECKERS_SMALL_COTTON("checkers_small",
            new ResourceLocation(
                    "weaversparadise",
                    "textures/clothing/variants/mittens/mitten_cotton_checkers_small_1.png"
            ),
            new ResourceLocation(
                    "weaversparadise",
                    "textures/clothing/variants/mittens/mitten_cotton_checkers_small_2.png"
            ),
            "double",
            "cotton"
    ),
    CHECKERS_SMALL_WOOL("checkers_small",
            new ResourceLocation(
                    "weaversparadise",
                    "textures/clothing/variants/mittens/mitten_wool_checkers_small_1.png"
            ),
            new ResourceLocation(
                    "weaversparadise",
                    "textures/clothing/variants/mittens/mitten_wool_checkers_small_2.png"
            ),
            "double",
            "wool"
    ),
    CHECKERS_SMALL_SILK("checkers_small",
            new ResourceLocation(
                    "weaversparadise",
                    "textures/clothing/variants/mittens/mitten_silk_checkers_small_1.png"
            ),
            new ResourceLocation(
                    "weaversparadise",
                    "textures/clothing/variants/mittens/mitten_silk_checkers_small_2.png"
            ),
            "double",
            "silk"
    );

    private final String type;
    private final ResourceLocation textureOne;
    private final ResourceLocation textureTwo;
    private final String renderType;
    private final String material;

    HandWarmersTextures(String type, ResourceLocation textureOne, ResourceLocation textureTwo, String renderType, String material) {
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

    private static final Map<Pair<String, String>, HandWarmersTextures> HANDLER_MAP =
            Stream.of(values())
                    .collect(Collectors.toMap(
                            e -> Pair.of(e.getType(), e.getMaterial()),
                            e -> e
                    ));

    public static HandWarmersTextures getByTypeAndMaterial(String type, String material) {
        Pair<String, String> typematPair = Pair.of(type, material);

        return HANDLER_MAP.getOrDefault(typematPair, DEFAULT_COTTON);
    }
}