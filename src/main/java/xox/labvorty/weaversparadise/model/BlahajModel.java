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

public class BlahajModel<T extends Entity> extends EntityModel<T> {
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("weaversparadise", "blahaj_model"), "main");
	public final ModelPart main;

	public BlahajModel(ModelPart root) {
		this.main = root.getChild("main");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition main = partdefinition.addOrReplaceChild("main", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -4.0F, -3.0F, 4.0F, 4.0F, 7.0F, new CubeDeformation(-0.002F))
		.texOffs(22, 41).addBox(-2.0F, -5.0014F, 0.0852F, 4.0F, 1.0F, 5.0F, new CubeDeformation(-0.001F))
		.texOffs(0, 11).addBox(-2.0F, -4.0F, 4.0F, 4.0F, 2.0F, 5.0F, new CubeDeformation(-0.002F))
		.texOffs(20, 24).addBox(-1.0F, -4.0F, 9.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.002F))
		.texOffs(40, 0).addBox(-2.0F, -4.0F, -5.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.004F))
		.texOffs(22, 0).addBox(-2.0F, -4.0F, -5.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(-0.002F))
		.texOffs(21, 7).addBox(-2.0F, -3.9949F, -6.9539F, 4.0F, 2.0F, 2.0F, new CubeDeformation(-0.002F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition cube_r1 = main.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 48).addBox(-2.005F, -0.5F, 0.5F, 4.0F, 1.0F, 4.0F, new CubeDeformation(-0.004F)), PartPose.offsetAndRotation(0.005F, -4.6505F, 4.4697F, -0.2618F, 0.0F, 0.0F));

		PartDefinition cube_r2 = main.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(36, 20).mirror().addBox(-0.5F, -1.1423F, -0.6852F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, -1.6752F, 10.708F, -2.9232F, 0.0015F, -0.0113F));

		PartDefinition cube_r3 = main.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(16, 18).addBox(-2.0F, -1.0F, -2.0F, 4.0F, 2.0F, 4.0F, new CubeDeformation(0.002F)), PartPose.offsetAndRotation(0.0F, -2.0892F, -5.4651F, -0.3927F, 0.0F, 0.0F));

		PartDefinition cube_r4 = main.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(34, 0).addBox(-0.5F, 0.0287F, -0.7247F, 1.0F, 1.0F, 2.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(0.0F, -1.6752F, 10.708F, 2.8364F, 0.0015F, -0.0113F));

		PartDefinition cube_r5 = main.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(34, 25).addBox(-0.5F, -0.8679F, -0.1934F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(0.0F, -1.6752F, 10.708F, -2.4432F, 0.0015F, -0.0113F));

		PartDefinition cube_r6 = main.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(36, 20).addBox(-0.5F, -1.1423F, -0.6852F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.6752F, 10.708F, -2.9232F, -0.0015F, 0.0113F));

		PartDefinition cube_r7 = main.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(32, 8).addBox(-0.5F, 0.0287F, -0.7247F, 1.0F, 1.0F, 2.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(0.0F, -1.6752F, 10.708F, 2.8364F, -0.0015F, 0.0113F));

		PartDefinition cube_r8 = main.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(34, 22).addBox(-0.5F, -0.8679F, -0.1934F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(0.0F, -1.6752F, 10.708F, -2.4432F, -0.0015F, 0.0113F));

		PartDefinition cube_r9 = main.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(10, 24).addBox(-0.8592F, -1.0838F, -3.4869F, 1.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(16, 36).addBox(-0.8602F, -1.0838F, 0.0245F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(0.0F, -2.9162F, 11.9828F, 0.0F, 0.3491F, 0.0F));

		PartDefinition cube_r10 = main.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(32, 17).addBox(-0.5F, -1.1034F, 0.03F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.002F)), PartPose.offsetAndRotation(-2.6571F, -0.8914F, 0.4312F, 0.1745F, 0.0F, -2.2253F));

		PartDefinition cube_r11 = main.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(18, 11).addBox(-2.0F, -1.0F, -2.0F, 4.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.6892F, -4.4651F, -0.3927F, 0.0F, 0.0F));

		PartDefinition cube_r12 = main.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(12, 36).addBox(-0.5F, -2.529F, -0.1102F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.6571F, -0.8914F, 0.4312F, -0.2618F, 0.0F, -2.2253F));

		PartDefinition cube_r13 = main.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(16, 30).addBox(-0.5F, -2.1924F, -1.2655F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(-2.6571F, -0.8914F, 0.4312F, -0.7418F, 0.0F, -2.2253F));

		PartDefinition cube_r14 = main.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(0, 30).addBox(-0.5F, -1.0201F, -2.4661F, 1.0F, 3.0F, 2.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(-2.6571F, -0.8914F, 0.4312F, 0.7854F, 0.0F, -2.2253F));

		PartDefinition cube_r15 = main.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(28, 35).addBox(-0.5F, -0.5683F, -0.9669F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.6571F, -0.8914F, 0.5312F, 0.1745F, 0.0F, -2.2253F));

		PartDefinition cube_r16 = main.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(38, 25).addBox(-2.0F, -0.5F, -1.5F, 4.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -4.0207F, -6.6925F, 1.0908F, 0.0F, 0.0F));

		PartDefinition cube_r17 = main.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(20, 28).addBox(-2.002F, -0.5F, -0.5F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.002F, -2.4906F, -7.2677F, 1.7017F, 0.0F, 0.0F));

		PartDefinition cube_r18 = main.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(24, 35).addBox(-0.5F, -2.529F, -0.1102F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.6571F, -0.8914F, 0.4312F, -0.2618F, 0.0F, 2.2253F));

		PartDefinition cube_r19 = main.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(12, 30).addBox(-0.5F, -2.1924F, -1.2655F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(2.6571F, -0.8914F, 0.4312F, -0.7418F, 0.0F, 2.2253F));

		PartDefinition cube_r20 = main.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(6, 30).addBox(-0.5F, -1.0201F, -2.4661F, 1.0F, 3.0F, 2.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(2.6571F, -0.8914F, 0.4312F, 0.7854F, 0.0F, 2.2253F));

		PartDefinition cube_r21 = main.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(28, 30).addBox(-0.5F, -1.1034F, 0.03F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.002F)), PartPose.offsetAndRotation(2.6571F, -0.8914F, 0.4312F, 0.1745F, 0.0F, 2.2253F));

		PartDefinition cube_r22 = main.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(8, 35).addBox(-0.5F, -0.5683F, -0.9669F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.6571F, -0.8914F, 0.5312F, 0.1745F, 0.0F, 2.2253F));

		PartDefinition cube_r23 = main.addOrReplaceChild("cube_r23", CubeListBuilder.create().texOffs(36, 37).addBox(-0.5F, 1.1682F, 0.9528F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.9162F, 11.9828F, 0.1309F, 0.0F, 0.0F));

		PartDefinition cube_r24 = main.addOrReplaceChild("cube_r24", CubeListBuilder.create().texOffs(32, 33).addBox(-0.5F, -0.7499F, -0.6178F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.003F)), PartPose.offsetAndRotation(0.0F, -2.9162F, 11.9828F, 0.6109F, 0.0F, 0.0F));

		PartDefinition cube_r25 = main.addOrReplaceChild("cube_r25", CubeListBuilder.create().texOffs(36, 31).addBox(-0.5F, -1.5014F, 1.3499F, 1.0F, 2.0F, 1.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(0.0F, -2.9162F, 11.9828F, -0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r26 = main.addOrReplaceChild("cube_r26", CubeListBuilder.create().texOffs(36, 28).addBox(-0.5F, -0.2104F, 1.1386F, 1.0F, 2.0F, 1.0F, new CubeDeformation(-0.002F)), PartPose.offsetAndRotation(0.0F, -2.9162F, 11.9828F, 0.48F, 0.0F, 0.0F));

		PartDefinition cube_r27 = main.addOrReplaceChild("cube_r27", CubeListBuilder.create().texOffs(20, 36).addBox(-0.5F, -1.7467F, 0.6976F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.9162F, 11.9828F, 0.0436F, 0.0F, 0.0F));

		PartDefinition cube_r28 = main.addOrReplaceChild("cube_r28", CubeListBuilder.create().texOffs(32, 28).addBox(-0.5F, -4.2303F, -1.1871F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.002F)), PartPose.offsetAndRotation(0.0F, -2.9162F, 11.9828F, -0.8727F, 0.0F, 0.0F));

		PartDefinition cube_r29 = main.addOrReplaceChild("cube_r29", CubeListBuilder.create().texOffs(20, 30).addBox(-0.5F, -4.3005F, 0.9004F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.002F)), PartPose.offsetAndRotation(0.0F, -2.9162F, 11.9828F, -0.3927F, 0.0F, 0.0F));

		PartDefinition cube_r30 = main.addOrReplaceChild("cube_r30", CubeListBuilder.create().texOffs(34, 14).addBox(-0.5F, -1.0589F, -0.2409F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(0.0F, -4.3726F, 10.7379F, -0.2182F, 0.0F, 0.0F));

		PartDefinition cube_r31 = main.addOrReplaceChild("cube_r31", CubeListBuilder.create().texOffs(34, 11).addBox(-0.5F, -0.8281F, -0.7026F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -4.3726F, 10.7379F, -0.6981F, 0.0F, 0.0F));

		PartDefinition cube_r32 = main.addOrReplaceChild("cube_r32", CubeListBuilder.create().texOffs(24, 30).addBox(-0.5F, -1.762F, -1.074F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(0.0F, -5.8331F, 1.5808F, -0.7418F, 0.0F, 0.0F));

		PartDefinition cube_r33 = main.addOrReplaceChild("cube_r33", CubeListBuilder.create().texOffs(4, 35).addBox(-0.5F, -0.1543F, -1.1918F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(34, 3).addBox(-0.5F, -0.6895F, -0.1949F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.002F)), PartPose.offsetAndRotation(0.0F, -5.8331F, 1.5808F, 0.1745F, 0.0F, 0.0F));

		PartDefinition cube_r34 = main.addOrReplaceChild("cube_r34", CubeListBuilder.create().texOffs(0, 35).addBox(-0.5F, -2.0588F, -0.1391F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -5.8331F, 1.5808F, -0.2618F, 0.0F, 0.0F));

		PartDefinition cube_r35 = main.addOrReplaceChild("cube_r35", CubeListBuilder.create().texOffs(0, 18).addBox(-1.0F, -3.0F, -1.0F, 4.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 0.5412F, 5.3066F, 0.3927F, 0.0F, 0.0F));

		PartDefinition cube_r36 = main.addOrReplaceChild("cube_r36", CubeListBuilder.create().texOffs(22, 4).addBox(-1.0F, -3.0F, 1.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(-1.0F, 0.0412F, 6.5066F, 0.3927F, 0.0F, 0.0F));

		PartDefinition cube_r37 = main.addOrReplaceChild("cube_r37", CubeListBuilder.create().texOffs(36, 17).addBox(-0.1398F, -1.0838F, 0.0245F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.001F))
		.texOffs(0, 24).addBox(-0.1408F, -1.0838F, -3.4869F, 1.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.9162F, 11.9828F, 0.0F, -0.3491F, 0.0F));

		PartDefinition cube_r38 = main.addOrReplaceChild("cube_r38", CubeListBuilder.create().texOffs(0, 39).addBox(-2.003F, -0.5F, -3.5F, 4.0F, 1.0F, 7.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(0.003F, -4.3492F, -3.3877F, 0.0436F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(@NotNull T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {}

	@Override
	public void renderToBuffer(@NotNull PoseStack poseStack, @NotNull VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
		main.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
	}
}