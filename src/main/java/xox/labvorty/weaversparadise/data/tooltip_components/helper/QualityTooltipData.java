package xox.labvorty.weaversparadise.data.tooltip_components.helper;

import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

public class QualityTooltipData {
    public static List<ResourceLocation> getTooltipData(int quality) {
        List<ResourceLocation> list = new ArrayList();

        if (quality >= 2) {
            list.add(new ResourceLocation("weaversparadise:textures/tooltips/star_full.png"));
        } else if (quality == 1) {
            list.add(new ResourceLocation("weaversparadise:textures/tooltips/star_half.png"));
        } else {
            list.add(new ResourceLocation("weaversparadise:textures/tooltips/star_empty.png"));
        }

        if (quality >= 4) {
            list.add(new ResourceLocation("weaversparadise:textures/tooltips/star_full.png"));
        } else if (quality == 3) {
            list.add(new ResourceLocation("weaversparadise:textures/tooltips/star_half.png"));
        } else {
            list.add(new ResourceLocation("weaversparadise:textures/tooltips/star_empty.png"));
        }

        if (quality >= 6) {
            list.add(new ResourceLocation("weaversparadise:textures/tooltips/star_full.png"));
        } else if (quality == 5) {
            list.add(new ResourceLocation("weaversparadise:textures/tooltips/star_half.png"));
        } else {
            list.add(new ResourceLocation("weaversparadise:textures/tooltips/star_empty.png"));
        }

        if (quality >= 8) {
            list.add(new ResourceLocation("weaversparadise:textures/tooltips/star_full.png"));
        } else if (quality == 7) {
            list.add(new ResourceLocation("weaversparadise:textures/tooltips/star_half.png"));
        } else {
            list.add(new ResourceLocation("weaversparadise:textures/tooltips/star_empty.png"));
        }

        if (quality >= 10) {
            list.add(new ResourceLocation("weaversparadise:textures/tooltips/star_full.png"));
        } else if (quality == 9) {
            list.add(new ResourceLocation("weaversparadise:textures/tooltips/star_half.png"));
        } else {
            list.add(new ResourceLocation("weaversparadise:textures/tooltips/star_empty.png"));
        }

        return list;
    }
}
