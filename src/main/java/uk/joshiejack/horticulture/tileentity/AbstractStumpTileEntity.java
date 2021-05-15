package uk.joshiejack.horticulture.tileentity;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.model.data.IModelData;
import net.minecraftforge.client.model.data.ModelDataMap;
import net.minecraftforge.client.model.data.ModelProperty;
import uk.joshiejack.horticulture.block.StumpBlock;
import uk.joshiejack.horticulture.crafting.AbstractStumpRecipe;
import uk.joshiejack.horticulture.network.StumpUpdatePacket;
import uk.joshiejack.penguinlib.network.PenguinNetwork;
import uk.joshiejack.penguinlib.tile.inventory.AbstractInventoryTileEntity;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@SuppressWarnings("ConstantConditions")
public abstract class AbstractStumpTileEntity<I extends AbstractStumpRecipe> extends AbstractInventoryTileEntity {
    private final IRecipeType<I> recipeType;
    public static final ModelProperty<BlockState> MUSHROOM_BLOCKSTATE = new ModelProperty<>();
    private int harvests = 0;

    public AbstractStumpTileEntity(TileEntityType<?> type, IRecipeType<I> recipeType) {
        super(type, 1);
        this.recipeType = recipeType;
    }

    @Override
    public int getMaxStackSize() {
        return 1;
    }

    @Override
    @Nonnull
    public IModelData getModelData() {
        AbstractStumpRecipe recipe = getRecipeResult(items.get(0));
        return new ModelDataMap.Builder().withInitial(MUSHROOM_BLOCKSTATE, recipe == null ? Blocks.AIR.defaultBlockState() : recipe.getState()).build();
    }

    @Override
    public boolean canPlaceItem(int slot, @Nonnull ItemStack stack) {
        return this.items.get(slot).isEmpty() && this.getRecipeResult(stack) != null;
    }

    @Nullable
    private I getRecipeResult(ItemStack stack) {
        for (I recipe : level.getRecipeManager().getAllRecipesFor(recipeType)) {
            if (recipe.getIngredients().stream().allMatch(ing -> ing.test(stack)))
                return recipe;
        }

        return null;
    }

    @Nonnull
    @Override
    public ItemStack removeItem(int slot, int amount) {
        ItemStack inSlot = items.get(slot);
        if (inSlot.isEmpty() || getBlockState().getValue(StumpBlock.AGE) < 3) return ItemStack.EMPTY;
        else {
            harvests++;
            level.setBlock(worldPosition, getBlockState().setValue(StumpBlock.AGE, 0), 3);
            markUpdated(); //Reset the stage to 0 when we remove the item
            AbstractStumpRecipe result = getRecipeResult(items.get(0));
            if (result == null) return ItemStack.EMPTY;
            else if (harvests > result.getMaxGrowths()) {
                ItemStack ret = super.removeItem(slot, amount);
                setChanged();
                return ret;
            }

            return result.assemble(this);
        }
    }

    public int getHarvests() {
        return harvests;
    }

    public void grow() {
        level.setBlock(worldPosition, getBlockState().cycle(StumpBlock.AGE), 3);
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
            PenguinNetwork.sendToNearby(new StumpUpdatePacket(worldPosition, items.get(0)), this);
    }

    @Override
    public void load(@Nonnull BlockState state, @Nonnull CompoundNBT nbt) {
        harvests = nbt.getShort("Harvests");
        super.load(state, nbt);
    }

    @Nonnull
    @Override
    public CompoundNBT save(CompoundNBT nbt) {
        nbt.putShort("Harvests", (short) harvests);
        return super.save(nbt);
    }
}
