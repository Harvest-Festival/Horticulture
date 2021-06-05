package uk.joshiejack.horticulture.data;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.data.*;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import uk.joshiejack.horticulture.Horticulture;
import uk.joshiejack.horticulture.block.HorticultureBlocks;
import uk.joshiejack.horticulture.crafting.NetherStumpRecipe;
import uk.joshiejack.horticulture.crafting.SeedMakerRecipe;
import uk.joshiejack.horticulture.data.builders.StumpRecipeBuilder;
import uk.joshiejack.horticulture.item.HorticultureItems;
import uk.joshiejack.horticulture.item.HorticultureTags;
import uk.joshiejack.penguinlib.item.PenguinItems;
import uk.joshiejack.penguinlib.util.PenguinTags;

import javax.annotation.Nonnull;
import java.util.function.Consumer;

@SuppressWarnings("ConstantConditions")
public class HorticultureRecipes extends RecipeProvider {
    public HorticultureRecipes(DataGenerator generator) {
        super(generator);
    }

    private ResourceLocation rl(String name) {
        return new ResourceLocation(Horticulture.MODID, name);
    }

    private void addSeedsRecipe(@Nonnull Consumer<IFinishedRecipe> consumer, Item... providers) {
        for (Item item : providers)
            SeedMakerRecipe.seedmaker(Ingredient.of(item),
                    ForgeRegistries.ITEMS.getValue(new ResourceLocation(item.getRegistryName().getNamespace(), item.getRegistryName().getPath() + "_seeds")), 1)
                    .unlocks("has_" + item.getRegistryName().getPath(), has(item))
                    .save(consumer, rl(item.getRegistryName().getPath() + "_seeds"));
    }

    private void addSporesRecipe(@Nonnull Consumer<IFinishedRecipe> consumer, Item... providers) {
        for (Item item : providers)
            SeedMakerRecipe.seedmaker(Ingredient.of(item),
                    ForgeRegistries.ITEMS.getValue(new ResourceLocation(Horticulture.MODID, item.getRegistryName().getPath() + "_spores")), 1)
                    .unlocks("has_" + item.getRegistryName().getPath(), has(item))
                    .save(consumer, rl(item.getRegistryName().getPath() + "_spores"));
    }

    @Override
    protected void buildShapelessRecipes(@Nonnull Consumer<IFinishedRecipe> consumer) {
        addSeedsRecipe(consumer, HorticultureItems.TURNIP.get(), HorticultureItems.CUCUMBER.get(), HorticultureItems.STRAWBERRY.get(),
                HorticultureItems.CABBAGE.get(), HorticultureItems.ONION.get(), HorticultureItems.TOMATO.get(),
                HorticultureItems.CORN.get(), HorticultureItems.PINEAPPLE.get(), HorticultureItems.EGGPLANT.get(), HorticultureItems.SPINACH.get()
                , HorticultureItems.SWEET_POTATO.get(), HorticultureItems.GREEN_PEPPER.get(), Items.WHEAT, Items.BEETROOT);
        addSporesRecipe(consumer, Items.BROWN_MUSHROOM, Items.RED_MUSHROOM, Items.CRIMSON_FUNGUS, Items.WARPED_FUNGUS);
        StumpRecipeBuilder.stump(Ingredient.of(HorticultureItems.BROWN_MUSHROOM_SPORES.get()), Items.BROWN_MUSHROOM, 50).unlocks("has_stump", has(HorticultureItemTags.STUMPS)).save(consumer, rl("brown_mushroom"));
        StumpRecipeBuilder.stump(Ingredient.of(HorticultureItems.RED_MUSHROOM_SPORES.get()), Items.RED_MUSHROOM, 30).unlocks("has_stump", has(HorticultureItemTags.STUMPS)).save(consumer, rl("red_mushroom"));
        NetherStumpRecipe.stump(Ingredient.of(HorticultureItems.WARPED_FUNGUS_SPORES.get()), Items.WARPED_FUNGUS, 25).unlocks("has_nether_stump", has(HorticultureItemTags.NETHER_STUMP)).save(consumer, rl("warped_fungus"));
        NetherStumpRecipe.stump(Ingredient.of(HorticultureItems.CRIMSON_FUNGUS_SPORES.get()), Items.CRIMSON_FUNGUS, 15).unlocks("has_nether_stump", has(HorticultureItemTags.NETHER_STUMP)).save(consumer, rl("crimson_fungus"));
        ShapelessRecipeBuilder.shapeless(HorticultureItems.PINEAPPLE_JUICE.get(), 1).requires(HorticultureTags.PINEAPPLE).requires(PenguinItems.GLASS.get()).unlockedBy("has_pineapple", has(HorticultureTags.PINEAPPLE)).save(consumer, rl("pineapple_juice"));
        ShapelessRecipeBuilder.shapeless(HorticultureItems.TOMATO_JUICE.get(), 1).requires(HorticultureTags.TOMATO).requires(PenguinItems.GLASS.get()).unlockedBy("has_tomato", has(HorticultureTags.TOMATO)).save(consumer, rl("tomato_juice"));
        ShapelessRecipeBuilder.shapeless(HorticultureItems.STRAWBERRY_MILK.get(), 1).requires(HorticultureTags.STRAWBERRY).requires(PenguinItems.GLASS.get()).requires(Items.MILK_BUCKET).unlockedBy("has_strawberry", has(HorticultureTags.STRAWBERRY)).save(consumer, rl("strawberry_milk"));
        ShapelessRecipeBuilder.shapeless(HorticultureItems.PEACH_JUICE.get(), 1).requires(HorticultureTags.PEACH).requires(PenguinItems.GLASS.get()).unlockedBy("has_peach", has(HorticultureTags.PEACH)).save(consumer, rl("peach_juice"));
        ShapelessRecipeBuilder.shapeless(HorticultureItems.BANANA_JUICE.get(), 1).requires(HorticultureTags.BANANA).requires(PenguinItems.GLASS.get()).unlockedBy("has_banana", has(HorticultureTags.BANANA)).save(consumer, rl("banana_juice"));
        ShapelessRecipeBuilder.shapeless(HorticultureItems.ORANGE_JUICE.get(), 1).requires(HorticultureTags.ORANGE).requires(PenguinItems.GLASS.get()).unlockedBy("has_orange", has(HorticultureTags.ORANGE)).save(consumer, rl("orange_juice"));
        ShapelessRecipeBuilder.shapeless(HorticultureItems.APPLE_JUICE.get(), 1).requires(PenguinTags.CROPS_APPLE).requires(PenguinItems.GLASS.get()).unlockedBy("has_apple", has(PenguinTags.CROPS_APPLE)).save(consumer, rl("apple_juice"));
        ShapelessRecipeBuilder.shapeless(HorticultureItems.PICKLED_CUCUMBER.get(), 1).requires(HorticultureTags.CUCUMBER).requires(PenguinItems.PICKLING_JAR.get()).unlockedBy("has_cucumber", has(HorticultureTags.CUCUMBER)).save(consumer, rl("pickled_cucumber"));
        ShapelessRecipeBuilder.shapeless(HorticultureItems.CORNFLAKES.get(), 1).requires(HorticultureTags.CORN).requires(PenguinItems.DEEP_BOWL.get()).requires(Items.MILK_BUCKET).unlockedBy("has_corn", has(HorticultureTags.CORN)).save(consumer, rl("cornflakes"));
        ShapelessRecipeBuilder.shapeless(HorticultureItems.CANDIED_POTATO.get(), 1).requires(HorticultureTags.SWEET_POTATO).requires(PenguinItems.DEEP_BOWL.get()).unlockedBy("has_sweet_potato", has(HorticultureTags.SWEET_POTATO)).save(consumer, rl("candied_potato"));
        ShapelessRecipeBuilder.shapeless(HorticultureItems.BOILED_SPINACH.get(), 1).requires(HorticultureTags.SPINACH).requires(PenguinItems.DEEP_BOWL.get()).unlockedBy("has_spinach", has(HorticultureTags.SPINACH)).save(consumer, rl("boiled_spinach"));
        ShapelessRecipeBuilder.shapeless(HorticultureItems.STIR_FRY.get(), 1).requires(HorticultureTags.CABBAGE).requires(PenguinItems.PLATE.get()).unlockedBy("has_cabbage", has(HorticultureTags.CABBAGE)).save(consumer, rl("stir_fry"));
        ShapelessRecipeBuilder.shapeless(HorticultureItems.PUMPKIN_STEW.get(), 1).requires(PenguinTags.CROPS_PUMPKIN).requires(PenguinItems.DEEP_BOWL.get()).unlockedBy("has_pumpkin", has(PenguinTags.CROPS_PUMPKIN)).save(consumer, rl("pumpkin_stew"));
        ShapelessRecipeBuilder.shapeless(HorticultureItems.SALAD.get(), 1).requires(HorticultureTags.ONION).requires(HorticultureTags.GREEN_PEPPER).requires(HorticultureTags.TOMATO).requires(PenguinItems.DEEP_BOWL.get()).unlockedBy("has_green_pepper", has(HorticultureTags.GREEN_PEPPER)).save(consumer, rl("salad"));
        ShapelessRecipeBuilder.shapeless(HorticultureItems.BAKED_CORN.get(), 1).requires(HorticultureTags.CORN).requires(PenguinItems.PLATE.get()).unlockedBy("has_corn", has(PenguinTags.CROPS_PUMPKIN)).save(consumer, rl("baked_corn"));
        ShapelessRecipeBuilder.shapeless(HorticultureItems.HAPPY_EGGPLANT.get(), 1).requires(HorticultureTags.EGGPLANT).requires(PenguinItems.PLATE.get()).unlockedBy("has_eggplant", has(HorticultureTags.EGGPLANT)).save(consumer, rl("happy_eggplant"));
        ShapelessRecipeBuilder.shapeless(HorticultureItems.PICKLED_TURNIP.get(), 1).requires(HorticultureTags.TURNIP).requires(PenguinItems.PLATE.get()).unlockedBy("has_turnip", has(HorticultureTags.TURNIP)).save(consumer, rl("pickled_turnip"));
        ShapedRecipeBuilder.shaped(HorticultureItems.WATERING_CAN.get()).define('S', Tags.Items.STONE).define('B', Items.BUCKET).pattern("S  ").pattern("SBS").pattern(" S ").unlockedBy("has_stone", has(Tags.Items.STONE)).save(consumer, rl("watering_can"));
        ShapedRecipeBuilder.shaped(HorticultureItems.SEED_MAKER.get()).define('W', ItemTags.PLANKS).define('R', Tags.Items.DUSTS_REDSTONE).define('F', Blocks.COMPOSTER).define('C', Tags.Items.CROPS).pattern("WCW").pattern("WRW").pattern("WFW").unlockedBy("has_redstone", has(Tags.Items.DUSTS_REDSTONE)).save(consumer, rl("seed_maker"));
        ShapedRecipeBuilder.shaped(HorticultureItems.OLD_SPRINKLER.get()).define('L', ItemTags.PLANKS).define('B', Items.BONE_MEAL).pattern(" L ").pattern("LBL").pattern(" L ").unlockedBy("has_planks", has(ItemTags.PLANKS)).save(consumer, rl("old_sprinkler"));
        ShapedRecipeBuilder.shaped(HorticultureItems.IRON_SPRINKLER.get()).define('L', Tags.Items.INGOTS_IRON).define('B', Items.BONE_MEAL).pattern(" L ").pattern("LBL").pattern(" L ").unlockedBy("has_iron", has(Tags.Items.INGOTS_IRON)).save(consumer, rl("iron_sprinkler"));
        stumpRecipe(HorticultureBlocks.OAK_STUMP, Blocks.OAK_LOG, Tags.Items.MUSHROOMS, consumer);
        stumpRecipe(HorticultureBlocks.SPRUCE_STUMP, Blocks.SPRUCE_LOG, Tags.Items.MUSHROOMS, consumer);
        stumpRecipe(HorticultureBlocks.BIRCH_STUMP, Blocks.BIRCH_LOG, Tags.Items.MUSHROOMS, consumer);
        stumpRecipe(HorticultureBlocks.JUNGLE_STUMP, Blocks.JUNGLE_LOG, Tags.Items.MUSHROOMS, consumer);
        stumpRecipe(HorticultureBlocks.ACACIA_STUMP, Blocks.ACACIA_LOG, Tags.Items.MUSHROOMS, consumer);
        stumpRecipe(HorticultureBlocks.DARK_OAK_STUMP, Blocks.DARK_OAK_LOG, Tags.Items.MUSHROOMS, consumer);
        stumpRecipe(HorticultureBlocks.CRIMSON_STUMP, Blocks.CRIMSON_STEM, PenguinTags.FUNGI, consumer);
        stumpRecipe(HorticultureBlocks.WARPED_STUMP, Blocks.WARPED_STEM, PenguinTags.FUNGI, consumer);
    }

    private void stumpRecipe(RegistryObject<Block> result, Block logs, ITag.INamedTag<Item> mushrooms, @Nonnull Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(result.get()).define('M', logs).define('L', mushrooms).group("stumps").pattern("L").pattern("M").unlockedBy("has_shrooms", has(mushrooms)).save(consumer, result.getId());
    }
}
