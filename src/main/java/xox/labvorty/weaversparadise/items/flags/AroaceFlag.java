package xox.labvorty.weaversparadise.items.flags;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.decoration.HangingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.HangingEntityItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import xox.labvorty.weaversparadise.entities.HangingFlagEntity;
import xox.labvorty.weaversparadise.init.WeaversParadiseEntityTypes;

import java.util.Optional;

public class AroaceFlag extends HangingEntityItem {
    private final EntityType<? extends HangingEntity> type;

    public AroaceFlag() {
        super(
                WeaversParadiseEntityTypes.HANGING_FLAG_ENTITY.get(),
                new Properties().rarity(Rarity.COMMON).stacksTo(1)
        );
        this.type = WeaversParadiseEntityTypes.HANGING_FLAG_ENTITY.get();
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        BlockPos blockpos = context.getClickedPos();
        Direction direction = context.getClickedFace();
        BlockPos blockpos1 = blockpos.relative(direction);
        Player player = context.getPlayer();
        ItemStack itemstack = context.getItemInHand();

        if (player != null && !this.mayPlace(player, direction, itemstack, blockpos1)) {
            return InteractionResult.FAIL;
        } else {
            Level level = context.getLevel();
            HangingEntity hangingentity;

            if (this.type == WeaversParadiseEntityTypes.HANGING_FLAG_ENTITY.get()) {
                Optional<HangingFlagEntity> hangingFlagEntity = HangingFlagEntity.create(level, blockpos1, direction, "aroace");

                hangingentity = hangingFlagEntity.get();

                if (hangingentity.survives()) {
                    if (!level.isClientSide) {
                        hangingentity.playPlacementSound();
                        level.gameEvent(player, GameEvent.ENTITY_PLACE, hangingentity.position());
                        level.addFreshEntity(hangingentity);
                    }

                    itemstack.shrink(1);
                    return InteractionResult.sidedSuccess(level.isClientSide);
                } else {
                    return InteractionResult.CONSUME;
                }
            } else {
                return InteractionResult.sidedSuccess(level.isClientSide);
            }
        }
    }
}
