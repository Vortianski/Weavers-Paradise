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

public class NikoArmorModel<T extends Entity> extends EntityModel<T> {
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("weaversparadise", "niko_armor_model"), "main");
	public final ModelPart LeftArm;
    public final ModelPart RightArm;
    public final ModelPart Body;
    public final ModelPart Head;
    public final ModelPart RightLeg;
    public final ModelPart LeftLeg;
    public final ModelPart LeftBoot;
    public final ModelPart RightBoot;

	public NikoArmorModel(ModelPart root) {
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

		PartDefinition LeftArm = partdefinition.addOrReplaceChild("LeftArm", CubeListBuilder.create().texOffs(68, 68).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.251F))
		.texOffs(24, 72).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.45F)), PartPose.offset(5.0F, 2.0F, 0.0F));

		PartDefinition LeftArm_r1 = LeftArm.addOrReplaceChild("Left Arm_r1", CubeListBuilder.create().texOffs(68, 84).addBox(-2.0F, -5.0F, -2.0F, 4.0F, 10.0F, 4.0F, new CubeDeformation(0.2501F)), PartPose.offsetAndRotation(1.0F, 3.3152F, 0.9737F, 0.1745F, 0.0F, 0.0F));

		PartDefinition Left_Arm_r2 = LeftArm.addOrReplaceChild("Left_Arm_r2", CubeListBuilder.create().texOffs(80, 36).addBox(-2.0F, -6.0F, -2.0F, 4.0F, 10.0F, 4.0F, new CubeDeformation(0.2501F)), PartPose.offsetAndRotation(1.0F, 4.3F, -1.1F, -0.1745F, 0.0F, 0.0F));

		PartDefinition bone2 = LeftArm.addOrReplaceChild("bone2", CubeListBuilder.create(), PartPose.offset(1.0F, 3.3152F, 0.9737F));

		PartDefinition RightArm = partdefinition.addOrReplaceChild("RightArm", CubeListBuilder.create().texOffs(64, 36).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.4501F))
		.texOffs(72, 52).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.251F)), PartPose.offset(-5.0F, 2.0F, 0.0F));

		PartDefinition RightArm_r1 = RightArm.addOrReplaceChild("Right Arm_r1", CubeListBuilder.create().texOffs(84, 68).addBox(-2.0F, -6.0F, -2.0F, 4.0F, 10.0F, 4.0F, new CubeDeformation(0.2501F)), PartPose.offsetAndRotation(-1.0F, 4.3F, -1.1F, -0.1745F, 0.0F, 0.0F));

		PartDefinition Right_Arm_r2 = RightArm.addOrReplaceChild("Right_Arm_r2", CubeListBuilder.create().texOffs(0, 73).addBox(-2.0F, -5.0F, -2.0F, 4.0F, 10.0F, 4.0F, new CubeDeformation(0.2501F)), PartPose.offsetAndRotation(-1.0F, 3.3152F, 0.9737F, 0.1745F, 0.0F, 0.0F));

		PartDefinition Body = partdefinition.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(0, 48).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.2504F))
		.texOffs(48, 56).addBox(-4.0F, 7.9F, -2.0F, 8.0F, 8.0F, 4.0F, new CubeDeformation(0.3505F))
		.texOffs(24, 56).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.45F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition Body_r1 = Body.addOrReplaceChild("Body_r1", CubeListBuilder.create().texOffs(58, 68).addBox(-2.0F, -1.0F, 2.5F, 5.0F, 23.0F, 0.0F, new CubeDeformation(0.0003F))
		.texOffs(46, 9).addBox(-2.0F, -1.0F, -3.0F, 4.0F, 2.0F, 5.0F, new CubeDeformation(0.2504F)), PartPose.offsetAndRotation(-1.9F, 0.0F, 0.4F, 0.0F, 0.0F, 0.2618F));

		PartDefinition Body_r2 = Body.addOrReplaceChild("Body_r2", CubeListBuilder.create().texOffs(48, 68).addBox(-3.0F, -1.0F, 2.5F, 5.0F, 23.0F, 0.0F, new CubeDeformation(0.0004F))
		.texOffs(82, 0).addBox(-4.0F, -1.0F, -3.0F, 6.0F, 2.0F, 5.0F, new CubeDeformation(0.34F)), PartPose.offsetAndRotation(1.9F, 0.0F, 0.4F, 0.0F, 0.0F, -0.2618F));

		PartDefinition Body_r3 = Body.addOrReplaceChild("Body_r3", CubeListBuilder.create().texOffs(28, 99).addBox(-2.5F, -3.5F, -2.0F, 1.0F, 7.0F, 4.0F, new CubeDeformation(0.34F)), PartPose.offsetAndRotation(-1.964F, 12.7368F, 0.0F, 0.0F, 0.0F, 0.2182F));

		PartDefinition Body_r4 = Body.addOrReplaceChild("Body_r4", CubeListBuilder.create().texOffs(18, 99).addBox(1.5F, -3.5F, -2.0F, 1.0F, 7.0F, 4.0F, new CubeDeformation(0.34F)), PartPose.offsetAndRotation(1.964F, 12.7368F, 0.0F, 0.0F, 0.0F, -0.2182F));

		PartDefinition Head = partdefinition.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(0, 16).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.2501F))
		.texOffs(46, 0).addBox(-5.0F, -9.0F, -4.0F, 10.0F, 1.0F, 8.0F, new CubeDeformation(0.2501F))
		.texOffs(32, 46).addBox(-4.0F, -11.0F, -4.0F, 8.0F, 2.0F, 8.0F, new CubeDeformation(0.251F))
		.texOffs(96, 47).addBox(-4.0F, -8.0F, -8.1104F, 8.0F, 1.0F, 1.0F, new CubeDeformation(0.2503F))
		.texOffs(0, 0).addBox(-4.0F, -8.0F, -7.0F, 8.0F, 1.0F, 15.0F, new CubeDeformation(0.251F))
		.texOffs(0, 32).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.501F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition Head_r1 = Head.addOrReplaceChild("Head_r1", CubeListBuilder.create().texOffs(44, 76).addBox(1.5F, -2.0F, 0.0F, 1.0F, 4.0F, 0.0F, new CubeDeformation(0.0001F)), PartPose.offsetAndRotation(6.0F, 0.0F, -2.5F, 0.0F, 0.0F, -1.5272F));

		PartDefinition Head_r2 = Head.addOrReplaceChild("Head_r2", CubeListBuilder.create().texOffs(40, 76).addBox(1.5F, -2.0F, 0.0F, 1.0F, 4.0F, 0.0F, new CubeDeformation(0.0001F)), PartPose.offsetAndRotation(4.9F, 1.3F, -2.5F, 0.0F, 0.0F, -1.2654F));

		PartDefinition Head_r3 = Head.addOrReplaceChild("Head_r3", CubeListBuilder.create().texOffs(24, 52).addBox(-1.5F, -2.0F, 0.0F, 4.0F, 4.0F, 0.0F, new CubeDeformation(0.0001F)), PartPose.offsetAndRotation(2.5F, -6.0F, -4.5F, 0.0F, 0.0F, -0.5236F));

		PartDefinition Head_r4 = Head.addOrReplaceChild("Head_r4", CubeListBuilder.create().texOffs(70, 52).addBox(1.5F, -2.0F, 0.0F, 1.0F, 4.0F, 0.0F, new CubeDeformation(0.0001F)), PartPose.offsetAndRotation(6.0F, -1.5F, -2.5F, 0.0F, 0.0F, -1.8326F));

		PartDefinition Head_r5 = Head.addOrReplaceChild("Head_r5", CubeListBuilder.create().texOffs(64, 54).addBox(-1.265F, 0.9745F, -0.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.2501F)), PartPose.offsetAndRotation(5.485F, -12.0892F, -0.5F, 0.0F, 0.0F, 3.0107F));

		PartDefinition Head_r6 = Head.addOrReplaceChild("Head_r6", CubeListBuilder.create().texOffs(100, 86).addBox(-0.0092F, 1.169F, -0.5F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.25F)), PartPose.offsetAndRotation(5.485F, -12.0892F, -0.5F, 0.0F, 0.0F, 2.4871F));

		PartDefinition Head_r7 = Head.addOrReplaceChild("Head_r7", CubeListBuilder.create().texOffs(52, 100).addBox(-1.265F, -3.0255F, -0.5F, 4.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.485F, -11.0892F, -0.5F, 0.0F, 0.0F, 3.0107F));

		PartDefinition Head_r8 = Head.addOrReplaceChild("Head_r8", CubeListBuilder.create().texOffs(64, 27).addBox(-1.5F, -1.5F, -4.0F, 4.0F, 1.0F, 8.0F, new CubeDeformation(0.25F)), PartPose.offsetAndRotation(3.7144F, -8.8338F, 0.0F, 0.0F, 0.0F, 1.0036F));

		PartDefinition Head_r9 = Head.addOrReplaceChild("Head_r9", CubeListBuilder.create().texOffs(100, 76).addBox(-1.5939F, 0.7106F, -0.5F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.25F)), PartPose.offsetAndRotation(-5.1001F, -10.9975F, -0.5F, 0.0F, 0.0F, 1.6581F));

		PartDefinition Head_r10 = Head.addOrReplaceChild("Head_r10", CubeListBuilder.create().texOffs(100, 78).addBox(-2.735F, -3.0255F, -0.5F, 4.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.485F, -11.0892F, -0.5F, 0.0F, 0.0F, -3.0107F));

		PartDefinition Head_r11 = Head.addOrReplaceChild("Head_r11", CubeListBuilder.create().texOffs(64, 52).addBox(-0.735F, 0.9745F, -0.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.2501F)), PartPose.offsetAndRotation(-5.485F, -12.0892F, -0.5F, 0.0F, 0.0F, -3.0107F));

		PartDefinition Head_r12 = Head.addOrReplaceChild("Head_r12", CubeListBuilder.create().texOffs(100, 84).addBox(-4.9908F, 1.169F, -0.5F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.25F)), PartPose.offsetAndRotation(-5.485F, -12.0892F, -0.5F, 0.0F, 0.0F, -2.4871F));

		PartDefinition Head_r13 = Head.addOrReplaceChild("Head_r13", CubeListBuilder.create().texOffs(64, 18).addBox(-2.5F, -1.5F, -4.0F, 4.0F, 1.0F, 8.0F, new CubeDeformation(0.25F)), PartPose.offsetAndRotation(-3.7144F, -8.8338F, 0.0F, 0.0F, 0.0F, -1.0036F));

		PartDefinition Head_r14 = Head.addOrReplaceChild("Head_r14", CubeListBuilder.create().texOffs(16, 75).addBox(-2.3579F, -0.5F, -6.6564F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.2501F))
		.texOffs(16, 73).addBox(-2.3579F, -0.5F, 8.4436F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.2501F))
		.texOffs(30, 31).mirror().addBox(-2.3579F, -0.5F, -5.546F, 3.0F, 1.0F, 14.0F, new CubeDeformation(0.2503F)).mirror(false)
		.texOffs(64, 9).addBox(-0.6421F, -0.5F, -2.6012F, 4.0F, 1.0F, 8.0F, new CubeDeformation(0.2501F)), PartPose.offsetAndRotation(6.5753F, -6.8508F, -1.4542F, 0.0F, 0.0F, 0.2618F));

		PartDefinition Head_r15 = Head.addOrReplaceChild("Head_r15", CubeListBuilder.create().texOffs(100, 68).addBox(2.3363F, -0.5F, -4.9147F, 2.0F, 1.0F, 5.0F, new CubeDeformation(0.25F)), PartPose.offsetAndRotation(6.5753F, -6.8508F, -1.4542F, 0.0F, 0.7418F, 0.2618F));

		PartDefinition Head_r16 = Head.addOrReplaceChild("Head_r16", CubeListBuilder.create().texOffs(64, 98).addBox(-6.9774F, -0.5F, 2.2264F, 5.0F, 1.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offsetAndRotation(6.5753F, -6.8508F, -1.4542F, 0.0F, 0.829F, 0.2618F));

		PartDefinition Head_r17 = Head.addOrReplaceChild("Head_r17", CubeListBuilder.create().texOffs(38, 100).addBox(-4.3363F, -0.5F, -4.9147F, 2.0F, 1.0F, 5.0F, new CubeDeformation(0.25F)), PartPose.offsetAndRotation(-6.5753F, -6.8508F, -1.4542F, 0.0F, -0.7418F, -0.2618F));

		PartDefinition Head_r18 = Head.addOrReplaceChild("Head_r18", CubeListBuilder.create().texOffs(0, 99).addBox(1.9774F, -0.5F, 2.2264F, 5.0F, 1.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offsetAndRotation(-6.5753F, -6.8508F, -1.4542F, 0.0F, -0.829F, -0.2618F));

		PartDefinition Head_r19 = Head.addOrReplaceChild("Head_r19", CubeListBuilder.create().texOffs(0, 64).addBox(-3.3579F, -0.5F, -2.6012F, 4.0F, 1.0F, 8.0F, new CubeDeformation(0.2501F))
		.texOffs(40, 74).addBox(0.3579F, -0.5F, 8.4436F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.2501F))
		.texOffs(40, 72).addBox(0.3579F, -0.5F, -6.6564F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.2501F))
		.texOffs(30, 31).addBox(-0.6421F, -0.5F, -5.546F, 3.0F, 1.0F, 14.0F, new CubeDeformation(0.2503F)), PartPose.offsetAndRotation(-6.5753F, -6.8508F, -1.4542F, 0.0F, 0.0F, -0.2618F));

		PartDefinition Head_r20 = Head.addOrReplaceChild("Head_r20", CubeListBuilder.create().texOffs(100, 74).addBox(-4.4061F, 0.7106F, -0.5F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.25F)), PartPose.offsetAndRotation(5.1001F, -10.9975F, -0.5F, 0.0F, 0.0F, -1.6581F));

		PartDefinition Head_r21 = Head.addOrReplaceChild("Head_r21", CubeListBuilder.create().texOffs(22, 73).addBox(-2.5F, -2.0F, 0.0F, 1.0F, 4.0F, 0.0F, new CubeDeformation(0.0001F)), PartPose.offsetAndRotation(-6.0F, -1.5F, -2.5F, 0.0F, 0.0F, 1.8326F));

		PartDefinition Head_r22 = Head.addOrReplaceChild("Head_r22", CubeListBuilder.create().texOffs(42, 76).addBox(-2.5F, -2.0F, 0.0F, 1.0F, 4.0F, 0.0F, new CubeDeformation(0.0001F)), PartPose.offsetAndRotation(-4.9F, 1.3F, -2.5F, 0.0F, 0.0F, 1.2654F));

		PartDefinition Head_r23 = Head.addOrReplaceChild("Head_r23", CubeListBuilder.create().texOffs(46, 72).addBox(-2.5F, -2.0F, 0.0F, 1.0F, 4.0F, 0.0F, new CubeDeformation(0.0001F)), PartPose.offsetAndRotation(-6.0F, 0.0F, -2.5F, 0.0F, 0.0F, 1.5272F));

		PartDefinition Head_r24 = Head.addOrReplaceChild("Head_r24", CubeListBuilder.create().texOffs(24, 48).addBox(-2.5F, -2.0F, 0.0F, 4.0F, 4.0F, 0.0F, new CubeDeformation(0.0001F)), PartPose.offsetAndRotation(-2.5F, -6.0F, -4.5F, 0.0F, 0.0F, 0.5236F));

		PartDefinition RightLeg = partdefinition.addOrReplaceChild("RightLeg", CubeListBuilder.create().texOffs(88, 7).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.45F))
		.texOffs(16, 88).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offset(-1.9F, 12.0F, 0.0F));

		PartDefinition LeftLeg = partdefinition.addOrReplaceChild("LeftLeg", CubeListBuilder.create().texOffs(88, 18).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.45F))
		.texOffs(32, 88).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.251F)), PartPose.offset(1.9F, 12.0F, 0.0F));

		PartDefinition LeftBoot = partdefinition.addOrReplaceChild("LeftBoot", CubeListBuilder.create().texOffs(48, 91).addBox(-2.0F, 7.0F, -2.0F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.45F))
		.texOffs(84, 93).addBox(-2.0F, 7.0F, -2.0F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offset(1.9F, 12.0F, 0.0F));

		PartDefinition RightBoot = partdefinition.addOrReplaceChild("RightBoot", CubeListBuilder.create().texOffs(96, 29).addBox(-2.0F, 7.0F, -2.0F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.45F))
		.texOffs(96, 38).addBox(-2.0F, 7.0F, -2.0F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.251F)), PartPose.offset(-1.9F, 12.0F, 0.0F));

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