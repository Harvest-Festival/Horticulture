package uk.joshiejack.horticulture.data;

import net.minecraft.data.DataGenerator;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import uk.joshiejack.horticulture.Horticulture;
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
        addCropGrowth("spring", HorticultureItems.TURNIP_SEEDS);
        addCropGrowth("spring", HorticultureItems.CUCUMBER_SEEDS);
        addCropGrowth("spring", HorticultureItems.STRAWBERRY_SEEDS);
        addCropGrowth("spring", HorticultureItems.CABBAGE_SEEDS);
        addCropGrowth("summer", HorticultureItems.ONION_SEEDS);
        addCropGrowth("summer", HorticultureItems.TOMATO_SEEDS);
        addCropGrowth("summer", HorticultureItems.CORN_SEEDS);
        addCropGrowth("summer", HorticultureItems.PINEAPPLE_SEEDS);
        addCropGrowth("autumn", HorticultureItems.EGGPLANT_SEEDS);
        addCropGrowth("autumn", HorticultureItems.SPINACH_SEEDS);
        addCropGrowth("autumn", HorticultureItems.SWEET_POTATO_SEEDS);
        addCropGrowth("autumn", HorticultureItems.GREEN_PEPPER_SEEDS);
        addCropGrowth("not_winter", HorticultureItems.APPLE_SAPLING);
        addCropGrowth("not_winter", HorticultureItems.BANANA_SAPLING);
        addCropGrowth("not_winter", HorticultureItems.ORANGE_SAPLING);
        addCropGrowth("not_winter", HorticultureItems.PEACH_SAPLING);
        addCropGrowth("autumn", HorticultureItems.APPLE_FRUIT);
        addCropGrowth("summer", HorticultureItems.BANANA_FRUIT);
        addCropGrowth("summer", HorticultureItems.ORANGE_FRUIT);
        addCropGrowth("summer", HorticultureItems.PEACH_FRUIT);
        addCropGrowth("autumn", HorticultureItems.APPLE_LEAVES);
        addCropGrowth("summer", HorticultureItems.BANANA_LEAVES);
        addCropGrowth("summer", HorticultureItems.ORANGE_LEAVES);
        addCropGrowth("summer", HorticultureItems.PEACH_LEAVES);
        addComposting(HorticultureItems.BROWN_MUSHROOM_SPORES, 0.3F);
        addComposting(HorticultureItems.RED_MUSHROOM_SPORES, 0.3F);
        addComposting(HorticultureItems.CRIMSON_FUNGUS_SPORES, 0.3F);
        addComposting(HorticultureItems.WARPED_FUNGUS_SPORES, 0.3F);
        addComposting(HorticultureItems.APPLE_LEAVES, 0.3F);
        addComposting(HorticultureItems.BANANA_LEAVES, 0.3F);
        addComposting(HorticultureItems.ORANGE_LEAVES, 0.3F);
        addComposting(HorticultureItems.PEACH_LEAVES, 0.3F);
        addComposting(HorticultureItems.APPLE_SAPLING, 0.3F);
        addComposting(HorticultureItems.BANANA_SAPLING, 0.3F);
        addComposting(HorticultureItems.ORANGE_SAPLING, 0.3F);
        addComposting(HorticultureItems.PEACH_SAPLING, 0.3F);
        addComposting(HorticultureItems.TURNIP_SEEDS, 0.3F);
        addComposting(HorticultureItems.CUCUMBER_SEEDS, 0.3F);
        addComposting(HorticultureItems.STRAWBERRY_SEEDS, 0.3F);
        addComposting(HorticultureItems.CABBAGE_SEEDS, 0.3F);
        addComposting(HorticultureItems.ONION_SEEDS, 0.3F);
        addComposting(HorticultureItems.TOMATO_SEEDS, 0.3F);
        addComposting(HorticultureItems.CORN_SEEDS, 0.3F);
        addComposting(HorticultureItems.PINEAPPLE_SEEDS, 0.3F);
        addComposting(HorticultureItems.EGGPLANT_SEEDS, 0.3F);
        addComposting(HorticultureItems.SPINACH_SEEDS, 0.3F);
        addComposting(HorticultureItems.SWEET_POTATO_SEEDS, 0.3F);
        addComposting(HorticultureItems.GREEN_PEPPER_SEEDS, 0.3F);
        addComposting(HorticultureItems.TURNIP, 0.65F);
        addComposting(HorticultureItems.CUCUMBER, 0.65F);
        addComposting(HorticultureItems.STRAWBERRY, 0.65F);
        addComposting(HorticultureItems.CABBAGE, 0.65F);
        addComposting(HorticultureItems.ONION, 0.65F);
        addComposting(HorticultureItems.TOMATO, 0.65F);
        addComposting(HorticultureItems.CORN, 0.65F);
        addComposting(HorticultureItems.PINEAPPLE, 0.65F);
        addComposting(HorticultureItems.EGGPLANT, 0.65F);
        addComposting(HorticultureItems.SPINACH, 0.65F);
        addComposting(HorticultureItems.SWEET_POTATO, 0.65F);
        addComposting(HorticultureItems.GREEN_PEPPER, 0.65F);
    }

    private void addCropGrowth(String predicate, RegistryObject<? extends Item> block) {
        addEntry("growth_seasons", "Item/Block,Season Predicate", CSVUtils.join(Objects.requireNonNull(block.get().getRegistryName()).toString(), predicate));
    }

    private void addComposting(RegistryObject<? extends Item> item, float compostAmount) {
        addEntry("composter", "Item,Compost Amount", CSVUtils.join(Objects.requireNonNull(item.get().getRegistryName()).toString(), compostAmount));
    }
}
