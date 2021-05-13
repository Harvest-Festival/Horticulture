package uk.joshiejack.horticulture.data;

import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.data.LanguageProvider;
import net.minecraftforge.registries.ForgeRegistries;
import uk.joshiejack.horticulture.Horticulture;
import uk.joshiejack.horticulture.item.HorticultureItems;

public class HorticultureLanguage extends LanguageProvider {
    public HorticultureLanguage(DataGenerator gen) {
        super(gen, Horticulture.MODID, "en_us");
    }

    @Override
    protected void addTranslations() {
        add("itemGroup.horticulture.creativetab", "Horticulture");
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
        addItem(HorticultureItems.GRAPE, "Grape");
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
        addItem(HorticultureItems.GRAPE_STARTER, "Grape Starter");
        addItem(HorticultureItems.BANANA_SAPLING, "Banana Sapling");
        addItem(HorticultureItems.ORANGE_SAPLING, "Orange Sapling");
        addItem(HorticultureItems.PEACH_SAPLING, "Peach Sapling");
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
        addItem(HorticultureItems.GRAPE_JUICE, "Grape Juice");
        addItem(HorticultureItems.PEACH_JUICE, "Peach Juice");
        addItem(HorticultureItems.BANANA_JUICE, "Banana Juice");
        addItem(HorticultureItems.ORANGE_JUICE, "Orange Juice");
        addItem(HorticultureItems.APPLE_JUICE, "Apple Juice");
        addItem(HorticultureItems.BANANA_LEAVES, "Banana Tree Leaves");
        addItem(HorticultureItems.ORANGE_LEAVES, "Orange Tree Leaves");
        addItem(HorticultureItems.APPLE_LEAVES, "Apple Tree Leaves");
        addItem(HorticultureItems.PEACH_LEAVES, "Peach Tree Leaves");
        addItem(HorticultureItems.BANANA_FRUIT, "Banana Tree Fruit");
        addItem(HorticultureItems.ORANGE_FRUIT, "Orange Tree Fruit");
        addItem(HorticultureItems.APPLE_FRUIT, "Apple Tree Fruit");
        addItem(HorticultureItems.PEACH_FRUIT, "Peach Tree Fruit");
        addItem(HorticultureItems.SEED_MAKER, "Seed Maker");
        addItem(HorticultureItems.OLD_SPRINKLER, "Old Sprinkler");
        addItem(HorticultureItems.IRON_SPRINKLER, "Iron Sprinkler");
        addItem(HorticultureItems.OAK_STUMP, "Oak Stump");
        addItem(HorticultureItems.SPRUCE_STUMP, "Spruce Stump");
        addItem(HorticultureItems.BIRCH_STUMP, "Birch Stump");
        addItem(HorticultureItems.JUNGLE_STUMP, "Jungle Stump");
        addItem(HorticultureItems.ACACIA_STUMP, "Acacia Stump");
        addItem(HorticultureItems.DARK_OAK_STUMP, "Dark Oak Stump");
        addItem(HorticultureItems.CRIMSON_STUMP, "Crimson Stump");
        addItem(HorticultureItems.WARPED_STUMP, "Warped Stump");
    }

    public void addItemFromName(String name, String text) {
        addItem(()-> ForgeRegistries.ITEMS.getValue(new ResourceLocation(Horticulture.MODID, name)), text);
    }
}
