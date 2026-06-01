package xox.labvorty.weaversparadise.mixins;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.entity.SculkSensorBlockEntity;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraftforge.common.util.LazyOptional;
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

@Mixin(SculkSensorBlockEntity.VibrationUser.class)
public class SculkBlockEntityMixin {
    @Inject(
            method = "canReceiveVibration",
            at = @At("HEAD"),
            cancellable = true
    )
    private void disableVibration(
            ServerLevel pLevel, BlockPos pPos, GameEvent gameEvent, GameEvent.Context context, CallbackInfoReturnable<Boolean> cir
    ) {
        if (!CommonConfig.ITEM_SPECIAL_ABILITIES.get()) return;

        if (context != null) {
            Entity entity = context.sourceEntity();

            if (entity instanceof Player player) {
                LazyOptional<ICuriosItemHandler> handler = CuriosApi.getCuriosInventory(player);

                if (handler.isPresent()) {
                    handler.ifPresent((curioHandler) -> {
                        if (curioHandler.isEquipped(stack -> {
                            if (stack.getItem() instanceof ThighHighsWoolItem thighHighsWool) {
                                int durabilityLeft = stack.getMaxDamage() - stack.getDamageValue();
                                if (!(durabilityLeft > 1)) {
                                    return false;
                                }

                                if (stack.getAllEnchantments()
                                        .keySet()
                                        .stream()
                                        .anyMatch((holder) -> {
                                            return holder == WeaversParadiseEnchantments.SOUND_MUFFLER.get();
                                        })
                                ) {
                                    return true;
                                }
                            }

                            return false;
                        })) {
                            if (
                                    gameEvent.equals(GameEvent.STEP)
                                            || gameEvent.equals(GameEvent.HIT_GROUND)
                            ) {
                                cir.setReturnValue(false);
                            }
                        }

                        if (curioHandler.isEquipped(stack -> {
                            if (stack.getItem() instanceof HandWarmersWoolItem handWarmersWool) {
                                int durabilityLeft = stack.getMaxDamage() - stack.getDamageValue();
                                if (!(durabilityLeft > 1)) {
                                    return false;
                                }

                                if (stack.getAllEnchantments()
                                        .keySet()
                                        .stream()
                                        .anyMatch((holder) -> {
                                            return holder == WeaversParadiseEnchantments.SOUND_MUFFLER.get();
                                        })
                                ) {
                                    return true;
                                }
                            }

                            return false;
                        })) {
                            if (gameEvent.equals(GameEvent.BLOCK_PLACE)
                                    || gameEvent.equals(GameEvent.BLOCK_DESTROY)
                                    || gameEvent.equals(GameEvent.BLOCK_ACTIVATE)
                                    || gameEvent.equals(GameEvent.BLOCK_DEACTIVATE)
                                    || gameEvent.equals(GameEvent.BLOCK_CHANGE)
                            ) {
                                cir.setReturnValue(false);
                            }
                        }
                    });
                }
            }
        }
    }
}