package uk.joshiejack.horticulture.data;

import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.neoforged.neoforge.registries.DeferredBlock;
import uk.joshiejack.horticulture.Horticulture;
import uk.joshiejack.horticulture.world.block.HorticultureBlocks;
import uk.joshiejack.horticulture.world.item.HorticultureItems;

public class HorticultureLanguage extends LanguageProvider {
    public HorticultureLanguage(PackOutput gen) {
        super(gen, Horticulture.MODID, "en_us");
    }

    protected void addItem(DeferredBlock<Block> block, String text) {
        addItem(block::asItem, text);
    }

    @Override
    protected void addTranslations() {
        add("itemGroup.horticulture", "Horticulture");

        addItem(HorticultureItems.TURNIP, "Turnip");
        addItem(HorticultureItems.CUCUMBER, "Cucumber");
        addItem(HorticultureItems.STRAWBERRY, "Strawberry");
        addItem(HorticultureItems.CABBAGE, "Cabbage");
        addItem(HorticultureItems.ONION, "Onion");
        addItem(HorticultureItems.TOMATO, "Tomato");
        addItem(HorticultureItems.CORN, "Corn");
        addItem(HorticultureItems.PINEAPPLE, "Pineapple");
        addItem(HorticultureItems.EGGPLANT, "Eggplant");
        addItem(HorticultureItems.SPINACH, "Spinach");
        addItem(HorticultureItems.SWEET_POTATO, "Sweet Potato");
        addItem(HorticultureItems.GREEN_PEPPER, "Green Pepper");
        addItem(HorticultureItems.BANANA, "Banana");
        addItem(HorticultureItems.ORANGE, "Orange");
        addItem(HorticultureItems.PEACH, "Peach");
        addItem(HorticultureItems.BROWN_MUSHROOM_SPORES, "Brown Mushroom Spores");
        addItem(HorticultureItems.RED_MUSHROOM_SPORES, "Red Mushroom Spores");
        addItem(HorticultureItems.CRIMSON_FUNGUS_SPORES, "Crimson Fungus Spores");
        addItem(HorticultureItems.WARPED_FUNGUS_SPORES, "Warped Fungus Spores");
        addItem(HorticultureItems.WATERING_CAN, "Watering Can");
        addItem(HorticultureItems.TURNIP_SEEDS, "Turnip Seeds");
        addItem(HorticultureItems.CUCUMBER_SEEDS, "Cucumber Seeds");
        addItem(HorticultureItems.STRAWBERRY_SEEDS, "Strawberry Seeds");
        addItem(HorticultureItems.CABBAGE_SEEDS, "Cabbage Seeds");
        addItem(HorticultureItems.ONION_SEEDS, "Onion Seeds");
        addItem(HorticultureItems.TOMATO_SEEDS, "Tomato Seeds");
        addItem(HorticultureItems.CORN_SEEDS, "Corn Seeds");
        addItem(HorticultureItems.PINEAPPLE_SEEDS, "Pineapple Seeds");
        addItem(HorticultureItems.EGGPLANT_SEEDS, "Eggplant Seeds");
        addItem(HorticultureItems.SPINACH_SEEDS, "Spinach Seeds");
        addItem(HorticultureItems.SWEET_POTATO_SEEDS, "Sweet Potato Slips");
        addItem(HorticultureItems.GREEN_PEPPER_SEEDS, "Green Pepper Seeds");
        addItem(HorticultureBlocks.APPLE_SAPLING, "Apple Sapling");
        addItem(HorticultureBlocks.BANANA_SAPLING, "Banana Sapling");
        addItem(HorticultureBlocks.ORANGE_SAPLING, "Orange Sapling");
        addItem(HorticultureBlocks.PEACH_SAPLING, "Peach Sapling");
        addItem(HorticultureItems.CORNFLAKES, "Cornflakes");
        addItem(HorticultureItems.HAPPY_EGGPLANT, "Happy Eggplant");
        addItem(HorticultureItems.BAKED_CORN, "Baked Corn");
        addItem(HorticultureItems.PICKLED_TURNIP, "Pickled Turnip");
        addItem(HorticultureItems.PICKLED_CUCUMBER, "Pickled Cucumber");
        addItem(HorticultureItems.SALAD, "Salad");
        addItem(HorticultureItems.BOILED_SPINACH, "Boiled Spinach");
        addItem(HorticultureItems.CANDIED_POTATO, "Candied Potato");
        addItem(HorticultureItems.PUMPKIN_STEW, "Pumpkin Stew");
        addItem(HorticultureItems.STIR_FRY, "Stir Fry");
        addItem(HorticultureItems.PINEAPPLE_JUICE, "Pineapple Juice");
        addItem(HorticultureItems.TOMATO_JUICE, "Tomato Juice");
        addItem(HorticultureItems.STRAWBERRY_MILK, "Strawberry Milk");
        addItem(HorticultureItems.PEACH_JUICE, "Peach Juice");
        addItem(HorticultureItems.BANANA_JUICE, "Banana Juice");
        addItem(HorticultureItems.ORANGE_JUICE, "Orange Juice");
        addItem(HorticultureItems.APPLE_JUICE, "Apple Juice");
        addItem(HorticultureBlocks.BANANA_LEAVES, "Banana Tree Leaves");
        addItem(HorticultureBlocks.ORANGE_LEAVES, "Orange Tree Leaves");
        addItem(HorticultureBlocks.APPLE_LEAVES, "Apple Tree Leaves");
        addItem(HorticultureBlocks.PEACH_LEAVES, "Peach Tree Leaves");
        addItem(HorticultureBlocks.BANANA_FRUIT, "Banana Tree Fruit");
        addItem(HorticultureBlocks.ORANGE_FRUIT, "Orange Tree Fruit");
        addItem(HorticultureBlocks.APPLE_FRUIT, "Apple Tree Fruit");
        addItem(HorticultureBlocks.PEACH_FRUIT, "Peach Tree Fruit");
        addItem(HorticultureBlocks.SEED_MAKER, "Seed Maker");
        addItem(HorticultureBlocks.OLD_SPRINKLER, "Old Sprinkler");
        addItem(HorticultureBlocks.IRON_SPRINKLER, "Iron Sprinkler");
        addItem(HorticultureBlocks.OAK_STUMP, "Oak Mushroom Stump");
        addItem(HorticultureBlocks.SPRUCE_STUMP, "Spruce Mushroom Stump");
        addItem(HorticultureBlocks.BIRCH_STUMP, "Birch Mushroom Stump");
        addItem(HorticultureBlocks.JUNGLE_STUMP, "Jungle Mushroom Stump");
        addItem(HorticultureBlocks.ACACIA_STUMP, "Acacia Mushroom Stump");
        addItem(HorticultureBlocks.DARK_OAK_STUMP, "Dark Oak Mushroom Stump");
        addItem(HorticultureBlocks.CRIMSON_STUMP, "Crimson Fungus Stump");
        addItem(HorticultureBlocks.WARPED_STUMP, "Warped Fungus Stump");
        addItem(HorticultureBlocks.CHERRY_STUMP, "Cherry Mushroom Stump");
        addItem(HorticultureBlocks.MANGROVE_STUMP, "Mangrove Mushroom Stump");
    }
}
