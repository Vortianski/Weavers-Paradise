package xox.labvorty.weaversparadise.mixins;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xox.labvorty.weaversparadise.init.WeaversParadiseSoundEvents;
import xox.labvorty.weaversparadise.items.clothing.*;
import xox.labvorty.weaversparadise.util.WPTrinkets;

@Mixin(Player.class)
public class PlayerMixin {
    @Inject(
            method = "playStepSound",
            at = @At("HEAD")
    )
    private void weaversparadise$makePlayerNoteBlock(BlockPos pos, BlockState state, CallbackInfo ci) {
        Player player = (Player) (Object) this;

        if (!WPTrinkets.isEquipped(player, stack -> stack.getItem() instanceof BellItem bellItem)) {
            return;
        }

        NoteBlockInstrument bell = NoteBlockInstrument.CHIME;
        SoundEvent instrumentSound = bell.getSoundEvent().value();

        float pitch = 1.6f;

        player.level().playSound(
                null,
                player.getX(), player.getY(), player.getZ(),
                instrumentSound,
                SoundSource.PLAYERS,
                0.5F,
                pitch
        );
    }

    @Inject(
            method = "getFallSounds",
            at = @At("HEAD"),
            cancellable = true
    )
    private void weaversparadise$fallSound(CallbackInfoReturnable<LivingEntity.Fallsounds> cir) {
        Player player = (Player) (Object) this;

        if (WPTrinkets.isEquipped(player, itemStack -> itemStack.getItem() instanceof HeartItem)) {
            cir.setReturnValue(new LivingEntity.Fallsounds(WeaversParadiseSoundEvents.FALL_SMALL_OLD, WeaversParadiseSoundEvents.FALL_BIG_OLD));
        }
    }

    @Inject(
            method = "getHurtSound",
            at = @At("HEAD"),
            cancellable = true
    )
    private void weaversparadise$hurtSound(DamageSource damageSource, CallbackInfoReturnable<SoundEvent> cir) {
        Player player = (Player) (Object) this;

        if (WPTrinkets.isEquipped(player, stack -> stack.getItem() instanceof CatRingItem catRingItem)) {
            cir.setReturnValue(SoundEvents.CAT_HURT);
        } else if (WPTrinkets.isEquipped(player, itemStack -> itemStack.getItem() instanceof PlateItem plateItem)) {
            cir.setReturnValue(SoundEvents.WOLF_HURT);
        } else if (WPTrinkets.isEquipped(player, itemStack -> itemStack.getItem() instanceof RingItem ringItem)) {
            cir.setReturnValue(SoundEvents.FOX_HURT);
        } else if (WPTrinkets.isEquipped(player, itemStack -> itemStack.getItem() instanceof HeartItem heartItem)) {
            cir.setReturnValue(WeaversParadiseSoundEvents.HURT_OLD);
        } else if (WPTrinkets.isEquipped(player, itemStack -> itemStack.getItem() instanceof FishItem fishItem)) {
            cir.setReturnValue(SoundEvents.AXOLOTL_HURT);
        }
    }
}
