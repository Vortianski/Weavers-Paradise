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

public class GiselleArmorModel<T extends Entity> extends EntityModel<T> {
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("weaversparadise", "giselle_armor_model"), "main");
	public final ModelPart LeftArm;
    public final ModelPart RightArm;
    public final ModelPart Body;
    public final ModelPart Head;
    public final ModelPart RightLeg;
    public final ModelPart RightBoot;
    public final ModelPart LeftLeg;
    public final ModelPart LeftBoot;

	public GiselleArmorModel(ModelPart root) {
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

		PartDefinition LeftArm = partdefinition.addOrReplaceChild("LeftArm", CubeListBuilder.create().texOffs(0, 46).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F))
		.texOffs(16, 48).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.45F)), PartPose.offset(5.0F, 2.0F, 0.0F));

		PartDefinition RightArm = partdefinition.addOrReplaceChild("RightArm", CubeListBuilder.create().texOffs(28, 32).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F))
		.texOffs(44, 32).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.45F)), PartPose.offset(-5.0F, 2.0F, 0.0F));

		PartDefinition Body = partdefinition.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(32, 0).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.252F))
		.texOffs(32, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.45F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition Body_r1 = Body.addOrReplaceChild("Body_r1", CubeListBuilder.create().texOffs(64, 59).addBox(-1.0F, -2.25F, -2.0F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.25F))
		.texOffs(40, 64).addBox(-1.0F, -0.25F, -2.0F, 2.0F, 3.0F, 5.0F, new CubeDeformation(0.25F)), PartPose.offsetAndRotation(4.0F, 9.582F, -0.553F, 0.6037F, 0.0998F, -0.1434F));

		PartDefinition Head = partdefinition.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -9.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.25F))
		.texOffs(0, 32).addBox(-7.0F, -14.0F, 0.3F, 14.0F, 14.0F, 0.0F, new CubeDeformation(0.001F))
		.texOffs(60, 32).addBox(-4.0F, 0.0F, 2.0F, 8.0F, 8.0F, 2.0F, new CubeDeformation(0.6001F))
		.texOffs(0, 16).addBox(-4.0F, -9.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.6F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition Head_r1 = Head.addOrReplaceChild("Head_r1", CubeListBuilder.create().texOffs(60, 42).addBox(-4.5F, 1.0F, -2.5F, 3.0F, 0.0F, 5.0F, new CubeDeformation(0.001F))
		.texOffs(0, 64).addBox(-2.5F, -1.0F, -2.5F, 5.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.1F, -9.5F, -2.1F, 0.0F, -0.2182F, -0.1484F));

		PartDefinition Head_r2 = Head.addOrReplaceChild("Head_r2", CubeListBuilder.create().texOffs(20, 64).addBox(-2.5F, -1.0F, -2.5F, 5.0F, 2.0F, 5.0F, new CubeDeformation(0.3F)), PartPose.offsetAndRotation(-3.3F, -9.5F, -2.2F, 0.0F, -0.2182F, -0.1484F));

		PartDefinition RightLeg = partdefinition.addOrReplaceChild("RightLeg", CubeListBuilder.create().texOffs(56, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F))
		.texOffs(32, 48).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 3.0F, 4.0F, new CubeDeformation(0.451F)), PartPose.offset(-1.9F, 12.0F, 0.0F));

		PartDefinition RightBoot = partdefinition.addOrReplaceChild("RightBoot", CubeListBuilder.create().texOffs(64, 47).addBox(-2.0F, 10.0F, -4.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.45F))
		.texOffs(32, 55).addBox(-2.0F, 7.0F, -2.0F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.451F)), PartPose.offset(-1.9F, 12.0F, 0.0F));

		PartDefinition RightLeg_r1 = RightBoot.addOrReplaceChild("RightLeg_r1", CubeListBuilder.create().texOffs(54, 64).addBox(-3.9F, -2.0F, -1.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.45F)), PartPose.offsetAndRotation(1.9F, 11.3563F, -2.2495F, 0.3491F, 0.0F, 0.0F));

		PartDefinition LeftLeg = partdefinition.addOrReplaceChild("LeftLeg", CubeListBuilder.create().texOffs(56, 0).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.251F))
		.texOffs(48, 48).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 3.0F, 4.0F, new CubeDeformation(0.4502F)), PartPose.offset(1.9F, 12.0F, 0.0F));

		PartDefinition LeftBoot = partdefinition.addOrReplaceChild("LeftBoot", CubeListBuilder.create().texOffs(48, 55).addBox(-2.0F, 7.0F, -2.0F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.4502F))
		.texOffs(64, 55).addBox(-2.0F, 10.0F, -4.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.451F)), PartPose.offset(1.9F, 12.0F, 0.0F));

		PartDefinition LeftLeg_r1 = LeftBoot.addOrReplaceChild("LeftLeg_r1", CubeListBuilder.create().texOffs(64, 51).addBox(-0.1F, -2.0F, -1.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.45F)), PartPose.offsetAndRotation(-1.9F, 11.3563F, -2.2495F, 0.3491F, 0.0F, 0.0F));

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