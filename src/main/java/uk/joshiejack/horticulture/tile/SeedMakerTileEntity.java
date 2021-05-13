package uk.joshiejack.horticulture.tile;

import uk.joshiejack.horticulture.crafting.HorticultureRegistries;
import uk.joshiejack.horticulture.crafting.SeedMakerRecipe;
import uk.joshiejack.penguinlib.tile.machine.AbstractIRecipeMachine;

public class SeedMakerTileEntity extends AbstractIRecipeMachine<SeedMakerRecipe> {
    public SeedMakerTileEntity() {
        super(HorticultureTileEntities.SEED_MAKER.get(), HorticultureRegistries.SEED_MAKER);
    }
}