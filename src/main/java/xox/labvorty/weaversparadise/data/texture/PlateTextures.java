package xox.labvorty.weaversparadise.data.texture;

import net.minecraft.resources.ResourceLocation;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum PlateTextures {
    DEFAULT(
            "default",
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/clothing/plate.png"
            ),
            false
    ),
    GOLDEN(
            "minecraft:gold_ingot",
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/clothing/plate_golden.png"
            ),
            true
    );

    private String metalType;
    private ResourceLocation texture;
    private boolean freezeColor;

    PlateTextures(
            String metalType,
            ResourceLocation texture,
            boolean freezeColor
    ) {
        this.metalType = metalType;
        this.texture = texture;
        this.freezeColor = freezeColor;
    }

    public String getMetalType() {
        return metalType;
    }

    public ResourceLocation getTexture() {
        return texture;
    }

    public boolean isFreezeColor() {
        return freezeColor;
    }

    private static final Map<String, PlateTextures> HANDLER_MAP =
            Stream.of(values())
                    .collect(Collectors.toMap(
                            e -> e.getMetalType(),
                            e -> e
                    ));

    public static PlateTextures getByMetalType(String metalType) {
        return HANDLER_MAP.getOrDefault(metalType, DEFAULT);
    }
}
