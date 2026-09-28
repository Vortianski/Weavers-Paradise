package xox.labvorty.weaversparadise.mixins;

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
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.common.util.NonNullSupplier;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.capability.ICuriosItemHandler;
import xox.labvorty.weaversparadise.configs.CommonConfig;
import xox.labvorty.weaversparadise.init.WeaversParadiseEnchantments;
import xox.labvorty.weaversparadise.items.clothing.ThighHighsSilkItem;

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
    public Vec3 weaversparadise$walkOnWater(Vec3 original) throws Throwable {
        if (!((Object) this instanceof LivingEntity entity)) {
            return original;
        }

        if (!CommonConfig.ITEM_SPECIAL_ABILITIES.get()) {
            return original;
        }

        LazyOptional<ICuriosItemHandler> handler = CuriosApi.getCuriosInventory(entity);
        if (!handler.isPresent()) {
            return original;
        }

        ICuriosItemHandler curioHandler = handler.orElseThrow(new NonNullSupplier<Throwable>() {
            @Override
            public @NotNull Throwable get() {
                return null;
            }
        });

        if (original.y > 0 || entity.isShiftKeyDown()) {
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
            BlockPos pos = sourcePos.offset(offset[0], offset[1], offset[2]);

            FluidState fluidState = level.getFluidState(pos);
            if (fluidState.isEmpty()) continue;

            VoxelShape shape = Shapes.block()
                    .move(pos.getX(), pos.getY() + fluidState.getOwnHeight(), pos.getZ());

            if (Shapes.joinIsNotEmpty(shape,
                    Shapes.create(entity.getBoundingBox().inflate(0.5)),
                    BooleanOp.AND)) {

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

        boolean hasBoots = curioHandler.isEquipped(stack -> {
            if (stack.getItem() instanceof ThighHighsSilkItem item) {
                int durabilityLeft = stack.getMaxDamage() - stack.getDamageValue();
                if (durabilityLeft <= 1) return false;

                return stack.getAllEnchantments()
                        .keySet()
                        .stream()
                        .anyMatch(holder -> holder == WeaversParadiseEnchantments.WATER_STRIDER.get());
            }
            return false;
        });

        if (hasBoots) {
            entity.fallDistance = 0F;
            entity.setOnGround(true);
            return new Vec3(original.x, highestValue, original.z);
        }

        return original;
    }
}
