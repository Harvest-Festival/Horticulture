package uk.joshiejack.horticulture.world.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.client.model.data.ModelData;
import net.neoforged.neoforge.client.model.data.ModelProperty;
import uk.joshiejack.horticulture.network.StumpUpdatePacket;
import uk.joshiejack.horticulture.world.block.AbstractStumpBlock;
import uk.joshiejack.horticulture.world.item.crafting.AbstractStumpRecipe;
import uk.joshiejack.penguinlib.network.PenguinNetwork;
import uk.joshiejack.penguinlib.world.block.entity.inventory.InventoryBlockEntity;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@SuppressWarnings("ConstantConditions")
public abstract class AbstractStumpBlockEntity<I extends AbstractStumpRecipe<I>> extends InventoryBlockEntity {
    private final RecipeType<I> recipeType;
    public static final ModelProperty<BlockState> MUSHROOM_BLOCKSTATE = new ModelProperty<>();
    private int harvests = 0;

    public AbstractStumpBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state, RecipeType<I> recipeType) {
        super(type, pos, state,1);
        this.recipeType = recipeType;
    }

    @Override
    public int getMaxStackSize() {
        return 1;
    }

    @Override
    @Nonnull
    public ModelData getModelData() {
        AbstractStumpRecipe<?> recipe = getRecipeResult(items.get(0));
        return ModelData.builder().with(MUSHROOM_BLOCKSTATE, recipe == null ? Blocks.AIR.defaultBlockState() : recipe.getState()).build();
    }

    @Override
    public boolean canPlaceItem(int slot, @Nonnull ItemStack stack) {
        return this.items.get(slot).isEmpty() && this.getRecipeResult(stack) != null;
    }

    @Nullable
    private I getRecipeResult(ItemStack stack) {
        for (RecipeHolder<I> recipe : level.getRecipeManager().getAllRecipesFor(recipeType)) {
            if (recipe.value().getIngredients().stream().allMatch(ing -> ing.test(stack)))
                return recipe.value();
        }

        return null;
    }

    @Nonnull
    @Override
    public ItemStack removeItem(int slot, int amount) {
        ItemStack inSlot = items.get(slot);
        if (inSlot.isEmpty() || getBlockState().getValue(AbstractStumpBlock.AGE) < 3) return ItemStack.EMPTY;
        else {
            harvests++;
            level.setBlock(worldPosition, getBlockState().setValue(AbstractStumpBlock.AGE, 0), 3);
            markUpdated(); //Reset the stage to 0 when we remove the item
            AbstractStumpRecipe<I> result = getRecipeResult(items.get(0));
            if (result == null) return ItemStack.EMPTY;
            else if (harvests > result.getMaxGrowths()) {
                ItemStack ret = super.removeItem(slot, amount);
                setChanged();
                return ret;
            }

            return result.assemble(this, level.registryAccess());
        }
    }

    public int getHarvests() {
        return harvests;
    }

    public void grow() {
        level.setBlock(worldPosition, getBlockState().cycle(AbstractStumpBlock.AGE), 3);
    }

    @OnlyIn(Dist.CLIENT)
    public void setMushroomData(Item item) {
        items.set(0, new ItemStack(item));
        requestModelDataUpdate();
        level.sendBlockUpdated(this.getBlockPos(), this.getBlockState(), this.getBlockState(), 3);
    }

    @Override
    public void setChanged() {
        super.setChanged();
        assert level != null;
        if (!level.isClientSide)
            PenguinNetwork.sendToNearby(this, new StumpUpdatePacket(worldPosition, items.get(0).getItem(), getBlockState().getValue(AbstractStumpBlock.AGE)));
    }

    @Override
    public void load( @Nonnull CompoundTag nbt) {
        harvests = nbt.getShort("Harvests");
        super.load(nbt);
    }

    @Override
    public void saveAdditional(CompoundTag nbt) {
        nbt.putShort("Harvests", (short) harvests);
        super.saveAdditional(nbt);
    }
}
