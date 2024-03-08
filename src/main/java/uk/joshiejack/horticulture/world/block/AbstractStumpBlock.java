package uk.joshiejack.horticulture.world.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.items.IItemHandler;
import org.jetbrains.annotations.NotNull;
import uk.joshiejack.horticulture.world.block.entity.AbstractStumpBlockEntity;
import uk.joshiejack.penguinlib.world.block.PenguinBlock;

import javax.annotation.Nonnull;

@SuppressWarnings("deprecation")
public abstract class AbstractStumpBlock extends PenguinBlock implements BonemealableBlock, EntityBlock {
    public static final IntegerProperty AGE = BlockStateProperties.AGE_3;
    private static final VoxelShape BOUNDING_BOX = Shapes.create(0.1D, 0.0D, 0.1D, 0.9D, 0.5D, 0.9D);

    public AbstractStumpBlock(BlockBehaviour.Properties block) {
        super(block.randomTicks());
        this.hasInventory = true;
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

    @Nonnull
    @Override
    public @NotNull VoxelShape getShape(@NotNull BlockState state, @NotNull BlockGetter getter, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        return BOUNDING_BOX;
    }

    @Override
    public void tick(@Nonnull BlockState state, @Nonnull ServerLevel world, @Nonnull BlockPos pos, @Nonnull RandomSource random) {
        BlockEntity tile = world.getBlockEntity(pos);
        if (tile instanceof AbstractStumpBlockEntity<?> stump) {
            if (!stump.getItem(0).isEmpty() && state.getValue(AGE) < 3) {
                stump.grow();
            }
        }
    }

    @Override
    protected void onRemoved(IItemHandler handler, Level world, BlockPos pos) {
        BlockEntity tile = world.getBlockEntity(pos);
        if (tile instanceof AbstractStumpBlockEntity<?> stump) {
            if (stump.getHarvests() != 0) return;
        }

        super.onRemoved(handler, world, pos);
    }

    @Override
    public boolean isValidBonemealTarget(@Nonnull LevelReader world, @Nonnull BlockPos pos, @Nonnull BlockState state) {
        BlockEntity tile = world.getBlockEntity(pos);
        return tile instanceof AbstractStumpBlockEntity<?> && !((AbstractStumpBlockEntity<?>) tile).getItem(0).isEmpty() && state.getValue(AGE) < 3;
    }

    @Override
    public boolean isBonemealSuccess(@Nonnull Level world, @Nonnull RandomSource rand, @Nonnull BlockPos pos, @Nonnull BlockState state) {
        return state.getValue(AGE) < 3;
    }

    @Override
    public void performBonemeal(@Nonnull ServerLevel world, @Nonnull RandomSource rand, @Nonnull BlockPos pos, @Nonnull BlockState state) {
        randomTick(state, world, pos, rand); //YES!
    }
}
