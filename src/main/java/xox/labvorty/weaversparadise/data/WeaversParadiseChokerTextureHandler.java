package xox.labvorty.weaversparadise.data;

import net.minecraft.resources.ResourceLocation;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum WeaversParadiseChokerTextureHandler {
    DEFAULT("default",
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/clothing/chocker_half_pri.png"
            ),
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/clothing/chocker_half_pri.png"
            ),
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/clothing/chocker_half_sec.png"
            ),
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/clothing/chocker_half_sec.png"
            ),
            "single",
            "base"
    ),
    HALF("half",
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/clothing/variants/choker/choker_half_pri_half_1.png"
            ),
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/clothing/variants/choker/choker_half_pri_half_2.png"
            ),
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/clothing/variants/choker/choker_half_sec_half_1.png"
            ),
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/clothing/variants/choker/choker_half_sec_half_2.png"
            ),
            "double",
            "base"
    ),
    VERTICAL_LINE("vertical_lines",
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/clothing/variants/choker/choker_half_pri_lines_vertical_1.png"
            ),
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/clothing/variants/choker/choker_half_pri_lines_vertical_2.png"
            ),
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/clothing/variants/choker/choker_half_sec_lines_vertical_1.png"
            ),
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/clothing/variants/choker/choker_half_sec_lines_vertical_2.png"
            ),
            "double",
            "base"
    ),
    SMALL_LINE("small_lines",
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/clothing/variants/choker/choker_half_pri_lines_small_1.png"
            ),
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/clothing/variants/choker/choker_half_pri_lines_small_2.png"
            ),
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/clothing/variants/choker/choker_half_sec_lines_small_1.png"
            ),
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/clothing/variants/choker/choker_half_sec_lines_small_2.png"
            ),
            "double",
            "base"
    ),
    BIG_LINE("big_lines",
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/clothing/variants/choker/choker_half_pri_lines_big_1.png"
            ),
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/clothing/variants/choker/choker_half_pri_lines_big_2.png"
            ),
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/clothing/variants/choker/choker_half_sec_lines_big_1.png"
            ),
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/clothing/variants/choker/choker_half_sec_lines_big_2.png"
            ),
            "double",
            "base"
    ),
    CHECKERS_SMALL("checkers_small",
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/clothing/variants/choker/choker_half_pri_checkers_small_1.png"
            ),
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/clothing/variants/choker/choker_half_pri_checkers_small_2.png"
            ),
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/clothing/variants/choker/choker_half_sec_checkers_small_1.png"
            ),
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/clothing/variants/choker/choker_half_sec_checkers_small_2.png"
            ),
            "double",
            "base"
    );

    private final String type;
    private final ResourceLocation textureOne;
    private final ResourceLocation textureTwo;
    private final ResourceLocation textureThree;
    private final ResourceLocation textureFour;
    private final String renderType;
    private final String material;

    WeaversParadiseChokerTextureHandler(
            String type,
            ResourceLocation textureOne,
            ResourceLocation textureTwo,
            ResourceLocation textureThree,
            ResourceLocation textureFour,
            String renderType,
            String material
    ) {
        this.type = type;
        this.textureOne = textureOne;
        this.textureTwo = textureTwo;
        this.textureThree = textureThree;
        this.textureFour = textureFour;
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

    public ResourceLocation getTextureThree() {
        return textureThree;
    }

    public ResourceLocation getTextureFour() {
        return textureFour;
    }

    public String getRenderType() {
        return renderType;
    }

    public String getMaterial() {
        return material;
    }

    private static final Map<Pair<String, String>, WeaversParadiseChokerTextureHandler> HANDLER_MAP =
            Stream.of(values())
                    .collect(Collectors.toMap(
                            e -> Pair.of(e.getType(), e.getMaterial()),
                            e -> e
                    ));

    public static WeaversParadiseChokerTextureHandler getByTypeAndMaterial(String type, String material) {
        Pair<String, String> typematPair = Pair.of(type, material);

        return HANDLER_MAP.getOrDefault(typematPair, DEFAULT);
    }
}
