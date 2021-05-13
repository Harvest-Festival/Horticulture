package uk.joshiejack.horticulture.crafting;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import uk.joshiejack.horticulture.block.HorticultureBlocks;
import uk.joshiejack.penguinlib.data.generators.builders.SimplePenguinRecipeBuilder;
import uk.joshiejack.penguinlib.item.crafting.SimplePenguinRecipe;

import javax.annotation.Nonnull;
import java.util.Random;

public class SeedMakerRecipe extends SimplePenguinRecipe {
    private static final Random random = new Random(System.currentTimeMillis());

    public SeedMakerRecipe(ResourceLocation resource, Ingredient ingredient, ItemStack output) {
        super(HorticultureRegistries.SEED_MAKER, HorticultureRegistries.SEED_MAKER_SERIALIZER.get(), resource, ingredient, output);
    }

    @Override
    public boolean matches(IInventory inventory, @Nonnull World world) {
        return this.ingredient.test(inventory.getItem(0));
    }

    @Override
    public ItemStack assemble(@Nonnull IInventory inventory) {
        ItemStack result = super.assemble(inventory);
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

    public static SimplePenguinRecipeBuilder seedmaker(Ingredient input, IItemProvider output, int amount) {
        return new SimplePenguinRecipeBuilder(HorticultureRegistries.SEED_MAKER_SERIALIZER.get(), input, output, amount);
    }
}
