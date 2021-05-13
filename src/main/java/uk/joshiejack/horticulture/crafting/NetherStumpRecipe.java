package uk.joshiejack.horticulture.crafting;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import uk.joshiejack.horticulture.block.HorticultureBlocks;
import uk.joshiejack.horticulture.data.builders.StumpRecipeBuilder;

import javax.annotation.Nonnull;

public class NetherStumpRecipe extends AbstractStumpRecipe {
    public NetherStumpRecipe(ResourceLocation resource, Ingredient ingredient, ItemStack output, int growths) {
        super(HorticultureRegistries.NETHER_STUMP, HorticultureRegistries.NETHER_STUMP_SERIALIZER.get(), resource, ingredient, output, growths);
    }

    @Nonnull
    @Override
    public ItemStack getToastSymbol() {
        return new ItemStack(HorticultureBlocks.CRIMSON_STUMP.get());
    }

    public static StumpRecipeBuilder stump(Ingredient input, IItemProvider output, int growths) {
        return new StumpRecipeBuilder(HorticultureRegistries.NETHER_STUMP_SERIALIZER.get(), input, output, growths);
    }
}
