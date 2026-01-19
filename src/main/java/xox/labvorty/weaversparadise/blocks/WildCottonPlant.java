package xox.labvorty.weaversparadise.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.SuspiciousStewEffects;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotResult;
import top.theillusivec4.curios.api.type.capability.ICuriosItemHandler;
import xox.labvorty.weaversparadise.init.WeaversParadiseItems;
import xox.labvorty.weaversparadise.items.LeatherGloves;
import xox.labvorty.weaversparadise.items.ThighHighsSilk;

import java.util.Optional;

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
        Optional<ICuriosItemHandler> handler = CuriosApi.getCuriosInventory(player);
        if (handler.isPresent()) {
            if (handler.get().isEquipped(stack -> {
                if (stack.getItem() instanceof LeatherGloves leatherGloves) {
                    return true;
                }
                return false;
            })) {
                RandomSource randomSource = RandomSource.create();
                SlotResult slotResult = handler.get().findFirstCurio(WeaversParadiseItems.LEATHER_GLOVES.get()).get();
                ItemStack stack = slotResult.stack();
                if (stack.getItem() instanceof LeatherGloves leatherGloves && leatherGloves.shouldReceiveDamage(stack, randomSource)) {
                    stack.setDamageValue(stack.getDamageValue() + 1);
                    handler.get().setEquippedCurio(slotResult.slotContext().identifier(), slotResult.slotContext().index(),stack);
                }
            } else {
                player.hurt(level.damageSources().cactus(), 1);
            }
        }

        return super.playerWillDestroy(level, pos, state, player);
    }
}
