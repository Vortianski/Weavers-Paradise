package xox.labvorty.weaversparadise.renderers;

import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.client.renderer.blockentity.TheEndPortalRenderer;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.api.distmarker.Dist;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.client.renderer.RenderStateShard;
import java.util.function.Function;
import net.minecraft.Util;
import net.neoforged.neoforge.client.event.ScreenEvent;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;
import java.util.ArrayList;

@OnlyIn(Dist.CLIENT)
public class WeaversParadiseRenderTypes {
    public static final RenderType GLINTY_INSTANCE = RenderType.create(
            "glinty",
            DefaultVertexFormat.POSITION_TEX,
            VertexFormat.Mode.QUADS,
            1536,
            RenderType.CompositeState.builder()
                    .setShaderState(RenderStateShard.RENDERTYPE_ARMOR_ENTITY_GLINT_SHADER)
                    .setTextureState(new RenderStateShard.TextureStateShard(
                            ResourceLocation.fromNamespaceAndPath(
                                    "weaversparadise",
                                    "textures/shaders/glint_item_white.png"),
                            true,
                            false)
                    )
                    .setWriteMaskState(RenderStateShard.COLOR_WRITE)
                    .setCullState(RenderStateShard.NO_CULL)
                    .setDepthTestState(RenderStateShard.EQUAL_DEPTH_TEST)
                    .setTransparencyState(RenderStateShard.GLINT_TRANSPARENCY)
                    .setTexturingState(RenderStateShard.ENTITY_GLINT_TEXTURING)
                    .setLayeringState(RenderStateShard.VIEW_OFFSET_Z_LAYERING)
                    .createCompositeState(false));

    public static final Function<List<ResourceLocation>, RenderType> VOID_ARMOR = Util.memoize(
            data -> {
                ResourceLocation texture1 = data.get(0);
                ResourceLocation texture2 = data.get(1);
                ResourceLocation texture3 = data.get(2);
                return createVoidArmor("voidarmor", texture1, texture2, texture3, false);
            }
    );

    public static RenderType createVoidArmor(String name, ResourceLocation texture1, ResourceLocation texture2, ResourceLocation texture3, boolean logic) {
        RenderType.CompositeState rendertype$compositestate = RenderType.CompositeState.builder()
                .setShaderState(new RenderStateShard.ShaderStateShard(() -> WeaversParadiseCustomShaders.VOID_ARMOR_SHADER))
                .setTextureState(RenderStateShard.MultiTextureStateShard.builder()
                        .add(texture1, false, false)
                        .add(texture2, false, false)
                        .add(texture3, false, false)
                        .add(texture2, false, false)
                        .build()
                )
                .setWriteMaskState(RenderStateShard.COLOR_WRITE)
                .setTransparencyState(RenderStateShard.TRANSLUCENT_TRANSPARENCY)
                .setCullState(RenderStateShard.NO_CULL)
                .setOverlayState(RenderStateShard.OVERLAY)
                .setLayeringState(RenderStateShard.VIEW_OFFSET_Z_LAYERING)
                .setDepthTestState(logic ? RenderStateShard.EQUAL_DEPTH_TEST : RenderStateShard.LEQUAL_DEPTH_TEST)
                .createCompositeState(true);


        return RenderType.create(name, DefaultVertexFormat.NEW_ENTITY, VertexFormat.Mode.QUADS, 1536, false, false, rendertype$compositestate);
    }

    public static RenderType getVoidArmor(ResourceLocation texture1, ResourceLocation texture2, ResourceLocation texture3) {
        List<ResourceLocation> data = new ArrayList();
        data.add(texture1);
        data.add(texture2);
        data.add(texture3);

        return VOID_ARMOR.apply(data);
    }

    public static RenderType getGlintyInstance() {
        return GLINTY_INSTANCE;
    }

    public static final Function<List<ResourceLocation>, RenderType> POLYCHROMATIC = Util.memoize(
            data -> {
                ResourceLocation texture1 = data.get(0);
                return createPolychromatic("polychromatic", texture1);
            }
    );

    public static RenderType createPolychromatic(String name, ResourceLocation texture1) {
        RenderType.CompositeState rendertype$compositestate = RenderType.CompositeState.builder()
                .setShaderState(new RenderStateShard.ShaderStateShard(() -> WeaversParadiseCustomShaders.POLYCHROMATIC))
                .setTextureState(
                        new RenderStateShard.TextureStateShard(
                                texture1, false, false
                        )
                )
                .setTransparencyState(RenderStateShard.TRANSLUCENT_TRANSPARENCY)
                .setCullState(RenderStateShard.NO_CULL)
                .setLightmapState(RenderStateShard.LIGHTMAP)
                .setOverlayState(RenderStateShard.OVERLAY)
                .createCompositeState(true);


        return RenderType.create(name, DefaultVertexFormat.NEW_ENTITY, VertexFormat.Mode.QUADS, 1536, true, true, rendertype$compositestate);
    }

    public static RenderType getPolychromatic(ResourceLocation texture1) {
        List<ResourceLocation> data = new ArrayList();
        data.add(texture1);

        return POLYCHROMATIC.apply(data);
    }

    public static final Function<List<ResourceLocation>, RenderType> ENTITY_TRANSLUCENT_MASK = Util.memoize(
            data -> {
                ResourceLocation resourceLocation = data.get(0);
                ResourceLocation maskLocation = data.get(1);

                return createEntityTranslucentMask("entity_translucent_mask", resourceLocation, maskLocation);
            }
    );

    public static RenderType createEntityTranslucentMask(String name, ResourceLocation resourceLocation, ResourceLocation maskLocation) {
        RenderType.CompositeState rendertype$compositestate = RenderType.CompositeState.builder()
                .setShaderState(new RenderStateShard.ShaderStateShard(() -> WeaversParadiseCustomShaders.ENTITY_TRANSLUCENT_MASK))
                .setTextureState(
                        RenderStateShard.MultiTextureStateShard.builder()
                                .add(
                                        resourceLocation,
                                        false,
                                        false
                                )
                                .add(
                                        ResourceLocation.fromNamespaceAndPath("minecraft", "textures/block/debug.png"),
                                        false,
                                        false
                                )
                                .add(
                                        ResourceLocation.fromNamespaceAndPath("minecraft", "textures/block/debug.png"),
                                        false,
                                        false
                                )
                                .add(
                                        maskLocation,
                                        false,
                                        false
                                )
                                .build()
                )
                .setTransparencyState(RenderStateShard.TRANSLUCENT_TRANSPARENCY)
                .setCullState(RenderStateShard.NO_CULL)
                .setLightmapState(RenderStateShard.LIGHTMAP)
                .setOverlayState(RenderStateShard.OVERLAY)
                .createCompositeState(true);

        return RenderType.create(name, DefaultVertexFormat.NEW_ENTITY, VertexFormat.Mode.QUADS, 1536, true, true, rendertype$compositestate);
    }

    public static RenderType getEntityTranslucentMask(ResourceLocation resourceLocation, ResourceLocation maskLocation) {
        List<ResourceLocation> data = new ArrayList<>();
        data.add(resourceLocation);
        data.add(maskLocation);

        return ENTITY_TRANSLUCENT_MASK.apply(data);
    }

    public static final Function<List<ResourceLocation>, RenderType> ENTITY_STATIC = Util.memoize(
            data -> {
                ResourceLocation resourceLocation = data.get(0);

                return createEntityStatic("entity_static", resourceLocation);
            }
    );

    public static RenderType createEntityStatic(String name, ResourceLocation resourceLocation) {
        RenderType.CompositeState rendertype$compositestate = RenderType.CompositeState.builder()
                .setShaderState(new RenderStateShard.ShaderStateShard(() -> WeaversParadiseCustomShaders.ENTITY_STATIC))
                .setTextureState(
                        new RenderStateShard.TextureStateShard(
                                resourceLocation,
                                false,
                                false
                        )
                )
                .setTransparencyState(RenderStateShard.TRANSLUCENT_TRANSPARENCY)
                .setCullState(RenderStateShard.NO_CULL)
                .setLightmapState(RenderStateShard.LIGHTMAP)
                .setOverlayState(RenderStateShard.OVERLAY)
                .createCompositeState(true);

        return RenderType.create(name, DefaultVertexFormat.NEW_ENTITY, VertexFormat.Mode.QUADS, 1536, true, true, rendertype$compositestate);
    }

    public static RenderType getEntityStatic(ResourceLocation resourceLocation) {
        List<ResourceLocation> data = new ArrayList<>();
        data.add(resourceLocation);

        return ENTITY_STATIC.apply(data);
    }

    public static final Function<List<ResourceLocation>, RenderType> ENTITY_CRYSTAL = Util.memoize(
            data -> {
                ResourceLocation texture1 = data.get(0);
                return createCrystal("entity_crystal", texture1);
            }
    );

    public static RenderType createCrystal(String name, ResourceLocation texture1) {
        RenderType.CompositeState rendertype$compositestate = RenderType.CompositeState.builder()
                .setShaderState(new RenderStateShard.ShaderStateShard(() -> WeaversParadiseCustomShaders.ENTITY_CRYSTAL))
                .setTextureState(
                        new RenderStateShard.TextureStateShard(
                                texture1, false, false
                        )
                )
                .setTransparencyState(RenderStateShard.TRANSLUCENT_TRANSPARENCY)
                .setCullState(RenderStateShard.NO_CULL)
                .setLightmapState(RenderStateShard.LIGHTMAP)
                .setOverlayState(RenderStateShard.OVERLAY)
                .createCompositeState(true);


        return RenderType.create(name, DefaultVertexFormat.NEW_ENTITY, VertexFormat.Mode.QUADS, 1536, true, true, rendertype$compositestate);
    }

    public static RenderType getCrystal(ResourceLocation texture1) {
        List<ResourceLocation> data = new ArrayList();
        data.add(texture1);

        return ENTITY_CRYSTAL.apply(data);
    }
}
