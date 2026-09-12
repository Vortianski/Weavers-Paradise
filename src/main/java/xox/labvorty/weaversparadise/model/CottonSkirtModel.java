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

public class CottonSkirtModel<T extends Entity> extends EntityModel<T> {
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("weaversparadise", "cotton_skirt_model"), "main");
	public final ModelPart Body;

	public CottonSkirtModel(ModelPart root) {
		this.Body = root.getChild("Body");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Body = partdefinition.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, 9.0F, -2.0F, 8.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(0, 5).addBox(-4.0F, 9.6F, -2.0F, 8.0F, 1.0F, 4.0F, new CubeDeformation(0.2F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition Body_r1 = Body.addOrReplaceChild("Body_r1", CubeListBuilder.create().texOffs(24, 37).addBox(-2.0F, -1.043F, 4.7093F, 4.0F, 6.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1781F, 12.1572F, -0.0091F, 0.0F, 1.5708F, -0.4363F));

		PartDefinition Body_r2 = Body.addOrReplaceChild("Body_r2", CubeListBuilder.create().texOffs(8, 37).addBox(-2.0F, -1.043F, 4.7093F, 4.0F, 6.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0781F, 12.5572F, -0.0091F, 0.0F, 1.5708F, -0.4363F));

		PartDefinition Body_r3 = Body.addOrReplaceChild("Body_r3", CubeListBuilder.create().texOffs(16, 37).addBox(-2.0F, -1.043F, -4.7093F, 4.0F, 6.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.1781F, 12.1572F, 0.0091F, 0.0F, 1.5708F, 0.4363F));

		PartDefinition Body_r4 = Body.addOrReplaceChild("Body_r4", CubeListBuilder.create().texOffs(0, 37).addBox(-2.0F, -1.043F, -4.7093F, 4.0F, 6.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.0781F, 12.5572F, 0.0091F, 0.0F, 1.5708F, 0.4363F));

		PartDefinition Body_r5 = Body.addOrReplaceChild("Body_r5", CubeListBuilder.create().texOffs(0, 28).addBox(-2.5F, -2.5F, -3.5F, 3.0F, 3.0F, 6.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(-2.9835F, 13.3865F, 1.0013F, -1.1514F, -0.0905F, 0.4194F));

		PartDefinition Body_r6 = Body.addOrReplaceChild("Body_r6", CubeListBuilder.create().texOffs(18, 19).addBox(-2.5F, -2.5F, -3.5F, 3.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.9835F, 13.8865F, 1.0013F, -1.1514F, -0.0905F, 0.4194F));

		PartDefinition Body_r7 = Body.addOrReplaceChild("Body_r7", CubeListBuilder.create().texOffs(24, 0).addBox(-0.5F, -2.5F, -3.5F, 3.0F, 3.0F, 6.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(2.9835F, 13.3865F, 1.0013F, -1.1514F, 0.0905F, -0.4194F));

		PartDefinition Body_r8 = Body.addOrReplaceChild("Body_r8", CubeListBuilder.create().texOffs(0, 19).addBox(-0.5F, -2.5F, -3.5F, 3.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.9835F, 13.8865F, 1.0013F, -1.1514F, 0.0905F, -0.4194F));

		PartDefinition Body_r9 = Body.addOrReplaceChild("Body_r9", CubeListBuilder.create().texOffs(18, 28).addBox(-2.5F, -2.5F, -2.5F, 3.0F, 3.0F, 6.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(-2.9835F, 13.3865F, -1.0013F, 1.1514F, 0.0905F, 0.4194F));

		PartDefinition Body_r10 = Body.addOrReplaceChild("Body_r10", CubeListBuilder.create().texOffs(18, 10).addBox(-2.5F, -2.5F, -2.5F, 3.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.9835F, 13.8865F, -1.0013F, 1.1514F, 0.0905F, 0.4194F));

		PartDefinition Body_r11 = Body.addOrReplaceChild("Body_r11", CubeListBuilder.create().texOffs(36, 9).addBox(-0.5F, -2.5F, -2.5F, 3.0F, 3.0F, 6.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(2.9835F, 13.3865F, -1.0013F, 1.1514F, -0.0905F, -0.4194F));

		PartDefinition Body_r12 = Body.addOrReplaceChild("Body_r12", CubeListBuilder.create().texOffs(0, 10).addBox(-0.5F, -2.5F, -2.5F, 3.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.9835F, 13.8865F, -1.0013F, 1.1514F, -0.0905F, -0.4194F));

		PartDefinition Body_r13 = Body.addOrReplaceChild("Body_r13", CubeListBuilder.create().texOffs(36, 24).addBox(-4.0F, -1.8084F, 2.8615F, 8.0F, 6.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.0219F, 11.9572F, 0.3091F, 0.4276F, 0.0F, 0.0F));

		PartDefinition Body_r14 = Body.addOrReplaceChild("Body_r14", CubeListBuilder.create().texOffs(0, 51).addBox(-4.0F, -1.8084F, 2.8615F, 8.0F, 6.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.0219F, 12.1572F, 0.1091F, 0.4276F, 0.0F, 0.0F));

		PartDefinition Body_r15 = Body.addOrReplaceChild("Body_r15", CubeListBuilder.create().texOffs(36, 30).addBox(-4.0F, -1.8084F, 2.8615F, 8.0F, 6.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.0219F, 12.5572F, 0.1091F, 0.4276F, 0.0F, 0.0F));

		PartDefinition Body_r16 = Body.addOrReplaceChild("Body_r16", CubeListBuilder.create().texOffs(36, 36).addBox(-4.0F, -1.8084F, -2.8615F, 8.0F, 6.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.0219F, 11.9572F, -0.3091F, -0.4276F, 0.0F, 0.0F));

		PartDefinition Body_r17 = Body.addOrReplaceChild("Body_r17", CubeListBuilder.create().texOffs(0, 45).addBox(-4.0F, -1.8084F, -2.8615F, 8.0F, 6.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.0219F, 12.1572F, -0.1091F, -0.4276F, 0.0F, 0.0F));

		PartDefinition Body_r18 = Body.addOrReplaceChild("Body_r18", CubeListBuilder.create().texOffs(36, 18).addBox(-4.0F, -1.8084F, -2.8615F, 8.0F, 6.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.0219F, 12.5572F, -0.1091F, -0.4276F, 0.0F, 0.0F));

		PartDefinition Body_r19 = Body.addOrReplaceChild("Body_r19", CubeListBuilder.create().texOffs(0, 61).mirror().addBox(-1.5F, -2.0F, 0.0F, 3.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-1.5F, 11.1F, 3.2F, 0.3491F, 0.3491F, 0.0F));

		PartDefinition Body_r20 = Body.addOrReplaceChild("Body_r20", CubeListBuilder.create().texOffs(0, 61).addBox(-1.5F, -2.0F, 0.0F, 3.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.5F, 11.1F, 3.2F, 0.3491F, -0.3491F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(@NotNull T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {}

	@Override
	public void renderToBuffer(@NotNull PoseStack poseStack, @NotNull VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
		Body.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
	}
}