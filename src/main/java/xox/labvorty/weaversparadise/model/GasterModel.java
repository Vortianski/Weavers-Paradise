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

public class GasterModel<T extends Entity> extends EntityModel<T> {
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("weaversparadise", "gaster_model"), "main");
	public final ModelPart Head;
    public final ModelPart Body;
    public final ModelPart Right_Arm;
    public final ModelPart Left_Arm;
    public final ModelPart Right_Leg;
    public final ModelPart Right_boot;
    public final ModelPart Left_Leg;
    public final ModelPart Left_boot;

	public GasterModel(ModelPart root) {
		this.Head = root.getChild("Head");
		this.Body = root.getChild("Body");
		this.Right_Arm = root.getChild("Right_Arm");
		this.Left_Arm = root.getChild("Left_Arm");
		this.Right_Leg = root.getChild("Right_Leg");
		this.Right_boot = root.getChild("Right_boot");
		this.Left_Leg = root.getChild("Left_Leg");
		this.Left_boot = root.getChild("Left_boot");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Head = partdefinition.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(0, 32).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.2501F))
		.texOffs(0, 16).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.7F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition HatLayer_r1 = Head.addOrReplaceChild("Hat Layer_r1", CubeListBuilder.create().texOffs(33, 83).addBox(-5.0F, 1.1979F, -4.713F, 9.0F, 1.0F, 10.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.5F, -7.7979F, -0.087F, -0.1745F, 0.0F, 0.0F));

		PartDefinition Head_r1 = Head.addOrReplaceChild("Head_r1", CubeListBuilder.create().texOffs(20, 50).addBox(-4.3835F, -3.8697F, -4.713F, 0.0F, 4.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -7.7979F, -0.087F, -0.1615F, -0.0665F, -0.3873F));

		PartDefinition Head_r2 = Head.addOrReplaceChild("Head_r2", CubeListBuilder.create().texOffs(64, 6).addBox(-5.0F, -1.5F, 0.0F, 10.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -6.848F, 4.9286F, -0.3054F, 0.0F, 0.0F));

		PartDefinition Head_r3 = Head.addOrReplaceChild("Head_r3", CubeListBuilder.create().texOffs(64, 0).addBox(-5.0F, -5.7599F, -4.1184F, 10.0F, 6.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -7.7979F, -0.387F, 0.2182F, 0.0F, 0.0F));

		PartDefinition Head_r4 = Head.addOrReplaceChild("Head_r4", CubeListBuilder.create().texOffs(0, 48).addBox(4.3835F, -3.8697F, -4.713F, 0.0F, 4.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -7.7979F, -0.087F, -0.1615F, 0.0665F, 0.3873F));

		PartDefinition Head_r5 = Head.addOrReplaceChild("Head_r5", CubeListBuilder.create().texOffs(111, 8).addBox(-4.0F, -6.5F, -4.0F, 0.0F, 10.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.5F, -8.5F, 1.1F, -1.0053F, -0.4621F, 0.2578F));

		PartDefinition Head_r6 = Head.addOrReplaceChild("Head_r6", CubeListBuilder.create().texOffs(32, 0).addBox(-4.0F, -0.5F, -4.0F, 8.0F, 2.0F, 8.0F, new CubeDeformation(0.4501F)), PartPose.offsetAndRotation(0.0F, -9.1F, 0.3F, -0.1658F, 0.0F, 0.0F));

		PartDefinition Body = partdefinition.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(32, 10).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.2501F))
		.texOffs(32, 26).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.5503F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition BodyLayer_r1 = Body.addOrReplaceChild("Body Layer_r1", CubeListBuilder.create().texOffs(24, 80).addBox(-1.5F, -1.5F, 0.0F, 3.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.1F, 10.5F, -2.4F, 0.0F, 0.0F, -0.3054F));

		PartDefinition Body_r1 = Body.addOrReplaceChild("Body_r1", CubeListBuilder.create().texOffs(0, 78).addBox(-3.0177F, 0.0748F, -1.607F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.2501F)), PartPose.offsetAndRotation(0.0F, 2.05F, -1.55F, -0.9966F, 0.147F, 0.0945F));

		PartDefinition Body_r2 = Body.addOrReplaceChild("Body_r2", CubeListBuilder.create().texOffs(64, 75).addBox(-1.0F, -0.0941F, -1.369F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.3501F)), PartPose.offsetAndRotation(0.0F, 2.05F, -1.55F, -1.0036F, 0.0F, 0.0F));

		PartDefinition Body_r3 = Body.addOrReplaceChild("Body_r3", CubeListBuilder.create().texOffs(74, 73).addBox(1.0177F, 0.0748F, -1.607F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.2501F)), PartPose.offsetAndRotation(0.0F, 2.05F, -1.55F, -0.9966F, -0.147F, -0.0945F));

		PartDefinition Body_r4 = Body.addOrReplaceChild("Body_r4", CubeListBuilder.create().texOffs(16, 80).addBox(1.5718F, 0.5574F, -0.9275F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.2501F)), PartPose.offsetAndRotation(0.0F, 2.05F, -1.55F, -0.4418F, -0.2909F, -0.5451F));

		PartDefinition Body_r5 = Body.addOrReplaceChild("Body_r5", CubeListBuilder.create().texOffs(8, 78).addBox(-3.5718F, 0.5574F, -0.9275F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.2501F)), PartPose.offsetAndRotation(0.0F, 2.05F, -1.55F, -0.4418F, 0.2909F, 0.5451F));

		PartDefinition Body_r6 = Body.addOrReplaceChild("Body_r6", CubeListBuilder.create().texOffs(74, 65).addBox(-2.9035F, 0.7221F, -0.988F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.3501F)), PartPose.offsetAndRotation(0.0F, 2.05F, -1.55F, -0.517F, 0.0869F, 0.1515F));

		PartDefinition Body_r7 = Body.addOrReplaceChild("Body_r7", CubeListBuilder.create().texOffs(72, 57).addBox(0.9035F, 0.7221F, -0.988F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.3501F)), PartPose.offsetAndRotation(0.0F, 2.05F, -1.55F, -0.517F, -0.0869F, -0.1515F));

		PartDefinition Body_r8 = Body.addOrReplaceChild("Body_r8", CubeListBuilder.create().texOffs(72, 49).addBox(-1.0F, 0.6525F, -0.8612F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.2501F)), PartPose.offsetAndRotation(0.0F, 2.05F, -1.55F, -0.5236F, 0.0F, 0.0F));

		PartDefinition Body_r9 = Body.addOrReplaceChild("Body_r9", CubeListBuilder.create().texOffs(82, 10).addBox(-4.0F, -6.0F, -4.0F, 8.0F, 7.0F, 6.0F, new CubeDeformation(0.5503F)), PartPose.offsetAndRotation(0.0F, 18.4F, 1.0F, 0.1745F, 0.0F, 0.0F));

		PartDefinition Right_Arm = partdefinition.addOrReplaceChild("Right_Arm", CubeListBuilder.create().texOffs(40, 50).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.2501F))
		.texOffs(56, 10).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.65F)), PartPose.offset(-5.0F, 2.0F, 0.0F));

		PartDefinition LeftArm_r1 = Right_Arm.addOrReplaceChild("Left Arm_r1", CubeListBuilder.create().texOffs(72, 18).addBox(-2.0F, -2.0F, -0.5F, 4.0F, 8.0F, 1.0F, new CubeDeformation(0.249F)), PartPose.offsetAndRotation(-1.0F, 2.0F, 1.6F, 0.0436F, 0.0F, 0.0F));

		PartDefinition Left_Arm_r2 = Right_Arm.addOrReplaceChild("Left_Arm_r2", CubeListBuilder.create().texOffs(72, 27).addBox(-2.0F, -2.0F, -0.5F, 4.0F, 8.0F, 1.0F, new CubeDeformation(0.249F)), PartPose.offsetAndRotation(-1.0F, 2.0F, -1.6F, -0.0436F, 0.0F, 0.0F));

		PartDefinition RightArm_r1 = Right_Arm.addOrReplaceChild("Right Arm_r1", CubeListBuilder.create().texOffs(83, 40).mirror().addBox(-2.5F, -1.0F, -2.5F, 5.0F, 2.0F, 5.0F, new CubeDeformation(0.1001F)).mirror(false), PartPose.offsetAndRotation(-0.8F, -2.0F, 0.0F, 0.0F, 0.0F, 0.2618F));

		PartDefinition Left_Arm = partdefinition.addOrReplaceChild("Left_Arm", CubeListBuilder.create().texOffs(56, 26).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.2501F))
		.texOffs(106, 31).addBox(1.0F, 2.0F, -2.0F, 0.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(56, 50).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.65F)), PartPose.offset(5.0F, 2.0F, 0.0F));

		PartDefinition Left_Arm_r3 = Left_Arm.addOrReplaceChild("Left_Arm_r3", CubeListBuilder.create().texOffs(72, 9).addBox(-2.0F, -2.0F, -0.5F, 4.0F, 8.0F, 1.0F, new CubeDeformation(0.249F)), PartPose.offsetAndRotation(1.0F, 2.0F, 1.6F, 0.0436F, 0.0F, 0.0F));

		PartDefinition Left_Arm_r4 = Left_Arm.addOrReplaceChild("Left_Arm_r4", CubeListBuilder.create().texOffs(64, 66).addBox(-2.0F, -2.0F, -0.5F, 4.0F, 8.0F, 1.0F, new CubeDeformation(0.249F)), PartPose.offsetAndRotation(1.0F, 2.0F, -1.6F, -0.0436F, 0.0F, 0.0F));

		PartDefinition Left_Arm_r5 = Left_Arm.addOrReplaceChild("Left_Arm_r5", CubeListBuilder.create().texOffs(84, 26).addBox(-2.5F, -1.0F, -2.5F, 5.0F, 2.0F, 5.0F, new CubeDeformation(0.1001F)), PartPose.offsetAndRotation(0.8F, -2.0F, 0.0F, 0.0F, 0.0F, -0.2618F));

		PartDefinition Right_Leg = partdefinition.addOrReplaceChild("Right_Leg", CubeListBuilder.create().texOffs(0, 62).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.2501F))
		.texOffs(16, 64).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.45F)), PartPose.offset(-1.9F, 12.0F, 0.0F));

		PartDefinition Right_boot = partdefinition.addOrReplaceChild("Right_boot", CubeListBuilder.create().texOffs(0, 88).addBox(-2.0F, 7.0F, -2.0F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.2501F))
		.texOffs(72, 36).addBox(-2.0F, 11.0F, -3.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.2499F))
		.texOffs(16, 65).addBox(-2.0F, 8.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.45F)), PartPose.offset(-1.9F, 12.0F, 0.0F));

		PartDefinition LeftLeg_r1 = Right_boot.addOrReplaceChild("Left Leg_r1", CubeListBuilder.create().texOffs(72, 40).addBox(-0.1F, -1.0F, -2.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.2497F)), PartPose.offsetAndRotation(-1.9002F, 10.7075F, -0.6767F, 0.5236F, 0.0F, 0.0F));

		PartDefinition Left_Leg = partdefinition.addOrReplaceChild("Left_Leg", CubeListBuilder.create().texOffs(32, 66).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.2501F))
		.texOffs(48, 66).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.45F)), PartPose.offset(1.9F, 12.0F, 0.0F));

		PartDefinition Left_boot = partdefinition.addOrReplaceChild("Left_boot", CubeListBuilder.create().texOffs(25, 84).addBox(-2.0F, 7.0F, -2.0F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.2501F))
		.texOffs(20, 48).addBox(-2.0F, 11.0F, -3.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.2499F))
		.texOffs(48, 66).addBox(-2.0F, 8.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.45F)), PartPose.offset(1.9F, 12.0F, 0.0F));

		PartDefinition Left_Leg_r2 = Left_boot.addOrReplaceChild("Left_Leg_r2", CubeListBuilder.create().texOffs(72, 38).addBox(-0.1F, -1.0F, -2.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.2497F)), PartPose.offsetAndRotation(-1.9002F, 10.7075F, -0.6767F, 0.5236F, 0.0F, 0.0F));

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
		Right_boot.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
		Left_Leg.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
		Left_boot.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
	}
}