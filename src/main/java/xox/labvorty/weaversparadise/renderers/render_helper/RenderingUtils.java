package xox.labvorty.weaversparadise.renderers.render_helper;

import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.TheEndPortalRenderer;
import net.minecraft.resources.ResourceLocation;
import xox.labvorty.weaversparadise.renderers.WeaversParadiseRenderTypes;

import java.util.List;

public class RenderingUtils {
    List<String> pride = List.of(
            "agender",
            "aroace",
            "aromantic",
            "asexual",
            "bisexual",
            "demiboy",
            "demigender",
            "demigirl",
            "gay",
            "genderfluid",
            "genderqueer",
            "intersex",
            "lesbian",
            "nonbinary",
            "pansexual",
            "pride",
            "trans"
    );

    public VertexConsumer parseVC(MultiBufferSource multiBufferSource, String dyeType, ResourceLocation texture, String clothingType) {
        if (texture.getPath().isBlank() || texture.getNamespace().isBlank()) {
            texture = ResourceLocation.fromNamespaceAndPath(
                    "minecraft",
                    "textures/block/debug.png"
            );
        }

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
        } else if (dyeType.equals("static")) {
            return multiBufferSource.getBuffer(
                    WeaversParadiseRenderTypes.getEntityStatic(
                            texture
                    )
            );
        } else if (dyeType.equals("crystal")) {
            return multiBufferSource.getBuffer(
                    WeaversParadiseRenderTypes.getCrystal(
                            texture
                    )
            );
        }

        if (pride.contains(dyeType)) {
            ResourceLocation mask = ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/clothing/mask/" + clothingType + "/" + dyeType + ".png"
            );

            return multiBufferSource.getBuffer(
                    WeaversParadiseRenderTypes.getEntityTranslucentMask(
                            texture,
                            mask
                    )
            );
        }

        return multiBufferSource.getBuffer(RenderType.entityTranslucent(texture));
    }
}
