package uk.joshiejack.horticulture.data;

import net.minecraft.data.DataGenerator;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import uk.joshiejack.horticulture.Horticulture;
import uk.joshiejack.horticulture.item.HorticultureItems;
import uk.joshiejack.horticulture.tile.AbstractSprinklerTileEntity;
import uk.joshiejack.horticulture.tile.HorticultureTileEntities;
import uk.joshiejack.penguinlib.data.TimeUnitRegistry;
import uk.joshiejack.penguinlib.data.generators.AbstractDatabaseProvider;
import uk.joshiejack.penguinlib.data.generators.builders.Trade;

public class HorticultureDatabase extends AbstractDatabaseProvider {
    public HorticultureDatabase(DataGenerator gen) {
        super(gen, Horticulture.MODID);
    }

    @Override
    protected void addDatabaseEntries() {
        addTimeUnit(AbstractSprinklerTileEntity.START, 0L);
        addTimeUnit(AbstractSprinklerTileEntity.FREQUENCY, 100L);
        addTimeUnit(AbstractSprinklerTileEntity.FINISH, 24000L);
        addTimeUnitForMachine(HorticultureTileEntities.SEED_MAKER.get(), TimeUnitRegistry.Defaults.HALF_HOUR);
        new Trade(VillagerProfession.FARMER, 1, HorticultureItems.CABBAGE_SEEDS.get()).setOutputAmount(8).setInputAmount(5).build(this);
        new Trade(VillagerProfession.FARMER, 1, HorticultureItems.PINEAPPLE_SEEDS.get()).setOutputAmount(16).setInputAmount(7).build(this);
        new Trade(VillagerProfession.FARMER, 1, HorticultureItems.GREEN_PEPPER_SEEDS.get()).setOutputAmount(12).setInputAmount(3).build(this);
        new Trade(VillagerProfession.FARMER, 2, HorticultureItems.GRAPE_STARTER.get()).setOutputAmount(3).setInputAmount(4).build(this);
    }
}
