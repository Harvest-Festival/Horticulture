package uk.joshiejack.horticulture.data;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.registries.DeferredBlock;
import org.jetbrains.annotations.NotNull;
import uk.joshiejack.horticulture.Horticulture;
import uk.joshiejack.horticulture.data.builders.NetherStumpRecipeBuilder;
import uk.joshiejack.horticulture.data.builders.StumpRecipeBuilder;
import uk.joshiejack.horticulture.world.block.HorticultureBlocks;
import uk.joshiejack.horticulture.world.item.HorticultureItems;
import uk.joshiejack.horticulture.world.item.HorticultureTags;
import uk.joshiejack.horticulture.world.item.crafting.SeedMakerRecipe;
import uk.joshiejack.penguinlib.util.PenguinTags;
import uk.joshiejack.penguinlib.world.item.PenguinItems;

import javax.annotation.Nonnull;

@SuppressWarnings("ConstantConditions")
public class HorticultureRecipeProvider extends RecipeProvider {
    public HorticultureRecipeProvider(PackOutput output) {
        super(output);
    }

    private void addSeedsRecipe(@Nonnull RecipeOutput output, ItemLike... providers) {
        for (ItemLike item : providers) {
            ResourceLocation resource = BuiltInRegistries.ITEM.getKey(item.asItem());
            SeedMakerRecipe.seedmaker(Ingredient.of(item),
                            new ItemStack(BuiltInRegistries.ITEM.get(new ResourceLocation(resource.getNamespace(), resource.getPath() + "_seeds"))))
                    .save(output, Horticulture.prefix(resource.getPath() + "_seeds"));
        }
    }

    private void addSporesRecipe(@Nonnull RecipeOutput output, ItemLike... providers) {
        for (ItemLike item : providers) {
            ResourceLocation resource = BuiltInRegistries.ITEM.getKey(item.asItem());
            SeedMakerRecipe.seedmaker(Ingredient.of(item),
                            new ItemStack(BuiltInRegistries.ITEM.get(new ResourceLocation(Horticulture.MODID, resource.getPath() + "_spores"))))
                    .save(output, Horticulture.prefix(resource.getPath() + "_spores"));
        }
    }

    @Override
    protected void buildRecipes(@NotNull RecipeOutput consumer) {
        addSeedsRecipe(consumer, HorticultureItems.TURNIP, HorticultureItems.CUCUMBER, HorticultureItems.STRAWBERRY,
                HorticultureItems.CABBAGE, HorticultureItems.ONION, HorticultureItems.TOMATO,
                HorticultureItems.CORN, HorticultureItems.PINEAPPLE, HorticultureItems.EGGPLANT, HorticultureItems.SPINACH
                , HorticultureItems.SWEET_POTATO, HorticultureItems.GREEN_PEPPER, Items.WHEAT, Items.BEETROOT);
        addSporesRecipe(consumer, Items.BROWN_MUSHROOM, Items.RED_MUSHROOM, Items.CRIMSON_FUNGUS, Items.WARPED_FUNGUS);
        StumpRecipeBuilder.stump(Ingredient.of(HorticultureItems.BROWN_MUSHROOM_SPORES.get()), Items.BROWN_MUSHROOM, 50).save(consumer, Horticulture.prefix("brown_mushroom"));
        StumpRecipeBuilder.stump(Ingredient.of(HorticultureItems.RED_MUSHROOM_SPORES.get()), Items.RED_MUSHROOM, 30).save(consumer, Horticulture.prefix("red_mushroom"));
        NetherStumpRecipeBuilder.stump(Ingredient.of(HorticultureItems.WARPED_FUNGUS_SPORES.get()), Items.WARPED_FUNGUS, 25).save(consumer, Horticulture.prefix("warped_fungus"));
        NetherStumpRecipeBuilder.stump(Ingredient.of(HorticultureItems.CRIMSON_FUNGUS_SPORES.get()), Items.CRIMSON_FUNGUS, 15).save(consumer, Horticulture.prefix("crimson_fungus"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, HorticultureItems.PINEAPPLE_JUICE.get(), 1).requires(HorticultureTags.PINEAPPLE).requires(PenguinItems.GLASS.get()).unlockedBy("has_pineapple", has(HorticultureTags.PINEAPPLE)).save(consumer, Horticulture.prefix("pineapple_juice"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, HorticultureItems.TOMATO_JUICE.get(), 1).requires(HorticultureTags.TOMATO).requires(PenguinItems.GLASS.get()).unlockedBy("has_tomato", has(HorticultureTags.TOMATO)).save(consumer, Horticulture.prefix("tomato_juice"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, HorticultureItems.STRAWBERRY_MILK.get(), 1).requires(HorticultureTags.STRAWBERRY).requires(PenguinItems.GLASS.get()).requires(Items.MILK_BUCKET).unlockedBy("has_strawberry", has(HorticultureTags.STRAWBERRY)).save(consumer, Horticulture.prefix("strawberry_milk"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, HorticultureItems.PEACH_JUICE.get(), 1).requires(HorticultureTags.PEACH).requires(PenguinItems.GLASS.get()).unlockedBy("has_peach", has(HorticultureTags.PEACH)).save(consumer, Horticulture.prefix("peach_juice"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, HorticultureItems.BANANA_JUICE.get(), 1).requires(HorticultureTags.BANANA).requires(PenguinItems.GLASS.get()).unlockedBy("has_banana", has(HorticultureTags.BANANA)).save(consumer, Horticulture.prefix("banana_juice"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, HorticultureItems.ORANGE_JUICE.get(), 1).requires(HorticultureTags.ORANGE).requires(PenguinItems.GLASS.get()).unlockedBy("has_orange", has(HorticultureTags.ORANGE)).save(consumer, Horticulture.prefix("orange_juice"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, HorticultureItems.APPLE_JUICE.get(), 1).requires(PenguinTags.CROPS_APPLE).requires(PenguinItems.GLASS.get()).unlockedBy("has_apple", has(PenguinTags.CROPS_APPLE)).save(consumer, Horticulture.prefix("apple_juice"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, HorticultureItems.PICKLED_CUCUMBER.get(), 1).requires(HorticultureTags.CUCUMBER).requires(PenguinItems.PICKLING_JAR.get()).unlockedBy("has_cucumber", has(HorticultureTags.CUCUMBER)).save(consumer, Horticulture.prefix("pickled_cucumber"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, HorticultureItems.CORNFLAKES.get(), 1).requires(HorticultureTags.CORN).requires(PenguinItems.DEEP_BOWL.get()).requires(Items.MILK_BUCKET).unlockedBy("has_corn", has(HorticultureTags.CORN)).save(consumer, Horticulture.prefix("cornflakes"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, HorticultureItems.CANDIED_POTATO.get(), 1).requires(HorticultureTags.SWEET_POTATO).requires(PenguinItems.DEEP_BOWL.get()).unlockedBy("has_sweet_potato", has(HorticultureTags.SWEET_POTATO)).save(consumer, Horticulture.prefix("candied_potato"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, HorticultureItems.BOILED_SPINACH.get(), 1).requires(HorticultureTags.SPINACH).requires(PenguinItems.DEEP_BOWL.get()).unlockedBy("has_spinach", has(HorticultureTags.SPINACH)).save(consumer, Horticulture.prefix("boiled_spinach"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, HorticultureItems.STIR_FRY.get(), 1).requires(HorticultureTags.CABBAGE).requires(PenguinItems.PLATE.get()).unlockedBy("has_cabbage", has(HorticultureTags.CABBAGE)).save(consumer, Horticulture.prefix("stir_fry"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, HorticultureItems.PUMPKIN_STEW.get(), 1).requires(PenguinTags.CROPS_PUMPKIN).requires(PenguinItems.DEEP_BOWL.get()).unlockedBy("has_pumpkin", has(PenguinTags.CROPS_PUMPKIN)).save(consumer, Horticulture.prefix("pumpkin_stew"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, HorticultureItems.SALAD.get(), 1).requires(HorticultureTags.ONION).requires(HorticultureTags.GREEN_PEPPER).requires(HorticultureTags.TOMATO).requires(PenguinItems.DEEP_BOWL.get()).unlockedBy("has_green_pepper", has(HorticultureTags.GREEN_PEPPER)).save(consumer, Horticulture.prefix("salad"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, HorticultureItems.BAKED_CORN.get(), 1).requires(HorticultureTags.CORN).requires(PenguinItems.PLATE.get()).unlockedBy("has_corn", has(PenguinTags.CROPS_PUMPKIN)).save(consumer, Horticulture.prefix("baked_corn"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, HorticultureItems.HAPPY_EGGPLANT.get(), 1).requires(HorticultureTags.EGGPLANT).requires(PenguinItems.PLATE.get()).unlockedBy("has_eggplant", has(HorticultureTags.EGGPLANT)).save(consumer, Horticulture.prefix("happy_eggplant"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, HorticultureItems.PICKLED_TURNIP.get(), 1).requires(HorticultureTags.TURNIP).requires(PenguinItems.PLATE.get()).unlockedBy("has_turnip", has(HorticultureTags.TURNIP)).save(consumer, Horticulture.prefix("pickled_turnip"));
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, HorticultureItems.WATERING_CAN.get()).define('S', Tags.Items.STONE).define('B', Items.BUCKET).pattern("S  ").pattern("SBS").pattern(" S ").unlockedBy("has_stone", has(Tags.Items.STONE)).save(consumer, Horticulture.prefix("watering_can"));
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, HorticultureBlocks.SEED_MAKER.get()).define('W', ItemTags.PLANKS).define('R', Tags.Items.DUSTS_REDSTONE).define('F', Blocks.COMPOSTER).define('C', Tags.Items.CROPS).pattern("WCW").pattern("WRW").pattern("WFW").unlockedBy("has_redstone", has(Tags.Items.DUSTS_REDSTONE)).save(consumer, Horticulture.prefix("seed_maker"));
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, HorticultureBlocks.OLD_SPRINKLER.get()).define('L', ItemTags.PLANKS).define('B', Items.BONE_MEAL).pattern(" L ").pattern("LBL").pattern(" L ").unlockedBy("has_planks", has(ItemTags.PLANKS)).save(consumer, Horticulture.prefix("old_sprinkler"));
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, HorticultureBlocks.IRON_SPRINKLER.get()).define('L', Tags.Items.INGOTS_IRON).define('B', Items.BONE_MEAL).pattern(" L ").pattern("LBL").pattern(" L ").unlockedBy("has_iron", has(Tags.Items.INGOTS_IRON)).save(consumer, Horticulture.prefix("iron_sprinkler"));
        stumpRecipe(HorticultureBlocks.OAK_STUMP, Blocks.OAK_LOG, Tags.Items.MUSHROOMS, consumer);
        stumpRecipe(HorticultureBlocks.SPRUCE_STUMP, Blocks.SPRUCE_LOG, Tags.Items.MUSHROOMS, consumer);
        stumpRecipe(HorticultureBlocks.BIRCH_STUMP, Blocks.BIRCH_LOG, Tags.Items.MUSHROOMS, consumer);
        stumpRecipe(HorticultureBlocks.JUNGLE_STUMP, Blocks.JUNGLE_LOG, Tags.Items.MUSHROOMS, consumer);
        stumpRecipe(HorticultureBlocks.ACACIA_STUMP, Blocks.ACACIA_LOG, Tags.Items.MUSHROOMS, consumer);
        stumpRecipe(HorticultureBlocks.DARK_OAK_STUMP, Blocks.DARK_OAK_LOG, Tags.Items.MUSHROOMS, consumer);
        stumpRecipe(HorticultureBlocks.CRIMSON_STUMP, Blocks.CRIMSON_STEM, PenguinTags.FUNGI, consumer);
        stumpRecipe(HorticultureBlocks.WARPED_STUMP, Blocks.WARPED_STEM, PenguinTags.FUNGI, consumer);
    }

    private void stumpRecipe(DeferredBlock<Block> result, Block logs, TagKey<Item> mushrooms, @NotNull RecipeOutput consumer) {
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, result.get()).define('M', logs).define('L', mushrooms).group("stumps").pattern("L").pattern("M").unlockedBy("has_shrooms", has(mushrooms)).save(consumer, result.getId());
    }
}
