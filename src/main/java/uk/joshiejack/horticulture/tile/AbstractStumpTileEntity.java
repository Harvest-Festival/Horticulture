package uk.joshiejack.horticulture.tile;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.model.data.IModelData;
import net.minecraftforge.client.model.data.ModelDataMap;
import net.minecraftforge.client.model.data.ModelProperty;
import uk.joshiejack.horticulture.crafting.AbstractStumpRecipe;
import uk.joshiejack.horticulture.network.StumpUpdatePacket;
import uk.joshiejack.penguinlib.network.PenguinNetwork;
import uk.joshiejack.penguinlib.tile.machine.AbstractIRecipeMachine;

import javax.annotation.Nonnull;

public abstract class AbstractStumpTileEntity <I extends AbstractStumpRecipe> extends AbstractIRecipeMachine<I> {
    public static final ModelProperty<BlockState> MUSHROOM_BLOCKSTATE = new ModelProperty<>();
    public static final ModelProperty<Integer> MUSHROOM_STAGE = new ModelProperty<>();
    private final ModelDataMap mushroomState = new ModelDataMap.Builder().withInitial(MUSHROOM_BLOCKSTATE, Blocks.AIR.defaultBlockState()).withInitial(MUSHROOM_STAGE, 0).build();
    private int stage = 0;
    private int harvests = 0;

    public AbstractStumpTileEntity(TileEntityType<?> type, IRecipeType<I> recipeType) {
        super(type, recipeType);
    }

    @Override
    @Nonnull
    public IModelData getModelData() {
        return mushroomState;
    }

    @Nonnull
    @Override
    public ItemStack removeItem(int slot, int amount) {
        ItemStack inSlot = items.get(slot);
        if (inSlot.isEmpty() || stage != 4) return ItemStack.EMPTY;
        else {
            harvests++;
            stage = 0;
            onStageChanged();
            markUpdated(); //Reset the stage to 0 when we remove the item
            AbstractStumpRecipe result = getRecipeResult(items.get(0));
            if (result == null) return ItemStack.EMPTY;
            else if (harvests > result.getMaxGrowths())
                return super.removeItem(slot, amount);
            return items.get(0).copy();
        }
    }

    public int getStage() {
        return stage;
    }

    public int getHarvests() {
        return harvests;
    }

    public void grow() {
        stage++; //Yes baby that is how we roll, we get this stage right up!
        onStageChanged();
    }

    @OnlyIn(Dist.CLIENT)
    public void setMushroomData(BlockState state, int stage) {
        mushroomState.setData(MUSHROOM_BLOCKSTATE, state);
        mushroomState.setData(MUSHROOM_STAGE, stage);
    }

    private void onStageChanged() {
        assert level != null;
        if (!level.isClientSide)
            PenguinNetwork.sendToNearby(new StumpUpdatePacket(worldPosition, items.get(0), stage), this);
        markUpdated();
    }

    @Override
    public void load(@Nonnull BlockState state, @Nonnull CompoundNBT nbt) {
        super.load(state, nbt);
        stage = nbt.getInt("Stage");
        if (stage > 4)
            stage = 4; //Reset
    }

    @Override
    @Nonnull
    public CompoundNBT save(CompoundNBT nbt) {
        nbt.putByte("Stage", (byte) stage);
        return super.save(nbt);
    }
}
