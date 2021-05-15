package uk.joshiejack.horticulture.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nonnull;

@SuppressWarnings("deprecation")
public class TrellisBlock extends CropBlock {
    private static final VoxelShape COLLISION = VoxelShapes.box(0.2D, 0.0D, 0.2D, 0.8D, 1.5D, 0.8D);
    public static final DirectionProperty FACING = DirectionProperty.create("facing", Direction.NORTH, Direction.EAST);

    public TrellisBlock(int num, int regrow) {
        super(AbstractBlock.Properties.of(Material.PLANT).randomTicks().sound(SoundType.WOOD).strength(0.6F).harvestTool(ToolType.AXE), num, regrow);
        registerDefaultState(getStateDefinition().any().setValue(FACING, Direction.NORTH).setValue(getAgeProperty(), 0));
    }

    @Nonnull
    @Override
    public VoxelShape getShape(@Nonnull BlockState state, @Nonnull IBlockReader source, @Nonnull BlockPos pos, @Nonnull ISelectionContext ctx) {
        return COLLISION;
    }


    @Nonnull
    @Override
    public VoxelShape getCollisionShape(@Nonnull BlockState state, @Nonnull IBlockReader source, @Nonnull BlockPos pos, @Nonnull ISelectionContext ctx) {
        return COLLISION;
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext ctx) {
        Direction direction = ctx.getHorizontalDirection().getOpposite();
        return this.defaultBlockState().setValue(FACING, direction == Direction.EAST || direction == Direction.WEST ? Direction.EAST : Direction.NORTH);
    }

    @Nonnull
    @Override
    public BlockState rotate(BlockState state, Rotation rotation) {
        Direction rotated = rotation.rotate(state.getValue(FACING));
        return state.setValue(FACING, rotated == Direction.EAST || rotated == Direction.WEST ? Direction.EAST : Direction.NORTH);
    }

    @Nonnull
    @Override
    public BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }

    @Override
    protected void createBlockStateDefinition(@Nonnull StateContainer.Builder<Block, BlockState> builder) {
        builder.add(getAgeProperty()).add(FACING);
    }
}
