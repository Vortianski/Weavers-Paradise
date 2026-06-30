package xox.labvorty.weaversparadise.renderers.helpers;

import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.resources.ResourceLocation;
import xox.labvorty.weaversparadise.data.tooltip_components.DyeTypeRegistry;
import xox.labvorty.weaversparadise.data.tooltip_components.helper.DyeInstance;
import xox.labvorty.weaversparadise.data.tooltip_components.helper.RenderData;

public class RenderingUtils {
    public VertexConsumer parseVC(MultiBufferSource multiBufferSource, String dyeType, ResourceLocation texture, String clothingType) {
        if (texture.getPath().isBlank() || texture.getNamespace().isBlank()) {
            texture = ResourceLocation.fromNamespaceAndPath(
                    "minecraft",
                    "textures/block/debug.png"
            );
        }

        DyeInstance dyeInstance = DyeTypeRegistry.getDyeType(dyeType);
        return multiBufferSource.getBuffer(
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
