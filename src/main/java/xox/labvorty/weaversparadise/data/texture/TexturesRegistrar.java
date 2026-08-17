package xox.labvorty.weaversparadise.data.texture;

import net.minecraft.resources.ResourceLocation;

public class TexturesRegistrar {
    private static final String DEFAULT = "default";

    public static void registerTexture(Texture texture) {
        String path = (texture.stencil.equals(DEFAULT) ? texture.default_path : texture.variant_path);
        TextureRegistry.registerTexture(
                texture.clothing_type,
                texture.stencil,
                texture.material,
                ResourceLocation.fromNamespaceAndPath(
                        texture.mod_id,
                        path + texture.texture_one
                ),
                ResourceLocation.fromNamespaceAndPath(
                        texture.mod_id,
                        path + texture.texture_two
                ),
                texture.doubleType
        );
    }

    public record Texture(
            String mod_id,
            String clothing_type,
            String material,
            String stencil,
            String default_path,
            String variant_path,
            String texture_one,
            String texture_two,
            boolean doubleType
    ) {}
}
