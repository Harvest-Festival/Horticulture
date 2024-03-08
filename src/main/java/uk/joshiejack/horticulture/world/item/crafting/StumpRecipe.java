package uk.joshiejack.horticulture.world.item.crafting;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.NotNull;
import uk.joshiejack.horticulture.world.block.HorticultureBlocks;

public class StumpRecipe extends AbstractStumpRecipe<StumpRecipe> {
    public StumpRecipe(Ingredient ingredient, ItemStack output, int growths) {
        super(HorticultureRecipes.STUMP, HorticultureRecipes.STUMP_SERIALIZER, ingredient, output, growths);
    }

    @Override
    public @NotNull ItemStack getToastSymbol() {
        return HorticultureBlocks.CRIMSON_STUMP.toStack();
    }

    public static StumpRecipe stump(Ingredient input, ItemStack output, int growths) {
        return new StumpRecipe(input, output, growths);
    }
}
