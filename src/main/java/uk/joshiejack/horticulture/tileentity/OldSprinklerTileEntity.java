package uk.joshiejack.horticulture.tileentity;

@SuppressWarnings("ConstantConditions")
public class OldSprinklerTileEntity extends AbstractSprinklerTileEntity {
    public OldSprinklerTileEntity() {
        super(HorticultureTileEntities.OLD_SPRINKLER.get(), 0.5D, 1);
    }

    @Override
    protected double getRandomDouble() {
        return (level.random.nextDouble() - 0.5D) / 3;
    }
}
