package xox.labvorty.weaversparadise.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

public class GriffithArmorModel<T extends Entity> extends EntityModel<T> {
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("weaversparadise", "griffith_armor_model"), "main");
	public final ModelPart LeftArm;
    public final ModelPart RightArm;
    public final ModelPart Body;
    public final ModelPart Head;
    public final ModelPart RightLeg;
    public final ModelPart RightBoot;
    public final ModelPart LeftLeg;
    public final ModelPart LeftBoot;

	public GriffithArmorModel(ModelPart root) {
		this.LeftArm = root.getChild("LeftArm");
		this.RightArm = root.getChild("RightArm");
		this.Body = root.getChild("Body");
		this.Head = root.getChild("Head");
		this.RightLeg = root.getChild("RightLeg");
		this.RightBoot = root.getChild("RightBoot");
		this.LeftLeg = root.getChild("LeftLeg");
		this.LeftBoot = root.getChild("LeftBoot");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition LeftArm = partdefinition.addOrReplaceChild("LeftArm", CubeListBuilder.create().texOffs(32, 48).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.2501F))
		.texOffs(16, 37).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.4501F))
		.texOffs(0, 64).addBox(-1.0F, -2.0F, 2.5F, 4.0F, 12.0F, 2.0F, new CubeDeformation(0.2501F)), PartPose.offset(5.0F, 2.0F, 0.0F));

		PartDefinition LeftArm_r1 = LeftArm.addOrReplaceChild("Left Arm_r1", CubeListBuilder.create().texOffs(60, 71).addBox(-2.0F, -1.0F, -2.0F, 4.0F, 3.0F, 4.0F, new CubeDeformation(0.4501F)), PartPose.offsetAndRotation(1.3F, -2.0F, 0.0F, 0.0F, 0.0F, 0.1309F));

		PartDefinition RightArm = partdefinition.addOrReplaceChild("RightArm", CubeListBuilder.create().texOffs(0, 37).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.2501F))
		.texOffs(64, 0).addBox(-3.0F, -2.0F, 2.5F, 4.0F, 12.0F, 2.0F, new CubeDeformation(0.2501F))
		.texOffs(48, 48).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.4501F)), PartPose.offset(-5.0F, 2.0F, 0.0F));

		PartDefinition RightArm_r1 = RightArm.addOrReplaceChild("Right Arm_r1", CubeListBuilder.create().texOffs(60, 64).addBox(-2.0F, -1.0F, -2.0F, 4.0F, 3.0F, 4.0F, new CubeDeformation(0.4501F)), PartPose.offsetAndRotation(-1.3F, -2.0F, 0.0F, 0.0F, 0.0F, -0.1309F));

		PartDefinition Body = partdefinition.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(32, 32).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.2503F))
		.texOffs(32, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.4501F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition Head = partdefinition.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(0, 21).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.2501F))
		.texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 13.0F, 8.0F, new CubeDeformation(0.4501F))
		.texOffs(32, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.6001F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition RightLeg = partdefinition.addOrReplaceChild("RightLeg", CubeListBuilder.create().texOffs(72, 39).addBox(-2.2F, 0.0F, 2.5F, 4.0F, 5.0F, 2.0F, new CubeDeformation(0.2501F))
		.texOffs(0, 53).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.4501F))
		.texOffs(56, 27).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.2502F)), PartPose.offset(-1.9F, 12.0F, 0.0F));

		PartDefinition RightBoot = partdefinition.addOrReplaceChild("RightBoot", CubeListBuilder.create().texOffs(64, 47).addBox(0.0F, 0.0F, 1.0F, 0.0F, 12.0F, 4.0F, new CubeDeformation(0.0001F))
		.texOffs(72, 32).addBox(-2.2F, 5.0F, 2.5F, 4.0F, 5.0F, 2.0F, new CubeDeformation(0.2501F))
		.texOffs(12, 64).addBox(-2.0F, 7.0F, -2.0F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.4501F))
		.texOffs(28, 64).addBox(-2.0F, 7.0F, -2.0F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.2503F)), PartPose.offset(-1.9F, 12.0F, 0.0F));

		PartDefinition LeftLeg = partdefinition.addOrReplaceChild("LeftLeg", CubeListBuilder.create().texOffs(56, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.2504F))
		.texOffs(16, 53).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.4502F))
		.texOffs(72, 23).addBox(-1.8F, 0.0F, 2.5F, 4.0F, 6.0F, 2.0F, new CubeDeformation(0.2501F)), PartPose.offset(1.9F, 12.0F, 0.0F));

		PartDefinition LeftBoot = partdefinition.addOrReplaceChild("LeftBoot", CubeListBuilder.create().texOffs(44, 64).addBox(-2.0F, 7.0F, -2.0F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.2502F))
		.texOffs(64, 47).mirror().addBox(0.0F, 0.0F, 1.0F, 0.0F, 12.0F, 4.0F, new CubeDeformation(0.0001F)).mirror(false)
		.texOffs(56, 38).addBox(-2.0F, 7.0F, -2.0F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.4502F))
		.texOffs(72, 14).addBox(-1.8F, 5.0F, 2.5F, 4.0F, 7.0F, 2.0F, new CubeDeformation(0.2501F)), PartPose.offset(1.9F, 12.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
		LeftArm.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
		RightArm.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
		Body.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
		Head.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
		RightLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
		RightBoot.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
		LeftLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
		LeftBoot.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
	}
}