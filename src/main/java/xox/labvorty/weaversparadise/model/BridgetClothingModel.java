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

public class BridgetClothingModel<T extends Entity> extends EntityModel<T> {
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("weaversparadise", "bridget_clothing_model"), "main");
	public final ModelPart RightLeg;
    public final ModelPart RightLegPants;
    public final ModelPart LeftLeg;
    public final ModelPart LeftLegPants;
    public final ModelPart RightArm;
    public final ModelPart LeftArm;
    public final ModelPart Body;
    public final ModelPart Head;

	public BridgetClothingModel(ModelPart root) {
		this.RightLeg = root.getChild("RightLeg");
		this.RightLegPants = root.getChild("RightLegPants");
		this.LeftLeg = root.getChild("LeftLeg");
		this.LeftLegPants = root.getChild("LeftLegPants");
		this.RightArm = root.getChild("RightArm");
		this.LeftArm = root.getChild("LeftArm");
		this.Body = root.getChild("Body");
		this.Head = root.getChild("Head");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition RightLeg = partdefinition.addOrReplaceChild("RightLeg", CubeListBuilder.create().texOffs(16, 60).addBox(-2.05F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.2501F))
		.texOffs(68, 64).addBox(-2.05F, 9.0F, -3.0F, 4.0F, 3.0F, 5.0F, new CubeDeformation(0.25F))
		.texOffs(110, 83).addBox(-2.05F, 9.0F, -3.0F, 4.0F, 3.0F, 5.0F, new CubeDeformation(0.4F))
		.texOffs(60, 32).addBox(-2.05F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.4F)), PartPose.offset(-1.9F, 12.0F, 0.0F));

		PartDefinition Right_Leg_r1 = RightLeg.addOrReplaceChild("Right_Leg_r1", CubeListBuilder.create().texOffs(117, 97).addBox(-3.8F, -4.0F, -3.0F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.3F)), PartPose.offsetAndRotation(1.75F, 9.9F, 2.4F, 0.7854F, 0.0F, 0.0F));

		PartDefinition RightLegPants = partdefinition.addOrReplaceChild("RightLegPants", CubeListBuilder.create().texOffs(92, 48).addBox(-2.05F, 0.0F, -2.0F, 4.0F, 2.0F, 4.0F, new CubeDeformation(0.2501F))
		.texOffs(76, 32).addBox(-2.05F, 0.0F, -2.0F, 4.0F, 2.0F, 4.0F, new CubeDeformation(0.4F)), PartPose.offset(-1.9F, 12.0F, 0.0F));

		PartDefinition LeftLeg = partdefinition.addOrReplaceChild("LeftLeg", CubeListBuilder.create().texOffs(60, 48).addBox(-2.05F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.2501F))
		.texOffs(72, 0).addBox(-2.05F, 9.0F, -3.0F, 4.0F, 3.0F, 5.0F, new CubeDeformation(0.25F))
		.texOffs(90, 0).addBox(-2.05F, 9.0F, -3.0F, 4.0F, 3.0F, 5.0F, new CubeDeformation(0.4F))
		.texOffs(32, 63).addBox(-2.05F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.35F)), PartPose.offset(1.9F, 12.0F, 0.0F));

		PartDefinition Right_Leg_r2 = LeftLeg.addOrReplaceChild("Right_Leg_r2", CubeListBuilder.create().texOffs(117, 97).addBox(-3.9F, -4.0F, -3.0F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.3F)), PartPose.offsetAndRotation(1.85F, 9.9F, 2.4F, 0.7854F, 0.0F, 0.0F));

		PartDefinition LeftLegPants = partdefinition.addOrReplaceChild("LeftLegPants", CubeListBuilder.create().texOffs(76, 48).addBox(-2.05F, 0.0F, -2.0F, 4.0F, 2.0F, 4.0F, new CubeDeformation(0.2501F))
		.texOffs(92, 32).addBox(-2.05F, 0.0F, -2.0F, 4.0F, 2.0F, 4.0F, new CubeDeformation(0.35F)), PartPose.offset(1.9F, 12.0F, 0.0F));

		PartDefinition RightArm = partdefinition.addOrReplaceChild("RightArm", CubeListBuilder.create().texOffs(44, 47).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.2501F))
		.texOffs(0, 47).addBox(-3.7F, 2.0F, -3.0F, 5.0F, 7.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(116, -6).addBox(-4.0F, 2.0F, -3.0F, 0.0F, 7.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(56, 0).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.349F))
		.texOffs(126, 8).addBox(-4.2F, 9.2F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, 2.0F, 0.0F));

		PartDefinition LeftArm = partdefinition.addOrReplaceChild("LeftArm", CubeListBuilder.create().texOffs(56, 16).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.2501F))
		.texOffs(116, -6).addBox(3.7F, 2.0F, -3.0F, 0.0F, 7.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(22, 47).addBox(-1.4F, 2.0F, -3.0F, 5.0F, 7.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(126, 8).addBox(3.2F, 9.2F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(0, 60).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.349F)), PartPose.offset(5.0F, 2.0F, 0.0F));

		PartDefinition Body = partdefinition.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(32, 15).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.2501F))
		.texOffs(32, 31).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.35F))
		.texOffs(112, 37).addBox(-4.0F, 0.0F, -2.4F, 8.0F, 12.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition Body_r1 = Body.addOrReplaceChild("Body_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-6.0F, -5.7F, -1.25F, 12.0F, 3.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(48, 64).addBox(-2.5F, -6.2F, -3.25F, 5.0F, 4.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(32, 60).addBox(-1.5F, -4.2F, -5.25F, 3.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.6776F, 13.7F, -3.1441F, 0.0F, 0.8727F, 0.0F));

		PartDefinition Body_r2 = Body.addOrReplaceChild("Body_r2", CubeListBuilder.create().texOffs(104, 65).addBox(-5.2F, 0.0F, -6.25F, 4.0F, 0.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(104, 65).addBox(-0.3F, 0.0F, -6.25F, 4.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.6776F, 13.7F, -3.1441F, 0.8727F, 0.0F, 1.5708F));

		PartDefinition Body_r3 = Body.addOrReplaceChild("Body_r3", CubeListBuilder.create().texOffs(104, 65).addBox(-2.7F, -4.25F, -1.5F, 4.0F, 0.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(104, 65).addBox(2.2F, -4.25F, -1.5F, 4.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.6776F, 13.7F, -3.1441F, 2.4435F, 0.0F, 1.5708F));

		PartDefinition Head = partdefinition.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(0, 15).addBox(-4.0F, -9.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.2501F))
		.texOffs(0, 31).addBox(-4.0F, -8.7F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.6F))
		.texOffs(0, 77).addBox(-4.0F, -8.7F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.8F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
		RightLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
		RightLegPants.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
		LeftLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
		LeftLegPants.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
		RightArm.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
		LeftArm.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
		Body.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
		Head.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
	}
}