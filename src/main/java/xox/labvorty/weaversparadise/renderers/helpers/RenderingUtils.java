package xox.labvorty.weaversparadise.renderers.helpers;

import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.TheEndPortalRenderer;
import net.minecraft.resources.ResourceLocation;
import xox.labvorty.vortylib.init.VortyLibRenderTypes;

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
                    VortyLibRenderTypes.getEntityEndPortal(
                            TheEndPortalRenderer.END_SKY_LOCATION,
                            texture,
                            TheEndPortalRenderer.END_PORTAL_LOCATION
                    )
            );
        } else if (dyeType.equals("polychromatic")) {
            return multiBufferSource.getBuffer(
                    VortyLibRenderTypes.getEntityPolychromatic(
                            texture
                    )
            );
        } else if (dyeType.equals("static")) {
            return multiBufferSource.getBuffer(
                    VortyLibRenderTypes.getEntityStaticNoise(
                            texture
                    )
            );
        } else if (dyeType.equals("crystal")) {
            return multiBufferSource.getBuffer(
                    VortyLibRenderTypes.getEntityCrystal(
                            texture
                    )
            );
        } else if (dyeType.equals("negative")) {
            return multiBufferSource.getBuffer(
                    VortyLibRenderTypes.getEntityNegative(
                            texture
                    )
            );
        } else if (dyeType.equals("true_negative")) {
            return multiBufferSource.getBuffer(
                    VortyLibRenderTypes.getEntityTrueNegative(
                            texture
                    )
            );
        } else if (dyeType.equals("nebula")) {
            return multiBufferSource.getBuffer(
                    VortyLibRenderTypes.getEntityNebula(
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
                    VortyLibRenderTypes.getEntityTranslucentMask(
                            texture,
                            mask
                    )
            );
        }

        return multiBufferSource.getBuffer(RenderType.entityTranslucent(texture));
    }
}
