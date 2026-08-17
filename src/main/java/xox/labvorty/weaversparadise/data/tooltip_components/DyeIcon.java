package xox.labvorty.weaversparadise.data.tooltip_components;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

public class DyeIcon {
    protected final ResourceLocation resourceLocation;
    protected final int width;
    protected final int height;

    public DyeIcon(ResourceLocation resourceLocation) {
        this(resourceLocation, 8, 8);
    }

    public DyeIcon(
            ResourceLocation resourceLocation,
            int width,
            int height
    ) {
        this.resourceLocation = resourceLocation;
        this.width = width;
        this.height = height;
    }

    public void render(GuiGraphics guiGraphics, int x, int y) {
        guiGraphics.blit(resourceLocation, x, y, 0, 0, width, height, width, height);
    }

    public void renderNative(GuiGraphics guiGraphics, int x, int y) {
        guiGraphics.blit(resourceLocation, x, y, 0, 0, width, height, width, height);
    }

    public void renderScaled(GuiGraphics guiGraphics, int x, int y, int targetWidth, int targetHeight) {
        PoseStack poseStack = guiGraphics.pose();
        float scaleX = (float) targetWidth / width;
        float scaleY = (float) targetHeight / height;

        poseStack.pushPose();
        poseStack.translate(x, y, 1);
        poseStack.scale(scaleX, scaleY, 1);
        guiGraphics.blit(resourceLocation, 0, 0, 0, 0, width, height, width, height);
        poseStack.popPose();
    }
}
