package uk.joshiejack.horticulture.world.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.common.CommonHooks;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;

@SuppressWarnings("deprecation")
public class FruitBlock extends BushBlock implements BonemealableBlock {
    public static final MapCodec<FruitBlock> CODEC = simpleCodec(FruitBlock::new);

    public static final IntegerProperty AGE = BlockStateProperties.AGE_3;
    private static final VoxelShape SML_AAB = Shapes.create(0.30000001192092896D, 0.5D, 0.30000001192092896D, 0.699999988079071D, 1D, 0.699999988079071D);
    private static final VoxelShape MED_AABB = Shapes.create(0.30000001192092896D, 0.4D, 0.30000001192092896D, 0.699999988079071D, 1D, 0.699999988079071D);
    private static final VoxelShape GROWN_AABB = Shapes.create(0.30000001192092896D, 0.2D, 0.30000001192092896D, 0.699999988079071D, 1D, 0.699999988079071D);

    public FruitBlock(BlockBehaviour.Properties properties) {
        super(properties.mapColor(MapColor.PLANT).randomTicks().strength(0.2F, 3.0F).sound(SoundType.CROP).noOcclusion());
        this.registerDefaultState(getStateDefinition().any().setValue(AGE, 0));
    }

    @Override
    protected @NotNull MapCodec<? extends BushBlock> codec() {
        return CODEC;
    }

    @Nonnull
    @Override
    public @NotNull VoxelShape getShape(@NotNull BlockState state, @NotNull BlockGetter getter, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        return switch (state.getValue(AGE)) {
            case 0 -> SML_AAB;
            case 1 -> MED_AABB;
            case 2, 3 -> GROWN_AABB;
            default -> GROWN_AABB;
        };

    }

    @Override
    public boolean canSurvive(@Nonnull BlockState state, @Nonnull LevelReader world, @Nonnull BlockPos pos) {
        return state.getBlock() != this || world.getBlockState(pos.above()).is(BlockTags.LEAVES);
    }

    @Nonnull
    @Override
    public @NotNull InteractionResult use(@Nonnull BlockState state, @NotNull Level world, @Nonnull BlockPos pos, @Nonnull Player player, @Nonnull InteractionHand hand, @Nonnull BlockHitResult blockRayTraceResult) {
        if (state.getValue(AGE) == 3) {
            dropResources(state, world, pos);
            world.setBlock(pos, Blocks.AIR.defaultBlockState(), 2);
            return InteractionResult.SUCCESS;
        } else return InteractionResult.PASS;
    }

    @Override
    public void randomTick(@Nonnull BlockState state, @Nonnull ServerLevel world, @Nonnull BlockPos pos, @Nonnull RandomSource rand) {
        if (!world.isAreaLoaded(pos, 1))
            return;
        if (isValidBonemealTarget(world, pos, state)) {
            if (CommonHooks.onCropsGrowPre(world, pos, state, rand.nextFloat() <= 0.1F)) {
                performBonemeal(world, rand, pos, state);
                CommonHooks.onCropsGrowPost(world, pos, state);
            }
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

    @Override
    public boolean isValidBonemealTarget(@Nonnull LevelReader worldIn, @Nonnull BlockPos pos, @Nonnull BlockState state) {
        return state.getValue(AGE) < 3;
    }

    @Override
    public boolean isBonemealSuccess(@Nonnull Level worldIn, @Nonnull RandomSource rand, @Nonnull BlockPos pos, @Nonnull BlockState state) {
        return true;
    }

    @Override
    public void performBonemeal(@Nonnull ServerLevel worldIn, @Nonnull RandomSource rand, @Nonnull BlockPos pos, @Nonnull BlockState state) {
        worldIn.setBlock(pos, state.setValue(AGE, state.getValue(AGE) + 1), 2);
    }
}
