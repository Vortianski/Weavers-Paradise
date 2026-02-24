package xox.labvorty.weaversparadise.gui.screen;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.TheEndPortalRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.core.component.DataComponents;
import net.minecraft.util.Mth;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.GuiGraphics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import xox.labvorty.weaversparadise.curios.WeaversParadiseMobLayers;
import xox.labvorty.weaversparadise.data.WeaversParadiseHandWarmersTextureHandler;
import xox.labvorty.weaversparadise.data.WeaversParadiseShirtTextureHandler;
import xox.labvorty.weaversparadise.data.WeaversParadiseThighHighsTextureHandler;
import xox.labvorty.weaversparadise.gui.menu.DyeingMenu;
import xox.labvorty.weaversparadise.init.WeaversParadiseItems;
import xox.labvorty.weaversparadise.items.*;
import xox.labvorty.weaversparadise.model.ModelChoker;
import xox.labvorty.weaversparadise.model.ModelUpperWear;
import xox.labvorty.weaversparadise.model.PantsModel;
import xox.labvorty.weaversparadise.model.ThighHighsModel;
import xox.labvorty.weaversparadise.renderers.WeaversParadiseGlobalRendererDataHolder;
import xox.labvorty.weaversparadise.renderers.WeaversParadiseRenderTypes;
import xox.labvorty.weaversparadise.renderers.render_helper.*;

@OnlyIn(Dist.CLIENT)
public class DyeingScreen extends AbstractContainerScreen<DyeingMenu> {
    private final static HashMap<String, Object> guistate = DyeingMenu.guistate;
    private final Level world;
    private final int x, y, z;
    private final Player entity;
    private ItemStack stack = ItemStack.EMPTY;
    private float modelYaw = 180f;
    private int lastMouseX = -1;
    private static final ThighHighsModel model = new ThighHighsModel(Minecraft.getInstance().getEntityModels().bakeLayer(WeaversParadiseMobLayers.THIGH_HIGHS));
    private static final ThighHighsModel model1 = new ThighHighsModel(Minecraft.getInstance().getEntityModels().bakeLayer(WeaversParadiseMobLayers.THIGH_HIGHS));
    private static final ModelUpperWear model2 = new ModelUpperWear(Minecraft.getInstance().getEntityModels().bakeLayer(WeaversParadiseMobLayers.UPPER_WEAR));
    private static final ModelChoker model3 = new ModelChoker(Minecraft.getInstance().getEntityModels().bakeLayer(WeaversParadiseMobLayers.CHOKER));
    private static final PantsModel model4 = new PantsModel(Minecraft.getInstance().getEntityModels().bakeLayer(WeaversParadiseMobLayers.PANTS));

    public DyeingScreen(DyeingMenu container, Inventory inventory, Component text) {
        super(container, inventory, text);
        this.world = container.world;
        this.x = container.x;
        this.y = container.y;
        this.z = container.z;
        this.entity = container.entity;
        this.imageWidth = 176;
        this.imageHeight = 220;
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(guiGraphics, mouseX, mouseY, partialTicks);
        super.render(guiGraphics, mouseX, mouseY, partialTicks);
        this.renderTooltip(guiGraphics, mouseX, mouseY);

        PoseStack poseStack = guiGraphics.pose();

        ThighHighModelRenderer thighHighModelRenderer = new ThighHighModelRenderer();
        HandWarmersSpecialModelRenderer handWarmersSpecialModelRenderer = new HandWarmersSpecialModelRenderer();
        ShirtModelRenderer shirtModelRenderer = new ShirtModelRenderer();
        ChokerModelRenderer chokerModelRenderer = new ChokerModelRenderer();
        PantsModelRenderer pantsModelRenderer = new PantsModelRenderer();

        if (stack.getItem() instanceof ThighHighsCotton cottonThighHighs) {
            Minecraft mc = Minecraft.getInstance();

            MultiBufferSource.BufferSource buffer = mc.renderBuffers().bufferSource();
            int ticks = (int) mc.level.getGameTime();
            int primaryColorLeftOne = cottonThighHighs.getItemMainColor(stack, "left", 1);
            int secondaryColorLeftOne = cottonThighHighs.getItemSecondaryColor(stack, "left", 1);
            int primaryColorRightOne = cottonThighHighs.getItemMainColor(stack, "right", 1);
            int secondaryColorRightOne = cottonThighHighs.getItemSecondaryColor(stack, "right", 1);
            int primaryColorLeftTwo = cottonThighHighs.getItemMainColor(stack, "left", 2);
            int secondaryColorLeftTwo = cottonThighHighs.getItemSecondaryColor(stack, "left", 2);
            int primaryColorRightTwo = cottonThighHighs.getItemMainColor(stack, "right", 2);
            int secondaryColorRightTwo = cottonThighHighs.getItemSecondaryColor(stack, "right", 2);
            String dyeTypeLeftOne = cottonThighHighs.getItemDyeType(stack, "left", 1);
            String dyeTypeRightOne = cottonThighHighs.getItemDyeType(stack, "right", 1);
            String dyeTypeLeftTwo = cottonThighHighs.getItemDyeType(stack, "left", 2);
            String dyeTypeRightTwo = cottonThighHighs.getItemDyeType(stack, "right", 2);
            String stensilTypeLeft = cottonThighHighs.getStensilType(stack, "left");
            String stensilTypeRight = cottonThighHighs.getStensilType(stack, "right");
            int lightValueLeftOne = cottonThighHighs.getItemLightValue(stack, "left", 1);
            int lightValueLeftTwo = cottonThighHighs.getItemLightValue(stack, "left", 2);
            int lightValueRightOne = cottonThighHighs.getItemLightValue(stack, "right", 1);
            int lightValueRightTwo = cottonThighHighs.getItemLightValue(stack, "right", 2);

            thighHighModelRenderer.renderModel(
                    buffer,
                    model,
                    new ThighHighsRenderingData(
                            primaryColorLeftOne,
                            secondaryColorLeftOne,
                            primaryColorRightOne,
                            secondaryColorRightOne,
                            primaryColorLeftTwo,
                            secondaryColorLeftTwo,
                            primaryColorRightTwo,
                            secondaryColorRightTwo,
                            dyeTypeLeftOne,
                            dyeTypeRightOne,
                            dyeTypeLeftTwo,
                            dyeTypeRightTwo,
                            stensilTypeLeft,
                            stensilTypeRight,
                            lightValueLeftOne,
                            lightValueLeftTwo,
                            lightValueRightOne,
                            lightValueRightTwo,
                            "cotton"
                    ),
                    mc.player,
                    48,
                    48,
                    48,
                    0,
                    0,
                    0,
                    this.leftPos + 87,
                    this.topPos,
                    50,
                    0,
                    modelYaw,
                    0,
                    poseStack,
                    LightTexture.FULL_BRIGHT
            );

            buffer.endBatch();
        } else if (stack.getItem() instanceof ThighHighsSilk silkThighHighs) {
            Minecraft mc = Minecraft.getInstance();

            MultiBufferSource.BufferSource buffer = mc.renderBuffers().bufferSource();
            int ticks = (int) mc.level.getGameTime();
            int primaryColorLeftOne = silkThighHighs.getItemMainColor(stack, "left", 1);
            int secondaryColorLeftOne = silkThighHighs.getItemSecondaryColor(stack, "left", 1);
            int primaryColorRightOne = silkThighHighs.getItemMainColor(stack, "right", 1);
            int secondaryColorRightOne = silkThighHighs.getItemSecondaryColor(stack, "right", 1);
            int primaryColorLeftTwo = silkThighHighs.getItemMainColor(stack, "left", 2);
            int secondaryColorLeftTwo = silkThighHighs.getItemSecondaryColor(stack, "left", 2);
            int primaryColorRightTwo = silkThighHighs.getItemMainColor(stack, "right", 2);
            int secondaryColorRightTwo = silkThighHighs.getItemSecondaryColor(stack, "right", 2);
            String dyeTypeLeftOne = silkThighHighs.getItemDyeType(stack, "left", 1);
            String dyeTypeRightOne = silkThighHighs.getItemDyeType(stack, "right", 1);
            String dyeTypeLeftTwo = silkThighHighs.getItemDyeType(stack, "left", 2);
            String dyeTypeRightTwo = silkThighHighs.getItemDyeType(stack, "right", 2);
            String stensilTypeLeft = silkThighHighs.getStensilType(stack, "left");
            String stensilTypeRight = silkThighHighs.getStensilType(stack, "right");
            int lightValueLeftOne = silkThighHighs.getItemLightValue(stack, "left", 1);
            int lightValueLeftTwo = silkThighHighs.getItemLightValue(stack, "left", 2);
            int lightValueRightOne = silkThighHighs.getItemLightValue(stack, "right", 1);
            int lightValueRightTwo = silkThighHighs.getItemLightValue(stack, "right", 2);

            thighHighModelRenderer.renderModel(
                    buffer,
                    model,
                    new ThighHighsRenderingData(
                            primaryColorLeftOne,
                            secondaryColorLeftOne,
                            primaryColorRightOne,
                            secondaryColorRightOne,
                            primaryColorLeftTwo,
                            secondaryColorLeftTwo,
                            primaryColorRightTwo,
                            secondaryColorRightTwo,
                            dyeTypeLeftOne,
                            dyeTypeRightOne,
                            dyeTypeLeftTwo,
                            dyeTypeRightTwo,
                            stensilTypeLeft,
                            stensilTypeRight,
                            lightValueLeftOne,
                            lightValueLeftTwo,
                            lightValueRightOne,
                            lightValueRightTwo,
                            "silk"
                    ),
                    mc.player,
                    48,
                    48,
                    48,
                    0,
                    0,
                    0,
                    this.leftPos + 87,
                    this.topPos,
                    50,
                    0,
                    modelYaw,
                    0,
                    poseStack,
                    LightTexture.FULL_BRIGHT
            );

            buffer.endBatch();
        } else if (stack.getItem() instanceof ThighHighsWool woolThighHighs) {
            Minecraft mc = Minecraft.getInstance();

            MultiBufferSource.BufferSource buffer = mc.renderBuffers().bufferSource();
            int ticks = (int) mc.level.getGameTime();
            int primaryColorLeftOne = woolThighHighs.getItemMainColor(stack, "left", 1);
            int secondaryColorLeftOne = woolThighHighs.getItemSecondaryColor(stack, "left", 1);
            int primaryColorRightOne = woolThighHighs.getItemMainColor(stack, "right", 1);
            int secondaryColorRightOne = woolThighHighs.getItemSecondaryColor(stack, "right", 1);
            int primaryColorLeftTwo = woolThighHighs.getItemMainColor(stack, "left", 2);
            int secondaryColorLeftTwo = woolThighHighs.getItemSecondaryColor(stack, "left", 2);
            int primaryColorRightTwo = woolThighHighs.getItemMainColor(stack, "right", 2);
            int secondaryColorRightTwo = woolThighHighs.getItemSecondaryColor(stack, "right", 2);
            String dyeTypeLeftOne = woolThighHighs.getItemDyeType(stack, "left", 1);
            String dyeTypeRightOne = woolThighHighs.getItemDyeType(stack, "right", 1);
            String dyeTypeLeftTwo = woolThighHighs.getItemDyeType(stack, "left", 2);
            String dyeTypeRightTwo = woolThighHighs.getItemDyeType(stack, "right", 2);
            String stensilTypeLeft = woolThighHighs.getStensilType(stack, "left");
            String stensilTypeRight = woolThighHighs.getStensilType(stack, "right");
            int lightValueLeftOne = woolThighHighs.getItemLightValue(stack, "left", 1);
            int lightValueLeftTwo = woolThighHighs.getItemLightValue(stack, "left", 2);
            int lightValueRightOne = woolThighHighs.getItemLightValue(stack, "right", 1);
            int lightValueRightTwo = woolThighHighs.getItemLightValue(stack, "right", 2);

            thighHighModelRenderer.renderModel(
                    buffer,
                    model,
                    new ThighHighsRenderingData(
                            primaryColorLeftOne,
                            secondaryColorLeftOne,
                            primaryColorRightOne,
                            secondaryColorRightOne,
                            primaryColorLeftTwo,
                            secondaryColorLeftTwo,
                            primaryColorRightTwo,
                            secondaryColorRightTwo,
                            dyeTypeLeftOne,
                            dyeTypeRightOne,
                            dyeTypeLeftTwo,
                            dyeTypeRightTwo,
                            stensilTypeLeft,
                            stensilTypeRight,
                            lightValueLeftOne,
                            lightValueLeftTwo,
                            lightValueRightOne,
                            lightValueRightTwo,
                            "wool"
                    ),
                    mc.player,
                    48,
                    48,
                    48,
                    0,
                    0,
                    0,
                    this.leftPos + 87,
                    this.topPos,
                    50,
                    0,
                    modelYaw,
                    0,
                    poseStack,
                    LightTexture.FULL_BRIGHT
            );

            buffer.endBatch();
        } else if (stack.getItem() instanceof ShirtCotton shirtCotton) {
            Minecraft mc = Minecraft.getInstance();

            MultiBufferSource.BufferSource buffer = mc.renderBuffers().bufferSource();
            int ticks = (int)mc.level.getGameTime();
            int primaryColorOne = shirtCotton.getItemMainColor(stack, 1);
            int secondaryColorOne = shirtCotton.getItemSecondaryColor(stack, 1);
            int primaryColorTwo = shirtCotton.getItemMainColor(stack, 2);
            int secondaryColorTwo = shirtCotton.getItemSecondaryColor(stack, 2);
            String dyeTypeOne = shirtCotton.getItemDyeType(stack, 1);
            String dyeTypeTwo = shirtCotton.getItemDyeType(stack, 2);
            String stensilType = shirtCotton.getStensilType(stack);
            int lightValueOne = shirtCotton.getItemLightValue(stack, 1);
            int lightValueTwo = shirtCotton.getItemLightValue(stack, 2);

            shirtModelRenderer.renderModel(
                    buffer,
                    model2,
                    new ShirtRenderingData(
                            stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getBoolean("is_open"),
                            primaryColorOne,
                            secondaryColorOne,
                            primaryColorTwo,
                            secondaryColorTwo,
                            dyeTypeOne,
                            dyeTypeTwo,
                            stensilType,
                            lightValueOne,
                            lightValueTwo,
                            "cotton"
                    ),
                    mc.player,
                    36,
                    36,
                    36,
                    0,
                    0,
                    0,
                    this.leftPos + 88,
                    this.topPos + 30,
                    50,
                    0,
                    modelYaw,
                    0,
                    poseStack,
                    LightTexture.FULL_BRIGHT
            );

            buffer.endBatch();
        } else if (stack.getItem() instanceof ShirtSilk shirtSilk) {
            Minecraft mc = Minecraft.getInstance();

            MultiBufferSource.BufferSource buffer = mc.renderBuffers().bufferSource();
            int ticks = (int)mc.level.getGameTime();
            int primaryColorOne = shirtSilk.getItemMainColor(stack, 1);
            int secondaryColorOne = shirtSilk.getItemSecondaryColor(stack, 1);
            int primaryColorTwo = shirtSilk.getItemMainColor(stack, 2);
            int secondaryColorTwo = shirtSilk.getItemSecondaryColor(stack, 2);
            String dyeTypeOne = shirtSilk.getItemDyeType(stack, 1);
            String dyeTypeTwo = shirtSilk.getItemDyeType(stack, 2);
            String stensilType = shirtSilk.getStensilType(stack);
            int lightValueOne = shirtSilk.getItemLightValue(stack, 1);
            int lightValueTwo = shirtSilk.getItemLightValue(stack, 2);

            shirtModelRenderer.renderModel(
                    buffer,
                    model2,
                    new ShirtRenderingData(
                            stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getBoolean("is_open"),
                            primaryColorOne,
                            secondaryColorOne,
                            primaryColorTwo,
                            secondaryColorTwo,
                            dyeTypeOne,
                            dyeTypeTwo,
                            stensilType,
                            lightValueOne,
                            lightValueTwo,
                            "silk"
                    ),
                    mc.player,
                    36,
                    36,
                    36,
                    0,
                    0,
                    0,
                    this.leftPos + 88,
                    this.topPos + 30,
                    50,
                    0,
                    modelYaw,
                    0,
                    poseStack,
                    LightTexture.FULL_BRIGHT
            );

            buffer.endBatch();
        } else if (stack.getItem() instanceof SweaterWool sweaterWool) {
            Minecraft mc = Minecraft.getInstance();

            MultiBufferSource.BufferSource buffer = mc.renderBuffers().bufferSource();
            int ticks = (int)mc.level.getGameTime();
            int primaryColorOne = sweaterWool.getItemMainColor(stack, 1);
            int secondaryColorOne = sweaterWool.getItemSecondaryColor(stack, 1);
            int primaryColorTwo = sweaterWool.getItemMainColor(stack, 2);
            int secondaryColorTwo = sweaterWool.getItemSecondaryColor(stack, 2);
            String dyeTypeOne = sweaterWool.getItemDyeType(stack, 1);
            String dyeTypeTwo = sweaterWool.getItemDyeType(stack, 2);
            String stensilType = sweaterWool.getStensilType(stack);
            int lightValueOne = sweaterWool.getItemLightValue(stack, 1);
            int lightValueTwo = sweaterWool.getItemLightValue(stack, 2);

            shirtModelRenderer.renderModel(
                    buffer,
                    model2,
                    new ShirtRenderingData(
                            stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getBoolean("is_open"),
                            primaryColorOne,
                            secondaryColorOne,
                            primaryColorTwo,
                            secondaryColorTwo,
                            dyeTypeOne,
                            dyeTypeTwo,
                            stensilType,
                            lightValueOne,
                            lightValueTwo,
                            "wool"
                    ),
                    mc.player,
                    36,
                    36,
                    36,
                    0,
                    0,
                    0,
                    this.leftPos + 88,
                    this.topPos + 30,
                    50,
                    0,
                    modelYaw,
                    0,
                    poseStack,
                    LightTexture.FULL_BRIGHT
            );

            buffer.endBatch();
        } else if (stack.getItem() instanceof HandWarmersCotton handWarmersCotton) {
            Minecraft mc = Minecraft.getInstance();

            MultiBufferSource.BufferSource buffer = mc.renderBuffers().bufferSource();
            int ticks = (int) mc.level.getGameTime();
            int primaryColorLeftOne = handWarmersCotton.getItemMainColor(stack, "left", 1);
            int secondaryColorLeftOne = handWarmersCotton.getItemSecondaryColor(stack, "left", 1);
            int primaryColorRightOne = handWarmersCotton.getItemMainColor(stack, "right", 1);
            int secondaryColorRightOne = handWarmersCotton.getItemSecondaryColor(stack, "right", 1);
            int primaryColorLeftTwo = handWarmersCotton.getItemMainColor(stack, "left", 2);
            int secondaryColorLeftTwo = handWarmersCotton.getItemSecondaryColor(stack, "left", 2);
            int primaryColorRightTwo = handWarmersCotton.getItemMainColor(stack, "right", 2);
            int secondaryColorRightTwo = handWarmersCotton.getItemSecondaryColor(stack, "right", 2);
            String dyeTypeLeftOne = handWarmersCotton.getItemDyeType(stack, "left", 1);
            String dyeTypeRightOne = handWarmersCotton.getItemDyeType(stack, "right", 1);
            String dyeTypeLeftTwo = handWarmersCotton.getItemDyeType(stack, "left", 2);
            String dyeTypeRightTwo = handWarmersCotton.getItemDyeType(stack, "right", 2);
            String stensilTypeLeft = handWarmersCotton.getStensilType(stack, "left");
            String stensilTypeRight = handWarmersCotton.getStensilType(stack, "right");
            int lightValueLeftOne = handWarmersCotton.getItemLightValue(stack, "left", 1);
            int lightValueLeftTwo = handWarmersCotton.getItemLightValue(stack, "left", 2);
            int lightValueRightOne = handWarmersCotton.getItemLightValue(stack, "right", 1);
            int lightValueRightTwo = handWarmersCotton.getItemLightValue(stack, "right", 2);

            handWarmersSpecialModelRenderer.renderModel(
                    buffer,
                    model,
                    new ThighHighsRenderingData(
                            primaryColorLeftOne,
                            secondaryColorLeftOne,
                            primaryColorRightOne,
                            secondaryColorRightOne,
                            primaryColorLeftTwo,
                            secondaryColorLeftTwo,
                            primaryColorRightTwo,
                            secondaryColorRightTwo,
                            dyeTypeLeftOne,
                            dyeTypeRightOne,
                            dyeTypeLeftTwo,
                            dyeTypeRightTwo,
                            stensilTypeLeft,
                            stensilTypeRight,
                            lightValueLeftOne,
                            lightValueLeftTwo,
                            lightValueRightOne,
                            lightValueRightTwo,
                            "cotton"
                    ),
                    mc.player,
                    48,
                    48,
                    48,
                    0,
                    0,
                    0,
                    this.leftPos + 87,
                    this.topPos,
                    50,
                    0,
                    modelYaw,
                    0,
                    poseStack,
                    LightTexture.FULL_BRIGHT
            );

            buffer.endBatch();
        } else if (stack.getItem() instanceof HandWarmersSilk handWarmersSilk) {
            Minecraft mc = Minecraft.getInstance();

            MultiBufferSource.BufferSource buffer = mc.renderBuffers().bufferSource();
            int ticks = (int) mc.level.getGameTime();
            int primaryColorLeftOne = handWarmersSilk.getItemMainColor(stack, "left", 1);
            int secondaryColorLeftOne = handWarmersSilk.getItemSecondaryColor(stack, "left", 1);
            int primaryColorRightOne = handWarmersSilk.getItemMainColor(stack, "right", 1);
            int secondaryColorRightOne = handWarmersSilk.getItemSecondaryColor(stack, "right", 1);
            int primaryColorLeftTwo = handWarmersSilk.getItemMainColor(stack, "left", 2);
            int secondaryColorLeftTwo = handWarmersSilk.getItemSecondaryColor(stack, "left", 2);
            int primaryColorRightTwo = handWarmersSilk.getItemMainColor(stack, "right", 2);
            int secondaryColorRightTwo = handWarmersSilk.getItemSecondaryColor(stack, "right", 2);
            String dyeTypeLeftOne = handWarmersSilk.getItemDyeType(stack, "left", 1);
            String dyeTypeRightOne = handWarmersSilk.getItemDyeType(stack, "right", 1);
            String dyeTypeLeftTwo = handWarmersSilk.getItemDyeType(stack, "left", 2);
            String dyeTypeRightTwo = handWarmersSilk.getItemDyeType(stack, "right", 2);
            String stensilTypeLeft = handWarmersSilk.getStensilType(stack, "left");
            String stensilTypeRight = handWarmersSilk.getStensilType(stack, "right");
            int lightValueLeftOne = handWarmersSilk.getItemLightValue(stack, "left", 1);
            int lightValueLeftTwo = handWarmersSilk.getItemLightValue(stack, "left", 2);
            int lightValueRightOne = handWarmersSilk.getItemLightValue(stack, "right", 1);
            int lightValueRightTwo = handWarmersSilk.getItemLightValue(stack, "right", 2);

            handWarmersSpecialModelRenderer.renderModel(
                    buffer,
                    model,
                    new ThighHighsRenderingData(
                            primaryColorLeftOne,
                            secondaryColorLeftOne,
                            primaryColorRightOne,
                            secondaryColorRightOne,
                            primaryColorLeftTwo,
                            secondaryColorLeftTwo,
                            primaryColorRightTwo,
                            secondaryColorRightTwo,
                            dyeTypeLeftOne,
                            dyeTypeRightOne,
                            dyeTypeLeftTwo,
                            dyeTypeRightTwo,
                            stensilTypeLeft,
                            stensilTypeRight,
                            lightValueLeftOne,
                            lightValueLeftTwo,
                            lightValueRightOne,
                            lightValueRightTwo,
                            "silk"
                    ),
                    mc.player,
                    48,
                    48,
                    48,
                    0,
                    0,
                    0,
                    this.leftPos + 87,
                    this.topPos,
                    50,
                    0,
                    modelYaw,
                    0,
                    poseStack,
                    LightTexture.FULL_BRIGHT
            );

            buffer.endBatch();
        } else if (stack.getItem() instanceof HandWarmersWool handWarmersWool) {
            Minecraft mc = Minecraft.getInstance();

            MultiBufferSource.BufferSource buffer = mc.renderBuffers().bufferSource();
            int ticks = (int) mc.level.getGameTime();
            int primaryColorLeftOne = handWarmersWool.getItemMainColor(stack, "left", 1);
            int secondaryColorLeftOne = handWarmersWool.getItemSecondaryColor(stack, "left", 1);
            int primaryColorRightOne = handWarmersWool.getItemMainColor(stack, "right", 1);
            int secondaryColorRightOne = handWarmersWool.getItemSecondaryColor(stack, "right", 1);
            int primaryColorLeftTwo = handWarmersWool.getItemMainColor(stack, "left", 2);
            int secondaryColorLeftTwo = handWarmersWool.getItemSecondaryColor(stack, "left", 2);
            int primaryColorRightTwo = handWarmersWool.getItemMainColor(stack, "right", 2);
            int secondaryColorRightTwo = handWarmersWool.getItemSecondaryColor(stack, "right", 2);
            String dyeTypeLeftOne = handWarmersWool.getItemDyeType(stack, "left", 1);
            String dyeTypeRightOne = handWarmersWool.getItemDyeType(stack, "right", 1);
            String dyeTypeLeftTwo = handWarmersWool.getItemDyeType(stack, "left", 2);
            String dyeTypeRightTwo = handWarmersWool.getItemDyeType(stack, "right", 2);
            String stensilTypeLeft = handWarmersWool.getStensilType(stack, "left");
            String stensilTypeRight = handWarmersWool.getStensilType(stack, "right");
            int lightValueLeftOne = handWarmersWool.getItemLightValue(stack, "left", 1);
            int lightValueLeftTwo = handWarmersWool.getItemLightValue(stack, "left", 2);
            int lightValueRightOne = handWarmersWool.getItemLightValue(stack, "right", 1);
            int lightValueRightTwo = handWarmersWool.getItemLightValue(stack, "right", 2);

            handWarmersSpecialModelRenderer.renderModel(
                    buffer,
                    model,
                    new ThighHighsRenderingData(
                            primaryColorLeftOne,
                            secondaryColorLeftOne,
                            primaryColorRightOne,
                            secondaryColorRightOne,
                            primaryColorLeftTwo,
                            secondaryColorLeftTwo,
                            primaryColorRightTwo,
                            secondaryColorRightTwo,
                            dyeTypeLeftOne,
                            dyeTypeRightOne,
                            dyeTypeLeftTwo,
                            dyeTypeRightTwo,
                            stensilTypeLeft,
                            stensilTypeRight,
                            lightValueLeftOne,
                            lightValueLeftTwo,
                            lightValueRightOne,
                            lightValueRightTwo,
                            "wool"
                    ),
                    mc.player,
                    48,
                    48,
                    48,
                    0,
                    0,
                    0,
                    this.leftPos + 87,
                    this.topPos,
                    50,
                    0,
                    modelYaw,
                    0,
                    poseStack,
                    LightTexture.FULL_BRIGHT
            );

            buffer.endBatch();
        } else if (stack.getItem() instanceof ChokerItem chokerItem) {
            Minecraft mc = Minecraft.getInstance();

            MultiBufferSource.BufferSource buffer = mc.renderBuffers().bufferSource();
            int ticks = (int) mc.level.getGameTime();
            int primaryColorLeftOne = chokerItem.getItemMainColor(stack, "left", 1);
            int secondaryColorLeftOne = chokerItem.getItemSecondaryColor(stack, "left", 1);
            int primaryColorRightOne = chokerItem.getItemMainColor(stack, "right", 1);
            int secondaryColorRightOne = chokerItem.getItemSecondaryColor(stack, "right", 1);
            int primaryColorLeftTwo = chokerItem.getItemMainColor(stack, "left", 2);
            int secondaryColorLeftTwo = chokerItem.getItemSecondaryColor(stack, "left", 2);
            int primaryColorRightTwo = chokerItem.getItemMainColor(stack, "right", 2);
            int secondaryColorRightTwo = chokerItem.getItemSecondaryColor(stack, "right", 2);
            String dyeTypeLeftOne = chokerItem.getItemDyeType(stack, "left", 1);
            String dyeTypeRightOne = chokerItem.getItemDyeType(stack, "right", 1);
            String dyeTypeLeftTwo = chokerItem.getItemDyeType(stack, "left", 2);
            String dyeTypeRightTwo = chokerItem.getItemDyeType(stack, "right", 2);
            String stensilTypeLeft = chokerItem.getStensilType(stack, "left");
            String stensilTypeRight = chokerItem.getStensilType(stack, "right");
            int lightValueLeftOne = chokerItem.getItemLightValue(stack, "left", 1);
            int lightValueLeftTwo = chokerItem.getItemLightValue(stack, "left", 2);
            int lightValueRightOne = chokerItem.getItemLightValue(stack, "right", 1);
            int lightValueRightTwo = chokerItem.getItemLightValue(stack, "right", 2);

            chokerModelRenderer.renderModel(
                    buffer,
                    model3,
                    new ChokerRenderingData(
                            primaryColorLeftOne,
                            secondaryColorLeftOne,
                            primaryColorRightOne,
                            secondaryColorRightOne,
                            primaryColorLeftTwo,
                            secondaryColorLeftTwo,
                            primaryColorRightTwo,
                            secondaryColorRightTwo,
                            dyeTypeLeftOne,
                            dyeTypeRightOne,
                            dyeTypeLeftTwo,
                            dyeTypeRightTwo,
                            stensilTypeLeft,
                            stensilTypeRight,
                            lightValueLeftOne,
                            lightValueLeftTwo,
                            lightValueRightOne,
                            lightValueRightTwo,
                            "base"
                    ),
                    mc.player,
                    48,
                    48,
                    48,
                    0,
                    0,
                    0,
                    this.leftPos + 87,
                    this.topPos + 30,
                    50,
                    0,
                    modelYaw,
                    0,
                    poseStack,
                    LightTexture.FULL_BRIGHT
            );

            buffer.endBatch();
        } else if (stack.getItem() instanceof PantsJeans pantsJeans) {
            Minecraft mc = Minecraft.getInstance();

            MultiBufferSource.BufferSource buffer = mc.renderBuffers().bufferSource();
            int ticks = (int)mc.level.getGameTime();
            int primaryColorOne = pantsJeans.getItemMainColor(stack, 1);
            int secondaryColorOne = pantsJeans.getItemSecondaryColor(stack, 1);
            int primaryColorTwo = pantsJeans.getItemMainColor(stack, 2);
            int secondaryColorTwo = pantsJeans.getItemSecondaryColor(stack, 2);
            String dyeTypeOne = pantsJeans.getItemDyeType(stack, 1);
            String dyeTypeTwo = pantsJeans.getItemDyeType(stack, 2);
            String stensilType = pantsJeans.getStensilType(stack);
            int lightValueOne = pantsJeans.getItemLightValue(stack, 1);
            int lightValueTwo = pantsJeans.getItemLightValue(stack, 2);

            pantsModelRenderer.renderModel(
                    buffer,
                    model4,
                    new PantsRenderingData(
                            primaryColorOne,
                            secondaryColorOne,
                            primaryColorTwo,
                            secondaryColorTwo,
                            dyeTypeOne,
                            dyeTypeTwo,
                            stensilType,
                            lightValueOne,
                            lightValueTwo,
                            "jeans"
                    ),
                    mc.player,
                    36,
                    36,
                    36,
                    0,
                    0,
                    0,
                    this.leftPos + 88,
                    this.topPos + 20,
                    50,
                    0,
                    modelYaw,
                    0,
                    poseStack,
                    LightTexture.FULL_BRIGHT
            );

            buffer.endBatch();
        } else if (stack.getItem() instanceof PantsCotton pantsCotton) {
            Minecraft mc = Minecraft.getInstance();

            MultiBufferSource.BufferSource buffer = mc.renderBuffers().bufferSource();
            int ticks = (int)mc.level.getGameTime();
            int primaryColorOne = pantsCotton.getItemMainColor(stack, 1);
            int secondaryColorOne = pantsCotton.getItemSecondaryColor(stack, 1);
            int primaryColorTwo = pantsCotton.getItemMainColor(stack, 2);
            int secondaryColorTwo = pantsCotton.getItemSecondaryColor(stack, 2);
            String dyeTypeOne = pantsCotton.getItemDyeType(stack, 1);
            String dyeTypeTwo = pantsCotton.getItemDyeType(stack, 2);
            String stensilType = pantsCotton.getStensilType(stack);
            int lightValueOne = pantsCotton.getItemLightValue(stack, 1);
            int lightValueTwo = pantsCotton.getItemLightValue(stack, 2);

            pantsModelRenderer.renderModel(
                    buffer,
                    model4,
                    new PantsRenderingData(
                            primaryColorOne,
                            secondaryColorOne,
                            primaryColorTwo,
                            secondaryColorTwo,
                            dyeTypeOne,
                            dyeTypeTwo,
                            stensilType,
                            lightValueOne,
                            lightValueTwo,
                            "cotton"
                    ),
                    mc.player,
                    36,
                    36,
                    36,
                    0,
                    0,
                    0,
                    this.leftPos + 88,
                    this.topPos + 20,
                    50,
                    0,
                    modelYaw,
                    0,
                    poseStack,
                    LightTexture.FULL_BRIGHT
            );

            buffer.endBatch();
        } else if (stack.getItem() instanceof PantsSilk pantsSilk) {
            Minecraft mc = Minecraft.getInstance();

            MultiBufferSource.BufferSource buffer = mc.renderBuffers().bufferSource();
            int ticks = (int)mc.level.getGameTime();
            int primaryColorOne = pantsSilk.getItemMainColor(stack, 1);
            int secondaryColorOne = pantsSilk.getItemSecondaryColor(stack, 1);
            int primaryColorTwo = pantsSilk.getItemMainColor(stack, 2);
            int secondaryColorTwo = pantsSilk.getItemSecondaryColor(stack, 2);
            String dyeTypeOne = pantsSilk.getItemDyeType(stack, 1);
            String dyeTypeTwo = pantsSilk.getItemDyeType(stack, 2);
            String stensilType = pantsSilk.getStensilType(stack);
            int lightValueOne = pantsSilk.getItemLightValue(stack, 1);
            int lightValueTwo = pantsSilk.getItemLightValue(stack, 2);

            pantsModelRenderer.renderModel(
                    buffer,
                    model4,
                    new PantsRenderingData(
                            primaryColorOne,
                            secondaryColorOne,
                            primaryColorTwo,
                            secondaryColorTwo,
                            dyeTypeOne,
                            dyeTypeTwo,
                            stensilType,
                            lightValueOne,
                            lightValueTwo,
                            "silk"
                    ),
                    mc.player,
                    36,
                    36,
                    36,
                    0,
                    0,
                    0,
                    this.leftPos + 88,
                    this.topPos + 20,
                    50,
                    0,
                    modelYaw,
                    0,
                    poseStack,
                    LightTexture.FULL_BRIGHT
            );

            buffer.endBatch();
        }
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int gx, int gy) {
        RenderSystem.setShaderColor(1, 1, 1, 1);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();

        guiGraphics.blit(ResourceLocation.parse("weaversparadise:textures/screens/dyeing_screen.png"), this.leftPos + -1, this.topPos + -7, 0, 0, 178, 227, 178, 227);

        NonNullList<Slot> slots = this.menu.slots;

        if (slots.get(0).getItem().is(WeaversParadiseItems.SHIRT_COTTON)) {
            guiGraphics.blit(
                    ResourceLocation.parse("weaversparadise:textures/screens/nope.png"),
                    this.leftPos + 126,
                    this.topPos + 87,
                    0,
                    0,
                    16,
                    16,
                    16,
                    16
            );

            guiGraphics.blit(
                    ResourceLocation.parse("weaversparadise:textures/screens/nope.png"),
                    this.leftPos + 148,
                    this.topPos + 87,
                    0,
                    0,
                    16,
                    16,
                    16,
                    16
            );

            guiGraphics.blit(
                    ResourceLocation.parse("weaversparadise:textures/screens/nope.png"),
                    this.leftPos + 137,
                    this.topPos + 66,
                    0,
                    0,
                    16,
                    16,
                    16,
                    16
            );
        }

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
    public void containerTick() {
        stack = menu.slots.get(1).getItem();
    }

    @Override
    public void init() {
        super.init();
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double dragX, double dragY) {
        if (button == 0) {
            if (lastMouseX != -1) {
                double dx = mouseX - lastMouseX;
                modelYaw += dx * 0.8f;
            }
            lastMouseX = (int) mouseX;
            return true;
        }

        return super.mouseDragged(mouseX, mouseY, button, dragX, dragY);
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        if (button == 0) lastMouseX = -1;
        return super.mouseReleased(mouseX, mouseY, button);
    }

    public int getRainbowColor(int ticks) {
        float speed = 0.05F;

        float red = Mth.clamp((float)(Math.sin(ticks * speed) * 0.5 + 0.5), 0, 1);
        float green = Mth.clamp((float)(Math.sin(ticks * speed + 2 * Math.PI / 3) * 0.5 + 0.5), 0, 1);
        float blue = Mth.clamp((float)(Math.sin(ticks * speed + 4 * Math.PI / 3) * 0.5 + 0.5), 0, 1);

        int truered = (int)(red * 255);
        int truegreen = (int)(green * 255);
        int trueblue = (int)(blue * 255);

        return 255 << 24 | truered << 16 | truegreen << 8 | trueblue;
    }
}
