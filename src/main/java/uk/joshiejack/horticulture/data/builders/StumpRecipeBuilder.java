package uk.joshiejack.horticulture.data.builders;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import uk.joshiejack.horticulture.world.item.crafting.HorticultureRecipes;
import uk.joshiejack.horticulture.world.item.crafting.StumpRecipe;
import uk.joshiejack.penguinlib.data.generator.builder.SimplePenguinRegistryBuilder;

public class StumpRecipeBuilder extends SimplePenguinRegistryBuilder.ItemOutput<StumpRecipe> {
    public StumpRecipeBuilder(Ingredient ingredient, ItemStack result, int maxGrowths) {
        super(HorticultureRecipes.STUMP_SERIALIZER.get(), (i, o) -> new StumpRecipe(i, o, maxGrowths), ingredient, result);
    }

    public static StumpRecipeBuilder stump(Ingredient input, ItemStack output, int growths) {
        return new StumpRecipeBuilder(input, output, growths);
    }

    public static StumpRecipeBuilder stump(Ingredient input, ItemLike output, int growths) {
        return new StumpRecipeBuilder(input, new ItemStack(output), growths);
    }
}
