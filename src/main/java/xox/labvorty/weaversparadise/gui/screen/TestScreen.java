package xox.labvorty.weaversparadise.gui.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import xox.labvorty.weaversparadise.gui.menu.TestMenu;

@OnlyIn(Dist.CLIENT)
public class TestScreen extends AbstractContainerScreen<TestMenu> {
    private final Level level;
    private final int x, y, z;
    private final Player player;

    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath("weaversparadise", "textures/tooltips/star_full.png");

    public TestScreen(TestMenu menu, Inventory inventory, Component component) {
        super(menu, inventory, component);
        this.level = menu.level;
        this.x = menu.x;
        this.y = menu.y;
        this.z = menu.z;
        this.player = menu.player;
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int gx, int gy) {
        RenderSystem.setShaderColor(1, 1, 1, 1);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        guiGraphics.blit(TEXTURE, this.leftPos, this.topPos, 0, 0, 160, 160, 160, 160);
        RenderSystem.disableBlend();
    }

    @Override
    public boolean keyPressed(int key, int b, int c) {
        if (key == 256) {
            this.minecraft.player.closeContainer();
            return true;
        }
        return super.keyPressed(key, b, c);
    }

    @Override
    protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
    }

    @Override
    public void init() {
        super.init();
    }
}
