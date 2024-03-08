package uk.joshiejack.horticulture.world.item.crafting;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.registries.DeferredHolder;
import org.jetbrains.annotations.NotNull;
import uk.joshiejack.horticulture.world.block.HorticultureBlocks;
import uk.joshiejack.penguinlib.world.item.crafting.AbstractSimplePenguinRecipe;

import javax.annotation.Nonnull;

public abstract class AbstractStumpRecipe <R extends AbstractStumpRecipe<?>> extends AbstractSimplePenguinRecipe<R, AbstractStumpRecipe.Serializer<R>, ItemStack> {
    protected final BlockState blockstate;
    protected final int growths;
    public AbstractStumpRecipe(DeferredHolder<RecipeType<?>, RecipeType<R>> recipeType, DeferredHolder<RecipeSerializer<?>, Serializer<R>> recipeSerializer,
                               Ingredient ingredient, ItemStack output, int growths) {
        super(recipeType, recipeSerializer, ingredient, output);
        this.blockstate = output.getItem() instanceof BlockItem ? ((BlockItem) output.getItem()).getBlock().defaultBlockState() : Blocks.AIR.defaultBlockState();
        this.growths = growths;
    }

    public int getMaxGrowths() {
        return growths;
    }

    public BlockState getState() {
        return blockstate;
    }

    @Override
    public @NotNull ItemStack getToastSymbol() {
        return HorticultureBlocks.OAK_STUMP.toStack();
    }

    public static class Serializer<T extends AbstractStumpRecipe<?>> implements RecipeSerializer<T> {
        public static <T extends AbstractStumpRecipe<?>> Codec<T> createCodec(AbstractStumpRecipe.Serializer.IRecipeFactory<T> factory) {
            return RecordCodecBuilder.create((instance) -> instance.group(
                    Ingredient.CODEC.fieldOf("ingredient").forGetter((p_311729_) -> p_311729_.ingredient),
                    ItemStack.ITEM_WITH_COUNT_CODEC.fieldOf("result").forGetter((p_311730_) -> p_311730_.output),
                    Codec.INT.optionalFieldOf("growths", 32).forGetter((p_311731_) -> p_311731_.growths)
            ).apply(instance, factory::create));
        }

        private final AbstractStumpRecipe.Serializer.IRecipeFactory<T> factory;
        private final Codec<T> codec;

        public Serializer(AbstractStumpRecipe.Serializer.IRecipeFactory<T> factory) {
            this.factory = factory;
            this.codec = createCodec(factory);
        }

        @Override
        public @NotNull Codec<T> codec() {
            return codec;
        }

        @Nonnull
        @Override
        public T fromNetwork(@Nonnull FriendlyByteBuf buffer) {
            Ingredient ingredient = Ingredient.fromNetwork(buffer);
            ItemStack itemstack = buffer.readItem();
            int growths = buffer.readInt();
            return factory.create(ingredient, itemstack, growths);
        }

        @Override
        public void toNetwork(@Nonnull FriendlyByteBuf buffer, @Nonnull T recipe) {
            recipe.ingredient.toNetwork(buffer);
            buffer.writeItem(recipe.output);
            buffer.writeInt(recipe.growths);
        }

        public interface IRecipeFactory<T extends AbstractStumpRecipe<?>> {
            T create(Ingredient input, ItemStack output, int growths);
        }
    }
}
