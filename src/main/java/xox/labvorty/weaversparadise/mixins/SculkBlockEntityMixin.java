package xox.labvorty.weaversparadise.mixins;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.gameevent.GameEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.capability.ICuriosItemHandler;
import xox.labvorty.weaversparadise.configs.CommonConfig;
import xox.labvorty.weaversparadise.init.WeaversParadiseEnchantments;
import xox.labvorty.weaversparadise.items.clothing.HandWarmersWoolItem;
import xox.labvorty.weaversparadise.items.clothing.ThighHighsWoolItem;

import java.util.Optional;

@Mixin(targets = "net.minecraft.world.level.block.entity.SculkSensorBlockEntity$VibrationUser")
public class SculkBlockEntityMixin {
    @Inject(
            method = "canReceiveVibration(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/core/BlockPos;Lnet/minecraft/core/Holder;Lnet/minecraft/world/level/gameevent/GameEvent$Context;)Z",
            at = @At("HEAD"),
            cancellable = true,
            remap = false
    )
    private void disableVibration(
            ServerLevel level,
            BlockPos pos,
            Holder<GameEvent> gameEvent,
            GameEvent.Context context,
            CallbackInfoReturnable<Boolean> cir
    ) {
        if (CommonConfig.ITEM_SPECIAL_ABILITIES.get()) {
            return;
        }

        if (context != null) {
            Entity entity = context.sourceEntity();

            if (entity instanceof Player player) {
                Optional<ICuriosItemHandler> handler = CuriosApi.getCuriosInventory(player);

            if (handler.isPresent()) {
                    if (handler.get().isEquipped(stack -> {
                        if (stack.getItem() instanceof ThighHighsWoolItem thighHighsWool) {
                            int durabilityLeft = stack.getMaxDamage() - stack.getDamageValue();
                            if (!(durabilityLeft > 1)) {
                                return false;
                            }

                            if (stack.getAllEnchantments(entity.level().registryAccess().lookupOrThrow(Registries.ENCHANTMENT))
                                    .keySet()
                                    .stream()
                                    .anyMatch((holder) -> {
                                        return holder.is(WeaversParadiseEnchantments.SOUND_MUFFLER);
                                    })
                            ) {
                                return true;
                            }
                        }

                        return false;
                    })) {
                        if (
                                gameEvent.getKey().equals(GameEvent.STEP.getKey())
                                        || gameEvent.getKey().equals(GameEvent.HIT_GROUND.getKey())
                        ) {
                            cir.setReturnValue(false);
                        }
                    }

                    if (handler.get().isEquipped(stack -> {
                        if (stack.getItem() instanceof HandWarmersWoolItem handWarmersWool) {
                            int durabilityLeft = stack.getMaxDamage() - stack.getDamageValue();
                            if (!(durabilityLeft > 1)) {
                                return false;
                            }

                            if (stack.getAllEnchantments(entity.level().registryAccess().lookupOrThrow(Registries.ENCHANTMENT))
                                    .keySet()
                                    .stream()
                                    .anyMatch((holder) -> {
                                        return holder.is(WeaversParadiseEnchantments.SOUND_MUFFLER);
                                    })
                            ) {
                                return true;
                            }
                        }

                        return false;
                    })) {
                        if (gameEvent.getKey().equals(GameEvent.BLOCK_PLACE.getKey())
                                || gameEvent.getKey().equals(GameEvent.BLOCK_DESTROY.getKey())
                                || gameEvent.getKey().equals(GameEvent.BLOCK_ACTIVATE.getKey())
                                || gameEvent.getKey().equals(GameEvent.BLOCK_DEACTIVATE.getKey())
                                || gameEvent.getKey().equals(GameEvent.BLOCK_CHANGE.getKey())
                        ) {
                            cir.setReturnValue(false);
                        }
                    }
                }
            }
        }
    }
}