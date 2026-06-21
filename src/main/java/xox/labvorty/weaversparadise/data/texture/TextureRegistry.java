package xox.labvorty.weaversparadise.data.texture;

import net.minecraft.resources.ResourceLocation;

import java.util.HashMap;
import java.util.Map;

public class TextureRegistry {
    private static final Map<String, ItemTexture> textures = new HashMap<>();

    public static void registerTexture(
            String clothing_type,
            String stencil,
            String material,
            ResourceLocation textureOne,
            ResourceLocation textureTwo,
            boolean renderType
    ) {
        textures.put(
                clothing_type + ":" + stencil + ":" + material,
                new ItemTexture(
                        clothing_type,
                        stencil,
                        material,
                        textureOne,
                        textureTwo,
                        renderType
                )
        );
    }

    public static ItemTexture find(
            String clothing_type,
            String stencil,
            String material
    ) {
        return textures.getOrDefault(clothing_type + ":" + stencil + ":" + material, textures.get(clothing_type + ":" + "default" + ":" + material));
    }
}
