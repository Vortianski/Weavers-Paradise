package xox.labvorty.weaversparadise.data.texture.deprecated;

import net.minecraft.resources.ResourceLocation;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum RingTextures {
    DEFAULT(
            "default",
            new ResourceLocation(
                    "weaversparadise",
                    "textures/clothing/ring.png"
            ),
            false
    ),
    GOLDEN(
            "minecraft:gold_ingot",
            new ResourceLocation(
                    "weaversparadise",
                    "textures/clothing/ring_golden.png"
            ),
            true
    );

    private String metalType;
    private ResourceLocation texture;
    private boolean freezeColor;

    RingTextures(
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

    private static final Map<String, RingTextures> HANDLER_MAP =
            Stream.of(values())
                    .collect(Collectors.toMap(
                            e -> e.getMetalType(),
                            e -> e
                    ));

    public static RingTextures getByMetalType(String metalType) {
        return HANDLER_MAP.getOrDefault(metalType, DEFAULT);
    }
}
