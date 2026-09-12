package xox.labvorty.weaversparadise.items.misc;

import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xox.labvorty.weaversparadise.blocks.entities.BlahajBlockEntity;
import xox.labvorty.weaversparadise.items.clothing.defined.DoubleSidedBlockItem;

public class BlahajItem extends DoubleSidedBlockItem implements Plushie {
    public BlahajItem(Block block) {
        super(
                block,
                new Properties()
                        .stacksTo(1)
                        .rarity(Rarity.RARE)
                        .component(DataComponents.CUSTOM_DATA, CustomData.of(createDefault()))
        );
    }

    @Override
    protected boolean updateCustomBlockEntityTag(@NotNull BlockPos pos, @NotNull Level level, @Nullable Player player, @NotNull ItemStack stack, @NotNull BlockState state) {
        BlockEntity blockEntity = level.getBlockEntity(pos);

        if (blockEntity instanceof BlahajBlockEntity blahajBlockEntity) {
            blahajBlockEntity.setItemStack(stack);
        }

        return super.updateCustomBlockEntityTag(pos, level, player, stack, state);
    }
}