package xox.labvorty.weaversparadise.gui.screen;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.GuiGraphics;
import java.util.HashMap;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.network.PacketDistributor;
import xox.labvorty.weaversparadise.blocks.entities.SpinningJennyBlockEntity;
import xox.labvorty.weaversparadise.data.StringNetworkMessage;
import xox.labvorty.weaversparadise.gui.menu.StringMenu;

@OnlyIn(Dist.CLIENT)
public class StringScreen extends AbstractContainerScreen<StringMenu> {
    private final static HashMap<String, Object> guistate = StringMenu.guistate;
    private final Level world;
    private final int x, y, z;
    private final Player entity;
    private static int progress = 0;

    public StringScreen(StringMenu container, Inventory inventory, Component text) {
        super(container, inventory, text);
        this.world = container.world;
        this.x = container.x;
        this.y = container.y;
        this.z = container.z;
        this.entity = container.entity;
        this.imageWidth = 176;
        this.imageHeight = 200;
    }

    public static void updateProgress(int prog) {
        progress = prog;
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(guiGraphics, mouseX, mouseY, partialTicks);
        super.render(guiGraphics, mouseX, mouseY, partialTicks);
        this.renderTooltip(guiGraphics, mouseX, mouseY);
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int gx, int gy) {
        RenderSystem.setShaderColor(1, 1, 1, 1);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();

        float percentage = (progress == 0 ? 1 : progress) / 100.0f;
        float realPercentage = 1.0f - percentage;

        guiGraphics.blit(ResourceLocation.parse("weaversparadise:textures/screens/string_ui.png"), this.leftPos + -8, this.topPos + -16, 0, 0, 240, 216, 240, 216);
        guiGraphics.blit(ResourceLocation.parse("weaversparadise:textures/screens/arrow_full.png"), this.leftPos + -8 + 152, this.topPos + -16 + 88, 0, 0, 31, 20, 31, 20);
        guiGraphics.blit(ResourceLocation.parse("weaversparadise:textures/screens/arrow_empty.png"), this.leftPos + -8 + 152, this.topPos + -16 + 88, 0, 0, (int)(31 * realPercentage), 20, 31, 20);

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

    @Override
    protected void containerTick() {
        PacketDistributor.sendToServer(new StringNetworkMessage(0, x, y, z, 0));
    }
}
