package xox.labvorty.weaversparadise.mixins;

import net.minecraft.world.level.block.entity.HopperBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(HopperBlockEntity.class)
public interface HopperBlockEntityAccessor {
    @Invoker("isOnCustomCooldown")
    boolean weaversparadise$callIsOnCustomCooldown();
}
