package xox.labvorty.weaversparadise.items.armor;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import top.theillusivec4.curios.api.type.capability.ICurioItem;
import xox.labvorty.vortylib.init.VortyLibRenderTypes;
import xox.labvorty.weaversparadise.model.GabrielModel;
import xox.labvorty.weaversparadise.model.MikkelaModel;

import java.util.Collections;
import java.util.Map;

public class MikkelaCosmeticItem extends Item implements ICurioItem, ModelReplacer {
    public MikkelaCosmeticItem() {
        super(
                new Properties()
                        .stacksTo(1)
        );
    }

    @Override
    public HumanoidModel<?> getModelForHead(LivingEntity livingEntity, HumanoidModel<?> defaultModel) {
        HumanoidModel<?> armorModel = new HumanoidModel<>(
                new ModelPart(
                        Collections.emptyList(),
                        Map.of(
                                "head", new MikkelaModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(MikkelaModel.LAYER_LOCATION)).Head,
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
    public HumanoidModel<?> getModelForChestplate(LivingEntity livingEntity, HumanoidModel<?> defaultModel) {
        HumanoidModel<?> armorModel = new HumanoidModel<>(
                new ModelPart(
                        Collections.emptyList(),
                        Map.of(
                                "body", new MikkelaModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(MikkelaModel.LAYER_LOCATION)).Body,
                                "left_arm", new MikkelaModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(MikkelaModel.LAYER_LOCATION)).LeftArm,
                                "right_arm", new MikkelaModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(MikkelaModel.LAYER_LOCATION)).RightArm,
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
    public HumanoidModel<?> getModelForLeggings(LivingEntity livingEntity, HumanoidModel<?> defaultModel) {
        HumanoidModel<?> armorModel = new HumanoidModel<>(
                new ModelPart(
                        Collections.emptyList(),
                        Map.of(
                                "left_leg", new MikkelaModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(MikkelaModel.LAYER_LOCATION)).LeftLeg,
                                "right_leg", new MikkelaModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(MikkelaModel.LAYER_LOCATION)).RightLeg,
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
    public HumanoidModel<?> getModelForBoots(LivingEntity livingEntity, HumanoidModel<?> defaultModel) {
        HumanoidModel<?> armorModel = new HumanoidModel<>(
                new ModelPart(
                        Collections.emptyList(),
                        Map.of(
                                "left_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                                "right_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
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
        return ResourceLocation.fromNamespaceAndPath("weaversparadise", "textures/clothing/armor/mikkela.png");
    }

    @Override
    public HumanoidModel<?> getAdditionalModelForSlot(LivingEntity livingEntity, EquipmentSlot equipmentSlot, HumanoidModel<?> defaultModel) {
        if (equipmentSlot.equals(EquipmentSlot.CHEST)) {
            HumanoidModel<?> armorModel = new HumanoidModel<>(
                    new ModelPart(
                            Collections.emptyList(),
                            Map.of(
                                    "body", new MikkelaModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(MikkelaModel.LAYER_LOCATION)).BodyGlow,
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
    public RenderType getAdditionalRenderTypeForSlot(EquipmentSlot equipmentSlot, ResourceLocation resourceLocation) {
        return VortyLibRenderTypes.getEntityTranslucentEmissiveCull(resourceLocation);
    }

    @Override
    public ResourceLocation getAdditionalTextureForSlot(EquipmentSlot equipmentSlot) {
        return ResourceLocation.fromNamespaceAndPath("weaversparadise", "textures/clothing/armor/mikkela.png");
    }
}
