package uk.joshiejack.horticulture.crafting;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipe;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nonnull;

public class StumpRecipeSerializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<AbstractStumpRecipe> {
    private final int defaultMaxGrowths;
    private final StumpRecipeSerializer.IFactory factory;

    public StumpRecipeSerializer(StumpRecipeSerializer.IFactory factory) {
        this.factory = factory;
        this.defaultMaxGrowths = 32;
    }

    @Nonnull
    @Override
    public AbstractStumpRecipe fromJson(@Nonnull ResourceLocation rl, @Nonnull JsonObject json) {
        JsonElement jsonelement = JSONUtils.isArrayNode(json, "ingredient") ? JSONUtils.getAsJsonArray(json, "ingredient") : JSONUtils.getAsJsonObject(json, "ingredient");
        Ingredient ingredient = Ingredient.fromJson(jsonelement);
        if (!json.has("result"))
            throw new com.google.gson.JsonSyntaxException("Missing result, expected to find a string or object");
        ItemStack itemstack;
        if (json.get("result").isJsonObject())
            itemstack = ShapedRecipe.itemFromJson(JSONUtils.getAsJsonObject(json, "result"));
        else {
            String s1 = JSONUtils.getAsString(json, "result");
            ResourceLocation resourcelocation = new ResourceLocation(s1);
            itemstack = new ItemStack(ForgeRegistries.ITEMS.getValue(resourcelocation));
        }

        if (!(itemstack.getItem() instanceof BlockItem))
            throw new IllegalStateException("Item: " + itemstack.getDisplayName() + " is not a valid item for a stump recipe. It must be a block item.");
        int max = JSONUtils.getAsInt(json, "max growths", defaultMaxGrowths);
        return this.factory.create(rl, ingredient, itemstack, max);
    }

    @Override
    public AbstractStumpRecipe fromNetwork(@Nonnull ResourceLocation rl, @Nonnull PacketBuffer pb) {
        Ingredient ingredient = Ingredient.fromNetwork(pb);
        ItemStack itemstack = pb.readItem();
        int i = pb.readVarInt();
        return factory.create(rl, ingredient, itemstack, i);
    }

    @Override
    public void toNetwork(@Nonnull PacketBuffer pb, AbstractStumpRecipe recipe) {
        recipe.getIngredients().get(0).toNetwork(pb);
        pb.writeItem(recipe.getResultItem());
        pb.writeVarInt(recipe.getMaxGrowths());
    }

    interface IFactory {
        AbstractStumpRecipe create(ResourceLocation rl, Ingredient input, ItemStack output, int maxGrowths);
    }
}