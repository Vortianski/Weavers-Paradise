package xox.labvorty.weaversparadise.mixins.client;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.player.PlayerModelPart;
import net.minecraft.world.item.ArmorItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xox.labvorty.weaversparadise.util.WPTrinkets;
import xox.labvorty.weaversparadise.configs.ClientConfig;
import xox.labvorty.weaversparadise.items.armor.ModelReplacer;
import xox.labvorty.weaversparadise.items.misc.Plushie;

@Mixin(PlayerRenderer.class)
public abstract class PlayerRendererMixin {
    @Shadow
    private static HumanoidModel.ArmPose getArmPose(AbstractClientPlayer player, InteractionHand hand) {
        return null;
    }

    @Inject(
            method = "getArmPose",
            at = @At("TAIL"),
            cancellable = true
    )
    private static void cuddleBlahaj(AbstractClientPlayer player, InteractionHand hand, CallbackInfoReturnable<HumanoidModel.ArmPose> ci) {
        if (player.getItemInHand(hand).getItem() instanceof Plushie) {
            ci.setReturnValue(HumanoidModel.ArmPose.CROSSBOW_HOLD);
            ci.cancel();
        }
    }

    @SuppressWarnings("unchecked")
    @Inject(
            method = "setModelProperties",
            at = @At("HEAD"),
            cancellable = true
    )
    private void weaversparadise$hideLayers(AbstractClientPlayer clientPlayer, CallbackInfo ci) {
        if (!clientPlayer.isSpectator()) {
            WPTrinkets.CurioMatch<ModelReplacer> match = WPTrinkets.findFirstCurioMatch(clientPlayer, ModelReplacer.class);

            if (match != null) {
                PlayerModel<AbstractClientPlayer> playermodel = ((LivingEntityRenderer<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>>)(Object)this).getModel();

                boolean fullArmor = ClientConfig.FULL_ARMOR.get();
                boolean head = clientPlayer.getItemBySlot(EquipmentSlot.HEAD).getItem() instanceof ArmorItem;
                boolean chest = clientPlayer.getItemBySlot(EquipmentSlot.CHEST).getItem() instanceof ArmorItem;
                boolean legs = clientPlayer.getItemBySlot(EquipmentSlot.LEGS).getItem() instanceof ArmorItem;
                boolean feet = clientPlayer.getItemBySlot(EquipmentSlot.FEET).getItem() instanceof ArmorItem;

                playermodel.setAllVisible(true);
                playermodel.hat.visible = !fullArmor && !head && clientPlayer.isModelPartShown(PlayerModelPart.HAT);
                playermodel.jacket.visible = !fullArmor && !chest && clientPlayer.isModelPartShown(PlayerModelPart.JACKET);
                playermodel.leftPants.visible = !fullArmor && !legs && !feet && clientPlayer.isModelPartShown(PlayerModelPart.LEFT_PANTS_LEG);
                playermodel.rightPants.visible = !fullArmor && !legs && !feet && clientPlayer.isModelPartShown(PlayerModelPart.RIGHT_PANTS_LEG);
                playermodel.leftSleeve.visible = !fullArmor && !chest && clientPlayer.isModelPartShown(PlayerModelPart.LEFT_SLEEVE);
                playermodel.rightSleeve.visible = !fullArmor && !chest && clientPlayer.isModelPartShown(PlayerModelPart.RIGHT_SLEEVE);
                playermodel.crouching = clientPlayer.isCrouching();
                HumanoidModel.ArmPose humanoidmodel$armpose = getArmPose(clientPlayer, InteractionHand.MAIN_HAND);
                HumanoidModel.ArmPose humanoidmodel$armpose1 = getArmPose(clientPlayer, InteractionHand.OFF_HAND);
                if (humanoidmodel$armpose.isTwoHanded()) {
                    humanoidmodel$armpose1 = clientPlayer.getOffhandItem().isEmpty() ? HumanoidModel.ArmPose.EMPTY : HumanoidModel.ArmPose.ITEM;
                }

                if (clientPlayer.getMainArm() == HumanoidArm.RIGHT) {
                    playermodel.rightArmPose = humanoidmodel$armpose;
                    playermodel.leftArmPose = humanoidmodel$armpose1;
                } else {
                    playermodel.rightArmPose = humanoidmodel$armpose1;
                    playermodel.leftArmPose = humanoidmodel$armpose;
                }

                ci.cancel();
            }
        }
    }
}
