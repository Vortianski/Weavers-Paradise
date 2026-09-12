package xox.labvorty.weaversparadise.items.flags;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.decoration.HangingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.HangingEntityItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import org.jetbrains.annotations.NotNull;
import xox.labvorty.weaversparadise.entities.HangingFlagEntity;
import xox.labvorty.weaversparadise.init.WeaversParadiseEntityTypes;

import java.util.Optional;

public class TransFlagItem extends HangingEntityItem {
    private final EntityType<? extends HangingEntity> type;
    private static final String flagType = "trans";

    public TransFlagItem() {
        super(
                WeaversParadiseEntityTypes.HANGING_FLAG_ENTITY,
                new Properties().rarity(Rarity.COMMON).stacksTo(1)
        );
        this.type = WeaversParadiseEntityTypes.HANGING_FLAG_ENTITY;
    }

    @Override
    public @NotNull InteractionResult useOn(UseOnContext context) {
        BlockPos blockPos = context.getClickedPos();
        Direction direction = context.getClickedFace();
        BlockPos blockPos1 = blockPos.relative(direction);
        Player player = context.getPlayer();
        ItemStack itemstack = context.getItemInHand();

        if (player != null && !this.mayPlace(player, direction, itemstack, blockPos1)) {
            return InteractionResult.FAIL;
        } else {
            Level level = context.getLevel();
            HangingEntity hangingentity;

            if (this.type == WeaversParadiseEntityTypes.HANGING_FLAG_ENTITY) {
                Optional<HangingFlagEntity> hangingFlagEntity = HangingFlagEntity.create(level, blockPos1, direction, flagType);

                if (hangingFlagEntity.isEmpty()) return InteractionResult.FAIL;

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
