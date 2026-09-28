package xox.labvorty.weaversparadise.items.armor;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import top.theillusivec4.curios.api.type.capability.ICurioItem;
import xox.labvorty.weaversparadise.model.BridgetModel;

import java.util.Collections;
import java.util.Map;

public class BridgetCosmeticItem extends Item implements ICurioItem, ModelReplacer {
    public BridgetCosmeticItem() {
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
                                "head", new BridgetModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(BridgetModel.LAYER_LOCATION)).Head,
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
                                "body", new BridgetModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(BridgetModel.LAYER_LOCATION)).Body,
                                "left_arm", new BridgetModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(BridgetModel.LAYER_LOCATION)).LeftArm,
                                "right_arm", new BridgetModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(BridgetModel.LAYER_LOCATION)).RightArm,
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
                                "left_leg", new BridgetModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(BridgetModel.LAYER_LOCATION)).LeftLegPants,
                                "right_leg", new BridgetModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(BridgetModel.LAYER_LOCATION)).RightLegPants,
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
                                "left_leg", new BridgetModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(BridgetModel.LAYER_LOCATION)).LeftLeg,
                                "right_leg", new BridgetModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(BridgetModel.LAYER_LOCATION)).RightLeg,
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
        return ResourceLocation.fromNamespaceAndPath("weaversparadise", "textures/clothing/armor/bridget.png");
    }
}
