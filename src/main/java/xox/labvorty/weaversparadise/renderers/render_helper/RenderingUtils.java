package xox.labvorty.weaversparadise.renderers.render_helper;

import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.TheEndPortalRenderer;
import net.minecraft.resources.ResourceLocation;
import xox.labvorty.weaversparadise.renderers.WeaversParadiseRenderTypes;

public class RenderingUtils {
    public VertexConsumer parseVC(MultiBufferSource multiBufferSource, String dyeType, ResourceLocation texture) {
        if (dyeType.equals("ender")) {
            return multiBufferSource.getBuffer(
                    WeaversParadiseRenderTypes.getVoidArmor(
                            TheEndPortalRenderer.END_SKY_LOCATION,
                            texture,
                            TheEndPortalRenderer.END_PORTAL_LOCATION
                    )
            );
        } else if (dyeType.equals("polychromatic")) {
            return multiBufferSource.getBuffer(
                    WeaversParadiseRenderTypes.getPolychromatic(
                            texture
                    )
            );
        }

        return multiBufferSource.getBuffer(RenderType.entityTranslucent(texture));
    }
}
