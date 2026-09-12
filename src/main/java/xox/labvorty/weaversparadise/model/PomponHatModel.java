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

public class PomponHatModel<T extends Entity> extends EntityModel<T> {
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("weaversparadise", "pompon_hat_model"), "main");
	public final ModelPart Head;

	public PomponHatModel(ModelPart root) {
		this.Head = root.getChild("Head");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Head = partdefinition.addOrReplaceChild("Head", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition HatLayer_r1 = Head.addOrReplaceChild("Hat Layer_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-4.5F, -4.44F, -0.9147F, 9.0F, 3.0F, 9.0F, new CubeDeformation(0.3F)), PartPose.offsetAndRotation(0.0F, -6.6818F, -3.4843F, -0.2263F, 0.0045F, 0.0055F));
		PartDefinition Hat_Layer_r2 = Head.addOrReplaceChild("Hat_Layer_r2", CubeListBuilder.create().texOffs(0, 34).addBox(-1.5F, -1.0955F, 2.2067F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(0.0F, -9.9818F, -1.9843F, 0.1309F, 0.0F, 0.0F));
		PartDefinition Hat_Layer_r3 = Head.addOrReplaceChild("Hat_Layer_r3", CubeListBuilder.create().texOffs(0, 23).addBox(-4.5F, -1.0955F, -0.7933F, 9.0F, 2.0F, 9.0F, new CubeDeformation(0.8F)).texOffs(0, 12).addBox(-4.5F, -1.0955F, -0.7933F, 9.0F, 2.0F, 9.0F, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(0.0F, -7.3818F, -3.3843F, -0.1396F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(@NotNull T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {}

	@Override
	public void renderToBuffer(@NotNull PoseStack poseStack, @NotNull VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
		Head.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
	}
}