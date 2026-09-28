package xox.labvorty.weaversparadise.renderers.helpers;

import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.VertexMultiConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.resources.ResourceLocation;
import org.joml.Vector3f;
import org.joml.Vector4f;
import xox.labvorty.vortylib.init.VortyLibRenderTypes;
import xox.labvorty.weaversparadise.data.tooltip_components.DyeTypeRegistry;
import xox.labvorty.weaversparadise.data.tooltip_components.helper.DyeInstance;
import xox.labvorty.weaversparadise.data.tooltip_components.helper.RenderData;

public class RenderingUtils {
    public VertexConsumer parseVC(MultiBufferSource multiBufferSource, String dyeType, ResourceLocation texture, String clothingType) {
        return this.parseVC(multiBufferSource, dyeType, texture, clothingType, false, new Vector3f(1, 1, 1));
    }

    public VertexConsumer parseVC(MultiBufferSource multiBufferSource, String dyeType, ResourceLocation texture, String clothingType, boolean glint, Vector3f glintColor) {
        if (texture.getPath().isBlank() || texture.getNamespace().isBlank()) {
            texture = ResourceLocation.fromNamespaceAndPath(
                    "minecraft",
                    "textures/block/debug.png"
            );
        }

        DyeInstance dyeInstance = DyeTypeRegistry.getDyeType(dyeType);
        VertexConsumer vertexConsumer = multiBufferSource.getBuffer(
                dyeInstance.getRenderTypeParser().apply(
                        new RenderData(
                                dyeType,
                                clothingType,
                                texture
                        )
                )
        );

        if (glint) {
            VertexConsumer glintConsumer = multiBufferSource.getBuffer(VortyLibRenderTypes.getEntityColoredGlint(glintColor));

            if (glintConsumer != vertexConsumer) {
                vertexConsumer = VertexMultiConsumer.create(
                        glintConsumer,
                        vertexConsumer
                );
            } else {
                vertexConsumer = multiBufferSource.getBuffer(
                        dyeInstance.getRenderTypeParser().apply(
                                new RenderData(
                                        dyeType,
                                        clothingType,
                                        texture
                                )
                        )
                );
            }
        }

        return vertexConsumer;
    }

    public Vector4f colorIntToVector4f(int color) {
        float a = ((color >> 24) & 0xFF) / 255.0f;
        float r = ((color >> 16) & 0xFF) / 255.0f;
        float g = ((color >> 8) & 0xFF) / 255.0f;
        float b = (color & 0xFF) / 255.0f;
        return new Vector4f(r, g, b, a);
    }
}
