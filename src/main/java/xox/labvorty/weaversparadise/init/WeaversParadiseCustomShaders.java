package xox.labvorty.weaversparadise.init;

import com.mojang.blaze3d.shaders.Uniform;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterShadersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.io.IOException;

@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class WeaversParadiseCustomShaders {
    public static ShaderInstance END_ENTITY;
    public static ShaderInstance POLYCHROMATIC;
    public static ShaderInstance ENTITY_TRANSLUCENT_MASK;
    public static ShaderInstance ENTITY_STATIC;
    public static ShaderInstance ENTITY_CRYSTAL;
    public static ShaderInstance NEGATIVE;
    public static ShaderInstance TRUE_NEGATIVE;
    public static ShaderInstance NEBULA;

    public static Uniform endPortalTime;
    public static Uniform endPortalLayers;

    public static Uniform staticTime;
    public static Uniform staticLayers;

    public static Uniform nebulaTime;

    public static Uniform crystalTime;

    public static int renderTime;
    public static float renderFrame;

    @SubscribeEvent
    public static void register(RegisterShadersEvent event) {
        try {
            END_ENTITY = new ShaderInstance(event.getResourceProvider(), new ResourceLocation("weaversparadise", "end_entity"), DefaultVertexFormat.NEW_ENTITY);
            event.registerShader(END_ENTITY, shaderInstance -> {
                endPortalTime = shaderInstance.getUniform("GameTime");
                endPortalLayers = shaderInstance.getUniform("EndPortalLayers");
                endPortalTime.set((float) renderTime + renderFrame);
                endPortalLayers.set(15);

                END_ENTITY.apply();
            });

            POLYCHROMATIC = new ShaderInstance(event.getResourceProvider(), new ResourceLocation("weaversparadise", "polychromatic"), DefaultVertexFormat.NEW_ENTITY);
            event.registerShader(POLYCHROMATIC, shaderInstance -> {
                POLYCHROMATIC.apply();
            });

            ENTITY_TRANSLUCENT_MASK = new ShaderInstance(event.getResourceProvider(), new ResourceLocation("weaversparadise", "entity_translucent_mask"), DefaultVertexFormat.NEW_ENTITY);
            event.registerShader(ENTITY_TRANSLUCENT_MASK, shaderInstance -> {
                ENTITY_TRANSLUCENT_MASK.apply();
            });

            ENTITY_STATIC = new ShaderInstance(event.getResourceProvider(), new ResourceLocation("weaversparadise", "entity_static_noise"), DefaultVertexFormat.NEW_ENTITY);
            event.registerShader(ENTITY_STATIC, shaderInstance -> {

                staticTime = shaderInstance.getUniform("GameTime");
                staticLayers = shaderInstance.getUniform("StaticLayers");
                staticTime.set((float) renderTime + renderFrame);
                staticLayers.set(15);

                ENTITY_STATIC.apply();
            });

            ENTITY_CRYSTAL = new ShaderInstance(event.getResourceProvider(), new ResourceLocation("weaversparadise", "entity_crystal"), DefaultVertexFormat.NEW_ENTITY);
            event.registerShader(ENTITY_CRYSTAL, shaderInstance -> {

                crystalTime = shaderInstance.getUniform("Time");
                crystalTime.set((float) renderTime + renderFrame);

                ENTITY_CRYSTAL.apply();
            });

            NEGATIVE = new ShaderInstance(event.getResourceProvider(), new ResourceLocation("weaversparadise", "negative"), DefaultVertexFormat.NEW_ENTITY);
            event.registerShader(NEGATIVE, shaderInstance -> {
                NEGATIVE.apply();
            });

            TRUE_NEGATIVE = new ShaderInstance(event.getResourceProvider(), new ResourceLocation("weaversparadise", "true_negative"), DefaultVertexFormat.NEW_ENTITY);
            event.registerShader(TRUE_NEGATIVE, shaderInstance -> {
                TRUE_NEGATIVE.apply();
            });

            NEBULA = new ShaderInstance(event.getResourceProvider(), new ResourceLocation("weaversparadise", "nebula"), DefaultVertexFormat.NEW_ENTITY);
            event.registerShader(NEBULA, shaderInstance -> {

                nebulaTime = shaderInstance.getUniform("GameTime");
                nebulaTime.set((float) renderTime + renderFrame);

                NEBULA.apply();
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
