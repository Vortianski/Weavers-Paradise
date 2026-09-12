package xox.labvorty.weaversparadise.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import org.jetbrains.annotations.NotNull;

public class RalseiModel<T extends Entity> extends EntityModel<T> {
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("weaversparadise", "ralsei_model"), "main");
	public final ModelPart Head;
    public final ModelPart Body;
    public final ModelPart Right_Arm;
    public final ModelPart Left_Arm;
    public final ModelPart Right_Leg;
    public final ModelPart Right_Boot;
    public final ModelPart Left_Leg;
    public final ModelPart Left_Boot;

	public RalseiModel(ModelPart root) {
		this.Head = root.getChild("Head");
		this.Body = root.getChild("Body");
		this.Right_Arm = root.getChild("Right_Arm");
		this.Left_Arm = root.getChild("Left_Arm");
		this.Right_Leg = root.getChild("Right_Leg");
		this.Right_Boot = root.getChild("Right_Boot");
		this.Left_Leg = root.getChild("Left_Leg");
		this.Left_Boot = root.getChild("Left_Boot");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Head = partdefinition.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(0, 16).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.2501F))
		.texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.85F))
		.texOffs(82, 8).addBox(-4.05F, -8.7F, -4.9F, 8.0F, 8.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition Head_r1 = Head.addOrReplaceChild("Head_r1", CubeListBuilder.create().texOffs(68, 27).addBox(-0.5F, -1.0F, -0.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.2501F)), PartPose.offsetAndRotation(3.5F, -9.0F, -2.5F, 0.2174F, -0.0189F, 0.0852F));

		PartDefinition Head_r2 = Head.addOrReplaceChild("Head_r2", CubeListBuilder.create().texOffs(56, 64).addBox(-0.5F, -3.0F, -2.5F, 1.0F, 6.0F, 5.0F, new CubeDeformation(0.2501F)), PartPose.offsetAndRotation(4.2F, -2.5F, -0.5F, 0.0F, 0.0F, -0.2182F));

		PartDefinition Head_r3 = Head.addOrReplaceChild("Head_r3", CubeListBuilder.create().texOffs(64, 27).addBox(-0.5F, -1.0F, -0.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.2501F)), PartPose.offsetAndRotation(-3.5F, -9.0F, -2.5F, 0.2174F, 0.0189F, -0.0852F));

		PartDefinition Head_r4 = Head.addOrReplaceChild("Head_r4", CubeListBuilder.create().texOffs(64, 48).addBox(-0.5F, -3.0F, -2.5F, 1.0F, 6.0F, 5.0F, new CubeDeformation(0.2501F)), PartPose.offsetAndRotation(-4.2F, -2.5F, -0.5F, 0.0F, 0.0F, 0.2182F));

		PartDefinition Body = partdefinition.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(32, 0).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.2505F))
		.texOffs(0, 32).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.6F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition Body_r1 = Body.addOrReplaceChild("Body_r1", CubeListBuilder.create().texOffs(56, 8).addBox(-4.0F, -1.5F, -2.5F, 6.0F, 3.0F, 5.0F, new CubeDeformation(0.25F)), PartPose.offsetAndRotation(-0.6F, 2.2F, -0.1F, 0.0F, 0.0F, 0.5236F));

		PartDefinition BodyLayer_r1 = Body.addOrReplaceChild("Body Layer_r1", CubeListBuilder.create().texOffs(0, 64).addBox(-4.0F, -3.0F, 0.0F, 8.0F, 6.0F, 2.0F, new CubeDeformation(0.7F)), PartPose.offsetAndRotation(0.0F, 15.2F, 0.7F, 0.192F, 0.0F, 0.0F));

		PartDefinition Body_Layer_r2 = Body.addOrReplaceChild("Body_Layer_r2", CubeListBuilder.create().texOffs(56, 40).addBox(-4.0F, -3.0F, -2.0F, 8.0F, 6.0F, 2.0F, new CubeDeformation(0.698F)), PartPose.offsetAndRotation(0.0F, 15.2F, -0.6F, -0.192F, 0.0F, 0.0F));

		PartDefinition Body_r2 = Body.addOrReplaceChild("Body_r2", CubeListBuilder.create().texOffs(56, 0).addBox(-2.0F, -1.5F, -2.5F, 6.0F, 3.0F, 5.0F, new CubeDeformation(0.2505F)), PartPose.offsetAndRotation(0.4F, 1.6F, -0.1F, 0.0F, 0.0F, -0.5236F));

		PartDefinition Body_r3 = Body.addOrReplaceChild("Body_r3", CubeListBuilder.create().texOffs(68, 59).addBox(-2.0F, -6.0F, 0.0F, 4.0F, 12.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.1F, 7.1F, 3.1F, 0.0698F, 0.0F, 0.0F));

		PartDefinition Right_Arm = partdefinition.addOrReplaceChild("Right_Arm", CubeListBuilder.create().texOffs(32, 16).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.2503F))
		.texOffs(78, 32).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.6F)), PartPose.offset(-5.0F, 2.0F, 0.0F));

		PartDefinition RightArm_r1 = Right_Arm.addOrReplaceChild("Right Arm_r1", CubeListBuilder.create().texOffs(44, 64).addBox(-2.0F, -6.0F, -2.0F, 4.0F, 9.0F, 2.0F, new CubeDeformation(0.2501F)), PartPose.offsetAndRotation(-1.0F, 4.4F, -0.9F, -0.1396F, 0.0F, 0.0F));

		PartDefinition Right_Arm_r2 = Right_Arm.addOrReplaceChild("Right_Arm_r2", CubeListBuilder.create().texOffs(64, 16).addBox(-2.0F, -6.0F, 0.0F, 4.0F, 9.0F, 2.0F, new CubeDeformation(0.2501F)), PartPose.offsetAndRotation(-1.0F, 4.4F, 0.9F, 0.1396F, 0.0F, 0.0F));

		PartDefinition Left_Arm = partdefinition.addOrReplaceChild("Left_Arm", CubeListBuilder.create().texOffs(24, 32).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.2503F))
		.texOffs(40, 32).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.6F)), PartPose.offset(5.0F, 2.0F, 0.0F));

		PartDefinition LeftArm_r1 = Left_Arm.addOrReplaceChild("Left Arm_r1", CubeListBuilder.create().texOffs(32, 64).addBox(-2.0F, -6.0F, 0.0F, 4.0F, 9.0F, 2.0F, new CubeDeformation(0.2501F)), PartPose.offsetAndRotation(1.0F, 4.4F, 0.9F, 0.1396F, 0.0F, 0.0F));

		PartDefinition Left_Arm_r2 = Left_Arm.addOrReplaceChild("Left_Arm_r2", CubeListBuilder.create().texOffs(20, 64).addBox(-2.0F, -6.0F, -2.0F, 4.0F, 9.0F, 2.0F, new CubeDeformation(0.2501F)), PartPose.offsetAndRotation(1.0F, 4.4F, -0.9F, -0.1396F, 0.0F, 0.0F));

		PartDefinition Right_Leg = partdefinition.addOrReplaceChild("Right_Leg", CubeListBuilder.create().texOffs(0, 48).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.2519F)), PartPose.offset(-1.9F, 12.0F, 0.0F));

		PartDefinition Right_Boot = partdefinition.addOrReplaceChild("Right_Boot", CubeListBuilder.create().texOffs(16, 85).addBox(-2.0F, 8.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.2529F))
		.texOffs(16, 48).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.6F)), PartPose.offset(-1.9F, 12.0F, 0.0F));

		PartDefinition Left_Leg = partdefinition.addOrReplaceChild("Left_Leg", CubeListBuilder.create().texOffs(48, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.2519F)), PartPose.offset(1.9F, 12.0F, 0.0F));

		PartDefinition Left_Boot = partdefinition.addOrReplaceChild("Left_Boot", CubeListBuilder.create().texOffs(0, 85).addBox(-2.0F, 8.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.2529F))
		.texOffs(32, 48).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.6F)), PartPose.offset(1.9F, 12.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(@NotNull T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {}

	@Override
	public void renderToBuffer(@NotNull PoseStack poseStack, @NotNull VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
		Head.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
		Body.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
		Right_Arm.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
		Left_Arm.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
		Right_Leg.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
		Right_Boot.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
		Left_Leg.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
		Left_Boot.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
	}
}