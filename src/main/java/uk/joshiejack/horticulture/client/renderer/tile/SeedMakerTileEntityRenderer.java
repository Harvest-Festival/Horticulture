package uk.joshiejack.horticulture.client.renderer.tile;


import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import uk.joshiejack.horticulture.tile.SeedMakerTileEntity;
import uk.joshiejack.penguinlib.client.renderer.tile.AbstractMachineTileEntityRenderer;

public class SeedMakerTileEntityRenderer extends AbstractMachineTileEntityRenderer<SeedMakerTileEntity> {
    public SeedMakerTileEntityRenderer(TileEntityRendererDispatcher dispatcher) {
        super(dispatcher);
    }
}
