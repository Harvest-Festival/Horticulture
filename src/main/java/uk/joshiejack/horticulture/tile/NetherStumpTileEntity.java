package uk.joshiejack.horticulture.tile;

import uk.joshiejack.horticulture.crafting.HorticultureRegistries;
import uk.joshiejack.horticulture.crafting.NetherStumpRecipe;

public class NetherStumpTileEntity extends AbstractStumpTileEntity<NetherStumpRecipe> {
    public NetherStumpTileEntity() {
        super(HorticultureTileEntities.NETHER_STUMP.get(), HorticultureRegistries.NETHER_STUMP);
    }
}
