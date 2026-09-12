package xox.labvorty.weaversparadise.blocks;


import com.mojang.serialization.MapCodec;
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
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import xox.labvorty.weaversparadise.util.WPTrinkets;
import xox.labvorty.weaversparadise.init.WeaversParadiseItems;
import xox.labvorty.weaversparadise.items.clothing.LeatherGlovesItem;


public class CottonBush extends CropBlock {
    public static final MapCodec<CottonBush> CODEC = simpleCodec(CottonBush::new);
    public static final int MAX_AGE = 6;
    public static final IntegerProperty AGE;
    private static final VoxelShape[] SHAPE_BY_AGE;

    public CottonBush(BlockBehaviour.Properties properties) {
        super(properties);
    }

    @Override
    protected IntegerProperty getAgeProperty() {
        return AGE;
    }

    @Override
    public int getMaxAge() {
        return 6;
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return WeaversParadiseItems.COTTON_SEEDS;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(new Property[]{AGE});
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE_BY_AGE[this.getAge(state)];
    }

    @Override
    public BlockState playerWillDestroy(Level level, BlockPos pos, BlockState state, Player player) {
        if (state.getValue(AGE) == MAX_AGE) {
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
        }

        return super.playerWillDestroy(level, pos, state, player);
    }

    static {
        AGE = IntegerProperty.create("age", 0, 6);
        SHAPE_BY_AGE = new VoxelShape[]{
                Block.box(6.0, -1.0, 6.0, 10.0, 6.0, 10.0),
                Block.box(6.0, -1.0, 6.0, 10.0, 6.0, 10.0),
                Block.box(5.0, -1.0, 5.0, 11.0, 12.0, 11.0),
                Block.box(3.0, -1.0, 3.0, 13.0, 14.0, 13.0),
                Block.box(3.0, -1.0, 3.0, 13.0, 14.0, 13.0),
                Block.box(3.0, -1.0, 3.0, 13.0, 14.0, 13.0),
                Block.box(3.0, -1.0, 3.0, 13.0, 14.0, 13.0)};
    }
}
