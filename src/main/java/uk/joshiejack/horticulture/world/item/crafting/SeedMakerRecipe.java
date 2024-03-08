package uk.joshiejack.horticulture.world.item.crafting;

import net.minecraft.core.RegistryAccess;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import uk.joshiejack.horticulture.world.block.HorticultureBlocks;
import uk.joshiejack.penguinlib.data.generator.builder.SimplePenguinRecipeBuilder;
import uk.joshiejack.penguinlib.world.item.crafting.SimplePenguinRecipe;

import javax.annotation.Nonnull;
import java.util.Random;

public class SeedMakerRecipe extends SimplePenguinRecipe<SeedMakerRecipe> {
    private static final Random random = new Random(System.currentTimeMillis());

    public SeedMakerRecipe(Ingredient ingredient, ItemStack output) {
        super(HorticultureRecipes.SEED_MAKER, HorticultureRecipes.SEED_MAKER_SERIALIZER, ingredient, output);
    }

    @Override
    public boolean matches(Container inventory, @Nonnull Level world) {
        return this.ingredient.test(inventory.getItem(0));
    }

    @Override
    public ItemStack assemble(@Nonnull Container inventory, RegistryAccess registry) {
        ItemStack result = super.assemble(inventory, registry);
        int chance = random.nextInt(100);
        if (chance > 90) result.setCount(result.getCount() * 3);
        else if (chance > 30) result.setCount(result.getCount() * 2);
        return result;
    }

    @Nonnull
    @Override
    public ItemStack getToastSymbol() {
        return new ItemStack(HorticultureBlocks.SEED_MAKER.get());
    }

    public static SimplePenguinRecipeBuilder<?> seedmaker(Ingredient input, ItemStack output) {
        return new SimplePenguinRecipeBuilder<>(input, output, HorticultureRecipes.SEED_MAKER_SERIALIZER.get(), SeedMakerRecipe::new);
    }

}
