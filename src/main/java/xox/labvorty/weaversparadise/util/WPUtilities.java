package xox.labvorty.weaversparadise.util;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.EmptyBlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.joml.Vector3f;

import java.util.List;

/** Port of legacy lib utilities (1:1, no NeoForge). */
public class WPUtilities {
    public static void tryInsertOrDrop(Player player, ItemStack stack) {
        if (stack.isEmpty()) return;
        ItemStack stackCopy = stack.copy();
        int totalCountBefore = player.getInventory().items.stream()
                .filter(s -> !s.isEmpty())
                .mapToInt(ItemStack::getCount)
                .sum();
        if (!player.getInventory().add(stackCopy)) {
            player.drop(stack, false);
        } else {
            int totalCountAfter = player.getInventory().items.stream()
                    .filter(s -> !s.isEmpty())
                    .mapToInt(ItemStack::getCount)
                    .sum();
            if (totalCountBefore == totalCountAfter) {
                player.drop(stack, false);
            }
        }
    }

    /** КЛИЕНТСКИЙ метод — вызывать только на клиенте. */
    public static boolean isSimpleBlockItem(ItemStack stack) {
        if (!(stack.getItem() instanceof BlockItem blockItem)) {
            return false;
        }
        Block block = blockItem.getBlock();
        BlockState defaultState = block.defaultBlockState();
        if (block instanceof StairBlock || block instanceof SlabBlock) {
            return true;
        }
        return isFullCube(defaultState);
    }

    private static boolean isFullCube(BlockState state) {
        VoxelShape shape = state.getShape(EmptyBlockGetter.INSTANCE, BlockPos.ZERO, CollisionContext.empty());
        return Block.isShapeFullBlock(shape);
    }

    public static Component createHoldBar(int ticks, int maxTicks) {
        int bars = 40;
        int filled = Math.min(bars, (ticks * bars) / maxTicks);
        MutableComponent component = Component.empty();
        component.append(Component.literal("[").withStyle(ChatFormatting.DARK_GRAY));
        for (int i = 0; i < bars; i++) {
            if (i < filled) {
                component.append(Component.literal("|").withStyle(ChatFormatting.GRAY));
            } else {
                component.append(Component.literal("|").withStyle(ChatFormatting.DARK_GRAY));
            }
        }
        component.append(Component.literal("]").withStyle(ChatFormatting.DARK_GRAY));
        return component;
    }

    public static void serializeVector3f(CompoundTag compoundTag, Vector3f vector3f) {
        serializeSpecificVector3f(compoundTag, vector3f, "vector3f");
    }

    public static void serializeSpecificVector3f(CompoundTag compoundTag, Vector3f vector3f, String name) {
        compoundTag.putFloat(name + "_x", vector3f.x);
        compoundTag.putFloat(name + "_y", vector3f.y);
        compoundTag.putFloat(name + "_z", vector3f.z);
    }

    public static Vector3f deserializeVector3f(CompoundTag compoundTag) {
        return deserializeSpecificVector3f(compoundTag, "vector3f");
    }

    public static Vector3f deserializeSpecificVector3f(CompoundTag compoundTag, String name) {
        return new Vector3f(
                compoundTag.getFloat(name + "_x"),
                compoundTag.getFloat(name + "_y"),
                compoundTag.getFloat(name + "_z")
        );
    }
}
