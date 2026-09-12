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

public class ExpieModel<T extends Entity> extends EntityModel<T> {
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("weaversparadise", "expie_model"), "main");
	public final ModelPart Head;
	public final ModelPart Body;
	public final ModelPart RightArm;
	public final ModelPart LeftArm;
	public final ModelPart RightLeg;
	public final ModelPart LeftLeg;

	public ExpieModel(ModelPart root) {
		this.Head = root.getChild("Head");
		this.Body = root.getChild("Body");
		this.RightArm = root.getChild("RightArm");
		this.LeftArm = root.getChild("LeftArm");
		this.RightLeg = root.getChild("RightLeg");
		this.LeftLeg = root.getChild("LeftLeg");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Head = partdefinition.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(0, 17).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.2501F))
		.texOffs(32, 17).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.85F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition Head_r1 = Head.addOrReplaceChild("Head_r1", CubeListBuilder.create().texOffs(58, 65).addBox(1.9657F, -3.5554F, 1.0916F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.2501F))
		.texOffs(46, 65).addBox(1.9657F, -3.6418F, -1.3574F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.2503F)), PartPose.offsetAndRotation(0.0F, -5.5591F, 4.0879F, -1.4385F, 0.6006F, -0.7434F));

		PartDefinition Head_r2 = Head.addOrReplaceChild("Head_r2", CubeListBuilder.create().texOffs(8, 65).addBox(1.9657F, -4.7973F, -3.6662F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.2499F)), PartPose.offsetAndRotation(0.0F, -5.5591F, 4.0879F, -2.2414F, 0.6006F, -0.7434F));

		PartDefinition Head_r3 = Head.addOrReplaceChild("Head_r3", CubeListBuilder.create().texOffs(16, 65).addBox(3.7128F, -1.8165F, -0.5498F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.2503F))
		.texOffs(28, 65).addBox(3.7128F, -1.7301F, 1.8993F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.2501F)), PartPose.offsetAndRotation(0.0F, -5.5591F, 4.0879F, -1.4586F, 0.3166F, -0.0457F));

		PartDefinition Head_r4 = Head.addOrReplaceChild("Head_r4", CubeListBuilder.create().texOffs(64, 56).addBox(3.7128F, -4.1103F, -1.7922F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.2499F)), PartPose.offsetAndRotation(0.0F, -5.5591F, 4.0879F, -2.2615F, 0.3166F, -0.0457F));

		PartDefinition Head_r5 = Head.addOrReplaceChild("Head_r5", CubeListBuilder.create().texOffs(64, 40).addBox(-3.0F, -0.5F, -1.0F, 6.0F, 2.0F, 2.0F, new CubeDeformation(0.2501F)), PartPose.offsetAndRotation(0.0F, -1.8F, -3.7F, 0.3491F, 0.0F, 0.0F));

		PartDefinition Head_r6 = Head.addOrReplaceChild("Head_r6", CubeListBuilder.create().texOffs(34, 65).addBox(-4.7128F, -1.7301F, 1.8993F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.2501F))
		.texOffs(22, 65).addBox(-4.7128F, -1.8165F, -0.5498F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.2503F)), PartPose.offsetAndRotation(0.0F, -5.5591F, 4.0879F, -1.4586F, -0.3166F, 0.0457F));

		PartDefinition Head_r7 = Head.addOrReplaceChild("Head_r7", CubeListBuilder.create().texOffs(64, 62).addBox(-4.7128F, -4.1103F, -1.7922F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.2499F)), PartPose.offsetAndRotation(0.0F, -5.5591F, 4.0879F, -2.2615F, -0.3166F, 0.0457F));

		PartDefinition Head_r8 = Head.addOrReplaceChild("Head_r8", CubeListBuilder.create().texOffs(0, 65).addBox(-2.9657F, -4.7973F, -3.6662F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.2499F)), PartPose.offsetAndRotation(0.0F, -5.5591F, 4.0879F, -2.2414F, -0.6006F, 0.7434F));

		PartDefinition Head_r9 = Head.addOrReplaceChild("Head_r9", CubeListBuilder.create().texOffs(52, 65).addBox(-2.9657F, -3.5554F, 1.0916F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.2501F))
		.texOffs(40, 65).addBox(-2.9657F, -3.6418F, -1.3574F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.2503F)), PartPose.offsetAndRotation(0.0F, -5.5591F, 4.0879F, -1.4385F, -0.6006F, 0.7434F));

		PartDefinition Body = partdefinition.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(0, 33).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.2505F))
		.texOffs(24, 33).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.45F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition Body_r1 = Body.addOrReplaceChild("Body_r1", CubeListBuilder.create().texOffs(64, 45).addBox(-2.0F, -15.0F, 4.0F, 4.0F, 4.0F, 2.0F, new CubeDeformation(0.2505F)), PartPose.offsetAndRotation(0.0F, 20.3522F, -7.5151F, -0.5672F, 0.0F, 0.0F));

		PartDefinition Body_r2 = Body.addOrReplaceChild("Body_r2", CubeListBuilder.create().texOffs(66, 0).addBox(-1.0F, -14.0F, 4.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.2505F)), PartPose.offsetAndRotation(0.0F, 19.8149F, -8.3585F, -0.5672F, 0.0F, 0.0F));

		PartDefinition Body_r3 = Body.addOrReplaceChild("Body_r3", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -16.0F, 2.0F, 6.0F, 6.0F, 11.0F, new CubeDeformation(0.2508F)), PartPose.offsetAndRotation(0.0F, 22.5014F, -4.1415F, -0.5672F, 0.0F, 0.0F));

		PartDefinition Body_r4 = Body.addOrReplaceChild("Body_r4", CubeListBuilder.create().texOffs(64, 51).addBox(-2.0F, -15.0F, 2.0F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.2505F)), PartPose.offsetAndRotation(0.0F, 28.4117F, 5.1358F, -0.5672F, 0.0F, 0.0F));

		PartDefinition Body_r5 = Body.addOrReplaceChild("Body_r5", CubeListBuilder.create().texOffs(64, 32).addBox(-3.0F, -16.0F, 11.0F, 6.0F, 6.0F, 2.0F, new CubeDeformation(0.2505F)), PartPose.offsetAndRotation(0.0F, 23.576F, -2.4547F, -0.5672F, 0.0F, 0.0F));

		PartDefinition RightArm = partdefinition.addOrReplaceChild("RightArm", CubeListBuilder.create().texOffs(34, 0).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.2503F))
		.texOffs(64, 16).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.45F)), PartPose.offset(-5.0F, 2.0F, 0.0F));

		PartDefinition LeftArm = partdefinition.addOrReplaceChild("LeftArm", CubeListBuilder.create().texOffs(48, 33).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.2503F))
		.texOffs(0, 49).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.45F)), PartPose.offset(5.0F, 2.0F, 0.0F));

		PartDefinition RightLeg = partdefinition.addOrReplaceChild("RightLeg", CubeListBuilder.create().texOffs(16, 49).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.2499F))
		.texOffs(18, 72).addBox(-2.0F, 11.0F, -3.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.2489F))
		.texOffs(32, 49).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.45F)), PartPose.offset(-1.9F, 12.0F, 0.0F));

		PartDefinition LeftLeg = partdefinition.addOrReplaceChild("LeftLeg", CubeListBuilder.create().texOffs(48, 49).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.2499F))
		.texOffs(35, 72).addBox(-2.0F, 11.0F, -3.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.2495F))
		.texOffs(50, 0).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.45F)), PartPose.offset(1.9F, 12.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(@NotNull T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {}

	@Override
	public void renderToBuffer(@NotNull PoseStack poseStack, @NotNull VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
		Head.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
		Body.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
		RightArm.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
		LeftArm.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
		RightLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
		LeftLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
	}
}