package xox.labvorty.weaversparadise.init;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.PoseStack;
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
public class WeaversParadiseRenderTypes {
    public static final Function<List<ResourceLocation>, RenderType> END_ENTITY = Util.memoize(
            data -> {
                ResourceLocation texture1 = data.get(0);
                ResourceLocation texture2 = data.get(1);
                ResourceLocation texture3 = data.get(2);
                return createEndEntity("end_entity", texture1, texture2, texture3, false);
            }
    );

    public static RenderType createEndEntity(String name, ResourceLocation texture1, ResourceLocation texture2, ResourceLocation texture3, boolean logic) {
        RenderType.CompositeState rendertype$compositestate = RenderType.CompositeState.builder()
                .setShaderState(new RenderStateShard.ShaderStateShard(() -> WeaversParadiseCustomShaders.END_ENTITY))
                .setTextureState(RenderStateShard.MultiTextureStateShard.builder()
                        .add(texture1, false, false)
                        .add(texture2, false, false)
                        .add(texture3, false, false)
                        .add(texture2, false, false)
                        .build()
                )
                .setWriteMaskState(new RenderStateShard.WriteMaskStateShard(true, false))
                .setTransparencyState(new RenderStateShard.TransparencyStateShard("translucent_transparency", () -> {
                    RenderSystem.enableBlend();
                    RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
                }, () -> {
                    RenderSystem.disableBlend();
                    RenderSystem.defaultBlendFunc();
                }))
                .setCullState(new RenderStateShard.CullStateShard(false))
                .setOverlayState(new RenderStateShard.OverlayStateShard(true))
                .setLayeringState(new RenderStateShard.LayeringStateShard("view_offset_z_layering", () -> {
                    PoseStack posestack = RenderSystem.getModelViewStack();
                    posestack.pushPose();
                    posestack.scale(0.99975586F, 0.99975586F, 0.99975586F);
                    RenderSystem.applyModelViewMatrix();
                }, () -> {
                    PoseStack posestack = RenderSystem.getModelViewStack();
                    posestack.popPose();
                    RenderSystem.applyModelViewMatrix();
                }))
                .setDepthTestState(logic ? new RenderStateShard.DepthTestStateShard("==", 514) : new RenderStateShard.DepthTestStateShard("<=", 515))
                .createCompositeState(true);


        return RenderType.create(name, DefaultVertexFormat.NEW_ENTITY, VertexFormat.Mode.QUADS, 1536, false, false, rendertype$compositestate);
    }

    public static RenderType getEndEntity(ResourceLocation texture1, ResourceLocation texture2, ResourceLocation texture3) {
        List<ResourceLocation> data = new ArrayList();
        data.add(texture1);
        data.add(texture2);
        data.add(texture3);

        return END_ENTITY.apply(data);
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
                .setTransparencyState(new RenderStateShard.TransparencyStateShard("translucent_transparency", () -> {
                    RenderSystem.enableBlend();
                    RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
                }, () -> {
                    RenderSystem.disableBlend();
                    RenderSystem.defaultBlendFunc();
                }))
                .setCullState(new RenderStateShard.CullStateShard(false))
                .setLightmapState(new RenderStateShard.LightmapStateShard(true))
                .setOverlayState(new RenderStateShard.OverlayStateShard(true))
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
                                        new ResourceLocation("minecraft", "textures/block/debug.png"),
                                        false,
                                        false
                                )
                                .add(
                                        new ResourceLocation("minecraft", "textures/block/debug.png"),
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
                .setTransparencyState(new RenderStateShard.TransparencyStateShard("translucent_transparency", () -> {
                    RenderSystem.enableBlend();
                    RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
                }, () -> {
                    RenderSystem.disableBlend();
                    RenderSystem.defaultBlendFunc();
                }))
                .setCullState(new RenderStateShard.CullStateShard(false))
                .setLightmapState(new RenderStateShard.LightmapStateShard(true))
                .setOverlayState(new RenderStateShard.OverlayStateShard(true))
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
                .setTransparencyState(new RenderStateShard.TransparencyStateShard("translucent_transparency", () -> {
                    RenderSystem.enableBlend();
                    RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
                }, () -> {
                    RenderSystem.disableBlend();
                    RenderSystem.defaultBlendFunc();
                }))
                .setCullState(new RenderStateShard.CullStateShard(false))
                .setLightmapState(new RenderStateShard.LightmapStateShard(true))
                .setOverlayState(new RenderStateShard.OverlayStateShard(true))
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
                .setTransparencyState(new RenderStateShard.TransparencyStateShard("translucent_transparency", () -> {
                    RenderSystem.enableBlend();
                    RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
                }, () -> {
                    RenderSystem.disableBlend();
                    RenderSystem.defaultBlendFunc();
                }))
                .setCullState(new RenderStateShard.CullStateShard(false))
                .setLightmapState(new RenderStateShard.LightmapStateShard(true))
                .setOverlayState(new RenderStateShard.OverlayStateShard(true))
                .createCompositeState(true);


        return RenderType.create(name, DefaultVertexFormat.NEW_ENTITY, VertexFormat.Mode.QUADS, 1536, true, true, rendertype$compositestate);
    }

    public static RenderType getCrystal(ResourceLocation texture1) {
        List<ResourceLocation> data = new ArrayList();
        data.add(texture1);

        return ENTITY_CRYSTAL.apply(data);
    }

    public static final Function<List<ResourceLocation>, RenderType> NEGATIVE = Util.memoize(
            data -> {
                ResourceLocation texture1 = data.get(0);
                return createNegative("negative", texture1);
            }
    );

    public static RenderType createNegative(String name, ResourceLocation texture1) {
        RenderType.CompositeState rendertype$compositestate = RenderType.CompositeState.builder()
                .setShaderState(new RenderStateShard.ShaderStateShard(() -> WeaversParadiseCustomShaders.NEGATIVE))
                .setTextureState(
                        new RenderStateShard.TextureStateShard(
                                texture1, false, false
                        )
                )
                .setTransparencyState(new RenderStateShard.TransparencyStateShard("translucent_transparency", () -> {
                    RenderSystem.enableBlend();
                    RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
                }, () -> {
                    RenderSystem.disableBlend();
                    RenderSystem.defaultBlendFunc();
                }))
                .setCullState(new RenderStateShard.CullStateShard(false))
                .setLightmapState(new RenderStateShard.LightmapStateShard(true))
                .setOverlayState(new RenderStateShard.OverlayStateShard(true))
                .createCompositeState(true);


        return RenderType.create(name, DefaultVertexFormat.NEW_ENTITY, VertexFormat.Mode.QUADS, 1536, true, true, rendertype$compositestate);
    }

    public static RenderType getNegative(ResourceLocation texture1) {
        List<ResourceLocation> data = new ArrayList();
        data.add(texture1);

        return NEGATIVE.apply(data);
    }

    public static final Function<List<ResourceLocation>, RenderType> TRUE_NEGATIVE = Util.memoize(
            data -> {
                ResourceLocation texture1 = data.get(0);
                return createTrueNegative("true_negative", texture1);
            }
    );

    public static RenderType createTrueNegative(String name, ResourceLocation texture1) {
        RenderType.CompositeState rendertype$compositestate = RenderType.CompositeState.builder()
                .setShaderState(new RenderStateShard.ShaderStateShard(() -> WeaversParadiseCustomShaders.TRUE_NEGATIVE))
                .setTextureState(
                        new RenderStateShard.TextureStateShard(
                                texture1, false, false
                        )
                )
                .setTransparencyState(new RenderStateShard.TransparencyStateShard("translucent_transparency", () -> {
                    RenderSystem.enableBlend();
                    RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
                }, () -> {
                    RenderSystem.disableBlend();
                    RenderSystem.defaultBlendFunc();
                }))
                .setCullState(new RenderStateShard.CullStateShard(false))
                .setLightmapState(new RenderStateShard.LightmapStateShard(true))
                .setOverlayState(new RenderStateShard.OverlayStateShard(true))
                .createCompositeState(true);


        return RenderType.create(name, DefaultVertexFormat.NEW_ENTITY, VertexFormat.Mode.QUADS, 1536, true, true, rendertype$compositestate);
    }

    public static RenderType getTrueNegative(ResourceLocation texture1) {
        List<ResourceLocation> data = new ArrayList();
        data.add(texture1);

        return TRUE_NEGATIVE.apply(data);
    }

    public static final Function<List<ResourceLocation>, RenderType> NEBULA = Util.memoize(
            data -> {
                ResourceLocation texture1 = data.get(0);
                return createNebula("nebula", texture1);
            }
    );

    public static RenderType createNebula(String name, ResourceLocation texture1) {
        RenderType.CompositeState rendertype$compositestate = RenderType.CompositeState.builder()
                .setShaderState(new RenderStateShard.ShaderStateShard(() -> WeaversParadiseCustomShaders.NEBULA))
                .setTextureState(
                        new RenderStateShard.TextureStateShard(
                                texture1, false, false
                        )
                )
                .setTransparencyState(new RenderStateShard.TransparencyStateShard("translucent_transparency", () -> {
                    RenderSystem.enableBlend();
                    RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
                }, () -> {
                    RenderSystem.disableBlend();
                    RenderSystem.defaultBlendFunc();
                }))
                .setCullState(new RenderStateShard.CullStateShard(false))
                .setLightmapState(new RenderStateShard.LightmapStateShard(true))
                .setOverlayState(new RenderStateShard.OverlayStateShard(true))
                .createCompositeState(true);


        return RenderType.create(name, DefaultVertexFormat.NEW_ENTITY, VertexFormat.Mode.QUADS, 1536, true, true, rendertype$compositestate);
    }

    public static RenderType getNebula(ResourceLocation texture1) {
        List<ResourceLocation> data = new ArrayList();
        data.add(texture1);

        return NEBULA.apply(data);
    }
}
