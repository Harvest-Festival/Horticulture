package uk.joshiejack.horticulture.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import uk.joshiejack.horticulture.tileentity.OldSprinklerTileEntity;
import uk.joshiejack.penguinlib.block.base.AbstractPenguinBlock;

import javax.annotation.Nonnull;

public class OldSprinklerBlock extends AbstractPenguinBlock {
    private static final VoxelShape SHAPE = VoxelShapes.box(0.2D, 0D, 0.2D, 0.8D, 0.5D, 0.8D);

    public OldSprinklerBlock() {
        super(AbstractBlock.Properties.of(Material.METAL).sound(SoundType.METAL).noCollission().strength(0.4F));
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @SuppressWarnings("deprecation")
    @Nonnull
    @Override
    public VoxelShape getShape(@Nonnull BlockState state, @Nonnull IBlockReader source, @Nonnull BlockPos pos, @Nonnull ISelectionContext ctx) {
        return SHAPE;
    }

    @Nonnull
    @Override
    public TileEntity createTileEntity(@Nonnull BlockState state, @Nonnull IBlockReader world) {
        return new OldSprinklerTileEntity();
    }
}
