package xox.labvorty.weaversparadise.renderers;

import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.neoforge.client.event.RegisterShadersEvent;
import java.io.IOException;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.resources.ResourceLocation;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.shaders.Uniform;
import net.minecraft.client.Minecraft;
import net.neoforged.neoforge.client.event.RenderFrameEvent;
import net.neoforged.neoforge.client.event.ClientTickEvent;

@EventBusSubscriber(value = Dist.CLIENT)
public class WeaversParadiseCustomShaders {
    public static ShaderInstance VOID_ARMOR_SHADER;
    public static ShaderInstance POLYCHROMATIC;
    public static ShaderInstance ENTITY_TRANSLUCENT_MASK;
    public static ShaderInstance ENTITY_STATIC;
    public static ShaderInstance ENTITY_CRYSTAL;
    public static ShaderInstance NEGATIVE;
    public static ShaderInstance TRUE_NEGATIVE;
    public static ShaderInstance NEBULA;

    public static int renderTime;
    public static float renderFrame;

    public static Uniform endPortalTime;
    public static Uniform endPortalLayers;

    public static Uniform staticTime;
    public static Uniform staticLayers;

    public static Uniform nebulaTime;

    public static Uniform crystalTime;

    public static void onRegisterShaders(RegisterShadersEvent event) {
        try {
            VOID_ARMOR_SHADER = new ShaderInstance(event.getResourceProvider(), ResourceLocation.fromNamespaceAndPath("weaversparadise", "void"), DefaultVertexFormat.NEW_ENTITY);
            event.registerShader(VOID_ARMOR_SHADER, shaderInstance -> {

                endPortalTime = shaderInstance.getUniform("GameTime");
                endPortalLayers = shaderInstance.getUniform("EndPortalLayers");
                endPortalTime.set((float) renderTime + renderFrame);
                endPortalLayers.set(15);

                VOID_ARMOR_SHADER.apply();
            });

            POLYCHROMATIC = new ShaderInstance(event.getResourceProvider(), ResourceLocation.fromNamespaceAndPath("weaversparadise", "polychromatic"), DefaultVertexFormat.NEW_ENTITY);
            event.registerShader(POLYCHROMATIC, shaderInstance -> {
                POLYCHROMATIC.apply();
            });

            ENTITY_TRANSLUCENT_MASK = new ShaderInstance(event.getResourceProvider(), ResourceLocation.fromNamespaceAndPath("weaversparadise", "entity_translucent_mask"), DefaultVertexFormat.NEW_ENTITY);
            event.registerShader(ENTITY_TRANSLUCENT_MASK, shaderInstance -> {
                ENTITY_TRANSLUCENT_MASK.apply();
            });

            ENTITY_STATIC = new ShaderInstance(event.getResourceProvider(), ResourceLocation.fromNamespaceAndPath("weaversparadise", "entity_static_noise"), DefaultVertexFormat.NEW_ENTITY);
            event.registerShader(ENTITY_STATIC, shaderInstance -> {

                staticTime = shaderInstance.getUniform("GameTime");
                staticLayers = shaderInstance.getUniform("StaticLayers");
                staticTime.set((float) renderTime + renderFrame);
                staticLayers.set(15);

                ENTITY_STATIC.apply();
            });

            ENTITY_CRYSTAL = new ShaderInstance(event.getResourceProvider(), ResourceLocation.fromNamespaceAndPath("weaversparadise", "entity_crystal"), DefaultVertexFormat.NEW_ENTITY);
            event.registerShader(ENTITY_CRYSTAL, shaderInstance -> {

                crystalTime = shaderInstance.getUniform("Time");
                crystalTime.set((float) renderTime + renderFrame);

                ENTITY_CRYSTAL.apply();
            });

            NEGATIVE = new ShaderInstance(event.getResourceProvider(), ResourceLocation.fromNamespaceAndPath("weaversparadise", "negative"), DefaultVertexFormat.NEW_ENTITY);
            event.registerShader(NEGATIVE, shaderInstance -> {
                NEGATIVE.apply();
            });

            TRUE_NEGATIVE = new ShaderInstance(event.getResourceProvider(), ResourceLocation.fromNamespaceAndPath("weaversparadise", "true_negative"), DefaultVertexFormat.NEW_ENTITY);
            event.registerShader(TRUE_NEGATIVE, shaderInstance -> {
                TRUE_NEGATIVE.apply();
            });

            NEBULA = new ShaderInstance(event.getResourceProvider(), ResourceLocation.fromNamespaceAndPath("weaversparadise", "nebula"), DefaultVertexFormat.NEW_ENTITY);
            event.registerShader(NEBULA, shaderInstance -> {

                nebulaTime = shaderInstance.getUniform("GameTime");
                nebulaTime.set((float) renderTime + renderFrame);

                NEBULA.apply();
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @SubscribeEvent
    public static void clientTick(ClientTickEvent.Post event) {
        if (!Minecraft.getInstance().isPaused() ) {
            ++renderTime;
        }
    }

    @SubscribeEvent
    public static void renderTick(RenderFrameEvent.Pre event) {
        if (!Minecraft.getInstance().isPaused()) {
            renderFrame = event.getPartialTick().getGameTimeDeltaTicks();
        }
    }
}