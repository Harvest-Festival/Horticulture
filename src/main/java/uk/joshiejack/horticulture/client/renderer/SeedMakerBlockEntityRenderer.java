package uk.joshiejack.horticulture.client.renderer;


import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import uk.joshiejack.horticulture.world.block.entity.SeedMakerBlockEntity;
import uk.joshiejack.penguinlib.client.renderer.block.entity.AbstractMachineBlockEntityRenderer;

@OnlyIn(Dist.CLIENT)
public class SeedMakerBlockEntityRenderer extends AbstractMachineBlockEntityRenderer<SeedMakerBlockEntity> {
    public SeedMakerBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        super(context);
    }
}
