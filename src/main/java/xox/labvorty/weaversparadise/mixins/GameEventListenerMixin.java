package xox.labvorty.weaversparadise.mixins;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.gameevent.DynamicGameEventListener;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xox.labvorty.weaversparadise.data.WeaversParadiseSculkPlayerListener;
import java.util.function.BiConsumer;

@Mixin(Entity.class)
public class GameEventListenerMixin {
    @Inject(
            method = "updateDynamicGameEventListener",
            at = @At("TAIL")
    )
    private void weaversparadise$addListener(BiConsumer<DynamicGameEventListener<?>, ServerLevel> consumer, CallbackInfo ci) {
        if ((Object)this instanceof ServerPlayer player) {
            ServerLevel var3 = player.serverLevel();
            DynamicGameEventListener<?> listener = new DynamicGameEventListener(new WeaversParadiseSculkPlayerListener(player, 8));
            if (var3 != null) {
                consumer.accept(listener, player.serverLevel());
            }
        }
    }
}
