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

public class CapModel<T extends Entity> extends EntityModel<T> {
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("weaversparadise", "cap_model"), "main");
	public final ModelPart Head;

	public CapModel(ModelPart root) {
		this.Head = root.getChild("Head");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Head = partdefinition.addOrReplaceChild("Head", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition HatLayer_r1 = Head.addOrReplaceChild("Hat Layer_r1", CubeListBuilder.create().texOffs(0, 22).addBox(-4.5F, -3.44F, -0.9147F, 9.0F, 2.0F, 9.0F, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(0.0F, -6.6818F, -3.6843F, -0.2612F, 0.0047F, 0.0053F));

		PartDefinition Hat_Layer_r2 = Head.addOrReplaceChild("Hat_Layer_r2", CubeListBuilder.create().texOffs(0, 33).addBox(-5.5F, -0.0855F, -1.75F, 10.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, -5.8755F, -9.181F, 0.1309F, 0.0F, 0.0F));

		PartDefinition Hat_Layer_r3 = Head.addOrReplaceChild("Hat_Layer_r3", CubeListBuilder.create().texOffs(0, 0).addBox(-4.5F, -1.0955F, -0.7933F, 9.0F, 2.0F, 9.0F, new CubeDeformation(0.6F))
		.texOffs(0, 11).addBox(-4.5F, -1.0955F, -0.7933F, 9.0F, 2.0F, 9.0F, new CubeDeformation(0.3F)), PartPose.offsetAndRotation(0.0F, -7.3818F, -3.6843F, -0.1745F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(@NotNull T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {}

	@Override
	public void renderToBuffer(@NotNull PoseStack poseStack, @NotNull VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
		Head.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
	}
}