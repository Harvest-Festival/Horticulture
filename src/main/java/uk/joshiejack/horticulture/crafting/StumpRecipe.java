package uk.joshiejack.horticulture.crafting;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import uk.joshiejack.horticulture.block.HorticultureBlocks;
import uk.joshiejack.horticulture.data.builders.StumpRecipeBuilder;

import javax.annotation.Nonnull;

public class StumpRecipe extends AbstractStumpRecipe {
    public StumpRecipe(ResourceLocation resource, Ingredient ingredient, ItemStack output, int growths) {
        super(HorticultureRegistries.STUMP, HorticultureRegistries.STUMP_SERIALIZER.get(), resource, ingredient, output, growths);
    }

    @Nonnull
    @Override
    public ItemStack getToastSymbol() {
        return new ItemStack(HorticultureBlocks.OAK_STUMP.get());
    }

}
