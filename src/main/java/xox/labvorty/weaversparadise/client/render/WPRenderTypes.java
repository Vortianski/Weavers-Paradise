package xox.labvorty.weaversparadise.client.render;

import com.mojang.blaze3d.shaders.Uniform;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.Util;
import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.resources.ResourceLocation;
import org.joml.Vector3f;
import org.joml.Vector4f;
import oshi.util.tuples.Pair;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class WPRenderTypes {
    static ResourceLocation DEBUG_TEXTURE = ResourceLocation.fromNamespaceAndPath("minecraft", "textures/block/debug.png");

    private static final Function<List<ResourceLocation>, RenderType> ENTITY_END_PORTAL = Util.memoize(
            data -> {
                ResourceLocation textureOne = data.get(0);
                ResourceLocation textureTwo = data.get(1);
                ResourceLocation textureThree = data.get(2);

                return createEntityEndPortal(textureOne, textureTwo, textureThree);
            }
    );
    private static final Function<List<ResourceLocation>, RenderType> ENTITY_TRANSLUCENT_MASK = Util.memoize(
            data -> {
                ResourceLocation textureOne = data.get(0);
                ResourceLocation textureTwo = data.get(1);

                return createEntityTranslucentMask(textureOne, textureTwo);
            }
    );
    private static final Function<ResourceLocation, RenderType> ENTITY_NEGATIVE = Util.memoize(WPRenderTypes::createEntityNegative);
    private static final Function<ResourceLocation, RenderType> ENTITY_TRUE_NEGATIVE = Util.memoize(WPRenderTypes::createEntityTrueNegative);
    private static final Function<ResourceLocation, RenderType> ENTITY_CRYSTAL = Util.memoize(WPRenderTypes::createEntityCrystal);
    private static final Function<ResourceLocation, RenderType> ENTITY_STATIC_NOISE = Util.memoize(WPRenderTypes::createEntityStaticNoise);
    private static final Function<ResourceLocation, RenderType> ENTITY_POLYCHROMATIC = Util.memoize(WPRenderTypes::createEntityPolychromatic);
    private static final Function<ResourceLocation, RenderType> ENTITY_POLYCHROMATIC_CULL = Util.memoize(WPRenderTypes::createEntityPolychromaticCull);
    private static final Function<ResourceLocation, RenderType> ENTITY_NEBULA = Util.memoize(WPRenderTypes::createEntityNebula);
    private static final Function<ResourceLocation, RenderType> ENTITY_TRANSLUCENT_EMISSIVE_CULL = Util.memoize(WPRenderTypes::createEntityTranslucentEmissiveCull);
    private static final Function<ResourceLocation, RenderType> ENTITY_CHROMATIC_ABERRATION = Util.memoize(WPRenderTypes::createEntityChromaticAberration);
    private static final Function<ParallaxRenderOptions, RenderType> ENTITY_PARALLAX = Util.memoize(WPRenderTypes::createEntityParallax);
    private static final Map<Vector3f, RenderType> ENTITY_COLORED_GLINT = new HashMap<>();
    private static final Function<ResourceLocation, RenderType> ENTITY_SPIRAL = Util.memoize(WPRenderTypes::createEntitySpiral);
    private static final Function<ResourceLocation, RenderType> TEXT_NO_CULL = Util.memoize(WPRenderTypes::createTextNoCull);

    private static RenderType createEntityEndPortal(ResourceLocation textureOne, ResourceLocation textureTwo, ResourceLocation textureThree) {
        RenderType.CompositeState compositeState = RenderType.CompositeState.builder()
                .setShaderState(new RenderStateShard.ShaderStateShard(() -> WPShaders.ENTITY_END_PORTAL))
                .setTextureState(RenderStateShard.MultiTextureStateShard.builder()
                        .add(textureOne, false, false)
                        .add(textureTwo, false, false)
                        .add(textureThree, false, false)
                        .add(textureTwo, false, false)
                        .build()
                )
                .setWriteMaskState(RenderStateShard.COLOR_WRITE)
                .setTransparencyState(RenderStateShard.TRANSLUCENT_TRANSPARENCY)
                .setCullState(RenderStateShard.NO_CULL)
                .setOverlayState(RenderStateShard.OVERLAY)
                .setLayeringState(RenderStateShard.VIEW_OFFSET_Z_LAYERING)
                .setDepthTestState(RenderStateShard.LEQUAL_DEPTH_TEST)
                .createCompositeState(true);

        return RenderType.create(
                "entity_end_portal",
                DefaultVertexFormat.NEW_ENTITY,
                VertexFormat.Mode.QUADS,
                1536,
                false,
                false,
                compositeState
        );
    }

    private static RenderType createEntityTranslucentMask(ResourceLocation textureOne, ResourceLocation textureTwo) {
        RenderType.CompositeState compositeState = RenderType.CompositeState.builder()
                .setShaderState(new RenderStateShard.ShaderStateShard(() -> WPShaders.ENTITY_TRANSLUCENT_MASK))
                .setTextureState(
                        RenderStateShard.MultiTextureStateShard.builder()
                                .add(textureOne, false, false)
                                .add(DEBUG_TEXTURE, false, false)
                                .add(DEBUG_TEXTURE, false, false)
                                .add(textureTwo, false, false)
                                .build()
                )
                .setTransparencyState(RenderStateShard.TRANSLUCENT_TRANSPARENCY)
                .setCullState(RenderStateShard.NO_CULL)
                .setLightmapState(RenderStateShard.LIGHTMAP)
                .setOverlayState(RenderStateShard.OVERLAY)
                .createCompositeState(true);

        return RenderType.create(
                "entity_translucent_mask",
                DefaultVertexFormat.NEW_ENTITY,
                VertexFormat.Mode.QUADS,
                1536,
                true,
                true,
                compositeState
        );
    }

    private static RenderType createEntityNegative(ResourceLocation resourceLocation) {
        RenderType.CompositeState compositeState = RenderType.CompositeState.builder()
                .setShaderState(new RenderStateShard.ShaderStateShard(() -> WPShaders.ENTITY_NEGATIVE))
                .setTextureState(new RenderStateShard.TextureStateShard(resourceLocation, false, false))
                .setTransparencyState(RenderStateShard.TRANSLUCENT_TRANSPARENCY)
                .setCullState(RenderStateShard.NO_CULL)
                .setLightmapState(RenderStateShard.LIGHTMAP)
                .setOverlayState(RenderStateShard.OVERLAY)
                .createCompositeState(true);

        return RenderType.create(
                "entity_negative",
                DefaultVertexFormat.NEW_ENTITY,
                VertexFormat.Mode.QUADS,
                1536,
                true,
                true,
                compositeState
        );
    }

    private static RenderType createEntityTrueNegative(ResourceLocation resourceLocation) {
        RenderType.CompositeState compositeState = RenderType.CompositeState.builder()
                .setShaderState(new RenderStateShard.ShaderStateShard(() -> WPShaders.ENTITY_TRUE_NEGATIVE))
                .setTextureState(new RenderStateShard.TextureStateShard(resourceLocation, false, false))
                .setTransparencyState(RenderStateShard.TRANSLUCENT_TRANSPARENCY)
                .setCullState(RenderStateShard.NO_CULL)
                .setLightmapState(RenderStateShard.LIGHTMAP)
                .setOverlayState(RenderStateShard.OVERLAY)
                .createCompositeState(true);

        return RenderType.create(
                "entity_true_negative",
                DefaultVertexFormat.NEW_ENTITY,
                VertexFormat.Mode.QUADS,
                1536,
                true,
                true,
                compositeState
        );
    }

    private static RenderType createEntityCrystal(ResourceLocation resourceLocation) {
        RenderType.CompositeState compositeState = RenderType.CompositeState.builder()
                .setShaderState(new RenderStateShard.ShaderStateShard(() -> WPShaders.ENTITY_CRYSTAL))
                .setTextureState(new RenderStateShard.TextureStateShard(resourceLocation, false, false))
                .setTransparencyState(RenderStateShard.TRANSLUCENT_TRANSPARENCY)
                .setCullState(RenderStateShard.NO_CULL)
                .setLightmapState(RenderStateShard.LIGHTMAP)
                .setOverlayState(RenderStateShard.OVERLAY)
                .createCompositeState(true);


        return RenderType.create(
                "entity_crystal",
                DefaultVertexFormat.NEW_ENTITY,
                VertexFormat.Mode.QUADS,
                1536,
                true,
                true,
                compositeState
        );
    }

    private static RenderType createEntityStaticNoise(ResourceLocation resourceLocation) {
        RenderType.CompositeState compositeState = RenderType.CompositeState.builder()
                .setShaderState(new RenderStateShard.ShaderStateShard(() -> WPShaders.ENTITY_STATIC_NOISE))
                .setTextureState(new RenderStateShard.TextureStateShard(resourceLocation, false, false))
                .setTransparencyState(RenderStateShard.TRANSLUCENT_TRANSPARENCY)
                .setCullState(RenderStateShard.NO_CULL)
                .setLightmapState(RenderStateShard.LIGHTMAP)
                .setOverlayState(RenderStateShard.OVERLAY)
                .createCompositeState(true);

        return RenderType.create(
                "entity_static_noise",
                DefaultVertexFormat.NEW_ENTITY,
                VertexFormat.Mode.QUADS,
                1536,
                true,
                true,
                compositeState
        );
    }

    private static RenderType createEntityPolychromatic(ResourceLocation resourceLocation) {
        RenderType.CompositeState compositeState = RenderType.CompositeState.builder()
                .setShaderState(new RenderStateShard.ShaderStateShard(() -> WPShaders.ENTITY_POLYCHROMATIC))
                .setTextureState(new RenderStateShard.TextureStateShard(resourceLocation, false, false))
                .setTransparencyState(RenderStateShard.TRANSLUCENT_TRANSPARENCY)
                .setCullState(RenderStateShard.NO_CULL)
                .setLightmapState(RenderStateShard.LIGHTMAP)
                .setOverlayState(RenderStateShard.OVERLAY)
                .createCompositeState(true);


        return RenderType.create(
                "entity_polychromatic",
                DefaultVertexFormat.NEW_ENTITY,
                VertexFormat.Mode.QUADS,
                1536,
                true,
                true,
                compositeState
        );
    }

    private static RenderType createEntityPolychromaticCull(ResourceLocation resourceLocation) {
        RenderType.CompositeState compositeState = RenderType.CompositeState.builder()
                .setShaderState(new RenderStateShard.ShaderStateShard(() -> WPShaders.ENTITY_POLYCHROMATIC))
                .setTextureState(new RenderStateShard.TextureStateShard(resourceLocation, false, false))
                .setTransparencyState(RenderStateShard.TRANSLUCENT_TRANSPARENCY)
                .setCullState(RenderStateShard.CULL)
                .setLightmapState(RenderStateShard.LIGHTMAP)
                .setOverlayState(RenderStateShard.OVERLAY)
                .createCompositeState(true);


        return RenderType.create(
                "entity_polychromatic_cull",
                DefaultVertexFormat.NEW_ENTITY,
                VertexFormat.Mode.QUADS,
                1536,
                true,
                true,
                compositeState
        );
    }

    private static RenderType createEntityNebula(ResourceLocation resourceLocation) {
        RenderType.CompositeState compositeState = RenderType.CompositeState.builder()
                .setShaderState(new RenderStateShard.ShaderStateShard(() -> WPShaders.ENTITY_NEBULA))
                .setTextureState(new RenderStateShard.TextureStateShard(resourceLocation, false, false))
                .setTransparencyState(RenderStateShard.TRANSLUCENT_TRANSPARENCY)
                .setCullState(RenderStateShard.NO_CULL)
                .setLightmapState(RenderStateShard.LIGHTMAP)
                .setOverlayState(RenderStateShard.OVERLAY)
                .createCompositeState(true);


        return RenderType.create(
                "entity_nebula",
                DefaultVertexFormat.NEW_ENTITY,
                VertexFormat.Mode.QUADS,
                1536,
                true,
                true,
                compositeState
        );
    }

    private static RenderType createEntityTranslucentEmissiveCull(ResourceLocation resourceLocation) {
        RenderType.CompositeState compositeState = RenderType.CompositeState.builder()
                .setShaderState(RenderStateShard.RENDERTYPE_ENTITY_TRANSLUCENT_EMISSIVE_SHADER)
                .setTextureState(new RenderStateShard.TextureStateShard(resourceLocation, false, false))
                .setTransparencyState(RenderStateShard.TRANSLUCENT_TRANSPARENCY)
                .setCullState(RenderStateShard.CULL)
                .setOverlayState(RenderStateShard.OVERLAY)
                .setWriteMaskState(RenderStateShard.COLOR_DEPTH_WRITE)
                .createCompositeState(false);

        return RenderType.create(
                "entity_translucent_emissive_cull",
                DefaultVertexFormat.NEW_ENTITY,
                VertexFormat.Mode.QUADS,
                1536,
                true,
                true,
                compositeState
        );
    }

    private static RenderType createEntityChromaticAberration(ResourceLocation resourceLocation) {
        RenderType.CompositeState compositeState = RenderType.CompositeState.builder()
                .setShaderState(new RenderStateShard.ShaderStateShard(() -> WPShaders.ENTITY_CHROMATIC_ABERRATION))
                .setTextureState(new RenderStateShard.TextureStateShard(resourceLocation, false, false))
                .setTransparencyState(RenderStateShard.TRANSLUCENT_TRANSPARENCY)
                .setCullState(RenderStateShard.NO_CULL)
                .setLightmapState(RenderStateShard.LIGHTMAP)
                .setOverlayState(RenderStateShard.OVERLAY)
                .createCompositeState(true);

        return RenderType.create(
                "entity_chromatic_aberration",
                DefaultVertexFormat.NEW_ENTITY,
                VertexFormat.Mode.QUADS,
                1536,
                true,
                true,
                compositeState
        );
    }

    private static RenderType createEntityParallax(ParallaxRenderOptions parallaxRenderOptions) {
        RenderType.CompositeState compositeState = RenderType.CompositeState.builder()
                .setShaderState(new RenderStateShard.ShaderStateShard(() -> {
                    ShaderInstance shaderInstance = WPShaders.ENTITY_PARALLAX;

                    Uniform speed = shaderInstance.getUniform("ParallaxSpeed");
                    if (speed != null) {
                        speed.set(parallaxRenderOptions.speed().getA(), parallaxRenderOptions.speed().getB());
                    }

                    Uniform rotation = shaderInstance.getUniform("ParallaxRotation");
                    if (rotation != null) {
                        rotation.set(parallaxRenderOptions.rotation());
                    }

                    Uniform rotationSpeed = shaderInstance.getUniform("ParallaxRotationSpeed");
                    if (rotationSpeed != null) {
                        rotationSpeed.set(parallaxRenderOptions.rotationSpeed());
                    }

                    Uniform scale = shaderInstance.getUniform("ParallaxScale");
                    if (scale != null) {
                        scale.set(parallaxRenderOptions.scale());
                    }

                    Uniform color = shaderInstance.getUniform("ParallaxColor");
                    if (color != null) {
                        color.set(parallaxRenderOptions.color.x, parallaxRenderOptions.color.y, parallaxRenderOptions.color.z, parallaxRenderOptions.color.w);
                    }

                    shaderInstance.apply();

                    return shaderInstance;
                }))
                .setTextureState(
                        RenderStateShard.MultiTextureStateShard.builder()
                                .add(parallaxRenderOptions.resourceLocation(),false,false)
                                .add(ResourceLocation.fromNamespaceAndPath("minecraft", "textures/block/debug.png"), false, false)
                                .add(ResourceLocation.fromNamespaceAndPath("minecraft", "textures/block/debug.png"), false, false)
                                .add(parallaxRenderOptions.maskLocation(),false,false)
                                .build()
                )
                .setTransparencyState(RenderStateShard.TRANSLUCENT_TRANSPARENCY)
                .setCullState(RenderStateShard.NO_CULL)
                .setLightmapState(RenderStateShard.LIGHTMAP)
                .setOverlayState(RenderStateShard.OVERLAY)
                .createCompositeState(true);

        return RenderType.create(
                "entity_translucent_parallax",
                DefaultVertexFormat.NEW_ENTITY,
                VertexFormat.Mode.QUADS,
                1536,
                true,
                true,
                compositeState
        );
    }

    private static RenderType createEntityColoredGlint(ColoredGlintOptions coloredGlintOptions) {
        RenderType.CompositeState compositeState = RenderType.CompositeState.builder()
                .setShaderState(new RenderStateShard.ShaderStateShard(() -> {
                    ShaderInstance shaderInstance = WPShaders.ENTITY_COLORED_GLINT;

                    Uniform color = shaderInstance.getUniform("GlintColor");
                    if (color != null) {
                        color.set(coloredGlintOptions.color.x, coloredGlintOptions.color.y, coloredGlintOptions.color.z);
                    }

                    return shaderInstance;
                }))
                .setTextureState(new RenderStateShard.TextureStateShard(ResourceLocation.withDefaultNamespace("textures/misc/enchanted_glint_entity.png"), true, false))
                .setWriteMaskState(RenderStateShard.COLOR_WRITE)
                .setCullState(RenderStateShard.NO_CULL)
                .setDepthTestState(RenderStateShard.EQUAL_DEPTH_TEST)
                .setTransparencyState(RenderStateShard.GLINT_TRANSPARENCY)
                .setOutputState(RenderStateShard.ITEM_ENTITY_TARGET)
                .setTexturingState(RenderStateShard.ENTITY_GLINT_TEXTURING)
                .createCompositeState(false);

        return RenderType.create(
                "entity_colored_glint",
                DefaultVertexFormat.POSITION_TEX,
                VertexFormat.Mode.QUADS,
                1536,
                compositeState
        );
    }

    private static RenderType createEntitySpiral(ResourceLocation resourceLocation) {
        RenderType.CompositeState compositeState = RenderType.CompositeState.builder()
                .setShaderState(new RenderStateShard.ShaderStateShard(() -> WPShaders.ENTITY_SPIRAL))
                .setTextureState(new RenderStateShard.TextureStateShard(resourceLocation, false, false))
                .setTransparencyState(RenderStateShard.TRANSLUCENT_TRANSPARENCY)
                .setCullState(RenderStateShard.NO_CULL)
                .setLightmapState(RenderStateShard.LIGHTMAP)
                .setOverlayState(RenderStateShard.OVERLAY)
                .createCompositeState(true);

        return RenderType.create(
                "entity_spiral",
                DefaultVertexFormat.NEW_ENTITY,
                VertexFormat.Mode.QUADS,
                1536,
                true,
                true,
                compositeState
        );
    }

    public static RenderType getEntityEndPortal(ResourceLocation textureOne, ResourceLocation textureTwo, ResourceLocation textureThree) {
        return wrapThis(ENTITY_END_PORTAL.apply(List.of(textureOne, textureTwo, textureThree)), textureTwo);
    }

    public static RenderType getEntityTranslucentMask(ResourceLocation textureOne, ResourceLocation textureTwo) {
        return wrapThis(ENTITY_TRANSLUCENT_MASK.apply(List.of(textureOne, textureTwo)), textureOne);
    }

    public static RenderType getEntityNegative(ResourceLocation resourceLocation) {
        return wrapThis(ENTITY_NEGATIVE.apply(resourceLocation), resourceLocation);
    }

    public static RenderType getEntityTrueNegative(ResourceLocation resourceLocation) {
        return wrapThis(ENTITY_TRUE_NEGATIVE.apply(resourceLocation), resourceLocation);
    }

    public static RenderType getEntityCrystal(ResourceLocation resourceLocation) {
        return wrapThis(ENTITY_CRYSTAL.apply(resourceLocation), resourceLocation);
    }

    public static RenderType getEntityStaticNoise(ResourceLocation resourceLocation) {
        return wrapThis(ENTITY_STATIC_NOISE.apply(resourceLocation), resourceLocation);
    }

    public static RenderType getEntityPolychromatic(ResourceLocation resourceLocation) {
        return wrapThis(ENTITY_POLYCHROMATIC.apply(resourceLocation), resourceLocation);
    }

    public static RenderType getEntityPolychromaticCull(ResourceLocation resourceLocation) {
        return wrapThis(ENTITY_POLYCHROMATIC_CULL.apply(resourceLocation), resourceLocation);
    }

    public static RenderType getEntityNebula(ResourceLocation resourceLocation) {
        return wrapThis(ENTITY_NEBULA.apply(resourceLocation), resourceLocation);
    }

    public static RenderType getEntityTranslucentEmissiveCull(ResourceLocation resourceLocation) {
        return ENTITY_TRANSLUCENT_EMISSIVE_CULL.apply(resourceLocation);
    }

    public static RenderType getEntityChromaticAberration(ResourceLocation resourceLocation) {
        return wrapThis(ENTITY_CHROMATIC_ABERRATION.apply(resourceLocation), resourceLocation);
    }

    public static RenderType getEntityParallax(ResourceLocation resourceLocation, ResourceLocation resourceLocation0, Pair<Float, Float> speed, float rotation, float rotationSpeed, float scale) {
        return wrapThis(ENTITY_PARALLAX.apply(new ParallaxRenderOptions(resourceLocation, resourceLocation0, speed, rotation, rotationSpeed, scale)), resourceLocation);
    }

    public static RenderType getEntityParallax(ResourceLocation resourceLocation, ResourceLocation resourceLocation0, Pair<Float, Float> speed, float rotation, float rotationSpeed, float scale, Vector4f vector4f) {
        return wrapThis(ENTITY_PARALLAX.apply(new ParallaxRenderOptions(resourceLocation, resourceLocation0, speed, rotation, rotationSpeed, scale, vector4f)), resourceLocation);
    }

    public static RenderType getEntityColoredGlint(Vector3f color) {
        float r = Math.round(color.x * 3f) / 3f;
        float g = Math.round(color.y * 3f) / 3f;
        float b = Math.round(color.z * 3f) / 3f;

        Vector3f closestColor = new Vector3f(r, g, b);

        // В оригинале: IllegalStateException для цветов вне сетки {0, 1/3, 2/3, 1}.
        // Здесь мемоизируем лениво — любой цвет валиден.
        return wrapThis(ENTITY_COLORED_GLINT.computeIfAbsent(closestColor, WPRenderTypes::registerEntityColoredGlint),
                ResourceLocation.withDefaultNamespace("textures/misc/enchanted_glint_entity.png"));
    }

    private static RenderType registerEntityColoredGlint(Vector3f color) {
        RenderType renderType = createEntityColoredGlint(
                new ColoredGlintOptions(color)
        );

        ENTITY_COLORED_GLINT.put(color, renderType);

        return renderType;
    }

    public static RenderType getEntitySpiral(ResourceLocation resourceLocation) {
        return wrapThis(ENTITY_SPIRAL.apply(resourceLocation), resourceLocation);
    }

    public static RenderType wrapThis(RenderType renderType, ResourceLocation resourceLocation) {
        // ПРИМЕЧАНИЕ: без Iris/ChaosLib-компата (упрощение).
        // Рендер-тайпы работают в ванильном конвейере; под шейдерпаками Iris
        // кастомные эффекты могут не применяться.
        return renderType;
    }

    /** Текстовый рендер-тайп без отсечения задних граней. */
    private static RenderType createTextNoCull(ResourceLocation resourceLocation) {
        RenderType.CompositeState compositeState = RenderType.CompositeState.builder()
                .setShaderState(RenderStateShard.RENDERTYPE_TEXT_SHADER)
                .setTextureState(new RenderStateShard.TextureStateShard(resourceLocation, false, false))
                .setTransparencyState(RenderType.TRANSLUCENT_TRANSPARENCY)
                .setLightmapState(RenderType.LIGHTMAP)
                .setCullState(RenderStateShard.NO_CULL)
                .createCompositeState(false);

        return RenderType.create(
                "text_no_cull",
                DefaultVertexFormat.POSITION_COLOR_TEX_LIGHTMAP,
                VertexFormat.Mode.QUADS,
                256,
                false,
                true,
                compositeState
        );
    }

    public static RenderType getTextNoCull(ResourceLocation resourceLocation) {
        return wrapThis(TEXT_NO_CULL.apply(resourceLocation), resourceLocation);
    }

    private record ColoredGlintOptions(
            Vector3f color
    ) {}

    private record ParallaxRenderOptions(ResourceLocation resourceLocation, ResourceLocation maskLocation, Pair<Float, Float> speed, float rotation, float rotationSpeed, float scale, Vector4f color) {
            public ParallaxRenderOptions(
                    ResourceLocation resourceLocation,
                    ResourceLocation maskLocation,
                    Pair<Float, Float> speed,
                    float rotation,
                    float rotationSpeed,
                    float scale
            ) {
                this(resourceLocation, maskLocation, speed, rotation, rotationSpeed, scale, new Vector4f(1, 1, 1, 1));
            }
    }
}
