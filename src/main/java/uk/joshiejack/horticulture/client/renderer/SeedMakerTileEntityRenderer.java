package uk.joshiejack.horticulture.client.renderer;


import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import uk.joshiejack.horticulture.tileentity.SeedMakerTileEntity;
import uk.joshiejack.penguinlib.client.renderer.tile.AbstractMachineTileEntityRenderer;

@OnlyIn(Dist.CLIENT)
public class SeedMakerTileEntityRenderer extends AbstractMachineTileEntityRenderer<SeedMakerTileEntity> {
    public SeedMakerTileEntityRenderer(TileEntityRendererDispatcher dispatcher) {
        super(dispatcher);
    }
}
