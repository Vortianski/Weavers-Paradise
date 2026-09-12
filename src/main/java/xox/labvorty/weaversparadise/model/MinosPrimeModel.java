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

public class MinosPrimeModel<T extends Entity> extends EntityModel<T> {
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("weaversparadise", "minos_prime_model"), "main");
    public final ModelPart Head;
    public final ModelPart Body;
    public final ModelPart Right_Arm;
    public final ModelPart Left_Arm;
    public final ModelPart Right_Leg;
    public final ModelPart Right_boot;
    public final ModelPart Left_Leg;
    public final ModelPart Left_boot;

	public MinosPrimeModel(ModelPart root) {
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

		PartDefinition Head = partdefinition.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.2505F))
		.texOffs(42, 57).addBox(-4.0F, -14.0F, -4.0F, 3.0F, 6.0F, 2.0F, new CubeDeformation(0.2501F))
		.texOffs(52, 57).addBox(1.0F, -13.0F, 2.0F, 3.0F, 5.0F, 2.0F, new CubeDeformation(0.2501F))
		.texOffs(58, 16).addBox(-4.0F, -12.0F, 2.0F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.2501F))
		.texOffs(32, 57).addBox(-3.0F, -16.0F, 1.0F, 3.0F, 8.0F, 2.0F, new CubeDeformation(0.2501F))
		.texOffs(62, 57).addBox(-1.0F, -13.0F, -3.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.2501F))
		.texOffs(32, 25).addBox(1.0F, -10.0F, -4.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.2501F))
		.texOffs(32, 16).addBox(-3.0F, -11.0F, -3.0F, 7.0F, 3.0F, 6.0F, new CubeDeformation(0.2503F))
		.texOffs(0, 16).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.85F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition Body = partdefinition.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(0, 32).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.2503F))
		.texOffs(32, 0).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.45F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition Right_Arm = partdefinition.addOrReplaceChild("Right_Arm", CubeListBuilder.create().texOffs(24, 32).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.2501F))
		.texOffs(56, 41).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.55F)), PartPose.offset(-5.0F, 2.0F, 0.0F));

		PartDefinition Left_Arm = partdefinition.addOrReplaceChild("Left_Arm", CubeListBuilder.create().texOffs(40, 25).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.2501F))
		.texOffs(40, 41).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.55F)), PartPose.offset(5.0F, 2.0F, 0.0F));

		PartDefinition Right_Leg = partdefinition.addOrReplaceChild("Right_Leg", CubeListBuilder.create().texOffs(0, 48).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.2501F))
		.texOffs(16, 48).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.45F)), PartPose.offset(-1.9F, 12.0F, 0.0F));

		PartDefinition Right_boot = partdefinition.addOrReplaceChild("Right_boot", CubeListBuilder.create().texOffs(0, 64).addBox(-2.0F, 8.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.2501F)), PartPose.offset(-1.9F, 12.0F, 0.0F));

		PartDefinition Left_Leg = partdefinition.addOrReplaceChild("Left_Leg", CubeListBuilder.create().texOffs(56, 0).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.2503F))
		.texOffs(56, 25).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.45F)), PartPose.offset(1.9F, 12.0F, 0.0F));

		PartDefinition Left_boot = partdefinition.addOrReplaceChild("Left_boot", CubeListBuilder.create().texOffs(72, 8).addBox(-2.0F, 8.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.2503F)), PartPose.offset(1.9F, 12.0F, 0.0F));

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