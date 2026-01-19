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

@EventBusSubscriber(bus = EventBusSubscriber.Bus.GAME, value = Dist.CLIENT)
public class WeaversParadiseCustomShaders {
    public static ShaderInstance VOID_ARMOR_SHADER;

    public static int renderTime;
    public static float renderFrame;

    public static Uniform endPortalTime;
    public static Uniform endPortalLayers;

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