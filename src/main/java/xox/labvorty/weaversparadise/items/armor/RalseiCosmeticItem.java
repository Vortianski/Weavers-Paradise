package xox.labvorty.weaversparadise.items.armor;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import top.theillusivec4.curios.api.type.capability.ICurioItem;
import xox.labvorty.weaversparadise.model.RalseiModel;
import xox.labvorty.weaversparadise.model.RalseiModel;

import java.util.Collections;
import java.util.Map;

public class RalseiCosmeticItem extends Item implements ICurioItem, ModelReplacer {
    public RalseiCosmeticItem() {
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
                                "head", new RalseiModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(RalseiModel.LAYER_LOCATION)).Head,
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
                                "body", new RalseiModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(RalseiModel.LAYER_LOCATION)).Body,
                                "left_arm", new RalseiModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(RalseiModel.LAYER_LOCATION)).Left_Arm,
                                "right_arm", new RalseiModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(RalseiModel.LAYER_LOCATION)).Right_Arm,
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
                                "left_leg", new RalseiModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(RalseiModel.LAYER_LOCATION)).Left_Leg,
                                "right_leg", new RalseiModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(RalseiModel.LAYER_LOCATION)).Right_Leg,
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
                                "left_leg", new RalseiModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(RalseiModel.LAYER_LOCATION)).Left_Boot,
                                "right_leg", new RalseiModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(RalseiModel.LAYER_LOCATION)).Right_Boot,
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
        return ResourceLocation.fromNamespaceAndPath("weaversparadise", "textures/clothing/armor/ralsei.png");
    }
}
