package uk.joshiejack.horticulture.world.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.neoforged.neoforge.common.CommonHooks;

import javax.annotation.Nonnull;
import java.util.function.Supplier;

@SuppressWarnings("WeakerAccess")
public abstract class AbstractFruitTreeLeavesBlock extends LeavesBlock implements BonemealableBlock {
    public static final BooleanProperty IN_SEASON = BooleanProperty.create("in_season");
    private final StateArgumentPredicate<Supplier<Block>> predicate;
    protected final Supplier<Block> fruit;

    public AbstractFruitTreeLeavesBlock(Supplier<Block> fruit, StateArgumentPredicate<Supplier<Block>> function) {
        super(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES).strength(0.2F).randomTicks()
                .noOcclusion().isValidSpawn(AbstractFruitTreeLeavesBlock::ocelotOrParrot)
                .isSuffocating(AbstractFruitTreeLeavesBlock::never).isViewBlocking(AbstractFruitTreeLeavesBlock::never));
        this.registerDefaultState(getStateDefinition().any().setValue(DISTANCE, 7).setValue(PERSISTENT, Boolean.FALSE).setValue(IN_SEASON, Boolean.TRUE));
        this.fruit = fruit;
        this.predicate = function;
    }

    @Override
    public boolean isRandomlyTicking(BlockState state) {
        return (state.getValue(DISTANCE) == 7 && !state.getValue(PERSISTENT)) || state.getValue(IN_SEASON);
    }

    @Override
    public void tick(@Nonnull BlockState state, @Nonnull ServerLevel world, @Nonnull BlockPos pos, @Nonnull RandomSource random) {
        if (!state.getValue(PERSISTENT) && state.getValue(DISTANCE) == 7) {
            dropResources(state, world, pos);
            world.removeBlock(pos, false);
            world.updateNeighborsAt(pos, Blocks.AIR);
        } else if (state.getValue(IN_SEASON)) {
            if (CommonHooks.onCropsGrowPre(world, pos, state, random.nextFloat() <= 0.2F)) {
                performBonemeal(world, random, pos, state);
                CommonHooks.onCropsGrowPost(world, pos, state);
            }
        }
    }

    @Override
    public boolean isValidBonemealTarget(@Nonnull LevelReader world, @Nonnull BlockPos pos, @Nonnull BlockState state) {
        return world.getBlockState(pos.below()).isAir();
    }

    @Override
    public boolean isBonemealSuccess(@Nonnull Level world, @Nonnull RandomSource rand, @Nonnull BlockPos pos, @Nonnull BlockState state) {
        return rand.nextFloat() < 0.25F;
    }

    @Override
    public void performBonemeal(@Nonnull ServerLevel world, @Nonnull RandomSource rand, @Nonnull BlockPos pos, @Nonnull BlockState state) {
        if (!state.getValue(BlockStateProperties.PERSISTENT) && predicate.test(state, world, pos.below(), fruit)
                && world.getBlockState(pos.below()).isAir()) {
            world.setBlock(pos.below(), this.fruit.get().defaultBlockState(), 3);
        }
    }

    private static Boolean ocelotOrParrot(BlockState state, BlockGetter reader, BlockPos pos, EntityType<?> type) {
        return type == EntityType.OCELOT || type == EntityType.PARROT;
    }

    @SuppressWarnings("SameReturnValue")
    private static boolean never(BlockState state, BlockGetter reader, BlockPos pos) {
        return false;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(DISTANCE, PERSISTENT, WATERLOGGED, IN_SEASON);
    }
}