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

public class MikkelaArmorModel<T extends Entity> extends EntityModel<T> {
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("weaversparadise", "mikkela_armor_model"), "main");
	public final ModelPart LeftArm;
	public final ModelPart RightArm;
	public final ModelPart Body;
	public final ModelPart Head;
	public final ModelPart RightLeg;
	public final ModelPart LeftLeg;

	public MikkelaArmorModel(ModelPart root) {
		this.LeftArm = root.getChild("LeftArm");
		this.RightArm = root.getChild("RightArm");
		this.Body = root.getChild("Body");
		this.Head = root.getChild("Head");
		this.RightLeg = root.getChild("RightLeg");
		this.LeftLeg = root.getChild("LeftLeg");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition LeftArm = partdefinition.addOrReplaceChild("LeftArm", CubeListBuilder.create().texOffs(56, 52).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.251F))
		.texOffs(16, 64).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.45F)), PartPose.offset(5.0F, 2.0F, 0.0F));

		PartDefinition RightArm_r1 = LeftArm.addOrReplaceChild("RightArm_r1", CubeListBuilder.create().texOffs(62, 16).addBox(-2.0F, 0.0F, 1.0F, 4.0F, 3.0F, 1.0F, new CubeDeformation(0.25F)), PartPose.offsetAndRotation(1.0F, 4.5F, 0.2F, 0.3491F, 0.0F, 0.0F));

		PartDefinition Right_Arm_r2 = LeftArm.addOrReplaceChild("Right_Arm_r2", CubeListBuilder.create().texOffs(68, 46).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 3.0F, 1.0F, new CubeDeformation(0.25F)), PartPose.offsetAndRotation(1.0F, 4.5F, -0.2F, -0.3491F, 0.0F, 0.0F));

		PartDefinition RightArm = partdefinition.addOrReplaceChild("RightArm", CubeListBuilder.create().texOffs(52, 0).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.251F))
		.texOffs(40, 52).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.45F)), PartPose.offset(-5.0F, 2.0F, 0.0F));

		PartDefinition Right_Arm_r3 = RightArm.addOrReplaceChild("Right_Arm_r3", CubeListBuilder.create().texOffs(52, 16).addBox(-2.0F, 0.0F, 1.0F, 4.0F, 3.0F, 1.0F, new CubeDeformation(0.25F)), PartPose.offsetAndRotation(-1.0F, 4.5F, 0.2F, 0.3491F, 0.0F, 0.0F));

		PartDefinition Right_Arm_r4 = RightArm.addOrReplaceChild("Right_Arm_r4", CubeListBuilder.create().texOffs(16, 44).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 3.0F, 1.0F, new CubeDeformation(0.25F)), PartPose.offsetAndRotation(-1.0F, 4.5F, -0.2F, -0.3491F, 0.0F, 0.0F));

		PartDefinition Body = partdefinition.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(16, 48).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.2504F))
		.texOffs(48, 22).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.45F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition LeftArm_r1 = Body.addOrReplaceChild("LeftArm_r1", CubeListBuilder.create().texOffs(72, 26).mirror().addBox(-3.7126F, -1.1976F, -2.0F, 5.0F, 4.0F, 4.0F, new CubeDeformation(0.001F)).mirror(false), PartPose.offsetAndRotation(3.6365F, 5.2239F, -3.4391F, -0.6429F, -0.1321F, -0.2614F));

		PartDefinition Left_Arm_r2 = Body.addOrReplaceChild("Left_Arm_r2", CubeListBuilder.create().texOffs(72, 16).mirror().addBox(-1.4084F, -4.3283F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0001F)).mirror(false), PartPose.offsetAndRotation(3.6365F, 5.2239F, -3.4391F, -0.6247F, 0.2097F, 0.1938F));

		PartDefinition Right_Arm_r5 = Body.addOrReplaceChild("Right_Arm_r5", CubeListBuilder.create().texOffs(72, 26).addBox(-1.2874F, -1.1976F, -2.0F, 5.0F, 4.0F, 4.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(-3.6365F, 5.2239F, -3.4391F, -0.6429F, 0.1321F, 0.2614F));

		PartDefinition Right_Arm_r6 = Body.addOrReplaceChild("Right_Arm_r6", CubeListBuilder.create().texOffs(72, 16).addBox(-2.5916F, -4.3283F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0001F)), PartPose.offsetAndRotation(-3.6365F, 5.2239F, -3.4391F, -0.6247F, -0.2097F, -0.1938F));

		PartDefinition Head = partdefinition.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.2503F))
		.texOffs(32, 0).addBox(-4.0F, -0.4F, 2.0F, 8.0F, 20.0F, 2.0F, new CubeDeformation(0.251F))
		.texOffs(108, 88).addBox(-4.0F, -0.4F, 2.0F, 8.0F, 20.0F, 2.0F, new CubeDeformation(0.551F))
		.texOffs(0, 16).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.6F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition Head_r1 = Head.addOrReplaceChild("Head_r1", CubeListBuilder.create().texOffs(112, 47).addBox(-4.0F, -10.0F, -1.0F, 6.0F, 24.0F, 2.0F, new CubeDeformation(0.55F))
		.texOffs(0, 44).addBox(-4.0F, -10.0F, -1.0F, 6.0F, 24.0F, 2.0F, new CubeDeformation(0.2501F)), PartPose.offsetAndRotation(-2.7353F, 5.5032F, 3.0F, 0.0F, 0.0F, 0.2618F));

		PartDefinition Head_r2 = Head.addOrReplaceChild("Head_r2", CubeListBuilder.create().texOffs(91, 24).addBox(-2.0F, -10.0F, -1.0F, 6.0F, 24.0F, 2.0F, new CubeDeformation(0.5501F))
		.texOffs(32, 22).addBox(-2.0F, -10.0F, -1.0F, 6.0F, 24.0F, 2.0F, new CubeDeformation(0.2501F)), PartPose.offsetAndRotation(2.7353F, 5.5032F, 3.0F, 0.0F, 0.0F, -0.2618F));

		PartDefinition Head_r3 = Head.addOrReplaceChild("Head_r3", CubeListBuilder.create().texOffs(40, 48).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.2503F)), PartPose.offsetAndRotation(0.0F, -5.0F, 4.0F, -0.7854F, 0.0F, 0.0F));

		PartDefinition Head_r4 = Head.addOrReplaceChild("Head_r4", CubeListBuilder.create().texOffs(0, 32).addBox(-4.0F, -2.0F, -4.0F, 8.0F, 4.0F, 8.0F, new CubeDeformation(0.6503F)), PartPose.offsetAndRotation(0.0F, -6.0F, -0.4F, -0.096F, 0.0F, 0.0F));

		PartDefinition RightLeg = partdefinition.addOrReplaceChild("RightLeg", CubeListBuilder.create().texOffs(68, 0).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 10.0F, 4.0F, new CubeDeformation(0.251F))
		.texOffs(32, 68).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.45F)), PartPose.offset(-1.9F, 12.0F, 0.0F));

		PartDefinition LeftLeg = partdefinition.addOrReplaceChild("LeftLeg", CubeListBuilder.create().texOffs(48, 68).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 10.0F, 4.0F, new CubeDeformation(0.2502F))
		.texOffs(64, 68).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.45F)), PartPose.offset(1.9F, 12.0F, 0.0F));

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
	}
}