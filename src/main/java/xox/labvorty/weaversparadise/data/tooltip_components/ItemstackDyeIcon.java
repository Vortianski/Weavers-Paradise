package xox.labvorty.weaversparadise.data.tooltip_components;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class ItemstackDyeIcon extends DyeIcon {
    private final ItemStack itemStack;

    public ItemstackDyeIcon(ItemStack itemStack) {
        this(itemStack, 16, 16);
    }

    public ItemstackDyeIcon(ItemStack itemStack, int width, int height) {
        super(ResourceLocation.fromNamespaceAndPath("", ""), width, height);
        this.itemStack = itemStack;
    }

    @Override
    public void render(GuiGraphics guiGraphics, int x, int y) {
        PoseStack poseStack = guiGraphics.pose();
        float scaleX = 1.0f;
        float scaleY = 1.0f;

        if (width > 8) {
            scaleX = 8.0f / width;
        }

        if (height > 8) {
            scaleY = 8.0f / height;
        }

        poseStack.pushPose();

        poseStack.translate(x, y, 1);

        poseStack.scale(scaleX, scaleY, 1);

        guiGraphics.renderItem(itemStack, 0, 0);

        poseStack.popPose();
    }

    @Override
    public void renderNative(GuiGraphics guiGraphics, int x, int y) {
        guiGraphics.renderItem(itemStack, x, y);
    }

    @Override
    public void renderScaled(GuiGraphics guiGraphics, int x, int y, int targetWidth, int targetHeight) {
        PoseStack poseStack = guiGraphics.pose();
        float scaleX = width > targetWidth ? (float) targetWidth / width : 1.0f;
        float scaleY = height > targetHeight ? (float) targetHeight / height : 1.0f;

        poseStack.pushPose();
        poseStack.translate(x, y, 1);
        poseStack.scale(scaleX, scaleY, 1);
        guiGraphics.renderItem(itemStack, 0, 0);
        poseStack.popPose();
    }
}
