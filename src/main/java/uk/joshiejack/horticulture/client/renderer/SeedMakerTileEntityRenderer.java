package uk.joshiejack.horticulture.client.renderer;


import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import uk.joshiejack.horticulture.tileentity.SeedMakerTileEntity;
import uk.joshiejack.penguinlib.client.renderer.tile.AbstractMachineTileEntityRenderer;

public class SeedMakerTileEntityRenderer extends AbstractMachineTileEntityRenderer<SeedMakerTileEntity> {
    public SeedMakerTileEntityRenderer(TileEntityRendererDispatcher dispatcher) {
        super(dispatcher);
    }

    @Override
    protected double getYOffset() {
        return 2.5D;
    }
}
