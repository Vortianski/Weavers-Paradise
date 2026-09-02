package xox.labvorty.weaversparadise.mixins;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xox.labvorty.vortylib.utilities.VortyLibCurioUtilities;
import xox.labvorty.vortylib.utilities.VortyLibUtilities;
import xox.labvorty.weaversparadise.items.armor.ModelReplacer;

@Mixin(ArmorStand.class)
public class ArmorStandMixin {
    @Inject(
            method = "interactAt",
            at = @At("HEAD"),
            cancellable = true
    )
    private void weaversparadise$applyOrGetArmorCosmetics(Player player, Vec3 vec, InteractionHand interactionHand, CallbackInfoReturnable<InteractionResult> cir) {
        if (player.isSpectator() || player.level().isClientSide) {
            return;
        } else {
            ArmorStand armorStand = (ArmorStand)(Object)this;
            ItemStack itemStack = player.getItemInHand(interactionHand);

            if (itemStack.getItem() instanceof ModelReplacer modelReplacer) {
                VortyLibCurioUtilities.CurioMatch<ModelReplacer> match = VortyLibCurioUtilities.findFirstCurioOfType(armorStand, ModelReplacer.class);

                if (match == null) {
                    boolean inserted = VortyLibCurioUtilities.insertIntoFirstAvailableSlotOfType(armorStand, itemStack);

                    if (inserted) {
                        cir.setReturnValue(InteractionResult.SUCCESS);
                    }
                } else {
                    ItemStack previous = match.stack().copy();
                    VortyLibCurioUtilities.modifyFirstCurioOfType(armorStand, ModelReplacer.class, true);

                    boolean inserted = VortyLibCurioUtilities.insertIntoFirstAvailableSlotOfType(armorStand, itemStack);

                    if (inserted) {
                        VortyLibUtilities.tryInsertOrDrop(player, previous);
                        cir.setReturnValue(InteractionResult.SUCCESS);
                    } else {
                        VortyLibCurioUtilities.insertIntoFirstAvailableSlotOfType(armorStand, previous);
                    }
                }
            } else if (itemStack.isEmpty()) {
                VortyLibCurioUtilities.CurioMatch<ModelReplacer> match = VortyLibCurioUtilities.findFirstCurioOfType(armorStand, ModelReplacer.class);
                if (match != null) {
                    VortyLibUtilities.tryInsertOrDrop(player, match.stack().copy());
                    VortyLibCurioUtilities.modifyFirstCurioOfType(armorStand, ModelReplacer.class, true);

                    cir.setReturnValue(InteractionResult.SUCCESS);
                }
            }
        }
    }
}