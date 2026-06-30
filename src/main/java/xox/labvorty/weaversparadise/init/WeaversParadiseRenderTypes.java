package xox.labvorty.weaversparadise.init;

import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.Util;
import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@OnlyIn(Dist.CLIENT)
public class WeaversParadiseRenderTypes extends RenderStateShard {
    public WeaversParadiseRenderTypes(String pName, Runnable pSetupState, Runnable pClearState) {
        super(pName, pSetupState, pClearState);
    }

    public static final Function<List<ResourceLocation>, RenderType> END_ENTITY = Util.memoize(
            data -> {
                ResourceLocation texture1 = data.get(0);
                ResourceLocation texture2 = data.get(1);
                ResourceLocation texture3 = data.get(2);

                return createEndEntity(texture1, texture2, texture3);
            }
    );

    private static RenderType createEndEntity(ResourceLocation texture1, ResourceLocation texture2, ResourceLocation texture3) {
        RenderType.CompositeState compositeState = RenderType.CompositeState.builder()
                .setShaderState(new RenderStateShard.ShaderStateShard(() -> WeaversParadiseCustomShaders.END_ENTITY))
                .setTextureState(RenderStateShard.MultiTextureStateShard.builder()
                        .add(texture1, false, false)
                        .add(texture2, false, false)
                        .add(texture3, false, false)
                        .add(texture2, false, false)
                        .build()
                )
                .setWriteMaskState(RenderStateShard.COLOR_WRITE)
                .setTransparencyState(TRANSLUCENT_TRANSPARENCY)
                .setCullState(NO_CULL)
                .setOverlayState(OVERLAY)
                .setLayeringState(VIEW_OFFSET_Z_LAYERING)
                .setDepthTestState(LEQUAL_DEPTH_TEST)
                .createCompositeState(true);

        return RenderType.create(
                "end_entity",
                DefaultVertexFormat.NEW_ENTITY,
                VertexFormat.Mode.QUADS,
                1536,
                false,
                false,
                compositeState
        );
    }

    public static RenderType getEndEntity(ResourceLocation texture1, ResourceLocation texture2, ResourceLocation texture3) {
        List<ResourceLocation> data = new ArrayList<>();
        data.add(texture1);
        data.add(texture2);
        data.add(texture3);

        return END_ENTITY.apply(data);
    }

    public static final Function<ResourceLocation, RenderType> POLYCHROMATIC = Util.memoize(
            WeaversParadiseRenderTypes::createPolychromatic
    );

    private static RenderType createPolychromatic(ResourceLocation resourceLocation) {
        RenderType.CompositeState compositeState = RenderType.CompositeState.builder()
                .setShaderState(new RenderStateShard.ShaderStateShard(() -> WeaversParadiseCustomShaders.POLYCHROMATIC))
                .setTextureState(new RenderStateShard.TextureStateShard(resourceLocation, false, false))
                .setTransparencyState(TRANSLUCENT_TRANSPARENCY)
                .setCullState(NO_CULL)
                .setLightmapState(LIGHTMAP)
                .setOverlayState(OVERLAY)
                .createCompositeState(true);

        return RenderType.create(
                "polychromatic",
                DefaultVertexFormat.NEW_ENTITY,
                VertexFormat.Mode.QUADS,
                1536,
                true,
                true,
                compositeState
        );
    }

    public static RenderType getPolychromatic(ResourceLocation resourceLocation) {
        return POLYCHROMATIC.apply(resourceLocation);
    }

    public static final Function<List<ResourceLocation>, RenderType> ENTITY_TRANSLUCENT_MASK = Util.memoize(
            data -> {
                ResourceLocation resourceLocation = data.get(0);
                ResourceLocation maskLocation = data.get(1);

                return createEntityTranslucentMask(resourceLocation, maskLocation);
            }
    );

    private static RenderType createEntityTranslucentMask(ResourceLocation resourceLocation, ResourceLocation maskLocation) {
        RenderType.CompositeState compositeState = RenderType.CompositeState.builder()
                .setShaderState(new RenderStateShard.ShaderStateShard(() -> WeaversParadiseCustomShaders.ENTITY_TRANSLUCENT_MASK))
                .setTextureState(
                        RenderStateShard.MultiTextureStateShard.builder()
                                .add(resourceLocation, false, false)
                                .add(ResourceLocation.fromNamespaceAndPath("minecraft", "textures/block/debug.png"), false, false)
                                .add(ResourceLocation.fromNamespaceAndPath("minecraft", "textures/block/debug.png"), false, false)
                                .add(maskLocation, false, false)
                                .build()
                )
                .setTransparencyState(TRANSLUCENT_TRANSPARENCY)
                .setCullState(NO_CULL)
                .setLightmapState(LIGHTMAP)
                .setOverlayState(OVERLAY)
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

    public static RenderType getEntityTranslucentMask(ResourceLocation resourceLocation, ResourceLocation maskLocation) {
        List<ResourceLocation> data = new ArrayList<>();

        data.add(resourceLocation);
        data.add(maskLocation);

        return ENTITY_TRANSLUCENT_MASK.apply(data);
    }

    public static final Function<ResourceLocation, RenderType> ENTITY_STATIC = Util.memoize(
            WeaversParadiseRenderTypes::createEntityStatic
    );

    private static RenderType createEntityStatic(ResourceLocation resourceLocation) {
        RenderType.CompositeState compositeState = RenderType.CompositeState.builder()
                .setShaderState(new RenderStateShard.ShaderStateShard(() -> WeaversParadiseCustomShaders.ENTITY_STATIC))
                .setTextureState(
                        new RenderStateShard.TextureStateShard(
                                resourceLocation,
                                false,
                                false
                        )
                )
                .setTransparencyState(TRANSLUCENT_TRANSPARENCY)
                .setCullState(NO_CULL)
                .setLightmapState(LIGHTMAP)
                .setOverlayState(OVERLAY)
                .createCompositeState(true);

        return RenderType.create(
                "entity_static",
                DefaultVertexFormat.NEW_ENTITY,
                VertexFormat.Mode.QUADS,
                1536,
                true,
                true,
                compositeState
        );
    }

    public static RenderType getEntityStatic(ResourceLocation resourceLocation) {
        return ENTITY_STATIC.apply(resourceLocation);
    }

    public static final Function<ResourceLocation, RenderType> ENTITY_CRYSTAL = Util.memoize(
            WeaversParadiseRenderTypes::createCrystal
    );

    private static RenderType createCrystal(ResourceLocation resourceLocation) {
        RenderType.CompositeState compositeState = RenderType.CompositeState.builder()
                .setShaderState(new RenderStateShard.ShaderStateShard(() -> WeaversParadiseCustomShaders.ENTITY_CRYSTAL))
                .setTextureState(new RenderStateShard.TextureStateShard(resourceLocation, false, false))
                .setTransparencyState(TRANSLUCENT_TRANSPARENCY)
                .setCullState(NO_CULL)
                .setLightmapState(LIGHTMAP)
                .setOverlayState(OVERLAY)
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

    public static RenderType getCrystal(ResourceLocation resourceLocation) {
        return ENTITY_CRYSTAL.apply(resourceLocation);
    }

    public static final Function<ResourceLocation, RenderType> NEGATIVE = Util.memoize(
            WeaversParadiseRenderTypes::createNegative
    );

    private static RenderType createNegative(ResourceLocation resourceLocation) {
        RenderType.CompositeState compositeState = RenderType.CompositeState.builder()
                .setShaderState(new RenderStateShard.ShaderStateShard(() -> WeaversParadiseCustomShaders.NEGATIVE))
                .setTextureState(new RenderStateShard.TextureStateShard(resourceLocation, false, false))
                .setTransparencyState(TRANSLUCENT_TRANSPARENCY)
                .setCullState(NO_CULL)
                .setLightmapState(LIGHTMAP)
                .setOverlayState(OVERLAY)
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

    public static RenderType getNegative(ResourceLocation resourceLocation) {
        return NEGATIVE.apply(resourceLocation);
    }

    public static final Function<ResourceLocation, RenderType> TRUE_NEGATIVE = Util.memoize(
            WeaversParadiseRenderTypes::createTrueNegative
    );

    private static RenderType createTrueNegative(ResourceLocation resourceLocation) {
        RenderType.CompositeState compositeState = RenderType.CompositeState.builder()
                .setShaderState(new RenderStateShard.ShaderStateShard(() -> WeaversParadiseCustomShaders.TRUE_NEGATIVE))
                .setTextureState(new RenderStateShard.TextureStateShard(resourceLocation, false, false))
                .setTransparencyState(TRANSLUCENT_TRANSPARENCY)
                .setCullState(NO_CULL)
                .setLightmapState(LIGHTMAP)
                .setOverlayState(OVERLAY)
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

    public static RenderType getTrueNegative(ResourceLocation resourceLocation) {
        return TRUE_NEGATIVE.apply(resourceLocation);
    }

    public static final Function<ResourceLocation, RenderType> NEBULA = Util.memoize(
            WeaversParadiseRenderTypes::createNebula
    );

    private static RenderType createNebula(ResourceLocation resourceLocation) {
        RenderType.CompositeState compositeState = RenderType.CompositeState.builder()
                .setShaderState(new RenderStateShard.ShaderStateShard(() -> WeaversParadiseCustomShaders.NEBULA))
                .setTextureState(new RenderStateShard.TextureStateShard(resourceLocation, false, false))
                .setTransparencyState(TRANSLUCENT_TRANSPARENCY)
                .setCullState(NO_CULL)
                .setLightmapState(LIGHTMAP)
                .setOverlayState(OVERLAY)
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

    public static RenderType getNebula(ResourceLocation resourceLocation) {
        return NEBULA.apply(resourceLocation);
    }

    public static final Function<List<ResourceLocation>, RenderType> ENTITY_STARFALL = Util.memoize(
            data -> {
                ResourceLocation textureOne = data.get(0);
                ResourceLocation textureTwo = data.get(1);

                return createEntityStarfall(textureOne, textureTwo);
            }
    );

    private static RenderType createEntityStarfall(ResourceLocation textureOne, ResourceLocation textureTwo) {
        RenderType.CompositeState compositeState = RenderType.CompositeState.builder()
                .setShaderState(new RenderStateShard.ShaderStateShard(() -> WeaversParadiseCustomShaders.ENTITY_STARFALL))
                .setTextureState(
                        RenderStateShard.MultiTextureStateShard.builder()
                                .add(textureOne,false,false)
                                .add(ResourceLocation.fromNamespaceAndPath("minecraft", "textures/block/debug.png"), false, false)
                                .add(ResourceLocation.fromNamespaceAndPath("minecraft", "textures/block/debug.png"), false, false)
                                .add(textureTwo,false,false)
                                .build()
                )
                .setTransparencyState(TRANSLUCENT_TRANSPARENCY)
                .setCullState(NO_CULL)
                .setLightmapState(LIGHTMAP)
                .setOverlayState(OVERLAY)
                .createCompositeState(true);

        return RenderType.create(
                "entity_starfall",
                DefaultVertexFormat.NEW_ENTITY,
                VertexFormat.Mode.QUADS,
                1536,
                true,
                true,
                compositeState
        );
    }

    public static RenderType getEntityStarfall(ResourceLocation textureOne, ResourceLocation textureTwo) {
        List<ResourceLocation> data = new ArrayList<>();

        data.add(textureOne);
        data.add(textureTwo);

        return ENTITY_STARFALL.apply(data);
    }
}
