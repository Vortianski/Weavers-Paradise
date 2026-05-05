package xox.labvorty.weaversparadise.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

import net.minecraftforge.common.util.LazyOptional;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotResult;
import top.theillusivec4.curios.api.type.capability.ICuriosItemHandler;

import xox.labvorty.weaversparadise.init.WeaversParadiseItems;
import xox.labvorty.weaversparadise.items.clothing.LeatherGlovesItem;

import java.util.Optional;

public class WildCottonPlant extends FlowerBlock {

    public WildCottonPlant() {
        super(
                () -> MobEffects.UNLUCK,
                1,
                BlockBehaviour.Properties.of()
                        .noCollission()
                        .sound(SoundType.GRASS)
                        .instabreak()
                        .offsetType(BlockBehaviour.OffsetType.NONE)
                        .pushReaction(PushReaction.DESTROY)
                        .mapColor(MapColor.PLANT)
        );
    }

    @Override
    public void playerWillDestroy(Level level, BlockPos pos, BlockState state, Player player) {

        LazyOptional<ICuriosItemHandler> handlerOpt = CuriosApi.getCuriosInventory(player);

        if (handlerOpt.isPresent()) {
            handlerOpt.ifPresent((curiosHandler) -> {
                boolean hasGloves = curiosHandler.isEquipped(stack -> stack.getItem() instanceof LeatherGlovesItem);

                if (hasGloves) {
                    Optional<SlotResult> slotOpt = curiosHandler.findFirstCurio(WeaversParadiseItems.LEATHER_GLOVES.get());

                    if (slotOpt.isPresent()) {
                        SlotResult slotResult = slotOpt.get();
                        ItemStack stack = slotResult.stack();
                        RandomSource random = level.random;

                        if (stack.getItem() instanceof LeatherGlovesItem gloves && gloves.shouldReceiveDamage(stack, random)) {
                            stack.setDamageValue(stack.getDamageValue() + 1);

                            curiosHandler.setEquippedCurio(
                                    slotResult.slotContext().identifier(),
                                    slotResult.slotContext().index(),
                                    stack
                            );
                        }
                    }

                } else {
                    player.hurt(level.damageSources().cactus(), 1.0F);
                }
            });
        }
    }
}