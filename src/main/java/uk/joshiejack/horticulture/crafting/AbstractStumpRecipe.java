package uk.joshiejack.horticulture.crafting;

import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import uk.joshiejack.horticulture.block.HorticultureBlocks;
import uk.joshiejack.penguinlib.item.crafting.SimplePenguinRecipe;

import javax.annotation.Nonnull;

public abstract class AbstractStumpRecipe extends SimplePenguinRecipe {
    protected final BlockState blockstate;
    protected final int growths;

    public AbstractStumpRecipe(IRecipeType<?> recipeType, IRecipeSerializer<?> recipeSerializer, ResourceLocation resource, Ingredient ingredient, ItemStack output, int growths) {
        super(recipeType, recipeSerializer, resource, ingredient, output);
        this.blockstate = ((BlockItem)output.getItem()).getBlock().defaultBlockState();
        this.growths = growths;
    }

    public int getMaxGrowths() {
        return growths;
    }

    @Nonnull
    @Override
    public ItemStack getToastSymbol() {
        return new ItemStack(HorticultureBlocks.OAK_STUMP.get());
    }
}
