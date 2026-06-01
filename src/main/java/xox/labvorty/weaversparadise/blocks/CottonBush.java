package xox.labvorty.weaversparadise.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.util.LazyOptional;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotResult;
import top.theillusivec4.curios.api.type.capability.ICuriosItemHandler;
import xox.labvorty.weaversparadise.init.WeaversParadiseItems;
import xox.labvorty.weaversparadise.items.clothing.LeatherGlovesItem;

import java.util.Optional;

public class CottonBush extends CropBlock {
    public static final int MAX_AGE = 6;
    public static final IntegerProperty AGE = IntegerProperty.create("age", 0, MAX_AGE);

    private static final VoxelShape[] SHAPE_BY_AGE = new VoxelShape[]{
            Block.box(6.0, -1.0, 6.0, 10.0, 6.0, 10.0),
            Block.box(6.0, -1.0, 6.0, 10.0, 6.0, 10.0),
            Block.box(5.0, -1.0, 5.0, 11.0, 12.0, 11.0),
            Block.box(3.0, -1.0, 3.0, 13.0, 14.0, 13.0),
            Block.box(3.0, -1.0, 3.0, 13.0, 14.0, 13.0),
            Block.box(3.0, -1.0, 3.0, 13.0, 14.0, 13.0),
            Block.box(3.0, -1.0, 3.0, 13.0, 14.0, 13.0)
    };

    public CottonBush(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(AGE, 0));
    }

    @Override
    protected IntegerProperty getAgeProperty() {
        return AGE;
    }

    @Override
    public int getMaxAge() {
        return MAX_AGE;
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return WeaversParadiseItems.COTTON_SEEDS.get();
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE_BY_AGE[this.getAge(state)];
    }

    @Override
    public void playerWillDestroy(Level level, BlockPos pos, BlockState state, Player player) {
        LazyOptional<ICuriosItemHandler> handlerOpt = CuriosApi.getCuriosInventory(player);
        if (handlerOpt.isPresent() && state.getValue(AGE) == MAX_AGE) {
            handlerOpt.ifPresent((curiosHandler) -> {
                boolean hasGloves = curiosHandler.isEquipped(stack -> stack.getItem() instanceof LeatherGlovesItem);

                if (hasGloves) {
                    Optional<SlotResult> slotOpt = curiosHandler.findFirstCurio(WeaversParadiseItems.LEATHER_GLOVES.get());

                    if (slotOpt.isPresent()) {
                        SlotResult slotResult = slotOpt.get();
                        ItemStack stack = slotResult.stack();
                        RandomSource random = level.random;

                        if (stack.getItem() instanceof LeatherGlovesItem gloves
                                && gloves.shouldReceiveDamage(stack, random)) {

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