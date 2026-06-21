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

public class GabrielArmorModel<T extends Entity> extends EntityModel<T> {
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("weaversparadise", "gabriel_armor_model"), "main");
	public final ModelPart LeftArm;
    public final ModelPart RightArm;
    public final ModelPart Body;
    public final ModelPart Head;
    public final ModelPart RightLeg;
    public final ModelPart LeftLeg;
    public final ModelPart LeftBoot;
    public final ModelPart RightBoot;

	public GabrielArmorModel(ModelPart root) {
		this.LeftArm = root.getChild("LeftArm");
		this.RightArm = root.getChild("RightArm");
		this.Body = root.getChild("Body");
		this.Head = root.getChild("Head");
		this.RightLeg = root.getChild("RightLeg");
		this.LeftLeg = root.getChild("LeftLeg");
		this.LeftBoot = root.getChild("LeftBoot");
		this.RightBoot = root.getChild("RightBoot");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition LeftArm = partdefinition.addOrReplaceChild("LeftArm", CubeListBuilder.create().texOffs(32, 71).addBox(0.0F, -1.8F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.251F))
		.texOffs(48, 71).addBox(0.0F, -1.8F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.45F)), PartPose.offset(4.0F, 2.0F, 0.0F));

		PartDefinition RightArm = partdefinition.addOrReplaceChild("RightArm", CubeListBuilder.create().texOffs(48, 55).addBox(-4.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.251F))
		.texOffs(0, 61).addBox(-4.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.45F)), PartPose.offset(-4.0F, 2.0F, 0.0F));

		PartDefinition Body = partdefinition.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(0, 45).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.2501F))
		.texOffs(0, 0).addBox(-23.5F, -13.0F, 7.0F, 47.0F, 23.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(64, 71).addBox(-4.5F, 9.4F, -1.75F, 9.0F, 1.0F, 4.0F, new CubeDeformation(0.2501F))
		.texOffs(10, 89).addBox(-0.5F, 10.5F, -2.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.2501F))
		.texOffs(38, 45).addBox(-1.5F, 12.5F, -2.5F, 3.0F, 7.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(24, 55).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.451F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition LeftArm_r1 = Body.addOrReplaceChild("LeftArm_r1", CubeListBuilder.create().texOffs(16, 65).mirror().addBox(-0.5F, -0.5F, -2.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.2501F)).mirror(false), PartPose.offsetAndRotation(5.6962F, -3.0095F, 1.8081F, -0.3927F, 0.0F, 0.1309F));

		PartDefinition Left_Arm_r2 = Body.addOrReplaceChild("Left_Arm_r2", CubeListBuilder.create().texOffs(14, 87).mirror().addBox(-1.0F, -1.0F, -2.5F, 3.0F, 1.0F, 5.0F, new CubeDeformation(0.251F)).mirror(false), PartPose.offsetAndRotation(6.1263F, -0.942F, -0.001F, 0.0F, 0.0F, 0.48F));

		PartDefinition Left_Arm_r3 = Body.addOrReplaceChild("Left_Arm_r3", CubeListBuilder.create().texOffs(16, 61).mirror().addBox(-0.5F, -0.5F, -1.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.2501F)).mirror(false), PartPose.offsetAndRotation(5.6962F, -3.0095F, -1.8081F, 0.3927F, 0.0F, 0.1309F));

		PartDefinition Left_Arm_r4 = Body.addOrReplaceChild("Left_Arm_r4", CubeListBuilder.create().texOffs(0, 77).mirror().addBox(-0.5F, -3.0F, -3.0F, 1.0F, 6.0F, 6.0F, new CubeDeformation(0.251F)).mirror(false), PartPose.offsetAndRotation(5.3F, 0.0F, 0.0F, 0.0F, 0.0F, 0.1309F));

		PartDefinition Left_Arm_r5 = Body.addOrReplaceChild("Left_Arm_r5", CubeListBuilder.create().texOffs(24, 45).mirror().addBox(-0.5F, -1.0F, -3.0F, 2.0F, 4.0F, 5.0F, new CubeDeformation(0.251F)).mirror(false), PartPose.offsetAndRotation(6.8F, 0.0F, 0.5F, 0.0F, 0.0F, 0.1309F));

		PartDefinition Left_Arm_r6 = Body.addOrReplaceChild("Left_Arm_r6", CubeListBuilder.create().texOffs(78, 77).mirror().addBox(-1.0F, -1.0F, -2.5F, 3.0F, 2.0F, 5.0F, new CubeDeformation(0.2501F)).mirror(false), PartPose.offsetAndRotation(7.3913F, 0.7301F, 0.0F, 0.0F, 0.0F, 0.9599F));

		PartDefinition Left_Arm_r7 = Body.addOrReplaceChild("Left_Arm_r7", CubeListBuilder.create().texOffs(30, 87).mirror().addBox(-1.0F, -1.0F, -2.5F, 2.0F, 2.0F, 5.0F, new CubeDeformation(0.2501F)).mirror(false), PartPose.offsetAndRotation(8.1037F, 2.6034F, 0.0F, 0.0F, 0.0F, 0.3491F));

		PartDefinition RightArm_r1 = Body.addOrReplaceChild("RightArm_r1", CubeListBuilder.create().texOffs(0, 77).addBox(-0.5F, -3.0F, -3.0F, 1.0F, 6.0F, 6.0F, new CubeDeformation(0.251F)), PartPose.offsetAndRotation(-5.3F, 0.0F, 0.0F, 0.0F, 0.0F, -0.1309F));

		PartDefinition Right_Arm_r2 = Body.addOrReplaceChild("Right_Arm_r2", CubeListBuilder.create().texOffs(16, 61).addBox(-0.5F, -0.5F, -1.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.2501F)), PartPose.offsetAndRotation(-5.6962F, -3.0095F, -1.8081F, 0.3927F, 0.0F, -0.1309F));

		PartDefinition Right_Arm_r3 = Body.addOrReplaceChild("Right_Arm_r3", CubeListBuilder.create().texOffs(16, 65).addBox(-0.5F, -0.5F, -2.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.2501F)), PartPose.offsetAndRotation(-5.6962F, -3.0095F, 1.8081F, -0.3927F, 0.0F, -0.1309F));

		PartDefinition Right_Arm_r4 = Body.addOrReplaceChild("Right_Arm_r4", CubeListBuilder.create().texOffs(24, 45).addBox(-1.5F, -1.0F, -3.0F, 2.0F, 4.0F, 5.0F, new CubeDeformation(0.251F)), PartPose.offsetAndRotation(-6.8F, 0.0F, 0.5F, 0.0F, 0.0F, -0.1309F));

		PartDefinition Right_Arm_r5 = Body.addOrReplaceChild("Right_Arm_r5", CubeListBuilder.create().texOffs(78, 77).addBox(-2.0F, -1.0F, -2.5F, 3.0F, 2.0F, 5.0F, new CubeDeformation(0.2501F)), PartPose.offsetAndRotation(-7.3913F, 0.7301F, 0.0F, 0.0F, 0.0F, -0.9599F));

		PartDefinition Right_Arm_r6 = Body.addOrReplaceChild("Right_Arm_r6", CubeListBuilder.create().texOffs(14, 87).addBox(-2.0F, -1.0F, -2.5F, 3.0F, 1.0F, 5.0F, new CubeDeformation(0.251F)), PartPose.offsetAndRotation(-6.1263F, -0.942F, -0.001F, 0.0F, 0.0F, -0.48F));

		PartDefinition Right_Arm_r7 = Body.addOrReplaceChild("Right_Arm_r7", CubeListBuilder.create().texOffs(30, 87).addBox(-1.0F, -1.0F, -2.5F, 2.0F, 2.0F, 5.0F, new CubeDeformation(0.2501F)), PartPose.offsetAndRotation(-8.1037F, 2.6034F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition Body_r1 = Body.addOrReplaceChild("Body_r1", CubeListBuilder.create().texOffs(92, 35).addBox(-2.0F, -1.0F, -2.0F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.2501F)), PartPose.offsetAndRotation(2.4F, 11.0F, -0.25F, 0.0F, -0.0873F, -0.2618F));

		PartDefinition Body_r2 = Body.addOrReplaceChild("Body_r2", CubeListBuilder.create().texOffs(92, 29).addBox(-2.0F, -1.0F, -2.0F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.2501F)), PartPose.offsetAndRotation(-2.4F, 11.0F, -0.25F, 0.0F, 0.0873F, 0.2618F));

		PartDefinition Body_r3 = Body.addOrReplaceChild("Body_r3", CubeListBuilder.create().texOffs(0, 89).addBox(1.0F, -1.0F, -2.0F, 1.0F, 4.0F, 4.0F, new CubeDeformation(0.2501F)), PartPose.offsetAndRotation(2.9F, 10.8F, -0.05F, 0.0F, 0.0F, -0.2618F));

		PartDefinition Body_r4 = Body.addOrReplaceChild("Body_r4", CubeListBuilder.create().texOffs(58, 89).addBox(-2.0F, -1.0F, -2.0F, 1.0F, 4.0F, 4.0F, new CubeDeformation(0.2501F)), PartPose.offsetAndRotation(-2.9F, 10.8F, -0.05F, 0.0F, 0.0F, 0.2618F));

		PartDefinition Body_r5 = Body.addOrReplaceChild("Body_r5", CubeListBuilder.create().texOffs(38, 52).addBox(-0.5F, -1.0F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.2501F)), PartPose.offsetAndRotation(0.0F, 10.3402F, -1.9646F, -0.4363F, 0.0F, 0.0F));

		PartDefinition Body_r6 = Body.addOrReplaceChild("Body_r6", CubeListBuilder.create().texOffs(76, 48).addBox(-4.5F, 0.0F, -2.0F, 9.0F, 1.0F, 4.0F, new CubeDeformation(0.2501F)), PartPose.offsetAndRotation(0.0F, 11.3498F, 3.0347F, -1.2217F, 0.0F, 0.0F));

		PartDefinition Head = partdefinition.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(44, 23).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.2501F))
		.texOffs(44, 39).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.5501F)), PartPose.offset(0.0F, -1.0F, 0.0F));

		PartDefinition Head_r1 = Head.addOrReplaceChild("Head_r1", CubeListBuilder.create().texOffs(0, 23).mirror().addBox(-11.0F, -11.0F, 0.0F, 22.0F, 22.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, -9.0F, 5.0F, 0.0F, 0.0F, -0.7854F));

		PartDefinition Head_r2 = Head.addOrReplaceChild("Head_r2", CubeListBuilder.create().texOffs(16, 69).addBox(-1.5F, -0.5F, -0.5F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.2501F)), PartPose.offsetAndRotation(0.0F, 0.341F, -4.1569F, -0.6981F, 0.0F, 0.0F));

		PartDefinition Head_r3 = Head.addOrReplaceChild("Head_r3", CubeListBuilder.create().texOffs(76, 39).addBox(-1.5F, -0.5F, -4.0F, 3.0F, 1.0F, 8.0F, new CubeDeformation(0.2501F)), PartPose.offsetAndRotation(0.0F, -0.5F, -1.0F, 0.3927F, 0.0F, 0.0F));

		PartDefinition RightLeg = partdefinition.addOrReplaceChild("RightLeg", CubeListBuilder.create().texOffs(64, 55).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.251F))
		.texOffs(0, 97).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.451F)), PartPose.offset(-1.9F, 12.0F, 0.0F));

		PartDefinition LeftLeg = partdefinition.addOrReplaceChild("LeftLeg", CubeListBuilder.create().texOffs(16, 71).addBox(-2.2F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.4501F))
		.texOffs(16, 71).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.4501F))
		.texOffs(76, 23).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.2501F)), PartPose.offset(1.9F, 12.0F, 0.0F));

		PartDefinition LeftBoot = partdefinition.addOrReplaceChild("LeftBoot", CubeListBuilder.create().texOffs(31, 94).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.4501F))
		.texOffs(87, 93).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.2501F))
		.texOffs(80, 90).addBox(-2.0F, 11.0F, -4.0F, 4.0F, 1.0F, 2.0F, new CubeDeformation(0.2501F)), PartPose.offset(1.9F, 12.0F, 0.0F));

		PartDefinition LeftLeg_r1 = LeftBoot.addOrReplaceChild("LeftLeg_r1", CubeListBuilder.create().texOffs(68, 90).addBox(-0.1F, -2.0F, -2.0F, 4.0F, 1.0F, 2.0F, new CubeDeformation(0.25F)), PartPose.offsetAndRotation(-1.9F, 11.7068F, -1.2153F, 0.48F, 0.0F, 0.0F));

		PartDefinition Left_Leg_r2 = LeftBoot.addOrReplaceChild("Left_Leg_r2", CubeListBuilder.create().texOffs(92, 23).addBox(-2.0F, -1.0F, -0.5F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.25F)), PartPose.offsetAndRotation(0.0F, 6.3F, -1.9F, 0.3491F, 0.0F, 0.0F));

		PartDefinition RightBoot = partdefinition.addOrReplaceChild("RightBoot", CubeListBuilder.create().texOffs(87, 93).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.251F))
		.texOffs(80, 90).mirror().addBox(-2.0F, 11.0F, -4.0F, 4.0F, 1.0F, 2.0F, new CubeDeformation(0.2501F)).mirror(false)
		.texOffs(31, 94).mirror().addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.4501F)).mirror(false), PartPose.offset(-1.9F, 12.0F, 0.0F));

		PartDefinition RightLeg_r1 = RightBoot.addOrReplaceChild("RightLeg_r1", CubeListBuilder.create().texOffs(68, 90).mirror().addBox(-3.9F, -2.0F, -2.0F, 4.0F, 1.0F, 2.0F, new CubeDeformation(0.25F)).mirror(false), PartPose.offsetAndRotation(1.9F, 11.7068F, -1.2153F, 0.48F, 0.0F, 0.0F));

		PartDefinition Right_Leg_r2 = RightBoot.addOrReplaceChild("Right_Leg_r2", CubeListBuilder.create().texOffs(92, 23).mirror().addBox(-2.0F, -1.0F, -0.5F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.25F)).mirror(false), PartPose.offsetAndRotation(0.0F, 6.3F, -1.9F, 0.3491F, 0.0F, 0.0F));

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
		LeftLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
		LeftBoot.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
		RightBoot.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
	}
}