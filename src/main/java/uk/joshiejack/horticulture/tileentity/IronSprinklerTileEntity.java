package uk.joshiejack.horticulture.tileentity;

@SuppressWarnings("ConstantConditions")
public class IronSprinklerTileEntity extends AbstractSprinklerTileEntity {
    public IronSprinklerTileEntity() {
        super(HorticultureTileEntities.IRON_SPRINKLER.get(), 0.7D, 4);
    }

    @Override
    protected double getRandomDouble() {
        return level.random.nextDouble() - 0.5D;
    }
}
