package uk.joshiejack.horticulture.world.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import uk.joshiejack.horticulture.Horticulture;
import uk.joshiejack.horticulture.world.block.HorticultureBlocks;
import uk.joshiejack.penguinlib.world.item.PenguinItem;
import uk.joshiejack.penguinlib.world.item.PenguinItems;


@SuppressWarnings("unused")
public class HorticultureItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Horticulture.MODID);
    public static final DeferredItem<Item> TURNIP = ITEMS.register("turnip", () -> new Item(new Item.Properties().food(HorticultureFoods.TURNIP)));
    public static final DeferredItem<Item> CUCUMBER = ITEMS.register("cucumber", () -> new Item(new Item.Properties().food(HorticultureFoods.CUCUMBER)));
    public static final DeferredItem<Item> STRAWBERRY = ITEMS.register("strawberry", () -> new Item(new Item.Properties().food(HorticultureFoods.STRAWBERRY)));
    public static final DeferredItem<Item> CABBAGE = ITEMS.register("cabbage", () -> new Item(new Item.Properties().food(HorticultureFoods.CABBAGE)));
    public static final DeferredItem<Item> ONION = ITEMS.register("onion", () -> new Item(new Item.Properties().food(HorticultureFoods.ONION)));
    public static final DeferredItem<Item> TOMATO = ITEMS.register("tomato", () -> new Item(new Item.Properties().food(HorticultureFoods.TOMATO)));
    public static final DeferredItem<Item> CORN = ITEMS.register("corn", () -> new Item(new Item.Properties().food(HorticultureFoods.CORN)));
    public static final DeferredItem<Item> PINEAPPLE = ITEMS.register("pineapple", () -> new Item(new Item.Properties().food(HorticultureFoods.PINEAPPLE)));
    public static final DeferredItem<Item> EGGPLANT = ITEMS.register("eggplant", () -> new Item(new Item.Properties().food(HorticultureFoods.EGGPLANT)));
    public static final DeferredItem<Item> SPINACH = ITEMS.register("spinach", () -> new Item(new Item.Properties().food(HorticultureFoods.SPINACH)));
    public static final DeferredItem<Item> SWEET_POTATO = ITEMS.register("sweet_potato", () -> new Item(new Item.Properties().food(HorticultureFoods.SWEET_POTATO)));
    public static final DeferredItem<Item> GREEN_PEPPER = ITEMS.register("green_pepper", () -> new Item(new Item.Properties().food(HorticultureFoods.GREEN_PEPPER)));
    public static final DeferredItem<Item> BANANA = ITEMS.register("banana", () -> new Item(new Item.Properties().food(HorticultureFoods.BANANA)));
    public static final DeferredItem<Item> ORANGE = ITEMS.register("orange", () -> new Item(new Item.Properties().food(HorticultureFoods.ORANGE)));
    public static final DeferredItem<Item> PEACH = ITEMS.register("peach", () -> new Item(new Item.Properties().food(HorticultureFoods.PEACH)));
    public static final DeferredItem<Item> BROWN_MUSHROOM_SPORES = ITEMS.register("brown_mushroom_spores", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> RED_MUSHROOM_SPORES = ITEMS.register("red_mushroom_spores", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> CRIMSON_FUNGUS_SPORES = ITEMS.register("crimson_fungus_spores", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> WARPED_FUNGUS_SPORES = ITEMS.register("warped_fungus_spores", () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, WateringCanItem> WATERING_CAN = ITEMS.register("watering_can", WateringCanItem::new);

    //Seeds/Saplings/Starter
    public static final DeferredItem<Item> TURNIP_SEEDS  = ITEMS.register("turnip_seeds", () -> new ItemNameBlockItem(HorticultureBlocks.TURNIPS.get(), new Item.Properties()));
    public static final DeferredItem<Item> CUCUMBER_SEEDS  = ITEMS.register("cucumber_seeds", () -> new ItemNameBlockItem(HorticultureBlocks.CUCUMBERS.get(), new Item.Properties()));
    public static final DeferredItem<Item> STRAWBERRY_SEEDS  = ITEMS.register("strawberry_seeds", () -> new ItemNameBlockItem(HorticultureBlocks.STRAWBERRIES.get(), new Item.Properties()));
    public static final DeferredItem<Item> CABBAGE_SEEDS  = ITEMS.register("cabbage_seeds", () -> new ItemNameBlockItem(HorticultureBlocks.CABBAGES.get(), new Item.Properties()));
    public static final DeferredItem<Item> ONION_SEEDS  = ITEMS.register("onion_seeds", () -> new ItemNameBlockItem(HorticultureBlocks.ONIONS.get(), new Item.Properties()));
    public static final DeferredItem<Item> TOMATO_SEEDS  = ITEMS.register("tomato_seeds", () -> new ItemNameBlockItem(HorticultureBlocks.TOMATOES.get(), new Item.Properties()));
    public static final DeferredItem<Item> CORN_SEEDS  = ITEMS.register("corn_seeds", () -> new ItemNameBlockItem(HorticultureBlocks.CORN.get(), new Item.Properties()));
    public static final DeferredItem<Item> PINEAPPLE_SEEDS  = ITEMS.register("pineapple_seeds", () -> new ItemNameBlockItem(HorticultureBlocks.PINEAPPLES.get(), new Item.Properties()));
    public static final DeferredItem<Item> EGGPLANT_SEEDS  = ITEMS.register("eggplant_seeds", () -> new ItemNameBlockItem(HorticultureBlocks.EGGPLANTS.get(), new Item.Properties()));
    public static final DeferredItem<Item> SPINACH_SEEDS  = ITEMS.register("spinach_seeds", () -> new ItemNameBlockItem(HorticultureBlocks.SPINACH.get(), new Item.Properties()));
    public static final DeferredItem<Item> SWEET_POTATO_SEEDS  = ITEMS.register("sweet_potato_seeds", () -> new ItemNameBlockItem(HorticultureBlocks.SWEET_POTATOES.get(), new Item.Properties()));
    public static final DeferredItem<Item> GREEN_PEPPER_SEEDS  = ITEMS.register("green_pepper_seeds", () -> new ItemNameBlockItem(HorticultureBlocks.GREEN_PEPPERS.get(), new Item.Properties()));

    //Food
    public static final DeferredItem<Item> CORNFLAKES = ITEMS.register("cornflakes", () -> new PenguinItem(new PenguinItem.Properties().withContainer(() -> new ItemStack(PenguinItems.DEEP_BOWL.get())).food(HorticultureFoods.CORNFLAKES)));
    public static final DeferredItem<Item> HAPPY_EGGPLANT = ITEMS.register("happy_eggplant", () -> new PenguinItem(new PenguinItem.Properties().withContainer(() -> new ItemStack(PenguinItems.PLATE.get())).food(HorticultureFoods.HAPPY_EGGPLANT)));
    public static final DeferredItem<Item> BAKED_CORN = ITEMS.register("baked_corn", () -> new PenguinItem(new PenguinItem.Properties().withContainer(() -> new ItemStack(PenguinItems.PLATE.get())).food(HorticultureFoods.BAKED_CORN)));
    public static final DeferredItem<Item> PICKLED_TURNIP = ITEMS.register("pickled_turnip", () -> new PenguinItem(new PenguinItem.Properties().withContainer(() -> new ItemStack(PenguinItems.PLATE.get())).food(HorticultureFoods.PICKLED_TURNIP)));
    public static final DeferredItem<Item> PICKLED_CUCUMBER = ITEMS.register("pickled_cucumber", () -> new PenguinItem(new PenguinItem.Properties().withContainer(() -> new ItemStack(PenguinItems.PICKLING_JAR.get())).food(HorticultureFoods.PICKLED_CUCUMBER)));
    public static final DeferredItem<Item> SALAD = ITEMS.register("salad", () -> new PenguinItem(new PenguinItem.Properties().withContainer(() -> new ItemStack(PenguinItems.DEEP_BOWL.get())).food(HorticultureFoods.SALAD)));
    public static final DeferredItem<Item> BOILED_SPINACH = ITEMS.register("boiled_spinach", () -> new PenguinItem(new PenguinItem.Properties().withContainer(() -> new ItemStack(PenguinItems.DEEP_BOWL.get())).food(HorticultureFoods.BOILED_SPINACH)));
    public static final DeferredItem<Item> CANDIED_POTATO = ITEMS.register("candied_potato", () -> new PenguinItem(new PenguinItem.Properties().withContainer(() -> new ItemStack(PenguinItems.DEEP_BOWL.get())).food(HorticultureFoods.CANDIED_POTATO)));
    public static final DeferredItem<Item> PUMPKIN_STEW = ITEMS.register("pumpkin_stew", () -> new PenguinItem(new PenguinItem.Properties().withContainer(() -> new ItemStack(PenguinItems.DEEP_BOWL.get())).food(HorticultureFoods.PUMPKIN_STEW)));
    public static final DeferredItem<Item> STIR_FRY = ITEMS.register("stir_fry", () -> new PenguinItem(new PenguinItem.Properties().withContainer(() -> new ItemStack(PenguinItems.PLATE.get())).food(HorticultureFoods.STIR_FRY)));

    //Drinks
    public static final DeferredItem<Item> PINEAPPLE_JUICE = ITEMS.register("pineapple_juice", () -> new PenguinItem(new PenguinItem.Properties().withContainer(() -> new ItemStack(PenguinItems.GLASS.get())).useAction(UseAnim.DRINK).food(HorticultureFoods.PINEAPPLE_JUICE)));
    public static final DeferredItem<Item> TOMATO_JUICE = ITEMS.register("tomato_juice", () -> new PenguinItem(new PenguinItem.Properties().withContainer(() -> new ItemStack(PenguinItems.GLASS.get())).useAction(UseAnim.DRINK).food(HorticultureFoods.TOMATO_JUICE)));
    public static final DeferredItem<Item> STRAWBERRY_MILK = ITEMS.register("strawberry_milk", () -> new PenguinItem(new PenguinItem.Properties().withContainer(() -> new ItemStack(PenguinItems.GLASS.get())).useAction(UseAnim.DRINK).food(HorticultureFoods.STRAWBERRY_MILK)));
    public static final DeferredItem<Item> PEACH_JUICE = ITEMS.register("peach_juice", () -> new PenguinItem(new PenguinItem.Properties().withContainer(() -> new ItemStack(PenguinItems.GLASS.get())).useAction(UseAnim.DRINK).food(HorticultureFoods.PEACH_JUICE)));
    public static final DeferredItem<Item> BANANA_JUICE = ITEMS.register("banana_juice", () -> new PenguinItem(new PenguinItem.Properties().withContainer(() -> new ItemStack(PenguinItems.GLASS.get())).useAction(UseAnim.DRINK).food(HorticultureFoods.BANANA_JUICE)));
    public static final DeferredItem<Item> ORANGE_JUICE = ITEMS.register("orange_juice", () -> new PenguinItem(new PenguinItem.Properties().withContainer(() -> new ItemStack(PenguinItems.GLASS.get())).useAction(UseAnim.DRINK).food(HorticultureFoods.ORANGE_JUICE)));
    public static final DeferredItem<Item> APPLE_JUICE = ITEMS.register("apple_juice", () -> new PenguinItem(new PenguinItem.Properties().withContainer(() -> new ItemStack(PenguinItems.GLASS.get())).useAction(UseAnim.DRINK).food(HorticultureFoods.APPLE_JUICE)));
}
