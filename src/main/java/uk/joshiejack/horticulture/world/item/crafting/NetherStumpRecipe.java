package uk.joshiejack.horticulture.world.item.crafting;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.NotNull;
import uk.joshiejack.horticulture.world.block.HorticultureBlocks;

public class NetherStumpRecipe extends AbstractStumpRecipe<NetherStumpRecipe> {
    public NetherStumpRecipe(Ingredient ingredient, ItemStack output, int growths) {
        super(HorticultureRecipes.NETHER_STUMP, HorticultureRecipes.NETHER_STUMP_SERIALIZER, ingredient, output, growths);
    }

    @Override
    public @NotNull ItemStack getToastSymbol() {
        return HorticultureBlocks.CRIMSON_STUMP.toStack();
    }

    public static NetherStumpRecipe stump(Ingredient input, ItemStack output, int growths) {
        return new NetherStumpRecipe(input, output, growths);
    }
}
