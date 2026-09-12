package xox.labvorty.weaversparadise.items.armor;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import dev.emi.trinkets.api.Trinket;
import dev.emi.trinkets.api.TrinketsApi;
import xox.labvorty.weaversparadise.client.render.WPRenderTypes;
import xox.labvorty.weaversparadise.model.GabrielModel;

import java.util.Collections;
import java.util.Map;

public class GabrielCosmeticItem extends Item implements Trinket, ModelReplacer {
    public GabrielCosmeticItem() {
        super(
                new Properties()
                        .stacksTo(1)
        );
        TrinketsApi.registerTrinket(this, this);
    }

    @Override
    @Environment(EnvType.CLIENT)
    public HumanoidModel<?> getModelForHead(LivingEntity livingEntity, HumanoidModel<?> defaultModel) {
        HumanoidModel<?> armorModel = new HumanoidModel<>(
                new ModelPart(
                        Collections.emptyList(),
                        Map.of(
                                "head", new GabrielModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(GabrielModel.LAYER_LOCATION)).Head,
                                "hat", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                                "body", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                                "right_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                                "left_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                                "right_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                                "left_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap())
                        )
                )
        );

        armorModel.crouching = livingEntity.isShiftKeyDown();
        armorModel.riding = defaultModel.riding;
        armorModel.young = livingEntity.isBaby();

        return armorModel;
    }

    @Override
    @Environment(EnvType.CLIENT)
    public HumanoidModel<?> getModelForChestplate(LivingEntity livingEntity, HumanoidModel<?> defaultModel) {
        HumanoidModel<?> armorModel = new HumanoidModel<>(
                new ModelPart(
                        Collections.emptyList(),
                        Map.of(
                                "body", new GabrielModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(GabrielModel.LAYER_LOCATION)).Body,
                                "left_arm", new GabrielModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(GabrielModel.LAYER_LOCATION)).LeftArm,
                                "right_arm", new GabrielModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(GabrielModel.LAYER_LOCATION)).RightArm,
                                "head", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                                "hat", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                                "right_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                                "left_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap())
                        )
                )
        );

        armorModel.crouching = livingEntity.isShiftKeyDown();
        armorModel.riding = defaultModel.riding;
        armorModel.young = livingEntity.isBaby();

        return armorModel;
    }

    @Override
    @Environment(EnvType.CLIENT)
    public HumanoidModel<?> getModelForLeggings(LivingEntity livingEntity, HumanoidModel<?> defaultModel) {
        HumanoidModel<?> armorModel = new HumanoidModel<>(
                new ModelPart(
                        Collections.emptyList(),
                        Map.of(
                                "left_leg", new GabrielModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(GabrielModel.LAYER_LOCATION)).LeftLeg,
                                "right_leg", new GabrielModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(GabrielModel.LAYER_LOCATION)).RightLeg,
                                "head", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                                "hat", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                                "body", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                                "right_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                                "left_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap())
                        )
                )
        );

        armorModel.crouching = livingEntity.isShiftKeyDown();
        armorModel.riding = defaultModel.riding;
        armorModel.young = livingEntity.isBaby();

        return armorModel;
    }

    @Override
    @Environment(EnvType.CLIENT)
    public HumanoidModel<?> getModelForBoots(LivingEntity livingEntity, HumanoidModel<?> defaultModel) {
        HumanoidModel<?> armorModel = new HumanoidModel<>(
                new ModelPart(
                        Collections.emptyList(),
                        Map.of(
                                "left_leg", new GabrielModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(GabrielModel.LAYER_LOCATION)).LeftBoot,
                                "right_leg", new GabrielModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(GabrielModel.LAYER_LOCATION)).RightBoot,
                                "head", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                                "hat", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                                "body", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                                "right_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                                "left_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap())
                        )
                )
        );

        armorModel.crouching = livingEntity.isShiftKeyDown();
        armorModel.riding = defaultModel.riding;
        armorModel.young = livingEntity.isBaby();

        return armorModel;
    }

    @Override
    public ResourceLocation getMainTexture() {
        return ResourceLocation.fromNamespaceAndPath("weaversparadise", "textures/clothing/armor/gabriel.png");
    }

    @Override
    @Environment(EnvType.CLIENT)
    public HumanoidModel<?> getAdditionalModelForSlot(LivingEntity livingEntity, EquipmentSlot equipmentSlot, HumanoidModel<?> defaultModel) {
        if (equipmentSlot.equals(EquipmentSlot.CHEST)) {
            HumanoidModel<?> armorModel = new HumanoidModel<>(
                    new ModelPart(
                            Collections.emptyList(),
                            Map.of(
                                    "body", new GabrielModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(GabrielModel.LAYER_LOCATION)).BodyGlow,
                                    "left_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                                    "right_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                                    "head", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                                    "hat", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                                    "right_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                                    "left_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap())
                            )
                    )
            );

            armorModel.crouching = livingEntity.isShiftKeyDown();
            armorModel.riding = defaultModel.riding;
            armorModel.young = livingEntity.isBaby();

            return armorModel;
        } else if (equipmentSlot.equals(EquipmentSlot.HEAD)) {
            HumanoidModel<?> armorModel = new HumanoidModel<>(
                    new ModelPart(
                            Collections.emptyList(),
                            Map.of(
                                    "body", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                                    "left_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                                    "right_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                                    "head", new GabrielModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(GabrielModel.LAYER_LOCATION)).HeadGlow,
                                    "hat", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                                    "right_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                                    "left_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap())
                            )
                    )
            );

            armorModel.crouching = livingEntity.isShiftKeyDown();
            armorModel.riding = defaultModel.riding;
            armorModel.young = livingEntity.isBaby();

            return armorModel;
        }

        HumanoidModel<?> armorModel = new HumanoidModel<>(
                new ModelPart(
                        Collections.emptyList(),
                        Map.of(
                                "left_leg", new GabrielModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(GabrielModel.LAYER_LOCATION)).LeftBoot,
                                "right_leg", new GabrielModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(GabrielModel.LAYER_LOCATION)).RightBoot,
                                "head", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                                "hat", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                                "body", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                                "right_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                                "left_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap())
                        )
                )
        );

        armorModel.crouching = livingEntity.isShiftKeyDown();
        armorModel.riding = defaultModel.riding;
        armorModel.young = livingEntity.isBaby();

        return armorModel;
    }

    @Override
    @Environment(EnvType.CLIENT)
    public RenderType getAdditionalRenderTypeForSlot(EquipmentSlot equipmentSlot, ResourceLocation resourceLocation) {
        return WPRenderTypes.getEntityTranslucentEmissiveCull(resourceLocation);
    }

    @Override
    public ResourceLocation getAdditionalTextureForSlot(EquipmentSlot equipmentSlot) {
        return ResourceLocation.fromNamespaceAndPath("weaversparadise", "textures/clothing/armor/gabriel.png");
    }

    @Override
    @Environment(EnvType.CLIENT)
    public RenderingData getAdditionalRenderingDataForSlot(EquipmentSlot equipmentSlot, ItemStack itemStack, int packedLight, int packedOverlay) {
        return new RenderingData(-1, LightTexture.FULL_BRIGHT, OverlayTexture.NO_OVERLAY);
    }
}
