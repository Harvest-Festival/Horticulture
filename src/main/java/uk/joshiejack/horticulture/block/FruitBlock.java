package uk.joshiejack.horticulture.block;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import uk.joshiejack.horticulture.HorticultureConfig;

import javax.annotation.Nonnull;
import java.util.Random;

public class FruitBlock extends BushBlock {
    public static final IntegerProperty AGE = BlockStateProperties.AGE_3;
    private static final VoxelShape SML_AAB = VoxelShapes.box(0.30000001192092896D, 0.5D, 0.30000001192092896D, 0.699999988079071D, 1D, 0.699999988079071D);
    private static final VoxelShape MED_AABB = VoxelShapes.box(0.30000001192092896D, 0.4D, 0.30000001192092896D, 0.699999988079071D, 1D, 0.699999988079071D);
    private static final VoxelShape GROWN_AABB = VoxelShapes.box(0.30000001192092896D, 0.2D, 0.30000001192092896D, 0.699999988079071D, 1D, 0.699999988079071D);

    public FruitBlock() {
        super(AbstractBlock.Properties.of(Material.PLANT).randomTicks().strength(0.2F, 3.0F).sound(SoundType.CROP).noOcclusion());
        this.registerDefaultState(this.stateDefinition.any().setValue(AGE, 0));
    }

    @SuppressWarnings("deprecation")
    @Nonnull
    @Override
    public VoxelShape getShape(BlockState state, @Nonnull IBlockReader source, @Nonnull BlockPos pos, @Nonnull ISelectionContext ctx) {
        switch (state.getValue(AGE)) {
            case 0:
                return SML_AAB;
            case 1:
                return MED_AABB;
            case 2:
            case 3:
                return GROWN_AABB;
        }

        return GROWN_AABB;
    }

    @Override
    public boolean canSurvive(@Nonnull BlockState state, @Nonnull IWorldReader world, BlockPos pos) {
        if (state.getBlock() == this) {
            return BlockTags.LEAVES.contains(world.getBlockState(pos.above()).getBlock());
        }

        return true;
    }

    @Override
    public ActionResultType use(@Nonnull BlockState state, @Nonnull World world, @Nonnull BlockPos pos, @Nonnull PlayerEntity player,
                                @Nonnull Hand hand, @Nonnull BlockRayTraceResult blockRayTraceResult) {
        if (state.getValue(AGE) == 3) {
            dropResources(state, world, pos);
            world.setBlock(pos, Blocks.AIR.defaultBlockState(), 2);
            return ActionResultType.SUCCESS;
        } else return ActionResultType.PASS;
    }

    @Override
    public void randomTick(@Nonnull BlockState state, @Nonnull ServerWorld world, @Nonnull BlockPos pos, @Nonnull Random rand) {
        if (world.random.nextDouble() <= HorticultureConfig.fruitGrowthChance) {
            super.randomTick(state, world, pos, rand);
        }
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }
}
