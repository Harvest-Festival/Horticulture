package uk.joshiejack.horticulture.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;
import uk.joshiejack.horticulture.tile.SeedMakerTileEntity;
import uk.joshiejack.penguinlib.block.base.AbstractPenguinBlock;
import uk.joshiejack.penguinlib.block.base.AbstractRotatableBlock;

import javax.annotation.Nonnull;

public class SeedMakerBlock extends AbstractRotatableBlock {
    public SeedMakerBlock() {
        super(AbstractPenguinBlock.Properties.of(Material.METAL, MaterialColor.STONE).requiresCorrectToolForDrops().strength(2.0F).noOcclusion());
        hasInventory = true;
    }

    @Nonnull
    @Override
    public TileEntity createTileEntity(@Nonnull BlockState state, @Nonnull IBlockReader world) {
        return new SeedMakerTileEntity();
    }
}
