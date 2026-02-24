package xox.labvorty.weaversparadise.mixins;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.capability.ICuriosItemHandler;
import xox.labvorty.weaversparadise.items.BellItem;
import java.util.Optional;

@Mixin(Player.class)
public class PlayerMixin {
    @Inject(method = "playStepSound", at = @At("HEAD"), cancellable = true)
    private void weaversparadise$makePlayerNoteBlock(BlockPos pos, BlockState state, CallbackInfo ci) {
        Player player = (Player) (Object) this;
        Optional<ICuriosItemHandler> handler = CuriosApi.getCuriosInventory(player);

        if (!handler.isPresent()) return;

        if (!handler.get().isEquipped(stack -> stack.getItem() instanceof BellItem bellItem)) {
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
}
