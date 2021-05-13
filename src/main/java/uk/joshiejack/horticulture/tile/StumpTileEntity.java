package uk.joshiejack.horticulture.tile;

import uk.joshiejack.horticulture.crafting.HorticultureRegistries;
import uk.joshiejack.horticulture.crafting.StumpRecipe;

public class StumpTileEntity extends AbstractStumpTileEntity<StumpRecipe> {
    public StumpTileEntity() {
        super(HorticultureTileEntities.OVERWORLD_STUMP.get(), HorticultureRegistries.STUMP);
    }
}
