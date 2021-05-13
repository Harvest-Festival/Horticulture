package uk.joshiejack.horticulture.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.IGrowable;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.items.IItemHandler;
import uk.joshiejack.horticulture.tile.AbstractStumpTileEntity;
import uk.joshiejack.penguinlib.block.base.AbstractPenguinBlock;

import javax.annotation.Nonnull;
import java.util.Random;
import java.util.function.Supplier;

public class StumpBlock extends AbstractPenguinBlock implements IGrowable {
    public static final IntegerProperty AGE = CropBlock.AGE_4;
    private static final VoxelShape BOUNDING_BOX = VoxelShapes.box(0.1D, 0.0D, 0.1D, 0.9D, 0.5D, 0.9D);
    private final Supplier<TileEntity> tile;

    public StumpBlock(Block block, Supplier<TileEntity> tile) {
        super(copy(block).randomTicks());
        this.hasInventory = true;
        this.tile = tile;
    }

    private static AbstractBlock.Properties copy(Block block) {
        return AbstractBlock.Properties.of(block.defaultBlockState().getMaterial(), block.defaultMaterialColor())
                .strength(block.defaultBlockState().getDestroySpeed(null, null)).sound(block.defaultBlockState().getSoundType());
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

    @SuppressWarnings("deprecation")
    @Nonnull
    @Override
    public VoxelShape getShape(@Nonnull BlockState state, @Nonnull IBlockReader source, @Nonnull BlockPos pos, @Nonnull ISelectionContext ctx) {
        return BOUNDING_BOX;
    }

    @Nonnull
    @Override
    public TileEntity createTileEntity(@Nonnull BlockState state, @Nonnull IBlockReader world) {
        return tile.get();
    }

    @SuppressWarnings("deprecation")
    @Override
    public void tick(@Nonnull BlockState state, @Nonnull ServerWorld world, @Nonnull BlockPos pos, @Nonnull Random random) {
        TileEntity tile = world.getBlockEntity(pos);
        if (tile instanceof AbstractStumpTileEntity) {
            AbstractStumpTileEntity<?> stump = (AbstractStumpTileEntity<?>) tile;
            if (!stump.getItem(0).isEmpty() && stump.getStage() < 4) {
                stump.grow();
            }
        }
    }

    @Override
    protected void onRemoved(IItemHandler handler, World world, BlockPos pos) {
        TileEntity tile = world.getBlockEntity(pos);
        if (tile instanceof AbstractStumpTileEntity) {
            AbstractStumpTileEntity<?> stump = (AbstractStumpTileEntity<?>) tile;
            if (stump.getHarvests() != 0) return;
        }

        super.onRemoved(handler, world, pos);
    }

    @Override
    public boolean isValidBonemealTarget(@Nonnull IBlockReader world, @Nonnull BlockPos pos, @Nonnull BlockState
            state, boolean isClient) {
        TileEntity tile = world.getBlockEntity(pos);
        return tile instanceof AbstractStumpTileEntity && ((AbstractStumpTileEntity<?>) tile).getStage() < 4;
    }

    @Override
    public boolean isBonemealSuccess(@Nonnull World world, @Nonnull Random rand, @Nonnull BlockPos
            pos, @Nonnull BlockState state) {
        TileEntity tile = world.getBlockEntity(pos);
        return tile instanceof AbstractStumpTileEntity && !((AbstractStumpTileEntity<?>) tile).getItem(0).isEmpty();
    }

    @Override
    public void performBonemeal(@Nonnull ServerWorld world, @Nonnull Random rand, @Nonnull BlockPos
            pos, @Nonnull BlockState state) {
        randomTick(state, world, pos, rand); //YES!
    }
}
