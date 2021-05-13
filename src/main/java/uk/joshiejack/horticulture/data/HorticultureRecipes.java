package uk.joshiejack.horticulture.data;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;
import uk.joshiejack.horticulture.Horticulture;
import uk.joshiejack.horticulture.crafting.NetherStumpRecipe;
import uk.joshiejack.horticulture.crafting.SeedMakerRecipe;
import uk.joshiejack.horticulture.crafting.StumpRecipe;
import uk.joshiejack.horticulture.item.HorticultureItems;

import javax.annotation.Nonnull;
import java.util.function.Consumer;

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
        StumpRecipe.stump(Ingredient.of(HorticultureItems.BROWN_MUSHROOM_SPORES.get()), Items.BROWN_MUSHROOM, 50).unlocks("has_stump", has(HorticultureItemTags.STUMPS)).save(consumer, rl("brown_mushroom"));
        StumpRecipe.stump(Ingredient.of(HorticultureItems.RED_MUSHROOM_SPORES.get()), Items.RED_MUSHROOM, 30).unlocks("has_stump", has(HorticultureItemTags.STUMPS)).save(consumer, rl("red_mushroom"));
        NetherStumpRecipe.stump(Ingredient.of(HorticultureItems.WARPED_FUNGUS_SPORES.get()), Items.WARPED_FUNGUS, 25).unlocks("has_nether_stump", has(HorticultureItemTags.NETHER_STUMP)).save(consumer, rl("warped_fungus"));
        NetherStumpRecipe.stump(Ingredient.of(HorticultureItems.CRIMSON_FUNGUS_SPORES.get()), Items.CRIMSON_FUNGUS, 15).unlocks("has_nether_stump", has(HorticultureItemTags.NETHER_STUMP)).save(consumer, rl("crimson_fungus"));
//        if (HorticultureConfig.addRecipes) {
//            RecipeHelper helper = new RecipeHelper(event.getRegistry(), MODID);
//            helper.bowlRecipe(MEAL, CORNFLAKES, "cropCorn", Items.MILK_BUCKET);
//            helper.glassRecipe(DRINK, PINEAPPLE_JUICE, "cropPineapple");
//            helper.glassRecipe(DRINK, TOMATO_JUICE, "cropTomato");
//            helper.glassRecipe(DRINK, STRAWBERRY_MILK, "cropStrawberry", Items.MILK_BUCKET);
//            helper.glassRecipe(DRINK, GRAPE_JUICE, "cropGrapes");
//            helper.glassRecipe(DRINK, PEACH_JUICE, "cropPeach");
//            helper.glassRecipe(DRINK, BANANA_JUICE, "cropBanana");
//            helper.glassRecipe(DRINK, ORANGE_JUICE, "cropOrange");
//            helper.glassRecipe(DRINK, APPLE_JUICE, Items.APPLE);
//
//            helper.picklingJarRecipe(MEAL, PICKLED_CUCUMBER, "cropCucumber");
//
//            helper.bowlRecipe(MEAL, CORNFLAKES, "cropCorn", Items.MILK_BUCKET);
//            helper.bowlRecipe(MEAL, CANDIED_POTATO, "cropSweetPotato");
//            helper.bowlRecipe(MEAL, BOILED_SPINACH, "cropSpinach");
//            helper.bowlRecipe(MEAL, STIR_FRY, "cropCabbage");
//            helper.bowlRecipe(MEAL, PUMPKIN_STEW, Blocks.PUMPKIN);
//            helper.bowlRecipe(MEAL, SALAD, "cropOnion", "cropGreenPepper", "cropTomato");
//
//            helper.plateRecipe(MEAL, BAKED_CORN, "cropCorn");
//            helper.plateRecipe(MEAL, HAPPY_EGGPLANT, "cropEggplant");
//            helper.plateRecipe(MEAL, PICKLED_TURNIP, "cropTurnip");
//
//            if (HorticultureConfig.recipeWateringCan) {
//                helper.shapedRecipe("watering_can", new ItemStack(WATERING_CAN), "S  ", "SBS", " S ", 'S', "stone", 'B', Items.BUCKET);
//            }
//
//            if (HorticultureConfig.enableSeedMakerRecipe) {
//                helper.shapedRecipe("seed_maker", MACHINE.getStackFromEnum(SeedMakerBlock.Machine.SEED_MAKER), "WCW", "WRW", "WFW", 'C', CROP, 'W', "plankWood", 'R', Items.REDSTONE, 'F', Blocks.FURNACE);
//            }
//
//
//            if (HorticultureConfig.enableSprinklerRecipe) {
//                ResourceLocation sprinklers = new ResourceLocation(MODID, "sprinkler");
//                helper.shapedRecipe("sprinkler_old", sprinklers, SPRINKLER.getStackFromEnum(AbstractSprinklerBlock.Sprinkler.OLD), " L ", "LBL", " L ", 'L', "plankWood", 'B', new ItemStack(Items.DYE, 1, 15));
//                helper.shapedRecipe("sprinkler_iron", sprinklers, SPRINKLER.getStackFromEnum(AbstractSprinklerBlock.Sprinkler.IRON), " L ", "LBL", " L ", 'L', "ingotIron", 'B', new ItemStack(Items.DYE, 1, 15));
//            }
//
//            if (HorticultureConfig.enableMushroomLogRecipe) {
//                ResourceLocation mushroom_logs = new ResourceLocation(MODID, "mushroom_log");
//                Set<Block> mushrooms = Sets.newHashSet(Blocks.BROWN_MUSHROOM, Blocks.RED_MUSHROOM);
//                for (Block mushroom: mushrooms) {
//                    helper.shapedRecipe("mushroom_log_oak_" + mushroom.getRegistryName().getPath(), mushroom_logs, STUMP.getStackFromEnum(BlockPlanks.EnumType.OAK), "M", "L", 'L', new ItemStack(Blocks.LOG, 1, BlockPlanks.EnumType.OAK.getMetadata()), 'M', mushroom);
//                    helper.shapedRecipe("mushroom_log_spruce_" + mushroom.getRegistryName().getPath(), mushroom_logs, STUMP.getStackFromEnum(BlockPlanks.EnumType.SPRUCE), "M", "L", 'L', new ItemStack(Blocks.LOG, 1, BlockPlanks.EnumType.SPRUCE.getMetadata()), 'M', mushroom);
//                    helper.shapedRecipe("mushroom_log_birch_" + mushroom.getRegistryName().getPath(), mushroom_logs, STUMP.getStackFromEnum(BlockPlanks.EnumType.BIRCH), "M", "L", 'L', new ItemStack(Blocks.LOG, 1, BlockPlanks.EnumType.BIRCH.getMetadata()), 'M', mushroom);
//                    helper.shapedRecipe("mushroom_log_jungle_" + mushroom.getRegistryName().getPath(), mushroom_logs, STUMP.getStackFromEnum(BlockPlanks.EnumType.JUNGLE), "M", "L", 'L', new ItemStack(Blocks.LOG, 1, BlockPlanks.EnumType.JUNGLE.getMetadata()), 'M', mushroom);
//                    helper.shapedRecipe("mushroom_log_acacia_" + mushroom.getRegistryName().getPath(), mushroom_logs, STUMP.getStackFromEnum(BlockPlanks.EnumType.ACACIA), "M", "L", 'L', new ItemStack(Blocks.LOG, 1, BlockPlanks.EnumType.ACACIA.getMetadata()), 'M', mushroom);
//                    helper.shapedRecipe("mushroom_log_dark_oak_" + mushroom.getRegistryName().getPath(), mushroom_logs, STUMP.getStackFromEnum(BlockPlanks.EnumType.DARK_OAK), "M", "L", 'L', new ItemStack(Blocks.LOG, 1, BlockPlanks.EnumType.DARK_OAK.getMetadata()), 'M', mushroom);
//                }
//            }
//        }
    }
}
