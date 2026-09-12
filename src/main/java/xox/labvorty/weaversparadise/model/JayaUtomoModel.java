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

public class JayaUtomoModel<T extends Entity> extends EntityModel<T> {
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("weaversparadise", "jaya_utomo_model"), "main");
	public final ModelPart Head;
    public final ModelPart Body;
    public final ModelPart Left_Arm;
    public final ModelPart Right_Arm;
    public final ModelPart Right_Leg;
    public final ModelPart Right_boot;
    public final ModelPart Left_Leg;
    public final ModelPart Left_boot;

	public JayaUtomoModel(ModelPart root) {
		this.Head = root.getChild("Head");
		this.Body = root.getChild("Body");
		this.Left_Arm = root.getChild("Left_Arm");
		this.Right_Arm = root.getChild("Right_Arm");
		this.Right_Leg = root.getChild("Right_Leg");
		this.Right_boot = root.getChild("Right_boot");
		this.Left_Leg = root.getChild("Left_Leg");
		this.Left_boot = root.getChild("Left_boot");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Head = partdefinition.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.2501F))
		.texOffs(0, 16).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.8F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition Head_r1 = Head.addOrReplaceChild("Head_r1", CubeListBuilder.create().texOffs(0, 66).addBox(-0.0538F, -1.373F, -0.5F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.5044F, -12.1096F, -0.5F, -0.0854F, -0.6695F, -0.0854F));

		PartDefinition Head_r2 = Head.addOrReplaceChild("Head_r2", CubeListBuilder.create().texOffs(66, 25).addBox(-1.7307F, -1.5464F, 0.0F, 2.0F, 6.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.5044F, -12.1096F, -0.5F, -0.1128F, -0.666F, -0.0412F));

		PartDefinition Head_r3 = Head.addOrReplaceChild("Head_r3", CubeListBuilder.create().texOffs(62, 65).addBox(-0.9685F, -1.4255F, -0.5F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.5044F, -12.1096F, -0.5F, -0.333F, -0.5976F, 0.3286F));

		PartDefinition Head_r4 = Head.addOrReplaceChild("Head_r4", CubeListBuilder.create().texOffs(66, 39).addBox(-1.6362F, -1.5414F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.5044F, -12.1096F, -0.5F, -0.6192F, -0.2857F, 0.9717F));

		PartDefinition Head_r5 = Head.addOrReplaceChild("Head_r5", CubeListBuilder.create().texOffs(66, 37).addBox(0.6362F, -1.5414F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5044F, -12.1096F, -0.5F, -0.6192F, 0.2857F, -0.9717F));

		PartDefinition Head_r6 = Head.addOrReplaceChild("Head_r6", CubeListBuilder.create().texOffs(4, 66).addBox(-0.2693F, -1.5464F, 0.0F, 2.0F, 6.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5044F, -12.1096F, -0.5F, -0.1128F, 0.666F, 0.0412F));

		PartDefinition Head_r7 = Head.addOrReplaceChild("Head_r7", CubeListBuilder.create().texOffs(58, 65).addBox(-0.0315F, -1.4255F, -0.5F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5044F, -12.1096F, -0.5F, -0.333F, 0.5976F, -0.3286F));

		PartDefinition Head_r8 = Head.addOrReplaceChild("Head_r8", CubeListBuilder.create().texOffs(10, 64).addBox(-0.9462F, -1.373F, -0.5F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5044F, -12.1096F, -0.5F, -0.0854F, 0.6695F, 0.0854F));

		PartDefinition Body = partdefinition.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(0, 32).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.2503F))
		.texOffs(32, 0).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.5505F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition Body_r1 = Body.addOrReplaceChild("Body_r1", CubeListBuilder.create().texOffs(32, 31).addBox(-1.0F, -0.5F, 0.0F, 2.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 10.7F, -2.3F, 0.0F, 0.0F, -0.1745F));

		PartDefinition BodyLayer_r1 = Body.addOrReplaceChild("Body Layer_r1", CubeListBuilder.create().texOffs(20, 48).addBox(-1.0F, -4.5F, 0.0F, 2.0F, 9.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.5F, 4.5F, -3.0F, -0.0436F, 0.4363F, 0.4363F));

		PartDefinition Body_r2 = Body.addOrReplaceChild("Body_r2", CubeListBuilder.create().texOffs(66, 34).addBox(-1.0F, -1.5F, 0.0F, 2.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.7F, 1.1F, -2.5F, 0.0F, -0.1745F, -2.0071F));

		PartDefinition Body_Layer_r2 = Body.addOrReplaceChild("Body_Layer_r2", CubeListBuilder.create().texOffs(16, 48).addBox(-1.0F, -4.5F, 0.0F, 2.0F, 9.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5F, 4.5F, -3.0F, -0.0436F, -0.4363F, -0.4363F));

		PartDefinition Body_r3 = Body.addOrReplaceChild("Body_r3", CubeListBuilder.create().texOffs(66, 31).addBox(-1.0F, -1.5F, 0.0F, 2.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.7F, 1.1F, -2.5F, 0.0F, 0.1745F, 2.0071F));

		PartDefinition Body_r4 = Body.addOrReplaceChild("Body_r4", CubeListBuilder.create().texOffs(42, 29).addBox(-1.0F, -14.0F, 1.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.249F)), PartPose.offsetAndRotation(0.0F, 22.4364F, -3.8607F, -0.3927F, 0.0F, 0.0F));

		PartDefinition Body_r5 = Body.addOrReplaceChild("Body_r5", CubeListBuilder.create().texOffs(16, 57).addBox(-1.0F, -14.0F, 1.0F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.2499F))
		.texOffs(58, 61).addBox(-1.0F, -14.0F, 1.0F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.3501F)), PartPose.offsetAndRotation(-0.5F, 22.8191F, -2.9368F, -0.3927F, 0.0F, 0.0F));

		PartDefinition Body_r6 = Body.addOrReplaceChild("Body_r6", CubeListBuilder.create().texOffs(58, 25).addBox(-1.0F, -14.0F, 1.0F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.2499F)), PartPose.offsetAndRotation(-0.5F, 26.6459F, 6.302F, -0.3927F, 0.0F, 0.0F));

		PartDefinition Body_r7 = Body.addOrReplaceChild("Body_r7", CubeListBuilder.create().texOffs(48, 61).addBox(-1.5F, -1.5F, -1.0F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.3501F)), PartPose.offsetAndRotation(0.0F, 16.0541F, 13.3952F, -0.3229F, 0.0F, 0.0F));

		PartDefinition Body_r8 = Body.addOrReplaceChild("Body_r8", CubeListBuilder.create().texOffs(58, 16).addBox(1.0F, -14.0F, 0.0F, 0.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 26.8438F, 7.3024F, -0.3927F, 0.0F, 0.0F));

		PartDefinition Body_r9 = Body.addOrReplaceChild("Body_r9", CubeListBuilder.create().texOffs(32, 16).addBox(-2.0F, -14.0F, 1.0F, 4.0F, 4.0F, 9.0F, new CubeDeformation(0.2503F))
		.texOffs(24, 32).addBox(-2.0F, -14.0F, 1.0F, 4.0F, 4.0F, 9.0F, new CubeDeformation(0.4503F)), PartPose.offsetAndRotation(0.0F, 23.2018F, -2.0129F, -0.3927F, 0.0F, 0.0F));

		PartDefinition Left_Arm = partdefinition.addOrReplaceChild("Left_Arm", CubeListBuilder.create().texOffs(0, 48).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.2501F))
		.texOffs(50, 29).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.55F)), PartPose.offset(5.0F, 2.0F, 0.0F));

		PartDefinition Right_Arm = partdefinition.addOrReplaceChild("Right_Arm", CubeListBuilder.create().texOffs(24, 45).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.2501F))
		.texOffs(40, 45).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.55F)), PartPose.offset(-5.0F, 2.0F, 0.0F));

		PartDefinition Right_Leg = partdefinition.addOrReplaceChild("Right_Leg", CubeListBuilder.create().texOffs(56, 0).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 11.0F, 4.0F, new CubeDeformation(0.2501F))
		.texOffs(56, 45).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.55F)), PartPose.offset(-1.9F, 12.0F, 0.0F));

		PartDefinition Right_boot = partdefinition.addOrReplaceChild("Right_boot", CubeListBuilder.create().texOffs(72, 11).addBox(-2.0F, 11.0F, -2.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.2511F))
		.texOffs(32, 29).addBox(-2.0F, 11.0F, -3.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.2503F)), PartPose.offset(-1.9F, 12.0F, 0.0F));

		PartDefinition Left_Leg = partdefinition.addOrReplaceChild("Left_Leg", CubeListBuilder.create().texOffs(16, 61).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 11.0F, 4.0F, new CubeDeformation(0.2519F))
		.texOffs(32, 61).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.55F)), PartPose.offset(1.9F, 12.0F, 0.0F));

		PartDefinition Left_boot = partdefinition.addOrReplaceChild("Left_boot", CubeListBuilder.create().texOffs(16, 78).addBox(-2.0F, 11.0F, -2.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.2529F))
		.texOffs(0, 64).addBox(-2.0F, 11.0F, -3.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.2503F)), PartPose.offset(1.9F, 12.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(@NotNull T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {}

	@Override
	public void renderToBuffer(@NotNull PoseStack poseStack, @NotNull VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
		Head.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
		Body.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
		Left_Arm.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
		Right_Arm.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
		Right_Leg.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
		Right_boot.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
		Left_Leg.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
		Left_boot.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
	}
}