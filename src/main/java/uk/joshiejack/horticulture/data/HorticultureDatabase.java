package uk.joshiejack.horticulture.data;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.level.ItemLike;
import uk.joshiejack.horticulture.Horticulture;
import uk.joshiejack.horticulture.world.block.HorticultureBlocks;
import uk.joshiejack.horticulture.world.block.entity.AbstractSprinklerBlockEntity;
import uk.joshiejack.horticulture.world.block.entity.HorticultureBlockEntities;
import uk.joshiejack.horticulture.world.item.HorticultureItems;
import uk.joshiejack.penguinlib.data.TimeUnitRegistry;
import uk.joshiejack.penguinlib.data.database.CSVUtils;
import uk.joshiejack.penguinlib.data.generator.AbstractDatabaseProvider;
import uk.joshiejack.penguinlib.data.generator.builder.TradeBuilder;

import java.util.Objects;

public class HorticultureDatabase extends AbstractDatabaseProvider {
    public HorticultureDatabase(PackOutput output) {
        super(output, Horticulture.MODID);
    }

    @Override
    protected void addDatabaseEntries() {
        addTimeUnit(AbstractSprinklerBlockEntity.START, 0L);
        addTimeUnit(AbstractSprinklerBlockEntity.FREQUENCY, 100L);
        addTimeUnit(AbstractSprinklerBlockEntity.FINISH, 24000L);
        addTimeUnitForMachine(HorticultureBlockEntities.SEED_MAKER.get(), TimeUnitRegistry.Defaults.HALF_HOUR);
        new TradeBuilder(VillagerProfession.FARMER, 1, HorticultureItems.CABBAGE_SEEDS.get()).setOutputAmount(8).setInputAmount(5).build(this);
        new TradeBuilder(VillagerProfession.FARMER, 1, HorticultureItems.PINEAPPLE_SEEDS.get()).setOutputAmount(16).setInputAmount(7).build(this);
        new TradeBuilder(VillagerProfession.FARMER, 1, HorticultureItems.GREEN_PEPPER_SEEDS.get()).setOutputAmount(12).setInputAmount(3).build(this);
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
        addComposting(HorticultureItems.BROWN_MUSHROOM_SPORES, 0.3F);
        addComposting(HorticultureItems.RED_MUSHROOM_SPORES, 0.3F);
        addComposting(HorticultureItems.CRIMSON_FUNGUS_SPORES, 0.3F);
        addComposting(HorticultureItems.WARPED_FUNGUS_SPORES, 0.3F);
        addComposting(HorticultureBlocks.APPLE_LEAVES, 0.3F);
        addComposting(HorticultureBlocks.BANANA_LEAVES, 0.3F);
        addComposting(HorticultureBlocks.ORANGE_LEAVES, 0.3F);
        addComposting(HorticultureBlocks.PEACH_LEAVES, 0.3F);
        addComposting(HorticultureBlocks.APPLE_SAPLING, 0.3F);
        addComposting(HorticultureBlocks.BANANA_SAPLING, 0.3F);
        addComposting(HorticultureBlocks.ORANGE_SAPLING, 0.3F);
        addComposting(HorticultureBlocks.PEACH_SAPLING, 0.3F);
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

    private void addCropGrowth(String predicate, ItemLike block) {
        addEntry("growth_seasons", "Item/Block,Season Predicate", CSVUtils.join(Objects.requireNonNull(BuiltInRegistries.ITEM.getKey(block.asItem())).toString(), predicate));
    }

    private void addComposting(ItemLike item, float compostAmount) {
        addEntry("composter", "Item,Compost Amount", CSVUtils.join(Objects.requireNonNull(BuiltInRegistries.ITEM.getKey(item.asItem())).toString(), compostAmount));
    }
}
