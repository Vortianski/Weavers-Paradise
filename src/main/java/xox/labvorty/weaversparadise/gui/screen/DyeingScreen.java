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
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.joml.Vector4f;
import oshi.util.tuples.Pair;
import xox.labvorty.weaversparadise.data.texture.ItemTexture;
import xox.labvorty.weaversparadise.data.texture.TextureRegistry;
import xox.labvorty.weaversparadise.gui.menu.DyeingMenu;
import xox.labvorty.weaversparadise.init.WeaversParadiseItems;
import xox.labvorty.weaversparadise.items.clothing.*;
import xox.labvorty.weaversparadise.items.clothing.defined.*;
import xox.labvorty.weaversparadise.items.misc.BlahajItem;
import xox.labvorty.weaversparadise.model.*;
import xox.labvorty.weaversparadise.renderers.helpers.ColorHandlers;
import xox.labvorty.weaversparadise.renderers.helpers.DoubleSidedClothingRenderingData;
import xox.labvorty.weaversparadise.renderers.helpers.RenderingUtils;
import xox.labvorty.weaversparadise.renderers.helpers.SingleSidedClothingRenderingData;
import xox.labvorty.weaversparadise.renderers.models.*;
import xox.labvorty.weaversparadise.utilities.PlayerModelInterface;

@OnlyIn(Dist.CLIENT)
public class DyeingScreen extends AbstractContainerScreen<DyeingMenu> {
    private final Player player;
    private ItemStack itemStack = ItemStack.EMPTY;
    private float modelYaw = 180f;
    private int lastMouseX = -1;
    private static final ThighHighsModel<?> thighHighsModel = new ThighHighsModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(ThighHighsModel.LAYER_LOCATION));
    private static final UpperWearModel<?> upperWearModel = new UpperWearModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(UpperWearModel.LAYER_LOCATION));
    private static final ChokerModel<?> chokerModel = new ChokerModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(ChokerModel.LAYER_LOCATION));
    private static final PantsModel<?> pantsModel = new PantsModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(PantsModel.LAYER_LOCATION));
    private static final PlayerModel<?> playerModel = new PlayerModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(ModelLayers.PLAYER), false);
    private static final CottonSkirtModel<?> cottonSkirtModel = new CottonSkirtModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(CottonSkirtModel.LAYER_LOCATION));
    private static final CapModel<?> capModel = new CapModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(CapModel.LAYER_LOCATION));
    private static final UshankaModel<?> ushankaModel = new UshankaModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(UshankaModel.LAYER_LOCATION));
    private static final PomponHatModel<?> pomponHatModel = new PomponHatModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(PomponHatModel.LAYER_LOCATION));
    private static final BlahajModel<?> blahajModel = new BlahajModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(BlahajModel.LAYER_LOCATION));

    public DyeingScreen(DyeingMenu container, Inventory inventory, Component text) {
        super(container, inventory, text);
        this.player = container.entity;
        this.imageWidth = 176;
        this.imageHeight = 220;
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(guiGraphics);
        super.render(guiGraphics, mouseX, mouseY, partialTicks);
        this.renderTooltip(guiGraphics, mouseX, mouseY);

        PoseStack poseStack = guiGraphics.pose();
        if (minecraft == null) return;
        MultiBufferSource.BufferSource multiBufferSource = minecraft.renderBuffers().bufferSource();

        if (itemStack.getItem() instanceof ThighHighsInterface thighHighsInterface) {
            String material;

            if (itemStack.getItem() instanceof ThighHighsCottonItem) {
                material = "cotton";
            } else if (itemStack.getItem() instanceof ThighHighsSilkItem) {
                material = "silk";
            } else if (itemStack.getItem() instanceof ThighHighsWoolItem) {
                material = "wool";
            } else {
                return;
            }

            ThighHighsModelRenderer.renderModel(
                    multiBufferSource,
                    thighHighsModel,
                    new DoubleSidedClothingRenderingData(
                            thighHighsInterface.getFlag(itemStack),
                            thighHighsInterface.getItemMainColor(itemStack, "left", 1),
                            thighHighsInterface.getItemSecondaryColor(itemStack, "left", 1),
                            thighHighsInterface.getItemMainColor(itemStack, "right", 1),
                            thighHighsInterface.getItemSecondaryColor(itemStack, "right", 1),
                            thighHighsInterface.getItemMainColor(itemStack, "left", 2),
                            thighHighsInterface.getItemSecondaryColor(itemStack, "left", 2),
                            thighHighsInterface.getItemMainColor(itemStack, "right", 2),
                            thighHighsInterface.getItemSecondaryColor(itemStack, "right", 2),
                            thighHighsInterface.getItemDyeType(itemStack, "left", 1),
                            thighHighsInterface.getItemDyeType(itemStack, "right", 1),
                            thighHighsInterface.getItemDyeType(itemStack, "left", 2),
                            thighHighsInterface.getItemDyeType(itemStack, "right", 2),
                            thighHighsInterface.getStensilType(itemStack, "left"),
                            thighHighsInterface.getStensilType(itemStack, "right"),
                            thighHighsInterface.getItemLightValue(itemStack, "left", 1),
                            thighHighsInterface.getItemLightValue(itemStack, "left", 2),
                            thighHighsInterface.getItemLightValue(itemStack, "right", 1),
                            thighHighsInterface.getItemLightValue(itemStack, "right", 2),
                            material,
                            itemStack.isEnchanted(),
                            thighHighsInterface.getGlintColor(itemStack),
                            thighHighsInterface.getAdditionalData(itemStack)
                    ),
                    player,
                    -48,
                    48,
                    48,
                    0,
                    0,
                    0,
                    this.leftPos + 87,
                    this.topPos,
                    50,
                    0,
                    -modelYaw,
                    0,
                    poseStack,
                    LightTexture.FULL_BRIGHT
            );
        } else if (itemStack.getItem() instanceof HandWarmersInterface handWarmersInterface) {
            String material;

            if (itemStack.getItem() instanceof HandWarmersCottonItem) {
                material = "cotton";
            } else if (itemStack.getItem() instanceof HandWarmersSilkItem) {
                material = "silk";
            } else if (itemStack.getItem() instanceof HandWarmersWoolItem) {
                material = "wool";
            } else {
                return;
            }

            HandWarmersGUIModelRenderer.renderModel(
                    multiBufferSource,
                    thighHighsModel,
                    new DoubleSidedClothingRenderingData(
                            handWarmersInterface.getFlag(itemStack),
                            handWarmersInterface.getItemMainColor(itemStack, "left", 1),
                            handWarmersInterface.getItemSecondaryColor(itemStack, "left", 1),
                            handWarmersInterface.getItemMainColor(itemStack, "right", 1),
                            handWarmersInterface.getItemSecondaryColor(itemStack, "right", 1),
                            handWarmersInterface.getItemMainColor(itemStack, "left", 2),
                            handWarmersInterface.getItemSecondaryColor(itemStack, "left", 2),
                            handWarmersInterface.getItemMainColor(itemStack, "right", 2),
                            handWarmersInterface.getItemSecondaryColor(itemStack, "right", 2),
                            handWarmersInterface.getItemDyeType(itemStack, "left", 1),
                            handWarmersInterface.getItemDyeType(itemStack, "right", 1),
                            handWarmersInterface.getItemDyeType(itemStack, "left", 2),
                            handWarmersInterface.getItemDyeType(itemStack, "right", 2),
                            handWarmersInterface.getStensilType(itemStack, "left"),
                            handWarmersInterface.getStensilType(itemStack, "right"),
                            handWarmersInterface.getItemLightValue(itemStack, "left", 1),
                            handWarmersInterface.getItemLightValue(itemStack, "left", 2),
                            handWarmersInterface.getItemLightValue(itemStack, "right", 1),
                            handWarmersInterface.getItemLightValue(itemStack, "right", 2),
                            material,
                            itemStack.isEnchanted(),
                            handWarmersInterface.getGlintColor(itemStack),
                            handWarmersInterface.getAdditionalData(itemStack)
                    ),
                    player,
                    -48,
                    48,
                    48,
                    0,
                    0,
                    0,
                    this.leftPos + 87,
                    this.topPos,
                    50,
                    0,
                    -modelYaw,
                    0,
                    poseStack,
                    LightTexture.FULL_BRIGHT
            );
        } else if (itemStack.getItem() instanceof ShirtInterface shirtInterface) {
            String material;

            if (itemStack.getItem() instanceof ShirtCottonItem) {
                material = "cotton";
            } else if (itemStack.getItem() instanceof ShirtSilkItem) {
                material = "silk";
            } else {
                return;
            }

            UpperwearModelRenderer.renderModel(
                    multiBufferSource,
                    upperWearModel,
                    new SingleSidedClothingRenderingData(
                            shirtInterface.getFlag(itemStack),
                            shirtInterface.getItemMainColor(itemStack, 1),
                            shirtInterface.getItemSecondaryColor(itemStack, 1),
                            shirtInterface.getItemMainColor(itemStack, 2),
                            shirtInterface.getItemSecondaryColor(itemStack, 2),
                            shirtInterface.getItemDyeType(itemStack, 1),
                            shirtInterface.getItemDyeType(itemStack, 2),
                            shirtInterface.getStensilType(itemStack),
                            shirtInterface.getItemLightValue(itemStack, 1),
                            shirtInterface.getItemLightValue(itemStack, 2),
                            material,
                            itemStack.isEnchanted(),
                            shirtInterface.getGlintColor(itemStack),
                            shirtInterface.getAdditionalData(itemStack)
                    ),
                    player,
                    -36,
                    36,
                    36,
                    0,
                    0,
                    0,
                    this.leftPos + 88,
                    this.topPos + 30,
                    50,
                    0,
                    -modelYaw,
                    0,
                    poseStack,
                    LightTexture.FULL_BRIGHT,
                    UpperwearModelRenderer.Type.SHIRT
            );
        } else if (itemStack.getItem() instanceof PulloverInterface pulloverInterface) {
            String material;

            if (itemStack.getItem() instanceof LongSleeveCottonItem) {
                material = "cotton";
            } else if (itemStack.getItem() instanceof SweaterWoolItem) {
                material = "wool";
            } else {
                return;
            }

            UpperwearModelRenderer.renderModel(
                    multiBufferSource,
                    upperWearModel,
                    new SingleSidedClothingRenderingData(
                            pulloverInterface.getFlag(itemStack),
                            pulloverInterface.getItemMainColor(itemStack, 1),
                            pulloverInterface.getItemSecondaryColor(itemStack, 1),
                            pulloverInterface.getItemMainColor(itemStack, 2),
                            pulloverInterface.getItemSecondaryColor(itemStack, 2),
                            pulloverInterface.getItemDyeType(itemStack, 1),
                            pulloverInterface.getItemDyeType(itemStack, 2),
                            pulloverInterface.getStensilType(itemStack),
                            pulloverInterface.getItemLightValue(itemStack, 1),
                            pulloverInterface.getItemLightValue(itemStack, 2),
                            material,
                            itemStack.isEnchanted(),
                            pulloverInterface.getGlintColor(itemStack),
                            pulloverInterface.getAdditionalData(itemStack)
                    ),
                    player,
                    -36,
                    36,
                    36,
                    0,
                    0,
                    0,
                    this.leftPos + 88,
                    this.topPos + 30,
                    50,
                    0,
                    -modelYaw,
                    0,
                    poseStack,
                    LightTexture.FULL_BRIGHT,
                    UpperwearModelRenderer.Type.PULLOVER
            );
        } else if (itemStack.getItem() instanceof TopsInterface topsInterface) {
            String material;

            if (itemStack.getItem() instanceof TShirtItem) {
                material = "cotton";
            } else if (itemStack.getItem() instanceof TankTopItem) {
                material = "silk";
            } else if (itemStack.getItem() instanceof WoolVestItem) {
                material = "wool";
            } else {
                return;
            }

            UpperwearModelRenderer.renderModel(
                    multiBufferSource,
                    upperWearModel,
                    new SingleSidedClothingRenderingData(
                            topsInterface.getFlag(itemStack),
                            topsInterface.getItemMainColor(itemStack, 1),
                            topsInterface.getItemSecondaryColor(itemStack, 1),
                            topsInterface.getItemMainColor(itemStack, 2),
                            topsInterface.getItemSecondaryColor(itemStack, 2),
                            topsInterface.getItemDyeType(itemStack, 1),
                            topsInterface.getItemDyeType(itemStack, 2),
                            topsInterface.getStensilType(itemStack),
                            topsInterface.getItemLightValue(itemStack, 1),
                            topsInterface.getItemLightValue(itemStack, 2),
                            material,
                            itemStack.isEnchanted(),
                            topsInterface.getGlintColor(itemStack),
                            topsInterface.getAdditionalData(itemStack)
                    ),
                    player,
                    -36,
                    36,
                    36,
                    0,
                    0,
                    0,
                    this.leftPos + 88,
                    this.topPos + 30,
                    50,
                    0,
                    -modelYaw,
                    0,
                    poseStack,
                    LightTexture.FULL_BRIGHT,
                    UpperwearModelRenderer.Type.TOPS
            );
        } else if (itemStack.getItem() instanceof PonchoItem ponchoItem) {
            UpperwearModelRenderer.renderModel(
                    multiBufferSource,
                    upperWearModel,
                    new SingleSidedClothingRenderingData(
                            ponchoItem.getFlag(itemStack),
                            ponchoItem.getItemMainColor(itemStack, 1),
                            ponchoItem.getItemSecondaryColor(itemStack, 1),
                            ponchoItem.getItemMainColor(itemStack, 2),
                            ponchoItem.getItemSecondaryColor(itemStack, 2),
                            ponchoItem.getItemDyeType(itemStack, 1),
                            ponchoItem.getItemDyeType(itemStack, 2),
                            ponchoItem.getStensilType(itemStack),
                            ponchoItem.getItemLightValue(itemStack, 1),
                            ponchoItem.getItemLightValue(itemStack, 2),
                            "cotton",
                            itemStack.isEnchanted(),
                            ponchoItem.getGlintColor(itemStack),
                            ponchoItem.getAdditionalData(itemStack)
                    ),
                    player,
                    -36,
                    36,
                    36,
                    0,
                    0,
                    0,
                    this.leftPos + 88,
                    this.topPos + 30,
                    50,
                    0,
                    -modelYaw,
                    0,
                    poseStack,
                    LightTexture.FULL_BRIGHT,
                    UpperwearModelRenderer.Type.PONCHO
            );
        } else if (itemStack.getItem() instanceof PantsInterface pantsInterface) {
            String material;

            if (itemStack.getItem() instanceof PantsCottonItem) {
                material = "cotton";
            } else if (itemStack.getItem() instanceof PantsSilkItem) {
                material = "silk";
            } else if (itemStack.getItem() instanceof PantsWoolItem) {
                material = "wool";
            } else if (itemStack.getItem() instanceof PantsJeansItem) {
                material = "jeans";
            } else {
                return;
            }

            PantsModelRenderer.renderModel(
                    multiBufferSource,
                    pantsModel,
                    new SingleSidedClothingRenderingData(
                            pantsInterface.getFlag(itemStack),
                            pantsInterface.getItemMainColor(itemStack, 1),
                            pantsInterface.getItemSecondaryColor(itemStack, 1),
                            pantsInterface.getItemMainColor(itemStack, 2),
                            pantsInterface.getItemSecondaryColor(itemStack, 2),
                            pantsInterface.getItemDyeType(itemStack, 1),
                            pantsInterface.getItemDyeType(itemStack, 2),
                            pantsInterface.getStensilType(itemStack),
                            pantsInterface.getItemLightValue(itemStack, 1),
                            pantsInterface.getItemLightValue(itemStack, 2),
                            material,
                            itemStack.isEnchanted(),
                            pantsInterface.getGlintColor(itemStack),
                            pantsInterface.getAdditionalData(itemStack)
                    ),
                    player,
                    -36,
                    36,
                    36,
                    0,
                    0,
                    0,
                    this.leftPos + 88,
                    this.topPos + 20,
                    50,
                    0,
                    -modelYaw,
                    0,
                    poseStack,
                    LightTexture.FULL_BRIGHT
            );
        } else if (itemStack.getItem() instanceof CapeInterface capeInterface) {
            String material;

            if (itemStack.getItem() instanceof CapeCottonItem) {
                material = "cotton";
            } else if (itemStack.getItem() instanceof CapeSilkItem) {
                material = "silk";
            } else if (itemStack.getItem() instanceof CapeWoolItem) {
                material = "wool";
            } else {
                return;
            }

            int primaryColorOne = capeInterface.getItemMainColor(itemStack, 1);
            int secondaryColorOne = capeInterface.getItemSecondaryColor(itemStack, 1);
            int primaryColorTwo = capeInterface.getItemMainColor(itemStack, 2);
            int secondaryColorTwo = capeInterface.getItemSecondaryColor(itemStack, 2);
            String dyeTypeOne = capeInterface.getItemDyeType(itemStack, 1);
            String dyeTypeTwo = capeInterface.getItemDyeType(itemStack, 2);
            String stensilType = capeInterface.getStensilType(itemStack);
            int lightValueOne = capeInterface.getItemLightValue(itemStack, 1);
            int lightValueTwo = capeInterface.getItemLightValue(itemStack, 2);

            RenderingUtils renderingUtils = new RenderingUtils();

            ItemTexture texture = TextureRegistry.find("cape", stensilType, material);

            Pair<Integer, Integer> col1 = ColorHandlers.handle(dyeTypeOne, primaryColorOne, secondaryColorOne, lightValueOne, minecraft.player, LightTexture.FULL_BRIGHT, (int)minecraft.level.getGameTime());
            Pair<Integer, Integer> col2 = ColorHandlers.handle(dyeTypeTwo, primaryColorTwo, secondaryColorTwo, lightValueTwo, minecraft.player, LightTexture.FULL_BRIGHT, (int)minecraft.level.getGameTime());

            poseStack.pushPose();

            poseStack.translate(this.leftPos + 88, this.topPos + 30, 50);
            poseStack.scale(36, 36, 36);
            poseStack.mulPose(Axis.YP.rotationDegrees(modelYaw));

            VertexConsumer vertexConsumer1 = renderingUtils.parseVC(multiBufferSource, dyeTypeOne, texture.getTextureOne(), "cape");
            Vector4f v1 = renderingUtils.colorIntToVector4f(col1.getA());
            ((PlayerModelInterface)playerModel).getCloak().render(poseStack, vertexConsumer1, col1.getB(), OverlayTexture.NO_OVERLAY, v1.x, v1.y, v1.z, v1.w);

            if (texture.getRenderType()) {
                VertexConsumer vertexConsumer2 = renderingUtils.parseVC(multiBufferSource, dyeTypeTwo, texture.getTextureTwo(),"cape");
                Vector4f v2 = renderingUtils.colorIntToVector4f(col2.getA());
                ((PlayerModelInterface)playerModel).getCloak().render(poseStack, vertexConsumer2, col2.getB(), OverlayTexture.NO_OVERLAY, v2.x, v2.y, v2.z, v2.w);
            }

            poseStack.popPose();
        } else if (itemStack.getItem() instanceof ChokerItem chokerItem) {
            ChokerModelRenderer.renderModel(
                    multiBufferSource,
                    chokerModel,
                    new DoubleSidedClothingRenderingData(
                            chokerItem.getFlag(itemStack),
                            chokerItem.getItemMainColor(itemStack, "left", 1),
                            chokerItem.getItemSecondaryColor(itemStack, "left", 1),
                            chokerItem.getItemMainColor(itemStack, "right", 1),
                            chokerItem.getItemSecondaryColor(itemStack, "right", 1),
                            chokerItem.getItemMainColor(itemStack, "left", 2),
                            chokerItem.getItemSecondaryColor(itemStack, "left", 2),
                            chokerItem.getItemMainColor(itemStack, "right", 2),
                            chokerItem.getItemSecondaryColor(itemStack, "right", 2),
                            chokerItem.getItemDyeType(itemStack, "left", 1),
                            chokerItem.getItemDyeType(itemStack, "right", 1),
                            chokerItem.getItemDyeType(itemStack, "left", 2),
                            chokerItem.getItemDyeType(itemStack, "right", 2),
                            chokerItem.getStensilType(itemStack, "left"),
                            chokerItem.getStensilType(itemStack, "right"),
                            chokerItem.getItemLightValue(itemStack, "left", 1),
                            chokerItem.getItemLightValue(itemStack, "left", 2),
                            chokerItem.getItemLightValue(itemStack, "right", 1),
                            chokerItem.getItemLightValue(itemStack, "right", 2),
                            "base",
                            itemStack.isEnchanted(),
                            chokerItem.getGlintColor(itemStack),
                            chokerItem.getAdditionalData(itemStack)
                    ),
                    player,
                    -48,
                    48,
                    48,
                    0,
                    0,
                    0,
                    this.leftPos + 87,
                    this.topPos + 30,
                    50,
                    0,
                    -modelYaw,
                    0,
                    poseStack,
                    LightTexture.FULL_BRIGHT
            );
        } else if (itemStack.getItem() instanceof SkirtCottonItem skirtCottonItem) {
            CottonSkirtModelRenderer.renderModel(
                    multiBufferSource,
                    cottonSkirtModel,
                    new DoubleSidedClothingRenderingData(
                            skirtCottonItem.getFlag(itemStack),
                            skirtCottonItem.getItemMainColor(itemStack, "left", 1),
                            skirtCottonItem.getItemSecondaryColor(itemStack, "left", 1),
                            skirtCottonItem.getItemMainColor(itemStack, "right", 1),
                            skirtCottonItem.getItemSecondaryColor(itemStack, "right", 1),
                            skirtCottonItem.getItemMainColor(itemStack, "left", 2),
                            skirtCottonItem.getItemSecondaryColor(itemStack, "left", 2),
                            skirtCottonItem.getItemMainColor(itemStack, "right", 2),
                            skirtCottonItem.getItemSecondaryColor(itemStack, "right", 2),
                            skirtCottonItem.getItemDyeType(itemStack, "left", 1),
                            skirtCottonItem.getItemDyeType(itemStack, "right", 1),
                            skirtCottonItem.getItemDyeType(itemStack, "left", 2),
                            skirtCottonItem.getItemDyeType(itemStack, "right", 2),
                            skirtCottonItem.getStensilType(itemStack, "left"),
                            skirtCottonItem.getStensilType(itemStack, "right"),
                            skirtCottonItem.getItemLightValue(itemStack, "left", 1),
                            skirtCottonItem.getItemLightValue(itemStack, "left", 2),
                            skirtCottonItem.getItemLightValue(itemStack, "right", 1),
                            skirtCottonItem.getItemLightValue(itemStack, "right", 2),
                            "base",
                            itemStack.isEnchanted(),
                            skirtCottonItem.getGlintColor(itemStack),
                            skirtCottonItem.getAdditionalData(itemStack)
                    ),
                    player,
                    -36,
                    36,
                    36,
                    0,
                    0,
                    0,
                    this.leftPos + 88,
                    this.topPos + 20,
                    50,
                    0,
                    -modelYaw,
                    0,
                    poseStack,
                    LightTexture.FULL_BRIGHT
            );
        } else if (itemStack.getItem() instanceof CapItem capItem) {
            CapModelRenderer.renderModel(
                    multiBufferSource,
                    capModel,
                    new SingleSidedClothingRenderingData(
                            capItem.getFlag(itemStack),
                            capItem.getItemMainColor(itemStack, 1),
                            capItem.getItemSecondaryColor(itemStack, 1),
                            capItem.getItemMainColor(itemStack, 2),
                            capItem.getItemSecondaryColor(itemStack, 2),
                            capItem.getItemDyeType(itemStack, 1),
                            capItem.getItemDyeType(itemStack, 2),
                            capItem.getStensilType(itemStack),
                            capItem.getItemLightValue(itemStack, 1),
                            capItem.getItemLightValue(itemStack, 2),
                            "jeans",
                            itemStack.isEnchanted(),
                            capItem.getGlintColor(itemStack),
                            capItem.getAdditionalData(itemStack)
                    ),
                    player,
                    -36,
                    36,
                    36,
                    0,
                    0,
                    0,
                    this.leftPos + 87,
                    this.topPos + 50,
                    50,
                    0,
                    -modelYaw,
                    0,
                    poseStack,
                    LightTexture.FULL_BRIGHT
            );
        } else if (itemStack.getItem() instanceof UshankaItem ushankaItem) {
            UshankaModelRenderer.renderModel(
                    multiBufferSource,
                    ushankaModel,
                    new DoubleSidedClothingRenderingData(
                            ushankaItem.getFlag(itemStack),
                            ushankaItem.getItemMainColor(itemStack, "left", 1),
                            ushankaItem.getItemSecondaryColor(itemStack, "left", 1),
                            ushankaItem.getItemMainColor(itemStack, "right", 1),
                            ushankaItem.getItemSecondaryColor(itemStack, "right", 1),
                            ushankaItem.getItemMainColor(itemStack, "left", 2),
                            ushankaItem.getItemSecondaryColor(itemStack, "left", 2),
                            ushankaItem.getItemMainColor(itemStack, "right", 2),
                            ushankaItem.getItemSecondaryColor(itemStack, "right", 2),
                            ushankaItem.getItemDyeType(itemStack, "left", 1),
                            ushankaItem.getItemDyeType(itemStack, "right", 1),
                            ushankaItem.getItemDyeType(itemStack, "left", 2),
                            ushankaItem.getItemDyeType(itemStack, "right", 2),
                            ushankaItem.getStensilType(itemStack, "left"),
                            ushankaItem.getStensilType(itemStack, "right"),
                            ushankaItem.getItemLightValue(itemStack, "left", 1),
                            ushankaItem.getItemLightValue(itemStack, "left", 2),
                            ushankaItem.getItemLightValue(itemStack, "right", 1),
                            ushankaItem.getItemLightValue(itemStack, "right", 2),
                            "base",
                            itemStack.isEnchanted(),
                            ushankaItem.getGlintColor(itemStack),
                            ushankaItem.getAdditionalData(itemStack)
                    ),
                    player,
                    -36,
                    36,
                    36,
                    0,
                    0,
                    0,
                    this.leftPos + 87,
                    this.topPos + 50,
                    50,
                    0,
                    -modelYaw,
                    0,
                    poseStack,
                    LightTexture.FULL_BRIGHT
            );
        } else if (itemStack.getItem() instanceof PomponHatItem pomponHatItem) {
            PomponHatModelRenderer.renderModel(
                    multiBufferSource,
                    pomponHatModel,
                    new SingleSidedClothingRenderingData(
                            pomponHatItem.getFlag(itemStack),
                            pomponHatItem.getItemMainColor(itemStack, 1),
                            pomponHatItem.getItemSecondaryColor(itemStack, 1),
                            pomponHatItem.getItemMainColor(itemStack, 2),
                            pomponHatItem.getItemSecondaryColor(itemStack, 2),
                            pomponHatItem.getItemDyeType(itemStack, 1),
                            pomponHatItem.getItemDyeType(itemStack, 2),
                            pomponHatItem.getStensilType(itemStack),
                            pomponHatItem.getItemLightValue(itemStack, 1),
                            pomponHatItem.getItemLightValue(itemStack, 2),
                            "cotton",
                            itemStack.isEnchanted(),
                            pomponHatItem.getGlintColor(itemStack),
                            pomponHatItem.getAdditionalData(itemStack)
                    ),
                    player,
                    -36,
                    36,
                    36,
                    0,
                    0,
                    0,
                    this.leftPos + 87,
                    this.topPos + 50,
                    50,
                    0,
                    -modelYaw,
                    0,
                    poseStack,
                    LightTexture.FULL_BRIGHT
            );
        } else if (itemStack.getItem() instanceof BlahajItem blahajItem) {
            BlahajModelRenderer.renderModel(
                    multiBufferSource,
                    blahajModel,
                    new DoubleSidedClothingRenderingData(
                            blahajItem.getFlag(itemStack),
                            blahajItem.getItemMainColor(itemStack, "left", 1),
                            blahajItem.getItemSecondaryColor(itemStack, "left", 1),
                            blahajItem.getItemMainColor(itemStack, "right", 1),
                            blahajItem.getItemSecondaryColor(itemStack, "right", 1),
                            blahajItem.getItemMainColor(itemStack, "left", 2),
                            blahajItem.getItemSecondaryColor(itemStack, "left", 2),
                            blahajItem.getItemMainColor(itemStack, "right", 2),
                            blahajItem.getItemSecondaryColor(itemStack, "right", 2),
                            blahajItem.getItemDyeType(itemStack, "left", 1),
                            blahajItem.getItemDyeType(itemStack, "right", 1),
                            blahajItem.getItemDyeType(itemStack, "left", 2),
                            blahajItem.getItemDyeType(itemStack, "right", 2),
                            blahajItem.getStensilType(itemStack, "left"),
                            blahajItem.getStensilType(itemStack, "right"),
                            blahajItem.getItemLightValue(itemStack, "left", 1),
                            blahajItem.getItemLightValue(itemStack, "left", 2),
                            blahajItem.getItemLightValue(itemStack, "right", 1),
                            blahajItem.getItemLightValue(itemStack, "right", 2),
                            "default",
                            itemStack.isEnchanted(),
                            blahajItem.getGlintColor(itemStack),
                            blahajItem.getAdditionalData(itemStack)
                    ),
                    player,
                    -32,
                    32,
                    32,
                    0,
                    0,
                    0,
                    this.leftPos + 87,
                    this.topPos,
                    50,
                    0,
                    -modelYaw,
                    0,
                    poseStack,
                    LightTexture.FULL_BRIGHT
            );
        }

        multiBufferSource.endBatch();
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int gx, int gy) {
        RenderSystem.setShaderColor(1, 1, 1, 1);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();

        guiGraphics.blit(ResourceLocation.parse("weaversparadise:textures/screens/dyeing_screen.png"), this.leftPos + -1, this.topPos + -7, 0, 0, 178, 227, 178, 227);

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
        itemStack = menu.slots.get(1).getItem();
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
