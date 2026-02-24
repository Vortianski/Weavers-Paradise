package xox.labvorty.weaversparadise.mixins;

import com.mojang.datafixers.util.Pair;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.capability.ICuriosItemHandler;
import xox.labvorty.weaversparadise.data.WeaversParadiseStatHandler;
import xox.labvorty.weaversparadise.items.ThighHighsSilk;

import java.util.Optional;

@Mixin(Entity.class)
public class EntityMixin {
    @ModifyVariable(
            method = "move",
            ordinal = 1,
            index = 3,
            name = "vec32",
            at = @At(
                    value = "INVOKE_ASSIGN",
                    target = "Lnet/minecraft/world/entity/Entity;collide(Lnet/minecraft/world/phys/Vec3;)Lnet/minecraft/world/phys/Vec3;"
            )
    )
    public Vec3 weaversparadise$walkOnWater(Vec3 original) {
        if (!((Entity) (Object) this instanceof LivingEntity entity)) {
            return original;
        }

        Optional<ICuriosItemHandler> handler = CuriosApi.getCuriosInventory(entity);
        Pair<String, Double> ability = WeaversParadiseStatHandler.calculateStaticType(entity);

        if (original.y > 0 || !handler.isPresent() || entity.isShiftKeyDown()) {
            return original;
        }

        Level level = entity.getCommandSenderWorld();

        int[][] offsets = {
                {1, 0, 1}, {1, 0, 0}, {1, -1, 0}, {1, 0, -1},
                {0, 0, 1}, {0, 0, 0}, {0, -1, 0}, {0, 0, -1},
                {-1, 0, 1}, {-1, 0, 0}, {-1, -1, 0}, {-1, 0, -1}
        };

        double highestValue = original.y;
        FluidState highestFluid = null;

        for (int[] offset : offsets) {
            BlockPos sourcePos = entity.blockPosition();
            BlockPos pos = new BlockPos(sourcePos.getX() + offset[0], sourcePos.getY() + offset[1], sourcePos.getZ() + offset[2]);

            FluidState fluidState = level.getFluidState(pos);

            if (fluidState.isEmpty())
                continue;

            VoxelShape shape = Shapes.block().move(pos.getX(), pos.getY() + fluidState.getOwnHeight(), pos.getZ());

            if (Shapes.joinIsNotEmpty(shape, Shapes.create(entity.getBoundingBox().inflate(0.5)), BooleanOp.AND)) {
                double height = shape.max(Direction.Axis.Y) - entity.getY() - 1;

                if (highestValue < height) {
                    highestValue = height;
                    highestFluid = fluidState;
                }
            }
        }

        if (highestFluid == null || !highestFluid.is(FluidTags.WATER)) {
            return original;
        }

        if (ability.getFirst().equals("silk") && ability.getSecond() > 0) {
            if (handler.get().isEquipped(stack -> {
                if (stack.getItem() instanceof ThighHighsSilk thighHighsSilk) {
                    return true;
                }
                return false;
            })) {
                entity.fallDistance = 0F;
                entity.setOnGround(true);

                return new Vec3(original.x, highestValue, original.z);
            }
        }

        return original;
    }
}
