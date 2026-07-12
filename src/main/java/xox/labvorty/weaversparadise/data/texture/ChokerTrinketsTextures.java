package xox.labvorty.weaversparadise.data.texture;

import net.minecraft.resources.ResourceLocation;

public class ChokerTrinketsTextures {
    public static void register() {
        registerTexture("bell", "default", ResourceLocation.fromNamespaceAndPath("weaversparadise", "textures/clothing/bell.png"), false);
        registerTexture("bell", "minecraft:gold_ingot", ResourceLocation.fromNamespaceAndPath("weaversparadise", "textures/clothing/bell_golden.png"), true);

        registerTexture("cat_ring", "default", ResourceLocation.fromNamespaceAndPath("weaversparadise", "textures/clothing/cat_ring.png"), false);
        registerTexture("cat_ring", "minecraft:gold_ingot", ResourceLocation.fromNamespaceAndPath("weaversparadise", "textures/clothing/cat_ring_golden.png"), true);

        registerTexture("heart", "default", ResourceLocation.fromNamespaceAndPath("weaversparadise", "textures/clothing/heart.png"), false);
        registerTexture("heart", "minecraft:gold_ingot", ResourceLocation.fromNamespaceAndPath("weaversparadise", "textures/clothing/heart_golden.png"), true);

        registerTexture("ring", "default", ResourceLocation.fromNamespaceAndPath("weaversparadise", "textures/clothing/ring.png"), false);
        registerTexture("ring", "minecraft:gold_ingot", ResourceLocation.fromNamespaceAndPath("weaversparadise", "textures/clothing/ring_golden.png"), true);

        registerTexture("plate", "default", ResourceLocation.fromNamespaceAndPath("weaversparadise", "textures/clothing/plate.png"), false);
        registerTexture("plate", "minecraft:gold_ingot", ResourceLocation.fromNamespaceAndPath("weaversparadise", "textures/clothing/plate_golden.png"), true);
    }

    private static void registerTexture(String clothing_type, String type, ResourceLocation texture, boolean render) {
        TextureRegistry.registerTexture(
                clothing_type,
                "default",
                type,
                texture,
                texture,
                render
        );
    }
}
