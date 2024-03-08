package uk.joshiejack.horticulture.world.item.crafting;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import uk.joshiejack.horticulture.Horticulture;
import uk.joshiejack.penguinlib.world.item.crafting.SimplePenguinRecipe;

public class HorticultureRecipes {
    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(Registries.RECIPE_TYPE, Horticulture.MODID);
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS = DeferredRegister.create(Registries.RECIPE_SERIALIZER, Horticulture.MODID);

    public static final DeferredHolder<RecipeType<?>, RecipeType<SeedMakerRecipe>> SEED_MAKER = RECIPE_TYPES.register("seed_maker", () -> RecipeType.simple(new ResourceLocation("seed_maker")));
    public static final DeferredHolder<RecipeType<?>, RecipeType<StumpRecipe>> STUMP = RECIPE_TYPES.register("stump", () -> RecipeType.simple(new ResourceLocation("stump")));
    public static final DeferredHolder<RecipeType<?>, RecipeType<NetherStumpRecipe>> NETHER_STUMP = RECIPE_TYPES.register("nether_stump", () -> RecipeType.simple(new ResourceLocation("nether_stump")));
    public static final DeferredHolder<RecipeSerializer<?>, SimplePenguinRecipe.Serializer<SeedMakerRecipe>> SEED_MAKER_SERIALIZER = SERIALIZERS.register("seed_maker", () -> new SimplePenguinRecipe.Serializer<>(SeedMakerRecipe::new));
    public static final DeferredHolder<RecipeSerializer<?>, AbstractStumpRecipe.Serializer<StumpRecipe>> STUMP_SERIALIZER = SERIALIZERS.register("stump", () -> new AbstractStumpRecipe.Serializer<>(StumpRecipe::new));
    public static final DeferredHolder<RecipeSerializer<?>, AbstractStumpRecipe.Serializer<NetherStumpRecipe>> NETHER_STUMP_SERIALIZER = SERIALIZERS.register("nether_stump", () -> new AbstractStumpRecipe.Serializer<>(NetherStumpRecipe::new));
}