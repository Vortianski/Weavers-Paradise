package xox.labvorty.weaversparadise.gui.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.joml.Vector4f;
import oshi.util.tuples.Pair;
import xox.labvorty.weaversparadise.data.texture.CapeTextures;
import xox.labvorty.weaversparadise.gui.menu.DyeingMenu;
import xox.labvorty.weaversparadise.init.WeaversParadiseItems;
import xox.labvorty.weaversparadise.items.clothing.*;
import xox.labvorty.weaversparadise.renderers.models.*;
import xox.labvorty.weaversparadise.utils.PlayerModelInterface;
import xox.labvorty.weaversparadise.models.ChokerModel;
import xox.labvorty.weaversparadise.models.PantsModel;
import xox.labvorty.weaversparadise.models.ThighHighsModel;
import xox.labvorty.weaversparadise.models.UpperWearModel;
import xox.labvorty.weaversparadise.renderers.helpers.*;

import java.util.HashMap;

@OnlyIn(Dist.CLIENT)
public class DyeingScreen extends AbstractContainerScreen<DyeingMenu> {
    private final static HashMap<String, Object> guistate = DyeingMenu.guistate;
    private final Level world;
    private final int x, y, z;
    private final Player entity;
    private ItemStack stack = ItemStack.EMPTY;
    private float modelYaw = 180f;
    private int lastMouseX = -1;
    private static final ThighHighsModel model = new ThighHighsModel(Minecraft.getInstance().getEntityModels().bakeLayer(ThighHighsModel.LAYER_LOCATION));
    private static final ThighHighsModel model1 = new ThighHighsModel(Minecraft.getInstance().getEntityModels().bakeLayer(ThighHighsModel.LAYER_LOCATION));
    private static final UpperWearModel model2 = new UpperWearModel(Minecraft.getInstance().getEntityModels().bakeLayer(UpperWearModel.LAYER_LOCATION));
    private static final ChokerModel model3 = new ChokerModel(Minecraft.getInstance().getEntityModels().bakeLayer(ChokerModel.LAYER_LOCATION));
    private static final PantsModel model4 = new PantsModel(Minecraft.getInstance().getEntityModels().bakeLayer(PantsModel.LAYER_LOCATION));
    private static final PlayerModel model5 = new PlayerModel(Minecraft.getInstance().getEntityModels().bakeLayer(ModelLayers.PLAYER), false);

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
        this.renderBackground(guiGraphics);
        super.render(guiGraphics, mouseX, mouseY, partialTicks);
        this.renderTooltip(guiGraphics, mouseX, mouseY);

        PoseStack poseStack = guiGraphics.pose();

        ThighHighsModelRenderer thighHighModelRenderer = new ThighHighsModelRenderer();
        HandWarmersSpecialModelRenderer handWarmersSpecialModelRenderer = new HandWarmersSpecialModelRenderer();
        ShirtModelRenderer shirtModelRenderer = new ShirtModelRenderer();
        ChokerModelRenderer chokerModelRenderer = new ChokerModelRenderer();
        PantsModelRenderer pantsModelRenderer = new PantsModelRenderer();

        if (stack.getItem() instanceof ThighHighsCottonItem cottonThighHighs) {
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
        } else if (stack.getItem() instanceof ThighHighsSilkItem silkThighHighs) {
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
        } else if (stack.getItem() instanceof ThighHighsWoolItem woolThighHighs) {
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
        } else if (stack.getItem() instanceof ShirtCottonItem shirtCotton) {
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
                            stack.getOrCreateTag().getBoolean("is_open"),
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
        } else if (stack.getItem() instanceof ShirtSilkItem shirtSilk) {
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
                            stack.getOrCreateTag().getBoolean("is_open"),
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
        } else if (stack.getItem() instanceof SweaterWoolItem sweaterWool) {
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
                            stack.getOrCreateTag().getBoolean("is_open"),
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
        } else if (stack.getItem() instanceof HandWarmersCottonItem handWarmersCottonItem) {
            Minecraft mc = Minecraft.getInstance();

            MultiBufferSource.BufferSource buffer = mc.renderBuffers().bufferSource();
            int ticks = (int) mc.level.getGameTime();
            int primaryColorLeftOne = handWarmersCottonItem.getItemMainColor(stack, "left", 1);
            int secondaryColorLeftOne = handWarmersCottonItem.getItemSecondaryColor(stack, "left", 1);
            int primaryColorRightOne = handWarmersCottonItem.getItemMainColor(stack, "right", 1);
            int secondaryColorRightOne = handWarmersCottonItem.getItemSecondaryColor(stack, "right", 1);
            int primaryColorLeftTwo = handWarmersCottonItem.getItemMainColor(stack, "left", 2);
            int secondaryColorLeftTwo = handWarmersCottonItem.getItemSecondaryColor(stack, "left", 2);
            int primaryColorRightTwo = handWarmersCottonItem.getItemMainColor(stack, "right", 2);
            int secondaryColorRightTwo = handWarmersCottonItem.getItemSecondaryColor(stack, "right", 2);
            String dyeTypeLeftOne = handWarmersCottonItem.getItemDyeType(stack, "left", 1);
            String dyeTypeRightOne = handWarmersCottonItem.getItemDyeType(stack, "right", 1);
            String dyeTypeLeftTwo = handWarmersCottonItem.getItemDyeType(stack, "left", 2);
            String dyeTypeRightTwo = handWarmersCottonItem.getItemDyeType(stack, "right", 2);
            String stensilTypeLeft = handWarmersCottonItem.getStensilType(stack, "left");
            String stensilTypeRight = handWarmersCottonItem.getStensilType(stack, "right");
            int lightValueLeftOne = handWarmersCottonItem.getItemLightValue(stack, "left", 1);
            int lightValueLeftTwo = handWarmersCottonItem.getItemLightValue(stack, "left", 2);
            int lightValueRightOne = handWarmersCottonItem.getItemLightValue(stack, "right", 1);
            int lightValueRightTwo = handWarmersCottonItem.getItemLightValue(stack, "right", 2);

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
        } else if (stack.getItem() instanceof HandWarmersSilkItem handWarmersSilk) {
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
        } else if (stack.getItem() instanceof HandWarmersWoolItem handWarmersWool) {
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
        } else if (stack.getItem() instanceof PantsJeansItem pantsJeans) {
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
        } else if (stack.getItem() instanceof PantsCottonItem pantsCotton) {
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
        } else if (stack.getItem() instanceof PantsSilkItem pantsSilk) {
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
        } else if (stack.getItem() instanceof CapeCottonItem capeCottonItem) {
            Minecraft mc = Minecraft.getInstance();

            MultiBufferSource.BufferSource buffer = mc.renderBuffers().bufferSource();
            int ticks = (int)mc.level.getGameTime();
            int primaryColorOne = capeCottonItem.getItemMainColor(stack, 1);
            int secondaryColorOne = capeCottonItem.getItemSecondaryColor(stack, 1);
            int primaryColorTwo = capeCottonItem.getItemMainColor(stack, 2);
            int secondaryColorTwo = capeCottonItem.getItemSecondaryColor(stack, 2);
            String dyeTypeOne = capeCottonItem.getItemDyeType(stack, 1);
            String dyeTypeTwo = capeCottonItem.getItemDyeType(stack, 2);
            String stensilType = capeCottonItem.getStensilType(stack);
            int lightValueOne = capeCottonItem.getItemLightValue(stack, 1);
            int lightValueTwo = capeCottonItem.getItemLightValue(stack, 2);

            RenderingUtils renderingUtils = new RenderingUtils();

            CapeTextures capeTextures = CapeTextures.getByTypeAndMaterial(stensilType, "cotton");

            Pair<Integer, Integer> col1 = ColorHandlers.handle(dyeTypeOne, primaryColorOne, secondaryColorOne, lightValueOne, minecraft.player, LightTexture.FULL_BRIGHT, (int)minecraft.level.getGameTime());
            Pair<Integer, Integer> col2 = ColorHandlers.handle(dyeTypeTwo, primaryColorTwo, secondaryColorTwo, lightValueTwo, minecraft.player, LightTexture.FULL_BRIGHT, (int)minecraft.level.getGameTime());
            Vector4f finalColorOne = colorIntToVector4f(col1.getA());
            Vector4f finalColorTwo = colorIntToVector4f(col2.getA());
            int finalLightOne = col1.getB();
            int finalLightTwo = col2.getB();

            poseStack.pushPose();

            poseStack.translate(this.leftPos + 88, this.topPos + 30, 50);
            poseStack.scale(36, 36, 36);
            poseStack.mulPose(Axis.YP.rotationDegrees(modelYaw));

            VertexConsumer vertexConsumer1 = renderingUtils.parseVC(buffer, dyeTypeOne, capeTextures.getTextureOne(), "cape");
            ((PlayerModelInterface)model5).getCloak().render(poseStack, vertexConsumer1, finalLightOne, OverlayTexture.NO_OVERLAY, finalColorOne.x, finalColorOne.y, finalColorOne.z, finalColorOne.w);

            if (capeTextures.getRenderType().equals("double")) {
                VertexConsumer vertexConsumer2 = renderingUtils.parseVC(buffer, dyeTypeTwo, capeTextures.getTextureTwo(),"cape");
                ((PlayerModelInterface)model5).getCloak().render(poseStack, vertexConsumer2, finalLightTwo, OverlayTexture.NO_OVERLAY, finalColorTwo.x, finalColorTwo.y, finalColorTwo.z, finalColorTwo.w);
            }

            poseStack.popPose();

            buffer.endBatch();
        } else if (stack.getItem() instanceof CapeSilkItem capeSilkItem) {
            Minecraft mc = Minecraft.getInstance();

            MultiBufferSource.BufferSource buffer = mc.renderBuffers().bufferSource();
            int ticks = (int)mc.level.getGameTime();
            int primaryColorOne = capeSilkItem.getItemMainColor(stack, 1);
            int secondaryColorOne = capeSilkItem.getItemSecondaryColor(stack, 1);
            int primaryColorTwo = capeSilkItem.getItemMainColor(stack, 2);
            int secondaryColorTwo = capeSilkItem.getItemSecondaryColor(stack, 2);
            String dyeTypeOne = capeSilkItem.getItemDyeType(stack, 1);
            String dyeTypeTwo = capeSilkItem.getItemDyeType(stack, 2);
            String stensilType = capeSilkItem.getStensilType(stack);
            int lightValueOne = capeSilkItem.getItemLightValue(stack, 1);
            int lightValueTwo = capeSilkItem.getItemLightValue(stack, 2);

            RenderingUtils renderingUtils = new RenderingUtils();

            CapeTextures capeTextures = CapeTextures.getByTypeAndMaterial(stensilType, "silk");

            Pair<Integer, Integer> col1 = ColorHandlers.handle(dyeTypeOne, primaryColorOne, secondaryColorOne, lightValueOne, minecraft.player, LightTexture.FULL_BRIGHT, (int)minecraft.level.getGameTime());
            Pair<Integer, Integer> col2 = ColorHandlers.handle(dyeTypeTwo, primaryColorTwo, secondaryColorTwo, lightValueTwo, minecraft.player, LightTexture.FULL_BRIGHT, (int)minecraft.level.getGameTime());
            Vector4f finalColorOne = colorIntToVector4f(col1.getA());
            Vector4f finalColorTwo = colorIntToVector4f(col2.getA());
            int finalLightOne = col1.getB();
            int finalLightTwo = col2.getB();

            poseStack.pushPose();

            poseStack.translate(this.leftPos + 88, this.topPos + 30, 50);
            poseStack.scale(36, 36, 36);
            poseStack.mulPose(Axis.YP.rotationDegrees(modelYaw));

            VertexConsumer vertexConsumer1 = renderingUtils.parseVC(buffer, dyeTypeOne, capeTextures.getTextureOne(), "cape");
            ((PlayerModelInterface)model5).getCloak().render(poseStack, vertexConsumer1, finalLightOne, OverlayTexture.NO_OVERLAY, finalColorOne.x, finalColorOne.y, finalColorOne.z, finalColorOne.w);

            if (capeTextures.getRenderType().equals("double")) {
                VertexConsumer vertexConsumer2 = renderingUtils.parseVC(buffer, dyeTypeTwo, capeTextures.getTextureTwo(),"cape");
                ((PlayerModelInterface)model5).getCloak().render(poseStack, vertexConsumer2, finalLightTwo, OverlayTexture.NO_OVERLAY, finalColorTwo.x, finalColorTwo.y, finalColorTwo.z, finalColorTwo.w);
            }

            poseStack.popPose();

            buffer.endBatch();
        } else if (stack.getItem() instanceof CapeWoolItem capeWoolItem) {
            Minecraft mc = Minecraft.getInstance();

            MultiBufferSource.BufferSource buffer = mc.renderBuffers().bufferSource();
            int ticks = (int)mc.level.getGameTime();
            int primaryColorOne = capeWoolItem.getItemMainColor(stack, 1);
            int secondaryColorOne = capeWoolItem.getItemSecondaryColor(stack, 1);
            int primaryColorTwo = capeWoolItem.getItemMainColor(stack, 2);
            int secondaryColorTwo = capeWoolItem.getItemSecondaryColor(stack, 2);
            String dyeTypeOne = capeWoolItem.getItemDyeType(stack, 1);
            String dyeTypeTwo = capeWoolItem.getItemDyeType(stack, 2);
            String stensilType = capeWoolItem.getStensilType(stack);
            int lightValueOne = capeWoolItem.getItemLightValue(stack, 1);
            int lightValueTwo = capeWoolItem.getItemLightValue(stack, 2);

            RenderingUtils renderingUtils = new RenderingUtils();

            CapeTextures capeTextures = CapeTextures.getByTypeAndMaterial(stensilType, "wool");

            Pair<Integer, Integer> col1 = ColorHandlers.handle(dyeTypeOne, primaryColorOne, secondaryColorOne, lightValueOne, minecraft.player, LightTexture.FULL_BRIGHT, (int)minecraft.level.getGameTime());
            Pair<Integer, Integer> col2 = ColorHandlers.handle(dyeTypeTwo, primaryColorTwo, secondaryColorTwo, lightValueTwo, minecraft.player, LightTexture.FULL_BRIGHT, (int)minecraft.level.getGameTime());
            Vector4f finalColorOne = colorIntToVector4f(col1.getA());
            Vector4f finalColorTwo = colorIntToVector4f(col2.getA());
            int finalLightOne = col1.getB();
            int finalLightTwo = col2.getB();

            poseStack.pushPose();

            poseStack.translate(this.leftPos + 88, this.topPos + 30, 50);
            poseStack.scale(36, 36, 36);
            poseStack.mulPose(Axis.YP.rotationDegrees(modelYaw));

            VertexConsumer vertexConsumer1 = renderingUtils.parseVC(buffer, dyeTypeOne, capeTextures.getTextureOne(), "cape");
            ((PlayerModelInterface)model5).getCloak().render(poseStack, vertexConsumer1, finalLightOne, OverlayTexture.NO_OVERLAY, finalColorOne.x, finalColorOne.y, finalColorOne.z, finalColorOne.w);

            if (capeTextures.getRenderType().equals("double")) {
                VertexConsumer vertexConsumer2 = renderingUtils.parseVC(buffer, dyeTypeTwo, capeTextures.getTextureTwo(),"cape");
                ((PlayerModelInterface)model5).getCloak().render(poseStack, vertexConsumer2, finalLightTwo, OverlayTexture.NO_OVERLAY, finalColorTwo.x, finalColorTwo.y, finalColorTwo.z, finalColorTwo.w);
            }

            poseStack.popPose();

            buffer.endBatch();
        }
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int gx, int gy) {
        RenderSystem.setShaderColor(1, 1, 1, 1);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();

        guiGraphics.blit(new ResourceLocation("weaversparadise:textures/screens/dyeing_screen.png"), this.leftPos + -1, this.topPos + -7, 0, 0, 178, 227, 178, 227);

        NonNullList<Slot> slots = this.menu.slots;

        if (
                slots.get(0).getItem().is(WeaversParadiseItems.SHIRT_COTTON.get()) ||
                        slots.get(0).getItem().is(WeaversParadiseItems.SHIRT_SILK.get()) ||
                        slots.get(0).getItem().is(WeaversParadiseItems.SWEATER_WOOL.get()) ||
                        slots.get(0).getItem().is(WeaversParadiseItems.PANTS_JEANS.get()) ||
                        slots.get(0).getItem().is(WeaversParadiseItems.PANTS_COTTON.get()) ||
                        slots.get(0).getItem().is(WeaversParadiseItems.PANTS_SILK.get()) ||
                        slots.get(0).getItem().is(WeaversParadiseItems.COTTON_CAPE.get()) ||
                        slots.get(0).getItem().is(WeaversParadiseItems.SILK_CAPE.get()) ||
                        slots.get(0).getItem().is(WeaversParadiseItems.WOOL_CAPE.get())
        ) {
            guiGraphics.blit(
                    new ResourceLocation("weaversparadise:textures/screens/nope.png"),
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
                    new ResourceLocation("weaversparadise:textures/screens/nope.png"),
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
                    new ResourceLocation("weaversparadise:textures/screens/nope.png"),
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

    private static Vector4f colorIntToVector4f(int color) {
        float a = ((color >> 24) & 0xFF) / 255.0f;
        float r = ((color >> 16) & 0xFF) / 255.0f;
        float g = ((color >> 8) & 0xFF) / 255.0f;
        float b = (color & 0xFF) / 255.0f;
        return new Vector4f(r, g, b, a);
    }
}
