package xox.labvorty.weaversparadise.gui.screen;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.components.WidgetSprites;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.core.component.DataComponents;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.GuiGraphics;

import java.util.*;

import com.mojang.blaze3d.systems.RenderSystem;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.network.PacketDistributor;
import xox.labvorty.weaversparadise.data.ClothcraftingNetworkMultiMessage;
import xox.labvorty.weaversparadise.gui.menu.ClothcraftingMenu;
import xox.labvorty.weaversparadise.init.WeaversParadiseItems;
import xox.labvorty.weaversparadise.init.WeaversParadiseKeyMappings;
import xox.labvorty.weaversparadise.items.CottonSpool;
import xox.labvorty.weaversparadise.items.JeansSpool;
import xox.labvorty.weaversparadise.items.SilkSpool;
import xox.labvorty.weaversparadise.items.WoolSpool;

@OnlyIn(Dist.CLIENT)
public class ClothcraftingScreen extends AbstractContainerScreen<ClothcraftingMenu> {
    private final static HashMap<String, Object> guistate = ClothcraftingMenu.guistate;
    private final Level world;
    private final int x, y, z;
    private final Player entity;
    private static int gameTime = 0;
    private static boolean gameStarted = false;
    private static int gameScore = 0;
    private static List<ItemStack> items = new ArrayList();
    private ImageButton startMinigame;
    private ImageButton claimItems;
    private int upColoredTime = 0;
    private int rightColoredTime = 0;
    private int downColoredTime = 0;
    private int leftColoredTime = 0;

    private String previewL1One = "";
    private String previewL1Two = "";
    private String previewL1Three = "";
    private String previewL1Four = "";
    private String previewL1Five = "";

    private String previewL2One = "";
    private String previewL2Two = "";
    private String previewL2Three = "";
    private String previewL2Four = "";
    private String previewL2Five = "";

    private String previewL3One = "";
    private String previewL3Two = "";
    private String previewL3Three = "";
    private String previewL3Four = "";
    private String previewL3Five = "";

    private String previewL4One = "";
    private String previewL4Two = "";
    private String previewL4Three = "";
    private String previewL4Four = "";
    private String previewL4Five = "";

    private String pretypeOne = "";
    private String pretypeTwo = "";
    private String pretypeThree = "";
    private String pretypeFour = "";
    private String pretypeFive = "";

    private String inputOne = "";
    private String inputTwo = "";
    private String inputThree = "";
    private String inputFour = "";
    private String inputFive = "";
    private static String clothType = "";

    public static void updateData(int time, int score, boolean ison, List<ItemStack> item, String type) {
        gameTime = time;
        gameScore = score;
        gameStarted = ison;
        items = item;
        clothType = type;
    }

    public ClothcraftingScreen(ClothcraftingMenu container, Inventory inventory, Component text) {
        super(container, inventory, text);
        this.world = container.world;
        this.x = container.x;
        this.y = container.y;
        this.z = container.z;
        this.entity = container.entity;
        this.imageWidth = 176;
        this.imageHeight = 209;
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
            if (gameStarted) {
                this.renderMiniGame(guiGraphics, partialTicks, mouseX, mouseY);
            } else {
                this.renderBackground(guiGraphics, mouseX, mouseY, partialTicks);
                super.render(guiGraphics, mouseX, mouseY, partialTicks);
                this.renderTooltip(guiGraphics, mouseX, mouseY);
                PoseStack poseStack = guiGraphics.pose();

                String startButtonText = Component.translatable("weaversparadise.clothcrafting.start_button").getString();
                float startButtonScale = 1.0f;
                if (this.font.width(startButtonText) > 66) {
                    startButtonScale = 66.0f / this.font.width(startButtonText);
                }

                poseStack.pushPose();
                poseStack.scale(startButtonScale, startButtonScale, startButtonScale);
                poseStack.translate((this.leftPos + 38.5) / startButtonScale, (this.topPos + 104 + (5 * (1 - startButtonScale))) / startButtonScale, 1);
                guiGraphics.drawCenteredString(
                        this.font,
                        Component.literal(startButtonText),
                        0,
                        0,
                        0xFFFFFF);
                poseStack.popPose();

                String claimItemsText = Component.translatable("weaversparadise.clothcrafting.claim_items").getString();
                float claimItemsScale = 1.0f;
                if (this.font.width(claimItemsText) > 66) {
                    claimItemsScale = 66.0f / this.font.width(claimItemsText);
                }

                poseStack.pushPose();
                poseStack.scale(claimItemsScale, claimItemsScale, claimItemsScale);
                poseStack.translate((this.leftPos + 99 + 35.5) / claimItemsScale, (this.topPos + 104 + (5 * (1 - claimItemsScale))) / claimItemsScale, 1);
                guiGraphics.drawCenteredString(
                        this.font,
                        Component.literal(claimItemsText),
                        0,
                        0,
                        0xFFFFFF);
                poseStack.popPose();
            }
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int gx, int gy) {
        RenderSystem.setShaderColor(1, 1, 1, 1);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();

        //if (items != null) {
        //    for (int i = 0; i < items.size(); i++) {
        //        guiGraphics.renderItem(items.get(i), (i * 16), this.topPos);
        //    }
        //}

        if ((gx >= this.leftPos + 99) && (gx <= this.leftPos + 99 + 72) && (gy >= this.topPos + 96) && (gy <= this.topPos + 96 + 25)) {
            List<Component> components = new ArrayList<>();

            for (ItemStack itemStack : items) {
                if (itemStack.getItem().equals(WeaversParadiseItems.JEANS_CLOTH.get()) || itemStack.getItem().equals(WeaversParadiseItems.COTTON_CLOTH.get()) || itemStack.getItem().equals(WeaversParadiseItems.WOOL_CLOTH.get()) || itemStack.getItem().equals(WeaversParadiseItems.SILK_CLOTH.get())) {
                    int quality = itemStack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getInt("quality");
                    components.add(Component.literal(itemStack.getItem().getName(itemStack).getString() + " Q" + quality + " x" + itemStack.getCount()));
                } else {
                    components.add(Component.literal(itemStack.getItem().getName(itemStack).getString() + " x" + itemStack.getCount()));
                }
            }

            guiGraphics.renderTooltip(this.font, components, Optional.empty(), gx, gy);
        }

        guiGraphics.blit(ResourceLocation.parse("weaversparadise:textures/screens/clothcrafting_main.png"), this.leftPos, this.topPos, 0, 0, 192, 209, 192, 209);

        RenderSystem.disableBlend();
    }

    public String renderArrowsMainHelper(String text) {
        switch (text) {
            case "UP" -> {
                return "weaversparadise:textures/screens/clothcrafting_input_support_u.png";
            }

            case "LEFT" -> {
                return "weaversparadise:textures/screens/clothcrafting_input_support_l.png";
            }

            case "DOWN" -> {
                return "weaversparadise:textures/screens/clothcrafting_input_support_d.png";
            }

            case "RIGHT" -> {
                return "weaversparadise:textures/screens/clothcrafting_input_support_r.png";
            }
        }

        return "weaversparadise:textures/screens/clothcrafting_input_support_u.png";
    }

    public String renderInputAssist(String main, String input) {
        if (!main.isBlank()) {
            if (main.equals(input)) {
                return "weaversparadise:textures/screens/clothcrafting_input_filled.png";
            } else {
                return "weaversparadise:textures/screens/clothcrafting_input_empty.png";
            }
        } else {
            return "weaversparadise:textures/screens/clothcrafting_input_empty.png";
        }
    }

    public String assistersRender(String text) {
        switch (text) {
            case "UP" -> {
                return "weaversparadise:textures/screens/clothcrafting_input_future_u.png";
            }

            case "LEFT" -> {
                return "weaversparadise:textures/screens/clothcrafting_input_future_l.png";
            }

            case "DOWN" -> {
                return "weaversparadise:textures/screens/clothcrafting_input_future_d.png";
            }

            case "RIGHT" -> {
                return "weaversparadise:textures/screens/clothcrafting_input_future_r.png";
            }
        }

        return "weaversparadise:textures/screens/clothcrafting_input_future_u.png";
    }

    protected void renderMiniGame(GuiGraphics guiGraphics, float partialTicks, int gx, int gy) {
        RenderSystem.setShaderColor(1, 1, 1, 1);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        PoseStack poseStack = guiGraphics.pose();

        int minRed = 0;
        int minGreen = 255;
        int minBlue = 0;

        int maxRed = 255;
        int maxGreen = 255;
        int maxBlue = 255;

        int totalSeconds = gameTime / 20;

        int minutes = totalSeconds / 60;
        int seconds = totalSeconds % 60;

        guiGraphics.blit(ResourceLocation.parse("weaversparadise:textures/screens/clothcrafting_minigame.png"), this.leftPos - 38, this.topPos - 26, 0, 0, 269, 255, 269, 255);
        guiGraphics.drawString(this.font, Component.literal(Component.translatable("weaversparadise.clothcrafting.time").getString() + " " + String.format("%02d:%02d", minutes, seconds)), this.leftPos + 10, this.topPos - 8, 0x000000, false);
        guiGraphics.drawString(this.font, Component.literal(Component.translatable("weaversparadise.clothcrafting.score").getString() + " " + gameScore), this.leftPos - 38 + 183, this.topPos - 6, 0xFFFFFF, false);

        guiGraphics.blit(
                ResourceLocation.parse(renderInputAssist(pretypeOne, inputOne)),
                this.leftPos - 3,
                this.topPos + 135,
                0,
                0,
                39,
                39,
                39,
                39
        );

        guiGraphics.blit(
                ResourceLocation.parse(renderInputAssist(pretypeTwo, inputTwo)),
                this.leftPos - 38 + 76,
                this.topPos + 135,
                0,
                0,
                39,
                39,
                39,
                39
        );

        guiGraphics.blit(
                ResourceLocation.parse(renderInputAssist(pretypeThree, inputThree)),
                this.leftPos - 38 + 117,
                this.topPos + 135,
                0,
                0,
                39,
                39,
                39,
                39
        );

        guiGraphics.blit(
                ResourceLocation.parse(renderInputAssist(pretypeFour, inputFour)),
                this.leftPos - 38 + 158,
                this.topPos + 135,
                0,
                0,
                39,
                39,
                39,
                39
        );

        guiGraphics.blit(
                ResourceLocation.parse(renderInputAssist(pretypeFive, inputFive)),
                this.leftPos - 38 + 199,
                this.topPos + 135,
                0,
                0,
                39,
                39,
                39,
                39
        );

        guiGraphics.blit(
                ResourceLocation.parse(renderArrowsMainHelper(pretypeOne)),
                this.leftPos - 3,
                this.topPos + 135,
                0,
                0,
                39,
                39,
                39,
                39
        );

        guiGraphics.blit(
                ResourceLocation.parse(renderArrowsMainHelper(pretypeTwo)),
                this.leftPos - 38 + 76,
                this.topPos + 135,
                0,
                0,
                39,
                39,
                39,
                39
        );

        guiGraphics.blit(
                ResourceLocation.parse(renderArrowsMainHelper(pretypeThree)),
                this.leftPos - 38 + 117,
                this.topPos + 135,
                0,
                0,
                39,
                39,
                39,
                39
        );

        guiGraphics.blit(
                ResourceLocation.parse(renderArrowsMainHelper(pretypeFour)),
                this.leftPos - 38 + 158,
                this.topPos + 135,
                0,
                0,
                39,
                39,
                39,
                39
        );

        guiGraphics.blit(
                ResourceLocation.parse(renderArrowsMainHelper(pretypeFive)),
                this.leftPos - 38 + 199,
                this.topPos + 135,
                0,
                0,
                39,
                39,
                39,
                39
        );

        guiGraphics.blit(
                ResourceLocation.parse(assistersRender(previewL1One)),
                this.leftPos - 38 + 75,
                this.topPos - 26 + 128,
                0,
                0,
                23,
                23,
                23,
                23
        );

        guiGraphics.blit(
                ResourceLocation.parse(assistersRender(previewL1Two)),
                this.leftPos - 38 + 100,
                this.topPos - 26 + 128,
                0,
                0,
                23,
                23,
                23,
                23
        );

        guiGraphics.blit(
                ResourceLocation.parse(assistersRender(previewL1Three)),
                this.leftPos - 38 + 125,
                this.topPos - 26 + 128,
                0,
                0,
                23,
                23,
                23,
                23
        );

        guiGraphics.blit(
                ResourceLocation.parse(assistersRender(previewL1Four)),
                this.leftPos - 38 + 150,
                this.topPos - 26 + 128,
                0,
                0,
                23,
                23,
                23,
                23
        );

        guiGraphics.blit(
                ResourceLocation.parse(assistersRender(previewL1Five)),
                this.leftPos - 38 + 175,
                this.topPos - 26 + 128,
                0,
                0,
                23,
                23,
                23,
                23
        );

        guiGraphics.blit(
                ResourceLocation.parse(assistersRender(previewL2One)),
                this.leftPos - 38 + 75,
                this.topPos - 26 + 103,
                0,
                0,
                23,
                23,
                23,
                23
        );

        guiGraphics.blit(
                ResourceLocation.parse(assistersRender(previewL2Two)),
                this.leftPos - 38 + 100,
                this.topPos - 26 + 103,
                0,
                0,
                23,
                23,
                23,
                23
        );

        guiGraphics.blit(
                ResourceLocation.parse(assistersRender(previewL2Three)),
                this.leftPos - 38 + 125,
                this.topPos - 26 + 103,
                0,
                0,
                23,
                23,
                23,
                23
        );

        guiGraphics.blit(
                ResourceLocation.parse(assistersRender(previewL2Four)),
                this.leftPos - 38 + 150,
                this.topPos - 26 + 103,
                0,
                0,
                23,
                23,
                23,
                23
        );

        guiGraphics.blit(
                ResourceLocation.parse(assistersRender(previewL2Five)),
                this.leftPos - 38 + 175,
                this.topPos - 26 + 103,
                0,
                0,
                23,
                23,
                23,
                23
        );

        guiGraphics.blit(
                ResourceLocation.parse(assistersRender(previewL3One)),
                this.leftPos - 38 + 75,
                this.topPos - 26 + 78,
                0,
                0,
                23,
                23,
                23,
                23
        );

        guiGraphics.blit(
                ResourceLocation.parse(assistersRender(previewL3Two)),
                this.leftPos - 38 + 100,
                this.topPos - 26 + 78,
                0,
                0,
                23,
                23,
                23,
                23
        );

        guiGraphics.blit(
                ResourceLocation.parse(assistersRender(previewL3Three)),
                this.leftPos - 38 + 125,
                this.topPos - 26 + 78,
                0,
                0,
                23,
                23,
                23,
                23
        );

        guiGraphics.blit(
                ResourceLocation.parse(assistersRender(previewL3Four)),
                this.leftPos - 38 + 150,
                this.topPos - 26 + 78,
                0,
                0,
                23,
                23,
                23,
                23
        );

        guiGraphics.blit(
                ResourceLocation.parse(assistersRender(previewL3Five)),
                this.leftPos - 38 + 175,
                this.topPos - 26 + 78,
                0,
                0,
                23,
                23,
                23,
                23
        );

        guiGraphics.blit(
                ResourceLocation.parse(assistersRender(previewL4One)),
                this.leftPos - 38 + 75,
                this.topPos - 26 + 53,
                0,
                0,
                23,
                23,
                23,
                23
        );

        guiGraphics.blit(
                ResourceLocation.parse(assistersRender(previewL4Two)),
                this.leftPos - 38 + 100,
                this.topPos - 26 + 53,
                0,
                0,
                23,
                23,
                23,
                23
        );

        guiGraphics.blit(
                ResourceLocation.parse(assistersRender(previewL4Three)),
                this.leftPos - 38 + 125,
                this.topPos - 26 + 53,
                0,
                0,
                23,
                23,
                23,
                23
        );

        guiGraphics.blit(
                ResourceLocation.parse(assistersRender(previewL4Four)),
                this.leftPos - 38 + 150,
                this.topPos - 26 + 53,
                0,
                0,
                23,
                23,
                23,
                23
        );

        guiGraphics.blit(
                ResourceLocation.parse(assistersRender(previewL4Five)),
                this.leftPos - 38 + 175,
                this.topPos - 26 + 53,
                0,
                0,
                23,
                23,
                23,
                23
        );

        String keyRight = WeaversParadiseKeyMappings.MINIGAME_RIGHT_BUTTON.getTranslatedKeyMessage().getString();
        float keyRightScale;

        if (keyRight.length() > 5) {
            keyRightScale = 5.0f / keyRight.length();
        } else {
            keyRightScale = 1.0f;
        }

        float rightStat = rightColoredTime / 20.0f;
        int rightColor = 255 << 24 | Mth.lerpInt(rightStat, maxRed, minRed) << 16 | Mth.lerpInt(rightStat, maxGreen, minGreen) << 8 | Mth.lerpInt(rightStat, maxBlue, minBlue);

        poseStack.pushPose();
        poseStack.scale(keyRightScale, keyRightScale, keyRightScale);
        poseStack.translate((this.leftPos) / keyRightScale, (this.topPos - 26 + 237 + (5 * (1 - keyRightScale))) / keyRightScale, 1.0f);
        guiGraphics.drawCenteredString(this.font, Component.literal(keyRight), 0, 0, rightColor);
        poseStack.popPose();

        String keyUp = WeaversParadiseKeyMappings.MINIGAME_UP_BUTTON.getTranslatedKeyMessage().getString();
        float keyUpScale;

        if (keyUp.length() > 5) {
            keyUpScale = 5.0f / keyUp.length();
        } else {
            keyUpScale = 1.0f;
        }

        float upStat = upColoredTime / 20.0f;
        int upColor = 255 << 24 | Mth.lerpInt(upStat, maxRed, minRed) << 16 | Mth.lerpInt(upStat, maxGreen, minGreen) << 8 | Mth.lerpInt(upStat, maxBlue, minBlue);

        poseStack.pushPose();
        poseStack.scale(keyUpScale, keyUpScale, keyUpScale);
        poseStack.translate((this.leftPos + 50) / keyUpScale, (this.topPos - 26 + 237 + (5 * (1 - keyUpScale))) / keyUpScale, 1.0f);
        guiGraphics.drawCenteredString(this.font, Component.literal(keyUp), 0, 0, upColor);
        poseStack.popPose();

        String keyLeft = WeaversParadiseKeyMappings.MINIGAME_LEFT_BUTTON.getTranslatedKeyMessage().getString();
        float keyLeftScale;

        if (keyLeft.length() > 5) {
            keyLeftScale = 5.0f / keyLeft.length();
        } else {
            keyLeftScale = 1.0f;
        }

        float leftStat = leftColoredTime / 20.0f;
        int leftColor = 255 << 24 | Mth.lerpInt(leftStat, maxRed, minRed) << 16 | Mth.lerpInt(leftStat, maxGreen, minGreen) << 8 | Mth.lerpInt(leftStat, maxBlue, minBlue);

        poseStack.pushPose();
        poseStack.scale(keyLeftScale, keyLeftScale, keyLeftScale);
        poseStack.translate((this.leftPos + 100) / keyLeftScale, (this.topPos - 26 + 237 + (5 * (1 - keyLeftScale))) / keyLeftScale, 1.0f);
        guiGraphics.drawCenteredString(this.font, Component.literal(keyLeft), 0, 0, leftColor);
        poseStack.popPose();

        String keyDown = WeaversParadiseKeyMappings.MINIGAME_DOWN_BUTTON.getTranslatedKeyMessage().getString();
        float keyDownScale;

        if (keyDown.length() > 5) {
            keyDownScale = 5.0f / keyDown.length();
        } else {
            keyDownScale = 1.0f;
        }

        float downStat = downColoredTime / 20.0f;
        int downColor = 255 << 24 | Mth.lerpInt(downStat, maxRed, minRed) << 16 | Mth.lerpInt(downStat, maxGreen, minGreen) << 8 | Mth.lerpInt(downStat, maxBlue, minBlue);

        poseStack.pushPose();
        poseStack.scale(keyDownScale, keyDownScale, keyDownScale);
        poseStack.translate((this.leftPos + 150) / keyDownScale, (this.topPos - 26 + 237 + (5 * (1 - keyDownScale))) / keyDownScale, 1.0f);
        guiGraphics.drawCenteredString(this.font, Component.literal(keyDown), 0, 0, downColor);
        poseStack.popPose();

        RenderSystem.disableBlend();
    }

    @Override
    public boolean keyPressed(int key, int b, int c) {
        if (key == 256) {
            this.minecraft.player.closeContainer();
            return true;
        }

        if (gameStarted) {
            if (key == WeaversParadiseKeyMappings.MINIGAME_UP_BUTTON.getKey().getValue()) {
                upColoredTime = 20;
                if (inputOne.isBlank()) {
                    inputOne = "UP";
                } else if (inputTwo.isBlank()) {
                    inputTwo = "UP";
                } else if (inputThree.isBlank()) {
                    inputThree = "UP";
                } else if (inputFour.isBlank()) {
                    inputFour = "UP";
                } else if (inputFive.isBlank()) {
                    inputFive = "UP";
                }
            }

            if (key == WeaversParadiseKeyMappings.MINIGAME_RIGHT_BUTTON.getKey().getValue()) {
                rightColoredTime = 20;
                if (inputOne.isBlank()) {
                    inputOne = "RIGHT";
                } else if (inputTwo.isBlank()) {
                    inputTwo = "RIGHT";
                } else if (inputThree.isBlank()) {
                    inputThree = "RIGHT";
                } else if (inputFour.isBlank()) {
                    inputFour = "RIGHT";
                } else if (inputFive.isBlank()) {
                    inputFive = "RIGHT";
                }
            }

            if (key == WeaversParadiseKeyMappings.MINIGAME_DOWN_BUTTON.getKey().getValue()) {
                downColoredTime = 20;
                if (inputOne.isBlank()) {
                    inputOne = "DOWN";
                } else if (inputTwo.isBlank()) {
                    inputTwo = "DOWN";
                } else if (inputThree.isBlank()) {
                    inputThree = "DOWN";
                } else if (inputFour.isBlank()) {
                    inputFour = "DOWN";
                } else if (inputFive.isBlank()) {
                    inputFive = "DOWN";
                }
            }

            if (key == WeaversParadiseKeyMappings.MINIGAME_LEFT_BUTTON.getKey().getValue()) {
                leftColoredTime = 20;
                if (inputOne.isBlank()) {
                    inputOne = "LEFT";
                } else if (inputTwo.isBlank()) {
                    inputTwo = "LEFT";
                } else if (inputThree.isBlank()) {
                    inputThree = "LEFT";
                } else if (inputFour.isBlank()) {
                    inputFour = "LEFT";
                } else if (inputFive.isBlank()) {
                    inputFive = "LEFT";
                }
            }

            return true;
        } else {
            return super.keyPressed(key, b, c);
        }
    }

    @Override
    protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
    }

    public boolean hasCraftItemsInSlot() {
        Slot slot = this.menu.slots.get(0);

        if (slot != null) {
            if (slot.getItem().is(WeaversParadiseItems.SILK_SPOOL.get()) && slot.getItem().getCount() > 5) {
                return true;
            }

            if (slot.getItem().is(WeaversParadiseItems.WOOL_SPOOL.get()) && slot.getItem().getCount() > 5) {
                return true;
            }

            if (slot.getItem().is(WeaversParadiseItems.COTTON_SPOOL.get()) && slot.getItem().getCount() > 5) {
                return true;
            }

            if (slot.getItem().is(WeaversParadiseItems.JEANS_SPOOL.get()) && slot.getItem().getCount() > 5) {
                return true;
            }
        }

        return false;
    }

    public String getClothType() {
        Slot slot = this.menu.slots.get(0);
        ItemStack stack = slot.getItem();

        if (stack.getItem() instanceof SilkSpool silkSpool) {
            return "SILK";
        }

        if (stack.getItem() instanceof WoolSpool woolSpool) {
            return "WOOL";
        }

        if (stack.getItem() instanceof CottonSpool cottonSpool) {
            return "COTTON";
        }

        if (stack.getItem() instanceof JeansSpool jeansSpool) {
            return "JEANS";
        }

        return "";
    }

    @Override
    public void init() {
        super.init();
        startMinigame = new ImageButton(
                this.leftPos + 3,
                this.topPos + 96,
                72,
                25,
                new WidgetSprites(ResourceLocation.parse("weaversparadise:textures/screens/clothcrafting_button.png"), ResourceLocation.parse("weaversparadise:textures/screens/clothcrafting_button.png")),
                e -> {
                        if (hasCraftItemsInSlot()) {
                            PacketDistributor.sendToServer(new ClothcraftingNetworkMultiMessage(1, x, y, z, 900, 0, true, items, getClothType()));
                            PacketDistributor.sendToServer(new ClothcraftingNetworkMultiMessage(5, x, y, z, gameTime, gameScore, gameStarted, items, clothType));
                            guistate.remove("startMinigame");
                            guistate.remove("claimItems");
                            this.removeWidget(startMinigame);
                            this.removeWidget(claimItems);
                        }
                }
                ) {
            @Override
            public void renderWidget(GuiGraphics guiGraphics, int x, int y, float partialTicks) {
                guiGraphics.blit(sprites.get(isActive(), isHoveredOrFocused()), getX(), getY(), 0, 0, width, height, width, height);
            }
        };
        guistate.put("startMinigame", startMinigame);
        this.addRenderableWidget(startMinigame);

        claimItems = new ImageButton(
                this.leftPos + 99,
                this.topPos + 96,
                72,
                25,
                new WidgetSprites(ResourceLocation.parse("weaversparadise:textures/screens/clothcrafting_button.png"), ResourceLocation.parse("weaversparadise:textures/screens/clothcrafting_button.png")),
                e -> {
                    PacketDistributor.sendToServer(new ClothcraftingNetworkMultiMessage(3, x, y, z, gameTime, gameScore, gameStarted, items, clothType));
                }
        ) {
            @Override
            public void renderWidget(GuiGraphics guiGraphics, int x, int y, float partialTicks) {
                guiGraphics.blit(sprites.get(isActive(), isHoveredOrFocused()), getX(), getY(), 0, 0, width, height, width, height);
            }
        };
        guistate.put("claimItems", claimItems);
        this.addRenderableWidget(claimItems);

        RandomSource random = RandomSource.create();

        previewL1One = randomTextHelper(random.nextIntBetweenInclusive(1,4));
        previewL1Two = randomTextHelper(random.nextIntBetweenInclusive(1,4));
        previewL1Three = randomTextHelper(random.nextIntBetweenInclusive(1,4));
        previewL1Four = randomTextHelper(random.nextIntBetweenInclusive(1,4));
        previewL1Five = randomTextHelper(random.nextIntBetweenInclusive(1,4));

        previewL2One = randomTextHelper(random.nextIntBetweenInclusive(1,4));
        previewL2Two = randomTextHelper(random.nextIntBetweenInclusive(1,4));
        previewL2Three = randomTextHelper(random.nextIntBetweenInclusive(1,4));
        previewL2Four = randomTextHelper(random.nextIntBetweenInclusive(1,4));
        previewL2Five = randomTextHelper(random.nextIntBetweenInclusive(1,4));

        previewL3One = randomTextHelper(random.nextIntBetweenInclusive(1,4));
        previewL3Two = randomTextHelper(random.nextIntBetweenInclusive(1,4));
        previewL3Three = randomTextHelper(random.nextIntBetweenInclusive(1,4));
        previewL3Four = randomTextHelper(random.nextIntBetweenInclusive(1,4));
        previewL3Five = randomTextHelper(random.nextIntBetweenInclusive(1,4));

        previewL4One = randomTextHelper(random.nextIntBetweenInclusive(1,4));
        previewL4Two = randomTextHelper(random.nextIntBetweenInclusive(1,4));
        previewL4Three = randomTextHelper(random.nextIntBetweenInclusive(1,4));
        previewL4Four = randomTextHelper(random.nextIntBetweenInclusive(1,4));
        previewL4Five = randomTextHelper(random.nextIntBetweenInclusive(1,4));

        pretypeOne = randomTextHelper(random.nextIntBetweenInclusive(1,4));
        pretypeTwo = randomTextHelper(random.nextIntBetweenInclusive(1,4));
        pretypeThree = randomTextHelper(random.nextIntBetweenInclusive(1,4));
        pretypeFour = randomTextHelper(random.nextIntBetweenInclusive(1,4));
        pretypeFive = randomTextHelper(random.nextIntBetweenInclusive(1,4));
    }

    private String randomTextHelper(int num) {
        switch (num) {
            case 1 -> {
                return "UP";
            }

            case 2 -> {
                return "LEFT";
            }

            case 3 -> {
                return "DOWN";
            }

            case 4 -> {
                return "RIGHT";
            }
        }

        return "";
    }

    @Override
    public void containerTick() {
        PacketDistributor.sendToServer(new ClothcraftingNetworkMultiMessage(0, x, y, z, 0, 0, false, new ArrayList<>(), clothType));
        if (upColoredTime > 0) {
            upColoredTime -= 1;
        }

        if (rightColoredTime > 0) {
            rightColoredTime -= 1;
        }

        if (downColoredTime > 0) {
            downColoredTime -= 1;
        }

        if (leftColoredTime > 0) {
            leftColoredTime -= 1;
        }

        if (pretypeOne != inputOne && !inputOne.isBlank()) {
            inputOne = "";
            inputTwo = "";
            inputThree = "";
            inputFour = "";
            inputFive = "";
            Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forUI(SoundEvents.NOTE_BLOCK_IRON_XYLOPHONE, 0.5f));
        }

        if (pretypeTwo != inputTwo && !inputTwo.isBlank()) {
            inputOne = "";
            inputTwo = "";
            inputThree = "";
            inputFour = "";
            inputFive = "";
            Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forUI(SoundEvents.NOTE_BLOCK_IRON_XYLOPHONE, 0.5f));
        }

        if (pretypeThree != inputThree && !inputThree.isBlank()) {
            inputOne = "";
            inputTwo = "";
            inputThree = "";
            inputFour = "";
            inputFive = "";
            Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forUI(SoundEvents.NOTE_BLOCK_IRON_XYLOPHONE, 0.5f));
        }

        if (pretypeFour != inputFour && !inputFour.isBlank()) {
            inputOne = "";
            inputTwo = "";
            inputThree = "";
            inputFour = "";
            inputFive = "";
            Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forUI(SoundEvents.NOTE_BLOCK_IRON_XYLOPHONE, 0.5f));
        }

        if (pretypeFive != inputFive && !inputFive.isBlank()) {
            inputOne = "";
            inputTwo = "";
            inputThree = "";
            inputFour = "";
            inputFive = "";
            Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forUI(SoundEvents.NOTE_BLOCK_IRON_XYLOPHONE, 0.5f));
        }

        if (inputOne == pretypeOne
                && inputTwo == pretypeTwo
                && inputThree == pretypeThree
                && inputFour == pretypeFour
                && inputFive == pretypeFive
        ) {
            RandomSource random = RandomSource.create();

            pretypeOne = previewL1One;
            pretypeTwo = previewL1Two;
            pretypeThree = previewL1Three;
            pretypeFour = previewL1Four;
            pretypeFive = previewL1Five;

            previewL1One = previewL2One;
            previewL1Two = previewL2Two;
            previewL1Three = previewL2Three;
            previewL1Four = previewL2Four;
            previewL1Five = previewL2Five;

            previewL2One = previewL3One;
            previewL2Two = previewL3Two;
            previewL2Three = previewL3Three;
            previewL2Four = previewL3Four;
            previewL2Five = previewL3Five;

            previewL3One = previewL4One;
            previewL3Two = previewL4Two;
            previewL3Three = previewL4Three;
            previewL3Four = previewL4Four;
            previewL3Five = previewL4Five;

            previewL4One = randomTextHelper(random.nextIntBetweenInclusive(1,4));
            previewL4Two = randomTextHelper(random.nextIntBetweenInclusive(1,4));
            previewL4Three = randomTextHelper(random.nextIntBetweenInclusive(1,4));
            previewL4Four = randomTextHelper(random.nextIntBetweenInclusive(1,4));
            previewL4Five = randomTextHelper(random.nextIntBetweenInclusive(1,4));

            inputOne = "";
            inputTwo = "";
            inputThree = "";
            inputFour = "";
            inputFive = "";

            Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forUI(SoundEvents.NOTE_BLOCK_CHIME, 2));
            PacketDistributor.sendToServer(new ClothcraftingNetworkMultiMessage(1, x, y, z, gameTime, gameScore + 1, gameStarted, items, clothType));
        }

        if (!gameStarted && guistate.containsKey("startMinigame")) {
            guistate.put("startMinigame", startMinigame);
            this.addRenderableWidget(startMinigame);
        }

        if (!gameStarted && guistate.containsKey("claimItems")) {
            guistate.put("claimItems", claimItems);
            this.addRenderableWidget(claimItems);
        }
    }
}
