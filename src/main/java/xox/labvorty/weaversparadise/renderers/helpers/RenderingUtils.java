package xox.labvorty.weaversparadise.renderers.helpers;

import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.VertexMultiConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.resources.ResourceLocation;
import org.joml.Vector3f;
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

            vertexConsumer = VertexMultiConsumer.create(
                    glintConsumer,
                    vertexConsumer
            );
        }
        return vertexConsumer;
    }
}
