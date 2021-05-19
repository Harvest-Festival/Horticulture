package uk.joshiejack.horticulture.item;

import net.minecraft.item.*;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import uk.joshiejack.horticulture.Horticulture;
import uk.joshiejack.horticulture.block.HorticultureBlocks;
import uk.joshiejack.penguinlib.item.PenguinItems;
import uk.joshiejack.penguinlib.item.base.PenguinItem;


@SuppressWarnings("unused")
public class HorticultureItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Horticulture.MODID);
    public static final RegistryObject<Item> TURNIP = ITEMS.register("turnip", () -> new Item(new Item.Properties().food(HorticultureFoods.TURNIP).tab(Horticulture.TAB)));
    public static final RegistryObject<Item> CUCUMBER = ITEMS.register("cucumber", () -> new Item(new Item.Properties().food(HorticultureFoods.CUCUMBER).tab(Horticulture.TAB)));
    public static final RegistryObject<Item> STRAWBERRY = ITEMS.register("strawberry", () -> new Item(new Item.Properties().food(HorticultureFoods.STRAWBERRY).tab(Horticulture.TAB)));
    public static final RegistryObject<Item> CABBAGE = ITEMS.register("cabbage", () -> new Item(new Item.Properties().food(HorticultureFoods.CABBAGE).tab(Horticulture.TAB)));
    public static final RegistryObject<Item> ONION = ITEMS.register("onion", () -> new Item(new Item.Properties().food(HorticultureFoods.ONION).tab(Horticulture.TAB)));
    public static final RegistryObject<Item> TOMATO = ITEMS.register("tomato", () -> new Item(new Item.Properties().food(HorticultureFoods.TOMATO).tab(Horticulture.TAB)));
    public static final RegistryObject<Item> CORN = ITEMS.register("corn", () -> new Item(new Item.Properties().food(HorticultureFoods.CORN).tab(Horticulture.TAB)));
    public static final RegistryObject<Item> PINEAPPLE = ITEMS.register("pineapple", () -> new Item(new Item.Properties().food(HorticultureFoods.PINEAPPLE).tab(Horticulture.TAB)));
    public static final RegistryObject<Item> EGGPLANT = ITEMS.register("eggplant", () -> new Item(new Item.Properties().food(HorticultureFoods.EGGPLANT).tab(Horticulture.TAB)));
    public static final RegistryObject<Item> SPINACH = ITEMS.register("spinach", () -> new Item(new Item.Properties().food(HorticultureFoods.SPINACH).tab(Horticulture.TAB)));
    public static final RegistryObject<Item> SWEET_POTATO = ITEMS.register("sweet_potato", () -> new Item(new Item.Properties().food(HorticultureFoods.SWEET_POTATO).tab(Horticulture.TAB)));
    public static final RegistryObject<Item> GREEN_PEPPER = ITEMS.register("green_pepper", () -> new Item(new Item.Properties().food(HorticultureFoods.GREEN_PEPPER).tab(Horticulture.TAB)));
    public static final RegistryObject<Item> BANANA = ITEMS.register("banana", () -> new Item(new Item.Properties().food(HorticultureFoods.BANANA).tab(Horticulture.TAB)));
    public static final RegistryObject<Item> ORANGE = ITEMS.register("orange", () -> new Item(new Item.Properties().food(HorticultureFoods.ORANGE).tab(Horticulture.TAB)));
    public static final RegistryObject<Item> PEACH = ITEMS.register("peach", () -> new Item(new Item.Properties().food(HorticultureFoods.PEACH).tab(Horticulture.TAB)));
    public static final RegistryObject<Item> BROWN_MUSHROOM_SPORES = ITEMS.register("brown_mushroom_spores", () -> new Item(new Item.Properties().tab(Horticulture.TAB)));
    public static final RegistryObject<Item> RED_MUSHROOM_SPORES = ITEMS.register("red_mushroom_spores", () -> new Item(new Item.Properties().tab(Horticulture.TAB)));
    public static final RegistryObject<Item> CRIMSON_FUNGUS_SPORES = ITEMS.register("crimson_fungus_spores", () -> new Item(new Item.Properties().tab(Horticulture.TAB)));
    public static final RegistryObject<Item> WARPED_FUNGUS_SPORES = ITEMS.register("warped_fungus_spores", () -> new Item(new Item.Properties().tab(Horticulture.TAB)));
    public static final RegistryObject<WateringCanItem> WATERING_CAN = ITEMS.register("watering_can", WateringCanItem::new);

    //Seeds/Saplings/Starter
    public static final RegistryObject<Item> TURNIP_SEEDS  = ITEMS.register("turnip_seeds", () -> new BlockNamedItem(HorticultureBlocks.TURNIPS.get(), new Item.Properties().tab(Horticulture.TAB)));
    public static final RegistryObject<Item> CUCUMBER_SEEDS  = ITEMS.register("cucumber_seeds", () -> new BlockNamedItem(HorticultureBlocks.CUCUMBERS.get(), new Item.Properties().tab(Horticulture.TAB)));
    public static final RegistryObject<Item> STRAWBERRY_SEEDS  = ITEMS.register("strawberry_seeds", () -> new BlockNamedItem(HorticultureBlocks.STRAWBERRIES.get(), new Item.Properties().tab(Horticulture.TAB)));
    public static final RegistryObject<Item> CABBAGE_SEEDS  = ITEMS.register("cabbage_seeds", () -> new BlockNamedItem(HorticultureBlocks.CABBAGES.get(), new Item.Properties().tab(Horticulture.TAB)));
    public static final RegistryObject<Item> ONION_SEEDS  = ITEMS.register("onion_seeds", () -> new BlockNamedItem(HorticultureBlocks.ONIONS.get(), new Item.Properties().tab(Horticulture.TAB)));
    public static final RegistryObject<Item> TOMATO_SEEDS  = ITEMS.register("tomato_seeds", () -> new BlockNamedItem(HorticultureBlocks.TOMATOES.get(), new Item.Properties().tab(Horticulture.TAB)));
    public static final RegistryObject<Item> CORN_SEEDS  = ITEMS.register("corn_seeds", () -> new BlockNamedItem(HorticultureBlocks.CORN.get(), new Item.Properties().tab(Horticulture.TAB)));
    public static final RegistryObject<Item> PINEAPPLE_SEEDS  = ITEMS.register("pineapple_seeds", () -> new BlockNamedItem(HorticultureBlocks.PINEAPPLES.get(), new Item.Properties().tab(Horticulture.TAB)));
    public static final RegistryObject<Item> EGGPLANT_SEEDS  = ITEMS.register("eggplant_seeds", () -> new BlockNamedItem(HorticultureBlocks.EGGPLANTS.get(), new Item.Properties().tab(Horticulture.TAB)));
    public static final RegistryObject<Item> SPINACH_SEEDS  = ITEMS.register("spinach_seeds", () -> new BlockNamedItem(HorticultureBlocks.SPINACH.get(), new Item.Properties().tab(Horticulture.TAB)));
    public static final RegistryObject<Item> SWEET_POTATO_SEEDS  = ITEMS.register("sweet_potato_seeds", () -> new BlockNamedItem(HorticultureBlocks.SWEET_POTATOES.get(), new Item.Properties().tab(Horticulture.TAB)));
    public static final RegistryObject<Item> GREEN_PEPPER_SEEDS  = ITEMS.register("green_pepper_seeds", () -> new BlockNamedItem(HorticultureBlocks.GREEN_PEPPERS.get(), new Item.Properties().tab(Horticulture.TAB)));
    public static final RegistryObject<Item> APPLE_SAPLING = ITEMS.register("apple_sapling", () -> new BlockItem(HorticultureBlocks.APPLE_SAPLING.get(), new Item.Properties().tab(Horticulture.TAB)));
    public static final RegistryObject<Item> BANANA_SAPLING = ITEMS.register("banana_sapling", () -> new BlockItem(HorticultureBlocks.BANANA_SAPLING.get(), new Item.Properties().tab(Horticulture.TAB)));
    public static final RegistryObject<Item> ORANGE_SAPLING = ITEMS.register("orange_sapling", () -> new BlockItem(HorticultureBlocks.ORANGE_SAPLING.get(), new Item.Properties().tab(Horticulture.TAB)));
    public static final RegistryObject<Item> PEACH_SAPLING  = ITEMS.register("peach_sapling", () -> new BlockItem(HorticultureBlocks.PEACH_SAPLING.get(), new Item.Properties().tab(Horticulture.TAB)));

    //Food
    public static final RegistryObject<Item> CORNFLAKES = ITEMS.register("cornflakes", () -> new PenguinItem(new PenguinItem.Properties().withContainer(() -> new ItemStack(PenguinItems.DEEP_BOWL.get())).food(HorticultureFoods.CORNFLAKES).tab(Horticulture.TAB)));
    public static final RegistryObject<Item> HAPPY_EGGPLANT = ITEMS.register("happy_eggplant", () -> new PenguinItem(new PenguinItem.Properties().withContainer(() -> new ItemStack(PenguinItems.PLATE.get())).food(HorticultureFoods.HAPPY_EGGPLANT).tab(Horticulture.TAB)));
    public static final RegistryObject<Item> BAKED_CORN = ITEMS.register("baked_corn", () -> new PenguinItem(new PenguinItem.Properties().withContainer(() -> new ItemStack(PenguinItems.PLATE.get())).food(HorticultureFoods.BAKED_CORN).tab(Horticulture.TAB)));
    public static final RegistryObject<Item> PICKLED_TURNIP = ITEMS.register("pickled_turnip", () -> new PenguinItem(new PenguinItem.Properties().withContainer(() -> new ItemStack(PenguinItems.PLATE.get())).food(HorticultureFoods.PICKLED_TURNIP).tab(Horticulture.TAB)));
    public static final RegistryObject<Item> PICKLED_CUCUMBER = ITEMS.register("pickled_cucumber", () -> new PenguinItem(new PenguinItem.Properties().withContainer(() -> new ItemStack(PenguinItems.PICKLING_JAR.get())).food(HorticultureFoods.PICKLED_CUCUMBER).tab(Horticulture.TAB)));
    public static final RegistryObject<Item> SALAD = ITEMS.register("salad", () -> new PenguinItem(new PenguinItem.Properties().withContainer(() -> new ItemStack(PenguinItems.DEEP_BOWL.get())).food(HorticultureFoods.SALAD).tab(Horticulture.TAB)));
    public static final RegistryObject<Item> BOILED_SPINACH = ITEMS.register("boiled_spinach", () -> new PenguinItem(new PenguinItem.Properties().withContainer(() -> new ItemStack(PenguinItems.DEEP_BOWL.get())).food(HorticultureFoods.BOILED_SPINACH).tab(Horticulture.TAB)));
    public static final RegistryObject<Item> CANDIED_POTATO = ITEMS.register("candied_potato", () -> new PenguinItem(new PenguinItem.Properties().withContainer(() -> new ItemStack(PenguinItems.DEEP_BOWL.get())).food(HorticultureFoods.CANDIED_POTATO).tab(Horticulture.TAB)));
    public static final RegistryObject<Item> PUMPKIN_STEW = ITEMS.register("pumpkin_stew", () -> new PenguinItem(new PenguinItem.Properties().withContainer(() -> new ItemStack(PenguinItems.DEEP_BOWL.get())).food(HorticultureFoods.PUMPKIN_STEW).tab(Horticulture.TAB)));
    public static final RegistryObject<Item> STIR_FRY = ITEMS.register("stir_fry", () -> new PenguinItem(new PenguinItem.Properties().withContainer(() -> new ItemStack(PenguinItems.PLATE.get())).food(HorticultureFoods.STIR_FRY).tab(Horticulture.TAB)));

    //Drinks
    public static final RegistryObject<Item> PINEAPPLE_JUICE = ITEMS.register("pineapple_juice", () -> new PenguinItem(new PenguinItem.Properties().withContainer(() -> new ItemStack(PenguinItems.GLASS.get())).useAction(UseAction.DRINK).food(HorticultureFoods.PINEAPPLE_JUICE).tab(Horticulture.TAB)));
    public static final RegistryObject<Item> TOMATO_JUICE = ITEMS.register("tomato_juice", () -> new PenguinItem(new PenguinItem.Properties().withContainer(() -> new ItemStack(PenguinItems.GLASS.get())).useAction(UseAction.DRINK).food(HorticultureFoods.TOMATO_JUICE).tab(Horticulture.TAB)));
    public static final RegistryObject<Item> STRAWBERRY_MILK = ITEMS.register("strawberry_milk", () -> new PenguinItem(new PenguinItem.Properties().withContainer(() -> new ItemStack(PenguinItems.GLASS.get())).useAction(UseAction.DRINK).food(HorticultureFoods.STRAWBERRY_MILK).tab(Horticulture.TAB)));
    public static final RegistryObject<Item> PEACH_JUICE = ITEMS.register("peach_juice", () -> new PenguinItem(new PenguinItem.Properties().withContainer(() -> new ItemStack(PenguinItems.GLASS.get())).useAction(UseAction.DRINK).food(HorticultureFoods.PEACH_JUICE).tab(Horticulture.TAB)));
    public static final RegistryObject<Item> BANANA_JUICE = ITEMS.register("banana_juice", () -> new PenguinItem(new PenguinItem.Properties().withContainer(() -> new ItemStack(PenguinItems.GLASS.get())).useAction(UseAction.DRINK).food(HorticultureFoods.BANANA_JUICE).tab(Horticulture.TAB)));
    public static final RegistryObject<Item> ORANGE_JUICE = ITEMS.register("orange_juice", () -> new PenguinItem(new PenguinItem.Properties().withContainer(() -> new ItemStack(PenguinItems.GLASS.get())).useAction(UseAction.DRINK).food(HorticultureFoods.ORANGE_JUICE).tab(Horticulture.TAB)));
    public static final RegistryObject<Item> APPLE_JUICE = ITEMS.register("apple_juice", () -> new PenguinItem(new PenguinItem.Properties().withContainer(() -> new ItemStack(PenguinItems.GLASS.get())).useAction(UseAction.DRINK).food(HorticultureFoods.APPLE_JUICE).tab(Horticulture.TAB)));

    //Blocks
    public static final RegistryObject<Item> BANANA_LEAVES = ITEMS.register("banana_leaves", () -> new BlockItem(HorticultureBlocks.BANANA_LEAVES.get(), new PenguinItem.Properties().tab(Horticulture.TAB)));
    public static final RegistryObject<Item> ORANGE_LEAVES = ITEMS.register("orange_leaves", () -> new BlockItem(HorticultureBlocks.ORANGE_LEAVES.get(), new PenguinItem.Properties().tab(Horticulture.TAB)));
    public static final RegistryObject<Item> APPLE_LEAVES = ITEMS.register("apple_leaves", () -> new BlockItem(HorticultureBlocks.APPLE_LEAVES.get(), new PenguinItem.Properties().tab(Horticulture.TAB)));
    public static final RegistryObject<Item> PEACH_LEAVES = ITEMS.register("peach_leaves", () -> new BlockItem(HorticultureBlocks.PEACH_LEAVES.get(), new PenguinItem.Properties().tab(Horticulture.TAB)));
    public static final RegistryObject<Item> BANANA_FRUIT = ITEMS.register("banana_fruit", () -> new BlockItem(HorticultureBlocks.BANANA_FRUIT.get(), new PenguinItem.Properties().tab(Horticulture.TAB)));
    public static final RegistryObject<Item> ORANGE_FRUIT = ITEMS.register("orange_fruit", () -> new BlockItem(HorticultureBlocks.ORANGE_FRUIT.get(), new PenguinItem.Properties().tab(Horticulture.TAB)));
    public static final RegistryObject<Item> APPLE_FRUIT = ITEMS.register("apple_fruit", () -> new BlockItem(HorticultureBlocks.APPLE_FRUIT.get(), new PenguinItem.Properties().tab(Horticulture.TAB)));
    public static final RegistryObject<Item> PEACH_FRUIT = ITEMS.register("peach_fruit", () -> new BlockItem(HorticultureBlocks.PEACH_FRUIT.get(), new PenguinItem.Properties().tab(Horticulture.TAB)));
    public static final RegistryObject<Item> SEED_MAKER = ITEMS.register("seed_maker", () -> new BlockItem(HorticultureBlocks.SEED_MAKER.get(), new PenguinItem.Properties().tab(Horticulture.TAB)));
    public static final RegistryObject<Item> OLD_SPRINKLER = ITEMS.register("old_sprinkler", () -> new BlockItem(HorticultureBlocks.OLD_SPRINKLER.get(), new PenguinItem.Properties().tab(Horticulture.TAB)));
    public static final RegistryObject<Item> IRON_SPRINKLER = ITEMS.register("iron_sprinkler", () -> new BlockItem(HorticultureBlocks.IRON_SPRINKLER.get(), new PenguinItem.Properties().tab(Horticulture.TAB)));
    public static final RegistryObject<Item> OAK_STUMP = ITEMS.register("oak_stump", () -> new BlockItem(HorticultureBlocks.OAK_STUMP.get(), new PenguinItem.Properties().tab(Horticulture.TAB)));
    public static final RegistryObject<Item> SPRUCE_STUMP = ITEMS.register("spruce_stump", () -> new BlockItem(HorticultureBlocks.SPRUCE_STUMP.get(), new PenguinItem.Properties().tab(Horticulture.TAB)));
    public static final RegistryObject<Item> BIRCH_STUMP = ITEMS.register("birch_stump", () -> new BlockItem(HorticultureBlocks.BIRCH_STUMP.get(), new PenguinItem.Properties().tab(Horticulture.TAB)));
    public static final RegistryObject<Item> JUNGLE_STUMP = ITEMS.register("jungle_stump", () -> new BlockItem(HorticultureBlocks.JUNGLE_STUMP.get(), new PenguinItem.Properties().tab(Horticulture.TAB)));
    public static final RegistryObject<Item> ACACIA_STUMP = ITEMS.register("acacia_stump", () -> new BlockItem(HorticultureBlocks.ACACIA_STUMP.get(), new PenguinItem.Properties().tab(Horticulture.TAB)));
    public static final RegistryObject<Item> DARK_OAK_STUMP = ITEMS.register("dark_oak_stump", () -> new BlockItem(HorticultureBlocks.DARK_OAK_STUMP.get(), new PenguinItem.Properties().tab(Horticulture.TAB)));
    public static final RegistryObject<Item> CRIMSON_STUMP = ITEMS.register("crimson_stump", () -> new BlockItem(HorticultureBlocks.CRIMSON_STUMP.get(), new PenguinItem.Properties().tab(Horticulture.TAB)));
    public static final RegistryObject<Item> WARPED_STUMP = ITEMS.register("warped_stump", () -> new BlockItem(HorticultureBlocks.WARPED_STUMP.get(), new PenguinItem.Properties().tab(Horticulture.TAB)));
}
