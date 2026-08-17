package xox.labvorty.weaversparadise.renderers.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import xox.labvorty.weaversparadise.configs.ClientConfig;
import xox.labvorty.weaversparadise.items.clothing.CapItem;
import xox.labvorty.weaversparadise.items.clothing.PomponHatItem;
import xox.labvorty.weaversparadise.items.clothing.UshankaItem;
import xox.labvorty.weaversparadise.model.CapModel;
import xox.labvorty.weaversparadise.model.PomponHatModel;
import xox.labvorty.weaversparadise.model.UshankaModel;
import xox.labvorty.weaversparadise.renderers.helpers.DoubleSidedClothingRenderingData;
import xox.labvorty.weaversparadise.renderers.helpers.SingleSidedClothingRenderingData;
import xox.labvorty.weaversparadise.renderers.models.CapModelRenderer;
import xox.labvorty.weaversparadise.renderers.models.PomponHatModelRenderer;
import xox.labvorty.weaversparadise.renderers.models.UshankaModelRenderer;
import xox.labvorty.weaversparadise.utilities.WeaversUtilities;

public class HatRenderLayer<T extends LivingEntity, M extends EntityModel<T>> extends RenderLayer<T, M> {
    private final UshankaModel<?> ushankaModel;
    private final CapModel<?> capModel;
    private final PomponHatModel<?> pomponHatModel;

    public HatRenderLayer(RenderLayerParent<T, M> parent) {
        super(parent);
        this.ushankaModel = new UshankaModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(UshankaModel.LAYER_LOCATION));
        this.capModel = new CapModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(CapModel.LAYER_LOCATION));
        this.pomponHatModel = new PomponHatModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(PomponHatModel.LAYER_LOCATION));
    }

    @Override
    public void render(
            @NotNull PoseStack poseStack,
            @NotNull MultiBufferSource multiBufferSource,
            int packedLight,
            @NotNull T entity,
            float limbSwing,
            float limbSwingAmount,
            float partialTicks,
            float ageInTicks,
            float netHeadYaw,
            float headPitch
    ) {
        ItemStack itemStack = entity.getItemBySlot(EquipmentSlot.HEAD);

        if (WeaversUtilities.isRestricted(entity, ClientConfig.HAT_RESTRICTOR.get())) {
            return;
        }

        if (itemStack.getItem() instanceof UshankaItem ushankaItem) {
            EntityModel<?> entityModel = getParentModel();
            if (entityModel instanceof HumanoidModel<?> humanoidModel) {
                this.ushankaModel.Head.copyFrom(humanoidModel.head);
            }

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
                    entity,
                    1,
                    1,
                    1,
                    0,
                    0,
                    0,
                    0,
                    0,
                    0,
                    0,
                    0,
                    0,
                    poseStack,
                    packedLight
            );
        } else if (itemStack.getItem() instanceof PomponHatItem pomponHatItem) {
            EntityModel<?> entityModel = getParentModel();
            if (entityModel instanceof HumanoidModel<?> humanoidModel) {
                this.pomponHatModel.Head.copyFrom(humanoidModel.head);
            }

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
                    entity,
                    1,
                    1,
                    1,
                    0,
                    0,
                    0,
                    0,
                    0,
                    0,
                    0,
                    0,
                    0,
                    poseStack,
                    packedLight
            );
        } else if (itemStack.getItem() instanceof CapItem capItem) {
            EntityModel<?> entityModel = getParentModel();
            if (entityModel instanceof HumanoidModel<?> humanoidModel) {
                this.capModel.Head.copyFrom(humanoidModel.head);
            }

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
                    entity,
                    1,
                    1,
                    1,
                    0,
                    0,
                    0,
                    0,
                    0,
                    0,
                    0,
                    0,
                    0,
                    poseStack,
                    packedLight
            );
        }
    }
}
