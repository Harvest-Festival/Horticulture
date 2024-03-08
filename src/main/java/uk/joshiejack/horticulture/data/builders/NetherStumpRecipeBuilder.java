package uk.joshiejack.horticulture.data.builders;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import uk.joshiejack.horticulture.world.item.crafting.HorticultureRecipes;
import uk.joshiejack.horticulture.world.item.crafting.NetherStumpRecipe;
import uk.joshiejack.penguinlib.data.generator.builder.SimplePenguinRegistryBuilder;

public class NetherStumpRecipeBuilder extends SimplePenguinRegistryBuilder.ItemOutput<NetherStumpRecipe> {
    public NetherStumpRecipeBuilder(Ingredient ingredient, ItemStack result, int maxGrowths) {
        super(HorticultureRecipes.NETHER_STUMP_SERIALIZER.get(), (i, o) -> new NetherStumpRecipe(i, o, maxGrowths), ingredient, result);
    }

    public static NetherStumpRecipeBuilder stump(Ingredient input, ItemStack output, int growths) {
        return new NetherStumpRecipeBuilder(input, output, growths);
    }

    public static NetherStumpRecipeBuilder stump(Ingredient input, ItemLike output, int growths) {
        return new NetherStumpRecipeBuilder(input, new ItemStack(output), growths);
    }
}
