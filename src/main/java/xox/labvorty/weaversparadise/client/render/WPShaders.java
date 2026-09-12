package xox.labvorty.weaversparadise.client.render;


import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import net.fabricmc.fabric.api.client.rendering.v1.CoreShaderRegistrationCallback;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.resources.ResourceLocation;
import xox.labvorty.weaversparadise.WeaversParadise;


import java.io.IOException;


/**
 * Порт xox.labvorty.vortylib.init.VortyLibShaders (NeoForge RegisterShadersEvent ->
 * Fabric CoreShaderRegistrationCallback).
 *
 * ВАЖНО: в отличие от NeoForge-версии, здесь НЕЛЬЗЯ вызывать uniform.set(...) в
 * consumer-лямбде регистрации: на Fabric consumer вызывается сразу после конструктора
 * ShaderInstance, когда буферы юниформов ещё не выделены — Uniform.set(float) на
 * int-юниформе кидает NPE ("this.floatValues is null"). Все значения уже заданы как
 * дефолтные "values" в json (EndPortalLayers=15, Time/StaticLayers=0 и т.д.) —
 * NeoForge-оригинал выставлял через set() ровно те же константы, поведение идентично.
 * Игровое время (GameTime) заливает сама vanilla в ShaderInstance.setDefaultUniforms.
 */
public class WPShaders {
    public static ShaderInstance ENTITY_END_PORTAL;
    public static ShaderInstance ENTITY_TRANSLUCENT_MASK;
    public static ShaderInstance ENTITY_NEGATIVE;
    public static ShaderInstance ENTITY_TRUE_NEGATIVE;
    public static ShaderInstance ENTITY_CRYSTAL;
    public static ShaderInstance ENTITY_STATIC_NOISE;
    public static ShaderInstance ENTITY_POLYCHROMATIC;
    public static ShaderInstance ENTITY_NEBULA;
    public static ShaderInstance ENTITY_CHROMATIC_ABERRATION;
    public static ShaderInstance ENTITY_PARALLAX;
    public static ShaderInstance ENTITY_COLORED_GLINT;
    public static ShaderInstance ENTITY_SPIRAL;


    private static boolean registered = false;


    /** Вызвать ОДИН РАЗ из WeaversParadiseFabricClient.onInitializeClient(). */
    public static void registerShaders() {
        if (registered) return;
        registered = true;
        CoreShaderRegistrationCallback.EVENT.register(context -> {
            try {
                context.register(id("entity_end_portal"), DefaultVertexFormat.NEW_ENTITY, shader -> ENTITY_END_PORTAL = shader);
                context.register(id("entity_translucent_mask"), DefaultVertexFormat.NEW_ENTITY, shader -> ENTITY_TRANSLUCENT_MASK = shader);
                context.register(id("entity_negative"), DefaultVertexFormat.NEW_ENTITY, shader -> ENTITY_NEGATIVE = shader);
                context.register(id("entity_true_negative"), DefaultVertexFormat.NEW_ENTITY, shader -> ENTITY_TRUE_NEGATIVE = shader);
                context.register(id("entity_crystal"), DefaultVertexFormat.NEW_ENTITY, shader -> ENTITY_CRYSTAL = shader);
                context.register(id("entity_static_noise"), DefaultVertexFormat.NEW_ENTITY, shader -> ENTITY_STATIC_NOISE = shader);
                context.register(id("entity_polychromatic"), DefaultVertexFormat.NEW_ENTITY, shader -> ENTITY_POLYCHROMATIC = shader);
                context.register(id("entity_nebula"), DefaultVertexFormat.NEW_ENTITY, shader -> ENTITY_NEBULA = shader);
                context.register(id("entity_chromatic_aberration"), DefaultVertexFormat.NEW_ENTITY, shader -> ENTITY_CHROMATIC_ABERRATION = shader);
                context.register(id("entity_translucent_parallax"), DefaultVertexFormat.NEW_ENTITY, shader -> ENTITY_PARALLAX = shader);
                context.register(id("entity_colored_glint"), DefaultVertexFormat.POSITION_TEX, shader -> ENTITY_COLORED_GLINT = shader);
                context.register(id("entity_spiral"), DefaultVertexFormat.NEW_ENTITY, shader -> ENTITY_SPIRAL = shader);
            } catch (IOException e) {
                throw new IllegalStateException("Не удалось зарегистрировать core-шейдеры WeaversParadise", e);
            }
        });
    }


    private static ResourceLocation id(String name) {
        return ResourceLocation.fromNamespaceAndPath(WeaversParadise.MODID, name);
    }
}
