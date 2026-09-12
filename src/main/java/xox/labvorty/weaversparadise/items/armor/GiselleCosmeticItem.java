package xox.labvorty.weaversparadise.items.armor;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import dev.emi.trinkets.api.Trinket;
import dev.emi.trinkets.api.TrinketsApi;
import xox.labvorty.weaversparadise.model.GiselleModel;

import java.util.Collections;
import java.util.Map;

public class GiselleCosmeticItem extends Item implements Trinket, ModelReplacer {
    public GiselleCosmeticItem() {
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
                                "head", new GiselleModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(GiselleModel.LAYER_LOCATION)).Head,
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
                                "body", new GiselleModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(GiselleModel.LAYER_LOCATION)).Body,
                                "left_arm", new GiselleModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(GiselleModel.LAYER_LOCATION)).LeftArm,
                                "right_arm", new GiselleModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(GiselleModel.LAYER_LOCATION)).RightArm,
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
                                "left_leg", new GiselleModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(GiselleModel.LAYER_LOCATION)).LeftLeg,
                                "right_leg", new GiselleModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(GiselleModel.LAYER_LOCATION)).RightLeg,
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
                                "left_leg", new GiselleModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(GiselleModel.LAYER_LOCATION)).LeftBoot,
                                "right_leg", new GiselleModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(GiselleModel.LAYER_LOCATION)).RightBoot, "head", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
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
        return ResourceLocation.fromNamespaceAndPath("weaversparadise", "textures/clothing/armor/giselle.png");
    }
}
