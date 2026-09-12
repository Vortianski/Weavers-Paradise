package xox.labvorty.weaversparadise.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.SuspiciousStewEffects;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import xox.labvorty.weaversparadise.util.WPTrinkets;
import xox.labvorty.weaversparadise.init.WeaversParadiseItems;
import xox.labvorty.weaversparadise.items.clothing.LeatherGlovesItem;


public class WildCottonPlant extends FlowerBlock {
    public WildCottonPlant() {
        super(SuspiciousStewEffects.EMPTY, BlockBehaviour.Properties.of()
                .noCollission()
                .sound(SoundType.GRASS)
                .instabreak()
                .offsetType(BlockBehaviour.OffsetType.NONE)
                .pushReaction(PushReaction.DESTROY)
                .mapColor(MapColor.PLANT)
        );
    }

    @Override
    public BlockState playerWillDestroy(Level level, BlockPos pos, BlockState state, Player player) {
        if (WPTrinkets.isEquipped(player, stack -> stack.getItem() instanceof LeatherGlovesItem)) {
            RandomSource randomSource = RandomSource.create();
            var slotPair = WPTrinkets.findFirstCurio(player, WeaversParadiseItems.LEATHER_GLOVES);
            if (slotPair.isPresent()) {
                ItemStack stack = slotPair.get().getB();
                if (stack.getItem() instanceof LeatherGlovesItem leatherGloves && leatherGloves.shouldReceiveDamage(stack, randomSource)) {
                    stack.setDamageValue(stack.getDamageValue() + 1);
                    WPTrinkets.setEquipped(slotPair.get().getA(), stack);
                }
            }
        } else {
            player.hurt(level.damageSources().cactus(), 1);
        }

        return super.playerWillDestroy(level, pos, state, player);
    }
}
