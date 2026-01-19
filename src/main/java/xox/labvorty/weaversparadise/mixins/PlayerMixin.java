package xox.labvorty.weaversparadise.mixins;

import com.mojang.datafixers.util.Pair;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.capability.ICuriosItemHandler;
import xox.labvorty.weaversparadise.data.WeaversParadiseStatHandler;
import xox.labvorty.weaversparadise.init.WeaversParadiseItems;
import xox.labvorty.weaversparadise.items.ThighHighsSilk;

import java.util.Optional;

@Mixin(LivingEntity.class)
public class PlayerMixin {
    @Inject(method = "canStandOnFluid", at = @At("HEAD"), cancellable = true)
    private void onCanStandOnFluid(FluidState fluidState, CallbackInfoReturnable<Boolean> cir) {
        if ((Object)this instanceof Player player) {
            Optional<ICuriosItemHandler> handler = CuriosApi.getCuriosInventory(player);
            BlockPos bPos = BlockPos.containing(player.position());
            BlockState bState = player.level().getBlockState(bPos);
            double fluidHeight = 0.0D;

            Pair<String, Double> ability = WeaversParadiseStatHandler.calculateStaticType((LivingEntity)player);

            if (!bState.getFluidState().isEmpty()) {
                fluidHeight = player.getFluidTypeHeight(player.level().getFluidState(bPos).getFluidType());
            }

            if (handler.isPresent() && fluidHeight < 0.4 && ability.getFirst().equals("silk") && ability.getSecond() > 0) {
                if (handler.get().isEquipped(stack -> {
                    if (stack.getItem() instanceof ThighHighsSilk thighHighsSilk) {
                        return true;
                    }
                    return false;
                })) {
                    cir.setReturnValue(
                            !(fluidState.is(Fluids.LAVA) || fluidState.is(Fluids.FLOWING_LAVA))
                    );
                }
            }
        }
    }
}
