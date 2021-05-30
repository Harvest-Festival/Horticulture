package uk.joshiejack.horticulture.data;

import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraftforge.fml.RegistryObject;
import uk.joshiejack.horticulture.Horticulture;
import uk.joshiejack.horticulture.block.HorticultureBlocks;
import uk.joshiejack.horticulture.item.HorticultureItems;
import uk.joshiejack.horticulture.tileentity.AbstractSprinklerTileEntity;
import uk.joshiejack.horticulture.tileentity.HorticultureTileEntities;
import uk.joshiejack.penguinlib.data.TimeUnitRegistry;
import uk.joshiejack.penguinlib.data.database.CSVUtils;
import uk.joshiejack.penguinlib.data.generators.AbstractDatabaseProvider;
import uk.joshiejack.penguinlib.data.generators.builders.Trade;

import java.util.Objects;

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
        addCropGrowth("spring", HorticultureBlocks.TURNIPS);
        addCropGrowth("spring", HorticultureBlocks.CUCUMBERS);
        addCropGrowth("spring", HorticultureBlocks.STRAWBERRIES);
        addCropGrowth("spring", HorticultureBlocks.CABBAGES);
        addCropGrowth("summer", HorticultureBlocks.ONIONS);
        addCropGrowth("summer", HorticultureBlocks.TOMATOES);
        addCropGrowth("summer", HorticultureBlocks.CORN);
        addCropGrowth("summer", HorticultureBlocks.PINEAPPLES);
        addCropGrowth("autumn", HorticultureBlocks.EGGPLANTS);
        addCropGrowth("autumn", HorticultureBlocks.SPINACH);
        addCropGrowth("autumn", HorticultureBlocks.SWEET_POTATOES);
        addCropGrowth("autumn", HorticultureBlocks.GREEN_PEPPERS);
        addCropGrowth("not_winter", HorticultureBlocks.APPLE_SAPLING);
        addCropGrowth("not_winter", HorticultureBlocks.BANANA_SAPLING);
        addCropGrowth("not_winter", HorticultureBlocks.ORANGE_SAPLING);
        addCropGrowth("not_winter", HorticultureBlocks.PEACH_SAPLING);
        addCropGrowth("autumn", HorticultureBlocks.APPLE_FRUIT);
        addCropGrowth("summer", HorticultureBlocks.BANANA_FRUIT);
        addCropGrowth("summer", HorticultureBlocks.ORANGE_FRUIT);
        addCropGrowth("summer", HorticultureBlocks.PEACH_FRUIT);
        addCropGrowth("autumn", HorticultureBlocks.APPLE_LEAVES);
        addCropGrowth("summer", HorticultureBlocks.BANANA_LEAVES);
        addCropGrowth("summer", HorticultureBlocks.ORANGE_LEAVES);
        addCropGrowth("summer", HorticultureBlocks.PEACH_LEAVES);
    }

    private void addCropGrowth(String predicate, RegistryObject<? extends Block> block) {
        addEntry("growth_seasons", "Block,Season Predicate", CSVUtils.join(Objects.requireNonNull(block.get().getRegistryName()).toString(), predicate));
    }
}
