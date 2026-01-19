package xox.labvorty.weaversparadise.mixins;

import com.mojang.datafixers.util.Pair;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.gameevent.GameEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.capability.ICuriosItemHandler;
import top.theillusivec4.curios.common.CuriosHelper;
import xox.labvorty.weaversparadise.data.WeaversParadiseStatHandler;
import xox.labvorty.weaversparadise.init.WeaversParadiseItems;
import xox.labvorty.weaversparadise.items.HandWarmersSilk;
import xox.labvorty.weaversparadise.items.HandWarmersWool;
import xox.labvorty.weaversparadise.items.ThighHighsCotton;
import xox.labvorty.weaversparadise.items.ThighHighsWool;

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
        if (context != null) {
            Entity entity = context.sourceEntity();

            if (entity instanceof Player player) {
                Optional<ICuriosItemHandler> handler = CuriosApi.getCuriosInventory(player);
                Pair<String, Double> ability = WeaversParadiseStatHandler.calculateStaticType((LivingEntity)player);

                if (ability.getFirst().equals("wool") && ability.getSecond() > 0) {
                    if (handler.isPresent()) {
                        if (handler.get().isEquipped(stack -> {
                            if (stack.getItem() instanceof ThighHighsWool thighHighsWool) {
                                return true;
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
                            if (stack.getItem() instanceof HandWarmersWool handWarmersWool) {
                                return true;
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
}