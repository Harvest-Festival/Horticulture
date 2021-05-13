package uk.joshiejack.horticulture.data.builders;

import com.google.gson.JsonObject;
import net.minecraft.advancements.Advancement;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import uk.joshiejack.penguinlib.data.generators.builders.SimplePenguinRecipeBuilder;

public class StumpRecipeBuilder extends SimplePenguinRecipeBuilder {
    private final int maxGrowths;

    public StumpRecipeBuilder(IRecipeSerializer<?> serializer, Ingredient ingredient, IItemProvider provider, int maxGrowths) {
        super(serializer, ingredient, provider, 1);
        this.maxGrowths = maxGrowths;
    }

    @Override
    protected IFinishedRecipe accept(ResourceLocation resource) {
        assert this.result.getItemCategory() != null;
        return new Result(resource, this.type, this.ingredient, this.result, this.advancement,
                new ResourceLocation(resource.getNamespace(), "recipes/" + this.result.getItemCategory().getRecipeFolderName() + "/" + resource.getPath()), maxGrowths);
    }

    public static class Result extends SimplePenguinRecipeBuilder.Result {
        private final int maxGrowths;

        public Result(ResourceLocation rl, IRecipeSerializer<?> serializer, Ingredient ingredient, Item output,
                      Advancement.Builder advancementBuilder, ResourceLocation advancementID, int maxGrowths) {
            super(rl, serializer, ingredient, output, 1, advancementBuilder, advancementID);
            this.maxGrowths = maxGrowths;
        }

        @Override
        public void serializeRecipeData(JsonObject json) {
            super.serializeRecipeData(json);
            json.addProperty("max growths", maxGrowths);
        }
    }
}
