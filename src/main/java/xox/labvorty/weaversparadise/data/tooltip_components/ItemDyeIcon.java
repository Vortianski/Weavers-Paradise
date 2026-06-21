package xox.labvorty.weaversparadise.data.tooltip_components;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class ItemDyeIcon extends DyeIcon {
    private final Item item;

    public ItemDyeIcon(Item item) {
        this(16, 16, item);
    }

    public ItemDyeIcon(
            int width,
            int height,
            Item item
    ) {
        super(ResourceLocation.fromNamespaceAndPath("", ""), width, height);

        this.item = item;
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

        guiGraphics.renderItem(new ItemStack(item), 0, 0);

        poseStack.popPose();
    }
}
