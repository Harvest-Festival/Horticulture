package uk.joshiejack.horticulture.tileentity;

import net.minecraft.util.SoundCategory;
import uk.joshiejack.horticulture.Horticulture;
import uk.joshiejack.horticulture.crafting.HorticultureRegistries;
import uk.joshiejack.horticulture.crafting.SeedMakerRecipe;
import uk.joshiejack.penguinlib.tile.machine.AbstractIRecipeMachine;

public class SeedMakerTileEntity extends AbstractIRecipeMachine<SeedMakerRecipe> {
    public SeedMakerTileEntity() {
        super(HorticultureTileEntities.SEED_MAKER.get(), HorticultureRegistries.SEED_MAKER);
    }

    @Override
    public void tick() {
        super.tick();
        assert this.level != null;
        if (this.level.getGameTime() % 50L == 1L) {
            if (isActive()) {
                double d0 = (double) this.worldPosition.getX() + 0.5D;
                double d1 = (double) this.worldPosition.getY() + 0.5D;
                double d2 = (double) this.worldPosition.getZ() + 0.5D;
                level.playSound(null, d0, d1, d2, Horticulture.HorticultureSounds.SEED_MAKER.get(), SoundCategory.BLOCKS, 0.5F, this.level.random.nextFloat() * 0.1F + 0.9F);
            }
        }
    }
}